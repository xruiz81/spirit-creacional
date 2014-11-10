package com.spirit.timeTracker.gui.main;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.timeTracker.componentes.PanelInformacion;

public abstract class JPInfoOrdenTrabajo extends PanelInformacion {
	public JPInfoOrdenTrabajo() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		lblCamapana = new JLabel();
		txtCampana = new JTextField();
		lblFechaEntrega = new JLabel();
		txtFechaEntrega = new JTextField();
		lblFechaLimite = new JLabel();
		txtFechaLimite = new JTextField();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(110), 1.0),
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(15), 0.0),
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.DEFAULT_COLSPEC,
				new ColumnSpec(Sizes.DLUX3),
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(110), 1.0),
				new ColumnSpec(Sizes.DLUX3),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo");
		add(lblCodigo, cc.xy(3, 3));

		//---- txtCodigo ----
		txtCodigo.setEditable(false);
		add(txtCodigo, cc.xy(5, 3));

		//---- lblEstado ----
		lblEstado.setText("Estado");
		add(lblEstado, cc.xy(9, 3));

		//---- txtEstado ----
		txtEstado.setEditable(false);
		add(txtEstado, cc.xy(11, 3));

		//---- lblCliente ----
		lblCliente.setText("Cliente");
		add(lblCliente, cc.xy(3, 5));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 5, 7, 1));

		//---- lblCamapana ----
		lblCamapana.setText("Campa\u00f1a");
		add(lblCamapana, cc.xy(3, 7));

		//---- txtCampana ----
		txtCampana.setEditable(false);
		add(txtCampana, cc.xywh(5, 7, 7, 1));

		//---- lblFechaEntrega ----
		lblFechaEntrega.setText("Fecha Entrega");
		add(lblFechaEntrega, cc.xy(3, 9));

		//---- txtFechaEntrega ----
		txtFechaEntrega.setEditable(false);
		add(txtFechaEntrega, cc.xy(5, 9));

		//---- lblFechaLimite ----
		lblFechaLimite.setText("Fecha L\u00edmite");
		add(lblFechaLimite, cc.xy(9, 9));

		//---- txtFechaLimite ----
		txtFechaLimite.setEditable(false);
		add(txtFechaLimite, cc.xy(11, 9));

		//---- lblDescripcion ----
		lblDescripcion.setText("Descripci\u00f3n");
		add(lblDescripcion, cc.xy(3, 11));

		//---- txtDescripcion ----
		txtDescripcion.setEditable(false);
		add(txtDescripcion, cc.xywh(5, 11, 7, 1));

		//---- lblObservacion ----
		lblObservacion.setText("Observaci\u00f3n");
		add(lblObservacion, cc.xy(3, 13));

		//---- txtObservacion ----
		txtObservacion.setEditable(false);
		add(txtObservacion, cc.xywh(5, 13, 7, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JLabel lblCamapana;
	private JTextField txtCampana;
	private JLabel lblFechaEntrega;
	private JTextField txtFechaEntrega;
	private JLabel lblFechaLimite;
	private JTextField txtFechaLimite;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCampana() {
		return txtCampana;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
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

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}
}
