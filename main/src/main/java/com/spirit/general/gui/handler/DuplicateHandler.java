package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;

public class DuplicateHandler implements ActionListener {
	
	
	public void actionPerformed(ActionEvent e) {
		
		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		if (panel != null) {
			/*if (panel.getMode() == SpiritMode.SAVE_MODE) {
			 
			 return;
			 }
			 if (panel.getMode() == SpiritMode.UPDATE_MODE || panel.getMode() == SpiritMode.FIND_MODE) {
			 
			 return;
			 }*/
			panel.duplicate();
		}
		else
			SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
		
		//SpiritAlert.createAlert("Panel: "+panel, SpiritAlert.INFORMATION);
	}
}