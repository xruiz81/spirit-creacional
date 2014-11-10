package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PropuestaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PropuestaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PropuestaIf addPropuesta(com.spirit.medios.entity.PropuestaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PropuestaEJB value = new PropuestaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setOrdentrabajoId(model.getOrdentrabajoId());
      value.setUsuarioId(model.getUsuarioId());
      value.setFecha(model.getFecha());
      value.setValor(model.getValor());
      value.setObservacion(model.getObservacion());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en propuesta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en propuesta.");
      }
     
      return getPropuesta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePropuesta(com.spirit.medios.entity.PropuestaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PropuestaEJB data = new PropuestaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setOrdentrabajoId(model.getOrdentrabajoId());
      data.setUsuarioId(model.getUsuarioId());
      data.setFecha(model.getFecha());
      data.setValor(model.getValor());
      data.setObservacion(model.getObservacion());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en propuesta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en propuesta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePropuesta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PropuestaEJB data = manager.find(PropuestaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en propuesta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en propuesta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PropuestaIf getPropuesta(java.lang.Long id) {
      return (PropuestaEJB)queryManagerLocal.find(PropuestaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPropuestaList() {
	  return queryManagerLocal.singleClassList(PropuestaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPropuestaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PropuestaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPropuestaListSize() {
      Query countQuery = manager.createQuery("select count(*) from PropuestaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByOrdentrabajoId(java.lang.Long ordentrabajoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ordentrabajoId", ordentrabajoId);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByFecha(java.sql.Date fecha) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fecha", fecha);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByValor(java.math.BigDecimal valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByObservacion(java.lang.String observacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("observacion", observacion);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PropuestaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPropuestaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PropuestaEJB.class, aMap);      
    }

/////////////////
}
