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
public abstract class _PrensaFormatoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PrensaFormatoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaFormatoIf addPrensaFormato(com.spirit.medios.entity.PrensaFormatoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PrensaFormatoEJB value = new PrensaFormatoEJB();
      try {
      value.setId(model.getId());
      value.setClienteId(model.getClienteId());
      value.setCodigo(model.getCodigo());
      value.setFormato(model.getFormato());
      value.setAnchoColumnas(model.getAnchoColumnas());
      value.setAltoModulos(model.getAltoModulos());
      value.setAnchoCm(model.getAnchoCm());
      value.setAltoCm(model.getAltoCm());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en prensaFormato.", e);
			throw new GenericBusinessException(
					"Error al insertar información en prensaFormato.");
      }
     
      return getPrensaFormato(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePrensaFormato(com.spirit.medios.entity.PrensaFormatoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PrensaFormatoEJB data = new PrensaFormatoEJB();
      data.setId(model.getId());
      data.setClienteId(model.getClienteId());
      data.setCodigo(model.getCodigo());
      data.setFormato(model.getFormato());
      data.setAnchoColumnas(model.getAnchoColumnas());
      data.setAltoModulos(model.getAltoModulos());
      data.setAnchoCm(model.getAnchoCm());
      data.setAltoCm(model.getAltoCm());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en prensaFormato.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en prensaFormato.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePrensaFormato(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PrensaFormatoEJB data = manager.find(PrensaFormatoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en prensaFormato.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en prensaFormato.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.PrensaFormatoIf getPrensaFormato(java.lang.Long id) {
      return (PrensaFormatoEJB)queryManagerLocal.find(PrensaFormatoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaFormatoList() {
	  return queryManagerLocal.singleClassList(PrensaFormatoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPrensaFormatoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PrensaFormatoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPrensaFormatoListSize() {
      Query countQuery = manager.createQuery("select count(*) from PrensaFormatoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByFormato(java.lang.String formato) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formato", formato);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByAnchoColumnas(java.math.BigDecimal anchoColumnas) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anchoColumnas", anchoColumnas);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByAltoModulos(java.math.BigDecimal altoModulos) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("altoModulos", altoModulos);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByAnchoCm(java.math.BigDecimal anchoCm) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("anchoCm", anchoCm);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByAltoCm(java.math.BigDecimal altoCm) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("altoCm", altoCm);
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PrensaFormatoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPrensaFormatoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PrensaFormatoEJB.class, aMap);      
    }

/////////////////
}
