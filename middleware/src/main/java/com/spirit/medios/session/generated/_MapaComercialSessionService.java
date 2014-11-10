package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MapaComercialSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.MapaComercialIf addMapaComercial(com.spirit.medios.entity.MapaComercialIf model) throws GenericBusinessException;

   void saveMapaComercial(com.spirit.medios.entity.MapaComercialIf model) throws GenericBusinessException;

   void deleteMapaComercial(java.lang.Long id) throws GenericBusinessException;

   Collection findMapaComercialByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.MapaComercialIf getMapaComercial(java.lang.Long id) throws GenericBusinessException;

   Collection getMapaComercialList() throws GenericBusinessException;

   Collection getMapaComercialList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMapaComercialListSize() throws GenericBusinessException;
    java.util.Collection findMapaComercialById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMapaComercialByPlanMedioDetalleId(java.lang.Long planMedioDetalleId) throws GenericBusinessException;
    java.util.Collection findMapaComercialByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findMapaComercialByFrecuencia(java.lang.Integer frecuencia) throws GenericBusinessException;

}
