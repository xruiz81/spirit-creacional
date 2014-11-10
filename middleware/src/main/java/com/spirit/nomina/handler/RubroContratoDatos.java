package com.spirit.nomina.handler;


public class RubroContratoDatos extends RolPagos {
	
	private String nombreRubro;
	private Double valor;
	private int tipo;
	private boolean eventual;
	private String observacion;
	private String Estado;
	private Long asientoId;

	public Long getAsientoId() {
		return asientoId;
	}

	public void setAsientoId(Long asientoId) {
		this.asientoId = asientoId;
	}

	public boolean isEventual() {
		return eventual;
	}

	public void setEventual(boolean eventual) {
		this.eventual = eventual;
	}

	public RubroContratoDatos(){
		this.nombreRubro = "";
		this.valor = 0.0;
		observacion = "";
	}
	
	public String getNombreRubro() {
		return nombreRubro;
	}
	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
}
