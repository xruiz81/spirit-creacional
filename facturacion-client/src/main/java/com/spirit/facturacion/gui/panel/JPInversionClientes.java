package com.spirit.facturacion.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Wed Apr 18 16:59:20 COT 2012
 */
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPInversionClientes extends SpiritModelImpl {
	public JPInversionClientes() {
		initComponents();
		setName("Inversion Clientes");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblFechasAprobacion = new JLabel();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		btnConsultar = new JButton();
		lblTipoMedio = new JLabel();
		cmbTipoMedio = new JComboBox();
		cbAprobadosVsFacturados = new JCheckBox();
		cbVerFacturas = new JCheckBox();
		cbVerProveedores = new JCheckBox();
		lblSegmento = new JLabel();
		cmbSegmento = new JComboBox();
		lblProducto = new JLabel();
		cmbProducto = new JComboBox();
		spTblInversion = new JScrollPane();
		tblInversion = new JTable();
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

		//---- txtCliente ----
		txtCliente.setEditable(false);
		add(txtCliente, cc.xywh(5, 3, 7, 1));
		add(btnCliente, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- cbTodos ----
		cbTodos.setText("Todos");
		add(cbTodos, cc.xy(17, 3));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 5));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 5));

		//---- lblFechasAprobacion ----
		lblFechasAprobacion.setText("(Fechas de Aprobaci\u00f3n)");
		add(lblFechasAprobacion, cc.xywh(13, 5, 7, 1));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo de Proveedor:");
		add(lblTipoProveedor, cc.xy(3, 7));

		//---- cmbTipoProveedor ----
		cmbTipoProveedor.setModel(new DefaultComboBoxModel(new String[] {
			"MEDIOS",
			"PRODUCCION",
			"TODOS"
		}));
		add(cmbTipoProveedor, cc.xy(5, 7));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODOS",
			"COTIZADO",
			"APROBADO",
			"FACTURADO",
			"PREPAGADO"
		}));
		add(cmbEstado, cc.xy(11, 7));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(19, 7));

		//---- lblTipoMedio ----
		lblTipoMedio.setText("Medio:");
		add(lblTipoMedio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoMedio, cc.xy(5, 9));

		//---- cbAprobadosVsFacturados ----
		cbAprobadosVsFacturados.setText("Aprobados vs. Facturados");
		cbAprobadosVsFacturados.setSelected(true);
		add(cbAprobadosVsFacturados, cc.xywh(9, 9, 3, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- cbVerFacturas ----
		cbVerFacturas.setText("Ver todas las facturas");
		add(cbVerFacturas, cc.xy(5, 11));

		//---- cbVerProveedores ----
		cbVerProveedores.setText("Ver todos los proveedores");
		add(cbVerProveedores, cc.xywh(9, 11, 3, 1));

		//---- lblSegmento ----
		lblSegmento.setText("Segmento:");
		add(lblSegmento, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbSegmento, cc.xywh(5, 13, 7, 1));

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		add(lblProducto, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbProducto, cc.xywh(5, 15, 7, 1));

		//======== spTblInversion ========
		{

			//---- tblInversion ----
			tblInversion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, "", null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Cliente", "F. Factura", "Factura", "F. Aprobaci\u00f3n", "Presupuesto", "SAP", "Segmento", "Producto", "Medio", "Proveedor", "Valor", "IVA", "TOTAL"
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
			spTblInversion.setViewportView(tblInversion);
		}
		add(spTblInversion, cc.xywh(3, 19, 19, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblFechasAprobacion;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JButton btnConsultar;
	private JLabel lblTipoMedio;
	private JComboBox cmbTipoMedio;
	private JCheckBox cbAprobadosVsFacturados;
	private JCheckBox cbVerFacturas;
	private JCheckBox cbVerProveedores;
	private JLabel lblSegmento;
	private JComboBox cmbSegmento;
	private JLabel lblProducto;
	private JComboBox cmbProducto;
	private JScrollPane spTblInversion;
	private JTable tblInversion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbVerFacturas() {
		return cbVerFacturas;
	}

	public JCheckBox getCbVerProveedores() {
		return cbVerProveedores;
	}

	public JLabel getLblFechasAprobacion() {
		return lblFechasAprobacion;
	}

	public JCheckBox getCbAprobadosVsFacturados() {
		return cbAprobadosVsFacturados;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public JLabel getLblSegmento() {
		return lblSegmento;
	}

	public JLabel getLblProducto() {
		return lblProducto;
	}

	public JComboBox getCmbTipoMedio() {
		return cmbTipoMedio;
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

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public JComboBox getCmbSegmento() {
		return cmbSegmento;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JComboBox getCmbProducto() {
		return cmbProducto;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JScrollPane getSpTblInversion() {
		return spTblInversion;
	}

	public JTable getTblInversion() {
		return tblInversion;
	}
}
