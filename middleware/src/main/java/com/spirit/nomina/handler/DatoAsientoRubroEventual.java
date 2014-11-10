package com.spirit.nomina.handler;

import java.io.Serializable;
import java.util.Collection;

import com.spirit.nomina.entity.RolPagoDetalleIf;

public class DatoAsientoRubroEventual implements Serializable{

	private static final long serialVersionUID = -4370976574000721258L;

	Collection<RolPagoDetalleIf> detalle = null;
	String nombreRubro = null;
	Double total = null;
	
	public DatoAsientoRubroEventual() {
		
	}

	public Collection<RolPagoDetalleIf> getDetalle() {
		return detalle;
	}

	public void setDetalle(Collection<RolPagoDetalleIf> detalle) {
		this.detalle = detalle;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}
	
}
