package com.spirit.compras.gui.model;
 

import java.io.Serializable;

 

public class ComprasIvaRetencionConsolidadoData implements Serializable {
	private static final long serialVersionUID = 6904175191376202624L;
	
	private String clienteId;
	private String numeroFactura;	
	private String fechaEmision;
	private String cliente;
	
	private String retencionRenta;	
	private String valorRenta;	
	private String retencionIva;
	private String valorIva;
	
	private String codigoRetencionIva;
	private String codigoRetencionRenta;
	
	private String estado;
	
	public ComprasIvaRetencionConsolidadoData(){
		cliente = ""; 
		fechaEmision = "";
		numeroFactura = "";
		retencionRenta = "";
		valorRenta = "";
		retencionIva = "";
		valorIva = "";
		codigoRetencionRenta = "";
		codigoRetencionIva = "";
		estado = "";
	}
	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	 

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getRetencionIva() {
		return retencionIva;
	}

	public void setRetencionIva(String retencionIva) {
		this.retencionIva = retencionIva;
	}

	public String getRetencionRenta() {
		return retencionRenta;
	}

	public void setRetencionRenta(String retencionRenta) {
		this.retencionRenta = retencionRenta;
	}

	public String getValorIva() {
		return valorIva;
	}

	public void setValorIva(String valorIva) {
		this.valorIva = valorIva;
	}

	public String getValorRenta() {
		return valorRenta;
	}

	public void setValorRenta(String valorRenta) {
		this.valorRenta = valorRenta;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getCodigoRetencionIva() {
		return codigoRetencionIva;
	}

	public void setCodigoRetencionIva(String codigoRetencionIva) {
		this.codigoRetencionIva = codigoRetencionIva;
	}

	public String getCodigoRetencionRenta() {
		return codigoRetencionRenta;
	}

	public void setCodigoRetencionRenta(String codigoRetencionRenta) {
		this.codigoRetencionRenta = codigoRetencionRenta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	
}
