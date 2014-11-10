package com.spirit.bpm.process.elements;

import java.io.Serializable;
import java.util.Date;

public class BamApi implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date fechaDesde;
	private int casosTerminados;
	private int casosPendientes;
	private int casosVencidos;
	private int casosEnRiesgo;

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public int getCasosTerminados() {
		return casosTerminados;
	}

	public void setCasosTerminados(int casosTerminados) {
		this.casosTerminados = casosTerminados;
	}

	public int getCasosPendientes() {
		return casosPendientes;
	}

	public void setCasosPendientes(int casosPendientes) {
		this.casosPendientes = casosPendientes;
	}

	public int getCasosVencidos() {
		return casosVencidos;
	}

	public void setCasosVencidos(int casosVencidos) {
		this.casosVencidos = casosVencidos;
	}

	public int getCasosEnRiesgo() {
		return casosEnRiesgo;
	}

	public void setCasosEnRiesgo(int casosEnRiesgo) {
		this.casosEnRiesgo = casosEnRiesgo;
	}
}
