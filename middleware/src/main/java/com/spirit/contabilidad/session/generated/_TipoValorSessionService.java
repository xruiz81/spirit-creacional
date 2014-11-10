package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoValorSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.TipoValorIf addTipoValor(com.spirit.contabilidad.entity.TipoValorIf model) throws GenericBusinessException;

   void saveTipoValor(com.spirit.contabilidad.entity.TipoValorIf model) throws GenericBusinessException;

   void deleteTipoValor(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoValorByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.TipoValorIf getTipoValor(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoValorList() throws GenericBusinessException;

   Collection getTipoValorList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoValorListSize() throws GenericBusinessException;
    java.util.Collection findTipoValorById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoValorByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoValorByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoValorByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
