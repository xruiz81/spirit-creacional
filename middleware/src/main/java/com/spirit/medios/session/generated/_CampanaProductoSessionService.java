package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CampanaProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.CampanaProductoIf addCampanaProducto(com.spirit.medios.entity.CampanaProductoIf model) throws GenericBusinessException;

   void saveCampanaProducto(com.spirit.medios.entity.CampanaProductoIf model) throws GenericBusinessException;

   void deleteCampanaProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findCampanaProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.CampanaProductoIf getCampanaProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getCampanaProductoList() throws GenericBusinessException;

   Collection getCampanaProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCampanaProductoListSize() throws GenericBusinessException;
    java.util.Collection findCampanaProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCampanaProductoByProductoClienteId(java.lang.Long productoClienteId) throws GenericBusinessException;
    java.util.Collection findCampanaProductoByCampanaId(java.lang.Long campanaId) throws GenericBusinessException;

}
