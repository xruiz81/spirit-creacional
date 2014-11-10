package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FormaPagoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.FormaPagoIf addFormaPago(com.spirit.cartera.entity.FormaPagoIf model) throws GenericBusinessException;

   void saveFormaPago(com.spirit.cartera.entity.FormaPagoIf model) throws GenericBusinessException;

   void deleteFormaPago(java.lang.Long id) throws GenericBusinessException;

   Collection findFormaPagoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.FormaPagoIf getFormaPago(java.lang.Long id) throws GenericBusinessException;

   Collection getFormaPagoList() throws GenericBusinessException;

   Collection getFormaPagoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFormaPagoListSize() throws GenericBusinessException;
    java.util.Collection findFormaPagoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFormaPagoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findFormaPagoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findFormaPagoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findFormaPagoByDiasInicio(java.lang.Integer diasInicio) throws GenericBusinessException;
    java.util.Collection findFormaPagoByDiasFin(java.lang.Integer diasFin) throws GenericBusinessException;
    java.util.Collection findFormaPagoByDescuentoVenta(java.math.BigDecimal descuentoVenta) throws GenericBusinessException;
    java.util.Collection findFormaPagoByDescuentoCartera(java.math.BigDecimal descuentoCartera) throws GenericBusinessException;
    java.util.Collection findFormaPagoByInteres(java.math.BigDecimal interes) throws GenericBusinessException;

}
