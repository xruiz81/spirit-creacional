package com.spirit.nomina.handler;

import java.io.Serializable;
import java.util.Collection;

import com.spirit.nomina.entity.RolPagoDetalleIf;

public class DatoMulta implements Serializable {

	private static final long serialVersionUID = 7266362123740770345L;
	
	String nombreEmpleado = null;
	Collection<RolPagoDetalleIf> rolPagoDetalleCollection = null;
	
	public DatoMulta() {
	}

	public Collection<RolPagoDetalleIf> getRolPagoDetalleCollection() {
		return rolPagoDetalleCollection;
	}

	public void setRolPagoDetalleCollection(
			Collection<RolPagoDetalleIf> rolPagoDetalleCollection) {
		this.rolPagoDetalleCollection = rolPagoDetalleCollection;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

}
