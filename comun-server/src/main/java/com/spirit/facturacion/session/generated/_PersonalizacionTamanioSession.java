package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _PersonalizacionTamanioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PersonalizacionTamanioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionTamanioIf addPersonalizacionTamanio(com.spirit.facturacion.entity.PersonalizacionTamanioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PersonalizacionTamanioEJB value = new PersonalizacionTamanioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en personalizacionTamanio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en personalizacionTamanio.");
      }
     
      return getPersonalizacionTamanio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePersonalizacionTamanio(com.spirit.facturacion.entity.PersonalizacionTamanioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PersonalizacionTamanioEJB data = new PersonalizacionTamanioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en personalizacionTamanio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en personalizacionTamanio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePersonalizacionTamanio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PersonalizacionTamanioEJB data = manager.find(PersonalizacionTamanioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en personalizacionTamanio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en personalizacionTamanio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionTamanioIf getPersonalizacionTamanio(java.lang.Long id) {
      return (PersonalizacionTamanioEJB)queryManagerLocal.find(PersonalizacionTamanioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionTamanioList() {
	  return queryManagerLocal.singleClassList(PersonalizacionTamanioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionTamanioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PersonalizacionTamanioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPersonalizacionTamanioListSize() {
      Query countQuery = manager.createQuery("select count(*) from PersonalizacionTamanioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTamanioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTamanioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTamanioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTamanioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTamanioByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTamanioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTamanioByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTamanioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PersonalizacionTamanioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTamanioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PersonalizacionTamanioEJB.class, aMap);      
    }

/////////////////
}
