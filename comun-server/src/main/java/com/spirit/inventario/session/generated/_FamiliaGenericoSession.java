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
public abstract class _FamiliaGenericoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_FamiliaGenericoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.FamiliaGenericoIf addFamiliaGenerico(com.spirit.inventario.entity.FamiliaGenericoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      FamiliaGenericoEJB value = new FamiliaGenericoEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en familiaGenerico.", e);
			throw new GenericBusinessException(
					"Error al insertar información en familiaGenerico.");
      }
     
      return getFamiliaGenerico(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveFamiliaGenerico(com.spirit.inventario.entity.FamiliaGenericoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      FamiliaGenericoEJB data = new FamiliaGenericoEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en familiaGenerico.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en familiaGenerico.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteFamiliaGenerico(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      FamiliaGenericoEJB data = manager.find(FamiliaGenericoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en familiaGenerico.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en familiaGenerico.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.FamiliaGenericoIf getFamiliaGenerico(java.lang.Long id) {
      return (FamiliaGenericoEJB)queryManagerLocal.find(FamiliaGenericoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFamiliaGenericoList() {
	  return queryManagerLocal.singleClassList(FamiliaGenericoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getFamiliaGenericoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(FamiliaGenericoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getFamiliaGenericoListSize() {
      Query countQuery = manager.createQuery("select count(*) from FamiliaGenericoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFamiliaGenericoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(FamiliaGenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFamiliaGenericoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(FamiliaGenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFamiliaGenericoByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(FamiliaGenericoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFamiliaGenericoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(FamiliaGenericoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of FamiliaGenericoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findFamiliaGenericoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(FamiliaGenericoEJB.class, aMap);      
    }

/////////////////
}
