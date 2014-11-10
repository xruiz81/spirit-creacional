package com.spirit.inventario.gui.helper;

import javax.swing.JTextField;

public class QueryHelper {

	private static String getValue(JTextField jTextField) {
		String valor = jTextField.getText();
		if (valor != null) {
			if (valor.trim().equalsIgnoreCase(""))
				return null;
			else
				return valor;
		} else
			return null;
	}

	public static String getParameter(JTextField jTextField) {
		String valor = getValue(jTextField);
		if (valor != null)
			return "%" + valor;
		else
			return "%";
	}
}
