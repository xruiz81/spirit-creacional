package com.spirit.facturacion.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.facturacion.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ListaPrecioSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ListaPrecioSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.ListaPrecioIf addListaPrecio(com.spirit.facturacion.entity.ListaPrecioIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ListaPrecioEJB value = new ListaPrecioEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setEmpresaId(model.getEmpresaId());
      value.setReferenciaFisica(model.getReferenciaFisica());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setFechaInicio(model.getFechaInicio());
      value.setFechaFinal(model.getFechaFinal());
      value.setEstado(model.getEstado());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en listaPrecio.", e);
			throw new GenericBusinessException(
					"Error al insertar información en listaPrecio.");
      }
     
      return getListaPrecio(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveListaPrecio(com.spirit.facturacion.entity.ListaPrecioIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ListaPrecioEJB data = new ListaPrecioEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setEmpresaId(model.getEmpresaId());
      data.setReferenciaFisica(model.getReferenciaFisica());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setFechaInicio(model.getFechaInicio());
      data.setFechaFinal(model.getFechaFinal());
      data.setEstado(model.getEstado());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en listaPrecio.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en listaPrecio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteListaPrecio(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ListaPrecioEJB data = manager.find(ListaPrecioEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en listaPrecio.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en listaPrecio.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.facturacion.entity.ListaPrecioIf getListaPrecio(java.lang.Long id) {
      return (ListaPrecioEJB)queryManagerLocal.find(ListaPrecioEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getListaPrecioList() {
	  return queryManagerLocal.singleClassList(ListaPrecioEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getListaPrecioList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ListaPrecioEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getListaPrecioListSize() {
      Query countQuery = manager.createQuery("select count(*) from ListaPrecioEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByEmpresaId(java.lang.Long empresaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("empresaId", empresaId);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByReferenciaFisica(java.lang.String referenciaFisica) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("referenciaFisica", referenciaFisica);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByFechaCreacion(java.sql.Date fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByFechaInicio(java.sql.Date fechaInicio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaInicio", fechaInicio);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByFechaFinal(java.sql.Date fechaFinal) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaFinal", fechaFinal);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ListaPrecioIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findListaPrecioByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ListaPrecioEJB.class, aMap);      
    }

/////////////////
}
