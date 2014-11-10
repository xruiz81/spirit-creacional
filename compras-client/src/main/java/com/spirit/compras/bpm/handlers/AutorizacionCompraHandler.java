package com.spirit.compras.bpm.handlers;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class AutorizacionCompraHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	int opcion=0;
	String mensajeHandler="Sin Mensaje";
	String nombreModelVentana="";
	String idUsuario="";
	
	private void generarOrdenCompra(){
		System.out.println("Se genera orden compra...");
	}

	private void accionNoAutorizadaOrdenCompra(){
		System.out.println("No se autorizo orden de compra...");
	}
	
	public void execute(ExecutionContext executionContext) throws Exception {
		String aprovacion=(String)executionContext.getVariable("aprovacion");
		if ( "Aprovada".equalsIgnoreCase(aprovacion) )
			generarOrdenCompra();
		else if ( "Desaprovada".equalsIgnoreCase(aprovacion) )
			accionNoAutorizadaOrdenCompra();
		else
			System.out.println("Ninguna accion realizada");
	}

	public String getNombreModelVentana() {
		return nombreModelVentana;
	}

	public void setNombreModelVentana(String nombreModelVentana) {
		this.nombreModelVentana = nombreModelVentana;
	}

}
