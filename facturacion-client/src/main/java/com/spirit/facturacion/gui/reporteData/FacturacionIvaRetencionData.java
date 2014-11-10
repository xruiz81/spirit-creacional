package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;

public class FacturacionIvaRetencionData implements Serializable  {
	private static final long serialVersionUID = 7644977459530184219L;

	private String clienteId;
	private String cliente;
	private String ruc;
	private String fechaEmision;
	private String numeroFactura;
	private String anulada;
	private String exterior;
	private String reembolso;
	private String normal;
	private String iva;
	private String total;
	private String retencionRenta;
	private String renta1;
	private String renta2;
	private String retencionIva;
	private String iva30;
	private String iva70;
	private String iva100;
	
	public FacturacionIvaRetencionData(){
		clienteId = "";
		cliente = "";
		ruc = "";
		fechaEmision = "";
		numeroFactura = "";
		anulada = "";
		exterior = "";
		reembolso = "";
		normal = "";
		iva = "";
		total = "";
		retencionRenta = "";
		renta1 = "";
		renta2 = "";
		retencionIva = "";
		iva30 = "";
		iva70 = "";
		iva100 = "";
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

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getIva100() {
		return iva100;
	}

	public void setIva100(String iva100) {
		this.iva100 = iva100;
	}

	public String getIva30() {
		return iva30;
	}

	public void setIva30(String iva30) {
		this.iva30 = iva30;
	}

	public String getIva70() {
		return iva70;
	}

	public void setIva70(String iva70) {
		this.iva70 = iva70;
	}

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public String getNumeroFactura() {
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
	}

	public String getRenta1() {
		return renta1;
	}

	public void setRenta1(String renta1) {
		this.renta1 = renta1;
	}

	public String getRenta2() {
		return renta2;
	}

	public void setRenta2(String renta2) {
		this.renta2 = renta2;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getAnulada() {
		return anulada;
	}

	public void setAnulada(String anulada) {
		this.anulada = anulada;
	}
}
