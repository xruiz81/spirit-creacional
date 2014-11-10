package com.spirit.contabilidad.gui.data;

import java.io.Serializable;

public class FondoRotativoFacturaPendienteData implements Serializable {
	
	String fechaComprobante = "";
	String codigoComprobante = "";
	String detalleComprobante = "";
	String numeroCheque = "";
	Double valorComprobante = 0D;
	String detalleFactura = "";
	Double valorFactura = 0D;
	Boolean imprimirCabecera = false;
	String mesPresupuesto = "";
	
	/*String codigoComprobanteFacturaPendiente = "";
	String detalleComprobanteFacturaPendiente = "";
	Double valorComprobanteFacturaPendiente = 0D;
	String detalleFacturaPendiente = "";
	Double valorFacturaPendiente = 0D;*/
	
	public String getMesPresupuesto() {
		return mesPresupuesto;
	}

	public void setMesPresupuesto(String mesPresupuesto) {
		this.mesPresupuesto = mesPresupuesto;
	}

	public Boolean getImprimirCabecera() {
		return imprimirCabecera;
	}

	public void setImprimirCabecera(Boolean imprimirCabecera) {
		this.imprimirCabecera = imprimirCabecera;
	}

	public FondoRotativoFacturaPendienteData() {
	}

	public String getFechaComprobante() {
		return fechaComprobante;
	}

	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

	public String getDetalleComprobante() {
		return detalleComprobante;
	}

	public void setDetalleComprobante(String detalleComprobante) {
		this.detalleComprobante = detalleComprobante;
	}

	public String getDetalleFactura() {
		return detalleFactura;
	}

	public void setDetalleFactura(String detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public String getCodigoComprobante() {
		return codigoComprobante;
	}

	public void setCodigoComprobante(String codigoComprobante) {
		this.codigoComprobante = codigoComprobante;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Double getValorComprobante() {
		return valorComprobante;
	}

	public void setValorComprobante(Double valorComprobante) {
		this.valorComprobante = valorComprobante;
	}

	public Double getValorFactura() {
		return valorFactura;
	}

	public void setValorFactura(Double valorFactura) {
		this.valorFactura = valorFactura;
	}
}
