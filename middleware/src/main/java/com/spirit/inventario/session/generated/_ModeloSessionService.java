package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ModeloSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.ModeloIf addModelo(com.spirit.inventario.entity.ModeloIf model) throws GenericBusinessException;

   void saveModelo(com.spirit.inventario.entity.ModeloIf model) throws GenericBusinessException;

   void deleteModelo(java.lang.Long id) throws GenericBusinessException;

   Collection findModeloByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.ModeloIf getModelo(java.lang.Long id) throws GenericBusinessException;

   Collection getModeloList() throws GenericBusinessException;

   Collection getModeloList(int startIndex, int endIndex) throws GenericBusinessException;

   int getModeloListSize() throws GenericBusinessException;
    java.util.Collection findModeloById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findModeloByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findModeloByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findModeloByMarcaId(java.lang.Long marcaId) throws GenericBusinessException;

}
