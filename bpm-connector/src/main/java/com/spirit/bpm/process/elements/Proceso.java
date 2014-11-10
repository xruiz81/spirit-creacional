package com.spirit.bpm.process.elements;

import java.io.Serializable;
import java.util.List;

import com.spirit.bpm.process.elemets.state.EnumProcessState;

public class Proceso implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private String version;
	private EnumProcessState estado;
	private List<UserBpm> participantes;
	private String id;

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public EnumProcessState getEstado() {
		return estado;
	}

	public void setEstado(EnumProcessState estado) {
		this.estado = estado;
	}

	public List<UserBpm> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<UserBpm> participantes) {
		this.participantes = participantes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
