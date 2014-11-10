package com.spirit.cartera.gui.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;

public abstract class JPCuentasPorCobrar extends ReportModelImpl {
	public JPCuentasPorCobrar() {
		setName("Cuentas por Cobrar");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		spTblCuentaPorCobrar = new JScrollPane();
		tblCuentasPorCobrar = new JTable();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbTodos = new JCheckBox();
		btnConsultar = new JButton();
		lblFechaInicial = new JLabel();
		cmbFechaInicial = new DateComboBox();
		lblFechaCorte = new JLabel();
		cmbFechaCorte = new DateComboBox();
		cbEstadoCuenta = new JCheckBox();
		lblFechaFinal = new JLabel();
		lblTotalSaldoCuentasPorCobrar = new JLabel();
		cmbFechaFinal = new DateComboBox();
		txtTotalSaldoCuentasPorCobrar = new JTextField();
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
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12)),
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

		//======== spTblCuentaPorCobrar ========
		{
			
			//---- tblCuentasPorCobrar ----
			tblCuentasPorCobrar.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"# Factura", "F. Emisi\u00f3n", "Ret.", "Detalle", "x Vencer", "+ 30 D\u00edas", "+ 60 D\u00edas", "+ 90 D\u00edas", "+ 120 D\u00edas", "# D\u00edas"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblCuentaPorCobrar.setViewportView(tblCuentasPorCobrar);
		}
		add(spTblCuentaPorCobrar, cc.xywh(3, 11, 20, 5));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 3, 7, 1));

		//---- btnBuscarCliente ----
		btnBuscarCliente.setText("B");
		add(btnBuscarCliente, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(17, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(19, 3));

		//---- lblFechaInicial ----
		lblFechaInicial.setText("Fecha inicial:");
		add(lblFechaInicial, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicial, cc.xy(5, 5));

		//---- lblFechaCorte ----
		lblFechaCorte.setText("Fecha de corte:");
		add(lblFechaCorte, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaCorte, cc.xy(11, 5));

		//---- cbEstadoCuenta ----
		cbEstadoCuenta.setText("Estado de Cuenta");
		add(cbEstadoCuenta, cc.xy(17, 5));

		//---- lblFechaFinal ----
		lblFechaFinal.setText("Fecha final:");
		add(lblFechaFinal, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblTotalSaldoCuentasPorCobrar ----
		lblTotalSaldoCuentasPorCobrar.setText("Total Saldo Cuentas por Cobrar:");
		lblTotalSaldoCuentasPorCobrar.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		lblTotalSaldoCuentasPorCobrar.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTotalSaldoCuentasPorCobrar, cc.xywh(11, 19, 7, 1));
		add(cmbFechaFinal, cc.xy(5, 7));

		//---- txtTotalSaldoCuentasPorCobrar ----
		txtTotalSaldoCuentasPorCobrar.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		txtTotalSaldoCuentasPorCobrar.setHorizontalAlignment(JTextField.RIGHT);
		txtTotalSaldoCuentasPorCobrar.setEditable(false);
		add(txtTotalSaldoCuentasPorCobrar, cc.xywh(19, 19, 3, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane spTblCuentaPorCobrar;
	private JTable tblCuentasPorCobrar;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbTodos;
	private JButton btnConsultar;
	private JLabel lblFechaInicial;
	private DateComboBox cmbFechaInicial;
	private JLabel lblFechaCorte;
	private DateComboBox cmbFechaCorte;
	private JCheckBox cbEstadoCuenta;
	private JLabel lblFechaFinal;
	private JLabel lblTotalSaldoCuentasPorCobrar;
	private DateComboBox cmbFechaFinal;
	private JTextField txtTotalSaldoCuentasPorCobrar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
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

	public JTable getTblCuentasPorCobrar() {
		return tblCuentasPorCobrar;
	}

	public void setTblCuentasPorCobrar(JTable tblCuentasPorCobrar) {
		this.tblCuentasPorCobrar = tblCuentasPorCobrar;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtTotalSaldoCuentasPorCobrar() {
		return txtTotalSaldoCuentasPorCobrar;
	}

	public void setTxtTotalSaldoCuentasPorCobrar(JTextField txtTotalSaldoCuentasPorCobrar) {
		this.txtTotalSaldoCuentasPorCobrar = txtTotalSaldoCuentasPorCobrar;
	}

	public JCheckBox getCbEstadoCuenta() {
		return cbEstadoCuenta;
	}

	public void setCbEstadoCuenta(JCheckBox cbEstadoCuenta) {
		this.cbEstadoCuenta = cbEstadoCuenta;
	}

	public DateComboBox getCmbFechaCorte() {
		return cmbFechaCorte;
	}

	public void setCmbFechaCorte(DateComboBox cmbFechaCorte) {
		this.cmbFechaCorte = cmbFechaCorte;
	}
}
