package com.spirit.crm.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.crm.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _CorporacionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_CorporacionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.CorporacionIf addCorporacion(com.spirit.crm.entity.CorporacionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      CorporacionEJB value = new CorporacionEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setRepresentante(model.getRepresentante());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en corporacion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en corporacion.");
      }
     
      return getCorporacion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveCorporacion(com.spirit.crm.entity.CorporacionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      CorporacionEJB data = new CorporacionEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setRepresentante(model.getRepresentante());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en corporacion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en corporacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteCorporacion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      CorporacionEJB data = manager.find(CorporacionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en corporacion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en corporacion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.CorporacionIf getCorporacion(java.lang.Long id) {
      return (CorporacionEJB)queryManagerLocal.find(CorporacionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCorporacionList() {
	  return queryManagerLocal.singleClassList(CorporacionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getCorporacionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(CorporacionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getCorporacionListSize() {
      Query countQuery = manager.createQuery("select count(*) from CorporacionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByRepresentante(java.lang.String representante) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("representante", representante);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of CorporacionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findCorporacionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(CorporacionEJB.class, aMap);      
    }

/////////////////
}
