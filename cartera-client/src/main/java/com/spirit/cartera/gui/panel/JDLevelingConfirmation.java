package com.spirit.cartera.gui.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
/*
 * Created by JFormDesigner on Thu Dec 09 16:31:19 COT 2010
 */



public class JDLevelingConfirmation extends JDialog {
	public JDLevelingConfirmation(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDLevelingConfirmation(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jdPendingBalancesConfirmation = new JPanel();
		jpConfirmation = new JPanel();
		lblQuestion = new JLabel();
		rbOptionYes = new JRadioButton();
		cmbLevelingDocument = new JComboBox();
		cbGenerateAdvancePayment = new JCheckBox();
		cmbAdvancePaymentDocument = new JComboBox();
		rbOptionNo = new JRadioButton();
		jpButtons = new JPanel();
		btnAccept = new JButton();
		btnCancel = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Confirmaci\u00f3n de nivelaci\u00f3n");
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== jdPendingBalancesConfirmation ========
		{
			jdPendingBalancesConfirmation.setBorder(Borders.DIALOG_BORDER);
			jdPendingBalancesConfirmation.setLayout(new BorderLayout());

			//======== jpConfirmation ========
			{
				jpConfirmation.setBorder(new EtchedBorder());
				jpConfirmation.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec("max(default;200dlu):grow"),
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

				//---- lblQuestion ----
				lblQuestion.setText("\u00bfDesea nivelar el saldo pendiente de este comprobante?");
				lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 11));
				jpConfirmation.add(lblQuestion, cc.xywh(3, 3, 3, 1));

				//---- rbOptionYes ----
				rbOptionYes.setText("S\u00ed, deseo nivelarlo con un comprobante de este tipo:");
				jpConfirmation.add(rbOptionYes, cc.xy(3, 7));
				jpConfirmation.add(cmbLevelingDocument, cc.xy(5, 7));

				//---- cbGenerateAdvancePayment ----
				cbGenerateAdvancePayment.setHorizontalAlignment(SwingConstants.RIGHT);
				cbGenerateAdvancePayment.setText("Generar anticipo:");
				jpConfirmation.add(cbGenerateAdvancePayment, cc.xy(3, 9));
				jpConfirmation.add(cmbAdvancePaymentDocument, cc.xy(5, 9));

				//---- rbOptionNo ----
				rbOptionNo.setText("No, deseo mantener el saldo pendiente en este comprobante y cruzarlo posteriormente");
				jpConfirmation.add(rbOptionNo, cc.xywh(3, 11, 3, 1));
			}
			jdPendingBalancesConfirmation.add(jpConfirmation, BorderLayout.CENTER);

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
			jdPendingBalancesConfirmation.add(jpButtons, BorderLayout.SOUTH);
		}
		contentPane.add(jdPendingBalancesConfirmation, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());

		//---- bgOptions ----
		ButtonGroup bgOptions = new ButtonGroup();
		bgOptions.add(rbOptionYes);
		bgOptions.add(rbOptionNo);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JPanel jdPendingBalancesConfirmation;
	private JPanel jpConfirmation;
	private JLabel lblQuestion;
	private JRadioButton rbOptionYes;
	private JComboBox cmbLevelingDocument;
	private JCheckBox cbGenerateAdvancePayment;
	private JComboBox cmbAdvancePaymentDocument;
	private JRadioButton rbOptionNo;
	private JPanel jpButtons;
	private JButton btnAccept;
	private JButton btnCancel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JPanel getJdPendingBalancesConfirmation() {
		return jdPendingBalancesConfirmation;
	}

	public void setJdPendingBalancesConfirmation(
			JPanel jdPendingBalancesConfirmation) {
		this.jdPendingBalancesConfirmation = jdPendingBalancesConfirmation;
	}

	public JPanel getJpConfirmation() {
		return jpConfirmation;
	}

	public void setJpConfirmation(JPanel jpConfirmation) {
		this.jpConfirmation = jpConfirmation;
	}

	public JLabel getLblQuestion() {
		return lblQuestion;
	}

	public void setLblQuestion(JLabel lblQuestion) {
		this.lblQuestion = lblQuestion;
	}

	public JRadioButton getRbOptionYes() {
		return rbOptionYes;
	}

	public void setRbOptionYes(JRadioButton rbOptionYes) {
		this.rbOptionYes = rbOptionYes;
	}

	public JComboBox getCmbLevelingDocument() {
		return cmbLevelingDocument;
	}

	public void setCmbLevelingDocument(JComboBox cmbLevelingDocument) {
		this.cmbLevelingDocument = cmbLevelingDocument;
	}

	public JCheckBox getCbGenerateAdvancePayment() {
		return cbGenerateAdvancePayment;
	}

	public void setCbGenerateAdvancePayment(JCheckBox cbGenerateAdvancePayment) {
		this.cbGenerateAdvancePayment = cbGenerateAdvancePayment;
	}

	public JComboBox getCmbAdvancePaymentDocument() {
		return cmbAdvancePaymentDocument;
	}

	public void setCmbAdvancePaymentDocument(JComboBox cmbAdvancePaymentDocument) {
		this.cmbAdvancePaymentDocument = cmbAdvancePaymentDocument;
	}

	public JRadioButton getRbOptionNo() {
		return rbOptionNo;
	}

	public void setRbOptionNo(JRadioButton rbOptionNo) {
		this.rbOptionNo = rbOptionNo;
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
