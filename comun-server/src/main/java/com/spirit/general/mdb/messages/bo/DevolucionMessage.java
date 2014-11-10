package com.spirit.general.mdb.messages.bo;

import java.util.Vector;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class DevolucionMessage extends ObjectMessenger implements DevolucionMessageLocal{
	String preImpresoFacturaAfectada;
	Object empleado;
	Object clienteOficinaIf; 
	Object clienteIf;
	Object puntoImpresionIf;
	Vector detalleDevolucionVector; 
	Vector<Vector> TarjetasCollection_detalles;
	Double ivaTotal;
	Double subTotal; 
	Double descuentoTotal; 
	Double descuentoGlobalTotal;
	Object ventasTrack;
	Long idempresa;
	Long idoficina;
	Object usuario;
	Long fechaDevolucion;
	Long idFacturaOrigen;
	Long tipoDocumentoId;
	String apd;
	String atptt;
	
	
	public void setData(String preImpresoFacturaAfectada, Object empleado,
			Object clienteOficinaIf, Object clienteIf, Object puntoImpresionIf,
			Vector detalleDevolucionVector, Vector<Vector> TarjetasCollection_detalles, Double ivaTotal, Double subTotal,
			Double descuentoTotal, Double descuentoGlobalTotal,
			Object ventasTrack, Long idempresa, Long idoficina, Object usuario,Long fechaDevolucion, Long idFacturaOrigen,Long tipoDocumentoId,String apd,String atptt) {
		this.preImpresoFacturaAfectada = preImpresoFacturaAfectada;
		this.empleado = empleado;
		this.clienteOficinaIf = clienteOficinaIf;
		this.clienteIf = clienteIf;
		this.puntoImpresionIf = puntoImpresionIf;
		this.detalleDevolucionVector = detalleDevolucionVector;
		this.TarjetasCollection_detalles = TarjetasCollection_detalles;
		this.ivaTotal = ivaTotal;
		this.subTotal = subTotal;
		this.descuentoTotal = descuentoTotal;
		this.descuentoGlobalTotal = descuentoGlobalTotal;
		this.ventasTrack = ventasTrack;
		this.idempresa = idempresa;
		this.idoficina = idoficina;
		this.usuario = usuario;
		this.idFacturaOrigen = idFacturaOrigen;
		this.fechaDevolucion = fechaDevolucion;
		this.tipoDocumentoId=tipoDocumentoId;
		this.apd=apd;
		this.atptt=atptt;
	}
	public String getPreImpresoFacturaAfectada() {
		return preImpresoFacturaAfectada;
	}
	public Object getEmpleado() {
		return empleado;
	}
	public Object getClienteOficinaIf() {
		return clienteOficinaIf;
	}
	public Object getClienteIf() {
		return clienteIf;
	}
	public Object getPuntoImpresionIf() {
		return puntoImpresionIf;
	}
	public Vector getDetalleDevolucionVector() {
		return detalleDevolucionVector;
	}
	public Double getIvaTotal() {
		return ivaTotal;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public Double getDescuentoTotal() {
		return descuentoTotal;
	}
	public Double getDescuentoGlobalTotal() {
		return descuentoGlobalTotal;
	}
	public Object getVentasTrack() {
		return ventasTrack;
	}
	public Long getIdempresa() {
		return idempresa;
	}
	public Long getIdoficina() {
		return idoficina;
	}
	public Object getUsuario() {
		return usuario;
	}
	public Long getFechaDevolucion() {
		return fechaDevolucion;
	}
	public Long getIdFacturaOrigen() {
		return idFacturaOrigen;
	}
	public Long getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public Vector<Vector> getTarjetasCollection_detalles() {
		return TarjetasCollection_detalles;
	}
	public String getApd() {
		return apd;
	}
	public String getAtptt() {
		return atptt;
	}
}
