package com.spirit.nomina.handler;

import java.io.Serializable;
import java.util.Collection;

import com.spirit.nomina.entity.RolPagoDetalleIf;

public class DatoAsientoMensual implements Serializable {

	private static final long serialVersionUID = 7266362123740770345L;
	
	String nombre = null;
	Collection<RolPagoDetalleIf> rolPagoDetalleCollection = null;
	Long contratoId = null;
	Double total = null;
	
	public DatoAsientoMensual() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<RolPagoDetalleIf> getRolPagoDetalleCollection() {
		return rolPagoDetalleCollection;
	}

	public void setRolPagoDetalleCollection(
			Collection<RolPagoDetalleIf> rolPagoDetalleCollection) {
		this.rolPagoDetalleCollection = rolPagoDetalleCollection;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
}
