package com.spirit.general.mdb.messages.object;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MasterDetailObjectMessageLocal extends ObjectMessengerLocal {

	public void setCabecera(Object param, String campoFK);

	public void addDetail(Object param);
	
	public void setDetalle(List listaDetalle);

	public String getCampoFK();
}
