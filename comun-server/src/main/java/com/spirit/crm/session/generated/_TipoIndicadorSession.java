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
public abstract class _TipoIndicadorSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoIndicadorSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.TipoIndicadorIf addTipoIndicador(com.spirit.crm.entity.TipoIndicadorIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoIndicadorEJB value = new TipoIndicadorEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setAcumulativo(model.getAcumulativo());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoIndicador.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoIndicador.");
      }
     
      return getTipoIndicador(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoIndicador(com.spirit.crm.entity.TipoIndicadorIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoIndicadorEJB data = new TipoIndicadorEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setAcumulativo(model.getAcumulativo());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoIndicador.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoIndicador.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoIndicador(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoIndicadorEJB data = manager.find(TipoIndicadorEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoIndicador.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoIndicador.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.crm.entity.TipoIndicadorIf getTipoIndicador(java.lang.Long id) {
      return (TipoIndicadorEJB)queryManagerLocal.find(TipoIndicadorEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoIndicadorList() {
	  return queryManagerLocal.singleClassList(TipoIndicadorEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoIndicadorList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoIndicadorEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoIndicadorListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoIndicadorEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIndicadorById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIndicadorByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIndicadorByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIndicadorByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoIndicadorEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIndicadorByAcumulativo(java.lang.String acumulativo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("acumulativo", acumulativo);
		return queryManagerLocal.singleClassQueryList(TipoIndicadorEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoIndicadorIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoIndicadorByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoIndicadorEJB.class, aMap);      
    }

/////////////////
}
