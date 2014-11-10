package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;

public class FacturacionTipoProductoData implements Serializable {
	private static final long serialVersionUID = 7510008564237318952L;
	
	private String clienteId;
	private String cliente;
	private String ruc;
	private String television;
	private String prensa;
	private String radio;
	private String revista;
	private String pinterna;
	private String pexterna;
	private String vallas;
	private String fee;
	private String comisionDirecta;
	private String otros;
	private String total;
	
	public FacturacionTipoProductoData(){
		clienteId = "";
		cliente = "";
		ruc = "";
		television = "";
		prensa = "";
		radio = "";
		revista = "";
		pinterna = "";
		pexterna = "";
		vallas = "";
		fee = "";
		comisionDirecta = "";
		otros = "";
		total = "";
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getPrensa() {
		return prensa;
	}

	public void setPrensa(String prensa) {
		this.prensa = prensa;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getRevista() {
		return revista;
	}

	public void setRevista(String revista) {
		this.revista = revista;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelevision() {
		return television;
	}

	public void setTelevision(String television) {
		this.television = television;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPexterna() {
		return pexterna;
	}

	public void setPexterna(String pexterna) {
		this.pexterna = pexterna;
	}

	public String getPinterna() {
		return pinterna;
	}

	public void setPinterna(String pinterna) {
		this.pinterna = pinterna;
	}

	public String getVallas() {
		return vallas;
	}

	public void setVallas(String vallas) {
		this.vallas = vallas;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getComisionDirecta() {
		return comisionDirecta;
	}

	public void setComisionDirecta(String comisionDirecta) {
		this.comisionDirecta = comisionDirecta;
	}
}
