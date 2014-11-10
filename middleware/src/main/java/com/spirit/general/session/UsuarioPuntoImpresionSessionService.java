package com.spirit.general.session;

import com.spirit.general.session.generated._UsuarioPuntoImpresionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface UsuarioPuntoImpresionSessionService extends _UsuarioPuntoImpresionSessionService{
	java.util.Collection findUsuarioPuntoImpresionByEmpresaId(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findUsuarioPuntoImpresionByPuntoImpresionAndByUsuarioId(java.lang.Long idPuntoImpresion, java.lang.Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	
}
