package com.spirit.medios.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.medios.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ProductoClienteSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ProductoClienteSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ProductoClienteIf addProductoCliente(com.spirit.medios.entity.ProductoClienteIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ProductoClienteEJB value = new ProductoClienteEJB();
      try {
      value.setId(model.getId());
      value.setCodigo(model.getCodigo());
      value.setNombre(model.getNombre());
      value.setClienteId(model.getClienteId());
      value.setCreativoId(model.getCreativoId());
      value.setEjecutivoId(model.getEjecutivoId());
      value.setEstado(model.getEstado());
      value.setMarcaProductoId(model.getMarcaProductoId());
      value.setMarcaProductoNombre(model.getMarcaProductoNombre());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en productoCliente.", e);
			throw new GenericBusinessException(
					"Error al insertar información en productoCliente.");
      }
     
      return getProductoCliente(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveProductoCliente(com.spirit.medios.entity.ProductoClienteIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ProductoClienteEJB data = new ProductoClienteEJB();
      data.setId(model.getId());
      data.setCodigo(model.getCodigo());
      data.setNombre(model.getNombre());
      data.setClienteId(model.getClienteId());
      data.setCreativoId(model.getCreativoId());
      data.setEjecutivoId(model.getEjecutivoId());
      data.setEstado(model.getEstado());
      data.setMarcaProductoId(model.getMarcaProductoId());
      data.setMarcaProductoNombre(model.getMarcaProductoNombre());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en productoCliente.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en productoCliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteProductoCliente(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ProductoClienteEJB data = manager.find(ProductoClienteEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en productoCliente.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en productoCliente.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.medios.entity.ProductoClienteIf getProductoCliente(java.lang.Long id) {
      return (ProductoClienteEJB)queryManagerLocal.find(ProductoClienteEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoClienteList() {
	  return queryManagerLocal.singleClassList(ProductoClienteEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoClienteList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ProductoClienteEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getProductoClienteListSize() {
      Query countQuery = manager.createQuery("select count(*) from ProductoClienteEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByNombre(java.lang.String nombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("nombre", nombre);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByClienteId(java.lang.Long clienteId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("clienteId", clienteId);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByCreativoId(java.lang.Long creativoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("creativoId", creativoId);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByEjecutivoId(java.lang.Long ejecutivoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("ejecutivoId", ejecutivoId);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByMarcaProductoId(java.lang.Long marcaProductoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("marcaProductoId", marcaProductoId);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByMarcaProductoNombre(java.lang.String marcaProductoNombre) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("marcaProductoNombre", marcaProductoNombre);
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ProductoClienteIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoClienteByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ProductoClienteEJB.class, aMap);      
    }

/////////////////
}
