package com.spirit.pos.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CajasesionMovimientosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.pos.entity.CajasesionMovimientosIf addCajasesionMovimientos(com.spirit.pos.entity.CajasesionMovimientosIf model) throws GenericBusinessException;

   void saveCajasesionMovimientos(com.spirit.pos.entity.CajasesionMovimientosIf model) throws GenericBusinessException;

   void deleteCajasesionMovimientos(java.lang.Long id) throws GenericBusinessException;

   Collection findCajasesionMovimientosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.pos.entity.CajasesionMovimientosIf getCajasesionMovimientos(java.lang.Long id) throws GenericBusinessException;

   Collection getCajasesionMovimientosList() throws GenericBusinessException;

   Collection getCajasesionMovimientosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCajasesionMovimientosListSize() throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByCajasesionId(java.lang.Long cajasesionId) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByTipomovimiento(java.lang.String tipomovimiento) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByCajadestinoId(java.lang.Long cajadestinoId) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByRevisado(java.lang.String revisado) throws GenericBusinessException;
    java.util.Collection findCajasesionMovimientosByNumDoc(java.lang.String numDoc) throws GenericBusinessException;

}
