package com.spirit.inventario.gui.panel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
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

public abstract class JPMovimiento extends SpiritModelImpl {
	public JPMovimiento() {
		initComponents();
		setName("Movimiento");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpMovimiento = new JideTabbedPane();
		spMovimiento = new JScrollPane();
		panelMovimiento = new JPanel();
		panelReferenciaMovimiento = new JPanel();
		label1 = new JLabel();
		txtSolicitudTransferencia = new JTextField();
		btnBuscarSolicitudTransferencia = new JButton();
		btnEliminarSolicitudTransferencia = new JButton();
		lblOrdenCompra = new JLabel();
		btnBuscarOrdenCompra = new JButton();
		txtOrdenCompra = new JTextField();
		btnEliminarOrdenCompra = new JButton();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		panelDatosMovimiento = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblFechaDocumento = new JLabel();
		cmbFechaDocumento = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		lblTipoDocumento = new JLabel();
		cmbTipoDocumento = new JComboBox();
		cmbBodega = new JComboBox();
		lblBodega = new JLabel();
		lblBodegaTransferencia = new JLabel();
		cmbBodegaTransferencia = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		spDetalle = new JScrollPane();
		PanelDetalleMovimiento = new JPanel();
		panelDetalle = new JPanel();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		lblProducto = new JLabel();
		txtProducto = new JTextField();
		btnBuscarProducto = new JButton();
		lblLote = new JLabel();
		cmbLote = new JComboBox();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		panel1 = new JPanel();
		btnAgregarDetalle = new JButton();
		btnActualizarDetalle = new JButton();
		btnEliminarDetalle = new JButton();
		spTblMovimientoDetalle = new JScrollPane();
		tblMovimientoDetalle = new JTable();
		spConsultas = new JScrollPane();
		PanelConsultas = new JPanel();
		label2 = new JLabel();
		spTblMovimientoDetalle2 = new JScrollPane();
		tblPorAprobar = new JTable();
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

		//======== jtpMovimiento ========
		{
			
			//======== spMovimiento ========
			{
				
				//======== panelMovimiento ========
				{
					panelMovimiento.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));
					
					//======== panelReferenciaMovimiento ========
					{
						panelReferenciaMovimiento.setBorder(new TitledBorder("Referencia del Movimiento"));
						panelReferenciaMovimiento.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(230)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							new RowSpec[] {
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						
						//---- label1 ----
						label1.setText("Solicitud Transferencia:");
						panelReferenciaMovimiento.add(label1, cc.xy(1, 1));
						panelReferenciaMovimiento.add(txtSolicitudTransferencia, cc.xy(3, 1));
						panelReferenciaMovimiento.add(btnBuscarSolicitudTransferencia, cc.xy(5, 1));
						panelReferenciaMovimiento.add(btnEliminarSolicitudTransferencia, cc.xy(7, 1));
						
						//---- lblOrdenCompra ----
						lblOrdenCompra.setText("Orden de Compra:");
						panelReferenciaMovimiento.add(lblOrdenCompra, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelReferenciaMovimiento.add(btnBuscarOrdenCompra, cc.xywh(5, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						panelReferenciaMovimiento.add(txtOrdenCompra, cc.xy(3, 3));
						panelReferenciaMovimiento.add(btnEliminarOrdenCompra, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						
						//---- lblReferencia ----
						lblReferencia.setText("Referencia:");
						panelReferenciaMovimiento.add(lblReferencia, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelReferenciaMovimiento.add(txtReferencia, cc.xy(3, 5));
					}
					panelMovimiento.add(panelReferenciaMovimiento, cc.xywh(3, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//======== panelDatosMovimiento ========
					{
						panelDatosMovimiento.setBorder(new TitledBorder("Datos del Movimiento"));
						panelDatosMovimiento.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(50)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(12)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(36)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(62)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(33)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(100))
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
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						
						//---- lblCodigo ----
						lblCodigo.setText("C\u00f3digo:");
						panelDatosMovimiento.add(lblCodigo, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(txtCodigo, cc.xy(3, 1));
						
						//---- lblFechaDocumento ----
						lblFechaDocumento.setText("Fecha Documento:");
						panelDatosMovimiento.add(lblFechaDocumento, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(cmbFechaDocumento, cc.xywh(3, 3, 5, 1));
						
						//---- lblEstado ----
						lblEstado.setText("Estado:");
						panelDatosMovimiento.add(lblEstado, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(cmbEstado, cc.xywh(3, 5, 3, 1));
						
						//---- lblTipoDocumento ----
						lblTipoDocumento.setText("Tipo Documento:");
						panelDatosMovimiento.add(lblTipoDocumento, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(cmbTipoDocumento, cc.xywh(3, 7, 9, 1));
						panelDatosMovimiento.add(cmbBodega, cc.xywh(3, 9, 9, 1));
						
						//---- lblBodega ----
						lblBodega.setText("Bodega:");
						panelDatosMovimiento.add(lblBodega, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- lblBodegaTransferencia ----
						lblBodegaTransferencia.setText("Bodega Referencia:");
						panelDatosMovimiento.add(lblBodegaTransferencia, cc.xywh(1, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(cmbBodegaTransferencia, cc.xywh(3, 11, 9, 1));
						
						//---- lblObservacion ----
						lblObservacion.setText("Observaci\u00f3n:");
						panelDatosMovimiento.add(lblObservacion, cc.xywh(1, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(txtObservacion, cc.xywh(3, 13, 11, 1));
					}
					panelMovimiento.add(panelDatosMovimiento, cc.xy(3, 3));
				}
				spMovimiento.setViewportView(panelMovimiento);
			}
			jtpMovimiento.addTab("Movimiento", spMovimiento);
			
			
			//======== spDetalle ========
			{
				
				//======== PanelDetalleMovimiento ========
				{
					PanelDetalleMovimiento.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.DLUX3),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.DLUX3)
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));
					
					//======== panelDetalle ========
					{
						panelDetalle.setBorder(new TitledBorder("Detalle"));
						panelDetalle.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(60)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(170)),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(80), FormSpec.DEFAULT_GROW)
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
								new RowSpec(Sizes.DLUY6),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.LINE_GAP_ROWSPEC,
								new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC
							}));
						
						//---- lblDocumento ----
						lblDocumento.setText("Documento:");
						panelDetalle.add(lblDocumento, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDetalle.add(cmbDocumento, cc.xywh(3, 1, 3, 1));
						
						//---- lblProducto ----
						lblProducto.setText("Producto:");
						panelDetalle.add(lblProducto, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDetalle.add(txtProducto, cc.xywh(3, 3, 3, 1));
						panelDetalle.add(btnBuscarProducto, cc.xywh(7, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						
						//---- lblLote ----
						lblLote.setText("Lote:");
						panelDetalle.add(lblLote, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDetalle.add(cmbLote, cc.xywh(3, 5, 3, 1));
						
						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						panelDetalle.add(lblCantidad, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
						panelDetalle.add(txtCantidad, cc.xy(3, 7));
						
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
							btnAgregarDetalle.setIcon(null);
							panel1.add(btnAgregarDetalle, cc.xy(1, 1));
							
							//---- btnActualizarDetalle ----
							btnActualizarDetalle.setText("U");
							btnActualizarDetalle.setIcon(null);
							panel1.add(btnActualizarDetalle, cc.xy(3, 1));
							
							//---- btnEliminarDetalle ----
							btnEliminarDetalle.setText("E");
							panel1.add(btnEliminarDetalle, cc.xy(5, 1));
						}
						panelDetalle.add(panel1, cc.xywh(1, 11, 3, 1));
						
						//======== spTblMovimientoDetalle ========
						{
							
							//---- tblMovimientoDetalle ----
							tblMovimientoDetalle.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null, null, null, null, null, null},
								},
								new String[] {
									"Doc. Movimiento", "Doc. Orden Compra", "Producto", "Lote", "Cantidad", "Costo", "Precio"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									true, false, true, true, false, false, false
								};
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblMovimientoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 200));
							spTblMovimientoDetalle.setViewportView(tblMovimientoDetalle);
						}
						panelDetalle.add(spTblMovimientoDetalle, cc.xywh(1, 13, 9, 5));
					}
					PanelDetalleMovimiento.add(panelDetalle, cc.xy(3, 3));
				}
				spDetalle.setViewportView(PanelDetalleMovimiento);
			}
			jtpMovimiento.addTab("Detalle", spDetalle);
			
			
			//======== spConsultas ========
			{
				
				//======== PanelConsultas ========
				{
					PanelConsultas.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));
					
					//---- label2 ----
					label2.setText("Movimientos por aprobar:");
					PanelConsultas.add(label2, cc.xy(3, 3));
					
					//======== spTblMovimientoDetalle2 ========
					{
						spTblMovimientoDetalle2.setPreferredSize(new Dimension(444, 150));
						
						//---- tblPorAprobar ----
						tblPorAprobar.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null},
							},
							new String[] {
								"Codigo", "Tipo Doc.", "Fecha de Ingreso", "Bodega", "Bodega Ref.", "Estado"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								true, true, false, true, false, true
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblPorAprobar.setPreferredScrollableViewportSize(new Dimension(450, 200));
						spTblMovimientoDetalle2.setViewportView(tblPorAprobar);
					}
					PanelConsultas.add(spTblMovimientoDetalle2, cc.xywh(3, 5, 4, 3));
				}
				spConsultas.setViewportView(PanelConsultas);
			}
			jtpMovimiento.addTab("Por Aprobar", spConsultas);
			
		}
		add(jtpMovimiento, cc.xywh(1, 1, 5, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpMovimiento;
	private JScrollPane spMovimiento;
	private JPanel panelMovimiento;
	private JPanel panelReferenciaMovimiento;
	private JLabel label1;
	private JTextField txtSolicitudTransferencia;
	private JButton btnBuscarSolicitudTransferencia;
	private JButton btnEliminarSolicitudTransferencia;
	private JLabel lblOrdenCompra;
	private JButton btnBuscarOrdenCompra;
	private JTextField txtOrdenCompra;
	private JButton btnEliminarOrdenCompra;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JPanel panelDatosMovimiento;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblFechaDocumento;
	private DateComboBox cmbFechaDocumento;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JLabel lblTipoDocumento;
	private JComboBox cmbTipoDocumento;
	private JComboBox cmbBodega;
	private JLabel lblBodega;
	private JLabel lblBodegaTransferencia;
	private JComboBox cmbBodegaTransferencia;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JScrollPane spDetalle;
	private JPanel PanelDetalleMovimiento;
	private JPanel panelDetalle;
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JButton btnBuscarProducto;
	private JLabel lblLote;
	private JComboBox cmbLote;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JPanel panel1;
	private JButton btnAgregarDetalle;
	private JButton btnActualizarDetalle;
	private JButton btnEliminarDetalle;
	private JScrollPane spTblMovimientoDetalle;
	private JTable tblMovimientoDetalle;
	private JScrollPane spConsultas;
	private JPanel PanelConsultas;
	private JLabel label2;
	private JScrollPane spTblMovimientoDetalle2;
	private JTable tblPorAprobar;
	
	public JideTabbedPane getJtpMovimiento() {
		return jtpMovimiento;
	}

	public JScrollPane getSpMovimiento() {
		return spMovimiento;
	}

	public JPanel getPanelMovimiento() {
		return panelMovimiento;
	}

	public JPanel getPanelReferenciaMovimiento() {
		return panelReferenciaMovimiento;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JTextField getTxtSolicitudTransferencia() {
		return txtSolicitudTransferencia;
	}

	public JButton getBtnBuscarSolicitudTransferencia() {
		return btnBuscarSolicitudTransferencia;
	}

	public JButton getBtnEliminarSolicitudTransferencia() {
		return btnEliminarSolicitudTransferencia;
	}

	public JLabel getLblOrdenCompra() {
		return lblOrdenCompra;
	}

	public JButton getBtnBuscarOrdenCompra() {
		return btnBuscarOrdenCompra;
	}

	public JTextField getTxtOrdenCompra() {
		return txtOrdenCompra;
	}

	public JButton getBtnEliminarOrdenCompra() {
		return btnEliminarOrdenCompra;
	}

	public JLabel getLblReferencia() {
		return lblReferencia;
	}

	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public JPanel getPanelDatosMovimiento() {
		return panelDatosMovimiento;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public JLabel getLblFechaDocumento() {
		return lblFechaDocumento;
	}

	public DateComboBox getCmbFechaDocumento() {
		return cmbFechaDocumento;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public JLabel getLblTipoDocumento() {
		return lblTipoDocumento;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public JLabel getLblBodega() {
		return lblBodega;
	}

	public JLabel getLblBodegaTransferencia() {
		return lblBodegaTransferencia;
	}

	public JComboBox getCmbBodegaTransferencia() {
		return cmbBodegaTransferencia;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public JScrollPane getSpDetalle() {
		return spDetalle;
	}

	public JPanel getPanelDetalleMovimiento() {
		return PanelDetalleMovimiento;
	}

	public JPanel getPanelDetalle() {
		return panelDetalle;
	}

	public JLabel getLblDocumento() {
		return lblDocumento;
	}

	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public JLabel getLblProducto() {
		return lblProducto;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public JLabel getLblLote() {
		return lblLote;
	}

	public JComboBox getCmbLote() {
		return cmbLote;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public JScrollPane getSpTblMovimientoDetalle() {
		return spTblMovimientoDetalle;
	}

	public JTable getTblMovimientoDetalle() {
		return tblMovimientoDetalle;
	}

	public JTable getTblPorAprobar() {
		return tblPorAprobar;
	}


}