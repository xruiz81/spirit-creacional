package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _NumeradoresSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.NumeradoresIf addNumeradores(com.spirit.general.entity.NumeradoresIf model) throws GenericBusinessException;

   void saveNumeradores(com.spirit.general.entity.NumeradoresIf model) throws GenericBusinessException;

   void deleteNumeradores(java.lang.Long id) throws GenericBusinessException;

   Collection findNumeradoresByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.NumeradoresIf getNumeradores(java.lang.Long id) throws GenericBusinessException;

   Collection getNumeradoresList() throws GenericBusinessException;

   Collection getNumeradoresList(int startIndex, int endIndex) throws GenericBusinessException;

   int getNumeradoresListSize() throws GenericBusinessException;
    java.util.Collection findNumeradoresById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findNumeradoresByUltimoValor(java.math.BigDecimal ultimoValor) throws GenericBusinessException;
    java.util.Collection findNumeradoresByNombreTabla(java.lang.String nombreTabla) throws GenericBusinessException;
    java.util.Collection findNumeradoresByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;

}
