package com.spirit.medios.gui.model;

import java.io.Serializable;

public class PresupuestosClientesData implements Serializable {

	private static final long serialVersionUID = -6950986268207010636L;
	
	private String codigo;
	private String fechaCreacion;
	private String estado;
	private String tipo;
	private String operador;	
	private String fechaFactura;
	private String preimpreso;
	private String valor;
	private String clienteId;	
	private String cantCotizadosCliente;
	private String cantAprobadosCliente;
	private String cantFacturadosCliente;
	private String cantTotalCliente;
	private String cantComprasCliente;
	private String totalCotizadoCliente;	
	private String totalAprobadoCliente;
	private String totalFacturadoCliente;
	private String totalPresupuestosCliente;
	private String totalComprasCliente;

	public PresupuestosClientesData(){
		codigo = "";
		fechaCreacion = "";
		estado = "";
		tipo = "";
		operador = "";
		fechaFactura = "";
		preimpreso = "";
		valor = "";
		clienteId = "";		
		cantCotizadosCliente = "";
		cantAprobadosCliente = "";
		cantFacturadosCliente = "";
		cantTotalCliente = "";
		cantComprasCliente = "";
		totalCotizadoCliente = "";	
		totalAprobadoCliente = "";
		totalFacturadoCliente = "";
		totalPresupuestosCliente = "";
		totalComprasCliente = "";		
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getPreimpreso() {
		return preimpreso;
	}

	public void setPreimpreso(String preimpreso) {
		this.preimpreso = preimpreso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getCantCotizadosCliente() {
		return cantCotizadosCliente;
	}

	public void setCantCotizadosCliente(String cantCotizadosCliente) {
		this.cantCotizadosCliente = cantCotizadosCliente;
	}

	public String getCantAprobadosCliente() {
		return cantAprobadosCliente;
	}

	public void setCantAprobadosCliente(String cantAprobadosCliente) {
		this.cantAprobadosCliente = cantAprobadosCliente;
	}

	public String getCantFacturadosCliente() {
		return cantFacturadosCliente;
	}

	public void setCantFacturadosCliente(String cantFacturadosCliente) {
		this.cantFacturadosCliente = cantFacturadosCliente;
	}

	public String getCantTotalCliente() {
		return cantTotalCliente;
	}

	public void setCantTotalCliente(String cantTotalCliente) {
		this.cantTotalCliente = cantTotalCliente;
	}

	public String getCantComprasCliente() {
		return cantComprasCliente;
	}

	public void setCantComprasCliente(String cantComprasCliente) {
		this.cantComprasCliente = cantComprasCliente;
	}

	public String getTotalCotizadoCliente() {
		return totalCotizadoCliente;
	}

	public void setTotalCotizadoCliente(String totalCotizadoCliente) {
		this.totalCotizadoCliente = totalCotizadoCliente;
	}

	public String getTotalAprobadoCliente() {
		return totalAprobadoCliente;
	}

	public void setTotalAprobadoCliente(String totalAprobadoCliente) {
		this.totalAprobadoCliente = totalAprobadoCliente;
	}

	public String getTotalFacturadoCliente() {
		return totalFacturadoCliente;
	}

	public void setTotalFacturadoCliente(String totalFacturadoCliente) {
		this.totalFacturadoCliente = totalFacturadoCliente;
	}

	public String getTotalPresupuestosCliente() {
		return totalPresupuestosCliente;
	}

	public void setTotalPresupuestosCliente(String totalPresupuestosCliente) {
		this.totalPresupuestosCliente = totalPresupuestosCliente;
	}

	public String getTotalComprasCliente() {
		return totalComprasCliente;
	}

	public void setTotalComprasCliente(String totalComprasCliente) {
		this.totalComprasCliente = totalComprasCliente;
	}
}
