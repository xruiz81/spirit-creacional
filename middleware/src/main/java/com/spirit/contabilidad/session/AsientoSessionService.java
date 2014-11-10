package com.spirit.contabilidad.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.LogAsientoIf;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.session.generated._AsientoSessionService;
import com.spirit.exception.CuentaNoImputableException;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.UsuarioIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface AsientoSessionService extends _AsientoSessionService{

	public Collection findDiariosByQueryAndByFechaInicioAndFechaFin(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin, boolean fechaInicioInclusive) throws GenericBusinessException;
	java.util.Collection findAsientoByPeriodoIdAndByPlanCuentaIdAndCuentaIdAndFechaInicioAndFechaFin(java.lang.Long idPeriodo, java.lang.Long idPlanCuenta, java.lang.Long idCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findAsientoByTipoAsientoIdByEfectivoByPeriodoIdByPlanCuentaAndByFechaInicioAndFechaFin(java.lang.Long idTipoAsiento, java.lang.Long idPeriodo, java.lang.Long idPlanCuenta, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findAsientoByQueryByFechaInicioAndFechaFin(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findAsientoByQueryByFechaInicioAndFechaFin_Id(Map aMap, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection getAsientoList(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	public int getAsientoListSize(Map aMap) throws GenericBusinessException;
	public AsientoIf registrarAsiento(AsientoIf model);
	public AsientoIf registrarAsiento(LogAsientoIf model);
	public String procesarAsiento(AsientoIf model, List<AsientoDetalleIf> modelDetalleList, boolean modificarNumeroAsiento) throws GenericBusinessException;
	public Map<String, Object> actualizarAsiento(AsientoIf model, List<AsientoDetalleIf> modelDetalleList, AsientoIf modelAnterior, List<AsientoDetalleIf> modelDetalleAnteriorList, List<AsientoDetalleIf> modelDetalleRemovidoList, boolean reversarSaldos, boolean actualizarNumeroAsiento, UsuarioIf usuario, Map cuentasMap, Map tiposCuentaMap, Map saldosCuentasMap, Map periodosDetallesActivosMap, boolean mayorizacion) throws SaldoCuentaNegativoException, CuentaNoImputableException, GenericBusinessException;
	public void eliminarAsiento(Long asientoId) throws GenericBusinessException;
	public void procesarEliminacionAsiento(AsientoIf asiento, String usuario, String log, boolean forzarEliminacionAsientoCierre) throws SaldoCuentaNegativoException, GenericBusinessException;
	public List<AsientoDetalleIf> agruparDetalles(List<AsientoDetalleIf> asientoDetalleColeccion);
	public List<AsientoDetalleIf> desagruparDetalles(List<AsientoDetalleIf> asientoDetalleColeccion);
	public List<AsientoDetalleIf> redondearDetallesAsiento(List<AsientoDetalleIf> asientoDetalleColeccion);
	public String getNumeroAsiento(java.sql.Date fechaAsiento, Long empresaId, PlanCuentaIf planCuenta) throws GenericBusinessException;
	public Collection findAsientoCostoVentaByEmpresaId(Long idEmpresa);
	
	/**** CONCILIACION BANCARIA *****/
	public Collection<Object[]> findAsientosConciliacionBancaria(Long cuentaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public Collection<Object[]> findAsientosConciliacionBancariaExtendida(Long cuentaId, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws GenericBusinessException;
	public Collection<Object[]> findAsientosPendientesPorCobrarConciliacionFondoRotativo(Long cuentaId, java.sql.Date fechaFin) throws GenericBusinessException;
	
	
	/**** MAYORIZACION ****/
	public void mayorizarPeriodos(PeriodoIf periodoIf,PeriodoDetalleIf periodoDetalleIf,Boolean progresivo,Map<String, Object> beansMap, boolean mayorizacionPorApertura) throws GenericBusinessException;
	
	/**** ADMINISTRACION DE PERIODOS CONTABLES */
	public void actualizarPeriodo(PeriodoIf model,List<PeriodoDetalleIf> modelDetalleList)throws GenericBusinessException;
	public void actualizarPeriodoServer(PeriodoIf periodo,List<PeriodoDetalleIf> periodoDetalleColeccion, Vector<Object> activado, AsientoIf asientoCierre, Map<String, Object> beansMap, boolean mayorizacionPorApertura) throws GenericBusinessException, SaldoCuentaNegativoException, CuentaNoImputableException;
	
}
