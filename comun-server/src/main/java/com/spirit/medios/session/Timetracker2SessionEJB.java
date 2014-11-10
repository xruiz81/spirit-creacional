package com.spirit.medios.session;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.spirit.medios.entity.Timetracker2EJB;
import com.spirit.medios.entity.Timetracker2EmpleadoEJB;
import com.spirit.medios.entity.Timetracker2EmpleadoIf;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.session.generated._Timetracker2Session;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class Timetracker2SessionEJB extends _Timetracker2Session implements Timetracker2SessionRemote,Timetracker2SessionLocal  {

	private DecimalFormat formatoEntero = new DecimalFormat("000");
	@EJB private Timetracker2EmpleadoSessionLocal timetracker2EmpleadoLocal;
	
	
   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/

	public void procesarTimetracker2Coleccion(Timetracker2If actividad, List<Timetracker2EmpleadoIf> empleadoColeccion, List<Timetracker2EmpleadoIf> empleadoEliminadoColeccion) throws com.spirit.exception.GenericBusinessException{
		Timetracker2EJB timetracker = new Timetracker2EJB();
 		if(actividad.getId() == null){
 			timetracker = registrarTimetracker2(actividad, true);
 			manager.persist(timetracker);
 		}else{
 			timetracker = registrarTimetracker2(actividad, false);
 			manager.merge(timetracker);
 		}
 		
 		for(Timetracker2EmpleadoIf empleado : empleadoColeccion){
			if(empleado.getId() == null){
				empleado.setTimetracker2Id(timetracker.getPrimaryKey());
				Timetracker2EmpleadoEJB model = registrarTimetracker2Empleado(empleado, true);
				manager.persist(model);
			}else{
				Timetracker2EmpleadoEJB model = registrarTimetracker2Empleado(empleado, false);
				manager.merge(model);
			}
		}
		
		for(Timetracker2EmpleadoIf empleadoEliminado : empleadoEliminadoColeccion){
			if(empleadoEliminado.getId() != null){
				Timetracker2EmpleadoEJB model = manager.find(Timetracker2EmpleadoEJB.class, empleadoEliminado.getId());
				manager.remove(model);
				manager.flush();
			}				
		}
	}
	
	public Timetracker2EJB registrarTimetracker2(Timetracker2If actividad, boolean save){
		Timetracker2EJB model = new Timetracker2EJB();
			 		
 		if(save || (actividad.getCodigo() == null)){
			String codigo = getMaximoCodigoTimetracker2();
			if(codigo.equals("")){
				codigo = "001";
			}else{
				codigo = formatoEntero.format(Integer.valueOf(codigo) + 1); 
			}
			model.setCodigo(codigo);
		}else{
			model.setCodigo(actividad.getCodigo());
		}		
 		
 		model.setEstado(actividad.getEstado());
 		model.setId(actividad.getId());
 		model.setActividad(actividad.getActividad());
 		model.setEmpresaId(actividad.getEmpresaId());
 		
		return model;
	}
 	
 	public Timetracker2EmpleadoEJB registrarTimetracker2Empleado(Timetracker2EmpleadoIf empleado, boolean save){
 		Timetracker2EmpleadoEJB model = new Timetracker2EmpleadoEJB();
		
		model.setEmpleadoId(empleado.getEmpleadoId());
		model.setEstado(empleado.getEstado());
		model.setId(empleado.getId());
		model.setTimetracker2Id(empleado.getTimetracker2Id());
		
		return model;
	}
 	
 	public String getMaximoCodigoTimetracker2(){
		String queryString = "select max(tt.codigo) from Timetracker2EJB tt";
		Query query = manager.createQuery(queryString);
		String codigo = "";
		if(query.getResultList().get(0)!=null){
			codigo = query.getResultList().toString().substring(1).replaceAll("]","");
		}
		return codigo;
	}
 	
 	public void eliminarTimetracker2(Timetracker2If actividad) throws com.spirit.exception.GenericBusinessException{
 		Collection<Timetracker2EmpleadoIf> empleadosList = timetracker2EmpleadoLocal.findTimetracker2EmpleadoByTimetracker2Id(actividad.getId());
 		
 		for(Timetracker2EmpleadoIf empleadoEliminado : empleadosList){
 			Timetracker2EmpleadoEJB model = manager.find(Timetracker2EmpleadoEJB.class, empleadoEliminado.getId());
			manager.remove(model);
		}
 		
 		Timetracker2EJB model = manager.find(Timetracker2EJB.class, actividad.getId());
		manager.remove(model);
		manager.flush();
 	}
	
}
