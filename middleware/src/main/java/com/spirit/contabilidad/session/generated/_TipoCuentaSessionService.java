package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _TipoCuentaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.TipoCuentaIf addTipoCuenta(com.spirit.contabilidad.entity.TipoCuentaIf model) throws GenericBusinessException;

   void saveTipoCuenta(com.spirit.contabilidad.entity.TipoCuentaIf model) throws GenericBusinessException;

   void deleteTipoCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection findTipoCuentaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.TipoCuentaIf getTipoCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection getTipoCuentaList() throws GenericBusinessException;

   Collection getTipoCuentaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getTipoCuentaListSize() throws GenericBusinessException;
    java.util.Collection findTipoCuentaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findTipoCuentaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findTipoCuentaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findTipoCuentaByDebehaber(java.lang.String debehaber) throws GenericBusinessException;

}
