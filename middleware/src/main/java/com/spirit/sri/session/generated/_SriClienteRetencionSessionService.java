package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriClienteRetencionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriClienteRetencionIf addSriClienteRetencion(com.spirit.sri.entity.SriClienteRetencionIf model) throws GenericBusinessException;

   void saveSriClienteRetencion(com.spirit.sri.entity.SriClienteRetencionIf model) throws GenericBusinessException;

   void deleteSriClienteRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection findSriClienteRetencionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriClienteRetencionIf getSriClienteRetencion(java.lang.Long id) throws GenericBusinessException;

   Collection getSriClienteRetencionList() throws GenericBusinessException;

   Collection getSriClienteRetencionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriClienteRetencionListSize() throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionByTipoRetencion(java.lang.String tipoRetencion) throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionByPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion) throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findSriClienteRetencionByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;

}
