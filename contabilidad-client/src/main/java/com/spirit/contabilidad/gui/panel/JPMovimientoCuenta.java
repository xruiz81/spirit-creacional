package com.spirit.contabilidad.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
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
import com.spirit.util.MyTableCellEditorNumber;

public abstract class JPMovimientoCuenta extends ReportModelImpl {
	public JPMovimientoCuenta() {
		initComponents();
		setName("Movimiento de Cuentas");
	}

	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		btnConsultar = new JButton();
		lblPeriodo = new JLabel();
		cmbFechaFin = new DateComboBox();
		cmbPeriodo = new JComboBox();
		lblFechaFin = new JLabel();
		fsCuentas = compFactory.createSeparator("Cuentas:");
		cbRango = new JCheckBox();
		txtCuentaInicial = new JTextField();
		panel2 = new JPanel();
		lblProgress = new JLabel();
		btnBuscarCuentaInicial = new JButton();
		txtCuentaFinal = new JTextField();
		btnBuscarCuentaFinal = new JButton();
		panel1 = new JPanel();
		btnAgregar = new JButton();
		spTblMovimientoCuenta = new JScrollPane();
		tblMovimientoCuenta = new JTable();
		popup = new JPopupMenu();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(pref;60dlu):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan Cuenta:");
		add(lblPlanCuenta, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbPlanCuenta, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaInicio, cc.xywh(13, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblPeriodo ----
		lblPeriodo.setText("Per\u00edodo:");
		add(lblPeriodo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaFin, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbPeriodo, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(fsCuentas, cc.xywh(3, 7, 15, 1));

		//---- cbRango ----
		cbRango.setText("Rango");
		add(cbRango, cc.xy(3, 9));
		add(txtCuentaInicial, cc.xywh(5, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"default",
				"default"));

			//---- lblProgress ----
			lblProgress.setText("Procesando informaci\u00f3n...");
			lblProgress.setFont(new Font("Cambria Math", Font.BOLD, 14));
			panel2.add(lblProgress, cc.xy(1, 1));
		}
		add(panel2, cc.xywh(11, 9, 6, 3, CellConstraints.FILL, CellConstraints.FILL));

		//---- btnBuscarCuentaInicial ----
		btnBuscarCuentaInicial.setText("B");
		add(btnBuscarCuentaInicial, cc.xywh(7, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(txtCuentaFinal, cc.xy(5, 11));

		//---- btnBuscarCuentaFinal ----
		btnBuscarCuentaFinal.setText("B");
		add(btnBuscarCuentaFinal, cc.xy(7, 11));

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

			//---- btnAgregar ----
			btnAgregar.setText("A");
			btnAgregar.setToolTipText("Agrega el Asiento a la Tabla");
			panel1.add(btnAgregar, cc.xy(1, 1));
		}
		add(panel1, cc.xywh(3, 15, 5, 1));

		//======== spTblMovimientoCuenta ========
		{

			//---- tblMovimientoCuenta ----
			tblMovimientoCuenta.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
				},
				new String[] {
					"Asiento", "Comprobante", "Fecha", "Glosa", "D\u00e9bitos", "Cr\u00e9ditos", "INDEX"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblMovimientoCuenta.setViewportView(tblMovimientoCuenta);
		}
		add(spTblMovimientoCuenta, cc.xywh(3, 17, 17, 3));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JButton btnConsultar;
	private JLabel lblPeriodo;
	private DateComboBox cmbFechaFin;
	private JComboBox cmbPeriodo;
	private JLabel lblFechaFin;
	private JComponent fsCuentas;
	private JCheckBox cbRango;
	private JTextField txtCuentaInicial;
	private JPanel panel2;
	private JLabel lblProgress;
	private JButton btnBuscarCuentaInicial;
	private JTextField txtCuentaFinal;
	private JButton btnBuscarCuentaFinal;
	private JPanel panel1;
	private JButton btnAgregar;
	private JScrollPane spTblMovimientoCuenta;
	private JTable tblMovimientoCuenta;
	

	protected JPopupMenu popup;
		protected JMenuItem menuItem;
		public JLabel getLblPlanCuenta() {
			return lblPlanCuenta;
		}


		public void setLblPlanCuenta(JLabel lblPlanCuenta) {
			this.lblPlanCuenta = lblPlanCuenta;
		}


		public JComboBox getCmbPlanCuenta() {
			return cmbPlanCuenta;
		}


		public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
			this.cmbPlanCuenta = cmbPlanCuenta;
		}


		public JLabel getLblFechaInicio() {
			return lblFechaInicio;
		}


		public void setLblFechaInicio(JLabel lblFechaInicio) {
			this.lblFechaInicio = lblFechaInicio;
		}


		public DateComboBox getCmbFechaInicio() {
			return cmbFechaInicio;
		}


		public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
			this.cmbFechaInicio = cmbFechaInicio;
		}


		public JButton getBtnConsultar() {
			return btnConsultar;
		}


		public void setBtnConsultar(JButton btnConsultar) {
			this.btnConsultar = btnConsultar;
		}


		public JLabel getLblPeriodo() {
			return lblPeriodo;
		}


		public void setLblPeriodo(JLabel lblPeriodo) {
			this.lblPeriodo = lblPeriodo;
		}


		public DateComboBox getCmbFechaFin() {
			return cmbFechaFin;
		}


		public void setCmbFechaFin(DateComboBox cmbFechaFin) {
			this.cmbFechaFin = cmbFechaFin;
		}


		public JComboBox getCmbPeriodo() {
			return cmbPeriodo;
		}


		public void setCmbPeriodo(JComboBox cmbPeriodo) {
			this.cmbPeriodo = cmbPeriodo;
		}


		public JLabel getLblFechaFin() {
			return lblFechaFin;
		}


		public void setLblFechaFin(JLabel lblFechaFin) {
			this.lblFechaFin = lblFechaFin;
		}


		public JComponent getFsCuentas() {
			return fsCuentas;
		}


		public void setFsCuentas(JComponent fsCuentas) {
			this.fsCuentas = fsCuentas;
		}


		public JCheckBox getCbRango() {
			return cbRango;
		}


		public void setCbRango(JCheckBox cbRango) {
			this.cbRango = cbRango;
		}


		public JTextField getTxtCuentaInicial() {
			return txtCuentaInicial;
		}


		public void setTxtCuentaInicial(JTextField txtCuentaInicial) {
			this.txtCuentaInicial = txtCuentaInicial;
		}


		public JPanel getPanel2() {
			return panel2;
		}


		public void setPanel2(JPanel panel2) {
			this.panel2 = panel2;
		}


		public JLabel getLblProgress() {
			return lblProgress;
		}


		public void setLblProgress(JLabel lblProgress) {
			this.lblProgress = lblProgress;
		}


		public JButton getBtnBuscarCuentaInicial() {
			return btnBuscarCuentaInicial;
		}


		public void setBtnBuscarCuentaInicial(JButton btnBuscarCuentaInicial) {
			this.btnBuscarCuentaInicial = btnBuscarCuentaInicial;
		}


		public JTextField getTxtCuentaFinal() {
			return txtCuentaFinal;
		}


		public void setTxtCuentaFinal(JTextField txtCuentaFinal) {
			this.txtCuentaFinal = txtCuentaFinal;
		}


		public JButton getBtnBuscarCuentaFinal() {
			return btnBuscarCuentaFinal;
		}


		public void setBtnBuscarCuentaFinal(JButton btnBuscarCuentaFinal) {
			this.btnBuscarCuentaFinal = btnBuscarCuentaFinal;
		}


		public JPanel getPanel1() {
			return panel1;
		}


		public void setPanel1(JPanel panel1) {
			this.panel1 = panel1;
		}


		public JButton getBtnAgregar() {
			return btnAgregar;
		}


		public void setBtnAgregar(JButton btnAgregar) {
			this.btnAgregar = btnAgregar;
		}


		public JScrollPane getSpTblMovimientoCuenta() {
			return spTblMovimientoCuenta;
		}


		public void setSpTblMovimientoCuenta(JScrollPane spTblMovimientoCuenta) {
			this.spTblMovimientoCuenta = spTblMovimientoCuenta;
		}


		public JTable getTblMovimientoCuenta() {
			return tblMovimientoCuenta;
		}


		public void setTblMovimientoCuenta(JTable tblMovimientoCuenta) {
			this.tblMovimientoCuenta = tblMovimientoCuenta;
		}


		public JPopupMenu getPopup() {
			return popup;
		}


		public void setPopup(JPopupMenu popup) {
			this.popup = popup;
		}


		public JMenuItem getMenuItem() {
			return menuItem;
		}


		public void setMenuItem(JMenuItem menuItem) {
			this.menuItem = menuItem;
		}

	// JFormDesigner - End of variables declaration  //GEN-END:variables
		
		
		
}
