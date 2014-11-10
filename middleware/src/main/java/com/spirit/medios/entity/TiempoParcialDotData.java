package com.spirit.medios.entity;

import java.io.Serializable;

import javax.persistence.Column;

import com.spirit.comun.util.ToStringer;

/**
 * 
 * @author www.versality.com.ec
 * 
 */
public class TiempoParcialDotData implements TiempoParcialDotIf, Serializable {

	private java.lang.Long id;

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	private java.lang.String descripcion;

	public java.lang.String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	private java.lang.Long fechaInicio;

	public java.lang.Long getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(java.lang.Long fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	private java.lang.Long fechaFin;

	public java.lang.Long getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(java.lang.Long fechaFin) {
		this.fechaFin = fechaFin;
	}

	private java.lang.Long tiempo;

	public java.lang.Long getTiempo() {
		return tiempo;
	}

	public void setTiempo(java.lang.Long tiempo) {
		this.tiempo = tiempo;
	}

	private java.lang.Long idOrdenTrabajoDetalle;

	public java.lang.Long getIdOrdenTrabajoDetalle() {
		return idOrdenTrabajoDetalle;
	}

	public void setIdOrdenTrabajoDetalle(java.lang.Long idOrdenTrabajoDetalle) {
		this.idOrdenTrabajoDetalle = idOrdenTrabajoDetalle;
	}

	public TiempoParcialDotData() {
	}

	public TiempoParcialDotData(
			com.spirit.medios.entity.TiempoParcialDotIf value) {
		setId(value.getId());
		setDescripcion(value.getDescripcion());
		setFechaInicio(value.getFechaInicio());
		setFechaFin(value.getFechaFin());
		setTiempo(value.getTiempo());
		setIdOrdenTrabajoDetalle(value.getIdOrdenTrabajoDetalle());
		setUsuarioAsignadoId(value.getUsuarioAsignadoId());
	}

	public java.lang.Long getPrimaryKey() {
		return getId();
	}

	public void setPrimaryKey(java.lang.Long pk) {
		setId(pk);
	}

	public String getPrimaryKeyParameters() {
		String parameters = "";
		parameters += "&id=" + getId();
		return parameters;
	}

	public String toString() {
		return ToStringer.toString((TiempoParcialDotIf) this);
	}

	private java.lang.Long usuarioAsignadoId;

	public java.lang.Long getUsuarioAsignadoId() {
		return usuarioAsignadoId;
	}

	public void setUsuarioAsignadoId(java.lang.Long usuarioAsignadoId) {
		this.usuarioAsignadoId = usuarioAsignadoId;
	}
}
