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

public abstract class JDPagoGiftCard extends JDialogModelImpl {
	public JDPagoGiftCard(Frame owner) {
		super(owner);
		initComponents();
		 
	}

	public JDPagoGiftCard(Dialog owner) {
		super(owner);
		initComponents(); 
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel52 = new JPanel();
		txtDeuda = new JTextField();
		lblBalance2 = new JLabel();
		separator3 = new JSeparator();
		panel6 = new JPanel();
		lblTotalPagar = new JLabel();
		txtSerialNumber = new JTextField();
		separator1 = new JSeparator();
		panel5 = new JPanel();
		txtBalance = new JTextField();
		lblBalance = new JLabel();
		panel4 = new JPanel();
		btnProcesar = new JButton();
		btnCancelar = new JButton();
		panel7 = new JPanel();
		lblTotalEntregado = new JLabel();
		txtMontoAcreditado = new JTextField();
		label1 = new JLabel();
		lblTotalEntregado2 = new JLabel();
		txtMontoDisponible = new JTextField();
		separator2 = new JSeparator();
		panel8 = new JPanel();
		lblTotalPagar2 = new JLabel();
		txtMonto = new JTextField();
		separator4 = new JSeparator();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Pago GIFT CARD");
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
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12))
				}));
			
			//======== panel52 ========
			{
				panel52.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(100), FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("default")));
				
				//---- txtDeuda ----
				txtDeuda.setEditable(false);
				txtDeuda.setFont(new Font("Tahoma", Font.BOLD, 20));
				panel52.add(txtDeuda, cc.xy(5, 1));
				
				//---- lblBalance2 ----
				lblBalance2.setText("DEUDA:");
				lblBalance2.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel52.add(lblBalance2, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			panel1.add(panel52, cc.xy(3, 3));
			panel1.add(separator3, cc.xy(3, 5));
			
			//======== panel6 ========
			{
				panel6.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(40)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblTotalPagar ----
				lblTotalPagar.setText("        Serial Number:");
				lblTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel6.add(lblTotalPagar, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtSerialNumber ----
				txtSerialNumber.setEditable(true);
				txtSerialNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel6.add(txtSerialNumber, cc.xy(5, 3));
			}
			panel1.add(panel6, cc.xy(3, 7));
			panel1.add(separator1, cc.xy(3, 9));
			
			//======== panel5 ========
			{
				panel5.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(46)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("default")));
				
				//---- txtBalance ----
				txtBalance.setEditable(false);
				txtBalance.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel5.add(txtBalance, cc.xy(5, 1));
				
				//---- lblBalance ----
				lblBalance.setText("BALANCE:");
				lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel5.add(lblBalance, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			panel1.add(panel5, cc.xy(3, 19));
			
			//======== panel4 ========
			{
				panel4.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- btnProcesar ----
				btnProcesar.setText("ACEPTAR");
				btnProcesar.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel4.add(btnProcesar, cc.xy(1, 1));
				
				//---- btnCancelar ----
				btnCancelar.setText("CANCELAR");
				btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel4.add(btnCancelar, cc.xy(3, 1));
			}
			panel1.add(panel4, cc.xywh(3, 21, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//======== panel7 ========
			{
				panel7.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(40)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblTotalEntregado ----
				lblTotalEntregado.setText("  Monto acreditado:");
				lblTotalEntregado.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel7.add(lblTotalEntregado, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtMontoAcreditado ----
				txtMontoAcreditado.setEditable(false);
				panel7.add(txtMontoAcreditado, cc.xy(5, 1));
				
				//---- label1 ----
				label1.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\smiley48.png"));
				panel7.add(label1, cc.xywh(1, 1, 1, 3, CellConstraints.CENTER, CellConstraints.DEFAULT));
				
				//---- lblTotalEntregado2 ----
				lblTotalEntregado2.setText("Monto disponible:");
				lblTotalEntregado2.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel7.add(lblTotalEntregado2, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtMontoDisponible ----
				txtMontoDisponible.setEditable(false);
				panel7.add(txtMontoDisponible, cc.xy(5, 3));
			}
			panel1.add(panel7, cc.xy(3, 11));
			panel1.add(separator2, cc.xy(3, 13));
			
			//======== panel8 ========
			{
				panel8.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("default")));
				
				//---- lblTotalPagar2 ----
				lblTotalPagar2.setText("MONTO A APLICAR:");
				lblTotalPagar2.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel8.add(lblTotalPagar2, cc.xy(1, 1));
				
				//---- txtMonto ----
				txtMonto.setEditable(true);
				txtMonto.setFont(new Font("Tahoma", Font.BOLD, 20));
				panel8.add(txtMonto, cc.xy(3, 1));
			}
			panel1.add(panel8, cc.xy(3, 15));
			panel1.add(separator4, cc.xy(3, 17));
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JPanel panel52;
	private JTextField txtDeuda;
	private JLabel lblBalance2;
	private JSeparator separator3;
	private JPanel panel6;
	private JLabel lblTotalPagar;
	private JTextField txtSerialNumber;
	private JSeparator separator1;
	private JPanel panel5;
	private JTextField txtBalance;
	private JLabel lblBalance;
	private JPanel panel4;
	private JButton btnProcesar;
	private JButton btnCancelar;
	private JPanel panel7;
	private JLabel lblTotalEntregado;
	private JTextField txtMontoAcreditado;
	private JLabel label1;
	private JLabel lblTotalEntregado2;
	private JTextField txtMontoDisponible;
	private JSeparator separator2;
	private JPanel panel8;
	private JLabel lblTotalPagar2;
	private JTextField txtMonto;
	private JSeparator separator4;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel52() {
		return panel52;
	}

	public JTextField getTxtDeuda() {
		return txtDeuda;
	}

	public JLabel getLblBalance2() {
		return lblBalance2;
	}

	public JSeparator getSeparator3() {
		return separator3;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public JLabel getLblTotalPagar() {
		return lblTotalPagar;
	}

	public JTextField getTxtSerialNumber() {
		return txtSerialNumber;
	}

	public JSeparator getSeparator1() {
		return separator1;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public JTextField getTxtBalance() {
		return txtBalance;
	}

	public JLabel getLblBalance() {
		return lblBalance;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JPanel getPanel7() {
		return panel7;
	}

	public JLabel getLblTotalEntregado() {
		return lblTotalEntregado;
	}

	public JTextField getTxtMontoAcreditado() {
		return txtMontoAcreditado;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JLabel getLblTotalEntregado2() {
		return lblTotalEntregado2;
	}

	public JTextField getTxtMontoDisponible() {
		return txtMontoDisponible;
	}

	public JSeparator getSeparator2() {
		return separator2;
	}

	public JPanel getPanel8() {
		return panel8;
	}

	public JLabel getLblTotalPagar2() {
		return lblTotalPagar2;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public JSeparator getSeparator4() {
		return separator4;
	}
}
