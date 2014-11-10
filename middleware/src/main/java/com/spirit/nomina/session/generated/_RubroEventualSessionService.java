package com.spirit.nomina.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RubroEventualSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.RubroEventualIf addRubroEventual(com.spirit.nomina.entity.RubroEventualIf model) throws GenericBusinessException;

   void saveRubroEventual(com.spirit.nomina.entity.RubroEventualIf model) throws GenericBusinessException;

   void deleteRubroEventual(java.lang.Long id) throws GenericBusinessException;

   Collection findRubroEventualByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.RubroEventualIf getRubroEventual(java.lang.Long id) throws GenericBusinessException;

   Collection getRubroEventualList() throws GenericBusinessException;

   Collection getRubroEventualList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRubroEventualListSize() throws GenericBusinessException;
    java.util.Collection findRubroEventualById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRubroEventualByContratoId(java.lang.Long contratoId) throws GenericBusinessException;
    java.util.Collection findRubroEventualByRubroId(java.lang.Long rubroId) throws GenericBusinessException;
    java.util.Collection findRubroEventualByTipoRolIdCobro(java.lang.Long tipoRolIdCobro) throws GenericBusinessException;
    java.util.Collection findRubroEventualByValor(java.math.BigDecimal valor) throws GenericBusinessException;
    java.util.Collection findRubroEventualByFechaCobro(java.sql.Date fechaCobro) throws GenericBusinessException;
    java.util.Collection findRubroEventualByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findRubroEventualByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findRubroEventualByTipoRolIdPago(java.lang.Long tipoRolIdPago) throws GenericBusinessException;
    java.util.Collection findRubroEventualByFechaPago(java.sql.Date fechaPago) throws GenericBusinessException;
    java.util.Collection findRubroEventualByIdentificacion(java.lang.Long identificacion) throws GenericBusinessException;

}
