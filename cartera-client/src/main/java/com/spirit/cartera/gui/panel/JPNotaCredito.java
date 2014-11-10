package com.spirit.cartera.gui.panel;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
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

public abstract class JPNotaCredito extends SpiritModelImpl {
	public JPNotaCredito() {
		initComponents();
		setName("Nota de Crédito");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpNotaCredito = new JideTabbedPane();
		spDetalle = new JScrollPane();
		panel11 = new JPanel();
		panel10 = new JPanel();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		scPlantilla = new JScrollPane();
		tblNotaCreditoDetalle = new JTable();
		lblCodigoProducto = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		lblDescripcion = new JLabel();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		spDescripcion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblTipoNota = new JLabel();
		cmbTipoNotaDetalle = new JComboBox();
		lblObservacionDetalle = new JLabel();
		lblValor = new JLabel();
		txtValor = new JTextField();
		spTxtObservacionDetalle = new JScrollPane();
		txtObservacionDetalle = new JTextArea();
		lblTipoReferencia = new JLabel();
		cmbTipoReferencia = new JComboBox();
		lblOtroImpuesto = new JLabel();
		txtOtroImpuesto = new JTextField();
		lblEscojaReferencia = new JLabel();
		txtEscojaReferencia = new JTextField();
		btnEscojaReferencia = new JButton();
		btnLimpiarEscojaReferencia = new JButton();
		lblEscojaOrden = new JLabel();
		txtEscojaOrden = new JTextField();
		btnEscojaOrden = new JButton();
		btnLimpiarEscojaOrden = new JButton();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		panel112 = new JPanel();
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		lblIVAFinal = new JLabel();
		txtIVAFinal = new JTextField();
		lblICEFinal = new JLabel();
		txtICEFinal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		spCabecera = new JScrollPane();
		panel32 = new JPanel();
		goodiesFormsSeparator1 = compFactory.createSeparator("Datos de la Nota Cr\u00e9dito");
		lblCodigo = new JLabel();
		lblFechaEmision = new JLabel();
		cmbFechaEmision = new DateComboBox();
		txtCodigo = new JTextField();
		lblTipoCartera = new JLabel();
		cmbTipoCartera = new JComboBox();
		lblFechaVencimiento = new JLabel();
		cmbFechaVencimiento = new DateComboBox();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		lblFechaCaducidad = new JLabel();
		cmbFechaCaducidad = new DateComboBox();
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		lblMoneda = new JLabel();
		cmbMoneda = new JComboBox();
		lblOperadorNegocio = new JLabel();
		btnBuscarOperadorNegocio = new JButton();
		txtOperadorNegocio = new JTextField();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		btnReferencia = new JButton();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		goodiesFormsSeparator3 = compFactory.createSeparator("Datos Generales");
		txtPreimpreso = new JTextField();
		lblPreimpreso = new JLabel();
		btnVerificarPreimpreso = new JButton();
		txtAutorizacion = new JTextField();
		lblAutorizacion = new JLabel();
		txtUsuario = new JTextField();
		lblUsuario = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("Compra");
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpNotaCredito ========
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
							new ColumnSpec(Sizes.dluX(120)),
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
					panel32.add(cmbFechaEmision, cc.xy(17, 5));

					//---- txtCodigo ----
					txtCodigo.setHorizontalAlignment(SwingConstants.LEADING);
					txtCodigo.setEditable(false);
					panel32.add(txtCodigo, cc.xy(5, 5));

					//---- lblTipoCartera ----
					lblTipoCartera.setText("Tipo cartera:");
					panel32.add(lblTipoCartera, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- cmbTipoCartera ----
					cmbTipoCartera.setModel(new DefaultComboBoxModel(new String[] {
						"CLIENTE",
						"PROVEEDOR"
					}));
					panel32.add(cmbTipoCartera, cc.xy(5, 7));

					//---- lblFechaVencimiento ----
					lblFechaVencimiento.setText("Fecha vencimiento:");
					lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblFechaVencimiento, cc.xy(15, 7));
					panel32.add(cmbFechaVencimiento, cc.xy(17, 7));

					//---- lblTipoDocumento ----
					lblTipoDocumento.setText("Tipo documento:");
					panel32.add(lblTipoDocumento, cc.xy(3, 9));
					panel32.add(cmbTipoDocumento, cc.xywh(5, 9, 4, 1));

					//---- lblFechaCaducidad ----
					lblFechaCaducidad.setText("Fecha caducidad:");
					panel32.add(lblFechaCaducidad, cc.xywh(15, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panel32.add(cmbFechaCaducidad, cc.xy(17, 9));

					//---- lblOficina ----
					lblOficina.setText("Oficina:");
					lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblOficina, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtOficina ----
					txtOficina.setEditable(false);
					txtOficina.setHorizontalAlignment(SwingConstants.LEADING);
					panel32.add(txtOficina, cc.xywh(5, 11, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblMoneda ----
					lblMoneda.setText("Moneda:");
					lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblMoneda, cc.xywh(15, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(cmbMoneda, cc.xy(17, 11));

					//---- lblOperadorNegocio ----
					lblOperadorNegocio.setText("Operador Negocio:");
					lblOperadorNegocio.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblOperadorNegocio, cc.xy(3, 13));
					panel32.add(btnBuscarOperadorNegocio, cc.xywh(11, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- txtOperadorNegocio ----
					txtOperadorNegocio.setFocusable(false);
					panel32.add(txtOperadorNegocio, cc.xywh(5, 13, 5, 1));

					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panel32.add(lblReferencia, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- txtReferencia ----
					txtReferencia.setEditable(false);
					panel32.add(txtReferencia, cc.xywh(5, 15, 5, 1));
					panel32.add(btnReferencia, cc.xywh(11, 15, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

					//---- lblEstado ----
					lblEstado.setText("Estado:");
					lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblEstado, cc.xy(3, 17));
					panel32.add(cmbEstado, cc.xy(5, 17));

					//---- lblObservacion ----
					lblObservacion.setText("Observaci\u00f3n:");
					lblObservacion.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblObservacion, cc.xy(3, 19));
					panel32.add(txtObservacion, cc.xywh(5, 19, 13, 1));
					panel32.add(goodiesFormsSeparator3, cc.xywh(3, 23, 15, 1));
					panel32.add(txtPreimpreso, cc.xy(5, 25));

					//---- lblPreimpreso ----
					lblPreimpreso.setText("Preimpreso:");
					lblPreimpreso.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblPreimpreso, cc.xy(3, 25));

					//---- btnVerificarPreimpreso ----
					btnVerificarPreimpreso.setText("Verificar");
					panel32.add(btnVerificarPreimpreso, cc.xy(7, 25));
					panel32.add(txtAutorizacion, cc.xywh(5, 27, 3, 1));

					//---- lblAutorizacion ----
					lblAutorizacion.setText("Autorizaci\u00f3n:");
					lblAutorizacion.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblAutorizacion, cc.xy(3, 27));

					//---- txtUsuario ----
					txtUsuario.setEditable(false);
					panel32.add(txtUsuario, cc.xy(5, 29));

					//---- lblUsuario ----
					lblUsuario.setText("Usuario:");
					lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblUsuario, cc.xy(3, 29));
				}
				spCabecera.setViewportView(panel32);
			}
			jtpNotaCredito.addTab("Cabecera", spCabecera);


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
								new ColumnSpec(Sizes.dluX(80)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(10)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(10)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(30)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(90)),
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
								new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
							}));

						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblDocumento, cc.xy(3, 1));
						panel10.add(cmbDocumento, cc.xywh(5, 1, 11, 1));

						//======== scPlantilla ========
						{

							//---- tblNotaCreditoDetalle ----
							tblNotaCreditoDetalle.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
									"Producto", "Cantidad", "Valor", "IVA", "ICE", "Otr. Imp.", "Total"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false, false, true
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblNotaCreditoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblNotaCreditoDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							scPlantilla.setViewportView(tblNotaCreditoDetalle);
						}
						panel10.add(scPlantilla, cc.xywh(3, 21, 25, 1));

						//---- lblCodigoProducto ----
						lblCodigoProducto.setText("Producto:");
						lblCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCodigoProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtProducto, cc.xywh(5, 3, 11, 1));
						panel10.add(btnBuscarProducto, cc.xywh(17, 3, 3, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCantidad, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCantidad, cc.xy(5, 11));

						//======== spDescripcion ========
						{

							//---- txtDescripcion ----
							txtDescripcion.setLineWrap(true);
							spDescripcion.setViewportView(txtDescripcion);
						}
						panel10.add(spDescripcion, cc.xywh(5, 5, 11, 5));

						//---- lblTipoNota ----
						lblTipoNota.setText("Tipo de Nota:");
						panel10.add(lblTipoNota, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- cmbTipoNotaDetalle ----
						cmbTipoNotaDetalle.setModel(new DefaultComboBoxModel(new String[] {
							"ERROR",
							"ANULACION",
							"GANANCIA",
							"OTRO"
						}));
						panel10.add(cmbTipoNotaDetalle, cc.xy(11, 11));

						//---- lblObservacionDetalle ----
						lblObservacionDetalle.setText("Observaci\u00f3n:");
						panel10.add(lblObservacionDetalle, cc.xywh(19, 11, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- lblValor ----
						lblValor.setText("Valor:");
						lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblValor, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValor ----
						txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtValor, cc.xywh(5, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//======== spTxtObservacionDetalle ========
						{
							spTxtObservacionDetalle.setMaximumSize(new Dimension(1000, 1000));
							spTxtObservacionDetalle.setViewportView(txtObservacionDetalle);
						}
						panel10.add(spTxtObservacionDetalle, cc.xywh(19, 12, 7, 6));

						//---- lblTipoReferencia ----
						lblTipoReferencia.setText("Tipo de referencia:");
						panel10.add(lblTipoReferencia, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(cmbTipoReferencia, cc.xy(11, 13));

						//---- lblOtroImpuesto ----
						lblOtroImpuesto.setText("Otro impuesto [%]:");
						lblOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblOtroImpuesto, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuesto ----
						txtOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtOtroImpuesto, cc.xy(5, 15));

						//---- lblEscojaReferencia ----
						lblEscojaReferencia.setText("Escoja Referencia:");
						panel10.add(lblEscojaReferencia, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(txtEscojaReferencia, cc.xy(11, 15));
						panel10.add(btnEscojaReferencia, cc.xywh(13, 15, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						panel10.add(btnLimpiarEscojaReferencia, cc.xywh(15, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblEscojaOrden ----
						lblEscojaOrden.setText("Escoja Orden:");
						panel10.add(lblEscojaOrden, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel10.add(txtEscojaOrden, cc.xy(11, 17));
						panel10.add(btnEscojaOrden, cc.xywh(13, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel10.add(btnLimpiarEscojaOrden, cc.xywh(15, 17, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//======== panel1 ========
						{
							panel1.setLayout(new FormLayout(
								new ColumnSpec[] {
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
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
						panel10.add(panel1, cc.xywh(3, 19, 25, 1));
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
						panel112.add(lblValorFinal, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValorFinal ----
						txtValorFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtValorFinal, cc.xywh(11, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtIVAFinal, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtICEFinal, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtOtroImpuestoFinal, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblTotalFinal ----
						lblTotalFinal.setText("TOTAL:");
						lblTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(lblTotalFinal, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtTotalFinal ----
						txtTotalFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(txtTotalFinal, cc.xywh(11, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel112, cc.xy(1, 3));
				}
				spDetalle.setViewportView(panel11);
			}
			jtpNotaCredito.addTab("Detalle", spDetalle);

		}
		add(jtpNotaCredito, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpNotaCredito;
	private JScrollPane spDetalle;
	private JPanel panel11;
	private JPanel panel10;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JScrollPane scPlantilla;
	private JTable tblNotaCreditoDetalle;
	private JLabel lblCodigoProducto;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblDescripcion;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JScrollPane spDescripcion;
	private JTextArea txtDescripcion;
	private JLabel lblTipoNota;
	private JComboBox cmbTipoNotaDetalle;
	private JLabel lblObservacionDetalle;
	private JLabel lblValor;
	private JTextField txtValor;
	private JScrollPane spTxtObservacionDetalle;
	private JTextArea txtObservacionDetalle;
	private JLabel lblTipoReferencia;
	private JComboBox cmbTipoReferencia;
	private JLabel lblOtroImpuesto;
	private JTextField txtOtroImpuesto;
	private JLabel lblEscojaReferencia;
	private JTextField txtEscojaReferencia;
	private JButton btnEscojaReferencia;
	private JButton btnLimpiarEscojaReferencia;
	private JLabel lblEscojaOrden;
	private JTextField txtEscojaOrden;
	private JButton btnEscojaOrden;
	private JButton btnLimpiarEscojaOrden;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JPanel panel112;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JLabel lblIVAFinal;
	private JTextField txtIVAFinal;
	private JLabel lblICEFinal;
	private JTextField txtICEFinal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	private JScrollPane spCabecera;
	private JPanel panel32;
	private JComponent goodiesFormsSeparator1;
	private JLabel lblCodigo;
	private JLabel lblFechaEmision;
	private DateComboBox cmbFechaEmision;
	private JTextField txtCodigo;
	private JLabel lblTipoCartera;
	private JComboBox cmbTipoCartera;
	private JLabel lblFechaVencimiento;
	private DateComboBox cmbFechaVencimiento;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JLabel lblFechaCaducidad;
	private DateComboBox cmbFechaCaducidad;
	private JLabel lblOficina;
	private JTextField txtOficina;
	private JLabel lblMoneda;
	private JComboBox cmbMoneda;
	private JLabel lblOperadorNegocio;
	private JButton btnBuscarOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JButton btnReferencia;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JComponent goodiesFormsSeparator3;
	private JTextField txtPreimpreso;
	private JLabel lblPreimpreso;
	private JButton btnVerificarPreimpreso;
	private JTextField txtAutorizacion;
	private JLabel lblAutorizacion;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtEscojaOrden() {
		return txtEscojaOrden;
	}

	public JButton getBtnEscojaOrden() {
		return btnEscojaOrden;
	}

	public JButton getBtnLimpiarEscojaOrden() {
		return btnLimpiarEscojaOrden;
	}

	public JLabel getLblICEFinal() {
		return lblICEFinal;
	}

	public JComboBox getCmbTipoNotaDetalle() {
		return cmbTipoNotaDetalle;
	}

	public JTextArea getTxtObservacionDetalle() {
		return txtObservacionDetalle;
	}

	public JButton getBtnLimpiarEscojaReferencia() {
		return btnLimpiarEscojaReferencia;
	}

	public JLabel getLblTipoReferencia() {
		return lblTipoReferencia;
	}

	public JComboBox getCmbTipoReferencia() {
		return cmbTipoReferencia;
	}

	public JLabel getLblEscojaReferencia() {
		return lblEscojaReferencia;
	}

	public JTextField getTxtEscojaReferencia() {
		return txtEscojaReferencia;
	}

	public JButton getBtnEscojaReferencia() {
		return btnEscojaReferencia;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public DateComboBox getCmbFechaEmision() {
		return cmbFechaEmision;
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

	public JComponent getGoodiesFormsSeparator1() {
		return goodiesFormsSeparator1;
	}

	public JComponent getGoodiesFormsSeparator3() {
		return goodiesFormsSeparator3;
	}

	public JideTabbedPane getJtpNotaCredito() {
		return jtpNotaCredito;
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

	public JTable getTblNotaCreditoDetalle() {
		return tblNotaCreditoDetalle;
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

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
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

	public JButton getBtnVerificarPreimpreso() {
		return btnVerificarPreimpreso;
	}

	public void setBtnVerificarPreimpreso(JButton btnVerificarPreimpreso) {
		this.btnVerificarPreimpreso = btnVerificarPreimpreso;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}
	
	public JComboBox getCmbTipoCartera() {
		return cmbTipoCartera;
	}

	public void setCmbTipoCartera(JComboBox cmbTipoCartera) {
		this.cmbTipoCartera = cmbTipoCartera;
	}

	public JButton getBtnReferencia() {
		return btnReferencia;
	}
}
