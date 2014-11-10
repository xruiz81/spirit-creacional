package com.spirit.facturacion.gui.model;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;

import com.spirit.client.SpiritAlert;
import com.spirit.facturacion.entity.PedidoEnvioIf;
import com.spirit.facturacion.gui.panel.JDInformacionEnvio;
import com.spirit.facturacion.session.PedidoEnvioSessionService;
import com.spirit.servicelocator.ServiceLocator;

public class InformacionEnvioModel extends JDInformacionEnvio {

	private static final long serialVersionUID = -8784231601334209224L;

	private PedidoEnvioIf pedidoEnvioIf = null;
	private String nuevaLinea = "\n\n";
	
	public InformacionEnvioModel(Dialog owner,PedidoEnvioIf pedidoEnvioIf) {
		super(owner);
		this.pedidoEnvioIf = pedidoEnvioIf;
		obtenerInformacionTotal();
	}

	public InformacionEnvioModel(Frame owner,PedidoEnvioIf pedidoEnvioIf) {
		super(owner);
		this.pedidoEnvioIf = pedidoEnvioIf;
		obtenerInformacionTotal();
	}
	
	private void obtenerInformacionTotal() {
		llenarInformacionGeneral();
		llenarInformacionFacturacion();
		llenarInformacionEnvio();
		setVisual();
	}
	
	private void setVisual(){
		setTitle("Información de Envío");
		setLocation(
        		(Toolkit.getDefaultToolkit().getScreenSize().width/4), //- getWidth()) / 6, 
        		(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 6
        		);
   		pack();
		setModal(true);
		setVisible(true);
        repaint();
	}
	
	private void llenarInformacionGeneral(){
		getTxtNombresClientes().setText(pedidoEnvioIf.getNombresCliente()+" "+pedidoEnvioIf.getApellidosCliente());
		getTxtMetodoEnvio().setText(pedidoEnvioIf.getMetodoEnvio());
		getTxtCorreoCliente().setText(pedidoEnvioIf.getCorreoCliente());
	}
	
	private void llenarInformacionFacturacion(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(convertirNegrillas("Nombres     : ")+pedidoEnvioIf.getNombresFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Apellidos   : ")+pedidoEnvioIf.getApellidosFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Dirección   : ")+pedidoEnvioIf.getDireccionFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Ciudad      : ")+pedidoEnvioIf.getCiudadFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Región      : ")+pedidoEnvioIf.getRegionFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Zip         : ")+pedidoEnvioIf.getZipFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Teléfono    : ")+pedidoEnvioIf.getTelefonoFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Celular     : ")+pedidoEnvioIf.getCelularFacturacion()+nuevaLinea);
		sb.append(convertirNegrillas("Código País : ")+pedidoEnvioIf.getCodigoPaisFacturacion()+nuevaLinea);
		
		getTxtInformacionFacturacion().setText(sb.toString());
	}
	
	private void llenarInformacionEnvio(){
		StringBuilder sb = new StringBuilder();
		sb.append(convertirNegrillas("Nombres     : ")+pedidoEnvioIf.getNombresEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Apellidos   : ")+pedidoEnvioIf.getApellidosEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Dirección   : ")+pedidoEnvioIf.getDireccionEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Ciudad      : ")+pedidoEnvioIf.getCiudadEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Región      : ")+pedidoEnvioIf.getRegionEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Zip         : ")+pedidoEnvioIf.getZipEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Teléfono    : ")+pedidoEnvioIf.getTelefonoEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Celular     : ")+pedidoEnvioIf.getCelularEnvio()+nuevaLinea);
		sb.append(convertirNegrillas("Código País : ")+pedidoEnvioIf.getCodigoPaisEnvio()+nuevaLinea);
		
		getTxtInformacionEnvio().setText(sb.toString());
	}

	private String convertirNegrillas(String texto){
		return texto;
		//return "<html><b>"+texto+"</b></html>";
	}
	 
	
}
