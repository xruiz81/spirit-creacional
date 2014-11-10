package com.spirit.bpm.process.elements;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.spirit.bpm.process.elemets.state.EnumInstanceState;

public class InstanciaProceso implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private EnumInstanceState estado;
	private String proceso;
	private Date fechaIniciado;
	private Date fechaFinalizado;
	private Date fechaUltimaActualizacion;
	private String iniciadoPor;
	private String finalizadoPor;
	private String descripcion;
	private List<Tarea> tareas;
	private List<Comentario> comentarios;
	private String tiempoEjecucion;

	public InstanciaProceso() {
		super();
	}

	public InstanciaProceso(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EnumInstanceState getEstado() {
		return estado;
	}

	public void setEstado(EnumInstanceState estado) {
		this.estado = estado;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public Date getFechaIniciado() {
		return fechaIniciado;
	}

	public void setFechaIniciado(Date fechaIniciado) {
		this.fechaIniciado = fechaIniciado;
	}

	public Date getFechaFinalizado() {
		return fechaFinalizado;
	}

	public void setFechaFinalizado(Date fechaFinalizado) {
		this.fechaFinalizado = fechaFinalizado;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public String getIniciadoPor() {
		return iniciadoPor;
	}

	public void setIniciadoPor(String iniciadoPor) {
		this.iniciadoPor = iniciadoPor;
	}

	public String getFinalizadoPor() {
		return finalizadoPor;
	}

	public void setFinalizadoPor(String finalizadoPor) {
		this.finalizadoPor = finalizadoPor;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(String tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

}
