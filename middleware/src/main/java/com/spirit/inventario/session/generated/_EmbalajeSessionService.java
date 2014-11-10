package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmbalajeSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.EmbalajeIf addEmbalaje(com.spirit.inventario.entity.EmbalajeIf model) throws GenericBusinessException;

   void saveEmbalaje(com.spirit.inventario.entity.EmbalajeIf model) throws GenericBusinessException;

   void deleteEmbalaje(java.lang.Long id) throws GenericBusinessException;

   Collection findEmbalajeByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.EmbalajeIf getEmbalaje(java.lang.Long id) throws GenericBusinessException;

   Collection getEmbalajeList() throws GenericBusinessException;

   Collection getEmbalajeList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmbalajeListSize() throws GenericBusinessException;
    java.util.Collection findEmbalajeById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmbalajeByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findEmbalajeByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findEmbalajeByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findEmbalajeByEstado(java.lang.String estado) throws GenericBusinessException;

}
