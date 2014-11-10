package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoContratoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.TipoContratoIf addTipoContrato(com.spirit.nomina.entity.TipoContratoIf model) throws GenericBusinessException;

   void saveTipoContrato(com.spirit.nomina.entity.TipoContratoIf model) throws GenericBusinessException;

   void deleteTipoContrato(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoContratoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.TipoContratoIf getTipoContrato(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoContratoList() throws GenericBusinessException;

   Collection getTipoContratoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoContratoListSize() throws GenericBusinessException;
    java.util.Collection findTipoContratoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoContratoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoContratoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoContratoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
