package com.spirit.rrhh.session;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.spirit.rrhh.entity.EmpleadoFormacionEJB;
import com.spirit.rrhh.entity.EmpleadoFormacionIf;
import com.spirit.rrhh.session.generated._EmpleadoFormacionSession;


@Stateless
public class EmpleadoFormacionSessionEJB extends _EmpleadoFormacionSession implements EmpleadoFormacionSessionRemote, EmpleadoFormacionSessionLocal  {

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public EmpleadoFormacionEJB registrarEmpleadoFormacion(EmpleadoFormacionIf modelEmpleadoFormacion) {
	   EmpleadoFormacionEJB empleadoFormacion = new EmpleadoFormacionEJB();
	   
	   empleadoFormacion.setEmpleadoId(modelEmpleadoFormacion.getEmpleadoId());
	   empleadoFormacion.setCiudadId(modelEmpleadoFormacion.getCiudadId());
	   empleadoFormacion.setFechaGraduacion(modelEmpleadoFormacion.getFechaGraduacion());
	   empleadoFormacion.setId(modelEmpleadoFormacion.getId());
	   empleadoFormacion.setInstitucion(modelEmpleadoFormacion.getInstitucion());
	   empleadoFormacion.setNivel(modelEmpleadoFormacion.getNivel());
	   empleadoFormacion.setTituloObtenido(modelEmpleadoFormacion.getTituloObtenido());
	   empleadoFormacion.setUltimoAnio(modelEmpleadoFormacion.getUltimoAnio());
	   
	   return empleadoFormacion;
   }

}
