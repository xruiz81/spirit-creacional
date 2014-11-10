package com.spirit.general.gui.controller;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import com.spirit.client.SpiritConstants;

public class MyCurrencyEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 8471495742827951825L;
	private NumberFormat currencyFormat;
	
	public MyCurrencyEditor(final JFormattedTextField tf, NumberFormat nf) {
		super(tf);
		currencyFormat = nf;
		tf.setFocusLostBehavior(JFormattedTextField.COMMIT);
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.setBorder(null);
		delegate = new EditorDelegate() {
			public void setValue(Object param) {
				Double _value = (Double)param;
				if (_value == null) {
					tf.setValue(currencyFormat.format(0.0));
				} else {
					double _d = _value.doubleValue();
					String _format = currencyFormat.format(_d);
					tf.setValue(Double.parseDouble(_format.replaceAll(SpiritConstants.getCommaCharacter(),SpiritConstants.getEmptyCharacter())));
				}
			}
			public Object getCellEditorValue() {
				try {
					String _field = tf.getText();
					Number _number = currencyFormat.parse(_field);
					double _parsed = _number.doubleValue();
					Double d = new Double(_parsed);
					return d;
				} catch (ParseException e) {
					e.printStackTrace();
					return new Double(0.0);
				}
			}
		};
	}
}
