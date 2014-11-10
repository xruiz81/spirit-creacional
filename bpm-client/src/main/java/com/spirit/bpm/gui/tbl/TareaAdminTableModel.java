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
public class TareaAdminTableModel<T> extends ConsultaTableModel {

	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy hh:mm:ss");

	public TareaAdminTableModel() {
		super();
	}

	public void setColumns() {
		addColumn(new MyTableColumn("#", 5));
		MyTableColumn myTableColumnNombre = new MyTableColumn("Nombre", 300);
		myTableColumnNombre.setMinWidth(220);
		addColumn(myTableColumnNombre);

		MyTableColumn tableColumnEstado = new MyTableColumn("Estado");
		tableColumnEstado.setMinWidth(130);
		tableColumnEstado.setCellRenderer(new StateTableCellRender());
		addColumn(tableColumnEstado);

		MyTableColumn tableColumnUsuario = new MyTableColumn("Usuario");
		tableColumnUsuario.setMinWidth(110);
		tableColumnUsuario.setCellRenderer(new StateTableCellRender());
		addColumn(tableColumnUsuario);

		MyTableColumn tableColumnFesperada = new MyTableColumn("F.Esperada");
		tableColumnFesperada.setMinWidth(80);
		tableColumnFesperada.setCellRenderer(new FechaEspedaTableCellRender());

		MyTableColumn tableColumnFfin = new MyTableColumn("T. Ejecucion");
		tableColumnFfin.setMinWidth(150);

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
		case 1:
			return object.getNombre();
		case 2:
			return object.getEstado();
		case 3:
			return object.getAsignadaA() != null ? object.getAsignadaA()
					.getUsername() : "";
		case 4:
			return object;
		case 5:
			return object.getTiempoEjecucion();
		default:
			return null;
		}
	}

}
