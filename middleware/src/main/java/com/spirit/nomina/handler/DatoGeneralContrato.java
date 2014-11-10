package com.spirit.nomina.handler;

import java.io.Serializable;
import java.util.Date;

public class DatoGeneralContrato implements Serializable{

	private static final long serialVersionUID = 7117400377377882690L;
	
	private Long contratoId = null;
	private String nombreEmpleado = null;
	private String tipoContrato = null;
	private Date fechaIngreso = null;
	private String fechaIngresoTexto = null;
	
	public DatoGeneralContrato() {
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaIngresoTexto() {
		return fechaIngresoTexto;
	}

	public void setFechaIngresoTexto(String fechaIngresoTexto) {
		this.fechaIngresoTexto = fechaIngresoTexto;
	}
	
	
	
}
