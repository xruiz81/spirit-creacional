package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;

public class NotaCreditoClienteData implements Serializable {
	
	private static final long serialVersionUID = -3632877847719188834L;
	
	private String clienteId;
	private String ruc;
	private String preimpreso;
	private String autorizacion;
	private String fechaEmision;	
	private String cliente;
	private String documentoNotaCredito;
	private String observacion;
	private String subtotal;
	private String iva;
	private String total;		
	
	public NotaCreditoClienteData(){
		clienteId = "";
		ruc = "";
		preimpreso = "";
		autorizacion = "";
		fechaEmision = "";	
		cliente = "";
		documentoNotaCredito = "";
		observacion = "";
		subtotal = "";
		iva = "";
		total = "";
	}

	public String getPreimpreso() {
		return preimpreso;
	}

	public void setPreimpreso(String preimpreso) {
		this.preimpreso = preimpreso;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDocumentoNotaCredito() {
		return documentoNotaCredito;
	}

	public void setDocumentoNotaCredito(String documentoNotaCredito) {
		this.documentoNotaCredito = documentoNotaCredito;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}
}
