package com.spirit.compras.session;

import java.util.List;
import java.util.Map;

import com.spirit.compras.entity.SolicitudCompraArchivoIf;
import com.spirit.compras.entity.SolicitudCompraDetalleIf;
import com.spirit.compras.entity.SolicitudCompraIf;
import com.spirit.compras.session.generated._SolicitudCompraSessionService;
import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SolicitudCompraSessionService extends _SolicitudCompraSessionService{

	public SolicitudCompraIf procesarSolicitudCompra(SolicitudCompraIf model, List<SolicitudCompraDetalleIf> modelSolicitudCompraDetalleList, Long idEmpresa) throws GenericBusinessException;
	public void actualizarSolicitudCompra(SolicitudCompraIf model, List<SolicitudCompraDetalleIf> modelSolicitudCompraDetalleList, List<SolicitudCompraDetalleIf> modelSolicitudCompraDetalleRemovidosList,long idTarea)throws GenericBusinessException;
	public void eliminarSolicitudCompra(Long solicitudCompraId,long idTarea) throws GenericBusinessException ;
	//public void autorizarSolicitudCompra(boolean autorizar,long idTarea) throws GenericBusinessException ;
	public java.util.Collection findSolicitudCompraByQuery(int startIndex, int endIndex, Map aMap) throws com.spirit.exception.GenericBusinessException;
	public int findSolicitudCompraByQuerySize(Map aMap) throws com.spirit.exception.GenericBusinessException;
	public int findSolicitudCompraByQueryAndEmpresaIdSize(Map aMap, Long empresaId) throws com.spirit.exception.GenericBusinessException;
	public int findSolicitudCompraByQueryAndEmpresaIdSize(Map aMap, Long empresaId, Long proveedorId) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findSolicitudCompraByQueryAndEmpresaId(int startIndex, int endIndex, Map aMap, Long empresaId, Boolean ordenCompra) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findSolicitudCompraByQueryAndEmpresaId(int startIndex, int endIndex, Map aMap, Long empresaId, Long proveedorId, Boolean ordenCompra) throws com.spirit.exception.GenericBusinessException;
	public java.util.Collection findSolicitudCompraByQueryAndEmpresaId(Map aMap, Long empresaId, Long proveedorId) throws com.spirit.exception.GenericBusinessException;
	public void actualizarArchivosSolicitudCompra(SolicitudCompraIf model, List<SolicitudCompraArchivoIf> modelArchivoList, List<SolicitudCompraArchivoIf> archivosEliminadosList,String urlCarpetaSevidor) throws GenericBusinessException;
}
