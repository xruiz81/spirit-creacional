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
public abstract class _PersonalizacionLogoDisenioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PersonalizacionLogoDisenioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf addPersonalizacionLogoDisenio(com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PersonalizacionLogoDisenioEJB value = new PersonalizacionLogoDisenioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en personalizacionLogoDisenio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en personalizacionLogoDisenio.");
      }
     
      return getPersonalizacionLogoDisenio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePersonalizacionLogoDisenio(com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PersonalizacionLogoDisenioEJB data = new PersonalizacionLogoDisenioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en personalizacionLogoDisenio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en personalizacionLogoDisenio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePersonalizacionLogoDisenio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PersonalizacionLogoDisenioEJB data = manager.find(PersonalizacionLogoDisenioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en personalizacionLogoDisenio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en personalizacionLogoDisenio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionLogoDisenioIf getPersonalizacionLogoDisenio(java.lang.Long id) {
      return (PersonalizacionLogoDisenioEJB)queryManagerLocal.find(PersonalizacionLogoDisenioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionLogoDisenioList() {
	  return queryManagerLocal.singleClassList(PersonalizacionLogoDisenioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionLogoDisenioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PersonalizacionLogoDisenioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPersonalizacionLogoDisenioListSize() {
      Query countQuery = manager.createQuery("select count(*) from PersonalizacionLogoDisenioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionLogoDisenioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PersonalizacionLogoDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionLogoDisenioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PersonalizacionLogoDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionLogoDisenioByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PersonalizacionLogoDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionLogoDisenioByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionLogoDisenioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PersonalizacionLogoDisenioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionLogoDisenioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PersonalizacionLogoDisenioEJB.class, aMap);      
    }

/////////////////
}
