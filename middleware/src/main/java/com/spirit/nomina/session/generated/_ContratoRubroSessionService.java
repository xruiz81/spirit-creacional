package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ContratoRubroSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.ContratoRubroIf addContratoRubro(com.spirit.nomina.entity.ContratoRubroIf model) throws GenericBusinessException;

   void saveContratoRubro(com.spirit.nomina.entity.ContratoRubroIf model) throws GenericBusinessException;

   void deleteContratoRubro(java.lang.Long id) throws GenericBusinessException;

   Collection findContratoRubroByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.ContratoRubroIf getContratoRubro(java.lang.Long id) throws GenericBusinessException;

   Collection getContratoRubroList() throws GenericBusinessException;

   Collection getContratoRubroList(int startIndex, int endIndex) throws GenericBusinessException;

   int getContratoRubroListSize() throws GenericBusinessException;
    java.util.Collection findContratoRubroById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findContratoRubroByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findContratoRubroByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findContratoRubroByFechaInicio(java.sql.Date fechaInicio) throws GenericBusinessException;
    java.util.Collection findContratoRubroByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findContratoRubroByContratoId(java.lang.Long contratoId) throws GenericBusinessException;
    java.util.Collection findContratoRubroByRubroId(java.lang.Long rubroId) throws GenericBusinessException;

}
