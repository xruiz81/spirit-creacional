package com.spirit.general.session;

import java.util.Map;

import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.session.generated._DocumentoSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface DocumentoSessionService extends _DocumentoSessionService{
	java.util.Collection findDocumentoByUsuarioIdAndModuloId(java.lang.Long idUsuario, java.lang.Long idModulo) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findDocumentoByTipodocumentoIdAndUsuarioId(java.lang.Long idTipoDocumento, java.lang.Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findDocumentoByEmpresaIdAndModuloIdAndSigno(java.lang.Long idEmpresa, java.lang.Long idModulo, java.lang.String signo) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findDocumentoByEmpresaId(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findDocumentoByQuery(int startIndex,int endIndex,Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	int getDocumentoListSize(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findDocumentoByQueryAndEmpresaId(Map aMap, Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findDocumentoByEmpresaIdAndModuloId(java.lang.Long idEmpresa, java.lang.Long idModulo) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findDocumentoByModuloAndUsuarioId(String modulo, Long idUsuario);
	public java.util.Collection findDocumentoByCodigoEmpresaId(String codigo, Long empresaId);
	public java.util.Collection<DocumentoIf> findAdvancePaymentDocumentsByQuery(Map<String, Object> queryMap) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection<DocumentoIf> findLevelingDocumentsByQuery(Map<String, Object> queryMap) throws com.spirit.exception.GenericBusinessException;
}
