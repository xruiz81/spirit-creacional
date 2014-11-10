package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoArchivoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoArchivoIf addTipoArchivo(com.spirit.general.entity.TipoArchivoIf model) throws GenericBusinessException;

   void saveTipoArchivo(com.spirit.general.entity.TipoArchivoIf model) throws GenericBusinessException;

   void deleteTipoArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoArchivoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoArchivoIf getTipoArchivo(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoArchivoList() throws GenericBusinessException;

   Collection getTipoArchivoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoArchivoListSize() throws GenericBusinessException;
    java.util.Collection findTipoArchivoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoArchivoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoArchivoByNombre(java.lang.String nombre) throws GenericBusinessException;

}
