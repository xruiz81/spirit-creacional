/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spirit.bpm.gui.tbl;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.spirit.bpm.gui.helper.IconStateManager;

/**
 * 
 * @author Administrador
 */
public class StateTableCellRender extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label= (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		IconStateManager.setLabel(label, value);
		return label;
	}

}
