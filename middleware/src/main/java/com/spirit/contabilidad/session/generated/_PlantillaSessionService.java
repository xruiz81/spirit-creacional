package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlantillaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.PlantillaIf addPlantilla(com.spirit.contabilidad.entity.PlantillaIf model) throws GenericBusinessException;

   void savePlantilla(com.spirit.contabilidad.entity.PlantillaIf model) throws GenericBusinessException;

   void deletePlantilla(java.lang.Long id) throws GenericBusinessException;

   Collection findPlantillaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.PlantillaIf getPlantilla(java.lang.Long id) throws GenericBusinessException;

   Collection getPlantillaList() throws GenericBusinessException;

   Collection getPlantillaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlantillaListSize() throws GenericBusinessException;
    java.util.Collection findPlantillaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlantillaByEventocontableId(java.lang.Long eventocontableId) throws GenericBusinessException;
    java.util.Collection findPlantillaByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findPlantillaByDebehaber(java.lang.String debehaber) throws GenericBusinessException;
    java.util.Collection findPlantillaByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findPlantillaByGlosa(java.lang.String glosa) throws GenericBusinessException;
    java.util.Collection findPlantillaByNemonico(java.lang.String nemonico) throws GenericBusinessException;
    java.util.Collection findPlantillaByFormula(java.lang.String formula) throws GenericBusinessException;
    java.util.Collection findPlantillaByTipoEntidad(java.lang.String tipoEntidad) throws GenericBusinessException;
    java.util.Collection findPlantillaByCuentaPredeterminadaId(java.lang.Long cuentaPredeterminadaId) throws GenericBusinessException;

}
