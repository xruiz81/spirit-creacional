package com.spirit.sri.gui.panel;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.jidesoft.swing.*;
import com.spirit.client.model.SpiritModelImpl;
/*
 * Created by JFormDesigner on Fri Oct 23 15:23:07 COT 2009
 */



/**
 * @author Antonio Seiler
 */
public abstract class JPDimm extends SpiritModelImpl {
	public JPDimm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblMes = new JLabel();
		jtpGenerar = new JideTabbedPane();
		panelCompras = new JPanel();
		btnGenerarReoc = new JButton();
		spTblCompras = new JScrollPane();
		tblCompras = new JTable();
		panelVentas = new JPanel();
		spTblVentas = new JScrollPane();
		tblVentas = new JTable();
		panelAnulaciones = new JPanel();
		spTblAnulaciones = new JScrollPane();
		tblAnulaciones = new JTable();
		panelExportaciones = new JPanel();
		spTblExportaciones = new JScrollPane();
		tblExportaciones = new JTable();
		panelGeneracionXML = new JPanel();
		spTextoXML = new JScrollPane();
		txtTextoXML = new JTextArea();
		btnGenerarAnexoTransaccional = new JButton();
		panelErrores = new JPanel();
		spTblErrores = new JScrollPane();
		txtErrores = new JTextArea();
		spnAnio = new JSpinner();
		lblAnio = new JLabel();
		chbCompras = new JCheckBox();
		chbAnulaciones = new JCheckBox();
		btnConsultar = new JButton();
		cmbMes = new JComboBox();
		chbVentas = new JCheckBox();
		chbImportaciones = new JCheckBox();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("DIMM");
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(20)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(40), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblMes ----
		lblMes.setText("Mes");
		add(lblMes, cc.xy(3, 5));

