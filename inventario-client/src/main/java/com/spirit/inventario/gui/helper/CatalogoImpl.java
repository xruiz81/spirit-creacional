package com.spirit.inventario.gui.helper;

public class CatalogoImpl {
	private String codigo;
	private String descripcion;

	public CatalogoImpl(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
