package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoContactoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.TipoContactoIf addTipoContacto(com.spirit.crm.entity.TipoContactoIf model) throws GenericBusinessException;

   void saveTipoContacto(com.spirit.crm.entity.TipoContactoIf model) throws GenericBusinessException;

   void deleteTipoContacto(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoContactoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.TipoContactoIf getTipoContacto(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoContactoList() throws GenericBusinessException;

   Collection getTipoContactoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoContactoListSize() throws GenericBusinessException;
    java.util.Collection findTipoContactoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoContactoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoContactoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoContactoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
