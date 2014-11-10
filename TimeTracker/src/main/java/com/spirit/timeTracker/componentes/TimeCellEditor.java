package com.spirit.timeTracker.componentes;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import timeTracker.tiempo.Hora;
import timeTracker.tiempo.Proyecto;
import timeTracker.tiempo.SubTarea;
import timeTracker.tiempo.SubTareaDetalle;
import timeTracker.tiempo.Tarea;
import timeTracker.tiempo.Utiles;

import com.spirit.exception.GenericBusinessException;

public class TimeCellEditor extends DefaultCellEditor {

	private JFormattedTextField textField;

	private JTable tableref;

	public TimeCellEditor(JFormattedTextField textField,JTable tableref) {
		super(textField);		
		this.textField=textField;
		this.textField.setFormatterFactory(new DefaultFormatterFactory(createFormatter("##:##:##")));
		this.tableref = tableref;
	}

	private Long getTime(String value) {
		if (value == null || value.trim().equalsIgnoreCase(""))
			return 0l;
		String[] split = value.split(":");
		if (split.length <= 1)
			return Long.valueOf(split[0]);
		Long horas = getLongValue(split[0]) * 60 * 60;
		Long min = getLongValue(split[1]) * 60;
		Long sec = getLongValue(split[2]);
		return horas + min + sec;
	}

	private Long getLongValue(String value) {
		if (value == null || value.trim().equalsIgnoreCase(""))
			return 0l;
		else
			return Long.valueOf(value);
	}

	@Override
	public Object getCellEditorValue() {
		Long segundos = getTime(textField.getText());
		int selectedRow = tableref.getSelectedRow();
		if (selectedRow >= 0) {
			Hora horaInicio = (Hora) tableref.getValueAt(selectedRow, 1);
			String valueAnterior = horaInicio.toString();
			Long segundosAnterior = getTime(valueAnterior);

			Long segundosSuma = segundosAnterior + segundos;
			int horasNuevas = ((Long) (segundosSuma / (60 * 60))).intValue();
			int minutosNuevos = ((Long) ((segundosSuma / 60) - (horasNuevas * 60)))
					.intValue();
			int segundosNuevos = ((Long) ((segundosSuma)
					- (horasNuevas * 60 * 60) - (minutosNuevos * 60)))
					.intValue();
			Hora horaNueva = new Hora(horasNuevas, minutosNuevos,
					segundosNuevos);
			tableref.setValueAt(horaNueva, selectedRow, 2);

			SubTareaDetalle subTareaDetalle = com.spirit.timeTracker.gui.model.Utiles
					.getSubtareaDetalleActivas().get(selectedRow);
			SubTarea subtarea = null;
			System.out.println("TIEMPO ANT: " + subTareaDetalle.getSegundos());
			System.out.println("TIEMPO NUEVO: " + segundos);
			subTareaDetalle.setSegundos(segundos);
			subTareaDetalle.getTiempoParcialDetalle().setTiempo(segundos);
			try {
				subtarea = com.spirit.timeTracker.gui.model.Utiles
						.getSubTareaActivaGlobal();
				Tarea t = com.spirit.timeTracker.gui.model.Utiles
						.getTareaActivaGlobal();				
				//subtarea.setDetalle(com.spirit.timeTracker.gui.model.Utiles
				//		.getSubtareaDetalleActivas());
			} catch (GenericBusinessException e) {

				e.printStackTrace();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(subTareaDetalle.getTiempoParcialDetalle()
					.getFecha()));
			cal.set(Calendar.HOUR, horasNuevas);
			cal.set(Calendar.MINUTE, minutosNuevos);
			cal.set(Calendar.SECOND, segundosNuevos);
			actualizarTablaSubTarea(selectedRow, segundos);
			actualizarTablaTarea();
			actualizarTablaProyecto();
		}
		return segundos;
	}

	private void actualizarTablaSubTarea(int row, Long selectedRowValue) {
		Long segundosTotales = 0l;
		for (int i = 0; i < tableref.getRowCount(); i++) {
			if (i == row)
				segundosTotales += selectedRowValue;
			else
				segundosTotales += (Long) tableref.getValueAt(i, 3);
		}
		System.out.println("TOTAL: " + segundosTotales);
		JTable tablaSubtareas = com.spirit.timeTracker.gui.model.Utiles
				.getTablaSubTareasGlobal();
		int selectedRow = tablaSubtareas.getSelectedRow();
		tablaSubtareas.setValueAt(segundosTotales, selectedRow, 1);
		SubTarea subtarea;
		try {
			subtarea = com.spirit.timeTracker.gui.model.Utiles
					.getSubTareaActivaGlobal();
			subtarea.getTiempoParcial().setTiempo(segundosTotales);
			subtarea.setSegundos(segundosTotales);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actualizarTablaTarea() {

		JTable tablaSubtareas = com.spirit.timeTracker.gui.model.Utiles
				.getTablaSubTareasGlobal();
		Long segundosTotales = 0l;
		for (int i = 0; i < tablaSubtareas.getRowCount(); i++) {
			segundosTotales += (Long) tablaSubtareas.getValueAt(i, 1);
		}
		JTable tablatareas = com.spirit.timeTracker.gui.model.Utiles
				.getTablaTareasGlobal();
		int selectedRow = tablatareas.getSelectedRow();
		tablatareas.setValueAt(segundosTotales, selectedRow, 1);
		Tarea tarea;
		try {
			tarea = com.spirit.timeTracker.gui.model.Utiles
					.getTareaActivaGlobal();
			tarea.setSegundosTotales(segundosTotales);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actualizarTablaProyecto() {

		JTable tablatareas = com.spirit.timeTracker.gui.model.Utiles
				.getTablaTareasGlobal();
		Long segundosTotales = 0l;
		for (int i = 0; i < tablatareas.getRowCount(); i++) {
			segundosTotales += (Long) tablatareas.getValueAt(i, 1);
		}
		JTable tablaproyectos = com.spirit.timeTracker.gui.model.Utiles
				.getTablaProyectosGlobal();
		int selectedRow = tablaproyectos.getSelectedRow();
		tablaproyectos.setValueAt(segundosTotales, selectedRow, 3);
		Proyecto proyecto;
		try {
			proyecto = com.spirit.timeTracker.gui.model.Utiles
					.getProyectoActivoGlobal();
			proyecto.setSegundosTotales(segundosTotales);
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected MaskFormatter createFormatter(String s) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc) {
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		if (value != null) {
			Long segundos = Long.valueOf(value.toString());
			textField.setText(Utiles.getTiempoCompleto(segundos));

		}
		return textField;
	}

}
