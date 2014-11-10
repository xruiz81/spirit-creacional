package com.spirit.pos.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;

public abstract class JDPagoEfectivo extends JDialogModelImpl {
	public JDPagoEfectivo(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPagoEfectivo(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel5 = new JPanel();
		txtDeuda = new JTextField();
		lblTotalPagar = new JLabel();
		lblImagenEfectivo = new JLabel();
		txtMonto = new JTextField();
		lblTotalEntregado = new JLabel();
		txtCambio = new JTextField();
		lblCambio = new JLabel();
		panel6 = new JPanel();
		lblBalance = new JLabel();
		txtBalance = new JTextField();
		panel2 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		separator1 = new JSeparator();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Pago Efectivo");
		setResizable(false);
		setModal(false);
		setFont(new Font("Dialog", Font.PLAIN, 26));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel1 ========
		{
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
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12))
				}));
			
			//======== panel5 ========
			{
				panel5.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(51)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(70), FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- txtDeuda ----
				txtDeuda.setEditable(false);
				txtDeuda.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel5.add(txtDeuda, cc.xy(5, 1));
				
				//---- lblTotalPagar ----
				lblTotalPagar.setText("   DEUDA:");
				lblTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblTotalPagar, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblImagenEfectivo ----
				lblImagenEfectivo.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\money.png"));
				panel5.add(lblImagenEfectivo, cc.xywh(1, 3, 1, 1, CellConstraints.CENTER, CellConstraints.BOTTOM));
				
				//---- txtMonto ----
				txtMonto.setEditable(true);
				txtMonto.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel5.add(txtMonto, cc.xy(5, 3));
				
				//---- lblTotalEntregado ----
				lblTotalEntregado.setText("MONTO:");
				lblTotalEntregado.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblTotalEntregado, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCambio ----
				txtCambio.setEditable(false);
				txtCambio.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel5.add(txtCambio, cc.xy(5, 5));
				
				//---- lblCambio ----
				lblCambio.setText("CAMBIO:");
				lblCambio.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblCambio, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			panel1.add(panel5, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
			
			//======== panel6 ========
			{
				panel6.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblBalance ----
				lblBalance.setText("BALANCE:");
				lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel6.add(lblBalance, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtBalance ----
				txtBalance.setEditable(false);
				txtBalance.setFont(new Font("Tahoma", Font.PLAIN, 16));
				panel6.add(txtBalance, cc.xy(3, 1));
				
				//======== panel2 ========
				{
					panel2.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(55)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnAceptar ----
					btnAceptar.setText("ACEPTAR");
					btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
					panel2.add(btnAceptar, cc.xywh(1, 1, 2, 1));
					
					//---- btnCancelar ----
					btnCancelar.setText("CANCELAR");
					btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
					panel2.add(btnCancelar, cc.xy(3, 1));
				}
				panel6.add(panel2, cc.xywh(1, 3, 3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			panel1.add(panel6, cc.xy(3, 7));
			panel1.add(separator1, cc.xy(3, 5));
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JPanel panel5;
	private JTextField txtDeuda;
	private JLabel lblTotalPagar;
	private JLabel lblImagenEfectivo;
	private JTextField txtMonto;
	private JLabel lblTotalEntregado;
	private JTextField txtCambio;
	private JLabel lblCambio;
	private JPanel panel6;
	private JLabel lblBalance;
	private JTextField txtBalance;
	private JPanel panel2;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JSeparator separator1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public void setPanel5(JPanel panel5) {
		this.panel5 = panel5;
	}

	public JTextField getTxtDeuda() {
		return txtDeuda;
	}

	public void setTxtDeuda(JTextField txtDeuda) {
		this.txtDeuda = txtDeuda;
	}

	public JLabel getLblTotalPagar() {
		return lblTotalPagar;
	}

	public void setLblTotalPagar(JLabel lblTotalPagar) {
		this.lblTotalPagar = lblTotalPagar;
	}

	public JLabel getLblImagenEfectivo() {
		return lblImagenEfectivo;
	}

	public void setLblImagenEfectivo(JLabel lblImagenEfectivo) {
		this.lblImagenEfectivo = lblImagenEfectivo;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(JTextField txtMonto) {
		this.txtMonto = txtMonto;
	}

	public JLabel getLblTotalEntregado() {
		return lblTotalEntregado;
	}

	public void setLblTotalEntregado(JLabel lblTotalEntregado) {
		this.lblTotalEntregado = lblTotalEntregado;
	}

	public JTextField getTxtCambio() {
		return txtCambio;
	}

	public void setTxtCambio(JTextField txtCambio) {
		this.txtCambio = txtCambio;
	}

	public JLabel getLblCambio() {
		return lblCambio;
	}

	public void setLblCambio(JLabel lblCambio) {
		this.lblCambio = lblCambio;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public void setPanel6(JPanel panel6) {
		this.panel6 = panel6;
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

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
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

	public JSeparator getSeparator1() {
		return separator1;
	}

	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
	}
	
	
	
}
