package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.RolPagoDocumentoEJB;
import com.spirit.server.LogService;
import com.spirit.server.Logger;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _RolPagoDocumentoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_RolPagoDocumentoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoDocumentoIf addRolPagoDocumento(com.spirit.nomina.entity.RolPagoDocumentoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      RolPagoDocumentoEJB value = new RolPagoDocumentoEJB();
      try {
      value.setId(model.getId());
      value.setTipoRolId(model.getTipoRolId());
      value.setTipoContratoId(model.getTipoContratoId());
      value.setDocumentoId(model.getDocumentoId());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setCreacionUsuarioId(model.getCreacionUsuarioId());
      value.setFechaActualizacion(model.getFechaActualizacion());
      value.setActualizacionUsuarioId(model.getActualizacionUsuarioId());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en rolPagoDocumento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en rolPagoDocumento.");
      }
     
      return getRolPagoDocumento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveRolPagoDocumento(com.spirit.nomina.entity.RolPagoDocumentoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      RolPagoDocumentoEJB data = new RolPagoDocumentoEJB();
      data.setId(model.getId());
      data.setTipoRolId(model.getTipoRolId());
      data.setTipoContratoId(model.getTipoContratoId());
      data.setDocumentoId(model.getDocumentoId());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setCreacionUsuarioId(model.getCreacionUsuarioId());
      data.setFechaActualizacion(model.getFechaActualizacion());
      data.setActualizacionUsuarioId(model.getActualizacionUsuarioId());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en rolPagoDocumento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en rolPagoDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteRolPagoDocumento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      RolPagoDocumentoEJB data = manager.find(RolPagoDocumentoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en rolPagoDocumento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en rolPagoDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.nomina.entity.RolPagoDocumentoIf getRolPagoDocumento(java.lang.Long id) {
      return (RolPagoDocumentoEJB)queryManagerLocal.find(RolPagoDocumentoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoDocumentoList() {
	  return queryManagerLocal.singleClassList(RolPagoDocumentoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getRolPagoDocumentoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(RolPagoDocumentoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getRolPagoDocumentoListSize() {
      Query countQuery = manager.createQuery("select count(*) from RolPagoDocumentoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByTipoRolId(java.lang.Long tipoRolId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoRolId", tipoRolId);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByTipoContratoId(java.lang.Long tipoContratoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoContratoId", tipoContratoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByFechaCreacion(java.sql.Date fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByCreacionUsuarioId(java.lang.Long creacionUsuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("creacionUsuarioId", creacionUsuarioId);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByFechaActualizacion(java.sql.Date fechaActualizacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaActualizacion", fechaActualizacion);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByActualizacionUsuarioId(java.lang.Long actualizacionUsuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("actualizacionUsuarioId", actualizacionUsuarioId);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of RolPagoDocumentoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findRolPagoDocumentoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(RolPagoDocumentoEJB.class, aMap);      
    }

/////////////////
}
