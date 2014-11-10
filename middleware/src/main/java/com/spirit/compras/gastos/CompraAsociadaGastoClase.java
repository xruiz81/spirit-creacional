package com.spirit.compras.gastos;

import java.io.Serializable;
import java.util.ArrayList;

import com.spirit.compras.entity.CompraAsociadaGastoIf;
import com.spirit.compras.entity.CompraGastoIf;
import com.spirit.exception.GenericBusinessException;

public class CompraAsociadaGastoClase implements Serializable{
	
	private static final long serialVersionUID = -5707872268761328095L;

	ArrayList<CompraAsociadaGastoIf> detalle = null;

	public CompraAsociadaGastoClase() throws GenericBusinessException{
		this.detalle = new ArrayList<CompraAsociadaGastoIf>();
	}

	public ArrayList<CompraAsociadaGastoIf> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<CompraAsociadaGastoIf> detalle) {
		this.detalle = detalle;
	}

}