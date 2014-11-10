package com.spirit.cartera.gui.panel;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPPagos extends MantenimientoModelImpl {
	public JPPagos() {
		initComponents();
		setName("Pagos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		cbProveedor = new JCheckBox();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		btnConsultar = new JButton();
		cbTodos = new JCheckBox();
		lblNumeroCheque = new JLabel();
		btnNumerosCheque = new JButton();
		lblFechaCobro = new JLabel();
		cmbFechaCobro = new DateComboBox();
		btnFechaCobro = new JButton();
		spTblPagos = new JScrollPane();
		tblPagos = new JTable();
		lblTotalPago = new JLabel();
		txtTotalPago = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX7),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(150)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX7)
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY8)
			}));

		//---- cbProveedor ----
		cbProveedor.setText("Proveedor");
		add(cbProveedor, cc.xy(3, 3));
		add(txtProveedor, cc.xywh(5, 3, 11, 1));
		add(btnProveedor, cc.xywh(17, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(21, 3));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(3, 5));

		//---- lblNumeroCheque ----
		lblNumeroCheque.setText("# Cheque:");
		add(lblNumeroCheque, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(btnNumerosCheque, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaCobro ----
		lblFechaCobro.setText("Fecha:");
		add(lblFechaCobro, cc.xywh(13, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaCobro, cc.xy(15, 5));
		add(btnFechaCobro, cc.xywh(17, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== spTblPagos ========
		{
			
			//---- tblPagos ----
			tblPagos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Pagar", "Proveedor", "# Factura", "Fecha Venc.", "Valor", "Forma de Pago", "# Cheque", "Fecha", "Observaci\u00f3n"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, true, true, false, false
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			tblPagos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			spTblPagos.setViewportView(tblPagos);
		}
		add(spTblPagos, cc.xywh(3, 7, 21, 5));

		//---- lblTotalPago ----
		lblTotalPago.setText("Total Pago:");
		add(lblTotalPago, cc.xywh(15, 13, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtTotalPago ----
		txtTotalPago.setEditable(false);
		txtTotalPago.setHorizontalAlignment(JTextField.RIGHT);
		add(txtTotalPago, cc.xywh(19, 13, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JCheckBox cbProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JButton btnConsultar;
	private JCheckBox cbTodos;
	private JLabel lblNumeroCheque;
	private JButton btnNumerosCheque;
	private JLabel lblFechaCobro;
	private DateComboBox cmbFechaCobro;
	private JButton btnFechaCobro;
	private JScrollPane spTblPagos;
	private JTable tblPagos;
	private JLabel lblTotalPago;
	private JTextField txtTotalPago;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtTotalPago() {
		return txtTotalPago;
	}

	public void setTxtTotalPago(JTextField txtTotalPago) {
		this.txtTotalPago = txtTotalPago;
	}

	public JScrollPane getSpTblPagos() {
		return spTblPagos;
	}

	public void setSpTblPagos(JScrollPane spTblPagos) {
		this.spTblPagos = spTblPagos;
	}

	public JTable getTblPagos() {
		return tblPagos;
	}

	public void setTblPagos(JTable tblPagos) {
		this.tblPagos = tblPagos;
	}

	public JButton getBtnFechaCobro() {
		return btnFechaCobro;
	}

	public void setBtnFechaCobro(JButton btnFechaCobro) {
		this.btnFechaCobro = btnFechaCobro;
	}

	public DateComboBox getCmbFechaCobro() {
		return cmbFechaCobro;
	}

	public void setCmbFechaCobro(DateComboBox cmbFechaCobro) {
		this.cmbFechaCobro = cmbFechaCobro;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public void setBtnProveedor(JButton btnProveedor) {
		this.btnProveedor = btnProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JCheckBox getCbProveedor() {
		return cbProveedor;
	}

	public void setCbProveedor(JCheckBox cbProveedor) {
		this.cbProveedor = cbProveedor;
	}

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}

	public JButton getBtnNumerosCheque() {
		return btnNumerosCheque;
	}

	public void setBtnNumerosCheque(JButton btnNumerosCheque) {
		this.btnNumerosCheque = btnNumerosCheque;
	}
}
