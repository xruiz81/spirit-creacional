package com.spirit.medios.util;

import com.spirit.medios.entity.OrdenTrabajoDetalleIf;

public class InfoOrdenTrabajoDetalle {

	OrdenTrabajoDetalleIf ordenTrabajoDetalleIf = null;
	String descripcion = "";
	
	public InfoOrdenTrabajoDetalle(
		OrdenTrabajoDetalleIf ordenTrabajoDetalleIf,String descripcion) {
		this.ordenTrabajoDetalleIf = ordenTrabajoDetalleIf;
		this.descripcion = descripcion;
	}	
	
	public String toString() {
		return descripcion;
	}

	public OrdenTrabajoDetalleIf getOrdenTrabajoDetalleIf() {
		return ordenTrabajoDetalleIf;
	}

	public void setOrdenTrabajoDetalleIf(OrdenTrabajoDetalleIf ordenTrabajoDetalleIf) {
		this.ordenTrabajoDetalleIf = ordenTrabajoDetalleIf;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if ( arg0 instanceof InfoOrdenTrabajoDetalle ){
			InfoOrdenTrabajoDetalle info = (InfoOrdenTrabajoDetalle ) arg0;
			return this.ordenTrabajoDetalleIf.getId().equals(info.getOrdenTrabajoDetalleIf().getId());
		}
		return super.equals(arg0);
	}
}
