package com.spirit.inventario.helper;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockInventarioData implements Serializable{
	private Long idProducto;
	private Long idBodega;
	private Long idLote;
	private String bodega;
	private String producto;
	private String lote;
	private String mesAnio;
	private BigDecimal cantidad;
	private BigDecimal cantidadFisica;
	private String estado;

	public String getBodega() {
		return bodega;
	}

	public void setBodega(String bodega) {
		this.bodega = bodega;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getMesAnio() {
		return mesAnio;
	}

	public void setMesAnio(String mesAnio) {
		this.mesAnio = mesAnio;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getCantidadFisica() {
		return cantidadFisica;
	}

	public void setCantidadFisica(BigDecimal cantidadFisica) {
		this.cantidadFisica = cantidadFisica;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

}
