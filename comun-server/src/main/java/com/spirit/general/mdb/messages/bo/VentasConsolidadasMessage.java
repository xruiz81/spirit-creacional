package com.spirit.general.mdb.messages.bo;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class VentasConsolidadasMessage extends ObjectMessenger implements VentasConsolidadasMessageLocal{

	Object ventaConsolidada;

	public Object getVentaConsolidada() {
		return ventaConsolidada;
	}

	public void setVentaConsolidada(Object ventaConsolidada) {
		this.ventaConsolidada = ventaConsolidada;
	}


}
