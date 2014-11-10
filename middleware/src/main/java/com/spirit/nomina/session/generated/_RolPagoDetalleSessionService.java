package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolPagoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.RolPagoDetalleIf addRolPagoDetalle(com.spirit.nomina.entity.RolPagoDetalleIf model) throws GenericBusinessException;

   void saveRolPagoDetalle(com.spirit.nomina.entity.RolPagoDetalleIf model) throws GenericBusinessException;

   void deleteRolPagoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findRolPagoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.RolPagoDetalleIf getRolPagoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getRolPagoDetalleList() throws GenericBusinessException;

   Collection getRolPagoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolPagoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByRolpagoId(java.lang.Long rolpagoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByContratoId(java.lang.Long contratoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByRubroId(java.lang.Long rubroId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByRubroEventualId(java.lang.Long rubroEventualId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByFechaPago(java.sql.Date fechaPago) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByTipoPagoId(java.lang.Long tipoPagoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByCuentaBancariaId(java.lang.Long cuentaBancariaId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByPreimpreso(java.lang.String preimpreso) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleByAsientoId(java.lang.Long asientoId) throws GenericBusinessException;

}
