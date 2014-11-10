package com.spirit.general.session;

import com.spirit.general.session.generated._PuntoImpresionSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PuntoImpresionSessionService extends _PuntoImpresionSessionService{
	java.util.Collection findPuntoImpresionByEmpresaId(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findPuntoImpresionByTipoDocumentoAndByCajaId(java.lang.Long idTipoDocumento, java.lang.Long idCaja) throws com.spirit.exception.GenericBusinessException;
	
}
