package com.spirit.timeTracker.gui.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.status.StatusBar;

public abstract class JFTimeTracker extends JFrame {
	public JFTimeTracker() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		statusBar = new StatusBar();
		panel2 = new JPanel();
		panel3 = new JPanel();
		toolBar = new JToolBar();
		btnGuardar = new JButton();
		btnNuevaTarea = new JButton();
		btnDetenerTarea = new JButton();
		btnEliminarTarea = new JButton();
		btnIniciarTarea = new JButton();
		btnReporte = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(statusBar, BorderLayout.SOUTH);

		//======== panel2 ========
		{
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
			
			//======== panel3 ========
			{
				panel3.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(23)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					}));
				
				//======== toolBar ========
				{
					toolBar.setFloatable(true);
					toolBar.setRollover(true);
					toolBar.setMinimumSize(new Dimension(101, 0));
					toolBar.setPreferredSize(new Dimension(300, 35));
					toolBar.setMaximumSize(new Dimension(300, 32769));
					
					//---- btnGuardar ----
					btnGuardar.setText("G");
					toolBar.add(btnGuardar);
					toolBar.addSeparator();
					
					//---- btnNuevaTarea ----
					btnNuevaTarea.setText("NT");
					toolBar.add(btnNuevaTarea);
					
					//---- btnDetenerTarea ----
					btnDetenerTarea.setText("DT");
					toolBar.add(btnDetenerTarea);
					
					//---- btnEliminarTarea ----
					btnEliminarTarea.setText("ET");
					toolBar.add(btnEliminarTarea);
					
					//---- btnIniciarTarea ----
					btnIniciarTarea.setText("IT");
					toolBar.add(btnIniciarTarea);
					toolBar.addSeparator();

					//---- btnReporte ----
					//btnReporte.setText("RE");
					//toolBar.add(btnReporte);
				}
				panel3.add(toolBar, cc.xy(1, 1));
			}
			panel2.add(panel3);
		}
		contentPane.add(panel2, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private StatusBar statusBar;
	private JPanel panel2;
	private JPanel panel3;
	private JToolBar toolBar;
	private JButton btnGuardar;
	private JButton btnNuevaTarea;
	private JButton btnDetenerTarea;
	private JButton btnEliminarTarea;
	private JButton btnIniciarTarea;
	private JButton btnReporte;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public StatusBar getStatusBar() {
		return statusBar;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnNuevaTarea() {
		return btnNuevaTarea;
	}

	public JButton getBtnDetenerTarea() {
		return btnDetenerTarea;
	}

	public JButton getBtnEliminarTarea() {
		return btnEliminarTarea;
	}

	public JButton getBtnIniciarTarea() {
		return btnIniciarTarea;
	}
}
