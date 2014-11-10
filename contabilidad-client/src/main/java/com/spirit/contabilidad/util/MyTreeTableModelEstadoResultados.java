package com.spirit.contabilidad.util;



import java.math.BigDecimal;
import java.util.List;

import com.jidesoft.grid.TreeTableModel;

public class MyTreeTableModelEstadoResultados extends TreeTableModel {
	final protected String[] COLUMN_NAMES = {""};
	
	public MyTreeTableModelEstadoResultados(List filas) {
		super(filas);
	}
	
	public MyTreeTableModelEstadoResultados() {
		
	}
	
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	public Class getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return MyRowTreeTableEstadoResultados.class;
		case 1:
			return BigDecimal.class;
		}
		return super.getColumnClass(columnIndex);
	}
}
