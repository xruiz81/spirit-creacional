package com.spirit.contabilidad.gui.panel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import com.jidesoft.grid.TreeTable;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPReportePlanCuentas extends ReportModelImpl {
	public JPReportePlanCuentas() {
		initComponents();
		setName("Plan Cuentas");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		cbIncluirCuentasInactivas = new JCheckBox();
		btnImprimir = new JButton();
		cmbNivelesVisibles = new JComboBox();
		lblNivelesVisibles = new JLabel();
		cbMostrarSaldos = new JCheckBox();
		spTblReportePlanCuentas = new JScrollPane();
		tblReportePlanCuentas = new JTable();
		treeTblReportePlanCuentas = new TreeTable();
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
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;60dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
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
				new RowSpec(Sizes.dluY(12)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan de Cuentas:");
		add(lblPlanCuenta, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbPlanCuenta, cc.xywh(5, 3, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbIncluirCuentasInactivas ----
		cbIncluirCuentasInactivas.setText("Incluir cuentas inactivas");
		add(cbIncluirCuentasInactivas, cc.xy(11, 3));

		//---- btnImprimir ----
		add(btnImprimir, cc.xywh(15, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		add(cmbNivelesVisibles, cc.xy(5, 5));

		//---- lblNivelesVisibles ----
		lblNivelesVisibles.setText("Niveles visibles:");
		add(lblNivelesVisibles, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cbMostrarSaldos ----
		cbMostrarSaldos.setText("Mostrar saldos");
		add(cbMostrarSaldos, cc.xy(11, 5));

		//======== spTblReportePlanCuentas ========
		{
			spTblReportePlanCuentas.setViewportView(treeTblReportePlanCuentas);
		}
		add(spTblReportePlanCuentas, cc.xywh(3, 9, 14, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnImprimir.setToolTipText("Consultar Plan de Cuentas");
		btnImprimir.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		//---- tblBalance ----
		tblReportePlanCuentas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cuenta", "Valor" }) {
			boolean[] columnEditable = new boolean[] { false, false, false,
					false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
		});
	
		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		cbMostrarSaldos.setVisible(false);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JCheckBox cbIncluirCuentasInactivas;
	private JButton btnImprimir;
	private JComboBox cmbNivelesVisibles;
	private JLabel lblNivelesVisibles;
	private JCheckBox cbMostrarSaldos;
	private JScrollPane spTblReportePlanCuentas;
	private TreeTable treeTblReportePlanCuentas;
	private JTable tblReportePlanCuentas;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnImprimir() {
		return btnImprimir;
	}
	
	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}
	
	public JCheckBox getCbIncluirCuentasInactivas() {
		return cbIncluirCuentasInactivas;
	}
	
	public void setCbIncluirCuentasInactivas(JCheckBox cbIncluirCuentasInactivas) {
		this.cbIncluirCuentasInactivas = cbIncluirCuentasInactivas;
	}
	
	public JCheckBox getCbMostrarSaldos() {
		return cbMostrarSaldos;
	}
	
	public void setCbMostrarSaldos(JCheckBox cbMostrarSaldos) {
		this.cbMostrarSaldos = cbMostrarSaldos;
	}
	
	public JComboBox getCmbPlanCuenta() {
		return cmbPlanCuenta;
	}
	
	public void setCmbPlanCuenta(JComboBox cmbPlanCuenta) {
		this.cmbPlanCuenta = cmbPlanCuenta;
	}

	public TreeTable getTreeTblReportePlanCuentas() {
		return treeTblReportePlanCuentas;
	}

	public void setTreeTblReportePlanCuentas(TreeTable treeTblReportePlanCuentas) {
		this.treeTblReportePlanCuentas = treeTblReportePlanCuentas;
	}

	public JTable getTblReportePlanCuentas() {
		return tblReportePlanCuentas;
	}

	public void setTblReportePlanCuentas(JTable tblReportePlanCuentas) {
		this.tblReportePlanCuentas = tblReportePlanCuentas;
	}

	public JComboBox getCmbNivelesVisibles() {
		return cmbNivelesVisibles;
	}

	public void setCmbNivelesVisibles(JComboBox cmbNivelesVisibles) {
		this.cmbNivelesVisibles = cmbNivelesVisibles;
	}
}
