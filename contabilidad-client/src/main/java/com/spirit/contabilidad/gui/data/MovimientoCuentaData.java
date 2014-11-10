package com.spirit.contabilidad.gui.data;

import java.io.Serializable;

import com.spirit.contabilidad.entity.AsientoIf;

public class MovimientoCuentaData implements Serializable {
	private static final long serialVersionUID = 8067124824344443932L;
	private java.math.BigDecimal saldoInicial;
	private java.lang.Long cuentaId;
	private java.lang.String fechaAsiento;
	private java.lang.String numeroAsiento;
	private java.lang.String glosa;
	private java.math.BigDecimal debe;
	private java.math.BigDecimal haber;
	private java.lang.String codigoCuenta;
	private java.lang.String nombreCuenta; 
	private java.lang.String codigoComprobante;
	private AsientoIf asiento;
	private java.math.BigDecimal debeInicial;
	private java.math.BigDecimal haberInicial;
	
	public MovimientoCuentaData() {
	   
	}

	public java.lang.Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(java.lang.Long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public java.math.BigDecimal getDebe() {
		return debe;
	}

	public void setDebe(java.math.BigDecimal debe) {
		this.debe = debe;
	}

	public java.lang.String getFechaAsiento() {
		return fechaAsiento;
	}

	public void setFechaAsiento(java.lang.String fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}

	public java.lang.String getGlosa() {
		return glosa;
	}

	public void setGlosa(java.lang.String glosa) {
		this.glosa = glosa;
	}

	public java.math.BigDecimal getHaber() {
		return haber;
	}

	public void setHaber(java.math.BigDecimal haber) {
		this.haber = haber;
	}

	public java.lang.String getNumeroAsiento() {
		return numeroAsiento;
	}

	public void setNumeroAsiento(java.lang.String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}

	public java.lang.String getCodigoCuenta() {
		return codigoCuenta;
	}

	public void setCodigoCuenta(java.lang.String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public java.lang.String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(java.lang.String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	public java.lang.String getCodigoComprobante() {
		return codigoComprobante;
	}

	public void setCodigoComprobante(java.lang.String codigoComprobante) {
		this.codigoComprobante = codigoComprobante;
	}

	public java.math.BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(java.math.BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public AsientoIf getAsiento() {
		return asiento;
	}

	public void setAsiento(AsientoIf asiento) {
		this.asiento = asiento;
	}

	public java.math.BigDecimal getDebeInicial() {
		return debeInicial;
	}

	public void setDebeInicial(java.math.BigDecimal debeInicial) {
		this.debeInicial = debeInicial;
	}

	public java.math.BigDecimal getHaberInicial() {
		return haberInicial;
	}

	public void setHaberInicial(java.math.BigDecimal haberInicial) {
		this.haberInicial = haberInicial;
	}
}
