package com.spirit.timeTracker.gui.main;

import java.awt.*;

import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Mon Mar 19 11:25:00 COT 2012
 */


/**
 * @author xruiz
 */
public class JDCrearOrdenTrabajoGenerica extends JDialog {
	public JDCrearOrdenTrabajoGenerica(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDCrearOrdenTrabajoGenerica(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		dialogPane = new JPanel();
		contentPane = new JPanel();
		ldescripcion = new JLabel();
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		btnBuscarClienteOficina = new JButton();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblEjecutivo = new JLabel();
		cmbEjecutivo = new JComboBox();
		lblEquipo = new JLabel();
		cmbEquipo = new JComboBox();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		lblSubtipo = new JLabel();
		cmbSubtipo = new JComboBox();
		btnCrear = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Crear Orden de Trabajo Gen\u00e9rica");
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.DLUX3),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(68)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(25)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(40)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(80)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.DLUX3)
					},
					new RowSpec[] {
						new RowSpec(Sizes.DLUY3),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY3)
					}));

				//---- ldescripcion ----
				ldescripcion.setText("Por favor complete la siguiente informaci\u00f3n:");
				contentPane.add(ldescripcion, cc.xywh(3, 3, 13, 1));

				//---- lblClienteOficina ----
				lblClienteOficina.setText("Cliente Oficina:");
				contentPane.add(lblClienteOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtClienteOficina ----
				txtClienteOficina.setEditable(false);
				contentPane.add(txtClienteOficina, cc.xywh(5, 5, 7, 1));
				contentPane.add(btnBuscarClienteOficina, cc.xywh(13, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblDescripcion ----
				lblDescripcion.setText("Descripci\u00f3n:");
				contentPane.add(lblDescripcion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(txtDescripcion, cc.xywh(5, 7, 7, 1));

				//---- lblEjecutivo ----
				lblEjecutivo.setText("Ejecutivo:");
				contentPane.add(lblEjecutivo, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(cmbEjecutivo, cc.xywh(5, 9, 3, 1));

				//---- lblEquipo ----
				lblEquipo.setText("Equipo:");
				contentPane.add(lblEquipo, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(cmbEquipo, cc.xy(11, 9));

				//---- lblTipo ----
				lblTipo.setText("Tipo:");
				contentPane.add(lblTipo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(cmbTipo, cc.xywh(5, 11, 3, 1));

				//---- lblSubtipo ----
				lblSubtipo.setText("Subtipo:");
				contentPane.add(lblSubtipo, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				contentPane.add(cmbSubtipo, cc.xy(11, 11));

				//---- btnCrear ----
				btnCrear.setText("Crear Orden");
				contentPane.add(btnCrear, cc.xy(5, 13));

				//---- btnCancelar ----
				btnCancelar.setText("Cancelar");
				contentPane.add(btnCancelar, cc.xywh(7, 13, 3, 1));
			}
			dialogPane.add(contentPane, BorderLayout.CENTER);
		}
		contentPane2.add(dialogPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel ldescripcion;
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JButton btnBuscarClienteOficina;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblEjecutivo;
	private JComboBox cmbEjecutivo;
	private JLabel lblEquipo;
	private JComboBox cmbEquipo;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JLabel lblSubtipo;
	private JComboBox cmbSubtipo;
	private JButton btnCrear;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbEquipo() {
		return cmbEquipo;
	}

	public void setCmbEquipo(JComboBox cmbEquipo) {
		this.cmbEquipo = cmbEquipo;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}

	public JComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JLabel getLblSubtipo() {
		return lblSubtipo;
	}

	public void setLblSubtipo(JLabel lblSubtipo) {
		this.lblSubtipo = lblSubtipo;
	}

	public JComboBox getCmbSubtipo() {
		return cmbSubtipo;
	}

	public void setCmbSubtipo(JComboBox cmbSubtipo) {
		this.cmbSubtipo = cmbSubtipo;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public void setTxtClienteOficina(JTextField txtClienteOficina) {
		this.txtClienteOficina = txtClienteOficina;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(JButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public JButton getBtnBuscarClienteOficina() {
		return btnBuscarClienteOficina;
	}

	public void setBtnBuscarClienteOficina(JButton btnBuscarClienteOficina) {
		this.btnBuscarClienteOficina = btnBuscarClienteOficina;
	}

	public JComboBox getCmbEjecutivo() {
		return cmbEjecutivo;
	}

	public void setCmbEjecutivo(JComboBox cmbEjecutivo) {
		this.cmbEjecutivo = cmbEjecutivo;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}
