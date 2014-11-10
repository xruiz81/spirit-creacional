package com.spirit.general.session;

import java.util.Map;
import java.util.Vector;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.UsuarioDocumentoIf;
import com.spirit.general.session.generated._UsuarioDocumentoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface UsuarioDocumentoSessionService extends _UsuarioDocumentoSessionService{
	java.util.Collection findUsuarioDocumentoByEmpresaId(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	public void procesarUsuarioDocumento(Map<Long,Vector<UsuarioDocumentoIf>> mapaUsuarioDocumento) throws com.spirit.exception.GenericBusinessException;
	public void actualizarUsuarioDocumento(Map<Long,Vector<UsuarioDocumentoIf>> mapaUsuarioDocumento,Map<Long,Vector<UsuarioDocumentoIf>> mapaEliminadosUsuarioDocumento) throws GenericBusinessException;
	
}
