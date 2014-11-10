package com.spirit.medios.gui.reporteData;

import java.io.Serializable;

public class OrdenesClientesData implements Serializable {
	private static final long serialVersionUID = 6904175191376202624L;
	
	
	private String cliente;
	private String presupuesto;
	private String orden;
	private String compra;
	private String cartera;
	private String tipo;
	private String proveedor;
	private String marca;
	private String producto;
	private String creadoPor;
	private String fecha;
	private String descuento;	
	private String subtotal;
	private String iva;
	private String total;
	private String numero;
	
	public OrdenesClientesData(){
		cliente = "";
		presupuesto = "";
		orden = "";
		compra = "";
		tipo = "";
		proveedor = "";
		marca = "";
		producto = "";
		creadoPor = "";
		fecha = "";
		descuento = "";
		subtotal = "";
		iva = "";
		total = "";
		numero = "";
		cartera = "";
	}

	public String getTipo() {
		return tipo;
	}

	public String getMarca() {
		return marca;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public String getCliente() {
		return cliente;
	}

	public String getPresupuesto() {
		return presupuesto;
	}

	public String getOrden() {
		return orden;
	}

	public String getProveedor() {
		return proveedor;
	}
	
	public String getProducto() {
		return producto;
	}

	public String getFecha() {
		return fecha;
	}

	public String getDescuento() {
		return descuento;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public String getIva() {
		return iva;
	}

	public String getTotal() {
		return total;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCompra() {
		return compra;
	}

	public void setCompra(String compra) {
		this.compra = compra;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCartera() {
		return cartera;
	}

	public void setCartera(String cartera) {
		this.cartera = cartera;
	}
}
