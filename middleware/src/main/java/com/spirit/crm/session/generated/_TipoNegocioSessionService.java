package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoNegocioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.TipoNegocioIf addTipoNegocio(com.spirit.crm.entity.TipoNegocioIf model) throws GenericBusinessException;

   void saveTipoNegocio(com.spirit.crm.entity.TipoNegocioIf model) throws GenericBusinessException;

   void deleteTipoNegocio(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoNegocioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.TipoNegocioIf getTipoNegocio(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoNegocioList() throws GenericBusinessException;

   Collection getTipoNegocioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoNegocioListSize() throws GenericBusinessException;
    java.util.Collection findTipoNegocioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoNegocioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoNegocioByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoNegocioByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
