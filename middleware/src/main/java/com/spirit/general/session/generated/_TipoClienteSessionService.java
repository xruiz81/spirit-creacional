package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoClienteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoClienteIf addTipoCliente(com.spirit.general.entity.TipoClienteIf model) throws GenericBusinessException;

   void saveTipoCliente(com.spirit.general.entity.TipoClienteIf model) throws GenericBusinessException;

   void deleteTipoCliente(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoClienteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoClienteIf getTipoCliente(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoClienteList() throws GenericBusinessException;

   Collection getTipoClienteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoClienteListSize() throws GenericBusinessException;
    java.util.Collection findTipoClienteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoClienteByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoClienteByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoClienteByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
