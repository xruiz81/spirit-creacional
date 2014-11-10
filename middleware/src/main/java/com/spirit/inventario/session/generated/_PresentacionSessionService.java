package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PresentacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.PresentacionIf addPresentacion(com.spirit.inventario.entity.PresentacionIf model) throws GenericBusinessException;

   void savePresentacion(com.spirit.inventario.entity.PresentacionIf model) throws GenericBusinessException;

   void deletePresentacion(java.lang.Long id) throws GenericBusinessException;

   Collection findPresentacionByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.PresentacionIf getPresentacion(java.lang.Long id) throws GenericBusinessException;

   Collection getPresentacionList() throws GenericBusinessException;

   Collection getPresentacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPresentacionListSize() throws GenericBusinessException;
    java.util.Collection findPresentacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPresentacionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPresentacionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPresentacionByPadreId(java.lang.Long padreId) throws GenericBusinessException;
    java.util.Collection findPresentacionByMedidaId(java.lang.Long medidaId) throws GenericBusinessException;
    java.util.Collection findPresentacionByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findPresentacionByEstado(java.lang.String estado) throws GenericBusinessException;

}
