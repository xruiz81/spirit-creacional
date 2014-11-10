package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolPagoDetalleUtilidadSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.RolPagoDetalleUtilidadIf addRolPagoDetalleUtilidad(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf model) throws GenericBusinessException;

   void saveRolPagoDetalleUtilidad(com.spirit.nomina.entity.RolPagoDetalleUtilidadIf model) throws GenericBusinessException;

   void deleteRolPagoDetalleUtilidad(java.lang.Long id) throws GenericBusinessException;

   Collection findRolPagoDetalleUtilidadByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.RolPagoDetalleUtilidadIf getRolPagoDetalleUtilidad(java.lang.Long id) throws GenericBusinessException;

   Collection getRolPagoDetalleUtilidadList() throws GenericBusinessException;

   Collection getRolPagoDetalleUtilidadList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolPagoDetalleUtilidadListSize() throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByRolpagoId(java.lang.Long rolpagoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByContratoUtilidadId(java.lang.Long contratoUtilidadId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByContratoId(java.lang.Long contratoId) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByCargo(java.lang.String cargo) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByFechaIngreso(java.sql.Date fechaIngreso) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByFechaSalida(java.sql.Date fechaSalida) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByGenero(java.lang.String genero) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByDiasLaborados(java.lang.Integer diasLaborados) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByUtilidadContrato(java.math.BigDecimal utilidadContrato) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByNumeroCargas(java.lang.Integer numeroCargas) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByDiasCargas(java.lang.Integer diasCargas) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByUtilidadCargas(java.math.BigDecimal utilidadCargas) throws GenericBusinessException;
    java.util.Collection findRolPagoDetalleUtilidadByTotal(java.math.BigDecimal total) throws GenericBusinessException;

}
