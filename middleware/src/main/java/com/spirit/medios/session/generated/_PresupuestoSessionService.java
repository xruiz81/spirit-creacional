package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PresupuestoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PresupuestoIf addPresupuesto(com.spirit.medios.entity.PresupuestoIf model) throws GenericBusinessException;

   void savePresupuesto(com.spirit.medios.entity.PresupuestoIf model) throws GenericBusinessException;

   void deletePresupuesto(java.lang.Long id) throws GenericBusinessException;

   Collection findPresupuestoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PresupuestoIf getPresupuesto(java.lang.Long id) throws GenericBusinessException;

   Collection getPresupuestoList() throws GenericBusinessException;

   Collection getPresupuestoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPresupuestoListSize() throws GenericBusinessException;
    java.util.Collection findPresupuestoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPresupuestoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPresupuestoByOrdentrabajodetId(java.lang.Long ordentrabajodetId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByClienteCondicionId(java.lang.Long clienteCondicionId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByPlanmedioId(java.lang.Long planmedioId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findPresupuestoByModificacion(java.lang.Integer modificacion) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFechaValidez(java.sql.Timestamp fechaValidez) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFechaEnvio(java.sql.Timestamp fechaEnvio) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFechaCancelacion(java.sql.Timestamp fechaCancelacion) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFechaAceptacion(java.sql.Timestamp fechaAceptacion) throws GenericBusinessException;
    java.util.Collection findPresupuestoByValorbruto(java.math.BigDecimal valorbruto) throws GenericBusinessException;
    java.util.Collection findPresupuestoByDescuento(java.math.BigDecimal descuento) throws GenericBusinessException;
    java.util.Collection findPresupuestoByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findPresupuestoByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findPresupuestoByCabecera(java.lang.String cabecera) throws GenericBusinessException;
    java.util.Collection findPresupuestoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFormaPagoId(java.lang.Long formaPagoId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByDiasValidez(java.lang.Integer diasValidez) throws GenericBusinessException;
    java.util.Collection findPresupuestoByPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia) throws GenericBusinessException;
    java.util.Collection findPresupuestoByUsuarioCreacionId(java.lang.Long usuarioCreacionId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findPresupuestoByUsuarioActualizacionId(java.lang.Long usuarioActualizacionId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByFechaActualizacion(java.sql.Timestamp fechaActualizacion) throws GenericBusinessException;
    java.util.Collection findPresupuestoByTemaCampana(java.lang.String temaCampana) throws GenericBusinessException;
    java.util.Collection findPresupuestoByAutorizacionSap(java.lang.String autorizacionSap) throws GenericBusinessException;
    java.util.Collection findPresupuestoByDescuentosVarios(java.math.BigDecimal descuentosVarios) throws GenericBusinessException;
    java.util.Collection findPresupuestoByDescuentoEspecial(java.math.BigDecimal descuentoEspecial) throws GenericBusinessException;
    java.util.Collection findPresupuestoByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByPrepago(java.lang.String prepago) throws GenericBusinessException;
    java.util.Collection findPresupuestoByReferenciaId(java.lang.Long referenciaId) throws GenericBusinessException;
    java.util.Collection findPresupuestoByTipoReferencia(java.lang.String tipoReferencia) throws GenericBusinessException;

}
