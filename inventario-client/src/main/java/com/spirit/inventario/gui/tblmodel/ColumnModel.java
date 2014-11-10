package com.spirit.inventario.gui.tblmodel;

public class ColumnModel {
	private String cabecera;
	private boolean editable;
	private int ancho;

	public ColumnModel(String cabecera, boolean editable, int ancho) {
		this.cabecera=cabecera;
		this.editable=editable;
		this.ancho=ancho;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
}
