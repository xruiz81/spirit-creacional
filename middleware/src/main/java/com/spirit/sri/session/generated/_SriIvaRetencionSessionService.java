package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriIvaRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriIvaRetencionIf addSriIvaRetencion(com.spirit.sri.entity.SriIvaRetencionIf model) throws GenericBusinessException;

   void saveSriIvaRetencion(com.spirit.sri.entity.SriIvaRetencionIf model) throws GenericBusinessException;

   void deleteSriIvaRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findSriIvaRetencionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriIvaRetencionIf getSriIvaRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection getSriIvaRetencionList() throws GenericBusinessException;

   Collection getSriIvaRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriIvaRetencionListSize() throws GenericBusinessException;
    java.util.Collection findSriIvaRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriIvaRetencionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSriIvaRetencionByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findSriIvaRetencionByPorcentaje(java.math.BigDecimal porcentaje) throws GenericBusinessException;
    java.util.Collection findSriIvaRetencionByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findSriIvaRetencionByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;

}
