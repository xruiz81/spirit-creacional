package com.spirit.bpm.process.elements;

import com.spirit.bpm.process.elemets.state.EnumNivelVencido;
import com.spirit.bpm.process.elemets.state.EnumPrioridad;
import com.spirit.bpm.process.elemets.state.EnumTareaState;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tarea implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String descripcion;
	private String proceso;
	private EnumPrioridad prioridad;
	private Date fechaInicio;
	private Date fechaCreacion;
	private Date fechaEsperadaFinalizacion;
	private Date fechaFinalizacion;
	private Date fechaAsignacion;
	private String iniciadoPor;
	private String finalizadoPor;
	private EnumTareaState estado;
	private int orderIndex;
	private EnumNivelVencido nivelVencido;
	private String observacionFechaVencida;
	private UserBpm asignadaA;
	private String tiempoEjecucion;
	private String instanciaId;
	private List<Comentario> comentarios;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public EnumPrioridad getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(EnumPrioridad prioridad) {
		this.prioridad = prioridad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaEsperadaFinalizacion() {
		return fechaEsperadaFinalizacion;
	}

	public void setFechaEsperadaFinalizacion(Date fechaEsperadaFinalizacion) {
		this.fechaEsperadaFinalizacion = fechaEsperadaFinalizacion;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}

	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
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

	public EnumTareaState getEstado() {
		return estado;
	}

	public void setEstado(EnumTareaState estado) {
		this.estado = estado;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public EnumNivelVencido getNivelVencido() {
		return nivelVencido;
	}

	public void setNivelVencido(EnumNivelVencido nivelVencido) {
		this.nivelVencido = nivelVencido;
	}

	public String getObservacionFechaVencida() {
		return observacionFechaVencida;
	}

	public void setObservacionFechaVencida(String observacionFechaVencida) {
		this.observacionFechaVencida = observacionFechaVencida;
	}

	public String getInfo() {
		return "[" + getProceso() + "] " + getNombre();
	}

	public UserBpm getAsignadaA() {
		return asignadaA;
	}

	public void setAsignadaA(UserBpm asignadaA) {
		this.asignadaA = asignadaA;
	}

	public String getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(String tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public String getInstanciaId() {
		return instanciaId;
	}

	public void setInstanciaId(String instanciaId) {
		this.instanciaId = instanciaId;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
