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
public abstract class _TipoNegocioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoNegocioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.TipoNegocioIf addTipoNegocio(com.spirit.crm.entity.TipoNegocioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoNegocioEJB value = new TipoNegocioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoNegocio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoNegocio.");
      }
     
      return getTipoNegocio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoNegocio(com.spirit.crm.entity.TipoNegocioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoNegocioEJB data = new TipoNegocioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoNegocio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoNegocio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoNegocio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoNegocioEJB data = manager.find(TipoNegocioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoNegocio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoNegocio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.TipoNegocioIf getTipoNegocio(java.lang.Long id) {
      return (TipoNegocioEJB)queryManagerLocal.find(TipoNegocioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoNegocioList() {
	  return queryManagerLocal.singleClassList(TipoNegocioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoNegocioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoNegocioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoNegocioListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoNegocioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoNegocioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoNegocioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoNegocioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoNegocioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoNegocioByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoNegocioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoNegocioByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoNegocioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoNegocioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoNegocioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoNegocioEJB.class, aMap);      
    }

/////////////////
}
