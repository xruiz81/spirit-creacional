package com.spirit.contabilidad.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPChequesEmitidos extends SpiritModelImpl {
	public JPChequesEmitidos() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblFechaInicio = new JLabel();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		cmbFechaInicio = new DateComboBox();
		lblCuentaBancaria = new JLabel();
		cmbCuentaBancaria = new JComboBox();
		txtBanco = new JTextField();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		btnSeleccionarTodos = new JButton();
		btnConsultar = new JButton();
		lblFechaCobro = new JLabel();
		cmbFechaCobro = new DateComboBox();
		btnFechaCobro = new JButton();
		spTblChequesEmitidos = new JScrollPane();
		tblChequesEmitidos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Cheques Emitidos");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.DLUY8, FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaFin, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbFechaInicio, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCuentaBancaria ----
		lblCuentaBancaria.setText("Cuenta Bancaria:");
		add(lblCuentaBancaria, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCuentaBancaria, cc.xywh(5, 5, 5, 1));
		add(txtBanco, cc.xy(11, 5));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xy(15, 5));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODOS",
			"EMITIDO",
			"COBRADO",
			"ANULADO"
		}));
		add(cmbEstado, cc.xy(17, 5));

		//---- btnSeleccionarTodos ----
		btnSeleccionarTodos.setText("Seleccionar Todos");
		add(btnSeleccionarTodos, cc.xywh(3, 7, 3, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(21, 5));

		//---- lblFechaCobro ----
		lblFechaCobro.setText("Fecha Cobro:");
		add(lblFechaCobro, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaCobro, cc.xy(11, 7));
		add(btnFechaCobro, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== spTblChequesEmitidos ========
		{
			
			//---- tblChequesEmitidos ----
			tblChequesEmitidos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null, null, Boolean.FALSE},
				},
				new String[] {
					"Cobrado", "Fecha Emisi\u00f3n", "Cuenta Bancaria", "N\u00famero", "Valor", "Estado", "Fecha Cobro", "Beneficiario", "Detalle", "Origen", "Emitido"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, false, false, false, false, false, true
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblChequesEmitidos.setViewportView(tblChequesEmitidos);
		}
		add(spTblChequesEmitidos, cc.xywh(3, 9, 21, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private DateComboBox cmbFechaInicio;
	private JLabel lblCuentaBancaria;
	private JComboBox cmbCuentaBancaria;
	private JTextField txtBanco;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JButton btnSeleccionarTodos;
	private JButton btnConsultar;
	private JLabel lblFechaCobro;
	private DateComboBox cmbFechaCobro;
	private JButton btnFechaCobro;
	private JScrollPane spTblChequesEmitidos;
	private JTable tblChequesEmitidos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblCuentaBancaria() {
		return lblCuentaBancaria;
	}

	public JComboBox getCmbCuentaBancaria() {
		return cmbCuentaBancaria;
	}

	public JTextField getTxtBanco() {
		return txtBanco;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JButton getBtnSeleccionarTodos() {
		return btnSeleccionarTodos;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JLabel getLblFechaCobro() {
		return lblFechaCobro;
	}

	public DateComboBox getCmbFechaCobro() {
		return cmbFechaCobro;
	}

	public JButton getBtnFechaCobro() {
		return btnFechaCobro;
	}

	public JScrollPane getSpTblChequesEmitidos() {
		return spTblChequesEmitidos;
	}

	public JTable getTblChequesEmitidos() {
		return tblChequesEmitidos;
	}
}
