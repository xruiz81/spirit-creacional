package com.spirit.general.mdb.messages.bo;

import java.util.Map;
import java.util.Vector;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class FacturaPosMessage extends ObjectMessenger implements FacturaPosMessageLocal{

	private static final long serialVersionUID = -41662608447633686L;
	Vector pedidoDetalleGiftcardVector;
	Vector pedidoDetalleTarjetaAfiliacionVector;
	Vector pedidoDetalleFacturaVector;
	Vector pedidoDetalleNotaVentaVector;
	Vector pedidoDetallePersonalizacionVector;
	Vector<Vector> pagoDetalleVector;
	String donacion;
	String idfundacion; 
	Object pedidoAnterior;
	Vector pedidoDetalleEliminadosVector;
	Vector pedidoDetalleProcesoVector;
	Vector<Vector> tarjetasCollectionDetalles;
	Map<String,Object> parametros;
	Long idFacturaOrigen;
	Long idReciboCajaOrigen;
	Object cliente;
	Object clienteOficina;
	Object ventasTrack;
	
	public void setData(Vector pedidoDetalleGiftcardVector,
			Vector pedidoDetalleTarjetaAfiliacionVector,
			Vector pedidoDetalleFacturaVector,
			Vector pedidoDetalleNotaVentaVector,
			Vector pedidoDetallePersonalizacionVector,
			Object ventasTrack,
			Vector<Vector> pagoDetalleVector, String donacion,
			String idfundacion, Object pedidoAnterior,
			Vector pedidoDetalleEliminadosVector,
			Vector pedidoDetalleProcesoVector,
			Vector<Vector> tarjetasCollectionDetalles, Map<String, Object> parametros,Long idFacturaOrigen,	Long idReciboCajaOrigen, Object cliente, Object clienteOficina) {
		this.pedidoDetalleGiftcardVector = pedidoDetalleGiftcardVector;
		this.pedidoDetalleTarjetaAfiliacionVector = pedidoDetalleTarjetaAfiliacionVector;
		this.pedidoDetalleFacturaVector = pedidoDetalleFacturaVector;
		this.pedidoDetalleNotaVentaVector = pedidoDetalleNotaVentaVector;
		this.pedidoDetallePersonalizacionVector = pedidoDetallePersonalizacionVector;
		this.tarjetasCollectionDetalles = tarjetasCollectionDetalles;
		this.ventasTrack=ventasTrack;
		this.pagoDetalleVector = pagoDetalleVector;
		this.donacion = donacion;
		this.idfundacion = idfundacion;
		this.pedidoAnterior = pedidoAnterior;
		this.pedidoDetalleEliminadosVector = pedidoDetalleEliminadosVector;
		this.pedidoDetalleProcesoVector = pedidoDetalleProcesoVector;
		this.parametros = parametros;
		this.idFacturaOrigen=idFacturaOrigen;
		this.idReciboCajaOrigen=idReciboCajaOrigen;
		this.cliente=cliente;
		this.clienteOficina=clienteOficina;
	}
	public Vector getPedidoDetalleGiftcardVector() {
		return pedidoDetalleGiftcardVector;
	}
	public Vector getPedidoDetalleTarjetaAfiliacionVector() {
		return pedidoDetalleTarjetaAfiliacionVector;
	}
	public Vector getPedidoDetalleFacturaVector() {
		return pedidoDetalleFacturaVector;
	}
	public Vector getPedidoDetalleNotaVentaVector() {
		return pedidoDetalleNotaVentaVector;
	}
	public Vector getPedidoDetallePersonalizacionVector() {
		return pedidoDetallePersonalizacionVector;
	}
	public Vector<Vector> getPagoDetalleVector() {
		return pagoDetalleVector;
	}
	public String getDonacion() {
		return donacion;
	}
	public String getIdfundacion() {
		return idfundacion;
	}
	public Object getPedidoAnterior() {
		return pedidoAnterior;
	}
	public Vector getPedidoDetalleEliminadosVector() {
		return pedidoDetalleEliminadosVector;
	}
	public Vector getPedidoDetalleProcesoVector() {
		return pedidoDetalleProcesoVector;
	}
	public Map<String, Object> getParametros() {
		return parametros;
	}
	public Long getIdFacturaOrigen() {
		return idFacturaOrigen;
	}
	public Long getIdReciboCajaOrigen() {
		return idReciboCajaOrigen;
	}
	public Object getCliente() {
		return cliente;
	}
	public Object getClienteOficina() {
		return clienteOficina;
	}
	public Object getVentasTrack() {
		return ventasTrack;
	}
	public Vector<Vector> getTarjetasCollectionDetalles() {
		return tarjetasCollectionDetalles;
	}
}
