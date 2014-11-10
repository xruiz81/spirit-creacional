package com.spirit.general.mdb.messages.object;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MasterDetailObjectMessage extends ObjectMessenger implements MasterDetailObjectMessageLocal {
	private String campoFK = "";
	private Object cabecera;

	public void setCabecera(Object param, String campoFK) {
		this.campoFK = campoFK;
		this.cabecera = param;
	}

	public Object getCabecera() {
		return this.cabecera;
	}
	
	public void setDetalle(List listaDetalle)
	{
		super.setObjectList(listaDetalle);
	}

	public void addDetail(Object param) {
		super.add(param);
	}

	public String getCampoFK() {
		return campoFK;
	}
	
	@Override
	public void clear() {
		super.clear();
		this.cabecera=null;
	}
}
