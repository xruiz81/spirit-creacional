package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CampanaBriefSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.CampanaBriefIf addCampanaBrief(com.spirit.medios.entity.CampanaBriefIf model) throws GenericBusinessException;

   void saveCampanaBrief(com.spirit.medios.entity.CampanaBriefIf model) throws GenericBusinessException;

   void deleteCampanaBrief(java.lang.Long id) throws GenericBusinessException;

   Collection findCampanaBriefByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.CampanaBriefIf getCampanaBrief(java.lang.Long id) throws GenericBusinessException;

   Collection getCampanaBriefList() throws GenericBusinessException;

   Collection getCampanaBriefList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCampanaBriefListSize() throws GenericBusinessException;
    java.util.Collection findCampanaBriefById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCampanaBriefByTipoBriefId(java.lang.Long tipoBriefId) throws GenericBusinessException;
    java.util.Collection findCampanaBriefByCampanaId(java.lang.Long campanaId) throws GenericBusinessException;
    java.util.Collection findCampanaBriefByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findCampanaBriefByUrlDescripcion(java.lang.String urlDescripcion) throws GenericBusinessException;

}
