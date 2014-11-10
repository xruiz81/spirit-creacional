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
public abstract class _Timetracker2Session {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_Timetracker2Session.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2If addTimetracker2(com.spirit.medios.entity.Timetracker2If model)
     throws com.spirit.exception.GenericBusinessException 
   {
      Timetracker2EJB value = new Timetracker2EJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setActividad(model.getActividad());
      value.setEstado(model.getEstado());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en timetracker2.", e);
			throw new GenericBusinessException(
					"Error al insertar información en timetracker2.");
      }
     
      return getTimetracker2(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTimetracker2(com.spirit.medios.entity.Timetracker2If model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      Timetracker2EJB data = new Timetracker2EJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setActividad(model.getActividad());
      data.setEstado(model.getEstado());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en timetracker2.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en timetracker2.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTimetracker2(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      Timetracker2EJB data = manager.find(Timetracker2EJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en timetracker2.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en timetracker2.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.Timetracker2If getTimetracker2(java.lang.Long id) {
      return (Timetracker2EJB)queryManagerLocal.find(Timetracker2EJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2List() {
	  return queryManagerLocal.singleClassList(Timetracker2EJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTimetracker2List(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(Timetracker2EJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTimetracker2ListSize() {
      Query countQuery = manager.createQuery("select count(*) from Timetracker2EJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2ById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(Timetracker2EJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2ByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(Timetracker2EJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2ByActividad(java.lang.String actividad) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("actividad", actividad);
		return queryManagerLocal.singleClassQueryList(Timetracker2EJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2ByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(Timetracker2EJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2ByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(Timetracker2EJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of Timetracker2If data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTimetracker2ByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(Timetracker2EJB.class, aMap);      
    }

/////////////////
}
