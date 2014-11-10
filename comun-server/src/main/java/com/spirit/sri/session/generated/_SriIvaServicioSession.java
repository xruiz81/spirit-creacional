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
public abstract class _SriIvaServicioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_SriIvaServicioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaServicioIf addSriIvaServicio(com.spirit.sri.entity.SriIvaServicioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      SriIvaServicioEJB value = new SriIvaServicioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setDescripcionPorcentaje(model.getDescripcionPorcentaje());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en sriIvaServicio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en sriIvaServicio.");
      }
     
      return getSriIvaServicio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveSriIvaServicio(com.spirit.sri.entity.SriIvaServicioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      SriIvaServicioEJB data = new SriIvaServicioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setDescripcionPorcentaje(model.getDescripcionPorcentaje());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en sriIvaServicio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en sriIvaServicio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteSriIvaServicio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      SriIvaServicioEJB data = manager.find(SriIvaServicioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en sriIvaServicio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en sriIvaServicio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.sri.entity.SriIvaServicioIf getSriIvaServicio(java.lang.Long id) {
      return (SriIvaServicioEJB)queryManagerLocal.find(SriIvaServicioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaServicioList() {
	  return queryManagerLocal.singleClassList(SriIvaServicioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getSriIvaServicioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(SriIvaServicioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getSriIvaServicioListSize() {
      Query countQuery = manager.createQuery("select count(*) from SriIvaServicioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaServicioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(SriIvaServicioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaServicioByCodigo(java.lang.Integer codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(SriIvaServicioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaServicioByDescripcionPorcentaje(java.lang.String descripcionPorcentaje) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcionPorcentaje", descripcionPorcentaje);
		return queryManagerLocal.singleClassQueryList(SriIvaServicioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of SriIvaServicioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findSriIvaServicioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(SriIvaServicioEJB.class, aMap);      
    }

/////////////////
}
