package com.spirit.inventario.gui.panel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.model.SpiritModelImpl;



public abstract class JPNuevoGenerico extends SpiritModelImpl {
	public JPNuevoGenerico() {
		initComponents();
		setName("Generico");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jtpGenerico = new JideTabbedPane();
		spPanelGenerico = new JScrollPane();
		panelGenerico = new JPanel();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		lblAbreviado = new JLabel();
		txtAbreviado = new JTextField();
		lblTipo = new JLabel();
		cmbTipo = new JComboBox();
		lblFamiliaGenerico = new JLabel();
		cmbFamiliaGenerico = new JComboBox();
		lblTipoProducto = new JLabel();
		cmbTipoProducto = new JComboBox();
		lblLinea = new JLabel();
		cmbLinea = new JComboBox();
		lblMedida = new JLabel();
		cmbMedida = new JComboBox();
		lblCobraIvaProveedor = new JLabel();
		rbCobraIvaProveedorSi = new JRadioButton();
		rbCobraIvaProveedorNo = new JRadioButton();
		lblCambiaDescripcion = new JLabel();
		rbCambioDescripcionSi = new JRadioButton();
		rbCambioDescripcionNo = new JRadioButton();
		lblCobraIvaCliente = new JLabel();
		rbCobraIvaClienteSi = new JRadioButton();
		rbCobraIvaClienteNo = new JRadioButton();
		lblServicio = new JLabel();
		rbServicioSi = new JRadioButton();
		rbServicioNo = new JRadioButton();
		lblCobraIce = new JLabel();
		rbCobraIceSi = new JRadioButton();
		rbCobraIceNo = new JRadioButton();
		lblEsInventario = new JLabel();
		rbLlevaInventarioSi = new JRadioButton();
		rbLlevaInventarioNo = new JRadioButton();
		lblAceptaDescuento = new JLabel();
		rbAceptaDescuentoSi = new JRadioButton();
		rbAceptaDescuentoNo = new JRadioButton();
		lblUsaLote = new JLabel();
		rbUsaLoteSi = new JRadioButton();
		rbUsaLoteNo = new JRadioButton();
		lblOtroImpuesto = new JLabel();
		txtOtroImpuesto = new JTextField();
		lblTieneSerie = new JLabel();
		rbTieneSerieSi = new JRadioButton();
		rbTieneSerieNo = new JRadioButton();
		lblEstadoGenerico = new JLabel();
		cmbEstadoGenerico = new JComboBox();
		spPanelProducto = new JScrollPane();
		panelProducto = new JPanel();
		lblProveedorId = new JLabel();
		txtProveedor = new JTextField();
		btnBuscarProveedor = new JButton();
		lblSubproveedor = new JLabel();
		txtSubproveedor = new JTextField();
		lblCodigoBarras = new JLabel();
		txtCodigoBarras = new JTextField();
		cbGenerarCodigoBarras = new JCheckBox();
		lblMarca = new JLabel();
		cmbMarca = new JComboBox();
		lblAceptaPromocion = new JLabel();
		rbAceptaPromocionSi = new JRadioButton();
		rbAceptaPromocionNo = new JRadioButton();
		lblModelo = new JLabel();
		cmbModelo = new JComboBox();
		lblAceptaDevolucion = new JLabel();
		rbAceptaDevolucionSi = new JRadioButton();
		rbAceptaDevolucionNo = new JRadioButton();
		lblPresentacionId = new JLabel();
		cmbPresentacionId = new JComboBox();
		lblPermiteVenta = new JLabel();
		rbPermiteVentaSi = new JRadioButton();
		rbPermiteVentaNo = new JRadioButton();
		lblColor = new JLabel();
		cmbColor = new JComboBox();
		lblCambioPrecio = new JLabel();
		rbCambioPrecioSi = new JRadioButton();
		rbCambioPrecioNo = new JRadioButton();
		lblOrigenProducto = new JLabel();
		cmbOrigenProducto = new JComboBox();
		lblEstadoProducto = new JLabel();
		cmbEstadoProducto = new JComboBox();
		lblPesoBruto = new JLabel();
		txtPesoBruto = new JTextField();
		lblPesoNeto = new JLabel();
		txtPesoNeto = new JTextField();
		lblCosto = new JLabel();
		txtCosto = new JTextField();
		lblMargenMinimo = new JLabel();
		txtMargenMinimo = new JTextField();
		lblRebate = new JLabel();
		txtRebate = new JTextField();
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
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(30)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
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
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));

					//---- lblNombre ----
					lblNombre.setText("Nombre:");
					lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblNombre, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtNombre, cc.xywh(5, 3, 15, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblAbreviado ----
					lblAbreviado.setText("Abreviado:");
					lblAbreviado.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblAbreviado, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(txtAbreviado, cc.xywh(5, 5, 15, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblTipo ----
					lblTipo.setText("Tipo:");
					lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblTipo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- cmbTipo ----
					cmbTipo.setModel(new DefaultComboBoxModel(new String[] {
						"MEDIOS",
						"PRODUCCION",
						"GASTO"
					}));
					panelGenerico.add(cmbTipo, cc.xywh(5, 7, 3, 1));

					//---- lblFamiliaGenerico ----
					lblFamiliaGenerico.setText("Familia del gen\u00e9rico:");
					lblFamiliaGenerico.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblFamiliaGenerico, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbFamiliaGenerico, cc.xywh(5, 9, 13, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblTipoProducto ----
					lblTipoProducto.setText("Tipo de producto:");
					lblTipoProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblTipoProducto, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbTipoProducto, cc.xywh(5, 11, 13, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblLinea ----
					lblLinea.setText("L\u00ednea:");
					lblLinea.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblLinea, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbLinea, cc.xywh(5, 13, 13, 1, CellConstraints.FILL, CellConstraints.FILL));

					//---- lblMedida ----
					lblMedida.setText("Medida:");
					lblMedida.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblMedida, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelGenerico.add(cmbMedida, cc.xywh(5, 15, 13, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblCobraIvaProveedor ----
					lblCobraIvaProveedor.setText("Cobra IVA Proveedor:");
					lblCobraIvaProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblCobraIvaProveedor, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbCobraIvaProveedorSi ----
					rbCobraIvaProveedorSi.setText("S\u00ed");
					rbCobraIvaProveedorSi.setSelected(true);
					panelGenerico.add(rbCobraIvaProveedorSi, cc.xy(5, 17));

					//---- rbCobraIvaProveedorNo ----
					rbCobraIvaProveedorNo.setText("No");
					panelGenerico.add(rbCobraIvaProveedorNo, cc.xy(7, 17));

					//---- lblCambiaDescripcion ----
					lblCambiaDescripcion.setText("Cambio descripci\u00f3n:");
					lblCambiaDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblCambiaDescripcion, cc.xywh(11, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbCambioDescripcionSi ----
					rbCambioDescripcionSi.setText("S\u00ed");
					rbCambioDescripcionSi.setSelected(true);
					panelGenerico.add(rbCambioDescripcionSi, cc.xy(13, 17));

					//---- rbCambioDescripcionNo ----
					rbCambioDescripcionNo.setText("No");
					panelGenerico.add(rbCambioDescripcionNo, cc.xy(15, 17));

					//---- lblCobraIvaCliente ----
					lblCobraIvaCliente.setText("Cobra IVA Cliente:");
					lblCobraIvaCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblCobraIvaCliente, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- rbCobraIvaClienteSi ----
					rbCobraIvaClienteSi.setText("S\u00ed");
					rbCobraIvaClienteSi.setSelected(true);
					panelGenerico.add(rbCobraIvaClienteSi, cc.xy(5, 19));

					//---- rbCobraIvaClienteNo ----
					rbCobraIvaClienteNo.setText("No");
					panelGenerico.add(rbCobraIvaClienteNo, cc.xy(7, 19));

					//---- lblServicio ----
					lblServicio.setText("Servicio:");
					lblServicio.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblServicio, cc.xywh(11, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbServicioSi ----
					rbServicioSi.setText("S\u00ed");
					rbServicioSi.setSelected(true);
					panelGenerico.add(rbServicioSi, cc.xy(13, 19));

					//---- rbServicioNo ----
					rbServicioNo.setText("No");
					panelGenerico.add(rbServicioNo, cc.xy(15, 19));

					//---- lblCobraIce ----
					lblCobraIce.setText("Cobra ICE:");
					lblCobraIce.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblCobraIce, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbCobraIceSi ----
					rbCobraIceSi.setText("S\u00ed");
					panelGenerico.add(rbCobraIceSi, cc.xy(5, 21));

					//---- rbCobraIceNo ----
					rbCobraIceNo.setText("No");
					rbCobraIceNo.setSelected(true);
					panelGenerico.add(rbCobraIceNo, cc.xy(7, 21));

					//---- lblEsInventario ----
					lblEsInventario.setText("Lleva inventario:");
					lblEsInventario.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblEsInventario, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- rbLlevaInventarioSi ----
					rbLlevaInventarioSi.setText("S\u00ed");
					panelGenerico.add(rbLlevaInventarioSi, cc.xy(13, 21));

					//---- rbLlevaInventarioNo ----
					rbLlevaInventarioNo.setText("No");
					rbLlevaInventarioNo.setSelected(true);
					panelGenerico.add(rbLlevaInventarioNo, cc.xy(15, 21));

					//---- lblAceptaDescuento ----
					lblAceptaDescuento.setText("Acepta descuento:");
					lblAceptaDescuento.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblAceptaDescuento, cc.xywh(3, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

					//---- rbAceptaDescuentoSi ----
					rbAceptaDescuentoSi.setText("S\u00ed");
					rbAceptaDescuentoSi.setSelected(true);
					panelGenerico.add(rbAceptaDescuentoSi, cc.xy(5, 23));

					//---- rbAceptaDescuentoNo ----
					rbAceptaDescuentoNo.setText("No");
					panelGenerico.add(rbAceptaDescuentoNo, cc.xy(7, 23));

					//---- lblUsaLote ----
					lblUsaLote.setText("Usa lote:");
					lblUsaLote.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblUsaLote, cc.xywh(11, 23, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbUsaLoteSi ----
					rbUsaLoteSi.setText("S\u00ed");
					panelGenerico.add(rbUsaLoteSi, cc.xy(13, 23));

					//---- rbUsaLoteNo ----
					rbUsaLoteNo.setText("No");
					rbUsaLoteNo.setSelected(true);
					panelGenerico.add(rbUsaLoteNo, cc.xy(15, 23));

					//---- lblOtroImpuesto ----
					lblOtroImpuesto.setText("Otro impuesto [%]:");
					lblOtroImpuesto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblOtroImpuesto, cc.xywh(3, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtOtroImpuesto ----
					txtOtroImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
					panelGenerico.add(txtOtroImpuesto, cc.xywh(5, 25, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblTieneSerie ----
					lblTieneSerie.setText("Tiene serie:");
					lblTieneSerie.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblTieneSerie, cc.xywh(11, 25, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbTieneSerieSi ----
					rbTieneSerieSi.setText("S\u00ed");
					panelGenerico.add(rbTieneSerieSi, cc.xy(13, 25));

					//---- rbTieneSerieNo ----
					rbTieneSerieNo.setText("No");
					rbTieneSerieNo.setSelected(true);
					panelGenerico.add(rbTieneSerieNo, cc.xy(15, 25));

					//---- lblEstadoGenerico ----
					lblEstadoGenerico.setText("Estado:");
					lblEstadoGenerico.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelGenerico.add(lblEstadoGenerico, cc.xywh(3, 27, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelGenerico.add(cmbEstadoGenerico, cc.xywh(5, 27, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				}
				spPanelGenerico.setViewportView(panelGenerico);
			}
			jtpGenerico.addTab("General", spPanelGenerico);


			//======== spPanelProducto ========
			{

				//======== panelProducto ========
				{
					panelProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(12)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
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
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW)
						}));

					//---- lblProveedorId ----
					lblProveedorId.setText("Proveedor:");
					lblProveedorId.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblProveedorId, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(txtProveedor, cc.xywh(5, 3, 13, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
					panelProducto.add(btnBuscarProveedor, cc.xywh(19, 3, 1, 1, CellConstraints.LEFT, CellConstraints.FILL));

					//---- lblSubproveedor ----
					lblSubproveedor.setText("Subproveedor:");
					lblSubproveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblSubproveedor, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(txtSubproveedor, cc.xywh(5, 5, 5, 1));

					//---- lblCodigoBarras ----
					lblCodigoBarras.setText("C\u00f3digo de barras:");
					lblCodigoBarras.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblCodigoBarras, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(txtCodigoBarras, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- cbGenerarCodigoBarras ----
					cbGenerarCodigoBarras.setText("Generar c\u00f3digo de barras");
					cbGenerarCodigoBarras.setFont(new Font("Tahoma", Font.BOLD, 11));
					cbGenerarCodigoBarras.setHorizontalAlignment(SwingConstants.LEFT);
					panelProducto.add(cbGenerarCodigoBarras, cc.xywh(13, 7, 5, 1));

					//---- lblMarca ----
					lblMarca.setText("Marca:");
					lblMarca.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblMarca, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(cmbMarca, cc.xywh(5, 9, 5, 1));

					//---- lblAceptaPromocion ----
					lblAceptaPromocion.setText("Acepta promoci\u00f3n:");
					lblAceptaPromocion.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblAceptaPromocion, cc.xywh(13, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbAceptaPromocionSi ----
					rbAceptaPromocionSi.setText("S\u00ed");
					rbAceptaPromocionSi.setSelected(true);
					panelProducto.add(rbAceptaPromocionSi, cc.xy(15, 9));

					//---- rbAceptaPromocionNo ----
					rbAceptaPromocionNo.setText("No");
					panelProducto.add(rbAceptaPromocionNo, cc.xy(17, 9));

					//---- lblModelo ----
					lblModelo.setText("Modelo:");
					lblModelo.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblModelo, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(cmbModelo, cc.xywh(5, 11, 5, 1));

					//---- lblAceptaDevolucion ----
					lblAceptaDevolucion.setText("Acepta devoluci\u00f3n:");
					lblAceptaDevolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblAceptaDevolucion, cc.xywh(13, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbAceptaDevolucionSi ----
					rbAceptaDevolucionSi.setText("S\u00ed");
					rbAceptaDevolucionSi.setSelected(true);
					panelProducto.add(rbAceptaDevolucionSi, cc.xy(15, 11));

					//---- rbAceptaDevolucionNo ----
					rbAceptaDevolucionNo.setText("No");
					panelProducto.add(rbAceptaDevolucionNo, cc.xy(17, 11));

					//---- lblPresentacionId ----
					lblPresentacionId.setText("Presentaci\u00f3n:");
					lblPresentacionId.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblPresentacionId, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbPresentacionId, cc.xywh(5, 13, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblPermiteVenta ----
					lblPermiteVenta.setText("Permite venta:");
					lblPermiteVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblPermiteVenta, cc.xywh(13, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbPermiteVentaSi ----
					rbPermiteVentaSi.setText("S\u00ed");
					rbPermiteVentaSi.setSelected(true);
					panelProducto.add(rbPermiteVentaSi, cc.xy(15, 13));

					//---- rbPermiteVentaNo ----
					rbPermiteVentaNo.setText("No");
					panelProducto.add(rbPermiteVentaNo, cc.xy(17, 13));

					//---- lblColor ----
					lblColor.setText("Color:");
					lblColor.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblColor, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(cmbColor, cc.xywh(5, 15, 5, 1));

					//---- lblCambioPrecio ----
					lblCambioPrecio.setText("Cambio de precio:");
					lblCambioPrecio.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblCambioPrecio, cc.xywh(13, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- rbCambioPrecioSi ----
					rbCambioPrecioSi.setText("S\u00ed");
					rbCambioPrecioSi.setSelected(true);
					panelProducto.add(rbCambioPrecioSi, cc.xy(15, 15));

					//---- rbCambioPrecioNo ----
					rbCambioPrecioNo.setText("No");
					panelProducto.add(rbCambioPrecioNo, cc.xy(17, 15));

					//---- lblOrigenProducto ----
					lblOrigenProducto.setText("Origen del producto:");
					lblOrigenProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblOrigenProducto, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbOrigenProducto, cc.xywh(5, 17, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblEstadoProducto ----
					lblEstadoProducto.setText("Estado:");
					lblEstadoProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblEstadoProducto, cc.xywh(13, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
					panelProducto.add(cmbEstadoProducto, cc.xywh(15, 17, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblPesoBruto ----
					lblPesoBruto.setText("Peso bruto:");
					lblPesoBruto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblPesoBruto, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(txtPesoBruto, cc.xy(5, 19));

					//---- lblPesoNeto ----
					lblPesoNeto.setText("Peso neto:");
					lblPesoNeto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblPesoNeto, cc.xywh(7, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					panelProducto.add(txtPesoNeto, cc.xy(9, 19));

					//---- lblCosto ----
					lblCosto.setText("Costo:");
					lblCosto.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblCosto, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtCosto ----
					txtCosto.setHorizontalAlignment(SwingConstants.RIGHT);
					panelProducto.add(txtCosto, cc.xywh(5, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

					//---- lblMargenMinimo ----
					lblMargenMinimo.setText("Margen m\u00ednimo:");
					lblMargenMinimo.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblMargenMinimo, cc.xywh(7, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtMargenMinimo ----
					txtMargenMinimo.setHorizontalAlignment(SwingConstants.RIGHT);
					panelProducto.add(txtMargenMinimo, cc.xy(9, 21));

					//---- lblRebate ----
					lblRebate.setText("Rebate:");
					lblRebate.setFont(new Font("Tahoma", Font.BOLD, 11));
					panelProducto.add(lblRebate, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

					//---- txtRebate ----
					txtRebate.setHorizontalAlignment(SwingConstants.RIGHT);
					panelProducto.add(txtRebate, cc.xywh(13, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

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
					panelProducto.add(panel1, cc.xywh(3, 23, 16, 1));

					//======== scpProducto ========
					{

						//---- tblProductos ----
						tblProductos.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Proveedor", "Descripci\u00f3n", "Estado"
							}
						) {
							boolean[] columnEditable = new boolean[] {
								false, false, false
							};
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						{
							TableColumnModel cm = tblProductos.getColumnModel();
							cm.getColumn(0).setMinWidth(150);
							cm.getColumn(0).setPreferredWidth(150);
							cm.getColumn(1).setMinWidth(150);
							cm.getColumn(1).setPreferredWidth(150);
							cm.getColumn(2).setResizable(false);
							cm.getColumn(2).setMinWidth(70);
							cm.getColumn(2).setMaxWidth(70);
							cm.getColumn(2).setPreferredWidth(70);
						}
						tblProductos.setPreferredScrollableViewportSize(new Dimension(450, 150));
						tblProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						scpProducto.setViewportView(tblProductos);
					}
					panelProducto.add(scpProducto, cc.xywh(3, 25, 17, 5));
				}
				spPanelProducto.setViewportView(panelProducto);
			}
			jtpGenerico.addTab("Productos", spPanelProducto);

		}
		add(jtpGenerico, cc.xy(1, 1));

		//---- bgCobraIvaProveedor ----
		ButtonGroup bgCobraIvaProveedor = new ButtonGroup();
		bgCobraIvaProveedor.add(rbCobraIvaProveedorSi);
		bgCobraIvaProveedor.add(rbCobraIvaProveedorNo);

		//---- bgCambioDescripcion ----
		ButtonGroup bgCambioDescripcion = new ButtonGroup();
		bgCambioDescripcion.add(rbCambioDescripcionSi);
		bgCambioDescripcion.add(rbCambioDescripcionNo);

		//---- bgCobraIvaCliente ----
		ButtonGroup bgCobraIvaCliente = new ButtonGroup();
		bgCobraIvaCliente.add(rbCobraIvaClienteSi);
		bgCobraIvaCliente.add(rbCobraIvaClienteNo);

		//---- bgServicio ----
		ButtonGroup bgServicio = new ButtonGroup();
		bgServicio.add(rbServicioSi);
		bgServicio.add(rbServicioNo);

		//---- bgCobraIce ----
		ButtonGroup bgCobraIce = new ButtonGroup();
		bgCobraIce.add(rbCobraIceSi);
		bgCobraIce.add(rbCobraIceNo);

		//---- bgLlevaInventario ----
		ButtonGroup bgLlevaInventario = new ButtonGroup();
		bgLlevaInventario.add(rbLlevaInventarioSi);
		bgLlevaInventario.add(rbLlevaInventarioNo);

		//---- bgAceptaDescuento ----
		ButtonGroup bgAceptaDescuento = new ButtonGroup();
		bgAceptaDescuento.add(rbAceptaDescuentoSi);
		bgAceptaDescuento.add(rbAceptaDescuentoNo);

		//---- bgUsaLote ----
		ButtonGroup bgUsaLote = new ButtonGroup();
		bgUsaLote.add(rbUsaLoteSi);
		bgUsaLote.add(rbUsaLoteNo);

		//---- bgTieneSerie ----
		ButtonGroup bgTieneSerie = new ButtonGroup();
		bgTieneSerie.add(rbTieneSerieSi);
		bgTieneSerie.add(rbTieneSerieNo);

		//---- bgAceptaPromocion ----
		ButtonGroup bgAceptaPromocion = new ButtonGroup();
		bgAceptaPromocion.add(rbAceptaPromocionSi);
		bgAceptaPromocion.add(rbAceptaPromocionNo);

		//---- bgAceptaDevolucion ----
		ButtonGroup bgAceptaDevolucion = new ButtonGroup();
		bgAceptaDevolucion.add(rbAceptaDevolucionSi);
		bgAceptaDevolucion.add(rbAceptaDevolucionNo);

		//---- bgPermiteVenta ----
		ButtonGroup bgPermiteVenta = new ButtonGroup();
		bgPermiteVenta.add(rbPermiteVentaSi);
		bgPermiteVenta.add(rbPermiteVentaNo);

		//---- bgCambioPrecio ----
		ButtonGroup bgCambioPrecio = new ButtonGroup();
		bgCambioPrecio.add(rbCambioPrecioSi);
		bgCambioPrecio.add(rbCambioPrecioNo);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpGenerico;
	private JScrollPane spPanelGenerico;
	private JPanel panelGenerico;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblAbreviado;
	private JTextField txtAbreviado;
	private JLabel lblTipo;
	private JComboBox cmbTipo;
	private JLabel lblFamiliaGenerico;
	private JComboBox cmbFamiliaGenerico;
	private JLabel lblTipoProducto;
	private JComboBox cmbTipoProducto;
	private JLabel lblLinea;
	private JComboBox cmbLinea;
	private JLabel lblMedida;
	private JComboBox cmbMedida;
	private JLabel lblCobraIvaProveedor;
	private JRadioButton rbCobraIvaProveedorSi;
	private JRadioButton rbCobraIvaProveedorNo;
	private JLabel lblCambiaDescripcion;
	private JRadioButton rbCambioDescripcionSi;
	private JRadioButton rbCambioDescripcionNo;
	private JLabel lblCobraIvaCliente;
	private JRadioButton rbCobraIvaClienteSi;
	private JRadioButton rbCobraIvaClienteNo;
	private JLabel lblServicio;
	private JRadioButton rbServicioSi;
	private JRadioButton rbServicioNo;
	private JLabel lblCobraIce;
	private JRadioButton rbCobraIceSi;
	private JRadioButton rbCobraIceNo;
	private JLabel lblEsInventario;
	private JRadioButton rbLlevaInventarioSi;
	private JRadioButton rbLlevaInventarioNo;
	private JLabel lblAceptaDescuento;
	private JRadioButton rbAceptaDescuentoSi;
	private JRadioButton rbAceptaDescuentoNo;
	private JLabel lblUsaLote;
	private JRadioButton rbUsaLoteSi;
	private JRadioButton rbUsaLoteNo;
	private JLabel lblOtroImpuesto;
	private JTextField txtOtroImpuesto;
	private JLabel lblTieneSerie;
	private JRadioButton rbTieneSerieSi;
	private JRadioButton rbTieneSerieNo;
	private JLabel lblEstadoGenerico;
	private JComboBox cmbEstadoGenerico;
	private JScrollPane spPanelProducto;
	private JPanel panelProducto;
	private JLabel lblProveedorId;
	private JTextField txtProveedor;
	private JButton btnBuscarProveedor;
	private JLabel lblSubproveedor;
	private JTextField txtSubproveedor;
	private JLabel lblCodigoBarras;
	private JTextField txtCodigoBarras;
	private JCheckBox cbGenerarCodigoBarras;
	private JLabel lblMarca;
	private JComboBox cmbMarca;
	private JLabel lblAceptaPromocion;
	private JRadioButton rbAceptaPromocionSi;
	private JRadioButton rbAceptaPromocionNo;
	private JLabel lblModelo;
	private JComboBox cmbModelo;
	private JLabel lblAceptaDevolucion;
	private JRadioButton rbAceptaDevolucionSi;
	private JRadioButton rbAceptaDevolucionNo;
	private JLabel lblPresentacionId;
	private JComboBox cmbPresentacionId;
	private JLabel lblPermiteVenta;
	private JRadioButton rbPermiteVentaSi;
	private JRadioButton rbPermiteVentaNo;
	private JLabel lblColor;
	private JComboBox cmbColor;
	private JLabel lblCambioPrecio;
	private JRadioButton rbCambioPrecioSi;
	private JRadioButton rbCambioPrecioNo;
	private JLabel lblOrigenProducto;
	private JComboBox cmbOrigenProducto;
	private JLabel lblEstadoProducto;
	private JComboBox cmbEstadoProducto;
	private JLabel lblPesoBruto;
	private JTextField txtPesoBruto;
	private JLabel lblPesoNeto;
	private JTextField txtPesoNeto;
	private JLabel lblCosto;
	private JTextField txtCosto;
	private JLabel lblMargenMinimo;
	private JTextField txtMargenMinimo;
	private JLabel lblRebate;
	private JTextField txtRebate;
	private JPanel panel1;
	private JButton btnAgregarProducto;
	private JButton btnActualizarProducto;
	private JButton btnEliminarProducto;
	private JScrollPane scpProducto;
	private JTable tblProductos;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideTabbedPane getJtpGenerico() {
		return jtpGenerico;
	}

	public void setJtpGenerico(JideTabbedPane jtpGenerico) {
		this.jtpGenerico = jtpGenerico;
	}

	public JScrollPane getSpPanelGenerico() {
		return spPanelGenerico;
	}

	public void setSpPanelGenerico(JScrollPane spPanelGenerico) {
		this.spPanelGenerico = spPanelGenerico;
	}

	public JPanel getPanelGenerico() {
		return panelGenerico;
	}

	public void setPanelGenerico(JPanel panelGenerico) {
		this.panelGenerico = panelGenerico;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JLabel getLblAbreviado() {
		return lblAbreviado;
	}

	public void setLblAbreviado(JLabel lblAbreviado) {
		this.lblAbreviado = lblAbreviado;
	}

	public JTextField getTxtAbreviado() {
		return txtAbreviado;
	}

	public void setTxtAbreviado(JTextField txtAbreviado) {
		this.txtAbreviado = txtAbreviado;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}

	public JComboBox getCmbTipo() {
		return cmbTipo;
	}

	public void setCmbTipo(JComboBox cmbTipo) {
		this.cmbTipo = cmbTipo;
	}

	public JLabel getLblFamiliaGenerico() {
		return lblFamiliaGenerico;
	}

	public void setLblFamiliaGenerico(JLabel lblFamiliaGenerico) {
		this.lblFamiliaGenerico = lblFamiliaGenerico;
	}

	public JComboBox getCmbFamiliaGenerico() {
		return cmbFamiliaGenerico;
	}

	public void setCmbFamiliaGenerico(JComboBox cmbFamiliaGenerico) {
		this.cmbFamiliaGenerico = cmbFamiliaGenerico;
	}

	public JLabel getLblTipoProducto() {
		return lblTipoProducto;
	}

	public void setLblTipoProducto(JLabel lblTipoProducto) {
		this.lblTipoProducto = lblTipoProducto;
	}

	public JComboBox getCmbTipoProducto() {
		return cmbTipoProducto;
	}

	public void setCmbTipoProducto(JComboBox cmbTipoProducto) {
		this.cmbTipoProducto = cmbTipoProducto;
	}

	public JLabel getLblLinea() {
		return lblLinea;
	}

	public void setLblLinea(JLabel lblLinea) {
		this.lblLinea = lblLinea;
	}

	public JComboBox getCmbLinea() {
		return cmbLinea;
	}

	public void setCmbLinea(JComboBox cmbLinea) {
		this.cmbLinea = cmbLinea;
	}

	public JLabel getLblMedida() {
		return lblMedida;
	}

	public void setLblMedida(JLabel lblMedida) {
		this.lblMedida = lblMedida;
	}

	public JComboBox getCmbMedida() {
		return cmbMedida;
	}

	public void setCmbMedida(JComboBox cmbMedida) {
		this.cmbMedida = cmbMedida;
	}

	public JLabel getLblCobraIvaProveedor() {
		return lblCobraIvaProveedor;
	}

	public void setLblCobraIvaProveedor(JLabel lblCobraIvaProveedor) {
		this.lblCobraIvaProveedor = lblCobraIvaProveedor;
	}

	public JRadioButton getRbCobraIvaProveedorSi() {
		return rbCobraIvaProveedorSi;
	}

	public void setRbCobraIvaProveedorSi(JRadioButton rbCobraIvaProveedorSi) {
		this.rbCobraIvaProveedorSi = rbCobraIvaProveedorSi;
	}

	public JRadioButton getRbCobraIvaProveedorNo() {
		return rbCobraIvaProveedorNo;
	}

	public void setRbCobraIvaProveedorNo(JRadioButton rbCobraIvaProveedorNo) {
		this.rbCobraIvaProveedorNo = rbCobraIvaProveedorNo;
	}

	public JLabel getLblCambiaDescripcion() {
		return lblCambiaDescripcion;
	}

	public void setLblCambiaDescripcion(JLabel lblCambiaDescripcion) {
		this.lblCambiaDescripcion = lblCambiaDescripcion;
	}

	public JRadioButton getRbCambioDescripcionSi() {
		return rbCambioDescripcionSi;
	}

	public void setRbCambioDescripcionSi(JRadioButton rbCambioDescripcionSi) {
		this.rbCambioDescripcionSi = rbCambioDescripcionSi;
	}

	public JRadioButton getRbCambioDescripcionNo() {
		return rbCambioDescripcionNo;
	}

	public void setRbCambioDescripcionNo(JRadioButton rbCambioDescripcionNo) {
		this.rbCambioDescripcionNo = rbCambioDescripcionNo;
	}

	public JLabel getLblCobraIvaCliente() {
		return lblCobraIvaCliente;
	}

	public void setLblCobraIvaCliente(JLabel lblCobraIvaCliente) {
		this.lblCobraIvaCliente = lblCobraIvaCliente;
	}

	public JRadioButton getRbCobraIvaClienteSi() {
		return rbCobraIvaClienteSi;
	}

	public void setRbCobraIvaClienteSi(JRadioButton rbCobraIvaClienteSi) {
		this.rbCobraIvaClienteSi = rbCobraIvaClienteSi;
	}

	public JRadioButton getRbCobraIvaClienteNo() {
		return rbCobraIvaClienteNo;
	}

	public void setRbCobraIvaClienteNo(JRadioButton rbCobraIvaClienteNo) {
		this.rbCobraIvaClienteNo = rbCobraIvaClienteNo;
	}

	public JLabel getLblServicio() {
		return lblServicio;
	}

	public void setLblServicio(JLabel lblServicio) {
		this.lblServicio = lblServicio;
	}

	public JRadioButton getRbServicioSi() {
		return rbServicioSi;
	}

	public void setRbServicioSi(JRadioButton rbServicioSi) {
		this.rbServicioSi = rbServicioSi;
	}

	public JRadioButton getRbServicioNo() {
		return rbServicioNo;
	}

	public void setRbServicioNo(JRadioButton rbServicioNo) {
		this.rbServicioNo = rbServicioNo;
	}

	public JLabel getLblCobraIce() {
		return lblCobraIce;
	}

	public void setLblCobraIce(JLabel lblCobraIce) {
		this.lblCobraIce = lblCobraIce;
	}

	public JRadioButton getRbCobraIceSi() {
		return rbCobraIceSi;
	}

	public void setRbCobraIceSi(JRadioButton rbCobraIceSi) {
		this.rbCobraIceSi = rbCobraIceSi;
	}

	public JRadioButton getRbCobraIceNo() {
		return rbCobraIceNo;
	}

	public void setRbCobraIceNo(JRadioButton rbCobraIceNo) {
		this.rbCobraIceNo = rbCobraIceNo;
	}

	public JLabel getLblEsInventario() {
		return lblEsInventario;
	}

	public void setLblEsInventario(JLabel lblEsInventario) {
		this.lblEsInventario = lblEsInventario;
	}

	public JRadioButton getRbLlevaInventarioSi() {
		return rbLlevaInventarioSi;
	}

	public void setRbLlevaInventarioSi(JRadioButton rbLlevaInventarioSi) {
		this.rbLlevaInventarioSi = rbLlevaInventarioSi;
	}

	public JRadioButton getRbLlevaInventarioNo() {
		return rbLlevaInventarioNo;
	}

	public void setRbLlevaInventarioNo(JRadioButton rbLlevaInventarioNo) {
		this.rbLlevaInventarioNo = rbLlevaInventarioNo;
	}

	public JLabel getLblAceptaDescuento() {
		return lblAceptaDescuento;
	}

	public void setLblAceptaDescuento(JLabel lblAceptaDescuento) {
		this.lblAceptaDescuento = lblAceptaDescuento;
	}

	public JRadioButton getRbAceptaDescuentoSi() {
		return rbAceptaDescuentoSi;
	}

	public void setRbAceptaDescuentoSi(JRadioButton rbAceptaDescuentoSi) {
		this.rbAceptaDescuentoSi = rbAceptaDescuentoSi;
	}

	public JRadioButton getRbAceptaDescuentoNo() {
		return rbAceptaDescuentoNo;
	}

	public void setRbAceptaDescuentoNo(JRadioButton rbAceptaDescuentoNo) {
		this.rbAceptaDescuentoNo = rbAceptaDescuentoNo;
	}

	public JLabel getLblUsaLote() {
		return lblUsaLote;
	}

	public void setLblUsaLote(JLabel lblUsaLote) {
		this.lblUsaLote = lblUsaLote;
	}

	public JRadioButton getRbUsaLoteSi() {
		return rbUsaLoteSi;
	}

	public void setRbUsaLoteSi(JRadioButton rbUsaLoteSi) {
		this.rbUsaLoteSi = rbUsaLoteSi;
	}

	public JRadioButton getRbUsaLoteNo() {
		return rbUsaLoteNo;
	}

	public void setRbUsaLoteNo(JRadioButton rbUsaLoteNo) {
		this.rbUsaLoteNo = rbUsaLoteNo;
	}

	public JLabel getLblOtroImpuesto() {
		return lblOtroImpuesto;
	}

	public void setLblOtroImpuesto(JLabel lblOtroImpuesto) {
		this.lblOtroImpuesto = lblOtroImpuesto;
	}

	public JTextField getTxtOtroImpuesto() {
		return txtOtroImpuesto;
	}

	public void setTxtOtroImpuesto(JTextField txtOtroImpuesto) {
		this.txtOtroImpuesto = txtOtroImpuesto;
	}

	public JLabel getLblTieneSerie() {
		return lblTieneSerie;
	}

	public void setLblTieneSerie(JLabel lblTieneSerie) {
		this.lblTieneSerie = lblTieneSerie;
	}

	public JRadioButton getRbTieneSerieSi() {
		return rbTieneSerieSi;
	}

	public void setRbTieneSerieSi(JRadioButton rbTieneSerieSi) {
		this.rbTieneSerieSi = rbTieneSerieSi;
	}

	public JRadioButton getRbTieneSerieNo() {
		return rbTieneSerieNo;
	}

	public void setRbTieneSerieNo(JRadioButton rbTieneSerieNo) {
		this.rbTieneSerieNo = rbTieneSerieNo;
	}

	public JLabel getLblEstadoGenerico() {
		return lblEstadoGenerico;
	}

	public void setLblEstadoGenerico(JLabel lblEstadoGenerico) {
		this.lblEstadoGenerico = lblEstadoGenerico;
	}

	public JComboBox getCmbEstadoGenerico() {
		return cmbEstadoGenerico;
	}

	public void setCmbEstadoGenerico(JComboBox cmbEstadoGenerico) {
		this.cmbEstadoGenerico = cmbEstadoGenerico;
	}

	public JScrollPane getSpPanelProducto() {
		return spPanelProducto;
	}

	public void setSpPanelProducto(JScrollPane spPanelProducto) {
		this.spPanelProducto = spPanelProducto;
	}

	public JPanel getPanelProducto() {
		return panelProducto;
	}

	public void setPanelProducto(JPanel panelProducto) {
		this.panelProducto = panelProducto;
	}

	public JLabel getLblProveedorId() {
		return lblProveedorId;
	}

	public void setLblProveedorId(JLabel lblProveedorId) {
		this.lblProveedorId = lblProveedorId;
	}

	public JTextField getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(JTextField txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public JButton getBtnBuscarProveedor() {
		return btnBuscarProveedor;
	}

	public void setBtnBuscarProveedor(JButton btnBuscarProveedor) {
		this.btnBuscarProveedor = btnBuscarProveedor;
	}

	public JLabel getLblSubproveedor() {
		return lblSubproveedor;
	}

	public void setLblSubproveedor(JLabel lblSubproveedor) {
		this.lblSubproveedor = lblSubproveedor;
	}

	public JTextField getTxtSubproveedor() {
		return txtSubproveedor;
	}

	public void setTxtSubproveedor(JTextField txtSubproveedor) {
		this.txtSubproveedor = txtSubproveedor;
	}

	public JLabel getLblCodigoBarras() {
		return lblCodigoBarras;
	}

	public void setLblCodigoBarras(JLabel lblCodigoBarras) {
		this.lblCodigoBarras = lblCodigoBarras;
	}

	public JTextField getTxtCodigoBarras() {
		return txtCodigoBarras;
	}

	public void setTxtCodigoBarras(JTextField txtCodigoBarras) {
		this.txtCodigoBarras = txtCodigoBarras;
	}

	public JCheckBox getCbGenerarCodigoBarras() {
		return cbGenerarCodigoBarras;
	}

	public void setCbGenerarCodigoBarras(JCheckBox cbGenerarCodigoBarras) {
		this.cbGenerarCodigoBarras = cbGenerarCodigoBarras;
	}

	public JLabel getLblMarca() {
		return lblMarca;
	}

	public void setLblMarca(JLabel lblMarca) {
		this.lblMarca = lblMarca;
	}

	public JComboBox getCmbMarca() {
		return cmbMarca;
	}

	public void setCmbMarca(JComboBox cmbMarca) {
		this.cmbMarca = cmbMarca;
	}

	public JLabel getLblAceptaPromocion() {
		return lblAceptaPromocion;
	}

	public void setLblAceptaPromocion(JLabel lblAceptaPromocion) {
		this.lblAceptaPromocion = lblAceptaPromocion;
	}

	public JRadioButton getRbAceptaPromocionSi() {
		return rbAceptaPromocionSi;
	}

	public void setRbAceptaPromocionSi(JRadioButton rbAceptaPromocionSi) {
		this.rbAceptaPromocionSi = rbAceptaPromocionSi;
	}

	public JRadioButton getRbAceptaPromocionNo() {
		return rbAceptaPromocionNo;
	}

	public void setRbAceptaPromocionNo(JRadioButton rbAceptaPromocionNo) {
		this.rbAceptaPromocionNo = rbAceptaPromocionNo;
	}

	public JLabel getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(JLabel lblModelo) {
		this.lblModelo = lblModelo;
	}

	public JComboBox getCmbModelo() {
		return cmbModelo;
	}

	public void setCmbModelo(JComboBox cmbModelo) {
		this.cmbModelo = cmbModelo;
	}

	public JLabel getLblAceptaDevolucion() {
		return lblAceptaDevolucion;
	}

	public void setLblAceptaDevolucion(JLabel lblAceptaDevolucion) {
		this.lblAceptaDevolucion = lblAceptaDevolucion;
	}

	public JRadioButton getRbAceptaDevolucionSi() {
		return rbAceptaDevolucionSi;
	}

	public void setRbAceptaDevolucionSi(JRadioButton rbAceptaDevolucionSi) {
		this.rbAceptaDevolucionSi = rbAceptaDevolucionSi;
	}

	public JRadioButton getRbAceptaDevolucionNo() {
		return rbAceptaDevolucionNo;
	}

	public void setRbAceptaDevolucionNo(JRadioButton rbAceptaDevolucionNo) {
		this.rbAceptaDevolucionNo = rbAceptaDevolucionNo;
	}

	public JLabel getLblPresentacionId() {
		return lblPresentacionId;
	}

	public void setLblPresentacionId(JLabel lblPresentacionId) {
		this.lblPresentacionId = lblPresentacionId;
	}

	public JComboBox getCmbPresentacionId() {
		return cmbPresentacionId;
	}

	public void setCmbPresentacionId(JComboBox cmbPresentacionId) {
		this.cmbPresentacionId = cmbPresentacionId;
	}

	public JLabel getLblPermiteVenta() {
		return lblPermiteVenta;
	}

	public void setLblPermiteVenta(JLabel lblPermiteVenta) {
		this.lblPermiteVenta = lblPermiteVenta;
	}

	public JRadioButton getRbPermiteVentaSi() {
		return rbPermiteVentaSi;
	}

	public void setRbPermiteVentaSi(JRadioButton rbPermiteVentaSi) {
		this.rbPermiteVentaSi = rbPermiteVentaSi;
	}

	public JRadioButton getRbPermiteVentaNo() {
		return rbPermiteVentaNo;
	}

	public void setRbPermiteVentaNo(JRadioButton rbPermiteVentaNo) {
		this.rbPermiteVentaNo = rbPermiteVentaNo;
	}

	public JLabel getLblColor() {
		return lblColor;
	}

	public void setLblColor(JLabel lblColor) {
		this.lblColor = lblColor;
	}

	public JComboBox getCmbColor() {
		return cmbColor;
	}

	public void setCmbColor(JComboBox cmbColor) {
		this.cmbColor = cmbColor;
	}

	public JLabel getLblCambioPrecio() {
		return lblCambioPrecio;
	}

	public void setLblCambioPrecio(JLabel lblCambioPrecio) {
		this.lblCambioPrecio = lblCambioPrecio;
	}

	public JRadioButton getRbCambioPrecioSi() {
		return rbCambioPrecioSi;
	}

	public void setRbCambioPrecioSi(JRadioButton rbCambioPrecioSi) {
		this.rbCambioPrecioSi = rbCambioPrecioSi;
	}

	public JRadioButton getRbCambioPrecioNo() {
		return rbCambioPrecioNo;
	}

	public void setRbCambioPrecioNo(JRadioButton rbCambioPrecioNo) {
		this.rbCambioPrecioNo = rbCambioPrecioNo;
	}

	public JLabel getLblOrigenProducto() {
		return lblOrigenProducto;
	}

	public void setLblOrigenProducto(JLabel lblOrigenProducto) {
		this.lblOrigenProducto = lblOrigenProducto;
	}

	public JComboBox getCmbOrigenProducto() {
		return cmbOrigenProducto;
	}

	public void setCmbOrigenProducto(JComboBox cmbOrigenProducto) {
		this.cmbOrigenProducto = cmbOrigenProducto;
	}

	public JLabel getLblEstadoProducto() {
		return lblEstadoProducto;
	}

	public void setLblEstadoProducto(JLabel lblEstadoProducto) {
		this.lblEstadoProducto = lblEstadoProducto;
	}

	public JComboBox getCmbEstadoProducto() {
		return cmbEstadoProducto;
	}

	public void setCmbEstadoProducto(JComboBox cmbEstadoProducto) {
		this.cmbEstadoProducto = cmbEstadoProducto;
	}

	public JLabel getLblPesoBruto() {
		return lblPesoBruto;
	}

	public void setLblPesoBruto(JLabel lblPesoBruto) {
		this.lblPesoBruto = lblPesoBruto;
	}

	public JTextField getTxtPesoBruto() {
		return txtPesoBruto;
	}

	public void setTxtPesoBruto(JTextField txtPesoBruto) {
		this.txtPesoBruto = txtPesoBruto;
	}

	public JLabel getLblPesoNeto() {
		return lblPesoNeto;
	}

	public void setLblPesoNeto(JLabel lblPesoNeto) {
		this.lblPesoNeto = lblPesoNeto;
	}

	public JTextField getTxtPesoNeto() {
		return txtPesoNeto;
	}

	public void setTxtPesoNeto(JTextField txtPesoNeto) {
		this.txtPesoNeto = txtPesoNeto;
	}

	public JLabel getLblCosto() {
		return lblCosto;
	}

	public void setLblCosto(JLabel lblCosto) {
		this.lblCosto = lblCosto;
	}

	public JTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(JTextField txtCosto) {
		this.txtCosto = txtCosto;
	}

	public JLabel getLblMargenMinimo() {
		return lblMargenMinimo;
	}

	public void setLblMargenMinimo(JLabel lblMargenMinimo) {
		this.lblMargenMinimo = lblMargenMinimo;
	}

	public JTextField getTxtMargenMinimo() {
		return txtMargenMinimo;
	}

	public void setTxtMargenMinimo(JTextField txtMargenMinimo) {
		this.txtMargenMinimo = txtMargenMinimo;
	}

	public JLabel getLblRebate() {
		return lblRebate;
	}

	public void setLblRebate(JLabel lblRebate) {
		this.lblRebate = lblRebate;
	}

	public JTextField getTxtRebate() {
		return txtRebate;
	}

	public void setTxtRebate(JTextField txtRebate) {
		this.txtRebate = txtRebate;
	}

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}

	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}

	public JButton getBtnActualizarProducto() {
		return btnActualizarProducto;
	}

	public void setBtnActualizarProducto(JButton btnActualizarProducto) {
		this.btnActualizarProducto = btnActualizarProducto;
	}

	public JButton getBtnEliminarProducto() {
		return btnEliminarProducto;
	}

	public void setBtnEliminarProducto(JButton btnEliminarProducto) {
		this.btnEliminarProducto = btnEliminarProducto;
	}

	public JScrollPane getScpProducto() {
		return scpProducto;
	}

	public void setScpProducto(JScrollPane scpProducto) {
		this.scpProducto = scpProducto;
	}

	public JTable getTblProductos() {
		return tblProductos;
	}

	public void setTblProductos(JTable tblProductos) {
		this.tblProductos = tblProductos;
	}
}
