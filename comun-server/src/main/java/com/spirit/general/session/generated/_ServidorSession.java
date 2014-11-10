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
public abstract class _ServidorSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ServidorSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ServidorIf addServidor(com.spirit.general.entity.ServidorIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ServidorEJB value = new ServidorEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setDescripcion(model.getDescripcion());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en servidor.", e);
			throw new GenericBusinessException(
					"Error al insertar información en servidor.");
      }
     
      return getServidor(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveServidor(com.spirit.general.entity.ServidorIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ServidorEJB data = new ServidorEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setDescripcion(model.getDescripcion());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en servidor.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en servidor.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteServidor(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ServidorEJB data = manager.find(ServidorEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en servidor.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en servidor.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ServidorIf getServidor(java.lang.Long id) {
      return (ServidorEJB)queryManagerLocal.find(ServidorEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getServidorList() {
	  return queryManagerLocal.singleClassList(ServidorEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getServidorList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ServidorEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getServidorListSize() {
      Query countQuery = manager.createQuery("select count(*) from ServidorEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findServidorById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ServidorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findServidorByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ServidorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findServidorByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(ServidorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findServidorByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(ServidorEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ServidorIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findServidorByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ServidorEJB.class, aMap);      
    }

/////////////////
}
