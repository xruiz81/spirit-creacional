package com.spirit.medios.gui.panel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.CheckBoxList;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;
/*
 * Created by JFormDesigner on Thu Sep 09 16:17:49 COT 2010
 */



/**
 * @author xruiz
 */
public abstract class JPPresupuesto extends SpiritModelImpl {
	public JPPresupuesto() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpPresupuesto = new JideTabbedPane();
		spGeneral = new JScrollPane();
		panelPresupuesto = new JPanel();
		txtCodigo = new JTextField();
		lblcodigo = new JLabel();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblConceptoPresupuesto = new JLabel();
		txtConceptoPresupuesto = new JTextField();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		cbSinIVA = new JCheckBox();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		cbPrepago = new JCheckBox();
		lblClienteOficina = new JLabel();
		txtOficina = new JTextField();
		btnBuscarOficina = new JButton();
		lblFechaCreacion = new JLabel();
		cmbFechaCreacion = new DateComboBox();
		lblReferencia = new JLabel();
		cmbTipoReferencia = new JComboBox();
		txtReferencia = new JTextField();
		btnReferencia = new JButton();
		btnLimpiarReferencia = new JButton();
		lblFechaPresupuesto = new JLabel();
		cmbFechaPresupuesto = new DateComboBox();
		lblTipoOrden = new JLabel();
		cmbTipoOrden = new JComboBox();
		lblFechaAprobacion = new JLabel();
		cmbFechaAprobacion = new DateComboBox();
		lblOrdenTrabajo = new JLabel();
		cmbOrdenTrabajo = new JComboBox();
		cmbOrdenTrabajoDetalle = new JComboBox();
		lblOrdenTrabajoDetId = new JLabel();
		txtSubTipoOrden = new JTextField();
		lblModificacion = new JLabel();
		txtModificacion = new JTextField();
		lblTemaCampana = new JLabel();
		txtTemaCampana = new JTextField();
		lblContadorPresupuestos = new JLabel();
		txtContadorPresupuestos = new JTextField();
		lblDiasValidez = new JLabel();
		txtDiasValidez = new JTextField();
		lblAutorizacionSAP = new JLabel();
		txtAutorizacionSAP = new JTextField();
		lblFormaPago = new JLabel();
		cmbFormaPago = new JComboBox();
		spTxtDescripcionOTdetalle = new JScrollPane();
		txtDescripcionOTdetalle = new JTextArea();
		lblCabecera = new JLabel();
		scrollPane1 = new JScrollPane();
		txtCabecera = new JTextArea();
		panelProductoCliente = new JPanel();
		btnSeleccionarTodo = new JButton();
		btnDeseleccionarTodo = new JButton();
		spCbListProductos = new JScrollPane();
		cbListProductos = new CheckBoxList();
		spPresupuestoDetalle = new JScrollPane();
		panelPresupuestoDetalle = new JPanel();
		lblProveedorId = new JLabel();
		txtProveedor = new JTextField();
		btnBuscarProveedor = new JButton();
		cbNegociacionDirecta = new JCheckBox();
		lblPorcentajeNegociacionDirecta = new JLabel();
		txtPorcentajeNegociacionDirecta = new JTextField();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		cbComisionPura = new JCheckBox();
		lblPorcentajeComisionPura = new JLabel();
		txtPorcentajeComisionPura = new JTextField();
		cbComisionAdicional = new JCheckBox();
		lblPorcentajeComisionAdicional = new JLabel();
		txtPorcentajeComisionAdicional = new JTextField();
		lblConceptoPresupuestoDetalle = new JLabel();
		spConceptoPresupuestoDetalle = new JScrollPane();
		txtConceptoPresupuestoDetalle = new JTextArea();
		lblOrden = new JLabel();
		txtOrden = new JTextField();
		lblFechaPublicacion = new JLabel();
		cmbFechaPublicacion = new DateComboBox();
		lblCompra = new JLabel();
		lblVenta = new JLabel();
		lblPrecioCompra = new JLabel();
		txtPrecioCompra = new JTextField();
		lblPorcentajeDescuentoEspecialCompra = new JLabel();
		txtPorcentajeDescuentoEspecialCompra = new JTextField();
		lblPorcentajeDescuentoEspecialVenta = new JLabel();
		txtPorcentajeDescuentoEspecialVenta = new JTextField();
		lblPrecioVenta = new JLabel();
		txtPrecioVenta = new JTextField();
		lblPorcentajeDsctoAgenciaCompra = new JLabel();
		txtPorcentajeDsctoAgenciaCompra = new JTextField();
		lblPorcentajeDsctoAgenciaVenta = new JLabel();
		txtPorcentajeDsctoAgenciaVenta = new JTextField();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		lblPorcentajeDescuentosVariosCompra = new JLabel();
		txtPorcentajeDescuentosVariosCompra = new JTextField();
		lblPorcentajeDescuentosVariosVenta = new JLabel();
		txtPorcentajeDescuentosVariosVenta = new JTextField();
		lblPorcentajeNotaCredito = new JLabel();
		txtPorcentajeNotaCredito = new JTextField();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		btnReorganizarTabla = new JButton();
		scPresupuestoDetalle = new JScrollPane();
		tblPresupuestoDetalle = new JTable();
		lblSubTotalCompra = new JLabel();
		txtSubTotalCompra = new JTextField();
		lblSubTotalVenta = new JLabel();
		txtSubTotalVenta = new JTextField();
		lblDescuentoEspecialTotalCompra = new JLabel();
		txtDescuentoEspecialTotalCompra = new JTextField();
		lblDescuentoEspecialTotalVenta = new JLabel();
		txtDescuentoEspecialTotalVenta = new JTextField();
		lblSubTotal2Compra = new JLabel();
		txtSubTotal2Compra = new JTextField();
		lblSubTotal2Venta = new JLabel();
		txtSubTotal2Venta = new JTextField();
		lblDsctoAgenciaCompra = new JLabel();
		txtDsctoAgenciaCompra = new JTextField();
		lblDsctoAgenciaVenta = new JLabel();
		txtDsctoAgenciaVenta = new JTextField();
		lblDescuentosVariosCompra = new JLabel();
		txtDescuentosVariosCompra = new JTextField();
		lblDescuentosVariosVenta = new JLabel();
		txtDescuentosVariosVenta = new JTextField();
		lblIvaTotalCompra = new JLabel();
		txtIvaTotalCompra = new JTextField();
		lblComisionAgencia = new JLabel();
		txtPorcentajeComision = new JTextField();
		lblValorComision = new JLabel();
		txtValorComision = new JTextField();
		lblTotalCompra = new JLabel();
		txtTotalCompra = new JTextField();
		lblTotalVenta = new JLabel();
		lblIvaVenta = new JLabel();
		txtIvaVenta = new JTextField();
		txtTotalVenta = new JTextField();
		spPresupuestoProveedor = new JScrollPane();
		panelPresupuestoProveedor = new JPanel();
		lblProveedorP = new JLabel();
		txtProveedorP = new JTextField();
		lblProductoP = new JLabel();
		txtProductoP = new JTextField();
		lblConceptoPresupuestoDetalleP = new JLabel();
		spConceptoPresupuestoDetalleP = new JScrollPane();
		txtConceptoPresupuestoDetalleP = new JTextArea();
		lblPrecioVentaP = new JLabel();
		txtPrecioVentaP = new JTextField();
		txtCantidadP = new JTextField();
		lblCantidadP = new JLabel();
		panel13 = new JPanel();
		btnAgregarDetalleP = new JButton();
		btnActualizarDetalleP = new JButton();
		btnEliminarDetalleP = new JButton();
		scPresupuestoDetalleP = new JScrollPane();
		tblPresupuestoDetalleP = new JTable();
		lblTotalReporte = new JLabel();
		txtTotalReporte = new JTextField();
		panelArchivos = new JPanel();
		lblTipoArchivo = new JLabel();
		cmbTipoArchivo = new JComboBox();
		lblArchivo = new JLabel();
		txtArchivo = new JTextField();
		btnBuscarArchivo = new JButton();
		panel4 = new JPanel();
		btnAgregarArchivo = new JButton();
		btnActualizarArchivo = new JButton();
		btnEliminarArchivo = new JButton();
		btnVerArchivo = new JButton();
		spTblArchivos = new JScrollPane();
		tblArchivo = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Presupuestos");
		setLayout(new FormLayout(
			"default:grow",
			"default"));