		//======== jtpGenerar ========
		{
			
			//======== panelCompras ========
			{
				panelCompras.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
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
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				
				//---- btnGenerarReoc ----
				btnGenerarReoc.setText("Generar Reoc");
				panelCompras.add(btnGenerarReoc, cc.xy(5, 7));
				
				//======== spTblCompras ========
				{
					
					//---- tblCompras ----
					tblCompras.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, "", "", null, "", null, null, null, null, null},
						},
						new String[] {
							" ", "Numero", "Tipo Identificaci\u00f3n", "Id Proveedor", "Cod. Comprobante", "No. Compr. Venta", "No. Autorizaci\u00f3n", "Fecha Emi. Comp.", "Base IVA 0%", "Base Iva Diferente 0%", "Base no objeto de IVA", "Monto IVA"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Integer.class, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, Double.class, Double.class, Double.class, Double.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false, false, false, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblCompras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblCompras.setViewportView(tblCompras);
				}
				panelCompras.add(spTblCompras, cc.xywh(3, 3, 3, 3));
			}
			jtpGenerar.addTab("Compras", panelCompras);
			
			
			//======== panelVentas ========
			{
				panelVentas.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
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
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				
				//======== spTblVentas ========
				{
					
					//---- tblVentas ----
					tblVentas.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null, null, null},
						},
						new String[] {
							" ", "Numero", "Cliente", "Tipo Comprobante", "No. Emisiones", "Base 0%", "Base Gravada", "IVA"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Object.class, Object.class, Double.class, Double.class, Double.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblVentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblVentas.setViewportView(tblVentas);
				}
				panelVentas.add(spTblVentas, cc.xywh(3, 3, 1, 3));
			}
			jtpGenerar.addTab("Ventas", panelVentas);
			
			
			//======== panelAnulaciones ========
			{
				panelAnulaciones.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
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
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				
				//======== spTblAnulaciones ========
				{
					
					//---- tblAnulaciones ----
					tblAnulaciones.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, "", null, null, null},
						},
						new String[] {
							" ", "N\u00famero", "Tipo Comprobante", "Establecimiento", "Punto Emisi\u00f3n", "Num. Sec. Inicial", "Num. Sec. Final", "Autorizaci\u00f3n"
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblAnulaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblAnulaciones.setViewportView(tblAnulaciones);
				}
				panelAnulaciones.add(spTblAnulaciones, cc.xywh(3, 3, 1, 3));
			}
			jtpGenerar.addTab("Anulaciones", panelAnulaciones);
			
			
			//======== panelExportaciones ========
			{
				panelExportaciones.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
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
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				
				//======== spTblExportaciones ========
				{
					
					//---- tblExportaciones ----
					tblExportaciones.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null, null, null, null},
						},
						new String[] {
							"Numero", "Tipo Exportaci\u00f3n", "Tipo Comprobante", "Refrendo", "No. FUE", "Valor FOB-FUE", "Factura de Exportaci\u00f3n", "Autorizaci\u00f3n", "Valor FOB Comp. Local"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblExportaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					spTblExportaciones.setViewportView(tblExportaciones);
				}
				panelExportaciones.add(spTblExportaciones, cc.xywh(3, 3, 1, 3));
			}
			jtpGenerar.addTab("Exportaciones", panelExportaciones);
			
			
			//======== panelGeneracionXML ========
			{
				panelGeneracionXML.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
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
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				
				//======== spTextoXML ========
				{
					spTextoXML.setViewportView(txtTextoXML);
				}
				panelGeneracionXML.add(spTextoXML, cc.xywh(3, 3, 3, 1));
				
				//---- btnGenerarAnexoTransaccional ----
				btnGenerarAnexoTransaccional.setText("Generar Anexo Transaccional");
				panelGeneracionXML.add(btnGenerarAnexoTransaccional, cc.xy(5, 5));
			}
			jtpGenerar.addTab("XML", panelGeneracionXML);
			
			
			//======== panelErrores ========
			{
				panelErrores.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(12)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(12))
					}));
				
				//======== spTblErrores ========
				{
					
					//---- txtErrores ----
					txtErrores.setRows(10);
					spTblErrores.setViewportView(txtErrores);
				}
				panelErrores.add(spTblErrores, cc.xy(3, 3));
			}
			jtpGenerar.addTab("Errores", panelErrores);
			
		}
		add(jtpGenerar, cc.xywh(3, 9, 15, 1));

		//---- spnAnio ----
		spnAnio.setModel(new SpinnerNumberModel(2007, 2007, 2100, 1));
		add(spnAnio, cc.xy(5, 3));

		//---- lblAnio ----
		lblAnio.setText("A\u00f1o");
		add(lblAnio, cc.xy(3, 3));

		//---- chbCompras ----
		chbCompras.setText("Compras");
		add(chbCompras, cc.xy(11, 3));

		//---- chbAnulaciones ----
		chbAnulaciones.setText("Anulaciones");
		add(chbAnulaciones, cc.xy(13, 3));

		//---- btnConsultar ----
		btnConsultar.setText("Consultar");
		add(btnConsultar, cc.xy(17, 3));

		//---- cmbMes ----
		cmbMes.setModel(new DefaultComboBoxModel(new String[] {
			"ENERO",
			"FEBRERO",
			"MARZO",
			"ABRIL",
			"MAYO",
			"JUNIO",
			"JULIO",
			"AGOSTO",
			"SEPTIEMBRE",
			"OCTUBRE",
			"NOVIEMBRE",
			"DICIEMBRE"
		}));
		cmbMes.setEditable(false);
		add(cmbMes, cc.xywh(5, 5, 3, 1));

		//---- chbVentas ----
		chbVentas.setText("Ventas");
		add(chbVentas, cc.xy(11, 5));

		//---- chbImportaciones ----
		chbImportaciones.setText("Importaciones");
		add(chbImportaciones, cc.xy(13, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblMes;
	private JideTabbedPane jtpGenerar;
	private JPanel panelCompras;
	private JButton btnGenerarReoc;
	private JScrollPane spTblCompras;
	private JTable tblCompras;
	private JPanel panelVentas;
	private JScrollPane spTblVentas;
	private JTable tblVentas;
	private JPanel panelAnulaciones;
	private JScrollPane spTblAnulaciones;
	private JTable tblAnulaciones;
	private JPanel panelExportaciones;
	private JScrollPane spTblExportaciones;
	private JTable tblExportaciones;
	private JPanel panelGeneracionXML;
	private JScrollPane spTextoXML;
	private JTextArea txtTextoXML;
	private JButton btnGenerarAnexoTransaccional;
	private JPanel panelErrores;
	private JScrollPane spTblErrores;
	private JTextArea txtErrores;
	private JSpinner spnAnio;
	private JLabel lblAnio;
	private JCheckBox chbCompras;
	private JCheckBox chbAnulaciones;
	private JButton btnConsultar;
	private JComboBox cmbMes;
	private JCheckBox chbVentas;
	private JCheckBox chbImportaciones;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JLabel getLblMes() {
		return lblMes;
	}

	public JideTabbedPane getJtpGenerar() {
		return jtpGenerar;
	}

	public JPanel getPanelCompras() {
		return panelCompras;
	}

	public JButton getBtnGenerarReoc() {
		return btnGenerarReoc;
	}

	public JScrollPane getSpTblCompras() {
		return spTblCompras;
	}

	public JTable getTblCompras() {
		return tblCompras;
	}

	public JPanel getPanelVentas() {
		return panelVentas;
	}

	public JScrollPane getSpTblVentas() {
		return spTblVentas;
	}

	public JTable getTblVentas() {
		return tblVentas;
	}

	public JPanel getPanelAnulaciones() {
		return panelAnulaciones;
	}

	public JScrollPane getSpTblAnulaciones() {
		return spTblAnulaciones;
	}

	public JTable getTblAnulaciones() {
		return tblAnulaciones;
	}

	public JPanel getPanelExportaciones() {
		return panelExportaciones;
	}

	public JScrollPane getSpTblExportaciones() {
		return spTblExportaciones;
	}

	public JTable getTblExportaciones() {
		return tblExportaciones;
	}

	public JPanel getPanelGeneracionXML() {
		return panelGeneracionXML;
	}

	public JScrollPane getSpTextoXML() {
		return spTextoXML;
	}

	public JTextArea getTxtTextoXML() {
		return txtTextoXML;
	}

	public JButton getBtnGenerarAnexoTransaccional() {
		return btnGenerarAnexoTransaccional;
	}

	public JPanel getPanelErrores() {
		return panelErrores;
	}

	public JScrollPane getSpTblErrores() {
		return spTblErrores;
	}

	public JTextArea getTxtErrores() {
		return txtErrores;
	}

	public JSpinner getSpnAnio() {
		return spnAnio;
	}

	public JLabel getLblAnio() {
		return lblAnio;
	}

	public JCheckBox getChbCompras() {
		return chbCompras;
	}

	public JCheckBox getChbAnulaciones() {
		return chbAnulaciones;
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public JComboBox getCmbMes() {
		return cmbMes;
	}

	public JCheckBox getChbVentas() {
		return chbVentas;
	}

	public JCheckBox getChbImportaciones() {
		return chbImportaciones;
	}
}
