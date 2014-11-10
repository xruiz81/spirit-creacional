package com.spirit.facturacion.gui.panel;

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
import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPListaPrecio extends SpiritModelImpl {
	
	public JPListaPrecio() {
		initComponents();
		setName("Lista de Precios");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpListaPrecio = new JideTabbedPane();
		spListaPrecio = new JScrollPane();
		panelListaPrecio = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblFechaInicio = new JLabel();
		cmbFechaInicio = new DateComboBox();
		lblFechaFin = new JLabel();
		cmbFechaFin = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstadoLista = new JComboBox();
		spTblListaPrecio = new JScrollPane();
		tblListaPrecio = new JTable();
		spPrecios = new JScrollPane();
		panelPrecios = new JPanel();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		lblProveedor = new JLabel();
		txtProveedor = new JTextField();
		lblPrecio = new JLabel();
		txtPrecio = new JTextField();
		cmbCambiarPrecio = new JComboBox();
		lblCambiarPrecio = new JLabel();
		lblEstadoPrecio = new JLabel();
		cmbEstadoPrecio = new JComboBox();
		panel1 = new JPanel();
		btnAgregarPrecio = new JButton();
		btnActualizarPrecio = new JButton();
		btnRemoverPrecio = new JButton();
		spTblPrecio = new JScrollPane();
		tblPrecio = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"default:grow"));

		//======== jtpListaPrecio ========
		{
			
			//======== spListaPrecio ========
			{
				
				//======== panelListaPrecio ========
				{
					panelListaPrecio.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.dluY(10), FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelListaPrecio.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelListaPrecio.add(txtCodigo, cc.xy(5, 3));
					
					//---- lblNombre ----
					lblNombre.setText("Nombre:");
					panelListaPrecio.add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelListaPrecio.add(txtNombre, cc.xywh(5, 5, 15, 1));
					
					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panelListaPrecio.add(lblReferencia, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelListaPrecio.add(txtReferencia, cc.xywh(5, 7, 15, 1));
					
					//---- lblFechaInicio ----
					lblFechaInicio.setText("Fecha Inicio:");
					panelListaPrecio.add(lblFechaInicio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelListaPrecio.add(cmbFechaInicio, cc.xywh(5, 9, 5, 1));
					
					//---- lblFechaFin ----
					lblFechaFin.setText("Fecha Fin:");
					panelListaPrecio.add(lblFechaFin, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelListaPrecio.add(cmbFechaFin, cc.xywh(15, 9, 5, 1));
					
					//---- lblEstado ----
					lblEstado.setText("Estado:");
					panelListaPrecio.add(lblEstado, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelListaPrecio.add(cmbEstadoLista, cc.xywh(5, 11, 3, 1));
					
					//======== spTblListaPrecio ========
					{
						
						//---- tblListaPrecio ----
						tblListaPrecio.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null},
							},
							new String[] {
								"C\u00f3digo", "Nombre", "Referencia", "Fecha Inicio", "Fecha Fin", "Estado"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblListaPrecio.setViewportView(tblListaPrecio);
					}
					panelListaPrecio.add(spTblListaPrecio, cc.xywh(3, 15, 19, 1));
				}
				spListaPrecio.setViewportView(panelListaPrecio);
			}
			jtpListaPrecio.addTab("Listas de Precios", spListaPrecio);
			
			
			//======== spPrecios ========
			{
				
				//======== panelPrecios ========
				{
					panelPrecios.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(80)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(35)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(35)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(RowSpec.TOP, Sizes.dluY(12), FormSpec.NO_GROW),
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.dluY(10), FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- lblProducto ----
					lblProducto.setText("Producto:");
					panelPrecios.add(lblProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPrecios.add(txtProducto, cc.xywh(5, 3, 9, 1));
					panelPrecios.add(btnProducto, cc.xywh(15, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblProveedor ----
					lblProveedor.setText("Proveedor:");
					panelPrecios.add(lblProveedor, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPrecios.add(txtProveedor, cc.xywh(5, 5, 9, 1));
					
					//---- lblPrecio ----
					lblPrecio.setText("Precio:");
					panelPrecios.add(lblPrecio, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPrecios.add(txtPrecio, cc.xy(5, 7));
					panelPrecios.add(cmbCambiarPrecio, cc.xy(11, 7));
					
					//---- lblCambiarPrecio ----
					lblCambiarPrecio.setText("Cambiar Precio:");
					panelPrecios.add(lblCambiarPrecio, cc.xywh(9, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblEstadoPrecio ----
					lblEstadoPrecio.setText("Estado:");
					panelPrecios.add(lblEstadoPrecio, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelPrecios.add(cmbEstadoPrecio, cc.xy(5, 9));
					
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
						
						//---- btnAgregarPrecio ----
						panel1.add(btnAgregarPrecio, cc.xy(1, 1));
						
						//---- btnActualizarPrecio ----
						panel1.add(btnActualizarPrecio, cc.xy(3, 1));
						
						//---- btnRemoverPrecio ----
						panel1.add(btnRemoverPrecio, cc.xy(5, 1));
					}
					panelPrecios.add(panel1, cc.xywh(3, 11, 15, 1));
					
					//======== spTblPrecio ========
					{
						
						//---- tblPrecio ----
						tblPrecio.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null},
							},
							new String[] {
								"Producto", "Proveedor", "Precio", "Cambiar P.", "Estado"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblPrecio.setViewportView(tblPrecio);
					}
					panelPrecios.add(spTblPrecio, cc.xywh(3, 13, 15, 1));
				}
				spPrecios.setViewportView(panelPrecios);
			}
			jtpListaPrecio.addTab("Precios", spPrecios);
			
		}
		add(jtpListaPrecio, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnProducto.setToolTipText("Buscar Producto");
		btnProducto.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		
		btnAgregarPrecio.setToolTipText("Agregar Precio");
		btnAgregarPrecio.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		btnActualizarPrecio.setToolTipText("Actualizar Precio");
		btnActualizarPrecio.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		btnRemoverPrecio.setToolTipText("Eliminar Precio");
		btnRemoverPrecio.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpListaPrecio;
	private JScrollPane spListaPrecio;
	private JPanel panelListaPrecio;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblFechaInicio;
	private DateComboBox cmbFechaInicio;
	private JLabel lblFechaFin;
	private DateComboBox cmbFechaFin;
	private JLabel lblEstado;
	private JComboBox cmbEstadoLista;
	private JScrollPane spTblListaPrecio;
	private JTable tblListaPrecio;
	private JScrollPane spPrecios;
	private JPanel panelPrecios;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JComboBox cmbCambiarPrecio;
	private JLabel lblCambiarPrecio;
	private JLabel lblEstadoPrecio;
	private JComboBox cmbEstadoPrecio;
	private JPanel panel1;
	private JButton btnAgregarPrecio;
	private JButton btnActualizarPrecio;
	private JButton btnRemoverPrecio;
	private JScrollPane spTblPrecio;
	private JTable tblPrecio;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}

	public JComboBox getCmbCambiarPrecio() {
		return cmbCambiarPrecio;
	}

	public void setCmbCambiarPrecio(JComboBox cmbCambiarPrecio) {
		this.cmbCambiarPrecio = cmbCambiarPrecio;
	}

	public JComboBox getCmbEstadoLista() {
		return cmbEstadoLista;
	}

	public void setCmbEstadoLista(JComboBox cmbEstadoLista) {
		this.cmbEstadoLista = cmbEstadoLista;
	}

	public JComboBox getCmbEstadoPrecio() {
		return cmbEstadoPrecio;
	}

	public void setCmbEstadoPrecio(JComboBox cmbEstadoPrecio) {
		this.cmbEstadoPrecio = cmbEstadoPrecio;
	}

	public DateComboBox getCmbFechaFin() {
		return cmbFechaFin;
	}

	public void setCmbFechaFin(DateComboBox cmbFechaFin) {
		this.cmbFechaFin = cmbFechaFin;
	}

	public DateComboBox getCmbFechaInicio() {
		return cmbFechaInicio;
	}

	public void setCmbFechaInicio(DateComboBox cmbFechaInicio) {
		this.cmbFechaInicio = cmbFechaInicio;
	}

	public JideTabbedPane getJtpListaPrecio() {
		return jtpListaPrecio;
	}

	public void setJtpListaPrecio(JideTabbedPane jtpListaPrecio) {
		this.jtpListaPrecio = jtpListaPrecio;
	}

	public JPanel getPanelListaPrecio() {
		return panelListaPrecio;
	}

	public void setPanelListaPrecio(JPanel panelListaPrecio) {
		this.panelListaPrecio = panelListaPrecio;
	}

	public JPanel getPanelPrecios() {
		return panelPrecios;
	}

	public void setPanelPrecios(JPanel panelPrecios) {
		this.panelPrecios = panelPrecios;
	}

	public JScrollPane getSpListaPrecio() {
		return spListaPrecio;
	}

	public void setSpListaPrecio(JScrollPane spListaPrecio) {
		this.spListaPrecio = spListaPrecio;
	}

	public JScrollPane getSpPrecios() {
		return spPrecios;
	}

	public void setSpPrecios(JScrollPane spPrecios) {
		this.spPrecios = spPrecios;
	}

	public JScrollPane getSpTblListaPrecio() {
		return spTblListaPrecio;
	}

	public void setSpTblListaPrecio(JScrollPane spTblListaPrecio) {
		this.spTblListaPrecio = spTblListaPrecio;
	}

	public JScrollPane getSpTblPrecio() {
		return spTblPrecio;
	}

	public void setSpTblPrecio(JScrollPane spTblPrecio) {
		this.spTblPrecio = spTblPrecio;
	}

	public JTable getTblListaPrecio() {
		return tblListaPrecio;
	}

	public void setTblListaPrecio(JTable tblListaPrecio) {
		this.tblListaPrecio = tblListaPrecio;
	}

	public JTable getTblPrecio() {
		return tblPrecio;
	}

	public void setTblPrecio(JTable tblPrecio) {
		this.tblPrecio = tblPrecio;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtPrecio() {
		return txtPrecio;
	}

	public void setTxtPrecio(JTextField txtPrecio) {
		this.txtPrecio = txtPrecio;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JButton getBtnActualizarPrecio() {
		return btnActualizarPrecio;
	}

	public void setBtnActualizarPrecio(JButton btnActualizarPrecio) {
		this.btnActualizarPrecio = btnActualizarPrecio;
	}

	public JButton getBtnAgregarPrecio() {
		return btnAgregarPrecio;
	}

	public void setBtnAgregarPrecio(JButton btnAgregarPrecio) {
		this.btnAgregarPrecio = btnAgregarPrecio;
	}

	public JButton getBtnRemoverPrecio() {
		return btnRemoverPrecio;
	}

	public void setBtnRemoverPrecio(JButton btnRemoverPrecio) {
		this.btnRemoverPrecio = btnRemoverPrecio;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}
}
