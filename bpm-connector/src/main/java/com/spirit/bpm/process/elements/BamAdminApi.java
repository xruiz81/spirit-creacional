package com.spirit.bpm.process.elements;

import java.io.Serializable;
import java.util.Date;

public class BamAdminApi implements Serializable {
	private static final long serialVersionUID = 1L;

	private int procesosActivos;
	private int procesosTerminados;
	private Date fechaDesde;

	public int getProcesosActivos() {
		return procesosActivos;
	}

	public void setProcesosActivos(int procesosActivos) {
		this.procesosActivos = procesosActivos;
	}

	public int getProcesosTerminados() {
		return procesosTerminados;
	}

	public void setProcesosTerminados(int procesosTerminados) {
		this.procesosTerminados = procesosTerminados;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

}
