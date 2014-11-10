package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ImpuestoRentaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.ImpuestoRentaIf addImpuestoRenta(com.spirit.sri.entity.ImpuestoRentaIf model) throws GenericBusinessException;

   void saveImpuestoRenta(com.spirit.sri.entity.ImpuestoRentaIf model) throws GenericBusinessException;

   void deleteImpuestoRenta(java.lang.Long id) throws GenericBusinessException;

   Collection findImpuestoRentaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.ImpuestoRentaIf getImpuestoRenta(java.lang.Long id) throws GenericBusinessException;

   Collection getImpuestoRentaList() throws GenericBusinessException;

   Collection getImpuestoRentaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getImpuestoRentaListSize() throws GenericBusinessException;
    java.util.Collection findImpuestoRentaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaByPorcentaje(java.math.BigDecimal porcentaje) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaByFechaInicio(java.sql.Timestamp fechaInicio) throws GenericBusinessException;
    java.util.Collection findImpuestoRentaByFechaFin(java.sql.Timestamp fechaFin) throws GenericBusinessException;

}
