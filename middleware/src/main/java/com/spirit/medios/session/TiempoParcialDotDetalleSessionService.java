package com.spirit.medios.session;

import com.spirit.exception.GenericBusinessException;
import com.spirit.medios.session.generated._TiempoParcialDotDetalleSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TiempoParcialDotDetalleSessionService extends _TiempoParcialDotDetalleSessionService{
	
	Long findTiempoTotalByTiempoDotId(java.lang.Long idTiempoParcialDot) throws GenericBusinessException;
}
