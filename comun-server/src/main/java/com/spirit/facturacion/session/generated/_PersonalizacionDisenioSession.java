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
public abstract class _PersonalizacionDisenioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_PersonalizacionDisenioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionDisenioIf addPersonalizacionDisenio(com.spirit.facturacion.entity.PersonalizacionDisenioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      PersonalizacionDisenioEJB value = new PersonalizacionDisenioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setTipoProductoId(model.getTipoProductoId());
      value.setLineaId(model.getLineaId());
      value.setPersonalizacionColorId(model.getPersonalizacionColorId());
      value.setMiniDisplay(model.getMiniDisplay());
      value.setFront(model.getFront());
      value.setBack(model.getBack());
      value.setEmpresaId(model.getEmpresaId());
      value.setEtiqueta(model.getEtiqueta());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en personalizacionDisenio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en personalizacionDisenio.");
      }
     
      return getPersonalizacionDisenio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void savePersonalizacionDisenio(com.spirit.facturacion.entity.PersonalizacionDisenioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      PersonalizacionDisenioEJB data = new PersonalizacionDisenioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setTipoProductoId(model.getTipoProductoId());
      data.setLineaId(model.getLineaId());
      data.setPersonalizacionColorId(model.getPersonalizacionColorId());
      data.setMiniDisplay(model.getMiniDisplay());
      data.setFront(model.getFront());
      data.setBack(model.getBack());
      data.setEmpresaId(model.getEmpresaId());
      data.setEtiqueta(model.getEtiqueta());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en personalizacionDisenio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en personalizacionDisenio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deletePersonalizacionDisenio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      PersonalizacionDisenioEJB data = manager.find(PersonalizacionDisenioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en personalizacionDisenio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en personalizacionDisenio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.PersonalizacionDisenioIf getPersonalizacionDisenio(java.lang.Long id) {
      return (PersonalizacionDisenioEJB)queryManagerLocal.find(PersonalizacionDisenioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionDisenioList() {
	  return queryManagerLocal.singleClassList(PersonalizacionDisenioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getPersonalizacionDisenioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(PersonalizacionDisenioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getPersonalizacionDisenioListSize() {
      Query countQuery = manager.createQuery("select count(*) from PersonalizacionDisenioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByTipoProductoId(java.lang.Long tipoProductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoProductoId", tipoProductoId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByLineaId(java.lang.Long lineaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("lineaId", lineaId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByPersonalizacionColorId(java.lang.Long personalizacionColorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("personalizacionColorId", personalizacionColorId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByMiniDisplay(java.lang.String miniDisplay) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("miniDisplay", miniDisplay);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByFront(java.lang.String front) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("front", front);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByBack(java.lang.String back) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("back", back);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByEtiqueta(java.lang.String etiqueta) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("etiqueta", etiqueta);
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of PersonalizacionDisenioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findPersonalizacionDisenioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(PersonalizacionDisenioEJB.class, aMap);      
    }

/////////////////
}
