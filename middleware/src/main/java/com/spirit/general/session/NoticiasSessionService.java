package com.spirit.general.session;

import com.spirit.general.session.generated._NoticiasSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NoticiasSessionService extends _NoticiasSessionService{
	java.util.Collection findNoticiasByUsuarioNoticiasId(java.lang.Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findNoticiaByAsuntoAndByUsuario(String asunto, java.lang.Long idUsuario) throws com.spirit.exception.GenericBusinessException;
	java.util.Collection findNoticiaByAsuntoByUsuarioAndByFechaInicioAndFechaFin(String asunto, java.lang.Long idUsuario, java.sql.Date fechaInicio, java.sql.Date fechaFin) throws com.spirit.exception.GenericBusinessException;
		
}
