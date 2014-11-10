package com.spirit.general.session;

import com.spirit.general.session.generated._NumeradoresSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NumeradoresSessionService extends _NumeradoresSessionService{
	java.util.Collection findNumeradoresByNombreTablaAndByEmpresaId(String nombreTabla, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;

}
