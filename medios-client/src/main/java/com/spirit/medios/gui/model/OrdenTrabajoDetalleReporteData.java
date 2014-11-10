package com.spirit.medios.gui.model;

import java.io.Serializable;

public class OrdenTrabajoDetalleReporteData implements Serializable {

	private static final long serialVersionUID = -1090285171767811111L;

	private String tipo;
	private String subtipo;
	private String asignadoA;
	private String descripcion;
	private String fechaEntrega;
	private String estado;
	private String fechaDetalle;
	private String horaDetalle;
	
	public OrdenTrabajoDetalleReporteData(){
		tipo = "";
		subtipo = "";
		asignadoA = "";
		descripcion = "";
		fechaEntrega = "";
		estado = "";
		fechaDetalle = "";
		horaDetalle = "";
	}

	public String getAsignadoA() {
		return asignadoA;
	}

	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFechaDetalle() {
		return fechaDetalle;
	}

	public void setFechaDetalle(String fechaDetalle) {
		this.fechaDetalle = fechaDetalle;
	}

	public String getHoraDetalle() {
		return horaDetalle;
	}

	public void setHoraDetalle(String horaDetalle) {
		this.horaDetalle = horaDetalle;
	}
}
