package com.spirit.contabilidad.gui.data;

import java.io.Serializable;

public class CuentaBancariaIngresoData implements Serializable {

	String detalle = "";
	String codigoOperacion = "";
	String numeroCheque = "";
	Double valor = 0D;
	
	public CuentaBancariaIngresoData() {
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
