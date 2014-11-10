package com.spirit.contabilidad.session;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.handler.SaldoInicial;
import com.spirit.contabilidad.session.generated._SaldoCuentaSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SaldoCuentaSessionService extends _SaldoCuentaSessionService{

    java.util.Collection findSaldoCuentaByCuentaIdAndByPeriodoId(java.lang.Long cuentaId,java.lang.Long periodoId) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findSaldoCuentaByPlanCuentaIdByCuentaEfectivoImputableAndByPeriodoId(java.lang.Long idEmpresa, java.lang.Long idPlanCuenta, java.lang.Long idPeriodo) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findSaldoCuentaByPlanCuentaId(java.lang.Long idPlanCuenta) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findSaldoCuentaByPeriodoIdByAnioAndByMes(java.lang.Long idPeriodo, java.lang.String anio, java.lang.String mes) throws com.spirit.exception.GenericBusinessException;
    java.util.Collection findSaldoCuentaByPeriodoIdSortedByCuentaIdByAnioByMes(java.lang.Long idPeriodo) throws com.spirit.exception.GenericBusinessException;
    
    public Map<Long, SaldoInicial> verificarSaldosInicialesCuentas(Date fechaComboInicio,Long planCuentaId,
			Map<Long, SaldoInicial> mapaSaldosIniciales, CuentaIf cuentaContable,Long empresaId)
			throws GenericBusinessException ;
    
    public Map<Long, SaldoInicial> verificarSaldosInicialesCuentasNuevo(Date fechaComboInicio,
			Map<Long, SaldoInicial> mapaSaldosIniciales, CuentaIf cuentaContable,Long empresaId)
			throws GenericBusinessException ;
    
    public Map mapearSaldosCuentasByPeriodosMap(Map periodosAutorizarMap) throws GenericBusinessException;
    public Map mapearSaldosCuentasByPeriodosMapAndCuentasVector(Map periodosAutorizarMap, Vector<CuentaIf> cuentasVector) throws GenericBusinessException;
}
