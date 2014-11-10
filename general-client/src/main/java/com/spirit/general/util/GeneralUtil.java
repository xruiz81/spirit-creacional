package com.spirit.general.util;

import java.util.Map;

import com.spirit.cartera.entity.CarteraIf;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.inventario.entity.ColorIf;
import com.spirit.inventario.entity.GenericoIf;
import com.spirit.inventario.entity.ModeloIf;
import com.spirit.inventario.entity.PresentacionIf;
import com.spirit.inventario.entity.ProductoIf;
import com.spirit.medios.entity.MarcaProductoIf;
import com.spirit.medios.entity.OrdenTrabajoIf;

public class GeneralUtil {

	public static TipoIdentificacionIf verificarMapaTipoIdentificacion(Map<Long, TipoIdentificacionIf> mapa,Long tipoIdentificacionId) throws GenericBusinessException{
		TipoIdentificacionIf ti = mapa.get(tipoIdentificacionId);
		if ( ti == null ){
			ti = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacion(tipoIdentificacionId);
			if ( ti != null )
				mapa.put(tipoIdentificacionId, ti);
			else 
				throw new GenericBusinessException("No existe Tipo de Identificacion con id "+tipoIdentificacionId);
		}
		return ti;
	}
	
	public static TipoDocumentoIf verificarMapaTipoDocumento(Map<Long, TipoDocumentoIf> mapa,Long tipoDocumentoId) throws GenericBusinessException{
		TipoDocumentoIf td = mapa.get(tipoDocumentoId);
		if ( td == null ){
			td = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(tipoDocumentoId);
			if ( td != null )
				mapa.put(tipoDocumentoId, td);
			else 
				throw new GenericBusinessException("No existe Tipo de Documento con id "+tipoDocumentoId);
		}
		return td;
	}
	
	public static ClienteOficinaIf verificarMapaClienteOficina(Map<Long, ClienteOficinaIf> mapa,Long clienteOficinaId) throws GenericBusinessException{
		ClienteOficinaIf co = mapa.get(clienteOficinaId);
		if ( co == null ){
			co = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(clienteOficinaId);
			if ( co != null )
				mapa.put(clienteOficinaId, co);
			else 
				throw new GenericBusinessException("No existe Cliente Oficina con id "+clienteOficinaId);
		}
		return co;
	}
	
	public static ClienteIf verificarMapaCliente(Map<Long, ClienteIf> mapa,Long clienteId) throws GenericBusinessException{
		ClienteIf cl = mapa.get(clienteId);
		if ( cl == null ){
			cl = SessionServiceLocator.getClienteSessionService().getCliente(clienteId);
			if ( cl != null )
				mapa.put(clienteId, cl);
			/*else 
				throw new GenericBusinessException("No existe Cliente con id "+clienteId);*/
		}
		return cl;
	}
	
	public static EmpleadoIf verificarMapaEmpleado(Map<Long, EmpleadoIf> mapa,Long empleadoId) throws GenericBusinessException{
		EmpleadoIf empleado = mapa.get(empleadoId);
		if ( empleado == null ){
			empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(empleadoId);
			if ( empleado != null )
				mapa.put(empleadoId, empleado);
			else 
				throw new GenericBusinessException("No existe Empleado con id "+empleadoId);
		}
		return empleado;
	}
	
	public static OrdenTrabajoIf verificarMapaOrdenTrabajo(Map<Long, OrdenTrabajoIf> mapa,Long ordenTrabajoId) throws GenericBusinessException{
		OrdenTrabajoIf ordenTrabajo = mapa.get(ordenTrabajoId);
		if ( ordenTrabajo == null ){
			ordenTrabajo = SessionServiceLocator.getOrdenTrabajoSessionService().getOrdenTrabajo(ordenTrabajoId);
			if ( ordenTrabajo != null )
				mapa.put(ordenTrabajoId, ordenTrabajo);
			else 
				throw new GenericBusinessException("No existe Orden de Trabajo con id "+ordenTrabajoId);
		}
		return ordenTrabajo;
	}
	
	public static MarcaProductoIf verificarMapaMarcaProducto(Map<Long, MarcaProductoIf> mapa,Long marcaProductoId) throws GenericBusinessException{
		MarcaProductoIf marcaProducto = mapa.get(marcaProductoId);
		if ( marcaProducto == null ){
			marcaProducto = SessionServiceLocator.getMarcaProductoSessionService().getMarcaProducto(marcaProductoId);
			if ( marcaProducto != null )
				mapa.put(marcaProductoId, marcaProducto);
			else 
				throw new GenericBusinessException("No existe Marca Producto con id "+marcaProductoId);
		}
		return marcaProducto;
	}
	
