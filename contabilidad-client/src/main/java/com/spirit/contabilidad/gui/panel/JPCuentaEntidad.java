package com.spirit.contabilidad.gui.panel;

import java.awt.Font;

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

public abstract class JPCuentaEntidad extends SpiritModelImpl {
	public JPCuentaEntidad() {
		initComponents();
		setName("Cuentas por Entidad");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		spTblCuentaEntidad = new JScrollPane();
		tblCuentaEntidad = new JTable();
		cmbTipoEntidad = new JComboBox();
		lbTipoEntidad = new JLabel();
		lblEntidad = new JLabel();
		txtEntidad = new JTextField();
		btnBuscarEntidad = new JButton();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		cmbNemonico = new JComboBox();
		lblNemonico = new JLabel();
		lblCuenta = new JLabel();
		txtCuenta = new JTextField();
		btnBuscarCuenta = new JButton();
		lblOficina = new JLabel();
		cmbOficina = new JComboBox();
		panel1 = new JPanel();
		btnAgregarRegistro = new JButton();
		btnActualizarRegistro = new JButton();
		btnRemoverRegistro = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Cuentas por Entidad");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW),
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
				new RowSpec(RowSpec.TOP, Sizes.DLUY8, FormSpec.NO_GROW),
				new RowSpec(Sizes.dluY(10)),
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.DLUY5, FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spTblCuentaEntidad ========
		{

			//---- tblCuentaEntidad ----
			tblCuentaEntidad.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"C\u00f3digo", "Cuenta", "Nem\u00f3nico", "Oficina"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblCuentaEntidad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
			spTblCuentaEntidad.setViewportView(tblCuentaEntidad);
		}
		add(spTblCuentaEntidad, cc.xywh(3, 18, 11, 5));

		//---- cmbTipoEntidad ----
		cmbTipoEntidad.setModel(new DefaultComboBoxModel(new String[] {
			"CLIENTE",
			"PROVEEDOR",
			"EMPLEADO",
			"PRODUCTO",
			"DOCUMENTO",
			"CUENTA BANCARIA",
			"DEPARTAMENTO"
		}));
		add(cmbTipoEntidad, cc.xywh(5, 3, 3, 1));

		//---- lbTipoEntidad ----
		lbTipoEntidad.setText("Tipo de entidad:");
		add(lbTipoEntidad, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblEntidad ----
		lblEntidad.setText("Entidad:");
		add(lblEntidad, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtEntidad, cc.xywh(5, 5, 5, 1));

		//---- btnBuscarEntidad ----
		btnBuscarEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		add(btnBuscarEntidad, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de cuenta:");
		add(lblPlanCuenta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbPlanCuenta, cc.xywh(5, 7, 3, 1));
		add(cmbNemonico, cc.xywh(5, 9, 3, 1));

		//---- lblNemonico ----
		lblNemonico.setText("Nem\u00f3nico:");
		add(lblNemonico, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblCuenta ----
		lblCuenta.setText("Cuenta:");
		add(lblCuenta, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCuenta, cc.xywh(5, 11, 5, 1));
		add(btnBuscarCuenta, cc.xywh(11, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblOficina ----
		lblOficina.setText("Oficina:");
		add(lblOficina, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbOficina, cc.xywh(5, 13, 3, 1));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));

			//---- btnAgregarRegistro ----
			btnAgregarRegistro.setText("A");
			panel1.add(btnAgregarRegistro, cc.xy(1, 1));

			//---- btnActualizarRegistro ----
			btnActualizarRegistro.setText("U");
			panel1.add(btnActualizarRegistro, cc.xy(3, 1));

			//---- btnRemoverRegistro ----
			btnRemoverRegistro.setText("E");
			panel1.add(btnRemoverRegistro, cc.xy(5, 1));
		}
		add(panel1, cc.xywh(3, 16, 12, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spTblCuentaEntidad;
	private JTable tblCuentaEntidad;
	private JComboBox cmbTipoEntidad;
	private JLabel lbTipoEntidad;
	private JLabel lblEntidad;
	private JTextField txtEntidad;
	private JButton btnBuscarEntidad;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JComboBox cmbNemonico;
	private JLabel lblNemonico;
	private JLabel lblCuenta;
	private JTextField txtCuenta;
	private JButton btnBuscarCuenta;
	private JLabel lblOficina;
	private JComboBox cmbOficina;
	private JPanel panel1;
	private JButton btnAgregarRegistro;
	private JButton btnActualizarRegistro;
	private JButton btnRemoverRegistro;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public JScrollPane getSpTblCuentaEntidad() {
		return spTblCuentaEntidad;
	}

	public JTable getTblCuentaEntidad() {
		return tblCuentaEntidad;
	}

	public JComboBox getCmbTipoEntidad() {
		return cmbTipoEntidad;
	}

	public JLabel getLbTipoEntidad() {
		return lbTipoEntidad;
	}

	public JLabel getLblEntidad() {
		return lblEntidad;
	}

	public JTextField getTxtEntidad() {
		return txtEntidad;
	}

	public JButton getBtnBuscarEntidad() {
		return btnBuscarEntidad;
	}

	public JLabel getLblPlanCuenta() {
		return lblPlanCuenta;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public JComboBox getCmbNemonico() {
		return cmbNemonico;
	}

	public JLabel getLblNemonico() {
		return lblNemonico;
	}

	public JLabel getLblCuenta() {
		return lblCuenta;
	}

	public JTextField getTxtCuenta() {
		return txtCuenta;
	}

	public JButton getBtnBuscarCuenta() {
		return btnBuscarCuenta;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JButton getBtnAgregarRegistro() {
		return btnAgregarRegistro;
	}

	public JButton getBtnActualizarRegistro() {
		return btnActualizarRegistro;
	}

	public JButton getBtnRemoverRegistro() {
		return btnRemoverRegistro;
	}
}
