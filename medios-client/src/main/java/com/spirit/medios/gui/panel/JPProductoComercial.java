package com.spirit.medios.gui.panel;

import java.awt.Dimension;

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
import com.spirit.client.model.SpiritResourceManager;

public abstract class JPProductoComercial extends SpiritModelImpl {
	
	public JPProductoComercial() {
		initComponents();
		setName("Producto-Comercial");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panelProductoComercial = new JPanel();
		panelCliente = new JPanel();
		lblCorporacion = new JLabel();
		txtCorporacion = new JTextField();
		btnBuscarCorporacion = new JButton();
		lblCliente = new JLabel();
		txtCliente = new JTextField();
		btnBuscarCliente = new JButton();
		jtpProductoComercial = new JideTabbedPane();
		panelProductoCliente = new JPanel();
		txtCodigoProducto = new JTextField();
		lblEstadoProducto = new JLabel();
		cmbEstadoProducto = new JComboBox();
		txtNombreProducto = new JTextField();
		btnBuscarCreativo = new JButton();
		btnBorrarCreativo = new JButton();
		txtCreativoProducto = new JTextField();
		btnBuscarEjecutivo = new JButton();
		btnBorrarEjecutivo = new JButton();
		txtEjecutivoProducto = new JTextField();
		spProductoClente = new JScrollPane();
		tblProductoCliente = new JTable();
		lblCodigoProducto = new JLabel();
		lblNombreProducto = new JLabel();
		lblCreativoProducto = new JLabel();
		lblEjecutivoProducto = new JLabel();
		panel1 = new JPanel();
		btnAgregarProductoCliente = new JButton();
		btnActualizarProductoCliente = new JButton();
		btnEliminarProductoCliente = new JButton();
		panelComercialCliente = new JPanel();
		txtCodigoComercial = new JTextField();
		cmbDerechoProgramaComercial = new JComboBox();
		txtNombreComercial = new JTextField();
		txtVersionComercial = new JTextField();
		lblDuracionComercial = new JLabel();
		txtDuracionComercial = new JTextField();
		lblSeg = new JLabel();
		spComercialClente = new JScrollPane();
		tblComercialCliente = new JTable();
		lblCodigoComercial = new JLabel();
		lblDerechoProgramaComercial = new JLabel();
		lblCampanaComercial = new JLabel();
		lblNombreComercial = new JLabel();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		txtCampanaComercial = new JTextField();
		btnBuscarCampanaComercial = new JButton();
		lblDescripcionComercial = new JLabel();
		txtDescripcionComercial = new JTextField();
		lblVersionComercial = new JLabel();
		panel2 = new JPanel();
		btnAgregarComercialCliente = new JButton();
		btnActualizarComercialCliente = new JButton();
		btnEliminarComercialCliente = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panelProductoComercial ========
		{
			panelProductoComercial.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(10)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(10))
				},
				new RowSpec[] {
					new RowSpec(Sizes.DLUY7),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY6),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY5)
				}));
			
			//======== panelCliente ========
			{
				panelCliente.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec("left:max(default;40dlu)"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(12)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.PREF_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(pref;50dlu):grow"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				((FormLayout)panelCliente.getLayout()).setColumnGroups(new int[][] {{3, 11}});
				
				//---- lblCorporacion ----
				lblCorporacion.setText("Corporaci\u00f3n:");
				panelCliente.add(lblCorporacion, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCorporacion ----
				txtCorporacion.setEditable(false);
				panelCliente.add(txtCorporacion, cc.xy(3, 1));
				panelCliente.add(btnBuscarCorporacion, cc.xywh(5, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				panelCliente.add(lblCliente, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCliente ----
				txtCliente.setEditable(true);
				panelCliente.add(txtCliente, cc.xy(11, 1));
				panelCliente.add(btnBuscarCliente, cc.xywh(13, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			}
			panelProductoComercial.add(panelCliente, cc.xy(3, 3));
			
			//======== jtpProductoComercial ========
			{
				jtpProductoComercial.setTabResizeMode(com.jidesoft.swing.JideTabbedPane.RESIZE_MODE_NONE);
				
				//======== panelProductoCliente ========
				{
					panelProductoCliente.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(140), FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.PREF_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
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
							new RowSpec(Sizes.DLUY6),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY3)
						}));
					panelProductoCliente.add(txtCodigoProducto, cc.xy(5, 3));
					
					//---- lblEstadoProducto ----
					lblEstadoProducto.setText("Estado:");
					panelProductoCliente.add(lblEstadoProducto, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProductoCliente.add(cmbEstadoProducto, cc.xy(17, 3));
					panelProductoCliente.add(txtNombreProducto, cc.xywh(5, 5, 3, 1));
					panelProductoCliente.add(btnBuscarCreativo, cc.xywh(9, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelProductoCliente.add(btnBorrarCreativo, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelProductoCliente.add(txtCreativoProducto, cc.xywh(5, 7, 3, 1));
					panelProductoCliente.add(btnBuscarEjecutivo, cc.xywh(9, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelProductoCliente.add(btnBorrarEjecutivo, cc.xywh(11, 9, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					panelProductoCliente.add(txtEjecutivoProducto, cc.xywh(5, 9, 3, 1));
					
					//======== spProductoClente ========
					{
						spProductoClente.setPreferredSize(new Dimension(452, 100));
						
						//---- tblProductoCliente ----
						tblProductoCliente.setModel(new DefaultTableModel(
							new Object[][] {
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
						tblProductoCliente.setPreferredScrollableViewportSize(new Dimension(450, 300));
						tblProductoCliente.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
						tblProductoCliente.setAutoCreateColumnsFromModel(true);
						spProductoClente.setViewportView(tblProductoCliente);
					}
					panelProductoCliente.add(spProductoClente, cc.xywh(3, 15, 17, 3));
					
					//---- lblCodigoProducto ----
					lblCodigoProducto.setText("C\u00f3digo:");
					panelProductoCliente.add(lblCodigoProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblNombreProducto ----
					lblNombreProducto.setText("Nombre:");
					panelProductoCliente.add(lblNombreProducto, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblCreativoProducto ----
					lblCreativoProducto.setText("Creativo:");
					panelProductoCliente.add(lblCreativoProducto, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblEjecutivoProducto ----
					lblEjecutivoProducto.setText("Ejecutivo:");
					panelProductoCliente.add(lblEjecutivoProducto, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
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
						
						//---- btnAgregarProductoCliente ----
						panel1.add(btnAgregarProductoCliente, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						
						//---- btnActualizarProductoCliente ----
						panel1.add(btnActualizarProductoCliente, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						
						//---- btnEliminarProductoCliente ----
						panel1.add(btnEliminarProductoCliente, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					}
					panelProductoCliente.add(panel1, cc.xywh(3, 13, 17, 1));
				}
				jtpProductoComercial.addTab("Producto Cliente", panelProductoCliente);
				
				
				//======== panelComercialCliente ========
				{
					panelComercialCliente.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(60), FormSpec.NO_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;50dlu):grow"),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.LEFT, Sizes.DEFAULT, FormSpec.NO_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
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
							new RowSpec(Sizes.DLUY4),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY3)
						}));
					panelComercialCliente.add(txtCodigoComercial, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
					panelComercialCliente.add(cmbDerechoProgramaComercial, cc.xywh(11, 3, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelComercialCliente.add(txtNombreComercial, cc.xywh(5, 5, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelComercialCliente.add(txtVersionComercial, cc.xywh(5, 11, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblDuracionComercial ----
					lblDuracionComercial.setText("Duraci\u00f3n:");
					panelComercialCliente.add(lblDuracionComercial, cc.xywh(17, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelComercialCliente.add(txtDuracionComercial, cc.xywh(19, 11, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblSeg ----
					lblSeg.setText("(seg)");
					panelComercialCliente.add(lblSeg, cc.xy(21, 11));
					
					//======== spComercialClente ========
					{
						spComercialClente.setPreferredSize(new Dimension(452, 100));
						
						//---- tblComercialCliente ----
						tblComercialCliente.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"C\u00f3digo", "Nombre", "Derecho Programa", "Descripci\u00f3n", "Campa\u00f1a", "Versi\u00f3n", "Duraci\u00f3n"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblComercialCliente.setPreferredScrollableViewportSize(new Dimension(450, 100));
						tblComercialCliente.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
						tblComercialCliente.setAutoCreateColumnsFromModel(true);
						spComercialClente.setViewportView(tblComercialCliente);
					}
					panelComercialCliente.add(spComercialClente, cc.xywh(3, 17, 21, 3));
					
					//---- lblCodigoComercial ----
					lblCodigoComercial.setText("C\u00f3digo:");
					panelComercialCliente.add(lblCodigoComercial, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblDerechoProgramaComercial ----
					lblDerechoProgramaComercial.setText("Derecho Programa:");
					panelComercialCliente.add(lblDerechoProgramaComercial, cc.xywh(9, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblCampanaComercial ----
					lblCampanaComercial.setText("Campa\u00f1a:");
					panelComercialCliente.add(lblCampanaComercial, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblNombreComercial ----
					lblNombreComercial.setText("Nombre:");
					panelComercialCliente.add(lblNombreComercial, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblEstado ----
					lblEstado.setText("Estado:");
					panelComercialCliente.add(lblEstado, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelComercialCliente.add(cmbEstado, cc.xy(19, 5));
					panelComercialCliente.add(txtCampanaComercial, cc.xywh(5, 7, 7, 1));
					panelComercialCliente.add(btnBuscarCampanaComercial, cc.xywh(13, 7, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));
					
					//---- lblDescripcionComercial ----
					lblDescripcionComercial.setText("Descripci\u00f3n:");
					panelComercialCliente.add(lblDescripcionComercial, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelComercialCliente.add(txtDescripcionComercial, cc.xywh(5, 9, 15, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblVersionComercial ----
					lblVersionComercial.setText("Versi\u00f3n:");
					panelComercialCliente.add(lblVersionComercial, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
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
						
						//---- btnAgregarComercialCliente ----
						panel2.add(btnAgregarComercialCliente, cc.xy(1, 1));
						
						//---- btnActualizarComercialCliente ----
						panel2.add(btnActualizarComercialCliente, cc.xy(3, 1));
						
						//---- btnEliminarComercialCliente ----
						panel2.add(btnEliminarComercialCliente, cc.xy(5, 1));
					}
					panelComercialCliente.add(panel2, cc.xywh(3, 15, 21, 1));
				}
				jtpProductoComercial.addTab("Comercial Cliente", panelComercialCliente);
				
			}
			panelProductoComercial.add(jtpProductoComercial, cc.xy(3, 7));
		}
		add(panelProductoComercial, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		//---- btnBuscarCorporacion ----
		btnBuscarCorporacion.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCorporacion.setToolTipText("Buscar Corporacion");
		
		//---- btnBuscarCliente ----
		btnBuscarCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCliente.setToolTipText("Buscar Cliente");
		
		//---- btnBuscarCreativo ----
		btnBuscarCreativo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCreativo.setToolTipText("Buscar Creativo");
		
		//---- btnBorrarCreativo
		btnBorrarCreativo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		btnBorrarCreativo.setToolTipText("Borro el Creativo seleccionado");
		
		//---- btnBuscarEjecutivo ----
		btnBuscarEjecutivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarEjecutivo.setToolTipText("Buscar Ejecutivo");
		
		//---- btnBorrarEjecutivo
		btnBorrarEjecutivo.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		btnBorrarEjecutivo.setToolTipText("Borro el Ejecutivo seleccionado");
		
		//---- btnAgregarProductoCliente ----
		btnAgregarProductoCliente.setToolTipText("Agregar Producto");
		btnAgregarProductoCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		
		//---- btnActualizarProductoCliente ----
		btnActualizarProductoCliente.setToolTipText("Actualizar Producto");
		btnActualizarProductoCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		//---- btnEliminarProductoCliente ----
		btnEliminarProductoCliente.setToolTipText("Eliminar Producto");
		btnEliminarProductoCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		//---- btnBuscarCampana ----
		btnBuscarCampanaComercial.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		btnBuscarCampanaComercial.setToolTipText("Buscar Campaña");
		
		//---- btnAgregarComercial ----
		btnAgregarComercialCliente.setToolTipText("Agregar Comercial");
		btnAgregarComercialCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		
		//---- btnActualizarComercial ----
		btnActualizarComercialCliente.setToolTipText("Actualizar Comercial");
		btnActualizarComercialCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		
		//---- btnEliminarComercial ----
		btnEliminarComercialCliente.setToolTipText("Eliminar Comercial");
		btnEliminarComercialCliente.setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		
		txtCorporacion.setEditable(false);
		txtCliente.setEditable(false);
		txtCreativoProducto.setEditable(false);
		txtEjecutivoProducto.setEditable(false);
		txtCampanaComercial.setEditable(false);
		
		
		//Tabla Producto Cliente
		getTblProductoCliente().getColumnModel().getColumn(0).setMinWidth(20);
		//getTblProductoCliente().getColumnModel().getColumn(0).setMaxWidth(50);
		getTblProductoCliente().getColumnModel().getColumn(1).setMinWidth(140);
		//getTblProductoCliente().getColumnModel().getColumn(1).setMaxWidth(280);
		getTblProductoCliente().getColumnModel().getColumn(2).setMinWidth(20);
		//getTblProductoCliente().getColumnModel().getColumn(2).setMaxWidth(80);
		getTblProductoCliente().getColumnModel().getColumn(3).setMinWidth(140);
		//getTblProductoCliente().getColumnModel().getColumn(3).setMaxWidth(280);
		getTblProductoCliente().getColumnModel().getColumn(4).setMinWidth(140);
		//getTblProductoCliente().getColumnModel().getColumn(4).setMaxWidth(280);
		
		//Tabla Comercial Cliente
		getTblComercialCliente().getColumnModel().getColumn(0).setMinWidth(20);
		//getTblComercialCliente().getColumnModel().getColumn(0).setMaxWidth(50);
		getTblComercialCliente().getColumnModel().getColumn(1).setMinWidth(130);
		//getTblComercialCliente().getColumnModel().getColumn(1).setMaxWidth(200);
		getTblComercialCliente().getColumnModel().getColumn(2).setMinWidth(130);
		//getTblComercialCliente().getColumnModel().getColumn(2).setMaxWidth(200);
		getTblComercialCliente().getColumnModel().getColumn(3).setMinWidth(130);
		//getTblComercialCliente().getColumnModel().getColumn(3).setMaxWidth(200);
		getTblComercialCliente().getColumnModel().getColumn(4).setMinWidth(130);
		//getTblComercialCliente().getColumnModel().getColumn(4).setMaxWidth(190);
		getTblComercialCliente().getColumnModel().getColumn(5).setMinWidth(20);
		//getTblComercialCliente().getColumnModel().getColumn(5).setMaxWidth(60);
		getTblComercialCliente().getColumnModel().getColumn(6).setMinWidth(30);
		//getTblComercialCliente().getColumnModel().getColumn(6).setMaxWidth(65);	
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panelProductoComercial;
	private JPanel panelCliente;
	private JLabel lblCorporacion;
	private JTextField txtCorporacion;
	private JButton btnBuscarCorporacion;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JButton btnBuscarCliente;
	private JideTabbedPane jtpProductoComercial;
	private JPanel panelProductoCliente;
	private JTextField txtCodigoProducto;
	private JLabel lblEstadoProducto;
	private JComboBox cmbEstadoProducto;
	private JTextField txtNombreProducto;
	private JButton btnBuscarCreativo;
	private JButton btnBorrarCreativo;
	private JTextField txtCreativoProducto;
	private JButton btnBuscarEjecutivo;
	private JButton btnBorrarEjecutivo;
	private JTextField txtEjecutivoProducto;
	private JScrollPane spProductoClente;
	private JTable tblProductoCliente;
	private JLabel lblCodigoProducto;
	private JLabel lblNombreProducto;
	private JLabel lblCreativoProducto;
	private JLabel lblEjecutivoProducto;
	private JPanel panel1;
	private JButton btnAgregarProductoCliente;
	private JButton btnActualizarProductoCliente;
	private JButton btnEliminarProductoCliente;
	private JPanel panelComercialCliente;
	private JTextField txtCodigoComercial;
	private JComboBox cmbDerechoProgramaComercial;
	private JTextField txtNombreComercial;
	private JTextField txtVersionComercial;
	private JLabel lblDuracionComercial;
	private JTextField txtDuracionComercial;
	private JLabel lblSeg;
	private JScrollPane spComercialClente;
	private JTable tblComercialCliente;
	private JLabel lblCodigoComercial;
	private JLabel lblDerechoProgramaComercial;
	private JLabel lblCampanaComercial;
	private JLabel lblNombreComercial;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JTextField txtCampanaComercial;
	private JButton btnBuscarCampanaComercial;
	private JLabel lblDescripcionComercial;
	private JTextField txtDescripcionComercial;
	private JLabel lblVersionComercial;
	private JPanel panel2;
	private JButton btnAgregarComercialCliente;
	private JButton btnActualizarComercialCliente;
	private JButton btnEliminarComercialCliente;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnActualizarComercialCliente() {
		return btnActualizarComercialCliente;
	}


	public void setBtnActualizarComercialCliente(
			JButton btnActualizarComercialCliente) {
		this.btnActualizarComercialCliente = btnActualizarComercialCliente;
	}


	public JButton getBtnActualizarProductoCliente() {
		return btnActualizarProductoCliente;
	}


	public void setBtnActualizarProductoCliente(JButton btnActualizarProductoCliente) {
		this.btnActualizarProductoCliente = btnActualizarProductoCliente;
	}


	public JButton getBtnAgregarComercialCliente() {
		return btnAgregarComercialCliente;
	}


	public void setBtnAgregarComercialCliente(JButton btnAgregarComercialCliente) {
		this.btnAgregarComercialCliente = btnAgregarComercialCliente;
	}


	public JButton getBtnAgregarProductoCliente() {
		return btnAgregarProductoCliente;
	}


	public void setBtnAgregarProductoCliente(JButton btnAgregarProductoCliente) {
		this.btnAgregarProductoCliente = btnAgregarProductoCliente;
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


	public JButton getBtnBuscarCampana() {
		return btnBuscarCampanaComercial;
	}


	public void setBtnBuscarCampana(JButton btnBuscarCampanaComercial) {
		this.btnBuscarCampanaComercial = btnBuscarCampanaComercial;
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


	public JComboBox getCmbDerechoProgramaComercial() {
		return cmbDerechoProgramaComercial;
	}


	public void setCmbDerechoProgramaComercial(JComboBox cmbDerechoProgramaComercial) {
		this.cmbDerechoProgramaComercial = cmbDerechoProgramaComercial;
	}


	public JComboBox getCmbEstadoProducto() {
		return cmbEstadoProducto;
	}


	public void setCmbEstadoProducto(JComboBox cmbEstadoProducto) {
		this.cmbEstadoProducto = cmbEstadoProducto;
	}


	public JTable getTblComercialCliente() {
		return tblComercialCliente;
	}


	public void setTblComercialCliente(JTable tblComercialCliente) {
		this.tblComercialCliente = tblComercialCliente;
	}


	public JTable getTblProductoCliente() {
		return tblProductoCliente;
	}


	public void setTblProductoCliente(JTable tblProductoCliente) {
		this.tblProductoCliente = tblProductoCliente;
	}


	public JTextField getTxtCampanaComercial() {
		return txtCampanaComercial;
	}


	public void setTxtCampanaComercial(JTextField txtCampanaComercial) {
		this.txtCampanaComercial = txtCampanaComercial;
	}


	public JTextField getTxtCliente() {
		return txtCliente;
	}


	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}


	public JTextField getTxtCodigoComercial() {
		return txtCodigoComercial;
	}


	public void setTxtCodigoComercial(JTextField txtCodigoComercial) {
		this.txtCodigoComercial = txtCodigoComercial;
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


	public JTextField getTxtDescripcionComercial() {
		return txtDescripcionComercial;
	}


	public void setTxtDescripcionComercial(JTextField txtDescripcionComercial) {
		this.txtDescripcionComercial = txtDescripcionComercial;
	}


	public JTextField getTxtDuracionComercial() {
		return txtDuracionComercial;
	}


	public void setTxtDuracionComercial(JTextField txtDuracionComercial) {
		this.txtDuracionComercial = txtDuracionComercial;
	}


	public JTextField getTxtEjecutivoProducto() {
		return txtEjecutivoProducto;
	}


	public void setTxtEjecutivoProducto(JTextField txtEjecutivoProducto) {
		this.txtEjecutivoProducto = txtEjecutivoProducto;
	}


	public JTextField getTxtNombreComercial() {
		return txtNombreComercial;
	}


	public void setTxtNombreComercial(JTextField txtNombreComercial) {
		this.txtNombreComercial = txtNombreComercial;
	}


	public JTextField getTxtNombreProducto() {
		return txtNombreProducto;
	}


	public void setTxtNombreProducto(JTextField txtNombreProducto) {
		this.txtNombreProducto = txtNombreProducto;
	}


	public JTextField getTxtVersionComercial() {
		return txtVersionComercial;
	}


	public void setTxtVersionComercial(JTextField txtVersionComercial) {
		this.txtVersionComercial = txtVersionComercial;
	}


	public JideTabbedPane getJtpProductoComercial() {
		return jtpProductoComercial;
	}


	public void setJtpProductoComercial(JideTabbedPane jtpProductoComercial) {
		this.jtpProductoComercial = jtpProductoComercial;
	}

	public JButton getBtnEliminarComercialCliente() {
		return btnEliminarComercialCliente;
	}

	public void setBtnEliminarComercialCliente(JButton btnEliminarComercialCliente) {
		this.btnEliminarComercialCliente = btnEliminarComercialCliente;
	}

	public JButton getBtnEliminarProductoCliente() {
		return btnEliminarProductoCliente;
	}

	public void setBtnEliminarProductoCliente(JButton btnEliminarProductoCliente) {
		this.btnEliminarProductoCliente = btnEliminarProductoCliente;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}
}
