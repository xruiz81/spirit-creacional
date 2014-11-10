package com.spirit.pos.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JDPagoTarjetaCredito_Temporal extends JDialogModelImpl {
	public JDPagoTarjetaCredito_Temporal(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPagoTarjetaCredito_Temporal(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel7 = new JPanel();
		lblBalance2 = new JLabel();
		txtTotalPagar = new JTextField();
		lblImagenTarjeta = new JLabel();
		chkOtroCliente = new JCheckBox();
		chkManual = new JCheckBox();
		label2 = new JLabel();
		cmbTipoTarjetaCredito = new JComboBox();
		lblTitularTarjeta = new JLabel();
		txtTitularCuenta = new JTextField();
		lnlnNumCedula = new JLabel();
		lblTelefono = new JLabel();
		txtTelefono = new JTextField();
		lblCodAutorizacion = new JLabel();
		txtCodigoAutorizacion = new JTextField();
		txtIdentificacionCliente = new JTextField();
		lblVoucher = new JLabel();
		txtNoVoucher = new JTextField();
		lblNoReferencia = new JLabel();
		txtNoReferencia = new JTextField();
		lblMontoAplicar = new JLabel();
		txtMonto = new JTextField();
		lblBalance = new JLabel();
		txtBalance = new JTextField();
		panel4 = new JPanel();
		btnCancelar = new JButton();
		btnAceptar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Pago Tarjeta de Credito");
		setResizable(false);
		setModal(false);
		setFont(new Font("Dialog", Font.PLAIN, 26));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel1 ========
		{
			panel1.setPreferredSize(null);
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(10)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12))
				}));
			
			//======== panel7 ========
			{
				panel7.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(55)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(40)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(40), FormSpec.DEFAULT_GROW)
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
				
				//---- lblBalance2 ----
				lblBalance2.setText("DEUDA:");
				lblBalance2.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel7.add(lblBalance2, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtTotalPagar ----
				txtTotalPagar.setEditable(false);
				txtTotalPagar.setFont(new Font("Tahoma", Font.PLAIN, 26));
				txtTotalPagar.setText("       0.00");
				panel7.add(txtTotalPagar, cc.xywh(5, 1, 1, 1, CellConstraints.CENTER, CellConstraints.FILL));
				
				//---- lblImagenTarjeta ----
				lblImagenTarjeta.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\CreditCard.png"));
				panel7.add(lblImagenTarjeta, cc.xywh(1, 3, 2, 13));
				
				//---- chkOtroCliente ----
				chkOtroCliente.setText("Otro Cliente");
				chkOtroCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel7.add(chkOtroCliente, cc.xy(3, 3));
				
				//---- chkManual ----
				chkManual.setText("Manual");
				chkManual.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel7.add(chkManual, cc.xywh(7, 3, 3, 1));
				
				//---- label2 ----
				label2.setText("Tipo de Tarjeta: ");
				panel7.add(label2, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panel7.add(cmbTipoTarjetaCredito, cc.xywh(5, 5, 5, 1));
				
				//---- lblTitularTarjeta ----
				lblTitularTarjeta.setText("Titular tarjeta:");
				lblTitularTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblTitularTarjeta, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtTitularCuenta ----
				txtTitularCuenta.setEditable(true);
				panel7.add(txtTitularCuenta, cc.xywh(5, 7, 5, 1));
				
				//---- lnlnNumCedula ----
				lnlnNumCedula.setText("Numero c\u00e9dula:");
				lnlnNumCedula.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lnlnNumCedula, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblTelefono ----
				lblTelefono.setText("Tel\u00e9fono:");
				lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblTelefono, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panel7.add(txtTelefono, cc.xy(5, 11));
				
				//---- lblCodAutorizacion ----
				lblCodAutorizacion.setText("C\u00f3digo de autorizaci\u00f3n:");
				lblCodAutorizacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblCodAutorizacion, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCodigoAutorizacion ----
				txtCodigoAutorizacion.setEditable(true);
				panel7.add(txtCodigoAutorizacion, cc.xy(5, 13));
				panel7.add(txtIdentificacionCliente, cc.xy(5, 9));
				
				//---- lblVoucher ----
				lblVoucher.setText("No. Voucher:");
				panel7.add(lblVoucher, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtNoVoucher ----
				txtNoVoucher.setEditable(true);
				panel7.add(txtNoVoucher, cc.xy(5, 15));
				
				//---- lblNoReferencia ----
				lblNoReferencia.setText("No. Referencia:");
				panel7.add(lblNoReferencia, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtNoReferencia ----
				txtNoReferencia.setEditable(true);
				panel7.add(txtNoReferencia, cc.xy(5, 17));
				
				//---- lblMontoAplicar ----
				lblMontoAplicar.setText("MONTO A APLICAR:");
				lblMontoAplicar.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel7.add(lblMontoAplicar, cc.xy(3, 19));
				
				//---- txtMonto ----
				txtMonto.setEditable(true);
				txtMonto.setFont(new Font("Tahoma", Font.BOLD, 20));
				panel7.add(txtMonto, cc.xy(5, 19));
				
				//---- lblBalance ----
				lblBalance.setText("BALANCE:");
				lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel7.add(lblBalance, cc.xywh(3, 21, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtBalance ----
				txtBalance.setEditable(false);
				txtBalance.setFont(new Font("Tahoma", Font.PLAIN, 26));
				txtBalance.setText("       0.00");
				panel7.add(txtBalance, cc.xywh(5, 21, 1, 1, CellConstraints.CENTER, CellConstraints.FILL));
			}
			panel1.add(panel7, cc.xy(3, 3));
			
			//======== panel4 ========
			{
				panel4.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- btnCancelar ----
				btnCancelar.setText("CANCELAR");
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel4.add(btnCancelar, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
				
				//---- btnAceptar ----
				btnAceptar.setText("ACEPTAR");
				btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel4.add(btnAceptar, cc.xy(1, 1));
			}
			panel1.add(panel4, cc.xywh(3, 7, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JPanel panel7;
	private JLabel lblBalance2;
	private JTextField txtTotalPagar;
	private JLabel lblImagenTarjeta;
	private JCheckBox chkOtroCliente;
	private JCheckBox chkManual;
	private JLabel label2;
	private JComboBox cmbTipoTarjetaCredito;
	private JLabel lblTitularTarjeta;
	private JTextField txtTitularCuenta;
	private JLabel lnlnNumCedula;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblCodAutorizacion;
	private JTextField txtCodigoAutorizacion;
	private JTextField txtIdentificacionCliente;
	private JLabel lblVoucher;
	private JTextField txtNoVoucher;
	private JLabel lblNoReferencia;
	private JTextField txtNoReferencia;
	private JLabel lblMontoAplicar;
	private JTextField txtMonto;
	private JLabel lblBalance;
	private JTextField txtBalance;
	private JPanel panel4;
	private JButton btnCancelar;
	private JButton btnAceptar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPanel7() {
		return panel7;
	}

	public void setPanel7(JPanel panel7) {
		this.panel7 = panel7;
	}

	public JLabel getLblBalance2() {
		return lblBalance2;
	}

	public void setLblBalance2(JLabel lblBalance2) {
		this.lblBalance2 = lblBalance2;
	}

	public JTextField getTxtTotalPagar() {
		return txtTotalPagar;
	}

	public void setTxtTotalPagar(JTextField txtTotalPagar) {
		this.txtTotalPagar = txtTotalPagar;
	}

	public JLabel getLblImagenTarjeta() {
		return lblImagenTarjeta;
	}

	public void setLblImagenTarjeta(JLabel lblImagenTarjeta) {
		this.lblImagenTarjeta = lblImagenTarjeta;
	}

	public JCheckBox getChkOtroCliente() {
		return chkOtroCliente;
	}

	public void setChkOtroCliente(JCheckBox chkOtroCliente) {
		this.chkOtroCliente = chkOtroCliente;
	}

	public JCheckBox getChkManual() {
		return chkManual;
	}

	public void setChkManual(JCheckBox chkManual) {
		this.chkManual = chkManual;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JComboBox getCmbTipoTarjetaCredito() {
		return cmbTipoTarjetaCredito;
	}

	public void setCmbTipoTarjetaCredito(JComboBox cmbTipoTarjetaCredito) {
		this.cmbTipoTarjetaCredito = cmbTipoTarjetaCredito;
	}

	public JLabel getLblTitularTarjeta() {
		return lblTitularTarjeta;
	}

	public void setLblTitularTarjeta(JLabel lblTitularTarjeta) {
		this.lblTitularTarjeta = lblTitularTarjeta;
	}

	public JTextField getTxtTitularCuenta() {
		return txtTitularCuenta;
	}

	public void setTxtTitularCuenta(JTextField txtTitularCuenta) {
		this.txtTitularCuenta = txtTitularCuenta;
	}

	public JLabel getLnlnNumCedula() {
		return lnlnNumCedula;
	}

	public void setLnlnNumCedula(JLabel lnlnNumCedula) {
		this.lnlnNumCedula = lnlnNumCedula;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JLabel getLblCodAutorizacion() {
		return lblCodAutorizacion;
	}

	public void setLblCodAutorizacion(JLabel lblCodAutorizacion) {
		this.lblCodAutorizacion = lblCodAutorizacion;
	}

	public JTextField getTxtCodigoAutorizacion() {
		return txtCodigoAutorizacion;
	}

	public void setTxtCodigoAutorizacion(JTextField txtCodigoAutorizacion) {
		this.txtCodigoAutorizacion = txtCodigoAutorizacion;
	}

	public JTextField getTxtIdentificacionCliente() {
		return txtIdentificacionCliente;
	}

	public void setTxtIdentificacionCliente(JTextField txtIdentificacionCliente) {
		this.txtIdentificacionCliente = txtIdentificacionCliente;
	}

	public JLabel getLblVoucher() {
		return lblVoucher;
	}

	public void setLblVoucher(JLabel lblVoucher) {
		this.lblVoucher = lblVoucher;
	}

	public JTextField getTxtNoVoucher() {
		return txtNoVoucher;
	}

	public void setTxtNoVoucher(JTextField txtNoVoucher) {
		this.txtNoVoucher = txtNoVoucher;
	}

	public JLabel getLblNoReferencia() {
		return lblNoReferencia;
	}

	public void setLblNoReferencia(JLabel lblNoReferencia) {
		this.lblNoReferencia = lblNoReferencia;
	}

	public JTextField getTxtNoReferencia() {
		return txtNoReferencia;
	}

	public void setTxtNoReferencia(JTextField txtNoReferencia) {
		this.txtNoReferencia = txtNoReferencia;
	}

	public JLabel getLblMontoAplicar() {
		return lblMontoAplicar;
	}

	public void setLblMontoAplicar(JLabel lblMontoAplicar) {
		this.lblMontoAplicar = lblMontoAplicar;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(JTextField txtMonto) {
		this.txtMonto = txtMonto;
	}

	public JLabel getLblBalance() {
		return lblBalance;
	}

	public void setLblBalance(JLabel lblBalance) {
		this.lblBalance = lblBalance;
	}

	public JTextField getTxtBalance() {
		return txtBalance;
	}

	public void setTxtBalance(JTextField txtBalance) {
		this.txtBalance = txtBalance;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}
	
	
	
	
}
