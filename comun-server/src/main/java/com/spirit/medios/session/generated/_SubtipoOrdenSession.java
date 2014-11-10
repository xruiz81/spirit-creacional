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
public abstract class _SubtipoOrdenSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SubtipoOrdenSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.SubtipoOrdenIf addSubtipoOrden(com.spirit.medios.entity.SubtipoOrdenIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SubtipoOrdenEJB value = new SubtipoOrdenEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setTipoordenId(model.getTipoordenId());
      value.setTipoproveedorId(model.getTipoproveedorId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en subtipoOrden.", e);
			throw new GenericBusinessException(
					"Error al insertar información en subtipoOrden.");
      }
     
      return getSubtipoOrden(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSubtipoOrden(com.spirit.medios.entity.SubtipoOrdenIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SubtipoOrdenEJB data = new SubtipoOrdenEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setTipoordenId(model.getTipoordenId());
      data.setTipoproveedorId(model.getTipoproveedorId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en subtipoOrden.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en subtipoOrden.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSubtipoOrden(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SubtipoOrdenEJB data = manager.find(SubtipoOrdenEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en subtipoOrden.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en subtipoOrden.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.SubtipoOrdenIf getSubtipoOrden(java.lang.Long id) {
      return (SubtipoOrdenEJB)queryManagerLocal.find(SubtipoOrdenEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSubtipoOrdenList() {
	  return queryManagerLocal.singleClassList(SubtipoOrdenEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSubtipoOrdenList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SubtipoOrdenEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSubtipoOrdenListSize() {
      Query countQuery = manager.createQuery("select count(*) from SubtipoOrdenEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoOrdenById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SubtipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoOrdenByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SubtipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoOrdenByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(SubtipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoOrdenByTipoordenId(java.lang.Long tipoordenId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoordenId", tipoordenId);
		return queryManagerLocal.singleClassQueryList(SubtipoOrdenEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoOrdenByTipoproveedorId(java.lang.Long tipoproveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoproveedorId", tipoproveedorId);
		return queryManagerLocal.singleClassQueryList(SubtipoOrdenEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SubtipoOrdenIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSubtipoOrdenByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SubtipoOrdenEJB.class, aMap);      
    }

/////////////////
}
