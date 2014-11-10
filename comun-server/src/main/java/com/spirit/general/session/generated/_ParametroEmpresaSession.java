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
public abstract class _ParametroEmpresaSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ParametroEmpresaSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ParametroEmpresaIf addParametroEmpresa(com.spirit.general.entity.ParametroEmpresaIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ParametroEmpresaEJB value = new ParametroEmpresaEJB();
      try {
      value.setId(model.getId());
      value.setTipoparametroId(model.getTipoparametroId());
      value.setEmpresaId(model.getEmpresaId());
      value.setValor(model.getValor());
      value.setCodigo(model.getCodigo());
      value.setDescripcion(model.getDescripcion());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en parametroEmpresa.", e);
			throw new GenericBusinessException(
					"Error al insertar información en parametroEmpresa.");
      }
     
      return getParametroEmpresa(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveParametroEmpresa(com.spirit.general.entity.ParametroEmpresaIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ParametroEmpresaEJB data = new ParametroEmpresaEJB();
      data.setId(model.getId());
      data.setTipoparametroId(model.getTipoparametroId());
      data.setEmpresaId(model.getEmpresaId());
      data.setValor(model.getValor());
      data.setCodigo(model.getCodigo());
      data.setDescripcion(model.getDescripcion());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en parametroEmpresa.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en parametroEmpresa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteParametroEmpresa(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ParametroEmpresaEJB data = manager.find(ParametroEmpresaEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en parametroEmpresa.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en parametroEmpresa.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.general.entity.ParametroEmpresaIf getParametroEmpresa(java.lang.Long id) {
      return (ParametroEmpresaEJB)queryManagerLocal.find(ParametroEmpresaEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getParametroEmpresaList() {
	  return queryManagerLocal.singleClassList(ParametroEmpresaEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getParametroEmpresaList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ParametroEmpresaEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getParametroEmpresaListSize() {
      Query countQuery = manager.createQuery("select count(*) from ParametroEmpresaEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaByTipoparametroId(java.lang.Long tipoparametroId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("tipoparametroId", tipoparametroId);
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaByValor(java.lang.String valor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("valor", valor);
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaByDescripcion(java.lang.String descripcion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("descripcion", descripcion);
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ParametroEmpresaIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findParametroEmpresaByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ParametroEmpresaEJB.class, aMap);      
    }

/////////////////
}
