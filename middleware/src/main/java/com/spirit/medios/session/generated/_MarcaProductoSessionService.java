package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MarcaProductoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.MarcaProductoIf addMarcaProducto(com.spirit.medios.entity.MarcaProductoIf model) throws GenericBusinessException;

   void saveMarcaProducto(com.spirit.medios.entity.MarcaProductoIf model) throws GenericBusinessException;

   void deleteMarcaProducto(java.lang.Long id) throws GenericBusinessException;

   Collection findMarcaProductoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.MarcaProductoIf getMarcaProducto(java.lang.Long id) throws GenericBusinessException;

   Collection getMarcaProductoList() throws GenericBusinessException;

   Collection getMarcaProductoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMarcaProductoListSize() throws GenericBusinessException;
    java.util.Collection findMarcaProductoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByTipo(java.lang.String tipo) throws GenericBusinessException;
    java.util.Collection findMarcaProductoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
