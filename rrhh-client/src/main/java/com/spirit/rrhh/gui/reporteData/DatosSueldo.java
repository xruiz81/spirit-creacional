package com.spirit.rrhh.gui.reporteData;

public class DatosSueldo {

	String incremento = "";
	String fecha = "";
	Double valor = 0.0;
	
	public DatosSueldo() {
	
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getIncremento() {
		return incremento;
	}

	public void setIncremento(String incremento) {
		this.incremento = incremento;
	}	
}
