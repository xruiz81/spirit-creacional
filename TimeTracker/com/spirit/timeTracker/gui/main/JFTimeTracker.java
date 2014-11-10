package com.spirit.timeTracker.gui.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.jidesoft.status.StatusBar;

public abstract class JFTimeTracker extends JFrame {
	public JFTimeTracker() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		statusBar = new StatusBar();
		panel1 = new JPanel();
		toolBar = new JToolBar();
		btnGuardar = new JButton();
		btnNuevaTarea = new JButton();
		btnDetenerTarea = new JButton();
		btnEliminarTarea = new JButton();
		btnIniciarTarea = new JButton();
		panel2 = new JPanel();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(statusBar, BorderLayout.SOUTH);

		//======== panel1 ========
		{
			panel1.setPreferredSize(new Dimension(300, 40));
			panel1.setMinimumSize(new Dimension(300, 40));
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
			
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
			}
			panel1.add(toolBar);
		}
		contentPane.add(panel1, BorderLayout.NORTH);

		//======== panel2 ========
		{
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		}
		contentPane.add(panel2, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private StatusBar statusBar;
	private JPanel panel1;
	private JToolBar toolBar;
	private JButton btnGuardar;
	private JButton btnNuevaTarea;
	private JButton btnDetenerTarea;
	private JButton btnEliminarTarea;
	private JButton btnIniciarTarea;
	private JPanel panel2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnDetenerTarea() {
		return btnDetenerTarea;
	}

	public JButton getBtnEliminarTarea() {
		return btnEliminarTarea;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnIniciarTarea() {
		return btnIniciarTarea;
	}

	public JButton getBtnNuevaTarea() {
		return btnNuevaTarea;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
}
