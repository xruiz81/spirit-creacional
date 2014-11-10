package com.spirit.sri.gui.util;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.spirit.client.model.SpiritResourceManager;

public class TableCellRenderer extends DefaultTableCellRenderer {    

	private static final long serialVersionUID = 1L;

	ImageIcon iconoError = SpiritResourceManager.getImageIcon("images/icons/dialog/error_cell.png");
	ImageIcon iconoBien = SpiritResourceManager.getImageIcon("images/icons/dialog/good_cell.png");
	public TableCellRenderer(){
		setHorizontalAlignment(SwingConstants.CENTER);
	}

	protected void setValue(Object value) {
		if (value instanceof String){
			String valor = (String)value;
			if ( valor.equalsIgnoreCase("1") )
				setIcon(iconoBien);
			else if (valor.equalsIgnoreCase("0"))
				setIcon(iconoError);
			super.setValue("");

		} else{
			setIcon(null);
			super.setValue(value);
		}
	}
}
