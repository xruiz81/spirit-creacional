package com.spirit.timeTracker.gui.base;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritResourceManager;

public class JDCloseSystem extends JDialog {
	
	private static final long serialVersionUID = 8050745711167248023L;

	public JDCloseSystem(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDCloseSystem(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblLogo = new JLabel();
		lblAccion = new JLabel();
		btnCerrarSesion = new JButton();
		btnSalir = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Cerrar Aplicaci\u00f3n");
		setForeground(null);
		setBackground(Color.red);
		setFont(new Font("Arial", Font.BOLD, 12));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;30dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(20)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(20))
			}));

		//---- lblLogo ----
		lblLogo.setIcon(SpiritResourceManager.getImageIcon("images/icons/spirit.png"));
		contentPane.add(lblLogo, cc.xywh(3, 3, 5, 5));

		//---- lblAccion ----
		lblAccion.setText("\u00bfQu\u00e9 acci\u00f3n desea realizar?");
		lblAccion.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		contentPane.add(lblAccion, cc.xywh(11, 3, 3, 1));

		//---- btnCerrarSesion ----
		btnCerrarSesion.setIcon(SpiritResourceManager.getImageIcon("images/icons/dialog/closeSession.png"));
		btnCerrarSesion.setText("Cerrar Sesi\u00f3n");
		contentPane.add(btnCerrarSesion, cc.xy(11, 7));

		//---- btnSalir ----
		btnSalir.setIcon(SpiritResourceManager.getImageIcon("images/icons/dialog/exit.png"));
		btnSalir.setText("Salir del Sistema");
		contentPane.add(btnSalir, cc.xy(13, 7));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblLogo;
	private JLabel lblAccion;
	private JButton btnCerrarSesion;
	private JButton btnSalir;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	public void setBtnCerrarSesion(JButton btnCerrarSesion) {
		this.btnCerrarSesion = btnCerrarSesion;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
}
