package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ComisionesAdicionalesData implements Serializable {

	private static final long serialVersionUID = 4169337006337078295L;
	
	private String tipoMedio;
	private String proveedorOficina;
	private String fechaCompra;
	private String preimpresoCompra;
	private String codigoPresupuesto;
	private String clienteOficina;
	private String codigoPresupuestoProveedor;
	private String valorPresupuestoProveedor;
	private String estadoPresupuestoProveedor;
	private String inversionPresupuesto;
	private String porcentajeComisionAdicional;
	private String valorComisionAdicional;
	
	private Timestamp date;
	
	public ComisionesAdicionalesData(){
		tipoMedio = "";
		proveedorOficina = "";
		fechaCompra = "";
		preimpresoCompra = "";
		codigoPresupuesto = "";
		clienteOficina = "";
		codigoPresupuestoProveedor = "";
		valorPresupuestoProveedor = "";
		estadoPresupuestoProveedor = "";
		inversionPresupuesto = "";
		porcentajeComisionAdicional = "";
		valorComisionAdicional = "";
		
		Calendar calendarInicio = new GregorianCalendar();
		date = new Timestamp(calendarInicio.getTimeInMillis());
	}
	
	public String getNombreAtributo(int i){
		String nombre = "";
		
		switch (i) {
		case 1:
			nombre = "TIPO DE MEDIO";
			break;
		case 2:
			nombre = "PROVEEDOR";
			break;
		case 3:
			nombre = "FECHA COMPRA";
			break;
		case 4:
			nombre = "PREIMPRESO COMPRA";
			break;
		case 5:
			nombre = "PRESUPUESTO";
			break;
		case 6:
			nombre = "CLIENTE";
			break;
		case 7:
			nombre = "PRESUPUESTO PROVEEDOR";
			break;
		case 8:
			nombre = "VALOR PRESUPUESTO PROVEEDOR";
			break;
		case 9:
			nombre = "ESTADO PRESUPUESTO PROVEEDOR";
			break;
		case 10:
			nombre = "INVERSION BRUTA";
			break;
		case 11:
			nombre = "PORCENTAJE DE COMISION";
			break;
		case 12:
			nombre = "VALOR COMISION";
			break;
			
		default:
			nombre = "Indice Invalido";
			break;
		}		
		
		return nombre;
	}

	public String getCampo(int i){
		String campo = "";
		
		switch (i) {
		case 1:
			campo = getTipoMedio();
			break;
		case 2:
			campo = getProveedorOficina();
			break;
		case 3:
			campo = getFechaCompra();
			break;
		case 4:
			campo = getPreimpresoCompra();
			break;
		case 5:
			campo = getCodigoPresupuesto();
			break;
		case 6:
			campo = getClienteOficina();
			break;
		case 7:
			campo = getCodigoPresupuestoProveedor();
			break;
		case 8:
			campo = getValorPresupuestoProveedor();
			break;
		case 9:
			campo = getEstadoPresupuestoProveedor();
			break;
		case 10:
			campo = getInversionPresupuesto();
			break;
		case 11:
			campo = getPorcentajeComisionAdicional();
			break;
		case 12:
			campo = getValorComisionAdicional();
			break;
			
		default:
			campo = "Indice Invalido";
			break;
		}		
		
		return campo;
	}

	public void setTipoMedio(String tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public void setProveedorOficina(String proveedorOficina) {
		this.proveedorOficina = proveedorOficina;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public void setPreimpresoCompra(String preimpresoCompra) {
		this.preimpresoCompra = preimpresoCompra;
	}

	public void setCodigoPresupuesto(String codigoPresupuesto) {
		this.codigoPresupuesto = codigoPresupuesto;
	}

	public void setClienteOficina(String clienteOficina) {
		this.clienteOficina = clienteOficina;
	}

	public void setCodigoPresupuestoProveedor(String codigoPresupuestoProveedor) {
		this.codigoPresupuestoProveedor = codigoPresupuestoProveedor;
	}

	public void setValorPresupuestoProveedor(String valorPresupuestoProveedor) {
		this.valorPresupuestoProveedor = valorPresupuestoProveedor;
	}

	public void setEstadoPresupuestoProveedor(String estadoPresupuestoProveedor) {
		this.estadoPresupuestoProveedor = estadoPresupuestoProveedor;
	}

	public void setInversionPresupuesto(String inversionPresupuesto) {
		this.inversionPresupuesto = inversionPresupuesto;
	}

	public void setPorcentajeComisionAdicional(String porcentajeComisionAdicional) {
		this.porcentajeComisionAdicional = porcentajeComisionAdicional;
	}

	public void setValorComisionAdicional(String valorComisionAdicional) {
		this.valorComisionAdicional = valorComisionAdicional;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getTipoMedio() {
		return tipoMedio;
	}

	public String getProveedorOficina() {
		return proveedorOficina;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public String getPreimpresoCompra() {
		return preimpresoCompra;
	}

	public String getCodigoPresupuesto() {
		return codigoPresupuesto;
	}

	public String getClienteOficina() {
		return clienteOficina;
	}

	public String getCodigoPresupuestoProveedor() {
		return codigoPresupuestoProveedor;
	}

	public String getValorPresupuestoProveedor() {
		return valorPresupuestoProveedor;
	}

	public String getEstadoPresupuestoProveedor() {
		return estadoPresupuestoProveedor;
	}

	public String getInversionPresupuesto() {
		return inversionPresupuesto;
	}

	public String getPorcentajeComisionAdicional() {
		return porcentajeComisionAdicional;
	}

	public String getValorComisionAdicional() {
		return valorComisionAdicional;
	}

	public Timestamp getDate() {
		return date;
	}
}
