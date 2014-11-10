package com.spirit.sri.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.sri.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _SriIvaBienSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriIvaBienSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaBienIf addSriIvaBien(com.spirit.sri.entity.SriIvaBienIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriIvaBienEJB value = new SriIvaBienEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setDescripcionPorcentaje(model.getDescripcionPorcentaje());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriIvaBien.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriIvaBien.");
      }
     
      return getSriIvaBien(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriIvaBien(com.spirit.sri.entity.SriIvaBienIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriIvaBienEJB data = new SriIvaBienEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setDescripcionPorcentaje(model.getDescripcionPorcentaje());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriIvaBien.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriIvaBien.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriIvaBien(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriIvaBienEJB data = manager.find(SriIvaBienEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriIvaBien.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriIvaBien.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaBienIf getSriIvaBien(java.lang.Long id) {
      return (SriIvaBienEJB)queryManagerLocal.find(SriIvaBienEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaBienList() {
	  return queryManagerLocal.singleClassList(SriIvaBienEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaBienList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriIvaBienEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriIvaBienListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriIvaBienEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaBienById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriIvaBienEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaBienByCodigo(java.lang.Integer codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriIvaBienEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaBienByDescripcionPorcentaje(java.lang.String descripcionPorcentaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcionPorcentaje", descripcionPorcentaje);
		return queryManagerLocal.singleClassQueryList(SriIvaBienEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriIvaBienIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaBienByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriIvaBienEJB.class, aMap);      
    }

/////////////////
}
