package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _FuncionBodegaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.FuncionBodegaIf addFuncionBodega(com.spirit.inventario.entity.FuncionBodegaIf model) throws GenericBusinessException;

   void saveFuncionBodega(com.spirit.inventario.entity.FuncionBodegaIf model) throws GenericBusinessException;

   void deleteFuncionBodega(java.lang.Long id) throws GenericBusinessException;

   Collection findFuncionBodegaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.FuncionBodegaIf getFuncionBodega(java.lang.Long id) throws GenericBusinessException;

   Collection getFuncionBodegaList() throws GenericBusinessException;

   Collection getFuncionBodegaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getFuncionBodegaListSize() throws GenericBusinessException;
    java.util.Collection findFuncionBodegaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findFuncionBodegaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findFuncionBodegaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findFuncionBodegaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findFuncionBodegaByEstado(java.lang.String estado) throws GenericBusinessException;

}
