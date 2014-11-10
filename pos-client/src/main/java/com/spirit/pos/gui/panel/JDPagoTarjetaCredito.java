package com.spirit.pos.gui.panel;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
public abstract class JDPagoTarjetaCredito extends JDialogModelImpl {
	public JDPagoTarjetaCredito(Frame owner) {
		super(owner);
		initComponents();
	}

	public JDPagoTarjetaCredito(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel1 = new JPanel();
		panel52 = new JPanel();
		lblBalance2 = new JLabel();
		txtTotalPagar = new JTextField();
		panel7 = new JPanel();
		lblImagenTarjeta = new JLabel();
		label2 = new JLabel();
		cmbTipoTarjetaCredito = new JComboBox();
		lblTotalPagar = new JLabel();
		txtNumTarjeta = new JTextField();
		lblTotalEntregado4 = new JLabel();
		txtTitularCuenta = new JTextField();
		lblTotalEntregado = new JLabel();
		txtExpira = new JTextField();
		lblTotalEntregado2 = new JLabel();
		txtCodSeguridad = new JTextField();
		panel8 = new JPanel();
		lblformapago = new JLabel();
		cmbformapagoTC = new JComboBox();
		lblTotalPagar2 = new JLabel();
		txtMonto = new JTextField();
		lblTotalEntregado3 = new JLabel();
		txtCodigoAutorizacion = new JTextField();
		progressBar1 = new JProgressBar();
		panel9 = new JPanel();
		btnProcesar = new JButton();
		panel5 = new JPanel();
		lblBalance = new JLabel();
		txtBalance = new JTextField();
		panel4 = new JPanel();
		btnCancelar = new JButton();
		btnAceptar = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Pago Tarjeta de Credito");
		setResizable(false);
		setModal(false);
		setFont(new Font("Dialog", Font.PLAIN, 26));
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"default:grow",
			"fill:default:grow"));

		//======== panel1 ========
		{
			panel1.setPreferredSize(null);
			panel1.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(12)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(12))
				},
				new RowSpec[] {
					new RowSpec(Sizes.dluY(12)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(15)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(10)),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12))
				}));
			
			//======== panel52 ========
			{
				panel52.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- lblBalance2 ----
				lblBalance2.setText("DEUDA:");
				lblBalance2.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel52.add(lblBalance2, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtTotalPagar ----
				txtTotalPagar.setEditable(false);
				txtTotalPagar.setFont(new Font("Tahoma", Font.PLAIN, 26));
				txtTotalPagar.setText("       0.00");
				panel52.add(txtTotalPagar, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.FILL));
			}
			panel1.add(panel52, cc.xywh(3, 3, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//======== panel7 ========
			{
				panel7.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(40)),
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
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblImagenTarjeta ----
				lblImagenTarjeta.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\CreditCard.png"));
				panel7.add(lblImagenTarjeta, cc.xywh(1, 1, 2, 13));
				
				//---- label2 ----
				label2.setText("Tipo de Tarjeta: ");
				panel7.add(label2, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panel7.add(cmbTipoTarjetaCredito, cc.xywh(5, 3, 5, 1));
				
				//---- lblTotalPagar ----
				lblTotalPagar.setText("N\u00fam. tarjeta:");
				lblTotalPagar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblTotalPagar, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtNumTarjeta ----
				txtNumTarjeta.setEditable(true);
				panel7.add(txtNumTarjeta, cc.xywh(5, 5, 5, 1));
				
				//---- lblTotalEntregado4 ----
				lblTotalEntregado4.setText("Titular tarjeta:");
				lblTotalEntregado4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblTotalEntregado4, cc.xywh(3, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtTitularCuenta ----
				txtTitularCuenta.setEditable(true);
				panel7.add(txtTitularCuenta, cc.xywh(5, 7, 5, 1));
				
				//---- lblTotalEntregado ----
				lblTotalEntregado.setText("Expira:");
				lblTotalEntregado.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblTotalEntregado, cc.xywh(3, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtExpira ----
				txtExpira.setEditable(false);
				panel7.add(txtExpira, cc.xy(5, 9));
				
				//---- lblTotalEntregado2 ----
				lblTotalEntregado2.setText("C\u00f3digo de Seguridad:");
				lblTotalEntregado2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel7.add(lblTotalEntregado2, cc.xywh(3, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCodSeguridad ----
				txtCodSeguridad.setEditable(true);
				panel7.add(txtCodSeguridad, cc.xy(5, 11));
			}
			panel1.add(panel7, cc.xy(3, 5));
			
			//======== panel8 ========
			{
				panel8.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(30)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(70)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblformapago ----
				lblformapago.setText("Forma pago:");
				lblformapago.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel8.add(lblformapago, cc.xywh(3, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- cmbformapagoTC ----
				cmbformapagoTC.setModel(new DefaultComboBoxModel(new String[] {
					"Corriente",
					"Diferido   3 meses",
					"Diferido   6 meses",
					"Diferido 12 meses"
				}));
				panel8.add(cmbformapagoTC, cc.xy(5, 1));
				
				//---- lblTotalPagar2 ----
				lblTotalPagar2.setText("MONTO");
				lblTotalPagar2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel8.add(lblTotalPagar2, cc.xywh(3, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				panel8.add(txtMonto, cc.xy(5, 3));
				
				//---- lblTotalEntregado3 ----
				lblTotalEntregado3.setText("C\u00f3digo de autorizaci\u00f3n:");
				lblTotalEntregado3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel8.add(lblTotalEntregado3, cc.xywh(3, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCodigoAutorizacion ----
				txtCodigoAutorizacion.setEditable(false);
				panel8.add(txtCodigoAutorizacion, cc.xy(5, 5));
			}
			panel1.add(panel8, cc.xy(3, 7));
			panel1.add(progressBar1, cc.xy(3, 9));
			
			//======== panel9 ========
			{
				panel9.setLayout(new FormLayout(
					"default",
					"default"));
				
				//---- btnProcesar ----
				btnProcesar.setText("PROCESAR");
				btnProcesar.setFont(new Font("Tahoma", Font.PLAIN, 11));
				panel9.add(btnProcesar, cc.xy(1, 1));
			}
			panel1.add(panel9, cc.xywh(3, 11, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//======== panel5 ========
			{
				panel5.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- lblBalance ----
				lblBalance.setText("BALANCE:");
				lblBalance.setFont(new Font("Tahoma", Font.BOLD, 18));
				panel5.add(lblBalance, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtBalance ----
				txtBalance.setEditable(false);
				txtBalance.setFont(new Font("Tahoma", Font.PLAIN, 26));
				txtBalance.setText("       0.00");
				panel5.add(txtBalance, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.FILL));
			}
			panel1.add(panel5, cc.xywh(1, 15, 3, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
			
			//======== panel4 ========
			{
				panel4.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC
					},
					RowSpec.decodeSpecs("default")));
				
				//---- btnCancelar ----
				btnCancelar.setText("CANCELAR");
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				panel4.add(btnCancelar, cc.xywh(3, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
				
				//---- btnAceptar ----
				btnAceptar.setText("ACEPTAR");
				btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 12));
				panel4.add(btnAceptar, cc.xy(1, 1));
			}
			panel1.add(panel4, cc.xywh(3, 19, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
		}
		contentPane.add(panel1, cc.xy(1, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel1;
	private JPanel panel52;
	private JLabel lblBalance2;
	private JTextField txtTotalPagar;
	private JPanel panel7;
	private JLabel lblImagenTarjeta;
	private JLabel label2;
	private JComboBox cmbTipoTarjetaCredito;
	private JLabel lblTotalPagar;
	private JTextField txtNumTarjeta;
	private JLabel lblTotalEntregado4;
	private JTextField txtTitularCuenta;
	private JLabel lblTotalEntregado;
	private JTextField txtExpira;
	private JLabel lblTotalEntregado2;
	private JTextField txtCodSeguridad;
	private JPanel panel8;
	private JLabel lblformapago;
	private JComboBox cmbformapagoTC;
	private JLabel lblTotalPagar2;
	private JTextField txtMonto;
	private JLabel lblTotalEntregado3;
	private JTextField txtCodigoAutorizacion;
	private JProgressBar progressBar1;
	private JPanel panel9;
	private JButton btnProcesar;
	private JPanel panel5;
	private JLabel lblBalance;
	private JTextField txtBalance;
	private JPanel panel4;
	private JButton btnCancelar;
	private JButton btnAceptar;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel1() {
		return panel1;
	}

	public JPanel getPanel52() {
		return panel52;
	}

	public JLabel getLblBalance2() {
		return lblBalance2;
	}

	public JTextField getTxtTotalPagar() {
		return txtTotalPagar;
	}

	public JPanel getPanel7() {
		return panel7;
	}

	public JLabel getLblImagenTarjeta() {
		return lblImagenTarjeta;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public JComboBox getCmbTipoTarjetaCredito() {
		return cmbTipoTarjetaCredito;
	}

	public JLabel getLblTotalPagar() {
		return lblTotalPagar;
	}

	public JTextField getTxtNumTarjeta() {
		return txtNumTarjeta;
	}

	public JLabel getLblTotalEntregado4() {
		return lblTotalEntregado4;
	}

	public JTextField getTxtTitularCuenta() {
		return txtTitularCuenta;
	}

	public JLabel getLblTotalEntregado() {
		return lblTotalEntregado;
	}

	public JTextField getTxtExpira() {
		return txtExpira;
	}

	public JLabel getLblTotalEntregado2() {
		return lblTotalEntregado2;
	}

	public JTextField getTxtCodSeguridad() {
		return txtCodSeguridad;
	}

	public JPanel getPanel8() {
		return panel8;
	}

	public JLabel getLblformapago() {
		return lblformapago;
	}

	public JComboBox getCmbformapagoTC() {
		return cmbformapagoTC;
	}

	public JLabel getLblTotalPagar2() {
		return lblTotalPagar2;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public JLabel getLblTotalEntregado3() {
		return lblTotalEntregado3;
	}

	public JTextField getTxtCodigoAutorizacion() {
		return txtCodigoAutorizacion;
	}

	public JProgressBar getProgressBar1() {
		return progressBar1;
	}

	public JPanel getPanel9() {
		return panel9;
	}

	public JButton getBtnProcesar() {
		return btnProcesar;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public JLabel getLblBalance() {
		return lblBalance;
	}

	public JTextField getTxtBalance() {
		return txtBalance;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}
}
