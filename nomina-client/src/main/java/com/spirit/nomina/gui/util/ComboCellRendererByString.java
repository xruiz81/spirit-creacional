package com.spirit.nomina.gui.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboCellRendererByString extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1343688000433092475L;
	String texto = null;
	
	public ComboCellRendererByString(String texto) {
		this.texto = texto;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		String tipoPagoNombre = (String) value;
		if ( tipoPagoNombre == null || "".equals(tipoPagoNombre.trim()) ){
			if ( this.texto != null )
				setText(this.texto);
			else 
				setText("");
		}
		else {
			setText(tipoPagoNombre);
		}
		return this;
	}

}
