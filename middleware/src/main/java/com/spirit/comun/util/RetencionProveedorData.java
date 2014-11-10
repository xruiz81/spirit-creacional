package com.spirit.comun.util;

import java.io.Serializable;

public class RetencionProveedorData implements Serializable {
	private static final long serialVersionUID = -1898996011569955399L;
	private String numComprobanteRetencion;
	private String proveedor;
	private String direccion;
	private String tipoComprobante;
	private String ruc;
	private String telefono;
	private String fechaEmision;
	private String numComprobanteVenta;
	private String ejercicioFiscal;
	private String baseImponible;
	private String impuesto;
	private String codigoImpuesto;
	private String porcentajeRetencion;
	private String valor;
	
	
	public RetencionProveedorData(){
		numComprobanteRetencion = "";
		proveedor = "";
		direccion = "";
		tipoComprobante = "";
		ruc = "";
		telefono = "";
		fechaEmision = "";
		numComprobanteVenta = "";
		ejercicioFiscal = "";
		baseImponible = "";
		impuesto = "";
		codigoImpuesto = "";
		porcentajeRetencion = "";
		valor = "";
	}

	public String getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(String baseImponible) {
		this.baseImponible = baseImponible;
	}

	public String getCodigoImpuesto() {
		return codigoImpuesto;
	}

	public void setCodigoImpuesto(String codigoImpuesto) {
		this.codigoImpuesto = codigoImpuesto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEjercicioFiscal() {
		return ejercicioFiscal;
	}

	public void setEjercicioFiscal(String ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getPorcentajeRetencion() {
		return porcentajeRetencion;
	}

	public void setPorcentajeRetencion(String porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getNumComprobanteRetencion() {
		return numComprobanteRetencion;
	}

	public void setNumComprobanteRetencion(String numComprobanteRetencion) {
		this.numComprobanteRetencion = numComprobanteRetencion;
	}

	public String getNumComprobanteVenta() {
		return numComprobanteVenta;
	}

	public void setNumComprobanteVenta(String numComprobanteVenta) {
		this.numComprobanteVenta = numComprobanteVenta;
	}
}
