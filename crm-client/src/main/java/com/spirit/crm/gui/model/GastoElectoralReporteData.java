package com.spirit.crm.gui.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;

public class GastoElectoralReporteData {

	private String campana;
	private Date fecha;
	private String tipo;
	private String producto;
	private String proveedor;
	private String descripcion;
	private String tamano;
	private BigDecimal cantidad;
	private BigDecimal costoUnitario;
	private BigDecimal inversionConFactura;
	private BigDecimal inversionSinFactura;
	private BigDecimal totalIngresoCampana;
	
	public GastoElectoralReporteData(){
		campana = "";
		fecha = new Date(new GregorianCalendar().getTime().getTime());
		tipo = "";
		producto = "";
		proveedor = "";
		descripcion = "";
		tamano = "";
		cantidad = new BigDecimal(0);
		costoUnitario = new BigDecimal(0);
		inversionConFactura = new BigDecimal(0);
		inversionSinFactura = new BigDecimal(0);
		totalIngresoCampana = new BigDecimal(0);
	}

	public String getCampana() {
		return campana;
	}

	public void setCampana(String campana) {
		this.campana = campana;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(BigDecimal costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getInversionConFactura() {
		return inversionConFactura;
	}

	public void setInversionConFactura(BigDecimal inversionConFactura) {
		this.inversionConFactura = inversionConFactura;
	}

	public BigDecimal getInversionSinFactura() {
		return inversionSinFactura;
	}

	public void setInversionSinFactura(BigDecimal inversionSinFactura) {
		this.inversionSinFactura = inversionSinFactura;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getTotalIngresoCampana() {
		return totalIngresoCampana;
	}

	public void setTotalIngresoCampana(BigDecimal totalIngresoCampana) {
		this.totalIngresoCampana = totalIngresoCampana;
	}
}
