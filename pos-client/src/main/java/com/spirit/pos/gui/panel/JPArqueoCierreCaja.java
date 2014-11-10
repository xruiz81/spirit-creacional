package com.spirit.pos.gui.panel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.spirit.client.model.SpiritModelImpl;



/**
 * @author Antonio Seiler
 */
public abstract class JPArqueoCierreCaja extends SpiritModelImpl {
	public JPArqueoCierreCaja() {	
		initComponents();
		setName("Arqueo/Cierre Caja");
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		panel5 = new JPanel();
		label54 = new JLabel();
		lblFechaActual = new JLabel();
		lblCajeroNombre = new JLabel();
		lblCajero2 = new JLabel();
		lblCajaPC = new JLabel();
		lblCajero = new JLabel();
		lblDetalleSeleccionado = new JLabel();
		splitPane2 = new JSplitPane();
		panel2 = new JPanel();
		panel1 = new JPanel();
		lblEfectivoLink = new JLabel();
		lblEfectivoPago = new JLabel();
		pnEfectivo = new JPanel();
		txtEfectivo = new JTextField();
		lblTarjetaPago = new JLabel();
		pnTarjetaC = new JPanel();
		txtTarjeta = new JTextField();
		lblTarjetaLink = new JLabel();
		lblXTarjeta = new JLabel();
		lblChequePago = new JLabel();
		PnCheque = new JPanel();
		txtCheque = new JTextField();
		lblChequeLink = new JLabel();
		lblXCheque = new JLabel();
		lblGiftPago = new JLabel();
		pnGiftC = new JPanel();
		txtGiftCards = new JTextField();
		lblGiftCLink = new JLabel();
		lblXGiftC = new JLabel();
		lblDevolucionesPago = new JLabel();
		pnDevoluciones = new JPanel();
		txtDevoluciones = new JTextField();
		lblDevolucionesLink = new JLabel();
		lblXDevoluciones = new JLabel();
		separator1 = new JSeparator();
		lblCreditoCPago = new JLabel();
		pnCreditoC = new JPanel();
		txtCreditoCliente = new JTextField();
		lblCreditoClienteLink = new JLabel();
		panel12 = new JPanel();
		lblCajaInicial = new JLabel();
		pnCajaInicial = new JPanel();
		txtCajaInicial = new JTextField();
		lblCajaInicialLink = new JLabel();
		lblCajaIngresos = new JLabel();
		pnIngresos = new JPanel();
		txtCajaIngresos = new JTextField();
		lblCajaIngresosLink = new JLabel();
		lblXIngresos = new JLabel();
		lblCajaEgresos = new JLabel();
		pnEgresos = new JPanel();
		txtCajaEgresos = new JTextField();
		lblCajaEgresosLink = new JLabel();
		lblXEgresos = new JLabel();
		panel122 = new JPanel();
		txtCantidadTeorica = new JTextField();
		lblCantidadTeorica = new JLabel();
		lblCantidadReal = new JLabel();
		txtCantReal = new JTextField();
		lblIconAlert = new JLabel();
		lblDescuadre = new JLabel();
		txtDescuadreEfectivo = new JTextField();
		lblDescuadre2 = new JLabel();
		txtDescuadreDoc = new JTextField();
		lblMultasPerdidasDoc = new JLabel();
		txtMultasPerdidasDoc = new JTextField();
		panel6 = new JPanel();
		btnCerrarCaja = new JButton();
		btnDesbloquear = new JButton();
		btnBloquearCaja = new JButton();
		panel3 = new JPanel();
		panel4 = new JPanel();
		scpDetalle = new JScrollPane();
		tblTablaDetalle = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setBackground(new Color(236, 233, 216));
		setBorder(null);
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.DLUX4),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(55)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.dluX(120)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX4)
			},
			new RowSpec[] {
				new RowSpec(Sizes.DLUY4),
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(15)),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.dluY(113), FormSpec.DEFAULT_GROW),
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.DLUY4)
			}));

		//======== panel5 ========
		{
			panel5.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(80)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC
				},
				RowSpec.decodeSpecs("default")));
			
			//---- label54 ----
			label54.setText("Fecha:");
			label54.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel5.add(label54, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- lblFechaActual ----
			lblFechaActual.setText("text");
			panel5.add(lblFechaActual, cc.xy(3, 1));
			
			//---- lblCajeroNombre ----
			lblCajeroNombre.setText("text");
			panel5.add(lblCajeroNombre, cc.xy(7, 1));
			
			//---- lblCajero2 ----
			lblCajero2.setText("Caja-PC:");
			lblCajero2.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel5.add(lblCajero2, cc.xywh(9, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			
			//---- lblCajaPC ----
			lblCajaPC.setText("text");
			panel5.add(lblCajaPC, cc.xy(11, 1));
			
			//---- lblCajero ----
			lblCajero.setText("Cajero:");
			lblCajero.setFont(new Font("Tahoma", Font.BOLD, 12));
			panel5.add(lblCajero, cc.xywh(5, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
		}
		add(panel5, cc.xywh(3, 3, 5, 1));
		add(lblDetalleSeleccionado, cc.xy(7, 3));

		//======== splitPane2 ========
		{
			
			//======== panel2 ========
			{
				panel2.setBorder(new LineBorder(new Color(0, 102, 0), 2));
				panel2.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//======== panel1 ========
				{
					panel1.setBorder(new TitledBorder("VENTAS: Formas de pago:"));
					panel1.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(53)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(55)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
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
					
					//---- lblEfectivoLink ----
					lblEfectivoLink.setText("Ver detalles...");
					lblEfectivoLink.setForeground(Color.blue);
					panel1.add(lblEfectivoLink, cc.xywh(5, 1, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
					
					//---- lblEfectivoPago ----
					lblEfectivoPago.setText("Efectivo:");
					panel1.add(lblEfectivoPago, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnEfectivo ========
					{
						pnEfectivo.setBorder(new LineBorder(Color.blue, 2));
						pnEfectivo.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtEfectivo ----
						txtEfectivo.setEditable(false);
						txtEfectivo.setText("210,00");
						txtEfectivo.setHorizontalAlignment(JTextField.RIGHT);
						pnEfectivo.add(txtEfectivo, cc.xy(1, 1));
					}
					panel1.add(pnEfectivo, cc.xy(3, 1));
					
					//---- lblTarjetaPago ----
					lblTarjetaPago.setText("Tarjeta:");
					panel1.add(lblTarjetaPago, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnTarjetaC ========
					{
						pnTarjetaC.setBorder(new LineBorder(Color.blue, 2));
						pnTarjetaC.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtTarjeta ----
						txtTarjeta.setEditable(false);
						txtTarjeta.setHorizontalAlignment(JTextField.RIGHT);
						pnTarjetaC.add(txtTarjeta, cc.xy(1, 1));
					}
					panel1.add(pnTarjetaC, cc.xy(3, 3));
					
					//---- lblTarjetaLink ----
					lblTarjetaLink.setText("Ver detalles...");
					lblTarjetaLink.setForeground(Color.blue);
					panel1.add(lblTarjetaLink, cc.xywh(5, 3, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
					
					//---- lblXTarjeta ----
					lblXTarjeta.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblXTarjeta.setForeground(Color.red);
					lblXTarjeta.setToolTipText("Incompleto");
					lblXTarjeta.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\lleno.png"));
					panel1.add(lblXTarjeta, cc.xy(7, 3));
					
					//---- lblChequePago ----
					lblChequePago.setText("Cheque:");
					lblChequePago.setHorizontalAlignment(SwingConstants.RIGHT);
					panel1.add(lblChequePago, cc.xy(1, 5));
					
					//======== PnCheque ========
					{
						PnCheque.setBorder(new LineBorder(Color.blue, 2));
						PnCheque.setBackground(Color.blue);
						PnCheque.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtCheque ----
						txtCheque.setEditable(false);
						txtCheque.setHorizontalAlignment(JTextField.RIGHT);
						PnCheque.add(txtCheque, cc.xy(1, 1));
					}
					panel1.add(PnCheque, cc.xy(3, 5));
					
					//---- lblChequeLink ----
					lblChequeLink.setText("Ver detalles...");
					lblChequeLink.setForeground(Color.blue);
					panel1.add(lblChequeLink, cc.xy(5, 5));
					
					//---- lblXCheque ----
					lblXCheque.setText("X");
					lblXCheque.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblXCheque.setForeground(Color.red);
					lblXCheque.setToolTipText("Incompleto");
					panel1.add(lblXCheque, cc.xy(7, 5));
					
					//---- lblGiftPago ----
					lblGiftPago.setText("Gift Cards");
					panel1.add(lblGiftPago, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnGiftC ========
					{
						pnGiftC.setBorder(new LineBorder(Color.blue, 2));
						pnGiftC.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtGiftCards ----
						txtGiftCards.setEditable(false);
						txtGiftCards.setHorizontalAlignment(JTextField.RIGHT);
						pnGiftC.add(txtGiftCards, cc.xy(1, 1));
					}
					panel1.add(pnGiftC, cc.xy(3, 7));
					
					//---- lblGiftCLink ----
					lblGiftCLink.setText("Ver detalles...");
					lblGiftCLink.setForeground(Color.blue);
					panel1.add(lblGiftCLink, cc.xywh(5, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
					
					//---- lblXGiftC ----
					lblXGiftC.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblXGiftC.setForeground(Color.red);
					lblXGiftC.setToolTipText("Incompleto");
					lblXGiftC.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\falta.png"));
					panel1.add(lblXGiftC, cc.xy(7, 7));
					
					//---- lblDevolucionesPago ----
					lblDevolucionesPago.setText("Devoluciones:");
					panel1.add(lblDevolucionesPago, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.CENTER));
					
					//======== pnDevoluciones ========
					{
						pnDevoluciones.setBorder(new LineBorder(Color.blue, 2));
						pnDevoluciones.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtDevoluciones ----
						txtDevoluciones.setEditable(false);
						txtDevoluciones.setHorizontalAlignment(JTextField.RIGHT);
						pnDevoluciones.add(txtDevoluciones, cc.xy(1, 1));
					}
					panel1.add(pnDevoluciones, cc.xy(3, 9));
					
					//---- lblDevolucionesLink ----
					lblDevolucionesLink.setText("Ver detalles...");
					lblDevolucionesLink.setForeground(Color.blue);
					panel1.add(lblDevolucionesLink, cc.xywh(5, 9, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
					
					//---- lblXDevoluciones ----
					lblXDevoluciones.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblXDevoluciones.setForeground(Color.red);
					lblXDevoluciones.setToolTipText("Incompleto");
					lblXDevoluciones.setText("X");
					panel1.add(lblXDevoluciones, cc.xy(7, 9));
					panel1.add(separator1, cc.xywh(1, 11, 7, 1));
					
					//---- lblCreditoCPago ----
					lblCreditoCPago.setText("Cr\u00e9dito Usado::");
					panel1.add(lblCreditoCPago, cc.xywh(1, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnCreditoC ========
					{
						pnCreditoC.setBorder(new LineBorder(Color.blue, 2));
						pnCreditoC.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtCreditoCliente ----
						txtCreditoCliente.setEditable(false);
						txtCreditoCliente.setHorizontalAlignment(JTextField.RIGHT);
						pnCreditoC.add(txtCreditoCliente, cc.xy(1, 1));
					}
					panel1.add(pnCreditoC, cc.xy(3, 13));
					
					//---- lblCreditoClienteLink ----
					lblCreditoClienteLink.setText("Ver detalles...");
					lblCreditoClienteLink.setForeground(Color.blue);
					panel1.add(lblCreditoClienteLink, cc.xywh(5, 13, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				}
				panel2.add(panel1, cc.xy(1, 1));
				
				//======== panel12 ========
				{
					panel12.setBorder(new TitledBorder("Ingreso / Egreso de Caja"));
					panel12.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(53)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(55)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
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
					panel12.add(lblCajaInicial, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnCajaInicial ========
					{
						pnCajaInicial.setBorder(new LineBorder(Color.blue, 2));
						pnCajaInicial.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtCajaInicial ----
						txtCajaInicial.setEditable(false);
						txtCajaInicial.setHorizontalAlignment(JTextField.RIGHT);
						pnCajaInicial.add(txtCajaInicial, cc.xy(1, 1));
					}
					panel12.add(pnCajaInicial, cc.xy(3, 1));
					
					//---- lblCajaInicialLink ----
					lblCajaInicialLink.setText("Ver detalles...");
					lblCajaInicialLink.setForeground(Color.blue);
					panel12.add(lblCajaInicialLink, cc.xywh(5, 1, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
					
					//---- lblCajaIngresos ----
					lblCajaIngresos.setText("Ingresos:");
					panel12.add(lblCajaIngresos, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnIngresos ========
					{
						pnIngresos.setBorder(new LineBorder(Color.blue, 2));
						pnIngresos.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtCajaIngresos ----
						txtCajaIngresos.setEditable(false);
						txtCajaIngresos.setHorizontalAlignment(JTextField.RIGHT);
						pnIngresos.add(txtCajaIngresos, cc.xy(1, 1));
					}
					panel12.add(pnIngresos, cc.xy(3, 3));
					
					//---- lblCajaIngresosLink ----
					lblCajaIngresosLink.setText("Ver detalles...");
					lblCajaIngresosLink.setForeground(Color.blue);
					panel12.add(lblCajaIngresosLink, cc.xy(5, 3));
					
					//---- lblXIngresos ----
					lblXIngresos.setText("X");
					lblXIngresos.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblXIngresos.setForeground(Color.red);
					lblXIngresos.setToolTipText("Incompleto");
					panel12.add(lblXIngresos, cc.xy(7, 3));
					
					//---- lblCajaEgresos ----
					lblCajaEgresos.setText("Egresos:");
					panel12.add(lblCajaEgresos, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//======== pnEgresos ========
					{
						pnEgresos.setBorder(new LineBorder(Color.blue, 2));
						pnEgresos.setLayout(new FormLayout(
							"default:grow",
							"default"));
						
						//---- txtCajaEgresos ----
						txtCajaEgresos.setEditable(false);
						txtCajaEgresos.setHorizontalAlignment(JTextField.RIGHT);
						pnEgresos.add(txtCajaEgresos, cc.xy(1, 1));
					}
					panel12.add(pnEgresos, cc.xy(3, 5));
					
					//---- lblCajaEgresosLink ----
					lblCajaEgresosLink.setText("Ver detalles...");
					lblCajaEgresosLink.setForeground(Color.blue);
					panel12.add(lblCajaEgresosLink, cc.xy(5, 5));
					
					//---- lblXEgresos ----
					lblXEgresos.setText("X");
					lblXEgresos.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblXEgresos.setForeground(Color.red);
					lblXEgresos.setToolTipText("Incompleto");
					panel12.add(lblXEgresos, cc.xy(7, 5));
				}
				panel2.add(panel12, cc.xy(1, 3));
				
				//======== panel122 ========
				{
					panel122.setBorder(new TitledBorder("RESUMEN FINAL"));
					panel122.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(22)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(72)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(65))
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
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
						}));
					
					//---- txtCantidadTeorica ----
					txtCantidadTeorica.setEditable(false);
					txtCantidadTeorica.setHorizontalAlignment(JTextField.RIGHT);
					panel122.add(txtCantidadTeorica, cc.xy(5, 1));
					
					//---- lblCantidadTeorica ----
					lblCantidadTeorica.setText("Cantidad Te\u00f3rica:");
					lblCantidadTeorica.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblCantidadTeorica.setHorizontalAlignment(SwingConstants.RIGHT);
					panel122.add(lblCantidadTeorica, cc.xywh(1, 1, 3, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
					
					//---- lblCantidadReal ----
					lblCantidadReal.setText("Cantidad Real:");
					lblCantidadReal.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblCantidadReal.setHorizontalAlignment(SwingConstants.RIGHT);
					panel122.add(lblCantidadReal, cc.xywh(1, 3, 3, 1));
					
					//---- txtCantReal ----
					txtCantReal.setHorizontalAlignment(JTextField.RIGHT);
					panel122.add(txtCantReal, cc.xy(5, 3));
					
					//---- lblIconAlert ----
					lblIconAlert.setText("icon");
					panel122.add(lblIconAlert, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- lblDescuadre ----
					lblDescuadre.setText("Descuadre x Efec:");
					lblDescuadre.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescuadre.setHorizontalAlignment(SwingConstants.RIGHT);
					panel122.add(lblDescuadre, cc.xy(3, 5));
					
					//---- txtDescuadreEfectivo ----
					txtDescuadreEfectivo.setEditable(false);
					txtDescuadreEfectivo.setHorizontalAlignment(JTextField.RIGHT);
					panel122.add(txtDescuadreEfectivo, cc.xy(5, 5));
					
					//---- lblDescuadre2 ----
					lblDescuadre2.setText("Descuadre x Doc:");
					lblDescuadre2.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblDescuadre2.setHorizontalAlignment(SwingConstants.RIGHT);
					panel122.add(lblDescuadre2, cc.xywh(1, 7, 3, 1));
					
					//---- txtDescuadreDoc ----
					txtDescuadreDoc.setEditable(false);
					txtDescuadreDoc.setHorizontalAlignment(JTextField.RIGHT);
					panel122.add(txtDescuadreDoc, cc.xy(5, 7));
					
					//---- lblMultasPerdidasDoc ----
					lblMultasPerdidasDoc.setText("Multas P\u00e9rdidas Doc:");
					lblMultasPerdidasDoc.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblMultasPerdidasDoc.setHorizontalAlignment(SwingConstants.RIGHT);
					panel122.add(lblMultasPerdidasDoc, cc.xywh(1, 9, 3, 1));
					
					//---- txtMultasPerdidasDoc ----
					txtMultasPerdidasDoc.setEditable(false);
					txtMultasPerdidasDoc.setHorizontalAlignment(JTextField.RIGHT);
					panel122.add(txtMultasPerdidasDoc, cc.xy(5, 9));
					
					//======== panel6 ========
					{
						panel6.setBorder(new TitledBorder(null, "CAJA", TitledBorder.LEFT, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
						panel6.setLayout(new FormLayout(
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
						
						//---- btnCerrarCaja ----
						btnCerrarCaja.setText("CERRAR");
						btnCerrarCaja.setFont(new Font("Tahoma", Font.BOLD, 10));
						panel6.add(btnCerrarCaja, cc.xywh(3, 1, 3, 1, CellConstraints.DEFAULT, CellConstraints.CENTER));
						
						//---- btnDesbloquear ----
						btnDesbloquear.setText("DESBLOQUEAR");
						btnDesbloquear.setFont(new Font("Tahoma", Font.BOLD, 10));
						panel6.add(btnDesbloquear, cc.xywh(1, 3, 4, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
						
						//---- btnBloquearCaja ----
						btnBloquearCaja.setText("BLOQUEAR");
						btnBloquearCaja.setFont(new Font("Tahoma", Font.BOLD, 10));
						panel6.add(btnBloquearCaja, cc.xywh(5, 3, 3, 1));
					}
					panel122.add(panel6, cc.xywh(1, 10, 5, 2, CellConstraints.CENTER, CellConstraints.DEFAULT));
				}
				panel2.add(panel122, cc.xy(1, 5));
			}
			splitPane2.setLeftComponent(panel2);
			
			//======== panel3 ========
			{
				panel3.setBorder(new LineBorder(new Color(0, 102, 0), 2));
				panel3.setLayout(new FormLayout(
					"default:grow",
					"default:grow"));
				
				//======== panel4 ========
				{
					panel4.setBorder(null);
					panel4.setLayout(new FormLayout(
						"default:grow",
						"default:grow"));
					
					//======== scpDetalle ========
					{
						scpDetalle.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						scpDetalle.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						
						//---- tblTablaDetalle ----
						tblTablaDetalle.setModel(new DefaultTableModel(
							new Object[][] {
								{null, null, null, null},
								{null, null, null, null},
								{null, null, null, null},
							},
							new String[] {
								"Fecha", "Valor", "Check", "Referencia"
							}
						));
						scpDetalle.setViewportView(tblTablaDetalle);
					}
					panel4.add(scpDetalle, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.TOP));
				}
				panel3.add(panel4, cc.xy(1, 1));
			}
			splitPane2.setRightComponent(panel3);
		}
		add(splitPane2, cc.xywh(3, 5, 6, 3, CellConstraints.FILL, CellConstraints.DEFAULT));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel panel5;
	private JLabel label54;
	private JLabel lblFechaActual;
	private JLabel lblCajeroNombre;
	private JLabel lblCajero2;
	private JLabel lblCajaPC;
	private JLabel lblCajero;
	private JLabel lblDetalleSeleccionado;
	private JSplitPane splitPane2;
	private JPanel panel2;
	private JPanel panel1;
	private JLabel lblEfectivoLink;
	private JLabel lblEfectivoPago;
	private JPanel pnEfectivo;
	private JTextField txtEfectivo;
	private JLabel lblTarjetaPago;
	private JPanel pnTarjetaC;
	private JTextField txtTarjeta;
	private JLabel lblTarjetaLink;
	private JLabel lblXTarjeta;
	private JLabel lblChequePago;
	private JPanel PnCheque;
	private JTextField txtCheque;
	private JLabel lblChequeLink;
	private JLabel lblXCheque;
	private JLabel lblGiftPago;
	private JPanel pnGiftC;
	private JTextField txtGiftCards;
	private JLabel lblGiftCLink;
	private JLabel lblXGiftC;
	private JLabel lblDevolucionesPago;
	private JPanel pnDevoluciones;
	private JTextField txtDevoluciones;
	private JLabel lblDevolucionesLink;
	private JLabel lblXDevoluciones;
	private JSeparator separator1;
	private JLabel lblCreditoCPago;
	private JPanel pnCreditoC;
	private JTextField txtCreditoCliente;
	private JLabel lblCreditoClienteLink;
	private JPanel panel12;
	private JLabel lblCajaInicial;
	private JPanel pnCajaInicial;
	private JTextField txtCajaInicial;
	private JLabel lblCajaInicialLink;
	private JLabel lblCajaIngresos;
	private JPanel pnIngresos;
	private JTextField txtCajaIngresos;
	private JLabel lblCajaIngresosLink;
	private JLabel lblXIngresos;
	private JLabel lblCajaEgresos;
	private JPanel pnEgresos;
	private JTextField txtCajaEgresos;
	private JLabel lblCajaEgresosLink;
	private JLabel lblXEgresos;
	private JPanel panel122;
	private JTextField txtCantidadTeorica;
	private JLabel lblCantidadTeorica;
	private JLabel lblCantidadReal;
	private JTextField txtCantReal;
	private JLabel lblIconAlert;
	private JLabel lblDescuadre;
	private JTextField txtDescuadreEfectivo;
	private JLabel lblDescuadre2;
	private JTextField txtDescuadreDoc;
	private JLabel lblMultasPerdidasDoc;
	private JTextField txtMultasPerdidasDoc;
	private JPanel panel6;
	private JButton btnCerrarCaja;
	private JButton btnDesbloquear;
	private JButton btnBloquearCaja;
	private JPanel panel3;
	private JPanel panel4;
	private JScrollPane scpDetalle;
	private JTable tblTablaDetalle;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPanel5() {
		return panel5;
	}

	public void setPanel5(JPanel panel5) {
		this.panel5 = panel5;
	}

	public JLabel getLabel54() {
		return label54;
	}

	public void setLabel54(JLabel label54) {
		this.label54 = label54;
	}

	public JLabel getLblFechaActual() {
		return lblFechaActual;
	}

	public void setLblFechaActual(JLabel lblFechaActual) {
		this.lblFechaActual = lblFechaActual;
	}

	public JLabel getLblCajeroNombre() {
		return lblCajeroNombre;
	}

	public void setLblCajeroNombre(JLabel lblCajeroNombre) {
		this.lblCajeroNombre = lblCajeroNombre;
	}

	public JLabel getLblCajero2() {
		return lblCajero2;
	}

	public void setLblCajero2(JLabel lblCajero2) {
		this.lblCajero2 = lblCajero2;
	}

	public JLabel getLblCajaPC() {
		return lblCajaPC;
	}

	public void setLblCajaPC(JLabel lblCajaPC) {
		this.lblCajaPC = lblCajaPC;
	}

	public JLabel getLblCajero() {
		return lblCajero;
	}

	public void setLblCajero(JLabel lblCajero) {
		this.lblCajero = lblCajero;
	}

	public JLabel getLblDetalleSeleccionado() {
		return lblDetalleSeleccionado;
	}

	public void setLblDetalleSeleccionado(JLabel lblDetalleSeleccionado) {
		this.lblDetalleSeleccionado = lblDetalleSeleccionado;
	}

	public JSplitPane getSplitPane2() {
		return splitPane2;
	}

	public void setSplitPane2(JSplitPane splitPane2) {
		this.splitPane2 = splitPane2;
	}

	public JPanel getPanel2() {
		return panel2;
	}

	public void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JLabel getLblEfectivoLink() {
		return lblEfectivoLink;
	}

	public void setLblEfectivoLink(JLabel lblEfectivoLink) {
		this.lblEfectivoLink = lblEfectivoLink;
	}

	public JLabel getLblEfectivoPago() {
		return lblEfectivoPago;
	}

	public void setLblEfectivoPago(JLabel lblEfectivoPago) {
		this.lblEfectivoPago = lblEfectivoPago;
	}

	public JPanel getPnEfectivo() {
		return pnEfectivo;
	}

	public void setPnEfectivo(JPanel pnEfectivo) {
		this.pnEfectivo = pnEfectivo;
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

	public JPanel getPnTarjetaC() {
		return pnTarjetaC;
	}

	public void setPnTarjetaC(JPanel pnTarjetaC) {
		this.pnTarjetaC = pnTarjetaC;
	}

	public JTextField getTxtTarjeta() {
		return txtTarjeta;
	}

	public void setTxtTarjeta(JTextField txtTarjeta) {
		this.txtTarjeta = txtTarjeta;
	}

	public JLabel getLblTarjetaLink() {
		return lblTarjetaLink;
	}

	public void setLblTarjetaLink(JLabel lblTarjetaLink) {
		this.lblTarjetaLink = lblTarjetaLink;
	}

	public JLabel getLblXTarjeta() {
		return lblXTarjeta;
	}

	public void setLblXTarjeta(JLabel lblXTarjeta) {
		this.lblXTarjeta = lblXTarjeta;
	}

	public JLabel getLblChequePago() {
		return lblChequePago;
	}

	public void setLblChequePago(JLabel lblChequePago) {
		this.lblChequePago = lblChequePago;
	}

	public JPanel getPnCheque() {
		return PnCheque;
	}

	public void setPnCheque(JPanel pnCheque) {
		PnCheque = pnCheque;
	}

	public JTextField getTxtCheque() {
		return txtCheque;
	}

	public void setTxtCheque(JTextField txtCheque) {
		this.txtCheque = txtCheque;
	}

	public JLabel getLblChequeLink() {
		return lblChequeLink;
	}

	public void setLblChequeLink(JLabel lblChequeLink) {
		this.lblChequeLink = lblChequeLink;
	}

	public JLabel getLblXCheque() {
		return lblXCheque;
	}

	public void setLblXCheque(JLabel lblXCheque) {
		this.lblXCheque = lblXCheque;
	}

	public JLabel getLblGiftPago() {
		return lblGiftPago;
	}

	public void setLblGiftPago(JLabel lblGiftPago) {
		this.lblGiftPago = lblGiftPago;
	}

	public JPanel getPnGiftC() {
		return pnGiftC;
	}

	public void setPnGiftC(JPanel pnGiftC) {
		this.pnGiftC = pnGiftC;
	}

	public JTextField getTxtGiftCards() {
		return txtGiftCards;
	}

	public void setTxtGiftCards(JTextField txtGiftCards) {
		this.txtGiftCards = txtGiftCards;
	}

	public JLabel getLblGiftCLink() {
		return lblGiftCLink;
	}

	public void setLblGiftCLink(JLabel lblGiftCLink) {
		this.lblGiftCLink = lblGiftCLink;
	}

	public JLabel getLblXGiftC() {
		return lblXGiftC;
	}

	public void setLblXGiftC(JLabel lblXGiftC) {
		this.lblXGiftC = lblXGiftC;
	}

	public JLabel getLblDevolucionesPago() {
		return lblDevolucionesPago;
	}

	public void setLblDevolucionesPago(JLabel lblDevolucionesPago) {
		this.lblDevolucionesPago = lblDevolucionesPago;
	}

	public JPanel getPnDevoluciones() {
		return pnDevoluciones;
	}

	public void setPnDevoluciones(JPanel pnDevoluciones) {
		this.pnDevoluciones = pnDevoluciones;
	}

	public JTextField getTxtDevoluciones() {
		return txtDevoluciones;
	}

	public void setTxtDevoluciones(JTextField txtDevoluciones) {
		this.txtDevoluciones = txtDevoluciones;
	}

	public JLabel getLblDevolucionesLink() {
		return lblDevolucionesLink;
	}

	public void setLblDevolucionesLink(JLabel lblDevolucionesLink) {
		this.lblDevolucionesLink = lblDevolucionesLink;
	}

	public JLabel getLblXDevoluciones() {
		return lblXDevoluciones;
	}

	public void setLblXDevoluciones(JLabel lblXDevoluciones) {
		this.lblXDevoluciones = lblXDevoluciones;
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

	public JPanel getPnCreditoC() {
		return pnCreditoC;
	}

	public void setPnCreditoC(JPanel pnCreditoC) {
		this.pnCreditoC = pnCreditoC;
	}

	public JTextField getTxtCreditoCliente() {
		return txtCreditoCliente;
	}

	public void setTxtCreditoCliente(JTextField txtCreditoCliente) {
		this.txtCreditoCliente = txtCreditoCliente;
	}

	public JLabel getLblCreditoClienteLink() {
		return lblCreditoClienteLink;
	}

	public void setLblCreditoClienteLink(JLabel lblCreditoClienteLink) {
		this.lblCreditoClienteLink = lblCreditoClienteLink;
	}

	public JPanel getPanel12() {
		return panel12;
	}

	public void setPanel12(JPanel panel12) {
		this.panel12 = panel12;
	}

	public JLabel getLblCajaInicial() {
		return lblCajaInicial;
	}

	public void setLblCajaInicial(JLabel lblCajaInicial) {
		this.lblCajaInicial = lblCajaInicial;
	}

	public JPanel getPnCajaInicial() {
		return pnCajaInicial;
	}

	public void setPnCajaInicial(JPanel pnCajaInicial) {
		this.pnCajaInicial = pnCajaInicial;
	}

	public JTextField getTxtCajaInicial() {
		return txtCajaInicial;
	}

	public void setTxtCajaInicial(JTextField txtCajaInicial) {
		this.txtCajaInicial = txtCajaInicial;
	}

	public JLabel getLblCajaInicialLink() {
		return lblCajaInicialLink;
	}

	public void setLblCajaInicialLink(JLabel lblCajaInicialLink) {
		this.lblCajaInicialLink = lblCajaInicialLink;
	}

	public JLabel getLblCajaIngresos() {
		return lblCajaIngresos;
	}

	public void setLblCajaIngresos(JLabel lblCajaIngresos) {
		this.lblCajaIngresos = lblCajaIngresos;
	}

	public JPanel getPnIngresos() {
		return pnIngresos;
	}

	public void setPnIngresos(JPanel pnIngresos) {
		this.pnIngresos = pnIngresos;
	}

	public JTextField getTxtCajaIngresos() {
		return txtCajaIngresos;
	}

	public void setTxtCajaIngresos(JTextField txtCajaIngresos) {
		this.txtCajaIngresos = txtCajaIngresos;
	}

	public JLabel getLblCajaIngresosLink() {
		return lblCajaIngresosLink;
	}

	public void setLblCajaIngresosLink(JLabel lblCajaIngresosLink) {
		this.lblCajaIngresosLink = lblCajaIngresosLink;
	}

	public JLabel getLblXIngresos() {
		return lblXIngresos;
	}

	public void setLblXIngresos(JLabel lblXIngresos) {
		this.lblXIngresos = lblXIngresos;
	}

	public JLabel getLblCajaEgresos() {
		return lblCajaEgresos;
	}

	public void setLblCajaEgresos(JLabel lblCajaEgresos) {
		this.lblCajaEgresos = lblCajaEgresos;
	}

	public JPanel getPnEgresos() {
		return pnEgresos;
	}

	public void setPnEgresos(JPanel pnEgresos) {
		this.pnEgresos = pnEgresos;
	}

	public JTextField getTxtCajaEgresos() {
		return txtCajaEgresos;
	}

	public void setTxtCajaEgresos(JTextField txtCajaEgresos) {
		this.txtCajaEgresos = txtCajaEgresos;
	}

	public JLabel getLblCajaEgresosLink() {
		return lblCajaEgresosLink;
	}

	public void setLblCajaEgresosLink(JLabel lblCajaEgresosLink) {
		this.lblCajaEgresosLink = lblCajaEgresosLink;
	}

	public JLabel getLblXEgresos() {
		return lblXEgresos;
	}

	public void setLblXEgresos(JLabel lblXEgresos) {
		this.lblXEgresos = lblXEgresos;
	}

	public JPanel getPanel122() {
		return panel122;
	}

	public void setPanel122(JPanel panel122) {
		this.panel122 = panel122;
	}

	public JTextField getTxtCantidadTeorica() {
		return txtCantidadTeorica;
	}

	public void setTxtCantidadTeorica(JTextField txtCantidadTeorica) {
		this.txtCantidadTeorica = txtCantidadTeorica;
	}

	public JLabel getLblCantidadTeorica() {
		return lblCantidadTeorica;
	}

	public void setLblCantidadTeorica(JLabel lblCantidadTeorica) {
		this.lblCantidadTeorica = lblCantidadTeorica;
	}

	public JLabel getLblCantidadReal() {
		return lblCantidadReal;
	}

	public void setLblCantidadReal(JLabel lblCantidadReal) {
		this.lblCantidadReal = lblCantidadReal;
	}

	public JTextField getTxtCantReal() {
		return txtCantReal;
	}

	public void setTxtCantReal(JTextField txtCantReal) {
		this.txtCantReal = txtCantReal;
	}

	public JLabel getLblIconAlert() {
		return lblIconAlert;
	}

	public void setLblIconAlert(JLabel lblIconAlert) {
		this.lblIconAlert = lblIconAlert;
	}

	public JLabel getLblDescuadre() {
		return lblDescuadre;
	}

	public void setLblDescuadre(JLabel lblDescuadre) {
		this.lblDescuadre = lblDescuadre;
	}

	public JTextField getTxtDescuadreEfectivo() {
		return txtDescuadreEfectivo;
	}

	public void setTxtDescuadreEfectivo(JTextField txtDescuadreEfectivo) {
		this.txtDescuadreEfectivo = txtDescuadreEfectivo;
	}

	public JLabel getLblDescuadre2() {
		return lblDescuadre2;
	}

	public void setLblDescuadre2(JLabel lblDescuadre2) {
		this.lblDescuadre2 = lblDescuadre2;
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

	public JTextField getTxtMultasPerdidasDoc() {
		return txtMultasPerdidasDoc;
	}

	public void setTxtMultasPerdidasDoc(JTextField txtMultasPerdidasDoc) {
		this.txtMultasPerdidasDoc = txtMultasPerdidasDoc;
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public void setPanel6(JPanel panel6) {
		this.panel6 = panel6;
	}

	public JButton getBtnCerrarCaja() {
		return btnCerrarCaja;
	}

	public void setBtnCerrarCaja(JButton btnCerrarCaja) {
		this.btnCerrarCaja = btnCerrarCaja;
	}

	public JButton getBtnDesbloquear() {
		return btnDesbloquear;
	}

	public void setBtnDesbloquear(JButton btnDesbloquear) {
		this.btnDesbloquear = btnDesbloquear;
	}

	public JButton getBtnBloquearCaja() {
		return btnBloquearCaja;
	}

	public void setBtnBloquearCaja(JButton btnBloquearCaja) {
		this.btnBloquearCaja = btnBloquearCaja;
	}

	public JPanel getPanel3() {
		return panel3;
	}

	public void setPanel3(JPanel panel3) {
		this.panel3 = panel3;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JScrollPane getScpDetalle() {
		return scpDetalle;
	}

	public void setScpDetalle(JScrollPane scpDetalle) {
		this.scpDetalle = scpDetalle;
	}

	public JTable getTblTablaDetalle() {
		return tblTablaDetalle;
	}

	public void setTblTablaDetalle(JTable tblTablaDetalle) {
		this.tblTablaDetalle = tblTablaDetalle;
	}
	
	
	
	
}
