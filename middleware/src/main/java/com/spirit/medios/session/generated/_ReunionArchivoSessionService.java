package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ReunionArchivoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ReunionArchivoIf addReunionArchivo(com.spirit.medios.entity.ReunionArchivoIf model) throws GenericBusinessException;

   void saveReunionArchivo(com.spirit.medios.entity.ReunionArchivoIf model) throws GenericBusinessException;

   void deleteReunionArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection findReunionArchivoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ReunionArchivoIf getReunionArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection getReunionArchivoList() throws GenericBusinessException;

   Collection getReunionArchivoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getReunionArchivoListSize() throws GenericBusinessException;
    java.util.Collection findReunionArchivoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findReunionArchivoByReunionId(java.lang.Long reunionId) throws GenericBusinessException;
    java.util.Collection findReunionArchivoByTipoArchivoId(java.lang.Long tipoArchivoId) throws GenericBusinessException;
    java.util.Collection findReunionArchivoByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findReunionArchivoByUrlDescripcion(java.lang.String urlDescripcion) throws GenericBusinessException;
    java.util.Collection findReunionArchivoByEstado(java.lang.String estado) throws GenericBusinessException;

}
