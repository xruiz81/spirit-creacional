package com.spirit.inventario.gui.panel;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

public abstract class JPGenerico extends SpiritModelImpl {
	public JPGenerico() {
		initComponents();
		setName("Generico");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		jtpGenerico = new JideTabbedPane();
		spPanelGenerico = new JScrollPane();
		panelGenerico = new JPanel();
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblFechaCreacionGenerico = new JLabel();
		txtFechaCreacionGenerico = new JTextField();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblEstadoGenerico = new JLabel();
		cmbEstadoGenerico = new JComboBox();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		lblNombreGenerico = new JLabel();
		txtNombreGenerico = new JTextField();
		lblAbreviado = new JLabel();
		txtAbreviado = new JTextField();
		lblPartidaArancelaria = new JLabel();
		txtPartidaArancelaria = new JTextField();
		lblCambiaDescripcion = new JLabel();
		cmbCambiaDescripcion = new JComboBox();
		lblFamiliaGenerico = new JLabel();
		cmbFamiliaGenerico = new JComboBox();
		lblEsInventario = new JLabel();
		cmbEsInventario = new JComboBox();
		lblTipoProducto = new JLabel();
		cmbTipoProducto = new JComboBox();
		lblUsaLote = new JLabel();
		cmbUsaLote = new JComboBox();
		lblLinea = new JLabel();
		cmbLinea = new JComboBox();
		lblAfectaStock = new JLabel();
		cmbAfectaStock = new JComboBox();
		lblMedida = new JLabel();
		cmbMedida = new JComboBox();
		lblServicio = new JLabel();
		lblCobraIva = new JLabel();
		cmbCobraIva = new JComboBox();
		cmbServicio = new JComboBox();
		lblAceptaDescuento = new JLabel();
		cmbAceptaDescuento = new JComboBox();
		lblCobraIce = new JLabel();
		cmbCobraIce = new JComboBox();
		lblIva = new JLabel();
		txtIva = new JTextField();
		lblTieneSerie = new JLabel();
		cmbSerie = new JComboBox();
		lblIce = new JLabel();
		txtIce = new JTextField();
		lblCobraIvaCliente = new JLabel();
		cmbCobraIvaCliente = new JComboBox();
		lblOtroImpuesto = new JLabel();
		txtOtroImpuesto = new JTextField();
		spPanelProducto = new JScrollPane();
		panelProducto = new JPanel();
		lblCodigoProducto = new JLabel();
		lblFechaCreacionProducto = new JLabel();
		txtCodigoProducto = new JTextField();
		txtFechaCreacionProducto = new JTextField();
		lblProveedorId = new JLabel();
		txtProveedor = new JTextField();
		btnBuscarProveedor = new JButton();
		lblSubproveedor = new JLabel();
		txtSubproveedor = new JTextField();
		lblCodigoBarras = new JLabel();
		txtCodigoBarras = new JTextField();
		lblMarca = new JLabel();
		cmbMarca = new JComboBox();
		lblModelo = new JLabel();
		cmbModelo = new JComboBox();
		lblPresentacionId = new JLabel();
		cmbPresentacionId = new JComboBox();
		lblAceptaPromocion = new JLabel();
		cmbAceptaPromocion = new JComboBox();
		lblColor = new JLabel();
		cmbColor = new JComboBox();
		lblAceptaDevolucion = new JLabel();
		cmbAceptaDevolucion = new JComboBox();
		lblOrigenProducto = new JLabel();
		cmbOrigenProducto = new JComboBox();
		lblPermiteVenta = new JLabel();
		cmbPermiteVenta = new JComboBox();
		lblCosto = new JLabel();
		txtCosto = new JTextField();
		lblCambioPrecio = new JLabel();
		cmbCambioPrecio = new JComboBox();
		lblMargenMinimo = new JLabel();
		lblRebate = new JLabel();
		txtRebate = new JTextField();
		txtMargenMinimo = new JTextField();
		lblEstadoProducto = new JLabel();
		cmbEstadoProducto = new JComboBox();
		lblPesoBruto = new JLabel();
		lblPesoNeto = new JLabel();
		txtPesoNeto = new JTextField();
		txtPesoBruto = new JTextField();
		cbGenerarCodigoBarras = new JCheckBox();
		panel1 = new JPanel();
		btnAgregarProducto = new JButton();
		btnActualizarProducto = new JButton();
		btnEliminarProducto = new JButton();
		scpProducto = new JScrollPane();
		tblProductos = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== jtpGenerico ========
		{
			
			//======== spPanelGenerico ========
			{
				
				//======== panelGenerico ========
				{
					panelGenerico.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(20)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(100)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
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
							new ColumnSpec(Sizes.dluX(12))
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
							new RowSpec(Sizes.dluY(10))
						}));
					
					//---- lblCodigo ----
					lblCodigo.setText("C\u00f3digo:");
					panelGenerico.add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtCodigo, cc.xywh(5, 3, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblFechaCreacionGenerico ----
					lblFechaCreacionGenerico.setText("Fecha de creaci\u00f3n:");
					panelGenerico.add(lblFechaCreacionGenerico, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtFechaCreacionGenerico, cc.xywh(17, 3, 5, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblReferencia ----
					lblReferencia.setText("Referencia:");
					panelGenerico.add(lblReferencia, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtReferencia, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblEstadoGenerico ----
					lblEstadoGenerico.setText("Estado:");
					panelGenerico.add(lblEstadoGenerico, cc.xywh(15, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbEstadoGenerico, cc.xywh(17, 5, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblNombre ----
					lblNombre.setText("Nombre:");
					panelGenerico.add(lblNombre, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtNombre, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblTipo ----
					lblTipo.setText("Tipo:");
					panelGenerico.add(lblTipo, cc.xywh(15, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- cmbTipo ----
					cmbTipo.setModel(new DefaultComboBoxModel(new String[] {
						"MEDIOS",
						"PRODUCCION",
						"GASTO"
					}));
					panelGenerico.add(cmbTipo, cc.xywh(17, 7, 3, 1));
					
					//---- lblNombreGenerico ----
					lblNombreGenerico.setText("Nombre del gen\u00e9rico:");
					panelGenerico.add(lblNombreGenerico, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtNombreGenerico, cc.xywh(5, 9, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblAbreviado ----
					lblAbreviado.setText("Abreviado");
					panelGenerico.add(lblAbreviado, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtAbreviado, cc.xywh(5, 11, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblPartidaArancelaria ----
					lblPartidaArancelaria.setText("Partida arancelaria:");
					panelGenerico.add(lblPartidaArancelaria, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtPartidaArancelaria, cc.xywh(5, 13, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCambiaDescripcion ----
					lblCambiaDescripcion.setText("Cambio descripci\u00f3n:");
					panelGenerico.add(lblCambiaDescripcion, cc.xywh(15, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbCambiaDescripcion, cc.xywh(17, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblFamiliaGenerico ----
					lblFamiliaGenerico.setText("Familia del gen\u00e9rico:");
					panelGenerico.add(lblFamiliaGenerico, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbFamiliaGenerico, cc.xywh(5, 15, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblEsInventario ----
					lblEsInventario.setText("Es inventario:");
					panelGenerico.add(lblEsInventario, cc.xywh(15, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelGenerico.add(cmbEsInventario, cc.xy(17, 15));
					
					//---- lblTipoProducto ----
					lblTipoProducto.setText("Tipo de producto:");
					panelGenerico.add(lblTipoProducto, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbTipoProducto, cc.xywh(5, 17, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblUsaLote ----
					lblUsaLote.setText("Usa lote:");
					panelGenerico.add(lblUsaLote, cc.xywh(15, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbUsaLote, cc.xywh(17, 17, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblLinea ----
					lblLinea.setText("L\u00ednea:");
					panelGenerico.add(lblLinea, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbLinea, cc.xywh(5, 19, 7, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblAfectaStock ----
					lblAfectaStock.setText("Afecta stock:");
					panelGenerico.add(lblAfectaStock, cc.xywh(15, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbAfectaStock, cc.xywh(17, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblMedida ----
					lblMedida.setText("Medida:");
					panelGenerico.add(lblMedida, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelGenerico.add(cmbMedida, cc.xywh(5, 21, 7, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblServicio ----
					lblServicio.setText("Es de servicio:");
					panelGenerico.add(lblServicio, cc.xywh(15, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblCobraIva ----
					lblCobraIva.setText("Cobra IVA Proveedor:");
					panelGenerico.add(lblCobraIva, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbCobraIva, cc.xywh(5, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelGenerico.add(cmbServicio, cc.xywh(17, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblAceptaDescuento ----
					lblAceptaDescuento.setText("Acepta descuento:");
					panelGenerico.add(lblAceptaDescuento, cc.xywh(15, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelGenerico.add(cmbAceptaDescuento, cc.xy(17, 23));
					
					//---- lblCobraIce ----
					lblCobraIce.setText("Cobra ICE:");
					panelGenerico.add(lblCobraIce, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbCobraIce, cc.xywh(5, 25, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblIva ----
					lblIva.setText("IVA:");
					panelGenerico.add(lblIva, cc.xywh(15, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtIva ----
					txtIva.setHorizontalAlignment(JTextField.RIGHT);
					panelGenerico.add(txtIva, cc.xywh(17, 25, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblTieneSerie ----
					lblTieneSerie.setText("Tiene serie:");
					panelGenerico.add(lblTieneSerie, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbSerie, cc.xywh(5, 27, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblIce ----
					lblIce.setText("ICE:");
					panelGenerico.add(lblIce, cc.xywh(15, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtIce ----
					txtIce.setHorizontalAlignment(JTextField.RIGHT);
					panelGenerico.add(txtIce, cc.xywh(17, 27, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCobraIvaCliente ----
					lblCobraIvaCliente.setText("Cobra IVA Cliente:");
					panelGenerico.add(lblCobraIvaCliente, cc.xywh(3, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelGenerico.add(cmbCobraIvaCliente, cc.xy(5, 29));
					
					//---- lblOtroImpuesto ----
					lblOtroImpuesto.setText("Otro impuesto:");
					panelGenerico.add(lblOtroImpuesto, cc.xywh(15, 29, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtOtroImpuesto ----
					txtOtroImpuesto.setHorizontalAlignment(JTextField.RIGHT);
					panelGenerico.add(txtOtroImpuesto, cc.xywh(17, 29, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				spPanelGenerico.setViewportView(panelGenerico);
			}
			jtpGenerico.addTab("General", spPanelGenerico);
			
			
			//======== spPanelProducto ========
			{
				
				//======== panelProducto ========
				{
					panelProducto.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(70)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(55)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(40)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
						}));
					
					//---- lblCodigoProducto ----
					lblCodigoProducto.setText("C\u00f3digo:");
					panelProducto.add(lblCodigoProducto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblFechaCreacionProducto ----
					lblFechaCreacionProducto.setText("Fecha de creaci\u00f3n:");
					panelProducto.add(lblFechaCreacionProducto, cc.xywh(15, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(txtCodigoProducto, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtFechaCreacionProducto ----
					txtFechaCreacionProducto.setEditable(false);
					panelProducto.add(txtFechaCreacionProducto, cc.xywh(17, 3, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblProveedorId ----
					lblProveedorId.setText("Proveedor:");
					panelProducto.add(lblProveedorId, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(txtProveedor, cc.xywh(5, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelProducto.add(btnBuscarProveedor, cc.xywh(11, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblSubproveedor ----
					lblSubproveedor.setText("Subproveedor:");
					panelProducto.add(lblSubproveedor, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(txtSubproveedor, cc.xywh(5, 7, 5, 1));
					
					//---- lblCodigoBarras ----
					lblCodigoBarras.setText("C\u00f3digo de barras:");
					panelProducto.add(lblCodigoBarras, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(txtCodigoBarras, cc.xywh(5, 9, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblMarca ----
					lblMarca.setText("Marca:");
					panelProducto.add(lblMarca, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(cmbMarca, cc.xywh(5, 11, 5, 1));
					
					//---- lblModelo ----
					lblModelo.setText("Modelo:");
					panelProducto.add(lblModelo, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(cmbModelo, cc.xywh(5, 13, 5, 1));
					
					//---- lblPresentacionId ----
					lblPresentacionId.setText("Presentaci\u00f3n:");
					panelProducto.add(lblPresentacionId, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbPresentacionId, cc.xywh(5, 15, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblAceptaPromocion ----
					lblAceptaPromocion.setText("Acepta promoci\u00f3n:");
					panelProducto.add(lblAceptaPromocion, cc.xywh(15, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbAceptaPromocion, cc.xywh(17, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblColor ----
					lblColor.setText("Color:");
					panelProducto.add(lblColor, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(cmbColor, cc.xywh(5, 17, 5, 1));
					
					//---- lblAceptaDevolucion ----
					lblAceptaDevolucion.setText("Acepta devoluci\u00f3n:");
					panelProducto.add(lblAceptaDevolucion, cc.xywh(15, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbAceptaDevolucion, cc.xywh(17, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblOrigenProducto ----
					lblOrigenProducto.setText("Origen del producto:");
					panelProducto.add(lblOrigenProducto, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbOrigenProducto, cc.xywh(5, 19, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblPermiteVenta ----
					lblPermiteVenta.setText("Permite venta:");
					panelProducto.add(lblPermiteVenta, cc.xywh(15, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbPermiteVenta, cc.xywh(17, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCosto ----
					lblCosto.setText("Costo:");
					panelProducto.add(lblCosto, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtCosto ----
					txtCosto.setHorizontalAlignment(JTextField.RIGHT);
					panelProducto.add(txtCosto, cc.xywh(5, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblCambioPrecio ----
					lblCambioPrecio.setText("Cambio de precio:");
					panelProducto.add(lblCambioPrecio, cc.xywh(15, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbCambioPrecio, cc.xywh(17, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblMargenMinimo ----
					lblMargenMinimo.setText("M\u00e1rgen m\u00ednimo:");
					panelProducto.add(lblMargenMinimo, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- lblRebate ----
					lblRebate.setText("Rebate:");
					panelProducto.add(lblRebate, cc.xywh(7, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					
					//---- txtRebate ----
					txtRebate.setHorizontalAlignment(JTextField.RIGHT);
					panelProducto.add(txtRebate, cc.xywh(9, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- txtMargenMinimo ----
					txtMargenMinimo.setHorizontalAlignment(JTextField.RIGHT);
					panelProducto.add(txtMargenMinimo, cc.xy(5, 23));
					
					//---- lblEstadoProducto ----
					lblEstadoProducto.setText("Estado:");
					panelProducto.add(lblEstadoProducto, cc.xywh(15, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbEstadoProducto, cc.xywh(17, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					
					//---- lblPesoBruto ----
					lblPesoBruto.setText("Peso bruto:");
					panelProducto.add(lblPesoBruto, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblPesoNeto ----
					lblPesoNeto.setText("Peso neto:");
					panelProducto.add(lblPesoNeto, cc.xywh(7, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(txtPesoNeto, cc.xy(9, 25));
					panelProducto.add(txtPesoBruto, cc.xy(5, 25));
					
					//---- cbGenerarCodigoBarras ----
					cbGenerarCodigoBarras.setText("Generar c\u00f3digo de barras");
					panelProducto.add(cbGenerarCodigoBarras, cc.xywh(15, 25, 3, 1));
					
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
						
						//---- btnAgregarProducto ----
						btnAgregarProducto.setText("A");
						panel1.add(btnAgregarProducto, cc.xy(1, 1));
						
						//---- btnActualizarProducto ----
						btnActualizarProducto.setText("U");
						btnActualizarProducto.setHorizontalAlignment(SwingConstants.CENTER);
						panel1.add(btnActualizarProducto, cc.xy(3, 1));
						
						//---- btnEliminarProducto ----
						btnEliminarProducto.setText("D");
						panel1.add(btnEliminarProducto, cc.xy(5, 1));
					}
					panelProducto.add(panel1, cc.xywh(3, 29, 17, 1));
					
					//======== scpProducto ========
					{
						
						//---- tblProductos ----
						tblProductos.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Proveedor", "Presentaci\u00f3n", "Costo", "Fecha de Creaci\u00f3n", "Estado"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false, false, false
							};
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						tblProductos.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						scpProducto.setViewportView(tblProductos);
					}
					panelProducto.add(scpProducto, cc.xywh(3, 33, 19, 5));
				}
				spPanelProducto.setViewportView(panelProducto);
			}
			jtpGenerico.addTab("Productos", spPanelProducto);
			
		}
		add(jtpGenerico, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JideTabbedPane jtpGenerico;
	private JScrollPane spPanelGenerico;
	private JPanel panelGenerico;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblFechaCreacionGenerico;
	private JTextField txtFechaCreacionGenerico;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblEstadoGenerico;
	private JComboBox cmbEstadoGenerico;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JLabel lblNombreGenerico;
	private JTextField txtNombreGenerico;
	private JLabel lblAbreviado;
	private JTextField txtAbreviado;
	private JLabel lblPartidaArancelaria;
	private JTextField txtPartidaArancelaria;
	private JLabel lblCambiaDescripcion;
	private JComboBox cmbCambiaDescripcion;
	private JLabel lblFamiliaGenerico;
	private JComboBox cmbFamiliaGenerico;
	private JLabel lblEsInventario;
	private JComboBox cmbEsInventario;
	private JLabel lblTipoProducto;
	private JComboBox cmbTipoProducto;
	private JLabel lblUsaLote;
	private JComboBox cmbUsaLote;
	private JLabel lblLinea;
	private JComboBox cmbLinea;
	private JLabel lblAfectaStock;
	private JComboBox cmbAfectaStock;
	private JLabel lblMedida;
	private JComboBox cmbMedida;
	private JLabel lblServicio;
	private JLabel lblCobraIva;
	private JComboBox cmbCobraIva;
	private JComboBox cmbServicio;
	private JLabel lblAceptaDescuento;
	private JComboBox cmbAceptaDescuento;
	private JLabel lblCobraIce;
	private JComboBox cmbCobraIce;
	private JLabel lblIva;
	private JTextField txtIva;
	private JLabel lblTieneSerie;
	private JComboBox cmbSerie;
	private JLabel lblIce;
	private JTextField txtIce;
	private JLabel lblCobraIvaCliente;
	private JComboBox cmbCobraIvaCliente;
	private JLabel lblOtroImpuesto;
	private JTextField txtOtroImpuesto;
	private JScrollPane spPanelProducto;
	private JPanel panelProducto;
	private JLabel lblCodigoProducto;
	private JLabel lblFechaCreacionProducto;
	private JTextField txtCodigoProducto;
	private JTextField txtFechaCreacionProducto;
	private JLabel lblProveedorId;
	private JTextField txtProveedor;
	private JButton btnBuscarProveedor;
	private JLabel lblSubproveedor;
	private JTextField txtSubproveedor;
	private JLabel lblCodigoBarras;
	private JTextField txtCodigoBarras;
	private JLabel lblMarca;
	private JComboBox cmbMarca;
	private JLabel lblModelo;
	private JComboBox cmbModelo;
	private JLabel lblPresentacionId;
	private JComboBox cmbPresentacionId;
	private JLabel lblAceptaPromocion;
	private JComboBox cmbAceptaPromocion;
	private JLabel lblColor;
	private JComboBox cmbColor;
	private JLabel lblAceptaDevolucion;
	private JComboBox cmbAceptaDevolucion;
	private JLabel lblOrigenProducto;
	private JComboBox cmbOrigenProducto;
	private JLabel lblPermiteVenta;
	private JComboBox cmbPermiteVenta;
	private JLabel lblCosto;
	private JTextField txtCosto;
	private JLabel lblCambioPrecio;
	private JComboBox cmbCambioPrecio;
	private JLabel lblMargenMinimo;
	private JLabel lblRebate;
	private JTextField txtRebate;
	private JTextField txtMargenMinimo;
	private JLabel lblEstadoProducto;
	private JComboBox cmbEstadoProducto;
	private JLabel lblPesoBruto;
	private JLabel lblPesoNeto;
	private JTextField txtPesoNeto;
	private JTextField txtPesoBruto;
	private JCheckBox cbGenerarCodigoBarras;
	private JPanel panel1;
	private JButton btnAgregarProducto;
	private JButton btnActualizarProducto;
	private JButton btnEliminarProducto;
	private JScrollPane scpProducto;
	private JTable tblProductos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}
	
	public void setBtnActualizarProducto(JButton btnActualizarProducto) {
		this.btnActualizarProducto = btnActualizarProducto;
	}
	
	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}
	
	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}
	
	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}
	
	public void setBtnBuscarProveedor(JButton btnBuscarProveedor) {
		this.btnBuscarProveedor = btnBuscarProveedor;
	}
	
	public JComboBox getCmbAceptaDevolucion() {
		return cmbAceptaDevolucion;
	}
	
	public void setCmbAceptaDevolucion(JComboBox cmbAceptaDevolucion) {
		this.cmbAceptaDevolucion = cmbAceptaDevolucion;
	}
	
	public JComboBox getCmbAceptaPromocion() {
		return cmbAceptaPromocion;
	}
	
	public void setCmbAceptaPromocion(JComboBox cmbAceptaPromocion) {
		this.cmbAceptaPromocion = cmbAceptaPromocion;
	}
	
	public JComboBox getCmbAfectaStock() {
		return cmbAfectaStock;
	}
	
	public void setCmbAfectaStock(JComboBox cmbAfectaStock) {
		this.cmbAfectaStock = cmbAfectaStock;
	}
	
	public JComboBox getCmbCambiaDescripcion() {
		return cmbCambiaDescripcion;
	}
	
	public void setCmbCambiaDescripcion(JComboBox cmbCambiaDescripcion) {
		this.cmbCambiaDescripcion = cmbCambiaDescripcion;
	}
	
	public JComboBox getCmbCambioPrecio() {
		return cmbCambioPrecio;
	}
	
	public void setCmbCambioPrecio(JComboBox cmbCambioPrecio) {
		this.cmbCambioPrecio = cmbCambioPrecio;
	}
	
	public JComboBox getCmbCobraIce() {
		return cmbCobraIce;
	}
	
	public void setCmbCobraIce(JComboBox cmbCobraIce) {
		this.cmbCobraIce = cmbCobraIce;
	}
	
	public JComboBox getCmbCobraIva() {
		return cmbCobraIva;
	}
	
	public void setCmbCobraIva(JComboBox cmbCobraIva) {
		this.cmbCobraIva = cmbCobraIva;
	}
	
	public JComboBox getCmbEstadoGenerico() {
		return cmbEstadoGenerico;
	}
	
	public void setCmbEstadoGenerico(JComboBox cmbEstadoGenerico) {
		this.cmbEstadoGenerico = cmbEstadoGenerico;
	}
	
	public JComboBox getCmbEstadoProducto() {
		return cmbEstadoProducto;
	}
	
	public void setCmbEstadoProducto(JComboBox cmbEstadoProducto) {
		this.cmbEstadoProducto = cmbEstadoProducto;
	}
	
	public JComboBox getCmbFamiliaGenerico() {
		return cmbFamiliaGenerico;
	}
	
	public void setCmbFamiliaGenerico(JComboBox cmbFamiliaGenerico) {
		this.cmbFamiliaGenerico = cmbFamiliaGenerico;
	}
	
	public JComboBox getCmbLinea() {
		return cmbLinea;
	}
	
	public void setCmbLinea(JComboBox cmbLinea) {
		this.cmbLinea = cmbLinea;
	}
	
	public JComboBox getCmbMedida() {
		return cmbMedida;
	}
	
	public void setCmbMedida(JComboBox cmbMedida) {
		this.cmbMedida = cmbMedida;
	}
	
	public JComboBox getCmbOrigenProducto() {
		return cmbOrigenProducto;
	}
	
	public void setCmbOrigenProducto(JComboBox cmbOrigenProducto) {
		this.cmbOrigenProducto = cmbOrigenProducto;
	}
	
	public JComboBox getCmbPermiteVenta() {
		return cmbPermiteVenta;
	}
	
	public void setCmbPermiteVenta(JComboBox cmbPermiteVenta) {
		this.cmbPermiteVenta = cmbPermiteVenta;
	}
	
	public JComboBox getCmbPresentacionId() {
		return cmbPresentacionId;
	}
	
	public void setCmbPresentacionId(JComboBox cmbPresentacionId) {
		this.cmbPresentacionId = cmbPresentacionId;
	}
	
	public JComboBox getCmbSerie() {
		return cmbSerie;
	}
	
	public void setCmbSerie(JComboBox cmbSerie) {
		this.cmbSerie = cmbSerie;
	}
	
	public JComboBox getCmbServicio() {
		return cmbServicio;
	}
	
	public void setCmbServicio(JComboBox cmbServicio) {
		this.cmbServicio = cmbServicio;
	}
	
	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}
	
	public void setCmbTipoProducto(JComboBox cmbTipoProducto) {
		this.cmbTipoProducto = cmbTipoProducto;
	}
	
	public JComboBox getCmbUsaLote() {
		return cmbUsaLote;
	}
	
	public void setCmbUsaLote(JComboBox cmbUsaLote) {
		this.cmbUsaLote = cmbUsaLote;
	}
	
	public JideTabbedPane getJtpGenerico() {
		return jtpGenerico;
	}
	
	public void setJtpGenerico(JideTabbedPane jtpGenerico) {
		this.jtpGenerico = jtpGenerico;
	}
	
	public JTable getTblProductos() {
		return tblProductos;
	}
	
	public void setTblProductos(JTable tblProductos) {
		this.tblProductos = tblProductos;
	}
	
	public JTextField getTxtAbreviado() {
		return txtAbreviado;
	}
	
	public void setTxtAbreviado(JTextField txtAbreviado) {
		this.txtAbreviado = txtAbreviado;
	}
	
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}
	
	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}
	
	public JTextField getTxtCodigoBarras() {
		return txtCodigoBarras;
	}
	
	public void setTxtCodigoBarras(JTextField txtCodigoBarras) {
		this.txtCodigoBarras = txtCodigoBarras;
	}
	
	public JTextField getTxtCodigoProducto() {
		return txtCodigoProducto;
	}
	
	public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}
	
	public JTextField getTxtCosto() {
		return txtCosto;
	}
	
	public void setTxtCosto(JTextField txtCosto) {
		this.txtCosto = txtCosto;
	}
	
	public JTextField getTxtFechaCreacionGenerico() {
		return txtFechaCreacionGenerico;
	}
	
	public void setTxtFechaCreacionGenerico(JTextField txtFechaCreacionGenerico) {
		this.txtFechaCreacionGenerico = txtFechaCreacionGenerico;
	}
	
	public JTextField getTxtFechaCreacionProducto() {
		return txtFechaCreacionProducto;
	}
	
	public void setTxtFechaCreacionProducto(JTextField txtFechaCreacionProducto) {
		this.txtFechaCreacionProducto = txtFechaCreacionProducto;
	}
	
	public JTextField getTxtIce() {
		return txtIce;
	}
	
	public void setTxtIce(JTextField txtIce) {
		this.txtIce = txtIce;
	}
	
	public JTextField getTxtIva() {
		return txtIva;
	}
	
	public void setTxtIva(JTextField txtIva) {
		this.txtIva = txtIva;
	}
	
	public JTextField getTxtMargenMinimo() {
		return txtMargenMinimo;
	}
	
	public void setTxtMargenMinimo(JTextField txtMargenMinimo) {
		this.txtMargenMinimo = txtMargenMinimo;
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}
	
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}
	
	public JTextField getTxtNombreGenerico() {
		return txtNombreGenerico;
	}
	
	public void setTxtNombreGenerico(JTextField txtNombreGenerico) {
		this.txtNombreGenerico = txtNombreGenerico;
	}
	
	public JTextField getTxtOtroImpuesto() {
		return txtOtroImpuesto;
	}
	
	public void setTxtOtroImpuesto(JTextField txtOtroImpuesto) {
		this.txtOtroImpuesto = txtOtroImpuesto;
	}
	
	public JTextField getTxtPartidaArancelaria() {
		return txtPartidaArancelaria;
	}
	
	public void setTxtPartidaArancelaria(JTextField txtPartidaArancelaria) {
		this.txtPartidaArancelaria = txtPartidaArancelaria;
	}
	
	public JTextField getTxtProveedor() {
		return txtProveedor;
	}
	
	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}
	
	public JTextField getTxtRebate() {
		return txtRebate;
	}
	
	public void setTxtRebate(JTextField txtRebate) {
		this.txtRebate = txtRebate;
	}
	
	public JTextField getTxtReferencia() {
		return txtReferencia;
	}
	
	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}
	
	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}
	
	public void setBtnEliminarProducto(JButton btnEliminarProducto) {
		this.btnEliminarProducto = btnEliminarProducto;
	}
	
	public JComboBox getCmbTipo() {
		return cmbTipo;
	}
	
	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}
	
	public JTextField getTxtSubproveedor() {
		return txtSubproveedor;
	}
	
	public void setTxtSubproveedor(JTextField txtSubproveedor) {
		this.txtSubproveedor = txtSubproveedor;
	}

	public JComboBox getCmbColor() {
		return cmbColor;
	}

	public void setCmbColor(JComboBox cmbColor) {
		this.cmbColor = cmbColor;
	}

	public JComboBox getCmbMarca() {
		return cmbMarca;
	}

	public void setCmbMarca(JComboBox cmbMarca) {
		this.cmbMarca = cmbMarca;
	}

	public JComboBox getCmbModelo() {
		return cmbModelo;
	}

	public void setCmbModelo(JComboBox cmbModelo) {
		this.cmbModelo = cmbModelo;
	}

	public JTextField getTxtPesoBruto() {
		return txtPesoBruto;
	}

	public void setTxtPesoBruto(JTextField txtPesoBruto) {
		this.txtPesoBruto = txtPesoBruto;
	}

	public JTextField getTxtPesoNeto() {
		return txtPesoNeto;
	}

	public void setTxtPesoNeto(JTextField txtPesoNeto) {
		this.txtPesoNeto = txtPesoNeto;
	}

	public JComboBox getCmbEsInventario() {
		return cmbEsInventario;
	}

	public void setCmbEsInventario(JComboBox cmbEsInventario) {
		this.cmbEsInventario = cmbEsInventario;
	}

	public JComboBox getCmbAceptaDescuento() {
		return cmbAceptaDescuento;
	}

	public void setCmbAceptaDescuento(JComboBox cmbAceptaDescuento) {
		this.cmbAceptaDescuento = cmbAceptaDescuento;
	}

	public JComboBox getCmbCobraIvaCliente() {
		return cmbCobraIvaCliente;
	}

	public void setCmbCobraIvaCliente(JComboBox cmbCobraIvaCliente) {
		this.cmbCobraIvaCliente = cmbCobraIvaCliente;
	}

	public JCheckBox getCbGenerarCodigoBarras() {
		return cbGenerarCodigoBarras;
	}

	public void setCbGenerarCodigoBarras(JCheckBox cbGenerarCodigoBarras) {
		this.cbGenerarCodigoBarras = cbGenerarCodigoBarras;
	}	
}
