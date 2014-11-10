package com.spirit.timeTracker.gui.main;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public abstract class JPTareaOrdenTrabajo extends JPanel {
	public JPTareaOrdenTrabajo() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jScrollPane1 = new JScrollPane();
		tblTareas = new JTable();

		//======== this ========
		setBackground(Color.white);
		setBorder(new TitledBorder(null, "Tareas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.black));
		setLayout(new CardLayout());

		//======== jScrollPane1 ========
		{
			
			//---- tblTareas ----
			tblTareas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Tiempo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			jScrollPane1.setViewportView(tblTareas);
		}
		add(jScrollPane1, "card2");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane jScrollPane1;
	private JTable tblTareas;
	
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JScrollPane getJScrollPane1() {
		return jScrollPane1;
	}

	public JTable getTblTareas() {
		return tblTareas;
	}
}
