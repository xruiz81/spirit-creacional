package com.spirit.general.mdb.messages.bo;

import java.util.Vector;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface DevolucionMessageLocal extends ObjectMessengerLocal{

	public void setData(String preImpresoFacturaAfectada, Object empleado, Object clienteOficinaIf, Object clienteIf,
			Object puntoImpresionIf, Vector detalleDevolucionVector, Vector<Vector> TarjetasCollection_detalles, Double ivaTotal, Double subTotal, Double descuentoTotal,
			Double descuentoGlobalTotal, Object ventasTrack, Long idempresa, Long idoficina, Object usuario,Long fechaDevolucion,Long idFacturaOrigen,Long tipoDocumentoId,String apd,String atptt);

	public String getPreImpresoFacturaAfectada();

	public Object getEmpleado();

	public Object getClienteOficinaIf();

	public Object getClienteIf();

	public Object getPuntoImpresionIf();

	public Vector getDetalleDevolucionVector();

	public Double getIvaTotal();

	public Double getSubTotal();

	public Double getDescuentoTotal();

	public Double getDescuentoGlobalTotal();

	public Object getVentasTrack();

	public Long getIdempresa();

	public Long getIdoficina();

	public Object getUsuario();

	public Long getIdFacturaOrigen();
	
	public Long getTipoDocumentoId();

	public String getApd();
	
	public String getAtptt();
	
	public Long getFechaDevolucion();
}
