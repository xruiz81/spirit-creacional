package com.spirit.nomina.gui.controller;

import com.spirit.client.SpiritConstants;

public class TotalVentasHorasExtrasTableWidget {
	private Boolean seleccion;
	private String empleado;
	private Double totalVentas;
	private Double numeroHorasExtras50;
	private Double numeroHorasExtras100;
	private Long contratoId;
	
	public TotalVentasHorasExtrasTableWidget() {
		this.seleccion = new Boolean(false);
		this.empleado = SpiritConstants.getEmptyCharacter();
		this.totalVentas = new Double(0D);
		this.numeroHorasExtras50 = new Double(0D);
		this.numeroHorasExtras100 = new Double(0D);
		this.contratoId = new Long(0L);
	}

	public Boolean getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Boolean seleccion) {
		this.seleccion = seleccion;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Double getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(Double totalVentas) {
		this.totalVentas = totalVentas;
	}

	public Double getNumeroHorasExtras50() {
		return numeroHorasExtras50;
	}

	public void setNumeroHorasExtras50(Double numeroHorasExtras50) {
		this.numeroHorasExtras50 = numeroHorasExtras50;
	}

	public Double getNumeroHorasExtras100() {
		return numeroHorasExtras100;
	}

	public void setNumeroHorasExtras100(Double numeroHorasExtras100) {
		this.numeroHorasExtras100 = numeroHorasExtras100;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}
}
