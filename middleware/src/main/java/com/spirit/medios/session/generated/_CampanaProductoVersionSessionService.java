package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CampanaProductoVersionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.CampanaProductoVersionIf addCampanaProductoVersion(com.spirit.medios.entity.CampanaProductoVersionIf model) throws GenericBusinessException;

   void saveCampanaProductoVersion(com.spirit.medios.entity.CampanaProductoVersionIf model) throws GenericBusinessException;

   void deleteCampanaProductoVersion(java.lang.Long id) throws GenericBusinessException;

   Collection findCampanaProductoVersionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.CampanaProductoVersionIf getCampanaProductoVersion(java.lang.Long id) throws GenericBusinessException;

   Collection getCampanaProductoVersionList() throws GenericBusinessException;

   Collection getCampanaProductoVersionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCampanaProductoVersionListSize() throws GenericBusinessException;
    java.util.Collection findCampanaProductoVersionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCampanaProductoVersionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCampanaProductoVersionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCampanaProductoVersionByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCampanaProductoVersionByCampanaProductoId(java.lang.Long campanaProductoId) throws GenericBusinessException;

}
