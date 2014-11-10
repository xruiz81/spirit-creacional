package com.spirit.util;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class SpiritNumberTextFieldDecimal extends JFormattedTextField {

	// Formato de visualizaci�n
	NumberFormat dispFormat = NumberFormat.getCurrencyInstance();
	// Formato de edici�n: ingl�s (separador decimal: el punto)
	NumberFormat editFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);

	public SpiritNumberTextFieldDecimal() {
		// Para la edici�n, queremos separadores de millares
		editFormat.setGroupingUsed(true);
		// Creamos los formateadores de n�meros
		NumberFormatter dnFormat = new NumberFormatter(dispFormat);
		NumberFormatter enFormat = new NumberFormatter(editFormat);
		// Creamos la factor�a de formateadores especificando los
		// formateadores por defecto, de visualizaci�n y de edici�n
		DefaultFormatterFactory currFactory = new DefaultFormatterFactory(dnFormat, dnFormat, enFormat);
		enFormat.setCommitsOnValidEdit(true);
		// El formateador de edici�n admite caracteres incorrectos
		enFormat.setAllowsInvalid(true);
		// Asignamos la factor�a al campo
		this.setFormatterFactory(currFactory);
	}
}
