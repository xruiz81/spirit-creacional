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
public abstract class _BancoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_BancoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.BancoIf addBanco(com.spirit.general.entity.BancoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      BancoEJB value = new BancoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEstado(model.getEstado());
      value.setCodigoMulticash(model.getCodigoMulticash());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en banco.", e);
			throw new GenericBusinessException(
					"Error al insertar información en banco.");
      }
     
      return getBanco(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveBanco(com.spirit.general.entity.BancoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      BancoEJB data = new BancoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEstado(model.getEstado());
      data.setCodigoMulticash(model.getCodigoMulticash());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en banco.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en banco.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteBanco(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      BancoEJB data = manager.find(BancoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en banco.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en banco.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.BancoIf getBanco(java.lang.Long id) {
      return (BancoEJB)queryManagerLocal.find(BancoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getBancoList() {
	  return queryManagerLocal.singleClassList(BancoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getBancoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(BancoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getBancoListSize() {
      Query countQuery = manager.createQuery("select count(*) from BancoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBancoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(BancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBancoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(BancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBancoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(BancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBancoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(BancoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBancoByCodigoMulticash(java.lang.String codigoMulticash) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoMulticash", codigoMulticash);
		return queryManagerLocal.singleClassQueryList(BancoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of BancoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findBancoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(BancoEJB.class, aMap);      
    }

/////////////////
}
