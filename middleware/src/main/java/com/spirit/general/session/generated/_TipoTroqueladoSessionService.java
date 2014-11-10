package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoTroqueladoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoTroqueladoIf addTipoTroquelado(com.spirit.general.entity.TipoTroqueladoIf model) throws GenericBusinessException;

   void saveTipoTroquelado(com.spirit.general.entity.TipoTroqueladoIf model) throws GenericBusinessException;

   void deleteTipoTroquelado(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoTroqueladoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoTroqueladoIf getTipoTroquelado(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoTroqueladoList() throws GenericBusinessException;

   Collection getTipoTroqueladoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoTroqueladoListSize() throws GenericBusinessException;
    java.util.Collection findTipoTroqueladoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoTroqueladoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoTroqueladoByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findTipoTroqueladoByNumeroSeccionesHoja(java.lang.Integer numeroSeccionesHoja) throws GenericBusinessException;

}
