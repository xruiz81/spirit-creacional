package com.spirit.cartera.gui.panel;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
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

public abstract class JPAnularNotaCredito extends SpiritModelImpl {
	public JPAnularNotaCredito() {
		initComponents();
		setName("Anular nota de crédito");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpAnularNotaCredito = new JideTabbedPane();
		spCabecera = new JScrollPane();
		panel32 = new JPanel();
		goodiesFormsSeparator1 = compFactory.createSeparator("Datos de la Nota Cr\u00e9dito");
		lblCodigo = new JLabel();
		lblFechaEmision = new JLabel();
		cmbFechaEmision = new DateComboBox();
		txtCodigo = new JTextField();
		lblTipoCartera = new JLabel();
		txtTipoCartera = new JTextField();
		lblFechaVencimiento = new JLabel();
		cmbFechaVencimiento = new DateComboBox();
		lblTipoDocumento = new JLabel();
		txtTipoDocumento = new JTextField();
		lblFechaCaducidad = new JLabel();
		cmbFechaCaducidad = new DateComboBox();
		lblOficina = new JLabel();
		txtOficina = new JTextField();
		lblMoneda = new JLabel();
		txtMoneda = new JTextField();
		lblOperadorNegocio = new JLabel();
		btnBuscarOperadorNegocio = new JButton();
		txtOperadorNegocio = new JTextField();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblEstado = new JLabel();
		txtEstado = new JTextField();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		goodiesFormsSeparator3 = compFactory.createSeparator("Datos Generales");
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
		txtDocumento = new JTextField();
		scPlantilla = new JScrollPane();
		tblNotaCreditoDetalle = new JTable();
		lblCodigoProducto = new JLabel();
		txtProducto = new JTextField();
		lblDescripcion = new JLabel();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		spDescripcion = new JScrollPane();
		txtDescripcion = new JTextArea();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblOtroImpuesto = new JLabel();
		txtOtroImpuesto = new JTextField();
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
					txtCodigo.setHorizontalAlignment(JTextField.LEADING);
					txtCodigo.setEditable(false);
					panel32.add(txtCodigo, cc.xy(5, 5));
					
					//---- lblTipoCartera ----
					lblTipoCartera.setText("Tipo cartera:");
					panel32.add(lblTipoCartera, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panel32.add(txtTipoCartera, cc.xy(5, 7));
					
					//---- lblFechaVencimiento ----
					lblFechaVencimiento.setText("Fecha vencimiento:");
					lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblFechaVencimiento, cc.xy(15, 7));
					panel32.add(cmbFechaVencimiento, cc.xy(17, 7));
					
					//---- lblTipoDocumento ----
					lblTipoDocumento.setText("Tipo documento:");
					panel32.add(lblTipoDocumento, cc.xy(3, 9));
					panel32.add(txtTipoDocumento, cc.xywh(5, 9, 3, 1));
					
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
					txtOficina.setHorizontalAlignment(JTextField.LEADING);
					panel32.add(txtOficina, cc.xywh(5, 11, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblMoneda ----
					lblMoneda.setText("Moneda:");
					lblMoneda.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblMoneda, cc.xywh(15, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panel32.add(txtMoneda, cc.xy(17, 11));
					
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
					panel32.add(txtReferencia, cc.xywh(5, 15, 5, 1));
					
					//---- lblEstado ----
					lblEstado.setText("Estado:");
					lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
					panel32.add(lblEstado, cc.xy(3, 17));
					panel32.add(txtEstado, cc.xy(5, 17));
					
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
					panel32.add(txtAutorizacion, cc.xy(5, 27));
					
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
			jtpAnularNotaCredito.addTab("Cabecera", spCabecera);
			
			
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
								new RowSpec(Sizes.dluY(12)),
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
							}));
						
						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblDocumento, cc.xy(3, 1));
						panel10.add(txtDocumento, cc.xywh(5, 1, 9, 1));
						
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
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblNotaCreditoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 150));
							tblNotaCreditoDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							scPlantilla.setViewportView(tblNotaCreditoDetalle);
						}
						panel10.add(scPlantilla, cc.xywh(3, 19, 15, 1));
						
						//---- lblCodigoProducto ----
						lblCodigoProducto.setText("Producto:");
						lblCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCodigoProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						panel10.add(txtProducto, cc.xywh(5, 3, 9, 1));
						
						//---- lblDescripcion ----
						lblDescripcion.setText("Descripci\u00f3n:");
						panel10.add(lblDescripcion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblCantidad, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtCantidad, cc.xy(5, 11));
						
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
						panel10.add(lblValor, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtValor ----
						txtValor.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtValor, cc.xywh(5, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblOtroImpuesto ----
						lblOtroImpuesto.setText("Otro impuesto [%]:");
						lblOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
						panel10.add(lblOtroImpuesto, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtOtroImpuesto ----
						txtOtroImpuesto.setHorizontalAlignment(JTextField.RIGHT);
						panel10.add(txtOtroImpuesto, cc.xy(5, 15));
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
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						((FormLayout)panel112.getLayout()).setColumnGroups(new int[][] {{5, 11}});
						
						//---- lblValorFinal ----
						lblValorFinal.setText("Valor:");
						panel112.add(lblValorFinal, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtValorFinal ----
						txtValorFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtValorFinal, cc.xywh(11, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblIVAFinal ----
						lblIVAFinal.setText("IVA:");
						panel112.add(lblIVAFinal, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtIVAFinal ----
						txtIVAFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtIVAFinal, cc.xywh(11, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblICEFinal ----
						lblICEFinal.setText("ICE:");
						panel112.add(lblICEFinal, cc.xywh(9, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtICEFinal ----
						txtICEFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtICEFinal, cc.xywh(11, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblOtroImpuestoFinal ----
						lblOtroImpuestoFinal.setText("Otro impuesto:");
						panel112.add(lblOtroImpuestoFinal, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtOtroImpuestoFinal ----
						txtOtroImpuestoFinal.setHorizontalAlignment(JTextField.RIGHT);
						panel112.add(txtOtroImpuestoFinal, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
						
						//---- lblTotalFinal ----
						lblTotalFinal.setText("TOTAL:");
						lblTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(lblTotalFinal, cc.xywh(9, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
						
						//---- txtTotalFinal ----
						txtTotalFinal.setHorizontalAlignment(JTextField.RIGHT);
						txtTotalFinal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
						panel112.add(txtTotalFinal, cc.xywh(11, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					}
					panel11.add(panel112, cc.xy(1, 3));
				}
				spDetalle.setViewportView(panel11);
			}
			jtpAnularNotaCredito.addTab("Detalle", spDetalle);
			
		}
		add(jtpAnularNotaCredito, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpAnularNotaCredito;
	private JScrollPane spCabecera;
	private JPanel panel32;
	private JComponent goodiesFormsSeparator1;
	private JLabel lblCodigo;
	private JLabel lblFechaEmision;
	private DateComboBox cmbFechaEmision;
	private JTextField txtCodigo;
	private JLabel lblTipoCartera;
	private JTextField txtTipoCartera;
	private JLabel lblFechaVencimiento;
	private DateComboBox cmbFechaVencimiento;
	private JLabel lblTipoDocumento;
	private JTextField txtTipoDocumento;
	private JLabel lblFechaCaducidad;
	private DateComboBox cmbFechaCaducidad;
	private JLabel lblOficina;
	private JTextField txtOficina;
	private JLabel lblMoneda;
	private JTextField txtMoneda;
	private JLabel lblOperadorNegocio;
	private JButton btnBuscarOperadorNegocio;
	private JTextField txtOperadorNegocio;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JComponent goodiesFormsSeparator3;
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
	private JTextField txtDocumento;
	private JScrollPane scPlantilla;
	private JTable tblNotaCreditoDetalle;
	private JLabel lblCodigoProducto;
	private JTextField txtProducto;
	private JLabel lblDescripcion;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JScrollPane spDescripcion;
	private JTextArea txtDescripcion;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblOtroImpuesto;
	private JTextField txtOtroImpuesto;
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
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JideTabbedPane getJtpAnularNotaCredito() {
		return jtpAnularNotaCredito;
	}

	public void setJtpAnularNotaCredito(JideTabbedPane jtpAnularNotaCredito) {
		this.jtpAnularNotaCredito = jtpAnularNotaCredito;
	}

	public DateComboBox getCmbFechaEmision() {
		return cmbFechaEmision;
	}

	public void setCmbFechaEmision(DateComboBox cmbFechaEmision) {
		this.cmbFechaEmision = cmbFechaEmision;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtTipoCartera() {
		return txtTipoCartera;
	}

	public void setTxtTipoCartera(JTextField txtTipoCartera) {
		this.txtTipoCartera = txtTipoCartera;
	}

	public DateComboBox getCmbFechaVencimiento() {
		return cmbFechaVencimiento;
	}

	public void setCmbFechaVencimiento(DateComboBox cmbFechaVencimiento) {
		this.cmbFechaVencimiento = cmbFechaVencimiento;
	}

	public JTextField getTxtTipoDocumento() {
		return txtTipoDocumento;
	}

	public void setTxtTipoDocumento(JTextField txtTipoDocumento) {
		this.txtTipoDocumento = txtTipoDocumento;
	}

	public DateComboBox getCmbFechaCaducidad() {
		return cmbFechaCaducidad;
	}

	public void setCmbFechaCaducidad(DateComboBox cmbFechaCaducidad) {
		this.cmbFechaCaducidad = cmbFechaCaducidad;
	}

	public JTextField getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JTextField txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JTextField getTxtMoneda() {
		return txtMoneda;
	}

	public void setTxtMoneda(JTextField txtMoneda) {
		this.txtMoneda = txtMoneda;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}

	public JTextField getTxtOperadorNegocio() {
		return txtOperadorNegocio;
	}

	public void setTxtOperadorNegocio(JTextField txtOperadorNegocio) {
		this.txtOperadorNegocio = txtOperadorNegocio;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextField getTxtEstado() {
		return txtEstado;
	}

	public void setTxtEstado(JTextField txtEstado) {
		this.txtEstado = txtEstado;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}

	public void setTxtPreimpreso(JTextField txtPreimpreso) {
		this.txtPreimpreso = txtPreimpreso;
	}

	public JTextField getTxtAutorizacion() {
		return txtAutorizacion;
	}

	public void setTxtAutorizacion(JTextField txtAutorizacion) {
		this.txtAutorizacion = txtAutorizacion;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JTextField getTxtDocumento() {
		return txtDocumento;
	}

	public void setTxtDocumento(JTextField txtDocumento) {
		this.txtDocumento = txtDocumento;
	}

	public JTable getTblNotaCreditoDetalle() {
		return tblNotaCreditoDetalle;
	}

	public void setTblNotaCreditoDetalle(JTable tblNotaCreditoDetalle) {
		this.tblNotaCreditoDetalle = tblNotaCreditoDetalle;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextArea getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextArea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JTextField getTxtOtroImpuesto() {
		return txtOtroImpuesto;
	}

	public void setTxtOtroImpuesto(JTextField txtOtroImpuesto) {
		this.txtOtroImpuesto = txtOtroImpuesto;
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

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public void setTxtICEFinal(JTextField txtICEFinal) {
		this.txtICEFinal = txtICEFinal;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public void setTxtOtroImpuestoFinal(JTextField txtOtroImpuestoFinal) {
		this.txtOtroImpuestoFinal = txtOtroImpuestoFinal;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public void setTxtTotalFinal(JTextField txtTotalFinal) {
		this.txtTotalFinal = txtTotalFinal;
	}
}
