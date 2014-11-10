package com.spirit.medios.gui.reporteData;

import java.io.Serializable;

public class OverComisionReporteData implements Serializable {
	
	private String proveedor;
	private String anio;
	private String inversionDe;
	private String inversionA;
	private String porcentajeOver;
	private String objetivo;
	
	public OverComisionReporteData(){
		proveedor = "";
		anio = "";
		inversionDe = "";
		inversionA = "";
		porcentajeOver = "";
		objetivo = "";
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getInversionDe() {
		return inversionDe;
	}

	public void setInversionDe(String inversionDe) {
		this.inversionDe = inversionDe;
	}

	public String getInversionA() {
		return inversionA;
	}

	public void setInversionA(String inversionA) {
		this.inversionA = inversionA;
	}

	public String getPorcentajeOver() {
		return porcentajeOver;
	}

	public void setPorcentajeOver(String porcentajeOver) {
		this.porcentajeOver = porcentajeOver;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
}
