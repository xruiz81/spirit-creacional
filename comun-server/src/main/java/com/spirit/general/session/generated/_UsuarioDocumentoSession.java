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
public abstract class _UsuarioDocumentoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_UsuarioDocumentoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioDocumentoIf addUsuarioDocumento(com.spirit.general.entity.UsuarioDocumentoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      UsuarioDocumentoEJB value = new UsuarioDocumentoEJB();
      try {
      value.setId(model.getId());
      value.setUsuarioId(model.getUsuarioId());
      value.setDocumentoId(model.getDocumentoId());
      value.setPermisoImpresion(model.getPermisoImpresion());
      value.setPermisoRegistro(model.getPermisoRegistro());
      value.setPermisoBorrado(model.getPermisoBorrado());
      value.setPermisoAutorizar(model.getPermisoAutorizar());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en usuarioDocumento.", e);
			throw new GenericBusinessException(
					"Error al insertar información en usuarioDocumento.");
      }
     
      return getUsuarioDocumento(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveUsuarioDocumento(com.spirit.general.entity.UsuarioDocumentoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      UsuarioDocumentoEJB data = new UsuarioDocumentoEJB();
      data.setId(model.getId());
      data.setUsuarioId(model.getUsuarioId());
      data.setDocumentoId(model.getDocumentoId());
      data.setPermisoImpresion(model.getPermisoImpresion());
      data.setPermisoRegistro(model.getPermisoRegistro());
      data.setPermisoBorrado(model.getPermisoBorrado());
      data.setPermisoAutorizar(model.getPermisoAutorizar());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en usuarioDocumento.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en usuarioDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteUsuarioDocumento(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      UsuarioDocumentoEJB data = manager.find(UsuarioDocumentoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en usuarioDocumento.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en usuarioDocumento.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioDocumentoIf getUsuarioDocumento(java.lang.Long id) {
      return (UsuarioDocumentoEJB)queryManagerLocal.find(UsuarioDocumentoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioDocumentoList() {
	  return queryManagerLocal.singleClassList(UsuarioDocumentoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioDocumentoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(UsuarioDocumentoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getUsuarioDocumentoListSize() {
      Query countQuery = manager.createQuery("select count(*) from UsuarioDocumentoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByDocumentoId(java.lang.Long documentoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("documentoId", documentoId);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByPermisoImpresion(java.lang.String permisoImpresion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("permisoImpresion", permisoImpresion);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByPermisoRegistro(java.lang.String permisoRegistro) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("permisoRegistro", permisoRegistro);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByPermisoBorrado(java.lang.String permisoBorrado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("permisoBorrado", permisoBorrado);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByPermisoAutorizar(java.lang.String permisoAutorizar) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("permisoAutorizar", permisoAutorizar);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of UsuarioDocumentoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioDocumentoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(UsuarioDocumentoEJB.class, aMap);      
    }

/////////////////
}
