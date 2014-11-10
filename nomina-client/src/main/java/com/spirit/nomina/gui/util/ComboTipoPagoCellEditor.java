package com.spirit.nomina.gui.util;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ComboTipoPagoCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -5562781699698331239L;

	JComboBox combo;
	Object valor = null;
	int indiceSeleccionado = 0;
	String nombreTipoPago = null;
	
	public ComboTipoPagoCellEditor( JComboBox combo, int indiceSeleccionado ) {
		this.combo = combo;
		this.indiceSeleccionado = indiceSeleccionado;
		combo.setSelectedIndex(indiceSeleccionado);
	}
	
	public ComboTipoPagoCellEditor( JComboBox combo, String nombreTipoPago ) {
		this.combo = combo;
		this.nombreTipoPago = nombreTipoPago;
		combo.setSelectedItem(nombreTipoPago);
	}
	
	public Object getCellEditorValue() {
		valor = combo.getSelectedItem();
		return valor;
	}

	public Component getTableCellEditorComponent(JTable table,
			Object value,boolean isSelected,int row,int column) {
		String nombreTipoPago = (String) value;
		if ( nombreTipoPago == null || "".equals(nombreTipoPago) ){
			if ( this.nombreTipoPago != null )
				combo.setSelectedItem(this.nombreTipoPago);
			else 
				combo.setSelectedItem(null);
		} else{
			combo.setSelectedItem(nombreTipoPago);
		}	
		return combo;
	}
}
