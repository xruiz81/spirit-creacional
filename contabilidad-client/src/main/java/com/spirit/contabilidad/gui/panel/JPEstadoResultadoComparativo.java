package com.spirit.contabilidad.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

public abstract class JPEstadoResultadoComparativo extends ReportModelImpl {
	
	public JPEstadoResultadoComparativo() {
		initComponents();
		setName("Estado de Resultados Comparativo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblEjercicioContable = new JLabel();
		lblPlanCuenta = new JLabel();
		btnConsultar = new JButton();
		cmbPlanCuenta = new JComboBox();
		cmbEjercicioContable = new JComboBox();
		lblFechaInicioPeriodo = new JLabel();
		cmbFechaInicioPeriodo = new DateComboBox();
		lblFechaFinPeriodo = new JLabel();
		cmbFechaFinPeriodo = new DateComboBox();
		lblComparativo = new JLabel();
		cmbComparativo = new JComboBox();
		lblFechaInicioComparativo = new JLabel();
		cmbFechaInicioComparativo = new DateComboBox();
		lblFechaFinComparativo = new JLabel();
		cmbFechaFinComparativo = new DateComboBox();
		cmbNivelesVisibles = new JComboBox();
		lblNivelesVisibles = new JLabel();
		scrollPane = new JScrollPane();
		tblEstadoResultados = new JTable();
		treeTblEstadoResultados = new TreeTable();
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
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(19, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbPlanCuenta, cc.xywh(5, 3, 3, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbEjercicioContable, cc.xywh(5, 5, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaInicioPeriodo ----
		lblFechaInicioPeriodo.setText("Fecha Inicio:");
		add(lblFechaInicioPeriodo, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaInicioPeriodo, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaFinPeriodo ----
		lblFechaFinPeriodo.setText("Fecha Fin:");
		add(lblFechaFinPeriodo, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaFinPeriodo, cc.xywh(19, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblComparativo ----
		lblComparativo.setText("Comparativo:");
		add(lblComparativo, cc.xy(3, 7));
		add(cmbComparativo, cc.xywh(5, 7, 3, 1));

		//---- lblFechaInicioComparativo ----
		lblFechaInicioComparativo.setText("Fecha Inicio:");
		add(lblFechaInicioComparativo, cc.xy(11, 7));
		add(cmbFechaInicioComparativo, cc.xy(13, 7));

		//---- lblFechaFinComparativo ----
		lblFechaFinComparativo.setText("Fecha Fin:");
		add(lblFechaFinComparativo, cc.xy(17, 7));
		add(cmbFechaFinComparativo, cc.xy(19, 7));
		add(cmbNivelesVisibles, cc.xy(5, 9));

		//---- lblNivelesVisibles ----
		lblNivelesVisibles.setText("Niveles visibles:");
		add(lblNivelesVisibles, cc.xy(3, 9));

		//======== scrollPane ========
		{
			scrollPane.setViewportView(treeTblEstadoResultados);
		}
		add(scrollPane, cc.xywh(3, 13, 17, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnConsultar.setToolTipText("Consultar Estado de Resultados Comparativo");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblEjercicioContable;
	private JLabel lblPlanCuenta;
	private JButton btnConsultar;
	private JComboBox cmbPlanCuenta;
	private JComboBox cmbEjercicioContable;
	private JLabel lblFechaInicioPeriodo;
	private DateComboBox cmbFechaInicioPeriodo;
	private JLabel lblFechaFinPeriodo;
	private DateComboBox cmbFechaFinPeriodo;
	private JLabel lblComparativo;
	private JComboBox cmbComparativo;
	private JLabel lblFechaInicioComparativo;
	private DateComboBox cmbFechaInicioComparativo;
	private JLabel lblFechaFinComparativo;
	private DateComboBox cmbFechaFinComparativo;
	private JComboBox cmbNivelesVisibles;
	private JLabel lblNivelesVisibles;
	private JScrollPane scrollPane;
	private JTable tblEstadoResultados;
	private TreeTable treeTblEstadoResultados;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

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

	public JComboBox getCmbComparativo() {
		return cmbComparativo;
	}

	public void setCmbComparativo(JComboBox cmbComparativo) {
		this.cmbComparativo = cmbComparativo;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public DateComboBox getCmbFechaFinComparativo() {
		return cmbFechaFinComparativo;
	}

	public void setCmbFechaFinComparativo(DateComboBox cmbFechaFinComparativo) {
		this.cmbFechaFinComparativo = cmbFechaFinComparativo;
	}

	public DateComboBox getCmbFechaFinPeriodo() {
		return cmbFechaFinPeriodo;
	}

	public void setCmbFechaFinPeriodo(DateComboBox cmbFechaFinPeriodo) {
		this.cmbFechaFinPeriodo = cmbFechaFinPeriodo;
	}

	public DateComboBox getCmbFechaInicioComparativo() {
		return cmbFechaInicioComparativo;
	}

	public void setCmbFechaInicioComparativo(DateComboBox cmbFechaInicioComparativo) {
		this.cmbFechaInicioComparativo = cmbFechaInicioComparativo;
	}

	public DateComboBox getCmbFechaInicioPeriodo() {
		return cmbFechaInicioPeriodo;
	}

	public void setCmbFechaInicioPeriodo(DateComboBox cmbFechaInicioPeriodo) {
		this.cmbFechaInicioPeriodo = cmbFechaInicioPeriodo;
	}

	public JComboBox getCmbNivelesVisibles() {
		return cmbNivelesVisibles;
	}

	public void setCmbNivelesVisibles(JComboBox cmbNivelesVisibles) {
		this.cmbNivelesVisibles = cmbNivelesVisibles;
	}

	public JComboBox getCmbEjercicioContable() {
		return cmbEjercicioContable;
	}

	public void setCmbEjercicioContable(JComboBox cmbEjercicioContable) {
		this.cmbEjercicioContable = cmbEjercicioContable;
	}
}
