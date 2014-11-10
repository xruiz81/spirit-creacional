package com.spirit.compras.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GastoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.compras.entity.GastoIf addGasto(com.spirit.compras.entity.GastoIf model) throws GenericBusinessException;

   void saveGasto(com.spirit.compras.entity.GastoIf model) throws GenericBusinessException;

   void deleteGasto(java.lang.Long id) throws GenericBusinessException;

   Collection findGastoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.compras.entity.GastoIf getGasto(java.lang.Long id) throws GenericBusinessException;

   Collection getGastoList() throws GenericBusinessException;

   Collection getGastoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGastoListSize() throws GenericBusinessException;
    java.util.Collection findGastoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGastoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findGastoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findGastoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findGastoByTipo(java.lang.String tipo) throws GenericBusinessException;

}
