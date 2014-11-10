package com.spirit.medios.gui.panel;
import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Mon May 30 18:52:43 COT 2011
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author JGUERRERO
 */
public abstract class JPOrdenesClientes extends SpiritModelImpl {
	public JPOrdenesClientes() {
		initComponents();
		setName("Ordenes por Cliente");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		rbOrdenarCodigoOrden = new JRadioButton();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		rbOrdenarCodigoPresupuesto = new JRadioButton();
		spTblOrdenesClientes = new JScrollPane();
		tblOrdenesClientes = new JTable();
		lblCreadoPor = new JLabel();
		txtCreadoPor = new JTextField();
		btnCreadoPor = new JButton();
		btnLimpiarCreadoPor = new JButton();
		rbOrdenarFecha = new JRadioButton();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		cbSoloCompras = new JCheckBox();
		cbVerCartera = new JCheckBox();
		btnConsultar = new JButton();
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
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(32)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX6),
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
		add(txtCliente, cc.xywh(5, 3, 11, 1));
		add(btnCliente, cc.xywh(17, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(19, 3));

		//---- rbOrdenarCodigoOrden ----
		rbOrdenarCodigoOrden.setText("Ordenar Por C\u00f3digo de Orden");
		rbOrdenarCodigoOrden.setSelected(true);
		add(rbOrdenarCodigoOrden, cc.xy(23, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xywh(11, 5, 5, 1));

		//---- rbOrdenarCodigoPresupuesto ----
		rbOrdenarCodigoPresupuesto.setText("Ordenar Por C\u00f3digo de Presupuesto");
		add(rbOrdenarCodigoPresupuesto, cc.xy(23, 5));

		//======== spTblOrdenesClientes ========
		{

			//---- tblOrdenesClientes ----
			tblOrdenesClientes.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, "", "", null, null, null, null, null, ""},
				},
				new String[] {
					"Cliente", "Presupuesto", "Orden", "Compra", "Tipo Proveedor", "Proveedor", "Marca", "Producto", "Creado por", "Fecha", "Subtotal", "Descuento", "IVA", "Total"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					true, false, false, false, false, false, false, false, false, true, true, true, true, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblOrdenesClientes.setViewportView(tblOrdenesClientes);
		}
		add(spTblOrdenesClientes, cc.xywh(3, 13, 23, 5));

		//---- lblCreadoPor ----
		lblCreadoPor.setText("Creado por:");
		add(lblCreadoPor, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCreadoPor ----
		txtCreadoPor.setEditable(false);
		txtCreadoPor.setHorizontalAlignment(SwingConstants.LEFT);
		add(txtCreadoPor, cc.xywh(5, 7, 5, 1));
		add(btnCreadoPor, cc.xywh(11, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
		add(btnLimpiarCreadoPor, cc.xywh(13, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

		//---- rbOrdenarFecha ----
		rbOrdenarFecha.setText("Ordenar Por Fecha");
		add(rbOrdenarFecha, cc.xy(23, 7));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo de Proveedor:");
		add(lblTipoProveedor, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoProveedor, cc.xy(5, 9));

		//---- cbSoloCompras ----
		cbSoloCompras.setText("S\u00f3lo las Compras");
		add(cbSoloCompras, cc.xywh(9, 9, 5, 1));

		//---- cbVerCartera ----
		cbVerCartera.setText("Ver Cartera");
		add(cbVerCartera, cc.xywh(15, 9, 3, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(23, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbOrdenarCodigoOrden);
		buttonGroup1.add(rbOrdenarCodigoPresupuesto);
		buttonGroup1.add(rbOrdenarFecha);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JRadioButton rbOrdenarCodigoOrden;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JRadioButton rbOrdenarCodigoPresupuesto;
	private JScrollPane spTblOrdenesClientes;
	private JTable tblOrdenesClientes;
	private JLabel lblCreadoPor;
	private JTextField txtCreadoPor;
	private JButton btnCreadoPor;
	private JButton btnLimpiarCreadoPor;
	private JRadioButton rbOrdenarFecha;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JCheckBox cbSoloCompras;
	private JCheckBox cbVerCartera;
	private JButton btnConsultar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JRadioButton getRbOrdenarCodigoOrden() {
		return rbOrdenarCodigoOrden;
	}

	public JRadioButton getRbOrdenarCodigoPresupuesto() {
		return rbOrdenarCodigoPresupuesto;
	}

	public JRadioButton getRbOrdenarFecha() {
		return rbOrdenarFecha;
	}

	public JCheckBox getCbVerCartera() {
		return cbVerCartera;
	}

	public JButton getBtnLimpiarCreadoPor() {
		return btnLimpiarCreadoPor;
	}

	public JTextField getTxtCreadoPor() {
		return txtCreadoPor;
	}

	public JButton getBtnCreadoPor() {
		return btnCreadoPor;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public JCheckBox getCbSoloCompras() {
		return cbSoloCompras;
	}

	public void setCbSoloCompras(JCheckBox cbSoloCompras) {
		this.cbSoloCompras = cbSoloCompras;
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
	public JCheckBox getCbTodos() {
		return cbTodos;
	}
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
	public JScrollPane getSpTblOrdenesClientes() {
		return spTblOrdenesClientes;
	}
	public JTable getTblOrdenesClientes() {
		return tblOrdenesClientes;
	}
	public JButton getBtnConsultar() {
		return btnConsultar;
	}
	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}
	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}
	public void setBtnCliente(JButton btnCliente) {
		this.btnCliente = btnCliente;
	}
	public void setCbTodos(JCheckBox cbTodos) {
		this.cbTodos = cbTodos;
	}
	public void setLblFechaInicio(JLabel lblFechaInicio) {
		this.lblFechaInicio = lblFechaInicio;
	}
	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}
	public void setLblFechaFin(JLabel lblFechaFin) {
		this.lblFechaFin = lblFechaFin;
	}
	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}
	public void setSpTblOrdenesClientes(JScrollPane spTblOrdenesClientes) {
		this.spTblOrdenesClientes = spTblOrdenesClientes;
	}
	public void setTblOrdenesClientes(JTable tblOrdenesClientes) {
		this.tblOrdenesClientes = tblOrdenesClientes;
	}
	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}
}
