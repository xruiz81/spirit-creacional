package com.spirit.contabilidad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.contabilidad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _TipoCuentaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoCuentaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.TipoCuentaIf addTipoCuenta(com.spirit.contabilidad.entity.TipoCuentaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoCuentaEJB value = new TipoCuentaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setDebehaber(model.getDebehaber());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoCuenta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoCuenta.");
      }
     
      return getTipoCuenta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoCuenta(com.spirit.contabilidad.entity.TipoCuentaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoCuentaEJB data = new TipoCuentaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setDebehaber(model.getDebehaber());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoCuenta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoCuenta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoCuentaEJB data = manager.find(TipoCuentaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoCuenta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.contabilidad.entity.TipoCuentaIf getTipoCuenta(java.lang.Long id) {
      return (TipoCuentaEJB)queryManagerLocal.find(TipoCuentaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoCuentaList() {
	  return queryManagerLocal.singleClassList(TipoCuentaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoCuentaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoCuentaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoCuentaListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoCuentaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoCuentaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoCuentaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoCuentaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoCuentaByDebehaber(java.lang.String debehaber) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("debehaber", debehaber);
		return queryManagerLocal.singleClassQueryList(TipoCuentaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoCuentaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoCuentaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoCuentaEJB.class, aMap);      
    }

/////////////////
}
