package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CuentaEntidadSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.CuentaEntidadIf addCuentaEntidad(com.spirit.contabilidad.entity.CuentaEntidadIf model) throws GenericBusinessException;

   void saveCuentaEntidad(com.spirit.contabilidad.entity.CuentaEntidadIf model) throws GenericBusinessException;

   void deleteCuentaEntidad(java.lang.Long id) throws GenericBusinessException;

   Collection findCuentaEntidadByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.CuentaEntidadIf getCuentaEntidad(java.lang.Long id) throws GenericBusinessException;

   Collection getCuentaEntidadList() throws GenericBusinessException;

   Collection getCuentaEntidadList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCuentaEntidadListSize() throws GenericBusinessException;
    java.util.Collection findCuentaEntidadById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCuentaEntidadByEntidadId(java.lang.Long entidadId) throws GenericBusinessException;
    java.util.Collection findCuentaEntidadByTipoEntidad(java.lang.String tipoEntidad) throws GenericBusinessException;
    java.util.Collection findCuentaEntidadByNemonico(java.lang.String nemonico) throws GenericBusinessException;
    java.util.Collection findCuentaEntidadByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findCuentaEntidadByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;

}
