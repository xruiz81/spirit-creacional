package com.spirit.facturacion.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public abstract class JDPreimpresoAnularFactura extends JDialog {
	public JDPreimpresoAnularFactura(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPreimpresoAnularFactura(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblPreimpreso = new JLabel();
		txtPreimpreso = new JTextField();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Ingreso de Preimpreso");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(false);
		setBackground(null);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblPreimpreso ----
		lblPreimpreso.setText("Preimpreso");
		contentPane.add(lblPreimpreso, cc.xywh(5, 3, 7, 1));
		contentPane.add(txtPreimpreso, cc.xywh(5, 5, 5, 1));

		//---- btnAceptar ----
		btnAceptar.setText("Aceptar");
		contentPane.add(btnAceptar, cc.xy(5, 7));

		//---- btnCancelar ----
		btnCancelar.setText("Cancelar");
		contentPane.add(btnCancelar, cc.xy(9, 7));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblPreimpreso;
	private JTextField txtPreimpreso;
	private JButton btnAceptar;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}

	public void setTxtPreimpreso(JTextField txtPreimpreso) {
		this.txtPreimpreso = txtPreimpreso;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}
