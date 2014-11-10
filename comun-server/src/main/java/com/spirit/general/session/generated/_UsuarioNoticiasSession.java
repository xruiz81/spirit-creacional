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
public abstract class _UsuarioNoticiasSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_UsuarioNoticiasSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioNoticiasIf addUsuarioNoticias(com.spirit.general.entity.UsuarioNoticiasIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      UsuarioNoticiasEJB value = new UsuarioNoticiasEJB();
      try {
      value.setId(model.getId());
      value.setUsuarioId(model.getUsuarioId());
      value.setNoticiasId(model.getNoticiasId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en usuarioNoticias.", e);
			throw new GenericBusinessException(
					"Error al insertar información en usuarioNoticias.");
      }
     
      return getUsuarioNoticias(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveUsuarioNoticias(com.spirit.general.entity.UsuarioNoticiasIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      UsuarioNoticiasEJB data = new UsuarioNoticiasEJB();
      data.setId(model.getId());
      data.setUsuarioId(model.getUsuarioId());
      data.setNoticiasId(model.getNoticiasId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en usuarioNoticias.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en usuarioNoticias.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteUsuarioNoticias(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      UsuarioNoticiasEJB data = manager.find(UsuarioNoticiasEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en usuarioNoticias.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en usuarioNoticias.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioNoticiasIf getUsuarioNoticias(java.lang.Long id) {
      return (UsuarioNoticiasEJB)queryManagerLocal.find(UsuarioNoticiasEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioNoticiasList() {
	  return queryManagerLocal.singleClassList(UsuarioNoticiasEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioNoticiasList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(UsuarioNoticiasEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getUsuarioNoticiasListSize() {
      Query countQuery = manager.createQuery("select count(*) from UsuarioNoticiasEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioNoticiasById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(UsuarioNoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioNoticiasByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(UsuarioNoticiasEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioNoticiasByNoticiasId(java.lang.Long noticiasId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("noticiasId", noticiasId);
		return queryManagerLocal.singleClassQueryList(UsuarioNoticiasEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of UsuarioNoticiasIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioNoticiasByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(UsuarioNoticiasEJB.class, aMap);      
    }

/////////////////
}
