package com.spirit.compras.gui.panel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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

public abstract class JPSolicitudCompra extends SpiritModelImpl {
	public JPSolicitudCompra() {
		initComponents();
		setName("Solicitud de Compra");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpSolicitudCompra = new JideTabbedPane();
		spCabecera = new JScrollPane();
		panelCabecera = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblBodega = new JLabel();
		cmbBodega = new JComboBox();
		lblEmpleado = new JLabel();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		txtEmpleado = new JTextField();
		btnEmpleado = new JButton();
		lblFechaEntrega = new JLabel();
		cmbFechaEntrega = new DateComboBox();
		lblTipoReferencia = new JLabel();
		cmbTipoReferencia = new JComboBox();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		btnBuscarReferencia = new JButton();
		lblSolicitud = new JLabel();
		spTxtSolicitud = new JScrollPane();
		txtSolicitud = new JTextArea();
		spDetalle = new JScrollPane();
		panelDetalle = new JPanel();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnProducto = new JButton();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		panelBotones = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnRemoverDetalle = new JButton();
		spTblDetalle = new JScrollPane();
		tblDetalle = new JTable();
		panelArchivos = new JPanel();
		lblTipoArchivo = new JLabel();
		cmbTipoArchivo = new JComboBox();
		lblArchivo = new JLabel();
		txtArchivo = new JTextField();
		btnBuscarArchivo = new JButton();
		panel4 = new JPanel();
		btnAgregarArchivo = new JButton();
		btnActualizarArchivo = new JButton();
		btnEliminarArchivo = new JButton();
		btnVerArchivo = new JButton();
		spTblArchivos = new JScrollPane();
		tblArchivo = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"default:grow"));

		//======== jtpSolicitudCompra ========
		{
			
			//======== spCabecera ========
			{
				
				//======== panelCabecera ========
				{
					panelCabecera.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(55)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(40)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
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
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(90)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(12))
						}));
					
					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelCabecera.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCabecera.add(txtCodigo, cc.xy(5, 3));
					
					//---- lblBodega ----
					lblBodega.setText("Bodega:");
					panelCabecera.add(lblBodega, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCabecera.add(cmbBodega, cc.xywh(5, 5, 7, 1));
					
					//---- lblEmpleado ----
					lblEmpleado.setText("Empleado:");
					panelCabecera.add(lblEmpleado, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblEstado ----
					lblEstado.setText("Estado:");
					panelCabecera.add(lblEstado, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCabecera.add(cmbEstado, cc.xy(5, 15));
					panelCabecera.add(txtEmpleado, cc.xywh(5, 7, 7, 1));
					panelCabecera.add(btnEmpleado, cc.xywh(13, 7, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblFechaEntrega ----
					lblFechaEntrega.setText("Fecha de Entrega:");
					panelCabecera.add(lblFechaEntrega, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCabecera.add(cmbFechaEntrega, cc.xywh(5, 9, 3, 1));
					
					//---- lblTipoReferencia ----
					lblTipoReferencia.setText("Tipo de Referencia:");
					panelCabecera.add(lblTipoReferencia, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- cmbTipoReferencia ----
					cmbTipoReferencia.setModel(new DefaultComboBoxModel(new String[] {
						"PRESUPUESTO",
						"ORDEN DE MEDIOS",
						"NINGUNO"
					}));
					panelCabecera.add(cmbTipoReferencia, cc.xywh(5, 11, 3, 1));
					
					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panelCabecera.add(lblReferencia, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelCabecera.add(txtReferencia, cc.xywh(5, 13, 3, 1));
					panelCabecera.add(btnBuscarReferencia, cc.xywh(9, 13, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblSolicitud ----
					lblSolicitud.setText("Solicitud:");
					panelCabecera.add(lblSolicitud, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== spTxtSolicitud ========
					{
						spTxtSolicitud.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						
						//---- txtSolicitud ----
						txtSolicitud.setAutoscrolls(false);
						txtSolicitud.setLineWrap(true);
						spTxtSolicitud.setViewportView(txtSolicitud);
					}
					panelCabecera.add(spTxtSolicitud, cc.xywh(5, 17, 11, 7));
				}
				spCabecera.setViewportView(panelCabecera);
			}
			jtpSolicitudCompra.addTab("Cabecera", spCabecera);
			
			
			//======== spDetalle ========
			{
				
				//======== panelDetalle ========
				{
					panelDetalle.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(140), FormSpec.DEFAULT_GROW),
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
							new RowSpec(Sizes.dluY(12)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- lblProducto ----
					lblProducto.setText("Producto:");
					panelDetalle.add(lblProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelDetalle.add(txtProducto, cc.xywh(5, 3, 5, 1));
					panelDetalle.add(btnProducto, cc.xywh(11, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblCantidad ----
					lblCantidad.setText("Cantidad:");
					panelDetalle.add(lblCantidad, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtCantidad ----
					txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
					panelDetalle.add(txtCantidad, cc.xy(5, 5));
					
					//======== panelBotones ========
					{
						panelBotones.setLayout(new FormLayout(
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
						panelBotones.add(btnAgregarDetalle, cc.xy(1, 1));
						
						//---- btnActualizarDetalle ----
						btnActualizarDetalle.setText("U");
						panelBotones.add(btnActualizarDetalle, cc.xy(3, 1));
						
						//---- btnRemoverDetalle ----
						btnRemoverDetalle.setText("D");
						panelBotones.add(btnRemoverDetalle, cc.xy(5, 1));
					}
					panelDetalle.add(panelBotones, cc.xywh(3, 9, 11, 1));
					
					//======== spTblDetalle ========
					{
						
						//---- tblDetalle ----
						tblDetalle.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Proveedor", "Producto", "Cantidad"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblDetalle.setViewportView(tblDetalle);
					}
					panelDetalle.add(spTblDetalle, cc.xywh(3, 11, 11, 1));
				}
				spDetalle.setViewportView(panelDetalle);
			}
			jtpSolicitudCompra.addTab("Detalle", spDetalle);
			
			
			//======== panelArchivos ========
			{
				panelArchivos.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
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
						new RowSpec(Sizes.dluY(12))
					}));
				
				//---- lblTipoArchivo ----
				lblTipoArchivo.setText("Tipo Archivo:");
				panelArchivos.add(lblTipoArchivo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panelArchivos.add(cmbTipoArchivo, cc.xy(5, 3));
				
				//---- lblArchivo ----
				lblArchivo.setText("Archivo:");
				panelArchivos.add(lblArchivo, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtArchivo ----
				txtArchivo.setEditable(false);
				panelArchivos.add(txtArchivo, cc.xywh(5, 5, 3, 1));
				panelArchivos.add(btnBuscarArchivo, cc.xywh(9, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//======== panel4 ========
				{
					panel4.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnAgregarArchivo ----
					btnAgregarArchivo.setText("A");
					panel4.add(btnAgregarArchivo, cc.xy(1, 1));
					
					//---- btnActualizarArchivo ----
					btnActualizarArchivo.setText("U");
					panel4.add(btnActualizarArchivo, cc.xy(3, 1));
					
					//---- btnEliminarArchivo ----
					btnEliminarArchivo.setText("D");
					panel4.add(btnEliminarArchivo, cc.xy(5, 1));
				}
				panelArchivos.add(panel4, cc.xywh(3, 9, 3, 1));
				panelArchivos.add(btnVerArchivo, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//======== spTblArchivos ========
				{
					
					//---- tblArchivo ----
					tblArchivo.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null},
						},
						new String[] {
							"Tipo Archivo", "Archivo"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					spTblArchivos.setViewportView(tblArchivo);
				}
				panelArchivos.add(spTblArchivos, cc.xywh(3, 11, 11, 5));
			}
			jtpSolicitudCompra.addTab("Archivos", panelArchivos);
			
		}
		add(jtpSolicitudCompra, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		btnAgregarDetalle.setText("");
		btnActualizarDetalle.setText("");
		btnRemoverDetalle.setText("");
		btnAgregarArchivo.setText("");
		btnActualizarArchivo.setText("");
		btnEliminarArchivo.setText("");
		btnBuscarReferencia.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnEmpleado.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnProducto.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnAgregarDetalle.setToolTipText("Agregar detalle");
		btnAgregarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		btnActualizarDetalle.setToolTipText("Actualizar detalle");
		btnActualizarDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		btnRemoverDetalle.setToolTipText("Eliminar detalle");
		btnRemoverDetalle.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		btnVerArchivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnVerArchivo.setToolTipText("Ver Archivo");
		btnBuscarArchivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/attachFile.png"));
		btnBuscarArchivo.setToolTipText("Buscar Archivo");
		btnAgregarArchivo.setToolTipText("Agregar Archivo");
		btnAgregarArchivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		btnActualizarArchivo.setToolTipText("Actualizar Archivo");
		btnActualizarArchivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		btnEliminarArchivo.setToolTipText("Eliminar Archivo");
		btnEliminarArchivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpSolicitudCompra;
	private JScrollPane spCabecera;
	private JPanel panelCabecera;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblBodega;
	private JComboBox cmbBodega;
	private JLabel lblEmpleado;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JTextField txtEmpleado;
	private JButton btnEmpleado;
	private JLabel lblFechaEntrega;
	private DateComboBox cmbFechaEntrega;
	private JLabel lblTipoReferencia;
	private JComboBox cmbTipoReferencia;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JButton btnBuscarReferencia;
	private JLabel lblSolicitud;
	private JScrollPane spTxtSolicitud;
	private JTextArea txtSolicitud;
	private JScrollPane spDetalle;
	private JPanel panelDetalle;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnProducto;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JPanel panelBotones;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnRemoverDetalle;
	private JScrollPane spTblDetalle;
	private JTable tblDetalle;
	private JPanel panelArchivos;
	private JLabel lblTipoArchivo;
	private JComboBox cmbTipoArchivo;
	private JLabel lblArchivo;
	private JTextField txtArchivo;
	private JButton btnBuscarArchivo;
	private JPanel panel4;
	private JButton btnAgregarArchivo;
	private JButton btnActualizarArchivo;
	private JButton btnEliminarArchivo;
	private JButton btnVerArchivo;
	private JScrollPane spTblArchivos;
	private JTable tblArchivo;
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

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}

	public JButton getBtnRemoverDetalle() {
		return btnRemoverDetalle;
	}

	public void setBtnRemoverDetalle(JButton btnRemoverDetalle) {
		this.btnRemoverDetalle = btnRemoverDetalle;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public void setCmbBodega(JComboBox cmbBodega) {
		this.cmbBodega = cmbBodega;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public DateComboBox getCmbFechaEntrega() {
		return cmbFechaEntrega;
	}

	public void setCmbFechaEntrega(DateComboBox cmbFechaEntrega) {
		this.cmbFechaEntrega = cmbFechaEntrega;
	}

	public JideTabbedPane getJtpSolicitudCompra() {
		return jtpSolicitudCompra;
	}

	public void setJtpSolicitudCompra(JideTabbedPane jtpSolicitudCompra) {
		this.jtpSolicitudCompra = jtpSolicitudCompra;
	}

	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public void setPanelBotones(JPanel panelBotones) {
		this.panelBotones = panelBotones;
	}

	public JPanel getPanelCabecera() {
		return panelCabecera;
	}

	public void setPanelCabecera(JPanel panelCabecera) {
		this.panelCabecera = panelCabecera;
	}

	public JPanel getPanelDetalle() {
		return panelDetalle;
	}

	public void setPanelDetalle(JPanel panelDetalle) {
		this.panelDetalle = panelDetalle;
	}

	public JScrollPane getSpCabecera() {
		return spCabecera;
	}

	public void setSpCabecera(JScrollPane spCabecera) {
		this.spCabecera = spCabecera;
	}

	public JScrollPane getSpDetalle() {
		return spDetalle;
	}

	public void setSpDetalle(JScrollPane spDetalle) {
		this.spDetalle = spDetalle;
	}

	public JScrollPane getSpTblDetalle() {
		return spTblDetalle;
	}

	public void setSpTblDetalle(JScrollPane spTblDetalle) {
		this.spTblDetalle = spTblDetalle;
	}

	public JTable getTblDetalle() {
		return tblDetalle;
	}

	public void setTblDetalle(JTable tblDetalle) {
		this.tblDetalle = tblDetalle;
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

	public JButton getBtnEmpleado() {
		return btnEmpleado;
	}

	public void setBtnEmpleado(JButton btnEmpleado) {
		this.btnEmpleado = btnEmpleado;
	}

	public JTextField getTxtEmpleado() {
		return txtEmpleado;
	}

	public void setTxtEmpleado(JTextField txtEmpleado) {
		this.txtEmpleado = txtEmpleado;
	}

	public JComboBox getCmbTipoReferencia() {
		return cmbTipoReferencia;
	}

	public void setCmbTipoReferencia(JComboBox cmbTipoReferencia) {
		this.cmbTipoReferencia = cmbTipoReferencia;
	}

	public JButton getBtnBuscarReferencia() {
		return btnBuscarReferencia;
	}

	public void setBtnBuscarReferencia(JButton btnBuscarReferencia) {
		this.btnBuscarReferencia = btnBuscarReferencia;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextArea getTxtSolicitud() {
		return txtSolicitud;
	}

	public void setTxtSolicitud(JTextArea txtSolicitud) {
		this.txtSolicitud = txtSolicitud;
	}

	public JButton getBtnActualizarArchivo() {
		return btnActualizarArchivo;
	}

	public void setBtnActualizarArchivo(JButton btnActualizarArchivo) {
		this.btnActualizarArchivo = btnActualizarArchivo;
	}

	public JButton getBtnAgregarArchivo() {
		return btnAgregarArchivo;
	}

	public void setBtnAgregarArchivo(JButton btnAgregarArchivo) {
		this.btnAgregarArchivo = btnAgregarArchivo;
	}

	public JButton getBtnBuscarArchivo() {
		return btnBuscarArchivo;
	}

	public void setBtnBuscarArchivo(JButton btnBuscarArchivo) {
		this.btnBuscarArchivo = btnBuscarArchivo;
	}

	public JButton getBtnEliminarArchivo() {
		return btnEliminarArchivo;
	}

	public void setBtnEliminarArchivo(JButton btnEliminarArchivo) {
		this.btnEliminarArchivo = btnEliminarArchivo;
	}

	public JButton getBtnVerArchivo() {
		return btnVerArchivo;
	}

	public void setBtnVerArchivo(JButton btnVerArchivo) {
		this.btnVerArchivo = btnVerArchivo;
	}

	public JComboBox getCmbTipoArchivo() {
		return cmbTipoArchivo;
	}

	public void setCmbTipoArchivo(JComboBox cmbTipoArchivo) {
		this.cmbTipoArchivo = cmbTipoArchivo;
	}

	public JTextField getTxtArchivo() {
		return txtArchivo;
	}

	public void setTxtArchivo(JTextField txtArchivo) {
		this.txtArchivo = txtArchivo;
	}

	public JTable getTblArchivo() {
		return tblArchivo;
	}

	public void setTblArchivo(JTable tblArchivo) {
		this.tblArchivo = tblArchivo;
	}
}
