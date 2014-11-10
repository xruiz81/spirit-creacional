package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;

public class FacturacionClientesData implements Serializable {
	private static final long serialVersionUID = 6904175191376202624L;
	
	private String clienteId;
	private String cliente;
	private String corporacionId;
	private String corporacion;
	private String ejecutivoId;
	private String ejecutivo;
	private String equipoId;
	private String equipo;
	private String ruc;
	private String fechaEmision;
	private String numeroFactura;
	private String reembolso;
	private String anulada;
	private String suma;
	private String descuento;
	private String comision;
	private String subtotal;
	private String iva;
	private String total;
	private Long facturaId;
	
	public FacturacionClientesData(){
		cliente = "";
		ruc = "";
		fechaEmision = "";
		numeroFactura = "";
		reembolso = "";
		anulada = "";
		suma = "";
		descuento = "";
		comision = "";
		subtotal = "";
		iva = "";
		total = "";
		clienteId = "";
		corporacionId = "";
		corporacion = "";
		ejecutivoId = "";
		ejecutivo = "";
		equipoId = "";
		equipo = "";
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

	public String getAnulada() {
		return anulada;
	}

	public void setAnulada(String anulada) {
		this.anulada = anulada;
	}

	public String getCorporacionId() {
		return corporacionId;
	}

	public void setCorporacionId(String corporacionId) {
		this.corporacionId = corporacionId;
	}

	public String getCorporacion() {
		return corporacion;
	}

	public void setCorporacion(String corporacion) {
		this.corporacion = corporacion;
	}

	public String getEjecutivoId() {
		return ejecutivoId;
	}

	public void setEjecutivoId(String ejecutivoId) {
		this.ejecutivoId = ejecutivoId;
	}

	public String getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public String getEquipoId() {
		return equipoId;
	}

	public void setEquipoId(String equipoId) {
		this.equipoId = equipoId;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Long getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}
}
