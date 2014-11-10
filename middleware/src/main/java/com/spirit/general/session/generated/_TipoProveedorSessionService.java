package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoProveedorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.TipoProveedorIf addTipoProveedor(com.spirit.general.entity.TipoProveedorIf model) throws GenericBusinessException;

   void saveTipoProveedor(com.spirit.general.entity.TipoProveedorIf model) throws GenericBusinessException;

   void deleteTipoProveedor(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoProveedorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.TipoProveedorIf getTipoProveedor(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoProveedorList() throws GenericBusinessException;

   Collection getTipoProveedorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoProveedorListSize() throws GenericBusinessException;
    java.util.Collection findTipoProveedorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoProveedorByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoProveedorByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoProveedorByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findTipoProveedorByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
