package com.spirit.medios.session;

import java.util.List;

import com.spirit.medios.entity.Timetracker2EmpleadoIf;
import com.spirit.medios.entity.Timetracker2If;
import com.spirit.medios.session.generated._Timetracker2SessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface Timetracker2SessionService extends _Timetracker2SessionService{
	
	public void procesarTimetracker2Coleccion(Timetracker2If actividad, List<Timetracker2EmpleadoIf> empleadoColeccion, List<Timetracker2EmpleadoIf> empleadoEliminadoColeccion) throws com.spirit.exception.GenericBusinessException;
	public void eliminarTimetracker2(Timetracker2If actividad) throws com.spirit.exception.GenericBusinessException;
}
