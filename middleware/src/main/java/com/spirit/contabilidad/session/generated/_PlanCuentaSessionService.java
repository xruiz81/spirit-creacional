package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _PlanCuentaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.PlanCuentaIf addPlanCuenta(com.spirit.contabilidad.entity.PlanCuentaIf model) throws GenericBusinessException;

   void savePlanCuenta(com.spirit.contabilidad.entity.PlanCuentaIf model) throws GenericBusinessException;

   void deletePlanCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection findPlanCuentaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.PlanCuentaIf getPlanCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection getPlanCuentaList() throws GenericBusinessException;

   Collection getPlanCuentaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getPlanCuentaListSize() throws GenericBusinessException;
    java.util.Collection findPlanCuentaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByMonedaId(java.lang.Long monedaId) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByMascara(java.lang.String mascara) throws GenericBusinessException;
    java.util.Collection findPlanCuentaByPredeterminado(java.lang.String predeterminado) throws GenericBusinessException;

}
