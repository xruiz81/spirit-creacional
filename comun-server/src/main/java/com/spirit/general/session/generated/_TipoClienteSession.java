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
public abstract class _TipoClienteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoClienteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoClienteIf addTipoCliente(com.spirit.general.entity.TipoClienteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoClienteEJB value = new TipoClienteEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoCliente.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoCliente.");
      }
     
      return getTipoCliente(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoCliente(com.spirit.general.entity.TipoClienteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoClienteEJB data = new TipoClienteEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoCliente.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoCliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoCliente(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoClienteEJB data = manager.find(TipoClienteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoCliente.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoCliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoClienteIf getTipoCliente(java.lang.Long id) {
      return (TipoClienteEJB)queryManagerLocal.find(TipoClienteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoClienteList() {
	  return queryManagerLocal.singleClassList(TipoClienteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoClienteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoClienteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoClienteListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoClienteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoClienteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoClienteByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoClienteByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoClienteByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoClienteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoClienteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoClienteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoClienteEJB.class, aMap);      
    }

/////////////////
}
