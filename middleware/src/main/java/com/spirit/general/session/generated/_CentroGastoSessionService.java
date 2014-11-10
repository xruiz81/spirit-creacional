package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CentroGastoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.CentroGastoIf addCentroGasto(com.spirit.general.entity.CentroGastoIf model) throws GenericBusinessException;

   void saveCentroGasto(com.spirit.general.entity.CentroGastoIf model) throws GenericBusinessException;

   void deleteCentroGasto(java.lang.Long id) throws GenericBusinessException;

   Collection findCentroGastoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.CentroGastoIf getCentroGasto(java.lang.Long id) throws GenericBusinessException;

   Collection getCentroGastoList() throws GenericBusinessException;

   Collection getCentroGastoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCentroGastoListSize() throws GenericBusinessException;
    java.util.Collection findCentroGastoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCentroGastoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCentroGastoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCentroGastoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
