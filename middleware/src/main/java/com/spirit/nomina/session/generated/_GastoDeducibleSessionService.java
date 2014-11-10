package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GastoDeducibleSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.GastoDeducibleIf addGastoDeducible(com.spirit.nomina.entity.GastoDeducibleIf model) throws GenericBusinessException;

   void saveGastoDeducible(com.spirit.nomina.entity.GastoDeducibleIf model) throws GenericBusinessException;

   void deleteGastoDeducible(java.lang.Long id) throws GenericBusinessException;

   Collection findGastoDeducibleByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.GastoDeducibleIf getGastoDeducible(java.lang.Long id) throws GenericBusinessException;

   Collection getGastoDeducibleList() throws GenericBusinessException;

   Collection getGastoDeducibleList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGastoDeducibleListSize() throws GenericBusinessException;
    java.util.Collection findGastoDeducibleById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGastoDeducibleByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findGastoDeducibleByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findGastoDeducibleByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
