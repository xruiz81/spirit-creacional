package com.spirit.nomina.gui.util;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.spirit.general.entity.TipoPagoIf;

public class TipoPagoCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -5562781699698331239L;

	JComboBox combo;
	Object valor = null;
	
	public TipoPagoCellEditor( JComboBox combo ) {
		this.combo = combo;
	}
	
	public Object getCellEditorValue() {
		valor = combo.getSelectedItem();
		return valor;
	}

	public Component getTableCellEditorComponent(JTable table,
			Object value, boolean isSelected, int row, int column) {
		
		if ( value instanceof TipoPagoIf )
			combo.setSelectedItem(value);
		else 
			combo.setSelectedItem(null);
		return combo;
	}
	
	
}
