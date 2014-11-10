package com.spirit.nomina.gui.panel;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import com.spirit.client.model.JDialogModelImpl;
/*
 * Created by JFormDesigner on Wed Dec 03 12:37:00 COT 2008
 */



/**
 * @author Antonio Seiler
 */
public class JDAutorizacionRequerida extends JDialogModelImpl {
	
	public JDAutorizacionRequerida(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDAutorizacionRequerida(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel5 = new JPanel();
		txtUsuario = new JTextField();
		lblTotalPagar = new JLabel();
		txtClave = new TextField();
		lblTotalEntregado = new JLabel();
		panel52 = new JPanel();
		label1 = new JLabel();
		panel6 = new JPanel();
		panel2 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		separator1 = new JSeparator();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("*** AUTORIZACION REQUERIDA ***");
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
					FormFactory.DEFAULT_COLSPEC
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
					new RowSpec(Sizes.dluY(12))
				}));
			
			//======== panel5 ========
			{
				panel5.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(58))
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW)
					}));
				
				//---- txtUsuario ----
				txtUsuario.setEditable(true);
				txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel5.add(txtUsuario, cc.xy(3, 1));
				
				//---- lblTotalPagar ----
				lblTotalPagar.setText("USUARIO:");
				lblTotalPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblTotalPagar, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtClave ----
				txtClave.setEditable(true);
				txtClave.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel5.add(txtClave, cc.xy(3, 3));
				
				//---- lblTotalEntregado ----
				lblTotalEntregado.setText("CLAVE:");
				lblTotalEntregado.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblTotalEntregado, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			panel1.add(panel5, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
			
			//======== panel52 ========
			{
				panel52.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("70dlu"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW)
					}));
				
				//---- label1 ----
				label1.setText("@creacional.com");
				label1.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel52.add(label1, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
			}
			panel1.add(panel52, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
			
			//======== panel6 ========
			{
				panel6.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(75), FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("default")));
				
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
				panel6.add(panel2, cc.xywh(1, 1, 3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
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
	private JTextField txtUsuario;
	private JLabel lblTotalPagar;
	private TextField txtClave;
	private JLabel lblTotalEntregado;
	private JPanel panel52;
	private JLabel label1;
	private JPanel panel6;
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

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public JLabel getLblTotalPagar() {
		return lblTotalPagar;
	}

	public void setLblTotalPagar(JLabel lblTotalPagar) {
		this.lblTotalPagar = lblTotalPagar;
	}

	public TextField getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(TextField txtClave) {
		this.txtClave = txtClave;
	}

	public JLabel getLblTotalEntregado() {
		return lblTotalEntregado;
	}

	public void setLblTotalEntregado(JLabel lblTotalEntregado) {
		this.lblTotalEntregado = lblTotalEntregado;
	}

	public JPanel getPanel52() {
		return panel52;
	}

	public void setPanel52(JPanel panel52) {
		this.panel52 = panel52;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public void setPanel6(JPanel panel6) {
		this.panel6 = panel6;
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
