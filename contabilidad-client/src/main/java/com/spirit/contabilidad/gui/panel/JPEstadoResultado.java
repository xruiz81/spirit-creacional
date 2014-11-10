package com.spirit.contabilidad.gui.panel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.grid.TreeTable;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPEstadoResultado extends ReportModelImpl {
	public JPEstadoResultado() {
		initComponents();
		setName("Estado de Resultados");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblEjercicioContable = new JLabel();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		btnConsultar = new JButton();
		cmbEjercicioContable = new JComboBox();
		cmbFechaFin = new DateComboBox();
		lblSaldoFinal = new JLabel();
		txtSaldoFinal = new JTextField();
		lblFechaFin = new JLabel();
		cmbNivelesVisibles = new JComboBox();
		cbAcumulado = new JCheckBox();
		lblNivelesVisibles = new JLabel();
		scrollPane = new JScrollPane();
		treeTblEstadoResultados = new TreeTable();
		tblEstadoResultados = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(pref;60dlu):grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblEjercicioContable ----
		lblEjercicioContable.setText("Ejercicio Contable:");
		add(lblEjercicioContable, cc.xywh(3, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de cuentas:");
		add(lblPlanCuenta, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
		add(cmbPlanCuenta, cc.xywh(5, 3, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaInicio, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(17, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbEjercicioContable, cc.xywh(5, 5, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbFechaFin, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblSaldoFinal ----
		lblSaldoFinal.setText("Saldo Final:");
		lblSaldoFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(lblSaldoFinal, cc.xywh(11, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtSaldoFinal ----
		txtSaldoFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		txtSaldoFinal.setHorizontalAlignment(JTextField.RIGHT);
		txtSaldoFinal.setEditable(false);
		add(txtSaldoFinal, cc.xywh(13, 19, 5, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbNivelesVisibles, cc.xy(5, 7));

		//---- cbAcumulado ----
		cbAcumulado.setText("Acumulado");
		cbAcumulado.setSelected(false);
		add(cbAcumulado, cc.xy(7, 7));

		//---- lblNivelesVisibles ----
		lblNivelesVisibles.setText("Niveles visibles:");
		add(lblNivelesVisibles, cc.xywh(3, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//======== scrollPane ========
		{
			scrollPane.setViewportView(treeTblEstadoResultados);
		}
		add(scrollPane, cc.xywh(3, 11, 15, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnConsultar.setToolTipText("Consultar Estado Resultados");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEjercicioContable;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JButton btnConsultar;
	private JComboBox cmbEjercicioContable;
	private DateComboBox cmbFechaFin;
	private JLabel lblSaldoFinal;
	private JTextField txtSaldoFinal;
	private JLabel lblFechaFin;
	private JComboBox cmbNivelesVisibles;
	private JCheckBox cbAcumulado;
	private JLabel lblNivelesVisibles;
	private JScrollPane scrollPane;
	private JTable tblEstadoResultados;
	private TreeTable treeTblEstadoResultados;
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

	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JTable getTblEstadoResultados() {
		return tblEstadoResultados;
	}

	public void setTblEstadoResultados(JTable tblEstadoResultados) {
		this.tblEstadoResultados = tblEstadoResultados;
	}
	
	public TreeTable getTreeTblEstadoResultados() {
		return treeTblEstadoResultados;
	}

	public void setTreeTblEstadoResultados(TreeTable treeTblEstadoFlujoEfectivo) {
		this.treeTblEstadoResultados = treeTblEstadoFlujoEfectivo;
	}

	public JTextField getTxtSaldoFinal() {
		return txtSaldoFinal;
	}

	public void setTxtSaldoFinal(JTextField txtSaldoFinal) {
		this.txtSaldoFinal = txtSaldoFinal;
	}
	
	public JLabel getLblSaldoFinal() {
		return lblSaldoFinal;
	}

	public void setLblSaldoFinal(JLabel lblSaldoFinal) {
		this.lblSaldoFinal = lblSaldoFinal;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JComboBox getCmbNivelesVisibles() {
		return cmbNivelesVisibles;
	}

	public void setCmbNivelesVisibles(JComboBox cmbNivelesVisibles) {
		this.cmbNivelesVisibles = cmbNivelesVisibles;
	}

	public JCheckBox getCbAcumulado() {
		return cbAcumulado;
	}

	public void setCbAcumulado(JCheckBox cbAcumulado) {
		this.cbAcumulado = cbAcumulado;
	}

	public JComboBox getCmbEjercicioContable() {
		return cmbEjercicioContable;
	}

	public void setCmbEjercicioContable(JComboBox cmbEjercicioContable) {
		this.cmbEjercicioContable = cmbEjercicioContable;
	}
}
