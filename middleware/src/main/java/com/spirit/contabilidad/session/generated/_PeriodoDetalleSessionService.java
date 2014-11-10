package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PeriodoDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.PeriodoDetalleIf addPeriodoDetalle(com.spirit.contabilidad.entity.PeriodoDetalleIf model) throws GenericBusinessException;

   void savePeriodoDetalle(com.spirit.contabilidad.entity.PeriodoDetalleIf model) throws GenericBusinessException;

   void deletePeriodoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findPeriodoDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.PeriodoDetalleIf getPeriodoDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getPeriodoDetalleList() throws GenericBusinessException;

   Collection getPeriodoDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPeriodoDetalleListSize() throws GenericBusinessException;
    java.util.Collection findPeriodoDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPeriodoDetalleByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findPeriodoDetalleByPeriodoId(java.lang.Long periodoId) throws GenericBusinessException;
    java.util.Collection findPeriodoDetalleByMes(java.lang.String mes) throws GenericBusinessException;
    java.util.Collection findPeriodoDetalleByAnio(java.lang.String anio) throws GenericBusinessException;

}
