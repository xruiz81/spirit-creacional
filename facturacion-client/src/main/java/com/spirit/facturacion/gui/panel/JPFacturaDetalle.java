package com.spirit.facturacion.gui.panel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;



/**
 * @author Antonio Seiler
 */
public class JPFacturaDetalle  extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	
	public JPFacturaDetalle() {
		initComponents();
		setName("Factura Detalle");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblDocumento = new JLabel();
		cmbDocumento = new JComboBox();
		fsDocumentoAplica = compFactory.createSeparator("Documento Aplica:");
		lblMotivo = new JLabel();
		cmbMotivo = new JComboBox();
		lblTipoDocumentoApl = new JLabel();
		txtTipoDocumento = new JTextField();
		lblDocumentoApl = new JLabel();
		txtDocumentoApl = new JTextField();
		lblNumeroApl = new JLabel();
		txtNumeroApl = new JTextField();
		lblProductoApl = new JLabel();
		txtProductoApl = new JTextField();
		fsProcedenciaFactura = compFactory.createSeparator("Procedencia de la Factura Detalle");
		lblOrdenMedios = new JLabel();
		cmbOrdenMedios = new JComboBox();
		lblPresupuesto = new JLabel();
		cmbPresupuesto = new JComboBox();
		fsOtraInformacion = compFactory.createSeparator("Otra Informacion:");
		lblProducto = new JLabel();
		cmbProducto = new JComboBox();
		lblLote = new JLabel();
		lblLinea = new JLabel();
		cmbLote = new JComboBox();
		txtLinea = new JTextField();
		lblDescripcion = new JLabel();
		txtDescripcion = new JTextField();
		lblCantidadPedida = new JLabel();
		lblCantidad = new JLabel();
		txtCantidad = new JTextField();
		txtCantidadPedida = new JTextField();
		lblBackorder = new JLabel();
		lblPrecioReal = new JLabel();
		txtPrecioReal = new JTextField();
		txtBackorder = new JTextField();
		lblPrecioVenta = new JLabel();
		txtPrecioVenta = new JTextField();
		lblCosto = new JLabel();
		txtCosto = new JTextField();
		lblValor = new JLabel();
		txtValor = new JTextField();
		lblDescuento = new JLabel();
		lblIVA = new JLabel();
		txtIVA = new JTextField();
		lblICE = new JLabel();
		txtICE = new JTextField();
		lblOtroImpuesto = new JLabel();
		txtOtroImpuesto = new JTextField();
		txtDescuento = new JTextField();
		lblValorTotal = new JLabel();
		txtValorTotal = new JTextField();
		fsDetalles = compFactory.createSeparator("Detalles:");
		scrollPane1 = new JScrollPane();
		txtDetalle = new JTextArea();
		fsTotales = compFactory.createSeparator("Totales:");
		lblValorFinal = new JLabel();
		txtValorFinal = new JTextField();
		lblDescuentoFinal = new JLabel();
		txtDescuentoFinal = new JTextField();
		lblIVAFinal = new JLabel();
		lblICEFinal = new JLabel();
		txtICEFinal = new JTextField();
		lblOtroImpuestoFinal = new JLabel();
		txtOtroImpuestoFinal = new JTextField();
		lblTotalFinal = new JLabel();
		txtTotalFinal = new JTextField();
		txtIVAFinal = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== JPFactDetalle ========
		{
			setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec("max(default;10dlu)"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;50dlu):grow"),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.RIGHT, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec("max(default;10dlu):grow")
				},
				new RowSpec[] {
					new RowSpec("max(default;10dlu)"),
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
					new RowSpec(RowSpec.FILL, Sizes.dluY(40), FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			((FormLayout)getLayout()).setColumnGroups(new int[][] {{3, 7, 11}, {5, 9, 13}});
			
			//---- lblDocumento ----
			lblDocumento.setText("Documento:");
			add(lblDocumento, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(cmbDocumento, cc.xy(5, 3));
			add(fsDocumentoAplica, cc.xywh(3, 5, 11, 1));
			
			//---- lblMotivo ----
			lblMotivo.setText("Motivo:");
			add(lblMotivo, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
			add(cmbMotivo, cc.xy(5, 7));
			
			//---- lblTipoDocumentoApl ----
			lblTipoDocumentoApl.setText("Tipo Documento:");
			add(lblTipoDocumentoApl, cc.xywh(7, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtTipoDocumento, cc.xywh(9, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblDocumentoApl ----
			lblDocumentoApl.setText("Documento:");
			add(lblDocumentoApl, cc.xywh(11, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtDocumentoApl, cc.xywh(13, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblNumeroApl ----
			lblNumeroApl.setText("Numero:");
			add(lblNumeroApl, cc.xy(3, 9));
			add(txtNumeroApl, cc.xywh(5, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblProductoApl ----
			lblProductoApl.setText("Producto:");
			add(lblProductoApl, cc.xy(7, 9));
			add(txtProductoApl, cc.xywh(9, 9, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(fsProcedenciaFactura, cc.xywh(3, 11, 11, 1));
			
			//---- lblOrdenMedios ----
			lblOrdenMedios.setText("Orden de Medios:");
			add(lblOrdenMedios, cc.xywh(3, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(cmbOrdenMedios, cc.xy(5, 13));
			
			//---- lblPresupuesto ----
			lblPresupuesto.setText("Presupuesto:");
			add(lblPresupuesto, cc.xywh(7, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(cmbPresupuesto, cc.xy(9, 13));
			add(fsOtraInformacion, cc.xywh(3, 15, 11, 1));
			
			//---- lblProducto ----
			lblProducto.setText("Producto:");
			add(lblProducto, cc.xywh(3, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(cmbProducto, cc.xy(5, 17));
			
			//---- lblLote ----
			lblLote.setText("Lote:");
			add(lblLote, cc.xywh(7, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblLinea ----
			lblLinea.setText("Linea:");
			add(lblLinea, cc.xywh(11, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(cmbLote, cc.xy(9, 17));
			add(txtLinea, cc.xywh(13, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblDescripcion ----
			lblDescripcion.setText("Descripcion:");
			add(lblDescripcion, cc.xywh(3, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtDescripcion, cc.xywh(5, 19, 9, 1));
			
			//---- lblCantidadPedida ----
			lblCantidadPedida.setText("Cantidad Pedida:");
			add(lblCantidadPedida, cc.xywh(3, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblCantidad ----
			lblCantidad.setText("Cantidad:");
			add(lblCantidad, cc.xywh(7, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtCantidad ----
			txtCantidad.setHorizontalAlignment(JTextField.RIGHT);
			add(txtCantidad, cc.xywh(9, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtCantidadPedida, cc.xywh(5, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblBackorder ----
			lblBackorder.setText("Backorder:");
			add(lblBackorder, cc.xywh(11, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblPrecioReal ----
			lblPrecioReal.setText("Precio Real:");
			add(lblPrecioReal, cc.xywh(3, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtPrecioReal ----
			txtPrecioReal.setHorizontalAlignment(JTextField.RIGHT);
			add(txtPrecioReal, cc.xywh(5, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtBackorder, cc.xywh(13, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblPrecioVenta ----
			lblPrecioVenta.setText("Precio Venta:");
			add(lblPrecioVenta, cc.xywh(7, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtPrecioVenta ----
			txtPrecioVenta.setHorizontalAlignment(JTextField.RIGHT);
			add(txtPrecioVenta, cc.xywh(9, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblCosto ----
			lblCosto.setText("Costo:");
			add(lblCosto, cc.xywh(11, 23, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtCosto ----
			txtCosto.setHorizontalAlignment(JTextField.RIGHT);
			add(txtCosto, cc.xy(13, 23));
			
			//---- lblValor ----
			lblValor.setText("Valor:");
			add(lblValor, cc.xywh(3, 25, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtValor ----
			txtValor.setHorizontalAlignment(JTextField.RIGHT);
			add(txtValor, cc.xy(5, 25));
			
			//---- lblDescuento ----
			lblDescuento.setText("Descuento:");
			add(lblDescuento, cc.xywh(7, 25, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblIVA ----
			lblIVA.setText("IVA:");
			add(lblIVA, cc.xywh(11, 25, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtIVA ----
			txtIVA.setHorizontalAlignment(JTextField.RIGHT);
			add(txtIVA, cc.xy(13, 25));
			
			//---- lblICE ----
			lblICE.setText("ICE:");
			add(lblICE, cc.xywh(3, 27, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtICE ----
			txtICE.setHorizontalAlignment(JTextField.RIGHT);
			add(txtICE, cc.xy(5, 27));
			
			//---- lblOtroImpuesto ----
			lblOtroImpuesto.setText("Otro Impuesto:");
			add(lblOtroImpuesto, cc.xywh(7, 27, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- txtOtroImpuesto ----
			txtOtroImpuesto.setHorizontalAlignment(JTextField.RIGHT);
			add(txtOtroImpuesto, cc.xy(9, 27));
			add(txtDescuento, cc.xy(9, 25));
			
			//---- lblValorTotal ----
			lblValorTotal.setText("Valor Total:");
			add(lblValorTotal, cc.xywh(11, 27, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtValorTotal, cc.xy(13, 27));
			add(fsDetalles, cc.xywh(3, 29, 11, 1));
			
			//======== scrollPane1 ========
			{
				
				//---- txtDetalle ----
				txtDetalle.setLineWrap(true);
				scrollPane1.setViewportView(txtDetalle);
			}
			add(scrollPane1, cc.xywh(3, 31, 11, 1, CellConstraints.FILL, CellConstraints.FILL));
			add(fsTotales, cc.xywh(3, 33, 11, 1));
			
			//---- lblValorFinal ----
			lblValorFinal.setText("Valor:");
			add(lblValorFinal, cc.xywh(3, 35, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtValorFinal, cc.xy(5, 35));
			
			//---- lblDescuentoFinal ----
			lblDescuentoFinal.setText("Descuento:");
			add(lblDescuentoFinal, cc.xywh(7, 35, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtDescuentoFinal, cc.xy(9, 35));
			
			//---- lblIVAFinal ----
			lblIVAFinal.setText("IVA:");
			add(lblIVAFinal, cc.xywh(11, 35, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//---- lblICEFinal ----
			lblICEFinal.setText("ICE:");
			add(lblICEFinal, cc.xywh(3, 37, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtICEFinal, cc.xy(5, 37));
			
			//---- lblOtroImpuestoFinal ----
			lblOtroImpuestoFinal.setText("Otro Impuesto:");
			add(lblOtroImpuestoFinal, cc.xywh(7, 37, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtOtroImpuestoFinal, cc.xy(9, 37));
			
			//---- lblTotalFinal ----
			lblTotalFinal.setText("TOTAL:");
			add(lblTotalFinal, cc.xywh(11, 37, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			add(txtTotalFinal, cc.xy(13, 37));
			add(txtIVAFinal, cc.xy(13, 35));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblDocumento;
	private JComboBox cmbDocumento;
	private JComponent fsDocumentoAplica;
	private JLabel lblMotivo;
	private JComboBox cmbMotivo;
	private JLabel lblTipoDocumentoApl;
	private JTextField txtTipoDocumento;
	private JLabel lblDocumentoApl;
	private JTextField txtDocumentoApl;
	private JLabel lblNumeroApl;
	private JTextField txtNumeroApl;
	private JLabel lblProductoApl;
	private JTextField txtProductoApl;
	private JComponent fsProcedenciaFactura;
	private JLabel lblOrdenMedios;
	private JComboBox cmbOrdenMedios;
	private JLabel lblPresupuesto;
	private JComboBox cmbPresupuesto;
	private JComponent fsOtraInformacion;
	private JLabel lblProducto;
	private JComboBox cmbProducto;
	private JLabel lblLote;
	private JLabel lblLinea;
	private JComboBox cmbLote;
	private JTextField txtLinea;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JLabel lblCantidadPedida;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private JTextField txtCantidadPedida;
	private JLabel lblBackorder;
	private JLabel lblPrecioReal;
	private JTextField txtPrecioReal;
	private JTextField txtBackorder;
	private JLabel lblPrecioVenta;
	private JTextField txtPrecioVenta;
	private JLabel lblCosto;
	private JTextField txtCosto;
	private JLabel lblValor;
	private JTextField txtValor;
	private JLabel lblDescuento;
	private JLabel lblIVA;
	private JTextField txtIVA;
	private JLabel lblICE;
	private JTextField txtICE;
	private JLabel lblOtroImpuesto;
	private JTextField txtOtroImpuesto;
	private JTextField txtDescuento;
	private JLabel lblValorTotal;
	private JTextField txtValorTotal;
	private JComponent fsDetalles;
	private JScrollPane scrollPane1;
	private JTextArea txtDetalle;
	private JComponent fsTotales;
	private JLabel lblValorFinal;
	private JTextField txtValorFinal;
	private JLabel lblDescuentoFinal;
	private JTextField txtDescuentoFinal;
	private JLabel lblIVAFinal;
	private JLabel lblICEFinal;
	private JTextField txtICEFinal;
	private JLabel lblOtroImpuestoFinal;
	private JTextField txtOtroImpuestoFinal;
	private JLabel lblTotalFinal;
	private JTextField txtTotalFinal;
	private JTextField txtIVAFinal;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JComboBox getCmbDocumento() {
		return cmbDocumento;
	}

	public void setCmbDocumento(JComboBox cmbDocumento) {
		this.cmbDocumento = cmbDocumento;
	}

	public JComboBox getCmbLote() {
		return cmbLote;
	}

	public void setCmbLote(JComboBox cmbLote) {
		this.cmbLote = cmbLote;
	}

	public JComboBox getCmbMotivo() {
		return cmbMotivo;
	}

	public void setCmbMotivo(JComboBox cmbMotivo) {
		this.cmbMotivo = cmbMotivo;
	}

	public JComboBox getCmbOrdenMedios() {
		return cmbOrdenMedios;
	}

	public void setCmbOrdenMedios(JComboBox cmbOrdenMedios) {
		this.cmbOrdenMedios = cmbOrdenMedios;
	}

	public JComboBox getCmbPresupuesto() {
		return cmbPresupuesto;
	}

	public void setCmbPresupuesto(JComboBox cmbPresupuesto) {
		this.cmbPresupuesto = cmbPresupuesto;
	}

	public JComboBox getCmbProducto() {
		return cmbProducto;
	}

	public void setCmbProducto(JComboBox cmbProducto) {
		this.cmbProducto = cmbProducto;
	}

	public JTextField getTxtBackorder() {
		return txtBackorder;
	}

	public void setTxtBackorder(JTextField txtBackorder) {
		this.txtBackorder = txtBackorder;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtCantidadPedida() {
		return txtCantidadPedida;
	}

	public void setTxtCantidadPedida(JTextField txtCantidadPedida) {
		this.txtCantidadPedida = txtCantidadPedida;
	}

	public JTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(JTextField txtCosto) {
		this.txtCosto = txtCosto;
	}

	public JTextField getTxtDescuento() {
		return txtDescuento;
	}

	public void setTxtDescuento(JTextField txtDescuento) {
		this.txtDescuento = txtDescuento;
	}

	public JTextField getTxtDescuentoFinal() {
		return txtDescuentoFinal;
	}

	public void setTxtDescuentoFinal(JTextField txtDescuentoFinal) {
		this.txtDescuentoFinal = txtDescuentoFinal;
	}

	public JTextField getTxtDocumentoApl() {
		return txtDocumentoApl;
	}

	public void setTxtDocumentoApl(JTextField txtDocumentoApl) {
		this.txtDocumentoApl = txtDocumentoApl;
	}

	public JTextField getTxtICE() {
		return txtICE;
	}

	public void setTxtICE(JTextField txtICE) {
		this.txtICE = txtICE;
	}

	public JTextField getTxtICEFinal() {
		return txtICEFinal;
	}

	public void setTxtICEFinal(JTextField txtICEFinal) {
		this.txtICEFinal = txtICEFinal;
	}

	public JTextField getTxtIVA() {
		return txtIVA;
	}

	public void setTxtIVA(JTextField txtIVA) {
		this.txtIVA = txtIVA;
	}

	public JTextField getTxtIVAFinal() {
		return txtIVAFinal;
	}

	public void setTxtIVAFinal(JTextField txtIVAFinal) {
		this.txtIVAFinal = txtIVAFinal;
	}

	public JTextField getTxtLinea() {
		return txtLinea;
	}

	public void setTxtLinea(JTextField txtLinea) {
		this.txtLinea = txtLinea;
	}

	public JTextField getTxtNumeroApl() {
		return txtNumeroApl;
	}

	public void setTxtNumeroApl(JTextField txtNumeroApl) {
		this.txtNumeroApl = txtNumeroApl;
	}

	public JTextField getTxtOtroImpuesto() {
		return txtOtroImpuesto;
	}

	public void setTxtOtroImpuesto(JTextField txtOtroImpuesto) {
		this.txtOtroImpuesto = txtOtroImpuesto;
	}

	public JTextField getTxtOtroImpuestoFinal() {
		return txtOtroImpuestoFinal;
	}

	public void setTxtOtroImpuestoFinal(JTextField txtOtroImpuestoFinal) {
		this.txtOtroImpuestoFinal = txtOtroImpuestoFinal;
	}

	public JTextField getTxtPrecioReal() {
		return txtPrecioReal;
	}

	public void setTxtPrecioReal(JTextField txtPrecioReal) {
		this.txtPrecioReal = txtPrecioReal;
	}

	public JTextField getTxtPrecioVenta() {
		return txtPrecioVenta;
	}

	public void setTxtPrecioVenta(JTextField txtPrecioVenta) {
		this.txtPrecioVenta = txtPrecioVenta;
	}

	public JTextField getTxtProductoApl() {
		return txtProductoApl;
	}

	public void setTxtProductoApl(JTextField txtProductoApl) {
		this.txtProductoApl = txtProductoApl;
	}

	public JTextField getTxtTipoDocumento() {
		return txtTipoDocumento;
	}

	public void setTxtTipoDocumento(JTextField txtTipoDocumento) {
		this.txtTipoDocumento = txtTipoDocumento;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public void setTxtTotalFinal(JTextField txtTotalFinal) {
		this.txtTotalFinal = txtTotalFinal;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public void setTxtValorFinal(JTextField txtValorFinal) {
		this.txtValorFinal = txtValorFinal;
	}

	public JTextField getTxtValorTotal() {
		return txtValorTotal;
	}

	public void setTxtValorTotal(JTextField txtValorTotal) {
		this.txtValorTotal = txtValorTotal;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JTextArea getTxtDetalle() {
		return txtDetalle;
	}

	public void setTxtDetalle(JTextArea txtDetalle) {
		this.txtDetalle = txtDetalle;
	}
}
