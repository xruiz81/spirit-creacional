package com.spirit.general.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.spirit.client.Parametros;

public class CerrarSesionHandler implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		new JDCloseSystemModel(Parametros.getMainFrame());
	}
}
