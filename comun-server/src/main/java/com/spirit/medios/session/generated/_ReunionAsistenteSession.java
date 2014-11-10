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
public abstract class _ReunionAsistenteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ReunionAsistenteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionAsistenteIf addReunionAsistente(com.spirit.medios.entity.ReunionAsistenteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ReunionAsistenteEJB value = new ReunionAsistenteEJB();
      try {
      value.setId(model.getId());
      value.setReunionId(model.getReunionId());
      value.setClienteContactoId(model.getClienteContactoId());
      value.setEmpleadoId(model.getEmpleadoId());
      value.setClienteContacto(model.getClienteContacto());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en reunionAsistente.", e);
			throw new GenericBusinessException(
					"Error al insertar información en reunionAsistente.");
      }
     
      return getReunionAsistente(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveReunionAsistente(com.spirit.medios.entity.ReunionAsistenteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ReunionAsistenteEJB data = new ReunionAsistenteEJB();
      data.setId(model.getId());
      data.setReunionId(model.getReunionId());
      data.setClienteContactoId(model.getClienteContactoId());
      data.setEmpleadoId(model.getEmpleadoId());
      data.setClienteContacto(model.getClienteContacto());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en reunionAsistente.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en reunionAsistente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteReunionAsistente(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ReunionAsistenteEJB data = manager.find(ReunionAsistenteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en reunionAsistente.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en reunionAsistente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ReunionAsistenteIf getReunionAsistente(java.lang.Long id) {
      return (ReunionAsistenteEJB)queryManagerLocal.find(ReunionAsistenteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionAsistenteList() {
	  return queryManagerLocal.singleClassList(ReunionAsistenteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getReunionAsistenteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ReunionAsistenteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getReunionAsistenteListSize() {
      Query countQuery = manager.createQuery("select count(*) from ReunionAsistenteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionAsistenteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ReunionAsistenteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionAsistenteByReunionId(java.lang.Long reunionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("reunionId", reunionId);
		return queryManagerLocal.singleClassQueryList(ReunionAsistenteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionAsistenteByClienteContactoId(java.lang.Long clienteContactoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteContactoId", clienteContactoId);
		return queryManagerLocal.singleClassQueryList(ReunionAsistenteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionAsistenteByEmpleadoId(java.lang.Long empleadoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empleadoId", empleadoId);
		return queryManagerLocal.singleClassQueryList(ReunionAsistenteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionAsistenteByClienteContacto(java.lang.String clienteContacto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteContacto", clienteContacto);
		return queryManagerLocal.singleClassQueryList(ReunionAsistenteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ReunionAsistenteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findReunionAsistenteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ReunionAsistenteEJB.class, aMap);      
    }

/////////////////
}
