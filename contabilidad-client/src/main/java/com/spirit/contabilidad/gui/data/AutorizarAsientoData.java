package com.spirit.contabilidad.gui.data;

import java.io.Serializable;

public class AutorizarAsientoData implements Serializable {
	private static final long serialVersionUID = -3926288023047205558L;
	private String numero;
	private String fechaMovimiento;
	private String mes;
	private String cuenta;
	private String nombreCuenta;
	private String glosa;
	private String debe;
	private String haber;
	private String totalDebe;
	private String totalHaber;
	private String elaboradoPor;
	private String autorizadoPor;
	private Long tipoDocumentoId;
	private Long transaccionId;
		
	public AutorizarAsientoData() {
		numero = "";
		fechaMovimiento = "";
		mes = "";
		cuenta = "";
		nombreCuenta = "";
		glosa = "";
		debe = "";
		haber = "";
		totalDebe = "";
		totalHaber = "";
		elaboradoPor = "";
		autorizadoPor = "";
		tipoDocumentoId = 0L;
		transaccionId = 0L;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDebe() {
		return debe;
	}

	public void setDebe(String debe) {
		this.debe = debe;
	}

	public String getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public String getGlosa() {
		return glosa;
	}

	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	public String getHaber() {
		return haber;
	}

	public void setHaber(String haber) {
		this.haber = haber;
	}

	public String getTotalDebe() {
		return totalDebe;
	}

	public void setTotalDebe(String totalDebe) {
		this.totalDebe = totalDebe;
	}

	public String getTotalHaber() {
		return totalHaber;
	}

	public void setTotalHaber(String totalHaber) {
		this.totalHaber = totalHaber;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAutorizadoPor() {
		return autorizadoPor;
	}

	public void setAutorizadoPor(String autorizadoPor) {
		this.autorizadoPor = autorizadoPor;
	}

	public String getElaboradoPor() {
		return elaboradoPor;
	}

	public void setElaboradoPor(String elaboradoPor) {
		this.elaboradoPor = elaboradoPor;
	}

	public Long getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(Long tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	public Long getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Long transaccionId) {
		this.transaccionId = transaccionId;
	}
}
