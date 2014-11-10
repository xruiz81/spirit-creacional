package com.spirit.nomina.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class DatoObservacion implements Serializable {

	private static final long serialVersionUID = 3287064407488984481L;
	
	String etiquetaTitulo = null;
	String contenidoTitulo = null;
	Collection<String> descripcionError = null;
	
	public DatoObservacion() {
		descripcionError = new ArrayList<String>();
	}

	public void agregarDescripcion(String descripcion){
		descripcionError.add(descripcion);
	}
	
	public String getContenidoTitulo() {
		return contenidoTitulo;
	}

	public void setContenidoTitulo(String contenidoTitulo) {
		this.contenidoTitulo = contenidoTitulo;
	}

	public Collection<String> getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(Collection<String> descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getEtiquetaTitulo() {
		return etiquetaTitulo;
	}

	public void setEtiquetaTitulo(String etiquetaTitulo) {
		this.etiquetaTitulo = etiquetaTitulo;
	}

}
