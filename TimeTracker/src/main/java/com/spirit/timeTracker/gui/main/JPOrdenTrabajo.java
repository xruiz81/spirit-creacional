package com.spirit.timeTracker.gui.main;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public abstract class JPOrdenTrabajo extends JPanel {
	public JPOrdenTrabajo() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jScrollPane1 = new JScrollPane();
		tblProyectos = new JTable();

		//======== this ========
		setBackground(Color.white);
		setBorder(new TitledBorder(null, "Ordenes de Trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, Color.black));
		setLayout(new CardLayout());

		//======== jScrollPane1 ========
		{
			jScrollPane1.setBackground(Color.white);
			jScrollPane1.setBorder(null);

			//---- tblProyectos ----
			tblProyectos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Cliente", "Titulo", "Tiempo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			jScrollPane1.setViewportView(tblProyectos);
		}
		add(jScrollPane1, "card2");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane jScrollPane1;
	private JTable tblProyectos;
	
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JScrollPane getJScrollPane1() {
		return jScrollPane1;
	}

	public JTable getTblProyectos() {
		return tblProyectos;
	}
}
