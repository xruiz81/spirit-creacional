package com.spirit.contabilidad.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
import com.spirit.util.MyTableCellEditorNumber;

public abstract class JPConsultaMayorGeneral extends ReportModelImpl {

	public JPConsultaMayorGeneral() {
		initComponents();
		setName("Consulta Mayor General");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		btnConsultar = new JButton();
		lblPeriodo = new JLabel();
		cmbFechaFin = new DateComboBox();
		cmbPeriodo = new JComboBox();
		lblFechaFin = new JLabel();
		lblCuenta = new JLabel();
		txtCuentaContable = new JTextField();
		lblSaldoInicial = new JLabel();
		txtSaldoInicial = new JTextField();
		btnBuscarCuenta = new JButton();
		spTblDetalle = new JScrollPane();
		tblDetalle = new JTable();
		lblTotalDebe = new JLabel();
		txtTotalDebe = new JTextField();
		lblTotalHaber = new JLabel();
		txtTotalHaber = new JTextField();
		lblSaldoFinal = new JLabel();
		txtSaldoFinal = new JTextField();
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
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(75), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(75), FormSpec.DEFAULT_GROW),
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
				new RowSpec(RowSpec.TOP, Sizes.dluY(12), FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				new RowSpec(RowSpec.TOP, Sizes.dluY(12), FormSpec.NO_GROW),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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
		add(cmbFechaInicio, cc.xywh(13, 3, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblPeriodo ----
		lblPeriodo.setText("Per\u00edodo:");
		add(lblPeriodo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaFin, cc.xywh(13, 5, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbPeriodo, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblCuenta ----
		lblCuenta.setText("Cuenta Contable:");
		add(lblCuenta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(txtCuentaContable, cc.xywh(5, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- txtSaldoInicial ----
		txtSaldoInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtSaldoInicial, cc.xywh(15, 7, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(btnBuscarCuenta, cc.xywh(7, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblSaldoInicial ----
		lblSaldoInicial.setText("Saldo Inicial:");
		add(lblSaldoInicial, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//======== spTblDetalle ========
		{

			//---- tblDetalle ----
			tblDetalle.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Fecha", "Referencia", "Glosa", "Debe", "Haber"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblDetalle.setViewportView(tblDetalle);
		}
		add(spTblDetalle, cc.xywh(3, 9, 15, 2));

		//---- lblTotalDebe ----
		lblTotalDebe.setText("Debe:");
		add(lblTotalDebe, cc.xywh(11, 12, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtTotalDebe ----
		txtTotalDebe.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtTotalDebe, cc.xy(13, 12));

		//---- lblTotalHaber ----
		lblTotalHaber.setText("Haber:");
		add(lblTotalHaber, cc.xywh(15, 12, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtTotalHaber ----
		txtTotalHaber.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtTotalHaber, cc.xy(17, 12));

		//---- lblSaldoFinal ----
		lblSaldoFinal.setText("Saldo Final:");
		add(lblSaldoFinal, cc.xywh(13, 14, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtSaldoFinal ----
		txtSaldoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtSaldoFinal, cc.xywh(15, 14, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
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
	private JLabel lblCuenta;
	private JTextField txtCuentaContable;
	private JLabel lblSaldoInicial;
	private JTextField txtSaldoInicial;
	private JButton btnBuscarCuenta;
	private JScrollPane spTblDetalle;
	private JTable tblDetalle;
	private JLabel lblTotalDebe;
	private JTextField txtTotalDebe;
	private JLabel lblTotalHaber;
	private JTextField txtTotalHaber;
	private JLabel lblSaldoFinal;
	private JTextField txtSaldoFinal;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JComboBox getCmbPeriodo() {
		return cmbPeriodo;
	}

	public void setCmbPeriodo(JComboBox cmbPeriodo) {
		this.cmbPeriodo = cmbPeriodo;
	}

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JTable getTblDetalle() {
		return tblDetalle;
	}

	public void setTblDetalle(JTable tblDetalle) {
		this.tblDetalle = tblDetalle;
	}

	public JTextField getTxtSaldoFinal() {
		return txtSaldoFinal;
	}

	public void setTxtSaldoFinal(JTextField txtSaldoFinal) {
		this.txtSaldoFinal = txtSaldoFinal;
	}

	public JTextField getTxtSaldoInicial() {
		return txtSaldoInicial;
	}

	public void setTxtSaldoInicial(JTextField txtSaldoInicial) {
		this.txtSaldoInicial = txtSaldoInicial;
	}

	public JButton getBtnBuscarCuenta() {
		return btnBuscarCuenta;
	}

	public void setBtnBuscarCuenta(JButton btnBuscarCuenta) {
		this.btnBuscarCuenta = btnBuscarCuenta;
	}

	public JTextField getTxtCuentaContable() {
		return txtCuentaContable;
	}

	public void setTxtCuentaContable(JTextField txtCuentaContable) {
		this.txtCuentaContable = txtCuentaContable;
	}

	public JTextField getTxtTotalDebe() {
		return txtTotalDebe;
	}

	public void setTxtTotalDebe(JTextField txtTotalDebe) {
		this.txtTotalDebe = txtTotalDebe;
	}

	public JTextField getTxtTotalHaber() {
		return txtTotalHaber;
	}

	public void setTxtTotalHaber(JTextField txtTotalHaber) {
		this.txtTotalHaber = txtTotalHaber;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
}
