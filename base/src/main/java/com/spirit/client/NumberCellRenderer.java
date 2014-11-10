package com.spirit.client;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class NumberCellRenderer extends  DefaultTableCellRenderer {

	private static final long serialVersionUID = 6166579243484731752L;
	
	public static int IZQUIERDA = 0;
	public static int CENTRO = 1;
	public static int DERECHA = 2;
	
	NumberFormat formato = null;
	
	public NumberCellRenderer(String patron, int alineacion) {
		formato = new DecimalFormat(patron);
		
		if ( alineacion ==  IZQUIERDA )
			setHorizontalAlignment(JLabel.LEFT);
		else if ( alineacion ==  CENTRO )
			setHorizontalAlignment(JLabel.CENTER);
		else if ( alineacion ==  DERECHA )
			setHorizontalAlignment(JLabel.RIGHT);
		setOpaque(true);
	}
	
	@Override
	protected void setValue(Object value) {
		Double valor = 0.0;
		if ( value instanceof BigDecimal )
			valor = ((BigDecimal)value).doubleValue();
		else if ( value instanceof Double )
			valor = ((Double)value);
		
		setText(formato.format(valor));
	}
	
}
