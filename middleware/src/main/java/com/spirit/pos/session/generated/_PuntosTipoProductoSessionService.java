package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PuntosTipoProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.PuntosTipoProductoIf addPuntosTipoProducto(com.spirit.pos.entity.PuntosTipoProductoIf model) throws GenericBusinessException;

   void savePuntosTipoProducto(com.spirit.pos.entity.PuntosTipoProductoIf model) throws GenericBusinessException;

   void deletePuntosTipoProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findPuntosTipoProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.PuntosTipoProductoIf getPuntosTipoProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getPuntosTipoProductoList() throws GenericBusinessException;

   Collection getPuntosTipoProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPuntosTipoProductoListSize() throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoByTipoProductoId(java.lang.Long tipoProductoId) throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoByTarjetaTipoId(java.lang.Long tarjetaTipoId) throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoByPuntosReferido(java.math.BigDecimal puntosReferido) throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoByPuntosCompras(java.math.BigDecimal puntosCompras) throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoByPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) throws GenericBusinessException;
    java.util.Collection findPuntosTipoProductoByPorcentajeDineroCompras(java.math.BigDecimal porcentajeDineroCompras) throws GenericBusinessException;

}
