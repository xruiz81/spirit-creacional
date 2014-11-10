package com.spirit.contabilidad.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _CuentaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.contabilidad.entity.CuentaIf addCuenta(com.spirit.contabilidad.entity.CuentaIf model) throws GenericBusinessException;

   void saveCuenta(com.spirit.contabilidad.entity.CuentaIf model) throws GenericBusinessException;

   void deleteCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection findCuentaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.contabilidad.entity.CuentaIf getCuenta(java.lang.Long id) throws GenericBusinessException;

   Collection getCuentaList() throws GenericBusinessException;

   Collection getCuentaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getCuentaListSize() throws GenericBusinessException;
    java.util.Collection findCuentaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findCuentaByPlancuentaId(java.lang.Long plancuentaId) throws GenericBusinessException;
    java.util.Collection findCuentaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findCuentaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findCuentaByNombreCorto(java.lang.String nombreCorto) throws GenericBusinessException;
    java.util.Collection findCuentaByTipocuentaId(java.lang.Long tipocuentaId) throws GenericBusinessException;
    java.util.Collection findCuentaByPadreId(java.lang.Long padreId) throws GenericBusinessException;
    java.util.Collection findCuentaByRelacionada(java.lang.Long relacionada) throws GenericBusinessException;
    java.util.Collection findCuentaByImputable(java.lang.String imputable) throws GenericBusinessException;
    java.util.Collection findCuentaByNivel(java.lang.Integer nivel) throws GenericBusinessException;
    java.util.Collection findCuentaByTiporesultadoId(java.lang.Long tiporesultadoId) throws GenericBusinessException;
    java.util.Collection findCuentaByEfectivo(java.lang.String efectivo) throws GenericBusinessException;
    java.util.Collection findCuentaByActivofijo(java.lang.String activofijo) throws GenericBusinessException;
    java.util.Collection findCuentaByDepartamento(java.lang.String departamento) throws GenericBusinessException;
    java.util.Collection findCuentaByLinea(java.lang.String linea) throws GenericBusinessException;
    java.util.Collection findCuentaByEmpleado(java.lang.String empleado) throws GenericBusinessException;
    java.util.Collection findCuentaByCentrogasto(java.lang.String centrogasto) throws GenericBusinessException;
    java.util.Collection findCuentaByCliente(java.lang.String cliente) throws GenericBusinessException;
    java.util.Collection findCuentaByGasto(java.lang.String gasto) throws GenericBusinessException;
    java.util.Collection findCuentaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findCuentaByCuentaBanco(java.lang.String cuentaBanco) throws GenericBusinessException;

}
