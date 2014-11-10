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
import com.spirit.rrhh.entity.EmpleadoFamiliaresEJB;
import com.spirit.rrhh.entity.EmpleadoFamiliaresIf;
import com.spirit.rrhh.session.generated._EmpleadoFamiliaresSession;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;

/**
 * The <code>EmpleadoFamiliaresSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 *
 * @author  lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:22 $
 *
 */
@Stateless
public class EmpleadoFamiliaresSessionEJB extends _EmpleadoFamiliaresSession implements EmpleadoFamiliaresSessionRemote, EmpleadoFamiliaresSessionLocal  {

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public EmpleadoFamiliaresEJB registrarEmpleadoFamiliares(EmpleadoFamiliaresIf modelEmpleadoFamiliares) {
	   EmpleadoFamiliaresEJB empleadoFamiliares = new EmpleadoFamiliaresEJB();
	   
	   empleadoFamiliares.setEmpleadoId(modelEmpleadoFamiliares.getEmpleadoId());
	   empleadoFamiliares.setApellidos(modelEmpleadoFamiliares.getApellidos());
	   empleadoFamiliares.setCedulaIdentidad(modelEmpleadoFamiliares.getCedulaIdentidad());
	   empleadoFamiliares.setEmbarazo(modelEmpleadoFamiliares.getEmbarazo());
	   empleadoFamiliares.setFechaNacimiento(modelEmpleadoFamiliares.getFechaNacimiento());
	   empleadoFamiliares.setFechaParto(modelEmpleadoFamiliares.getFechaParto());
	   empleadoFamiliares.setId(modelEmpleadoFamiliares.getId());
	   empleadoFamiliares.setNivelEstudios(modelEmpleadoFamiliares.getNivelEstudios());
	   empleadoFamiliares.setNombreInstitucion(modelEmpleadoFamiliares.getNombreInstitucion());
	   empleadoFamiliares.setNombres(modelEmpleadoFamiliares.getNombres());
	   empleadoFamiliares.setOcupacion(modelEmpleadoFamiliares.getOcupacion());
	   empleadoFamiliares.setTipo(modelEmpleadoFamiliares.getTipo());
	   empleadoFamiliares.setTrabaja(modelEmpleadoFamiliares.getTrabaja());
	   empleadoFamiliares.setUltimoAnio(modelEmpleadoFamiliares.getUltimoAnio());
	   	   
	   return empleadoFamiliares;
   }

}
