package com.spirit.pos.gui.panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
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



public abstract class JPPos extends SpiritModelImpl {
	public JPPos() {
		initComponents();
		setName("Pos");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		pnVendedor = new JPanel();
		cmbVendedor = new JComboBox();
		lblVendedor = new JLabel();
		panel12 = new JPanel();
		lblCajero = new JLabel();
		txtCajero = new JTextField();
		lblCaja = new JLabel();
		txtCaja = new JTextField();
		btnAbrir = new JButton();
		panel4 = new JPanel();
		panel9 = new JPanel();
		btnDonacion = new JButton();
		btnCaja = new JButton();
		btnCancelar = new JButton();
		btnTransferirTarjetaAfiliacion = new JButton();
		btnAcreditar = new JButton();
		btnHistorial = new JButton();
		pnCliente = new JPanel();
		txtCedula = new JTextField();
		lblCedula = new JLabel();
		lblNombre = new JLabel();
		txtNombre = new JLabel();
		lblDireccion = new JLabel();
		txtDireccion = new JTextField();
		txtTelefono = new JTextField();
		lblTelefono = new JLabel();
		lblEmail = new JLabel();
		txtEmail = new JTextField();
		lblCorporacion = new JLabel();
		txtCorporacion = new JLabel();
		lblOficina = new JLabel();
		txtOficina = new JLabel();
		panel14 = new JPanel();
		btnBuscar = new JButton();
		btnActualizarCliente = new JButton();
		panel1 = new JPanel();
		pnResumenPagos = new JPanel();
		lblEfectivo = new JLabel();
		txtEfectivo = new JTextField();
		lblGiftcard = new JLabel();
		txtGiftcard = new JTextField();
		lblTarjetaCredito = new JLabel();
		txtTarjetaCredito = new JTextField();
		txtCheque = new JTextField();
		lblDebitoBancario = new JLabel();
		txtDebitoBancario = new JTextField();
		lblCreditoCliente = new JLabel();
		txtCreditoCliente = new JTextField();
		lblCheque = new JLabel();
		lblDebitoBancario2 = new JLabel();
		txtPuntos = new JTextField();
		btnResumenCobrosPagos = new JButton();
		pnTransaccion = new JPanel();
		btnAnticipo = new JButton();
		btnFactura = new JButton();
		btnDevolucion = new JButton();
		btnNotaVenta = new JButton();
		panel10 = new JPanel();
		splitPane1 = new JSplitPane();
		PnCalculadora = new JPanel();
		panelInformacionProducto = new JPanel();
		txtLoteid = new JTextField();
		txtIdProducto = new JTextField();
		txtIdGiftcard = new JTextField();
		txtTipoProducto = new JTextField();
		label1 = new JLabel();
		txtDescripcion = new JTextField();
		txtPVP = new JTextField();
		txtCantidad = new JTextField();
		txtDescuento = new JTextField();
		txtIVA = new JTextField();
		txtImporte = new JTextField();
		txtCodigoProducto = new JTextField();
		pnEsteItem = new JPanel();
		lblEsteItem = new JLabel();
		txtEsteItem = new JTextField();
		pnCodigoBarras = new JPanel();
		lblCodigoBarras = new JLabel();
		txtCodigoBarras = new JTextField();
		pnFormasPago = new JPanel();
		btnEfectivo = new JButton();
		btnTarjetaCredito = new JButton();
		btnCredito = new JButton();
		btnGiftcard = new JButton();
		btnDebito = new JButton();
		btnCheque = new JButton();
		btnAfiliacionReferido = new JButton();
		btnBorrar = new JButton();
		lblTipoDocumentoSeleccionado = new JLabel();
		spVentaDetalle = new JPanel();
		spTotales = new JPanel();
		txtSubtotalFinal = new JLabel();
		lblSubtotalFinal = new JLabel();
		txtDescuentoFinal = new JLabel();
		lblDescuentoFinal = new JLabel();
		lblImpuestos = new JLabel();
		txtImpuestosFinal = new JLabel();
		txtvacio = new JTextField();
		lblTotalFinal = new JLabel();
		lblDonacion = new JLabel();
		txtTotalFinal = new JTextField();
		txtDonacion = new JLabel();
		label13 = new JLabel();
		txtDeuda = new JLabel();
		lblVuelto = new JLabel();
		txtVuelto = new JLabel();
		spDetalles = new JPanel();
		scrollPane1 = new JScrollPane();
		tblVentaDetalle = new JTable();
		panel11 = new JPanel();
		btnEliminar = new JButton();
		pnNumeroDocumento = new JPanel();
		cmbTipoDocDevolucion = new JComboBox();
		lblPreImpreso = new JLabel();
		txtNumDocumento = new JTextField();
		lblBuscarDocumento = new JLabel();
		btnBuscarProducto = new JButton();
		panel5 = new JPanel();
		lblCantidadDetalle = new JLabel();
		txtCantidadDetalle = new JTextField();
		lblCantidadDevuelta = new JLabel();
		txtCantidadDevuelta = new JTextField();
		label2 = new JLabel();
		lblItem = new JLabel();
		txtPorcentajeDescuento = new JTextField();
		lblPorcentajeDescuento = new JLabel();
		txtItem = new JTextField();
		lblVisible = new JLabel();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			new ColumnSpec[] {
				new ColumnSpec(Sizes.dluX(160)),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				new ColumnSpec(Sizes.DLUX5)
			},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(Sizes.dluY(12))
			}));

		//======== pnVendedor ========
		{
			pnVendedor.setLayout(new FormLayout(
				new ColumnSpec[] {
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(110), FormSpec.DEFAULT_GROW)
				},
				RowSpec.decodeSpecs("default")));
			
			//---- cmbVendedor ----
			cmbVendedor.setFont(new Font("Tahoma", Font.PLAIN, 10));
			pnVendedor.add(cmbVendedor, cc.xy(3, 1));
			
			//---- lblVendedor ----
			lblVendedor.setText("Vendedor");
			lblVendedor.setFont(new Font("Tahoma", Font.BOLD, 12));
			pnVendedor.add(lblVendedor, cc.xy(1, 1));
		}
		add(pnVendedor, cc.xy(1, 1));

		//======== panel12 ========
		{
			panel12.setLayout(new FormLayout(
				new ColumnSpec[] {
					new ColumnSpec(Sizes.dluX(25)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(25)),
					FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
					new ColumnSpec(Sizes.dluX(95))
				},
				RowSpec.decodeSpecs("default")));
			
			//---- lblCajero ----
			lblCajero.setText("Cajero:");
			lblCajero.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel12.add(lblCajero, cc.xywh(1, 1, 2, 1));
			
			//---- txtCajero ----
			txtCajero.setEditable(false);
			txtCajero.setFont(new Font("Tahoma", Font.BOLD, 11));
			txtCajero.setBackground(new Color(236, 233, 216));
			txtCajero.setHorizontalAlignment(JTextField.LEADING);
			txtCajero.setForeground(Color.black);
			panel12.add(txtCajero, cc.xy(3, 1));
			
			//---- lblCaja ----
			lblCaja.setText("Caja: ");
			lblCaja.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel12.add(lblCaja, cc.xy(5, 1));
			
			//---- txtCaja ----
			txtCaja.setEditable(false);
			txtCaja.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel12.add(txtCaja, cc.xy(7, 1));
		}
		add(panel12, cc.xy(3, 1));

		//---- btnAbrir ----
		btnAbrir.setText("F30 - ABRIR ");
		btnAbrir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAbrir.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\cajon.png"));
		btnAbrir.setToolTipText("ABRIR CAJON");
		add(btnAbrir, cc.xywh(5, 1, 1, 1, CellConstraints.CENTER, CellConstraints.DEFAULT));

		//======== panel4 ========
		{
			panel4.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("160dlu:grow"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//======== panel9 ========
			{
				panel9.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 51, 0), null, new Color(153, 51, 0), null), "Acciones", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
				panel9.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(75)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(75))
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- btnDonacion ----
				btnDonacion.setText("F10 - DON.");
				btnDonacion.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnDonacion.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\Handshake.png"));
				btnDonacion.setHorizontalAlignment(SwingConstants.LEFT);
				btnDonacion.setToolTipText("DONACION");
				panel9.add(btnDonacion, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnCaja ----
				btnCaja.setText("F12 - CAJA");
				btnCaja.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\locked.png"));
				btnCaja.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnCaja.setHorizontalAlignment(SwingConstants.LEFT);
				btnCaja.setToolTipText("CAJA");
				panel9.add(btnCaja, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnCancelar ----
				btnCancelar.setText("F13 - C/C");
				btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnCancelar.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\cancelar.png"));
				btnCancelar.setHorizontalAlignment(SwingConstants.LEFT);
				btnCancelar.setToolTipText("CANCELAR VENTA / COTIZACION");
				panel9.add(btnCancelar, cc.xywh(1, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnTarjetaAfiliacion ----
				btnTransferirTarjetaAfiliacion.setText("F11 - TRANSF. T/A");
				btnTransferirTarjetaAfiliacion.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnTransferirTarjetaAfiliacion.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\tarjetaDebito.png"));
				btnTransferirTarjetaAfiliacion.setHorizontalAlignment(SwingConstants.LEFT);
				btnTransferirTarjetaAfiliacion.setToolTipText("TRANSFERIR TARJETA DE AFILIACION");
				panel9.add(btnTransferirTarjetaAfiliacion, cc.xywh(1, 3, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
				
				//---- btnAcreditar ----
				btnAcreditar.setText("ACR.");
				btnAcreditar.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnAcreditar.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\creditoCliente.png"));
				btnAcreditar.setHorizontalAlignment(SwingConstants.LEFT);
				btnAcreditar.setToolTipText("ACREDITAR CLIENTE");
				panel9.add(btnAcreditar, cc.xywh(3, 5, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnHistorial ----
				btnHistorial.setText("F9 - HIST.");
				btnHistorial.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnHistorial.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\FindDocument.png"));
				btnHistorial.setHorizontalAlignment(SwingConstants.LEFT);
				btnHistorial.setToolTipText("HISTORIAL DE VENTAS");
				panel9.add(btnHistorial, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			}
			panel4.add(panel9, cc.xy(1, 3));
			
			//======== pnCliente ========
			{
				pnCliente.setBorder(new TitledBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(153, 51, 0), null, new Color(153, 51, 0), null), "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
				pnCliente.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(60)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(40), FormSpec.DEFAULT_GROW)
					},
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- txtCedula ----
				txtCedula.setFont(new Font("Tahoma", Font.BOLD, 10));
				pnCliente.add(txtCedula, cc.xywh(3, 1, 3, 1));
				
				//---- lblCedula ----
				lblCedula.setText("C\u00e9dula:");
				pnCliente.add(lblCedula, cc.xy(1, 1));
				
				//---- lblNombre ----
				lblNombre.setText("Nombre:");
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
				pnCliente.add(lblNombre, cc.xy(1, 2));
				
				//---- txtNombre ----
				txtNombre.setText("text");
				txtNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
				pnCliente.add(txtNombre, cc.xywh(3, 2, 3, 1));
				
				//---- lblDireccion ----
				lblDireccion.setText("Direcci\u00f3n:");
				pnCliente.add(lblDireccion, cc.xy(1, 3));
				
				//---- txtDireccion ----
				txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 10));
				pnCliente.add(txtDireccion, cc.xywh(3, 3, 3, 1));
				
				//---- txtTelefono ----
				txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 10));
				pnCliente.add(txtTelefono, cc.xy(3, 4));
				
				//---- lblTelefono ----
				lblTelefono.setText("Tel\u00e9fono:");
				pnCliente.add(lblTelefono, cc.xy(1, 4));
				
				//---- lblEmail ----
				lblEmail.setText("Email:");
				pnCliente.add(lblEmail, cc.xy(1, 5));
				
				//---- txtEmail ----
				txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
				pnCliente.add(txtEmail, cc.xywh(3, 5, 3, 1));
				
				//---- lblCorporacion ----
				lblCorporacion.setText("Corporaci\u00f3n:");
				lblCorporacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
				pnCliente.add(lblCorporacion, cc.xy(1, 6));
				
				//---- txtCorporacion ----
				txtCorporacion.setText("text");
				pnCliente.add(txtCorporacion, cc.xywh(3, 6, 3, 1));
				
				//---- lblOficina ----
				lblOficina.setText("Oficina:");
				lblOficina.setFont(new Font("Tahoma", Font.PLAIN, 11));
				pnCliente.add(lblOficina, cc.xy(1, 7));
				
				//---- txtOficina ----
				txtOficina.setText("text");
				pnCliente.add(txtOficina, cc.xywh(3, 7, 3, 1));
				
				//======== panel14 ========
				{
					panel14.setLayout(new FormLayout(
						"70dlu, default:grow",
						"default"));
					
					//---- btnBuscar ----
					btnBuscar.setText("Buscar");
					btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));
					btnBuscar.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\ViewUser.png"));
					panel14.add(btnBuscar, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
					
					//---- btnActualizarCliente ----
					btnActualizarCliente.setText("Actualizar");
					btnActualizarCliente.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\contact.png"));
					btnActualizarCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel14.add(btnActualizarCliente, cc.xywh(2, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
				}
				pnCliente.add(panel14, cc.xywh(1, 9, 5, 1));
			}
			panel4.add(pnCliente, cc.xy(1, 1));
			
			//======== panel1 ========
			{
				panel1.setBorder(new LineBorder(new Color(0, 0, 102)));
				panel1.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//======== pnResumenPagos ========
				{
					pnResumenPagos.setFont(new Font("Tahoma", Font.BOLD, 10));
					pnResumenPagos.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(38)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(36)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(33)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(36))
						},
						new RowSpec[] {
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC
						}));
					
					//---- lblEfectivo ----
					lblEfectivo.setText("Efectivo:");
					lblEfectivo.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblEfectivo, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtEfectivo ----
					txtEfectivo.setText("0.00");
					txtEfectivo.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtEfectivo.setEditable(false);
					txtEfectivo.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtEfectivo, cc.xy(3, 1));
					
					//---- lblGiftcard ----
					lblGiftcard.setText("Gift C:");
					lblGiftcard.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblGiftcard.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblGiftcard, cc.xy(5, 1));
					
					//---- txtGiftcard ----
					txtGiftcard.setText("0.00");
					txtGiftcard.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtGiftcard.setEditable(false);
					txtGiftcard.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtGiftcard, cc.xy(7, 1));
					
					//---- lblTarjetaCredito ----
					lblTarjetaCredito.setText("  T/ Cr\u00e9dito:");
					lblTarjetaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTarjetaCredito.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblTarjetaCredito, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					
					//---- txtTarjetaCredito ----
					txtTarjetaCredito.setText("0.00");
					txtTarjetaCredito.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtTarjetaCredito.setEditable(false);
					txtTarjetaCredito.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtTarjetaCredito, cc.xy(3, 3));
					
					//---- txtCheque ----
					txtCheque.setText("0.00");
					txtCheque.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtCheque.setEditable(false);
					txtCheque.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtCheque, cc.xy(7, 3));
					
					//---- lblDebitoBancario ----
					lblDebitoBancario.setText("D\u00e9bito B:");
					lblDebitoBancario.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblDebitoBancario.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblDebitoBancario, cc.xy(1, 5));
					
					//---- txtDebitoBancario ----
					txtDebitoBancario.setText("0.00");
					txtDebitoBancario.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtDebitoBancario.setEditable(false);
					txtDebitoBancario.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtDebitoBancario, cc.xy(3, 5));
					
					//---- lblCreditoCliente ----
					lblCreditoCliente.setText("Cr\u00e9d/C:");
					lblCreditoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblCreditoCliente.setToolTipText("Cr\u00e9dito Cliente");
					lblCreditoCliente.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblCreditoCliente, cc.xy(5, 5));
					
					//---- txtCreditoCliente ----
					txtCreditoCliente.setText("0.00");
					txtCreditoCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtCreditoCliente.setEditable(false);
					txtCreditoCliente.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtCreditoCliente, cc.xy(7, 5));
					
					//---- lblCheque ----
					lblCheque.setText("Cheque:");
					lblCheque.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblCheque.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblCheque, cc.xy(5, 3));
					
					//---- lblDebitoBancario2 ----
					lblDebitoBancario2.setText("Puntos:");
					lblDebitoBancario2.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblDebitoBancario2.setHorizontalAlignment(SwingConstants.RIGHT);
					pnResumenPagos.add(lblDebitoBancario2, cc.xy(1, 7));
					
					//---- txtPuntos ----
					txtPuntos.setText("0.00");
					txtPuntos.setFont(new Font("Tahoma", Font.BOLD, 11));
					txtPuntos.setEditable(false);
					txtPuntos.setHorizontalAlignment(JTextField.RIGHT);
					pnResumenPagos.add(txtPuntos, cc.xy(3, 7));
				}
				panel1.add(pnResumenPagos, cc.xy(1, 3));
				
				//---- btnResumenCobrosPagos ----
				btnResumenCobrosPagos.setText("RESUMEN COBROS/PAGOS");
				btnResumenCobrosPagos.setForeground(new Color(0, 0, 102));
				btnResumenCobrosPagos.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel1.add(btnResumenCobrosPagos, cc.xy(1, 1));
			}
			panel4.add(panel1, cc.xy(1, 7));
			
			//======== pnTransaccion ========
			{
				pnTransaccion.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 51, 0), null, new Color(153, 51, 0), null), "Tipo de Transacci\u00f3n", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
				pnTransaccion.setForeground(Color.black);
				pnTransaccion.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(75)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(Sizes.dluX(75))
					},
					new RowSpec[] {
						new RowSpec(Sizes.dluY(22)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(22))
					}));
				
				//---- btnAnticipo ----
				btnAnticipo.setText("F16 - ANT");
				btnAnticipo.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnAnticipo.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\downloadfromWeb.png"));
				btnAnticipo.setHorizontalAlignment(SwingConstants.CENTER);
				btnAnticipo.setToolTipText("ANTICIPO");
				pnTransaccion.add(btnAnticipo, cc.xywh(3, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnFactura ----
				btnFactura.setText("F15 - FAC");
				btnFactura.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\invoice.png"));
				btnFactura.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnFactura.setHorizontalAlignment(SwingConstants.CENTER);
				btnFactura.setToolTipText("FACTURA");
				pnTransaccion.add(btnFactura, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnDevolucion ----
				btnDevolucion.setText("F18 - DEV");
				btnDevolucion.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnDevolucion.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\devolucion.png"));
				btnDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
				btnDevolucion.setToolTipText("DEVOLUCION");
				pnTransaccion.add(btnDevolucion, cc.xywh(3, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
				
				//---- btnNotaVenta ----
				btnNotaVenta.setText("F17 - N/V");
				btnNotaVenta.setFont(new Font("Tahoma", Font.BOLD, 12));
				btnNotaVenta.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\notaventa.png"));
				btnNotaVenta.setHorizontalAlignment(SwingConstants.CENTER);
				btnNotaVenta.setToolTipText("NOTA DE VENTA");
				pnTransaccion.add(btnNotaVenta, cc.xywh(1, 3, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			}
			panel4.add(pnTransaccion, cc.xy(1, 5));
		}
		add(panel4, cc.xywh(1, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));

		//======== panel10 ========
		{
			panel10.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("105dlu"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
				}));
			
			//======== splitPane1 ========
			{
				splitPane1.setBackground(new Color(204, 204, 204));
				splitPane1.setContinuousLayout(false);
				
				//======== PnCalculadora ========
				{
					PnCalculadora.setLayout(new BorderLayout());
				}
				splitPane1.setLeftComponent(PnCalculadora);
				
				//======== panelInformacionProducto ========
				{
					panelInformacionProducto.setBorder(new TitledBorder(null, "Informaci\u00f3n producto", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 10)));
					panelInformacionProducto.setBackground(new Color(236, 233, 216));
					panelInformacionProducto.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(40), FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						new RowSpec[] {
							new RowSpec(Sizes.DLUY3),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
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
					panelInformacionProducto.add(txtLoteid, cc.xy(1, 1));
					
					//---- txtIdProducto ----
					txtIdProducto.setEditable(false);
					panelInformacionProducto.add(txtIdProducto, cc.xy(3, 1));
					
					//---- txtIdGiftcard ----
					txtIdGiftcard.setEditable(false);
					panelInformacionProducto.add(txtIdGiftcard, cc.xy(3, 5));
					
					//---- txtTipoProducto ----
					txtTipoProducto.setEditable(false);
					panelInformacionProducto.add(txtTipoProducto, cc.xy(5, 1));
					
					//---- label1 ----
					label1.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\productos\\camiseta_1.png"));
					panelInformacionProducto.add(label1, cc.xywh(3, 3, 1, 11, CellConstraints.CENTER, CellConstraints.CENTER));
					
					//---- txtDescripcion ----
					txtDescripcion.setEditable(false);
					txtDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 11));
					panelInformacionProducto.add(txtDescripcion, cc.xy(5, 5));
					
					//---- txtPVP ----
					txtPVP.setEditable(false);
					txtPVP.setFont(new Font("Tahoma", Font.PLAIN, 11));
					panelInformacionProducto.add(txtPVP, cc.xy(5, 7));
					
					//---- txtCantidad ----
					txtCantidad.setFont(new Font("Tahoma", Font.BOLD, 15));
					txtCantidad.setEditable(false);
					panelInformacionProducto.add(txtCantidad, cc.xy(5, 9));
					
					//---- txtDescuento ----
					txtDescuento.setFont(new Font("Tahoma", Font.PLAIN, 11));
					txtDescuento.setEditable(false);
					panelInformacionProducto.add(txtDescuento, cc.xy(5, 11));
					
					//---- txtIVA ----
					txtIVA.setEditable(false);
					txtIVA.setFont(new Font("Tahoma", Font.PLAIN, 11));
					panelInformacionProducto.add(txtIVA, cc.xy(5, 13));
					
					//---- txtImporte ----
					txtImporte.setEditable(false);
					txtImporte.setFont(new Font("Tahoma", Font.PLAIN, 11));
					panelInformacionProducto.add(txtImporte, cc.xy(5, 15));
					
					//---- txtCodigoProducto ----
					txtCodigoProducto.setFont(new Font("Tahoma", Font.PLAIN, 11));
					txtCodigoProducto.setEditable(false);
					panelInformacionProducto.add(txtCodigoProducto, cc.xy(5, 3));
				}
				splitPane1.setRightComponent(panelInformacionProducto);
			}
			panel10.add(splitPane1, cc.xywh(1, 3, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
			
			//======== pnEsteItem ========
			{
				pnEsteItem.setLayout(new FormLayout(
					"default:grow",
					"default, default"));
				
				//---- lblEsteItem ----
				lblEsteItem.setText("Este item:");
				lblEsteItem.setFont(new Font("Tahoma", Font.PLAIN, 26));
				pnEsteItem.add(lblEsteItem, cc.xywh(1, 1, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
				
				//---- txtEsteItem ----
				txtEsteItem.setEditable(false);
				txtEsteItem.setBackground(new Color(51, 51, 51));
				txtEsteItem.setForeground(Color.yellow);
				txtEsteItem.setText(" 250");
				txtEsteItem.setFont(new Font("Tahoma", Font.BOLD, 28));
				txtEsteItem.setHorizontalAlignment(JTextField.RIGHT);
				pnEsteItem.add(txtEsteItem, cc.xy(1, 2));
			}
			panel10.add(pnEsteItem, cc.xywh(1, 5, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
			
			//======== pnCodigoBarras ========
			{
				pnCodigoBarras.setLayout(new FormLayout(
					new ColumnSpec[] {
						new ColumnSpec(Sizes.dluX(50)),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
					},
					RowSpec.decodeSpecs("5dlu, 13dlu")));
				
				//---- lblCodigoBarras ----
				lblCodigoBarras.setIcon(new ImageIcon("H:\\Documents and Settings\\RRHH\\Mis documentos\\Mis im\u00e1genes\\Dibujo.GIF"));
				pnCodigoBarras.add(lblCodigoBarras, cc.xy(1, 2));
				
				//---- txtCodigoBarras ----
				txtCodigoBarras.setEditable(true);
				txtCodigoBarras.setToolTipText("Ingrese el c\u00f3digo o use el Lector de barras");
				txtCodigoBarras.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 10));
				pnCodigoBarras.add(txtCodigoBarras, cc.xywh(3, 2, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
			}
			panel10.add(pnCodigoBarras, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.BOTTOM));
			
			//======== pnFormasPago ========
			{
				pnFormasPago.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 51, 0), null, new Color(153, 51, 0), null), "Formas de pago", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
				pnFormasPago.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("90dlu:grow"),
					new RowSpec[] {
						new RowSpec(Sizes.dluY(17)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(17)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(17)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(17)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(17)),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(17)),
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(Sizes.dluY(17))
					}));
				
				//---- btnEfectivo ----
				btnEfectivo.setText("F6  - EFE.");
				btnEfectivo.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\money.png"));
				btnEfectivo.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnEfectivo.setHorizontalAlignment(SwingConstants.LEFT);
				btnEfectivo.setToolTipText("EFECTIVO");
				pnFormasPago.add(btnEfectivo, cc.xywh(1, 1, 1, 1, CellConstraints.FILL, CellConstraints.DEFAULT));
				
				//---- btnTarjetaCredito ----
				btnTarjetaCredito.setText("F8  - T/C");
				btnTarjetaCredito.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnTarjetaCredito.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\CreditCards.png"));
				btnTarjetaCredito.setHorizontalAlignment(SwingConstants.LEFT);
				btnTarjetaCredito.setToolTipText("TARJETA CREDITO");
				pnFormasPago.add(btnTarjetaCredito, cc.xy(1, 3));
				
				//---- btnCredito ----
				btnCredito.setText("F22 - CRED.");
				btnCredito.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnCredito.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\tarjetaDebito.png"));
				btnCredito.setHorizontalAlignment(SwingConstants.LEFT);
				btnCredito.setToolTipText("CREDITO CLIENTE");
				pnFormasPago.add(btnCredito, cc.xy(1, 7));
				
				//---- btnGiftcard ----
				btnGiftcard.setText("F7  - G/C");
				btnGiftcard.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnGiftcard.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\smiley.png"));
				btnGiftcard.setHorizontalAlignment(SwingConstants.LEFT);
				btnGiftcard.setToolTipText("GIFT CARD");
				pnFormasPago.add(btnGiftcard, cc.xy(1, 5));
				
				//---- btnDebito ----
				btnDebito.setText("F21 - DEB.");
				btnDebito.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnDebito.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\tarjetaDebito.png"));
				btnDebito.setHorizontalAlignment(SwingConstants.LEFT);
				btnDebito.setToolTipText("TARJ. DEBITO");
				pnFormasPago.add(btnDebito, cc.xy(1, 9));
				
				//---- btnCheque ----
				btnCheque.setText("F20 - CH");
				btnCheque.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnCheque.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\cheque.jpeg"));
				btnCheque.setHorizontalAlignment(SwingConstants.LEFT);
				btnCheque.setToolTipText("CHEQUE");
				pnFormasPago.add(btnCheque, cc.xywh(1, 13, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//---- btnAfiliacionReferido ----
				btnAfiliacionReferido.setText("AFIL/REFERIDO");
				btnAfiliacionReferido.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnAfiliacionReferido.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\cheque.jpeg"));
				btnAfiliacionReferido.setHorizontalAlignment(SwingConstants.LEFT);
				btnAfiliacionReferido.setToolTipText("CHEQUE");
				pnFormasPago.add(btnAfiliacionReferido, cc.xy(1, 11));
				
				//---- btnBorrar ----
				btnBorrar.setText("F21 - BORRAR");
				btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnBorrar.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\borrarPagos.png"));
				btnBorrar.setHorizontalAlignment(SwingConstants.LEADING);
				btnBorrar.setVerticalAlignment(SwingConstants.CENTER);
				pnFormasPago.add(btnBorrar, cc.xy(1, 15));
			}
			panel10.add(pnFormasPago, cc.xywh(1, 7, 1, 1, CellConstraints.FILL, CellConstraints.TOP));
		}
		add(panel10, cc.xywh(5, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));

		//---- lblTipoDocumentoSeleccionado ----
		lblTipoDocumentoSeleccionado.setText("NO BORRAR");
		add(lblTipoDocumentoSeleccionado, cc.xy(1, 5));

		//======== spVentaDetalle ========
		{
			spVentaDetalle.setLayout(new FormLayout(
				ColumnSpec.decodeSpecs("default:grow"),
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					FormFactory.LINE_GAP_ROWSPEC,
					FormFactory.DEFAULT_ROWSPEC
				}));
			
			//======== spTotales ========
			{
				spTotales.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 51, 0), Color.white, new Color(153, 51, 0), Color.white), "TOTALES", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 14)));
				spTotales.setBackground(new Color(232, 227, 197));
				spTotales.setLayout(new FormLayout(
					new ColumnSpec[] {
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.DEFAULT_COLSPEC,
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
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//---- txtSubtotalFinal ----
				txtSubtotalFinal.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtSubtotalFinal.setText("0.00");
				spTotales.add(txtSubtotalFinal, cc.xywh(4, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblSubtotalFinal ----
				lblSubtotalFinal.setText("Subtotal:");
				lblSubtotalFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
				spTotales.add(lblSubtotalFinal, cc.xywh(1, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtDescuentoFinal ----
				txtDescuentoFinal.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtDescuentoFinal.setText("0.00");
				spTotales.add(txtDescuentoFinal, cc.xywh(4, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblDescuentoFinal ----
				lblDescuentoFinal.setText("Descuento:");
				lblDescuentoFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
				spTotales.add(lblDescuentoFinal, cc.xywh(1, 3, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblImpuestos ----
				lblImpuestos.setText("Impuestos:");
				lblImpuestos.setFont(new Font("Tahoma", Font.BOLD, 14));
				spTotales.add(lblImpuestos, cc.xywh(1, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtImpuestosFinal ----
				txtImpuestosFinal.setFont(new Font("Tahoma", Font.BOLD, 16));
				txtImpuestosFinal.setText("0.00");
				spTotales.add(txtImpuestosFinal, cc.xywh(4, 5, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtvacio ----
				txtvacio.setBackground(new Color(51, 51, 51));
				spTotales.add(txtvacio, cc.xywh(3, 7, 1, 1, CellConstraints.DEFAULT, CellConstraints.FILL));
				
				//---- lblTotalFinal ----
				lblTotalFinal.setText("TOTAL:");
				lblTotalFinal.setFont(new Font("Tahoma", Font.BOLD, 45));
				spTotales.add(lblTotalFinal, cc.xywh(1, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblDonacion ----
				lblDonacion.setText("Donaci\u00f3n:");
				lblDonacion.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblDonacion.setForeground(Color.black);
				spTotales.add(lblDonacion, cc.xywh(1, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtTotalFinal ----
				txtTotalFinal.setBackground(new Color(51, 51, 51));
				txtTotalFinal.setForeground(Color.yellow);
				txtTotalFinal.setEditable(false);
				txtTotalFinal.setFont(new Font("Tahoma", Font.BOLD, 45));
				txtTotalFinal.setText("26660.00");
				txtTotalFinal.setHorizontalAlignment(JTextField.RIGHT);
				spTotales.add(txtTotalFinal, cc.xywh(4, 7, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtDonacion ----
				txtDonacion.setText("0.00");
				txtDonacion.setFont(new Font("Tahoma", Font.BOLD, 14));
				spTotales.add(txtDonacion, cc.xywh(4, 9, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- label13 ----
				label13.setText("DEUDA:");
				label13.setFont(new Font("Arial Black", Font.BOLD, 20));
				label13.setForeground(Color.black);
				spTotales.add(label13, cc.xywh(1, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.FILL));
				
				//---- txtDeuda ----
				txtDeuda.setText("0.00");
				txtDeuda.setForeground(Color.black);
				txtDeuda.setFont(new Font("Tahoma", Font.BOLD, 20));
				spTotales.add(txtDeuda, cc.xywh(4, 11, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- lblVuelto ----
				lblVuelto.setText("VUELTO");
				lblVuelto.setForeground(Color.red);
				lblVuelto.setFont(new Font("Arial Black", Font.BOLD, 20));
				spTotales.add(lblVuelto, cc.xywh(1, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
				
				//---- txtVuelto ----
				txtVuelto.setText("0.00");
				txtVuelto.setForeground(Color.red);
				txtVuelto.setFont(new Font("Tahoma", Font.BOLD, 24));
				spTotales.add(txtVuelto, cc.xywh(4, 13, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
			}
			spVentaDetalle.add(spTotales, cc.xy(1, 3));
			
			//======== spDetalles ========
			{
				spDetalles.setBackground(new Color(236, 233, 216));
				spDetalles.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12)));
				spDetalles.setLayout(new FormLayout(
					ColumnSpec.decodeSpecs("default:grow"),
					new RowSpec[] {
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC
					}));
				
				//======== scrollPane1 ========
				{
					scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					
					//---- tblVentaDetalle ----
					tblVentaDetalle.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, "", null, null, null, null, null, null},
							{null, null, null, null, null, "", null, null, null},
							{null, null, null, null, null, null, null, null, null},
						},
						new String[] {
							"CODIGO", "DESCRIPCION", "CAN", "CANT DEV.", "P.U", "DC.", "IMP.", "VALOR", " "
						}
					) {
						Class[] columnTypes = new Class[] {
							Object.class, Object.class, Object.class, Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							true, false, false, true, false, false, false, false, true
						};
						public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					tblVentaDetalle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 10));
					scrollPane1.setViewportView(tblVentaDetalle);
				}
				spDetalles.add(scrollPane1, cc.xywh(1, 3, 1, 3));
				
				//======== panel11 ========
				{
					panel11.setBackground(new Color(236, 233, 216));
					panel11.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC
						},
						RowSpec.decodeSpecs("default")));
					
					//---- btnEliminar ----
					btnEliminar.setText("F3 - Eliminar");
					btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
					panel11.add(btnEliminar, cc.xy(1, 1));
					
					//======== pnNumeroDocumento ========
					{
						pnNumeroDocumento.setBorder(new LineBorder(Color.red));
						pnNumeroDocumento.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(145), FormSpec.DEFAULT_GROW),
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC
							},
							RowSpec.decodeSpecs("default")));
						
						//---- cmbTipoDocDevolucion ----
						cmbTipoDocDevolucion.setModel(new DefaultComboBoxModel(new String[] {
							"FAC.",
							"N/V."
						}));
						cmbTipoDocDevolucion.setEditable(false);
						pnNumeroDocumento.add(cmbTipoDocDevolucion, cc.xy(1, 1));
						
						//---- lblPreImpreso ----
						lblPreImpreso.setText("PreImpreso:");
						lblPreImpreso.setFont(new Font("MS Sans Serif", Font.BOLD, 14));
						pnNumeroDocumento.add(lblPreImpreso, cc.xy(3, 1));
						
						//---- txtNumDocumento ----
						txtNumDocumento.setBackground(new Color(102, 102, 102));
						txtNumDocumento.setForeground(Color.yellow);
						txtNumDocumento.setText("003-003-1234567");
						txtNumDocumento.setFont(new Font("Tahoma", Font.BOLD, 22));
						pnNumeroDocumento.add(txtNumDocumento, cc.xy(5, 1));
						
						//---- lblBuscarDocumento ----
						lblBuscarDocumento.setIcon(new ImageIcon("C:\\workspace\\base\\src\\main\\resources\\images\\pos\\searchInvoice.png"));
						pnNumeroDocumento.add(lblBuscarDocumento, cc.xy(7, 1));
					}
					panel11.add(pnNumeroDocumento, cc.xy(3, 1));
					
					//---- btnBuscarProducto ----
					btnBuscarProducto.setText("B");
					panel11.add(btnBuscarProducto, cc.xy(5, 1));
				}
				spDetalles.add(panel11, cc.xy(1, 1));
				
				//======== panel5 ========
				{
					panel5.setBorder(null);
					panel5.setLayout(new FormLayout(
						new ColumnSpec[] {
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(33)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(33)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(33)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec("max(default;50dlu):grow")
						},
						RowSpec.decodeSpecs("default, 20dlu")));
					
					//---- lblCantidadDetalle ----
					lblCantidadDetalle.setText("  Cant:");
					lblCantidadDetalle.setFont(new Font("Tahoma", Font.PLAIN, 20));
					panel5.add(lblCantidadDetalle, cc.xy(1, 1));
					
					//---- txtCantidadDetalle ----
					txtCantidadDetalle.setFont(new Font("Tahoma", Font.BOLD, 20));
					panel5.add(txtCantidadDetalle, cc.xy(3, 1));
					
					//---- lblCantidadDevuelta ----
					lblCantidadDevuelta.setText("  Cant DEV:");
					lblCantidadDevuelta.setFont(new Font("Tahoma", Font.PLAIN, 20));
					panel5.add(lblCantidadDevuelta, cc.xy(5, 1));
					
					//---- txtCantidadDevuelta ----
					txtCantidadDevuelta.setFont(new Font("Tahoma", Font.BOLD, 20));
					panel5.add(txtCantidadDevuelta, cc.xy(7, 1));
					
					//---- label2 ----
					label2.setText("%");
					label2.setFont(new Font("Tahoma", Font.BOLD, 28));
					panel5.add(label2, cc.xywh(9, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblItem ----
					lblItem.setText("   Item:");
					lblItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
					panel5.add(lblItem, cc.xy(1, 2));
					
					//---- txtPorcentajeDescuento ----
					txtPorcentajeDescuento.setFont(new Font("MS Sans Serif", Font.PLAIN, 16));
					txtPorcentajeDescuento.setEditable(true);
					panel5.add(txtPorcentajeDescuento, cc.xywh(13, 1, 1, 1, CellConstraints.FILL, CellConstraints.FILL));
					
					//---- lblPorcentajeDescuento ----
					lblPorcentajeDescuento.setText("Dscto GLOBAL:");
					lblPorcentajeDescuento.setFont(new Font("Tahoma", Font.PLAIN, 20));
					panel5.add(lblPorcentajeDescuento, cc.xy(11, 1));
					
					//---- txtItem ----
					txtItem.setFont(new Font("Tahoma", Font.BOLD, 16));
					txtItem.setEditable(false);
					panel5.add(txtItem, cc.xywh(3, 2, 13, 1));
				}
				spDetalles.add(panel5, cc.xywh(1, 7, 1, 1, CellConstraints.FILL, CellConstraints.CENTER));
			}
			spVentaDetalle.add(spDetalles, cc.xywh(1, 1, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));
		}
		add(spVentaDetalle, cc.xywh(3, 3, 1, 1, CellConstraints.DEFAULT, CellConstraints.TOP));

		//---- lblVisible ----
		lblVisible.setText("no borrar1");
		add(lblVisible, cc.xy(3, 5));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel pnVendedor;
	private JComboBox cmbVendedor;
	private JLabel lblVendedor;
	private JPanel panel12;
	private JLabel lblCajero;
	private JTextField txtCajero;
	private JLabel lblCaja;
	private JTextField txtCaja;
	private JButton btnAbrir;
	private JPanel panel4;
	private JPanel panel9;
	private JButton btnDonacion;
	private JButton btnCaja;
	private JButton btnCancelar;
	private JButton btnTransferirTarjetaAfiliacion;
	private JButton btnAcreditar;
	private JButton btnHistorial;
	private JPanel pnCliente;
	private JTextField txtCedula;
	private JLabel lblCedula;
	private JLabel lblNombre;
	private JLabel txtNombre;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JLabel lblTelefono;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblCorporacion;
	private JLabel txtCorporacion;
	private JLabel lblOficina;
	private JLabel txtOficina;
	private JPanel panel14;
	private JButton btnBuscar;
	private JButton btnActualizarCliente;
	private JPanel panel1;
	private JPanel pnResumenPagos;
	private JLabel lblEfectivo;
	private JTextField txtEfectivo;
	private JLabel lblGiftcard;
	private JTextField txtGiftcard;
	private JLabel lblTarjetaCredito;
	private JTextField txtTarjetaCredito;
	private JTextField txtCheque;
	private JLabel lblDebitoBancario;
	private JTextField txtDebitoBancario;
	private JLabel lblCreditoCliente;
	private JTextField txtCreditoCliente;
	private JLabel lblCheque;
	private JLabel lblDebitoBancario2;
	private JTextField txtPuntos;
	private JButton btnResumenCobrosPagos;
	private JPanel pnTransaccion;
	private JButton btnAnticipo;
	private JButton btnFactura;
	private JButton btnDevolucion;
	private JButton btnNotaVenta;
	private JPanel panel10;
	private JSplitPane splitPane1;
	private JPanel PnCalculadora;
	private JPanel panelInformacionProducto;
	private JTextField txtLoteid;
	private JTextField txtIdProducto;
	private JTextField txtIdGiftcard;
	private JTextField txtTipoProducto;
	private JLabel label1;
	private JTextField txtDescripcion;
	private JTextField txtPVP;
	private JTextField txtCantidad;
	private JTextField txtDescuento;
	private JTextField txtIVA;
	private JTextField txtImporte;
	private JTextField txtCodigoProducto;
	private JPanel pnEsteItem;
	private JLabel lblEsteItem;
	private JTextField txtEsteItem;
	private JPanel pnCodigoBarras;
	private JLabel lblCodigoBarras;
	private JTextField txtCodigoBarras;
	private JPanel pnFormasPago;
	private JButton btnEfectivo;
	private JButton btnTarjetaCredito;
	private JButton btnCredito;
	private JButton btnGiftcard;
	private JButton btnDebito;
	private JButton btnCheque;
	private JButton btnAfiliacionReferido;
	private JButton btnBorrar;
	private JLabel lblTipoDocumentoSeleccionado;
	private JPanel spVentaDetalle;
	private JPanel spTotales;
	private JLabel txtSubtotalFinal;
	private JLabel lblSubtotalFinal;
	private JLabel txtDescuentoFinal;
	private JLabel lblDescuentoFinal;
	private JLabel lblImpuestos;
	private JLabel txtImpuestosFinal;
	private JTextField txtvacio;
	private JLabel lblTotalFinal;
	private JLabel lblDonacion;
	private JTextField txtTotalFinal;
	private JLabel txtDonacion;
	private JLabel label13;
	private JLabel txtDeuda;
	private JLabel lblVuelto;
	private JLabel txtVuelto;
	private JPanel spDetalles;
	private JScrollPane scrollPane1;
	private JTable tblVentaDetalle;
	private JPanel panel11;
	private JButton btnEliminar;
	private JPanel pnNumeroDocumento;
	private JComboBox cmbTipoDocDevolucion;
	private JLabel lblPreImpreso;
	private JTextField txtNumDocumento;
	private JLabel lblBuscarDocumento;
	private JButton btnBuscarProducto;
	private JPanel panel5;
	private JLabel lblCantidadDetalle;
	private JTextField txtCantidadDetalle;
	private JLabel lblCantidadDevuelta;
	private JTextField txtCantidadDevuelta;
	private JLabel label2;
	private JLabel lblItem;
	private JTextField txtPorcentajeDescuento;
	private JLabel lblPorcentajeDescuento;
	private JTextField txtItem;
	private JLabel lblVisible;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	public JPanel getPnVendedor() {
		return pnVendedor;
	}

	public void setPnVendedor(JPanel pnVendedor) {
		this.pnVendedor = pnVendedor;
	}

	public JComboBox getCmbVendedor() {
		return cmbVendedor;
	}

	public void setCmbVendedor(JComboBox cmbVendedor) {
		this.cmbVendedor = cmbVendedor;
	}

	public JLabel getLblVendedor() {
		return lblVendedor;
	}

	public void setLblVendedor(JLabel lblVendedor) {
		this.lblVendedor = lblVendedor;
	}

	public JPanel getPanel12() {
		return panel12;
	}

	public void setPanel12(JPanel panel12) {
		this.panel12 = panel12;
	}

	public JLabel getLblCajero() {
		return lblCajero;
	}

	public void setLblCajero(JLabel lblCajero) {
		this.lblCajero = lblCajero;
	}

	public JTextField getTxtCajero() {
		return txtCajero;
	}

	public void setTxtCajero(JTextField txtCajero) {
		this.txtCajero = txtCajero;
	}

	public JLabel getLblCaja() {
		return lblCaja;
	}

	public void setLblCaja(JLabel lblCaja) {
		this.lblCaja = lblCaja;
	}

	public JTextField getTxtCaja() {
		return txtCaja;
	}

	public void setTxtCaja(JTextField txtCaja) {
		this.txtCaja = txtCaja;
	}

	public JButton getBtnAbrir() {
		return btnAbrir;
	}

	public void setBtnAbrir(JButton btnAbrir) {
		this.btnAbrir = btnAbrir;
	}

	public JPanel getPanel4() {
		return panel4;
	}

	public void setPanel4(JPanel panel4) {
		this.panel4 = panel4;
	}

	public JPanel getPanel9() {
		return panel9;
	}

	public void setPanel9(JPanel panel9) {
		this.panel9 = panel9;
	}

	public JButton getBtnDonacion() {
		return btnDonacion;
	}

	public void setBtnDonacion(JButton btnDonacion) {
		this.btnDonacion = btnDonacion;
	}

	public JButton getBtnCaja() {
		return btnCaja;
	}

	public void setBtnCaja(JButton btnCaja) {
		this.btnCaja = btnCaja;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnTransferirTarjetaAfiliacion() {
		return btnTransferirTarjetaAfiliacion;
	}

	public void setBtnTransferirTarjetaAfiliacion(JButton btnTransferirTarjetaAfiliacion) {
		this.btnTransferirTarjetaAfiliacion = btnTransferirTarjetaAfiliacion;
	}

	public JButton getBtnAcreditar() {
		return btnAcreditar;
	}

	public void setBtnAcreditar(JButton btnAcreditar) {
		this.btnAcreditar = btnAcreditar;
	}

	public JButton getBtnHistorial() {
		return btnHistorial;
	}

	public void setBtnHistorial(JButton btnHistorial) {
		this.btnHistorial = btnHistorial;
	}

	public JPanel getPnCliente() {
		return pnCliente;
	}

	public void setPnCliente(JPanel pnCliente) {
		this.pnCliente = pnCliente;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	public JLabel getLblCedula() {
		return lblCedula;
	}

	public void setLblCedula(JLabel lblCedula) {
		this.lblCedula = lblCedula;
	}

	public JLabel getLblNombre() {
		return lblNombre;
	}

	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	public JLabel getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JLabel txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JLabel getLblEmail() {
		return lblEmail;
	}

	public void setLblEmail(JLabel lblEmail) {
		this.lblEmail = lblEmail;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(JTextField txtEmail) {
		this.txtEmail = txtEmail;
	}

	public JLabel getLblCorporacion() {
		return lblCorporacion;
	}

	public void setLblCorporacion(JLabel lblCorporacion) {
		this.lblCorporacion = lblCorporacion;
	}

	public JLabel getTxtCorporacion() {
		return txtCorporacion;
	}

	public void setTxtCorporacion(JLabel txtCorporacion) {
		this.txtCorporacion = txtCorporacion;
	}

	public JLabel getLblOficina() {
		return lblOficina;
	}

	public void setLblOficina(JLabel lblOficina) {
		this.lblOficina = lblOficina;
	}

	public JLabel getTxtOficina() {
		return txtOficina;
	}

	public void setTxtOficina(JLabel txtOficina) {
		this.txtOficina = txtOficina;
	}

	public JPanel getPanel14() {
		return panel14;
	}

	public void setPanel14(JPanel panel14) {
		this.panel14 = panel14;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnActualizarCliente() {
		return btnActualizarCliente;
	}

	public void setBtnActualizarCliente(JButton btnActualizarCliente) {
		this.btnActualizarCliente = btnActualizarCliente;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPnResumenPagos() {
		return pnResumenPagos;
	}

	public void setPnResumenPagos(JPanel pnResumenPagos) {
		this.pnResumenPagos = pnResumenPagos;
	}

	public JLabel getLblEfectivo() {
		return lblEfectivo;
	}

	public void setLblEfectivo(JLabel lblEfectivo) {
		this.lblEfectivo = lblEfectivo;
	}

	public JTextField getTxtEfectivo() {
		return txtEfectivo;
	}

	public void setTxtEfectivo(JTextField txtEfectivo) {
		this.txtEfectivo = txtEfectivo;
	}

	public JLabel getLblGiftcard() {
		return lblGiftcard;
	}

	public void setLblGiftcard(JLabel lblGiftcard) {
		this.lblGiftcard = lblGiftcard;
	}

	public JTextField getTxtGiftcard() {
		return txtGiftcard;
	}

	public void setTxtGiftcard(JTextField txtGiftcard) {
		this.txtGiftcard = txtGiftcard;
	}

	public JLabel getLblTarjetaCredito() {
		return lblTarjetaCredito;
	}

	public void setLblTarjetaCredito(JLabel lblTarjetaCredito) {
		this.lblTarjetaCredito = lblTarjetaCredito;
	}

	public JTextField getTxtTarjetaCredito() {
		return txtTarjetaCredito;
	}

	public void setTxtTarjetaCredito(JTextField txtTarjetaCredito) {
		this.txtTarjetaCredito = txtTarjetaCredito;
	}

	public JTextField getTxtCheque() {
		return txtCheque;
	}

	public void setTxtCheque(JTextField txtCheque) {
		this.txtCheque = txtCheque;
	}

	public JLabel getLblDebitoBancario() {
		return lblDebitoBancario;
	}

	public void setLblDebitoBancario(JLabel lblDebitoBancario) {
		this.lblDebitoBancario = lblDebitoBancario;
	}

	public JTextField getTxtDebitoBancario() {
		return txtDebitoBancario;
	}

	public void setTxtDebitoBancario(JTextField txtDebitoBancario) {
		this.txtDebitoBancario = txtDebitoBancario;
	}

	public JLabel getLblCreditoCliente() {
		return lblCreditoCliente;
	}

	public void setLblCreditoCliente(JLabel lblCreditoCliente) {
		this.lblCreditoCliente = lblCreditoCliente;
	}

	public JTextField getTxtCreditoCliente() {
		return txtCreditoCliente;
	}

	public void setTxtCreditoCliente(JTextField txtCreditoCliente) {
		this.txtCreditoCliente = txtCreditoCliente;
	}

	public JLabel getLblCheque() {
		return lblCheque;
	}

	public void setLblCheque(JLabel lblCheque) {
		this.lblCheque = lblCheque;
	}

	public JLabel getLblDebitoBancario2() {
		return lblDebitoBancario2;
	}

	public void setLblDebitoBancario2(JLabel lblDebitoBancario2) {
		this.lblDebitoBancario2 = lblDebitoBancario2;
	}

	public JTextField getTxtPuntos() {
		return txtPuntos;
	}

	public void setTxtPuntos(JTextField txtPuntos) {
		this.txtPuntos = txtPuntos;
	}

	public JButton getBtnResumenCobrosPagos() {
		return btnResumenCobrosPagos;
	}

	public void setBtnResumenCobrosPagos(JButton btnResumenCobrosPagos) {
		this.btnResumenCobrosPagos = btnResumenCobrosPagos;
	}

	public JPanel getPnTransaccion() {
		return pnTransaccion;
	}

	public void setPnTransaccion(JPanel pnTransaccion) {
		this.pnTransaccion = pnTransaccion;
	}

	public JButton getBtnAnticipo() {
		return btnAnticipo;
	}

	public void setBtnAnticipo(JButton btnAnticipo) {
		this.btnAnticipo = btnAnticipo;
	}

	public JButton getBtnFactura() {
		return btnFactura;
	}

	public void setBtnFactura(JButton btnFactura) {
		this.btnFactura = btnFactura;
	}

	public JButton getBtnDevolucion() {
		return btnDevolucion;
	}

	public void setBtnDevolucion(JButton btnDevolucion) {
		this.btnDevolucion = btnDevolucion;
	}

	public JButton getBtnNotaVenta() {
		return btnNotaVenta;
	}

	public void setBtnNotaVenta(JButton btnNotaVenta) {
		this.btnNotaVenta = btnNotaVenta;
	}

	public JPanel getPanel10() {
		return panel10;
	}

	public void setPanel10(JPanel panel10) {
		this.panel10 = panel10;
	}

	public JSplitPane getSplitPane1() {
		return splitPane1;
	}

	public void setSplitPane1(JSplitPane splitPane1) {
		this.splitPane1 = splitPane1;
	}

	public JPanel getPnCalculadora() {
		return PnCalculadora;
	}

	public void setPnCalculadora(JPanel pnCalculadora) {
		PnCalculadora = pnCalculadora;
	}

	public JPanel getPanelInformacionProducto() {
		return panelInformacionProducto;
	}

	public void setPanelInformacionProducto(JPanel panelInformacionProducto) {
		this.panelInformacionProducto = panelInformacionProducto;
	}

	public JTextField getTxtLoteid() {
		return txtLoteid;
	}

	public void setTxtLoteid(JTextField txtLoteid) {
		this.txtLoteid = txtLoteid;
	}

	public JTextField getTxtIdProducto() {
		return txtIdProducto;
	}

	public void setTxtIdProducto(JTextField txtIdProducto) {
		this.txtIdProducto = txtIdProducto;
	}

	public JTextField getTxtTipoProducto() {
		return txtTipoProducto;
	}

	public void setTxtTipoProducto(JTextField txtTipoProducto) {
		this.txtTipoProducto = txtTipoProducto;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JTextField getTxtPVP() {
		return txtPVP;
	}

	public void setTxtPVP(JTextField txtPVP) {
		this.txtPVP = txtPVP;
	}

	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	public JTextField getTxtDescuento() {
		return txtDescuento;
	}

	public void setTxtDescuento(JTextField txtDescuento) {
		this.txtDescuento = txtDescuento;
	}

	public JTextField getTxtIVA() {
		return txtIVA;
	}

	public void setTxtIVA(JTextField txtIVA) {
		this.txtIVA = txtIVA;
	}

	public JTextField getTxtImporte() {
		return txtImporte;
	}

	public void setTxtImporte(JTextField txtImporte) {
		this.txtImporte = txtImporte;
	}

	public JTextField getTxtCodigoProducto() {
		return txtCodigoProducto;
	}

	public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}

	public JPanel getPnEsteItem() {
		return pnEsteItem;
	}

	public void setPnEsteItem(JPanel pnEsteItem) {
		this.pnEsteItem = pnEsteItem;
	}

	public JLabel getLblEsteItem() {
		return lblEsteItem;
	}

	public void setLblEsteItem(JLabel lblEsteItem) {
		this.lblEsteItem = lblEsteItem;
	}

	public JTextField getTxtEsteItem() {
		return txtEsteItem;
	}

	public void setTxtEsteItem(JTextField txtEsteItem) {
		this.txtEsteItem = txtEsteItem;
	}

	public JPanel getPnCodigoBarras() {
		return pnCodigoBarras;
	}

	public void setPnCodigoBarras(JPanel pnCodigoBarras) {
		this.pnCodigoBarras = pnCodigoBarras;
	}

	public JLabel getLblCodigoBarras() {
		return lblCodigoBarras;
	}

	public void setLblCodigoBarras(JLabel lblCodigoBarras) {
		this.lblCodigoBarras = lblCodigoBarras;
	}

	public JTextField getTxtCodigoBarras() {
		return txtCodigoBarras;
	}

	public void setTxtCodigoBarras(JTextField txtCodigoBarras) {
		this.txtCodigoBarras = txtCodigoBarras;
	}

	public JPanel getPnFormasPago() {
		return pnFormasPago;
	}

	public void setPnFormasPago(JPanel pnFormasPago) {
		this.pnFormasPago = pnFormasPago;
	}

	public JButton getBtnEfectivo() {
		return btnEfectivo;
	}

	public void setBtnEfectivo(JButton btnEfectivo) {
		this.btnEfectivo = btnEfectivo;
	}

	public JButton getBtnTarjetaCredito() {
		return btnTarjetaCredito;
	}

	public void setBtnTarjetaCredito(JButton btnTarjetaCredito) {
		this.btnTarjetaCredito = btnTarjetaCredito;
	}

	public JButton getBtnCredito() {
		return btnCredito;
	}

	public void setBtnCredito(JButton btnCredito) {
		this.btnCredito = btnCredito;
	}

	public JButton getBtnGiftcard() {
		return btnGiftcard;
	}

	public void setBtnGiftcard(JButton btnGiftcard) {
		this.btnGiftcard = btnGiftcard;
	}

	public JButton getBtnDebito() {
		return btnDebito;
	}

	public void setBtnDebito(JButton btnDebito) {
		this.btnDebito = btnDebito;
	}

	public JButton getBtnCheque() {
		return btnCheque;
	}

	public void setBtnCheque(JButton btnCheque) {
		this.btnCheque = btnCheque;
	}

	public JButton getBtnAfiliacionReferido() {
		return btnAfiliacionReferido;
	}

	public void setBtnAfiliacionReferido(JButton btnAfiliacionReferido) {
		this.btnAfiliacionReferido = btnAfiliacionReferido;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JLabel getLblTipoDocumentoSeleccionado() {
		return lblTipoDocumentoSeleccionado;
	}

	public void setLblTipoDocumentoSeleccionado(JLabel lblTipoDocumentoSeleccionado) {
		this.lblTipoDocumentoSeleccionado = lblTipoDocumentoSeleccionado;
	}

	public JPanel getSpVentaDetalle() {
		return spVentaDetalle;
	}

	public void setSpVentaDetalle(JPanel spVentaDetalle) {
		this.spVentaDetalle = spVentaDetalle;
	}

	public JPanel getSpTotales() {
		return spTotales;
	}

	public void setSpTotales(JPanel spTotales) {
		this.spTotales = spTotales;
	}

	public JLabel getTxtSubtotalFinal() {
		return txtSubtotalFinal;
	}

	public void setTxtSubtotalFinal(JLabel txtSubtotalFinal) {
		this.txtSubtotalFinal = txtSubtotalFinal;
	}

	public JLabel getLblSubtotalFinal() {
		return lblSubtotalFinal;
	}

	public void setLblSubtotalFinal(JLabel lblSubtotalFinal) {
		this.lblSubtotalFinal = lblSubtotalFinal;
	}

	public JLabel getTxtDescuentoFinal() {
		return txtDescuentoFinal;
	}

	public void setTxtDescuentoFinal(JLabel txtDescuentoFinal) {
		this.txtDescuentoFinal = txtDescuentoFinal;
	}

	public JLabel getLblDescuentoFinal() {
		return lblDescuentoFinal;
	}

	public void setLblDescuentoFinal(JLabel lblDescuentoFinal) {
		this.lblDescuentoFinal = lblDescuentoFinal;
	}

	public JLabel getLblImpuestos() {
		return lblImpuestos;
	}

	public void setLblImpuestos(JLabel lblImpuestos) {
		this.lblImpuestos = lblImpuestos;
	}

	public JLabel getTxtImpuestosFinal() {
		return txtImpuestosFinal;
	}

	public void setTxtImpuestosFinal(JLabel txtImpuestosFinal) {
		this.txtImpuestosFinal = txtImpuestosFinal;
	}

	public JTextField getTxtvacio() {
		return txtvacio;
	}

	public void setTxtvacio(JTextField txtvacio) {
		this.txtvacio = txtvacio;
	}

	public JLabel getLblTotalFinal() {
		return lblTotalFinal;
	}

	public void setLblTotalFinal(JLabel lblTotalFinal) {
		this.lblTotalFinal = lblTotalFinal;
	}

	public JLabel getLblDonacion() {
		return lblDonacion;
	}

	public void setLblDonacion(JLabel lblDonacion) {
		this.lblDonacion = lblDonacion;
	}

	public JTextField getTxtTotalFinal() {
		return txtTotalFinal;
	}

	public void setTxtTotalFinal(JTextField txtTotalFinal) {
		this.txtTotalFinal = txtTotalFinal;
	}

	public JLabel getTxtDonacion() {
		return txtDonacion;
	}

	public void setTxtDonacion(JLabel txtDonacion) {
		this.txtDonacion = txtDonacion;
	}

	public JLabel getLabel13() {
		return label13;
	}

	public void setLabel13(JLabel label13) {
		this.label13 = label13;
	}

	public JLabel getTxtDeuda() {
		return txtDeuda;
	}

	public void setTxtDeuda(JLabel txtDeuda) {
		this.txtDeuda = txtDeuda;
	}

	public JLabel getLblVuelto() {
		return lblVuelto;
	}

	public void setLblVuelto(JLabel lblVuelto) {
		this.lblVuelto = lblVuelto;
	}

	public JLabel getTxtVuelto() {
		return txtVuelto;
	}

	public void setTxtVuelto(JLabel txtVuelto) {
		this.txtVuelto = txtVuelto;
	}

	public JPanel getSpDetalles() {
		return spDetalles;
	}

	public void setSpDetalles(JPanel spDetalles) {
		this.spDetalles = spDetalles;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public void setScrollPane1(JScrollPane scrollPane1) {
		this.scrollPane1 = scrollPane1;
	}

	public JTable getTblVentaDetalle() {
		return tblVentaDetalle;
	}

	public void setTblVentaDetalle(JTable tblVentaDetalle) {
		this.tblVentaDetalle = tblVentaDetalle;
	}

	public JPanel getPanel11() {
		return panel11;
	}

	public void setPanel11(JPanel panel11) {
		this.panel11 = panel11;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JPanel getPnNumeroDocumento() {
		return pnNumeroDocumento;
	}

	public void setPnNumeroDocumento(JPanel pnNumeroDocumento) {
		this.pnNumeroDocumento = pnNumeroDocumento;
	}

	public JComboBox getCmbTipoDocDevolucion() {
		return cmbTipoDocDevolucion;
	}

	public void setCmbTipoDocDevolucion(JComboBox cmbTipoDocDevolucion) {
		this.cmbTipoDocDevolucion = cmbTipoDocDevolucion;
	}

	public JLabel getLblPreImpreso() {
		return lblPreImpreso;
	}

	public void setLblPreImpreso(JLabel lblPreImpreso) {
		this.lblPreImpreso = lblPreImpreso;
	}

	public JTextField getTxtNumDocumento() {
		return txtNumDocumento;
	}

	public void setTxtNumDocumento(JTextField txtNumDocumento) {
		this.txtNumDocumento = txtNumDocumento;
	}

	public JLabel getLblBuscarDocumento() {
		return lblBuscarDocumento;
	}

	public void setLblBuscarDocumento(JLabel lblBuscarDocumento) {
		this.lblBuscarDocumento = lblBuscarDocumento;
	}

	public JButton getBtnBuscarProducto() {
		return btnBuscarProducto;
	}

	public void setBtnBuscarProducto(JButton btnBuscarProducto) {
		this.btnBuscarProducto = btnBuscarProducto;
	}

	public JPanel getPanel5() {
		return panel5;
	}

	public void setPanel5(JPanel panel5) {
		this.panel5 = panel5;
	}

	public JLabel getLblCantidadDetalle() {
		return lblCantidadDetalle;
	}

	public void setLblCantidadDetalle(JLabel lblCantidadDetalle) {
		this.lblCantidadDetalle = lblCantidadDetalle;
	}

	public JTextField getTxtCantidadDetalle() {
		return txtCantidadDetalle;
	}

	public void setTxtCantidadDetalle(JTextField txtCantidadDetalle) {
		this.txtCantidadDetalle = txtCantidadDetalle;
	}

	public JLabel getLblCantidadDevuelta() {
		return lblCantidadDevuelta;
	}

	public void setLblCantidadDevuelta(JLabel lblCantidadDevuelta) {
		this.lblCantidadDevuelta = lblCantidadDevuelta;
	}

	public JTextField getTxtCantidadDevuelta() {
		return txtCantidadDevuelta;
	}

	public void setTxtCantidadDevuelta(JTextField txtCantidadDevuelta) {
		this.txtCantidadDevuelta = txtCantidadDevuelta;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JLabel getLblItem() {
		return lblItem;
	}

	public void setLblItem(JLabel lblItem) {
		this.lblItem = lblItem;
	}

	public JTextField getTxtPorcentajeDescuento() {
		return txtPorcentajeDescuento;
	}

	public void setTxtPorcentajeDescuento(JTextField txtPorcentajeDescuento) {
		this.txtPorcentajeDescuento = txtPorcentajeDescuento;
	}

	public JLabel getLblPorcentajeDescuento() {
		return lblPorcentajeDescuento;
	}

	public void setLblPorcentajeDescuento(JLabel lblPorcentajeDescuento) {
		this.lblPorcentajeDescuento = lblPorcentajeDescuento;
	}

	public JTextField getTxtItem() {
		return txtItem;
	}

	public void setTxtItem(JTextField txtItem) {
		this.txtItem = txtItem;
	}

	public JLabel getLblVisible() {
		return lblVisible;
	}

	public void setLblVisible(JLabel lblVisible) {
		this.lblVisible = lblVisible;
	}

	public JTextField getTxtIdGiftcard() {
		return txtIdGiftcard;
	}

	public void setTxtIdGiftcard(JTextField txtIdGiftcard) {
		this.txtIdGiftcard = txtIdGiftcard;
	}
}
