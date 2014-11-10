package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.ProductoIf addProducto(com.spirit.inventario.entity.ProductoIf model) throws GenericBusinessException;

   void saveProducto(com.spirit.inventario.entity.ProductoIf model) throws GenericBusinessException;

   void deleteProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.ProductoIf getProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getProductoList() throws GenericBusinessException;

   Collection getProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getProductoListSize() throws GenericBusinessException;
    java.util.Collection findProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findProductoByGenericoId(java.lang.Long genericoId) throws GenericBusinessException;
    java.util.Collection findProductoByPresentacionId(java.lang.Long presentacionId) throws GenericBusinessException;
    java.util.Collection findProductoByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;
    java.util.Collection findProductoByOrigenProducto(java.lang.String origenProducto) throws GenericBusinessException;
    java.util.Collection findProductoByCodigoBarras(java.lang.String codigoBarras) throws GenericBusinessException;
    java.util.Collection findProductoByCosto(java.math.BigDecimal costo) throws GenericBusinessException;
    java.util.Collection findProductoByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findProductoByRebate(java.math.BigDecimal rebate) throws GenericBusinessException;
    java.util.Collection findProductoByAceptapromocion(java.lang.String aceptapromocion) throws GenericBusinessException;
    java.util.Collection findProductoByPermiteventa(java.lang.String permiteventa) throws GenericBusinessException;
    java.util.Collection findProductoByAceptadevolucion(java.lang.String aceptadevolucion) throws GenericBusinessException;
    java.util.Collection findProductoByCambioprecio(java.lang.String cambioprecio) throws GenericBusinessException;
    java.util.Collection findProductoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findProductoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findProductoByMargenminimo(java.math.BigDecimal margenminimo) throws GenericBusinessException;
    java.util.Collection findProductoBySubproveedor(java.lang.String subproveedor) throws GenericBusinessException;
    java.util.Collection findProductoByCostoLifo(java.math.BigDecimal costoLifo) throws GenericBusinessException;
    java.util.Collection findProductoByCostoFifo(java.math.BigDecimal costoFifo) throws GenericBusinessException;
    java.util.Collection findProductoByPesoBruto(java.math.BigDecimal pesoBruto) throws GenericBusinessException;
    java.util.Collection findProductoByPesoNeto(java.math.BigDecimal pesoNeto) throws GenericBusinessException;
    java.util.Collection findProductoByColorId(java.lang.Long colorId) throws GenericBusinessException;
    java.util.Collection findProductoByMarcaId(java.lang.Long marcaId) throws GenericBusinessException;
    java.util.Collection findProductoByModeloId(java.lang.Long modeloId) throws GenericBusinessException;
    java.util.Collection findProductoByGenerarCodigoBarras(java.lang.String generarCodigoBarras) throws GenericBusinessException;

}
