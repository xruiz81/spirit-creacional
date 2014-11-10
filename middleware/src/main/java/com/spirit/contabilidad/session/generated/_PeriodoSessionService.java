package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PeriodoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.PeriodoIf addPeriodo(com.spirit.contabilidad.entity.PeriodoIf model) throws GenericBusinessException;

   void savePeriodo(com.spirit.contabilidad.entity.PeriodoIf model) throws GenericBusinessException;

   void deletePeriodo(java.lang.Long id) throws GenericBusinessException;

   Collection findPeriodoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.PeriodoIf getPeriodo(java.lang.Long id) throws GenericBusinessException;

   Collection getPeriodoList() throws GenericBusinessException;

   Collection getPeriodoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPeriodoListSize() throws GenericBusinessException;
    java.util.Collection findPeriodoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPeriodoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPeriodoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findPeriodoByFechaini(java.sql.Date fechaini) throws GenericBusinessException;
    java.util.Collection findPeriodoByFechafin(java.sql.Date fechafin) throws GenericBusinessException;
    java.util.Collection findPeriodoByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findPeriodoByOrden(java.lang.Long orden) throws GenericBusinessException;

}
