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
public abstract class _EmpleadoFormacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_EmpleadoFormacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoFormacionIf addEmpleadoFormacion(com.spirit.rrhh.entity.EmpleadoFormacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      EmpleadoFormacionEJB value = new EmpleadoFormacionEJB();
      try {
      value.setId(model.getId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setNivel(model.getNivel());
      value.setUltimoAnio(model.getUltimoAnio());
      value.setFechaGraduacion(model.getFechaGraduacion());
      value.setTituloObtenido(model.getTituloObtenido());
      value.setInstitucion(model.getInstitucion());
      value.setCiudadId(model.getCiudadId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en empleadoFormacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en empleadoFormacion.");
      }
     
      return getEmpleadoFormacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveEmpleadoFormacion(com.spirit.rrhh.entity.EmpleadoFormacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      EmpleadoFormacionEJB data = new EmpleadoFormacionEJB();
      data.setId(model.getId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setNivel(model.getNivel());
      data.setUltimoAnio(model.getUltimoAnio());
      data.setFechaGraduacion(model.getFechaGraduacion());
      data.setTituloObtenido(model.getTituloObtenido());
      data.setInstitucion(model.getInstitucion());
      data.setCiudadId(model.getCiudadId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en empleadoFormacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en empleadoFormacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteEmpleadoFormacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      EmpleadoFormacionEJB data = manager.find(EmpleadoFormacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en empleadoFormacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en empleadoFormacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.rrhh.entity.EmpleadoFormacionIf getEmpleadoFormacion(java.lang.Long id) {
      return (EmpleadoFormacionEJB)queryManagerLocal.find(EmpleadoFormacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoFormacionList() {
	  return queryManagerLocal.singleClassList(EmpleadoFormacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getEmpleadoFormacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(EmpleadoFormacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getEmpleadoFormacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from EmpleadoFormacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByNivel(java.lang.String nivel) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nivel", nivel);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByUltimoAnio(java.lang.String ultimoAnio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ultimoAnio", ultimoAnio);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByFechaGraduacion(java.sql.Timestamp fechaGraduacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaGraduacion", fechaGraduacion);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByTituloObtenido(java.lang.String tituloObtenido) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tituloObtenido", tituloObtenido);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByInstitucion(java.lang.String institucion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("institucion", institucion);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByCiudadId(java.lang.Long ciudadId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ciudadId", ciudadId);
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of EmpleadoFormacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findEmpleadoFormacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(EmpleadoFormacionEJB.class, aMap);      
    }

/////////////////
}
