package com.spirit.nomina.handler;

import java.io.Serializable;

import com.spirit.client.SpiritConstants;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.nomina.entity.ContratoIf;

public class VentasHorasExtrasEmpleado implements Serializable {
	private static final long serialVersionUID = -2198138887606864718L;
	private ContratoIf contrato;
	private EmpleadoIf empleado;
	private double totalVentas;
	private double numeroHorasExtras50;
	private double numeroHorasExtras100;
	
	public VentasHorasExtrasEmpleado() {
		contrato = (ContratoIf) SpiritConstants.getNullValue();
		empleado = (EmpleadoIf) SpiritConstants.getNullValue();
		totalVentas = 0D;
		numeroHorasExtras50 = 0;
		numeroHorasExtras100 = 0;
	}

	public ContratoIf getContrato() {
		return contrato;
	}

	public void setContrato(ContratoIf contrato) {
		this.contrato = contrato;
	}

	public EmpleadoIf getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoIf empleado) {
		this.empleado = empleado;
	}

	public double getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(double totalVentas) {
		this.totalVentas = totalVentas;
	}

	public double getNumeroHorasExtras50() {
		return numeroHorasExtras50;
	}

	public void setNumeroHorasExtras50(double numeroHorasExtras50) {
		this.numeroHorasExtras50 = numeroHorasExtras50;
	}

	public double getNumeroHorasExtras100() {
		return numeroHorasExtras100;
	}

	public void setNumeroHorasExtras100(double numeroHorasExtras100) {
		this.numeroHorasExtras100 = numeroHorasExtras100;
	}
}
