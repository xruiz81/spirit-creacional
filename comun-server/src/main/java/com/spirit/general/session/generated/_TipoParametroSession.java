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
public abstract class _TipoParametroSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_TipoParametroSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoParametroIf addTipoParametro(com.spirit.general.entity.TipoParametroIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      TipoParametroEJB value = new TipoParametroEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setTipo(model.getTipo());
      value.setMascara(model.getMascara());
      value.setEmpresaId(model.getEmpresaId());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en tipoParametro.", e);
			throw new GenericBusinessException(
					"Error al insertar información en tipoParametro.");
      }
     
      return getTipoParametro(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveTipoParametro(com.spirit.general.entity.TipoParametroIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      TipoParametroEJB data = new TipoParametroEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setTipo(model.getTipo());
      data.setMascara(model.getMascara());
      data.setEmpresaId(model.getEmpresaId());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en tipoParametro.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en tipoParametro.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteTipoParametro(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      TipoParametroEJB data = manager.find(TipoParametroEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en tipoParametro.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en tipoParametro.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.TipoParametroIf getTipoParametro(java.lang.Long id) {
      return (TipoParametroEJB)queryManagerLocal.find(TipoParametroEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoParametroList() {
	  return queryManagerLocal.singleClassList(TipoParametroEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getTipoParametroList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(TipoParametroEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getTipoParametroListSize() {
      Query countQuery = manager.createQuery("select count(*) from TipoParametroEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroByTipo(java.lang.String tipo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipo", tipo);
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroByMascara(java.lang.String mascara) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("mascara", mascara);
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of TipoParametroIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findTipoParametroByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(TipoParametroEJB.class, aMap);      
    }

/////////////////
}
