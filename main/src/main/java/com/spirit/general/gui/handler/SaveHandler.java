package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;

//TODO: Se debe validar de que si dan click en buscar y en guardar y no se tiene un panel, no 
//debe tomar accion.
public class SaveHandler implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		
		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		if (panel != null) {
			if (panel.getMode() == SpiritMode.UPDATE_MODE) {
				panel.update();
				return;
			}
			if (panel.getMode() == SpiritMode.SAVE_MODE) {
				panel.save();
				return;
			}
			if (panel.getMode() == SpiritMode.FIND_MODE) {
				return;
			}
			if ((panel.getMode() == SpiritMode.SAVE_MODE) && (panel.isEmpty())) {
				return;
			} else {
				panel.save();
				return;
			}
		}
		else
    		SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
	}

}
