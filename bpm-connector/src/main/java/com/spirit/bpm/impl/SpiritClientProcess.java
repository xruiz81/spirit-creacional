/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.impl;

import java.io.Serializable;

import com.spirit.bpm.process.SpiritBPMConnectorIf;
import com.spirit.bpm.process.elements.BPMException;
import com.spirit.bpm.process.elements.ClientParams;
import com.spirit.bpm.process.elements.Tarea;

/**
 * 
 * @author Administrador
 */
public abstract class SpiritClientProcess extends SpiritProcesParams implements Serializable {

	private ClientParams clientParams;
	private SpiritBPMConnectorIf spiritBPMConnector;
	private ClientParams admin = new ClientParams("admin", "bpm");

	public ClientParams getUserParams() {
		return clientParams;
	}

	public void setUserParams(ClientParams clientParams) {
		this.clientParams = clientParams;
	}

	public abstract void openTarea(Tarea tarea) throws BPMException;

	public abstract void iniciarTarea(Tarea tarea) throws BPMException;
	
	public abstract void continuarTarea(Tarea tarea) throws BPMException;

	public SpiritBPMConnectorIf getSpiritBPMConnector() {
		return spiritBPMConnector;
	}

	public void setSpiritBPMConnector(SpiritBPMConnectorIf spiritBPMConnector) {
		this.spiritBPMConnector = spiritBPMConnector;
	}

	public ClientParams getAdmin() {
		return admin;
	}

	public abstract SpiritServerProcessIf getRemoteProcess();
	
	

}
