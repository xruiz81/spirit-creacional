package com.spirit.timeTracker.gui.exceptions;

public class TareaNoSeleccionadaException extends Exception {

	private static final long serialVersionUID = -5506775781535885485L;

	public TareaNoSeleccionadaException(){
		super();
	}
	
	public TareaNoSeleccionadaException(String mensaje){
		super(mensaje);
	}
}
