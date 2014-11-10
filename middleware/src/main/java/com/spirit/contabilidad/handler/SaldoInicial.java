package com.spirit.contabilidad.handler;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaldoInicial implements Serializable{
	
	private static final long serialVersionUID = 1322362440018004940L;
	
	private java.math.BigDecimal debeInicial = BigDecimal.ZERO;
	private java.math.BigDecimal haberInicial = BigDecimal.ZERO;
	
	public SaldoInicial() {
	}
	
	public java.math.BigDecimal getDebeInicial() {
		return debeInicial;
	}
	public void setDebeInicial(java.math.BigDecimal debeInicial) {
		this.debeInicial = debeInicial;
	}
	public java.math.BigDecimal getHaberInicial() {
		return haberInicial;
	}
	public void setHaberInicial(java.math.BigDecimal haberInicial) {
		this.haberInicial = haberInicial;
	}
}