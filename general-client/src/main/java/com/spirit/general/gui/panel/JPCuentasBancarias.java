package com.spirit.general.gui.panel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Sat Jan 22 15:03:59 COT 2011
 */
import com.spirit.client.model.MantenimientoModelImpl;



/**
 * @author SHOCKIE
 */
public abstract class JPCuentasBancarias extends MantenimientoModelImpl {
	public JPCuentasBancarias() {
		setName("Cuentas Bancarias");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		spPanelCuentasBancarias = new JScrollPane();
		panelCuentasBancarias = new JPanel();
		lblBanco = new JLabel();
		cmbBanco = new JComboBox();
		lblTipoCuenta = new JLabel();
		lblNombre = new JLabel();
		cmbTipoCuenta = new JComboBox();
		txtCuenta = new JTextField();
		lblNumeroCheque = new JLabel();
		txtNumeroCheque = new JTextField();
		cbCuentaCliente = new JCheckBox();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		scCuentasDetalles = new JScrollPane();
		tblCuentasDetalles = new JTable();
		CellConstraints cc = new CellConstraints();
		popup = new JPopupMenu();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== spPanelCuentasBancarias ========
		{

			//======== panelCuentasBancarias ========
			{
				panelCuentasBancarias.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
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
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				((FormLayout)panelCuentasBancarias.getLayout()).setColumnGroups(new int[][] {{5, 11}});

				//---- lblBanco ----
				lblBanco.setText("Banco:");
				panelCuentasBancarias.add(lblBanco, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelCuentasBancarias.add(cmbBanco, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblTipoCuenta ----
				lblTipoCuenta.setText("Tipo de Cuenta:");
				panelCuentasBancarias.add(lblTipoCuenta, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

				//---- lblNombre ----
				lblNombre.setText("Cuenta:");
				panelCuentasBancarias.add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				panelCuentasBancarias.add(cmbTipoCuenta, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				panelCuentasBancarias.add(txtCuenta, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

				//---- lblNumeroCheque ----
				lblNumeroCheque.setText("N\u00famero de Cheque:");
				panelCuentasBancarias.add(lblNumeroCheque, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelCuentasBancarias.add(txtNumeroCheque, cc.xy(11, 5));

				//---- cbCuentaCliente ----
				cbCuentaCliente.setText("Cuenta de cliente");
				panelCuentasBancarias.add(cbCuentaCliente, cc.xy(3, 7));

				//---- txtCliente ----
				txtCliente.setEditable(false);
				panelCuentasBancarias.add(txtCliente, cc.xywh(5, 7, 7, 1));

				//---- btnBuscarCliente ----
				btnBuscarCliente.setText("Buscar");
				panelCuentasBancarias.add(btnBuscarCliente, cc.xy(13, 7));

				//======== scCuentasDetalles ========
				{

					//---- tblCuentasDetalles ----
					tblCuentasDetalles.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null},
						},
						new String[] {
							"Banco", "Tipo de Cuenta", "N\u00famero de Cuenta", "N\u00famero de Cheque"
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
					tblCuentasDetalles.setPreferredScrollableViewportSize(new Dimension(450, 150));
					tblCuentasDetalles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					scCuentasDetalles.setViewportView(tblCuentasDetalles);
				}
				panelCuentasBancarias.add(scCuentasDetalles, cc.xywh(3, 11, 11, 1));
			}
			spPanelCuentasBancarias.setViewportView(panelCuentasBancarias);
		}
		add(spPanelCuentasBancarias, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JScrollPane spPanelCuentasBancarias;
	private JPanel panelCuentasBancarias;
	private JLabel lblBanco;
	private JComboBox cmbBanco;
	private JLabel lblTipoCuenta;
	private JLabel lblNombre;
	private JComboBox cmbTipoCuenta;
	private JTextField txtCuenta;
	private JLabel lblNumeroCheque;
	private JTextField txtNumeroCheque;
	private JCheckBox cbCuentaCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JScrollPane scCuentasDetalles;
	private JTable tblCuentasDetalles;
	protected JPopupMenu popup;
	protected JMenuItem menuItem;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JComboBox getCmbBanco() {
		return cmbBanco;
	}

	public void setCmbBanco(JComboBox cmbBanco) {
		this.cmbBanco = cmbBanco;
	}

	public JComboBox getCmbTipoCuenta() {
		return cmbTipoCuenta;
	}

	public JTable getTblCuentasDetalles() {
		return tblCuentasDetalles;
	}

	public void setTblCuentasDetalles(JTable tblCuentasDetalles) {
		this.tblCuentasDetalles = tblCuentasDetalles;
	}

	public JTextField getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(JTextField txtCuenta) {
		this.txtCuenta = txtCuenta;
	}

	public JTextField getTxtNumeroCheque() {
		return txtNumeroCheque;
	}

	public void setTxtNumeroCheque(JTextField txtNumeroCheque) {
		this.txtNumeroCheque = txtNumeroCheque;
	}

	public void setCmbTipoCuenta(JComboBox cmbTipoCuenta) {
		this.cmbTipoCuenta = cmbTipoCuenta;
	}

	public JCheckBox getCbCuentaCliente() {
		return cbCuentaCliente;
	}

	public void setCbCuentaCliente(JCheckBox cbCuentaCliente) {
		this.cbCuentaCliente = cbCuentaCliente;
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
}
