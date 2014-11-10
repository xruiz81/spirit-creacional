package com.spirit.medios.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;

/**
 * @author xruiz
 */
public abstract class JPProducto extends SpiritModelImpl {
	public JPProducto() {
		initComponents();
		setName("Marcas y Productos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpProducto = new JideTabbedPane();
		panelMarca = new JPanel();
		lblCodigoMarca = new JLabel();
		txtCodigoMarca = new JTextField();
		lblEstadoMarca = new JLabel();
		cmbEstadoMarca = new JComboBox();
		btnBuscarCorporacion = new JButton();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		lblNombreMarca = new JLabel();
		txtNombreMarca = new JTextField();
		panelBotonesMarca = new JPanel();
		btnAgregarMarca = new JButton();
		btnActualizarMarca = new JButton();
		btnEliminarMarca = new JButton();
		spTblMarca = new JScrollPane();
		tblMarca = new JTable();
		panelProducto = new JPanel();
		lblMarca = new JLabel();
		txtMarca = new JTextField();
		lblCodigoProducto = new JLabel();
		txtCodigoProducto = new JTextField();
		lblEstadoProducto = new JLabel();
		cmbEstadoProducto = new JComboBox();
		lblNombreProducto = new JLabel();
		txtNombreProducto = new JTextField();
		lblCreativoProducto = new JLabel();
		txtCreativoProducto = new JTextField();
		btnBuscarCreativo = new JButton();
		btnBorrarCreativo = new JButton();
		lblEjecutivoProducto = new JLabel();
		txtEjecutivoProducto = new JTextField();
		btnBuscarEjecutivo = new JButton();
		btnBorrarEjecutivo = new JButton();
		panelBotonesProducto = new JPanel();
		btnAgregarProducto = new JButton();
		btnActualizarProducto = new JButton();
		btnEliminarProducto = new JButton();
		spTblProducto = new JScrollPane();
		tblProducto = new JTable();
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

		//======== jtpProducto ========
		{
			
			//======== panelMarca ========
			{
				panelMarca.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(120)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
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
				
				//---- lblCodigoMarca ----
				lblCodigoMarca.setText("C\u00f3digo:");
				panelMarca.add(lblCodigoMarca, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelMarca.add(txtCodigoMarca, cc.xy(5, 3));
				
				//---- lblEstadoMarca ----
				lblEstadoMarca.setText("Estado:");
				panelMarca.add(lblEstadoMarca, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- cmbEstadoMarca ----
				cmbEstadoMarca.setModel(new DefaultComboBoxModel(new String[] {
					"ACTIVO",
					"INACTIVO"
				}));
				panelMarca.add(cmbEstadoMarca, cc.xy(11, 3));
				panelMarca.add(btnBuscarCorporacion, cc.xywh(13, 5, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
				
				//---- lblCorporacion ----
				lblCorporacion.setText("Corporaci\u00f3n:");
				panelMarca.add(lblCorporacion, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCorporacion ----
				txtCorporacion.setEditable(true);
				panelMarca.add(txtCorporacion, cc.xywh(5, 5, 7, 1));
				
				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				panelMarca.add(lblCliente, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCliente ----
				txtCliente.setEditable(true);
				panelMarca.add(txtCliente, cc.xywh(5, 7, 7, 1));
				panelMarca.add(btnBuscarCliente, cc.xywh(13, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
				
				//---- lblNombreMarca ----
				lblNombreMarca.setText("Nombre:");
				panelMarca.add(lblNombreMarca, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelMarca.add(txtNombreMarca, cc.xywh(5, 9, 5, 1));
				
				//======== panelBotonesMarca ========
				{
					panelBotonesMarca.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnAgregarMarca ----
					btnAgregarMarca.setText("A");
					panelBotonesMarca.add(btnAgregarMarca, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- btnActualizarMarca ----
					btnActualizarMarca.setText("U");
					panelBotonesMarca.add(btnActualizarMarca, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- btnEliminarMarca ----
					btnEliminarMarca.setText("E");
					panelBotonesMarca.add(btnEliminarMarca, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				panelMarca.add(panelBotonesMarca, cc.xywh(3, 13, 5, 1));
				
				//======== spTblMarca ========
				{
					spTblMarca.setPreferredSize(new Dimension(452, 100));
					
					//---- tblMarca ----
					tblMarca.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null},
						},
						new String[] {
							"C\u00f3digo", "Nombre", "Estado"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblMarca.setPreferredScrollableViewportSize(new Dimension(450, 300));
					tblMarca.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblMarca.setAutoCreateColumnsFromModel(true);
					spTblMarca.setViewportView(tblMarca);
				}
				panelMarca.add(spTblMarca, cc.xywh(3, 15, 13, 5));
			}
			jtpProducto.addTab("Marca", panelMarca);
			
			
			//======== panelProducto ========
			{
				panelProducto.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(170)),
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
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
				
				//---- lblMarca ----
				lblMarca.setText("Marca:");
				panelProducto.add(lblMarca, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelProducto.add(txtMarca, cc.xywh(5, 3, 3, 1));
				
				//---- lblCodigoProducto ----
				lblCodigoProducto.setText("C\u00f3digo:");
				panelProducto.add(lblCodigoProducto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelProducto.add(txtCodigoProducto, cc.xy(5, 5));
				
				//---- lblEstadoProducto ----
				lblEstadoProducto.setText("Estado:");
				panelProducto.add(lblEstadoProducto, cc.xywh(7, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- cmbEstadoProducto ----
				cmbEstadoProducto.setModel(new DefaultComboBoxModel(new String[] {
					"ACTIVO",
					"INACTIVO"
				}));
				panelProducto.add(cmbEstadoProducto, cc.xywh(9, 5, 3, 1));
				
				//---- lblNombreProducto ----
				lblNombreProducto.setText("Nombre:");
				panelProducto.add(lblNombreProducto, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelProducto.add(txtNombreProducto, cc.xywh(5, 7, 3, 1));
				
				//---- lblCreativoProducto ----
				lblCreativoProducto.setText("Creativo:");
				panelProducto.add(lblCreativoProducto, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelProducto.add(txtCreativoProducto, cc.xywh(5, 9, 3, 1));
				panelProducto.add(btnBuscarCreativo, cc.xywh(9, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				panelProducto.add(btnBorrarCreativo, cc.xywh(11, 9, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- lblEjecutivoProducto ----
				lblEjecutivoProducto.setText("Ejecutivo:");
				panelProducto.add(lblEjecutivoProducto, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelProducto.add(txtEjecutivoProducto, cc.xywh(5, 11, 3, 1));
				panelProducto.add(btnBuscarEjecutivo, cc.xywh(9, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				panelProducto.add(btnBorrarEjecutivo, cc.xywh(11, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//======== panelBotonesProducto ========
				{
					panelBotonesProducto.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnAgregarProducto ----
					btnAgregarProducto.setText("A");
					panelBotonesProducto.add(btnAgregarProducto, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- btnActualizarProducto ----
					btnActualizarProducto.setText("U");
					panelBotonesProducto.add(btnActualizarProducto, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- btnEliminarProducto ----
					btnEliminarProducto.setText("E");
					panelBotonesProducto.add(btnEliminarProducto, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				}
				panelProducto.add(panelBotonesProducto, cc.xywh(3, 15, 5, 1));
				
				//======== spTblProducto ========
				{
					spTblProducto.setPreferredSize(new Dimension(452, 100));
					
					//---- tblProducto ----
					tblProducto.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null},
						},
						new String[] {
							"C\u00f3digo", "Nombre", "Estado", "Creativo", "Ejecutivo"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false, false, false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblProducto.setPreferredScrollableViewportSize(new Dimension(450, 300));
					tblProducto.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					tblProducto.setAutoCreateColumnsFromModel(true);
					spTblProducto.setViewportView(tblProducto);
				}
				panelProducto.add(spTblProducto, cc.xywh(3, 17, 11, 5));
			}
			jtpProducto.addTab("Producto", panelProducto);
			
		}
		add(jtpProducto, cc.xywh(1, 1, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpProducto;
	private JPanel panelMarca;
	private JLabel lblCodigoMarca;
	private JTextField txtCodigoMarca;
	private JLabel lblEstadoMarca;
	private JComboBox cmbEstadoMarca;
	private JButton btnBuscarCorporacion;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JLabel lblNombreMarca;
	private JTextField txtNombreMarca;
	private JPanel panelBotonesMarca;
	private JButton btnAgregarMarca;
	private JButton btnActualizarMarca;
	private JButton btnEliminarMarca;
	private JScrollPane spTblMarca;
	private JTable tblMarca;
	private JPanel panelProducto;
	private JLabel lblMarca;
	private JTextField txtMarca;
	private JLabel lblCodigoProducto;
	private JTextField txtCodigoProducto;
	private JLabel lblEstadoProducto;
	private JComboBox cmbEstadoProducto;
	private JLabel lblNombreProducto;
	private JTextField txtNombreProducto;
	private JLabel lblCreativoProducto;
	private JTextField txtCreativoProducto;
	private JButton btnBuscarCreativo;
	private JButton btnBorrarCreativo;
	private JLabel lblEjecutivoProducto;
	private JTextField txtEjecutivoProducto;
	private JButton btnBuscarEjecutivo;
	private JButton btnBorrarEjecutivo;
	private JPanel panelBotonesProducto;
	private JButton btnAgregarProducto;
	private JButton btnActualizarProducto;
	private JButton btnEliminarProducto;
	private JScrollPane spTblProducto;
	private JTable tblProducto;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnActualizarMarca() {
		return btnActualizarMarca;
	}

	public void setBtnActualizarMarca(JButton btnActualizarMarca) {
		this.btnActualizarMarca = btnActualizarMarca;
	}

	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}

	public void setBtnActualizarProducto(JButton btnActualizarProducto) {
		this.btnActualizarProducto = btnActualizarProducto;
	}

	public JButton getBtnAgregarMarca() {
		return btnAgregarMarca;
	}

	public void setBtnAgregarMarca(JButton btnAgregarMarca) {
		this.btnAgregarMarca = btnAgregarMarca;
	}

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}

	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}

	public JButton getBtnBorrarCreativo() {
		return btnBorrarCreativo;
	}

	public void setBtnBorrarCreativo(JButton btnBorrarCreativo) {
		this.btnBorrarCreativo = btnBorrarCreativo;
	}

	public JButton getBtnBorrarEjecutivo() {
		return btnBorrarEjecutivo;
	}

	public void setBtnBorrarEjecutivo(JButton btnBorrarEjecutivo) {
		this.btnBorrarEjecutivo = btnBorrarEjecutivo;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnBuscarCorporacion() {
		return btnBuscarCorporacion;
	}

	public void setBtnBuscarCorporacion(JButton btnBuscarCorporacion) {
		this.btnBuscarCorporacion = btnBuscarCorporacion;
	}

	public JButton getBtnBuscarCreativo() {
		return btnBuscarCreativo;
	}

	public void setBtnBuscarCreativo(JButton btnBuscarCreativo) {
		this.btnBuscarCreativo = btnBuscarCreativo;
	}

	public JButton getBtnBuscarEjecutivo() {
		return btnBuscarEjecutivo;
	}

	public void setBtnBuscarEjecutivo(JButton btnBuscarEjecutivo) {
		this.btnBuscarEjecutivo = btnBuscarEjecutivo;
	}

	public JButton getBtnEliminarMarca() {
		return btnEliminarMarca;
	}

	public void setBtnEliminarMarca(JButton btnEliminarMarca) {
		this.btnEliminarMarca = btnEliminarMarca;
	}

	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}

	public void setBtnEliminarProducto(JButton btnEliminarProducto) {
		this.btnEliminarProducto = btnEliminarProducto;
	}

	public JComboBox getCmbEstadoMarca() {
		return cmbEstadoMarca;
	}

	public void setCmbEstadoMarca(JComboBox cmbEstadoMarca) {
		this.cmbEstadoMarca = cmbEstadoMarca;
	}

	public JComboBox getCmbEstadoProducto() {
		return cmbEstadoProducto;
	}

	public void setCmbEstadoProducto(JComboBox cmbEstadoProducto) {
		this.cmbEstadoProducto = cmbEstadoProducto;
	}

	public JTable getTblMarca() {
		return tblMarca;
	}

	public void setTblMarca(JTable tblMarca) {
		this.tblMarca = tblMarca;
	}

	public JTable getTblProducto() {
		return tblProducto;
	}

	public void setTblProducto(JTable tblProducto) {
		this.tblProducto = tblProducto;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtCodigoMarca() {
		return txtCodigoMarca;
	}

	public void setTxtCodigoMarca(JTextField txtCodigoMarca) {
		this.txtCodigoMarca = txtCodigoMarca;
	}

	public JTextField getTxtCodigoProducto() {
		return txtCodigoProducto;
	}

	public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}

	public JTextField getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JTextField txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JTextField getTxtCreativoProducto() {
		return txtCreativoProducto;
	}

	public void setTxtCreativoProducto(JTextField txtCreativoProducto) {
		this.txtCreativoProducto = txtCreativoProducto;
	}

	public JTextField getTxtEjecutivoProducto() {
		return txtEjecutivoProducto;
	}

	public void setTxtEjecutivoProducto(JTextField txtEjecutivoProducto) {
		this.txtEjecutivoProducto = txtEjecutivoProducto;
	}

	public JTextField getTxtNombreMarca() {
		return txtNombreMarca;
	}

	public void setTxtNombreMarca(JTextField txtNombreMarca) {
		this.txtNombreMarca = txtNombreMarca;
	}

	public JTextField getTxtNombreProducto() {
		return txtNombreProducto;
	}

	public void setTxtNombreProducto(JTextField txtNombreProducto) {
		this.txtNombreProducto = txtNombreProducto;
	}

	public JTextField getTxtMarca() {
		return txtMarca;
	}

	public void setTxtMarca(JTextField txtMarca) {
		this.txtMarca = txtMarca;
	}

	public JPanel getPanelBotonesMarca() {
		return panelBotonesMarca;
	}

	public void setPanelBotonesMarca(JPanel panelBotonesMarca) {
		this.panelBotonesMarca = panelBotonesMarca;
	}

	public JLabel getLblCreativoProducto() {
		return lblCreativoProducto;
	}

	public void setLblCreativoProducto(JLabel lblCreativoProducto) {
		this.lblCreativoProducto = lblCreativoProducto;
	}

	public JLabel getLblEjecutivoProducto() {
		return lblEjecutivoProducto;
	}

	public void setLblEjecutivoProducto(JLabel lblEjecutivoProducto) {
		this.lblEjecutivoProducto = lblEjecutivoProducto;
	}

	public JideTabbedPane getJtpProducto() {
		return jtpProducto;
	}

	public void setJtpProducto(JideTabbedPane jtpProducto) {
		this.jtpProducto = jtpProducto;
	}
	
	
	
}
