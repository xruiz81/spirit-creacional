package com.spirit.facturacion.handler;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ConsultaFacturaVendedor implements Serializable {
	private Long facturaId;
	private Date fechaEmision;
	private String transaccion;
	private String observacion;
	private String vendedor;
	private String codigoTransaccion;
	private String numeroFactura;
	private BigDecimal total;
	private BigDecimal descuento;
	private BigDecimal totalBrutas;
	private BigDecimal valorIva;
	
	public Long getFacturaId() {
		return facturaId;
	}
	public void setFacturaId(Long facturaId) {
		this.facturaId = facturaId;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getCodigoTransaccion() {
		return codigoTransaccion;
	}
	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public BigDecimal getTotalBrutas() {
		return totalBrutas;
	}
	public void setTotalBrutas(BigDecimal totalBrutas) {
		this.totalBrutas = totalBrutas;
	}
	public BigDecimal getValorIva() {
		return valorIva;
	}
	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}
	
	
}
