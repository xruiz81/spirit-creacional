package com.spirit.nomina.gui.datos;

import java.math.BigDecimal;

public class AutorizacionPagoRolRubrosEventualesData {

	private String nombreRubro;
	private String codigoContrato;
	private String observacion;
	private BigDecimal valor;
	
	public AutorizacionPagoRolRubrosEventualesData(){
		nombreRubro = "";
		codigoContrato = "";
		observacion = "";
		valor = new BigDecimal(0);
	}

	public String getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}	
}
