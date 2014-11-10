package com.spirit.nomina.handler;

import java.io.Serializable;

import com.spirit.nomina.entity.RubroIf;

public class DatoAsientoPagoRol implements Serializable {

	private static final long serialVersionUID = -7555799266831680381L;
	
	Boolean eventual = false;
	String nombreEmpleado = null;
	String nombreRubro = null;
	RubroIf rubroIf = null;
	Double total = null;
	Long contratId = null;
	String numeroCheque = null;
	Long cuentaBancariaId = null;
	Long rolPagoDetalleId = null;
	
	public Long getRolPagoDetalleId() {
		return rolPagoDetalleId;
	}

	public void setRolPagoDetalleId(Long rolPagoDetalleId) {
		this.rolPagoDetalleId = rolPagoDetalleId;
	}

	public DatoAsientoPagoRol() {
	}
	
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getContratId() {
		return contratId;
	}

	public void setContratId(Long contratId) {
		this.contratId = contratId;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Long getCuentaBancariaId() {
		return cuentaBancariaId;
	}

	public void setCuentaBancariaId(Long cuentaBancariaId) {
		this.cuentaBancariaId = cuentaBancariaId;
	}

	public Boolean getEventual() {
		return eventual;
	}

	public void setEventual(Boolean isEventual) {
		this.eventual = isEventual;
	}

	public String getNombreRubro() {
		return nombreRubro;
	}

	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}

	public RubroIf getRubroIf() {
		return rubroIf;
	}

	public void setRubroIf(RubroIf rubroIf) {
		this.rubroIf = rubroIf;
	}	
}
