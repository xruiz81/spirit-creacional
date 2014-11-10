package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _DepositoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.DepositoIf addDeposito(com.spirit.cartera.entity.DepositoIf model) throws GenericBusinessException;

   void saveDeposito(com.spirit.cartera.entity.DepositoIf model) throws GenericBusinessException;

   void deleteDeposito(java.lang.Long id) throws GenericBusinessException;

   Collection findDepositoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.DepositoIf getDeposito(java.lang.Long id) throws GenericBusinessException;

   Collection getDepositoList() throws GenericBusinessException;

   Collection getDepositoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getDepositoListSize() throws GenericBusinessException;
    java.util.Collection findDepositoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findDepositoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findDepositoByCuentabancariaId(java.lang.Long cuentabancariaId) throws GenericBusinessException;
    java.util.Collection findDepositoByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findDepositoByFechaDeposito(java.sql.Date fechaDeposito) throws GenericBusinessException;
    java.util.Collection findDepositoByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findDepositoByEstado(java.lang.String estado) throws GenericBusinessException;

}
