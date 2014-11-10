package com.spirit.general.gui.controller;

import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MyCurrencyRenderer  extends DefaultTableCellRenderer {
	NumberFormat currencyFormat;
	public MyCurrencyRenderer(NumberFormat cf) {
		currencyFormat = cf;
	}
	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, 
			int row, int col) {
		String _formattedValue;
		Double _value = (Double)value;
		if (value == null) {
			_formattedValue = "Not Set";
		} else {
			_formattedValue = currencyFormat.format(_value);
		}
		JLabel testLabel = new JLabel(_formattedValue,
				SwingConstants.RIGHT);
		if (isSelected) {
			testLabel.setBackground(table.getSelectionBackground());
			testLabel.setOpaque(true);
			testLabel.setForeground(table.getSelectionForeground());
		}
		if (hasFocus) {
			testLabel.setForeground(table.getSelectionBackground());
			testLabel.setBackground(table.getSelectionForeground());
			testLabel.setOpaque(true);
		}
		return testLabel;
	}
}
