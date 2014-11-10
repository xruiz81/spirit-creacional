package com.spirit.pos.gui.panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;



/**
 * @author Antonio Seiler
 */
public class JDReciboCaja extends JDialog {
	public JDReciboCaja(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDReciboCaja(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPane = new JPanel();
		lblReferencia = new JLabel();
		txtReferencia = new JTextField();
		lblTotal = new JLabel();
		txtTotal = new JTextField();
		okButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Recibo de caja");
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());
			
			//======== contentPane ========
			{
				contentPane.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(10)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
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
						new RowSpec(Sizes.dluY(10))
					}));
				
				//---- lblReferencia ----
				lblReferencia.setText("Referencia:");
				contentPane.add(lblReferencia, cc.xy(3, 3));
				
				//---- txtReferencia ----
				txtReferencia.setHorizontalAlignment(JTextField.RIGHT);
				contentPane.add(txtReferencia, cc.xy(5, 3));
				
				//---- lblTotal ----
				lblTotal.setText("Total:");
				contentPane.add(lblTotal, cc.xy(3, 5));
				
				//---- txtTotal ----
				txtTotal.setHorizontalAlignment(JTextField.RIGHT);
				contentPane.add(txtTotal, cc.xy(5, 5));
				
				//---- okButton ----
				okButton.setText("OK");
				contentPane.add(okButton, cc.xy(7, 5));
			}
			dialogPane.add(contentPane, BorderLayout.CENTER);
		}
		contentPane2.add(dialogPane, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JLabel lblTotal;
	private JTextField txtTotal;
	private JButton okButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JTextField getTxtReferencia() {
		return txtReferencia;
	}

	public void setTxtReferencia(JTextField txtReferencia) {
		this.txtReferencia = txtReferencia;
	}

	public JTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
}
