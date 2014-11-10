package com.spirit.contabilidad.util;



import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.StyleModel;
import com.jidesoft.grid.TreeTableModel;

public class MyTreeTableModelFlujoEfectivo extends TreeTableModel implements StyleModel {
	 static final protected String[] COLUMN_NAMES = {"Nombre", "Valor"};

	    public MyTreeTableModelFlujoEfectivo(List filas) {
	    	super(filas);
	    }
	    
	    public MyTreeTableModelFlujoEfectivo() {
	    	
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
	        if (columnIndex == 0) {
	            return CELL_STYLE;
	        }
	        else {
	            return null;
	        }
	    }

	    public boolean isCellStyleOn() {
	        return true;
	    }

	    public Class getColumnClass(int columnIndex) {
	        switch (columnIndex) {
	            case 0:
	                return MyRowTreeTableFlujoEfectivo.class;
	            case 1:
	                return BigDecimal.class;
	        }
	        return super.getColumnClass(columnIndex);
	    }
}
