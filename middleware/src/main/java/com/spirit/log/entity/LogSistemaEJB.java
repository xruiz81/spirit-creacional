package com.spirit.log.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

/**
 * 
 * @author www.versality.com.ec
 * 
 */

@Table(name = "LOG_SISTEMA")
@Entity
public class LogSistemaEJB implements Serializable, LogSistemaIf {

	private java.lang.Long id;
	private java.lang.String tipoMensaje;
	private java.lang.String tipoRegistro;
	private java.sql.Timestamp fechaTransaccion;
	private java.lang.String error;
	private java.lang.String descripcion;
	private String modulo;
	private java.sql.Blob object;

	public LogSistemaEJB() {
	}

	public LogSistemaEJB(LogSistemaIf value) {
		setId(value.getId());
		setTipoMensaje(value.getTipoMensaje());
		setTipoRegistro(value.getTipoRegistro());
		setFechaTransaccion(value.getFechaTransaccion());
		setError(value.getError());
		setDescripcion(value.getDescripcion());
		setObject(value.getObject());
	}

	public java.lang.Long create(LogSistemaIf value) {
		setId(value.getId());
		setTipoMensaje(value.getTipoMensaje());
		setTipoRegistro(value.getTipoRegistro());
		setFechaTransaccion(value.getFechaTransaccion());
		setError(value.getError());
		setDescripcion(value.getDescripcion());
		setObject(value.getObject());
		return value.getPrimaryKey();
	}

	@javax.persistence.Transient
	public java.lang.Long getPrimaryKey() {
		return getId();
	}

	@javax.persistence.Transient
	public void setPrimaryKey(java.lang.Long primaryKey) {
		setId(primaryKey);
	}

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	@Column(name = "TIPO_MENSAJE")
	public java.lang.String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(java.lang.String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	@Column(name = "TIPO_REGISTRO")
	public java.lang.String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(java.lang.String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	@Column(name = "FECHA_TRANSACCION")
	public java.sql.Timestamp getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(java.sql.Timestamp fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	@Column(name = "ERROR")
	public java.lang.String getError() {
		return error;
	}

	public void setError(java.lang.String error) {
		this.error = error;
	}

	@Column(name = "DESCRIPCION")
	public java.lang.String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(java.lang.String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "OBJECT")
	public java.sql.Blob getObject() {
		return object;
	}

	public void setObject(java.sql.Blob object) {
		this.object = object;
	}

	@Column(name = "MODULO")
	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
}
