package com.spirit.facturacion.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Fri Jan 10 12:36:14 COT 2014
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPLibroVentasNotasCredito extends SpiritModelImpl {
	public JPLibroVentasNotasCredito() {
		initComponents();
		setName("Libro de Ventas y Notas de Credito");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodosClientes = new JCheckBox();
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		btnClienteOficina = new JButton();
		cbTodosClientesOficina = new JCheckBox();
		rbOrdenarPorPresupuesto = new JRadioButton();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		cbTodosProveedores = new JCheckBox();
		rbOrdenarPorFactura = new JRadioButton();
		lblProveedorOficina = new JLabel();
		txtProveedorOficina = new JTextField();
		btnProveedorOficina = new JButton();
		cbTodosProveedoresOficina = new JCheckBox();
		btnConsultar = new JButton();
		cbVerIngresos = new JCheckBox();
		cbVerEgresos = new JCheckBox();
		spTblFacturacion = new JScrollPane();
		tblFacturacion = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX6),
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
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6)
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY8),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10))
			}));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 3));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 3));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 5, 7, 1));
		add(btnCliente, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosClientes ----
		cbTodosClientes.setText("Todos");
		add(cbTodosClientes, cc.xy(15, 5));

		//---- lblClienteOficina ----
		lblClienteOficina.setText("Cliente Oficina:");
		add(lblClienteOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtClienteOficina ----
		txtClienteOficina.setEditable(false);
		add(txtClienteOficina, cc.xywh(5, 7, 7, 1));
		add(btnClienteOficina, cc.xywh(13, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosClientesOficina ----
		cbTodosClientesOficina.setText("Todos");
		add(cbTodosClientesOficina, cc.xy(15, 7));

		//---- rbOrdenarPorPresupuesto ----
		rbOrdenarPorPresupuesto.setText("Ordenar por Presupuesto");
		rbOrdenarPorPresupuesto.setSelected(true);
		add(rbOrdenarPorPresupuesto, cc.xywh(19, 7, 3, 1));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedor ----
		txtProveedor.setEditable(false);
		add(txtProveedor, cc.xywh(5, 9, 7, 1));
		add(btnProveedor, cc.xywh(13, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosProveedores ----
		cbTodosProveedores.setText("Todos");
		add(cbTodosProveedores, cc.xy(15, 9));

		//---- rbOrdenarPorFactura ----
		rbOrdenarPorFactura.setText("Ordenar por Factura");
		add(rbOrdenarPorFactura, cc.xywh(19, 9, 3, 1));

		//---- lblProveedorOficina ----
		lblProveedorOficina.setText("Proveedor Oficina:");
		add(lblProveedorOficina, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedorOficina ----
		txtProveedorOficina.setEditable(false);
		add(txtProveedorOficina, cc.xywh(5, 11, 7, 1));
		add(btnProveedorOficina, cc.xywh(13, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosProveedoresOficina ----
		cbTodosProveedoresOficina.setText("Todos");
		add(cbTodosProveedoresOficina, cc.xy(15, 11));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(19, 11, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- cbVerIngresos ----
		cbVerIngresos.setText("Ver Ingresos");
		cbVerIngresos.setSelected(true);
		add(cbVerIngresos, cc.xy(5, 13));

		//---- cbVerEgresos ----
		cbVerEgresos.setText("Ver Egresos");
		cbVerEgresos.setSelected(true);
		add(cbVerEgresos, cc.xywh(9, 13, 3, 1));

		//======== spTblFacturacion ========
		{

			//---- tblFacturacion ----
			tblFacturacion.setModel(new DefaultTableModel(
				new Object[][] {
					{"", null, "", null, null, null, "", null, "", null, null, "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Cliente", "Presupuesto", "Factura Agencia", "Nota Cr\u00e9dito", "Fecha Factura", "Producto", "Tipo de Medio", "Inversi\u00f3n", "Subtotal Factura", "IVA", "Total Factura", "SAP", "Fecha de Pago del Cliente", "N\u00famero del Ingreso", "Valor del Ingreso", "Medio", "Orden de Compra", "Factura del Medio", "Fecha Factura Medio", "Subtotal Factura Medio", "IVA Factura Medio", "Total Factura Medio", "Retenci\u00f3n Renta", "Retenci\u00f3n IVA", "Valor Pagado al Medio", "Fecha de Pago de la Agencia al Medio", "Forma de Pago", "N\u00famero del Egreso", "N\u00famero del Cheque", "Nota de Cr\u00e9dito Proveedor", "Valor Nota de Cr\u00e9dito Proveedor", "Valor Aplicado a la Factura del Medio"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblFacturacion.setViewportView(tblFacturacion);
		}
		add(spTblFacturacion, cc.xywh(3, 17, 19, 5));

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbOrdenarPorPresupuesto);
		buttonGroup1.add(rbOrdenarPorFactura);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodosClientes;
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JButton btnClienteOficina;
	private JCheckBox cbTodosClientesOficina;
	private JRadioButton rbOrdenarPorPresupuesto;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JCheckBox cbTodosProveedores;
	private JRadioButton rbOrdenarPorFactura;
	private JLabel lblProveedorOficina;
	private JTextField txtProveedorOficina;
	private JButton btnProveedorOficina;
	private JCheckBox cbTodosProveedoresOficina;
	private JButton btnConsultar;
	private JCheckBox cbVerIngresos;
	private JCheckBox cbVerEgresos;
	private JScrollPane spTblFacturacion;
	private JTable tblFacturacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblFechaInicio() {
		return lblFechaInicio;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public JLabel getLblFechaFin() {
		return lblFechaFin;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JCheckBox getCbTodosClientes() {
		return cbTodosClientes;
	}

	public JLabel getLblClienteOficina() {
		return lblClienteOficina;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public JButton getBtnClienteOficina() {
		return btnClienteOficina;
	}

	public JCheckBox getCbTodosClientesOficina() {
		return cbTodosClientesOficina;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JButton getBtnProveedor() {
		return btnProveedor;
	}

	public JCheckBox getCbTodosProveedores() {
		return cbTodosProveedores;
	}

	public JLabel getLblProveedorOficina() {
		return lblProveedorOficina;
	}

	public JTextField getTxtProveedorOficina() {
		return txtProveedorOficina;
	}

	public JButton getBtnProveedorOficina() {
		return btnProveedorOficina;
	}

	public JCheckBox getCbTodosProveedoresOficina() {
		return cbTodosProveedoresOficina;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblFacturacion() {
		return spTblFacturacion;
	}

	public JTable getTblFacturacion() {
		return tblFacturacion;
	}

	public JCheckBox getCbVerIngresos() {
		return cbVerIngresos;
	}

	public JCheckBox getCbVerEgresos() {
		return cbVerEgresos;
	}

	public JRadioButton getRbOrdenarPorPresupuesto() {
		return rbOrdenarPorPresupuesto;
	}

	public JRadioButton getRbOrdenarPorFactura() {
		return rbOrdenarPorFactura;
	}
}
