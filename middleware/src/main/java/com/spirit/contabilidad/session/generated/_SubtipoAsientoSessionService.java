package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SubtipoAsientoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.SubtipoAsientoIf addSubtipoAsiento(com.spirit.contabilidad.entity.SubtipoAsientoIf model) throws GenericBusinessException;

   void saveSubtipoAsiento(com.spirit.contabilidad.entity.SubtipoAsientoIf model) throws GenericBusinessException;

   void deleteSubtipoAsiento(java.lang.Long id) throws GenericBusinessException;

   Collection findSubtipoAsientoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.SubtipoAsientoIf getSubtipoAsiento(java.lang.Long id) throws GenericBusinessException;

   Collection getSubtipoAsientoList() throws GenericBusinessException;

   Collection getSubtipoAsientoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSubtipoAsientoListSize() throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoByTipoId(java.lang.Long tipoId) throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoByOrden(java.lang.Long orden) throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findSubtipoAsientoByTipo(java.lang.Long tipo) throws GenericBusinessException;

}
