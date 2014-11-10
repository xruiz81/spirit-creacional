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
public class JDIngresarPreimpresoReciboCaja extends JDialog {
	public JDIngresarPreimpresoReciboCaja(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDIngresarPreimpresoReciboCaja(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPane = new JPanel();
		lblPreimpreso = new JLabel();
		txtPreimpreso = new JTextField();
		okButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Ingresar preimpreso");
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
						new RowSpec(Sizes.dluY(10))
					}));
				
				//---- lblPreimpreso ----
				lblPreimpreso.setText("Preimpreso:");
				contentPane.add(lblPreimpreso, cc.xy(3, 3));
				contentPane.add(txtPreimpreso, cc.xy(5, 3));
				
				//---- okButton ----
				okButton.setText("OK");
				contentPane.add(okButton, cc.xy(7, 3));
			}
			dialogPane.add(contentPane, BorderLayout.CENTER);
		}
		contentPane2.add(dialogPane, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPane;
	private JLabel lblPreimpreso;
	private JTextField txtPreimpreso;
	private JButton okButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JTextField getTxtPreimpreso() {
		return txtPreimpreso;
	}

	public void setTxtPreimpreso(JTextField txtPreimpreso) {
		this.txtPreimpreso = txtPreimpreso;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
}
