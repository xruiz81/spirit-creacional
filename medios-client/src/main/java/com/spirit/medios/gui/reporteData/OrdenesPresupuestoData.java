package com.spirit.medios.gui.reporteData;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrdenesPresupuestoData implements Serializable {

	private static final long serialVersionUID = 1250240410780858339L;
	
	private String clienteId;
	private String clienteOficina;
	private String fecha;
	private String codigoPresupuesto;
	private String estadoPresupuesto;
	private String campana;
	private String marca;
	private String producto;
	private String codigoOrden;
	private String estadoOrden;	
	private String tipoProveedor;
	private String proveedor;
	private String creadoPor;
	private String valor;
	private String valorOrden;
	
	private String posicion;	
	private Timestamp date;	
	
	public OrdenesPresupuestoData(){
		clienteId = "";
		clienteOficina = "";
		fecha = "";
		codigoPresupuesto = "";
		estadoPresupuesto = "";
		campana = "";
		marca = "";
		producto = "";
		codigoOrden = "";
		estadoOrden = "";		
		tipoProveedor = "";
		proveedor = "";
		creadoPor = "";
		valor = "";
		valorOrden = "";
		
		posicion = "";		
		Calendar calendarInicio = new GregorianCalendar();
		date = new Timestamp(calendarInicio.getTimeInMillis());
	}

	public String getClienteId() {
		return clienteId;
	}


	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}


	public String getClienteOficina() {
		return clienteOficina;
	}


	public void setClienteOficina(String clienteOficina) {
		this.clienteOficina = clienteOficina;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getCodigoPresupuesto() {
		return codigoPresupuesto;
	}


	public void setCodigoPresupuesto(String codigoPresupuesto) {
		this.codigoPresupuesto = codigoPresupuesto;
	}


	public String getCampana() {
		return campana;
	}


	public void setCampana(String campana) {
		this.campana = campana;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getProducto() {
		return producto;
	}


	public void setProducto(String producto) {
		this.producto = producto;
	}


	public String getTipoProveedor() {
		return tipoProveedor;
	}


	public void setTipoProveedor(String tipoProveedor) {
		this.tipoProveedor = tipoProveedor;
	}


	public String getProveedor() {
		return proveedor;
	}


	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}


	public String getCodigoOrden() {
		return codigoOrden;
	}


	public void setCodigoOrden(String codigoOrden) {
		this.codigoOrden = codigoOrden;
	}


	public String getCreadoPor() {
		return creadoPor;
	}


	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public Timestamp getDate() {
		return date;
	}


	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getEstadoPresupuesto() {
		return estadoPresupuesto;
	}

	public void setEstadoPresupuesto(String estadoPresupuesto) {
		this.estadoPresupuesto = estadoPresupuesto;
	}

	public String getValorOrden() {
		return valorOrden;
	}

	public void setValorOrden(String valorOrden) {
		this.valorOrden = valorOrden;
	}

	public String getEstadoOrden() {
		return estadoOrden;
	}

	public void setEstadoOrden(String estadoOrden) {
		this.estadoOrden = estadoOrden;
	}
}
