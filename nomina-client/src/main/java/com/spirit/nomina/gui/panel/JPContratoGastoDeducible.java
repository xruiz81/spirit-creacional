package com.spirit.nomina.gui.panel;
import javax.swing.JButton;
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
import com.jidesoft.combobox.DateComboBox;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPContratoGastoDeducible extends SpiritModelImpl {
	public JPContratoGastoDeducible() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblEmpleado = new JLabel();
		txtEmpleado = new JTextField();
		btnEmpleado = new JButton();
		lblContrato = new JLabel();
		txtContrato = new JTextField();
		btnContrato = new JButton();
		lblDeducible = new JLabel();
		txtGastoDeducible = new JTextField();
		btnGastoDeducible = new JButton();
		lblAnio = new JLabel();
		cmbFechaMesAnio = new DateComboBox();
		lblValor = new JLabel();
		txtValor = new JTextField();
		panelTblGastosDeducibles = new JPanel();
		btnAgregarDeducible = new JButton();
		btnActualizarDeducible = new JButton();
		btnRemoverDeducible = new JButton();
		spTblContratoDeducible = new JScrollPane();
		tblContratoGastoDeducible = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Gastos Deducibles Por Contrato");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(50)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(60)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblEmpleado ----
		lblEmpleado.setText("Empleado:");
		add(lblEmpleado, cc.xy(3, 3));

		//---- txtEmpleado ----
		txtEmpleado.setEditable(false);
		add(txtEmpleado, cc.xywh(5, 3, 5, 1));

		//---- btnEmpleado ----
		btnEmpleado.setText(" ");
		add(btnEmpleado, cc.xy(11, 3));

		//---- lblContrato ----
		lblContrato.setText("Contrato:");
		add(lblContrato, cc.xy(3, 5));

		//---- txtContrato ----
		txtContrato.setEditable(false);
		add(txtContrato, cc.xywh(5, 5, 3, 1));

		//---- btnContrato ----
		btnContrato.setText(" ");
		add(btnContrato, cc.xy(11, 5));

		//---- lblDeducible ----
		lblDeducible.setText("Deducible: ");
		add(lblDeducible, cc.xy(3, 7));

		//---- txtGastoDeducible ----
		txtGastoDeducible.setEditable(false);
		add(txtGastoDeducible, cc.xywh(5, 7, 5, 1));

		//---- btnGastoDeducible ----
		btnGastoDeducible.setText(" ");
		add(btnGastoDeducible, cc.xy(11, 7));

		//---- lblAnio ----
		lblAnio.setText("Mes/A\u00f1o: ");
		add(lblAnio, cc.xy(3, 9));
		add(cmbFechaMesAnio, cc.xywh(5, 9, 3, 1));

		//---- lblValor ----
		lblValor.setText("Valor: ");
		add(lblValor, cc.xy(3, 11));
		add(txtValor, cc.xy(5, 11));

		//======== panelTblGastosDeducibles ========
		{
			panelTblGastosDeducibles.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- btnAgregarDeducible ----
			btnAgregarDeducible.setText("A");
			panelTblGastosDeducibles.add(btnAgregarDeducible, cc.xy(1, 1));
			
			//---- btnActualizarDeducible ----
			btnActualizarDeducible.setText("U");
			btnActualizarDeducible.setHorizontalAlignment(SwingConstants.CENTER);
			panelTblGastosDeducibles.add(btnActualizarDeducible, cc.xy(3, 1));
			
			//---- btnRemoverDeducible ----
			btnRemoverDeducible.setText("D");
			panelTblGastosDeducibles.add(btnRemoverDeducible, cc.xy(5, 1));
		}
		add(panelTblGastosDeducibles, cc.xywh(3, 15, 5, 1));

		//======== spTblContratoDeducible ========
		{
			
			//---- tblContratoGastoDeducible ----
			tblContratoGastoDeducible.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"Contrato", "Empleado", "Gasto Deducible", "A\u00f1o", "Valor"
				}
			) {
				Class[] columnTypes = new Class[] {
					Object.class, Object.class, Object.class, Object.class, Double.class
				};
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblContratoDeducible.setViewportView(tblContratoGastoDeducible);
		}
		add(spTblContratoDeducible, cc.xywh(3, 17, 11, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEmpleado;
	private JTextField txtEmpleado;
	private JButton btnEmpleado;
	private JLabel lblContrato;
	private JTextField txtContrato;
	private JButton btnContrato;
	private JLabel lblDeducible;
	private JTextField txtGastoDeducible;
	private JButton btnGastoDeducible;
	private JLabel lblAnio;
	private DateComboBox cmbFechaMesAnio;
	private JLabel lblValor;
	private JTextField txtValor;
	private JPanel panelTblGastosDeducibles;
	private JButton btnAgregarDeducible;
	private JButton btnActualizarDeducible;
	private JButton btnRemoverDeducible;
	private JScrollPane spTblContratoDeducible;
	private JTable tblContratoGastoDeducible;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblEmpleado() {
		return lblEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public JLabel getLblContrato() {
		return lblContrato;
	}

	public JTextField getTxtContrato() {
		return txtContrato;
	}

	public JButton getBtnContrato() {
		return btnContrato;
	}

	public JLabel getLblDeducible() {
		return lblDeducible;
	}

	public JTextField getTxtGastoDeducible() {
		return txtGastoDeducible;
	}

	public JButton getBtnGastoDeducible() {
		return btnGastoDeducible;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public DateComboBox getCmbFechaMesAnio() {
		return cmbFechaMesAnio;
	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JPanel getPanelTblGastosDeducibles() {
		return panelTblGastosDeducibles;
	}

	public JButton getBtnAgregarDeducible() {
		return btnAgregarDeducible;
	}

	public JButton getBtnActualizarDeducible() {
		return btnActualizarDeducible;
	}

	public JButton getBtnRemoverDeducible() {
		return btnRemoverDeducible;
	}

	public JScrollPane getSpTblContratoDeducible() {
		return spTblContratoDeducible;
	}

	public JTable getTblContratoGastoDeducible() {
		return tblContratoGastoDeducible;
	}
}
