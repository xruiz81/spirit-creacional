package com.spirit.crm.gui.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.GregorianCalendar;

public class GastoElectoralIngresosReporteData {

	private String campana;
	private Date fecha;
	private String entregadoPor;
	private BigDecimal valor;
	private BigDecimal totalEgresoCampana;
	
	public GastoElectoralIngresosReporteData(){
		campana = "";
		fecha = new Date(new GregorianCalendar().getTime().getTime());
		entregadoPor = "";
		valor = new BigDecimal(0);
		totalEgresoCampana = new BigDecimal(0);
	}

	public String getCampana() {
		return campana;
	}

	public void setCampana(String campana) {
		this.campana = campana;
	}

	public String getEntregadoPor() {
		return entregadoPor;
	}

	public void setEntregadoPor(String entregadoPor) {
		this.entregadoPor = entregadoPor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotalEgresoCampana() {
		return totalEgresoCampana;
	}

	public void setTotalEgresoCampana(BigDecimal totalEgresoCampana) {
		this.totalEgresoCampana = totalEgresoCampana;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
