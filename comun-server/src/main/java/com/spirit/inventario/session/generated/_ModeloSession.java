package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ModeloSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ModeloSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ModeloIf addModelo(com.spirit.inventario.entity.ModeloIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ModeloEJB value = new ModeloEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setMarcaId(model.getMarcaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en modelo.", e);
			throw new GenericBusinessException(
					"Error al insertar información en modelo.");
      }
     
      return getModelo(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveModelo(com.spirit.inventario.entity.ModeloIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ModeloEJB data = new ModeloEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setMarcaId(model.getMarcaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en modelo.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en modelo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteModelo(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ModeloEJB data = manager.find(ModeloEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en modelo.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en modelo.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ModeloIf getModelo(java.lang.Long id) {
      return (ModeloEJB)queryManagerLocal.find(ModeloEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getModeloList() {
	  return queryManagerLocal.singleClassList(ModeloEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getModeloList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ModeloEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getModeloListSize() {
      Query countQuery = manager.createQuery("select count(*) from ModeloEJB");
      List countQueryResult = countQuery.getResultList();
      Long countResult = (Long) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModeloById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ModeloEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModeloByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ModeloEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModeloByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ModeloEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModeloByMarcaId(java.lang.Long marcaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("marcaId", marcaId);
		return queryManagerLocal.singleClassQueryList(ModeloEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ModeloIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findModeloByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ModeloEJB.class, aMap);      
    }

/////////////////
}
