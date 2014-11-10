package com.spirit.inventario.helper;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockOperativoDataModel implements Serializable{

	private Long bodegaId;
	private String bodega;
	private BigDecimal min;
	private BigDecimal max;
	private Long tiempoMinimoReposision;
	private Long productoId;
	private String producto;
	private Long loteId;

	
	public Long getBodegaId() {
		return bodegaId;
	}
	public void setBodegaId(Long bodegaId) {
		this.bodegaId = bodegaId;
	}
	public String getBodega() {
		return bodega;
	}
	public void setBodega(String bodega) {
		this.bodega = bodega;
	}
	public BigDecimal getMin() {
		return min;
	}
	public void setMin(BigDecimal min) {
		this.min = min;
	}
	public BigDecimal getMax() {
		return max;
	}
	public void setMax(BigDecimal max) {
		this.max = max;
	}
	public Long getTiempoMinimoReposision() {
		return tiempoMinimoReposision;
	}
	public void setTiempoMinimoReposision(Long tiempoMinimoReposision) {
		this.tiempoMinimoReposision = tiempoMinimoReposision;
	}
	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public Long getLoteId() {
		return loteId;
	}
	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}
}
