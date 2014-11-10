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
public abstract class _PersonalizacionTipoImpresionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PersonalizacionTipoImpresionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf addPersonalizacionTipoImpresion(com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PersonalizacionTipoImpresionEJB value = new PersonalizacionTipoImpresionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en personalizacionTipoImpresion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en personalizacionTipoImpresion.");
      }
     
      return getPersonalizacionTipoImpresion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePersonalizacionTipoImpresion(com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PersonalizacionTipoImpresionEJB data = new PersonalizacionTipoImpresionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en personalizacionTipoImpresion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en personalizacionTipoImpresion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePersonalizacionTipoImpresion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PersonalizacionTipoImpresionEJB data = manager.find(PersonalizacionTipoImpresionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en personalizacionTipoImpresion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en personalizacionTipoImpresion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionTipoImpresionIf getPersonalizacionTipoImpresion(java.lang.Long id) {
      return (PersonalizacionTipoImpresionEJB)queryManagerLocal.find(PersonalizacionTipoImpresionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionTipoImpresionList() {
	  return queryManagerLocal.singleClassList(PersonalizacionTipoImpresionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionTipoImpresionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PersonalizacionTipoImpresionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPersonalizacionTipoImpresionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PersonalizacionTipoImpresionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoImpresionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoImpresionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoImpresionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoImpresionByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoImpresionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PersonalizacionTipoImpresionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoImpresionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoImpresionEJB.class, aMap);      
    }

/////////////////
}
