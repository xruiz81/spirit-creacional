/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.spirit.bpm.process.elements.InstanciaProceso;
import com.spirit.bpm.process.elements.Proceso;

/**
 * 
 * @author Administrador
 */
public class ProcesoTableModel<T> extends ConsultaTableModel<T> {
	private static final long serialVersionUID = 1L;

	public ProcesoTableModel() {
		super();
	}

	public void setColumns() {
		addColumn(new MyTableColumn("#", 5));
		MyTableColumn tableColumnNombre = new MyTableColumn("Nombre");
		tableColumnNombre.setMinWidth(220);
		addColumn(tableColumnNombre);

		MyTableColumn tableColumnVersion = new MyTableColumn("Version");
		tableColumnVersion.setMinWidth(100);
		tableColumnVersion.setCellRenderer(new StateTableCellRender());
		addColumn(tableColumnVersion);

		MyTableColumn tableColumnDescripcion = new MyTableColumn("Descripcion");
		tableColumnDescripcion.setMinWidth(150);
		addColumn(tableColumnDescripcion);

		MyTableColumn tableColumnInstanciasActivas = new MyTableColumn(
				"Instancias Activas");
		tableColumnInstanciasActivas.setMinWidth(10);
		addColumn(tableColumnInstanciasActivas);

	}

	public Object getValue(Object dataRow, int row, int column) {
		Proceso object = (Proceso) dataRow;
		switch (column) {
		case 0:
			return row + 1;
		case 1:
			return object.getNombre();
		case 2:
			return object.getDescripcion();
		case 3:
			return object.getVersion();
		case 4:
			return 1;
		default:
			return null;
		}
	}

}
