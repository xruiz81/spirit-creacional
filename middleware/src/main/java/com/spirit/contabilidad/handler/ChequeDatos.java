package com.spirit.contabilidad.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.spirit.contabilidad.entity.ChequeEmitidoIf;

public class ChequeDatos implements Serializable {

	private static final long serialVersionUID = -7830263706608034609L;
	
	Long id = null;
	ChequeEmitidoIf chequeEmitido = null;
	Set<Long> transaccionesIds = new HashSet<Long>(); 
	String origen = null;
	
	public ChequeDatos() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChequeEmitidoIf getChequeEmitido() {
		return chequeEmitido;
	}

	public void setChequeEmitido(ChequeEmitidoIf chequeEmitido) {
		this.chequeEmitido = chequeEmitido;
	}

	public Set<Long> getTransaccionesIds() {
		return transaccionesIds;
	}

	public void setTransaccionesIds(Set<Long> transaccionesIds) {
		this.transaccionesIds = transaccionesIds;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
}
