package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _OverComisionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.OverComisionIf addOverComision(com.spirit.medios.entity.OverComisionIf model) throws GenericBusinessException;

   void saveOverComision(com.spirit.medios.entity.OverComisionIf model) throws GenericBusinessException;

   void deleteOverComision(java.lang.Long id) throws GenericBusinessException;

   Collection findOverComisionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.OverComisionIf getOverComision(java.lang.Long id) throws GenericBusinessException;

   Collection getOverComisionList() throws GenericBusinessException;

   Collection getOverComisionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getOverComisionListSize() throws GenericBusinessException;
    java.util.Collection findOverComisionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findOverComisionByProveedorOficinaId(java.lang.Long proveedorOficinaId) throws GenericBusinessException;
    java.util.Collection findOverComisionByAnio(java.sql.Timestamp anio) throws GenericBusinessException;
    java.util.Collection findOverComisionByInversionDe(java.math.BigDecimal inversionDe) throws GenericBusinessException;
    java.util.Collection findOverComisionByInversionA(java.math.BigDecimal inversionA) throws GenericBusinessException;
    java.util.Collection findOverComisionByPorcentajeOver(java.math.BigDecimal porcentajeOver) throws GenericBusinessException;
    java.util.Collection findOverComisionByObjetivo(java.lang.String objetivo) throws GenericBusinessException;
    java.util.Collection findOverComisionByProveedorId(java.lang.Long proveedorId) throws GenericBusinessException;

}
