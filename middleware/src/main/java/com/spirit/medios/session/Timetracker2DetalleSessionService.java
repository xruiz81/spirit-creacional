package com.spirit.medios.session;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.entity.Timetracker2DetalleIf;
import com.spirit.medios.session.generated._Timetracker2DetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface Timetracker2DetalleSessionService extends _Timetracker2DetalleSessionService{
	
	public Collection findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, Long empleadoId, boolean resetearFechas) throws GenericBusinessException;
	public Collection findTimetracker2DetalleByQueryByFechaInicioAndByFechaFin2(Map aMap, java.sql.Timestamp fechaInicio, java.sql.Timestamp fechaFin, Long empleadoId, boolean resetearFechas) throws GenericBusinessException;
	public void procesarTimetracker2DetalleColeccion(Vector<Timetracker2DetalleIf> detalles) throws com.spirit.exception.GenericBusinessException;
}
