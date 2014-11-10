package com.spirit.timeTracker.gui.main;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPInfoSubtarea extends SpiritModelImpl {
	public JPInfoSubtarea() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblId = new JLabel();
		txtId = new JTextField();
		btnGuardar = new JButton();
		lblFechaInicio = new JLabel();
		txtFechaInicio = new JTextField();
		lblFechaFin = new JLabel();
		txtFechaFin = new JTextField();
		label1 = new JLabel();
		cmbUsrAsignado = new JComboBox();
		lblDescipcion = new JLabel();
		scrollPane1 = new JScrollPane();
		txtDescripcion = new JTextArea();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(80)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
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
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblId ----
		lblId.setText("Id:");
		add(lblId, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtId ----
		txtId.setEditable(false);
		add(txtId, cc.xy(5, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("F. Inicio:");
		add(lblFechaInicio, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtFechaInicio ----
		txtFechaInicio.setEditable(false);
		add(txtFechaInicio, cc.xywh(11, 3, 3, 1));

		//---- lblFechaFin ----
		lblFechaFin.setText("F. Fin:");
		add(lblFechaFin, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtFechaFin ----
		txtFechaFin.setEditable(false);
		add(txtFechaFin, cc.xywh(19, 3, 3, 1));

		//---- label1 ----
		label1.setText("Asignar a:");
		add(label1, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbUsrAsignado, cc.xywh(5, 5, 9, 1));

		//---- btnGuardar ----
		btnGuardar.setText("G");
		add(btnGuardar, cc.xy(17, 5));

		//---- lblDescipcion ----
		lblDescipcion.setText("Descripci\u00f3n");
		add(lblDescipcion, cc.xy(3, 7));

		//======== scrollPane1 ========
		{

			//---- txtDescripcion ----
			txtDescripcion.setWrapStyleWord(false);
			txtDescripcion.setLineWrap(true);
			txtDescripcion.setRows(5);
			scrollPane1.setViewportView(txtDescripcion);
		}
		add(scrollPane1, cc.xywh(3, 9, 23, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnGuardar;
	private JLabel lblFechaInicio;
	private JTextField txtFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txtFechaFin;
	private JLabel label1;
	private JComboBox cmbUsrAsignado;
	public JComboBox getCmbUsrAsignado() {
		return cmbUsrAsignado;
	}

	private JLabel lblDescipcion;
	private JScrollPane scrollPane1;
	private JTextArea txtDescripcion;

	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public JTextField getTxtFechaFin() {
		return txtFechaFin;
	}

	public JTextField getTxtFechaInicio() {
		return txtFechaInicio;
	}
}
