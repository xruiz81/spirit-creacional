package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.ActivePanel;
import com.spirit.client.ChangeModeImpl;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;

public class NewHandler implements ActionListener {
		

	public void actionPerformed(ActionEvent e) {

		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		if (panel != null) {
			if (panel.getMode() == SpiritMode.SAVE_MODE) {
				panel.clean();
				panel.showSaveMode();
				return;
			}
			if (panel.getMode() == SpiritMode.UPDATE_MODE || panel.getMode() == SpiritMode.FIND_MODE) {
				panel.setMode(SpiritMode.SAVE_MODE);
				panel.clean();
				panel.showSaveMode();
				new ChangeModeImpl().fireChangeModeEvent("MODO: SAVE");
				return;
			}
		}
		else
    		SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
	}
}