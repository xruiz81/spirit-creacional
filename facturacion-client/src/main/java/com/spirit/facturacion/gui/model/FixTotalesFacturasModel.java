package com.spirit.facturacion.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.HotKeyComponent;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.gui.panel.JPFixTotalesFacturas;
import com.spirit.general.entity.UsuarioIf;

public class FixTotalesFacturasModel extends JPFixTotalesFacturas {
	private static final long serialVersionUID = -5282288192989951641L;

	public FixTotalesFacturasModel(){
		showSaveMode();		
		initListeners();
		new HotKeyComponent(this);
	}
	
	private void initListeners() {
		getBtnContinuar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				try {
					SessionServiceLocator.getFacturaSessionService().procesarFixTotalesFacturas();
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
