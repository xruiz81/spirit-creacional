package com.spirit.medios.gui.reporteData;

import java.io.Serializable;

public class InversionClientesData implements Serializable {
	private static final long serialVersionUID = 6904175191376202624L;
	
	private String clienteId;
	private String cliente;
	private String ruc;
	
	private String enero;
	private String febrero;
	private String marzo;
	private String abril;
	private String mayo;
	private String junio;
	private String julio;
	private String agosto;
	private String septiembre;
	private String octubre;
	private String noviembre;
	private String diciembre;
	//private String total;
	
	private String suma;
	private String descuento;
	private String comision;
	private String subtotal;
	private String iva;
	private String total;
	
	public InversionClientesData(){
		cliente = "";
		ruc = "";
		/*fechaEmision = "";
		numeroFactura = "";
		reembolso = "";
		anulada = "";*/
		suma = "";
		descuento = "";
		comision = "";
		subtotal = "";
		iva = "";
		total = "";
		clienteId = "";
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	/*public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}*/

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	/*public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getReembolso() {
		return reembolso;
	}

	public void setReembolso(String reembolso) {
		this.reembolso = reembolso;
	}*/

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getSuma() {
		return suma;
	}

	public void setSuma(String suma) {
		this.suma = suma;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	/*public String getAnulada() {
		return anulada;
	}

	public void setAnulada(String anulada) {
		this.anulada = anulada;
	}*/
}
