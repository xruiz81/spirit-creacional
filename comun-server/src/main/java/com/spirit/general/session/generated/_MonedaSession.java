package com.spirit.general.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.general.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _MonedaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_MonedaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.MonedaIf addMoneda(com.spirit.general.entity.MonedaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      MonedaEJB value = new MonedaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setSimbolo(model.getSimbolo());
      value.setPlural(model.getPlural());
      value.setSufijoCantidadLetras(model.getSufijoCantidadLetras());
      value.setPredeterminada(model.getPredeterminada());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en moneda.", e);
			throw new GenericBusinessException(
					"Error al insertar información en moneda.");
      }
     
      return getMoneda(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveMoneda(com.spirit.general.entity.MonedaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      MonedaEJB data = new MonedaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setSimbolo(model.getSimbolo());
      data.setPlural(model.getPlural());
      data.setSufijoCantidadLetras(model.getSufijoCantidadLetras());
      data.setPredeterminada(model.getPredeterminada());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en moneda.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en moneda.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteMoneda(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      MonedaEJB data = manager.find(MonedaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en moneda.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en moneda.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.MonedaIf getMoneda(java.lang.Long id) {
      return (MonedaEJB)queryManagerLocal.find(MonedaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMonedaList() {
	  return queryManagerLocal.singleClassList(MonedaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getMonedaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(MonedaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getMonedaListSize() {
      Query countQuery = manager.createQuery("select count(*) from MonedaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaBySimbolo(java.lang.String simbolo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("simbolo", simbolo);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaByPlural(java.lang.String plural) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("plural", plural);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaBySufijoCantidadLetras(java.lang.String sufijoCantidadLetras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("sufijoCantidadLetras", sufijoCantidadLetras);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaByPredeterminada(java.lang.String predeterminada) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("predeterminada", predeterminada);
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of MonedaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findMonedaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(MonedaEJB.class, aMap);      
    }

/////////////////
}
