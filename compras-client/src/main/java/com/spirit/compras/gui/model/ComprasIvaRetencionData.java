package com.spirit.compras.gui.model;

import java.io.Serializable;

public class ComprasIvaRetencionData implements Serializable {
	private static final long serialVersionUID = -3673783320807244874L;
	
	private String clienteId;
	private String cliente;
	private String ruc;
	private String fechaEmision;
	private String numeroFactura;
	private String anulada;
	private String exterior;
	private String reembolso;
	private String reposicion;
	private String normal;
	private String baseIva12;
	private String baseIva0;
	private String iva;
	private String total;
	private String impuesto;
	private String codigo;
	private String porcentaje;
	private String valorRetenido;
	private String numeroRetencion;
	
	public ComprasIvaRetencionData(){
		clienteId = "";
		cliente = "";
		ruc = "";
		fechaEmision = "";
		numeroFactura = "";
		anulada = "";
		exterior = "";
		reembolso = "";
		reposicion = "";
		normal = "";
		baseIva12 = "";
		baseIva0 = "";
		iva = "";
		total = "";
		impuesto = "";
		codigo = "";
		porcentaje = "";
		valorRetenido = "";
		numeroRetencion = "";
	}

	public String getAnulada() {
		return anulada;
	}

	public void setAnulada(String anulada) {
		this.anulada = anulada;
	}

	public String getBaseIva0() {
		return baseIva0;
	}

	public void setBaseIva0(String baseIva0) {
		this.baseIva0 = baseIva0;
	}

	public String getBaseIva12() {
		return baseIva12;
	}

	public void setBaseIva12(String baseIva12) {
		this.baseIva12 = baseIva12;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
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

	public String getNumeroRetencion() {
		return numeroRetencion;
	}

	public void setNumeroRetencion(String numeroRetencion) {
		this.numeroRetencion = numeroRetencion;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getReembolso() {
		return reembolso;
	}

	public void setReembolso(String reembolso) {
		this.reembolso = reembolso;
	}

	public String getReposicion() {
		return reposicion;
	}

	public void setReposicion(String reposicion) {
		this.reposicion = reposicion;
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

	public String getValorRetenido() {
		return valorRetenido;
	}

	public void setValorRetenido(String valorRetenido) {
		this.valorRetenido = valorRetenido;
	}
}
