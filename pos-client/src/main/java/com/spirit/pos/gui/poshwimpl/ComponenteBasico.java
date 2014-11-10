package com.spirit.pos.gui.poshwimpl;

import jpos.JposConst;
import jpos.events.DataEvent;
import jpos.events.DataListener;
import jpos.events.ErrorEvent;
import jpos.events.ErrorListener;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;

public abstract class ComponenteBasico implements StatusUpdateListener,DataListener
,ErrorListener {

	protected String nombreLogico = null;
	protected boolean activo = false;
	protected Integer estado = null;
	protected String mensajeGeneral = null;
	protected String mensajeEstado = "";
	protected String codigoError = "";
	protected String codigoErrorExtendido = "";
	
	public abstract void iniciar() throws Exception;
	
	public abstract void cerrar() throws Exception;
	
	//StatusUpdateListenet
	public void statusUpdateOccurred(StatusUpdateEvent e) {
		estado = Integer.valueOf(e.getStatus());
		mensajeEstado = "";
		switch (estado.intValue()) {
		case JposConst.JPOS_SUE_POWER_ONLINE:
			mensajeEstado += "Encendido";
			break;

		case JposConst.JPOS_SUE_POWER_OFF_OFFLINE:
			mensajeEstado += "";
			break;
		case JposConst.JPOS_SUE_POWER_OFFLINE:
			mensajeEstado += "Apagado";
			break;
		}
		System.out.println(mensajeEstado);
	}
	
	public void errorOccurred(ErrorEvent ee) {}
	
	public void dataOccurred(DataEvent arg0) {}
	
	
}
