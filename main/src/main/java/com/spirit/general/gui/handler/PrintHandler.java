package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;

public class PrintHandler implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		final SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		
		if (panel != null) {
			Thread t = new Thread( new Espera(panel) );
			t.start();
			t = null;
			//panel.report();
			//return;
		} else
    		SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
	}
}