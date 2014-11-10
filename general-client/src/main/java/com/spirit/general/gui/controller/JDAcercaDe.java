package com.spirit.general.gui.controller;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JDAcercaDe extends JDialogModelImpl {
	public JDAcercaDe(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAcercaDe(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblLogo = new JLabel();
		lblAcercaDeLinea1 = new JLabel();
		lblAcercaDeLinea2 = new JLabel();
		lblAcercaDeLinea3 = new JLabel();
		lblEtiquetaVersion = new JLabel();
		lblAcercaDeLinea4 = new JLabel();
		lblVersion = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Acerca de Spirit");
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
				new ColumnSpec("max(default;20dlu):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(20)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(20))
			}));

		//---- lblLogo ----
		lblLogo.setText("LOGO");
		contentPane.add(lblLogo, cc.xywh(3, 3, 5, 4));

		//---- lblAcercaDeLinea1 ----
		lblAcercaDeLinea1.setText("Spirit Office System");
		lblAcercaDeLinea1.setFont(new Font("Verdana", Font.BOLD, 18));
		contentPane.add(lblAcercaDeLinea1, cc.xywh(11, 3, 2, 1));

		//---- lblAcercaDeLinea2 ----
		lblAcercaDeLinea2.setText("2007, Todos los derechos reservados");
		lblAcercaDeLinea2.setFont(new Font("Verdana", Font.BOLD, 12));
		contentPane.add(lblAcercaDeLinea2, cc.xywh(11, 5, 2, 1));

		//---- lblAcercaDeLinea3 ----
		lblAcercaDeLinea3.setText("VERSALITY");
		lblAcercaDeLinea3.setFont(new Font("Verdana", Font.BOLD, 12));
		contentPane.add(lblAcercaDeLinea3, cc.xy(11, 6));

		//---- lblEtiquetaVersion ----
		lblEtiquetaVersion.setText("VERSION:");
		lblEtiquetaVersion.setFont(new Font("Verdana", Font.BOLD, 12));
		contentPane.add(lblEtiquetaVersion, cc.xy(11, 8));

		//---- lblAcercaDeLinea4 ----
		lblAcercaDeLinea4.setText(", Integradores de Tecnolog\u00eda");
		lblAcercaDeLinea4.setFont(new Font("Verdana", Font.BOLD, 12));
		contentPane.add(lblAcercaDeLinea4, cc.xy(12, 6));

		//---- lblVersion ----
		lblVersion.setText(" ");
		contentPane.add(lblVersion, cc.xy(12, 8));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblLogo;
	private JLabel lblAcercaDeLinea1;
	private JLabel lblAcercaDeLinea2;
	private JLabel lblAcercaDeLinea3;
	private JLabel lblEtiquetaVersion;
	private JLabel lblAcercaDeLinea4;
	private JLabel lblVersion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLblVersion() {
		return lblVersion;
	}

	public JLabel getLblLogo() {
		return lblLogo;
	}
}
