package com.spirit.inventario.gui.helper;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jidesoft.combobox.DateComboBox;
import com.spirit.util.ComboBoxComponent;

public class ValueHelper {
	private static Calendar calendar = new GregorianCalendar();

	public static void setValue(JTextField jTextField, Object value) {
		if (value != null) {
			jTextField.setText(value.toString());
		}
	}

	public static void setValue(DateComboBox jComboBox, Date value) {
		if (value == null)
			return;
		calendar.setTime((Date) value);
		jComboBox.setCalendar(calendar);
		jComboBox.repaint();
	}

	public static void setValue(JComboBox jComboBox, Object value) {
		if (value != null) {
			if (value instanceof Long) {
				jComboBox.setSelectedIndex(ComboBoxComponent
						.getIndexToSelectItem(jComboBox, (Long) value));
			} else if (value instanceof Date) {
				System.out.println("USAR DATECOMBOBOX");
			} else {
				jComboBox.setSelectedItem(value);
			}

			jComboBox.repaint();
		}
	}

	public static void fillCombo(JComboBox jComboBox, Object[] lista) {
		jComboBox.setModel(new DefaultComboBoxModel(lista));
	}

	public static BigDecimal convertValueToBigDecimal(JTextField txtField) {

		String value = txtField.getText();

		if (!"".equals(value.trim())) {
			try {
				return (new BigDecimal(value));
			} catch (java.lang.NumberFormatException nfe) {
				txtField.grabFocus();
				throw new java.lang.NumberFormatException(
						"El formato del valor ingresado no es correcto");
			}
		}
		return (new BigDecimal(0));
	}

	public static void limpiarTabla(JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		for (int i = modelo.getRowCount(); i > 0; i--) {
			modelo.removeRow(i - 1);
		}
	}

	public static void getValue(JTextField jTextField, Object value) {
		if (value != null)
			jTextField.setText(value.toString());
	}

	public static void getValue(JComboBox jComboBox, Object value) {
		if (value != null)
			jComboBox.setSelectedItem(value);
		else
			jComboBox.setSelectedIndex(-1);
	}

}
