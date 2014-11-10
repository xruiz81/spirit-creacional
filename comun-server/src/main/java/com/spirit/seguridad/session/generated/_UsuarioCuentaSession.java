package com.spirit.seguridad.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.seguridad.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _UsuarioCuentaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_UsuarioCuentaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.UsuarioCuentaIf addUsuarioCuenta(com.spirit.seguridad.entity.UsuarioCuentaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      UsuarioCuentaEJB value = new UsuarioCuentaEJB();
      try {
      value.setId(model.getId());
      value.setUsuarioId(model.getUsuarioId());
      value.setCuentaId(model.getCuentaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en usuarioCuenta.", e);
			throw new GenericBusinessException(
					"Error al insertar información en usuarioCuenta.");
      }
     
      return getUsuarioCuenta(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveUsuarioCuenta(com.spirit.seguridad.entity.UsuarioCuentaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      UsuarioCuentaEJB data = new UsuarioCuentaEJB();
      data.setId(model.getId());
      data.setUsuarioId(model.getUsuarioId());
      data.setCuentaId(model.getCuentaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en usuarioCuenta.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en usuarioCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteUsuarioCuenta(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      UsuarioCuentaEJB data = manager.find(UsuarioCuentaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en usuarioCuenta.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en usuarioCuenta.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.seguridad.entity.UsuarioCuentaIf getUsuarioCuenta(java.lang.Long id) {
      return (UsuarioCuentaEJB)queryManagerLocal.find(UsuarioCuentaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioCuentaList() {
	  return queryManagerLocal.singleClassList(UsuarioCuentaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioCuentaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(UsuarioCuentaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getUsuarioCuentaListSize() {
      Query countQuery = manager.createQuery("select count(*) from UsuarioCuentaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioCuentaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(UsuarioCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioCuentaByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(UsuarioCuentaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioCuentaByCuentaId(java.lang.Long cuentaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cuentaId", cuentaId);
		return queryManagerLocal.singleClassQueryList(UsuarioCuentaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of UsuarioCuentaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioCuentaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(UsuarioCuentaEJB.class, aMap);      
    }

/////////////////
}
