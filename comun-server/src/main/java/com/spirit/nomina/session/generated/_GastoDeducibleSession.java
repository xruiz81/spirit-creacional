package com.spirit.nomina.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.nomina.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _GastoDeducibleSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_GastoDeducibleSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.GastoDeducibleIf addGastoDeducible(com.spirit.nomina.entity.GastoDeducibleIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      GastoDeducibleEJB value = new GastoDeducibleEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en gastoDeducible.", e);
			throw new GenericBusinessException(
					"Error al insertar información en gastoDeducible.");
      }
     
      return getGastoDeducible(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveGastoDeducible(com.spirit.nomina.entity.GastoDeducibleIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      GastoDeducibleEJB data = new GastoDeducibleEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en gastoDeducible.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en gastoDeducible.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteGastoDeducible(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      GastoDeducibleEJB data = manager.find(GastoDeducibleEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en gastoDeducible.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en gastoDeducible.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.GastoDeducibleIf getGastoDeducible(java.lang.Long id) {
      return (GastoDeducibleEJB)queryManagerLocal.find(GastoDeducibleEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGastoDeducibleList() {
	  return queryManagerLocal.singleClassList(GastoDeducibleEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getGastoDeducibleList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(GastoDeducibleEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getGastoDeducibleListSize() {
      Query countQuery = manager.createQuery("select count(*) from GastoDeducibleEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoDeducibleById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(GastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoDeducibleByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(GastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoDeducibleByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(GastoDeducibleEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoDeducibleByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(GastoDeducibleEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of GastoDeducibleIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findGastoDeducibleByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(GastoDeducibleEJB.class, aMap);      
    }

/////////////////
}
