package com.spirit.contabilidad.util;



import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;

import com.jidesoft.grid.CellStyle;
import com.jidesoft.grid.StyleModel;

public class MyTreeTableModelEstadoResultadosAcumulado extends MyTreeTableModelEstadoResultados implements StyleModel {
	//Creacion de las columnas del TreeTable
	static final protected String[] COLUMN_NAMES = {"Cuenta", "Saldo", "Margen", "Porcentaje"};
	
	public MyTreeTableModelEstadoResultadosAcumulado(List filas) {
		super(filas);
	}
	
	public MyTreeTableModelEstadoResultadosAcumulado() {
		
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
			CELL_STYLE.setHorizontalAlignment(CellStyle.LEFT);
			//CELL_STYLE.setFontStyle(1);
			return CELL_STYLE;
		} else if(columnIndex >= 1 || columnIndex <= 3){
			CELL_STYLE.setHorizontalAlignment(CellStyle.RIGHT);
			/*if (columnIndex == 2)
			 CELL_STYLE.setFontStyle(1);
			 else
			 CELL_STYLE.setFontStyle(0);*/
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
			return MyRowTreeTableEstadoResultados.class;
		case 1:
			return BigDecimal.class;
		}
		return super.getColumnClass(columnIndex);
	}
}
