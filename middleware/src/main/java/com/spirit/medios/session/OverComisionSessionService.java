package com.spirit.medios.session;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.spirit.medios.entity.OverComisionIf;
import com.spirit.medios.session.generated._OverComisionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OverComisionSessionService extends _OverComisionSessionService{
	
	public void procesarOverComision(List<OverComisionIf> overColeccion, List<OverComisionIf> overColeccionEliminado) throws com.spirit.exception.GenericBusinessException;
	public Collection findOverComisionByClienteOficinaAndByFechaInicioFechaFin(Long clienteOficinaId, Timestamp fechaInicio, Timestamp fechaFin) throws com.spirit.exception.GenericBusinessException;
	
}
