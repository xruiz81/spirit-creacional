package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.ActivePanel;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModel;

public class GenerateGraphicHandler implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		SpiritModel panel = (SpiritModel) ActivePanel.getSingleton().getActivePanel();
		
		if (panel != null)
			System.out.println("Se genera el gráfico");
		else
    		SpiritAlert.createAlert("No está activo ningún panel", SpiritAlert.WARNING);
	}

}