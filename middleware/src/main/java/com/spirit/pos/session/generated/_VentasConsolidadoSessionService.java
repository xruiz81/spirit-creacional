package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _VentasConsolidadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.VentasConsolidadoIf addVentasConsolidado(com.spirit.pos.entity.VentasConsolidadoIf model) throws GenericBusinessException;

   void saveVentasConsolidado(com.spirit.pos.entity.VentasConsolidadoIf model) throws GenericBusinessException;

   void deleteVentasConsolidado(java.lang.Long id) throws GenericBusinessException;

   Collection findVentasConsolidadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.VentasConsolidadoIf getVentasConsolidado(java.lang.Long id) throws GenericBusinessException;

   Collection getVentasConsolidadoList() throws GenericBusinessException;

   Collection getVentasConsolidadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getVentasConsolidadoListSize() throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByFechaCierre(java.sql.Timestamp fechaCierre) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByCajeroNombre(java.lang.String cajeroNombre) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByCajeroCedula(java.lang.String cajeroCedula) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByCajaCodigo(java.lang.String cajaCodigo) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByCajaNombre(java.lang.String cajaNombre) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorEfectivo(java.math.BigDecimal valorEfectivo) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorTarjeta(java.math.BigDecimal valorTarjeta) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorGiftcards(java.math.BigDecimal valorGiftcards) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorDevoluciones(java.math.BigDecimal valorDevoluciones) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorCajaInicial(java.math.BigDecimal valorCajaInicial) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorCheque(java.math.BigDecimal valorCheque) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorDonacion(java.math.BigDecimal valorDonacion) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorCredito(java.math.BigDecimal valorCredito) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorCajaIngreso(java.math.BigDecimal valorCajaIngreso) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorCajaEgreso(java.math.BigDecimal valorCajaEgreso) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorDescuadre(java.math.BigDecimal valorDescuadre) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorMultas(java.math.BigDecimal valorMultas) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByValorDocumentos(java.math.BigDecimal valorDocumentos) throws GenericBusinessException;
    java.util.Collection findVentasConsolidadoByFechaApertura(java.sql.Timestamp fechaApertura) throws GenericBusinessException;

}
