package com.spirit.nomina.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.nomina.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _TipoRolSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoRolSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.TipoRolIf addTipoRol(com.spirit.nomina.entity.TipoRolIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoRolEJB value = new TipoRolEJB();
      try {
      value.setId(model.getId());
      value.setEmpresaId(model.getEmpresaId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setNemonico(model.getNemonico());
      value.setRubroEventual(model.getRubroEventual());
      value.setDocumentoId(model.getDocumentoId());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFin(model.getFechaFin());
      value.setRubroProvisionado(model.getRubroProvisionado());
      value.setFormaPago(model.getFormaPago());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoRol.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoRol.");
      }
     
      return getTipoRol(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoRol(com.spirit.nomina.entity.TipoRolIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoRolEJB data = new TipoRolEJB();
      data.setId(model.getId());
      data.setEmpresaId(model.getEmpresaId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setNemonico(model.getNemonico());
      data.setRubroEventual(model.getRubroEventual());
      data.setDocumentoId(model.getDocumentoId());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFin(model.getFechaFin());
      data.setRubroProvisionado(model.getRubroProvisionado());
      data.setFormaPago(model.getFormaPago());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoRol.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoRol.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoRol(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoRolEJB data = manager.find(TipoRolEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoRol.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoRol.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.TipoRolIf getTipoRol(java.lang.Long id) {
      return (TipoRolEJB)queryManagerLocal.find(TipoRolEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoRolList() {
	  return queryManagerLocal.singleClassList(TipoRolEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoRolList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoRolEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoRolListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoRolEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByNemonico(java.lang.String nemonico) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nemonico", nemonico);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByRubroEventual(java.lang.String rubroEventual) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroEventual", rubroEventual);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByFechaFin(java.sql.Date fechaFin) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFin", fechaFin);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByRubroProvisionado(java.lang.String rubroProvisionado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rubroProvisionado", rubroProvisionado);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByFormaPago(java.lang.String formaPago) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("formaPago", formaPago);
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoRolIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoRolByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoRolEJB.class, aMap);      
    }

/////////////////
}
