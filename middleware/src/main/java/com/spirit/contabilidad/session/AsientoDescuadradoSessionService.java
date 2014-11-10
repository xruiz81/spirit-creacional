package com.spirit.contabilidad.session;

import com.spirit.contabilidad.session.generated._AsientoDescuadradoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface AsientoDescuadradoSessionService extends _AsientoDescuadradoSessionService{
	//public void restaurarAsiento(String asientoNumero) throws GenericBusinessException;
	public void restaurarAsiento() throws GenericBusinessException;
}
