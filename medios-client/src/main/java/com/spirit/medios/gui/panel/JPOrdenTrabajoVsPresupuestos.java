package com.spirit.medios.gui.panel;

import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Wed Feb 17 17:51:03 COT 2010
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */

public abstract class JPOrdenTrabajoVsPresupuestos extends SpiritModelImpl {
	public JPOrdenTrabajoVsPresupuestos() {
		initComponents();
		setName("Orden de Trabajo vs Presupuestos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		cbReporteCliente = new JCheckBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		spTblPresupuestos = new JScrollPane();
		tblPresupuestos = new JTable();
		btnConsultar = new JButton();
		cbReporteCodigo = new JCheckBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		cbPresupuestosConFacturaCompra = new JCheckBox();
		cbSoloFacturas = new JCheckBox();
		cbAprobadoFacturado = new JCheckBox();
		cbSinCompras = new JCheckBox();
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
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(95)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(90)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(txtCliente, cc.xywh(5, 3, 7, 1));
		add(btnCliente, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(17, 3));

		//---- cbReporteCliente ----
		cbReporteCliente.setText("Reporte por Cliente");
		add(cbReporteCliente, cc.xywh(21, 3, 3, 1));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 5));

		//======== spTblPresupuestos ========
		{
			
			//---- tblPresupuestos ----
			tblPresupuestos.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, "", "", null, null},
				},
				new String[] {
					"C\u00f3digo", "F. Creaci\u00f3n", "Estado", "Tipo", "Operador de Negocio", "F. Factura", "Preimpreso", "Valor"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblPresupuestos.setViewportView(tblPresupuestos);
		}
		add(spTblPresupuestos, cc.xywh(3, 11, 23, 5));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(17, 5));

		//---- cbReporteCodigo ----
		cbReporteCodigo.setText("Reporte por C\u00f3digo");
		add(cbReporteCodigo, cc.xywh(21, 5, 3, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbEstado, cc.xy(5, 7));

		//---- cbPresupuestosConFacturaCompra ----
		cbPresupuestosConFacturaCompra.setText("S\u00f3lo factura y compras");
		add(cbPresupuestosConFacturaCompra, cc.xywh(9, 7, 3, 1));

		//---- cbSoloFacturas ----
		cbSoloFacturas.setText("S\u00f3lo facturas");
		add(cbSoloFacturas, cc.xywh(13, 7, 5, 1));

		//---- cbAprobadoFacturado ----
		cbAprobadoFacturado.setText("Aprobado vs Facturado");
		add(cbAprobadoFacturado, cc.xywh(9, 9, 3, 1));

		//---- cbSinCompras ----
		cbSinCompras.setText("Sin Compras");
		add(cbSinCompras, cc.xywh(13, 9, 5, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JCheckBox cbReporteCliente;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JScrollPane spTblPresupuestos;
	private JTable tblPresupuestos;
	private JButton btnConsultar;
	private JCheckBox cbReporteCodigo;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JCheckBox cbPresupuestosConFacturaCompra;
	private JCheckBox cbSoloFacturas;
	private JCheckBox cbAprobadoFacturado;
	private JCheckBox cbSinCompras;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public void setBtnCliente(JButton btnCliente) {
		this.btnCliente = btnCliente;
	}

	public JCheckBox getCbTodos() {
		return cbTodos;
	}

	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}

	public JCheckBox getCbReporteCliente() {
		return cbReporteCliente;
	}

	public void setCbReporteCliente(JCheckBox cbReporteCliente) {
		this.cbReporteCliente = cbReporteCliente;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public JTable getTblPresupuestos() {
		return tblPresupuestos;
	}

	public void setTblPresupuestos(JTable tblPresupuestos) {
		this.tblPresupuestos = tblPresupuestos;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public JCheckBox getCbReporteCodigo() {
		return cbReporteCodigo;
	}

	public void setCbReporteCodigo(JCheckBox cbReporteCodigo) {
		this.cbReporteCodigo = cbReporteCodigo;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}
}
