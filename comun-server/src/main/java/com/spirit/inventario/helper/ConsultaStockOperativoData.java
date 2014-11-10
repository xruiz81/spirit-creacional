package com.spirit.inventario.helper;

import java.math.BigDecimal;

public class ConsultaStockOperativoData extends StockOperativoDataModel {
	private Boolean selected=new Boolean(false);
	private BigDecimal stockActual;
	private BigDecimal promedioDiario;
	private BigDecimal rotacion;
	private BigDecimal cantidadSolicitada;
	private EnumSemaforo semaforoStock;
	private EnumSemaforo semaforoRotacion;

	public BigDecimal getStockActual() {
		return stockActual;
	}

	public void setStockActual(BigDecimal stockActual) {
		this.stockActual = stockActual;
	}

	public BigDecimal getPromedioDiario() {
		return promedioDiario;
	}

	public void setPromedioDiario(BigDecimal promedioDiario) {
		this.promedioDiario = promedioDiario;
	}

	public BigDecimal getRotacion() {
		return rotacion;
	}

	public void setRotacion(BigDecimal rotacion) {
		this.rotacion = rotacion;
	}

	public EnumSemaforo getSemaforoStock() {
		return semaforoStock;
	}

	public void setSemaforoStock(EnumSemaforo semaforoStock) {
		this.semaforoStock = semaforoStock;
	}

	public EnumSemaforo getSemaforoRotacion() {
		return semaforoRotacion;
	}

	public void setSemaforoRotacion(EnumSemaforo semaforoRotacion) {
		this.semaforoRotacion = semaforoRotacion;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public BigDecimal getCantidadSolicitada() {
		return cantidadSolicitada;
	}

	public void setCantidadSolicitada(BigDecimal cantidadSolicitada) {
		this.cantidadSolicitada = cantidadSolicitada;
	}

}
