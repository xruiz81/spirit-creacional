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
public abstract class _PaisSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PaisSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.PaisIf addPais(com.spirit.general.entity.PaisIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PaisEJB value = new PaisEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en pais.", e);
			throw new GenericBusinessException(
					"Error al insertar información en pais.");
      }
     
      return getPais(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePais(com.spirit.general.entity.PaisIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PaisEJB data = new PaisEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en pais.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en pais.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePais(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PaisEJB data = manager.find(PaisEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en pais.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en pais.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.PaisIf getPais(java.lang.Long id) {
      return (PaisEJB)queryManagerLocal.find(PaisEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPaisList() {
	  return queryManagerLocal.singleClassList(PaisEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPaisList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PaisEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPaisListSize() {
      Query countQuery = manager.createQuery("select count(*) from PaisEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPaisById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PaisEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPaisByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PaisEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPaisByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PaisEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PaisIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPaisByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PaisEJB.class, aMap);      
    }

/////////////////
}
