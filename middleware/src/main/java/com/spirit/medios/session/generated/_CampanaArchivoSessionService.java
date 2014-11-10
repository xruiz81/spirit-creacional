package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CampanaArchivoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.CampanaArchivoIf addCampanaArchivo(com.spirit.medios.entity.CampanaArchivoIf model) throws GenericBusinessException;

   void saveCampanaArchivo(com.spirit.medios.entity.CampanaArchivoIf model) throws GenericBusinessException;

   void deleteCampanaArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection findCampanaArchivoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.CampanaArchivoIf getCampanaArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection getCampanaArchivoList() throws GenericBusinessException;

   Collection getCampanaArchivoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCampanaArchivoListSize() throws GenericBusinessException;
    java.util.Collection findCampanaArchivoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCampanaArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) throws GenericBusinessException;
    java.util.Collection findCampanaArchivoByCampanaId(java.lang.Long campanaId) throws GenericBusinessException;
    java.util.Collection findCampanaArchivoByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findCampanaArchivoByUrlDescripcion(java.lang.String urlDescripcion) throws GenericBusinessException;

}
