package com.spirit.timeTracker.gui.main;

import java.awt.Font;

import javax.swing.JButton;
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
import com.spirit.timeTracker.componentes.PanelInformacion;


public abstract class JPInfoTareaOrdenTrabajo extends PanelInformacion {
	public JPInfoTareaOrdenTrabajo() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblAsignado = new JLabel();
		txtAsignado = new JTextField();
		lblTipo = new JLabel();
		lblSubtipo = new JLabel();
		txtSubtipo = new JTextField();
		txtTipo = new JTextField();
		lblFechaEntrega = new JLabel();
		txtFechaEntrega = new JTextField();
		lblFechaLimite = new JLabel();
		txtFechaLimite = new JTextField();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		btnOrdenEntregada = new JButton();
		lblEquipo = new JLabel();
		txtEquipo = new JTextField();
		lblArchivoDescripcion = new JLabel();
		btnArchivoDescripcion = new JButton();
		txtArchivoDescripcion = new JTextField();
		lblPropuesta = new JLabel();
		txtPropuesta = new JTextField();
		btnPropuesta = new JButton();
		lblDescripcion = new JLabel();
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
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(15)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.dluY(43), FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblAsignado ----
		lblAsignado.setText("Asignado a:");
		add(lblAsignado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtAsignado ----
		txtAsignado.setEditable(false);
		add(txtAsignado, cc.xywh(5, 3, 11, 1));

		//---- lblTipo ----
		lblTipo.setText("Tipo:");
		add(lblTipo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblSubtipo ----
		lblSubtipo.setText("Subtipo:");
		add(lblSubtipo, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtSubtipo ----
		txtSubtipo.setEditable(false);
		add(txtSubtipo, cc.xywh(13, 5, 3, 1));

		//---- txtTipo ----
		txtTipo.setEditable(false);
		add(txtTipo, cc.xywh(5, 5, 3, 1));

		//---- lblFechaEntrega ----
		lblFechaEntrega.setText("Fecha Entrega:");
		add(lblFechaEntrega, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtFechaEntrega ----
		txtFechaEntrega.setEditable(false);
		add(txtFechaEntrega, cc.xywh(5, 7, 3, 1));

		//---- lblFechaLimite ----
		lblFechaLimite.setText("Fecha L\u00edmite:");
		add(lblFechaLimite, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtFechaLimite ----
		txtFechaLimite.setEditable(false);
		add(txtFechaLimite, cc.xywh(13, 7, 3, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEstado ----
		txtEstado.setEditable(false);
		add(txtEstado, cc.xy(5, 9));

		//---- btnOrdenEntregada ----
		btnOrdenEntregada.setText("Entregado");
		add(btnOrdenEntregada, cc.xy(7, 9));

		//---- lblEquipo ----
		lblEquipo.setText("Equipo:");
		add(lblEquipo, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtEquipo ----
		txtEquipo.setEditable(false);
		add(txtEquipo, cc.xywh(13, 9, 3, 1));

		//---- lblArchivoDescripcion ----
		lblArchivoDescripcion.setText("Descripci\u00f3n:");
		add(lblArchivoDescripcion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(btnArchivoDescripcion, cc.xywh(15, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtArchivoDescripcion ----
		txtArchivoDescripcion.setEditable(false);
		add(txtArchivoDescripcion, cc.xywh(5, 11, 9, 1));

		//---- lblPropuesta ----
		lblPropuesta.setText("Propuesta:");
		add(lblPropuesta, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtPropuesta ----
		txtPropuesta.setEditable(false);
		add(txtPropuesta, cc.xywh(5, 13, 9, 1));
		add(btnPropuesta, cc.xywh(15, 13, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n:");
		add(lblDescripcion, cc.xy(3, 15));

		//======== scrollPane1 ========
		{

			//---- txtDescripcion ----
			txtDescripcion.setEditable(false);
			txtDescripcion.setFont(new Font("Courier", Font.PLAIN, 13));
			txtDescripcion.setLineWrap(true);
			txtDescripcion.setRows(4);
			scrollPane1.setViewportView(txtDescripcion);
		}
		add(scrollPane1, cc.xywh(3, 17, 13, 4));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblAsignado;
	private JTextField txtAsignado;
	private JLabel lblTipo;
	private JLabel lblSubtipo;
	private JTextField txtSubtipo;
	private JTextField txtTipo;
	private JLabel lblFechaEntrega;
	private JTextField txtFechaEntrega;
	private JLabel lblFechaLimite;
	private JTextField txtFechaLimite;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JButton btnOrdenEntregada;
	private JLabel lblEquipo;
	private JTextField txtEquipo;
	private JLabel lblArchivoDescripcion;
	private JButton btnArchivoDescripcion;
	private JTextField txtArchivoDescripcion;
	private JLabel lblPropuesta;
	private JTextField txtPropuesta;
	private JButton btnPropuesta;
	private JLabel lblDescripcion;
	private JScrollPane scrollPane1;
	private JTextArea txtDescripcion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtAsignado() {
		return txtAsignado;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtEquipo() {
		return txtEquipo;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public JTextField getTxtFechaEntrega() {
		return txtFechaEntrega;
	}

	public JTextField getTxtFechaLimite() {
		return txtFechaLimite;
	}

	public JTextField getTxtSubtipo() {
		return txtSubtipo;
	}

	public JTextField getTxtTipo() {
		return txtTipo;
	}

	public JButton getBtnArchivoDescripcion() {
		return btnArchivoDescripcion;
	}

	public JButton getBtnPropuesta() {
		return btnPropuesta;
	}

	public JTextField getTxtArchivoDescripcion() {
		return txtArchivoDescripcion;
	}

	public JTextField getTxtPropuesta() {
		return txtPropuesta;
	}

	public JButton getBtnOrdenEntregada() {
		return btnOrdenEntregada;
	}

	public void setBtnOrdenEntregada(JButton btnOrdenEntregada) {
		this.btnOrdenEntregada = btnOrdenEntregada;
	}
}
