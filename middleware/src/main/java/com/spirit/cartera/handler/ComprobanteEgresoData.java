package com.spirit.cartera.handler;

import java.io.Serializable;

public class ComprobanteEgresoData implements Serializable {
	private static final long serialVersionUID = 5390985971926105645L;
	private String fechaEmision;
	private String codigo;
	private String banco;
	private String numeroCuenta;
	private String numeroCheque;
	private String fechaCompra;
	private String codigoCompra;
	private String preimpresoFactura;
	private String detalle;
	private String valor;
	private String saldo;
	private String proveedor;
	private String cantidad;
	private String concepto;
	private String valorTotal;
	private String codigoAsiento;
	private boolean anulado;
	private String totalAplicado;
	private String saldoTotal;
	
	public ComprobanteEgresoData() {
		fechaEmision = "";
		codigo = "";
		banco = "";
		numeroCuenta = "";
		numeroCheque = "";
		fechaCompra = "";
		codigoCompra = "";
		preimpresoFactura = "";
		detalle = "";
		valor = "";
		saldo = "";
		proveedor = "";
		cantidad = "";
		concepto = "";
		valorTotal = "";
		codigoAsiento = "";
		anulado = false;
		totalAplicado = "";
		saldoTotal = "";
	}
	
	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCodigoCompra() {
		return codigoCompra;
	}

	public void setCodigoCompra(String codigoCompra) {
		this.codigoCompra = codigoCompra;
	}

	public String getPreimpresoFactura() {
		return preimpresoFactura;
	}

	public void setPreimpresoFactura(String preimpresoFactura) {
		this.preimpresoFactura = preimpresoFactura;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getCodigoAsiento() {
		return codigoAsiento;
	}

	public void setCodigoAsiento(String codigoAsiento) {
		this.codigoAsiento = codigoAsiento;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public String getTotalAplicado() {
		return totalAplicado;
	}

	public void setTotalAplicado(String totalAplicado) {
		this.totalAplicado = totalAplicado;
	}

	public String getSaldoTotal() {
		return saldoTotal;
	}

	public void setSaldoTotal(String saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	
}