package com.spirit.nomina.gui.datos;


public class RolPagoContratoContinuoDatos {

	private String nombreEmpleado = null;
	private Long contratoId = null;
	private Double totalIngresos = 0.0;
	private Double totalEgresos = 0.0;

	private boolean eventual;
	private String observacion;
	private String estado;
	
	private String nombreRubroIngreso = null;
	private Double valorIngreso = null;
	private int tipoIngreso;
	
	private String nombreRubroEgreso = null;
	private Double valorEgreso = null;
	private int tipoEgreso;
	
	public RolPagoContratoContinuoDatos(){
		this.nombreRubroIngreso = null;
		this.valorIngreso = null;
		
		this.nombreRubroEgreso = null;
		this.valorEgreso = null;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public Double getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(Double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public Double getTotalEgresos() {
		return totalEgresos;
	}

	public void setTotalEgresos(Double totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

	public String getNombreRubroIngreso() {
		return nombreRubroIngreso;
	}

	public void setNombreRubroIngreso(String nombreRubroIngreso) {
		this.nombreRubroIngreso = nombreRubroIngreso;
	}

	public Double getValorIngreso() {
		return valorIngreso;
	}

	public void setValorIngreso(Double valorIngreso) {
		this.valorIngreso = valorIngreso;
	}

	public int getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(int tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	public String getNombreRubroEgreso() {
		return nombreRubroEgreso;
	}

	public void setNombreRubroEgreso(String nombreRubroEgreso) {
		this.nombreRubroEgreso = nombreRubroEgreso;
	}

	public Double getValorEgreso() {
		return valorEgreso;
	}

	public void setValorEgreso(Double valorEgreso) {
		this.valorEgreso = valorEgreso;
	}

	public int getTipoEgreso() {
		return tipoEgreso;
	}

	public void setTipoEgreso(int tipoEgreso) {
		this.tipoEgreso = tipoEgreso;
	}
	
	public boolean isEventual() {
		return eventual;
	}

	public void setEventual(boolean eventual) {
		this.eventual = eventual;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
