package com.spirit.pos.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.pos.gui.panel.JPFixCaja;

public class FixCajaModel extends JPFixCaja {
	private static final long serialVersionUID = -5282288192989951641L;

	public FixCajaModel(){
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnContinuar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					SessionServiceLocator.getFacturaSessionService().procesarFixCaja();
					SpiritAlert.createAlert("Proceso realizado exitosamente.", SpiritAlert.INFORMATION);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error.", SpiritAlert.ERROR);
				}
			}
		});
	}
	
	public void save() {
		showSaveMode();
	}

	public void delete() {
		// TODO Auto-generated method stub		
	}

	public void update() {
		// TODO Auto-generated method stub		
	}

	public void showSaveMode() {
		setSaveMode();
	}
	
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void refresh() {
		clean();
	}
	
	public void clean() {
		// TODO Auto-generated method stub		
	}
}
