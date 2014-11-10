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
public abstract class _UsuarioPuntoImpresionSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_UsuarioPuntoImpresionSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioPuntoImpresionIf addUsuarioPuntoImpresion(com.spirit.general.entity.UsuarioPuntoImpresionIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      UsuarioPuntoImpresionEJB value = new UsuarioPuntoImpresionEJB();
      try {
      value.setId(model.getId());
      value.setPuntoimpresionId(model.getPuntoimpresionId());
      value.setUsuarioId(model.getUsuarioId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en usuarioPuntoImpresion.", e);
			throw new GenericBusinessException(
					"Error al insertar información en usuarioPuntoImpresion.");
      }
     
      return getUsuarioPuntoImpresion(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveUsuarioPuntoImpresion(com.spirit.general.entity.UsuarioPuntoImpresionIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      UsuarioPuntoImpresionEJB data = new UsuarioPuntoImpresionEJB();
      data.setId(model.getId());
      data.setPuntoimpresionId(model.getPuntoimpresionId());
      data.setUsuarioId(model.getUsuarioId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en usuarioPuntoImpresion.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en usuarioPuntoImpresion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteUsuarioPuntoImpresion(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      UsuarioPuntoImpresionEJB data = manager.find(UsuarioPuntoImpresionEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en usuarioPuntoImpresion.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en usuarioPuntoImpresion.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.UsuarioPuntoImpresionIf getUsuarioPuntoImpresion(java.lang.Long id) {
      return (UsuarioPuntoImpresionEJB)queryManagerLocal.find(UsuarioPuntoImpresionEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioPuntoImpresionList() {
	  return queryManagerLocal.singleClassList(UsuarioPuntoImpresionEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getUsuarioPuntoImpresionList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(UsuarioPuntoImpresionEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getUsuarioPuntoImpresionListSize() {
      Query countQuery = manager.createQuery("select count(*) from UsuarioPuntoImpresionEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioPuntoImpresionById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(UsuarioPuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioPuntoImpresionByPuntoimpresionId(java.lang.Long puntoimpresionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("puntoimpresionId", puntoimpresionId);
		return queryManagerLocal.singleClassQueryList(UsuarioPuntoImpresionEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioPuntoImpresionByUsuarioId(java.lang.Long usuarioId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("usuarioId", usuarioId);
		return queryManagerLocal.singleClassQueryList(UsuarioPuntoImpresionEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of UsuarioPuntoImpresionIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findUsuarioPuntoImpresionByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(UsuarioPuntoImpresionEJB.class, aMap);      
    }

/////////////////
}
