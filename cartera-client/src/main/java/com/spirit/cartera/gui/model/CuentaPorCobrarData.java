package com.spirit.cartera.gui.model;

import java.io.Serializable;

public class CuentaPorCobrarData implements Serializable {
	private static final long serialVersionUID = 6620576970884475499L;
	private String cliente;
	private String ruc;
	private String numeroFactura;
	private String fechaFactura;
	private String fechaEmision;
	private String retencionRegistrada;
	private String detalle;
	private String xVencer;
	private String _30Dias;
	private String _60Dias;
	private String _90Dias;
	private String _120Dias;
	private String diasVencidos;
	private String clienteId;
	
	public CuentaPorCobrarData() {
		cliente = "";
		ruc = "";
		numeroFactura = "";
		fechaFactura = "";
		fechaEmision = "";
		retencionRegistrada = "";
		detalle = "";
		xVencer = "";
		_30Dias = "";
		_60Dias = "";
		_90Dias = "";
		_120Dias = "";
		diasVencidos = "";
		clienteId = "";
	}

	public String get_30Dias() {
		return _30Dias;
	}

	public void set_30Dias(String dias) {
		_30Dias = dias;
	}

	public String get_60Dias() {
		return _60Dias;
	}

	public void set_60Dias(String dias) {
		_60Dias = dias;
	}

	public String get_90Dias() {
		return _90Dias;
	}

	public void set_90Dias(String dias) {
		_90Dias = dias;
	}

	public String get_120Dias() {
		return _120Dias;
	}

	public void set_120Dias(String dias) {
		_120Dias = dias;
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

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getRetencionRegistrada() {
		return retencionRegistrada;
	}

	public void setRetencionRegistrada(String retencionRegistrada) {
		this.retencionRegistrada = retencionRegistrada;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getxVencer() {
		return xVencer;
	}

	public void setxVencer(String vencer) {
		xVencer = vencer;
	}

	public String getDiasVencidos() {
		return diasVencidos;
	}

	public void setDiasVencidos(String diasVencidos) {
		this.diasVencidos = diasVencidos;
	}
}