		//======== jtpPresupuesto ========
		{

			//======== spGeneral ========
			{

				//======== panelPresupuesto ========
				{
					panelPresupuesto.setForeground(Color.black);
					panelPresupuesto.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("min(default;100dlu):grow"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
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
							new RowSpec(Sizes.dluY(35)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(38), FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					panelPresupuesto.add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblcodigo ----
					lblcodigo.setText("C\u00f3digo:");
					panelPresupuesto.add(lblcodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- lblEstado ----
					lblEstado.setText("Estado:");
					panelPresupuesto.add(lblEstado, cc.xywh(17, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(cmbEstado, cc.xywh(19, 3, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

					//---- lblConceptoPresupuesto ----
					lblConceptoPresupuesto.setText("Concepto:");
					panelPresupuesto.add(lblConceptoPresupuesto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(txtConceptoPresupuesto, cc.xywh(5, 5, 7, 1));

					//---- lblCorporacion ----
					lblCorporacion.setText("Corporaci\u00f3n:");
					panelPresupuesto.add(lblCorporacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelPresupuesto.add(txtCorporacion, cc.xywh(5, 7, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelPresupuesto.add(btnBuscarCorporacion, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- cbSinIVA ----
					cbSinIVA.setText("Sin IVA");
					panelPresupuesto.add(cbSinIVA, cc.xy(19, 7));

					//---- lblCliente ----
					lblCliente.setText("Cliente:");
					panelPresupuesto.add(lblCliente, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(txtCliente, cc.xywh(5, 9, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelPresupuesto.add(btnBuscarCliente, cc.xywh(13, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- cbPrepago ----
					cbPrepago.setText("Prepago");
					panelPresupuesto.add(cbPrepago, cc.xy(19, 9));

					//---- lblClienteOficina ----
					lblClienteOficina.setText("Oficina del Cliente:");
					panelPresupuesto.add(lblClienteOficina, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(txtOficina, cc.xywh(5, 11, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelPresupuesto.add(btnBuscarOficina, cc.xywh(13, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblFechaCreacion ----
					lblFechaCreacion.setText("F. de Creaci\u00f3n:");
					panelPresupuesto.add(lblFechaCreacion, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- cmbFechaCreacion ----
					cmbFechaCreacion.setEditable(false);
					cmbFechaCreacion.setShowNoneButton(false);
					panelPresupuesto.add(cmbFechaCreacion, cc.xy(19, 11));

					//---- lblTipoOrden ----
					lblTipoOrden.setText("Tipo de Orden:");
					panelPresupuesto.add(lblTipoOrden, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelPresupuesto.add(cmbTipoOrden, cc.xywh(5, 13, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

					//---- lblFechaPresupuesto ----
					lblFechaPresupuesto.setText("F. de Presupuesto:");
					panelPresupuesto.add(lblFechaPresupuesto, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- cmbFechaPresupuesto ----
					cmbFechaPresupuesto.setShowNoneButton(false);
					cmbFechaPresupuesto.setEditable(false);
					panelPresupuesto.add(cmbFechaPresupuesto, cc.xy(19, 13));

					//---- lblOrdenTrabajo ----
					lblOrdenTrabajo.setText("Orden de Trabajo:");
					panelPresupuesto.add(lblOrdenTrabajo, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(cmbOrdenTrabajo, cc.xywh(5, 15, 7, 1));

					//---- lblFechaAprobacion ----
					lblFechaAprobacion.setText("F. de Aprobaci\u00f3n:");
					panelPresupuesto.add(lblFechaAprobacion, cc.xywh(17, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- cmbFechaAprobacion ----
					cmbFechaAprobacion.setEditable(false);
					cmbFechaAprobacion.setShowNoneButton(false);
					panelPresupuesto.add(cmbFechaAprobacion, cc.xy(19, 15));
					panelPresupuesto.add(cmbOrdenTrabajoDetalle, cc.xywh(5, 17, 7, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblOrdenTrabajoDetId ----
					lblOrdenTrabajoDetId.setText("Detalle Orden de Trabajo:");
					panelPresupuesto.add(lblOrdenTrabajoDetId, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(txtSubTipoOrden, cc.xy(13, 17));

					//---- lblModificacion ----
					lblModificacion.setText("Modificado:");
					panelPresupuesto.add(lblModificacion, cc.xywh(17, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelPresupuesto.add(txtModificacion, cc.xywh(19, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblTemaCampana ----
					lblTemaCampana.setText("Tema:");
					panelPresupuesto.add(lblTemaCampana, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(txtTemaCampana, cc.xywh(5, 19, 7, 1));

					//---- lblContadorPresupuestos ----
					lblContadorPresupuestos.setText("Presupuestos:");
					panelPresupuesto.add(lblContadorPresupuestos, cc.xywh(17, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtContadorPresupuestos ----
					txtContadorPresupuestos.setEditable(false);
					panelPresupuesto.add(txtContadorPresupuestos, cc.xy(19, 19));

					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panelPresupuesto.add(lblReferencia, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- cmbTipoReferencia ----
					cmbTipoReferencia.setModel(new DefaultComboBoxModel(new String[] {
						"NINGUNO",
						"PRESUPUESTO",
						"PLAN DE MEDIOS"
					}));
					panelPresupuesto.add(cmbTipoReferencia, cc.xy(5, 21));

					//---- txtReferencia ----
					txtReferencia.setEditable(false);
					panelPresupuesto.add(txtReferencia, cc.xy(7, 21));
					panelPresupuesto.add(btnReferencia, cc.xywh(9, 21, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					panelPresupuesto.add(btnLimpiarReferencia, cc.xywh(11, 21, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

					//---- lblAutorizacionSAP ----
					lblAutorizacionSAP.setText("SAP:");
					panelPresupuesto.add(lblAutorizacionSAP, cc.xywh(17, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtAutorizacionSAP ----
					txtAutorizacionSAP.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuesto.add(txtAutorizacionSAP, cc.xy(19, 21));

					//---- lblDiasValidez ----
					lblDiasValidez.setText("D\u00edas de validez:");
					panelPresupuesto.add(lblDiasValidez, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDiasValidez ----
					txtDiasValidez.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuesto.add(txtDiasValidez, cc.xy(5, 23));

					//---- lblFormaPago ----
					lblFormaPago.setText("Forma de Pago:");
					panelPresupuesto.add(lblFormaPago, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuesto.add(cmbFormaPago, cc.xy(5, 25));

					//======== spTxtDescripcionOTdetalle ========
					{

						//---- txtDescripcionOTdetalle ----
						txtDescripcionOTdetalle.setLineWrap(true);
						spTxtDescripcionOTdetalle.setViewportView(txtDescripcionOTdetalle);
					}
					panelPresupuesto.add(spTxtDescripcionOTdetalle, cc.xywh(7, 25, 13, 3));

					//---- lblCabecera ----
					lblCabecera.setText("Observaci\u00f3n:");
					panelPresupuesto.add(lblCabecera, cc.xywh(3, 29, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//======== scrollPane1 ========
					{

						//---- txtCabecera ----
						txtCabecera.setLineWrap(true);
						scrollPane1.setViewportView(txtCabecera);
					}
					panelPresupuesto.add(scrollPane1, cc.xywh(3, 31, 17, 1));
				}
				spGeneral.setViewportView(panelPresupuesto);
			}
			jtpPresupuesto.addTab("General", spGeneral);


			//======== panelProductoCliente ========
			{
				panelProductoCliente.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						new RowSpec(RowSpec.CENTER, Sizes.dluY(80), FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));

				//---- btnSeleccionarTodo ----
				btnSeleccionarTodo.setText("Seleccionar todo");
				panelProductoCliente.add(btnSeleccionarTodo, cc.xy(7, 3));

				//---- btnDeseleccionarTodo ----
				btnDeseleccionarTodo.setText("Deseleccionar todo");
				panelProductoCliente.add(btnDeseleccionarTodo, cc.xy(7, 5));

				//======== spCbListProductos ========
				{
					spCbListProductos.setViewportView(cbListProductos);
				}
				panelProductoCliente.add(spCbListProductos, cc.xywh(3, 3, 3, 7));
			}
			jtpPresupuesto.addTab("Productos", panelProductoCliente);


			//======== spPresupuestoDetalle ========
			{

				//======== panelPresupuestoDetalle ========
				{
					panelPresupuestoDetalle.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX9),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(25)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(Sizes.dluY(70)),
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
							new RowSpec(RowSpec.TOP, Sizes.dluY(12), FormSpec.NO_GROW),
							new RowSpec(Sizes.DLUY6),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.dluY(70), FormSpec.DEFAULT_GROW),
							new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
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
							new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
						}));

					//---- lblProveedorId ----
					lblProveedorId.setText("Proveedor:");
					panelPresupuestoDetalle.add(lblProveedorId, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuestoDetalle.add(txtProveedor, cc.xywh(5, 3, 7, 1));
					panelPresupuestoDetalle.add(btnBuscarProveedor, cc.xywh(13, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- cbNegociacionDirecta ----
					cbNegociacionDirecta.setText("Facturaci\u00f3n Directa");
					panelPresupuestoDetalle.add(cbNegociacionDirecta, cc.xywh(19, 3, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- lblPorcentajeNegociacionDirecta ----
					lblPorcentajeNegociacionDirecta.setText("[%]:");
					panelPresupuestoDetalle.add(lblPorcentajeNegociacionDirecta, cc.xywh(23, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeNegociacionDirecta ----
					txtPorcentajeNegociacionDirecta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeNegociacionDirecta, cc.xy(25, 3));

					//---- lblProducto ----
					lblProducto.setText("Producto:");
					panelPresupuestoDetalle.add(lblProducto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelPresupuestoDetalle.add(txtProducto, cc.xywh(5, 5, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
					panelPresupuestoDetalle.add(btnBuscarProducto, cc.xywh(13, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- cbComisionPura ----
					cbComisionPura.setText("Comisi\u00f3n Directa");
					panelPresupuestoDetalle.add(cbComisionPura, cc.xywh(19, 5, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- lblPorcentajeComisionPura ----
					lblPorcentajeComisionPura.setText("[%]:");
					panelPresupuestoDetalle.add(lblPorcentajeComisionPura, cc.xywh(23, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeComisionPura ----
					txtPorcentajeComisionPura.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeComisionPura, cc.xy(25, 5));

					//---- cbComisionAdicional ----
					cbComisionAdicional.setText("Comisi\u00f3n Adicional");
					panelPresupuestoDetalle.add(cbComisionAdicional, cc.xy(29, 5));

					//---- lblPorcentajeComisionAdicional ----
					lblPorcentajeComisionAdicional.setText("[%]:");
					panelPresupuestoDetalle.add(lblPorcentajeComisionAdicional, cc.xy(31, 5));

					//---- txtPorcentajeComisionAdicional ----
					txtPorcentajeComisionAdicional.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeComisionAdicional, cc.xy(33, 5));

					//---- lblConceptoPresupuestoDetalle ----
					lblConceptoPresupuestoDetalle.setText("Concepto:");
					panelPresupuestoDetalle.add(lblConceptoPresupuestoDetalle, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//======== spConceptoPresupuestoDetalle ========
					{

						//---- txtConceptoPresupuestoDetalle ----
						txtConceptoPresupuestoDetalle.setWrapStyleWord(false);
						txtConceptoPresupuestoDetalle.setRows(6);
						txtConceptoPresupuestoDetalle.setLineWrap(true);
						txtConceptoPresupuestoDetalle.setEditable(false);
						spConceptoPresupuestoDetalle.setViewportView(txtConceptoPresupuestoDetalle);
					}
					panelPresupuestoDetalle.add(spConceptoPresupuestoDetalle, cc.xywh(5, 7, 34, 5));

					//---- lblOrden ----
					lblOrden.setText("Orden:");
					panelPresupuestoDetalle.add(lblOrden, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPresupuestoDetalle.add(txtOrden, cc.xy(5, 13));

					//---- lblFechaPublicacion ----
					lblFechaPublicacion.setText("Fecha de Publicaci\u00f3n:");
					panelPresupuestoDetalle.add(lblFechaPublicacion, cc.xywh(21, 13, 7, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- cmbFechaPublicacion ----
					cmbFechaPublicacion.setEditable(false);
					panelPresupuestoDetalle.add(cmbFechaPublicacion, cc.xywh(29, 13, 4, 1));

					//---- lblCompra ----
					lblCompra.setText("Compra - Proveedor:");
					lblCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblCompra, cc.xywh(11, 15, 3, 1));

					//---- lblVenta ----
					lblVenta.setText("Venta - Cliente:");
					lblVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblVenta, cc.xy(19, 15));

					//---- lblPrecioCompra ----
					lblPrecioCompra.setText("Precio de compra:");
					panelPresupuestoDetalle.add(lblPrecioCompra, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtPrecioCompra ----
					txtPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPrecioCompra, cc.xywh(5, 17, 3, 1));

					//---- lblPorcentajeDescuentoEspecialCompra ----
					lblPorcentajeDescuentoEspecialCompra.setText("Dscto. Especial [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeDescuentoEspecialCompra, cc.xywh(11, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeDescuentoEspecialCompra ----
					txtPorcentajeDescuentoEspecialCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeDescuentoEspecialCompra, cc.xywh(13, 17, 3, 1));

					//---- lblPorcentajeDescuentoEspecialVenta ----
					lblPorcentajeDescuentoEspecialVenta.setText("Dscto. Especial [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeDescuentoEspecialVenta, cc.xywh(19, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeDescuentoEspecialVenta ----
					txtPorcentajeDescuentoEspecialVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeDescuentoEspecialVenta, cc.xywh(21, 17, 5, 1));

					//---- lblPrecioVenta ----
					lblPrecioVenta.setText("Precio de venta:");
					panelPresupuestoDetalle.add(lblPrecioVenta, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtPrecioVenta ----
					txtPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPrecioVenta, cc.xywh(5, 19, 3, 1));

					//---- lblPorcentajeDsctoAgenciaCompra ----
					lblPorcentajeDsctoAgenciaCompra.setText("Dscto. Agencia [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeDsctoAgenciaCompra, cc.xywh(11, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeDsctoAgenciaCompra ----
					txtPorcentajeDsctoAgenciaCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeDsctoAgenciaCompra, cc.xywh(13, 19, 3, 1));

					//---- lblPorcentajeDsctoAgenciaVenta ----
					lblPorcentajeDsctoAgenciaVenta.setText("Dscto. Agencia [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeDsctoAgenciaVenta, cc.xywh(19, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtPorcentajeDsctoAgenciaVenta ----
					txtPorcentajeDsctoAgenciaVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeDsctoAgenciaVenta, cc.xywh(21, 19, 5, 1));

					//---- lblCantidad ----
					lblCantidad.setText("Cantidad:");
					panelPresupuestoDetalle.add(lblCantidad, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtCantidad ----
					txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtCantidad, cc.xy(5, 21));

					//---- lblPorcentajeDescuentosVariosCompra ----
					lblPorcentajeDescuentosVariosCompra.setText("Dsctos. Varios [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeDescuentosVariosCompra, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeDescuentosVariosCompra ----
					txtPorcentajeDescuentosVariosCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeDescuentosVariosCompra, cc.xywh(13, 21, 3, 1));

					//---- lblPorcentajeDescuentosVariosVenta ----
					lblPorcentajeDescuentosVariosVenta.setText("Dsctos. Varios [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeDescuentosVariosVenta, cc.xywh(19, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeDescuentosVariosVenta ----
					txtPorcentajeDescuentosVariosVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeDescuentosVariosVenta, cc.xywh(21, 21, 5, 1));

					//---- lblPorcentajeNotaCredito ----
					lblPorcentajeNotaCredito.setText("Nota de Cr\u00e9dito [%]:");
					panelPresupuestoDetalle.add(lblPorcentajeNotaCredito, cc.xy(11, 23));

					//---- txtPorcentajeNotaCredito ----
					txtPorcentajeNotaCredito.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeNotaCredito, cc.xywh(13, 23, 3, 1));

					//======== panel1 ========
					{
						panel1.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- btnAgregarDetalle ----
						btnAgregarDetalle.setText("A");
						panel1.add(btnAgregarDetalle, cc.xy(1, 1));

						//---- btnActualizarDetalle ----
						btnActualizarDetalle.setText("U");
						panel1.add(btnActualizarDetalle, cc.xy(3, 1));

						//---- btnEliminarDetalle ----
						btnEliminarDetalle.setText("D");
						panel1.add(btnEliminarDetalle, cc.xy(5, 1));
					}
					panelPresupuestoDetalle.add(panel1, cc.xywh(3, 26, 3, 1));

					//---- btnReorganizarTabla ----
					btnReorganizarTabla.setText("Reorganizar Tabla");
					panelPresupuestoDetalle.add(btnReorganizarTabla, cc.xywh(11, 26, 3, 1));

					//======== scPresupuestoDetalle ========
					{

						//---- tblPresupuestoDetalle ----
						tblPresupuestoDetalle.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null, null, null, null, null, null, null, "", null, null, null, null},
							},
							new String[] {
								" ", "Concepto", "Cant.", "Precio Compra", "Precio Venta", "Dscto. Venta", "Dscto. Compra", "Orden", "Orden Compra", "% F.D.", "% C.D.", "% D.V.C.", "% D.E.C.", "% D.V.V.", "% D.E.V.", "% C.A.", "F. Publicaci\u00f3n"
							}
						) {
							Class[] columnTypes = new Class[] {
								Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
							};
							boolean[] columnEditable = new boolean[] {
								true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
							};
							@Override
							public Class<?> getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblPresupuestoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblPresupuestoDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						scPresupuestoDetalle.setViewportView(tblPresupuestoDetalle);
					}
					panelPresupuestoDetalle.add(scPresupuestoDetalle, cc.xywh(3, 28, 36, 3, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblSubTotalCompra ----
					lblSubTotalCompra.setText("SubTotal Compra:");
					lblSubTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblSubTotalCompra, cc.xywh(3, 32, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtSubTotalCompra ----
					txtSubTotalCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					txtSubTotalCompra.setEditable(false);
					panelPresupuestoDetalle.add(txtSubTotalCompra, cc.xywh(5, 32, 3, 1));

					//---- lblSubTotalVenta ----
					lblSubTotalVenta.setText("SubTotal Venta:");
					lblSubTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblSubTotalVenta, cc.xywh(11, 32, 3, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtSubTotalVenta ----
					txtSubTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					txtSubTotalVenta.setEditable(false);
					panelPresupuestoDetalle.add(txtSubTotalVenta, cc.xywh(15, 32, 5, 1));

					//---- lblDescuentoEspecialTotalCompra ----
					lblDescuentoEspecialTotalCompra.setText("Dscto. Especial:");
					panelPresupuestoDetalle.add(lblDescuentoEspecialTotalCompra, cc.xywh(3, 34, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDescuentoEspecialTotalCompra ----
					txtDescuentoEspecialTotalCompra.setEditable(false);
					txtDescuentoEspecialTotalCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtDescuentoEspecialTotalCompra, cc.xywh(5, 34, 3, 1));

					//---- lblDescuentoEspecialTotalVenta ----
					lblDescuentoEspecialTotalVenta.setText("Dscto. Especial:");
					panelPresupuestoDetalle.add(lblDescuentoEspecialTotalVenta, cc.xywh(11, 34, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDescuentoEspecialTotalVenta ----
					txtDescuentoEspecialTotalVenta.setEditable(false);
					txtDescuentoEspecialTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtDescuentoEspecialTotalVenta, cc.xywh(15, 34, 5, 1));

					//---- lblSubTotal2Compra ----
					lblSubTotal2Compra.setText("SubTotal 2:");
					lblSubTotal2Compra.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblSubTotal2Compra, cc.xywh(3, 36, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtSubTotal2Compra ----
					txtSubTotal2Compra.setEditable(false);
					txtSubTotal2Compra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtSubTotal2Compra, cc.xywh(5, 36, 3, 1));

					//---- lblSubTotal2Venta ----
					lblSubTotal2Venta.setText("SubTotal 2:");
					lblSubTotal2Venta.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblSubTotal2Venta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(lblSubTotal2Venta, cc.xywh(11, 36, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtSubTotal2Venta ----
					txtSubTotal2Venta.setEditable(false);
					txtSubTotal2Venta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtSubTotal2Venta, cc.xywh(15, 36, 5, 1));

					//---- lblDsctoAgenciaCompra ----
					lblDsctoAgenciaCompra.setText("Dscto. Agencia:");
					panelPresupuestoDetalle.add(lblDsctoAgenciaCompra, cc.xywh(3, 38, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDsctoAgenciaCompra ----
					txtDsctoAgenciaCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					txtDsctoAgenciaCompra.setEditable(false);
					panelPresupuestoDetalle.add(txtDsctoAgenciaCompra, cc.xywh(5, 38, 3, 1));

					//---- lblDsctoAgenciaVenta ----
					lblDsctoAgenciaVenta.setText("Dscto. Agencia:");
					panelPresupuestoDetalle.add(lblDsctoAgenciaVenta, cc.xywh(11, 38, 3, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtDsctoAgenciaVenta ----
					txtDsctoAgenciaVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					txtDsctoAgenciaVenta.setEditable(false);
					panelPresupuestoDetalle.add(txtDsctoAgenciaVenta, cc.xywh(15, 38, 5, 1));

					//---- lblDescuentosVariosCompra ----
					lblDescuentosVariosCompra.setText("Dsctos. Varios:");
					panelPresupuestoDetalle.add(lblDescuentosVariosCompra, cc.xywh(3, 40, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDescuentosVariosCompra ----
					txtDescuentosVariosCompra.setEditable(false);
					txtDescuentosVariosCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtDescuentosVariosCompra, cc.xywh(5, 40, 3, 1));

					//---- lblDescuentosVariosVenta ----
					lblDescuentosVariosVenta.setText("Dsctos. Varios:");
					panelPresupuestoDetalle.add(lblDescuentosVariosVenta, cc.xywh(11, 40, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtDescuentosVariosVenta ----
					txtDescuentosVariosVenta.setEditable(false);
					txtDescuentosVariosVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtDescuentosVariosVenta, cc.xywh(15, 40, 5, 1));

					//---- lblIvaTotalCompra ----
					lblIvaTotalCompra.setText("IVA Compra:");
					panelPresupuestoDetalle.add(lblIvaTotalCompra, cc.xywh(3, 42, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtIvaTotalCompra ----
					txtIvaTotalCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					txtIvaTotalCompra.setEditable(false);
					panelPresupuestoDetalle.add(txtIvaTotalCompra, cc.xywh(5, 42, 3, 1));

					//---- lblComisionAgencia ----
					lblComisionAgencia.setText("Comisi\u00f3n Agencia [%]:");
					panelPresupuestoDetalle.add(lblComisionAgencia, cc.xywh(11, 42, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtPorcentajeComision ----
					txtPorcentajeComision.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtPorcentajeComision, cc.xy(15, 42));

					//---- lblValorComision ----
					lblValorComision.setText("[$]:");
					panelPresupuestoDetalle.add(lblValorComision, cc.xy(17, 42));

					//---- txtValorComision ----
					txtValorComision.setEditable(false);
					txtValorComision.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoDetalle.add(txtValorComision, cc.xy(19, 42));

					//---- lblTotalCompra ----
					lblTotalCompra.setText("Total Compra:");
					lblTotalCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblTotalCompra, cc.xywh(3, 44, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtTotalCompra ----
					txtTotalCompra.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
					txtTotalCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					txtTotalCompra.setEditable(false);
					panelPresupuestoDetalle.add(txtTotalCompra, cc.xywh(5, 44, 3, 1));

					//---- lblTotalVenta ----
					lblTotalVenta.setText("Total Venta:");
					lblTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelPresupuestoDetalle.add(lblTotalVenta, cc.xywh(11, 46, 3, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- lblIvaVenta ----
					lblIvaVenta.setText("IVA Venta:");
					panelPresupuestoDetalle.add(lblIvaVenta, cc.xywh(11, 44, 3, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtIvaVenta ----
					txtIvaVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					txtIvaVenta.setEditable(false);
					panelPresupuestoDetalle.add(txtIvaVenta, cc.xywh(15, 44, 5, 1));

					//---- txtTotalVenta ----
					txtTotalVenta.setHorizontalAlignment(SwingConstants.RIGHT);
					txtTotalVenta.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
					txtTotalVenta.setEditable(false);
					panelPresupuestoDetalle.add(txtTotalVenta, cc.xywh(15, 46, 5, 1));
				}
				spPresupuestoDetalle.setViewportView(panelPresupuestoDetalle);
			}
			jtpPresupuesto.addTab("Detalle", spPresupuestoDetalle);


			//======== spPresupuestoProveedor ========
			{

				//======== panelPresupuestoProveedor ========
				{
					panelPresupuestoProveedor.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(40)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(90)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
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
							new RowSpec(Sizes.dluY(70)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.TOP, Sizes.DLUY7, FormSpec.NO_GROW),
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.dluY(70), FormSpec.DEFAULT_GROW),
							new RowSpec(RowSpec.TOP, Sizes.dluY(10), FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
						}));

					//---- lblProveedorP ----
					lblProveedorP.setText("Proveedor:");
					panelPresupuestoProveedor.add(lblProveedorP, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtProveedorP ----
					txtProveedorP.setEditable(false);
					panelPresupuestoProveedor.add(txtProveedorP, cc.xywh(5, 3, 9, 1));

					//---- lblProductoP ----
					lblProductoP.setText("Producto:");
					panelPresupuestoProveedor.add(lblProductoP, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtProductoP ----
					txtProductoP.setEditable(false);
					panelPresupuestoProveedor.add(txtProductoP, cc.xywh(5, 5, 9, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblConceptoPresupuestoDetalleP ----
					lblConceptoPresupuestoDetalleP.setText("Concepto:");
					panelPresupuestoProveedor.add(lblConceptoPresupuestoDetalleP, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//======== spConceptoPresupuestoDetalleP ========
					{

						//---- txtConceptoPresupuestoDetalleP ----
						txtConceptoPresupuestoDetalleP.setWrapStyleWord(false);
						txtConceptoPresupuestoDetalleP.setRows(6);
						txtConceptoPresupuestoDetalleP.setLineWrap(true);
						txtConceptoPresupuestoDetalleP.setEditable(true);
						spConceptoPresupuestoDetalleP.setViewportView(txtConceptoPresupuestoDetalleP);
					}
					panelPresupuestoProveedor.add(spConceptoPresupuestoDetalleP, cc.xywh(5, 7, 21, 5));

					//---- lblPrecioVentaP ----
					lblPrecioVentaP.setText("Precio de venta:");
					panelPresupuestoProveedor.add(lblPrecioVentaP, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtPrecioVentaP ----
					txtPrecioVentaP.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoProveedor.add(txtPrecioVentaP, cc.xywh(5, 13, 3, 1));

					//---- txtCantidadP ----
					txtCantidadP.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoProveedor.add(txtCantidadP, cc.xy(13, 13));

					//---- lblCantidadP ----
					lblCantidadP.setText("Cantidad:");
					panelPresupuestoProveedor.add(lblCantidadP, cc.xywh(11, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//======== panel13 ========
					{
						panel13.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- btnAgregarDetalleP ----
						btnAgregarDetalleP.setText("A");
						panel13.add(btnAgregarDetalleP, cc.xy(1, 1));

						//---- btnActualizarDetalleP ----
						btnActualizarDetalleP.setText("U");
						panel13.add(btnActualizarDetalleP, cc.xy(3, 1));

						//---- btnEliminarDetalleP ----
						btnEliminarDetalleP.setText("D");
						panel13.add(btnEliminarDetalleP, cc.xy(5, 1));
					}
					panelPresupuestoProveedor.add(panel13, cc.xywh(3, 16, 19, 1));

					//======== scPresupuestoDetalleP ========
					{

						//---- tblPresupuestoDetalleP ----
						tblPresupuestoDetalleP.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null},
							},
							new String[] {
								"Concepto", "Cantidad", "Precio Compra", "Precio Venta", "Dscto. Venta", "Dscto. Compra"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false
							};
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblPresupuestoDetalleP.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblPresupuestoDetalleP.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						scPresupuestoDetalleP.setViewportView(tblPresupuestoDetalleP);
					}
					panelPresupuestoProveedor.add(scPresupuestoDetalleP, cc.xywh(3, 18, 25, 3, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblTotalReporte ----
					lblTotalReporte.setText("Total:");
					panelPresupuestoProveedor.add(lblTotalReporte, cc.xywh(21, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtTotalReporte ----
					txtTotalReporte.setEditable(false);
					txtTotalReporte.setHorizontalAlignment(SwingConstants.RIGHT);
					panelPresupuestoProveedor.add(txtTotalReporte, cc.xywh(23, 23, 3, 1));
				}
				spPresupuestoProveedor.setViewportView(panelPresupuestoProveedor);
			}
			jtpPresupuesto.addTab("Reporte", spPresupuestoProveedor);


			//======== panelArchivos ========
			{
				panelArchivos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						new RowSpec(Sizes.DLUY6),
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

				//---- lblTipoArchivo ----
				lblTipoArchivo.setText("Tipo Archivo:");
				panelArchivos.add(lblTipoArchivo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivos.add(cmbTipoArchivo, cc.xy(5, 3));

				//---- lblArchivo ----
				lblArchivo.setText("Archivo:");
				panelArchivos.add(lblArchivo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

				//---- txtArchivo ----
				txtArchivo.setEditable(false);
				panelArchivos.add(txtArchivo, cc.xywh(5, 5, 3, 1));
				panelArchivos.add(btnBuscarArchivo, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== panel4 ========
				{
					panel4.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));

					//---- btnAgregarArchivo ----
					btnAgregarArchivo.setText("A");
					panel4.add(btnAgregarArchivo, cc.xy(1, 1));

					//---- btnActualizarArchivo ----
					btnActualizarArchivo.setText("U");
					panel4.add(btnActualizarArchivo, cc.xy(3, 1));

					//---- btnEliminarArchivo ----
					btnEliminarArchivo.setText("D");
					panel4.add(btnEliminarArchivo, cc.xy(5, 1));
				}
				panelArchivos.add(panel4, cc.xywh(3, 9, 3, 1));
				panelArchivos.add(btnVerArchivo, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

				//======== spTblArchivos ========
				{

					//---- tblArchivo ----
					tblArchivo.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null},
						},
						new String[] {
							"Tipo Archivo", "Archivo"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblArchivos.setViewportView(tblArchivo);
				}
				panelArchivos.add(spTblArchivos, cc.xywh(3, 11, 11, 5));
			}
			jtpPresupuesto.addTab("Archivos", panelArchivos);

		}
		add(jtpPresupuesto, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpPresupuesto;
	private JScrollPane spGeneral;
	private JPanel panelPresupuesto;
	private JTextField txtCodigo;
	private JLabel lblcodigo;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblConceptoPresupuesto;
	private JTextField txtConceptoPresupuesto;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JCheckBox cbSinIVA;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JCheckBox cbPrepago;
	private JLabel lblClienteOficina;
	private JTextField txtOficina;
	private JButton btnBuscarOficina;
	private JLabel lblFechaCreacion;
	private DateComboBox cmbFechaCreacion;
	private JLabel lblReferencia;
	private JComboBox cmbTipoReferencia;
	private JTextField txtReferencia;
	private JButton btnReferencia;
	private JButton btnLimpiarReferencia;
	private JLabel lblFechaPresupuesto;
	private DateComboBox cmbFechaPresupuesto;
	private JLabel lblTipoOrden;
	private JComboBox cmbTipoOrden;
	private JLabel lblFechaAprobacion;
	private DateComboBox cmbFechaAprobacion;
	private JLabel lblOrdenTrabajo;
	private JComboBox cmbOrdenTrabajo;
	private JComboBox cmbOrdenTrabajoDetalle;
	private JLabel lblOrdenTrabajoDetId;
	private JTextField txtSubTipoOrden;
	private JLabel lblModificacion;
	private JTextField txtModificacion;
	private JLabel lblTemaCampana;
	private JTextField txtTemaCampana;
	private JLabel lblContadorPresupuestos;
	private JTextField txtContadorPresupuestos;
	private JLabel lblDiasValidez;
	private JTextField txtDiasValidez;
	private JLabel lblAutorizacionSAP;
	private JTextField txtAutorizacionSAP;
	private JLabel lblFormaPago;
	private JComboBox cmbFormaPago;
	private JScrollPane spTxtDescripcionOTdetalle;
	private JTextArea txtDescripcionOTdetalle;
	private JLabel lblCabecera;
	private JScrollPane scrollPane1;
	private JTextArea txtCabecera;
	private JPanel panelProductoCliente;
	private JButton btnSeleccionarTodo;
	private JButton btnDeseleccionarTodo;
	private JScrollPane spCbListProductos;
	private CheckBoxList cbListProductos;
	private JScrollPane spPresupuestoDetalle;
	private JPanel panelPresupuestoDetalle;
	private JLabel lblProveedorId;
	private JTextField txtProveedor;
	private JButton btnBuscarProveedor;
	private JCheckBox cbNegociacionDirecta;
	private JLabel lblPorcentajeNegociacionDirecta;
	private JTextField txtPorcentajeNegociacionDirecta;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JCheckBox cbComisionPura;
	private JLabel lblPorcentajeComisionPura;
	private JTextField txtPorcentajeComisionPura;
	private JCheckBox cbComisionAdicional;
	private JLabel lblPorcentajeComisionAdicional;
	private JTextField txtPorcentajeComisionAdicional;
	private JLabel lblConceptoPresupuestoDetalle;
	private JScrollPane spConceptoPresupuestoDetalle;
	private JTextArea txtConceptoPresupuestoDetalle;
	private JLabel lblOrden;
	private JTextField txtOrden;
	private JLabel lblFechaPublicacion;
	private DateComboBox cmbFechaPublicacion;
	private JLabel lblCompra;
	private JLabel lblVenta;
	private JLabel lblPrecioCompra;
	private JTextField txtPrecioCompra;
	private JLabel lblPorcentajeDescuentoEspecialCompra;
	private JTextField txtPorcentajeDescuentoEspecialCompra;
	private JLabel lblPorcentajeDescuentoEspecialVenta;
	private JTextField txtPorcentajeDescuentoEspecialVenta;
	private JLabel lblPrecioVenta;
	private JTextField txtPrecioVenta;
	private JLabel lblPorcentajeDsctoAgenciaCompra;
	private JTextField txtPorcentajeDsctoAgenciaCompra;
	private JLabel lblPorcentajeDsctoAgenciaVenta;
	private JTextField txtPorcentajeDsctoAgenciaVenta;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JLabel lblPorcentajeDescuentosVariosCompra;
	private JTextField txtPorcentajeDescuentosVariosCompra;
	private JLabel lblPorcentajeDescuentosVariosVenta;
	private JTextField txtPorcentajeDescuentosVariosVenta;
	private JLabel lblPorcentajeNotaCredito;
	private JTextField txtPorcentajeNotaCredito;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JButton btnReorganizarTabla;
	private JScrollPane scPresupuestoDetalle;
	private JTable tblPresupuestoDetalle;
	private JLabel lblSubTotalCompra;
	private JTextField txtSubTotalCompra;
	private JLabel lblSubTotalVenta;
	private JTextField txtSubTotalVenta;
	private JLabel lblDescuentoEspecialTotalCompra;
	private JTextField txtDescuentoEspecialTotalCompra;
	private JLabel lblDescuentoEspecialTotalVenta;
	private JTextField txtDescuentoEspecialTotalVenta;
	private JLabel lblSubTotal2Compra;
	private JTextField txtSubTotal2Compra;
	private JLabel lblSubTotal2Venta;
	private JTextField txtSubTotal2Venta;
	private JLabel lblDsctoAgenciaCompra;
	private JTextField txtDsctoAgenciaCompra;
	private JLabel lblDsctoAgenciaVenta;
	private JTextField txtDsctoAgenciaVenta;
	private JLabel lblDescuentosVariosCompra;
	private JTextField txtDescuentosVariosCompra;
	private JLabel lblDescuentosVariosVenta;
	private JTextField txtDescuentosVariosVenta;
	private JLabel lblIvaTotalCompra;
	private JTextField txtIvaTotalCompra;
	private JLabel lblComisionAgencia;
	private JTextField txtPorcentajeComision;
	private JLabel lblValorComision;
	private JTextField txtValorComision;
	private JLabel lblTotalCompra;
	private JTextField txtTotalCompra;
	private JLabel lblTotalVenta;
	private JLabel lblIvaVenta;
	private JTextField txtIvaVenta;
	private JTextField txtTotalVenta;
	private JScrollPane spPresupuestoProveedor;
	private JPanel panelPresupuestoProveedor;
	private JLabel lblProveedorP;
	private JTextField txtProveedorP;
	private JLabel lblProductoP;
	private JTextField txtProductoP;
	private JLabel lblConceptoPresupuestoDetalleP;
	private JScrollPane spConceptoPresupuestoDetalleP;
	private JTextArea txtConceptoPresupuestoDetalleP;
	private JLabel lblPrecioVentaP;
	private JTextField txtPrecioVentaP;
	private JTextField txtCantidadP;
	private JLabel lblCantidadP;
	private JPanel panel13;
	private JButton btnAgregarDetalleP;
	private JButton btnActualizarDetalleP;
	private JButton btnEliminarDetalleP;
	private JScrollPane scPresupuestoDetalleP;
	private JTable tblPresupuestoDetalleP;
	private JLabel lblTotalReporte;
	private JTextField txtTotalReporte;
	private JPanel panelArchivos;
	private JLabel lblTipoArchivo;
	private JComboBox cmbTipoArchivo;
	private JLabel lblArchivo;
	private JTextField txtArchivo;
	private JButton btnBuscarArchivo;
	private JPanel panel4;
	private JButton btnAgregarArchivo;
	private JButton btnActualizarArchivo;
	private JButton btnEliminarArchivo;
	private JButton btnVerArchivo;
	private JScrollPane spTblArchivos;
	private JTable tblArchivo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JCheckBox getCbPrepago() {
		return cbPrepago;
	}

	public JComboBox getCmbTipoReferencia() {
		return cmbTipoReferencia;
	}

	public JButton getBtnLimpiarReferencia() {
		return btnLimpiarReferencia;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public JButton getBtnReferencia() {
		return btnReferencia;
	}

	public JButton getBtnReorganizarTabla() {
		return btnReorganizarTabla;
	}

	public DateComboBox getCmbFechaPublicacion() {
		return cmbFechaPublicacion;
	}

	public JTextField getTxtDescuentosVariosVenta() {
		return txtDescuentosVariosVenta;
	}

	public JTextField getTxtPorcentajeDescuentoEspecialVenta() {
		return txtPorcentajeDescuentoEspecialVenta;
	}

	public JTextField getTxtPorcentajeDescuentosVariosVenta() {
		return txtPorcentajeDescuentosVariosVenta;
	}

	public JTextField getTxtSubTotal2Compra() {
		return txtSubTotal2Compra;
	}

	public JTextField getTxtSubTotal2Venta() {
		return txtSubTotal2Venta;
	}

	public JTextField getTxtPorcentajeDescuentoEspecialCompra() {
		return txtPorcentajeDescuentoEspecialCompra;
	}

	public JTextField getTxtDescuentoEspecialTotalCompra() {
		return txtDescuentoEspecialTotalCompra;
	}

	public JTextField getTxtDescuentoEspecialTotalVenta() {
		return txtDescuentoEspecialTotalVenta;
	}

	public JLabel getLblDescuentosVariosVenta() {
		return lblDescuentosVariosVenta;
	}

	public JTextField getTxtDsctoAgenciaVenta() {
		return txtDsctoAgenciaVenta;
	}

	public JTextField getTxtDescuentosVariosCompra() {
		return txtDescuentosVariosCompra;
	}

	public JTextField getTxtPorcentajeDsctoAgenciaVenta() {
		return txtPorcentajeDsctoAgenciaVenta;
	}

	public JTextField getTxtPorcentajeDescuentosVariosCompra() {
		return txtPorcentajeDescuentosVariosCompra;
	}

	public JTextField getTxtAutorizacionSAP() {
		return txtAutorizacionSAP;
	}

	public JTextField getTxtPorcentajeNegociacionDirecta() {
		return txtPorcentajeNegociacionDirecta;
	}

	public JideTabbedPane getJtpPresupuesto() {
		return jtpPresupuesto;
	}

	public JPanel getPanelPresupuesto() {
		return panelPresupuesto;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblcodigo() {
		return lblcodigo;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JLabel getLblConceptoPresupuesto() {
		return lblConceptoPresupuesto;
	}

	public JTextField getTxtConceptoPresupuesto() {
		return txtConceptoPresupuesto;
	}

	public JLabel getLblCorporacion() {
		return lblCorporacion;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public JCheckBox getCbSinIVA() {
		return cbSinIVA;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public JLabel getLblFechaCreacion() {
		return lblFechaCreacion;
	}

	public DateComboBox getCmbFechaCreacion() {
		return cmbFechaCreacion;
	}

	public JLabel getLblClienteOficina() {
		return lblClienteOficina;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public JButton getBtnBuscarOficina() {
		return btnBuscarOficina;
	}

	public JLabel getLblFechaPresupuesto() {
		return lblFechaPresupuesto;
	}

	public DateComboBox getCmbFechaPresupuesto() {
		return cmbFechaPresupuesto;
	}

	public JLabel getLblTipoOrden() {
		return lblTipoOrden;
	}

	public JComboBox getCmbTipoOrden() {
		return cmbTipoOrden;
	}

	public JLabel getLblFechaAprobacion() {
		return lblFechaAprobacion;
	}

	public DateComboBox getCmbFechaAprobacion() {
		return cmbFechaAprobacion;
	}

	public JLabel getLblOrdenTrabajo() {
		return lblOrdenTrabajo;
	}

	public JComboBox getCmbOrdenTrabajo() {
		return cmbOrdenTrabajo;
	}

	public JComboBox getCmbOrdenTrabajoDetalle() {
		return cmbOrdenTrabajoDetalle;
	}

	public JLabel getLblOrdenTrabajoDetId() {
		return lblOrdenTrabajoDetId;
	}

	public JTextField getTxtSubTipoOrden() {
		return txtSubTipoOrden;
	}

	public JLabel getLblModificacion() {
		return lblModificacion;
	}

	public JTextField getTxtModificacion() {
		return txtModificacion;
	}

	public JLabel getLblTemaCampana() {
		return lblTemaCampana;
	}

	public JTextField getTxtTemaCampana() {
		return txtTemaCampana;
	}

	public JLabel getLblContadorPresupuestos() {
		return lblContadorPresupuestos;
	}

	public JTextField getTxtContadorPresupuestos() {
		return txtContadorPresupuestos;
	}

	public JLabel getLblDiasValidez() {
		return lblDiasValidez;
	}

	public JTextField getTxtDiasValidez() {
		return txtDiasValidez;
	}

	public JLabel getLblFormaPago() {
		return lblFormaPago;
	}

	public JComboBox getCmbFormaPago() {
		return cmbFormaPago;
	}

	public JScrollPane getSpTxtDescripcionOTdetalle() {
		return spTxtDescripcionOTdetalle;
	}

	public JTextArea getTxtDescripcionOTdetalle() {
		return txtDescripcionOTdetalle;
	}

	public JLabel getLblCabecera() {
		return lblCabecera;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public JTextArea getTxtCabecera() {
		return txtCabecera;
	}

	public JPanel getPanelProductoCliente() {
		return panelProductoCliente;
	}

	public JButton getBtnSeleccionarTodo() {
		return btnSeleccionarTodo;
	}

	public JButton getBtnDeseleccionarTodo() {
		return btnDeseleccionarTodo;
	}

	public JScrollPane getSpCbListProductos() {
		return spCbListProductos;
	}

	public CheckBoxList getCbListProductos() {
		return cbListProductos;
	}

	public JScrollPane getSpPresupuestoDetalle() {
		return spPresupuestoDetalle;
	}

	public JPanel getPanelPresupuestoDetalle() {
		return panelPresupuestoDetalle;
	}

	public JLabel getLblProveedorId() {
		return lblProveedorId;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public JLabel getLblProducto() {
		return lblProducto;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public JLabel getLblConceptoPresupuestoDetalle() {
		return lblConceptoPresupuestoDetalle;
	}

	public JScrollPane getSpConceptoPresupuestoDetalle() {
		return spConceptoPresupuestoDetalle;
	}

	public JTextArea getTxtConceptoPresupuestoDetalle() {
		return txtConceptoPresupuestoDetalle;
	}

	public JLabel getLblPrecioCompra() {
		return lblPrecioCompra;
	}

	public JTextField getTxtPrecioCompra() {
		return txtPrecioCompra;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JLabel getLblOrden() {
		return lblOrden;
	}

	public JTextField getTxtOrden() {
		return txtOrden;
	}

	public JLabel getLblPrecioVenta() {
		return lblPrecioVenta;
	}

	public JTextField getTxtPrecioVenta() {
		return txtPrecioVenta;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public JScrollPane getScPresupuestoDetalle() {
		return scPresupuestoDetalle;
	}

	public JTable getTblPresupuestoDetalle() {
		return tblPresupuestoDetalle;
	}

	public JLabel getLblSubTotalCompra() {
		return lblSubTotalCompra;
	}

	public JTextField getTxtSubTotalCompra() {
		return txtSubTotalCompra;
	}

	public JLabel getLblSubTotalVenta() {
		return lblSubTotalVenta;
	}

	public JTextField getTxtSubTotalVenta() {
		return txtSubTotalVenta;
	}

	public JLabel getLblIvaTotalCompra() {
		return lblIvaTotalCompra;
	}

	public JTextField getTxtIvaTotalCompra() {
		return txtIvaTotalCompra;
	}

	public JLabel getLblComisionAgencia() {
		return lblComisionAgencia;
	}

	public JTextField getTxtPorcentajeComision() {
		return txtPorcentajeComision;
	}

	public JLabel getLblValorComision() {
		return lblValorComision;
	}

	public JTextField getTxtValorComision() {
		return txtValorComision;
	}

	public JLabel getLblTotalCompra() {
		return lblTotalCompra;
	}

	public JLabel getLblIvaVenta() {
		return lblIvaVenta;
	}

	public JTextField getTxtTotalCompra() {
		return txtTotalCompra;
	}

	public JLabel getLblTotalVenta() {
		return lblTotalVenta;
	}

	public JTextField getTxtIvaVenta() {
		return txtIvaVenta;
	}

	public JTextField getTxtTotalVenta() {
		return txtTotalVenta;
	}

	public JScrollPane getSpPresupuestoProveedor() {
		return spPresupuestoProveedor;
	}

	public JPanel getPanelPresupuestoProveedor() {
		return panelPresupuestoProveedor;
	}

	public JLabel getLblProveedorP() {
		return lblProveedorP;
	}

	public JTextField getTxtProveedorP() {
		return txtProveedorP;
	}

	public JLabel getLblProductoP() {
		return lblProductoP;
	}

	public JTextField getTxtProductoP() {
		return txtProductoP;
	}

	public JLabel getLblConceptoPresupuestoDetalleP() {
		return lblConceptoPresupuestoDetalleP;
	}

	public JScrollPane getSpConceptoPresupuestoDetalleP() {
		return spConceptoPresupuestoDetalleP;
	}

	public JTextArea getTxtConceptoPresupuestoDetalleP() {
		return txtConceptoPresupuestoDetalleP;
	}

	public JLabel getLblPrecioVentaP() {
		return lblPrecioVentaP;
	}

	public JTextField getTxtPrecioVentaP() {
		return txtPrecioVentaP;
	}

	public JTextField getTxtCantidadP() {
		return txtCantidadP;
	}

	public JLabel getLblCantidadP() {
		return lblCantidadP;
	}

	public JPanel getPanel13() {
		return panel13;
	}

	public JButton getBtnAgregarDetalleP() {
		return btnAgregarDetalleP;
	}

	public JButton getBtnActualizarDetalleP() {
		return btnActualizarDetalleP;
	}

	public JButton getBtnEliminarDetalleP() {
		return btnEliminarDetalleP;
	}

	public JScrollPane getScPresupuestoDetalleP() {
		return scPresupuestoDetalleP;
	}

	public JTable getTblPresupuestoDetalleP() {
		return tblPresupuestoDetalleP;
	}

	public JLabel getLblTotalReporte() {
		return lblTotalReporte;
	}

	public JTextField getTxtTotalReporte() {
		return txtTotalReporte;
	}

	public JPanel getPanelArchivos() {
		return panelArchivos;
	}

	public JLabel getLblTipoArchivo() {
		return lblTipoArchivo;
	}

	public JComboBox getCmbTipoArchivo() {
		return cmbTipoArchivo;
	}

	public JLabel getLblArchivo() {
		return lblArchivo;
	}

	public JTextField getTxtArchivo() {
		return txtArchivo;
	}

	public JButton getBtnBuscarArchivo() {
		return btnBuscarArchivo;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public JButton getBtnAgregarArchivo() {
		return btnAgregarArchivo;
	}

	public JButton getBtnActualizarArchivo() {
		return btnActualizarArchivo;
	}

	public JButton getBtnEliminarArchivo() {
		return btnEliminarArchivo;
	}

	public JButton getBtnVerArchivo() {
		return btnVerArchivo;
	}

	public JScrollPane getSpTblArchivos() {
		return spTblArchivos;
	}

	public JTable getTblArchivo() {
		return tblArchivo;
	}

	public JCheckBox getCbNegociacionDirecta() {
		return cbNegociacionDirecta;
	}

	public JCheckBox getCbComisionPura() {
		return cbComisionPura;
	}

	public JTextField getTxtPorcentajeComisionPura() {
		return txtPorcentajeComisionPura;
	}

	public JTextField getTxtPorcentajeDsctoAgenciaCompra() {
		return txtPorcentajeDsctoAgenciaCompra;
	}

	public JTextField getTxtDsctoAgenciaCompra() {
		return txtDsctoAgenciaCompra;
	}

	public JLabel getLblPorcentajeDescuentosVariosVenta() {
		return lblPorcentajeDescuentosVariosVenta;
	}

	public JTextField getTxtPorcentajeNotaCredito() {
		return txtPorcentajeNotaCredito;
	}

	public JCheckBox getCbComisionAdicional() {
		return cbComisionAdicional;
	}

	public JTextField getTxtPorcentajeComisionAdicional() {
		return txtPorcentajeComisionAdicional;
	}
}
