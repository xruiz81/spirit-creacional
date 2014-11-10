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



/**
 * @author Antonio Seiler
 */
public abstract class JPSolicitudTransferencia extends SpiritModelImpl {
	public JPSolicitudTransferencia() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpMovimiento = new JideTabbedPane();
		spMovimiento = new JScrollPane();
		panelMovimiento = new JPanel();
		panelDatosMovimiento = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblFechaDocumento = new JLabel();
		cmbFechaDocumento = new DateComboBox();
		lblEstado = new JLabel();
		cmbEstado = new JComboBox();
		cmbBodega = new JComboBox();
		lblBodega = new JLabel();
		lblBodegaTransferencia = new JLabel();
		cmbBodegaTransferencia = new JComboBox();
		lblObservacion = new JLabel();
		txtObservacion = new JTextField();
		spDetalle = new JScrollPane();
		PanelDetalleMovimiento = new JPanel();
		panelDetalle = new JPanel();
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
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setName("SOLICITUD TRANSFERENCIA");
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
							FormFactory.DEFAULT_ROWSPEC
						}));
					
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
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(Sizes.dluX(62)),
								FormFactory.DEFAULT_COLSPEC,
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
						panelDatosMovimiento.add(cmbBodega, cc.xywh(3, 7, 12, 1));
						
						//---- lblBodega ----
						lblBodega.setText("Desde bodega:");
						panelDatosMovimiento.add(lblBodega, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- lblBodegaTransferencia ----
						lblBodegaTransferencia.setText("Hacia bodega:");
						panelDatosMovimiento.add(lblBodegaTransferencia, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(cmbBodegaTransferencia, cc.xywh(3, 9, 12, 1));
						
						//---- lblObservacion ----
						lblObservacion.setText("Observaci\u00f3n:");
						panelDatosMovimiento.add(lblObservacion, cc.xywh(1, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDatosMovimiento.add(txtObservacion, cc.xywh(3, 11, 14, 1));
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
						
						//---- lblProducto ----
						lblProducto.setText("Producto:");
						panelDetalle.add(lblProducto, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDetalle.add(txtProducto, cc.xywh(3, 1, 3, 1));
						panelDetalle.add(btnBuscarProducto, cc.xywh(7, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
						
						//---- lblLote ----
						lblLote.setText("Lote:");
						panelDetalle.add(lblLote, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						panelDetalle.add(cmbLote, cc.xywh(3, 3, 3, 1));
						
						//---- lblCantidad ----
						lblCantidad.setText("Cantidad:");
						panelDetalle.add(lblCantidad, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
						
						//---- txtCantidad ----
						txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
						panelDetalle.add(txtCantidad, cc.xy(3, 5));
						
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
						panelDetalle.add(panel1, cc.xywh(1, 9, 3, 1));
						
						//======== spTblMovimientoDetalle ========
						{
							
							//---- tblMovimientoDetalle ----
							tblMovimientoDetalle.setModel(new DefaultTableModel(
								new Object[][] {
									{null, null, null},
								},
								new String[] {
									"Producto", "Lote", "Cantidad"
								}
							) {
								boolean[] columnEditable = new boolean[] {
									true, true, false
								};
								public boolean isCellEditable(int rowIndex, int columnIndex) {
									return columnEditable[columnIndex];
								}
							});
							tblMovimientoDetalle.setPreferredScrollableViewportSize(new Dimension(450, 200));
							spTblMovimientoDetalle.setViewportView(tblMovimientoDetalle);
						}
						panelDetalle.add(spTblMovimientoDetalle, cc.xywh(1, 11, 9, 5));
					}
					PanelDetalleMovimiento.add(panelDetalle, cc.xy(3, 3));
				}
				spDetalle.setViewportView(PanelDetalleMovimiento);
			}
			jtpMovimiento.addTab("Detalle", spDetalle);
			
		}
		add(jtpMovimiento, cc.xywh(3, 1, 3, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpMovimiento;
	private JScrollPane spMovimiento;
	private JPanel panelMovimiento;
	private JPanel panelDatosMovimiento;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblFechaDocumento;
	private DateComboBox cmbFechaDocumento;
	private JLabel lblEstado;
	private JComboBox cmbEstado;
	private JComboBox cmbBodega;
	private JLabel lblBodega;
	private JLabel lblBodegaTransferencia;
	private JComboBox cmbBodegaTransferencia;
	private JLabel lblObservacion;
	private JTextField txtObservacion;
	private JScrollPane spDetalle;
	private JPanel PanelDetalleMovimiento;
	private JPanel panelDetalle;
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
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public JideTabbedPane getJtpMovimiento() {
		return jtpMovimiento;
	}

	public void setJtpMovimiento(JideTabbedPane jtpMovimiento) {
		this.jtpMovimiento = jtpMovimiento;
	}

	public JScrollPane getSpMovimiento() {
		return spMovimiento;
	}

	public void setSpMovimiento(JScrollPane spMovimiento) {
		this.spMovimiento = spMovimiento;
	}

	public JPanel getPanelMovimiento() {
		return panelMovimiento;
	}

	public void setPanelMovimiento(JPanel panelMovimiento) {
		this.panelMovimiento = panelMovimiento;
	}

	public JPanel getPanelDatosMovimiento() {
		return panelDatosMovimiento;
	}

	public void setPanelDatosMovimiento(JPanel panelDatosMovimiento) {
		this.panelDatosMovimiento = panelDatosMovimiento;
	}

	public JLabel getLblCodigo() {
		return lblCodigo;
	}

	public void setLblCodigo(JLabel lblCodigo) {
		this.lblCodigo = lblCodigo;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JLabel getLblFechaDocumento() {
		return lblFechaDocumento;
	}

	public void setLblFechaDocumento(JLabel lblFechaDocumento) {
		this.lblFechaDocumento = lblFechaDocumento;
	}

	public DateComboBox getCmbFechaDocumento() {
		return cmbFechaDocumento;
	}

	public void setCmbFechaDocumento(DateComboBox cmbFechaDocumento) {
		this.cmbFechaDocumento = cmbFechaDocumento;
	}

	public JLabel getLblEstado() {
		return lblEstado;
	}

	public void setLblEstado(JLabel lblEstado) {
		this.lblEstado = lblEstado;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbBodega() {
		return cmbBodega;
	}

	public void setCmbBodega(JComboBox cmbBodega) {
		this.cmbBodega = cmbBodega;
	}

	public JLabel getLblBodega() {
		return lblBodega;
	}

	public void setLblBodega(JLabel lblBodega) {
		this.lblBodega = lblBodega;
	}

	public JLabel getLblBodegaTransferencia() {
		return lblBodegaTransferencia;
	}

	public void setLblBodegaTransferencia(JLabel lblBodegaTransferencia) {
		this.lblBodegaTransferencia = lblBodegaTransferencia;
	}

	public JComboBox getCmbBodegaTransferencia() {
		return cmbBodegaTransferencia;
	}

	public void setCmbBodegaTransferencia(JComboBox cmbBodegaTransferencia) {
		this.cmbBodegaTransferencia = cmbBodegaTransferencia;
	}

	public JLabel getLblObservacion() {
		return lblObservacion;
	}

	public void setLblObservacion(JLabel lblObservacion) {
		this.lblObservacion = lblObservacion;
	}

	public JTextField getTxtObservacion() {
		return txtObservacion;
	}

	public void setTxtObservacion(JTextField txtObservacion) {
		this.txtObservacion = txtObservacion;
	}

	public JScrollPane getSpDetalle() {
		return spDetalle;
	}

	public void setSpDetalle(JScrollPane spDetalle) {
		this.spDetalle = spDetalle;
	}

	public JPanel getPanelDetalleMovimiento() {
		return PanelDetalleMovimiento;
	}

	public void setPanelDetalleMovimiento(JPanel panelDetalleMovimiento) {
		PanelDetalleMovimiento = panelDetalleMovimiento;
	}

	public JPanel getPanelDetalle() {
		return panelDetalle;
	}

	public void setPanelDetalle(JPanel panelDetalle) {
		this.panelDetalle = panelDetalle;
	}

	public JLabel getLblProducto() {
		return lblProducto;
	}

	public void setLblProducto(JLabel lblProducto) {
		this.lblProducto = lblProducto;
	}

	public JTextField getTxtProducto() {
		return txtProducto;
	}

	public void setTxtProducto(JTextField txtProducto) {
		this.txtProducto = txtProducto;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JLabel getLblLote() {
		return lblLote;
	}

	public void setLblLote(JLabel lblLote) {
		this.lblLote = lblLote;
	}

	public JComboBox getCmbLote() {
		return cmbLote;
	}

	public void setCmbLote(JComboBox cmbLote) {
		this.cmbLote = cmbLote;
	}

	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JButton getBtnAgregarDetalle() {
		return btnAgregarDetalle;
	}

	public void setBtnAgregarDetalle(JButton btnAgregarDetalle) {
		this.btnAgregarDetalle = btnAgregarDetalle;
	}

	public JButton getBtnActualizarDetalle() {
		return btnActualizarDetalle;
	}

	public void setBtnActualizarDetalle(JButton btnActualizarDetalle) {
		this.btnActualizarDetalle = btnActualizarDetalle;
	}

	public JButton getBtnEliminarDetalle() {
		return btnEliminarDetalle;
	}

	public void setBtnEliminarDetalle(JButton btnEliminarDetalle) {
		this.btnEliminarDetalle = btnEliminarDetalle;
	}

	public JScrollPane getSpTblMovimientoDetalle() {
		return spTblMovimientoDetalle;
	}

	public void setSpTblMovimientoDetalle(JScrollPane spTblMovimientoDetalle) {
		this.spTblMovimientoDetalle = spTblMovimientoDetalle;
	}

	public JTable getTblMovimientoDetalle() {
		return tblMovimientoDetalle;
	}

	public void setTblMovimientoDetalle(JTable tblMovimientoDetalle) {
		this.tblMovimientoDetalle = tblMovimientoDetalle;
	}
}
