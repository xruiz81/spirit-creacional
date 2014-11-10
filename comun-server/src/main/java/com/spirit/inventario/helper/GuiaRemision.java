package com.spirit.inventario.helper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GuiaRemision implements Serializable {
	Date fechaInicioTraslado;
	Date fechaFinTraslado;
	String puntoPartida;
	String destino;

	String comprobanteVenta;

	String destinatarioRucCi;
	String destinatarioRazonSocial;

	String transportistaRucCi;
	String transportistaRazonSocial;
	String transportistaTelefono;
	String transportistaPlacaVehiculo;

	List<GuiaRemisionDetalle> guiaRemisiondetalle;

	Object beanCollection;

	public Date getFechaInicioTraslado() {
		return fechaInicioTraslado;
	}

	public void setFechaInicioTraslado(Date fechaInicioTraslado) {
		this.fechaInicioTraslado = fechaInicioTraslado;
	}

	public Date getFechaFinTraslado() {
		return fechaFinTraslado;
	}

	public void setFechaFinTraslado(Date fechaFinTraslado) {
		this.fechaFinTraslado = fechaFinTraslado;
	}

	public String getPuntoPartida() {
		return puntoPartida;
	}

	public void setPuntoPartida(String puntoPartida) {
		this.puntoPartida = puntoPartida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getComprobanteVenta() {
		return comprobanteVenta;
	}

	public void setComprobanteVenta(String comprobanteVenta) {
		this.comprobanteVenta = comprobanteVenta;
	}

	public String getDestinatarioRucCi() {
		return destinatarioRucCi;
	}

	public void setDestinatarioRucCi(String destinatarioRucCi) {
		this.destinatarioRucCi = destinatarioRucCi;
	}

	public String getDestinatarioRazonSocial() {
		return destinatarioRazonSocial;
	}

	public void setDestinatarioRazonSocial(String destinatarioRazonSocial) {
		this.destinatarioRazonSocial = destinatarioRazonSocial;
	}

	public String getTransportistaRucCi() {
		return transportistaRucCi;
	}

	public void setTransportistaRucCi(String transportistaRucCi) {
		this.transportistaRucCi = transportistaRucCi;
	}

	public String getTransportistaRazonSocial() {
		return transportistaRazonSocial;
	}

	public void setTransportistaRazonSocial(String transportistaRazonSocial) {
		this.transportistaRazonSocial = transportistaRazonSocial;
	}

	public String getTransportistaTelefono() {
		return transportistaTelefono;
	}

	public void setTransportistaTelefono(String transportistaTelefono) {
		this.transportistaTelefono = transportistaTelefono;
	}

	public String getTransportistaPlacaVehiculo() {
		return transportistaPlacaVehiculo;
	}

	public void setTransportistaPlacaVehiculo(String transportistaPlacaVehiculo) {
		this.transportistaPlacaVehiculo = transportistaPlacaVehiculo;
	}

	public List<GuiaRemisionDetalle> getGuiaRemisiondetalle() {
		return guiaRemisiondetalle;
	}

	public void setGuiaRemisiondetalle(List<GuiaRemisionDetalle> guiaRemisiondetalle) {
		this.guiaRemisiondetalle = guiaRemisiondetalle;
	}

	public Object getBeanCollection() {
		return beanCollection;
	}

	public void setBeanCollection(Object beanCollection) {
		this.beanCollection = beanCollection;
	}
}