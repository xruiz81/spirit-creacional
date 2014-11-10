package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrensaFormatoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PrensaFormatoIf addPrensaFormato(com.spirit.medios.entity.PrensaFormatoIf model) throws GenericBusinessException;

   void savePrensaFormato(com.spirit.medios.entity.PrensaFormatoIf model) throws GenericBusinessException;

   void deletePrensaFormato(java.lang.Long id) throws GenericBusinessException;

   Collection findPrensaFormatoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PrensaFormatoIf getPrensaFormato(java.lang.Long id) throws GenericBusinessException;

   Collection getPrensaFormatoList() throws GenericBusinessException;

   Collection getPrensaFormatoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrensaFormatoListSize() throws GenericBusinessException;
    java.util.Collection findPrensaFormatoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByFormato(java.lang.String formato) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByAnchoColumnas(java.math.BigDecimal anchoColumnas) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByAltoModulos(java.math.BigDecimal altoModulos) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByAnchoCm(java.math.BigDecimal anchoCm) throws GenericBusinessException;
    java.util.Collection findPrensaFormatoByAltoCm(java.math.BigDecimal altoCm) throws GenericBusinessException;

}
