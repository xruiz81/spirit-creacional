package com.spirit.inventario.session;

import java.util.*;

import com.spirit.exception.GenericBusinessException;
import com.spirit.inventario.entity.SolicitudTransferenciaDetalleIf;
import com.spirit.inventario.entity.SolicitudTransferenciaIf;
import com.spirit.inventario.helper.ConsultaStockOperativoData;
import com.spirit.inventario.session.generated._SolicitudTransferenciaSessionService;

/**
 * The <code>SolicitudTransferenciaSessionService</code> bean exposes the
 * business methods in the interface.
 * 
 * @author Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:44 $
 */
public interface SolicitudTransferenciaSessionService extends _SolicitudTransferenciaSessionService{

	public void procesarSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf,
			List<? super SolicitudTransferenciaDetalleIf> listaSolicitudTransferenciaDetalle)
			throws GenericBusinessException;

	public Collection getSolicitudTransferenciaList(int startIndex,
			int endIndex, Map aMap) throws GenericBusinessException;

	public int getSolicitudTransferenciaListSize(Map aMap)
			throws GenericBusinessException;

	public void autorizarSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf)
			throws GenericBusinessException;

	public void eliminarSolicitudTransferencia(
			SolicitudTransferenciaIf solicitudTransferenciaIf)
			throws GenericBusinessException;
	
	public void generarSolicitudTransferencia(
			List<ConsultaStockOperativoData> consultaStockOperativoDataList,Long usuarioId)
			throws GenericBusinessException;

}
