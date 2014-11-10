package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrecioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.PrecioIf addPrecio(com.spirit.facturacion.entity.PrecioIf model) throws GenericBusinessException;

   void savePrecio(com.spirit.facturacion.entity.PrecioIf model) throws GenericBusinessException;

   void deletePrecio(java.lang.Long id) throws GenericBusinessException;

   Collection findPrecioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.PrecioIf getPrecio(java.lang.Long id) throws GenericBusinessException;

   Collection getPrecioList() throws GenericBusinessException;

   Collection getPrecioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrecioListSize() throws GenericBusinessException;
    java.util.Collection findPrecioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrecioByListaprecioId(java.lang.Long listaprecioId) throws GenericBusinessException;
    java.util.Collection findPrecioByProductoId(java.lang.Long productoId) throws GenericBusinessException;
    java.util.Collection findPrecioByPrecio(java.math.BigDecimal precio) throws GenericBusinessException;
    java.util.Collection findPrecioByCambiarPrecio(java.lang.String cambiarPrecio) throws GenericBusinessException;
    java.util.Collection findPrecioByEstado(java.lang.String estado) throws GenericBusinessException;

}
