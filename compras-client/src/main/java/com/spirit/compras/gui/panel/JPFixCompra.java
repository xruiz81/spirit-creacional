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

public abstract class JPFixCompra extends SpiritModelImpl {
	public JPFixCompra() {
		initComponents();
		setName("Fix Compra");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
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
		txtProveedor = new JTextField();
		lblFechaCaducidad = new JLabel();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblMoneda = new JLabel();
		cmbFechaCaducidad = new DateComboBox();
		lblOrdenCompra = new JLabel();
		txtOrdenCompra = new JTextField();
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
		lblDescripcion = new JLabel();
		spDescripcion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		lblOtroImpuesto = new JLabel();
		lblDescuento = new JLabel();
		txtDescuento = new JTextField();
		txtOtroImpuesto = new JTextField();
		lblRetencionRenta = new JLabel();
		cmbRetencionRenta = new JComboBox();
		lblRetencionIva = new JLabel();
		cmbRetencionIva = new JComboBox();
		panel1 = new JPanel();
		btnActualizarDetalle = new JButton();
		panel112 = new JPanel();
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		txtDescuentoFinal = new JTextField();
		lblDescuentoFinal = new JLabel();
		lblIVAFinal = new JLabel();
		txtIVAFinal = new JTextField();
		lblRetencionFinal = new JLabel();
		txtRetencionFinal = new JTextField();
		lblICEFinal = new JLabel();
		txtICEFinal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Compra");
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
							FormFactory.DEFAULT_COLSPEC,
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
					txtCodigo.setHorizontalAlignment(JTextField.LEADING);
					txtCodigo.setEditable(false);
					panel32.add(txtCodigo, cc.xy(5, 5));
					
					//---- lblOficina ----
					lblOficina.setText("Oficina:");
					lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblOficina, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtOficina ----
					txtOficina.setEditable(false);
					txtOficina.setHorizontalAlignment(JTextField.LEADING);
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
					lblOrdenCompra.setText("Orden de Compra:");
					panel32.add(lblOrdenCompra, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(txtOrdenCompra, cc.xywh(5, 11, 5, 1));
					
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
						panel10.setBorder(new TitledBorder("Detalle"));
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
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(120)),
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
								new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
							}));
						
						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblDocumento, cc.xywh(3, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel10.add(cmbDocumento, cc.xywh(5, 1, 9, 1));
						
						//======== scPlantilla ========
						{
							
							//---- tblCompraDetalle ----
							tblCompraDetalle.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Producto", "Cantidad", "Valor", "Descuento", "IVA", "Retenci\u00f3n", "ICE", "Otr. Imp.", "Total"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false, false, false, false, true
								};
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblCompraDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblCompraDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							scPlantilla.setViewportView(tblCompraDetalle);
						}
						panel10.add(scPlantilla, cc.xywh(3, 23, 17, 1));
						
						//---- lblCodigoProducto ----
						lblCodigoProducto.setText("Producto:");
						lblCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCodigoProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtProducto, cc.xywh(5, 3, 9, 1));
						
						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//======== spDescripcion ========
						{
							
							//---- txtDescripcion ----
							txtDescripcion.setLineWrap(true);
							spDescripcion.setViewportView(txtDescripcion);
						}
						panel10.add(spDescripcion, cc.xywh(5, 5, 9, 5));
						
						//---- lblValor ----
						lblValor.setText("Valor:");
						lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblValor, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtValor ----
						txtValor.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtValor, cc.xywh(5, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCantidad, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtCantidad, cc.xy(11, 11));
						
						//---- lblOtroImpuesto ----
						lblOtroImpuesto.setText("Otro impuesto [%]:");
						lblOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblOtroImpuesto, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- lblDescuento ----
						lblDescuento.setText("Descuento [%]:");
						lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblDescuento, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtDescuento ----
						txtDescuento.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtDescuento, cc.xywh(11, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- txtOtroImpuesto ----
						txtOtroImpuesto.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtOtroImpuesto, cc.xy(5, 13));
						
						//---- lblRetencionRenta ----
						lblRetencionRenta.setText("Retenci\u00f3n Renta [%]:");
						panel10.add(lblRetencionRenta, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- cmbRetencionRenta ----
						cmbRetencionRenta.setAutoscrolls(false);
						cmbRetencionRenta.setMaximumRowCount(8);
						panel10.add(cmbRetencionRenta, cc.xywh(5, 15, 13, 1));
						
						//---- lblRetencionIva ----
						lblRetencionIva.setText("Retenci\u00f3n IVA [%]:");
						panel10.add(lblRetencionIva, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(cmbRetencionIva, cc.xywh(5, 17, 13, 1));
						
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
							
							//---- btnActualizarDetalle ----
							btnActualizarDetalle.setText("U");
							panel1.add(btnActualizarDetalle, cc.xy(1, 1));
						}
						panel10.add(panel1, cc.xywh(3, 21, 15, 1));
					}
					panel11.add(panel10, cc.xy(1, 1));
					
					//======== panel112 ========
					{
						panel112.setBorder(new TitledBorder("Totales"));
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
								FormFactory.DEFAULT_ROWSPEC
							}));
						((FormLayout)panel112.getLayout()).setColumnGroups(new int[][] {{5, 11}});
						
						//---- lblValorFinal ----
						lblValorFinal.setText("Valor:");
						panel112.add(lblValorFinal, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtValorFinal ----
						txtValorFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtValorFinal, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- txtDescuentoFinal ----
						txtDescuentoFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtDescuentoFinal, cc.xywh(11, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblDescuentoFinal ----
						lblDescuentoFinal.setText("Descuento:");
						panel112.add(lblDescuentoFinal, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtIVAFinal, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblRetencionFinal ----
						lblRetencionFinal.setText("Retenci\u00f3n:");
						panel112.add(lblRetencionFinal, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- txtRetencionFinal ----
						txtRetencionFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtRetencionFinal, cc.xy(11, 3));
						
						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtICEFinal, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtOtroImpuestoFinal, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblTotalFinal ----
						lblTotalFinal.setText("TOTAL:");
						lblTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(lblTotalFinal, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtTotalFinal ----
						txtTotalFinal.setHorizontalAlignment(JTextField.RIGHT);
						txtTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(txtTotalFinal, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel112, cc.xy(1, 3));
				}
				spDetalle.setViewportView(panel11);
			}
			jtpCompras.addTab("Detalle", spDetalle);
			
		}
		add(jtpCompras, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnActualizarDetalle.setText("");
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
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
	private JTextField txtProveedor;
	private JLabel lblFechaCaducidad;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblMoneda;
	private DateComboBox cmbFechaCaducidad;
	private JLabel lblOrdenCompra;
	private JTextField txtOrdenCompra;
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
	private JLabel lblDescripcion;
	private JScrollPane spDescripcion;
	private JTextArea txtDescripcion;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JLabel lblOtroImpuesto;
	private JLabel lblDescuento;
	private JTextField txtDescuento;
	private JTextField txtOtroImpuesto;
	private JLabel lblRetencionRenta;
	private JComboBox cmbRetencionRenta;
	private JLabel lblRetencionIva;
	private JComboBox cmbRetencionIva;
	private JPanel panel1;
	private JButton btnActualizarDetalle;
	private JPanel panel112;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JTextField txtDescuentoFinal;
	private JLabel lblDescuentoFinal;
	private JLabel lblIVAFinal;
	private JTextField txtIVAFinal;
	private JLabel lblRetencionFinal;
	private JTextField txtRetencionFinal;
	private JLabel lblICEFinal;
	private JTextField txtICEFinal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public DateComboBox getCmbFechaCaducidad() {
		return cmbFechaCaducidad;
	}

	public DateComboBox getCmbFechaVencimiento() {
		return cmbFechaVencimiento;
	}

	public JComboBox getCmbMoneda() {
		return cmbMoneda;
	}

	public JComboBox getCmbTipoCompra() {
		return cmbTipoCompra;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public JComboBox getCmbTipoSustentoTributario() {
		return cmbTipoSustentoTributario;
	}

	public JComponent getGoodiesFormsSeparator1() {
		return goodiesFormsSeparator1;
	}

	public JComponent getGoodiesFormsSeparator3() {
		return goodiesFormsSeparator3;
	}

	public JideTabbedPane getJtpCompras() {
		return jtpCompras;
	}

	public JLabel getLblAutorizacion() {
		return lblAutorizacion;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JLabel getLblCodigoProducto() {
		return lblCodigoProducto;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public JLabel getLblDescuento() {
		return lblDescuento;
	}

	public JLabel getLblDescuentoFinal() {
		return lblDescuentoFinal;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JLabel getLblFechaCaducidad() {
		return lblFechaCaducidad;
	}

	public JLabel getLblFechaEmision() {
		return lblFechaEmision;
	}

	public JLabel getLblFechaVencimiento() {
		return lblFechaVencimiento;
	}

	public JLabel getLblICEFinal() {
		return lblICEFinal;
	}

	public JLabel getLblIVAFinal() {
		return lblIVAFinal;
	}

	public JLabel getLblMoneda() {
		return lblMoneda;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public JLabel getLblOrdenCompra() {
		return lblOrdenCompra;
	}

	public JLabel getLblOtroImpuesto() {
		return lblOtroImpuesto;
	}

	public JLabel getLblOtroImpuestoFinal() {
		return lblOtroImpuestoFinal;
	}

	public JLabel getLblPreimpreso() {
		return lblPreimpreso;
	}

	public JLabel getLblProveedor() {
		return lblProveedor;
	}

	public JLabel getLblReferencia() {
		return lblReferencia;
	}

	public JLabel getLblRetencionFinal() {
		return lblRetencionFinal;
	}

	public JLabel getLblTipoCompra() {
		return lblTipoCompra;
	}

	public JLabel getLblTipoDocumento() {
		return lblTipoDocumento;
	}

	public JLabel getLblTipoSustentoTributario() {
		return lblTipoSustentoTributario;
	}

	public JLabel getLblTotalFinal() {
		return lblTotalFinal;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public JLabel getLblValor() {
		return lblValor;
	}

	public JLabel getLblValorFinal() {
		return lblValorFinal;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel10() {
		return panel10;
	}

	public JPanel getPanel11() {
		return panel11;
	}

	public JPanel getPanel112() {
		return panel112;
	}

	public JPanel getPanel32() {
		return panel32;
	}

	public JScrollPane getScPlantilla() {
		return scPlantilla;
	}

	public JScrollPane getSpCabecera() {
		return spCabecera;
	}

	public JScrollPane getSpDescripcion() {
		return spDescripcion;
	}

	public JScrollPane getSpDetalle() {
		return spDetalle;
	}

	public JTable getTblCompraDetalle() {
		return tblCompraDetalle;
	}

	public JTextField getTxtAutorizacion() {
		return txtAutorizacion;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public JTextField getTxtDescuento() {
		return txtDescuento;
	}

	public JTextField getTxtDescuentoFinal() {
		return txtDescuentoFinal;
	}

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public JTextField getTxtIVAFinal() {
		return txtIVAFinal;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public JTextField getTxtOrdenCompra() {
		return txtOrdenCompra;
	}

	public JTextField getTxtOtroImpuesto() {
		return txtOtroImpuesto;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public JTextField getTxtRetencionFinal() {
		return txtRetencionFinal;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public JComboBox getCmbRetencionIva() {
		return cmbRetencionIva;
	}

	public void setCmbRetencionIva(JComboBox cmbRetencionIva) {
		this.cmbRetencionIva = cmbRetencionIva;
	}

	public JComboBox getCmbRetencionRenta() {
		return cmbRetencionRenta;
	}

	public void setCmbRetencionRenta(JComboBox cmbRetencionRenta) {
		this.cmbRetencionRenta = cmbRetencionRenta;
	}
}
