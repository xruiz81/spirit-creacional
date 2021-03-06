package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoIdentificacionSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoIdentificacionIf addTipoIdentificacion(com.spirit.general.entity.TipoIdentificacionIf model) throws GenericBusinessException;

   void saveTipoIdentificacion(com.spirit.general.entity.TipoIdentificacionIf model) throws GenericBusinessException;

   void deleteTipoIdentificacion(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoIdentificacionByQuery(Map aMap) throws GenericBusinessException;


   com.spirit.general.entity.TipoOrdenIf addTipoOrden(com.spirit.general.entity.TipoOrdenIf model) throws GenericBusinessException;

   void saveTipoOrden(com.spirit.general.entity.TipoOrdenIf model) throws GenericBusinessException;

   void deleteTipoOrden(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoOrdenByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoIdentificacionIf getTipoIdentificacion(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoIdentificacionList() throws GenericBusinessException;

   Collection getTipoIdentificacionList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoIdentificacionListSize() throws GenericBusinessException;

   com.spirit.general.entity.TipoOrdenIf getTipoOrden(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoOrdenList() throws GenericBusinessException;

   Collection getTipoOrdenList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoOrdenListSize() throws GenericBusinessException;
    java.util.Collection findTipoIdentificacionById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoIdentificacionByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoIdentificacionByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoIdentificacionByCodigoSri(java.lang.String codigoSri) throws GenericBusinessException;

    java.util.Collection findTipoOrdenById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoOrdenByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoOrdenByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoOrdenByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoOrdenByTipo(java.lang.String tipo) throws GenericBusinessException;

}
