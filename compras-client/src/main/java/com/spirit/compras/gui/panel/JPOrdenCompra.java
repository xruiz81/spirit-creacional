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

public abstract class JPOrdenCompra extends SpiritModelImpl {
	public JPOrdenCompra() {
		initComponents();
		setName("Orden de Compra");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpOrdenCompra = new JideTabbedPane();
		scrollPane1 = new JScrollPane();
		panel3 = new JPanel();
		panel32 = new JPanel();
		goodiesFormsSeparator1 = compFactory.createSeparator("Datos de la Orden de Compra");
		txtCodigo = new JTextField();
		lblFecha = new JLabel();
		cmbFecha = new DateComboBox();
		lblFechaRecepcion = new JLabel();
		cmbFechaRecepcion = new DateComboBox();
		lblFechaVencimiento = new JLabel();
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		cmbFechaVencimiento = new DateComboBox();
		lblProveedor = new JLabel();
		btnBuscarProveedor = new JButton();
		txtProveedor = new JTextField();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblCodigo = new JLabel();
		lblRevision = new JLabel();
		txtRevision = new JTextField();
		lblTipoCompra = new JLabel();
		cmbTipoCompra = new JComboBox();
		lblMoneda = new JLabel();
		cmbMoneda = new JComboBox();
		lblSolicitudCompra = new JLabel();
		txtSolicitudCompra = new JTextField();
		btnBuscarSolicitudCompra = new JButton();
		lblPresupuesto = new JLabel();
		txtPresupuesto = new JTextField();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		goodiesFormsSeparator122 = compFactory.createSeparator("Datos Generales");
		lblEmpleado = new JLabel();
		cmbEmpleado = new JComboBox();
		lblUsuario = new JLabel();
		txtUsuario = new JTextField();
		lblBodega = new JLabel();
		cmbBodega = new JComboBox();
		scrollPane2 = new JScrollPane();
		panel11 = new JPanel();
		panel112 = new JPanel();
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		lblIVAFinal = new JLabel();
		txtIVAFinal = new JTextField();
		lblDescuentoEspecialFinal = new JLabel();
		txtDescuentoEspecialFinal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblDescuentoAgenciaFinal = new JLabel();
		txtDescuentoAgenciaFinal = new JTextField();
		lblICEFinal = new JLabel();
		txtICEFinal = new JTextField();
		lblDescuentosVariosFinal = new JLabel();
		txtDescuentosVariosFinal = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		panel10 = new JPanel();
		spTblOrdenCompraDetalle = new JScrollPane();
		tblOrdenCompraDetalle = new JTable();
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
		lblPorcentajeDescuentoAgencia = new JLabel();
		lblPorcentajeNegociacionDirecta = new JLabel();
		txtPorcentajeNegociacionDirecta = new JTextField();
		lblFechaPublicacion = new JLabel();
		txtFechaPublicacion = new JTextField();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		txtPorcentajeDescuentoAgencia = new JTextField();
		lblPorcentajeOtroImpuesto = new JLabel();
		txtPorcentajeOtroImpuesto = new JTextField();
		lblPorcentajeDescuentosVarios = new JLabel();
		txtPorcentajeDescuentosVarios = new JTextField();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpOrdenCompra ========
		{

			//======== scrollPane1 ========
			{

				//======== panel3 ========
				{
					panel3.setLayout(new FormLayout(
						"default:grow",
						"default"));

					//======== panel32 ========
					{
						panel32.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(10)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
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
								new ColumnSpec(Sizes.dluX(100)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(10))
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
								new RowSpec(Sizes.dluY(12))
							}));
						panel32.add(goodiesFormsSeparator1, cc.xywh(3, 3, 17, 1));

						//---- txtCodigo ----
						txtCodigo.setHorizontalAlignment(SwingConstants.LEADING);
						txtCodigo.setEditable(false);
						panel32.add(txtCodigo, cc.xy(5, 5));

						//---- lblFecha ----
						lblFecha.setText("Fecha:");
						lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblFecha, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbFecha, cc.xy(19, 5));

						//---- lblFechaRecepcion ----
						lblFechaRecepcion.setText("Fecha de recepci\u00f3n:");
						lblFechaRecepcion.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblFechaRecepcion, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbFechaRecepcion, cc.xy(5, 7));

