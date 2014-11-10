package com.spirit.general.mdb.messages.bo;

import javax.ejb.Local;

import com.spirit.general.mdb.messages.object.MasterDetailObjectMessageLocal;

@Local
public interface StockMessageLocal extends MasterDetailObjectMessageLocal{

	public Long getIdBodega();

	public void setIdBodega(Long idBodega);
}
