package com.spirit.general.mdb.messages.bo;

import java.util.Map;
import java.util.Vector;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface FacturaPosMessageLocal extends ObjectMessengerLocal{

	public void setData(Vector pedidoDetalleGiftcardVector, Vector pedidoDetalletarjetaAfiliacionVector, Vector pedidoDetalleFacturaVector, Vector pedidoDetalleNotaVentaVector, Vector pedidoDetallePersonalizacionVector,
			Object ventasTrack,Vector<Vector> pagoDetalleVector, String donacion, String idfundacion, Object pedidoAnterior, Vector pedidoDetalleEliminadosVector,
			Vector pedidoDetalleProcesoVector, Vector<Vector> tarjetasCollectionDetalles, Map<String, Object> parametros,Long idFacturaOrigen,Long idReciboCajaOrigen,Object cliente,Object clienteOficina);

	public Vector getPedidoDetalleGiftcardVector();
	
	public Vector getPedidoDetalleTarjetaAfiliacionVector();

	public Vector getPedidoDetalleFacturaVector();

	public Vector getPedidoDetalleNotaVentaVector();

	public Vector getPedidoDetallePersonalizacionVector();

	public Vector<Vector> getPagoDetalleVector();

	public String getDonacion();

	public String getIdfundacion();

	public Object getPedidoAnterior();

	public Vector getPedidoDetalleEliminadosVector();

	public Vector getPedidoDetalleProcesoVector();
	
	public Vector<Vector> getTarjetasCollectionDetalles();

	public Map<String, Object> getParametros();

	public Long getIdFacturaOrigen();
	
	public Long getIdReciboCajaOrigen();

	public Object getCliente();

	public Object getClienteOficina();
	
	public Object getVentasTrack();


}