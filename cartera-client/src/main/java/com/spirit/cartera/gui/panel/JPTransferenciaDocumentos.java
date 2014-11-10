package com.spirit.cartera.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPTransferenciaDocumentos extends SpiritModelImpl {
	
	public JPTransferenciaDocumentos() {
		setName("Transferencia de Documentos");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		lblOficinaOrigen = new JLabel();
		txtOficinaOrigen = new JFormattedTextField();
		lblTipoDocumento = new JLabel();
		btnBuscarComprobante = new JButton();
		cmbTipoDocumento = new JComboBox();
		rbCliente = new JRadioButton();
		gfsDetalleComprobante = compFactory.createSeparator("Detalle comprobante");
		lblTipoOperador = new JLabel();
		rbProveedor = new JRadioButton();
		lblPreimpresoCodigo = new JLabel();
		lblFechaEmision = new JLabel();
		txtFechaEmision = new JFormattedTextField();
		gfsOperadorNegocio = compFactory.createSeparator("Operador de Negocio");
		lblNombreComercial = new JLabel();
		txtNombreComercial = new JFormattedTextField();
		lblIdentificacion = new JLabel();
		txtIdentificacion = new JFormattedTextField();
		gfsTotales = compFactory.createSeparator("");
		lblTotal = new JLabel();
		txtTotal = new JFormattedTextField();
		lblSaldo = new JLabel();
		txtSaldo = new JFormattedTextField();
		gfsTransferencia = compFactory.createSeparator("");
		lblOficinaDestino = new JLabel();
		txtOficinaDestino = new JFormattedTextField();
		btnBuscarOficinaDestino = new JButton();
		btnTransferirComprobante = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(10)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(100)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(50), FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.LEFT, Sizes.dluX(60), FormSpec.NO_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(10)),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.FILL, Sizes.dluY(10), FormSpec.NO_GROW)
			}));

		//---- lblOficinaOrigen ----
		lblOficinaOrigen.setText("Oficina origen:");
		lblOficinaOrigen.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblOficinaOrigen, cc.xy(3, 3));

		//---- txtOficinaOrigen ----
		txtOficinaOrigen.setEditable(false);
		txtOficinaOrigen.setFocusTraversalPolicyProvider(false);
		add(txtOficinaOrigen, cc.xywh(5, 3, 5, 1));

		//---- lblTipoDocumento ----
		lblTipoDocumento.setText("Tipo documento:");
		lblTipoDocumento.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblTipoDocumento, cc.xy(3, 5));

		//---- btnBuscarComprobante ----
		btnBuscarComprobante.setText("B");
		btnBuscarComprobante.setToolTipText("Buscar comprobante");
		add(btnBuscarComprobante, cc.xywh(11, 5, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		add(cmbTipoDocumento, cc.xywh(5, 5, 5, 1));

		//---- rbCliente ----
		rbCliente.setText("Cliente");
		add(rbCliente, cc.xy(5, 11));
		add(gfsDetalleComprobante, cc.xywh(3, 9, 17, 1));

		//---- lblTipoOperador ----
		lblTipoOperador.setText("Tipo operador:");
		lblTipoOperador.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblTipoOperador, cc.xy(3, 11));

		//---- rbProveedor ----
		rbProveedor.setText("Proveedor");
		add(rbProveedor, cc.xy(7, 11));

		//---- lblPreimpresoCodigo ----
		lblPreimpresoCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreimpresoCodigo.setText("XXX-XXX-XXXXXXX");
		lblPreimpresoCodigo.setForeground(Color.blue);
		lblPreimpresoCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPreimpresoCodigo.setFocusTraversalPolicyProvider(false);
		add(lblPreimpresoCodigo, cc.xywh(9, 11, 3, 1));

		//---- lblFechaEmision ----
		lblFechaEmision.setText("Fecha emisi\u00f3n:");
		lblFechaEmision.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblFechaEmision, cc.xy(15, 11));

		//---- txtFechaEmision ----
		txtFechaEmision.setHorizontalAlignment(JTextField.CENTER);
		txtFechaEmision.setEditable(false);
		add(txtFechaEmision, cc.xywh(17, 11, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		add(gfsOperadorNegocio, cc.xywh(3, 15, 17, 1));

		//---- lblNombreComercial ----
		lblNombreComercial.setText("Nombre Comercial:");
		lblNombreComercial.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNombreComercial, cc.xy(3, 19));

		//---- txtNombreComercial ----
		txtNombreComercial.setEditable(false);
		add(txtNombreComercial, cc.xywh(5, 19, 7, 1));

		//---- lblIdentificacion ----
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdentificacion.setText("Identificaci\u00f3n:");
		add(lblIdentificacion, cc.xy(15, 19));

		//---- txtIdentificacion ----
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setHorizontalAlignment(JTextField.CENTER);
		add(txtIdentificacion, cc.xywh(17, 19, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		add(gfsTotales, cc.xywh(3, 23, 17, 1));

		//---- lblTotal ----
		lblTotal.setText("T  O  T  A  L  :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setHorizontalAlignment(SwingConstants.LEADING);
		add(lblTotal, cc.xy(15, 25));

		//---- txtTotal ----
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(JTextField.CENTER);
		txtTotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		txtTotal.setForeground(Color.black);
		add(txtTotal, cc.xywh(17, 25, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));

		//---- lblSaldo ----
		lblSaldo.setText("S  A  L  D  O  :");
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSaldo.setHorizontalAlignment(SwingConstants.LEADING);
		add(lblSaldo, cc.xy(15, 27));

		//---- txtSaldo ----
		txtSaldo.setEditable(false);
		txtSaldo.setHorizontalAlignment(JTextField.CENTER);
		txtSaldo.setForeground(Color.blue);
		txtSaldo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 11));
		add(txtSaldo, cc.xywh(17, 27, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		add(gfsTransferencia, cc.xywh(3, 31, 17, 1));

		//---- lblOficinaDestino ----
		lblOficinaDestino.setText("Oficina destino:");
		lblOficinaDestino.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblOficinaDestino, cc.xy(3, 33));

		//---- txtOficinaDestino ----
		txtOficinaDestino.setEditable(false);
		txtOficinaDestino.setFocusTraversalPolicyProvider(false);
		add(txtOficinaDestino, cc.xywh(5, 33, 5, 1));

		//---- btnBuscarOficinaDestino ----
		btnBuscarOficinaDestino.setText("B");
		btnBuscarOficinaDestino.setToolTipText("Buscar oficina destino");
		add(btnBuscarOficinaDestino, cc.xywh(11, 33, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

		//---- btnTransferirComprobante ----
		btnTransferirComprobante.setText("Transferir comprobante");
		add(btnTransferirComprobante, cc.xywh(15, 33, 3, 1));

		//---- btnGroupTipoOperador ----
		ButtonGroup btnGroupTipoOperador = new ButtonGroup();
		btnGroupTipoOperador.add(rbCliente);
		btnGroupTipoOperador.add(rbProveedor);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel lblOficinaOrigen;
	private JFormattedTextField txtOficinaOrigen;
	private JLabel lblTipoDocumento;
	private JButton btnBuscarComprobante;
	private JComboBox cmbTipoDocumento;
	private JRadioButton rbCliente;
	private JComponent gfsDetalleComprobante;
	private JLabel lblTipoOperador;
	private JRadioButton rbProveedor;
	private JLabel lblPreimpresoCodigo;
	private JLabel lblFechaEmision;
	private JFormattedTextField txtFechaEmision;
	private JComponent gfsOperadorNegocio;
	private JLabel lblNombreComercial;
	private JFormattedTextField txtNombreComercial;
	private JLabel lblIdentificacion;
	private JFormattedTextField txtIdentificacion;
	private JComponent gfsTotales;
	private JLabel lblTotal;
	private JFormattedTextField txtTotal;
	private JLabel lblSaldo;
	private JFormattedTextField txtSaldo;
	private JComponent gfsTransferencia;
	private JLabel lblOficinaDestino;
	private JFormattedTextField txtOficinaDestino;
	private JButton btnBuscarOficinaDestino;
	private JButton btnTransferirComprobante;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JFormattedTextField getTxtOficinaOrigen() {
		return txtOficinaOrigen;
	}

	public void setTxtOficinaOrigen(JFormattedTextField txtOficinaOrigen) {
		this.txtOficinaOrigen = txtOficinaOrigen;
	}

	public JButton getBtnBuscarComprobante() {
		return btnBuscarComprobante;
	}

	public void setBtnBuscarComprobante(JButton btnBuscarComprobante) {
		this.btnBuscarComprobante = btnBuscarComprobante;
	}

	public JComboBox getCmbTipoDocumento() {
		return cmbTipoDocumento;
	}

	public void setCmbTipoDocumento(JComboBox cmbTipoDocumento) {
		this.cmbTipoDocumento = cmbTipoDocumento;
	}

	public JRadioButton getRbCliente() {
		return rbCliente;
	}

	public void setRbCliente(JRadioButton rbCliente) {
		this.rbCliente = rbCliente;
	}

	public JRadioButton getRbProveedor() {
		return rbProveedor;
	}

	public void setRbProveedor(JRadioButton rbProveedor) {
		this.rbProveedor = rbProveedor;
	}

	public JFormattedTextField getTxtFechaEmision() {
		return txtFechaEmision;
	}

	public void setTxtFechaEmision(JFormattedTextField txtFechaEmision) {
		this.txtFechaEmision = txtFechaEmision;
	}

	public JFormattedTextField getTxtNombreComercial() {
		return txtNombreComercial;
	}

	public void setTxtNombreComercial(JFormattedTextField txtNombreComercial) {
		this.txtNombreComercial = txtNombreComercial;
	}

	public JFormattedTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JFormattedTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public JFormattedTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JFormattedTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JFormattedTextField getTxtSaldo() {
		return txtSaldo;
	}

	public void setTxtSaldo(JFormattedTextField txtSaldo) {
		this.txtSaldo = txtSaldo;
	}

	public JFormattedTextField getTxtOficinaDestino() {
		return txtOficinaDestino;
	}

	public void setTxtOficinaDestino(JFormattedTextField txtOficinaDestino) {
		this.txtOficinaDestino = txtOficinaDestino;
	}

	public JButton getBtnBuscarOficinaDestino() {
		return btnBuscarOficinaDestino;
	}

	public void setBtnBuscarOficinaDestino(JButton btnBuscarOficinaDestino) {
		this.btnBuscarOficinaDestino = btnBuscarOficinaDestino;
	}

	public JButton getBtnTransferirComprobante() {
		return btnTransferirComprobante;
	}

	public void setBtnTransferirComprobante(JButton btnTransferirComprobante) {
		this.btnTransferirComprobante = btnTransferirComprobante;
	}

	public JLabel getLblPreimpresoCodigo() {
		return lblPreimpresoCodigo;
	}

	public void setLblPreimpresoCodigo(JLabel lblPreimpresoCodigo) {
		this.lblPreimpresoCodigo = lblPreimpresoCodigo;
	}
}
