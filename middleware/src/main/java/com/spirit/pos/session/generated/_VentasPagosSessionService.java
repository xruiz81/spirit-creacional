package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _VentasPagosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.VentasPagosIf addVentasPagos(com.spirit.pos.entity.VentasPagosIf model) throws GenericBusinessException;

   void saveVentasPagos(com.spirit.pos.entity.VentasPagosIf model) throws GenericBusinessException;

   void deleteVentasPagos(java.lang.Long id) throws GenericBusinessException;

   Collection findVentasPagosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.VentasPagosIf getVentasPagos(java.lang.Long id) throws GenericBusinessException;

   Collection getVentasPagosList() throws GenericBusinessException;

   Collection getVentasPagosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getVentasPagosListSize() throws GenericBusinessException;
    java.util.Collection findVentasPagosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findVentasPagosByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findVentasPagosByTipo(java.lang.Long tipo) throws GenericBusinessException;
    java.util.Collection findVentasPagosByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findVentasPagosByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findVentasPagosByVentastrackId(java.lang.Long ventastrackId) throws GenericBusinessException;
    java.util.Collection findVentasPagosByRevisado(java.lang.String revisado) throws GenericBusinessException;
    java.util.Collection findVentasPagosByNumDoc(java.lang.String numDoc) throws GenericBusinessException;

}
