package com.spirit.general.gui.main;
import java.awt.Color;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class SpiritBackground extends SpiritModelImpl {
	public SpiritBackground() {
		initComponents();
		setName("Background");
		this.setBackground(Color.WHITE);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblBackground = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"default:grow"));
		lblBackground.setIcon(SpiritResourceManager.getImageIcon("images/spirit_background.jpg"));
		add(lblBackground, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.CENTER));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblBackground;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
