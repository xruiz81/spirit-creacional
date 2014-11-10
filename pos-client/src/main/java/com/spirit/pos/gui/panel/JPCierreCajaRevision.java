package com.spirit.pos.gui.panel;



import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.MantenimientoModelImpl;
 



/**
 * @author Antonio Seiler
 */
public abstract class JPCierreCajaRevision extends MantenimientoModelImpl {
	public JPCierreCajaRevision() {	
		initComponents();
		setName("Cierre Caja Revision");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		label1 = new JLabel();
		lblCajero = new JLabel();
		lblCajero22 = new JLabel();
		cmbCajero = new JComboBox();
		lblCajero2 = new JLabel();
		cmbCaja = new JComboBox();
		cmbOficina = new JComboBox();
		label54 = new JLabel();
		cmbFecha = new JComboBox();
		label542 = new JLabel();
		lblFechas = new JLabel();
		panel2 = new JPanel();
		pnFormasPago = new JPanel();
		lblEfectivoPago = new JLabel();
		txtEfectivo = new JTextField();
		lblTarjetaPago = new JLabel();
		txtTarjeta = new JTextField();
		lblChequePago = new JLabel();
		txtCheque = new JTextField();
		lblGiftPago = new JLabel();
		txtGiftCards = new JTextField();
		lblDevolucionesPago = new JLabel();
		txtDevoluciones = new JTextField();
		separator1 = new JSeparator();
		lblCreditoCPago = new JLabel();
		txtCreditoCliente = new JTextField();
		panel22 = new JPanel();
		pnResumenFinal = new JPanel();
		lblDescuadreEfe2 = new JLabel();
		txtValorFinal = new JTextField();
		separator2 = new JSeparator();
		lblDescuadreEfe = new JLabel();
		txtDescuadreEfec = new JTextField();
		lblDescuadreDoc = new JLabel();
		txtDescuadreDoc = new JTextField();
		lblMultasPerdidasDoc = new JLabel();
		panel62 = new JPanel();
		btnRevisado = new JButton();
		txtMultasPerdidasDoc = new JTextField();
		panel3 = new JPanel();
		separator12 = new JSeparator();
		panelIngresoEgreso = new JPanel();
		lblCajaInicial = new JLabel();
		txtCajaInicial = new JTextField();
		lblCajaIngresos = new JLabel();
		txtCajaIngresos = new JTextField();
		lblCajaEgresos = new JLabel();
		txtCajaEgresos = new JTextField();
		panel4 = new JPanel();
		lblDonacion = new JLabel();
		txtDonacion = new JTextField();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setBackground(new Color(236, 233, 216));
		setBorder(null);
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(12)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(12))
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY4),
				new RowSpec(RowSpec.TOP, Sizes.dluY(12), FormSpec.NO_GROW),
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
				new RowSpec(RowSpec.FILL, Sizes.dluY(12), FormSpec.NO_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//---- label1 ----
		label1.setText("Khomo S.A");
		label1.setFont(new Font("Tahoma", Font.BOLD, 24));
		label1.setForeground(new Color(102, 51, 0));
		add(label1, cc.xy(3, 2));

		//---- lblCajero ----
		lblCajero.setText("Cajero:");
		lblCajero.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero, cc.xywh(3, 6, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblCajero22 ----
		lblCajero22.setText("Oficina:");
		lblCajero22.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero22, cc.xywh(3, 8, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCajero, cc.xy(5, 6));

		//---- lblCajero2 ----
		lblCajero2.setText("Caja-PC:");
		lblCajero2.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(lblCajero2, cc.xywh(3, 10, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbCaja, cc.xy(5, 10));
		add(cmbOficina, cc.xy(5, 8));

		//---- label54 ----
		label54.setText("Fecha:");
		label54.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label54, cc.xywh(3, 12, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		add(cmbFecha, cc.xy(5, 12));

		//---- label542 ----
		label542.setText("Fecha APERTURA / CIERRE:");
		label542.setFont(new Font("Tahoma", Font.BOLD, 12));
		add(label542, cc.xywh(3, 14, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));

		//---- lblFechas ----
		lblFechas.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblFechas, cc.xywh(5, 14, 3, 1));

		//======== panel2 ========
		{
			panel2.setBorder(new LineBorder(new Color(102, 51, 0), 2));
			panel2.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//======== pnFormasPago ========
			{
				pnFormasPago.setBorder(new TitledBorder("VENTAS: Formas de pago:"));
				pnFormasPago.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(53)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(55))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblEfectivoPago ----
				lblEfectivoPago.setText("Efectivo:");
				pnFormasPago.add(lblEfectivoPago, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtEfectivo ----
				txtEfectivo.setEditable(false);
				txtEfectivo.setHorizontalAlignment(JTextField.RIGHT);
				pnFormasPago.add(txtEfectivo, cc.xy(3, 1));
				
				//---- lblTarjetaPago ----
				lblTarjetaPago.setText("Tarjeta:");
				pnFormasPago.add(lblTarjetaPago, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtTarjeta ----
				txtTarjeta.setEditable(false);
				txtTarjeta.setHorizontalAlignment(JTextField.RIGHT);
				pnFormasPago.add(txtTarjeta, cc.xy(3, 3));
				
				//---- lblChequePago ----
				lblChequePago.setText("Cheque:");
				lblChequePago.setHorizontalAlignment(SwingConstants.RIGHT);
				pnFormasPago.add(lblChequePago, cc.xy(1, 5));
				
				//---- txtCheque ----
				txtCheque.setEditable(false);
				txtCheque.setHorizontalAlignment(JTextField.RIGHT);
				pnFormasPago.add(txtCheque, cc.xy(3, 5));
				
				//---- lblGiftPago ----
				lblGiftPago.setText("Gift Cards:");
				pnFormasPago.add(lblGiftPago, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtGiftCards ----
				txtGiftCards.setEditable(false);
				txtGiftCards.setHorizontalAlignment(JTextField.RIGHT);
				pnFormasPago.add(txtGiftCards, cc.xy(3, 7));
				
				//---- lblDevolucionesPago ----
				lblDevolucionesPago.setText("Devoluciones:");
				pnFormasPago.add(lblDevolucionesPago, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.CENTER));
				
				//---- txtDevoluciones ----
				txtDevoluciones.setEditable(false);
				txtDevoluciones.setHorizontalAlignment(JTextField.RIGHT);
				pnFormasPago.add(txtDevoluciones, cc.xy(3, 9));
				pnFormasPago.add(separator1, cc.xywh(1, 11, 3, 1));
				
				//---- lblCreditoCPago ----
				lblCreditoCPago.setText("Cr\u00e9dito Usado:");
				pnFormasPago.add(lblCreditoCPago, cc.xywh(1, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCreditoCliente ----
				txtCreditoCliente.setEditable(false);
				txtCreditoCliente.setHorizontalAlignment(JTextField.RIGHT);
				pnFormasPago.add(txtCreditoCliente, cc.xy(3, 13));
			}
			panel2.add(pnFormasPago, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
		}
		add(panel2, cc.xywh(3, 16, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== panel22 ========
		{
			panel22.setBorder(new LineBorder(new Color(102, 51, 0), 2));
			panel22.setLayout(new FormLayout(
				"default",
				"default"));
			
			//======== pnResumenFinal ========
			{
				pnResumenFinal.setBorder(new TitledBorder("RESUMEN FINAL"));
				pnResumenFinal.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(22)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(72)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(65))
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.DLUY8),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- lblDescuadreEfe2 ----
				lblDescuadreEfe2.setText("Valor Final:");
				lblDescuadreEfe2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescuadreEfe2.setHorizontalAlignment(SwingConstants.RIGHT);
				pnResumenFinal.add(lblDescuadreEfe2, cc.xy(3, 1));
				
				//---- txtValorFinal ----
				txtValorFinal.setEditable(false);
				txtValorFinal.setHorizontalAlignment(JTextField.RIGHT);
				pnResumenFinal.add(txtValorFinal, cc.xywh(5, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
				
				//---- separator2 ----
				separator2.setBackground(new Color(255, 153, 0));
				pnResumenFinal.add(separator2, cc.xywh(1, 3, 5, 1));
				
				//---- lblDescuadreEfe ----
				lblDescuadreEfe.setText("Descuadre x Efec:");
				lblDescuadreEfe.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescuadreEfe.setHorizontalAlignment(SwingConstants.RIGHT);
				pnResumenFinal.add(lblDescuadreEfe, cc.xy(3, 5));
				
				//---- txtDescuadreEfec ----
				txtDescuadreEfec.setEditable(false);
				txtDescuadreEfec.setHorizontalAlignment(JTextField.RIGHT);
				pnResumenFinal.add(txtDescuadreEfec, cc.xy(5, 5));
				
				//---- lblDescuadreDoc ----
				lblDescuadreDoc.setText("Descuadre x Doc:");
				lblDescuadreDoc.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblDescuadreDoc.setHorizontalAlignment(SwingConstants.RIGHT);
				pnResumenFinal.add(lblDescuadreDoc, cc.xywh(1, 7, 3, 1));
				
				//---- txtDescuadreDoc ----
				txtDescuadreDoc.setEditable(false);
				txtDescuadreDoc.setHorizontalAlignment(JTextField.RIGHT);
				pnResumenFinal.add(txtDescuadreDoc, cc.xy(5, 7));
				
				//---- lblMultasPerdidasDoc ----
				lblMultasPerdidasDoc.setText("Multas P\u00e9rdidas Doc:");
				lblMultasPerdidasDoc.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblMultasPerdidasDoc.setHorizontalAlignment(SwingConstants.RIGHT);
				pnResumenFinal.add(lblMultasPerdidasDoc, cc.xywh(1, 9, 3, 1));
				
				//======== panel62 ========
				{
					panel62.setBorder(new TitledBorder(null, "CAJA", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
					panel62.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(32)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(32)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(32)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(32))
						},
						new RowSpec[] {
							new RowSpec(RowSpec.TOP, Sizes.dluY(13), FormSpec.NO_GROW),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.DLUY11)
						}));
					
					//---- btnRevisado ----
					btnRevisado.setText("REVISADO");
					btnRevisado.setFont(new Font("Tahoma", Font.BOLD, 10));
					btnRevisado.setToolTipText("REVISADO");
					panel62.add(btnRevisado, cc.xywh(3, 1, 3, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
				}
				pnResumenFinal.add(panel62, cc.xywh(1, 11, 5, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));
				
				//---- txtMultasPerdidasDoc ----
				txtMultasPerdidasDoc.setEditable(false);
				txtMultasPerdidasDoc.setHorizontalAlignment(JTextField.RIGHT);
				pnResumenFinal.add(txtMultasPerdidasDoc, cc.xy(5, 9));
			}
			panel22.add(pnResumenFinal, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
		}
		add(panel22, cc.xywh(7, 16, 1, 1, CellConstraints.FILL, CellConstraints.FILL));

		//======== panel3 ========
		{
			panel3.setBorder(new LineBorder(new Color(102, 51, 0), 2));
			panel3.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.RELATED_GAP_ROWSPEC,
					new RowSpec(Sizes.dluY(12)),
					new RowSpec(Sizes.DLUY14),
					FormFactory.DEFAULT_ROWSPEC,
					new RowSpec(Sizes.DLUY4),
					FormFactory.DEFAULT_ROWSPEC
				}));
			panel3.add(separator12, cc.xy(1, 5));
			
			//======== panelIngresoEgreso ========
			{
				panelIngresoEgreso.setBorder(new TitledBorder("Ingreso / Egreso de Caja"));
				panelIngresoEgreso.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(53)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(55))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(15))
					}));
				
				//---- lblCajaInicial ----
				lblCajaInicial.setText("Caja Inicial:");
				panelIngresoEgreso.add(lblCajaInicial, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCajaInicial ----
				txtCajaInicial.setEditable(false);
				txtCajaInicial.setHorizontalAlignment(JTextField.RIGHT);
				panelIngresoEgreso.add(txtCajaInicial, cc.xy(3, 1));
				
				//---- lblCajaIngresos ----
				lblCajaIngresos.setText("Ingresos:");
				panelIngresoEgreso.add(lblCajaIngresos, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCajaIngresos ----
				txtCajaIngresos.setEditable(false);
				txtCajaIngresos.setHorizontalAlignment(JTextField.RIGHT);
				panelIngresoEgreso.add(txtCajaIngresos, cc.xy(3, 3));
				
				//---- lblCajaEgresos ----
				lblCajaEgresos.setText("Egresos:");
				panelIngresoEgreso.add(lblCajaEgresos, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtCajaEgresos ----
				txtCajaEgresos.setEditable(false);
				txtCajaEgresos.setHorizontalAlignment(JTextField.RIGHT);
				panelIngresoEgreso.add(txtCajaEgresos, cc.xy(3, 5));
			}
			panel3.add(panelIngresoEgreso, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
			
			//======== panel4 ========
			{
				panel4.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(56)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(57))
					},
					RowSpec.decodeSpecs("default")));
				
				//---- lblDonacion ----
				lblDonacion.setText("Donaci\u00f3n: ");
				panel4.add(lblDonacion, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtDonacion ----
				txtDonacion.setEditable(false);
				txtDonacion.setHorizontalAlignment(JTextField.RIGHT);
				panel4.add(txtDonacion, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
			}
			panel3.add(panel4, cc.xy(1, 7));
		}
		add(panel3, cc.xywh(5, 16, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JLabel lblCajero;
	private JLabel lblCajero22;
	private JComboBox cmbCajero;
	private JLabel lblCajero2;
	private JComboBox cmbCaja;
	private JComboBox cmbOficina;
	private JLabel label54;
	private JComboBox cmbFecha;
	private JLabel label542;
	private JLabel lblFechas;
	private JPanel panel2;
	private JPanel pnFormasPago;
	private JLabel lblEfectivoPago;
	private JTextField txtEfectivo;
	private JLabel lblTarjetaPago;
	private JTextField txtTarjeta;
	private JLabel lblChequePago;
	private JTextField txtCheque;
	private JLabel lblGiftPago;
	private JTextField txtGiftCards;
	private JLabel lblDevolucionesPago;
	private JTextField txtDevoluciones;
	private JSeparator separator1;
	private JLabel lblCreditoCPago;
	private JTextField txtCreditoCliente;
	private JPanel panel22;
	private JPanel pnResumenFinal;
	private JLabel lblDescuadreEfe2;
	private JTextField txtValorFinal;
	private JSeparator separator2;
	private JLabel lblDescuadreEfe;
	private JTextField txtDescuadreEfec;
	private JLabel lblDescuadreDoc;
	private JTextField txtDescuadreDoc;
	private JLabel lblMultasPerdidasDoc;
	private JPanel panel62;
	private JButton btnRevisado;
	private JTextField txtMultasPerdidasDoc;
	private JPanel panel3;
	private JSeparator separator12;
	private JPanel panelIngresoEgreso;
	private JLabel lblCajaInicial;
	private JTextField txtCajaInicial;
	private JLabel lblCajaIngresos;
	private JTextField txtCajaIngresos;
	private JLabel lblCajaEgresos;
	private JTextField txtCajaEgresos;
	private JPanel panel4;
	private JLabel lblDonacion;
	private JTextField txtDonacion;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLblCajero() {
		return lblCajero;
	}

	public void setLblCajero(JLabel lblCajero) {
		this.lblCajero = lblCajero;
	}

	public JLabel getLblCajero22() {
		return lblCajero22;
	}

	public void setLblCajero22(JLabel lblCajero22) {
		this.lblCajero22 = lblCajero22;
	}

	public JComboBox getCmbCajero() {
		return cmbCajero;
	}

	public void setCmbCajero(JComboBox cmbCajero) {
		this.cmbCajero = cmbCajero;
	}

	public JLabel getLblCajero2() {
		return lblCajero2;
	}

	public void setLblCajero2(JLabel lblCajero2) {
		this.lblCajero2 = lblCajero2;
	}

	public JComboBox getCmbCaja() {
		return cmbCaja;
	}

	public void setCmbCaja(JComboBox cmbCaja) {
		this.cmbCaja = cmbCaja;
	}

	public JComboBox getCmbOficina() {
		return cmbOficina;
	}

	public void setCmbOficina(JComboBox cmbOficina) {
		this.cmbOficina = cmbOficina;
	}

	public JLabel getLabel54() {
		return label54;
	}

	public void setLabel54(JLabel label54) {
		this.label54 = label54;
	}

	public JComboBox getCmbFecha() {
		return cmbFecha;
	}

	public void setCmbFecha(JComboBox cmbFecha) {
		this.cmbFecha = cmbFecha;
	}

	public JLabel getLabel542() {
		return label542;
	}

	public void setLabel542(JLabel label542) {
		this.label542 = label542;
	}

	public JLabel getLblFechas() {
		return lblFechas;
	}

	public void setLblFechas(JLabel lblFechas) {
		this.lblFechas = lblFechas;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JPanel getPnFormasPago() {
		return pnFormasPago;
	}

	public void setPnFormasPago(JPanel pnFormasPago) {
		this.pnFormasPago = pnFormasPago;
	}

	public JLabel getLblEfectivoPago() {
		return lblEfectivoPago;
	}

	public void setLblEfectivoPago(JLabel lblEfectivoPago) {
		this.lblEfectivoPago = lblEfectivoPago;
	}

	public JTextField getTxtEfectivo() {
		return txtEfectivo;
	}

	public void setTxtEfectivo(JTextField txtEfectivo) {
		this.txtEfectivo = txtEfectivo;
	}

	public JLabel getLblTarjetaPago() {
		return lblTarjetaPago;
	}

	public void setLblTarjetaPago(JLabel lblTarjetaPago) {
		this.lblTarjetaPago = lblTarjetaPago;
	}

	public JTextField getTxtTarjeta() {
		return txtTarjeta;
	}

	public void setTxtTarjeta(JTextField txtTarjeta) {
		this.txtTarjeta = txtTarjeta;
	}

	public JLabel getLblChequePago() {
		return lblChequePago;
	}

	public void setLblChequePago(JLabel lblChequePago) {
		this.lblChequePago = lblChequePago;
	}

	public JTextField getTxtCheque() {
		return txtCheque;
	}

	public void setTxtCheque(JTextField txtCheque) {
		this.txtCheque = txtCheque;
	}

	public JLabel getLblGiftPago() {
		return lblGiftPago;
	}

	public void setLblGiftPago(JLabel lblGiftPago) {
		this.lblGiftPago = lblGiftPago;
	}

	public JTextField getTxtGiftCards() {
		return txtGiftCards;
	}

	public void setTxtGiftCards(JTextField txtGiftCards) {
		this.txtGiftCards = txtGiftCards;
	}

	public JLabel getLblDevolucionesPago() {
		return lblDevolucionesPago;
	}

	public void setLblDevolucionesPago(JLabel lblDevolucionesPago) {
		this.lblDevolucionesPago = lblDevolucionesPago;
	}

	public JTextField getTxtDevoluciones() {
		return txtDevoluciones;
	}

	public void setTxtDevoluciones(JTextField txtDevoluciones) {
		this.txtDevoluciones = txtDevoluciones;
	}

	public JSeparator getSeparator1() {
		return separator1;
	}

	public void setSeparator1(JSeparator separator1) {
		this.separator1 = separator1;
	}

	public JLabel getLblCreditoCPago() {
		return lblCreditoCPago;
	}

	public void setLblCreditoCPago(JLabel lblCreditoCPago) {
		this.lblCreditoCPago = lblCreditoCPago;
	}

	public JTextField getTxtCreditoCliente() {
		return txtCreditoCliente;
	}

	public void setTxtCreditoCliente(JTextField txtCreditoCliente) {
		this.txtCreditoCliente = txtCreditoCliente;
	}

	public JPanel getPanel22() {
		return panel22;
	}

	public void setPanel22(JPanel panel22) {
		this.panel22 = panel22;
	}

	public JPanel getPnResumenFinal() {
		return pnResumenFinal;
	}

	public void setPnResumenFinal(JPanel pnResumenFinal) {
		this.pnResumenFinal = pnResumenFinal;
	}

	public JLabel getLblDescuadreEfe2() {
		return lblDescuadreEfe2;
	}

	public void setLblDescuadreEfe2(JLabel lblDescuadreEfe2) {
		this.lblDescuadreEfe2 = lblDescuadreEfe2;
	}

	public JTextField getTxtValorFinal() {
		return txtValorFinal;
	}

	public void setTxtValorFinal(JTextField txtValorFinal) {
		this.txtValorFinal = txtValorFinal;
	}

	public JSeparator getSeparator2() {
		return separator2;
	}

	public void setSeparator2(JSeparator separator2) {
		this.separator2 = separator2;
	}

	public JLabel getLblDescuadreEfe() {
		return lblDescuadreEfe;
	}

	public void setLblDescuadreEfe(JLabel lblDescuadreEfe) {
		this.lblDescuadreEfe = lblDescuadreEfe;
	}

	public JTextField getTxtDescuadreEfec() {
		return txtDescuadreEfec;
	}

	public void setTxtDescuadreEfec(JTextField txtDescuadreEfec) {
		this.txtDescuadreEfec = txtDescuadreEfec;
	}

	public JLabel getLblDescuadreDoc() {
		return lblDescuadreDoc;
	}

	public void setLblDescuadreDoc(JLabel lblDescuadreDoc) {
		this.lblDescuadreDoc = lblDescuadreDoc;
	}

	public JTextField getTxtDescuadreDoc() {
		return txtDescuadreDoc;
	}

	public void setTxtDescuadreDoc(JTextField txtDescuadreDoc) {
		this.txtDescuadreDoc = txtDescuadreDoc;
	}

	public JLabel getLblMultasPerdidasDoc() {
		return lblMultasPerdidasDoc;
	}

	public void setLblMultasPerdidasDoc(JLabel lblMultasPerdidasDoc) {
		this.lblMultasPerdidasDoc = lblMultasPerdidasDoc;
	}

	public JPanel getPanel62() {
		return panel62;
	}

	public void setPanel62(JPanel panel62) {
		this.panel62 = panel62;
	}

	public JButton getBtnRevisado() {
		return btnRevisado;
	}

	public void setBtnRevisado(JButton btnRevisado) {
		this.btnRevisado = btnRevisado;
	}

	public JTextField getTxtMultasPerdidasDoc() {
		return txtMultasPerdidasDoc;
	}

	public void setTxtMultasPerdidasDoc(JTextField txtMultasPerdidasDoc) {
		this.txtMultasPerdidasDoc = txtMultasPerdidasDoc;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JSeparator getSeparator12() {
		return separator12;
	}

	public void setSeparator12(JSeparator separator12) {
		this.separator12 = separator12;
	}

	public JPanel getPanelIngresoEgreso() {
		return panelIngresoEgreso;
	}

	public void setPanelIngresoEgreso(JPanel panelIngresoEgreso) {
		this.panelIngresoEgreso = panelIngresoEgreso;
	}

	public JLabel getLblCajaInicial() {
		return lblCajaInicial;
	}

	public void setLblCajaInicial(JLabel lblCajaInicial) {
		this.lblCajaInicial = lblCajaInicial;
	}

	public JTextField getTxtCajaInicial() {
		return txtCajaInicial;
	}

	public void setTxtCajaInicial(JTextField txtCajaInicial) {
		this.txtCajaInicial = txtCajaInicial;
	}

	public JLabel getLblCajaIngresos() {
		return lblCajaIngresos;
	}

	public void setLblCajaIngresos(JLabel lblCajaIngresos) {
		this.lblCajaIngresos = lblCajaIngresos;
	}

	public JTextField getTxtCajaIngresos() {
		return txtCajaIngresos;
	}

	public void setTxtCajaIngresos(JTextField txtCajaIngresos) {
		this.txtCajaIngresos = txtCajaIngresos;
	}

	public JLabel getLblCajaEgresos() {
		return lblCajaEgresos;
	}

	public void setLblCajaEgresos(JLabel lblCajaEgresos) {
		this.lblCajaEgresos = lblCajaEgresos;
	}

	public JTextField getTxtCajaEgresos() {
		return txtCajaEgresos;
	}

	public void setTxtCajaEgresos(JTextField txtCajaEgresos) {
		this.txtCajaEgresos = txtCajaEgresos;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JLabel getLblDonacion() {
		return lblDonacion;
	}

	public void setLblDonacion(JLabel lblDonacion) {
		this.lblDonacion = lblDonacion;
	}

	public JTextField getTxtDonacion() {
		return txtDonacion;
	}

	public void setTxtDonacion(JTextField txtDonacion) {
		this.txtDonacion = txtDonacion;
	}
	
	
	
}
