package com.spirit.facturacion.gui.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

public abstract class JPPedido extends SpiritModelImpl {
	public JPPedido() {
		initComponents();
		setName("Pedido");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpPedido = new JideTabbedPane();
		scrollPane1 = new JScrollPane();
		panel3 = new JPanel();
		panel2 = new JPanel();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblGenerarFactura = new JLabel();
		btnGenerarFactura = new JButton();
		lblInformacionPedido = new JLabel();
		btnInformacionEnvio = new JButton();
		panel32 = new JPanel();
		cmbFechaPedido = new DateComboBox();
		lblFechaPedido = new JLabel();
		lblFechaVencimiento = new JLabel();
		cmbFechaVencimiento = new DateComboBox();
		lblMoneda = new JLabel();
		cmbMoneda = new JComboBox();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblCaja = new JLabel();
		cmbCaja = new JComboBox();
		lblOficina = new JLabel();
		cmbOficinaEmpresa = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		panel4 = new JPanel();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnEncerarCliente = new JButton();
		btnBuscarCorporacion = new JButton();
		lblTipoIdentificacion = new JLabel();
		cmbTipoIdentificacion = new JComboBox();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		btnNuevoCliente = new JButton();
		lblIdentificacion = new JLabel();
		txtIdentificacion = new JTextField();
		lblClienteOficina = new JLabel();
		txtClienteOficina = new JTextField();
		lblTelefono = new JLabel();
		txtTelefono = new JTextField();
		txtContacto = new JTextField();
		btnBuscarClienteOficina = new JButton();
		lblTipoReferencia = new JLabel();
		cmbTipoReferencia = new JComboBox();
		cbMostrarReferenciaFactura = new JCheckBox();
		lblEscojaReferencia = new JLabel();
		txtEscojaReferencia = new JTextField();
		btnEscojaReferencia = new JButton();
		cbMultipleFacturacion = new JCheckBox();
		lblAutorizacionSAP = new JLabel();
		txtAutorizacionSAP = new JTextField();
		lblArchivoAdjunto = new JLabel();
		txtArchivoAdjunto = new JTextField();
		btnVerArchivoAdjunto = new JButton();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblContacto = new JLabel();
		lblDireccion = new JLabel();
		txtDireccion = new JTextField();
		lblInformacionAdc = new JLabel();
		txtInformacionAdc = new JTextField();
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
		panel6 = new JPanel();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		lblListaPrecio = new JLabel();
		cmbListaPrecio = new JComboBox();
		lblDiasValidez = new JLabel();
		txtDiasValidez = new JTextField();
		lblFormaPago = new JLabel();
		cmbFormaPago = new JComboBox();
		lblPuntoImpresion = new JLabel();
		txtPuntoImpresion = new JTextField();
		lblVendedor = new JLabel();
		cmbVendedor = new JComboBox();
		lblBodega = new JLabel();
		cmbBodega = new JComboBox();
		lblDirectorCuentas = new JLabel();
		cmbDirectorCuentas = new JComboBox();
		lblOrigenDocumento = new JLabel();
		cmbOrigenDocumento = new JComboBox();
		lblFundacion = new JLabel();
		cmbFundacion = new JComboBox();
		scrollPane2 = new JScrollPane();
		panel11 = new JPanel();
		panel8 = new JPanel();
		lblDocumento = new JLabel();
		lblMotivo = new JLabel();
		cmbMotivo = new JComboBox();
		cmbDocumento = new JComboBox();
		panel10 = new JPanel();
		scPlantilla = new JScrollPane();
		tblPedidoDetalle = new JTable();
		lblCodigoProducto = new JLabel();
		btnBuscarProducto = new JButton();
		txtCodigoProducto = new JTextField();
		lblDescripcion = new JLabel();
		spDescripcion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblCantidad = new JLabel();
		txtCantidadPedida = new JTextField();
		lblCantidadPedida = new JLabel();
		txtCantidad = new JTextField();
		lblPrecio = new JLabel();
		txtPrecio = new JTextField();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		lblPrecioReal = new JLabel();
		txtPrecioReal = new JTextField();
		lblFacturaProveedor = new JLabel();
		txtFacturaProveedor = new JTextField();
		btnFacturaProveedor = new JButton();
		lblPorcentajeDescuentoAgencia = new JLabel();
		txtPorcentajeDescuentoAgencia = new JTextField();
		lblLinea = new JLabel();
		txtLinea = new JTextField();
		lblPorcentajeDescuentosVarios = new JLabel();
		txtPorcentajeDescuentosVarios = new JTextField();
		lblLote = new JLabel();
		cmbLote = new JComboBox();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		panel7 = new JPanel();
		rbDescuentoGlobalPorcentaje = new JRadioButton();
		txtDescuentoGlobalPorcentaje = new JTextField();
		rbDescuentoGlobalValor = new JRadioButton();
		txtDescuentoGlobalValor = new JTextField();
		btnActualizarTotales = new JButton();
		panel112 = new JPanel();
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		txtIVAFinal = new JTextField();
		lblIVAFinal = new JLabel();
		lblDescuentoFinal = new JLabel();
		txtDescuentoFinal = new JTextField();
		lblICEFinal = new JLabel();
		txtICEFinal = new JTextField();
		lblDescuentosVariosTotal = new JLabel();
		txtDescuentosVariosTotal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblComisionAgencia = new JLabel();
		txtPorcentajeComision = new JTextField();
		lblValorComision = new JLabel();
		txtValorComision = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		spPresupuesto = new JScrollPane();
		panelPresupuesto = new JPanel();
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
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Pedido");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpPedido ========
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
							FormFactory.DEFAULT_ROWSPEC
						}));

					//======== panel2 ========
					{
						panel2.setBorder(new TitledBorder(null, "Tipo de Documento", TitledBorder.LEADING, TitledBorder.TOP));
						panel2.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(140)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(50)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.CENTER, Sizes.dluX(50), FormSpec.NO_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- lblTipoDocumento ----
						lblTipoDocumento.setText("Tipo de documento:");
						panel2.add(lblTipoDocumento, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel2.add(cmbTipoDocumento, cc.xy(5, 1));

						//---- lblGenerarFactura ----
						lblGenerarFactura.setText("Generar factura:");
						panel2.add(lblGenerarFactura, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel2.add(btnGenerarFactura, cc.xywh(11, 1, 1, 1, CellConstraints.CENTER, CellConstraints.FILL));

						//---- lblInformacionPedido ----
						lblInformacionPedido.setText("Informaci\u00f3n de Envio:");
						panel2.add(lblInformacionPedido, cc.xy(13, 1));

						//---- btnInformacionEnvio ----
						btnInformacionEnvio.setText(" ");
						panel2.add(btnInformacionEnvio, cc.xy(15, 1));
					}
					panel3.add(panel2, cc.xy(1, 1));

					//======== panel32 ========
					{
						panel32.setBorder(new TitledBorder(null, "Datos Generales", TitledBorder.LEADING, TitledBorder.TOP));
						panel32.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
						panel32.add(cmbFechaPedido, cc.xy(5, 1));

						//---- lblFechaPedido ----
						lblFechaPedido.setText("Fecha del pedido:");
						panel32.add(lblFechaPedido, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblFechaVencimiento ----
						lblFechaVencimiento.setText("Fecha de Vencimiento:");
						panel32.add(lblFechaVencimiento, cc.xy(9, 1));
						panel32.add(cmbFechaVencimiento, cc.xy(11, 1));

						//---- lblMoneda ----
						lblMoneda.setText("Moneda:");
						panel32.add(lblMoneda, cc.xywh(15, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbMoneda, cc.xy(17, 1));

						//---- lblCodigo ----
						lblCodigo.setText("C\u00f3digo:");
						panel32.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtCodigo ----
						txtCodigo.setHorizontalAlignment(SwingConstants.LEADING);
						panel32.add(txtCodigo, cc.xy(5, 3));

						//---- lblCaja ----
						lblCaja.setText("Caja:");
						panel32.add(lblCaja, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbCaja, cc.xy(17, 3));

						//---- lblOficina ----
						lblOficina.setText("Oficina :");
						panel32.add(lblOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbOficinaEmpresa, cc.xywh(5, 5, 7, 1));

						//---- lblEstado ----
						lblEstado.setText("Estado:");
						panel32.add(lblEstado, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel32.add(cmbEstado, cc.xy(17, 5));
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
								new ColumnSpec(Sizes.dluX(100)),
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
								new ColumnSpec(Sizes.dluX(95)),
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
						panel4.add(txtCorporacion, cc.xywh(5, 1, 3, 1));
						panel4.add(btnEncerarCliente, cc.xywh(11, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						panel4.add(btnBuscarCorporacion, cc.xywh(9, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblTipoIdentificacion ----
						lblTipoIdentificacion.setText("Tipo de identificaci\u00f3n:");
						panel4.add(lblTipoIdentificacion, cc.xywh(15, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(cmbTipoIdentificacion, cc.xy(17, 1));

						//---- lblCliente ----
						lblCliente.setText("Cliente:");
						panel4.add(lblCliente, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtCliente, cc.xywh(5, 3, 3, 1));
						panel4.add(btnBuscarCliente, cc.xywh(9, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						panel4.add(btnNuevoCliente, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblIdentificacion ----
						lblIdentificacion.setText("Identificaci\u00f3n:");
						panel4.add(lblIdentificacion, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtIdentificacion, cc.xywh(17, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblClienteOficina ----
						lblClienteOficina.setText("Oficina del cliente:");
						panel4.add(lblClienteOficina, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtClienteOficina, cc.xywh(5, 5, 3, 1));

						//---- lblTelefono ----
						lblTelefono.setText("Tel\u00e9fono:");
						panel4.add(lblTelefono, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtTelefono, cc.xy(17, 5));
						panel4.add(txtContacto, cc.xywh(5, 14, 3, 1));
						panel4.add(btnBuscarClienteOficina, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblTipoReferencia ----
						lblTipoReferencia.setText("Tipo de referencia:");
						panel4.add(lblTipoReferencia, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(cmbTipoReferencia, cc.xy(5, 7));

						//---- cbMostrarReferenciaFactura ----
						cbMostrarReferenciaFactura.setText("Mostrar Referencia en Factura");
						panel4.add(cbMostrarReferenciaFactura, cc.xywh(7, 7, 3, 1));

						//---- lblEscojaReferencia ----
						lblEscojaReferencia.setText("Escoja referencia:");
						panel4.add(lblEscojaReferencia, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtEscojaReferencia, cc.xywh(5, 9, 3, 1));
						panel4.add(btnEscojaReferencia, cc.xywh(9, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- cbMultipleFacturacion ----
						cbMultipleFacturacion.setText("M\u00faltiple");
						panel4.add(cbMultipleFacturacion, cc.xywh(11, 9, 3, 1));

						//---- lblAutorizacionSAP ----
						lblAutorizacionSAP.setText("SAP:");
						panel4.add(lblAutorizacionSAP, cc.xywh(15, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtAutorizacionSAP ----
						txtAutorizacionSAP.setHorizontalAlignment(SwingConstants.RIGHT);
						panel4.add(txtAutorizacionSAP, cc.xywh(17, 9, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

						//---- lblArchivoAdjunto ----
						lblArchivoAdjunto.setText("Archivo Adjunto:");
						panel4.add(lblArchivoAdjunto, cc.xywh(3, 10, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel4.add(txtArchivoAdjunto, cc.xywh(5, 10, 3, 1));
						panel4.add(btnVerArchivoAdjunto, cc.xywh(9, 10, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblReferencia ----
						lblReferencia.setText("Referencia:");
						panel4.add(lblReferencia, cc.xywh(3, 12, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtReferencia, cc.xywh(5, 12, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblContacto ----
						lblContacto.setText("Contacto:");
						panel4.add(lblContacto, cc.xywh(3, 14, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblDireccion ----
						lblDireccion.setText("Direcci\u00f3n:");
						panel4.add(lblDireccion, cc.xywh(3, 16, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel4.add(txtDireccion, cc.xywh(5, 16, 13, 1));

						//---- lblInformacionAdc ----
						lblInformacionAdc.setText("Inf. Adicional (cobro/pago):");
						panel4.add(lblInformacionAdc, cc.xy(3, 18));
						panel4.add(txtInformacionAdc, cc.xywh(5, 18, 13, 1));
					}
					panel3.add(panel4, cc.xy(1, 5));

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
							jpNegociacion.add(txtPorcentajeNegociacionDirecta, cc.xy(7, 3));

							//---- lblDsctoCompraPorcentaje ----
							lblDsctoCompraPorcentaje.setText("Descuento:");
							jpNegociacion.add(lblDsctoCompraPorcentaje, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

							//---- txtDsctoCompraPorcentaje ----
							txtDsctoCompraPorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
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
					panel3.add(bpNegociacionPanel, cc.xy(1, 7));

					//======== panel6 ========
					{
						panel6.setBorder(new TitledBorder(null, "Datos del Pedido", TitledBorder.LEADING, TitledBorder.TOP));
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
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));

						//---- lblObservacion ----
						lblObservacion.setText("Observaci\u00f3n:");
						panel6.add(lblObservacion, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel6.add(txtObservacion, cc.xywh(5, 1, 7, 1));

						//---- lblListaPrecio ----
						lblListaPrecio.setText("Lista de precios:");
						panel6.add(lblListaPrecio, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(cmbListaPrecio, cc.xy(5, 3));

						//---- lblDiasValidez ----
						lblDiasValidez.setText("D\u00edas de validez:");
						panel6.add(lblDiasValidez, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtDiasValidez ----
						txtDiasValidez.setHorizontalAlignment(SwingConstants.RIGHT);
						panel6.add(txtDiasValidez, cc.xy(11, 3));

						//---- lblFormaPago ----
						lblFormaPago.setText("Forma de pago:");
						panel6.add(lblFormaPago, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(cmbFormaPago, cc.xy(5, 5));

						//---- lblPuntoImpresion ----
						lblPuntoImpresion.setText("Punto de impresi\u00f3n:");
						panel6.add(lblPuntoImpresion, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPuntoImpresion ----
						txtPuntoImpresion.setEditable(false);
						panel6.add(txtPuntoImpresion, cc.xy(11, 5));

						//---- lblVendedor ----
						lblVendedor.setText("Vendedor:");
						panel6.add(lblVendedor, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(cmbVendedor, cc.xy(5, 7));

						//---- lblBodega ----
						lblBodega.setText("Bodega:");
						panel6.add(lblBodega, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel6.add(cmbBodega, cc.xy(11, 7));

						//---- lblDirectorCuentas ----
						lblDirectorCuentas.setText("Director(a):");
						panel6.add(lblDirectorCuentas, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel6.add(cmbDirectorCuentas, cc.xy(5, 9));

						//---- lblOrigenDocumento ----
						lblOrigenDocumento.setText("Origen del documento:");
						panel6.add(lblOrigenDocumento, cc.xy(9, 9));
						panel6.add(cmbOrigenDocumento, cc.xy(11, 9));

						//---- lblFundacion ----
						lblFundacion.setText("Fundaci\u00f3n:");
						panel6.add(lblFundacion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel6.add(cmbFundacion, cc.xy(5, 11));
					}
					panel3.add(panel6, cc.xy(1, 9));
				}
				scrollPane1.setViewportView(panel3);
			}
			jtpPedido.addTab("Pedido", scrollPane1);


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
								new ColumnSpec(Sizes.dluX(200)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(200)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default, 3dlu, default")));

						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						panel8.add(lblDocumento, cc.xywh(3, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblMotivo ----
						lblMotivo.setText("Motivo:");
						panel8.add(lblMotivo, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel8.add(cmbMotivo, cc.xy(11, 1));
						panel8.add(cmbDocumento, cc.xy(5, 1));
					}
					panel11.add(panel8, cc.xy(1, 1));

					//======== panel10 ========
					{
						panel10.setBorder(new TitledBorder(null, "Detalle", TitledBorder.LEADING, TitledBorder.TOP));
						panel10.setLayout(new FormLayout(
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
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
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
								FormFactory.DEFAULT_ROWSPEC
							}));
						((FormLayout)panel10.getLayout()).setColumnGroups(new int[][] {{3, 9}, {5, 11}});

						//======== scPlantilla ========
						{

							//---- tblPedidoDetalle ----
							tblPedidoDetalle.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Descripci\u00f3n", "Cantidad", "Precio Real", "Dscto. Agencia", "Descuento Global", "IVA", "Dsctos. Varios"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false, false, false
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblPedidoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblPedidoDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							scPlantilla.setViewportView(tblPedidoDetalle);
						}
						panel10.add(scPlantilla, cc.xywh(3, 21, 11, 1));

						//---- lblCodigoProducto ----
						lblCodigoProducto.setText("C\u00f3digo del producto:");
						panel10.add(lblCodigoProducto, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- btnBuscarProducto ----
						btnBuscarProducto.setIcon(null);
						panel10.add(btnBuscarProducto, cc.xywh(7, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- txtCodigoProducto ----
						txtCodigoProducto.setFocusable(true);
						panel10.add(txtCodigoProducto, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//======== spDescripcion ========
						{

							//---- txtDescripcion ----
							txtDescripcion.setLineWrap(true);
							spDescripcion.setViewportView(txtDescripcion);
						}
						panel10.add(spDescripcion, cc.xywh(5, 3, 9, 5));

						//---- lblCantidad ----
						lblCantidad.setText("Cantidad a pedir:");
						panel10.add(lblCantidad, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtCantidadPedida ----
						txtCantidadPedida.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCantidadPedida, cc.xy(5, 9));

						//---- lblCantidadPedida ----
						lblCantidadPedida.setText("Cantidad facturada:");
						panel10.add(lblCantidadPedida, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCantidad, cc.xywh(11, 9, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPrecio ----
						lblPrecio.setText("Precio:");
						panel10.add(lblPrecio, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPrecio ----
						txtPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPrecio, cc.xywh(5, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblProveedor ----
						lblProveedor.setText("Proveedor:");
						panel10.add(lblProveedor, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtProveedor ----
						txtProveedor.setFocusable(false);
						panel10.add(txtProveedor, cc.xywh(11, 11, 3, 1));

						//---- lblPrecioReal ----
						lblPrecioReal.setText("Precio real:");
						panel10.add(lblPrecioReal, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPrecioReal ----
						txtPrecioReal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPrecioReal, cc.xy(5, 13));

						//---- lblFacturaProveedor ----
						lblFacturaProveedor.setText("Factura Proveedor:");
						panel10.add(lblFacturaProveedor, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(txtFacturaProveedor, cc.xy(11, 13));

						//---- btnFacturaProveedor ----
						btnFacturaProveedor.setEnabled(false);
						panel10.add(btnFacturaProveedor, cc.xywh(13, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblPorcentajeDescuentoAgencia ----
						lblPorcentajeDescuentoAgencia.setText("Dscto. Agencia [%]:");
						panel10.add(lblPorcentajeDescuentoAgencia, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPorcentajeDescuentoAgencia ----
						txtPorcentajeDescuentoAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentoAgencia, cc.xywh(5, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblLinea ----
						lblLinea.setText("L\u00ednea:");
						panel10.add(lblLinea, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtLinea ----
						txtLinea.setFocusable(false);
						panel10.add(txtLinea, cc.xywh(11, 15, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPorcentajeDescuentosVarios ----
						lblPorcentajeDescuentosVarios.setText("Dsctos. Varios [%]:");
						panel10.add(lblPorcentajeDescuentosVarios, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeDescuentosVarios ----
						txtPorcentajeDescuentosVarios.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentosVarios, cc.xy(5, 17));

						//---- lblLote ----
						lblLote.setText("Lote:");
						panel10.add(lblLote, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(cmbLote, cc.xywh(11, 17, 3, 1));

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
							btnAgregarDetalle.setIcon(null);
							panel1.add(btnAgregarDetalle, cc.xy(1, 1));

							//---- btnActualizarDetalle ----
							btnActualizarDetalle.setText("U");
							btnActualizarDetalle.setIcon(null);
							panel1.add(btnActualizarDetalle, cc.xy(3, 1));

							//---- btnEliminarDetalle ----
							btnEliminarDetalle.setText("E");
							panel1.add(btnEliminarDetalle, cc.xy(5, 1));
						}
						panel10.add(panel1, cc.xywh(3, 19, 9, 1));
					}
					panel11.add(panel10, cc.xy(1, 3));

					//======== panel7 ========
					{
						panel7.setBorder(new TitledBorder(null, "Descuento Global", TitledBorder.LEADING, TitledBorder.TOP));
						panel7.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(50)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(50)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- rbDescuentoGlobalPorcentaje ----
						rbDescuentoGlobalPorcentaje.setText("Descuento global [%]:");
						panel7.add(rbDescuentoGlobalPorcentaje, cc.xy(3, 1));

						//---- txtDescuentoGlobalPorcentaje ----
						txtDescuentoGlobalPorcentaje.setHorizontalAlignment(SwingConstants.RIGHT);
						panel7.add(txtDescuentoGlobalPorcentaje, cc.xy(5, 1));

						//---- rbDescuentoGlobalValor ----
						rbDescuentoGlobalValor.setText("Descuento global [$]:");
						panel7.add(rbDescuentoGlobalValor, cc.xy(7, 1));

						//---- txtDescuentoGlobalValor ----
						txtDescuentoGlobalValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel7.add(txtDescuentoGlobalValor, cc.xy(9, 1));

						//---- btnActualizarTotales ----
						btnActualizarTotales.setText("U");
						panel7.add(btnActualizarTotales, cc.xy(11, 1));
					}
					panel11.add(panel7, cc.xy(1, 5));

					//======== panel112 ========
					{
						panel112.setBorder(new TitledBorder(null, "Totales", TitledBorder.LEADING, TitledBorder.TOP));
						panel112.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(40)),
								new ColumnSpec(ColumnSpec.LEFT, Sizes.DLUX3, FormSpec.NO_GROW),
								new ColumnSpec(Sizes.dluX(10)),
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
						panel112.add(txtValorFinal, cc.xywh(5, 1, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtIVAFinal, cc.xywh(17, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(15, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblDescuentoFinal ----
						lblDescuentoFinal.setText("Dscto. Agencia:");
						panel112.add(lblDescuentoFinal, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtDescuentoFinal ----
						txtDescuentoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtDescuentoFinal, cc.xywh(5, 3, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtICEFinal, cc.xywh(17, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDescuentosVariosTotal ----
						lblDescuentosVariosTotal.setText("Dsctos. Varios:");
						panel112.add(lblDescuentosVariosTotal, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtDescuentosVariosTotal ----
						txtDescuentosVariosTotal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtDescuentosVariosTotal.setEditable(false);
						panel112.add(txtDescuentosVariosTotal, cc.xywh(5, 5, 7, 1));

						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtOtroImpuestoFinal, cc.xywh(17, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblComisionAgencia ----
						lblComisionAgencia.setText("Comisi\u00f3n de Agencia [%]:");
						panel112.add(lblComisionAgencia, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeComision ----
						txtPorcentajeComision.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtPorcentajeComision, cc.xy(5, 7));

						//---- lblValorComision ----
						lblValorComision.setText("Valor [$]:");
						panel112.add(lblValorComision, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

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
						panel112.add(txtTotalFinal, cc.xywh(17, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel112, cc.xy(1, 7));
				}
				scrollPane2.setViewportView(panel11);
			}
			jtpPedido.addTab("Detalle", scrollPane2);


			//======== spPresupuesto ========
			{

				//======== panelPresupuesto ========
				{
					panelPresupuesto.setLayout(new FormLayout(
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
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
						}));

					//---- lblProveedorP ----
					lblProveedorP.setText("Proveedor:");
					panelPresupuesto.add(lblProveedorP, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtProveedorP ----
					txtProveedorP.setEditable(false);
					panelPresupuesto.add(txtProveedorP, cc.xywh(5, 3, 9, 1));

					//---- lblProductoP ----
					lblProductoP.setText("Producto:");
					panelPresupuesto.add(lblProductoP, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtProductoP ----
					txtProductoP.setEditable(false);
					panelPresupuesto.add(txtProductoP, cc.xywh(5, 5, 9, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblConceptoPresupuestoDetalleP ----
					lblConceptoPresupuestoDetalleP.setText("Concepto:");
					panelPresupuesto.add(lblConceptoPresupuestoDetalleP, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//======== spConceptoPresupuestoDetalleP ========
					{

						//---- txtConceptoPresupuestoDetalleP ----
						txtConceptoPresupuestoDetalleP.setWrapStyleWord(false);
						txtConceptoPresupuestoDetalleP.setRows(6);
						txtConceptoPresupuestoDetalleP.setLineWrap(true);
						txtConceptoPresupuestoDetalleP.setEditable(true);
						spConceptoPresupuestoDetalleP.setViewportView(txtConceptoPresupuestoDetalleP);
					}
					panelPresupuesto.add(spConceptoPresupuestoDetalleP, cc.xywh(5, 7, 21, 5));

					//---- lblPrecioVentaP ----
					lblPrecioVentaP.setText("Precio de venta:");
					panelPresupuesto.add(lblPrecioVentaP, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtPrecioVentaP ----
					txtPrecioVentaP.setHorizontalAlignment(SwingConstants.RIGHT);
					txtPrecioVentaP.setEditable(false);
					panelPresupuesto.add(txtPrecioVentaP, cc.xywh(5, 13, 3, 1));

					//---- txtCantidadP ----
					txtCantidadP.setHorizontalAlignment(SwingConstants.RIGHT);
					txtCantidadP.setEditable(false);
					panelPresupuesto.add(txtCantidadP, cc.xy(13, 13));

					//---- lblCantidadP ----
					lblCantidadP.setText("Cantidad:");
					panelPresupuesto.add(lblCantidadP, cc.xywh(11, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

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
						btnAgregarDetalleP.setEnabled(false);
						panel13.add(btnAgregarDetalleP, cc.xy(1, 1));

						//---- btnActualizarDetalleP ----
						btnActualizarDetalleP.setText("U");
						panel13.add(btnActualizarDetalleP, cc.xy(3, 1));

						//---- btnEliminarDetalleP ----
						btnEliminarDetalleP.setText("D");
						btnEliminarDetalleP.setEnabled(false);
						panel13.add(btnEliminarDetalleP, cc.xy(5, 1));
					}
					panelPresupuesto.add(panel13, cc.xywh(3, 16, 19, 1));

					//======== scPresupuestoDetalleP ========
					{

						//---- tblPresupuestoDetalleP ----
						tblPresupuestoDetalleP.setModel(new DefaultTableModel(
							new Object[][] {
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
					panelPresupuesto.add(scPresupuestoDetalleP, cc.xywh(3, 18, 25, 3, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				spPresupuesto.setViewportView(panelPresupuesto);
			}
			jtpPedido.addTab("Presupuesto", spPresupuesto);

		}
		add(jtpPedido, cc.xy(1, 1));

		//---- buttonGroupDescuento ----
		ButtonGroup buttonGroupDescuento = new ButtonGroup();
		buttonGroupDescuento.add(rbDescuentoGlobalPorcentaje);
		buttonGroupDescuento.add(rbDescuentoGlobalValor);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpPedido;
	private JScrollPane scrollPane1;
	private JPanel panel3;
	private JPanel panel2;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblGenerarFactura;
	private JButton btnGenerarFactura;
	private JLabel lblInformacionPedido;
	private JButton btnInformacionEnvio;
	private JPanel panel32;
	private DateComboBox cmbFechaPedido;
	private JLabel lblFechaPedido;
	private JLabel lblFechaVencimiento;
	private DateComboBox cmbFechaVencimiento;
	private JLabel lblMoneda;
	private JComboBox cmbMoneda;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblCaja;
	private JComboBox cmbCaja;
	private JLabel lblOficina;
	private JComboBox cmbOficinaEmpresa;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JPanel panel4;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnEncerarCliente;
	private JButton btnBuscarCorporacion;
	private JLabel lblTipoIdentificacion;
	private JComboBox cmbTipoIdentificacion;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JButton btnNuevoCliente;
	private JLabel lblIdentificacion;
	private JTextField txtIdentificacion;
	private JLabel lblClienteOficina;
	private JTextField txtClienteOficina;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JTextField txtContacto;
	private JButton btnBuscarClienteOficina;
	private JLabel lblTipoReferencia;
	private JComboBox cmbTipoReferencia;
	private JCheckBox cbMostrarReferenciaFactura;
	private JLabel lblEscojaReferencia;
	private JTextField txtEscojaReferencia;
	private JButton btnEscojaReferencia;
	private JCheckBox cbMultipleFacturacion;
	private JLabel lblAutorizacionSAP;
	private JTextField txtAutorizacionSAP;
	private JLabel lblArchivoAdjunto;
	private JTextField txtArchivoAdjunto;
	private JButton btnVerArchivoAdjunto;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblContacto;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblInformacionAdc;
	private JTextField txtInformacionAdc;
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
	private JPanel panel6;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JLabel lblListaPrecio;
	private JComboBox cmbListaPrecio;
	private JLabel lblDiasValidez;
	private JTextField txtDiasValidez;
	private JLabel lblFormaPago;
	private JComboBox cmbFormaPago;
	private JLabel lblPuntoImpresion;
	private JTextField txtPuntoImpresion;
	private JLabel lblVendedor;
	private JComboBox cmbVendedor;
	private JLabel lblBodega;
	private JComboBox cmbBodega;
	private JLabel lblDirectorCuentas;
	private JComboBox cmbDirectorCuentas;
	private JLabel lblOrigenDocumento;
	private JComboBox cmbOrigenDocumento;
	private JLabel lblFundacion;
	private JComboBox cmbFundacion;
	private JScrollPane scrollPane2;
	private JPanel panel11;
	private JPanel panel8;
	private JLabel lblDocumento;
	private JLabel lblMotivo;
	private JComboBox cmbMotivo;
	private JComboBox cmbDocumento;
	private JPanel panel10;
	private JScrollPane scPlantilla;
	private JTable tblPedidoDetalle;
	private JLabel lblCodigoProducto;
	private JButton btnBuscarProducto;
	private JTextField txtCodigoProducto;
	private JLabel lblDescripcion;
	private JScrollPane spDescripcion;
	private JTextArea txtDescripcion;
	private JLabel lblCantidad;
	private JTextField txtCantidadPedida;
	private JLabel lblCantidadPedida;
	private JTextField txtCantidad;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JLabel lblPrecioReal;
	private JTextField txtPrecioReal;
	private JLabel lblFacturaProveedor;
	private JTextField txtFacturaProveedor;
	private JButton btnFacturaProveedor;
	private JLabel lblPorcentajeDescuentoAgencia;
	private JTextField txtPorcentajeDescuentoAgencia;
	private JLabel lblLinea;
	private JTextField txtLinea;
	private JLabel lblPorcentajeDescuentosVarios;
	private JTextField txtPorcentajeDescuentosVarios;
	private JLabel lblLote;
	private JComboBox cmbLote;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JPanel panel7;
	private JRadioButton rbDescuentoGlobalPorcentaje;
	private JTextField txtDescuentoGlobalPorcentaje;
	private JRadioButton rbDescuentoGlobalValor;
	private JTextField txtDescuentoGlobalValor;
	private JButton btnActualizarTotales;
	private JPanel panel112;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JTextField txtIVAFinal;
	private JLabel lblIVAFinal;
	private JLabel lblDescuentoFinal;
	private JTextField txtDescuentoFinal;
	private JLabel lblICEFinal;
	private JTextField txtICEFinal;
	private JLabel lblDescuentosVariosTotal;
	private JTextField txtDescuentosVariosTotal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblComisionAgencia;
	private JTextField txtPorcentajeComision;
	private JLabel lblValorComision;
	private JTextField txtValorComision;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	private JScrollPane spPresupuesto;
	private JPanel panelPresupuesto;
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
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public DateComboBox getCmbFechaVencimiento() {
		return cmbFechaVencimiento;
	}

	public JComboBox getCmbOficinaEmpresa() {
		return cmbOficinaEmpresa;
	}

	public JCheckBox getCbMultipleFacturacion() {
		return cbMultipleFacturacion;
	}

	public JTextField getTxtDescuentosVariosTotal() {
		return txtDescuentosVariosTotal;
	}

	public JTextField getTxtPorcentajeDescuentosVarios() {
		return txtPorcentajeDescuentosVarios;
	}

	public JTextField getTxtPorcentajeDescuentoAgencia() {
		return txtPorcentajeDescuentoAgencia;
	}

	public JTextField getTxtAutorizacionSAP() {
		return txtAutorizacionSAP;
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

	public void setCmbDirectorCuentas(JComboBox cmbDirectorCuentas) {
		this.cmbDirectorCuentas = cmbDirectorCuentas;
	}

	public JComboBox getCmbDirectorCuentas() {
		return cmbDirectorCuentas;
	}

	public JideTabbedPane getJtpPedido() {
		return jtpPedido;
	}

	public void setJtpPedido(JideTabbedPane jtpPedido) {
		this.jtpPedido = jtpPedido;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public void setScrollPane1(JScrollPane scrollPane1) {
		this.scrollPane1 = scrollPane1;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JLabel getLblTipoDocumento() {
		return lblTipoDocumento;
	}

	public void setLblTipoDocumento(JLabel lblTipoDocumento) {
		this.lblTipoDocumento = lblTipoDocumento;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JLabel getLblGenerarFactura() {
		return lblGenerarFactura;
	}

	public void setLblGenerarFactura(JLabel lblGenerarFactura) {
		this.lblGenerarFactura = lblGenerarFactura;
	}

	public JButton getBtnGenerarFactura() {
		return btnGenerarFactura;
	}

	public void setBtnGenerarFactura(JButton btnGenerarFactura) {
		this.btnGenerarFactura = btnGenerarFactura;
	}

	public JLabel getLblInformacionPedido() {
		return lblInformacionPedido;
	}

	public void setLblInformacionPedido(JLabel lblInformacionPedido) {
		this.lblInformacionPedido = lblInformacionPedido;
	}

	public JButton getBtnInformacionEnvio() {
		return btnInformacionEnvio;
	}

	public void setBtnInformacionEnvio(JButton btnInformacionEnvio) {
		this.btnInformacionEnvio = btnInformacionEnvio;
	}

	public JPanel getPanel32() {
		return panel32;
	}

	public void setPanel32(JPanel panel32) {
		this.panel32 = panel32;
	}

	public DateComboBox getCmbFechaPedido() {
		return cmbFechaPedido;
	}

	public void setCmbFechaPedido(DateComboBox cmbFechaPedido) {
		this.cmbFechaPedido = cmbFechaPedido;
	}

	public JLabel getLblFechaPedido() {
		return lblFechaPedido;
	}

	public void setLblFechaPedido(JLabel lblFechaPedido) {
		this.lblFechaPedido = lblFechaPedido;
	}

	public JLabel getLblMoneda() {
		return lblMoneda;
	}

	public void setLblMoneda(JLabel lblMoneda) {
		this.lblMoneda = lblMoneda;
	}

	public JComboBox getCmbMoneda() {
		return cmbMoneda;
	}

	public void setCmbMoneda(JComboBox cmbMoneda) {
		this.cmbMoneda = cmbMoneda;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JLabel getLblCaja() {
		return lblCaja;
	}

	public void setLblCaja(JLabel lblCaja) {
		this.lblCaja = lblCaja;
	}

	public JComboBox getCmbCaja() {
		return cmbCaja;
	}

	public void setCmbCaja(JComboBox cmbCaja) {
		this.cmbCaja = cmbCaja;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public void setLblOficina(JLabel lblOficina) {
		this.lblOficina = lblOficina;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(JLabel lblEstado) {
		this.lblEstado = lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JLabel getLblCorporacion() {
		return lblCorporacion;
	}

	public void setLblCorporacion(JLabel lblCorporacion) {
		this.lblCorporacion = lblCorporacion;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JButton getBtnEncerarCliente() {
		return btnEncerarCliente;
	}

	public void setBtnEncerarCliente(JButton btnEncerarCliente) {
		this.btnEncerarCliente = btnEncerarCliente;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JLabel getLblTipoIdentificacion() {
		return lblTipoIdentificacion;
	}

	public void setLblTipoIdentificacion(JLabel lblTipoIdentificacion) {
		this.lblTipoIdentificacion = lblTipoIdentificacion;
	}

	public JComboBox getCmbTipoIdentificacion() {
		return cmbTipoIdentificacion;
	}

	public void setCmbTipoIdentificacion(JComboBox cmbTipoIdentificacion) {
		this.cmbTipoIdentificacion = cmbTipoIdentificacion;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnNuevoCliente() {
		return btnNuevoCliente;
	}

	public void setBtnNuevoCliente(JButton btnNuevoCliente) {
		this.btnNuevoCliente = btnNuevoCliente;
	}

	public JLabel getLblIdentificacion() {
		return lblIdentificacion;
	}

	public void setLblIdentificacion(JLabel lblIdentificacion) {
		this.lblIdentificacion = lblIdentificacion;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public JLabel getLblClienteOficina() {
		return lblClienteOficina;
	}

	public void setLblClienteOficina(JLabel lblClienteOficina) {
		this.lblClienteOficina = lblClienteOficina;
	}

	public JTextField getTxtClienteOficina() {
		return txtClienteOficina;
	}

	public void setTxtClienteOficina(JTextField txtClienteOficina) {
		this.txtClienteOficina = txtClienteOficina;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JLabel getLblContacto() {
		return lblContacto;
	}

	public void setLblContacto(JLabel lblContacto) {
		this.lblContacto = lblContacto;
	}

	public JTextField getTxtContacto() {
		return txtContacto;
	}

	public void setTxtContacto(JTextField txtContacto) {
		this.txtContacto = txtContacto;
	}

	public JButton getBtnBuscarClienteOficina() {
		return btnBuscarClienteOficina;
	}

	public void setBtnBuscarClienteOficina(JButton btnBuscarClienteOficina) {
		this.btnBuscarClienteOficina = btnBuscarClienteOficina;
	}

	public JLabel getLblTipoReferencia() {
		return lblTipoReferencia;
	}

	public void setLblTipoReferencia(JLabel lblTipoReferencia) {
		this.lblTipoReferencia = lblTipoReferencia;
	}

	public JComboBox getCmbTipoReferencia() {
		return cmbTipoReferencia;
	}

	public void setCmbTipoReferencia(JComboBox cmbTipoReferencia) {
		this.cmbTipoReferencia = cmbTipoReferencia;
	}

	public JLabel getLblEscojaReferencia() {
		return lblEscojaReferencia;
	}

	public void setLblEscojaReferencia(JLabel lblEscojaReferencia) {
		this.lblEscojaReferencia = lblEscojaReferencia;
	}

	public JTextField getTxtEscojaReferencia() {
		return txtEscojaReferencia;
	}

	public void setTxtEscojaReferencia(JTextField txtEscojaReferencia) {
		this.txtEscojaReferencia = txtEscojaReferencia;
	}

	public JButton getBtnEscojaReferencia() {
		return btnEscojaReferencia;
	}

	public void setBtnEscojaReferencia(JButton btnEscojaReferencia) {
		this.btnEscojaReferencia = btnEscojaReferencia;
	}

	public JLabel getLblArchivoAdjunto() {
		return lblArchivoAdjunto;
	}

	public void setLblArchivoAdjunto(JLabel lblArchivoAdjunto) {
		this.lblArchivoAdjunto = lblArchivoAdjunto;
	}

	public JTextField getTxtArchivoAdjunto() {
		return txtArchivoAdjunto;
	}

	public void setTxtArchivoAdjunto(JTextField txtArchivoAdjunto) {
		this.txtArchivoAdjunto = txtArchivoAdjunto;
	}

	public JButton getBtnVerArchivoAdjunto() {
		return btnVerArchivoAdjunto;
	}

	public void setBtnVerArchivoAdjunto(JButton btnVerArchivoAdjunto) {
		this.btnVerArchivoAdjunto = btnVerArchivoAdjunto;
	}

	public JLabel getLblReferencia() {
		return lblReferencia;
	}

	public void setLblReferencia(JLabel lblReferencia) {
		this.lblReferencia = lblReferencia;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JLabel getLblInformacionAdc() {
		return lblInformacionAdc;
	}

	public void setLblInformacionAdc(JLabel lblInformacionAdc) {
		this.lblInformacionAdc = lblInformacionAdc;
	}

	public JTextField getTxtInformacionAdc() {
		return txtInformacionAdc;
	}

	public void setTxtInformacionAdc(JTextField txtInformacionAdc) {
		this.txtInformacionAdc = txtInformacionAdc;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public void setPanel6(JPanel panel6) {
		this.panel6 = panel6;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public void setLblObservacion(JLabel lblObservacion) {
		this.lblObservacion = lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JLabel getLblListaPrecio() {
		return lblListaPrecio;
	}

	public void setLblListaPrecio(JLabel lblListaPrecio) {
		this.lblListaPrecio = lblListaPrecio;
	}

	public JComboBox getCmbListaPrecio() {
		return cmbListaPrecio;
	}

	public void setCmbListaPrecio(JComboBox cmbListaPrecio) {
		this.cmbListaPrecio = cmbListaPrecio;
	}

	public JLabel getLblDiasValidez() {
		return lblDiasValidez;
	}

	public void setLblDiasValidez(JLabel lblDiasValidez) {
		this.lblDiasValidez = lblDiasValidez;
	}

	public JTextField getTxtDiasValidez() {
		return txtDiasValidez;
	}

	public void setTxtDiasValidez(JTextField txtDiasValidez) {
		this.txtDiasValidez = txtDiasValidez;
	}

	public JLabel getLblFormaPago() {
		return lblFormaPago;
	}

	public void setLblFormaPago(JLabel lblFormaPago) {
		this.lblFormaPago = lblFormaPago;
	}

	public JComboBox getCmbFormaPago() {
		return cmbFormaPago;
	}

	public void setCmbFormaPago(JComboBox cmbFormaPago) {
		this.cmbFormaPago = cmbFormaPago;
	}

	public JLabel getLblPuntoImpresion() {
		return lblPuntoImpresion;
	}

	public void setLblPuntoImpresion(JLabel lblPuntoImpresion) {
		this.lblPuntoImpresion = lblPuntoImpresion;
	}

	public JTextField getTxtPuntoImpresion() {
		return txtPuntoImpresion;
	}

	public void setTxtPuntoImpresion(JTextField txtPuntoImpresion) {
		this.txtPuntoImpresion = txtPuntoImpresion;
	}

	public JLabel getLblVendedor() {
		return lblVendedor;
	}

	public void setLblVendedor(JLabel lblVendedor) {
		this.lblVendedor = lblVendedor;
	}

	public JComboBox getCmbVendedor() {
		return cmbVendedor;
	}

	public void setCmbVendedor(JComboBox cmbVendedor) {
		this.cmbVendedor = cmbVendedor;
	}

	public JLabel getLblBodega() {
		return lblBodega;
	}

	public void setLblBodega(JLabel lblBodega) {
		this.lblBodega = lblBodega;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public void setCmbBodega(JComboBox cmbBodega) {
		this.cmbBodega = cmbBodega;
	}

	public JLabel getLblFundacion() {
		return lblFundacion;
	}

	public void setLblFundacion(JLabel lblFundacion) {
		this.lblFundacion = lblFundacion;
	}

	public JComboBox getCmbFundacion() {
		return cmbFundacion;
	}

	public void setCmbFundacion(JComboBox cmbFundacion) {
		this.cmbFundacion = cmbFundacion;
	}

	public JComboBox getCmbOrigenDocumento() {
		return cmbOrigenDocumento;
	}

	public void setCmbOrigenDocumento(JComboBox cmbOrigenDocumento) {
		this.cmbOrigenDocumento = cmbOrigenDocumento;
	}

	public JLabel getLblOrigenDocumento() {
		return lblOrigenDocumento;
	}

	public void setLblOrigenDocumento(JLabel lblOrigenDocumento) {
		this.lblOrigenDocumento = lblOrigenDocumento;
	}

	public JScrollPane getScrollPane2() {
		return scrollPane2;
	}

	public void setScrollPane2(JScrollPane scrollPane2) {
		this.scrollPane2 = scrollPane2;
	}

	public JPanel getPanel11() {
		return panel11;
	}

	public void setPanel11(JPanel panel11) {
		this.panel11 = panel11;
	}

	public JPanel getPanel8() {
		return panel8;
	}

	public void setPanel8(JPanel panel8) {
		this.panel8 = panel8;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public void setLblDocumento(JLabel lblDocumento) {
		this.lblDocumento = lblDocumento;
	}

	public JLabel getLblMotivo() {
		return lblMotivo;
	}

	public void setLblMotivo(JLabel lblMotivo) {
		this.lblMotivo = lblMotivo;
	}

	public JComboBox getCmbMotivo() {
		return cmbMotivo;
	}

	public void setCmbMotivo(JComboBox cmbMotivo) {
		this.cmbMotivo = cmbMotivo;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JPanel getPanel10() {
		return panel10;
	}

	public void setPanel10(JPanel panel10) {
		this.panel10 = panel10;
	}

	public JScrollPane getScPlantilla() {
		return scPlantilla;
	}

	public void setScPlantilla(JScrollPane scPlantilla) {
		this.scPlantilla = scPlantilla;
	}

	public JTable getTblPedidoDetalle() {
		return tblPedidoDetalle;
	}

	public void setTblPedidoDetalle(JTable tblPedidoDetalle) {
		this.tblPedidoDetalle = tblPedidoDetalle;
	}

	public JLabel getLblCodigoProducto() {
		return lblCodigoProducto;
	}

	public void setLblCodigoProducto(JLabel lblCodigoProducto) {
		this.lblCodigoProducto = lblCodigoProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JTextField getTxtCodigoProducto() {
		return txtCodigoProducto;
	}

	public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JScrollPane getSpDescripcion() {
		return spDescripcion;
	}

	public void setSpDescripcion(JScrollPane spDescripcion) {
		this.spDescripcion = spDescripcion;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextArea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public JTextField getTxtCantidadPedida() {
		return txtCantidadPedida;
	}

	public void setTxtCantidadPedida(JTextField txtCantidadPedida) {
		this.txtCantidadPedida = txtCantidadPedida;
	}

	public JLabel getLblCantidadPedida() {
		return lblCantidadPedida;
	}

	public void setLblCantidadPedida(JLabel lblCantidadPedida) {
		this.lblCantidadPedida = lblCantidadPedida;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public void setLblProveedor(JLabel lblProveedor) {
		this.lblProveedor = lblProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JLabel getLblPrecioReal() {
		return lblPrecioReal;
	}

	public void setLblPrecioReal(JLabel lblPrecioReal) {
		this.lblPrecioReal = lblPrecioReal;
	}

	public JTextField getTxtPrecioReal() {
		return txtPrecioReal;
	}

	public void setTxtPrecioReal(JTextField txtPrecioReal) {
		this.txtPrecioReal = txtPrecioReal;
	}

	public JLabel getLblFacturaProveedor() {
		return lblFacturaProveedor;
	}

	public void setLblFacturaProveedor(JLabel lblFacturaProveedor) {
		this.lblFacturaProveedor = lblFacturaProveedor;
	}

	public JTextField getTxtFacturaProveedor() {
		return txtFacturaProveedor;
	}

	public void setTxtFacturaProveedor(JTextField txtFacturaProveedor) {
		this.txtFacturaProveedor = txtFacturaProveedor;
	}

	public JButton getBtnFacturaProveedor() {
		return btnFacturaProveedor;
	}

	public void setBtnFacturaProveedor(JButton btnFacturaProveedor) {
		this.btnFacturaProveedor = btnFacturaProveedor;
	}

	public JLabel getLblLinea() {
		return lblLinea;
	}

	public void setLblLinea(JLabel lblLinea) {
		this.lblLinea = lblLinea;
	}

	public JTextField getTxtLinea() {
		return txtLinea;
	}

	public void setTxtLinea(JTextField txtLinea) {
		this.txtLinea = txtLinea;
	}

	public JLabel getLblLote() {
		return lblLote;
	}

	public void setLblLote(JLabel lblLote) {
		this.lblLote = lblLote;
	}

	public JComboBox getCmbLote() {
		return cmbLote;
	}

	public void setCmbLote(JComboBox cmbLote) {
		this.cmbLote = cmbLote;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public void setBtnActualizarDetalle(JButton btnActualizarDetalle) {
		this.btnActualizarDetalle = btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JPanel getPanel7() {
		return panel7;
	}

	public void setPanel7(JPanel panel7) {
		this.panel7 = panel7;
	}

	public JRadioButton getRbDescuentoGlobalPorcentaje() {
		return rbDescuentoGlobalPorcentaje;
	}

	public void setRbDescuentoGlobalPorcentaje(
			JRadioButton rbDescuentoGlobalPorcentaje) {
		this.rbDescuentoGlobalPorcentaje = rbDescuentoGlobalPorcentaje;
	}

	public JTextField getTxtDescuentoGlobalPorcentaje() {
		return txtDescuentoGlobalPorcentaje;
	}

	public void setTxtDescuentoGlobalPorcentaje(
			JTextField txtDescuentoGlobalPorcentaje) {
		this.txtDescuentoGlobalPorcentaje = txtDescuentoGlobalPorcentaje;
	}

	public JRadioButton getRbDescuentoGlobalValor() {
		return rbDescuentoGlobalValor;
	}

	public void setRbDescuentoGlobalValor(JRadioButton rbDescuentoGlobalValor) {
		this.rbDescuentoGlobalValor = rbDescuentoGlobalValor;
	}

	public JTextField getTxtDescuentoGlobalValor() {
		return txtDescuentoGlobalValor;
	}

	public void setTxtDescuentoGlobalValor(JTextField txtDescuentoGlobalValor) {
		this.txtDescuentoGlobalValor = txtDescuentoGlobalValor;
	}

	public JButton getBtnActualizarTotales() {
		return btnActualizarTotales;
	}

	public void setBtnActualizarTotales(JButton btnActualizarTotales) {
		this.btnActualizarTotales = btnActualizarTotales;
	}

	public JPanel getPanel112() {
		return panel112;
	}

	public void setPanel112(JPanel panel112) {
		this.panel112 = panel112;
	}

	public JLabel getLblValorFinal() {
		return lblValorFinal;
	}

	public void setLblValorFinal(JLabel lblValorFinal) {
		this.lblValorFinal = lblValorFinal;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public void setTxtValorFinal(JTextField txtValorFinal) {
		this.txtValorFinal = txtValorFinal;
	}

	public JTextField getTxtIVAFinal() {
		return txtIVAFinal;
	}

	public void setTxtIVAFinal(JTextField txtIVAFinal) {
		this.txtIVAFinal = txtIVAFinal;
	}

	public JLabel getLblIVAFinal() {
		return lblIVAFinal;
	}

	public void setLblIVAFinal(JLabel lblIVAFinal) {
		this.lblIVAFinal = lblIVAFinal;
	}

	public JLabel getLblDescuentoFinal() {
		return lblDescuentoFinal;
	}

	public void setLblDescuentoFinal(JLabel lblDescuentoFinal) {
		this.lblDescuentoFinal = lblDescuentoFinal;
	}

	public JTextField getTxtDescuentoFinal() {
		return txtDescuentoFinal;
	}

	public void setTxtDescuentoFinal(JTextField txtDescuentoFinal) {
		this.txtDescuentoFinal = txtDescuentoFinal;
	}

	public JLabel getLblICEFinal() {
		return lblICEFinal;
	}

	public void setLblICEFinal(JLabel lblICEFinal) {
		this.lblICEFinal = lblICEFinal;
	}

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public void setTxtICEFinal(JTextField txtICEFinal) {
		this.txtICEFinal = txtICEFinal;
	}

	public JLabel getLblComisionAgencia() {
		return lblComisionAgencia;
	}

	public void setLblComisionAgencia(JLabel lblComisionAgencia) {
		this.lblComisionAgencia = lblComisionAgencia;
	}

	public JTextField getTxtPorcentajeComision() {
		return txtPorcentajeComision;
	}

	public void setTxtPorcentajeComision(JTextField txtPorcentajeComision) {
		this.txtPorcentajeComision = txtPorcentajeComision;
	}

	public JLabel getLblValorComision() {
		return lblValorComision;
	}

	public void setLblValorComision(JLabel lblValorComision) {
		this.lblValorComision = lblValorComision;
	}

	public JTextField getTxtValorComision() {
		return txtValorComision;
	}

	public void setTxtValorComision(JTextField txtValorComision) {
		this.txtValorComision = txtValorComision;
	}

	public JLabel getLblOtroImpuestoFinal() {
		return lblOtroImpuestoFinal;
	}

	public void setLblOtroImpuestoFinal(JLabel lblOtroImpuestoFinal) {
		this.lblOtroImpuestoFinal = lblOtroImpuestoFinal;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public void setTxtOtroImpuestoFinal(JTextField txtOtroImpuestoFinal) {
		this.txtOtroImpuestoFinal = txtOtroImpuestoFinal;
	}

	public JLabel getLblTotalFinal() {
		return lblTotalFinal;
	}

	public void setLblTotalFinal(JLabel lblTotalFinal) {
		this.lblTotalFinal = lblTotalFinal;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public void setTxtTotalFinal(JTextField txtTotalFinal) {
		this.txtTotalFinal = txtTotalFinal;
	}

	public JScrollPane getSpPresupuesto() {
		return spPresupuesto;
	}

	public void setSpPresupuesto(JScrollPane spPresupuesto) {
		this.spPresupuesto = spPresupuesto;
	}

	public JPanel getPanelPresupuesto() {
		return panelPresupuesto;
	}

	public void setPanelPresupuesto(JPanel panelPresupuesto) {
		this.panelPresupuesto = panelPresupuesto;
	}

	public JLabel getLblProveedorP() {
		return lblProveedorP;
	}

	public void setLblProveedorP(JLabel lblProveedorP) {
		this.lblProveedorP = lblProveedorP;
	}

	public JTextField getTxtProveedorP() {
		return txtProveedorP;
	}

	public void setTxtProveedorP(JTextField txtProveedorP) {
		this.txtProveedorP = txtProveedorP;
	}

	public JLabel getLblProductoP() {
		return lblProductoP;
	}

	public void setLblProductoP(JLabel lblProductoP) {
		this.lblProductoP = lblProductoP;
	}

	public JTextField getTxtProductoP() {
		return txtProductoP;
	}

	public void setTxtProductoP(JTextField txtProductoP) {
		this.txtProductoP = txtProductoP;
	}

	public JLabel getLblConceptoPresupuestoDetalleP() {
		return lblConceptoPresupuestoDetalleP;
	}

	public void setLblConceptoPresupuestoDetalleP(
			JLabel lblConceptoPresupuestoDetalleP) {
		this.lblConceptoPresupuestoDetalleP = lblConceptoPresupuestoDetalleP;
	}

	public JScrollPane getSpConceptoPresupuestoDetalleP() {
		return spConceptoPresupuestoDetalleP;
	}

	public void setSpConceptoPresupuestoDetalleP(
			JScrollPane spConceptoPresupuestoDetalleP) {
		this.spConceptoPresupuestoDetalleP = spConceptoPresupuestoDetalleP;
	}

	public JTextArea getTxtConceptoPresupuestoDetalleP() {
		return txtConceptoPresupuestoDetalleP;
	}

	public void setTxtConceptoPresupuestoDetalleP(
			JTextArea txtConceptoPresupuestoDetalleP) {
		this.txtConceptoPresupuestoDetalleP = txtConceptoPresupuestoDetalleP;
	}

	public JLabel getLblPrecioVentaP() {
		return lblPrecioVentaP;
	}

	public void setLblPrecioVentaP(JLabel lblPrecioVentaP) {
		this.lblPrecioVentaP = lblPrecioVentaP;
	}

	public JTextField getTxtPrecioVentaP() {
		return txtPrecioVentaP;
	}

	public void setTxtPrecioVentaP(JTextField txtPrecioVentaP) {
		this.txtPrecioVentaP = txtPrecioVentaP;
	}

	public JTextField getTxtCantidadP() {
		return txtCantidadP;
	}

	public void setTxtCantidadP(JTextField txtCantidadP) {
		this.txtCantidadP = txtCantidadP;
	}

	public JLabel getLblCantidadP() {
		return lblCantidadP;
	}

	public void setLblCantidadP(JLabel lblCantidadP) {
		this.lblCantidadP = lblCantidadP;
	}

	public JPanel getPanel13() {
		return panel13;
	}

	public void setPanel13(JPanel panel13) {
		this.panel13 = panel13;
	}

	public JButton getBtnAgregarDetalleP() {
		return btnAgregarDetalleP;
	}

	public void setBtnAgregarDetalleP(JButton btnAgregarDetalleP) {
		this.btnAgregarDetalleP = btnAgregarDetalleP;
	}

	public JButton getBtnActualizarDetalleP() {
		return btnActualizarDetalleP;
	}

	public void setBtnActualizarDetalleP(JButton btnActualizarDetalleP) {
		this.btnActualizarDetalleP = btnActualizarDetalleP;
	}

	public JButton getBtnEliminarDetalleP() {
		return btnEliminarDetalleP;
	}

	public void setBtnEliminarDetalleP(JButton btnEliminarDetalleP) {
		this.btnEliminarDetalleP = btnEliminarDetalleP;
	}

	public JScrollPane getScPresupuestoDetalleP() {
		return scPresupuestoDetalleP;
	}

	public void setScPresupuestoDetalleP(JScrollPane scPresupuestoDetalleP) {
		this.scPresupuestoDetalleP = scPresupuestoDetalleP;
	}

	public JTable getTblPresupuestoDetalleP() {
		return tblPresupuestoDetalleP;
	}

	public void setTblPresupuestoDetalleP(JTable tblPresupuestoDetalleP) {
		this.tblPresupuestoDetalleP = tblPresupuestoDetalleP;
	}

	public JButton getBtnMostrarPanelNegociacion() {
		return btnMostrarPanelNegociacion;
	}

	public JCheckBox getCbMostrarReferenciaFactura() {
		return cbMostrarReferenciaFactura;
	}
}
