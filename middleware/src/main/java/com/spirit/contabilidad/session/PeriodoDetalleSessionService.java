package com.spirit.contabilidad.session;

import java.util.Map;

import com.spirit.contabilidad.session.generated._PeriodoDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PeriodoDetalleSessionService extends _PeriodoDetalleSessionService{

	java.util.Collection findPeriodoDetalleByQueryAndEstadoActivoOrParcial(Map aMap) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPeriodoDetalleSiguientesNoInactivoByPeriodoIdAndMesAndAnio(Long idPeriodo, String mes, String anio, boolean periodoMesIncluido) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPeriodoDetalleInicialByPeriodoId(Long idPeriodo) throws com.spirit.exception.GenericBusinessException;
	boolean periodoDetalleCerrado(String year, String month, Long periodoId);
	public Map mapearPeriodosDetallesNoInactivosByPeriodosMap(Map periodosMap);
}
