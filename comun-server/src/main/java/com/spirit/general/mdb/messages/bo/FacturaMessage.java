package com.spirit.general.mdb.messages.bo;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.MasterDetailObjectMessage;

@Stateless
public class FacturaMessage extends MasterDetailObjectMessage implements
		FacturaMessageLocal {

	private Long idEmpresa;
	private Object factura;
	private Long idOficina;
	private Object usuario;
	private Object cliente;
	private Object cienteOficina;

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Object getFactura() {
		return factura;
	}

	public void setFactura(Object factura) {
		this.factura = factura;
	}

	public Long getIdOficina() {
		return idOficina;
	}

	public void setIdOficina(Long idOficina) {
		this.idOficina = idOficina;
	}

	public Object getUsuario() {
		return usuario;
	}

	public void setUsuario(Object usuario) {
		this.usuario = usuario;
	}

	public Object getCliente() {
		return cliente;
	}

	public void setCliente(Object cliente) {
		this.cliente = cliente;
	}

	public Object getCienteOficina() {
		return cienteOficina;
	}

	public void setCienteOficina(Object cienteOficina) {
		this.cienteOficina = cienteOficina;
	}

}
