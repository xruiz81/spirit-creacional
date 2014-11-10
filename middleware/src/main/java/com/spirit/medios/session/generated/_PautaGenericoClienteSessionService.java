package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PautaGenericoClienteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PautaGenericoClienteIf addPautaGenericoCliente(com.spirit.medios.entity.PautaGenericoClienteIf model) throws GenericBusinessException;

   void savePautaGenericoCliente(com.spirit.medios.entity.PautaGenericoClienteIf model) throws GenericBusinessException;

   void deletePautaGenericoCliente(java.lang.Long id) throws GenericBusinessException;

   Collection findPautaGenericoClienteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PautaGenericoClienteIf getPautaGenericoCliente(java.lang.Long id) throws GenericBusinessException;

   Collection getPautaGenericoClienteList() throws GenericBusinessException;

   Collection getPautaGenericoClienteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPautaGenericoClienteListSize() throws GenericBusinessException;
    java.util.Collection findPautaGenericoClienteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPautaGenericoClienteByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findPautaGenericoClienteByTipoProductoId(java.lang.Long tipoProductoId) throws GenericBusinessException;
    java.util.Collection findPautaGenericoClienteByGenericoId(java.lang.Long genericoId) throws GenericBusinessException;

}
