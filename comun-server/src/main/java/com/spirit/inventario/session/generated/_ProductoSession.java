package com.spirit.inventario.session.generated;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.exception.GenericBusinessException;
import com.spirit.comun.querys.JPAManagerLocal;
import com.spirit.inventario.entity.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public abstract class _ProductoSession {

   @PersistenceContext(unitName = "spirit")
   protected EntityManager manager;
   
   @EJB
   protected JPAManagerLocal queryManagerLocal;

   /**
    * The logger object.
    */
   private static Logger log = LogService.getLogger(_ProductoSession.class);

   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ProductoIf addProducto(com.spirit.inventario.entity.ProductoIf model)
     throws com.spirit.exception.GenericBusinessException 
   {
      ProductoEJB value = new ProductoEJB();
      try {
      value.setId(model.getId());
      value.setGenericoId(model.getGenericoId());
      value.setPresentacionId(model.getPresentacionId());
      value.setProveedorId(model.getProveedorId());
      value.setOrigenProducto(model.getOrigenProducto());
      value.setCodigoBarras(model.getCodigoBarras());
      value.setCosto(model.getCosto());
      value.setFechaCreacion(model.getFechaCreacion());
      value.setRebate(model.getRebate());
      value.setAceptapromocion(model.getAceptapromocion());
      value.setPermiteventa(model.getPermiteventa());
      value.setAceptadevolucion(model.getAceptadevolucion());
      value.setCambioprecio(model.getCambioprecio());
      value.setEstado(model.getEstado());
      value.setCodigo(model.getCodigo());
      value.setMargenminimo(model.getMargenminimo());
      value.setSubproveedor(model.getSubproveedor());
      value.setCostoLifo(model.getCostoLifo());
      value.setCostoFifo(model.getCostoFifo());
      value.setPesoBruto(model.getPesoBruto());
      value.setPesoNeto(model.getPesoNeto());
      value.setColorId(model.getColorId());
      value.setMarcaId(model.getMarcaId());
      value.setModeloId(model.getModeloId());
      value.setGenerarCodigoBarras(model.getGenerarCodigoBarras());
      queryManagerLocal.persist(value);
      } catch (Exception e) {
        log.error("Error al insertar información en producto.", e);
			throw new GenericBusinessException(
					"Error al insertar información en producto.");
      }
     
      return getProducto(value.getPrimaryKey());
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void saveProducto(com.spirit.inventario.entity.ProductoIf model)
    throws com.spirit.exception.GenericBusinessException 
   {
      
      try
      {
      ProductoEJB data = new ProductoEJB();
      data.setId(model.getId());
      data.setGenericoId(model.getGenericoId());
      data.setPresentacionId(model.getPresentacionId());
      data.setProveedorId(model.getProveedorId());
      data.setOrigenProducto(model.getOrigenProducto());
      data.setCodigoBarras(model.getCodigoBarras());
      data.setCosto(model.getCosto());
      data.setFechaCreacion(model.getFechaCreacion());
      data.setRebate(model.getRebate());
      data.setAceptapromocion(model.getAceptapromocion());
      data.setPermiteventa(model.getPermiteventa());
      data.setAceptadevolucion(model.getAceptadevolucion());
      data.setCambioprecio(model.getCambioprecio());
      data.setEstado(model.getEstado());
      data.setCodigo(model.getCodigo());
      data.setMargenminimo(model.getMargenminimo());
      data.setSubproveedor(model.getSubproveedor());
      data.setCostoLifo(model.getCostoLifo());
      data.setCostoFifo(model.getCostoFifo());
      data.setPesoBruto(model.getPesoBruto());
      data.setPesoNeto(model.getPesoNeto());
      data.setColorId(model.getColorId());
      data.setMarcaId(model.getMarcaId());
      data.setModeloId(model.getModeloId());
      data.setGenerarCodigoBarras(model.getGenerarCodigoBarras());
       queryManagerLocal.save(data);
      } catch (Exception e) {
        log.error("Error al actualizar información en producto.", e);
			throw new GenericBusinessException(
					"Error al actualizar información en producto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public void deleteProducto(java.lang.Long id) throws com.spirit.exception.GenericBusinessException 
   {
    try
      {

      ProductoEJB data = manager.find(ProductoEJB.class, id);
      queryManagerLocal.remove(data);

      } catch (Exception e) {
        log.error("Error al eliminar información en producto.", e);
			throw new GenericBusinessException(
					"Error al eliminar información en producto.");
      }

   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public com.spirit.inventario.entity.ProductoIf getProducto(java.lang.Long id) {
      return (ProductoEJB)queryManagerLocal.find(ProductoEJB.class, id);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoList() {
	  return queryManagerLocal.singleClassList(ProductoEJB.class);
   }

   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public Collection getProductoList(int startIndex, int endIndex) {
		return queryManagerLocal.singleClassListPaginado(ProductoEJB.class,
				startIndex, endIndex);
   }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
   public int getProductoListSize() {
      Query countQuery = manager.createQuery("select count(*) from ProductoEJB");
      List countQueryResult = countQuery.getResultList();
      Integer countResult = (Integer) countQueryResult.get(0);
      log.debug("The list size is: " + countResult.intValue());
      return countResult.intValue();
   }



   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoById(java.lang.Long id) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("id", id);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByGenericoId(java.lang.Long genericoId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("genericoId", genericoId);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByPresentacionId(java.lang.Long presentacionId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("presentacionId", presentacionId);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByProveedorId(java.lang.Long proveedorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("proveedorId", proveedorId);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByOrigenProducto(java.lang.String origenProducto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("origenProducto", origenProducto);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByCodigoBarras(java.lang.String codigoBarras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigoBarras", codigoBarras);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByCosto(java.math.BigDecimal costo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costo", costo);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByFechaCreacion(java.sql.Timestamp fechaCreacion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("fechaCreacion", fechaCreacion);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByRebate(java.math.BigDecimal rebate) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("rebate", rebate);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByAceptapromocion(java.lang.String aceptapromocion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("aceptapromocion", aceptapromocion);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByPermiteventa(java.lang.String permiteventa) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("permiteventa", permiteventa);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByAceptadevolucion(java.lang.String aceptadevolucion) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("aceptadevolucion", aceptadevolucion);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByCambioprecio(java.lang.String cambioprecio) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("cambioprecio", cambioprecio);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByEstado(java.lang.String estado) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("estado", estado);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByCodigo(java.lang.String codigo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("codigo", codigo);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByMargenminimo(java.math.BigDecimal margenminimo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("margenminimo", margenminimo);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoBySubproveedor(java.lang.String subproveedor) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("subproveedor", subproveedor);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByCostoLifo(java.math.BigDecimal costoLifo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costoLifo", costoLifo);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByCostoFifo(java.math.BigDecimal costoFifo) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("costoFifo", costoFifo);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByPesoBruto(java.math.BigDecimal pesoBruto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pesoBruto", pesoBruto);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByPesoNeto(java.math.BigDecimal pesoNeto) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("pesoNeto", pesoNeto);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByColorId(java.lang.Long colorId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("colorId", colorId);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByMarcaId(java.lang.Long marcaId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("marcaId", marcaId);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByModeloId(java.lang.Long modeloId) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("modeloId", modeloId);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }


   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByGenerarCodigoBarras(java.lang.String generarCodigoBarras) {
		HashMap<String, Object> parametros=new HashMap<String, Object>();
  		parametros.put("generarCodigoBarras", generarCodigoBarras);
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, parametros);
    }
//////////////

    /**
     *
     * Retrieves a list of data object for the specified query Map.
     *
     * //@param Map $field.Name the field
     * @return Collection of ProductoIf data objects, empty list in case no results were found.
     */
   @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public java.util.Collection findProductoByQuery(Map aMap) {
		return queryManagerLocal.singleClassQueryList(ProductoEJB.class, aMap);      
    }

/////////////////
}
