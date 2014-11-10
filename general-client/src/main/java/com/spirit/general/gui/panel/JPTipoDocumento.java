package com.spirit.general.gui.panel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import com.spirit.client.model.MantenimientoModelImpl;

public abstract class JPTipoDocumento extends MantenimientoModelImpl {
	public JPTipoDocumento() {
		initComponents();
		setName("Tipo Documento");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		lblCodigo = new JLabel();
		txtCodigo = new JTextField();
		lblNombre = new JLabel();
		txtNombre = new JTextField();
		txtMascara = new JTextField();
		lblModuloId = new JLabel();
		cmbModulo = new JComboBox();
		lblMascara = new JLabel();
		txtNumeroLineas = new JTextField();
		lblNumeroLineas = new JLabel();
		lblTipoComprobante = new JLabel();
		cmbTipoComprobante = new JComboBox();
		lblTipoTroquelado = new JLabel();
		cmbTipoTroquelado = new JComboBox();
		lblTipoCartera = new JLabel();
		lblAfectaVenta = new JLabel();
		lblCliente = new JLabel();
		cmbCliente = new JComboBox();
		cmbTipoCartera = new JComboBox();
		cmbAfectaVenta = new JComboBox();
		lblCaja = new JLabel();
		lblSignoVenta = new JLabel();
		cmbSignoVenta = new JComboBox();
		lblPermiteEliminacion = new JLabel();
		cmbPermiteEliminacion = new JComboBox();
		cmbCaja = new JComboBox();
		lblEstado = new JLabel();
		lblAfectaStock = new JLabel();
		lblReembolso = new JLabel();
		cmbReembolso = new JComboBox();
		cmbEstado = new JComboBox();
		cmbAfectaStock = new JComboBox();
		lblAfectaCartera = new JLabel();
		lblSignoStock = new JLabel();
		cmbSignoStock = new JComboBox();
		lblExigeMotivo = new JLabel();
		cmbExigeMotivo = new JComboBox();
		cmbAfectaCartera = new JComboBox();
		lblSignoCartera = new JLabel();
		cmbSignoCartera = new JComboBox();
		cmbFormaPago = new JComboBox();
		lblFormaPago = new JLabel();
		lblDescuentoEspecial = new JLabel();
		cmbDescuentoEspecial = new JComboBox();
		spTblTipoDocumento = new JScrollPane();
		tblTipoDocumento = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;30dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("max(default;40dlu)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(25)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(30)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec("min(pref;20dlu)"),
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- lblCodigo ----
		lblCodigo.setText("C\u00f3digo:");
		lblCodigo.setToolTipText("C\u00f3digo descriptivo del Tipo de Documento");
		add(lblCodigo, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtCodigo ----
		txtCodigo.setToolTipText("C\u00f3digo descriptivo del Tipo de Documento");
		add(txtCodigo, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNombre ----
		lblNombre.setText("Nombre:");
		lblNombre.setToolTipText("Nombre del Tipo de Documento");
		add(lblNombre, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNombre ----
		txtNombre.setToolTipText("Nombre del Tipo de Documento");
		add(txtNombre, cc.xywh(5, 5, 9, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- txtMascara ----
		txtMascara.setToolTipText("Mascara del Tipo de Documento");
		add(txtMascara, cc.xywh(19, 5, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblModuloId ----
		lblModuloId.setText("M\u00f3dulo:");
		add(lblModuloId, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbModulo, cc.xywh(5, 7, 5, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblMascara ----
		lblMascara.setText("M\u00e1scara:");
		lblMascara.setToolTipText("M\u00e1scara para el Tipo");
		add(lblMascara, cc.xywh(17, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- txtNumeroLineas ----
		txtNumeroLineas.setHorizontalAlignment(JTextField.RIGHT);
		add(txtNumeroLineas, cc.xywh(19, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblNumeroLineas ----
		lblNumeroLineas.setText("# de l\u00edneas:");
		add(lblNumeroLineas, cc.xywh(17, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblTipoComprobante ----
		lblTipoComprobante.setText("Tipo de Comprobante:");
		add(lblTipoComprobante, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoComprobante, cc.xywh(5, 9, 21, 1));

		//---- lblTipoTroquelado ----
		lblTipoTroquelado.setText("Tipo de Troquelado:");
		add(lblTipoTroquelado, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbTipoTroquelado, cc.xywh(5, 11, 9, 1));

		//---- lblTipoCartera ----
		lblTipoCartera.setText("Tipo de cartera:");
		add(lblTipoCartera, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblAfectaVenta ----
		lblAfectaVenta.setText("Afecta venta:");
		add(lblAfectaVenta, cc.xywh(11, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblCliente ----
		lblCliente.setText("Cliente:");
		add(lblCliente, cc.xywh(21, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbCliente, cc.xywh(23, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbTipoCartera, cc.xywh(5, 13, 3, 1));
		add(cmbAfectaVenta, cc.xywh(13, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblCaja ----
		lblCaja.setText("Caja:");
		add(lblCaja, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblSignoVenta ----
		lblSignoVenta.setText("Signo venta:");
		lblSignoVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSignoVenta, cc.xy(11, 15));
		add(cmbSignoVenta, cc.xywh(13, 15, 3, 1));

		//---- lblPermiteEliminacion ----
		lblPermiteEliminacion.setText("Permite eliminaci\u00f3n:");
		add(lblPermiteEliminacion, cc.xywh(19, 15, 3, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbPermiteEliminacion, cc.xywh(23, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbCaja, cc.xywh(5, 15, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblEstado ----
		lblEstado.setText("Estado:");
		add(lblEstado, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblAfectaStock ----
		lblAfectaStock.setText("Afecta stock:");
		add(lblAfectaStock, cc.xywh(11, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblReembolso ----
		lblReembolso.setText("Reembolso:");
		add(lblReembolso, cc.xywh(21, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbReembolso, cc.xy(23, 17));
		add(cmbEstado, cc.xywh(5, 17, 3, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbAfectaStock, cc.xywh(13, 17, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblAfectaCartera ----
		lblAfectaCartera.setText("Afecta cartera:");
		add(lblAfectaCartera, cc.xywh(3, 19, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblSignoStock ----
		lblSignoStock.setText("Signo stock:");
		lblSignoStock.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSignoStock, cc.xy(11, 19));
		add(cmbSignoStock, cc.xywh(13, 19, 3, 1));

		//---- lblExigeMotivo ----
		lblExigeMotivo.setText("Exige motivo:");
		add(lblExigeMotivo, cc.xywh(19, 19, 3, 1, CellConstraints.RIGHT, CellConstraints.FILL));
		add(cmbExigeMotivo, cc.xywh(23, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
		add(cmbAfectaCartera, cc.xywh(5, 19, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblSignoCartera ----
		lblSignoCartera.setText("Signo cartera:");
		add(lblSignoCartera, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbSignoCartera, cc.xywh(5, 21, 3, 1));
		add(cmbFormaPago, cc.xywh(13, 21, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));

		//---- lblFormaPago ----
		lblFormaPago.setText("Forma de pago:");
		add(lblFormaPago, cc.xywh(11, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));

		//---- lblDescuentoEspecial ----
		lblDescuentoEspecial.setText("Descuento especial:");
		add(lblDescuentoEspecial, cc.xywh(19, 21, 3, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbDescuentoEspecial, cc.xy(23, 21));

		//======== spTblTipoDocumento ========
		{
			
			//---- tblTipoDocumento ----
			tblTipoDocumento.setModel(new DefaultTableModel(
				new Object[][] {
					{null, "", "", null, null},
				},
				new String[] {
					"Codigo", "Nombre", "Modulo", "Tipo de Cartera", "Estado"
				}
			) {
				boolean[] columnEditable = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return columnEditable[columnIndex];
				}
			});
			spTblTipoDocumento.setViewportView(tblTipoDocumento);
		}
		add(spTblTipoDocumento, cc.xywh(3, 25, 25, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtMascara;
	private JLabel lblModuloId;
	private JComboBox cmbModulo;
	private JLabel lblMascara;
	private JTextField txtNumeroLineas;
	private JLabel lblNumeroLineas;
	private JLabel lblTipoComprobante;
	private JComboBox cmbTipoComprobante;
	private JLabel lblTipoTroquelado;
	private JComboBox cmbTipoTroquelado;
	private JLabel lblTipoCartera;
	private JLabel lblAfectaVenta;
	private JLabel lblCliente;
	private JComboBox cmbCliente;
	private JComboBox cmbTipoCartera;
	private JComboBox cmbAfectaVenta;
	private JLabel lblCaja;
	private JLabel lblSignoVenta;
	private JComboBox cmbSignoVenta;
	private JLabel lblPermiteEliminacion;
	private JComboBox cmbPermiteEliminacion;
	private JComboBox cmbCaja;
	private JLabel lblEstado;
	private JLabel lblAfectaStock;
	private JLabel lblReembolso;
	private JComboBox cmbReembolso;
	private JComboBox cmbEstado;
	private JComboBox cmbAfectaStock;
	private JLabel lblAfectaCartera;
	private JLabel lblSignoStock;
	private JComboBox cmbSignoStock;
	private JLabel lblExigeMotivo;
	private JComboBox cmbExigeMotivo;
	private JComboBox cmbAfectaCartera;
	private JLabel lblSignoCartera;
	private JComboBox cmbSignoCartera;
	private JComboBox cmbFormaPago;
	private JLabel lblFormaPago;
	private JLabel lblDescuentoEspecial;
	private JComboBox cmbDescuentoEspecial;
	private JScrollPane spTblTipoDocumento;
	private JTable tblTipoDocumento;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JComboBox getCmbAfectaCartera() {
		return cmbAfectaCartera;
	}

	public void setCmbAfectaCartera(JComboBox cmbAfectaCartera) {
		this.cmbAfectaCartera = cmbAfectaCartera;
	}

	public JComboBox getCmbAfectaStock() {
		return cmbAfectaStock;
	}

	public void setCmbAfectaStock(JComboBox cmbAfectaStock) {
		this.cmbAfectaStock = cmbAfectaStock;
	}

	public JComboBox getCmbAfectaVenta() {
		return cmbAfectaVenta;
	}

	public void setCmbAfectaVenta(JComboBox cmbAfectaVenta) {
		this.cmbAfectaVenta = cmbAfectaVenta;
	}

	public JComboBox getCmbCaja() {
		return cmbCaja;
	}

	public void setCmbCaja(JComboBox cmbCaja) {
		this.cmbCaja = cmbCaja;
	}

	public JComboBox getCmbCliente() {
		return cmbCliente;
	}

	public void setCmbCliente(JComboBox cmbCliente) {
		this.cmbCliente = cmbCliente;
	}

	public JComboBox getCmbDescuentoEspecial() {
		return cmbDescuentoEspecial;
	}

	public void setCmbDescuentoEspecial(JComboBox cmbDescuentoEspecial) {
		this.cmbDescuentoEspecial = cmbDescuentoEspecial;
	}

	public JComboBox getCmbEstado() {
		return cmbEstado;
	}

	public void setCmbEstado(JComboBox cmbEstado) {
		this.cmbEstado = cmbEstado;
	}

	public JComboBox getCmbExigeMotivo() {
		return cmbExigeMotivo;
	}

	public void setCmbExigeMotivo(JComboBox cmbExigeMotivo) {
		this.cmbExigeMotivo = cmbExigeMotivo;
	}

	public JComboBox getCmbFormaPago() {
		return cmbFormaPago;
	}

	public void setCmbFormaPago(JComboBox cmbFormaPago) {
		this.cmbFormaPago = cmbFormaPago;
	}

	public JComboBox getCmbModulo() {
		return cmbModulo;
	}

	public void setCmbModulo(JComboBox cmbModulo) {
		this.cmbModulo = cmbModulo;
	}

	public JComboBox getCmbPermiteEliminacion() {
		return cmbPermiteEliminacion;
	}

	public void setCmbPermiteEliminacion(JComboBox cmbPermiteEliminacion) {
		this.cmbPermiteEliminacion = cmbPermiteEliminacion;
	}

	public JComboBox getCmbReembolso() {
		return cmbReembolso;
	}

	public void setCmbReembolso(JComboBox cmbReembolso) {
		this.cmbReembolso = cmbReembolso;
	}

	public JComboBox getCmbTipoCartera() {
		return cmbTipoCartera;
	}

	public void setCmbTipoCartera(JComboBox cmbTipoCartera) {
		this.cmbTipoCartera = cmbTipoCartera;
	}

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtMascara() {
		return txtMascara;
	}

	public void setTxtMascara(JTextField txtMascara) {
		this.txtMascara = txtMascara;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtNumeroLineas() {
		return txtNumeroLineas;
	}

	public void setTxtNumeroLineas(JTextField txtNumeroLineas) {
		this.txtNumeroLineas = txtNumeroLineas;
	}

	public JComboBox getCmbSignoCartera() {
		return cmbSignoCartera;
	}

	public void setCmbSignoCartera(JComboBox cmbSignoCartera) {
		this.cmbSignoCartera = cmbSignoCartera;
	}

	public JComboBox getCmbSignoStock() {
		return cmbSignoStock;
	}

	public void setCmbSignoStock(JComboBox cmbSignoStock) {
		this.cmbSignoStock = cmbSignoStock;
	}

	public JComboBox getCmbSignoVenta() {
		return cmbSignoVenta;
	}

	public void setCmbSignoVenta(JComboBox cmbSignoVenta) {
		this.cmbSignoVenta = cmbSignoVenta;
	}

	public JScrollPane getSpTblTipoDocumento() {
		return spTblTipoDocumento;
	}

	public void setSpTblTipoDocumento(JScrollPane spTblTipoDocumento) {
		this.spTblTipoDocumento = spTblTipoDocumento;
	}

	public JTable getTblTipoDocumento() {
		return tblTipoDocumento;
	}

	public void setTblTipoDocumento(JTable tblTipoDocumento) {
		this.tblTipoDocumento = tblTipoDocumento;
	}

	public JComboBox getCmbTipoComprobante() {
		return cmbTipoComprobante;
	}

	public void setCmbTipoComprobante(JComboBox cmbTipoComprobante) {
		this.cmbTipoComprobante = cmbTipoComprobante;
	}

	public JComboBox getCmbTipoTroquelado() {
		return cmbTipoTroquelado;
	}

	public void setCmbTipoTroquelado(JComboBox cmbTipoTroquelado) {
		this.cmbTipoTroquelado = cmbTipoTroquelado;
	}
}
