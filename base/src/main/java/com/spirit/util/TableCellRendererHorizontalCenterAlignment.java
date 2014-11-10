package com.spirit.util;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererHorizontalCenterAlignment extends DefaultTableCellRenderer {    
	protected void setValue(Object value) {
		setHorizontalAlignment(SwingConstants.CENTER);
		super.setValue(value);
	}
}
