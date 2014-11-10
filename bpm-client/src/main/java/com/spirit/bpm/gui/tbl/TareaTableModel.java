/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.spirit.bpm.process.elements.Tarea;

/**
 * 
 * @author Administrador
 */
public class TareaTableModel<T> extends ConsultaTableModel {

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm:ss");

	public TareaTableModel() {
		super();
	}

	public void setColumns() {
		addColumn(new MyTableColumn("#", 5));
		/*MyTableColumn proceso=new MyTableColumn("Proceso", 150);
		proceso.setMinWidth(150);
		addColumn(proceso);*/
		MyTableColumn nombre=new MyTableColumn("Nombre", 150);
		nombre.setMinWidth(100);
		addColumn(nombre);
		addColumn(new MyTableColumn("Descripcion", 150));
		MyTableColumn tableColumnPrioridad = new MyTableColumn("Prioridad");
		tableColumnPrioridad.setMinWidth(80);
		tableColumnPrioridad.setCellRenderer(new StateTableCellRender());
		addColumn(tableColumnPrioridad);

		MyTableColumn tableColumnEstado = new MyTableColumn("Estado");
		tableColumnEstado.setMinWidth(100);
		tableColumnEstado.setCellRenderer(new StateTableCellRender());
		addColumn(tableColumnEstado);

		int longFecha = 120;
		MyTableColumn tableColumnFasignacion = new MyTableColumn("F.Asignacion");
		tableColumnFasignacion.setMinWidth(longFecha);

		MyTableColumn tableColumnFinicio = new MyTableColumn("F.Inicio");
		tableColumnFinicio.setMinWidth(longFecha);

		MyTableColumn tableColumnFesperada = new MyTableColumn("F.Esperada");
		tableColumnFesperada.setMinWidth(80);
		tableColumnFesperada.setCellRenderer(new FechaEspedaTableCellRender());

		MyTableColumn tableColumnFfin = new MyTableColumn("F.Fin");
		tableColumnFfin.setMinWidth(longFecha);

		addColumn(tableColumnFasignacion);
		addColumn(tableColumnFinicio);
		addColumn(tableColumnFesperada);
		addColumn(tableColumnFfin);

	}

	private String formatDate(Date fecha) {
		if (fecha != null) {
			return dateFormat.format(fecha);
		} else
			return "";
	}

	public Object getValue(Object dataRow, int row, int column) {
		Tarea object = (Tarea) dataRow;
		switch (column) {
		case 0:
			return row + 1;
		//case 1:
		//	return object.getProceso();
		case 1:
			return object.getNombre();
		case 2:
			return object.getDescripcion();
		case 3:
			return object.getPrioridad();
		case 4:
			return object.getEstado();
		case 5:
			return formatDate(object.getFechaAsignacion());
		case 6:
			return formatDate(object.getFechaInicio());
		case 7:
			// CASO ESPECIAL PARA DETERMINAR LA AYUDA VISUAL
			return object;
		case 8:
			return formatDate(object.getFechaFinalizacion());
		default:
			return null;
		}
	}

}
