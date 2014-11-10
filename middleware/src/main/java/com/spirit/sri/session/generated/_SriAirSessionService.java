package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriAirSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriAirIf addSriAir(com.spirit.sri.entity.SriAirIf model) throws GenericBusinessException;

   void saveSriAir(com.spirit.sri.entity.SriAirIf model) throws GenericBusinessException;

   void deleteSriAir(java.lang.Long id) throws GenericBusinessException;

   Collection findSriAirByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriAirIf getSriAir(java.lang.Long id) throws GenericBusinessException;

   Collection getSriAirList() throws GenericBusinessException;

   Collection getSriAirList(int startIndex, int endIndex) throws GenericBusinessException;
   
   	java.util.Collection findSriAirCodigoFechaActual() throws GenericBusinessException;

   int getSriAirListSize() throws GenericBusinessException;
    java.util.Collection findSriAirById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriAirByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSriAirByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findSriAirByPorcentaje(java.math.BigDecimal porcentaje) throws GenericBusinessException;
    java.util.Collection findSriAirByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findSriAirByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;

}
