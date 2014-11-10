package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InversionTotalesClientesData implements Serializable {

	private static final long serialVersionUID = -9019237053285107072L;
	
	private String clienteId;
	private String clienteOficina;
	private String mesPresupuesto;
	private String fechaPresupuesto;
	private String codigoPresupuesto;
	private String tipoMedio;
	private String subtotalPresupuesto;
	private String subtotalOrdenes;
	private Timestamp date;
	
	public InversionTotalesClientesData(){
		clienteId = "";
		clienteOficina = "";
		mesPresupuesto = "";
		fechaPresupuesto = "";
		codigoPresupuesto = "";
		tipoMedio = "";
		subtotalPresupuesto = "";
		subtotalOrdenes = "";
		Calendar calendarInicio = new GregorianCalendar();
		date = new Timestamp(calendarInicio.getTimeInMillis());
	}
	
	public String getNombreAtributo(int i){
		String nombre = "";
		
		switch (i) {
		case 1:
			nombre = "CLIENTE OFICINA";
			break;
		case 2:
			nombre = "MES PRESUPUESTO";
			break;
		case 3:
			nombre = "FECHA PRESUPUESTO";
			break;
		case 4:
			nombre = "CODIGO PRESUPUESTO";
			break;
		case 5:
			nombre = "TIPO DE MEDIO";
			break;
		case 6:
			nombre = "SUBTOTAL PRESUPUESTO";
			break;
		case 7:
			nombre = "SUBTOTAL ORDENES";
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
			campo = getClienteOficina();
			break;
		case 2:
			campo = getMesPresupuesto();
			break;
		case 3:
			campo = getFechaPresupuesto();
			break;
		case 4:
			campo = getCodigoPresupuesto();
			break;
		case 5:
			campo = getTipoMedio();
			break;
		case 6:
			campo = getSubtotalPresupuesto();
			break;
		case 7:
			campo = getSubtotalOrdenes();
			break;
			
		default:
			campo = "Indice Invalido";
			break;
		}		
		
		return campo;
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

	public String getMesPresupuesto() {
		return mesPresupuesto;
	}

	public void setMesPresupuesto(String mesPresupuesto) {
		this.mesPresupuesto = mesPresupuesto;
	}

	public String getFechaPresupuesto() {
		return fechaPresupuesto;
	}

	public void setFechaPresupuesto(String fechaPresupuesto) {
		this.fechaPresupuesto = fechaPresupuesto;
	}

	public String getCodigoPresupuesto() {
		return codigoPresupuesto;
	}

	public void setCodigoPresupuesto(String codigoPresupuesto) {
		this.codigoPresupuesto = codigoPresupuesto;
	}

	public String getTipoMedio() {
		return tipoMedio;
	}

	public void setTipoMedio(String tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public String getSubtotalPresupuesto() {
		return subtotalPresupuesto;
	}

	public void setSubtotalPresupuesto(String subtotalPresupuesto) {
		this.subtotalPresupuesto = subtotalPresupuesto;
	}

	public String getSubtotalOrdenes() {
		return subtotalOrdenes;
	}

	public void setSubtotalOrdenes(String subtotalOrdenes) {
		this.subtotalOrdenes = subtotalOrdenes;
	}
	
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
