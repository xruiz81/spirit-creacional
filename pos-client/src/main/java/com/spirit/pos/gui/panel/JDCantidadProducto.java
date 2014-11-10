package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.JDialogModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JDCantidadProducto extends JDialogModelImpl {
	public JDCantidadProducto(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDCantidadProducto(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label2 = new JLabel();
		chkProductoRegalo = new JCheckBox();
		panel1 = new JPanel();
		label1 = new JLabel();
		textField1 = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Ingrese la cantidad");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(40)),
				new ColumnSpec(Sizes.dluX(135)),
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
				new RowSpec(Sizes.dluY(12))
			}));

		//---- label2 ----
		label2.setIcon(new ImageIcon("C:\\Documents and Settings\\Spirit\\Escritorio\\images3.jpeg"));
		contentPane.add(label2, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//---- chkProductoRegalo ----
		chkProductoRegalo.setText("Producto de regalo");
		chkProductoRegalo.setFont(new Font("Tahoma", Font.BOLD, 18));
		chkProductoRegalo.setForeground(new Color(204, 153, 0));
		chkProductoRegalo.setBackground(Color.white);
		chkProductoRegalo.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(chkProductoRegalo, cc.xywh(4, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(80)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(50))
				},
				RowSpec.decodeSpecs("default")));
			
			//---- label1 ----
			label1.setText("CANTIDAD");
			label1.setFont(new Font("Tahoma", Font.BOLD, 20));
			panel1.add(label1, cc.xywh(1, 1, 2, 1, CellConstraints.FILL, CellConstraints.FILL));
			
			//---- textField1 ----
			textField1.setFont(new Font("Tahoma", Font.PLAIN, 28));
			panel1.add(textField1, cc.xy(3, 1));
		}
		contentPane.add(panel1, cc.xywh(3, 5, 2, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label2;
	private JCheckBox chkProductoRegalo;
	private JPanel panel1;
	private JLabel label1;
	private JTextField textField1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JCheckBox getChkProductoRegalo() {
		return chkProductoRegalo;
	}

	public void setChkProductoRegalo(JCheckBox chkProductoRegalo) {
		this.chkProductoRegalo = chkProductoRegalo;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JTextField getTextField1() {
		return textField1;
	}

	public void setTextField1(JTextField textField1) {
		this.textField1 = textField1;
	}
	
	
	
}
