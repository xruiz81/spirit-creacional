package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoBodegaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.TipoBodegaIf addTipoBodega(com.spirit.inventario.entity.TipoBodegaIf model) throws GenericBusinessException;

   void saveTipoBodega(com.spirit.inventario.entity.TipoBodegaIf model) throws GenericBusinessException;

   void deleteTipoBodega(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoBodegaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.TipoBodegaIf getTipoBodega(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoBodegaList() throws GenericBusinessException;

   Collection getTipoBodegaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoBodegaListSize() throws GenericBusinessException;
    java.util.Collection findTipoBodegaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoBodegaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoBodegaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoBodegaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
