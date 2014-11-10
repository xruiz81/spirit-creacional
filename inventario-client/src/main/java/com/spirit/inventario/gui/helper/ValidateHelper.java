package com.spirit.inventario.gui.helper;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.SpiritAlert;

public class ValidateHelper {

	
	public static boolean validateCmboBox(DateComboBox jComboBox) {
		if ((("".equals(jComboBox.getSelectedItem())) || (jComboBox
				.getSelectedItem() == null))) {
			jComboBox.grabFocus();
			return false;
		}
		return true;
	}
	public static boolean validateCmboBox(JComboBox jComboBox) {
		if ((("".equals(jComboBox.getSelectedItem())) || (jComboBox
				.getSelectedItem() == null))) {
			jComboBox.grabFocus();
			return false;
		}
		return true;
	}
	
	public static boolean validateTextField(JTextField jTextField) {
		String valor=jTextField.getText();
		if (valor==null || valor.trim().equalsIgnoreCase("")) {
			jTextField.grabFocus();
			return false;
		}
		return true;
	}
}
