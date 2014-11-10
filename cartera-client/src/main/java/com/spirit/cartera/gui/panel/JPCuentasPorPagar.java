package com.spirit.cartera.gui.panel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
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
import com.spirit.client.model.ReportModelImpl;

public abstract class JPCuentasPorPagar extends ReportModelImpl {
	public JPCuentasPorPagar() {
		setName("Cuentas por Pagar");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		spTblCuentaPorPagar = new JScrollPane();
		tblCuentasPorPagar = new JTable();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		btnBuscarProveedor = new JButton();
		cbTodosProveedores = new JCheckBox();
		cbCalcularSaldoInicial = new JCheckBox();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		rbPorTipoProveedor = new JRadioButton();
		cmbFechaInicial = new DateComboBox();
		lblFechaInicial = new JLabel();
		lblFechaFinal = new JLabel();
		cmbFechaFinal = new DateComboBox();
		rbPorDiasVencidos = new JRadioButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbTodosClientes = new JCheckBox();
		btnConsultar = new JButton();
		cbCompararTodasOficinas = new JCheckBox();
		lblTotalSaldoCuentasPorPagar = new JLabel();
		txtTotalSaldoCuentasPorPagar = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
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
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.TOP, Sizes.DLUY3, FormSpec.NO_GROW),
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
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== spTblCuentaPorPagar ========
		{

			//---- tblCuentasPorPagar ----
			tblCuentasPorPagar.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Raz\u00f3n Social", "RUC", "# Factura", "Valor Total", "Rte. Fte.", "Saldo", "Fecha", "# Dias"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblCuentaPorPagar.setViewportView(tblCuentasPorPagar);
		}
		add(spTblCuentaPorPagar, cc.xywh(3, 15, 19, 5));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedor ----
		txtProveedor.setEditable(false);
		add(txtProveedor, cc.xywh(5, 3, 7, 1));
		add(btnBuscarProveedor, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosProveedores ----
		cbTodosProveedores.setText("Todos");
		add(cbTodosProveedores, cc.xy(15, 3));

		//---- cbCalcularSaldoInicial ----
		cbCalcularSaldoInicial.setText("Calcular Saldo Inicial");
		add(cbCalcularSaldoInicial, cc.xywh(19, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo proveedor:");
		add(lblTipoProveedor, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoProveedor, cc.xy(5, 5));

		//---- rbPorTipoProveedor ----
		rbPorTipoProveedor.setText("Por Tipo de Proveedor");
		add(rbPorTipoProveedor, cc.xywh(19, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		add(cmbFechaInicial, cc.xy(5, 7));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFinal, cc.xy(11, 7));

		//---- rbPorDiasVencidos ----
		rbPorDiasVencidos.setText("Por D\u00edas Vencidos");
		add(rbPorDiasVencidos, cc.xywh(19, 7, 2, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- lblCliente ----
		lblCliente.setText("Cliente Oficina:");
		add(lblCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 9, 7, 1));
		add(btnBuscarCliente, cc.xywh(13, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosClientes ----
		cbTodosClientes.setText("Todos");
		add(cbTodosClientes, cc.xy(15, 9));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(19, 9));

		//---- cbCompararTodasOficinas ----
		cbCompararTodasOficinas.setText("Comparar con todas las oficinas");
		add(cbCompararTodasOficinas, cc.xywh(5, 11, 5, 1));

		//---- lblTotalSaldoCuentasPorPagar ----
		lblTotalSaldoCuentasPorPagar.setText("Total Saldo Cuentas por Pagar:");
		lblTotalSaldoCuentasPorPagar.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalSaldoCuentasPorPagar.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalSaldoCuentasPorPagar, cc.xywh(11, 23, 7, 1));

		//---- txtTotalSaldoCuentasPorPagar ----
		txtTotalSaldoCuentasPorPagar.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		txtTotalSaldoCuentasPorPagar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalSaldoCuentasPorPagar.setEditable(false);
		add(txtTotalSaldoCuentasPorPagar, cc.xywh(19, 23, 3, 1));

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbPorTipoProveedor);
		buttonGroup1.add(rbPorDiasVencidos);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spTblCuentaPorPagar;
	private JTable tblCuentasPorPagar;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JButton btnBuscarProveedor;
	private JCheckBox cbTodosProveedores;
	private JCheckBox cbCalcularSaldoInicial;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JRadioButton rbPorTipoProveedor;
	private DateComboBox cmbFechaInicial;
	private JLabel lblFechaInicial;
	private JLabel lblFechaFinal;
	private DateComboBox cmbFechaFinal;
	private JRadioButton rbPorDiasVencidos;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbTodosClientes;
	private JButton btnConsultar;
	private JCheckBox cbCompararTodasOficinas;
	private JLabel lblTotalSaldoCuentasPorPagar;
	private JTextField txtTotalSaldoCuentasPorPagar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbTodosProveedores() {
		return cbTodosProveedores;
	}

	public void setCbTodosProveedores(JCheckBox cbTodosProveedores) {
		this.cbTodosProveedores = cbTodosProveedores;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JCheckBox getCbTodosClientes() {
		return cbTodosClientes;
	}

	public void setCbTodosClientes(JCheckBox cbTodosClientes) {
		this.cbTodosClientes = cbTodosClientes;
	}

	public JCheckBox getCbCalcularSaldoInicial() {
		return cbCalcularSaldoInicial;
	}

	public void setCbCalcularSaldoInicial(JCheckBox cbCalcularSaldoInicial) {
		this.cbCalcularSaldoInicial = cbCalcularSaldoInicial;
	}

	public JRadioButton getRbPorTipoProveedor() {
		return rbPorTipoProveedor;
	}

	public void setRbPorTipoProveedor(JRadioButton rbPorTipoProveedor) {
		this.rbPorTipoProveedor = rbPorTipoProveedor;
	}

	public JRadioButton getRbPorDiasVencidos() {
		return rbPorDiasVencidos;
	}

	public void setRbPorDiasVencidos(JRadioButton rbPorDiasVencidos) {
		this.rbPorDiasVencidos = rbPorDiasVencidos;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public void setBtnBuscarProveedor(JButton btnBuscarProveedor) {
		this.btnBuscarProveedor = btnBuscarProveedor;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public void setCmbTipoProveedor(JComboBox cmbTipoProveedor) {
		this.cmbTipoProveedor = cmbTipoProveedor;
	}

	public JTable getTblCuentasPorPagar() {
		return tblCuentasPorPagar;
	}

	public void setTblCuentasPorPagar(JTable tblCuentasPorPagar) {
		this.tblCuentasPorPagar = tblCuentasPorPagar;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public DateComboBox getCmbFechaFinal() {
		return cmbFechaFinal;
	}

	public void setCmbFechaFinal(DateComboBox cmbFechaFinal) {
		this.cmbFechaFinal = cmbFechaFinal;
	}

	public DateComboBox getCmbFechaInicial() {
		return cmbFechaInicial;
	}

	public void setCmbFechaInicial(DateComboBox cmbFechaInicial) {
		this.cmbFechaInicial = cmbFechaInicial;
	}

	public JTextField getTxtTotalSaldoCuentasPorPagar() {
		return txtTotalSaldoCuentasPorPagar;
	}

	public void setTxtTotalSaldoCuentasPorPagar(
			JTextField txtTotalSaldoCuentasPorPagar) {
		this.txtTotalSaldoCuentasPorPagar = txtTotalSaldoCuentasPorPagar;
	}

	public JCheckBox getCbCompararTodasOficinas() {
		return cbCompararTodasOficinas;
	}
}
