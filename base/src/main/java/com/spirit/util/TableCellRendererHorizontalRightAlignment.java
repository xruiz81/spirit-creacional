package com.spirit.util;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRendererHorizontalRightAlignment extends DefaultTableCellRenderer {    
    protected void setValue(Object value) {
        setHorizontalAlignment(SwingConstants.RIGHT);
        super.setValue(value);
    }
}
