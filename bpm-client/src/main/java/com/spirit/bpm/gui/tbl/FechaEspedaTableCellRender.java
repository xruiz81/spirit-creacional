/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.spirit.bpm.gui.helper.IconStateManager;
import com.spirit.bpm.process.elements.Tarea;

/**
 * 
 * @author Administrador
 */
public class FechaEspedaTableCellRender extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		IconStateManager.fechaEsperada(label, (Tarea) value);
		return label;
	}

}
