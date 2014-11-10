/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.spirit.bpm.process.elements.InstanciaProceso;

/**
 * 
 * @author Administrador
 */
public class InstanciaProcesoTableModel<T> extends ConsultaTableModel<T> {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm:ss");

	public InstanciaProcesoTableModel() {
		super();
	}

	public void setColumns() {
		addColumn(new MyTableColumn("#", 5));
		MyTableColumn tableColumnNombre=new MyTableColumn("Nombre");
		tableColumnNombre.setMinWidth(220);
		addColumn(tableColumnNombre);

		MyTableColumn tableColumnEstado = new MyTableColumn("Estado");
		tableColumnEstado.setMinWidth(100);
		tableColumnEstado.setCellRenderer(new StateTableCellRender());
		addColumn(tableColumnEstado);

		int longFecha = 120;

		MyTableColumn tableColumnFinicio = new MyTableColumn("F.Inicio");
		tableColumnFinicio.setMinWidth(longFecha);
		addColumn(tableColumnFinicio);
		MyTableColumn tableColumnFasignacion = new MyTableColumn(
				"F.Ultima Act.");
		tableColumnFasignacion.setMinWidth(longFecha);
		addColumn(tableColumnFasignacion);

		MyTableColumn tableColumnFfin = new MyTableColumn("F.Fin");
		tableColumnFfin.setMinWidth(longFecha);
		addColumn(tableColumnFfin);

	}

	private String formatDate(Date fecha) {
		if (fecha != null) {
			return dateFormat.format(fecha);
		} else
			return "";
	}

	public Object getValue(Object dataRow, int row, int column) {
		InstanciaProceso object = (InstanciaProceso) dataRow;
		switch (column) {
		case 0:
			return row + 1;
		case 1:
			return object.getProceso();
		case 2:
			return object.getEstado();
		case 3:
			return formatDate(object.getFechaIniciado());
		case 4:
			return formatDate(object.getFechaUltimaActualizacion());
		case 5:
			return formatDate(object.getFechaFinalizado());
		default:
			return null;
		}
	}

}
