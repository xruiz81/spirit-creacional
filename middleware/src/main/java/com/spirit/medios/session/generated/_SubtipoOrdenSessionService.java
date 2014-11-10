package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SubtipoOrdenSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.SubtipoOrdenIf addSubtipoOrden(com.spirit.medios.entity.SubtipoOrdenIf model) throws GenericBusinessException;

   void saveSubtipoOrden(com.spirit.medios.entity.SubtipoOrdenIf model) throws GenericBusinessException;

   void deleteSubtipoOrden(java.lang.Long id) throws GenericBusinessException;

   Collection findSubtipoOrdenByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.SubtipoOrdenIf getSubtipoOrden(java.lang.Long id) throws GenericBusinessException;

   Collection getSubtipoOrdenList() throws GenericBusinessException;

   Collection getSubtipoOrdenList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSubtipoOrdenListSize() throws GenericBusinessException;
    java.util.Collection findSubtipoOrdenById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSubtipoOrdenByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSubtipoOrdenByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findSubtipoOrdenByTipoordenId(java.lang.Long tipoordenId) throws GenericBusinessException;
    java.util.Collection findSubtipoOrdenByTipoproveedorId(java.lang.Long tipoproveedorId) throws GenericBusinessException;

}
