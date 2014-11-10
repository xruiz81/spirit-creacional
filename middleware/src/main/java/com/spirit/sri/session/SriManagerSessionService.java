package com.spirit.sri.session;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIvaBienIf;
import com.spirit.sri.entity.SriIvaServicioIf;
import com.spirit.sri.entity.SriTipoComprobanteIf;


/**
 * The <code>SriManagerSessionService</code> bean exposes the business methods in the interface.
 *
 * @author  Rudie Ekkelenkamp - Finalist IT Group
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:06:41 $
 */
public interface SriManagerSessionService extends Serializable {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	//public long iniciarProceso(String nombreProceso, String nombrePanelSiguiente) throws ComprasBpmException;
	//COMPRAS
	public Collection<Object> generarDetallesCompraReoc(int numeroFila,Map<String, Date> mapaQuery,Long idEmpresa,Map<Long, SriAirIf> mapaAir,
			HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,HashMap<String, String> mapaCodigoTipoIdentificacionCompra,HashMap<String, Double> mapaImpuestos,Long empresaId) throws GenericBusinessException;

	public Collection<Object> generarDetallesCompraAnexoTransaccional(int numeroFila,Map<String, Date> mapaQuery,Long idEmpresa,Map<Long, SriAirIf> mapaAir,
			HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,HashMap<String, String> mapaCodigoTipoIdentificacionCompra,HashMap<String, Double> mapaImpuestos,Long empresaId) throws GenericBusinessException;

	
	public Collection<Object> generarNotasCreditoCompraReoc(int numeroFila,Long idEmpresa,Map<Long, SriAirIf> mapaAir,
			Map<String, Date> mapaQuery,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<String, String> mapaCodigoTipoIdentificacionCompra) throws GenericBusinessException;
	
	public Collection<Object> generarNotasCreditoCompraAnexoTransaccional(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,
			HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva) throws GenericBusinessException;
	
	public Collection<Object> generarDetallesVentaAnexoTransaccional(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,Set<String> codigosDocumentos,HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio,
			HashMap<String, SriIvaBienIf> mapaCodigoIvaBien,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes,
			HashMap<String, String> mapaCodigoTipoIdentificacionVenta,HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva) throws GenericBusinessException;
		
	public Collection<Object> generarNotasCreditoVenta(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio,HashMap<String, SriIvaBienIf> mapaCodigoIvaBien,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes,HashMap<String, String> mapaCodigoTipoIdentificacionVenta,HashMap<String, Double> mapaImpuestos,HashMap<Integer, Integer> mapaCodigoIva) throws GenericBusinessException;
	
	public Collection<Object> generarDetallesAnulaciones(int numeroFila,Long idEmpresa,Map<String, Date> mapaQuery,String codigoDocumento,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes) throws GenericBusinessException;
	
	public Collection<Object> generarDetalleAnulacionesCartera(int numeroFila,Long idEmpresa,Map<String, Date> mapaFecha,String codigoTipoDocumento,
			HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes) throws GenericBusinessException;
	
	
}
