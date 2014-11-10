package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InversionClientesData implements Serializable {

	private static final long serialVersionUID = -3898843604358534913L;

	private String clienteId;
	private String clienteOficina;
	private String codigoPresupuesto;
	private String tipoFactura;
	private String cartera;
	private String fechaFactura;
	private String factura;
	private String fechaAprobacion;
	private String mesPresupuesto;
	private String sap;
	private String segmento;
	private String producto;
	private String medio;
	private String proveedor;
	private String valor;
	private String iva;
	private String total;
	private String posicion;
	
	private String inversionPautaValor = "";
	private String inversionPautaIva = "";
	private String inversionPautaTotal = "";
	
	private Timestamp date;
	
	private String carteraFactura;
	private String carteraCompra;
	
	
	public InversionClientesData(){
		clienteId = "";
		clienteOficina = "";
		codigoPresupuesto = "";
		tipoFactura = "";
		cartera = "";
		fechaFactura = "";
		factura = "";
		fechaAprobacion = "";
		mesPresupuesto = "";
		sap = "";
		segmento = "";
		producto = "";
		medio = "";
		proveedor = "";
		valor = "";
		iva = "";
		total = "";
		posicion = "";
		
		inversionPautaValor = "";
		inversionPautaIva = "";
		inversionPautaTotal = "";
		
		Calendar calendarInicio = new GregorianCalendar();
		date = new Timestamp(calendarInicio.getTimeInMillis());
		
		carteraFactura = "";
		carteraCompra = "";
	}

	public String getMesPresupuesto() {
		return mesPresupuesto;
	}

	public void setMesPresupuesto(String mesPresupuesto) {
		this.mesPresupuesto = mesPresupuesto;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getSap() {
		return sap;
	}

	public void setSap(String sap) {
		this.sap = sap;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getMedio() {
		return medio;
	}

	public void setMedio(String medio) {
		this.medio = medio;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

	public String getClienteOficina() {
		return clienteOficina;
	}

	public void setClienteOficina(String clienteOficina) {
		this.clienteOficina = clienteOficina;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getCodigoPresupuesto() {
		return codigoPresupuesto;
	}

	public void setCodigoPresupuesto(String codigoPresupuesto) {
		this.codigoPresupuesto = codigoPresupuesto;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getInversionPautaValor() {
		return inversionPautaValor;
	}

	public void setInversionPautaValor(String inversionPautaValor) {
		this.inversionPautaValor = inversionPautaValor;
	}

	public String getInversionPautaIva() {
		return inversionPautaIva;
	}

	public void setInversionPautaIva(String inversionPautaIva) {
		this.inversionPautaIva = inversionPautaIva;
	}

	public String getInversionPautaTotal() {
		return inversionPautaTotal;
	}

	public void setInversionPautaTotal(String inversionPautaTotal) {
		this.inversionPautaTotal = inversionPautaTotal;
	}

	public String getCartera() {
		return cartera;
	}

	public void setCartera(String cartera) {
		this.cartera = cartera;
	}

	public String getCarteraFactura() {
		return carteraFactura;
	}

	public void setCarteraFactura(String carteraFactura) {
		this.carteraFactura = carteraFactura;
	}

	public String getCarteraCompra() {
		return carteraCompra;
	}

	public void setCarteraCompra(String carteraCompra) {
		this.carteraCompra = carteraCompra;
	}
}
