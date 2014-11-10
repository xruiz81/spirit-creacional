package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrensaTarifaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PrensaTarifaIf addPrensaTarifa(com.spirit.medios.entity.PrensaTarifaIf model) throws GenericBusinessException;

   void savePrensaTarifa(com.spirit.medios.entity.PrensaTarifaIf model) throws GenericBusinessException;

   void deletePrensaTarifa(java.lang.Long id) throws GenericBusinessException;

   Collection findPrensaTarifaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PrensaTarifaIf getPrensaTarifa(java.lang.Long id) throws GenericBusinessException;

   Collection getPrensaTarifaList() throws GenericBusinessException;

   Collection getPrensaTarifaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrensaTarifaListSize() throws GenericBusinessException;
    java.util.Collection findPrensaTarifaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByPrensaSeccionId(java.lang.Long prensaSeccionId) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByPrensaUbicacionId(java.lang.Long prensaUbicacionId) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByPrensaFormatoId(java.lang.Long prensaFormatoId) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByColor(java.lang.String color) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByDia(java.lang.String dia) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByTarifaCalculada(java.lang.String tarifaCalculada) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByTarifa(java.math.BigDecimal tarifa) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByRecargo(java.math.BigDecimal recargo) throws GenericBusinessException;
    java.util.Collection findPrensaTarifaByOperacion(java.lang.String operacion) throws GenericBusinessException;

}
