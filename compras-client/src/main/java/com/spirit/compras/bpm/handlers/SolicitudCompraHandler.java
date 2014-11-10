package com.spirit.compras.bpm.handlers;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class SolicitudCompraHandler implements ActionHandler {

	private static final long serialVersionUID = 1L;
	
	int opcion=0;
	String mensajeHandler="Sin Mensaje";
	String nombreModelVentana="";
	String idUsuario="";
	
	private void lanzarModelVentana(){
		System.out.println("ProcesoCompra Handler, Model:"
				+nombreModelVentana+ " Mensaje: "
				+mensajeHandler+" Opcion: "+opcion);
	}

	public void execute(ExecutionContext executionContext) throws Exception {
		lanzarModelVentana();
	}

	public String getNombreModelVentana() {
		return nombreModelVentana;
	}

	public void setNombreModelVentana(String nombreModelVentana) {
		this.nombreModelVentana = nombreModelVentana;
	}

}
