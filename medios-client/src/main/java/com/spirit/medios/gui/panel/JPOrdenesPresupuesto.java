package com.spirit.medios.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Tue Apr 30 10:25:41 COT 2013
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author xruiz
 */
public abstract class JPOrdenesPresupuesto extends SpiritModelImpl {
	public JPOrdenesPresupuesto() {
		initComponents();
		setName("Ordenes por Presupuesto");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		rbValorBruto = new JRadioButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodosClientes = new JCheckBox();
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		btnClienteOficina = new JButton();
		cbTodosClientesOficina = new JCheckBox();
		rbValorNeto = new JRadioButton();
		lblCampana = new JLabel();
		txtCampana = new JTextField();
		btnCampana = new JButton();
		cbTodosCampana = new JCheckBox();
		btnConsultar = new JButton();
		lblMarca = new JLabel();
		cmbMarca = new JComboBox();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		cbTodosProducto = new JCheckBox();
		lblEstadoPresupuesto = new JLabel();
		cmbEstadoPresupuesto = new JComboBox();
		cbOrdenesPresupuestoPrepago = new JCheckBox();
		lblEstadoOrden = new JLabel();
		cmbEstadoOrden = new JComboBox();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		lblSubtipoProveedor = new JLabel();
		cmbSubtipoProveedor = new JComboBox();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		btnProveedor = new JButton();
		cbTodosProveedores = new JCheckBox();
		lblProveedorOficina = new JLabel();
		txtProveedorOficina = new JTextField();
		btnProveedorOficina = new JButton();
		cbTodosProveedorOficina = new JCheckBox();
		lblCreadorPor = new JLabel();
		txtCreadoPor = new JTextField();
		btnCreadoPor = new JButton();
		cbTodosCreadoPor = new JCheckBox();
		cbFiltrarCodigoPresupuesto = new JCheckBox();
		txtPresupuesto = new JTextField();
		spTblOrdenes = new JScrollPane();
		tblOrdenes = new JTable();
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
				new ColumnSpec(Sizes.DLUX3),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY3),
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
		add(btnCliente, cc.xywh(13, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosClientes ----
		cbTodosClientes.setText("Todos");
		add(cbTodosClientes, cc.xy(17, 5));

		//---- rbValorBruto ----
		rbValorBruto.setText("Valor Bruto");
		add(rbValorBruto, cc.xywh(21, 5, 3, 1));

		//---- lblClienteOficina ----
		lblClienteOficina.setText("Cliente Oficina:");
		add(lblClienteOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtClienteOficina ----
		txtClienteOficina.setEditable(false);
		add(txtClienteOficina, cc.xywh(5, 7, 7, 1));
		add(btnClienteOficina, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodosClientesOficina ----
		cbTodosClientesOficina.setText("Todos");
		add(cbTodosClientesOficina, cc.xy(17, 7));

		//---- rbValorNeto ----
		rbValorNeto.setText("Valor Neto");
		add(rbValorNeto, cc.xywh(21, 7, 3, 1));

		//---- lblCampana ----
		lblCampana.setText("Campa\u00f1a:");
		add(lblCampana, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCampana ----
		txtCampana.setEditable(false);
		add(txtCampana, cc.xywh(5, 9, 7, 1));
		add(btnCampana, cc.xywh(13, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosCampana ----
		cbTodosCampana.setText("Todos");
		add(cbTodosCampana, cc.xy(17, 9));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(21, 9, 5, 1));

		//---- lblMarca ----
		lblMarca.setText("Marca:");
		add(lblMarca, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbMarca, cc.xywh(5, 11, 7, 1));

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		add(lblProducto, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProducto ----
		txtProducto.setEditable(false);
		add(txtProducto, cc.xywh(5, 13, 7, 1));
		add(btnProducto, cc.xywh(13, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosProducto ----
		cbTodosProducto.setText("Todos");
		add(cbTodosProducto, cc.xy(17, 13));

		//---- lblEstadoPresupuesto ----
		lblEstadoPresupuesto.setText("Estado del Presupuesto:");
		add(lblEstadoPresupuesto, cc.xy(3, 15));

		//---- cmbEstadoPresupuesto ----
		cmbEstadoPresupuesto.setModel(new DefaultComboBoxModel(new String[] {
			"COTIZADO",
			"APROBADO",
			"FACTURADO",
			"PREPAGADO",
			"TODOS"
		}));
		add(cmbEstadoPresupuesto, cc.xy(5, 15));

		//---- cbOrdenesPresupuestoPrepago ----
		cbOrdenesPresupuestoPrepago.setText("Buscar Ordenes de Presupuestos \"Prepago\"");
		cbOrdenesPresupuestoPrepago.setSelected(true);
		add(cbOrdenesPresupuestoPrepago, cc.xywh(9, 15, 9, 1));

		//---- lblEstadoOrden ----
		lblEstadoOrden.setText("Estado de la Orden:");
		add(lblEstadoOrden, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstadoOrden ----
		cmbEstadoOrden.setModel(new DefaultComboBoxModel(new String[] {
			"EMITIDA",
			"ORDENADA",
			"ENVIADA",
			"INGRESADA",
			"PREPAGADA",
			"TODOS"
		}));
		add(cmbEstadoOrden, cc.xy(5, 17));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo de Proveedor:");
		add(lblTipoProveedor, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbTipoProveedor ----
		cmbTipoProveedor.setModel(new DefaultComboBoxModel(new String[] {
			"MEDIOS",
			"PRODUCCION",
			"TODOS"
		}));
		add(cmbTipoProveedor, cc.xy(5, 19));

		//---- lblSubtipoProveedor ----
		lblSubtipoProveedor.setText("Subtipo de Proveedor:");
		add(lblSubtipoProveedor, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbSubtipoProveedor, cc.xy(5, 21));

		//---- lblProveedor ----
		lblProveedor.setText("Proveedor:");
		add(lblProveedor, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedor ----
		txtProveedor.setEditable(false);
		add(txtProveedor, cc.xywh(5, 23, 7, 1));
		add(btnProveedor, cc.xywh(13, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosProveedores ----
		cbTodosProveedores.setText("Todos");
		add(cbTodosProveedores, cc.xy(17, 23));

		//---- lblProveedorOficina ----
		lblProveedorOficina.setText("Proveedor Oficina:");
		add(lblProveedorOficina, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedorOficina ----
		txtProveedorOficina.setEditable(false);
		add(txtProveedorOficina, cc.xywh(5, 25, 7, 1));
		add(btnProveedorOficina, cc.xywh(13, 25, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosProveedorOficina ----
		cbTodosProveedorOficina.setText("Todos");
		add(cbTodosProveedorOficina, cc.xy(17, 25));

		//---- lblCreadorPor ----
		lblCreadorPor.setText("Creado por:");
		add(lblCreadorPor, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtCreadoPor ----
		txtCreadoPor.setEditable(false);
		add(txtCreadoPor, cc.xywh(5, 27, 7, 1));
		add(btnCreadoPor, cc.xywh(13, 27, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosCreadoPor ----
		cbTodosCreadoPor.setText("Todos");
		add(cbTodosCreadoPor, cc.xy(17, 27));

		//---- cbFiltrarCodigoPresupuesto ----
		cbFiltrarCodigoPresupuesto.setText("Filtrar por c\u00f3digo presupuesto:");
		add(cbFiltrarCodigoPresupuesto, cc.xywh(3, 29, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtPresupuesto ----
		txtPresupuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtPresupuesto, cc.xywh(7, 29, 4, 1));

		//======== spTblOrdenes ========
		{

			//---- tblOrdenes ----
			tblOrdenes.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Fecha", "Cliente", "Presupuesto", "Estado", "Campa\u00f1a", "Marca", "Producto", "Orden", "Estado", "Tipo de Proveedor", "Proveedor", "Creado Por", "Valor"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblOrdenes.setViewportView(tblOrdenes);
		}
		add(spTblOrdenes, cc.xywh(3, 33, 27, 5));

		//---- buttonGroup2 ----
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(rbValorBruto);
		buttonGroup2.add(rbValorNeto);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JRadioButton rbValorBruto;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodosClientes;
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JButton btnClienteOficina;
	private JCheckBox cbTodosClientesOficina;
	private JRadioButton rbValorNeto;
	private JLabel lblCampana;
	private JTextField txtCampana;
	private JButton btnCampana;
	private JCheckBox cbTodosCampana;
	private JButton btnConsultar;
	private JLabel lblMarca;
	private JComboBox cmbMarca;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JCheckBox cbTodosProducto;
	private JLabel lblEstadoPresupuesto;
	private JComboBox cmbEstadoPresupuesto;
	private JCheckBox cbOrdenesPresupuestoPrepago;
	private JLabel lblEstadoOrden;
	private JComboBox cmbEstadoOrden;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JLabel lblSubtipoProveedor;
	private JComboBox cmbSubtipoProveedor;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JButton btnProveedor;
	private JCheckBox cbTodosProveedores;
	private JLabel lblProveedorOficina;
	private JTextField txtProveedorOficina;
	private JButton btnProveedorOficina;
	private JCheckBox cbTodosProveedorOficina;
	private JLabel lblCreadorPor;
	private JTextField txtCreadoPor;
	private JButton btnCreadoPor;
	private JCheckBox cbTodosCreadoPor;
	private JCheckBox cbFiltrarCodigoPresupuesto;
	private JTextField txtPresupuesto;
	private JScrollPane spTblOrdenes;
	private JTable tblOrdenes;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbMarca() {
		return cmbMarca;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public JCheckBox getCbTodosProducto() {
		return cbTodosProducto;
	}

	public JTextField getTxtProveedorOficina() {
		return txtProveedorOficina;
	}

	public JButton getBtnProveedorOficina() {
		return btnProveedorOficina;
	}

	public JCheckBox getCbTodosProveedorOficina() {
		return cbTodosProveedorOficina;
	}

	public JRadioButton getRbValorBruto() {
		return rbValorBruto;
	}

	public JRadioButton getRbValorNeto() {
		return rbValorNeto;
	}

	public JComboBox getCmbEstadoPresupuesto() {
		return cmbEstadoPresupuesto;
	}

	public JComboBox getCmbEstadoOrden() {
		return cmbEstadoOrden;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JTextField getTxtCampana() {
		return txtCampana;
	}

	public JButton getBtnCampana() {
		return btnCampana;
	}

	public JCheckBox getCbTodosCampana() {
		return cbTodosCampana;
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

	public JTextField getTxtCreadoPor() {
		return txtCreadoPor;
	}

	public JButton getBtnCreadoPor() {
		return btnCreadoPor;
	}

	public JCheckBox getCbTodosCreadoPor() {
		return cbTodosCreadoPor;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public JComboBox getCmbSubtipoProveedor() {
		return cmbSubtipoProveedor;
	}

	public JCheckBox getCbFiltrarCodigoPresupuesto() {
		return cbFiltrarCodigoPresupuesto;
	}

	public JTextField getTxtPresupuesto() {
		return txtPresupuesto;
	}

	public JScrollPane getSpTblOrdenes() {
		return spTblOrdenes;
	}

	public JTable getTblOrdenes() {
		return tblOrdenes;
	}

	public JCheckBox getCbOrdenesPresupuestoPrepago() {
		return cbOrdenesPresupuestoPrepago;
	}

	public void setCbOrdenesPresupuestoPrepago(JCheckBox cbOrdenesPresupuestoPrepago) {
		this.cbOrdenesPresupuestoPrepago = cbOrdenesPresupuestoPrepago;
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

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public JCheckBox getCbTodosClientes() {
		return cbTodosClientes;
	}
}
