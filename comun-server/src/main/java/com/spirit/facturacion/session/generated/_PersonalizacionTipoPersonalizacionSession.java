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
public abstract class _PersonalizacionTipoPersonalizacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PersonalizacionTipoPersonalizacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf addPersonalizacionTipoPersonalizacion(com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PersonalizacionTipoPersonalizacionEJB value = new PersonalizacionTipoPersonalizacionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en personalizacionTipoPersonalizacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en personalizacionTipoPersonalizacion.");
      }
     
      return getPersonalizacionTipoPersonalizacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePersonalizacionTipoPersonalizacion(com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PersonalizacionTipoPersonalizacionEJB data = new PersonalizacionTipoPersonalizacionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en personalizacionTipoPersonalizacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en personalizacionTipoPersonalizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePersonalizacionTipoPersonalizacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PersonalizacionTipoPersonalizacionEJB data = manager.find(PersonalizacionTipoPersonalizacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en personalizacionTipoPersonalizacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en personalizacionTipoPersonalizacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionTipoPersonalizacionIf getPersonalizacionTipoPersonalizacion(java.lang.Long id) {
      return (PersonalizacionTipoPersonalizacionEJB)queryManagerLocal.find(PersonalizacionTipoPersonalizacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionTipoPersonalizacionList() {
	  return queryManagerLocal.singleClassList(PersonalizacionTipoPersonalizacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionTipoPersonalizacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PersonalizacionTipoPersonalizacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPersonalizacionTipoPersonalizacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from PersonalizacionTipoPersonalizacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoPersonalizacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoPersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoPersonalizacionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoPersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoPersonalizacionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoPersonalizacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoPersonalizacionByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoPersonalizacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PersonalizacionTipoPersonalizacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionTipoPersonalizacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PersonalizacionTipoPersonalizacionEJB.class, aMap);      
    }

/////////////////
}
