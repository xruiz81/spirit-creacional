package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FacturastipoproductodetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.FacturastipoproductodetalleIf addFacturastipoproductodetalle(com.spirit.pos.entity.FacturastipoproductodetalleIf model) throws GenericBusinessException;

   void saveFacturastipoproductodetalle(com.spirit.pos.entity.FacturastipoproductodetalleIf model) throws GenericBusinessException;

   void deleteFacturastipoproductodetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findFacturastipoproductodetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.FacturastipoproductodetalleIf getFacturastipoproductodetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getFacturastipoproductodetalleList() throws GenericBusinessException;

   Collection getFacturastipoproductodetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFacturastipoproductodetalleListSize() throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByModelo(java.lang.String modelo) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByColor(java.lang.String color) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByTalla(java.lang.String talla) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByProducto(java.lang.Long producto) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByCant(java.math.BigDecimal cant) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByDev(java.math.BigDecimal dev) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByCantfinal(java.math.BigDecimal cantfinal) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByPreciouni(java.math.BigDecimal preciouni) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByValorsinivaventas(java.math.BigDecimal valorsinivaventas) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByDescuentoventas(java.math.BigDecimal descuentoventas) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByIvaventas(java.math.BigDecimal ivaventas) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByTotalventas(java.math.BigDecimal totalventas) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByValorsinivadev(java.math.BigDecimal valorsinivadev) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByIvadev(java.math.BigDecimal ivadev) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByTotaldev(java.math.BigDecimal totaldev) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByModeloId(java.lang.Long modeloId) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByColorId(java.lang.Long colorId) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByTipoproducto(java.lang.Long tipoproducto) throws GenericBusinessException;
    java.util.Collection findFacturastipoproductodetalleByTallaId(java.lang.Long tallaId) throws GenericBusinessException;

}
