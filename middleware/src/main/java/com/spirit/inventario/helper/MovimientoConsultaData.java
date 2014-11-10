package com.spirit.inventario.helper;

import java.io.Serializable;
import java.util.Date;

public class MovimientoConsultaData implements Serializable{

	Long id;
	String codigo;
	String tipoDocumento;
	Date fechaIngreso;
	String bodega;
	String bodegaRef;
	String estado;
	String observacion;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public String getBodegaRef() {
		return bodegaRef;
	}
	public void setBodegaRef(String bodegaRef) {
		this.bodegaRef = bodegaRef;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
