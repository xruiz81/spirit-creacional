package com.spirit.general.mdb.messages.bo;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.ObjectMessenger;

@Stateless
public class CierreStockMessage extends ObjectMessenger implements CierreStockMessageLocal {
	Long idBodega;

	public Long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}
}
