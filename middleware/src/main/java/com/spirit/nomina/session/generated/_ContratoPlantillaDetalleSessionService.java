package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ContratoPlantillaDetalleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ContratoPlantillaDetalleIf addContratoPlantillaDetalle(com.spirit.nomina.entity.ContratoPlantillaDetalleIf model) throws GenericBusinessException;

   void saveContratoPlantillaDetalle(com.spirit.nomina.entity.ContratoPlantillaDetalleIf model) throws GenericBusinessException;

   void deleteContratoPlantillaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection findContratoPlantillaDetalleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ContratoPlantillaDetalleIf getContratoPlantillaDetalle(java.lang.Long id) throws GenericBusinessException;

   Collection getContratoPlantillaDetalleList() throws GenericBusinessException;

   Collection getContratoPlantillaDetalleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getContratoPlantillaDetalleListSize() throws GenericBusinessException;
    java.util.Collection findContratoPlantillaDetalleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findContratoPlantillaDetalleByContratoPlantillaId(java.lang.Long contratoPlantillaId) throws GenericBusinessException;
    java.util.Collection findContratoPlantillaDetalleByRubroId(java.lang.Long rubroId) throws GenericBusinessException;
    java.util.Collection findContratoPlantillaDetalleByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
