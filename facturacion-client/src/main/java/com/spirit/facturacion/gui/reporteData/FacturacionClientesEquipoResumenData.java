package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;

public class FacturacionClientesEquipoResumenData implements Serializable {
	
	private static final long serialVersionUID = 6904175191376202624L;
	
	private String equipoId;
	private String equipo;
	private String ejecutivoId;
	private String ejecutivo;
	private String clienteId;
	private String cliente;
	private String facturacionCliente;
	private String negociacionCliente;
	private String facturacionNegociacionCliente;
	private String ivaCliente;
	private String totalCliente;
	
	public FacturacionClientesEquipoResumenData(){
		clienteId = "";
		cliente = "";
		ivaCliente = "";
		totalCliente = "";
		ejecutivoId = "";
		ejecutivo = "";
		equipoId = "";
		equipo = "";
		facturacionCliente = "";
		negociacionCliente = "";
		facturacionNegociacionCliente = "";
	}

	public String getEquipoId() {
		return equipoId;
	}

	public void setEquipoId(String equipoId) {
		this.equipoId = equipoId;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getEjecutivoId() {
		return ejecutivoId;
	}

	public void setEjecutivoId(String ejecutivoId) {
		this.ejecutivoId = ejecutivoId;
	}

	public String getEjecutivo() {
		return ejecutivo;
	}

	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getIvaCliente() {
		return ivaCliente;
	}

	public void setIvaCliente(String ivaCliente) {
		this.ivaCliente = ivaCliente;
	}

	public String getTotalCliente() {
		return totalCliente;
	}

	public void setTotalCliente(String totalCliente) {
		this.totalCliente = totalCliente;
	}

	public String getFacturacionCliente() {
		return facturacionCliente;
	}

	public void setFacturacionCliente(String facturacionCliente) {
		this.facturacionCliente = facturacionCliente;
	}

	public String getNegociacionCliente() {
		return negociacionCliente;
	}

	public void setNegociacionCliente(String negociacionCliente) {
		this.negociacionCliente = negociacionCliente;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getFacturacionNegociacionCliente() {
		return facturacionNegociacionCliente;
	}

	public void setFacturacionNegociacionCliente(
			String facturacionNegociacionCliente) {
		this.facturacionNegociacionCliente = facturacionNegociacionCliente;
	}
}
