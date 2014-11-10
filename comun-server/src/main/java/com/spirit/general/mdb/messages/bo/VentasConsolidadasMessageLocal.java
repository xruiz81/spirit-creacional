package com.spirit.general.mdb.messages.bo;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface VentasConsolidadasMessageLocal extends ObjectMessengerLocal {

	public void setVentaConsolidada(Object ventaConsolidada);

	public Object getVentaConsolidada();

}
