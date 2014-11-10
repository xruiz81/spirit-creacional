package com.spirit.contabilidad.session;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.PeriodoDetalleEJB;
import com.spirit.contabilidad.entity.PeriodoDetalleIf;
import com.spirit.contabilidad.entity.PeriodoEJB;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.session.generated._PeriodoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PeriodoSessionService extends _PeriodoSessionService{

	public void procesarPeriodo(PeriodoIf model,List<PeriodoDetalleIf> modelDetalleList) throws GenericBusinessException;
	public PeriodoEJB registrarPeriodo(PeriodoIf model);
	public PeriodoDetalleEJB registrarPeriodoDetalle(PeriodoDetalleIf modelDetalle);
	public void eliminarPeriodo(Long periodoId) throws GenericBusinessException ;
	public java.util.Collection findPeriodoByEmpresaIdAndByFechaFin(Long idEmpresa, Date fechaFin) throws GenericBusinessException;
	public Collection<PeriodoIf> findPeriodoByRangoFechas(Long idEmpresa,Date fechaInicio, Date fechaFin) throws GenericBusinessException;
	public java.util.Collection findPeriodoByMesAndAnio(String mes, String año) throws GenericBusinessException;
	public Collection findPeriodoForAsientoAutomatico(Long idEmpresa, Date fechaAsiento) throws GenericBusinessException;
	public boolean periodoActivoOParcial(AsientoIf model);
	 public Map mapearPeriodos(Long idEmpresa) throws GenericBusinessException;
}