	public static BancoIf verificarMapaBanco(Map<Long, BancoIf> mapa,Long bancoId) throws GenericBusinessException{
		BancoIf banco = mapa.get(bancoId);
		if ( banco == null ){
			banco = SessionServiceLocator.getBancoSessionService().getBanco(bancoId);
			if ( banco != null )
				mapa.put(bancoId, banco);
			else 
				throw new GenericBusinessException("No existe Ciudad con id "+bancoId);
		}
		return banco;
	}
	
	public static OrdenCompraIf verificarMapaOrdenCompra(Map<Long, OrdenCompraIf> mapa,Long ordenId) throws GenericBusinessException{
		OrdenCompraIf banco = mapa.get(ordenId);
		if ( banco == null ){
			banco = SessionServiceLocator.getOrdenCompraSessionService().getOrdenCompra(ordenId);
			if ( banco != null )
				mapa.put(ordenId, banco);
			else 
				throw new GenericBusinessException("No existe Orden de Compra con id "+ordenId);
		}
		return banco;
	}
	
	public static CiudadIf verificarMapaCiudad(Map<Long, CiudadIf> mapa,Long ciudadId) throws GenericBusinessException{
		CiudadIf ciudad = mapa.get(ciudadId);
		if ( ciudad == null ){
			ciudad = SessionServiceLocator.getCiudadSessionService().getCiudad(ciudadId);
			if ( ciudad != null )
				mapa.put(ciudadId, ciudad);
			else 
				throw new GenericBusinessException("No existe Ciudad con id "+ciudadId);
		}
		return ciudad;
	}
	
	public static ProductoIf verificarMapaProducto(Map<Long, ProductoIf> mapa,Long productoId) throws GenericBusinessException{
		ProductoIf producto = mapa.get(productoId);
		if ( producto == null ){
			producto = SessionServiceLocator.getProductoSessionService().getProducto(productoId);
			if ( producto != null )
				mapa.put(productoId, producto);
			else 
				throw new GenericBusinessException("No existe Producto con id "+productoId);
		}
		return producto;
	}
	
	public static GenericoIf verificarMapaGenerico(Map<Long, GenericoIf> mapa,Long genericoId) throws GenericBusinessException{
		GenericoIf generico = mapa.get(genericoId);
		if ( generico == null ){
			generico = SessionServiceLocator.getGenericoSessionService().getGenerico(genericoId);
			if ( generico != null )
				mapa.put(genericoId, generico);
			else 
				throw new GenericBusinessException("No existe Generico con id "+genericoId);
		}
		return generico;
	}
	
	public static ModeloIf verificarMapaModelo(Map<Long, ModeloIf> mapa, Long modeloId) throws GenericBusinessException{
		ModeloIf modelo = mapa.get(modeloId);
		if (modelo == null) {
			modelo = SessionServiceLocator.getModeloSessionService().getModelo(modeloId);
			if (modelo != null)
				mapa.put(modeloId, modelo);
			else
				throw new GenericBusinessException("No existe modelo con id " + modeloId);
		}
		return modelo;
	}
	
	public static PresentacionIf verificarMapaPresentacion(Map<Long, PresentacionIf> mapa,Long presentacionId) throws GenericBusinessException{
		PresentacionIf presentacion = mapa.get(presentacionId);
		if ( presentacion == null ){
			presentacion = SessionServiceLocator.getPresentacionSessionService().getPresentacion(presentacionId);
			if ( presentacion != null )
				mapa.put(presentacionId, presentacion);
			else 
				throw new GenericBusinessException("No existe Presentacion con id "+presentacionId);
		}
		return presentacion;
	}
	
	public static ColorIf verificarMapaColor(Map<Long, ColorIf> mapa, Long colorId) throws GenericBusinessException {
		ColorIf color = mapa.get(colorId);
		if (color == null) {
			color = SessionServiceLocator.getColorSessionService().getColor(colorId);
			if (color != null)
				mapa.put(colorId, color);
			else
				throw new GenericBusinessException("No existe color con id " + colorId);
		}
		return color;
	}
	
	public static CarteraIf verificarMapaCartera(Map<Long, CarteraIf> mapa,Long carteraId) throws GenericBusinessException{
		CarteraIf cartera = mapa.get(carteraId);
		if ( cartera == null ){
			cartera = SessionServiceLocator.getCarteraSessionService().getCartera(carteraId);
			if ( cartera != null )
				mapa.put(carteraId, cartera);
			else 
				throw new GenericBusinessException("No existe Cartera con id "+carteraId);
		}
		return cartera;
	}
	
	public static CompraIf verificarMapaCompra(Map<Long, CompraIf> mapa,Long compraId) throws GenericBusinessException{
		CompraIf compra = mapa.get(compraId);
		if ( compra == null ){
			compra = SessionServiceLocator.getCompraSessionService().getCompra(compraId);
			if ( compra != null )
				mapa.put(compraId, compra);
			else 
				throw new GenericBusinessException("No existe Compra con id "+compraId);
		}
		return compra;
	}
	
}
