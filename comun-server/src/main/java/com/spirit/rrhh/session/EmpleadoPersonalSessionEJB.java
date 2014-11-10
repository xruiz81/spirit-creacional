package com.spirit.rrhh.session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoEJB;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.rrhh.entity.EmpleadoFamiliaresEJB;
import com.spirit.rrhh.entity.EmpleadoFamiliaresIf;
import com.spirit.rrhh.entity.EmpleadoFormacionEJB;
import com.spirit.rrhh.entity.EmpleadoFormacionIf;
import com.spirit.rrhh.entity.EmpleadoIdiomasEJB;
import com.spirit.rrhh.entity.EmpleadoIdiomasIf;
import com.spirit.rrhh.entity.EmpleadoPersonalEJB;
import com.spirit.rrhh.entity.EmpleadoPersonalIf;
import com.spirit.rrhh.session.generated._EmpleadoPersonalSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

@Stateless
public class EmpleadoPersonalSessionEJB extends _EmpleadoPersonalSession implements EmpleadoPersonalSessionRemote,EmpleadoPersonalSessionLocal  {

   @PersistenceContext(unitName = "spirit")
   private EntityManager manager;

   @EJB 
   private EmpleadoFamiliaresSessionLocal empleadoFamiliaresLocal;
   @EJB 
   private EmpleadoFormacionSessionLocal empleadoFormacionLocal;
   @EJB 
   private EmpleadoIdiomasSessionLocal empleadoIdiomasLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(EmpleadoPersonalSessionEJB.class);
   
   @Resource private SessionContext ctx;

