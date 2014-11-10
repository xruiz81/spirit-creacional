package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ContratoPlantillaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ContratoPlantillaIf addContratoPlantilla(com.spirit.nomina.entity.ContratoPlantillaIf model) throws GenericBusinessException;

   void saveContratoPlantilla(com.spirit.nomina.entity.ContratoPlantillaIf model) throws GenericBusinessException;

   void deleteContratoPlantilla(java.lang.Long id) throws GenericBusinessException;

   Collection findContratoPlantillaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ContratoPlantillaIf getContratoPlantilla(java.lang.Long id) throws GenericBusinessException;

   Collection getContratoPlantillaList() throws GenericBusinessException;

   Collection getContratoPlantillaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getContratoPlantillaListSize() throws GenericBusinessException;
    java.util.Collection findContratoPlantillaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findContratoPlantillaByTipoContratoId(java.lang.Long tipoContratoId) throws GenericBusinessException;
    java.util.Collection findContratoPlantillaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findContratoPlantillaByObservacion(java.lang.String observacion) throws GenericBusinessException;

}
