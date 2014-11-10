package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SalarioMinimoVitalSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.SalarioMinimoVitalIf addSalarioMinimoVital(com.spirit.nomina.entity.SalarioMinimoVitalIf model) throws GenericBusinessException;

   void saveSalarioMinimoVital(com.spirit.nomina.entity.SalarioMinimoVitalIf model) throws GenericBusinessException;

   void deleteSalarioMinimoVital(java.lang.Long id) throws GenericBusinessException;

   Collection findSalarioMinimoVitalByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.SalarioMinimoVitalIf getSalarioMinimoVital(java.lang.Long id) throws GenericBusinessException;

   Collection getSalarioMinimoVitalList() throws GenericBusinessException;

   Collection getSalarioMinimoVitalList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSalarioMinimoVitalListSize() throws GenericBusinessException;
    java.util.Collection findSalarioMinimoVitalById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSalarioMinimoVitalByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findSalarioMinimoVitalByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findSalarioMinimoVitalByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;

}
