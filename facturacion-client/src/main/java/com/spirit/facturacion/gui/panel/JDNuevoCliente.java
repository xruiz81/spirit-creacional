package com.spirit.facturacion.gui.panel;
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
public class JDNuevoCliente extends JDialogModelImpl {
	public JDNuevoCliente(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDNuevoCliente(Dialog owner) {
		super(owner);
		initComponents();
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		label1 = new JLabel();
		panel5 = new JPanel();
		lblTipoIdentificacionCliente = new JLabel();
		cmbTipoIdentificacionCliente = new JComboBox();
		txtIdentificacionCliente = new JTextField();
		lblIdentificacionCliente = new JLabel();
		txtRazonSocialCliente = new JTextField();
		lblRazonSocialCliente = new JLabel();
		txtDireccionClienteOficina = new JTextField();
		lblDireccionClienteOficina = new JLabel();
		txtTelefonoClienteOficina = new JTextField();
		lblTelefonoClienteOficina = new JLabel();
		lblEmailClienteOficina = new JLabel();
		txtEmailClienteOficina = new JTextField();
		separator1 = new JSeparator();
		panel6 = new JPanel();
		panel2 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Ingreso Nuevo Cliente");
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
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
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
					new RowSpec(Sizes.dluY(12))
				}));
			
			//---- label1 ----
			label1.setIcon(new ImageIcon("H:\\Documents and Settings\\RRHH\\Mis documentos\\Mis im\u00e1genes\\user_64x64.png"));
			panel1.add(label1, cc.xy(3, 3));
			
			//======== panel5 ========
			{
				panel5.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(100)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(58))
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
						new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW)
					}));
				
				//---- lblTipoIdentificacionCliente ----
				lblTipoIdentificacionCliente.setText("Tipo Identificaci\u00f3n:");
				panel5.add(lblTipoIdentificacionCliente, cc.xy(1, 1));
				panel5.add(cmbTipoIdentificacionCliente, cc.xy(3, 1));
				panel5.add(txtIdentificacionCliente, cc.xy(3, 3));
				
				//---- lblIdentificacionCliente ----
				lblIdentificacionCliente.setText("Identificaci\u00f3n:");
				panel5.add(lblIdentificacionCliente, cc.xy(1, 3));
				panel5.add(txtRazonSocialCliente, cc.xywh(3, 5, 3, 1));
				
				//---- lblRazonSocialCliente ----
				lblRazonSocialCliente.setText("Raz\u00f3n social:");
				panel5.add(lblRazonSocialCliente, cc.xy(1, 5));
				panel5.add(txtDireccionClienteOficina, cc.xywh(3, 7, 3, 1));
				
				//---- lblDireccionClienteOficina ----
				lblDireccionClienteOficina.setText("Direcci\u00f3n:");
				panel5.add(lblDireccionClienteOficina, cc.xy(1, 7));
				panel5.add(txtTelefonoClienteOficina, cc.xy(3, 9));
				
				//---- lblTelefonoClienteOficina ----
				lblTelefonoClienteOficina.setText("Tel\u00e9fono:");
				panel5.add(lblTelefonoClienteOficina, cc.xy(1, 9));
				
				//---- lblEmailClienteOficina ----
				lblEmailClienteOficina.setText("E-mail:");
				panel5.add(lblEmailClienteOficina, cc.xy(1, 11));
				panel5.add(txtEmailClienteOficina, cc.xywh(3, 11, 3, 1));
			}
			panel1.add(panel5, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
			panel1.add(separator1, cc.xy(5, 5));
			
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
					btnAceptar.setText("GUARDAR");
					btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
					panel2.add(btnAceptar, cc.xywh(1, 1, 2, 1));
					
					//---- btnCancelar ----
					btnCancelar.setText("CANCELAR");
					btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
					panel2.add(btnCancelar, cc.xy(3, 1));
				}
				panel6.add(panel2, cc.xywh(1, 1, 3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			}
			panel1.add(panel6, cc.xy(5, 7));
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JLabel label1;
	private JPanel panel5;
	private JLabel lblTipoIdentificacionCliente;
	private JComboBox cmbTipoIdentificacionCliente;
	private JTextField txtIdentificacionCliente;
	private JLabel lblIdentificacionCliente;
	private JTextField txtRazonSocialCliente;
	private JLabel lblRazonSocialCliente;
	private JTextField txtDireccionClienteOficina;
	private JLabel lblDireccionClienteOficina;
	private JTextField txtTelefonoClienteOficina;
	private JLabel lblTelefonoClienteOficina;
	private JLabel lblEmailClienteOficina;
	private JTextField txtEmailClienteOficina;
	private JSeparator separator1;
	private JPanel panel6;
	private JPanel panel2;
	private JButton btnAceptar;
	private JButton btnCancelar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
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

	public JPanel getPanel5() {
		return panel5;
	}

	public void setPanel5(JPanel panel5) {
		this.panel5 = panel5;
	}

	public JLabel getLblTipoIdentificacionCliente() {
		return lblTipoIdentificacionCliente;
	}

	public void setLblTipoIdentificacionCliente(JLabel lblTipoIdentificacionCliente) {
		this.lblTipoIdentificacionCliente = lblTipoIdentificacionCliente;
	}

	public JComboBox getCmbTipoIdentificacionCliente() {
		return cmbTipoIdentificacionCliente;
	}

	public void setCmbTipoIdentificacionCliente(
			JComboBox cmbTipoIdentificacionCliente) {
		this.cmbTipoIdentificacionCliente = cmbTipoIdentificacionCliente;
	}

	public JTextField getTxtIdentificacionCliente() {
		return txtIdentificacionCliente;
	}

	public void setTxtIdentificacionCliente(JTextField txtIdentificacionCliente) {
		this.txtIdentificacionCliente = txtIdentificacionCliente;
	}

	public JLabel getLblIdentificacionCliente() {
		return lblIdentificacionCliente;
	}

	public void setLblIdentificacionCliente(JLabel lblIdentificacionCliente) {
		this.lblIdentificacionCliente = lblIdentificacionCliente;
	}

	public JTextField getTxtRazonSocialCliente() {
		return txtRazonSocialCliente;
	}

	public void setTxtRazonSocialCliente(JTextField txtRazonSocialCliente) {
		this.txtRazonSocialCliente = txtRazonSocialCliente;
	}

	public JLabel getLblRazonSocialCliente() {
		return lblRazonSocialCliente;
	}

	public void setLblRazonSocialCliente(JLabel lblRazonSocialCliente) {
		this.lblRazonSocialCliente = lblRazonSocialCliente;
	}

	public JTextField getTxtDireccionClienteOficina() {
		return txtDireccionClienteOficina;
	}

	public void setTxtDireccionClienteOficina(JTextField txtDireccionClienteOficina) {
		this.txtDireccionClienteOficina = txtDireccionClienteOficina;
	}

	public JLabel getLblDireccionClienteOficina() {
		return lblDireccionClienteOficina;
	}

	public void setLblDireccionClienteOficina(JLabel lblDireccionClienteOficina) {
		this.lblDireccionClienteOficina = lblDireccionClienteOficina;
	}

	public JTextField getTxtTelefonoClienteOficina() {
		return txtTelefonoClienteOficina;
	}

	public void setTxtTelefonoClienteOficina(JTextField txtTelefonoClienteOficina) {
		this.txtTelefonoClienteOficina = txtTelefonoClienteOficina;
	}

	public JLabel getLblTelefonoClienteOficina() {
		return lblTelefonoClienteOficina;
	}

	public void setLblTelefonoClienteOficina(JLabel lblTelefonoClienteOficina) {
		this.lblTelefonoClienteOficina = lblTelefonoClienteOficina;
	}

	public JLabel getLblEmailClienteOficina() {
		return lblEmailClienteOficina;
	}

	public void setLblEmailClienteOficina(JLabel lblEmailClienteOficina) {
		this.lblEmailClienteOficina = lblEmailClienteOficina;
	}

	public JTextField getTxtEmailClienteOficina() {
		return txtEmailClienteOficina;
	}

	public void setTxtEmailClienteOficina(JTextField txtEmailClienteOficina) {
		this.txtEmailClienteOficina = txtEmailClienteOficina;
	}

	public JSeparator getSeparator1() {
		return separator1;
	}

	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
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
	
	
}
