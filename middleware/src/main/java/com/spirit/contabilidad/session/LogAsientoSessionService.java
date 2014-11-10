package com.spirit.contabilidad.session;

import java.text.ParseException;
import java.util.List;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.session.generated._LogAsientoSessionService;
import com.spirit.general.entity.UsuarioIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogAsientoSessionService extends _LogAsientoSessionService{

	public void procesarLogAsiento(AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleList, String log, UsuarioIf usuario) throws ParseException;
}
