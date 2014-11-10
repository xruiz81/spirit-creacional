package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CuentaBancariaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.CuentaBancariaIf addCuentaBancaria(com.spirit.general.entity.CuentaBancariaIf model) throws GenericBusinessException;

   void saveCuentaBancaria(com.spirit.general.entity.CuentaBancariaIf model) throws GenericBusinessException;

   void deleteCuentaBancaria(java.lang.Long id) throws GenericBusinessException;

   Collection findCuentaBancariaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.CuentaBancariaIf getCuentaBancaria(java.lang.Long id) throws GenericBusinessException;

   Collection getCuentaBancariaList() throws GenericBusinessException;

   Collection getCuentaBancariaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCuentaBancariaListSize() throws GenericBusinessException;
    java.util.Collection findCuentaBancariaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByBancoId(java.lang.Long bancoId) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByCuenta(java.lang.String cuenta) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByTipocuenta(java.lang.String tipocuenta) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByNumeroCheque(java.lang.String numeroCheque) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByCuentaCliente(java.lang.String cuentaCliente) throws GenericBusinessException;
    java.util.Collection findCuentaBancariaByClienteId(java.lang.Long clienteId) throws GenericBusinessException;

}
