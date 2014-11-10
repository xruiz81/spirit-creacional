package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _BancoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.BancoIf addBanco(com.spirit.general.entity.BancoIf model) throws GenericBusinessException;

   void saveBanco(com.spirit.general.entity.BancoIf model) throws GenericBusinessException;

   void deleteBanco(java.lang.Long id) throws GenericBusinessException;

   Collection findBancoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.BancoIf getBanco(java.lang.Long id) throws GenericBusinessException;

   Collection getBancoList() throws GenericBusinessException;

   Collection getBancoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getBancoListSize() throws GenericBusinessException;
    java.util.Collection findBancoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findBancoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findBancoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findBancoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findBancoByCodigoMulticash(java.lang.String codigoMulticash) throws GenericBusinessException;

}
