package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoAsientoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.TipoAsientoIf addTipoAsiento(com.spirit.contabilidad.entity.TipoAsientoIf model) throws GenericBusinessException;

   void saveTipoAsiento(com.spirit.contabilidad.entity.TipoAsientoIf model) throws GenericBusinessException;

   void deleteTipoAsiento(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoAsientoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.TipoAsientoIf getTipoAsiento(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoAsientoList() throws GenericBusinessException;

   Collection getTipoAsientoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoAsientoListSize() throws GenericBusinessException;
    java.util.Collection findTipoAsientoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoAsientoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoAsientoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoAsientoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findTipoAsientoByOrden(java.lang.Long orden) throws GenericBusinessException;
    java.util.Collection findTipoAsientoByStatus(java.lang.String status) throws GenericBusinessException;

}
