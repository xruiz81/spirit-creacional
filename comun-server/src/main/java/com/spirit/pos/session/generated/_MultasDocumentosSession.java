package com.spirit.pos.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.pos.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _MultasDocumentosSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MultasDocumentosSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.MultasDocumentosIf addMultasDocumentos(com.spirit.pos.entity.MultasDocumentosIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MultasDocumentosEJB value = new MultasDocumentosEJB();
      try {
      value.setId(model.getId());
      value.setMotivoId(model.getMotivoId());
      value.setDocumentoId(model.getDocumentoId());
      value.setValorMulta(model.getValorMulta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en multasDocumentos.", e);
			throw new GenericBusinessException(
					"Error al insertar información en multasDocumentos.");
      }
     
      return getMultasDocumentos(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMultasDocumentos(com.spirit.pos.entity.MultasDocumentosIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MultasDocumentosEJB data = new MultasDocumentosEJB();
      data.setId(model.getId());
      data.setMotivoId(model.getMotivoId());
      data.setDocumentoId(model.getDocumentoId());
      data.setValorMulta(model.getValorMulta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en multasDocumentos.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en multasDocumentos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMultasDocumentos(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MultasDocumentosEJB data = manager.find(MultasDocumentosEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en multasDocumentos.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en multasDocumentos.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.pos.entity.MultasDocumentosIf getMultasDocumentos(java.lang.Long id) {
      return (MultasDocumentosEJB)queryManagerLocal.find(MultasDocumentosEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMultasDocumentosList() {
	  return queryManagerLocal.singleClassList(MultasDocumentosEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMultasDocumentosList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MultasDocumentosEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMultasDocumentosListSize() {
      Query countQuery = manager.createQuery("select count(*) from MultasDocumentosEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMultasDocumentosById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MultasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMultasDocumentosByMotivoId(java.lang.Long motivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("motivoId", motivoId);
		return queryManagerLocal.singleClassQueryList(MultasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMultasDocumentosByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(MultasDocumentosEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMultasDocumentosByValorMulta(java.math.BigDecimal valorMulta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valorMulta", valorMulta);
		return queryManagerLocal.singleClassQueryList(MultasDocumentosEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MultasDocumentosIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMultasDocumentosByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MultasDocumentosEJB.class, aMap);      
    }

/////////////////
}
