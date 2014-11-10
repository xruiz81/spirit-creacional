package com.spirit.timeTracker.gui.exceptions;

public class ProyectoNoSeleccionadoException extends Exception {

	private static final long serialVersionUID = -5506775781535885485L;

	public ProyectoNoSeleccionadoException(){
		super();
	}
	
	public ProyectoNoSeleccionadoException(String mensaje){
		super(mensaje);
	}
}
