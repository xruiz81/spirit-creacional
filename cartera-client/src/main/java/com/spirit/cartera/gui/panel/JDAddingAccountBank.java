package com.spirit.cartera.gui.panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
/*
 * Created by JFormDesigner on Wed Feb 16 12:38:49 COT 2011
 */




public class JDAddingAccountBank extends JDialog {
	public JDAddingAccountBank(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAddingAccountBank(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jdAddingAccountBank = new JPanel();
		jpConfirmation = new JPanel();
		lblMessage = new JLabel();
		lblBank = new JLabel();
		cmbBank = new JComboBox();
		lblAccountBank = new JLabel();
		txtAccountBank = new JTextField();
		lblAccountType = new JLabel();
		rbCheckingAccount = new JRadioButton();
		rbSavingsAccount = new JRadioButton();
		jpButtons = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Nueva cuenta bancaria");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== jdAddingAccountBank ========
		{
			jdAddingAccountBank.setBorder(Borders.DIALOG_BORDER);
			jdAddingAccountBank.setLayout(new BorderLayout());

			//======== jpConfirmation ========
			{
				jpConfirmation.setBorder(new EtchedBorder());
				jpConfirmation.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;150dlu)"),
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(10))
					}));

				//---- lblMessage ----
				lblMessage.setText("Ingrese los datos de la nueva cuenta bancaria:");
				lblMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
				jpConfirmation.add(lblMessage, cc.xywh(3, 3, 5, 1));

				//---- lblBank ----
				lblBank.setText("Banco:");
				lblBank.setFont(new Font("Tahoma", Font.BOLD, 11));
				jpConfirmation.add(lblBank, cc.xy(3, 7));
				jpConfirmation.add(cmbBank, cc.xywh(5, 7, 3, 1));

				//---- lblAccountBank ----
				lblAccountBank.setText("Cuenta bancaria:");
				lblAccountBank.setFont(new Font("Tahoma", Font.BOLD, 11));
				jpConfirmation.add(lblAccountBank, cc.xy(3, 9));
				jpConfirmation.add(txtAccountBank, cc.xywh(5, 9, 3, 1));

				//---- lblAccountType ----
				lblAccountType.setText("Tipo de cuenta:");
				lblAccountType.setFont(new Font("Tahoma", Font.BOLD, 11));
				jpConfirmation.add(lblAccountType, cc.xy(3, 11));

				//---- rbCheckingAccount ----
				rbCheckingAccount.setText("Corriente");
				rbCheckingAccount.setSelected(true);
				jpConfirmation.add(rbCheckingAccount, cc.xy(5, 11));

				//---- rbSavingsAccount ----
				rbSavingsAccount.setText("Ahorros");
				jpConfirmation.add(rbSavingsAccount, cc.xy(7, 11));
			}
			jdAddingAccountBank.add(jpConfirmation, BorderLayout.CENTER);

			//======== jpButtons ========
			{
				jpButtons.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				jpButtons.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.GLUE_COLSPEC,
						FormFactory.BUTTON_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.BUTTON_COLSPEC
					},
					RowSpec.decodeSpecs("pref")));

				//---- btnAccept ----
				btnAccept.setText("Aceptar");
				jpButtons.add(btnAccept, cc.xy(2, 1));

				//---- btnCancel ----
				btnCancel.setText("Cancelar");
				jpButtons.add(btnCancel, cc.xy(4, 1));
			}
			jdAddingAccountBank.add(jpButtons, BorderLayout.SOUTH);
		}
		contentPane.add(jdAddingAccountBank, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//---- bgAccountTypes ----
		ButtonGroup bgAccountTypes = new ButtonGroup();
		bgAccountTypes.add(rbCheckingAccount);
		bgAccountTypes.add(rbSavingsAccount);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel jdAddingAccountBank;
	private JPanel jpConfirmation;
	private JLabel lblMessage;
	private JLabel lblBank;
	private JComboBox cmbBank;
	private JLabel lblAccountBank;
	private JTextField txtAccountBank;
	private JLabel lblAccountType;
	private JRadioButton rbCheckingAccount;
	private JRadioButton rbSavingsAccount;
	private JPanel jpButtons;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getJdAddingAccountBank() {
		return jdAddingAccountBank;
	}

	public void setJdAddingAccountBank(JPanel jdAddingAccountBank) {
		this.jdAddingAccountBank = jdAddingAccountBank;
	}

	public JPanel getJpConfirmation() {
		return jpConfirmation;
	}

	public void setJpConfirmation(JPanel jpConfirmation) {
		this.jpConfirmation = jpConfirmation;
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}

	public JLabel getLblBank() {
		return lblBank;
	}

	public void setLblBank(JLabel lblBank) {
		this.lblBank = lblBank;
	}

	public JComboBox getCmbBank() {
		return cmbBank;
	}

	public void setCmbBank(JComboBox cmbBank) {
		this.cmbBank = cmbBank;
	}

	public JLabel getLblAccountBank() {
		return lblAccountBank;
	}

	public void setLblAccountBank(JLabel lblAccountBank) {
		this.lblAccountBank = lblAccountBank;
	}

	public JTextField getTxtAccountBank() {
		return txtAccountBank;
	}

	public void setTxtAccountBank(JTextField txtAccountBank) {
		this.txtAccountBank = txtAccountBank;
	}

	public JLabel getLblAccountType() {
		return lblAccountType;
	}

	public void setLblAccountType(JLabel lblAccountType) {
		this.lblAccountType = lblAccountType;
	}

	public JRadioButton getRbCheckingAccount() {
		return rbCheckingAccount;
	}

	public void setRbCheckingAccount(JRadioButton rbCheckingAccount) {
		this.rbCheckingAccount = rbCheckingAccount;
	}

	public JRadioButton getRbSavingsAccount() {
		return rbSavingsAccount;
	}

	public void setRbSavingsAccount(JRadioButton rbSavingsAccount) {
		this.rbSavingsAccount = rbSavingsAccount;
	}

	public JPanel getJpButtons() {
		return jpButtons;
	}

	public void setJpButtons(JPanel jpButtons) {
		this.jpButtons = jpButtons;
	}

	public JButton getBtnAccept() {
		return btnAccept;
	}

	public void setBtnAccept(JButton btnAccept) {
		this.btnAccept = btnAccept;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
}
