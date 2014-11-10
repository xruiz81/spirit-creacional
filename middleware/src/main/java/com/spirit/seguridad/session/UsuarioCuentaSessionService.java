package com.spirit.seguridad.session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;
import com.spirit.seguridad.entity.UsuarioCuentaIf;
import com.spirit.seguridad.session.generated._UsuarioCuentaSessionService;

/**
 * The <code>UsuarioCuentaSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:42 $
 */
public interface UsuarioCuentaSessionService extends _UsuarioCuentaSessionService{

	public void procesarUsuarioCuenta(Long idUsuario, List<UsuarioCuentaIf> modelUsuarioCuentaList) throws GenericBusinessException;
	public void actualizarUsuarioCuenta(Long idUsuario, List<UsuarioCuentaIf> modelUsuarioCuentaList) throws GenericBusinessException;
	
}
