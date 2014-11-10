package com.spirit.medios.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPComercial extends SpiritModelImpl {
	public JPComercial() {
		initComponents();
		setName("Versiones");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCodigoComercial = new JLabel();
		txtCodigoComercial = new JTextField();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		lblCampanaComercial = new JLabel();
		txtCampanaComercial = new JTextField();
		btnBuscarCampana = new JButton();
		lblComercial = new JLabel();
		cmbCampanaProductoVersion = new JComboBox();
		lblVersionComercial = new JLabel();
		txtIdentificacionComercial = new JTextField();
		lblProductoComercial = new JLabel();
		txtProductoComercial = new JTextField();
		lblTiempo = new JLabel();
		txtTiempo = new JTextField();
		lblSeg = new JLabel();
		lblDerechoProgramaComercial = new JLabel();
		cmbDerechoProgramaComercial = new JComboBox();
		panel2 = new JPanel();
		btnAgregarComercialCliente = new JButton();
		btnActualizarComercialCliente = new JButton();
		btnEliminarComercialCliente = new JButton();
		spComercialClente = new JScrollPane();
		tblComercialCliente = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(72)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigoComercial ----
		lblCodigoComercial.setText("C\u00f3digo:");
		add(lblCodigoComercial, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCodigoComercial, cc.xy(5, 3));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"ACTIVO",
			"INACTIVO"
		}));
		add(cmbEstado, cc.xy(19, 3));

		//---- lblCorporacion ----
		lblCorporacion.setText("Corporaci\u00f3n:");
		add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCorporacion ----
		txtCorporacion.setEditable(false);
		add(txtCorporacion, cc.xywh(5, 5, 7, 1));
		add(btnBuscarCorporacion, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(true);
		add(txtCliente, cc.xywh(5, 7, 7, 1));
		add(btnBuscarCliente, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblCampanaComercial ----
		lblCampanaComercial.setText("Campa\u00f1a:");
		add(lblCampanaComercial, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCampanaComercial, cc.xywh(5, 9, 7, 1));
		add(btnBuscarCampana, cc.xywh(13, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblComercial ----
		lblComercial.setText("Comercial:");
		add(lblComercial, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCampanaProductoVersion, cc.xywh(5, 11, 7, 1));

		//---- lblVersionComercial ----
		lblVersionComercial.setText("Identificaci\u00f3n:");
		add(lblVersionComercial, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtIdentificacionComercial, cc.xy(19, 11));

		//---- lblProductoComercial ----
		lblProductoComercial.setText("Producto Comercial:");
		lblProductoComercial.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblProductoComercial, cc.xy(3, 13));

		//---- txtProductoComercial ----
		txtProductoComercial.setEditable(false);
		add(txtProductoComercial, cc.xywh(5, 13, 7, 1));

		//---- lblTiempo ----
		lblTiempo.setText("Tiempo:");
		add(lblTiempo, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtTiempo, cc.xy(19, 13));

		//---- lblSeg ----
		lblSeg.setText("(seg.)");
		add(lblSeg, cc.xy(21, 13));

		//---- lblDerechoProgramaComercial ----
		lblDerechoProgramaComercial.setText("Derecho de Programa:");
		add(lblDerechoProgramaComercial, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDerechoProgramaComercial, cc.xywh(5, 15, 7, 1));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnAgregarComercialCliente ----
			btnAgregarComercialCliente.setText("A");
			panel2.add(btnAgregarComercialCliente, cc.xy(1, 1));

			//---- btnActualizarComercialCliente ----
			btnActualizarComercialCliente.setText("U");
			panel2.add(btnActualizarComercialCliente, cc.xy(3, 1));

			//---- btnEliminarComercialCliente ----
			btnEliminarComercialCliente.setText("E");
			panel2.add(btnEliminarComercialCliente, cc.xy(5, 1));
		}
		add(panel2, cc.xywh(3, 19, 9, 1));

		//======== spComercialClente ========
		{
			spComercialClente.setPreferredSize(new Dimension(452, 100));

			//---- tblComercialCliente ----
			tblComercialCliente.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null},
				},
				new String[] {
					"C\u00f3digo", "Campa\u00f1a", "Comercial", "Derecho de Programa", "Identificaci\u00f3n", "(seg.)"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblComercialCliente.setPreferredScrollableViewportSize(new Dimension(450, 100));
			tblComercialCliente.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tblComercialCliente.setAutoCreateColumnsFromModel(true);
			spComercialClente.setViewportView(tblComercialCliente);
		}
		add(spComercialClente, cc.xywh(3, 21, 21, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCodigoComercial;
	private JTextField txtCodigoComercial;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JLabel lblCampanaComercial;
	private JTextField txtCampanaComercial;
	private JButton btnBuscarCampana;
	private JLabel lblComercial;
	private JComboBox cmbCampanaProductoVersion;
	private JLabel lblVersionComercial;
	private JTextField txtIdentificacionComercial;
	private JLabel lblProductoComercial;
	private JTextField txtProductoComercial;
	private JLabel lblTiempo;
	private JTextField txtTiempo;
	private JLabel lblSeg;
	private JLabel lblDerechoProgramaComercial;
	private JComboBox cmbDerechoProgramaComercial;
	private JPanel panel2;
	private JButton btnAgregarComercialCliente;
	private JButton btnActualizarComercialCliente;
	private JButton btnEliminarComercialCliente;
	private JScrollPane spComercialClente;
	private JTable tblComercialCliente;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	
	public JButton getBtnActualizarComercialCliente() {
		return btnActualizarComercialCliente;
	}

	public void setBtnActualizarComercialCliente(
			JButton btnActualizarComercialCliente) {
		this.btnActualizarComercialCliente = btnActualizarComercialCliente;
	}

	public JButton getBtnAgregarComercialCliente() {
		return btnAgregarComercialCliente;
	}

	public void setBtnAgregarComercialCliente(JButton btnAgregarComercialCliente) {
		this.btnAgregarComercialCliente = btnAgregarComercialCliente;
	}

	public JButton getBtnBuscarCampana() {
		return btnBuscarCampana;
	}

	public void setBtnBuscarCampana(JButton btnBuscarCampanaComercial) {
		this.btnBuscarCampana = btnBuscarCampanaComercial;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JButton getBtnEliminarComercialCliente() {
		return btnEliminarComercialCliente;
	}

	public void setBtnEliminarComercialCliente(JButton btnEliminarComercialCliente) {
		this.btnEliminarComercialCliente = btnEliminarComercialCliente;
	}

	public JComboBox getCmbDerechoProgramaComercial() {
		return cmbDerechoProgramaComercial;
	}

	public void setCmbDerechoProgramaComercial(JComboBox cmbDerechoProgramaComercial) {
		this.cmbDerechoProgramaComercial = cmbDerechoProgramaComercial;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JScrollPane getSpComercialClente() {
		return spComercialClente;
	}

	public void setSpComercialClente(JScrollPane spComercialClente) {
		this.spComercialClente = spComercialClente;
	}

	public JTable getTblComercialCliente() {
		return tblComercialCliente;
	}

	public void setTblComercialCliente(JTable tblComercialCliente) {
		this.tblComercialCliente = tblComercialCliente;
	}

	public JTextField getTxtCampanaComercial() {
		return txtCampanaComercial;
	}

	public void setTxtCampanaComercial(JTextField txtCampanaComercial) {
		this.txtCampanaComercial = txtCampanaComercial;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtCodigoComercial() {
		return txtCodigoComercial;
	}

	public void setTxtCodigoComercial(JTextField txtCodigoComercial) {
		this.txtCodigoComercial = txtCodigoComercial;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtTiempo() {
		return txtTiempo;
	}

	public void setTxtTiempo(JTextField txtTiempo) {
		this.txtTiempo = txtTiempo;
	}

	public JComboBox getCmbCampanaProductoVersion() {
		return cmbCampanaProductoVersion;
	}

	public void setCmbCampanaProductoVersion(JComboBox cmbCampanaProductoVersion) {
		this.cmbCampanaProductoVersion = cmbCampanaProductoVersion;
	}

	public JTextField getTxtProductoComercial() {
		return txtProductoComercial;
	}

	public void setTxtProductoComercial(JTextField txtProductoComercial) {
		this.txtProductoComercial = txtProductoComercial;
	}

	public JTextField getTxtIdentificacionComercial() {
		return txtIdentificacionComercial;
	}

	public void setTxtIdentificacionComercial(JTextField txtIdentificacionComercial) {
		this.txtIdentificacionComercial = txtIdentificacionComercial;
	}

	
}
