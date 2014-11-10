package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PrensaInsertosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.PrensaInsertosIf addPrensaInsertos(com.spirit.medios.entity.PrensaInsertosIf model) throws GenericBusinessException;

   void savePrensaInsertos(com.spirit.medios.entity.PrensaInsertosIf model) throws GenericBusinessException;

   void deletePrensaInsertos(java.lang.Long id) throws GenericBusinessException;

   Collection findPrensaInsertosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.PrensaInsertosIf getPrensaInsertos(java.lang.Long id) throws GenericBusinessException;

   Collection getPrensaInsertosList() throws GenericBusinessException;

   Collection getPrensaInsertosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPrensaInsertosListSize() throws GenericBusinessException;
    java.util.Collection findPrensaInsertosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPrensaInsertosByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findPrensaInsertosByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPrensaInsertosByMaxPaginas(java.math.BigDecimal maxPaginas) throws GenericBusinessException;
    java.util.Collection findPrensaInsertosByTarifa(java.math.BigDecimal tarifa) throws GenericBusinessException;

}
