package com.spirit.bpm.gui.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import com.spirit.bpm.process.elements.Tarea;
import com.spirit.bpm.process.elemets.state.StateIf;
import com.spirit.client.model.SpiritResourceManager;

public class IconStateManager {
	public static SimpleDateFormat dateFormatFechaEsperada = new SimpleDateFormat(
			"dd/MM/yyyy");

	public static SimpleDateFormat dateFormatFechaHora = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm");

	public static void setText(JLabel label, Object text) {
		if (text != null) {
			if (text instanceof String) {
				label.setText((String) text);
			} else if (text instanceof Date) {
				label.setText(dateFormatFechaHora.format((Date) text));
			} else {
				label.setText(text.toString());
			}
		} else {
			label.setText("");
		}
	}

	public static void setLabel(JLabel label, Object o) {
		if (o == null) {
			label.setText("");
			label.setIcon(null);
			label.setToolTipText(null);
		} else if (o instanceof StateIf) {
			setLabel(label, (StateIf) o);
		}
	}

	public static void fechaEsperada(JLabel label, Tarea tarea) {
		Date fechaEsperada = tarea.getFechaEsperadaFinalizacion();
		if (fechaEsperada == null) {
			label.setText("-");
			label.setIcon(null);
			label.setToolTipText(null);
			return;
		}

		label.setToolTipText(tarea.getObservacionFechaVencida());
		String icono = tarea.getNivelVencido().getIcono();
		label.setText(dateFormatFechaEsperada.format(fechaEsperada));
		if (icono != null)
			label.setIcon(SpiritResourceManager.getImageIcon(icono));

	}

	private static void setLabel(JLabel label, StateIf stateIf) {
		label.setIcon(stateIf.getIcono() != null ? SpiritResourceManager
				.getImageIcon(stateIf.getIcono()) : null);
		label.setText(stateIf.getDescripcion() != null ? stateIf
				.getDescripcion() : stateIf.name());
	}

}
