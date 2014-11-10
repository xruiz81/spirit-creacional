package com.spirit.util;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class SpiritNumberTextFieldDecimal extends JFormattedTextField {

	// Formato de visualización
	NumberFormat dispFormat = NumberFormat.getCurrencyInstance();
	// Formato de edición: inglés (separador decimal: el punto)
	NumberFormat editFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);

	public SpiritNumberTextFieldDecimal() {
		// Para la edición, queremos separadores de millares
		editFormat.setGroupingUsed(true);
		// Creamos los formateadores de números
		NumberFormatter dnFormat = new NumberFormatter(dispFormat);
		NumberFormatter enFormat = new NumberFormatter(editFormat);
		// Creamos la factoría de formateadores especificando los
		// formateadores por defecto, de visualización y de edición
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(dnFormat, dnFormat, enFormat);
		enFormat.setCommitsOnValidEdit(true);
		// El formateador de edición admite caracteres incorrectos
		enFormat.setAllowsInvalid(true);
		// Asignamos la factoría al campo
		this.setFormatterFactory(currFactory);
	}
}
