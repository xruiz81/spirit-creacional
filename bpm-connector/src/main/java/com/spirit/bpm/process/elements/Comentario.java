package com.spirit.bpm.process.elements;

import java.io.Serializable;
import java.util.Date;

public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private UserBpm userBpm;
	private Date fechaIngreso;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public UserBpm getUserBpm() {
		return userBpm;
	}

	public void setUserBpm(UserBpm userBpm) {
		this.userBpm = userBpm;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}
