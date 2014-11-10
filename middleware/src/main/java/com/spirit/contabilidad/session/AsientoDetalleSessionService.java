package com.spirit.contabilidad.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.generated._AsientoDetalleSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface AsientoDetalleSessionService extends _AsientoDetalleSessionService{

	java.util.Collection findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection<Object[]> findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaber(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	
	
	Collection findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntos(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin,Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	Collection findAsientoDetalleByEmpresaIdAndByCuentaEfectivoImputable_DebeHaberJuntosTipo(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin,Long idEmpresa,Long subtipoasientoId) throws com.spirit.exception.GenericBusinessException;
	
	java.util.Collection findAsientoDetalleBySubTipoAsientoIdByPeriodoIdByPlanCuentaIdAndByAsientoEfectivoAndByCuentaEfectivoImputableAndByFechaInicioAndFechaFin(java.lang.Long idSubTipoAsiento, Long idPeriodo, Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findAsientoDetalleByEmpresaByCuentaIdByPeriodoIdByPlanCuentaIdAndByFechaInicioAndFechaFin(java.lang.Long idEmpresa, java.lang.Long idCuenta, Long idPeriodo, Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws com.spirit.exception.GenericBusinessException;
	public Collection findAsientoDetalleByAsientoIdAndByCuentaId(java.lang.Long idAsiento, java.lang.Long idCuenta);
	public Collection<AsientoDetalleIf> findAsientoDetalleFechaInicioParaCuenta(AsientoIf asiento, java.sql.Date fechaInicio,java.lang.Long cuentaId);
	public List findAsientoDetalleByAsientoIdOrderByDebeHaberAndCodigo(java.lang.Long asientoId) throws com.spirit.exception.GenericBusinessException;
	public Collection findAsientoDetalleByPlanCuentaIdByFechaInicioAndFechaFin(Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin);
	java.util.Collection findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(java.lang.Long idEmpresa, Long idPeriodo, Long idPlanCuenta, String status, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findAsientoDetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFinOrderedByAsientoIdAndCuentaId(Long idEmpresa, Long idPeriodo, Long idPlanCuenta, String status, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException;
	java.util.Collection findAsiento_ADetalleByEmpresaByPeriodoIdByPlanCuentaIdByStatusAndByFechaInicioAndFechaFin(Long idEmpresa, Long idPeriodo, Long idPlanCuenta, String status, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException;
	public Collection findAsientoDetalleByAsientoIdOrderedByCodigoCuenta(java.lang.Long asientoId) throws com.spirit.exception.GenericBusinessException;
	public Collection findAsientoDetalleFromAsientoCierreByQuery(Map queryMap) throws GenericBusinessException;
}
