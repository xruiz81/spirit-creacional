package com.spirit.compras.gui.model;

import java.io.Serializable;

public class ComprasIvaRetencionResumenData implements Serializable {
	private static final long serialVersionUID = 4503965903716429869L;
	
	private String impuesto;
	private String codigo;
	private String porcentaje;
	private String baseIva12;
	private String baseIva0;
	private String iva;
	private String valorRetenido;
	private String numReg;
		
	public ComprasIvaRetencionResumenData(){
		impuesto = "";
		codigo = "";
		porcentaje = "";
		baseIva12 = "";
		baseIva0 = "";
		iva = "";
		valorRetenido = "";		
		numReg = "";
	}

	public String getBaseIva0() {
		return baseIva0;
	}

	public void setBaseIva0(String baseIva0) {
		this.baseIva0 = baseIva0;
	}

	public String getBaseIva12() {
		return baseIva12;
	}

	public void setBaseIva12(String baseIva12) {
		this.baseIva12 = baseIva12;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getValorRetenido() {
		return valorRetenido;
	}

	public void setValorRetenido(String valorRetenido) {
		this.valorRetenido = valorRetenido;
	}

	public String getNumReg() {
		return numReg;
	}

	public void setNumReg(String numReg) {
		this.numReg = numReg;
	}
	
	
	
}
