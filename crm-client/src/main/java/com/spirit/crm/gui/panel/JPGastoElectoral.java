package com.spirit.crm.gui.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
/*
 * Created by JFormDesigner on Fri Mar 13 17:23:16 COT 2009
 */


public abstract class JPGastoElectoral extends SpiritModelImpl {
	public JPGastoElectoral() {
		initComponents();
		setName("Gasto Electoral");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpGastoElectoral = new JideTabbedPane();
		panelEgresos = new JPanel();
		lblCampana = new JLabel();
		txtCampana = new JTextField();
		lblFecha = new JLabel();
		cmbFecha = new DateComboBox();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblTipo = new JLabel();
		txtTipo = new JTextField();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		lblTamano = new JLabel();
		txtTamano = new JTextField();
		lblCantidad = new JLabel();
		lblCostoUnitario = new JLabel();
		txtCostoUnitario = new JTextField();
		lblInversionConFactura = new JLabel();
		txtInversionConFactura = new JTextField();
		lblInversionSinFactura = new JLabel();
		txtInversionSinFactura = new JTextField();
		txtCantidad = new JTextField();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spTblGastos = new JScrollPane();
		tblGastos = new JTable();
		panelIngresos = new JPanel();
		lblCampanaIngreso = new JLabel();
		txtCampanaIngreso = new JTextField();
		lblFechaIngreso = new JLabel();
		cmbFechaIngreso = new DateComboBox();
		lblEntregadoPor = new JLabel();
		txtEntregadoPor = new JTextField();
		lblValorIngreso = new JLabel();
		txtValorIngreso = new JTextField();
		panel2 = new JPanel();
		btnAgregarIngreso = new JButton();
		btnActualizarIngreso = new JButton();
		btnEliminarIngreso = new JButton();
		spTblIngresos = new JScrollPane();
		tblIngresos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC
			}));

		//======== jtpGastoElectoral ========
		{
			
			//======== panelEgresos ========
			{
				panelEgresos.setLayout(new FormLayout(
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
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(30)),
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
						new RowSpec(Sizes.dluY(10)),
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
				
				//---- lblCampana ----
				lblCampana.setText("Campa\u00f1a:");
				panelEgresos.add(lblCampana, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtCampana, cc.xywh(5, 3, 9, 1));
				
				//---- lblFecha ----
				lblFecha.setText("Fecha:");
				panelEgresos.add(lblFecha, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(cmbFecha, cc.xy(5, 5));
				
				//---- lblProveedor ----
				lblProveedor.setText("Proveedor:");
				panelEgresos.add(lblProveedor, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtProveedor, cc.xywh(5, 9, 9, 1));
				
				//---- lblDescripcion ----
				lblDescripcion.setText("Descripcion:");
				panelEgresos.add(lblDescripcion, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtDescripcion, cc.xywh(5, 11, 9, 1));
				
				//---- lblTipo ----
				lblTipo.setText("Tipo:");
				panelEgresos.add(lblTipo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtTipo, cc.xy(5, 7));
				
				//---- lblProducto ----
				lblProducto.setText("Producto:");
				panelEgresos.add(lblProducto, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtProducto, cc.xy(11, 7));
				
				//---- lblTamano ----
				lblTamano.setText("Tama\u00f1o:");
				panelEgresos.add(lblTamano, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtTamano, cc.xy(5, 13));
				
				//---- lblCantidad ----
				lblCantidad.setText("Cantidad:");
				panelEgresos.add(lblCantidad, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblCostoUnitario ----
				lblCostoUnitario.setText("Costo Unitario:");
				panelEgresos.add(lblCostoUnitario, cc.xywh(9, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtCostoUnitario, cc.xy(11, 15));
				
				//---- lblInversionConFactura ----
				lblInversionConFactura.setText("Inv. con factura:");
				panelEgresos.add(lblInversionConFactura, cc.xy(3, 17));
				panelEgresos.add(txtInversionConFactura, cc.xy(5, 17));
				
				//---- lblInversionSinFactura ----
				lblInversionSinFactura.setText("Inv. sin factura:");
				panelEgresos.add(lblInversionSinFactura, cc.xywh(9, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelEgresos.add(txtInversionSinFactura, cc.xy(11, 17));
				panelEgresos.add(txtCantidad, cc.xy(5, 15));
				
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
				panelEgresos.add(panel1, cc.xywh(3, 21, 3, 1));
				
				//======== spTblGastos ========
				{
					
					//---- tblGastos ----
					tblGastos.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, "", null, null, null, null, null},
						},
						new String[] {
							"Campa\u00f1a", "Fecha", "Tipo", "Producto", "Proveedor", "Cantidad", "Costo Unitario", "Inv. sin factura"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblGastos.setViewportView(tblGastos);
				}
				panelEgresos.add(spTblGastos, cc.xywh(3, 23, 13, 5));
			}
			jtpGastoElectoral.addTab("Egresos", panelEgresos);
			
			
			//======== panelIngresos ========
			{
				panelIngresos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(95)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
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
						new RowSpec(Sizes.dluY(10))
					}));
				
				//---- lblCampanaIngreso ----
				lblCampanaIngreso.setText("Campana:");
				panelIngresos.add(lblCampanaIngreso, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIngresos.add(txtCampanaIngreso, cc.xywh(5, 3, 3, 1));
				
				//---- lblFechaIngreso ----
				lblFechaIngreso.setText("Fecha:");
				panelIngresos.add(lblFechaIngreso, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIngresos.add(cmbFechaIngreso, cc.xy(5, 5));
				
				//---- lblEntregadoPor ----
				lblEntregadoPor.setText("Entregado por:");
				panelIngresos.add(lblEntregadoPor, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIngresos.add(txtEntregadoPor, cc.xywh(5, 7, 3, 1));
				
				//---- lblValorIngreso ----
				lblValorIngreso.setText("Valor:");
				panelIngresos.add(lblValorIngreso, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelIngresos.add(txtValorIngreso, cc.xy(5, 9));
				
				//======== panel2 ========
				{
					panel2.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnAgregarIngreso ----
					btnAgregarIngreso.setText("A");
					panel2.add(btnAgregarIngreso, cc.xy(1, 1));
					
					//---- btnActualizarIngreso ----
					btnActualizarIngreso.setText("U");
					panel2.add(btnActualizarIngreso, cc.xy(3, 1));
					
					//---- btnEliminarIngreso ----
					btnEliminarIngreso.setText("D");
					panel2.add(btnEliminarIngreso, cc.xy(5, 1));
				}
				panelIngresos.add(panel2, cc.xywh(3, 13, 3, 1));
				
				//======== spTblIngresos ========
				{
					
					//---- tblIngresos ----
					tblIngresos.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null},
						},
						new String[] {
							"Campa\u00f1a", "Fecha", "Entregado por", "Valor"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblIngresos.setViewportView(tblIngresos);
				}
				panelIngresos.add(spTblIngresos, cc.xywh(3, 15, 7, 5));
			}
			jtpGastoElectoral.addTab("Ingresos", panelIngresos);
			
		}
		add(jtpGastoElectoral, cc.xywh(1, 1, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnAgregarDetalle.setText("");
		btnActualizarDetalle.setText("");
		btnEliminarDetalle.setText("");
		btnAgregarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		btnAgregarDetalle.setToolTipText("Agregar Detalle");		
		btnActualizarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		btnActualizarDetalle.setToolTipText("Actualizar Detalle");		
		btnEliminarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		btnEliminarDetalle.setToolTipText("Eliminar Detalle");
		btnAgregarIngreso.setText("");
		btnActualizarIngreso.setText("");
		btnEliminarIngreso.setText("");
		btnAgregarIngreso.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		btnAgregarIngreso.setToolTipText("Agregar Ingreso");		
		btnActualizarIngreso.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		btnActualizarIngreso.setToolTipText("Actualizar Ingreso");		
		btnEliminarIngreso.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		btnEliminarIngreso.setToolTipText("Eliminar Ingreso");
		
		TableCellRendererHorizontalRightAlignment tableCellRenderer = new TableCellRendererHorizontalRightAlignment();
		tblGastos.getColumnModel().getColumn(5).setCellRenderer(tableCellRenderer);
		tblGastos.getColumnModel().getColumn(6).setCellRenderer(tableCellRenderer);
		tblGastos.getColumnModel().getColumn(7).setCellRenderer(tableCellRenderer);
		tblIngresos.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpGastoElectoral;
	private JPanel panelEgresos;
	private JLabel lblCampana;
	private JTextField txtCampana;
	private JLabel lblFecha;
	private DateComboBox cmbFecha;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblTipo;
	private JTextField txtTipo;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JLabel lblTamano;
	private JTextField txtTamano;
	private JLabel lblCantidad;
	private JLabel lblCostoUnitario;
	private JTextField txtCostoUnitario;
	private JLabel lblInversionConFactura;
	private JTextField txtInversionConFactura;
	private JLabel lblInversionSinFactura;
	private JTextField txtInversionSinFactura;
	private JTextField txtCantidad;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spTblGastos;
	private JTable tblGastos;
	private JPanel panelIngresos;
	private JLabel lblCampanaIngreso;
	private JTextField txtCampanaIngreso;
	private JLabel lblFechaIngreso;
	private DateComboBox cmbFechaIngreso;
	private JLabel lblEntregadoPor;
	private JTextField txtEntregadoPor;
	private JLabel lblValorIngreso;
	private JTextField txtValorIngreso;
	private JPanel panel2;
	private JButton btnAgregarIngreso;
	private JButton btnActualizarIngreso;
	private JButton btnEliminarIngreso;
	private JScrollPane spTblIngresos;
	private JTable tblIngresos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
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

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public DateComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(DateComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public JTable getTblGastos() {
		return tblGastos;
	}

	public void setTblGastos(JTable tblGastos) {
		this.tblGastos = tblGastos;
	}

	public JTextField getTxtCampana() {
		return txtCampana;
	}

	public void setTxtCampana(JTextField txtCampana) {
		this.txtCampana = txtCampana;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtCostoUnitario() {
		return txtCostoUnitario;
	}

	public void setTxtCostoUnitario(JTextField txtCostoUnitario) {
		this.txtCostoUnitario = txtCostoUnitario;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JTextField getTxtInversionConFactura() {
		return txtInversionConFactura;
	}

	public void setTxtInversionConFactura(JTextField txtInversionConFactura) {
		this.txtInversionConFactura = txtInversionConFactura;
	}

	public JTextField getTxtInversionSinFactura() {
		return txtInversionSinFactura;
	}

	public void setTxtInversionSinFactura(JTextField txtInversionSinFactura) {
		this.txtInversionSinFactura = txtInversionSinFactura;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JTextField getTxtTamano() {
		return txtTamano;
	}

	public void setTxtTamano(JTextField txtTamano) {
		this.txtTamano = txtTamano;
	}

	public JTextField getTxtTipo() {
		return txtTipo;
	}

	public void setTxtTipo(JTextField txtTipo) {
		this.txtTipo = txtTipo;
	}

	public JButton getBtnActualizarIngreso() {
		return btnActualizarIngreso;
	}

	public void setBtnActualizarIngreso(JButton btnActualizarIngreso) {
		this.btnActualizarIngreso = btnActualizarIngreso;
	}

	public JButton getBtnAgregarIngreso() {
		return btnAgregarIngreso;
	}

	public void setBtnAgregarIngreso(JButton btnAgregarIngreso) {
		this.btnAgregarIngreso = btnAgregarIngreso;
	}

	public JButton getBtnEliminarIngreso() {
		return btnEliminarIngreso;
	}

	public void setBtnEliminarIngreso(JButton btnEliminarIngreso) {
		this.btnEliminarIngreso = btnEliminarIngreso;
	}

	public DateComboBox getCmbFechaIngreso() {
		return cmbFechaIngreso;
	}

	public void setCmbFechaIngreso(DateComboBox cmbFechaIngreso) {
		this.cmbFechaIngreso = cmbFechaIngreso;
	}

	public JTable getTblIngresos() {
		return tblIngresos;
	}

	public void setTblIngresos(JTable tblIngresos) {
		this.tblIngresos = tblIngresos;
	}

	public JTextField getTxtCampanaIngreso() {
		return txtCampanaIngreso;
	}

	public void setTxtCampanaIngreso(JTextField txtCampanaIngreso) {
		this.txtCampanaIngreso = txtCampanaIngreso;
	}

	public JTextField getTxtEntregadoPor() {
		return txtEntregadoPor;
	}

	public void setTxtEntregadoPor(JTextField txtEntregadoPor) {
		this.txtEntregadoPor = txtEntregadoPor;
	}

	public JTextField getTxtValorIngreso() {
		return txtValorIngreso;
	}

	public void setTxtValorIngreso(JTextField txtValorIngreso) {
		this.txtValorIngreso = txtValorIngreso;
	}

	public JideTabbedPane getJtpGastoElectoral() {
		return jtpGastoElectoral;
	}

	public void setJtpGastoElectoral(JideTabbedPane jtpGastoElectoral) {
		this.jtpGastoElectoral = jtpGastoElectoral;
	}
}
