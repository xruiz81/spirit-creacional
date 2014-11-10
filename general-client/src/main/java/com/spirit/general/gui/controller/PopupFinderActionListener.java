package com.spirit.general.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class PopupFinderActionListener implements ActionListener {

	JTextField txtField = null;
	
	public void actionPerformed(ActionEvent arg0) {
	}

	public JTextField getTxtField() {
		return txtField;
	}

	public void setTxtField(JTextField txtField) {
		this.txtField = txtField;
	}

}
