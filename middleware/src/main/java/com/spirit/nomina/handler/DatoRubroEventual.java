package com.spirit.nomina.handler;

import java.io.Serializable;

import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;

public class DatoRubroEventual implements Serializable {

	private static final long serialVersionUID = 1115923583400349068L;
	
	RubroEventualIf rubroEventualIf = null;
	RubroIf rubroIf = null;
	Double valor = 0D;
	
	public DatoRubroEventual() {
	}

	public RubroEventualIf getRubroEventualIf() {
		return rubroEventualIf;
	}

	public void setRubroEventualIf(RubroEventualIf rubroEventualIf) {
		this.rubroEventualIf = rubroEventualIf;
	}

	public RubroIf getRubroIf() {
		return rubroIf;
	}

	public void setRubroIf(RubroIf rubroIf) {
		this.rubroIf = rubroIf;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
}
