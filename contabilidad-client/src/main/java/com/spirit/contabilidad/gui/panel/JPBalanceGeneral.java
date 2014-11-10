package com.spirit.contabilidad.gui.panel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

public abstract class JPBalanceGeneral extends ReportModelImpl {
	public JPBalanceGeneral() {
		initComponents();
		setName("Balance");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblFechaCorte = new JLabel();
		cmbFechaCorte = new DateComboBox();
		spBalanceGeneral = new JScrollPane();
		tblBalance = new JTable();
		treeTblBalance = new TreeTable();
		lblPlanCuenta = new JLabel();
		btnConsultar = new JButton();
		cmbPlanCuenta = new JComboBox();
		lblEjercicioContable = new JLabel();
		cmbEjercicioContable = new JComboBox();
		cmbNivelesVisibles = new JComboBox();
		lblNivelesVisibles = new JLabel();
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
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(110)),
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
				new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblFechaCorte ----
		lblFechaCorte.setText("Fecha de Corte:");
		add(lblFechaCorte, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaCorte, cc.xywh(5, 7, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== spBalanceGeneral ========
		{
			spBalanceGeneral.setViewportView(treeTblBalance);
		}
		add(spBalanceGeneral, cc.xywh(3, 13, 13, 6));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan Cuenta:");
		add(lblPlanCuenta, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbPlanCuenta, cc.xywh(5, 3, 5, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblEjercicioContable ----
		lblEjercicioContable.setText("Ejercicio Contable:");
		add(lblEjercicioContable, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbEjercicioContable, cc.xywh(5, 5, 5, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbNivelesVisibles, cc.xy(5, 9));

		//---- lblNivelesVisibles ----
		lblNivelesVisibles.setText("Niveles visibles:");
		add(lblNivelesVisibles, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnConsultar.setToolTipText("Consultar Balance General");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		//---- tblBalance ----
		tblBalance.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cuenta", "Valor" }) {
			boolean[] columnEditable = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
		});
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblFechaCorte;
	private DateComboBox cmbFechaCorte;
	private JScrollPane spBalanceGeneral;
	private TreeTable treeTblBalance;
	private JLabel lblPlanCuenta;
	private JButton btnConsultar;
	private JComboBox cmbPlanCuenta;
	private JLabel lblEjercicioContable;
	private JComboBox cmbEjercicioContable;
	private JComboBox cmbNivelesVisibles;
	private JLabel lblNivelesVisibles;
	private JTable tblBalance;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public DateComboBox getCmbFechaCorte() {
		return cmbFechaCorte;
	}

	public void setCmbFechaCorte(DateComboBox cmbFechaCorte) {
		this.cmbFechaCorte = cmbFechaCorte;
	}

	public JComboBox getCmbEjercicioContable() {
		return cmbEjercicioContable;
	}

	public void setCmbEjercicioContable(JComboBox cmbEjercicioContable) {
		this.cmbEjercicioContable = cmbEjercicioContable;
	}
	
	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}

	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public JScrollPane getSpBalanceGeneral() {
		return spBalanceGeneral;
	}

	public void setSpBalanceGeneral(JScrollPane spBalanceGeneral) {
		this.spBalanceGeneral = spBalanceGeneral;
	}

	public TreeTable getTreeTblBalance() {
		return treeTblBalance;
	}

	public void setTreeTblBalance(TreeTable treeTblEstadoFlujoEfectivo) {
		this.treeTblBalance = treeTblEstadoFlujoEfectivo;
	}

	public JTable getTblBalance() {
		return tblBalance;
	}

	public void setTblBalance(JTable tblBalance) {
		this.tblBalance = tblBalance;
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
}
