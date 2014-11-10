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
public abstract class _SriSustentoTributarioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriSustentoTributarioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriSustentoTributarioIf addSriSustentoTributario(com.spirit.sri.entity.SriSustentoTributarioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriSustentoTributarioEJB value = new SriSustentoTributarioEJB();
      try {
      value.setId(model.getId());
      value.setDescripcion(model.getDescripcion());
      value.setCodigo(model.getCodigo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriSustentoTributario.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriSustentoTributario.");
      }
     
      return getSriSustentoTributario(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriSustentoTributario(com.spirit.sri.entity.SriSustentoTributarioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriSustentoTributarioEJB data = new SriSustentoTributarioEJB();
      data.setId(model.getId());
      data.setDescripcion(model.getDescripcion());
      data.setCodigo(model.getCodigo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriSustentoTributario.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriSustentoTributario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriSustentoTributario(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriSustentoTributarioEJB data = manager.find(SriSustentoTributarioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriSustentoTributario.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriSustentoTributario.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriSustentoTributarioIf getSriSustentoTributario(java.lang.Long id) {
      return (SriSustentoTributarioEJB)queryManagerLocal.find(SriSustentoTributarioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriSustentoTributarioList() {
	  return queryManagerLocal.singleClassList(SriSustentoTributarioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriSustentoTributarioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriSustentoTributarioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriSustentoTributarioListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriSustentoTributarioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriSustentoTributarioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriSustentoTributarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriSustentoTributarioByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(SriSustentoTributarioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriSustentoTributarioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriSustentoTributarioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriSustentoTributarioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriSustentoTributarioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriSustentoTributarioEJB.class, aMap);      
    }

/////////////////
}
