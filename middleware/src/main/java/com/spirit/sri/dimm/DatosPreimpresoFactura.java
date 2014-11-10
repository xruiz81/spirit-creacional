package com.spirit.sri.dimm;

import java.io.Serializable;

public class DatosPreimpresoFactura implements Serializable {

	private static final long serialVersionUID = 7809717432033420948L;
	
	String secuencialInicial = "";
	Integer secuencialInicialInt = null;
	
	String secuencialFinal = "";
	Integer secuencialFinalInt = null;
	
	String aurtorizacion = "";
	
	public DatosPreimpresoFactura() {
	}

	public String getSecuencialInicial() {
		return secuencialInicial;
	}

	public void setSecuencialInicial(String secuencialInicial) {
		this.secuencialInicial = secuencialInicial;
	}

	public Integer getSecuencialInicialInt() {
		return secuencialInicialInt;
	}

	public void setSecuencialInicialInt(Integer secuencialInicialInt) {
		this.secuencialInicialInt = secuencialInicialInt;
	}

	public String getSecuencialFinal() {
		return secuencialFinal;
	}

	public void setSecuencialFinal(String secuencialFinal) {
		this.secuencialFinal = secuencialFinal;
	}

	public Integer getSecuencialFinalInt() {
		return secuencialFinalInt;
	}

	public void setSecuencialFinalInt(Integer secuencialFinalInt) {
		this.secuencialFinalInt = secuencialFinalInt;
	}

	public String getAurtorizacion() {
		return aurtorizacion;
	}

	public void setAurtorizacion(String aurtorizacion) {
		this.aurtorizacion = aurtorizacion;
	}

	
	
	
}
