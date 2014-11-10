package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.ActivePanel;
import com.spirit.client.ChangeModeImpl;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;

public class FindHandler implements ActionListener {
		

	public void actionPerformed(ActionEvent e) {

		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		if (panel != null) {
			if (panel.getMode() == SpiritMode.SAVE_MODE) {
				panel.setMode(SpiritMode.FIND_MODE);
				panel.clean();
				new ChangeModeImpl().fireChangeModeEvent("MODO: FIND");
				panel.showFindMode();
				return;
			}

			if (panel.getMode() == SpiritMode.UPDATE_MODE) {
				panel.setMode(SpiritMode.FIND_MODE);
				panel.clean();
				new ChangeModeImpl().fireChangeModeEvent("MODO: FIND");
				panel.showFindMode();
				return;
			}
			/*if ((panel.getMode() == SpiritModel.FIND_MODE) && (panel.isEmpty())) {
				panel.setMode(SpiritModel.SAVE_MODE);
				panel.clean();
				new ChangeModeImpl().fireChangeModeEvent("MODO: SAVE");
				panel.showSaveMode();
				return;
			} */
			if(panel.getMode() == SpiritMode.FIND_MODE){
				panel.find();
				return;
			}
		}
		else
    		SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
	}
}
