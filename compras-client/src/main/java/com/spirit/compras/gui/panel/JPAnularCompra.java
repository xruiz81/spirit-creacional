package com.spirit.compras.gui.panel;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
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

public abstract class JPAnularCompra extends SpiritModelImpl {
	public JPAnularCompra() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpCompras = new JideTabbedPane();
		spCabecera = new JScrollPane();
		panel32 = new JPanel();
		goodiesFormsSeparator1 = compFactory.createSeparator("Datos de la Compra");
		lblCodigo = new JLabel();
		lblFechaEmision = new JLabel();
		cmbFecha = new DateComboBox();
		txtCodigo = new JTextField();
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		lblFechaVencimiento = new JLabel();
		lblProveedor = new JLabel();
		cmbFechaVencimiento = new DateComboBox();
		btnBuscarProveedor = new JButton();
		txtProveedor = new JTextField();
		lblFechaCaducidad = new JLabel();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblMoneda = new JLabel();
		cmbFechaCaducidad = new DateComboBox();
		lblOrdenCompra = new JLabel();
		txtOrden = new JTextField();
		btnBuscarOrden = new JButton();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		cmbMoneda = new JComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblTipoCompra = new JLabel();
		cmbTipoCompra = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		goodiesFormsSeparator3 = compFactory.createSeparator("Datos Generales");
		lblTipoSustentoTributario = new JLabel();
		cmbTipoSustentoTributario = new JComboBox();
		txtPreimpreso = new JTextField();
		lblPreimpreso = new JLabel();
		txtAutorizacion = new JTextField();
		lblAutorizacion = new JLabel();
		txtUsuario = new JTextField();
		lblUsuario = new JLabel();
		spDetalle = new JScrollPane();
		panel11 = new JPanel();
		panel10 = new JPanel();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		scPlantilla = new JScrollPane();
		tblCompraDetalle = new JTable();
		lblCodigoProducto = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		lblDescripcion = new JLabel();
		spDescripcion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblPorcentajeDescuentoEspecial = new JLabel();
		txtPorcentajeDescuentoEspecial = new JTextField();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		lblPorcentajeDescuentoAgencia = new JLabel();
		txtPorcentajeDescuentoAgencia = new JTextField();
		lblOtroImpuesto = new JLabel();
		txtOtroImpuesto = new JTextField();
		lblPorcentajeDescuentosVarios = new JLabel();
		txtPorcentajeDescuentosVarios = new JTextField();
		lblRetencionRenta = new JLabel();
		cmbRetencionRenta = new JComboBox();
		lblRetencionIva = new JLabel();
		cmbRetencionIva = new JComboBox();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		panel112 = new JPanel();
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		lblICEFinal = new JLabel();
		txtICEFinal = new JTextField();
		lblDescuentoEspecialFinal = new JLabel();
		txtDescuentoEspecialFinal = new JTextField();
		lblRetencionFinal = new JLabel();
		txtRetencionFinal = new JTextField();
		lblDescuentoAgenciaFinal = new JLabel();
		txtDescuentoAgenciaFinal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblDescuentosVariosFinal = new JLabel();
		txtDescuentosVariosFinal = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		lblIVAFinal = new JLabel();
		txtIVAFinal = new JTextField();
		spRetenciones = new JScrollPane();
		panelRetenciones = new JPanel();
		lblNoSerieSecuencial = new JLabel();
		txtEstablecimiento = new JTextField();
		txtPuntoEmision = new JTextField();
		txtSecuencial = new JTextField();
		lblNoAutorizacion = new JLabel();
		txtAutorizacionRetencion = new JTextField();
		lblFechaEmisionRetencion = new JLabel();
		cmbFechaEmision = new DateComboBox();
		panel12 = new JPanel();
		btnActualizarRetencion = new JButton();
		spTblRetenciones = new JScrollPane();
		tblRetenciones = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Anular Compra");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpCompras ========
		{

			//======== spCabecera ========
			{

				//======== panel32 ========
				{
					panel32.setBorder(null);
					panel32.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX5),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX5),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(95)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX5)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY5),
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
							new RowSpec(Sizes.dluY(12))
						}));
					panel32.add(goodiesFormsSeparator1, cc.xywh(3, 3, 15, 1));

					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panel32.add(lblCodigo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- lblFechaEmision ----
					lblFechaEmision.setText("Fecha de Emisi\u00f3n:");
					lblFechaEmision.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblFechaEmision, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(cmbFecha, cc.xy(17, 5));

					//---- txtCodigo ----
					txtCodigo.setHorizontalAlignment(SwingConstants.LEADING);
					txtCodigo.setEditable(false);
					panel32.add(txtCodigo, cc.xy(5, 5));

					//---- lblOficina ----
					lblOficina.setText("Oficina:");
					lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtOficina ----
					txtOficina.setEditable(false);
					txtOficina.setHorizontalAlignment(SwingConstants.LEADING);
					panel32.add(txtOficina, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblFechaVencimiento ----
					lblFechaVencimiento.setText("Fecha vencimiento:");
					lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblFechaVencimiento, cc.xy(15, 7));

					//---- lblProveedor ----
					lblProveedor.setText("Proveedor:");
					lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblProveedor, cc.xy(3, 9));
					panel32.add(cmbFechaVencimiento, cc.xy(17, 7));
					panel32.add(btnBuscarProveedor, cc.xywh(11, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- txtProveedor ----
					txtProveedor.setFocusable(false);
					panel32.add(txtProveedor, cc.xywh(5, 9, 5, 1));

					//---- lblFechaCaducidad ----
					lblFechaCaducidad.setText("Fecha caducidad:");
					panel32.add(lblFechaCaducidad, cc.xywh(15, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- lblTipoDocumento ----
					lblTipoDocumento.setText("T. de documento:");
					lblTipoDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblTipoDocumento, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(cmbTipoDocumento, cc.xywh(5, 15, 5, 1));

					//---- lblMoneda ----
					lblMoneda.setText("Moneda:");
					lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblMoneda, cc.xywh(15, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(cmbFechaCaducidad, cc.xy(17, 9));

					//---- lblOrdenCompra ----
					lblOrdenCompra.setText("Orden:");
					panel32.add(lblOrdenCompra, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(txtOrden, cc.xywh(5, 11, 5, 1));
					panel32.add(btnBuscarOrden, cc.xywh(11, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panel32.add(lblReferencia, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panel32.add(txtReferencia, cc.xywh(5, 13, 5, 1));
					panel32.add(cmbMoneda, cc.xy(17, 15));

					//---- lblEstado ----
					lblEstado.setText("Estado:");
					lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblEstado, cc.xy(3, 17));
					panel32.add(cmbEstado, cc.xy(5, 17));

					//---- lblTipoCompra ----
					lblTipoCompra.setText("Tipo de compra:");
					lblTipoCompra.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblTipoCompra, cc.xy(15, 17));
					panel32.add(cmbTipoCompra, cc.xy(17, 17));

					//---- lblObservacion ----
					lblObservacion.setText("Observaci\u00f3n:");
					lblObservacion.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblObservacion, cc.xy(3, 19));
					panel32.add(txtObservacion, cc.xywh(5, 19, 13, 1));
					panel32.add(goodiesFormsSeparator3, cc.xywh(3, 23, 15, 1));

					//---- lblTipoSustentoTributario ----
					lblTipoSustentoTributario.setText("T. sust. tributario:");
					panel32.add(lblTipoSustentoTributario, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panel32.add(cmbTipoSustentoTributario, cc.xywh(5, 25, 13, 1));
					panel32.add(txtPreimpreso, cc.xy(5, 27));

					//---- lblPreimpreso ----
					lblPreimpreso.setText("Preimpreso:");
					lblPreimpreso.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblPreimpreso, cc.xy(3, 27));
					panel32.add(txtAutorizacion, cc.xy(5, 29));

					//---- lblAutorizacion ----
					lblAutorizacion.setText("Autorizaci\u00f3n:");
					lblAutorizacion.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblAutorizacion, cc.xy(3, 29));

					//---- txtUsuario ----
					txtUsuario.setEditable(false);
					panel32.add(txtUsuario, cc.xy(5, 31));

					//---- lblUsuario ----
					lblUsuario.setText("Usuario:");
					lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblUsuario, cc.xy(3, 31));
				}
				spCabecera.setViewportView(panel32);
			}
			jtpCompras.addTab("Cabecera", spCabecera);


			//======== spDetalle ========
			{

				//======== panel11 ========
				{
					panel11.setLayout(new FormLayout(
						ColumnSpec.decodeSpecs("default:grow"),
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));

					//======== panel10 ========
					{
						panel10.setBorder(new TitledBorder(null, "Detalle", TitledBorder.LEADING, TitledBorder.TOP));
						panel10.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(60)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(60)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(60)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(80)),
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
								new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));

						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblDocumento, cc.xywh(3, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel10.add(cmbDocumento, cc.xywh(5, 1, 13, 1));

						//======== scPlantilla ========
						{

							//---- tblCompraDetalle ----
							tblCompraDetalle.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null, null, null, null, null, null, null, null, null, null},
								},
								new String[] {
									"Producto", "Cantidad", "Valor", "Dscto. Especial", "Dscto. Agencia", "Dsctos. Varios", "IVA", "Retenci\u00f3n", "ICE", "Otr. Imp.", "Total"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false, false, false, false, false, false, true
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblCompraDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblCompraDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							scPlantilla.setViewportView(tblCompraDetalle);
						}
						panel10.add(scPlantilla, cc.xywh(3, 25, 21, 1));

						//---- lblCodigoProducto ----
						lblCodigoProducto.setText("Producto:");
						lblCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCodigoProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtProducto, cc.xywh(5, 3, 13, 1));
						panel10.add(btnBuscarProducto, cc.xywh(19, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//======== spDescripcion ========
						{

							//---- txtDescripcion ----
							txtDescripcion.setLineWrap(true);
							spDescripcion.setViewportView(txtDescripcion);
						}
						panel10.add(spDescripcion, cc.xywh(5, 5, 13, 5));

						//---- lblValor ----
						lblValor.setText("Valor:");
						lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblValor, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValor ----
						txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtValor, cc.xywh(5, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPorcentajeDescuentoEspecial ----
						lblPorcentajeDescuentoEspecial.setText("Dscto. Especial [%]:");
						panel10.add(lblPorcentajeDescuentoEspecial, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeDescuentoEspecial ----
						txtPorcentajeDescuentoEspecial.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentoEspecial, cc.xy(11, 11));

						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCantidad, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCantidad, cc.xy(5, 13));

						//---- lblPorcentajeDescuentoAgencia ----
						lblPorcentajeDescuentoAgencia.setText("Dscto. Agencia [%]:");
						lblPorcentajeDescuentoAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblPorcentajeDescuentoAgencia, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPorcentajeDescuentoAgencia ----
						txtPorcentajeDescuentoAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentoAgencia, cc.xywh(11, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblOtroImpuesto ----
						lblOtroImpuesto.setText("Otro impuesto [%]:");
						lblOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblOtroImpuesto, cc.xywh(15, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuesto ----
						txtOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtOtroImpuesto, cc.xy(17, 13));

						//---- lblPorcentajeDescuentosVarios ----
						lblPorcentajeDescuentosVarios.setText("Dsctos. Varios [%]:");
						panel10.add(lblPorcentajeDescuentosVarios, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeDescuentosVarios ----
						txtPorcentajeDescuentosVarios.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentosVarios, cc.xy(11, 15));

						//---- lblRetencionRenta ----
						lblRetencionRenta.setText("Retenci\u00f3n Renta [%]:");
						panel10.add(lblRetencionRenta, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(cmbRetencionRenta, cc.xywh(5, 17, 17, 1));

						//---- lblRetencionIva ----
						lblRetencionIva.setText("Retenci\u00f3n IVA [%]:");
						panel10.add(lblRetencionIva, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(cmbRetencionIva, cc.xywh(5, 19, 17, 1));

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
						panel10.add(panel1, cc.xywh(3, 23, 19, 1));
					}
					panel11.add(panel10, cc.xy(1, 1));

					//======== panel112 ========
					{
						panel112.setBorder(new TitledBorder(null, "Totales", TitledBorder.LEADING, TitledBorder.TOP));
						panel112.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(0), FormSpec.DEFAULT_GROW),
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
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						((FormLayout)panel112.getLayout()).setColumnGroups(new int[][] {{5, 11}});

						//---- lblValorFinal ----
						lblValorFinal.setText("Valor:");
						panel112.add(lblValorFinal, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValorFinal ----
						txtValorFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtValorFinal, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtICEFinal, cc.xywh(11, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDescuentoEspecialFinal ----
						lblDescuentoEspecialFinal.setText("Dscto. Especial:");
						panel112.add(lblDescuentoEspecialFinal, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtDescuentoEspecialFinal ----
						txtDescuentoEspecialFinal.setEditable(false);
						txtDescuentoEspecialFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtDescuentoEspecialFinal, cc.xy(5, 3));

						//---- lblRetencionFinal ----
						lblRetencionFinal.setText("Retenci\u00f3n:");
						panel112.add(lblRetencionFinal, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtRetencionFinal ----
						txtRetencionFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtRetencionFinal, cc.xy(11, 3));

						//---- lblDescuentoAgenciaFinal ----
						lblDescuentoAgenciaFinal.setText("Dscto. Agencia:");
						panel112.add(lblDescuentoAgenciaFinal, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtDescuentoAgenciaFinal ----
						txtDescuentoAgenciaFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtDescuentoAgenciaFinal, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtOtroImpuestoFinal, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDescuentosVariosFinal ----
						lblDescuentosVariosFinal.setText("Dsctos. Varios:");
						panel112.add(lblDescuentosVariosFinal, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtDescuentosVariosFinal ----
						txtDescuentosVariosFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtDescuentosVariosFinal, cc.xy(5, 7));

						//---- lblTotalFinal ----
						lblTotalFinal.setText("TOTAL:");
						lblTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(lblTotalFinal, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtTotalFinal ----
						txtTotalFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(txtTotalFinal, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtIVAFinal, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel112, cc.xy(1, 3));
				}
				spDetalle.setViewportView(panel11);
			}
			jtpCompras.addTab("Detalle", spDetalle);


			//======== spRetenciones ========
			{

				//======== panelRetenciones ========
				{
					panelRetenciones.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(25)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(25)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(40)),
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
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(120)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));

					//---- lblNoSerieSecuencial ----
					lblNoSerieSecuencial.setText("No. de Serie y Secuencial:");
					panelRetenciones.add(lblNoSerieSecuencial, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelRetenciones.add(txtEstablecimiento, cc.xy(5, 3));
					panelRetenciones.add(txtPuntoEmision, cc.xy(7, 3));
					panelRetenciones.add(txtSecuencial, cc.xy(9, 3));

					//---- lblNoAutorizacion ----
					lblNoAutorizacion.setText("No. de Autorizaci\u00f3n:");
					panelRetenciones.add(lblNoAutorizacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelRetenciones.add(txtAutorizacionRetencion, cc.xywh(5, 5, 3, 1));

					//---- lblFechaEmisionRetencion ----
					lblFechaEmisionRetencion.setText("Fecha de Emisi\u00f3n:");
					panelRetenciones.add(lblFechaEmisionRetencion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelRetenciones.add(cmbFechaEmision, cc.xywh(5, 7, 5, 1));

					//======== panel12 ========
					{
						panel12.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));

						//---- btnActualizarRetencion ----
						btnActualizarRetencion.setText("U");
						panel12.add(btnActualizarRetencion, cc.xy(1, 1));
					}
					panelRetenciones.add(panel12, cc.xy(3, 11));

					//======== spTblRetenciones ========
					{

						//---- tblRetenciones ----
						tblRetenciones.setModel(new DefaultTableModel(
							new Object[][] {
								{"", null, null, null, null, null, null, null},
							},
							new String[] {
								"Ejercicio", "Preimpreso", "Autorizaci\u00f3n", "Base Imponible", "Impuesto", "% Retenci\u00f3n", "Valor Retenido", "Fecha de Emisi\u00f3n"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false, false, false
							};
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblRetenciones.setPreferredScrollableViewportSize(new Dimension(450, 120));
						spTblRetenciones.setViewportView(tblRetenciones);
					}
					panelRetenciones.add(spTblRetenciones, cc.xywh(3, 13, 9, 5));
				}
				spRetenciones.setViewportView(panelRetenciones);
			}
			jtpCompras.addTab("Retenciones", spRetenciones);

		}
		add(jtpCompras, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpCompras;
	private JScrollPane spCabecera;
	private JPanel panel32;
	private JComponent goodiesFormsSeparator1;
	private JLabel lblCodigo;
	private JLabel lblFechaEmision;
	private DateComboBox cmbFecha;
	private JTextField txtCodigo;
	private JLabel lblOficina;
	private JTextField txtOficina;
	private JLabel lblFechaVencimiento;
	private JLabel lblProveedor;
	private DateComboBox cmbFechaVencimiento;
	private JButton btnBuscarProveedor;
	private JTextField txtProveedor;
	private JLabel lblFechaCaducidad;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblMoneda;
	private DateComboBox cmbFechaCaducidad;
	private JLabel lblOrdenCompra;
	private JTextField txtOrden;
	private JButton btnBuscarOrden;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JComboBox cmbMoneda;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblTipoCompra;
	private JComboBox cmbTipoCompra;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JComponent goodiesFormsSeparator3;
	private JLabel lblTipoSustentoTributario;
	private JComboBox cmbTipoSustentoTributario;
	private JTextField txtPreimpreso;
	private JLabel lblPreimpreso;
	private JTextField txtAutorizacion;
	private JLabel lblAutorizacion;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JScrollPane spDetalle;
	private JPanel panel11;
	private JPanel panel10;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JScrollPane scPlantilla;
	private JTable tblCompraDetalle;
	private JLabel lblCodigoProducto;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblDescripcion;
	private JScrollPane spDescripcion;
	private JTextArea txtDescripcion;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblPorcentajeDescuentoEspecial;
	private JTextField txtPorcentajeDescuentoEspecial;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JLabel lblPorcentajeDescuentoAgencia;
	private JTextField txtPorcentajeDescuentoAgencia;
	private JLabel lblOtroImpuesto;
	private JTextField txtOtroImpuesto;
	private JLabel lblPorcentajeDescuentosVarios;
	private JTextField txtPorcentajeDescuentosVarios;
	private JLabel lblRetencionRenta;
	private JComboBox cmbRetencionRenta;
	private JLabel lblRetencionIva;
	private JComboBox cmbRetencionIva;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JPanel panel112;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JLabel lblICEFinal;
	private JTextField txtICEFinal;
	private JLabel lblDescuentoEspecialFinal;
	private JTextField txtDescuentoEspecialFinal;
	private JLabel lblRetencionFinal;
	private JTextField txtRetencionFinal;
	private JLabel lblDescuentoAgenciaFinal;
	private JTextField txtDescuentoAgenciaFinal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblDescuentosVariosFinal;
	private JTextField txtDescuentosVariosFinal;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	private JLabel lblIVAFinal;
	private JTextField txtIVAFinal;
	private JScrollPane spRetenciones;
	private JPanel panelRetenciones;
	private JLabel lblNoSerieSecuencial;
	private JTextField txtEstablecimiento;
	private JTextField txtPuntoEmision;
	private JTextField txtSecuencial;
	private JLabel lblNoAutorizacion;
	private JTextField txtAutorizacionRetencion;
	private JLabel lblFechaEmisionRetencion;
	private DateComboBox cmbFechaEmision;
	private JPanel panel12;
	private JButton btnActualizarRetencion;
	private JScrollPane spTblRetenciones;
	private JTable tblRetenciones;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtPorcentajeDescuentoEspecial() {
		return txtPorcentajeDescuentoEspecial;
	}

	public JTextField getTxtPorcentajeDescuentoAgencia() {
		return txtPorcentajeDescuentoAgencia;
	}

	public JTextField getTxtPorcentajeDescuentosVarios() {
		return txtPorcentajeDescuentosVarios;
	}

	public JTextField getTxtDescuentoEspecialFinal() {
		return txtDescuentoEspecialFinal;
	}

	public JTextField getTxtDescuentosVariosFinal() {
		return txtDescuentosVariosFinal;
	}

	public JTextField getTxtDescuentoAgenciaFinal() {
		return txtDescuentoAgenciaFinal;
	}

	public JideTabbedPane getJtpCompras() {
		return jtpCompras;
	}

	public JScrollPane getSpCabecera() {
		return spCabecera;
	}

	public JPanel getPanel32() {
		return panel32;
	}

	public JComponent getGoodiesFormsSeparator1() {
		return goodiesFormsSeparator1;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public JLabel getLblFechaVencimiento() {
		return lblFechaVencimiento;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public DateComboBox getCmbFechaVencimiento() {
		return cmbFechaVencimiento;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JLabel getLblFechaCaducidad() {
		return lblFechaCaducidad;
	}

	public JLabel getLblTipoDocumento() {
		return lblTipoDocumento;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public JLabel getLblMoneda() {
		return lblMoneda;
	}

	public DateComboBox getCmbFechaCaducidad() {
		return cmbFechaCaducidad;
	}

	public JLabel getLblOrdenCompra() {
		return lblOrdenCompra;
	}
	
	public JTextField getTxtOrden() {
		return txtOrden;
	}

	public JButton getBtnBuscarOrden() {
		return btnBuscarOrden;
	}

	public JLabel getLblReferencia() {
		return lblReferencia;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public JComboBox getCmbMoneda() {
		return cmbMoneda;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JLabel getLblTipoCompra() {
		return lblTipoCompra;
	}

	public JComboBox getCmbTipoCompra() {
		return cmbTipoCompra;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JComponent getGoodiesFormsSeparator3() {
		return goodiesFormsSeparator3;
	}

	public JLabel getLblTipoSustentoTributario() {
		return lblTipoSustentoTributario;
	}

	public JComboBox getCmbTipoSustentoTributario() {
		return cmbTipoSustentoTributario;
	}

	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}

	public JLabel getLblPreimpreso() {
		return lblPreimpreso;
	}

	public JTextField getTxtAutorizacion() {
		return txtAutorizacion;
	}

	public JLabel getLblAutorizacion() {
		return lblAutorizacion;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JScrollPane getSpDetalle() {
		return spDetalle;
	}

	public JPanel getPanel11() {
		return panel11;
	}

	public JPanel getPanel10() {
		return panel10;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public JScrollPane getScPlantilla() {
		return scPlantilla;
	}

	public JTable getTblCompraDetalle() {
		return tblCompraDetalle;
	}

	public JLabel getLblCodigoProducto() {
		return lblCodigoProducto;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JScrollPane getSpDescripcion() {
		return spDescripcion;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public JLabel getLblOtroImpuesto() {
		return lblOtroImpuesto;
	}

	public JTextField getTxtOtroImpuesto() {
		return txtOtroImpuesto;
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

	public JPanel getPanel112() {
		return panel112;
	}

	public JLabel getLblValorFinal() {
		return lblValorFinal;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public JLabel getLblIVAFinal() {
		return lblIVAFinal;
	}

	public JTextField getTxtIVAFinal() {
		return txtIVAFinal;
	}

	public JLabel getLblRetencionFinal() {
		return lblRetencionFinal;
	}

	public JTextField getTxtRetencionFinal() {
		return txtRetencionFinal;
	}

	public JLabel getLblICEFinal() {
		return lblICEFinal;
	}

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public JLabel getLblOtroImpuestoFinal() {
		return lblOtroImpuestoFinal;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public JLabel getLblTotalFinal() {
		return lblTotalFinal;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public JScrollPane getSpRetenciones() {
		return spRetenciones;
	}

	public JPanel getPanelRetenciones() {
		return panelRetenciones;
	}

	public JLabel getLblNoSerieSecuencial() {
		return lblNoSerieSecuencial;
	}

	public JTextField getTxtEstablecimiento() {
		return txtEstablecimiento;
	}

	public JTextField getTxtPuntoEmision() {
		return txtPuntoEmision;
	}

	public JTextField getTxtSecuencial() {
		return txtSecuencial;
	}

	public JLabel getLblNoAutorizacion() {
		return lblNoAutorizacion;
	}

	public JTextField getTxtAutorizacionRetencion() {
		return txtAutorizacionRetencion;
	}

	public JLabel getLblFechaEmisionRetencion() {
		return lblFechaEmisionRetencion;
	}

	public DateComboBox getCmbFechaEmision() {
		return cmbFechaEmision;
	}

	public JPanel getPanel12() {
		return panel12;
	}

	public JButton getBtnActualizarRetencion() {
		return btnActualizarRetencion;
	}

	public JScrollPane getSpTblRetenciones() {
		return spTblRetenciones;
	}

	public JTable getTblRetenciones() {
		return tblRetenciones;
	}

	public JComboBox getCmbRetencionRenta() {
		return cmbRetencionRenta;
	}

	public JComboBox getCmbRetencionIva() {
		return cmbRetencionIva;
	}
}
