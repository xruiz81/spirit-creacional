package com.spirit.timeTracker.gui.main;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public abstract class JDInformacion extends JDialog {
	public JDInformacion(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDInformacion(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Orden de Trabajo");
		setResizable(false);
		setModal(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"12dlu:grow",
			"fill:12dlu:grow"));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
