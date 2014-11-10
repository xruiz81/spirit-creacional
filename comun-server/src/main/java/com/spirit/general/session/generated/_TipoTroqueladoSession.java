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
public abstract class _TipoTroqueladoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoTroqueladoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoTroqueladoIf addTipoTroquelado(com.spirit.general.entity.TipoTroqueladoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoTroqueladoEJB value = new TipoTroqueladoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setDescripcion(model.getDescripcion());
      value.setNumeroSeccionesHoja(model.getNumeroSeccionesHoja());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoTroquelado.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoTroquelado.");
      }
     
      return getTipoTroquelado(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoTroquelado(com.spirit.general.entity.TipoTroqueladoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoTroqueladoEJB data = new TipoTroqueladoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setDescripcion(model.getDescripcion());
      data.setNumeroSeccionesHoja(model.getNumeroSeccionesHoja());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoTroquelado.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoTroquelado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoTroquelado(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoTroqueladoEJB data = manager.find(TipoTroqueladoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoTroquelado.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoTroquelado.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoTroqueladoIf getTipoTroquelado(java.lang.Long id) {
      return (TipoTroqueladoEJB)queryManagerLocal.find(TipoTroqueladoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoTroqueladoList() {
	  return queryManagerLocal.singleClassList(TipoTroqueladoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoTroqueladoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoTroqueladoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoTroqueladoListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoTroqueladoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoTroqueladoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoTroqueladoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoTroqueladoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoTroqueladoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoTroqueladoByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(TipoTroqueladoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoTroqueladoByNumeroSeccionesHoja(java.lang.Integer numeroSeccionesHoja) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("numeroSeccionesHoja", numeroSeccionesHoja);
		return queryManagerLocal.singleClassQueryList(TipoTroqueladoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoTroqueladoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoTroqueladoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoTroqueladoEJB.class, aMap);      
    }

/////////////////
}
