package com.spirit.contabilidad.gui.panel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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

public abstract class JPEstadoFlujoEfectivo extends ReportModelImpl {
	public JPEstadoFlujoEfectivo() {
		initComponents();
		setName("Flujo de Efectivo");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblPeriodo = new JLabel();
		lblPlanCuenta = new JLabel();
		cmbPlanCuenta = new JComboBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		btnConsultar = new JButton();
		cmbPeriodo = new JComboBox();
		cmbFechaFin = new DateComboBox();
		lblSaldoInicial = new JLabel();
		txtSaldoInicial = new JTextField();
		lblSaldoFinal = new JLabel();
		txtSaldoFinal = new JTextField();
		lblFechaFin = new JLabel();
		scrollPane = new JScrollPane();
		tblEstadoFlujoEfectivo = new JTable();
		treeTblEstadoFlujoEfectivo = new TreeTable() {
			public Component prepareRenderer(TableCellRenderer renderer,
					int rowIndex, int vColIndex) {
				Component c = super.prepareRenderer(renderer, rowIndex,
						vColIndex);
				if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
					Color aColor = new Color(0xEEEEEE);
					c.setBackground(aColor);
				} else {
					// If not shaded, match the table's background
					c.setBackground(getBackground());
				}
				return c;
			}
		};
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(180)),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
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
				new RowSpec(Sizes.DLUY6),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblPeriodo ----
		lblPeriodo.setText("Per\u00edodo:");
		add(lblPeriodo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblPlanCuenta ----
		lblPlanCuenta.setText("Plan Cuenta:");
		add(lblPlanCuenta, cc.xywh(3, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
		add(cmbPlanCuenta, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbFechaInicio, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(15, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbPeriodo, cc.xywh(5, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		add(cmbFechaFin, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- txtSaldoInicial ----
		txtSaldoInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtSaldoInicial, cc.xywh(13, 9, 5, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- txtSaldoFinal ----
		txtSaldoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtSaldoFinal, cc.xywh(13, 21, 5, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblSaldoInicial ----
		lblSaldoInicial.setText("Saldo Inicial:");
		add(lblSaldoInicial, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//======== scrollPane ========
		{
			scrollPane.setViewportView(treeTblEstadoFlujoEfectivo);
		}
		add(scrollPane, cc.xywh(3, 13, 15, 5));

		//---- lblSaldoFinal ----
		lblSaldoFinal.setText("Saldo Final:");
		add(lblSaldoFinal, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		tblEstadoFlujoEfectivo.setModel(new DefaultTableModel(
				new Object[][] {}, new String[] { "Nombre", "Valor" }) {
			boolean[] columnEditable = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnEditable[columnIndex];
			}
		});
		
		btnConsultar.setToolTipText("Consultar Estado de Flujo de Efectivo");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblPeriodo;
	private JLabel lblPlanCuenta;
	private JComboBox cmbPlanCuenta;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JButton btnConsultar;
	private JComboBox cmbPeriodo;
	private DateComboBox cmbFechaFin;
	private JLabel lblSaldoInicial;
	private JTextField txtSaldoInicial;
	private JLabel lblSaldoFinal;
	private JTextField txtSaldoFinal;
	private JLabel lblFechaFin;
	private JScrollPane scrollPane;
	private JTable tblEstadoFlujoEfectivo;
	private TreeTable treeTblEstadoFlujoEfectivo;
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

	public JTable getTblEstadoFlujoEfectivo() {
		return tblEstadoFlujoEfectivo;
	}

	public void setTblEstadoFlujoEfectivo(JTable tblEstadoFlujoEfectivo) {
		this.tblEstadoFlujoEfectivo = tblEstadoFlujoEfectivo;
	}

	public TreeTable getTreeTblEstadoFlujoEfectivo() {
		return treeTblEstadoFlujoEfectivo;
	}

	public void setTreeTblEstadoFlujoEfectivo(
			TreeTable treeTblEstadoFlujoEfectivo) {
		this.treeTblEstadoFlujoEfectivo = treeTblEstadoFlujoEfectivo;
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

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
}
