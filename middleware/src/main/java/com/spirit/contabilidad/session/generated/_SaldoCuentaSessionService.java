package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SaldoCuentaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.SaldoCuentaIf addSaldoCuenta(com.spirit.contabilidad.entity.SaldoCuentaIf model) throws GenericBusinessException;

   void saveSaldoCuenta(com.spirit.contabilidad.entity.SaldoCuentaIf model) throws GenericBusinessException;

   void deleteSaldoCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection findSaldoCuentaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.SaldoCuentaIf getSaldoCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection getSaldoCuentaList() throws GenericBusinessException;

   Collection getSaldoCuentaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSaldoCuentaListSize() throws GenericBusinessException;
    java.util.Collection findSaldoCuentaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByPeriodoId(java.lang.Long periodoId) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByAnio(java.lang.String anio) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByMes(java.lang.String mes) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByValordebe(java.math.BigDecimal valordebe) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByValorhaber(java.math.BigDecimal valorhaber) throws GenericBusinessException;
    java.util.Collection findSaldoCuentaByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
