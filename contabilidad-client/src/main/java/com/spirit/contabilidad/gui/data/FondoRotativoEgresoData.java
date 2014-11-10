package com.spirit.contabilidad.gui.data;

import java.io.Serializable;

public class FondoRotativoEgresoData implements Serializable {

	String fecha = "";
	String detalle = "";
	String proveedor = "";
	String cheque = "";
	Double valor = 0D;
	Double valorFactura = 0D;
	String factura = "";
	
	public FondoRotativoEgresoData() {
		fecha = "";
		detalle = "";
		proveedor = "";
		cheque = "";
		valor = 0D;
		valorFactura = 0D;
		factura = "";
	}

	public Double getValorFactura() {
		return valorFactura;
	}

	public void setValorFactura(Double valorFactura) {
		this.valorFactura = valorFactura;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}
}
