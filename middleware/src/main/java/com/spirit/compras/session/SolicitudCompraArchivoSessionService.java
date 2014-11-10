package com.spirit.compras.session;

import com.spirit.compras.entity.SolicitudCompraArchivoEJB;
import com.spirit.compras.entity.SolicitudCompraArchivoIf;
import com.spirit.compras.session.generated._SolicitudCompraArchivoSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudCompraArchivoSessionService extends _SolicitudCompraArchivoSessionService{

	public SolicitudCompraArchivoEJB registrarSolicitudCompraArchivo(SolicitudCompraArchivoIf modelSolicitudCompraArchivo, String urlCarpetaSevidor) throws GenericBusinessException;
}
