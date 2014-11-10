package com.spirit.bpm.compras.handlers.actions;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class InicializarCompraIdealActionHandler implements ActionHandler{

	private static final long serialVersionUID = 1L;
	String autorizadorSolicitudCompra;
	String autorizadorCompra;
	String autorizadorOrdenCompra;
	
	public void execute(ExecutionContext executionContext) throws Exception {
		executionContext.setVariable("autorizadorSolicitudCompra", autorizadorSolicitudCompra);
		executionContext.setVariable("autorizadorCompra", autorizadorCompra);
		executionContext.setVariable("autorizadorOrdenCompra", autorizadorOrdenCompra);
	}
	
	
}
