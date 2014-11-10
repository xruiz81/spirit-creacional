package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PresupuestosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PresupuestosIf addPresupuestos(com.spirit.medios.entity.PresupuestosIf model) throws GenericBusinessException;

   void savePresupuestos(com.spirit.medios.entity.PresupuestosIf model) throws GenericBusinessException;

   void deletePresupuestos(java.lang.Long id) throws GenericBusinessException;

   Collection findPresupuestosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PresupuestosIf getPresupuestos(java.lang.Long id) throws GenericBusinessException;

   Collection getPresupuestosList() throws GenericBusinessException;

   Collection getPresupuestosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPresupuestosListSize() throws GenericBusinessException;
    java.util.Collection findPresupuestosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPresupuestosByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPresupuestosByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findPresupuestosByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findPresupuestosByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findPresupuestosByFechaAprobacion(java.sql.Timestamp fechaAprobacion) throws GenericBusinessException;
    java.util.Collection findPresupuestosBySubtotal(java.math.BigDecimal subtotal) throws GenericBusinessException;
    java.util.Collection findPresupuestosByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findPresupuestosByComision(java.math.BigDecimal comision) throws GenericBusinessException;
    java.util.Collection findPresupuestosByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findPresupuestosByTotal(java.math.BigDecimal total) throws GenericBusinessException;
    java.util.Collection findPresupuestosByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPresupuestosByPrepago(java.lang.String prepago) throws GenericBusinessException;
    java.util.Collection findPresupuestosByRevision(java.lang.String revision) throws GenericBusinessException;
    java.util.Collection findPresupuestosByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;

}
