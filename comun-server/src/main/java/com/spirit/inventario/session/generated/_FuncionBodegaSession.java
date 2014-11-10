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
public abstract class _FuncionBodegaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FuncionBodegaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.FuncionBodegaIf addFuncionBodega(com.spirit.inventario.entity.FuncionBodegaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FuncionBodegaEJB value = new FuncionBodegaEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en funcionBodega.", e);
			throw new GenericBusinessException(
					"Error al insertar información en funcionBodega.");
      }
     
      return getFuncionBodega(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFuncionBodega(com.spirit.inventario.entity.FuncionBodegaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FuncionBodegaEJB data = new FuncionBodegaEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en funcionBodega.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en funcionBodega.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFuncionBodega(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FuncionBodegaEJB data = manager.find(FuncionBodegaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en funcionBodega.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en funcionBodega.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.FuncionBodegaIf getFuncionBodega(java.lang.Long id) {
      return (FuncionBodegaEJB)queryManagerLocal.find(FuncionBodegaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFuncionBodegaList() {
	  return queryManagerLocal.singleClassList(FuncionBodegaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFuncionBodegaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FuncionBodegaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFuncionBodegaListSize() {
      Query countQuery = manager.createQuery("select count(*) from FuncionBodegaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionBodegaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FuncionBodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionBodegaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(FuncionBodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionBodegaByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(FuncionBodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionBodegaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(FuncionBodegaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionBodegaByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(FuncionBodegaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FuncionBodegaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFuncionBodegaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FuncionBodegaEJB.class, aMap);      
    }

/////////////////
}
