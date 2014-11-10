package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ContratoUtilidadSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ContratoUtilidadIf addContratoUtilidad(com.spirit.nomina.entity.ContratoUtilidadIf model) throws GenericBusinessException;

   void saveContratoUtilidad(com.spirit.nomina.entity.ContratoUtilidadIf model) throws GenericBusinessException;

   void deleteContratoUtilidad(java.lang.Long id) throws GenericBusinessException;

   Collection findContratoUtilidadByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ContratoUtilidadIf getContratoUtilidad(java.lang.Long id) throws GenericBusinessException;

   Collection getContratoUtilidadList() throws GenericBusinessException;

   Collection getContratoUtilidadList(int startIndex, int endIndex) throws GenericBusinessException;

   int getContratoUtilidadListSize() throws GenericBusinessException;
    java.util.Collection findContratoUtilidadById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByUtilidad(java.math.BigDecimal utilidad) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByPorcentajeContrato(java.math.BigDecimal porcentajeContrato) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByPorcentajeCarga(java.math.BigDecimal porcentajeCarga) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findContratoUtilidadByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
