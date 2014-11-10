package com.spirit.pos.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
public abstract class JPPagoCheque extends JDialogModelImpl {
	public JPPagoCheque(Frame owner) {
		super(owner);
		initComponents();
	}

	public JPPagoCheque(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel5 = new JPanel();
		lblDeuda = new JLabel();
		txtDeuda = new JTextField();
		lblImagenCheque = new JLabel();
		txtMonto = new JTextField();
		lblMonto = new JLabel();
		cmbBanco = new JComboBox();
		lblBanco = new JLabel();
		txtNumCheque = new JTextField();
		lblNumCheque = new JLabel();
		lblNumCuenta = new JLabel();
		txtNumCuenta = new JTextField();
		panel6 = new JPanel();
		lblBalance = new JLabel();
		txtBalance = new JTextField();
		panel2 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		separator1 = new JSeparator();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Pago Cheque");
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
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY4),
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
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(30), FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblDeuda ----
				lblDeuda.setText("   DEUDA:");
				lblDeuda.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblDeuda, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtDeuda ----
				txtDeuda.setEditable(false);
				txtDeuda.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel5.add(txtDeuda, cc.xy(5, 1));
				
				//---- lblImagenCheque ----
				lblImagenCheque.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\cheque.jpeg"));
				panel5.add(lblImagenCheque, cc.xywh(1, 3, 1, 1, CellConstraints.CENTER, CellConstraints.BOTTOM));
				
				//---- txtMonto ----
				txtMonto.setEditable(true);
				txtMonto.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel5.add(txtMonto, cc.xy(5, 3));
				
				//---- lblMonto ----
				lblMonto.setText("MONTO:");
				lblMonto.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblMonto, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- cmbBanco ----
				cmbBanco.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel5.add(cmbBanco, cc.xywh(5, 5, 3, 1));
				
				//---- lblBanco ----
				lblBanco.setText("Banco:");
				lblBanco.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblBanco, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtNumCheque ----
				txtNumCheque.setEditable(true);
				txtNumCheque.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel5.add(txtNumCheque, cc.xy(5, 7));
				
				//---- lblNumCheque ----
				lblNumCheque.setText("No. Cheque:");
				lblNumCheque.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNumCheque.setHorizontalAlignment(SwingConstants.RIGHT);
				panel5.add(lblNumCheque, cc.xy(3, 7));
				
				//---- lblNumCuenta ----
				lblNumCuenta.setText("No. Cuenta:");
				lblNumCuenta.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNumCuenta.setHorizontalAlignment(SwingConstants.RIGHT);
				panel5.add(lblNumCuenta, cc.xy(3, 9));
				
				//---- txtNumCuenta ----
				txtNumCuenta.setEditable(true);
				txtNumCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel5.add(txtNumCuenta, cc.xy(5, 9));
			}
			panel1.add(panel5, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
			
			//======== panel6 ========
			{
				panel6.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(85)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(50))
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
	private JLabel lblDeuda;
	private JTextField txtDeuda;
	private JLabel lblImagenCheque;
	private JTextField txtMonto;
	private JLabel lblMonto;
	private JComboBox cmbBanco;
	private JLabel lblBanco;
	private JTextField txtNumCheque;
	private JLabel lblNumCheque;
	private JLabel lblNumCuenta;
	private JTextField txtNumCuenta;
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

	public JLabel getLblDeuda() {
		return lblDeuda;
	}

	public void setLblDeuda(JLabel lblDeuda) {
		this.lblDeuda = lblDeuda;
	}

	public JTextField getTxtDeuda() {
		return txtDeuda;
	}

	public void setTxtDeuda(JTextField txtDeuda) {
		this.txtDeuda = txtDeuda;
	}

	public JLabel getLblImagenCheque() {
		return lblImagenCheque;
	}

	public void setLblImagenCheque(JLabel lblImagenCheque) {
		this.lblImagenCheque = lblImagenCheque;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(JTextField txtMonto) {
		this.txtMonto = txtMonto;
	}

	public JLabel getLblMonto() {
		return lblMonto;
	}

	public void setLblMonto(JLabel lblMonto) {
		this.lblMonto = lblMonto;
	}

	public JComboBox getCmbBanco() {
		return cmbBanco;
	}

	public void setCmbBanco(JComboBox cmbBanco) {
		this.cmbBanco = cmbBanco;
	}

	public JLabel getLblBanco() {
		return lblBanco;
	}

	public void setLblBanco(JLabel lblBanco) {
		this.lblBanco = lblBanco;
	}

	public JTextField getTxtNumCheque() {
		return txtNumCheque;
	}

	public void setTxtNumCheque(JTextField txtNumCheque) {
		this.txtNumCheque = txtNumCheque;
	}

	public JLabel getLblNumCheque() {
		return lblNumCheque;
	}

	public void setLblNumCheque(JLabel lblNumCheque) {
		this.lblNumCheque = lblNumCheque;
	}

	public JLabel getLblNumCuenta() {
		return lblNumCuenta;
	}

	public void setLblNumCuenta(JLabel lblNumCuenta) {
		this.lblNumCuenta = lblNumCuenta;
	}

	public JTextField getTxtNumCuenta() {
		return txtNumCuenta;
	}

	public void setTxtNumCuenta(JTextField txtNumCuenta) {
		this.txtNumCuenta = txtNumCuenta;
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
