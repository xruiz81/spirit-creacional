package com.spirit.cartera.gui.model;

import java.io.Serializable;

public class CuentaPorPagarData implements Serializable {
	private static final long serialVersionUID = 6620576970884475499L;
	private String razonSocial;
	private String ruc;
	private String numeroFactura;
	private String valorTotal;
	private String retefuente;
	private String saldo;
	private String fecha;
	private String tipoProveedor;
	private String codigoTipoProveedor;
	private String tipoProveedorId;
	private String proveedorId;
	private String detalle;
	private String reembolso;
	private String diasVencidos;
	
	public CuentaPorPagarData() {
		razonSocial = "";
		ruc = "";
		numeroFactura = "";
		valorTotal = "";
		retefuente = "";
		saldo = "";
		fecha = "";
		tipoProveedor = "";
		codigoTipoProveedor = "";
		tipoProveedorId = "";
		proveedorId = "";
		detalle = "";
		reembolso = "";
		diasVencidos = "";
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRetefuente() {
		return retefuente;
	}

	public void setRetefuente(String retefuente) {
		this.retefuente = retefuente;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getTipoProveedor() {
		return tipoProveedor;
	}

	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}

	public String getTipoProveedorId() {
		return tipoProveedorId;
	}

	public void setTipoProveedorId(String tipoProveedorId) {
		this.tipoProveedorId = tipoProveedorId;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(String proveedorId) {
		this.proveedorId = proveedorId;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getDiasVencidos() {
		return diasVencidos;
	}

	public void setDiasVencidos(String diasVencidos) {
		this.diasVencidos = diasVencidos;
	}

	public String getReembolso() {
		return reembolso;
	}

	public void setReembolso(String reembolso) {
		this.reembolso = reembolso;
	}

	public String getCodigoTipoProveedor() {
		return codigoTipoProveedor;
	}

	public void setCodigoTipoProveedor(String codigoTipoProveedor) {
		this.codigoTipoProveedor = codigoTipoProveedor;
	}
}