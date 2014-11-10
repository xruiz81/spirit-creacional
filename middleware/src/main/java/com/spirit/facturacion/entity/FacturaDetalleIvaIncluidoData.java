package com.spirit.facturacion.entity;

import java.io.Serializable;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class FacturaDetalleIvaIncluidoData implements Serializable  {

	private static final long serialVersionUID = -837063254932712411L;
	
	private String descripcion;
	private String subtotal;
	private String iva;
	private String total;
	private java.lang.Long id;

	public FacturaDetalleIvaIncluidoData(){
		descripcion = "";
		subtotal = "";
		iva = "";
		total = "";
		id = 0L;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
}
