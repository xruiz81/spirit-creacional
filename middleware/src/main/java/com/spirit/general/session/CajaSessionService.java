package com.spirit.general.session;

import java.util.Map;

import com.spirit.general.session.generated._CajaSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CajaSessionService extends _CajaSessionService{
	java.util.Collection findCajaByEmpresaId(java.lang.Long idEmpresa) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findCajaByUsuarioId(java.lang.Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	  public java.util.Collection findCajaByQueryVariosOficinaId(Map aMap);
	
}
