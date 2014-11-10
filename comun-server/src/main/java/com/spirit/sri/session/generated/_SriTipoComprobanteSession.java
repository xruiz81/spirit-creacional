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
public abstract class _SriTipoComprobanteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriTipoComprobanteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriTipoComprobanteIf addSriTipoComprobante(com.spirit.sri.entity.SriTipoComprobanteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriTipoComprobanteEJB value = new SriTipoComprobanteEJB();
      try {
      value.setId(model.getId());
      value.setNombre(model.getNombre());
      value.setCodigo(model.getCodigo());
      value.setAnulacionTipoComprobanteId(model.getAnulacionTipoComprobanteId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriTipoComprobante.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriTipoComprobante.");
      }
     
      return getSriTipoComprobante(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriTipoComprobante(com.spirit.sri.entity.SriTipoComprobanteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriTipoComprobanteEJB data = new SriTipoComprobanteEJB();
      data.setId(model.getId());
      data.setNombre(model.getNombre());
      data.setCodigo(model.getCodigo());
      data.setAnulacionTipoComprobanteId(model.getAnulacionTipoComprobanteId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriTipoComprobante.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriTipoComprobante.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriTipoComprobante(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriTipoComprobanteEJB data = manager.find(SriTipoComprobanteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriTipoComprobante.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriTipoComprobante.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriTipoComprobanteIf getSriTipoComprobante(java.lang.Long id) {
      return (SriTipoComprobanteEJB)queryManagerLocal.find(SriTipoComprobanteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriTipoComprobanteList() {
	  return queryManagerLocal.singleClassList(SriTipoComprobanteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriTipoComprobanteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriTipoComprobanteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriTipoComprobanteListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriTipoComprobanteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoComprobanteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriTipoComprobanteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoComprobanteByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(SriTipoComprobanteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoComprobanteByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriTipoComprobanteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoComprobanteByAnulacionTipoComprobanteId(java.lang.Long anulacionTipoComprobanteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anulacionTipoComprobanteId", anulacionTipoComprobanteId);
		return queryManagerLocal.singleClassQueryList(SriTipoComprobanteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriTipoComprobanteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriTipoComprobanteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriTipoComprobanteEJB.class, aMap);      
    }

/////////////////
}
