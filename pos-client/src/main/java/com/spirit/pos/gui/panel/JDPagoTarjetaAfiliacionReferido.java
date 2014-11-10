package com.spirit.pos.gui.panel;
import java.awt.*;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Mon May 10 16:51:34 COT 2010
 */
import com.spirit.client.model.JDialogModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JDPagoTarjetaAfiliacionReferido extends JDialogModelImpl {
	public JDPagoTarjetaAfiliacionReferido(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPagoTarjetaAfiliacionReferido(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		chkReferido = new JCheckBox();
		chkPropietario = new JCheckBox();
		lblPuntosReales3 = new JLabel();
		separator2 = new JSeparator();
		panel1 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		lblDeuda = new JLabel();
		txtDeuda = new JTextField();
		lblIdentificacion = new JLabel();
		lblOperadorNegocio = new JLabel();
		txtIdentificacion = new JTextField();
		btnBuscarOperadorNegocio = new JButton();
		txtPropietarioTarjeta = new JTextField();
		lblPuntosReales = new JLabel();
		txtPuntosAcumulados = new JTextField();
		lblPuntosReales2 = new JLabel();
		txtPuntosUtilizados = new JTextField();
		lblPuntosReservados = new JLabel();
		txtPuntosComprometidos = new JTextField();
		lblPuntosDisponibles = new JLabel();
		txtPuntosDisponibles = new JTextField();
		lblCantidadAplicar = new JLabel();
		txtCantidadAplicar = new JTextField();
		btnAplicar = new JButton();
		lblPuntosReales43 = new JLabel();
		txtPuntosDinero = new JTextField();
		lblPuntosReales42 = new JLabel();
		txtEquivalenteDinero = new JTextField();
		lblBalance = new JLabel();
		txtBalance = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(65)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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

		//---- chkReferido ----
		chkReferido.setText("Referido");
		chkReferido.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(chkReferido, cc.xy(3, 7));

		//---- chkPropietario ----
		chkPropietario.setText("Propietario");
		chkPropietario.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(chkPropietario, cc.xywh(5, 7, 3, 1));

		//---- lblPuntosReales3 ----
		lblPuntosReales3.setText("P U N T O S / D I N E R O:");
		lblPuntosReales3.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblPuntosReales3, cc.xywh(3, 13, 3, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
		contentPane.add(separator2, cc.xywh(3, 23, 8, 1));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- btnAceptar ----
			btnAceptar.setText("ACEPTAR");
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			panel1.add(btnAceptar, cc.xywh(1, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//---- btnCancelar ----
			btnCancelar.setText("CANCELAR");
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			panel1.add(btnCancelar, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
		}
		contentPane.add(panel1, cc.xywh(3, 33, 9, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

		//---- lblDeuda ----
		lblDeuda.setText("DEUDA:");
		lblDeuda.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblDeuda, cc.xy(5, 3));

		//---- txtDeuda ----
		txtDeuda.setEditable(false);
		txtDeuda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDeuda.setText("0.00");
		txtDeuda.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtDeuda, cc.xy(7, 3));

		//---- lblIdentificacion ----
		lblIdentificacion.setText("Identificaci\u00f3n:");
		lblIdentificacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblIdentificacion, cc.xy(3, 9));

		//---- lblOperadorNegocio ----
		lblOperadorNegocio.setText("Propietario:");
		lblOperadorNegocio.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblOperadorNegocio, cc.xy(3, 11));

		//---- txtIdentificacion ----
		txtIdentificacion.setEditable(true);
		txtIdentificacion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(txtIdentificacion, cc.xywh(5, 9, 3, 1));

		//---- btnBuscarOperadorNegocio ----
		btnBuscarOperadorNegocio.setText("BUSCAR");
		btnBuscarOperadorNegocio.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnBuscarOperadorNegocio, cc.xy(9, 9));

		//---- txtPropietarioTarjeta ----
		txtPropietarioTarjeta.setEditable(false);
		txtPropietarioTarjeta.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(txtPropietarioTarjeta, cc.xywh(5, 11, 3, 1));

		//---- lblPuntosReales ----
		lblPuntosReales.setText("Acumulado:");
		lblPuntosReales.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblPuntosReales, cc.xy(5, 15));

		//---- txtPuntosAcumulados ----
		txtPuntosAcumulados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPuntosAcumulados.setEditable(false);
		txtPuntosAcumulados.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtPuntosAcumulados, cc.xy(7, 15));

		//---- lblPuntosReales2 ----
		lblPuntosReales2.setText("Utilizado:");
		lblPuntosReales2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblPuntosReales2, cc.xy(5, 17));

		//---- txtPuntosUtilizados ----
		txtPuntosUtilizados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPuntosUtilizados.setEditable(false);
		txtPuntosUtilizados.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtPuntosUtilizados, cc.xy(7, 17));

		//---- lblPuntosReservados ----
		lblPuntosReservados.setText("Comprometido:");
		lblPuntosReservados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblPuntosReservados, cc.xy(5, 19));

		//---- txtPuntosComprometidos ----
		txtPuntosComprometidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPuntosComprometidos.setEditable(false);
		txtPuntosComprometidos.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtPuntosComprometidos, cc.xy(7, 19));

		//---- lblPuntosDisponibles ----
		lblPuntosDisponibles.setText("Disponible:");
		lblPuntosDisponibles.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblPuntosDisponibles, cc.xy(5, 21));

		//---- txtPuntosDisponibles ----
		txtPuntosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPuntosDisponibles.setEditable(false);
		txtPuntosDisponibles.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtPuntosDisponibles, cc.xy(7, 21));

		//---- lblCantidadAplicar ----
		lblCantidadAplicar.setText("Cantidad a aplicar:");
		lblCantidadAplicar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblCantidadAplicar, cc.xy(5, 25));

		//---- txtCantidadAplicar ----
		txtCantidadAplicar.setEditable(true);
		txtCantidadAplicar.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCantidadAplicar.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtCantidadAplicar, cc.xy(7, 25));

		//---- btnAplicar ----
		btnAplicar.setText("APLICAR");
		btnAplicar.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(btnAplicar, cc.xy(9, 25));

		//---- lblPuntosReales43 ----
		lblPuntosReales43.setText("Puntos/$");
		lblPuntosReales43.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblPuntosReales43, cc.xy(5, 27));

		//---- txtPuntosDinero ----
		txtPuntosDinero.setEditable(false);
		txtPuntosDinero.setHorizontalAlignment(JTextField.RIGHT);
		txtPuntosDinero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(txtPuntosDinero, cc.xy(7, 27));

		//---- lblPuntosReales42 ----
		lblPuntosReales42.setText("   en $:  ");
		lblPuntosReales42.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblPuntosReales42, cc.xywh(5, 29, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- txtEquivalenteDinero ----
		txtEquivalenteDinero.setEditable(false);
		txtEquivalenteDinero.setHorizontalAlignment(JTextField.RIGHT);
		txtEquivalenteDinero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(txtEquivalenteDinero, cc.xywh(7, 29, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- lblBalance ----
		lblBalance.setText("BALANCE:");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblBalance, cc.xywh(5, 31, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- txtBalance ----
		txtBalance.setEditable(false);
		txtBalance.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtBalance.setText("0.00");
		txtBalance.setHorizontalAlignment(JTextField.RIGHT);
		contentPane.add(txtBalance, cc.xy(7, 31));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JCheckBox chkReferido;
	private JCheckBox chkPropietario;
	private JLabel lblPuntosReales3;
	private JSeparator separator2;
	private JPanel panel1;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblDeuda;
	private JTextField txtDeuda;
	private JLabel lblIdentificacion;
	private JLabel lblOperadorNegocio;
	private JTextField txtIdentificacion;
	private JButton btnBuscarOperadorNegocio;
	private JTextField txtPropietarioTarjeta;
	private JLabel lblPuntosReales;
	private JTextField txtPuntosAcumulados;
	private JLabel lblPuntosReales2;
	private JTextField txtPuntosUtilizados;
	private JLabel lblPuntosReservados;
	private JTextField txtPuntosComprometidos;
	private JLabel lblPuntosDisponibles;
	private JTextField txtPuntosDisponibles;
	private JLabel lblCantidadAplicar;
	private JTextField txtCantidadAplicar;
	private JButton btnAplicar;
	private JLabel lblPuntosReales43;
	private JTextField txtPuntosDinero;
	private JLabel lblPuntosReales42;
	private JTextField txtEquivalenteDinero;
	private JLabel lblBalance;
	private JTextField txtBalance;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JCheckBox getChkReferido() {
		return chkReferido;
	}

	public void setChkReferido(JCheckBox chkReferido) {
		this.chkReferido = chkReferido;
	}

	public JCheckBox getChkPropietario() {
		return chkPropietario;
	}

	public void setChkPropietario(JCheckBox chkPropietario) {
		this.chkPropietario = chkPropietario;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JTextField getTxtDeuda() {
		return txtDeuda;
	}

	public void setTxtDeuda(JTextField txtDeuda) {
		this.txtDeuda = txtDeuda;
	}

	public JTextField getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(JTextField txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public JButton getBtnBuscarOperadorNegocio() {
		return btnBuscarOperadorNegocio;
	}

	public void setBtnBuscarOperadorNegocio(JButton btnBuscarOperadorNegocio) {
		this.btnBuscarOperadorNegocio = btnBuscarOperadorNegocio;
	}

	public JTextField getTxtPropietarioTarjeta() {
		return txtPropietarioTarjeta;
	}

	public void setTxtPropietarioTarjeta(JTextField txtPropietarioTarjeta) {
		this.txtPropietarioTarjeta = txtPropietarioTarjeta;
	}

	public JTextField getTxtPuntosAcumulados() {
		return txtPuntosAcumulados;
	}

	public void setTxtPuntosAcumulados(JTextField txtPuntosAcumulados) {
		this.txtPuntosAcumulados = txtPuntosAcumulados;
	}

	public JTextField getTxtPuntosUtilizados() {
		return txtPuntosUtilizados;
	}

	public void setTxtPuntosUtilizados(JTextField txtPuntosUtilizados) {
		this.txtPuntosUtilizados = txtPuntosUtilizados;
	}

	public JTextField getTxtPuntosComprometidos() {
		return txtPuntosComprometidos;
	}

	public void setTxtPuntosComprometidos(JTextField txtPuntosComprometidos) {
		this.txtPuntosComprometidos = txtPuntosComprometidos;
	}

	public JTextField getTxtPuntosDisponibles() {
		return txtPuntosDisponibles;
	}

	public void setTxtPuntosDisponibles(JTextField txtPuntosDisponibles) {
		this.txtPuntosDisponibles = txtPuntosDisponibles;
	}

	public JTextField getTxtCantidadAplicar() {
		return txtCantidadAplicar;
	}

	public void setTxtCantidadAplicar(JTextField txtCantidadAplicar) {
		this.txtCantidadAplicar = txtCantidadAplicar;
	}

	public JButton getBtnAplicar() {
		return btnAplicar;
	}

	public void setBtnAplicar(JButton btnAplicar) {
		this.btnAplicar = btnAplicar;
	}

	public JTextField getTxtPuntosDinero() {
		return txtPuntosDinero;
	}

	public void setTxtPuntosDinero(JTextField txtPuntosDinero) {
		this.txtPuntosDinero = txtPuntosDinero;
	}

	public JTextField getTxtEquivalenteDinero() {
		return txtEquivalenteDinero;
	}

	public void setTxtEquivalenteDinero(JTextField txtEquivalenteDinero) {
		this.txtEquivalenteDinero = txtEquivalenteDinero;
	}

	public JTextField getTxtBalance() {
		return txtBalance;
	}

	public void setTxtBalance(JTextField txtBalance) {
		this.txtBalance = txtBalance;
	}
	
	
}
