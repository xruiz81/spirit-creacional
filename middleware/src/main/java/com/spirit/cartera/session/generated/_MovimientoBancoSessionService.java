package com.spirit.cartera.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _MovimientoBancoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.cartera.entity.MovimientoBancoIf addMovimientoBanco(com.spirit.cartera.entity.MovimientoBancoIf model) throws GenericBusinessException;

   void saveMovimientoBanco(com.spirit.cartera.entity.MovimientoBancoIf model) throws GenericBusinessException;

   void deleteMovimientoBanco(java.lang.Long id) throws GenericBusinessException;

   Collection findMovimientoBancoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.cartera.entity.MovimientoBancoIf getMovimientoBanco(java.lang.Long id) throws GenericBusinessException;

   Collection getMovimientoBancoList() throws GenericBusinessException;

   Collection getMovimientoBancoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getMovimientoBancoListSize() throws GenericBusinessException;
    java.util.Collection findMovimientoBancoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findMovimientoBancoByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findMovimientoBancoByDocumentoId(java.lang.Long documentoId) throws GenericBusinessException;
    java.util.Collection findMovimientoBancoByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findMovimientoBancoByFecha(java.sql.Timestamp fecha) throws GenericBusinessException;
    java.util.Collection findMovimientoBancoByValor(java.math.BigDecimal valor) throws GenericBusinessException;

}
