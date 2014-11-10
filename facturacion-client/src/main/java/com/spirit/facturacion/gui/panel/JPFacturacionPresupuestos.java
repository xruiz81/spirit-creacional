package com.spirit.facturacion.gui.panel;

import javax.swing.*;
import javax.swing.table.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.combobox.*;
/*
 * Created by JFormDesigner on Mon Aug 27 17:46:22 COT 2012
 */
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author xruiz
 */
public abstract class JPFacturacionPresupuestos extends SpiritModelImpl {
	public JPFacturacionPresupuestos() {
		initComponents();
		setName("Facturacion de Presupuestos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnCliente = new JButton();
		cbTodos = new JCheckBox();
		lblProveedorOficina = new JLabel();
		txtProveedorOficina = new JTextField();
		btnProveedorOficina = new JButton();
		cbTodosProveedorOficina = new JCheckBox();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblFechasAprobacion = new JLabel();
		btnConsultar = new JButton();
		cbFiltrarCodigoPresupuesto = new JCheckBox();
		txtPresupuesto = new JTextField();
		lblTipoProveedor = new JLabel();
		cmbTipoProveedor = new JComboBox();
		cbVerFacturas = new JCheckBox();
		cbAprobadosVsFacturados = new JCheckBox();
		cbReporteInversionTotales = new JCheckBox();
		lblSubtipoProveedor = new JLabel();
		cmbSubtipoProveedor = new JComboBox();
		cbVerFacturasNegociacion = new JCheckBox();
		cbVerCarteraIngresos = new JCheckBox();
		cbReporteComisionAdicional = new JCheckBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		cbVerCompras = new JCheckBox();
		cbVerCarteraEgresos = new JCheckBox();
		cbVerCarteraIngresoTotal = new JCheckBox();
		cbVerCarteraIngresoRetencionParcial = new JCheckBox();
		cbVerCarteraIngresoParcial = new JCheckBox();
		cbVerCarteraIngresoRetencion = new JCheckBox();
		cbVerProveedoresFactura = new JCheckBox();
		cbVerCarteraEgresoTotal = new JCheckBox();
		cbVerCarteraEgresoRetencionParcial = new JCheckBox();
		cbVerCarteraEgresoParcial = new JCheckBox();
		cbVerCarteraEgresoRetencion = new JCheckBox();
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

		//---- lblProveedorOficina ----
		lblProveedorOficina.setText("Proveedor:");
		add(lblProveedorOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtProveedorOficina ----
		txtProveedorOficina.setEditable(false);
		add(txtProveedorOficina, cc.xywh(5, 5, 7, 1));
		add(btnProveedorOficina, cc.xywh(13, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- cbTodosProveedorOficina ----
		cbTodosProveedorOficina.setText("Todos");
		add(cbTodosProveedorOficina, cc.xy(17, 5));

		//---- lblFechaInicio ----
		lblFechaInicio.setText("Fecha Inicio:");
		add(lblFechaInicio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaInicio, cc.xy(5, 7));

		//---- lblFechaFin ----
		lblFechaFin.setText("Fecha Fin:");
		add(lblFechaFin, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFechaFin, cc.xy(11, 7));

		//---- lblFechasAprobacion ----
		lblFechasAprobacion.setText("(Fechas de Aprobaci\u00f3n)");
		add(lblFechasAprobacion, cc.xywh(13, 7, 7, 1));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xywh(21, 7, 5, 1));

		//---- cbFiltrarCodigoPresupuesto ----
		cbFiltrarCodigoPresupuesto.setText("Filtrar por c\u00f3digo presupuesto / pauta:");
		add(cbFiltrarCodigoPresupuesto, cc.xywh(3, 9, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtPresupuesto ----
		txtPresupuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		add(txtPresupuesto, cc.xywh(7, 9, 4, 1));

		//---- lblTipoProveedor ----
		lblTipoProveedor.setText("Tipo de Proveedor:");
		add(lblTipoProveedor, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbTipoProveedor ----
		cmbTipoProveedor.setModel(new DefaultComboBoxModel(new String[] {
			"MEDIOS",
			"PRODUCCION",
			"TODOS"
		}));
		add(cmbTipoProveedor, cc.xy(5, 11));

		//---- cbVerFacturas ----
		cbVerFacturas.setText("Ver las Facturas");
		cbVerFacturas.setSelected(true);
		add(cbVerFacturas, cc.xywh(9, 11, 3, 1));

		//---- cbAprobadosVsFacturados ----
		cbAprobadosVsFacturados.setText("Aprobados vs. Facturados");
		cbAprobadosVsFacturados.setSelected(true);
		add(cbAprobadosVsFacturados, cc.xywh(13, 11, 16, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- cbReporteInversionTotales ----
		cbReporteInversionTotales.setText("Reporte de Inversi\u00f3n (Totales)");
		add(cbReporteInversionTotales, cc.xywh(23, 11, 7, 1));

		//---- lblSubtipoProveedor ----
		lblSubtipoProveedor.setText("Subtipo:");
		add(lblSubtipoProveedor, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbSubtipoProveedor, cc.xy(5, 13));

		//---- cbVerFacturasNegociacion ----
		cbVerFacturasNegociacion.setText("Ver Facturas por Negociaci\u00f3n");
		cbVerFacturasNegociacion.setSelected(true);
		add(cbVerFacturasNegociacion, cc.xywh(9, 13, 3, 1));

		//---- cbVerCarteraIngresos ----
		cbVerCarteraIngresos.setText("Ver Cartera Ingresos");
		add(cbVerCarteraIngresos, cc.xywh(13, 13, 7, 1));

		//---- cbReporteComisionAdicional ----
		cbReporteComisionAdicional.setText("Reporte de Comisiones Adicionales");
		add(cbReporteComisionAdicional, cc.xywh(23, 13, 7, 1));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- cmbEstado ----
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {
			"TODOS",
			"COTIZADO",
			"APROBADO",
			"FACTURADO",
			"PREPAGADO"
		}));
		add(cmbEstado, cc.xy(5, 15));

		//---- cbVerCompras ----
		cbVerCompras.setText("Ver las Ordenes / Compras");
		cbVerCompras.setSelected(true);
		add(cbVerCompras, cc.xywh(9, 15, 3, 1));

		//---- cbVerCarteraEgresos ----
		cbVerCarteraEgresos.setText("Ver Cartera Egresos");
		add(cbVerCarteraEgresos, cc.xywh(13, 15, 7, 1));

		//---- cbVerCarteraIngresoTotal ----
		cbVerCarteraIngresoTotal.setText("T");
		cbVerCarteraIngresoTotal.setEnabled(false);
		add(cbVerCarteraIngresoTotal, cc.xy(21, 15));

		//---- cbVerCarteraIngresoRetencionParcial ----
		cbVerCarteraIngresoRetencionParcial.setText("R/P");
		cbVerCarteraIngresoRetencionParcial.setEnabled(false);
		add(cbVerCarteraIngresoRetencionParcial, cc.xy(23, 15));

		//---- cbVerCarteraIngresoParcial ----
		cbVerCarteraIngresoParcial.setText("P");
		cbVerCarteraIngresoParcial.setEnabled(false);
		add(cbVerCarteraIngresoParcial, cc.xy(25, 15));

		//---- cbVerCarteraIngresoRetencion ----
		cbVerCarteraIngresoRetencion.setText("R");
		cbVerCarteraIngresoRetencion.setEnabled(false);
		add(cbVerCarteraIngresoRetencion, cc.xy(27, 15));

		//---- cbVerProveedoresFactura ----
		cbVerProveedoresFactura.setText("Ver Proveedores en Factura");
		add(cbVerProveedoresFactura, cc.xywh(9, 17, 3, 1));

		//---- cbVerCarteraEgresoTotal ----
		cbVerCarteraEgresoTotal.setText("T");
		cbVerCarteraEgresoTotal.setEnabled(false);
		add(cbVerCarteraEgresoTotal, cc.xy(21, 17));

		//---- cbVerCarteraEgresoRetencionParcial ----
		cbVerCarteraEgresoRetencionParcial.setText("R/P");
		cbVerCarteraEgresoRetencionParcial.setEnabled(false);
		add(cbVerCarteraEgresoRetencionParcial, cc.xy(23, 17));

		//---- cbVerCarteraEgresoParcial ----
		cbVerCarteraEgresoParcial.setText("P");
		cbVerCarteraEgresoParcial.setEnabled(false);
		add(cbVerCarteraEgresoParcial, cc.xy(25, 17));

		//---- cbVerCarteraEgresoRetencion ----
		cbVerCarteraEgresoRetencion.setText("R");
		cbVerCarteraEgresoRetencion.setEnabled(false);
		add(cbVerCarteraEgresoRetencion, cc.xy(27, 17));

		//---- lblSegmento ----
		lblSegmento.setText("Segmento:");
		add(lblSegmento, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbSegmento, cc.xywh(5, 19, 7, 1));

		//---- lblProducto ----
		lblProducto.setText("Producto:");
		add(lblProducto, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbProducto, cc.xywh(5, 21, 7, 1));

		//======== spTblInversion ========
		{

			//---- tblInversion ----
			tblInversion.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, "", null, null, null, null, null, null, null, null, null, null, null, null},
				},
				new String[] {
					"Cliente", "F. Aprobaci\u00f3n", "Presupuesto", "SAP", "Segmento", "Producto", "Tipo", "Cartera", "F. Factura", "Factura", "Medio", "Proveedor", "Valor", "IVA", "TOTAL"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
				};
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblInversion.setViewportView(tblInversion);
		}
		add(spTblInversion, cc.xywh(3, 25, 27, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnCliente;
	private JCheckBox cbTodos;
	private JLabel lblProveedorOficina;
	private JTextField txtProveedorOficina;
	private JButton btnProveedorOficina;
	private JCheckBox cbTodosProveedorOficina;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblFechasAprobacion;
	private JButton btnConsultar;
	private JCheckBox cbFiltrarCodigoPresupuesto;
	private JTextField txtPresupuesto;
	private JLabel lblTipoProveedor;
	private JComboBox cmbTipoProveedor;
	private JCheckBox cbVerFacturas;
	private JCheckBox cbAprobadosVsFacturados;
	private JCheckBox cbReporteInversionTotales;
	private JLabel lblSubtipoProveedor;
	private JComboBox cmbSubtipoProveedor;
	private JCheckBox cbVerFacturasNegociacion;
	private JCheckBox cbVerCarteraIngresos;
	private JCheckBox cbReporteComisionAdicional;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JCheckBox cbVerCompras;
	private JCheckBox cbVerCarteraEgresos;
	private JCheckBox cbVerCarteraIngresoTotal;
	private JCheckBox cbVerCarteraIngresoRetencionParcial;
	private JCheckBox cbVerCarteraIngresoParcial;
	private JCheckBox cbVerCarteraIngresoRetencion;
	private JCheckBox cbVerProveedoresFactura;
	private JCheckBox cbVerCarteraEgresoTotal;
	private JCheckBox cbVerCarteraEgresoRetencionParcial;
	private JCheckBox cbVerCarteraEgresoParcial;
	private JCheckBox cbVerCarteraEgresoRetencion;
	private JLabel lblSegmento;
	private JComboBox cmbSegmento;
	private JLabel lblProducto;
	private JComboBox cmbProducto;
	private JScrollPane spTblInversion;
	private JTable tblInversion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbVerCarteraIngresoTotal() {
		return cbVerCarteraIngresoTotal;
	}

	public JCheckBox getCbReporteComisionAdicional() {
		return cbReporteComisionAdicional;
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

	public JCheckBox getCbVerCarteraIngresoRetencionParcial() {
		return cbVerCarteraIngresoRetencionParcial;
	}

	public JCheckBox getCbVerCarteraIngresoParcial() {
		return cbVerCarteraIngresoParcial;
	}

	public JCheckBox getCbVerCarteraIngresoRetencion() {
		return cbVerCarteraIngresoRetencion;
	}

	public JCheckBox getCbVerCarteraEgresoTotal() {
		return cbVerCarteraEgresoTotal;
	}

	public JCheckBox getCbVerCarteraEgresoRetencionParcial() {
		return cbVerCarteraEgresoRetencionParcial;
	}

	public JCheckBox getCbVerCarteraEgresoParcial() {
		return cbVerCarteraEgresoParcial;
	}

	public JCheckBox getCbVerCarteraEgresoRetencion() {
		return cbVerCarteraEgresoRetencion;
	}

	public JCheckBox getCbVerProveedoresFactura() {
		return cbVerProveedoresFactura;
	}

	public JCheckBox getCbFiltrarCodigoPresupuesto() {
		return cbFiltrarCodigoPresupuesto;
	}

	public JTextField getTxtPresupuesto() {
		return txtPresupuesto;
	}

	public JComboBox getCmbSubtipoProveedor() {
		return cmbSubtipoProveedor;
	}

	public JCheckBox getCbVerFacturasNegociacion() {
		return cbVerFacturasNegociacion;
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

	public JLabel getLblFechasAprobacion() {
		return lblFechasAprobacion;
	}

	public JLabel getLblTipoProveedor() {
		return lblTipoProveedor;
	}

	public JComboBox getCmbTipoProveedor() {
		return cmbTipoProveedor;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JCheckBox getCbVerFacturas() {
		return cbVerFacturas;
	}

	public JCheckBox getCbAprobadosVsFacturados() {
		return cbAprobadosVsFacturados;
	}

	public JCheckBox getCbVerCompras() {
		return cbVerCompras;
	}

	public JLabel getLblSegmento() {
		return lblSegmento;
	}

	public JComboBox getCmbSegmento() {
		return cmbSegmento;
	}

	public JLabel getLblProducto() {
		return lblProducto;
	}

	public JComboBox getCmbProducto() {
		return cmbProducto;
	}

	public JScrollPane getSpTblInversion() {
		return spTblInversion;
	}

	public JTable getTblInversion() {
		return tblInversion;
	}

	public JCheckBox getCbVerCarteraIngresos() {
		return cbVerCarteraIngresos;
	}

	public JCheckBox getCbVerCarteraEgresos() {
		return cbVerCarteraEgresos;
	}

	public JCheckBox getCbReporteInversionTotales() {
		return cbReporteInversionTotales;
	}
	
}
