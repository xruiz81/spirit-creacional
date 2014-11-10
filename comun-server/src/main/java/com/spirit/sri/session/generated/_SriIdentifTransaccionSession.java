package com.spirit.sri.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SriIdentifTransaccionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriIdentifTransaccionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIdentifTransaccionIf addSriIdentifTransaccion(com.spirit.sri.entity.SriIdentifTransaccionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriIdentifTransaccionEJB value = new SriIdentifTransaccionEJB();
      try {
      value.setId(model.getId());
      value.setIdTipoTransaccion(model.getIdTipoTransaccion());
      value.setIdTipoIdentificacion(model.getIdTipoIdentificacion());
      value.setCodigo(model.getCodigo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriIdentifTransaccion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriIdentifTransaccion.");
      }
     
      return getSriIdentifTransaccion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriIdentifTransaccion(com.spirit.sri.entity.SriIdentifTransaccionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriIdentifTransaccionEJB data = new SriIdentifTransaccionEJB();
      data.setId(model.getId());
      data.setIdTipoTransaccion(model.getIdTipoTransaccion());
      data.setIdTipoIdentificacion(model.getIdTipoIdentificacion());
      data.setCodigo(model.getCodigo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriIdentifTransaccion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriIdentifTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriIdentifTransaccion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriIdentifTransaccionEJB data = manager.find(SriIdentifTransaccionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriIdentifTransaccion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriIdentifTransaccion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIdentifTransaccionIf getSriIdentifTransaccion(java.lang.Long id) {
      return (SriIdentifTransaccionEJB)queryManagerLocal.find(SriIdentifTransaccionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIdentifTransaccionList() {
	  return queryManagerLocal.singleClassList(SriIdentifTransaccionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIdentifTransaccionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriIdentifTransaccionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriIdentifTransaccionListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriIdentifTransaccionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIdentifTransaccionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriIdentifTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIdentifTransaccionByIdTipoTransaccion(java.lang.Long idTipoTransaccion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idTipoTransaccion", idTipoTransaccion);
		return queryManagerLocal.singleClassQueryList(SriIdentifTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIdentifTransaccionByIdTipoIdentificacion(java.lang.Long idTipoIdentificacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("idTipoIdentificacion", idTipoIdentificacion);
		return queryManagerLocal.singleClassQueryList(SriIdentifTransaccionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIdentifTransaccionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriIdentifTransaccionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriIdentifTransaccionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIdentifTransaccionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriIdentifTransaccionEJB.class, aMap);      
    }

/////////////////
}
