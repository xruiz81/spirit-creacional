package com.spirit.facturacion.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ListaPrecioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.facturacion.entity.ListaPrecioIf addListaPrecio(com.spirit.facturacion.entity.ListaPrecioIf model) throws GenericBusinessException;

   void saveListaPrecio(com.spirit.facturacion.entity.ListaPrecioIf model) throws GenericBusinessException;

   void deleteListaPrecio(java.lang.Long id) throws GenericBusinessException;

   Collection findListaPrecioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.facturacion.entity.ListaPrecioIf getListaPrecio(java.lang.Long id) throws GenericBusinessException;

   Collection getListaPrecioList() throws GenericBusinessException;

   Collection getListaPrecioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getListaPrecioListSize() throws GenericBusinessException;
    java.util.Collection findListaPrecioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findListaPrecioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findListaPrecioByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findListaPrecioByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findListaPrecioByReferenciaFisica(java.lang.String referenciaFisica) throws GenericBusinessException;
    java.util.Collection findListaPrecioByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findListaPrecioByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findListaPrecioByFechaFinal(java.sql.Date fechaFinal) throws GenericBusinessException;
    java.util.Collection findListaPrecioByEstado(java.lang.String estado) throws GenericBusinessException;

}
