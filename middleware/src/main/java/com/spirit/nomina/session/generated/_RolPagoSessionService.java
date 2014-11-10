package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RolPagoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.RolPagoIf addRolPago(com.spirit.nomina.entity.RolPagoIf model) throws GenericBusinessException;

   void saveRolPago(com.spirit.nomina.entity.RolPagoIf model) throws GenericBusinessException;

   void deleteRolPago(java.lang.Long id) throws GenericBusinessException;

   Collection findRolPagoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.RolPagoIf getRolPago(java.lang.Long id) throws GenericBusinessException;

   Collection getRolPagoList() throws GenericBusinessException;

   Collection getRolPagoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRolPagoListSize() throws GenericBusinessException;
    java.util.Collection findRolPagoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRolPagoByTiporolId(java.lang.Long tiporolId) throws GenericBusinessException;
    java.util.Collection findRolPagoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findRolPagoByMes(java.lang.String mes) throws GenericBusinessException;
    java.util.Collection findRolPagoByAnio(java.lang.String anio) throws GenericBusinessException;
    java.util.Collection findRolPagoByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findRolPagoByTipocontratoId(java.lang.Long tipocontratoId) throws GenericBusinessException;
    java.util.Collection findRolPagoByAsientoGenerado(java.lang.String asientoGenerado) throws GenericBusinessException;

}
