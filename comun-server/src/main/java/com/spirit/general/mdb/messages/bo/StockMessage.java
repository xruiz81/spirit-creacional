package com.spirit.general.mdb.messages.bo;

import javax.ejb.Stateless;

import com.spirit.general.mdb.messages.object.MasterDetailObjectMessage;

@Stateless
public class StockMessage extends MasterDetailObjectMessage implements StockMessageLocal {
	Long idBodega;

	public Long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}
}
