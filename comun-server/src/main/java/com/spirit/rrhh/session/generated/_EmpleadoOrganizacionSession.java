package com.spirit.rrhh.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.rrhh.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _EmpleadoOrganizacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoOrganizacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoOrganizacionIf addEmpleadoOrganizacion(com.spirit.rrhh.entity.EmpleadoOrganizacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoOrganizacionEJB value = new EmpleadoOrganizacionEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setDepartamento(model.getDepartamento());
      value.setTipoEmpleadoId(model.getTipoEmpleadoId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setDescripcion(model.getDescripcion());
      value.setArchivoAdjunto(model.getArchivoAdjunto());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoOrganizacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoOrganizacion.");
      }
     
      return getEmpleadoOrganizacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoOrganizacion(com.spirit.rrhh.entity.EmpleadoOrganizacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoOrganizacionEJB data = new EmpleadoOrganizacionEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setDepartamento(model.getDepartamento());
      data.setTipoEmpleadoId(model.getTipoEmpleadoId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setDescripcion(model.getDescripcion());
      data.setArchivoAdjunto(model.getArchivoAdjunto());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoOrganizacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoOrganizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoOrganizacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoOrganizacionEJB data = manager.find(EmpleadoOrganizacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoOrganizacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoOrganizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoOrganizacionIf getEmpleadoOrganizacion(java.lang.Long id) {
      return (EmpleadoOrganizacionEJB)queryManagerLocal.find(EmpleadoOrganizacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoOrganizacionList() {
	  return queryManagerLocal.singleClassList(EmpleadoOrganizacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoOrganizacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoOrganizacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoOrganizacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoOrganizacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByDepartamento(java.lang.Long departamento) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("departamento", departamento);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByTipoEmpleadoId(java.lang.Long tipoEmpleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoEmpleadoId", tipoEmpleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByFechaInicio(java.sql.Timestamp fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByFechaFin(java.sql.Timestamp fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByArchivoAdjunto(java.lang.String archivoAdjunto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("archivoAdjunto", archivoAdjunto);
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoOrganizacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoOrganizacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoOrganizacionEJB.class, aMap);      
    }

/////////////////
}
