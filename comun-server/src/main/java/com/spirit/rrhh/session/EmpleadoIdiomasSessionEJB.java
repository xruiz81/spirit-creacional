package com.spirit.rrhh.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.exception.GenericBusinessException;
import com.spirit.rrhh.entity.EmpleadoIdiomasEJB;
import com.spirit.rrhh.entity.EmpleadoIdiomasIf;
import com.spirit.rrhh.session.generated._EmpleadoIdiomasSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

@Stateless
public class EmpleadoIdiomasSessionEJB extends _EmpleadoIdiomasSession implements EmpleadoIdiomasSessionRemote, EmpleadoIdiomasSessionLocal  {

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public EmpleadoIdiomasEJB registrarEmpleadoIdiomas(EmpleadoIdiomasIf modelEmpleadoIdiomas) {
	   EmpleadoIdiomasEJB empleadoIdiomas = new EmpleadoIdiomasEJB();
	   
	   empleadoIdiomas.setEmpleadoId(modelEmpleadoIdiomas.getEmpleadoId());
	   empleadoIdiomas.setComprende(modelEmpleadoIdiomas.getComprende());
	   empleadoIdiomas.setEscribe(modelEmpleadoIdiomas.getEscribe());
	   empleadoIdiomas.setHabla(modelEmpleadoIdiomas.getHabla());
	   empleadoIdiomas.setId(modelEmpleadoIdiomas.getId());
	   empleadoIdiomas.setIdiomaId(modelEmpleadoIdiomas.getIdiomaId());
	   empleadoIdiomas.setLee(modelEmpleadoIdiomas.getLee());
	   
	   return empleadoIdiomas;
   }

}
