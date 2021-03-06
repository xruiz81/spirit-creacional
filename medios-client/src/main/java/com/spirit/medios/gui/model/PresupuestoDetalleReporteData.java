package com.spirit.medios.gui.model;

import java.io.Serializable;

public class PresupuestoDetalleReporteData implements Serializable {

	private static final long serialVersionUID = -1585252418761979114L;

	private String proveedor;
	private String item;
	private String detalle;
	private String cantidad;
	private String parcial;
	private String total;
	
	public PresupuestoDetalleReporteData(){
		proveedor = "";
		item = "";
		detalle = "";
		cantidad = "";
		parcial = "";
		total = "";
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getParcial() {
		return parcial;
	}

	public void setParcial(String parcial) {
		this.parcial = parcial;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
}
