package com.spirit.pos.gui.poshwimpl;

import javax.swing.JLabel;

import jpos.CashDrawer;
import jpos.CashDrawerConst;
import jpos.JposConst;
import jpos.JposException;
import jpos.events.StatusUpdateEvent;

public class Cajon extends ComponenteBasico {

	private CashDrawer cashDrawer = null;
	private JLabel mensajeEtiqueta = null;
	
	public void statusUpdateOccurred(StatusUpdateEvent e) {
		super.statusUpdateOccurred(e);
		switch (estado.intValue()) {
		case CashDrawerConst.CASH_SUE_DRAWERCLOSED:
			mensajeEstado += "CAJA CERRADA";
			break;

		case CashDrawerConst.CASH_SUE_DRAWEROPEN:
			mensajeEstado += "CAJA ABIERTA";
			break;
		default:
			mensajeEstado += "";
		}

	}
	
	public Cajon(String nombreLogico) {
		this.nombreLogico = nombreLogico;
		cashDrawer = CajonSingleton.getInstance();
	}
	
	public void iniciar() throws Exception  {
		
		if ( cashDrawer != null  && !activo)
			try {
				cashDrawer.open(nombreLogico);
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception ("Error al abrir control de la Cajón !!\n"+e.getMessage());
			} 
			
			try {
				cashDrawer.claim(1000);
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception ("Error al obtener el control de la Cajón !!\n"+e.getMessage());
			}
			
			cashDrawer.addStatusUpdateListener(this);
			
			try {
				if (cashDrawer.getCapPowerReporting() != JposConst.JPOS_PR_NONE) {
					cashDrawer.setPowerNotify(JposConst.JPOS_PN_ENABLED);
				}
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al obtener Reporte de poder de la Cajón !!\n"+e.getMessage());
			}
			
			try {
				cashDrawer.setDeviceEnabled(true);
			} catch (JposException e) {
				e.printStackTrace();
				throw new Exception("Error al habilitar la Caja !!\n"+e.getMessage());
			}
			
			activo = true;
	}
	
	public void abrirCaja() throws Exception{
		mensajeGeneral="";
		if ( cashDrawer!= null && activo ){
			try {
				if ( !cashDrawer.getDrawerOpened() )
					cashDrawer.openDrawer();
				else {
					mensajeGeneral = "Caja ya esta abierta !!";
					if ( mensajeEtiqueta != null )
						mensajeEtiqueta.setText(mensajeGeneral);
					System.out.println(mensajeGeneral);
				}
			} catch (JposException e) {
				//e.printStackTrace();
				//throw new Exception("Error al abrir Cajón !!\n"+e.getMessage());
			}
				
		}
	}

	public void cerrar() throws Exception {
		if ( cashDrawer!= null && activo ){
			cashDrawer.removeStatusUpdateListener(this);
			try{
				cashDrawer.close();
			} catch(JposException e){
				e.printStackTrace();
				throw new Exception ("Error al cerrar control Caja !!\n"+e.getMessage());
			}
			activo = false;
		}
		
	}
	
	public void setMensajeEtiqueta(JLabel mensajeEtiqueta){
		this.mensajeEtiqueta = mensajeEtiqueta;
	}
	
	public void abrirCajaExpress() throws Exception{
		iniciar();
		abrirCaja();
		cerrar();
	}
	
	/*public static void main(String[] args) throws Exception {
		Cajon c =  new Cajon("CashDrawer1");
		c.iniciar();
		c.abrirCaja();
		c.cerrar();
		
		c.iniciar();
		c.abrirCaja();
		c.cerrar(); 
		
		Thread.sleep(10000);
		
		c.abrirCajaExpress();
		
		System.exit(0);
	}*/
}