	public void procesarEmpleadoPersonal(EmpleadoIf modelEmpleado, EmpleadoPersonalIf modelEmpleadoPersonal, List<EmpleadoFamiliaresIf> modelEmpleadoFamiliaresList, List<EmpleadoFormacionIf> modelEmpleadoFormacionList, List<EmpleadoIdiomasIf> modelEmpleadoIdiomasList) throws GenericBusinessException {
		try {
			EmpleadoIf empleado = registrarEmpleado(modelEmpleado);
			manager.merge(empleado);
			 
			EmpleadoPersonalIf empleadoPersonal = registrarEmpleadoPersonal(modelEmpleadoPersonal);
			manager.persist(empleadoPersonal);

			for (EmpleadoFamiliaresIf modelEmpleadoFamiliares : modelEmpleadoFamiliaresList) {
				modelEmpleadoFamiliares.setEmpleadoId(empleado.getPrimaryKey());
				EmpleadoFamiliaresIf empleadoFamiliares = empleadoFamiliaresLocal.registrarEmpleadoFamiliares(modelEmpleadoFamiliares);
				manager.persist(empleadoFamiliares);
			}
			
			for (EmpleadoFormacionIf modelEmpleadoFormacion : modelEmpleadoFormacionList) {
				modelEmpleadoFormacion.setEmpleadoId(empleado.getPrimaryKey());
				EmpleadoFormacionIf empleadoFormacion = empleadoFormacionLocal.registrarEmpleadoFormacion(modelEmpleadoFormacion);
				manager.persist(empleadoFormacion);
			}
			
			for (EmpleadoIdiomasIf modelEmpleadoIdiomas : modelEmpleadoIdiomasList) {
				modelEmpleadoIdiomas.setEmpleadoId(empleado.getPrimaryKey());
				EmpleadoIdiomasIf empleadoIdiomas = empleadoIdiomasLocal.registrarEmpleadoIdiomas(modelEmpleadoIdiomas);
				manager.persist(empleadoIdiomas);
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al guardar información en EmpleadoPersonalEJB", e);
			throw new GenericBusinessException("Error al insertar información en Empleado, EmpleadoPersonal, EmpleadoFamiliares, EmpleadoFormacion, EmpleadoIdiomas.");
		}
	}
	
	public void actualizarEmpleadoPersonal(EmpleadoIf modelEmpleado, EmpleadoPersonalIf modelEmpleadoPersonal, List<EmpleadoFamiliaresIf> modelEmpleadoFamiliaresList, List<EmpleadoFormacionIf> modelEmpleadoFormacionList, List<EmpleadoIdiomasIf> modelEmpleadoIdiomasList
			,List<EmpleadoFamiliaresIf> modelEmpleadoFamiliaresRemovidosList, List<EmpleadoFormacionIf> modelEmpleadoFormacionRemovidosList, List<EmpleadoIdiomasIf> modelEmpleadoIdiomasRemovidosList) throws GenericBusinessException {
		try {
			EmpleadoIf empleado = registrarEmpleado(modelEmpleado);
			manager.merge(empleado);
			 
			EmpleadoPersonalIf empleadoPersonal = registrarEmpleadoPersonal(modelEmpleadoPersonal);
			manager.merge(empleadoPersonal);
			
			for (EmpleadoFamiliaresIf familiar: modelEmpleadoFamiliaresRemovidosList){
				EmpleadoFamiliaresEJB data = manager.find(EmpleadoFamiliaresEJB.class, familiar.getId());
				manager.remove(data);
			}

			for (EmpleadoFormacionIf formacion: modelEmpleadoFormacionRemovidosList){
				EmpleadoFormacionEJB data = manager.find(EmpleadoFormacionEJB.class, formacion.getId());
				manager.remove(data);
			}

			for (EmpleadoIdiomasIf idioma: modelEmpleadoIdiomasRemovidosList){
				EmpleadoIdiomasEJB data = manager.find(EmpleadoIdiomasEJB.class, idioma.getId());
				manager.remove(data);
			}

			for (EmpleadoFamiliaresIf modelEmpleadoFamiliares : modelEmpleadoFamiliaresList) {
				modelEmpleadoFamiliares.setEmpleadoId(empleado.getPrimaryKey());
				EmpleadoFamiliaresIf empleadoFamiliares = empleadoFamiliaresLocal.registrarEmpleadoFamiliares(modelEmpleadoFamiliares);
				manager.merge(empleadoFamiliares);
			}
			
			for (EmpleadoFormacionIf modelEmpleadoFormacion : modelEmpleadoFormacionList) {
				modelEmpleadoFormacion.setEmpleadoId(empleado.getPrimaryKey());
				EmpleadoFormacionIf empleadoFormacion = empleadoFormacionLocal.registrarEmpleadoFormacion(modelEmpleadoFormacion);
				manager.merge(empleadoFormacion);
			}
			
			for (EmpleadoIdiomasIf modelEmpleadoIdiomas : modelEmpleadoIdiomasList) {
				modelEmpleadoIdiomas.setEmpleadoId(empleado.getPrimaryKey());
				EmpleadoIdiomasIf empleadoIdiomas = empleadoIdiomasLocal.registrarEmpleadoIdiomas(modelEmpleadoIdiomas);
				manager.merge(empleadoIdiomas);
			}
			
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al actualizar información en EmpleadoPersonalEJB", e);
			throw new GenericBusinessException("Error al actualizar información en Empleado, EmpleadoPersonal, EmpleadoFamiliares, EmpleadoFormacion, EmpleadoIdiomas.");
		}
	}
	
	private EmpleadoEJB registrarEmpleado(EmpleadoIf modelEmpleado) {
		EmpleadoEJB empleado = new EmpleadoEJB();
		
		empleado.setApellidos(modelEmpleado.getApellidos());
		empleado.setCelular(modelEmpleado.getCelular());
		empleado.setCodigo(modelEmpleado.getCodigo());
		empleado.setDepartamentoId(modelEmpleado.getDepartamentoId());
		empleado.setDireccionDomicilio(modelEmpleado.getDireccionDomicilio());
		empleado.setEmailOficina(modelEmpleado.getEmailOficina());
		empleado.setEmpresaId(modelEmpleado.getEmpresaId());
		empleado.setEstado(modelEmpleado.getEstado());
		empleado.setExtensionOficina(modelEmpleado.getExtensionOficina());
		empleado.setId(modelEmpleado.getId());
		empleado.setIdentificacion(modelEmpleado.getIdentificacion());
		empleado.setJefeId(modelEmpleado.getJefeId());
		empleado.setNivel(modelEmpleado.getNivel());
		empleado.setNombres(modelEmpleado.getNombres());
		empleado.setOficinaId(modelEmpleado.getOficinaId());
		empleado.setProfesion(modelEmpleado.getProfesion());
		empleado.setTelefonoDomicilio(modelEmpleado.getTelefonoDomicilio());
		empleado.setTipocontratoId(modelEmpleado.getTipocontratoId());
		empleado.setTipoempleadoId(modelEmpleado.getTipoempleadoId());
		empleado.setTipoidentificacionId(modelEmpleado.getTipoidentificacionId());
		empleado.setBancoId(modelEmpleado.getBancoId());
		empleado.setTipoCuenta(modelEmpleado.getTipoCuenta());
		empleado.setNumeroCuenta(modelEmpleado.getNumeroCuenta());
						
		return empleado;
	}
	
	private EmpleadoPersonalEJB registrarEmpleadoPersonal(EmpleadoPersonalIf modelEmpleadoPersonal) {
		EmpleadoPersonalEJB empleadoPersonal = new EmpleadoPersonalEJB();
		
		empleadoPersonal.setId(modelEmpleadoPersonal.getId());
		empleadoPersonal.setAfiliacionIess(modelEmpleadoPersonal.getAfiliacionIess());
		empleadoPersonal.setCanton(modelEmpleadoPersonal.getCanton());
		empleadoPersonal.setCasoEmergencia(modelEmpleadoPersonal.getCasoEmergencia());
		empleadoPersonal.setCedulaIdentidad(modelEmpleadoPersonal.getCedulaIdentidad());
		empleadoPersonal.setCiudadEmergenciaId(modelEmpleadoPersonal.getCiudadEmergenciaId());
		empleadoPersonal.setCiudadId(modelEmpleadoPersonal.getCiudadId());
		empleadoPersonal.setColorOjos(modelEmpleadoPersonal.getColorOjos());
		empleadoPersonal.setColorPelo(modelEmpleadoPersonal.getColorPelo());
		empleadoPersonal.setColorPiel(modelEmpleadoPersonal.getColorPiel());
		empleadoPersonal.setDireccionEmergencia(modelEmpleadoPersonal.getDireccionEmergencia());
		empleadoPersonal.setEmpleadoId(modelEmpleadoPersonal.getEmpleadoId());
		empleadoPersonal.setEstadoCivil(modelEmpleadoPersonal.getEstadoCivil());
		empleadoPersonal.setEstatura(modelEmpleadoPersonal.getEstatura());
		empleadoPersonal.setExestudianteCtt(modelEmpleadoPersonal.getExestudianteCtt());
		empleadoPersonal.setFechaNacimiento(modelEmpleadoPersonal.getFechaNacimiento());
		empleadoPersonal.setLibretaMilitar(modelEmpleadoPersonal.getLibretaMilitar());
		empleadoPersonal.setNumeroCalzado(modelEmpleadoPersonal.getNumeroCalzado());
		empleadoPersonal.setPaisId(modelEmpleadoPersonal.getPaisId());
		empleadoPersonal.setParroquia(modelEmpleadoPersonal.getParroquia());
		empleadoPersonal.setPeso(modelEmpleadoPersonal.getPeso());
		empleadoPersonal.setProvinciaId(modelEmpleadoPersonal.getProvinciaId());
		empleadoPersonal.setSenasParticulares(modelEmpleadoPersonal.getSenasParticulares());
		empleadoPersonal.setSexo(modelEmpleadoPersonal.getSexo());
		empleadoPersonal.setTallaCamisa(modelEmpleadoPersonal.getTallaCamisa());
		empleadoPersonal.setTallaPantalon(modelEmpleadoPersonal.getTallaPantalon());
		empleadoPersonal.setTelefonoEmergencia(modelEmpleadoPersonal.getTelefonoEmergencia());
		empleadoPersonal.setTipoSangre(modelEmpleadoPersonal.getTipoSangre());
		empleadoPersonal.setTitulo(modelEmpleadoPersonal.getTitulo());
						
		return empleadoPersonal;
	}
	
	public void eliminarEmpleadoPersonal(Long empleadoId) throws GenericBusinessException {
		try {
			if(!findEmpleadoPersonalByEmpleadoId(empleadoId).isEmpty()){
				EmpleadoPersonalIf empleadoPersonal = (EmpleadoPersonalIf) findEmpleadoPersonalByEmpleadoId(empleadoId).iterator().next();
				EmpleadoPersonalEJB data = manager.find(EmpleadoPersonalEJB.class, empleadoPersonal.getId());
				
				eliminarReferencias(empleadoId);

				manager.remove(data);
				manager.flush();
			}
		} catch (Exception e) {
			ctx.setRollbackOnly();
			log.error("Error al eliminar información en EmpleadoPersonalEJB.", e);
			throw new GenericBusinessException("Error al eliminar información en EmpleadoPersonalEJB");
		}
	}
	
	private void eliminarReferencias(Long empleadoId) throws GenericBusinessException {
		Collection<EmpleadoFamiliaresIf> modelEmpleadoFamilaresList = empleadoFamiliaresLocal.findEmpleadoFamiliaresByEmpleadoId(empleadoId);
		Collection<EmpleadoFormacionIf> modelEmpleadoFormacionList = empleadoFormacionLocal.findEmpleadoFormacionByEmpleadoId(empleadoId);
		Collection<EmpleadoIdiomasIf> modelEmpleadoIdiomasList = empleadoIdiomasLocal.findEmpleadoIdiomasByEmpleadoId(empleadoId);
		
		for (EmpleadoFamiliaresIf modelEmpleadoFamiliares : modelEmpleadoFamilaresList) {
			manager.remove(modelEmpleadoFamiliares);
		}
		for (EmpleadoFormacionIf modelEmpleadoFormacion : modelEmpleadoFormacionList) {
			manager.remove(modelEmpleadoFormacion);
		}
		for (EmpleadoIdiomasIf modelEmpleadoIdiomas : modelEmpleadoIdiomasList) {
			manager.remove(modelEmpleadoIdiomas);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findEmpleadoPersonalByFechaInicioAndFechaFin(Timestamp fechaInicio,Timestamp fechaFin)
	throws GenericBusinessException{
		//select * from EMPLEADO_PERSONAL ep where ep.fecha_nacimiento BETWEEN '1978-04-01' and '1978-04-30'
		String queryString = "select distinct e, ep from EmpleadoEJB e, EmpleadoPersonalEJB ep where e.id = ep.empleadoId and ep.fechaNacimiento between :fechaInicio and :fechaFin ";
		Query query = manager.createQuery(queryString);
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection findEmpleadoAndEmpleadoPersonalActivos() throws GenericBusinessException{
		String queryString = "select distinct e, ep, c from EmpleadoEJB e, EmpleadoPersonalEJB ep, ContratoEJB c where e.id = ep.empleadoId and e.id = c.empleadoId and e.estado = 'A' and c.estado = 'A'";
		Query query = manager.createQuery(queryString);
        return query.getResultList();
        
	}
	
}
