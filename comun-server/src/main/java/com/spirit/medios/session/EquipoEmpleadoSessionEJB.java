package com.spirit.medios.session;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.client.Parametros;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaEJB;
import com.spirit.medios.entity.EquipoEmpleadoEJB;
import com.spirit.medios.entity.LogEquipoEmpleadoEJB;
import com.spirit.medios.session.generated._EquipoEmpleadoSession;

/**
 * The <code>EquipoEmpleadoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:14 $
 *
 */
@Stateless
public class EquipoEmpleadoSessionEJB extends _EquipoEmpleadoSession implements EquipoEmpleadoSessionRemote, EquipoEmpleadoSessionLocal {

	@PersistenceContext(unitName="spirit")
	private EntityManager manager; 
	
	@EJB 
	private LogEquipoEmpleadoSessionLocal logEquipoEmpleadoLocal;

	/*******************************************************************************************************************
	 *                                  B U S I N E S S   M E T H O D S
	 *******************************************************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findEquipoEmpleadoByEmpresaId(Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct ee from EquipoTrabajoEJB et, EquipoEmpleadoEJB ee where ee.equipoId = et.id and et.empresaId = " + idEmpresa;
		String orderByPart = "";
		orderByPart += " order by ee.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findEquipoEmpleadoByCodigoTipoOrdenAndByEmpresaId(String codigoTipoOrden, Long idEmpresa) throws GenericBusinessException {
		String queryString = "select distinct ee from EquipoTrabajoEJB et, EquipoEmpleadoEJB ee, TipoOrdenEJB tor" +
				" where ee.equipoId = et.id and et.tipoordenId = tor.id and tor.codigo = '" + codigoTipoOrden + "' and et.empresaId = " + idEmpresa;
		String orderByPart = "";
		orderByPart += " order by ee.id";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findEmpleadoByEquipoTrabajoId(Long idEquipoTrabajo) throws GenericBusinessException {
		//select distinct e.* from empleado e, equipo_empleado ee where ee.EQUIPO_ID = 40 and e.ID = ee.EMPLEADO_ID
		String queryString = "select distinct e from EmpleadoEJB e, EquipoEmpleadoEJB ee where ee.equipoId = " + idEquipoTrabajo + " and e.id = ee.empleadoId";
		String orderByPart = "";
		orderByPart += " order by e.nombres asc";
		queryString += orderByPart;
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarEquipoEmpleado(Long equipoTrabajoId, String usuario) throws GenericBusinessException {
		try {
			//primero guardo el registro eliminado en el log de equipo empleado
			EquipoEmpleadoEJB data = manager.find(EquipoEmpleadoEJB.class, equipoTrabajoId);
			LogEquipoEmpleadoEJB logData = registrarLogEquipoEmpleado(data, usuario);
			manager.persist(logData);
			//luego elimino el registro
			manager.remove(data);
			manager.flush();

		} catch (Exception e) {
			throw new GenericBusinessException("Error al eliminar información en EquipoTrabajo");
		}
	}
	
	public LogEquipoEmpleadoEJB registrarLogEquipoEmpleado(EquipoEmpleadoEJB equipoEmpleado, String usuario){
		LogEquipoEmpleadoEJB logEquipoEmpleado = new LogEquipoEmpleadoEJB();
		logEquipoEmpleado.setEmpleadoId(equipoEmpleado.getEmpleadoId());
		logEquipoEmpleado.setEquipoId(equipoEmpleado.getEquipoId());
		logEquipoEmpleado.setRol(equipoEmpleado.getRol());
		logEquipoEmpleado.setJefe(equipoEmpleado.getJefe());
		
		Timestamp fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
		logEquipoEmpleado.setLog("EQUIPO EMPLEADO ID: " + equipoEmpleado.getId().toString() + ", " +
				"ELIMINADO POR USUARIO: " + usuario + ", FECHA: " + fecha);
		
		return logEquipoEmpleado;
	}
}
