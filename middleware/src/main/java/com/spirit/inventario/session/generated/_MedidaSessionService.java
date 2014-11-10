package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MedidaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.MedidaIf addMedida(com.spirit.inventario.entity.MedidaIf model) throws GenericBusinessException;

   void saveMedida(com.spirit.inventario.entity.MedidaIf model) throws GenericBusinessException;

   void deleteMedida(java.lang.Long id) throws GenericBusinessException;

   Collection findMedidaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.MedidaIf getMedida(java.lang.Long id) throws GenericBusinessException;

   Collection getMedidaList() throws GenericBusinessException;

   Collection getMedidaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMedidaListSize() throws GenericBusinessException;
    java.util.Collection findMedidaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMedidaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findMedidaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findMedidaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
