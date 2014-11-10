package com.spirit.crm.gui.panel;

import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
import com.spirit.client.model.MantenimientoModelImpl;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
/*
 * Created by JFormDesigner on Sat Mar 14 14:04:27 COT 2009
 */

public abstract class JPReporteGastoElectoral extends MantenimientoModelImpl {
	public JPReporteGastoElectoral() {
		initComponents();
		setName("Reporte Gasto Electoral");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCampana = new JLabel();
		cmbCampana = new JComboBox();
		cbTodaCampana = new JCheckBox();
		cbReporteCampana = new JCheckBox();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		cbTodoTipo = new JCheckBox();
		cbReporteTipo = new JCheckBox();
		lblProveedor = new JLabel();
		cmbProveedor = new JComboBox();
		cbTodoProveedor = new JCheckBox();
		cbReporteIngresos = new JCheckBox();
		lblFechaInicio = new JLabel();
		lblFechaFin = new JLabel();
		cmbFechaInicio = new DateComboBox();
		btnConsultar = new JButton();
		cmbFechaFin = new DateComboBox();
		spTblGastos = new JScrollPane();
		tblGastos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(70)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(10))
			},
			new RowSpec[] {
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
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
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCampana ----
		lblCampana.setText("Campa\u00f1a:");
		add(lblCampana, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCampana, cc.xywh(5, 3, 3, 1));

		//---- cbTodaCampana ----
		cbTodaCampana.setText("Toda Campa\u00f1a");
		add(cbTodaCampana, cc.xy(11, 3));

		//---- cbReporteCampana ----
		cbReporteCampana.setText("Reporte por Campa\u00f1a");
		add(cbReporteCampana, cc.xy(15, 3));

		//---- lblTipo ----
		lblTipo.setText("Tipo:");
		add(lblTipo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipo, cc.xywh(5, 5, 3, 1));

		//---- cbTodoTipo ----
		cbTodoTipo.setText("Todo Tipo");
		add(cbTodoTipo, cc.xy(11, 5));

		//---- cbReporteTipo ----
		cbReporteTipo.setText("Reporte por Tipo");
		add(cbReporteTipo, cc.xy(15, 5));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbProveedor, cc.xywh(5, 7, 3, 1));

		//---- cbTodoProveedor ----
		cbTodoProveedor.setText("Todo Proveedor");
		add(cbTodoProveedor, cc.xy(11, 7));

		//---- cbReporteProveedor ----
		cbReporteIngresos.setText("Reporte de Ingresos");
		add(cbReporteIngresos, cc.xy(15, 7));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(7, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 9));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(17, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(9, 9, 5, 1));

		//======== spTblGastos ========
		{
			
			//---- tblGastos ----
			tblGastos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null},
				},
				new String[] {
					"Campa\u00f1a", "Fecha", "Tipo", "Producto", "Proveedor", "Cantidad", "Costo Unitario", "Inv. sin factura"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblGastos.setViewportView(tblGastos);
		}
		add(spTblGastos, cc.xywh(3, 13, 15, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/consultar.png"));
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblGastos.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblGastos.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblGastos.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCampana;
	private JComboBox cmbCampana;
	private JCheckBox cbTodaCampana;
	private JCheckBox cbReporteCampana;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JCheckBox cbTodoTipo;
	private JCheckBox cbReporteTipo;
	private JLabel lblProveedor;
	private JComboBox cmbProveedor;
	private JCheckBox cbTodoProveedor;
	private JCheckBox cbReporteIngresos;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaInicio;
	private JButton btnConsultar;
	private DateComboBox cmbFechaFin;
	private JScrollPane spTblGastos;
	private JTable tblGastos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JCheckBox getCbReporteCampana() {
		return cbReporteCampana;
	}

	public void setCbReporteCampana(JCheckBox cbReporteCampana) {
		this.cbReporteCampana = cbReporteCampana;
	}

	public JCheckBox getCbReporteTipo() {
		return cbReporteTipo;
	}

	public void setCbReporteTipo(JCheckBox cbReporteTipo) {
		this.cbReporteTipo = cbReporteTipo;
	}

	public JCheckBox getCbTodaCampana() {
		return cbTodaCampana;
	}

	public void setCbTodaCampana(JCheckBox cbTodaCampana) {
		this.cbTodaCampana = cbTodaCampana;
	}

	public JCheckBox getCbTodoProveedor() {
		return cbTodoProveedor;
	}

	public void setCbTodoProveedor(JCheckBox cbTodoProveedor) {
		this.cbTodoProveedor = cbTodoProveedor;
	}

	public JCheckBox getCbTodoTipo() {
		return cbTodoTipo;
	}

	public void setCbTodoTipo(JCheckBox cbTodoTipo) {
		this.cbTodoTipo = cbTodoTipo;
	}

	public JComboBox getCmbCampana() {
		return cmbCampana;
	}

	public void setCmbCampana(JComboBox cmbCampana) {
		this.cmbCampana = cmbCampana;
	}

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

	public JComboBox getCmbProveedor() {
		return cmbProveedor;
	}

	public void setCmbProveedor(JComboBox cmbProveedor) {
		this.cmbProveedor = cmbProveedor;
	}

	public JComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public JTable getTblGastos() {
		return tblGastos;
	}

	public void setTblGastos(JTable tblGastos) {
		this.tblGastos = tblGastos;
	}

	public JCheckBox getCbReporteIngresos() {
		return cbReporteIngresos;
	}

	public void setCbReporteIngresos(JCheckBox cbReporteIngresos) {
		this.cbReporteIngresos = cbReporteIngresos;
	}
}
