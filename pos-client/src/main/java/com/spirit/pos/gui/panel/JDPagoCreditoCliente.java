package com.spirit.pos.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
public class JDPagoCreditoCliente extends JDialogModelImpl {
	public JDPagoCreditoCliente(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPagoCreditoCliente(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel5 = new JPanel();
		txtUsuario = new JTextField();
		lblUsuario = new JLabel();
		txtClave = new JPasswordField();
		lblClave = new JLabel();
		panel3 = new JPanel();
		separator1 = new JSeparator();
		lblTotalPagar2 = new JLabel();
		lblImagen = new JLabel();
		txtDeuda = new JTextField();
		txtDatosCliente = new JTextField();
		lblCliente = new JLabel();
		lblDetalles = new JLabel();
		scrDetalles = new JScrollPane();
		tblDetalles = new JTable();
		lblTotalDisponible = new JLabel();
		txtMontoDisponible = new JTextField();
		lblMonto = new JLabel();
		txtMonto = new JTextField();
		lblBalance = new JLabel();
		txtBalance = new JTextField();
		panel6 = new JPanel();
		panel2 = new JPanel();
		btnAceptar = new JButton();
		btnCancelar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("*** AUTORIZACION REQUERIDA ***");
		setResizable(false);
		setModal(false);
		setFont(new Font("Dialog", Font.PLAIN, 26));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default",
			"fill:default"));

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
					new RowSpec(Sizes.DLUY8),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.DLUY8)
				}));
			
			//======== panel5 ========
			{
				panel5.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(100)),
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
				
				//---- lblUsuario ----
				lblUsuario.setText("USUARIO:");
				lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblUsuario, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtClave ----
				txtClave.setEditable(true);
				txtClave.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel5.add(txtClave, cc.xy(3, 3));
				
				//---- lblClave ----
				lblClave.setText("CLAVE:");
				lblClave.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel5.add(lblClave, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			panel1.add(panel5, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
			
			//======== panel3 ========
			{
				panel3.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(58)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(40)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(40), FormSpec.DEFAULT_GROW)
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
						new RowSpec(Sizes.dluY(45)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				panel3.add(separator1, cc.xywh(3, 1, 7, 1));
				
				//---- lblTotalPagar2 ----
				lblTotalPagar2.setText("DEUDA:");
				lblTotalPagar2.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel3.add(lblTotalPagar2, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblImagen ----
				lblImagen.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\PagoCreditoCiente.png"));
				panel3.add(lblImagen, cc.xywh(1, 5, 1, 5));
				
				//---- txtDeuda ----
				txtDeuda.setEditable(false);
				txtDeuda.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtDeuda.setHorizontalAlignment(JTextField.RIGHT);
				panel3.add(txtDeuda, cc.xy(5, 3));
				
				//---- txtDatosCliente ----
				txtDatosCliente.setEditable(false);
				txtDatosCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel3.add(txtDatosCliente, cc.xywh(5, 5, 5, 1));
				
				//---- lblCliente ----
				lblCliente.setText("Cliente:");
				lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel3.add(lblCliente, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblDetalles ----
				lblDetalles.setText(" Detalle de cr\u00e9dito(s):");
				lblDetalles.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel3.add(lblDetalles, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//======== scrDetalles ========
				{
					scrDetalles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrDetalles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					
					//---- tblDetalles ----
					tblDetalles.setModel(new DefaultTableModel(
						new Object[][] {
							{"", null},
							{null, null},
						},
						new String[] {
							"Fecha", "Valor"
						}
					) {
						boolean[] columnEditable = new boolean[] {
							false, false
						};
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					scrDetalles.setViewportView(tblDetalles);
				}
				panel3.add(scrDetalles, cc.xywh(5, 7, 3, 5));
				
				//---- lblTotalDisponible ----
				lblTotalDisponible.setText("Total disponible:");
				lblTotalDisponible.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel3.add(lblTotalDisponible, cc.xywh(3, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtMontoDisponible ----
				txtMontoDisponible.setEditable(false);
				txtMontoDisponible.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel3.add(txtMontoDisponible, cc.xy(5, 13));
				
				//---- lblMonto ----
				lblMonto.setText("MONTO:");
				lblMonto.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel3.add(lblMonto, cc.xywh(3, 15, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				
				//---- txtMonto ----
				txtMonto.setEditable(true);
				txtMonto.setFont(new Font("Tahoma", Font.BOLD, 16));
				panel3.add(txtMonto, cc.xy(5, 15));
				
				//---- lblBalance ----
				lblBalance.setText("BALANCE:");
				lblBalance.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel3.add(lblBalance, cc.xywh(3, 17, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtBalance ----
				txtBalance.setEditable(false);
				txtBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtBalance.setText("       0.00");
				txtBalance.setHorizontalAlignment(JTextField.RIGHT);
				panel3.add(txtBalance, cc.xy(5, 17));
			}
			panel1.add(panel3, cc.xy(3, 5));
			
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
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JPanel panel5;
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JPasswordField txtClave;
	private JLabel lblClave;
	private JPanel panel3;
	private JSeparator separator1;
	private JLabel lblTotalPagar2;
	private JLabel lblImagen;
	private JTextField txtDeuda;
	private JTextField txtDatosCliente;
	private JLabel lblCliente;
	private JLabel lblDetalles;
	private JScrollPane scrDetalles;
	private JTable tblDetalles;
	private JLabel lblTotalDisponible;
	private JTextField txtMontoDisponible;
	private JLabel lblMonto;
	private JTextField txtMonto;
	private JLabel lblBalance;
	private JTextField txtBalance;
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

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JPasswordField getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(JPasswordField txtClave) {
		this.txtClave = txtClave;
	}

	public JLabel getLblClave() {
		return lblClave;
	}

	public void setLblClave(JLabel lblClave) {
		this.lblClave = lblClave;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JSeparator getSeparator1() {
		return separator1;
	}

	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
	}

	public JLabel getLblTotalPagar2() {
		return lblTotalPagar2;
	}

	public void setLblTotalPagar2(JLabel lblTotalPagar2) {
		this.lblTotalPagar2 = lblTotalPagar2;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}

	public void setLblImagen(JLabel lblImagen) {
		this.lblImagen = lblImagen;
	}

	public JTextField getTxtDeuda() {
		return txtDeuda;
	}

	public void setTxtDeuda(JTextField txtDeuda) {
		this.txtDeuda = txtDeuda;
	}

	public JTextField getTxtDatosCliente() {
		return txtDatosCliente;
	}

	public void setTxtDatosCliente(JTextField txtDatosCliente) {
		this.txtDatosCliente = txtDatosCliente;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}

	public JLabel getLblDetalles() {
		return lblDetalles;
	}

	public void setLblDetalles(JLabel lblDetalles) {
		this.lblDetalles = lblDetalles;
	}

	public JScrollPane getScrDetalles() {
		return scrDetalles;
	}

	public void setScrDetalles(JScrollPane scrDetalles) {
		this.scrDetalles = scrDetalles;
	}

	public JTable getTblDetalles() {
		return tblDetalles;
	}

	public void setTblDetalles(JTable tblDetalles) {
		this.tblDetalles = tblDetalles;
	}

	public JLabel getLblTotalDisponible() {
		return lblTotalDisponible;
	}

	public void setLblTotalDisponible(JLabel lblTotalDisponible) {
		this.lblTotalDisponible = lblTotalDisponible;
	}

	public JTextField getTxtMontoDisponible() {
		return txtMontoDisponible;
	}

	public void setTxtMontoDisponible(JTextField txtMontoDisponible) {
		this.txtMontoDisponible = txtMontoDisponible;
	}

	public JLabel getLblMonto() {
		return lblMonto;
	}

	public void setLblMonto(JLabel lblMonto) {
		this.lblMonto = lblMonto;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(JTextField txtMonto) {
		this.txtMonto = txtMonto;
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