						//---- lblFechaVencimiento ----
						lblFechaVencimiento.setText("Fecha de vencimiento:");
						lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblFechaVencimiento, cc.xy(17, 7));

						//---- lblOficina ----
						lblOficina.setText("Oficina:");
						lblOficina.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblOficina, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOficina ----
						txtOficina.setEditable(false);
						txtOficina.setHorizontalAlignment(SwingConstants.LEADING);
						panel32.add(txtOficina, cc.xywh(5, 9, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						panel32.add(cmbFechaVencimiento, cc.xy(19, 7));

						//---- lblProveedor ----
						lblProveedor.setText("Proveedor:");
						lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblProveedor, cc.xy(3, 11));
						panel32.add(btnBuscarProveedor, cc.xywh(15, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- txtProveedor ----
						txtProveedor.setFocusable(false);
						panel32.add(txtProveedor, cc.xywh(5, 11, 9, 1));

						//---- lblEstado ----
						lblEstado.setText("Estado:");
						lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblEstado, cc.xy(3, 15));
						panel32.add(cmbEstado, cc.xy(5, 15));

						//---- lblCodigo ----
						lblCodigo.setText("C\u00f3digo:");
						panel32.add(lblCodigo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblRevision ----
						lblRevision.setText("Revisi\u00f3n:");
						panel32.add(lblRevision, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtRevision ----
						txtRevision.setEditable(false);
						txtRevision.setHorizontalAlignment(SwingConstants.CENTER);
						panel32.add(txtRevision, cc.xy(11, 5));

						//---- lblTipoCompra ----
						lblTipoCompra.setText("Tipo de compra:");
						lblTipoCompra.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblTipoCompra, cc.xy(17, 9));
						panel32.add(cmbTipoCompra, cc.xy(19, 9));

						//---- lblMoneda ----
						lblMoneda.setText("Moneda:");
						lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblMoneda, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbMoneda, cc.xy(19, 11));

						//---- lblSolicitudCompra ----
						lblSolicitudCompra.setText("Solicitud de Compra:");
						panel32.add(lblSolicitudCompra, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(txtSolicitudCompra, cc.xywh(5, 13, 9, 1));
						panel32.add(btnBuscarSolicitudCompra, cc.xywh(15, 13, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblPresupuesto ----
						lblPresupuesto.setText("Presupuesto:");
						panel32.add(lblPresupuesto, cc.xywh(17, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panel32.add(txtPresupuesto, cc.xy(19, 13));

						//---- lblObservacion ----
						lblObservacion.setText("Observaci\u00f3n:");
						lblObservacion.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblObservacion, cc.xy(3, 17));
						panel32.add(txtObservacion, cc.xywh(5, 17, 15, 1));
						panel32.add(goodiesFormsSeparator122, cc.xywh(3, 21, 17, 1));

						//---- lblEmpleado ----
						lblEmpleado.setText("Empleado:");
						lblEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblEmpleado, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbEmpleado, cc.xywh(5, 23, 9, 1));

						//---- lblUsuario ----
						lblUsuario.setText("Usuario:");
						lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
						panel32.add(lblUsuario, cc.xy(17, 23));

						//---- txtUsuario ----
						txtUsuario.setEditable(false);
						panel32.add(txtUsuario, cc.xy(19, 23));

						//---- lblBodega ----
						lblBodega.setText("Bodega:");
						panel32.add(lblBodega, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel32.add(cmbBodega, cc.xywh(5, 25, 9, 1));
					}
					panel3.add(panel32, cc.xy(1, 1));
				}
				scrollPane1.setViewportView(panel3);
			}
			jtpOrdenCompra.addTab("Cabecera", scrollPane1);


			//======== scrollPane2 ========
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

					//======== panel112 ========
					{
						panel112.setBorder(new TitledBorder(null, "Totales", TitledBorder.LEADING, TitledBorder.TOP));
						panel112.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(12)),
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
						((FormLayout)panel112.getLayout()).setColumnGroups(new int[][] {{5, 11}});

						//---- lblValorFinal ----
						lblValorFinal.setText("Valor:");
						panel112.add(lblValorFinal, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValorFinal ----
						txtValorFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtValorFinal, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtIVAFinal, cc.xywh(11, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDescuentoEspecialFinal ----
						lblDescuentoEspecialFinal.setText("Dscto. Especial:");
						panel112.add(lblDescuentoEspecialFinal, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtDescuentoEspecialFinal ----
						txtDescuentoEspecialFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						txtDescuentoEspecialFinal.setEditable(false);
						panel112.add(txtDescuentoEspecialFinal, cc.xy(5, 3));

						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtOtroImpuestoFinal, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblDescuentoAgenciaFinal ----
						lblDescuentoAgenciaFinal.setText("Dscto. Agencia:");
						panel112.add(lblDescuentoAgenciaFinal, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtDescuentoAgenciaFinal ----
						txtDescuentoAgenciaFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtDescuentoAgenciaFinal, cc.xywh(5, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(SwingConstants.RIGHT);
						panel112.add(txtICEFinal, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

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
					}
					panel11.add(panel112, cc.xy(1, 3));

					//======== panel10 ========
					{
						panel10.setBorder(new TitledBorder(null, "Detalle", TitledBorder.LEADING, TitledBorder.TOP));
						panel10.setLayout(new FormLayout(
							new ColumnSpec[] {
								new ColumnSpec(Sizes.dluX(10)),
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
								new ColumnSpec(Sizes.dluX(85)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(60)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(30), FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(10))
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(Sizes.dluY(60)),
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

						//======== spTblOrdenCompraDetalle ========
						{

							//---- tblOrdenCompraDetalle ----
							tblOrdenCompraDetalle.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null, null, null, null, null, null, null, null, null},
								},
								new String[] {
									"Producto", "Cantidad", "Valor", "Dscto. Especial", "Dscto. Agencia", "Dsctos. Varios", "IVA", "ICE", "Otr. Imp.", "Total"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									false, false, false, false, false, false, false, false, true, true
								};
								@Override
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblOrdenCompraDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblOrdenCompraDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							spTblOrdenCompraDetalle.setViewportView(tblOrdenCompraDetalle);
						}
						panel10.add(spTblOrdenCompraDetalle, cc.xywh(3, 17, 21, 1));

						//---- lblCodigoProducto ----
						lblCodigoProducto.setText("Producto:");
						lblCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCodigoProducto, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtProducto, cc.xywh(5, 1, 9, 1));
						panel10.add(btnBuscarProducto, cc.xywh(15, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//======== spDescripcion ========
						{

							//---- txtDescripcion ----
							txtDescripcion.setLineWrap(true);
							spDescripcion.setViewportView(txtDescripcion);
						}
						panel10.add(spDescripcion, cc.xywh(5, 3, 17, 5));

						//---- lblValor ----
						lblValor.setText("Valor:");
						lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblValor, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtValor ----
						txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
						txtValor.setEditable(true);
						panel10.add(txtValor, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPorcentajeDescuentoEspecial ----
						lblPorcentajeDescuentoEspecial.setText("Dscto. Especial [%]:");
						panel10.add(lblPorcentajeDescuentoEspecial, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeDescuentoEspecial ----
						txtPorcentajeDescuentoEspecial.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentoEspecial, cc.xy(11, 9));

						//---- lblPorcentajeDescuentoAgencia ----
						lblPorcentajeDescuentoAgencia.setText("Dscto. Agencia [%]:");
						panel10.add(lblPorcentajeDescuentoAgencia, cc.xywh(9, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- lblPorcentajeNegociacionDirecta ----
						lblPorcentajeNegociacionDirecta.setText("Negociaci\u00f3n Directa [%]:");
						panel10.add(lblPorcentajeNegociacionDirecta, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeNegociacionDirecta ----
						txtPorcentajeNegociacionDirecta.setEditable(false);
						txtPorcentajeNegociacionDirecta.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeNegociacionDirecta, cc.xy(15, 9));

						//---- lblFechaPublicacion ----
						lblFechaPublicacion.setText("F. Publicaci\u00f3n:");
						panel10.add(lblFechaPublicacion, cc.xy(19, 9));

						//---- txtFechaPublicacion ----
						txtFechaPublicacion.setEditable(false);
						txtFechaPublicacion.setHorizontalAlignment(SwingConstants.CENTER);
						panel10.add(txtFechaPublicacion, cc.xy(21, 9));

						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCantidad, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtCantidad, cc.xy(5, 11));

						//---- txtPorcentajeDescuentoAgencia ----
						txtPorcentajeDescuentoAgencia.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentoAgencia, cc.xywh(11, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

						//---- lblPorcentajeOtroImpuesto ----
						lblPorcentajeOtroImpuesto.setText("Otro impuesto [%]:");
						lblPorcentajeOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblPorcentajeOtroImpuesto, cc.xywh(13, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

						//---- txtPorcentajeOtroImpuesto ----
						txtPorcentajeOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeOtroImpuesto, cc.xy(15, 11));

						//---- lblPorcentajeDescuentosVarios ----
						lblPorcentajeDescuentosVarios.setText("Dsctos. Varios [%]:");
						panel10.add(lblPorcentajeDescuentosVarios, cc.xywh(9, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

						//---- txtPorcentajeDescuentosVarios ----
						txtPorcentajeDescuentosVarios.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(txtPorcentajeDescuentosVarios, cc.xy(11, 13));

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
						panel10.add(panel1, cc.xywh(3, 15, 14, 1));
					}
					panel11.add(panel10, cc.xy(1, 1));
				}
				scrollPane2.setViewportView(panel11);
			}
			jtpOrdenCompra.addTab("Detalle", scrollPane2);

		}
		add(jtpOrdenCompra, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpOrdenCompra;
	private JScrollPane scrollPane1;
	private JPanel panel3;
	private JPanel panel32;
	private JComponent goodiesFormsSeparator1;
	private JTextField txtCodigo;
	private JLabel lblFecha;
	private DateComboBox cmbFecha;
	private JLabel lblFechaRecepcion;
	private DateComboBox cmbFechaRecepcion;
	private JLabel lblFechaVencimiento;
	private JLabel lblOficina;
	private JTextField txtOficina;
	private DateComboBox cmbFechaVencimiento;
	private JLabel lblProveedor;
	private JButton btnBuscarProveedor;
	private JTextField txtProveedor;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblCodigo;
	private JLabel lblRevision;
	private JTextField txtRevision;
	private JLabel lblTipoCompra;
	private JComboBox cmbTipoCompra;
	private JLabel lblMoneda;
	private JComboBox cmbMoneda;
	private JLabel lblSolicitudCompra;
	private JTextField txtSolicitudCompra;
	private JButton btnBuscarSolicitudCompra;
	private JLabel lblPresupuesto;
	private JTextField txtPresupuesto;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JComponent goodiesFormsSeparator122;
	private JLabel lblEmpleado;
	private JComboBox cmbEmpleado;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblBodega;
	private JComboBox cmbBodega;
	private JScrollPane scrollPane2;
	private JPanel panel11;
	private JPanel panel112;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JLabel lblIVAFinal;
	private JTextField txtIVAFinal;
	private JLabel lblDescuentoEspecialFinal;
	private JTextField txtDescuentoEspecialFinal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblDescuentoAgenciaFinal;
	private JTextField txtDescuentoAgenciaFinal;
	private JLabel lblICEFinal;
	private JTextField txtICEFinal;
	private JLabel lblDescuentosVariosFinal;
	private JTextField txtDescuentosVariosFinal;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	private JPanel panel10;
	private JScrollPane spTblOrdenCompraDetalle;
	private JTable tblOrdenCompraDetalle;
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
	private JLabel lblPorcentajeDescuentoAgencia;
	private JLabel lblPorcentajeNegociacionDirecta;
	private JTextField txtPorcentajeNegociacionDirecta;
	private JLabel lblFechaPublicacion;
	private JTextField txtFechaPublicacion;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JTextField txtPorcentajeDescuentoAgencia;
	private JLabel lblPorcentajeOtroImpuesto;
	private JTextField txtPorcentajeOtroImpuesto;
	private JLabel lblPorcentajeDescuentosVarios;
	private JTextField txtPorcentajeDescuentosVarios;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtRevision() {
		return txtRevision;
	}

	public JTextField getTxtFechaPublicacion() {
		return txtFechaPublicacion;
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

	public JTextField getTxtPorcentajeOtroImpuesto() {
		return txtPorcentajeOtroImpuesto;
	}

	public JTextField getTxtPorcentajeDescuentosVarios() {
		return txtPorcentajeDescuentosVarios;
	}

	public JTextField getTxtPorcentajeDescuentoEspecial() {
		return txtPorcentajeDescuentoEspecial;
	}

	public JTextField getTxtPorcentajeDescuentoAgencia() {
		return txtPorcentajeDescuentoAgencia;
	}

	public JTextField getTxtPorcentajeNegociacionDirecta() {
		return txtPorcentajeNegociacionDirecta;
	}

	public JTextField getTxtPresupuesto() {
		return txtPresupuesto;
	}

	public void setTxtPresupuesto(JTextField txtPresupuesto) {
		this.txtPresupuesto = txtPresupuesto;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public void setBtnActualizarDetalle(JButton btnActualizarDetalle) {
		this.btnActualizarDetalle = btnActualizarDetalle;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public void setBtnBuscarProveedor(JButton btnBuscarProveedor) {
		this.btnBuscarProveedor = btnBuscarProveedor;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public void setCmbBodega(JComboBox cmbBodega) {
		this.cmbBodega = cmbBodega;
	}

	public JComboBox getCmbEmpleado() {
		return cmbEmpleado;
	}

	public void setCmbEmpleado(JComboBox cmbEmpleado) {
		this.cmbEmpleado = cmbEmpleado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public DateComboBox getCmbFechaRecepcion() {
		return cmbFechaRecepcion;
	}

	public void setCmbFechaRecepcion(DateComboBox cmbFechaRecepcion) {
		this.cmbFechaRecepcion = cmbFechaRecepcion;
	}

	public DateComboBox getCmbFechaVencimiento() {
		return cmbFechaVencimiento;
	}

	public void setCmbFechaVencimiento(DateComboBox cmbFechaVencimiento) {
		this.cmbFechaVencimiento = cmbFechaVencimiento;
	}

	public JComboBox getCmbMoneda() {
		return cmbMoneda;
	}

	public void setCmbMoneda(JComboBox cmbMoneda) {
		this.cmbMoneda = cmbMoneda;
	}

	public JComboBox getCmbTipoCompra() {
		return cmbTipoCompra;
	}

	public void setCmbTipoCompra(JComboBox cmbTipoCompra) {
		this.cmbTipoCompra = cmbTipoCompra;
	}

	public JTable getTblOrdenCompraDetalle() {
		return tblOrdenCompraDetalle;
	}

	public void setTblOrdenCompraDetalle(JTable tblOrdenCompraDetalle) {
		this.tblOrdenCompraDetalle = tblOrdenCompraDetalle;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public void setTxtICEFinal(JTextField txtICEFinal) {
		this.txtICEFinal = txtICEFinal;
	}

	public JTextField getTxtIVAFinal() {
		return txtIVAFinal;
	}

	public void setTxtIVAFinal(JTextField txtIVAFinal) {
		this.txtIVAFinal = txtIVAFinal;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JTextField txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public void setTxtOtroImpuestoFinal(JTextField txtOtroImpuestoFinal) {
		this.txtOtroImpuestoFinal = txtOtroImpuestoFinal;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public void setTxtTotalFinal(JTextField txtTotalFinal) {
		this.txtTotalFinal = txtTotalFinal;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public void setTxtValorFinal(JTextField txtValorFinal) {
		this.txtValorFinal = txtValorFinal;
	}

	public JideTabbedPane getJtpOrdenCompra() {
		return jtpOrdenCompra;
	}

	public void setJtpOrdenCompra(JideTabbedPane jtpOrdenCompra) {
		this.jtpOrdenCompra = jtpOrdenCompra;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JButton getBtnBuscarSolicitudCompra() {
		return btnBuscarSolicitudCompra;
	}

	public void setBtnBuscarSolicitudCompra(JButton btnBuscarSolicitudCompra) {
		this.btnBuscarSolicitudCompra = btnBuscarSolicitudCompra;
	}

	public JTextField getTxtSolicitudCompra() {
		return txtSolicitudCompra;
	}

	public void setTxtSolicitudCompra(JTextField txtSolicitudCompra) {
		this.txtSolicitudCompra = txtSolicitudCompra;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextArea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
}
