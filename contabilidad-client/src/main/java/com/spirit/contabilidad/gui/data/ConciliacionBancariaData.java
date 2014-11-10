package com.spirit.contabilidad.gui.data;

import java.io.Serializable;

public class ConciliacionBancariaData implements Serializable {
	private static final long serialVersionUID = -3926288023047205558L;
	private String detalle;
	private String codigo;
	private String cheque;
	private String valor;	 
	private Long transaccionId;
		
	public ConciliacionBancariaData() {
		detalle="";
		codigo="";
		cheque="";
		valor="";
		transaccionId = 0L;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCheque() {
		return cheque;
	}

	public void setCheque(String cheque) {
		this.cheque = cheque;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(Long transaccionId) {
		this.transaccionId = transaccionId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
}
