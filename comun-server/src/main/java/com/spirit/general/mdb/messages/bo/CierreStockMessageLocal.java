package com.spirit.general.mdb.messages.bo;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.ObjectMessengerLocal;

@Local
public interface CierreStockMessageLocal extends ObjectMessengerLocal{

	public Long getIdBodega();

	public void setIdBodega(Long idBodega);
}
