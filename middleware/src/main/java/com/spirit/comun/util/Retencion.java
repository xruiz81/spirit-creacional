package com.spirit.comun.util;

import java.io.Serializable;

public class Retencion implements Serializable {
	private static final long serialVersionUID = 4308848217255463764L;
	private int ejercicio;
	private String establecimiento;
	private String puntoEmision;
	private String secuencial;
	private String autorizacion;
	private double baseImponible;
	private String impuesto;
	private String codigoImpuesto;
	private double porcentajeRetencion;
	private double valorRetenido;
	private Long cuentaId;
	
	public Retencion() {
		ejercicio = 0;
		establecimiento = "";
		puntoEmision = "";
		secuencial = "";
		autorizacion = "";
		baseImponible = 0D;
		impuesto = "";
		codigoImpuesto = "";
		porcentajeRetencion = 0;
		valorRetenido = 0.0;
		cuentaId = 0L;
	}
	
	public String getAutorizacion() {
		return autorizacion;
	}
	
	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}
	
	public double getBaseImponible() {
		return baseImponible;
	}
	
	public void setBaseImponible(double baseImponible) {
		this.baseImponible = baseImponible;
	}
	
	public int getEjercicio() {
		return ejercicio;
	}
	
	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	public String getEstablecimiento() {
		return establecimiento;
	}
	
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	
	public String getImpuesto() {
		return impuesto;
	}
	
	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}
	
	public double getPorcentajeRetencion() {
		return porcentajeRetencion;
	}
	
	public void setPorcentajeRetencion(double porcentajeRetencion) {
		this.porcentajeRetencion = porcentajeRetencion;
	}
	
	public String getPuntoEmision() {
		return puntoEmision;
	}
	
	public void setPuntoEmision(String puntoEmision) {
		this.puntoEmision = puntoEmision;
	}
	
	public String getSecuencial() {
		return secuencial;
	}
	
	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}
	
	public double getValorRetenido() {
		return valorRetenido;
	}
	
	public void setValorRetenido(double valorRetenido) {
		this.valorRetenido = valorRetenido;
	}

	public Long getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(Long cuentaId) {
		this.cuentaId = cuentaId;
	}

	public String getCodigoImpuesto() {
		return codigoImpuesto;
	}

	public void setCodigoImpuesto(String codigoImpuesto) {
		this.codigoImpuesto = codigoImpuesto;
	}
}