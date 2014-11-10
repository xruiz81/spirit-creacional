package com.spirit.contabilidad.util;

import java.awt.Color;
import java.util.List;

import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.StyleModel;
import com.jidesoft.grid.TreeTableModel;

public class MyTreeTableModelPlanCuentas extends TreeTableModel implements StyleModel {
	 
	//Creacion de las columnas del TreeTable con sus respectivos nombres
	static final protected String[] COLUMN_NAMES = {"Cuenta Contable"};

	    public MyTreeTableModelPlanCuentas(List filas) {
	    	super(filas);
	    }
	    
	    public MyTreeTableModelPlanCuentas() {
	    	
	    }
	     
	    public String getColumnName(int column) {

	    	return COLUMN_NAMES[column];
	    }

	    public int getColumnCount() {
	    	
	    	return COLUMN_NAMES.length;
	    }

	    final static Color BACKGROUND = new Color(247, 247, 247);
	    final static CellStyle CELL_STYLE = new CellStyle();

	    static {
	        CELL_STYLE.setBackground(BACKGROUND);
	    }

	    public CellStyle getCellStyleAt(int rowIndex, int columnIndex) {
	    	
	    	//Seteo la alineacion a la izquierda de la columna 0 que es el arbol
	    	if (columnIndex == 0) {
	        	CELL_STYLE.setHorizontalAlignment(CellStyle.LEFT);
	        	return CELL_STYLE;
	        } else {
	            return null;
	        }
	    }

	    public boolean isCellStyleOn() {
	        return true;
	    }

	    public Class getColumnClass(int columnIndex) {
	        switch (columnIndex) {
	            case 0:
	                return MyRowTreeTableBalance.class;
	        }
	        return super.getColumnClass(columnIndex);
	    }
}
