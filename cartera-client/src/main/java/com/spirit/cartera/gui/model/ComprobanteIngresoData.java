package com.spirit.cartera.gui.model;

import java.io.Serializable;

public class ComprobanteIngresoData implements Serializable {
	private static final long serialVersionUID = 6632016095670928759L;
	private String bancoDeposito;
	private String numeroCuenta;
	private String bancoCheque;
	private String numeroCheque;
	private String fechaFactura;
	private String numeroFactura;
	private String detalle;
	private String valor;
	private String saldo;
	
	public ComprobanteIngresoData() {
		bancoDeposito = "";
		numeroCuenta = "";
		bancoCheque = "";
		numeroCheque = "";
		fechaFactura = "";
		numeroFactura = "";
		detalle = "";
		valor = "";
		saldo = "";
	}

	public String getBancoCheque() {
		return bancoCheque;
	}

	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}

	public String getBancoDeposito() {
		return bancoDeposito;
	}

	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
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

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
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
}