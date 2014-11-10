package com.spirit.general.session;

import java.util.Collection;
import java.util.Map;

import com.spirit.general.session.generated._TipoDocumentoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoDocumentoSessionService extends _TipoDocumentoSessionService{
	Collection findTipoDocumentoByQuery(int startIndex,int endIndex,Map aMap) throws com.spirit.exception.GenericBusinessException;
	int getTipoDocumentoListSize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public Collection findTipoDocumentoByQueryAndUsuarioId(Map aMap, java.lang.Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findTipoDocumentoTransferibleByUsuarioIdAndModuloId(java.lang.Long idUsuario, java.lang.Long idModulo) throws com.spirit.exception.GenericBusinessException;
	public Map mapearTiposDocumento(Long idEmpresa);
}
