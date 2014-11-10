package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoPagoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoPagoIf addTipoPago(com.spirit.general.entity.TipoPagoIf model) throws GenericBusinessException;

   void saveTipoPago(com.spirit.general.entity.TipoPagoIf model) throws GenericBusinessException;

   void deleteTipoPago(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoPagoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoPagoIf getTipoPago(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoPagoList() throws GenericBusinessException;

   Collection getTipoPagoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoPagoListSize() throws GenericBusinessException;
    java.util.Collection findTipoPagoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoPagoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoPagoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoPagoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
