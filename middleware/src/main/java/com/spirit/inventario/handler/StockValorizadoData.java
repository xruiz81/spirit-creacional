package com.spirit.inventario.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.client.SpiritConstants;

public class StockValorizadoData implements Serializable {
	private static final long serialVersionUID = -5888152611808222054L;
	/* Fields */
	private String sku;
	private String modelo;
	private String presentacion;
	private String color;
	private BigDecimal stock;
	private BigDecimal promedioUnitario;
	private BigDecimal stockValorizado;
	
	public StockValorizadoData() {
		this.setSku(SpiritConstants.getEmptyCharacter());
		this.setModelo(SpiritConstants.getEmptyCharacter());
		this.setPresentacion(SpiritConstants.getEmptyCharacter());
		this.setColor(SpiritConstants.getEmptyCharacter());
		this.setStock(SpiritConstants.getZeroValue());
		this.setPromedioUnitario(SpiritConstants.getZeroValue());
		this.setStockValorizado(SpiritConstants.getZeroValue());
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getPromedioUnitario() {
		return promedioUnitario;
	}

	public void setPromedioUnitario(BigDecimal promedioUnitario) {
		this.promedioUnitario = promedioUnitario;
	}

	public BigDecimal getStockValorizado() {
		return stockValorizado;
	}

	public void setStockValorizado(BigDecimal stockValorizado) {
		this.stockValorizado = stockValorizado;
	}
}
