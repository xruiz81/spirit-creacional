package com.spirit.medios.session;

import java.util.Collection;

import com.spirit.medios.entity.TiempoParcialDotDetalleIf;
import com.spirit.medios.entity.TiempoParcialDotIf;
import com.spirit.medios.session.generated._TiempoParcialDotSessionService;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TiempoParcialDotSessionService extends _TiempoParcialDotSessionService{

	public TiempoParcialDotIf procesarTiempoParcialDot(TiempoParcialDotIf model, Collection<TiempoParcialDotDetalleIf> detalle, String codigoOT, boolean enviarCorreo) throws com.spirit.exception.GenericBusinessException;
	public void eliminarTiempoParcialDot(java.lang.Long id) throws com.spirit.exception.GenericBusinessException;

}
