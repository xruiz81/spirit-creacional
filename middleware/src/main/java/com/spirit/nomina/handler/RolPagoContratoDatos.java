package com.spirit.nomina.handler;

import java.io.Serializable;
import java.util.Collection;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class RolPagoContratoDatos implements Serializable  {

	private static final long serialVersionUID = -8199469037459987155L;
	
	String nombreEmpleado = null;
	String cargoEmpleado = null;
	Long contratoId = null;
	Collection<RubroContratoDatos> ingresosRubro = null;
	Collection<RubroContratoDatos> egresosRubro = null;
	Double totalIngresos = 0.0;
	Double totalEgresos = 0.0;
	
	JRBeanCollectionDataSource ingresos = null;
	
	JRBeanCollectionDataSource egresos = null;
	
	public RolPagoContratoDatos() {
	}


	public String getCargoEmpleado() {
		return cargoEmpleado;
	}


	public void setCargoEmpleado(String cargoEmpleado) {
		this.cargoEmpleado = cargoEmpleado;
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


	public Collection<RubroContratoDatos> getIngresosRubro() {
		return ingresosRubro;
	}


	public void setIngresosRubro(Collection<RubroContratoDatos> ingresosRubro) {
		this.ingresosRubro = ingresosRubro;
	}


	public Collection<RubroContratoDatos> getEgresosRubro() {
		return egresosRubro;
	}


	public void setEgresosRubro(Collection<RubroContratoDatos> egresosRubro) {
		this.egresosRubro = egresosRubro;
	}


	public Double getTotalIngresos() {
		return totalIngresos;
	}


	public Double getTotalEgresos() {
		return totalEgresos;
	}


	public void setTotalIngresos(Double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}


	public void setTotalEgresos(Double totalEgresos) {
		this.totalEgresos = totalEgresos;
	}


	public JRBeanCollectionDataSource getIngresos() {
		return ingresos;
	}


	public void setIngresos(JRBeanCollectionDataSource ingresos) {
		this.ingresos = ingresos;
	}


	public JRBeanCollectionDataSource getEgresos() {
		return egresos;
	}


	public void setEgresos(JRBeanCollectionDataSource egresos) {
		this.egresos = egresos;
	}
	
	
	
}
