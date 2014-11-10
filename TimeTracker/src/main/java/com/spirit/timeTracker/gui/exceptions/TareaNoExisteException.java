package com.spirit.timeTracker.gui.exceptions;

public class TareaNoExisteException extends Exception {

	private static final long serialVersionUID = -5506775781535885485L;

	public TareaNoExisteException(){
		super();
	}
	
	public TareaNoExisteException(String mensaje){
		super(mensaje);
	}
}
