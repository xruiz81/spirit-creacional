package com.spirit.facturacion.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPAnularFactura extends SpiritModelImpl {
	public JPAnularFactura() {
		initComponents();
		setName("Anular Factura");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpAnularFactura = new JideTabbedPane();
		scrollPane1 = new JScrollPane();
		panel3 = new JPanel();
		panel2 = new JPanel();
		lblTipoDocumento = new JLabel();
		txtTipoDocumento = new JTextField();
		rbNinguno = new JRadioButton();
		lblDescuentoGlobal = new JLabel();
		txtDescuentoGlobal = new JTextField();
		lblTipoFactura = new JLabel();
		rbPresupuesto = new JRadioButton();
		rbOrdenMedios = new JRadioButton();
		panel32 = new JPanel();
		cmbFechaFactura = new DateComboBox();
		lblFechaFactura = new JLabel();
		lblFechaCreacion = new JLabel();
		txtFechaCreacion = new JTextField();
		lblPreimpreso = new JLabel();
		txtPreimpreso = new JTextField();
		lblMoneda = new JLabel();
		txtMoneda = new JTextField();
		lblOficina = new JLabel();
		txtOficinaEmpresa = new JTextField();
		lblCaja = new JLabel();
		txtCaja = new JTextField();
		lblFormaPago = new JLabel();
		txtFormaPago = new JTextField();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		lblListaPrecio = new JLabel();
		txtListaPrecio = new JTextField();
		txtFechaVencimiento = new JTextField();
		lblFechaVencimiento = new JLabel();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		panel4 = new JPanel();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		btnEncerarCliente = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		lblTipoIdentificacion = new JLabel();
		txtTipodentificacion = new JTextField();
		btnBuscarClienteOficina = new JButton();
		lblIdentificacion = new JLabel();
		txtIdentificacion = new JTextField();
		lblTelefono = new JLabel();
		txtTelefono = new JTextField();
		lblContacto = new JLabel();
		txtContacto = new JTextField();
		lblAutorizacionSAP = new JLabel();
		txtAutorizacionSAP = new JTextField();
		lblDireccion = new JLabel();
		txtDireccion = new JTextField();
		panel5 = new JPanel();
		lblPlanMedios = new JLabel();
		txtPlanMedios = new JTextField();
		lblPresupuesto = new JLabel();
		txtPresupuesto = new JTextField();
		panel6 = new JPanel();
		lblFacturadoPor = new JLabel();
		txtFacturadoPor = new JTextField();
		lblOrigenDocumento = new JLabel();
		txtOrigenDocumento = new JTextField();
		lblBodega = new JLabel();
		txtBodega = new JTextField();
		lblPuntoImpresion = new JLabel();
		txtPuntoImpresion = new JTextField();
		lblVendedor = new JLabel();
		txtVendedor = new JTextField();
		lblNumero = new JLabel();
		txtNumero = new JTextField();
		lblDirectorCuentas = new JLabel();
		txtDirectorCuentas = new JTextField();
		bpNegociacionPanel = new JPanel();
		btnMostrarPanelNegociacion = new JButton();
		jpNegociacion = new JPanel();
		cbNegociacionDirecta = new JCheckBox();
		lblPorcentajeNegociacionDirecta = new JLabel();
		txtPorcentajeNegociacionDirecta = new JTextField();
		lblDsctoCompraPorcentaje = new JLabel();
		txtDsctoCompraPorcentaje = new JTextField();
		lblClienteNegociacion = new JLabel();
		txtClienteNegociacion = new JTextField();
		btnBuscarClienteNegociacion = new JButton();
		lblPorcentajeDescuento = new JLabel();
		cbComisionPura = new JCheckBox();
		scrollPane2 = new JScrollPane();
		panel11 = new JPanel();
		panel8 = new JPanel();
		lblDocumento = new JLabel();
		txtDocumento = new JTextField();
		panel10 = new JPanel();
		lblProducto = new JLabel();
		lblDescripcion = new JLabel();
		txtCodigoProducto = new JTextField();
		spDescripcion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		lblFacturaProveedor = new JLabel();
		txtFacturaProveedor = new JTextField();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		lblCantidadDevuelta = new JLabel();
		txtCantidadDevuelta = new JTextField();
		lblPrecio = new JLabel();
		txtPrecio = new JTextField();
		lblCosto = new JLabel();
		txtCosto = new JTextField();
		lblPrecioReal = new JLabel();
		txtPrecioReal = new JTextField();
		lblLinea = new JLabel();
		txtLinea = new JTextField();
		lblPorcentajeDescuentoAgencia = new JLabel();
		lblLote = new JLabel();
		txtLote = new JTextField();
		txtPorcentajeDescuentoAgencia = new JTextField();
		lblPorcentajeDescuentosVarios = new JLabel();
		txtPorcentajeDescuentosVarios = new JTextField();
		scPlantilla = new JScrollPane();
		tblFacturaDetalle = new JTable();
		panel9 = new JPanel();
		lblMotivo = new JLabel();
		txtMotivo = new JTextField();
		panel112 = new JPanel();
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		lblIVAFinal = new JLabel();
		txtIVAFinal = new JTextField();
		lblDescuentoAgenciaTotal = new JLabel();
		txtDescuentoAgenciaTotal = new JTextField();
		txtICEFinal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblICEFinal = new JLabel();
		lblDescuentosVariosTotal = new JLabel();
		txtDescuentosVariosTotal = new JTextField();
		lblComisionAgencia = new JLabel();
		txtPorcentajeComision = new JTextField();
		lblPorcentajeComision = new JLabel();
		lblValorComision = new JLabel();
		txtValorComision = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpAnularFactura ========
		{

			//======== scrollPane1 ========
			{

				//======== panel3 ========
				{
					panel3.setLayout(new FormLayout(
						ColumnSpec.decodeSpecs("default:grow"),
						new RowSpec[] {
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
							FormFactory.DEFAULT_ROWSPEC
						}));

					//======== panel2 ========
					{
						panel2.setBorder(new TitledBorder(null, "Tipo Documento", TitledBorder.LEADING, TitledBorder.TOP));
						panel2.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));

						//---- lblTipoDocumento ----
						lblTipoDocumento.setText("Tipo de documento:");
						panel2.add(lblTipoDocumento, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel2.add(txtTipoDocumento, cc.xy(5, 1));

						//---- rbNinguno ----
						rbNinguno.setText("Ninguno");
						panel2.add(rbNinguno, cc.xy(11, 1));

						//---- lblDescuentoGlobal ----
						lblDescuentoGlobal.setText("Descuento global [%]:");
						panel2.add(lblDescuentoGlobal, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel2.add(txtDescuentoGlobal, cc.xy(5, 3));

						//---- lblTipoFactura ----
						lblTipoFactura.setText("Tipo de Factura:");
						panel2.add(lblTipoFactura, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- rbPresupuesto ----
						rbPresupuesto.setText("De Presupuesto");
						panel2.add(rbPresupuesto, cc.xy(11, 3));

						//---- rbOrdenMedios ----
						rbOrdenMedios.setText("De Orden de Medios");
						panel2.add(rbOrdenMedios, cc.xy(11, 5));
					}
					panel3.add(panel2, cc.xy(1, 1));

					//======== panel32 ========
					{
						panel32.setBorder(new TitledBorder(null, "Datos de Factura", TitledBorder.LEADING, TitledBorder.TOP));
						panel32.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
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
								FormFactory.DEFAULT_ROWSPEC
							}));
						panel32.add(cmbFechaFactura, cc.xy(5, 1));

						//---- lblFechaFactura ----
						lblFechaFactura.setText("Fecha de la factura:");
						panel32.add(lblFechaFactura, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblFechaCreacion ----
						lblFechaCreacion.setText("Fecha de creaci\u00f3n:");
						panel32.add(lblFechaCreacion, cc.xywh(11, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtFechaCreacion ----
						txtFechaCreacion.setHorizontalAlignment(SwingConstants.LEADING);
						panel32.add(txtFechaCreacion, cc.xywh(13, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPreimpreso ----
						lblPreimpreso.setText("Preimpreso:");
						panel32.add(lblPreimpreso, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtPreimpreso, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblMoneda ----
						lblMoneda.setText("Moneda:");
						panel32.add(lblMoneda, cc.xywh(11, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtMoneda, cc.xywh(13, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblOficina ----
						lblOficina.setText("Oficina :");
						panel32.add(lblOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOficinaEmpresa ----
						txtOficinaEmpresa.setEditable(true);
						txtOficinaEmpresa.setHorizontalAlignment(SwingConstants.LEADING);
						panel32.add(txtOficinaEmpresa, cc.xywh(5, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblCaja ----
						lblCaja.setText("Caja:");
						panel32.add(lblCaja, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtCaja, cc.xywh(13, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblFormaPago ----
						lblFormaPago.setText("Forma de pago:");
						panel32.add(lblFormaPago, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtFormaPago, cc.xywh(5, 7, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblEstado ----
						lblEstado.setText("Estado:");
						panel32.add(lblEstado, cc.xywh(11, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtEstado, cc.xy(13, 7));

						//---- lblListaPrecio ----
						lblListaPrecio.setText("Lista de precios:");
						panel32.add(lblListaPrecio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtListaPrecio, cc.xywh(5, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel32.add(txtFechaVencimiento, cc.xywh(13, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblFechaVencimiento ----
						lblFechaVencimiento.setText("Fecha de vencimiento:");
						panel32.add(lblFechaVencimiento, cc.xywh(11, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblObservacion ----
						lblObservacion.setText("Observaci\u00f3n:");
						panel32.add(lblObservacion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtObservacion, cc.xywh(5, 11, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel3.add(panel32, cc.xy(1, 3));

					//======== panel4 ========
					{
						panel4.setBorder(new TitledBorder(null, "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP));
						panel4.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
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
								FormFactory.DEFAULT_ROWSPEC
							}));

						//---- lblCorporacion ----
						lblCorporacion.setText("Corporaci\u00f3n:");
						panel4.add(lblCorporacion, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtCorporacion, cc.xy(5, 1));
						panel4.add(btnBuscarCorporacion, cc.xywh(7, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						panel4.add(btnEncerarCliente, cc.xywh(9, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblCliente ----
						lblCliente.setText("Cliente:");
						panel4.add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtCliente, cc.xy(5, 3));
						panel4.add(btnBuscarCliente, cc.xywh(7, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblClienteOficina ----
						lblClienteOficina.setText("Oficina del cliente:");
						panel4.add(lblClienteOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtClienteOficina, cc.xy(5, 5));

						//---- lblTipoIdentificacion ----
						lblTipoIdentificacion.setText("Tipo de identificaci\u00f3n:");
						panel4.add(lblTipoIdentificacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtTipodentificacion, cc.xywh(5, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel4.add(btnBuscarClienteOficina, cc.xywh(7, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblIdentificacion ----
						lblIdentificacion.setText("Identificaci\u00f3n:");
						panel4.add(lblIdentificacion, cc.xywh(13, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtIdentificacion ----
						txtIdentificacion.setHorizontalAlignment(SwingConstants.RIGHT);
						panel4.add(txtIdentificacion, cc.xywh(15, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblTelefono ----
						lblTelefono.setText("Tel\u00e9fono:");
						panel4.add(lblTelefono, cc.xywh(13, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtTelefono ----
						txtTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
						panel4.add(txtTelefono, cc.xywh(15, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblContacto ----
						lblContacto.setText("Contacto:");
						panel4.add(lblContacto, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtContacto, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblAutorizacionSAP ----
						lblAutorizacionSAP.setText("SAP:");
						panel4.add(lblAutorizacionSAP, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtAutorizacionSAP ----
						txtAutorizacionSAP.setHorizontalAlignment(SwingConstants.RIGHT);
						txtAutorizacionSAP.setEditable(false);
						panel4.add(txtAutorizacionSAP, cc.xy(15, 9));

						//---- lblDireccion ----
						lblDireccion.setText("Direcci\u00f3n:");
						panel4.add(lblDireccion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtDireccion, cc.xywh(5, 11, 11, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel3.add(panel4, cc.xy(1, 5));

					//======== panel5 ========
					{
						panel5.setBorder(new TitledBorder(null, "Procedencia de la Factura", TitledBorder.LEADING, TitledBorder.TOP));
						panel5.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(180)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default, 3dlu, default")));
						((FormLayout)panel5.getLayout()).setColumnGroups(new int[][] {{5, 7}});

						//---- lblPlanMedios ----
						lblPlanMedios.setText("Plan de Medios:");
						panel5.add(lblPlanMedios, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel5.add(txtPlanMedios, cc.xy(5, 1));

						//---- lblPresupuesto ----
						lblPresupuesto.setText("Presupuesto:");
						panel5.add(lblPresupuesto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel5.add(txtPresupuesto, cc.xy(5, 3));
					}
					panel3.add(panel5, cc.xy(1, 7));

					//======== panel6 ========
					{
						panel6.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP));
						panel6.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						((FormLayout)panel6.getLayout()).setColumnGroups(new int[][] {{3, 9}, {5, 11}});

						//---- lblFacturadoPor ----
						lblFacturadoPor.setText("Facturado por:");
						panel6.add(lblFacturadoPor, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(txtFacturadoPor, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblOrigenDocumento ----
						lblOrigenDocumento.setText("Origen del documento:");
						panel6.add(lblOrigenDocumento, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(txtOrigenDocumento, cc.xywh(11, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblBodega ----
						lblBodega.setText("Bodega:");
						panel6.add(lblBodega, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(txtBodega, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPuntoImpresion ----
						lblPuntoImpresion.setText("Punto de impresi\u00f3n:");
						panel6.add(lblPuntoImpresion, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(txtPuntoImpresion, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblVendedor ----
						lblVendedor.setText("Vendedor:");
						panel6.add(lblVendedor, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(txtVendedor, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblNumero ----
						lblNumero.setText("N\u00famero:");
						panel6.add(lblNumero, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtNumero ----
						txtNumero.setHorizontalAlignment(SwingConstants.LEADING);
						panel6.add(txtNumero, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDirectorCuentas ----
						lblDirectorCuentas.setText("Director(a):");
						panel6.add(lblDirectorCuentas, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel6.add(txtDirectorCuentas, cc.xy(5, 7));
					}
					panel3.add(panel6, cc.xy(1, 11));

					//======== bpNegociacionPanel ========
					{
						bpNegociacionPanel.setBorder(new LineBorder(Color.lightGray));
						bpNegociacionPanel.setLayout(new BorderLayout());

						//---- btnMostrarPanelNegociacion ----
						btnMostrarPanelNegociacion.setText("Negociaci\u00f3n | >>");
						btnMostrarPanelNegociacion.setHorizontalAlignment(SwingConstants.LEFT);
						bpNegociacionPanel.add(btnMostrarPanelNegociacion, BorderLayout.NORTH);

						//======== jpNegociacion ========
						{
							jpNegociacion.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.dluX(12)),
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
									new ColumnSpec(Sizes.dluX(130)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(10))
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY2),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY3)
								}));

							//---- cbNegociacionDirecta ----
							cbNegociacionDirecta.setText("Negociaci\u00f3n Directa");
							jpNegociacion.add(cbNegociacionDirecta, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- lblPorcentajeNegociacionDirecta ----
							lblPorcentajeNegociacionDirecta.setText("%:");
							jpNegociacion.add(lblPorcentajeNegociacionDirecta, cc.xywh(5, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtPorcentajeNegociacionDirecta ----
							txtPorcentajeNegociacionDirecta.setHorizontalAlignment(SwingConstants.RIGHT);
							txtPorcentajeNegociacionDirecta.setEditable(false);
							jpNegociacion.add(txtPorcentajeNegociacionDirecta, cc.xy(7, 3));

							//---- lblDsctoCompraPorcentaje ----
							lblDsctoCompraPorcentaje.setText("Descuento:");
							jpNegociacion.add(lblDsctoCompraPorcentaje, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtDsctoCompraPorcentaje ----
							txtDsctoCompraPorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
							txtDsctoCompraPorcentaje.setEditable(false);
							jpNegociacion.add(txtDsctoCompraPorcentaje, cc.xy(7, 5));

							//---- lblClienteNegociacion ----
							lblClienteNegociacion.setText("Cliente Negociaci\u00f3n:");
							jpNegociacion.add(lblClienteNegociacion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtClienteNegociacion ----
							txtClienteNegociacion.setEditable(false);
							jpNegociacion.add(txtClienteNegociacion, cc.xywh(5, 7, 9, 1));
							jpNegociacion.add(btnBuscarClienteNegociacion, cc.xywh(15, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

							//---- lblPorcentajeDescuento ----
							lblPorcentajeDescuento.setText("%:");
							jpNegociacion.add(lblPorcentajeDescuento, cc.xywh(5, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- cbComisionPura ----
							cbComisionPura.setText("Comisi\u00f3n Pura");
							jpNegociacion.add(cbComisionPura, cc.xywh(11, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						}
						bpNegociacionPanel.add(jpNegociacion, BorderLayout.CENTER);
					}
					panel3.add(bpNegociacionPanel, cc.xy(1, 9));
				}
				scrollPane1.setViewportView(panel3);
			}
			jtpAnularFactura.addTab("Factura", scrollPane1);


			//======== scrollPane2 ========
			{

				//======== panel11 ========
				{
					panel11.setLayout(new FormLayout(
						ColumnSpec.decodeSpecs("default:grow"),
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));

					//======== panel8 ========
					{
						panel8.setBorder(new TitledBorder(null, "Documento", TitledBorder.LEADING, TitledBorder.TOP));
						panel8.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						panel8.add(lblDocumento, cc.xywh(3, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel8.add(txtDocumento, cc.xywh(5, 1, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel8, cc.xy(1, 1));

					//======== panel10 ========
					{
						panel10.setBorder(new TitledBorder(null, "Otra Informaci\u00f3n", TitledBorder.LEADING, TitledBorder.TOP));
						panel10.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(230)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(Sizes.dluY(30)),
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
								FormFactory.DEFAULT_ROWSPEC
							}));

						//---- lblProducto ----
						lblProducto.setText("C\u00f3digo del producto:");
						panel10.add(lblProducto, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtCodigoProducto, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//======== spDescripcion ========
						{

							//---- txtDescripcion ----
							txtDescripcion.setLineWrap(true);
							spDescripcion.setViewportView(txtDescripcion);
						}
						panel10.add(spDescripcion, cc.xywh(5, 3, 7, 5));

						//---- lblProveedor ----
						lblProveedor.setText("Proveedor:");
						panel10.add(lblProveedor, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtProveedor, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblFacturaProveedor ----
						lblFacturaProveedor.setText("Factura Proveedor:");
						panel10.add(lblFacturaProveedor, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(txtFacturaProveedor, cc.xy(11, 9));

						//---- lblCantidad ----
						lblCantidad.setText("Cantidad a pedir:");
						panel10.add(lblCantidad, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCantidad, cc.xy(5, 11));

						//---- lblCantidadDevuelta ----
						lblCantidadDevuelta.setText("Cantidad devuelta:");
						panel10.add(lblCantidadDevuelta, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(txtCantidadDevuelta, cc.xy(11, 11));

						//---- lblPrecio ----
						lblPrecio.setText("Precio:");
						panel10.add(lblPrecio, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPrecio ----
						txtPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPrecio, cc.xy(5, 13));

						//---- lblCosto ----
						lblCosto.setText("Costo del producto:");
						panel10.add(lblCosto, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtCosto ----
						txtCosto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCosto, cc.xywh(11, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPrecioReal ----
						lblPrecioReal.setText("Precio real:");
						panel10.add(lblPrecioReal, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPrecioReal ----
						txtPrecioReal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPrecioReal, cc.xywh(5, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblLinea ----
						lblLinea.setText("L\u00ednea:");
						panel10.add(lblLinea, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtLinea, cc.xywh(11, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPorcentajeDescuentoAgencia ----
						lblPorcentajeDescuentoAgencia.setText("Dscto. Agencia [%]:");
						panel10.add(lblPorcentajeDescuentoAgencia, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblLote ----
						lblLote.setText("Lote:");
						panel10.add(lblLote, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtLote, cc.xywh(11, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- txtPorcentajeDescuentoAgencia ----
						txtPorcentajeDescuentoAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentoAgencia, cc.xywh(5, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPorcentajeDescuentosVarios ----
						lblPorcentajeDescuentosVarios.setText("Dsctos. Varios [%]:");
						panel10.add(lblPorcentajeDescuentosVarios, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeDescuentosVarios ----
						txtPorcentajeDescuentosVarios.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentosVarios, cc.xy(5, 19));

						//======== scPlantilla ========
						{

							//---- tblFacturaDetalle ----
							tblFacturaDetalle.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Descripci\u00f3n", "Cantidad", "Precio Real", "Descuento", "Descuento Global", "Iva"
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
							tblFacturaDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblFacturaDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							scPlantilla.setViewportView(tblFacturaDetalle);
						}
						panel10.add(scPlantilla, cc.xywh(3, 23, 9, 1));
					}
					panel11.add(panel10, cc.xy(1, 5));

					//======== panel9 ========
					{
						panel9.setBorder(new TitledBorder(null, "Documento Aplica", TitledBorder.LEADING, TitledBorder.TOP));
						panel9.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));
						((FormLayout)panel9.getLayout()).setColumnGroups(new int[][] {{3, 9}, {5, 11}});

						//---- lblMotivo ----
						lblMotivo.setText("Motivo:");
						panel9.add(lblMotivo, cc.xywh(3, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel9.add(txtMotivo, cc.xywh(5, 1, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel9, cc.xy(1, 3));

					//======== panel112 ========
					{
						panel112.setBorder(new TitledBorder(null, "Totales", TitledBorder.LEADING, TitledBorder.TOP));
						panel112.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(40)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12))
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						((FormLayout)panel112.getLayout()).setColumnGroups(new int[][] {{11, 17}});

						//---- lblValorFinal ----
						lblValorFinal.setText("SubTotal:");
						panel112.add(lblValorFinal, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValorFinal ----
						txtValorFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtValorFinal.setEditable(false);
						panel112.add(txtValorFinal, cc.xywh(5, 1, 7, 1));

						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(15, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtIVAFinal.setEditable(false);
						panel112.add(txtIVAFinal, cc.xy(17, 1));

						//---- lblDescuentoAgenciaTotal ----
						lblDescuentoAgenciaTotal.setText("Dscto. Agencia:");
						panel112.add(lblDescuentoAgenciaTotal, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtDescuentoAgenciaTotal ----
						txtDescuentoAgenciaTotal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtDescuentoAgenciaTotal.setEditable(false);
						panel112.add(txtDescuentoAgenciaTotal, cc.xywh(5, 3, 7, 1));

						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtICEFinal.setEditable(false);
						panel112.add(txtICEFinal, cc.xy(17, 3));

						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro Impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtOtroImpuestoFinal.setEditable(false);
						panel112.add(txtOtroImpuestoFinal, cc.xy(17, 5));

						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblDescuentosVariosTotal ----
						lblDescuentosVariosTotal.setText("Dsctos. Varios:");
						panel112.add(lblDescuentosVariosTotal, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtDescuentosVariosTotal ----
						txtDescuentosVariosTotal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtDescuentosVariosTotal.setEditable(false);
						panel112.add(txtDescuentosVariosTotal, cc.xywh(5, 5, 7, 1));

						//---- lblComisionAgencia ----
						lblComisionAgencia.setText("Comisi\u00f3n de Agencia:");
						panel112.add(lblComisionAgencia, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeComision ----
						txtPorcentajeComision.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtPorcentajeComision, cc.xy(5, 7));

						//---- lblPorcentajeComision ----
						lblPorcentajeComision.setText("%");
						panel112.add(lblPorcentajeComision, cc.xy(7, 7));

						//---- lblValorComision ----
						lblValorComision.setText("Valor:");
						panel112.add(lblValorComision, cc.xy(9, 7));

						//---- txtValorComision ----
						txtValorComision.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtValorComision, cc.xy(11, 7));

						//---- lblTotalFinal ----
						lblTotalFinal.setText("TOTAL:");
						lblTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(lblTotalFinal, cc.xywh(15, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtTotalFinal ----
						txtTotalFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						txtTotalFinal.setEditable(false);
						panel112.add(txtTotalFinal, cc.xy(17, 7));
					}
					panel11.add(panel112, cc.xy(1, 7));
				}
				scrollPane2.setViewportView(panel11);
			}
			jtpAnularFactura.addTab("Detalle", scrollPane2);

		}
		add(jtpAnularFactura, cc.xy(1, 1));

		//---- buttonGroup1 ----
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(rbNinguno);
		buttonGroup1.add(rbPresupuesto);
		buttonGroup1.add(rbOrdenMedios);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpAnularFactura;
	private JScrollPane scrollPane1;
	private JPanel panel3;
	private JPanel panel2;
	private JLabel lblTipoDocumento;
	private JTextField txtTipoDocumento;
	private JRadioButton rbNinguno;
	private JLabel lblDescuentoGlobal;
	private JTextField txtDescuentoGlobal;
	private JLabel lblTipoFactura;
	private JRadioButton rbPresupuesto;
	private JRadioButton rbOrdenMedios;
	private JPanel panel32;
	private DateComboBox cmbFechaFactura;
	private JLabel lblFechaFactura;
	private JLabel lblFechaCreacion;
	private JTextField txtFechaCreacion;
	private JLabel lblPreimpreso;
	private JTextField txtPreimpreso;
	private JLabel lblMoneda;
	private JTextField txtMoneda;
	private JLabel lblOficina;
	private JTextField txtOficinaEmpresa;
	private JLabel lblCaja;
	private JTextField txtCaja;
	private JLabel lblFormaPago;
	private JTextField txtFormaPago;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblListaPrecio;
	private JTextField txtListaPrecio;
	private JTextField txtFechaVencimiento;
	private JLabel lblFechaVencimiento;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JPanel panel4;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JButton btnEncerarCliente;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JLabel lblTipoIdentificacion;
	private JTextField txtTipodentificacion;
	private JButton btnBuscarClienteOficina;
	private JLabel lblIdentificacion;
	private JTextField txtIdentificacion;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblContacto;
	private JTextField txtContacto;
	private JLabel lblAutorizacionSAP;
	private JTextField txtAutorizacionSAP;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JPanel panel5;
	private JLabel lblPlanMedios;
	private JTextField txtPlanMedios;
	private JLabel lblPresupuesto;
	private JTextField txtPresupuesto;
	private JPanel panel6;
	private JLabel lblFacturadoPor;
	private JTextField txtFacturadoPor;
	private JLabel lblOrigenDocumento;
	private JTextField txtOrigenDocumento;
	private JLabel lblBodega;
	private JTextField txtBodega;
	private JLabel lblPuntoImpresion;
	private JTextField txtPuntoImpresion;
	private JLabel lblVendedor;
	private JTextField txtVendedor;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblDirectorCuentas;
	private JTextField txtDirectorCuentas;
	private JPanel bpNegociacionPanel;
	private JButton btnMostrarPanelNegociacion;
	private JPanel jpNegociacion;
	private JCheckBox cbNegociacionDirecta;
	private JLabel lblPorcentajeNegociacionDirecta;
	private JTextField txtPorcentajeNegociacionDirecta;
	private JLabel lblDsctoCompraPorcentaje;
	private JTextField txtDsctoCompraPorcentaje;
	private JLabel lblClienteNegociacion;
	private JTextField txtClienteNegociacion;
	private JButton btnBuscarClienteNegociacion;
	private JLabel lblPorcentajeDescuento;
	private JCheckBox cbComisionPura;
	private JScrollPane scrollPane2;
	private JPanel panel11;
	private JPanel panel8;
	private JLabel lblDocumento;
	private JTextField txtDocumento;
	private JPanel panel10;
	private JLabel lblProducto;
	private JLabel lblDescripcion;
	private JTextField txtCodigoProducto;
	private JScrollPane spDescripcion;
	private JTextArea txtDescripcion;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JLabel lblFacturaProveedor;
	private JTextField txtFacturaProveedor;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JLabel lblCantidadDevuelta;
	private JTextField txtCantidadDevuelta;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblCosto;
	private JTextField txtCosto;
	private JLabel lblPrecioReal;
	private JTextField txtPrecioReal;
	private JLabel lblLinea;
	private JTextField txtLinea;
	private JLabel lblPorcentajeDescuentoAgencia;
	private JLabel lblLote;
	private JTextField txtLote;
	private JTextField txtPorcentajeDescuentoAgencia;
	private JLabel lblPorcentajeDescuentosVarios;
	private JTextField txtPorcentajeDescuentosVarios;
	private JScrollPane scPlantilla;
	private JTable tblFacturaDetalle;
	private JPanel panel9;
	private JLabel lblMotivo;
	private JTextField txtMotivo;
	private JPanel panel112;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JLabel lblIVAFinal;
	private JTextField txtIVAFinal;
	private JLabel lblDescuentoAgenciaTotal;
	private JTextField txtDescuentoAgenciaTotal;
	private JTextField txtICEFinal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblICEFinal;
	private JLabel lblDescuentosVariosTotal;
	private JTextField txtDescuentosVariosTotal;
	private JLabel lblComisionAgencia;
	private JTextField txtPorcentajeComision;
	private JLabel lblPorcentajeComision;
	private JLabel lblValorComision;
	private JTextField txtValorComision;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtDescuentoAgenciaTotal() {
		return txtDescuentoAgenciaTotal;
	}

	public JTextField getTxtDescuentosVariosTotal() {
		return txtDescuentosVariosTotal;
	}

	public JTextField getTxtPorcentajeDescuentoAgencia() {
		return txtPorcentajeDescuentoAgencia;
	}

	public JTextField getTxtPorcentajeDescuentosVarios() {
		return txtPorcentajeDescuentosVarios;
	}

	public JTextField getTxtAutorizacionSAP() {
		return txtAutorizacionSAP;
	}

	public JTextField getTxtPlanMedios() {
		return txtPlanMedios;
	}

	public JPanel getBpNegociacionPanel() {
		return bpNegociacionPanel;
	}

	public JButton getBtnMostrarPanelNegociacion() {
		return btnMostrarPanelNegociacion;
	}

	public JPanel getJpNegociacion() {
		return jpNegociacion;
	}

	public JCheckBox getCbNegociacionDirecta() {
		return cbNegociacionDirecta;
	}

	public JTextField getTxtPorcentajeNegociacionDirecta() {
		return txtPorcentajeNegociacionDirecta;
	}

	public JTextField getTxtDsctoCompraPorcentaje() {
		return txtDsctoCompraPorcentaje;
	}

	public JTextField getTxtClienteNegociacion() {
		return txtClienteNegociacion;
	}

	public JButton getBtnBuscarClienteNegociacion() {
		return btnBuscarClienteNegociacion;
	}

	public JCheckBox getCbComisionPura() {
		return cbComisionPura;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(JTextField txtEstado) {
		this.txtEstado = txtEstado;
	}

	public DateComboBox getCmbFechaFactura() {
		return cmbFechaFactura;
	}

	public void setCmbFechaFactura(DateComboBox cmbFechaFactura) {
		this.cmbFechaFactura = cmbFechaFactura;
	}

	public JTextField getTxtPresupuesto() {
		return txtPresupuesto;
	}

	public void setTxtPresupuesto(JTextField txtPresupuesto) {
		this.txtPresupuesto = txtPresupuesto;
	}

	public JTextField getTxtTipoDocumento() {
		return txtTipoDocumento;
	}

	public void setTxtTipoDocumento(JTextField txtTipoDocumento) {
		this.txtTipoDocumento = txtTipoDocumento;
	}

	public JideTabbedPane getJtpAnularFactura() {
		return jtpAnularFactura;
	}

	public void setJtpAnularFactura(JideTabbedPane jtpAnularFactura) {
		this.jtpAnularFactura = jtpAnularFactura;
	}

	public JRadioButton getRbNinguno() {
		return rbNinguno;
	}

	public void setRbNinguno(JRadioButton rbNinguno) {
		this.rbNinguno = rbNinguno;
	}

	public JRadioButton getRbOrdenMedios() {
		return rbOrdenMedios;
	}

	public void setRbOrdenMedios(JRadioButton rbOrdenMedios) {
		this.rbOrdenMedios = rbOrdenMedios;
	}

	public JRadioButton getRbPresupuesto() {
		return rbPresupuesto;
	}

	public void setRbPresupuesto(JRadioButton rbPresupuesto) {
		this.rbPresupuesto = rbPresupuesto;
	}

	public JTable getTblFacturaDetalle() {
		return tblFacturaDetalle;
	}

	public void setTblFacturaDetalle(JTable tblFacturaDetalle) {
		this.tblFacturaDetalle = tblFacturaDetalle;
	}

	public JTextField getTxtBodega() {
		return txtBodega;
	}

	public void setTxtBodega(JTextField txtBodega) {
		this.txtBodega = txtBodega;
	}

	public JTextField getTxtCaja() {
		return txtCaja;
	}

	public void setTxtCaja(JTextField txtCaja) {
		this.txtCaja = txtCaja;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtCantidadDevuelta() {
		return txtCantidadDevuelta;
	}

	public void setTxtCantidadDevuelta(JTextField txtCantidadDevuelta) {
		this.txtCantidadDevuelta = txtCantidadDevuelta;
	}

	public JTextField getTxtCodigoProducto() {
		return txtCodigoProducto;
	}

	public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}

	public JTextField getTxtContacto() {
		return txtContacto;
	}

	public void setTxtContacto(JTextField txtContacto) {
		this.txtContacto = txtContacto;
	}

	public JTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(JTextField txtCosto) {
		this.txtCosto = txtCosto;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextArea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JTextField getTxtDescuentoGlobal() {
		return txtDescuentoGlobal;
	}

	public void setTxtDescuentoGlobal(JTextField txtDescuentoGlobal) {
		this.txtDescuentoGlobal = txtDescuentoGlobal;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JTextField getTxtDocumento() {
		return txtDocumento;
	}

	public void setTxtDocumento(JTextField txtDocumento) {
		this.txtDocumento = txtDocumento;
	}

	public JTextField getTxtFacturadoPor() {
		return txtFacturadoPor;
	}

	public void setTxtFacturadoPor(JTextField txtFacturadoPor) {
		this.txtFacturadoPor = txtFacturadoPor;
	}

	public JTextField getTxtFechaCreacion() {
		return txtFechaCreacion;
	}

	public void setTxtFechaCreacion(JTextField txtFechaCreacion) {
		this.txtFechaCreacion = txtFechaCreacion;
	}

	public JTextField getTxtFechaVencimiento() {
		return txtFechaVencimiento;
	}

	public void setTxtFechaVencimiento(JTextField txtFechaVencimiento) {
		this.txtFechaVencimiento = txtFechaVencimiento;
	}

	public JTextField getTxtFormaPago() {
		return txtFormaPago;
	}

	public void setTxtFormaPago(JTextField txtFormaPago) {
		this.txtFormaPago = txtFormaPago;
	}

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public void setTxtICEFinal(JTextField txtICEFinal) {
		this.txtICEFinal = txtICEFinal;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public JTextField getTxtIVAFinal() {
		return txtIVAFinal;
	}

	public void setTxtIVAFinal(JTextField txtIVAFinal) {
		this.txtIVAFinal = txtIVAFinal;
	}

	public JTextField getTxtLinea() {
		return txtLinea;
	}

	public void setTxtLinea(JTextField txtLinea) {
		this.txtLinea = txtLinea;
	}

	public JTextField getTxtListaPrecio() {
		return txtListaPrecio;
	}

	public void setTxtListaPrecio(JTextField txtListaPrecio) {
		this.txtListaPrecio = txtListaPrecio;
	}

	public JTextField getTxtLote() {
		return txtLote;
	}

	public void setTxtLote(JTextField txtLote) {
		this.txtLote = txtLote;
	}

	public JTextField getTxtMoneda() {
		return txtMoneda;
	}

	public void setTxtMoneda(JTextField txtMoneda) {
		this.txtMoneda = txtMoneda;
	}

	public JTextField getTxtMotivo() {
		return txtMotivo;
	}

	public void setTxtMotivo(JTextField txtMotivo) {
		this.txtMotivo = txtMotivo;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(JTextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JTextField getTxtOficinaEmpresa() {
		return txtOficinaEmpresa;
	}

	public void setTxtOficinaEmpresa(JTextField txtOficinaEmpresa) {
		this.txtOficinaEmpresa = txtOficinaEmpresa;
	}

	public JTextField getTxtOrigenDocumento() {
		return txtOrigenDocumento;
	}

	public void setTxtOrigenDocumento(JTextField txtOrigenDocumento) {
		this.txtOrigenDocumento = txtOrigenDocumento;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public void setTxtOtroImpuestoFinal(JTextField txtOtroImpuestoFinal) {
		this.txtOtroImpuestoFinal = txtOtroImpuestoFinal;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public JTextField getTxtPrecioReal() {
		return txtPrecioReal;
	}

	public void setTxtPrecioReal(JTextField txtPrecioReal) {
		this.txtPrecioReal = txtPrecioReal;
	}

	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}

	public void setTxtPreimpreso(JTextField txtPreimpreso) {
		this.txtPreimpreso = txtPreimpreso;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JTextField getTxtPuntoImpresion() {
		return txtPuntoImpresion;
	}

	public void setTxtPuntoImpresion(JTextField txtPuntoImpresion) {
		this.txtPuntoImpresion = txtPuntoImpresion;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JTextField getTxtTipodentificacion() {
		return txtTipodentificacion;
	}

	public void setTxtTipodentificacion(JTextField txtTipodentificacion) {
		this.txtTipodentificacion = txtTipodentificacion;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public void setTxtTotalFinal(JTextField txtTotalFinal) {
		this.txtTotalFinal = txtTotalFinal;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public void setTxtValorFinal(JTextField txtValorFinal) {
		this.txtValorFinal = txtValorFinal;
	}

	public JTextField getTxtVendedor() {
		return txtVendedor;
	}

	public void setTxtVendedor(JTextField txtVendedor) {
		this.txtVendedor = txtVendedor;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarClienteOficina() {
		return btnBuscarClienteOficina;
	}

	public void setBtnBuscarClienteOficina(JButton btnBuscarClienteOficina) {
		this.btnBuscarClienteOficina = btnBuscarClienteOficina;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public void setTxtClienteOficina(JTextField txtClienteOficina) {
		this.txtClienteOficina = txtClienteOficina;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtPorcentajeComision() {
		return txtPorcentajeComision;
	}

	public void setTxtPorcentajeComision(JTextField txtPorcentajeComision) {
		this.txtPorcentajeComision = txtPorcentajeComision;
	}

	public JTextField getTxtValorComision() {
		return txtValorComision;
	}

	public void setTxtValorComision(JTextField txtValorComision) {
		this.txtValorComision = txtValorComision;
	}

	public JTextField getTxtFacturaProveedor() {
		return txtFacturaProveedor;
	}

	public void setTxtFacturaProveedor(JTextField txtFacturaProveedor) {
		this.txtFacturaProveedor = txtFacturaProveedor;
	}

	public JButton getBtnEncerarCliente() {
		return btnEncerarCliente;
	}

	public void setBtnEncerarCliente(JButton btnEncerarCliente) {
		this.btnEncerarCliente = btnEncerarCliente;
	}

	public JTextField getTxtDirectorCuentas() {
		return txtDirectorCuentas;
	}
}
