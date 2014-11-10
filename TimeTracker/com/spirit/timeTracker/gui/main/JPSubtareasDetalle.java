package com.spirit.timeTracker.gui.main;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public abstract class JPSubtareasDetalle extends JPanel {
	public JPSubtareasDetalle() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		scrollPane1 = new JScrollPane();
		tblSubtareaDetalle = new JTable();

		//======== this ========
		setBackground(Color.white);
		setMinimumSize(new Dimension(300, 70));
		setBorder(null);
		setLayout(new CardLayout());

		//======== scrollPane1 ========
		{
			scrollPane1.setMinimumSize(new Dimension(290, 27));
			
			//---- tblSubtareaDetalle ----
			tblSubtareaDetalle.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha", "Hora Inicio", "Hora Fin", "Tiempo"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			scrollPane1.setViewportView(tblSubtareaDetalle);
		}
		add(scrollPane1, "card1");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JTable tblSubtareaDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTable getTblSubtareaDetalle() {
		return tblSubtareaDetalle;
	}
}
