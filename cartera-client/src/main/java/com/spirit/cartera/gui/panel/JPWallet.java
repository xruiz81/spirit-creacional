package com.spirit.cartera.gui.panel;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.jidesoft.swing.JideTabbedPane;
import com.spirit.client.SpiritAlert;
import com.spirit.client.model.SpiritModelImpl;

public abstract class JPWallet extends SpiritModelImpl {
	
	public JPWallet() {
		setName("Cartera");
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jtpWallet = new JideTabbedPane();
		jspMaster = new JScrollPane();
		jpMaster = new JPanel();
		txtWalletId = new JFormattedTextField();
		txtReferenceId = new JFormattedTextField();
		rbCustomer = new JRadioButton();
		rbProvider = new JRadioButton();
		lblBusinessOperatorType = new JLabel();
		txtCode = new JFormattedTextField();
		lblEmissionDate = new JLabel();
		txtEmissionDate = new JFormattedTextField();
		lblOffice = new JLabel();
		txtOffice = new JFormattedTextField();
		lblCreationDate = new JLabel();
		txtCreationDate = new JFormattedTextField();
		lblDocumentType = new JLabel();
		cmbDocumentType = new JComboBox();
		lblLastUpdateDate = new JLabel();
		txtLastUpdateDate = new JFormattedTextField();
		lblCurrency = new JLabel();
		cmbCurrency = new JComboBox();
		lblAnulled = new JLabel();
		cbAnulled = new JCheckBox();
		lblComment = new JLabel();
		txtComment = new JFormattedTextField();
		gfsBusinessOperator = compFactory.createSeparator("Operador de Negocio");
		lblLegalName = new JLabel();
		txtLegalName = new JFormattedTextField();
		btnSearchBusinessOperator = new JButton();
		btnAddBusinessOperator = new JButton();
		lblIdentificationType = new JLabel();
		txtIdentification = new JFormattedTextField();
		lblCityAddress = new JLabel();
		txtCityAddress = new JFormattedTextField();
		gfsTotalValues = compFactory.createSeparator("");
		lblViewWalletAccountingEntry = new JLabel();
		lblTotal = new JLabel();
		txtTotal = new JFormattedTextField();
		lblBalance = new JLabel();
		txtBalance = new JFormattedTextField();
		jspDetail = new JScrollPane();
		jpDetail = new JPanel();
		txtWalletDetailId = new JFormattedTextField();
		lblDocument = new JLabel();
		cmbDocument = new JComboBox();
		lblDetailValue = new JLabel();
		txtDetailValue = new JFormattedTextField();
		lblDetailBalance = new JLabel();
		txtDetailBalance = new JFormattedTextField();
		jtpTransactionDetail = new JTabbedPane();
		jpCheck = new JPanel();
		gfsCheckData = compFactory.createSeparator("Datos cheque:");
		gfsBankDepositData = compFactory.createSeparator("Para depositar en:");
		lblCheckBank = new JLabel();
		cmbCheckBank = new JComboBox();
		lblDepositBank = new JLabel();
		cmbDepositBank = new JComboBox();
		lblCheckAccount = new JLabel();
		cmbCheckAccount = new JComboBox();
		btnAddCheckAccount = new JButton();
		lblDepositAccount = new JLabel();
		cmbDepositAccount = new JComboBox();
		btnAddDepositAccount = new JButton();
		lblCheckNumber = new JLabel();
		txtCheckNumber = new JFormattedTextField();
		jpRetention = new JPanel();
		gfsRetentionData = compFactory.createSeparator("Datos retenci\u00f3n:");
		lblRetentionAuthorization = new JLabel();
		lblRetentionNumber = new JLabel();
		try {
			MaskFormatter retentionNumberFormatter = new MaskFormatter("###-###-#########");
			txtRetentionNumber = new JFormattedTextField(retentionNumberFormatter);
			//MaskFormatter authorizationNumberFormatter = new MaskFormatter("#####################################");
			txtRetentionAuthorization = new JFormattedTextField(/*authorizationNumberFormatter*/);
		} catch (ParseException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar los componentes", SpiritAlert.ERROR);
		}
		lblRetentionPercentage = new JLabel();
		cmbRetentionPercentage = new JComboBox();
		jpDebit = new JPanel();
		gfsDebitData = compFactory.createSeparator("Datos d\u00e9bito:");
		lblDebitBank = new JLabel();
		cmbDebitBank = new JComboBox();
		lblDebitAccount = new JLabel();
		cmbDebitAccount = new JComboBox();
		btnAddDebitAccount = new JButton();
		lblDebitReference = new JLabel();
		txtDebitReference = new JTextField();
		jpTransfer = new JPanel();
		gfsSourceAccountData = compFactory.createSeparator("Cuenta bancaria origen:");
		gfsTargetAccountData = compFactory.createSeparator("Cuenta bancaria destino:");
		lblSourceBank = new JLabel();
		cmbSourceBank = new JComboBox();
		lblTargetBank = new JLabel();
		cmbTargetBank = new JComboBox();
		lblSourceAccount = new JLabel();
		cmbSourceAccount = new JComboBox();
		btnAddSourceAccount = new JButton();
		lblTargetAccount = new JLabel();
		cmbTargetAccount = new JComboBox();
		btnAddTargetAccount = new JButton();
		jpCreditCard = new JPanel();
		gfsCreditCardData = compFactory.createSeparator("Datos T/C:");
		lblCreditCardBank = new JLabel();
		cmbCreditCardBank = new JComboBox();
		lblCreditCard = new JLabel();
		cmbCreditCard = new JComboBox();
		btnAddCreditCard = new JButton();
		lblVoucherReference = new JLabel();
		txtVoucherReference = new JTextField();
		jpElectronicPayment = new JPanel();
		lblElectronicPaymentReference = new JLabel();
		gfsElectronicPaymentData = compFactory.createSeparator("Datos pago electr\u00f3nico:");
		txtElectronicPaymentReference = new JTextField();
		lblStatusMessage = new JLabel();
		spButtonBar = new JPanel();
		btnAddDetail = new JButton();
		btnUpdateDetail = new JButton();
		btnDeleteDetail = new JButton();
		btnHit = new JButton();
		spTblWalletDetail = new JScrollPane();
		tblWalletDetail = new JTable();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setLayout(new FormLayout(
			ColumnSpec.decodeSpecs("max(default;400dlu):grow"),
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
			}));

		//======== jtpWallet ========
		{

			//======== jspMaster ========
			{

				//======== jpMaster ========
				{
					jpMaster.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(50)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(10), FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							new RowSpec(Sizes.dluY(10)),
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
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(RowSpec.CENTER, Sizes.dluY(10), FormSpec.DEFAULT_GROW)
						}));

					//---- txtWalletId ----
					txtWalletId.setEditable(false);
					jpMaster.add(txtWalletId, cc.xy(3, 1));

					//---- txtReferenceId ----
					txtReferenceId.setEditable(false);
					jpMaster.add(txtReferenceId, cc.xy(5, 1));

					//---- rbCustomer ----
					rbCustomer.setText("Cliente");
					rbCustomer.setSelected(true);
					jpMaster.add(rbCustomer, cc.xy(5, 3));

					//---- rbProvider ----
					rbProvider.setText("Proveedor");
					jpMaster.add(rbProvider, cc.xy(7, 3));

					//---- lblBusinessOperatorType ----
					lblBusinessOperatorType.setText("Tipo operador:");
					lblBusinessOperatorType.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblBusinessOperatorType, cc.xy(3, 3));

					//---- txtCode ----
					txtCode.setHorizontalAlignment(SwingConstants.CENTER);
					txtCode.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(txtCode, cc.xywh(11, 3, 3, 1));

					//---- lblEmissionDate ----
					lblEmissionDate.setText("Fecha emisi\u00f3n:");
					lblEmissionDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblEmissionDate, cc.xy(19, 3));

					//---- txtEmissionDate ----
					txtEmissionDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpMaster.add(txtEmissionDate, cc.xy(21, 3));

					//---- lblOffice ----
					lblOffice.setText("Oficina:");
					lblOffice.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblOffice, cc.xy(3, 5));

					//---- txtOffice ----
					txtOffice.setEditable(false);
					txtOffice.setFocusTraversalPolicyProvider(false);
					jpMaster.add(txtOffice, cc.xywh(5, 5, 9, 1));

					//---- lblCreationDate ----
					lblCreationDate.setText("Fecha creaci\u00f3n:");
					lblCreationDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblCreationDate, cc.xy(19, 5));

					//---- txtCreationDate ----
					txtCreationDate.setEditable(false);
					txtCreationDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpMaster.add(txtCreationDate, cc.xy(21, 5));

					//---- lblDocumentType ----
					lblDocumentType.setText("Tipo documento:");
					lblDocumentType.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblDocumentType, cc.xy(3, 7));
					jpMaster.add(cmbDocumentType, cc.xywh(5, 7, 9, 1));

					//---- lblLastUpdateDate ----
					lblLastUpdateDate.setText("Fecha \u00falt. actualizaci\u00f3n:");
					lblLastUpdateDate.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblLastUpdateDate, cc.xy(19, 7));

					//---- txtLastUpdateDate ----
					txtLastUpdateDate.setEditable(false);
					txtLastUpdateDate.setHorizontalAlignment(SwingConstants.CENTER);
					jpMaster.add(txtLastUpdateDate, cc.xy(21, 7));

					//---- lblCurrency ----
					lblCurrency.setText("Moneda:");
					lblCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblCurrency, cc.xy(3, 9));
					jpMaster.add(cmbCurrency, cc.xywh(5, 9, 9, 1));

					//---- lblAnulled ----
					lblAnulled.setText("Anulado:");
					lblAnulled.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblAnulled, cc.xy(19, 9));

					//---- cbAnulled ----
					cbAnulled.setEnabled(false);
					jpMaster.add(cbAnulled, cc.xy(21, 9));

					//---- lblComment ----
					lblComment.setText("Comentario:");
					lblComment.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblComment, cc.xy(3, 11));
					jpMaster.add(txtComment, cc.xywh(5, 11, 17, 1));
					jpMaster.add(gfsBusinessOperator, cc.xywh(3, 15, 21, 1));

					//---- lblLegalName ----
					lblLegalName.setText("Nombre Comercial:");
					lblLegalName.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblLegalName, cc.xy(3, 17));
					jpMaster.add(txtLegalName, cc.xywh(5, 17, 9, 1));

					//---- btnSearchBusinessOperator ----
					btnSearchBusinessOperator.setText("B");
					jpMaster.add(btnSearchBusinessOperator, cc.xy(15, 17));

					//---- btnAddBusinessOperator ----
					btnAddBusinessOperator.setText("+");
					jpMaster.add(btnAddBusinessOperator, cc.xywh(17, 17, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

					//---- lblIdentificationType ----
					lblIdentificationType.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblIdentificationType.setText("Identificaci\u00f3n:");
					jpMaster.add(lblIdentificationType, cc.xy(19, 17));

					//---- txtIdentification ----
					txtIdentification.setEditable(false);
					txtIdentification.setHorizontalAlignment(SwingConstants.CENTER);
					jpMaster.add(txtIdentification, cc.xy(21, 17));

					//---- lblCityAddress ----
					lblCityAddress.setText("Ciudad/Direcci\u00f3n:");
					lblCityAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblCityAddress, cc.xy(3, 19));

					//---- txtCityAddress ----
					txtCityAddress.setEditable(false);
					jpMaster.add(txtCityAddress, cc.xywh(5, 19, 17, 1));
					jpMaster.add(gfsTotalValues, cc.xywh(3, 23, 21, 1));

					//---- lblViewWalletAccountingEntry ----
					lblViewWalletAccountingEntry.setText("<html><u>Visualizar asiento(s) asociado(s) a este comprobante</u></html>");
					lblViewWalletAccountingEntry.setForeground(Color.blue);
					lblViewWalletAccountingEntry.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpMaster.add(lblViewWalletAccountingEntry, cc.xywh(3, 25, 9, 1));

					//---- lblTotal ----
					lblTotal.setText("T  O  T  A  L  :");
					lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblTotal.setHorizontalAlignment(SwingConstants.LEADING);
					jpMaster.add(lblTotal, cc.xy(19, 25));

					//---- txtTotal ----
					txtTotal.setEditable(false);
					txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
					jpMaster.add(txtTotal, cc.xy(21, 25));

					//---- lblBalance ----
					lblBalance.setText("S  A  L  D  O  :");
					lblBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblBalance.setHorizontalAlignment(SwingConstants.LEADING);
					jpMaster.add(lblBalance, cc.xy(19, 27));

					//---- txtBalance ----
					txtBalance.setEditable(false);
					txtBalance.setHorizontalAlignment(SwingConstants.CENTER);
					txtBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
					txtBalance.setForeground(Color.blue);
					jpMaster.add(txtBalance, cc.xy(21, 27));
				}
				jspMaster.setViewportView(jpMaster);
			}
			jtpWallet.addTab("Maestro", jspMaster);


			//======== jspDetail ========
			{

				//======== jpDetail ========
				{
					jpDetail.setLayout(new FormLayout(
						new ColumnSpec[] {
							new ColumnSpec(Sizes.dluX(10)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(60)),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
							FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
							new ColumnSpec(Sizes.dluX(10))
						},
						new RowSpec[] {
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10)),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec("max(default;75dlu)"),
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							FormFactory.DEFAULT_ROWSPEC,
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec("max(default;150dlu)"),
							FormFactory.LINE_GAP_ROWSPEC,
							new RowSpec(Sizes.dluY(10))
						}));

					//---- txtWalletDetailId ----
					txtWalletDetailId.setEditable(false);
					jpDetail.add(txtWalletDetailId, cc.xy(3, 1));

					//---- lblDocument ----
					lblDocument.setText("Documento:");
					lblDocument.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpDetail.add(lblDocument, cc.xy(3, 3));
					jpDetail.add(cmbDocument, cc.xywh(5, 3, 9, 1));

					//---- lblDetailValue ----
					lblDetailValue.setText("Valor:");
					lblDetailValue.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpDetail.add(lblDetailValue, cc.xy(3, 5));

					//---- txtDetailValue ----
					txtDetailValue.setHorizontalAlignment(SwingConstants.RIGHT);
					jpDetail.add(txtDetailValue, cc.xy(5, 5));

					//---- lblDetailBalance ----
					lblDetailBalance.setText("Saldo:");
					lblDetailBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
					jpDetail.add(lblDetailBalance, cc.xy(9, 5));

					//---- txtDetailBalance ----
					txtDetailBalance.setEditable(false);
					txtDetailBalance.setHorizontalAlignment(SwingConstants.RIGHT);
					jpDetail.add(txtDetailBalance, cc.xy(11, 5));

					//======== jtpTransactionDetail ========
					{

						//======== jpCheck ========
						{
							jpCheck.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(10), FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC
								}));
							jpCheck.add(gfsCheckData, cc.xywh(3, 3, 5, 1));
							jpCheck.add(gfsBankDepositData, cc.xywh(11, 3, 5, 1));

							//---- lblCheckBank ----
							lblCheckBank.setText("Banco:");
							lblCheckBank.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCheck.add(lblCheckBank, cc.xy(3, 5));
							jpCheck.add(cmbCheckBank, cc.xywh(5, 5, 3, 1));

							//---- lblDepositBank ----
							lblDepositBank.setText("Banco:");
							lblDepositBank.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCheck.add(lblDepositBank, cc.xy(11, 5));
							jpCheck.add(cmbDepositBank, cc.xywh(13, 5, 3, 1));

							//---- lblCheckAccount ----
							lblCheckAccount.setText("Cuenta bancaria:");
							lblCheckAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCheck.add(lblCheckAccount, cc.xy(3, 7));
							jpCheck.add(cmbCheckAccount, cc.xywh(5, 7, 3, 1));

							//---- btnAddCheckAccount ----
							btnAddCheckAccount.setText("+");
							jpCheck.add(btnAddCheckAccount, cc.xywh(9, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblDepositAccount ----
							lblDepositAccount.setText("Cuenta bancaria:");
							lblDepositAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCheck.add(lblDepositAccount, cc.xy(11, 7));
							jpCheck.add(cmbDepositAccount, cc.xywh(13, 7, 3, 1));

							//---- btnAddDepositAccount ----
							btnAddDepositAccount.setText("+");
							jpCheck.add(btnAddDepositAccount, cc.xywh(17, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblCheckNumber ----
							lblCheckNumber.setText("No. Cheque:");
							lblCheckNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCheck.add(lblCheckNumber, cc.xy(3, 9));

							//---- txtCheckNumber ----
							txtCheckNumber.setHorizontalAlignment(SwingConstants.RIGHT);
							jpCheck.add(txtCheckNumber, cc.xy(5, 9));
						}
						jtpTransactionDetail.addTab("Cheque", jpCheck);
						jtpTransactionDetail.setEnabledAt(0, false);

						//======== jpRetention ========
						{
							jpRetention.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(70)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(80)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5)
								}));
							jpRetention.add(gfsRetentionData, cc.xywh(3, 3, 5, 1));

							//---- lblRetentionAuthorization ----
							lblRetentionAuthorization.setText("Autorizaci\u00f3n:");
							lblRetentionAuthorization.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpRetention.add(lblRetentionAuthorization, cc.xy(3, 7));

							//---- lblRetentionNumber ----
							lblRetentionNumber.setText("Preimpreso:");
							lblRetentionNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpRetention.add(lblRetentionNumber, cc.xy(3, 5));

							//---- txtRetentionNumber ----
							txtRetentionNumber.setHorizontalAlignment(SwingConstants.CENTER);
							jpRetention.add(txtRetentionNumber, cc.xywh(5, 5, 3, 1));

							//---- txtRetentionAuthorization ----
							txtRetentionAuthorization.setHorizontalAlignment(SwingConstants.CENTER);
							jpRetention.add(txtRetentionAuthorization, cc.xywh(5, 7, 3, 1));

							//---- lblRetentionPercentage ----
							lblRetentionPercentage.setText("% Retenci\u00f3n:");
							lblRetentionPercentage.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpRetention.add(lblRetentionPercentage, cc.xy(3, 9));
							jpRetention.add(cmbRetentionPercentage, cc.xywh(5, 9, 3, 1));
						}
						jtpTransactionDetail.addTab("Retenci\u00f3n", jpRetention);
						jtpTransactionDetail.setEnabledAt(1, false);

						//======== jpDebit ========
						{
							jpDebit.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.dluX(10), FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(RowSpec.FILL, Sizes.DEFAULT, FormSpec.NO_GROW),
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5)
								}));
							jpDebit.add(gfsDebitData, cc.xywh(3, 3, 5, 1));

							//---- lblDebitBank ----
							lblDebitBank.setText("Banco:");
							lblDebitBank.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpDebit.add(lblDebitBank, cc.xy(3, 5));
							jpDebit.add(cmbDebitBank, cc.xywh(5, 5, 3, 1));

							//---- lblDebitAccount ----
							lblDebitAccount.setText("Cuenta bancaria:");
							lblDebitAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpDebit.add(lblDebitAccount, cc.xy(3, 7));
							jpDebit.add(cmbDebitAccount, cc.xywh(5, 7, 3, 1));

							//---- btnAddDebitAccount ----
							btnAddDebitAccount.setText("+");
							jpDebit.add(btnAddDebitAccount, cc.xywh(9, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblDebitReference ----
							lblDebitReference.setText("Referencia:");
							lblDebitReference.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpDebit.add(lblDebitReference, cc.xy(3, 9));

							//---- txtDebitReference ----
							txtDebitReference.setHorizontalAlignment(SwingConstants.RIGHT);
							jpDebit.add(txtDebitReference, cc.xy(5, 9));
						}
						jtpTransactionDetail.addTab("D\u00e9bito", jpDebit);
						jtpTransactionDetail.setEnabledAt(2, false);

						//======== jpTransfer ========
						{
							jpTransfer.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5)
								}));
							jpTransfer.add(gfsSourceAccountData, cc.xywh(3, 3, 5, 1));
							jpTransfer.add(gfsTargetAccountData, cc.xywh(11, 3, 5, 1));

							//---- lblSourceBank ----
							lblSourceBank.setText("Banco:");
							lblSourceBank.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpTransfer.add(lblSourceBank, cc.xy(3, 5));
							jpTransfer.add(cmbSourceBank, cc.xywh(5, 5, 3, 1));

							//---- lblTargetBank ----
							lblTargetBank.setText("Banco:");
							lblTargetBank.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpTransfer.add(lblTargetBank, cc.xy(11, 5));
							jpTransfer.add(cmbTargetBank, cc.xywh(13, 5, 3, 1));

							//---- lblSourceAccount ----
							lblSourceAccount.setText("Cuenta bancaria:");
							lblSourceAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpTransfer.add(lblSourceAccount, cc.xy(3, 7));
							jpTransfer.add(cmbSourceAccount, cc.xywh(5, 7, 3, 1));

							//---- btnAddSourceAccount ----
							btnAddSourceAccount.setText("+");
							jpTransfer.add(btnAddSourceAccount, cc.xywh(9, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblTargetAccount ----
							lblTargetAccount.setText("Cuenta bancaria:");
							lblTargetAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpTransfer.add(lblTargetAccount, cc.xy(11, 7));
							jpTransfer.add(cmbTargetAccount, cc.xywh(13, 7, 3, 1));

							//---- btnAddTargetAccount ----
							btnAddTargetAccount.setText("+");
							jpTransfer.add(btnAddTargetAccount, cc.xywh(17, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));
						}
						jtpTransactionDetail.addTab("Transferencia Bancaria", jpTransfer);
						jtpTransactionDetail.setEnabledAt(3, false);

						//======== jpCreditCard ========
						{
							jpCreditCard.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5)
								}));
							jpCreditCard.add(gfsCreditCardData, cc.xywh(3, 3, 5, 1));

							//---- lblCreditCardBank ----
							lblCreditCardBank.setText("Banco:");
							lblCreditCardBank.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCreditCard.add(lblCreditCardBank, cc.xy(3, 5));
							jpCreditCard.add(cmbCreditCardBank, cc.xywh(5, 5, 3, 1));

							//---- lblCreditCard ----
							lblCreditCard.setText("Tarjeta:");
							lblCreditCard.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCreditCard.add(lblCreditCard, cc.xy(3, 7));
							jpCreditCard.add(cmbCreditCard, cc.xywh(5, 7, 3, 1));

							//---- btnAddCreditCard ----
							btnAddCreditCard.setText("+");
							jpCreditCard.add(btnAddCreditCard, cc.xywh(9, 7, 1, 1, CellConstraints.LEFT, CellConstraints.DEFAULT));

							//---- lblVoucherReference ----
							lblVoucherReference.setText("Referencia:");
							lblVoucherReference.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpCreditCard.add(lblVoucherReference, cc.xy(3, 9));

							//---- txtVoucherReference ----
							txtVoucherReference.setHorizontalAlignment(SwingConstants.RIGHT);
							jpCreditCard.add(txtVoucherReference, cc.xy(5, 9));
						}
						jtpTransactionDetail.addTab("T/C", jpCreditCard);
						jtpTransactionDetail.setEnabledAt(4, false);

						//======== jpElectronicPayment ========
						{
							jpElectronicPayment.setLayout(new FormLayout(
								new ColumnSpec[] {
									new ColumnSpec(Sizes.DLUX5),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									FormFactory.DEFAULT_COLSPEC,
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(Sizes.dluX(75)),
									FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
									new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
								},
								new RowSpec[] {
									new RowSpec(Sizes.DLUY5),
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									FormFactory.DEFAULT_ROWSPEC,
									FormFactory.LINE_GAP_ROWSPEC,
									new RowSpec(Sizes.DLUY5)
								}));

							//---- lblElectronicPaymentReference ----
							lblElectronicPaymentReference.setText("Referencia:");
							lblElectronicPaymentReference.setFont(new Font("Tahoma", Font.BOLD, 11));
							jpElectronicPayment.add(lblElectronicPaymentReference, cc.xy(3, 5));
							jpElectronicPayment.add(gfsElectronicPaymentData, cc.xywh(3, 3, 3, 1));

							//---- txtElectronicPaymentReference ----
							txtElectronicPaymentReference.setHorizontalAlignment(SwingConstants.RIGHT);
							jpElectronicPayment.add(txtElectronicPaymentReference, cc.xy(5, 5));
						}
						jtpTransactionDetail.addTab("Pago Electr\u00f3nico", jpElectronicPayment);
						jtpTransactionDetail.setEnabledAt(5, false);
					}
					jpDetail.add(jtpTransactionDetail, cc.xywh(3, 7, 13, 6));

					//---- lblStatusMessage ----
					lblStatusMessage.setText("Status Message");
					lblStatusMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
					lblStatusMessage.setForeground(Color.blue);
					jpDetail.add(lblStatusMessage, cc.xywh(3, 13, 13, 1));

					//======== spButtonBar ========
					{
						spButtonBar.setLayout(new FormLayout(
							new ColumnSpec[] {
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
								FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
								new ColumnSpec(ColumnSpec.FILL, Sizes.DEFAULT, FormSpec.DEFAULT_GROW)
							},
							RowSpec.decodeSpecs("default")));

						//---- btnAddDetail ----
						btnAddDetail.setText("Agregar");
						spButtonBar.add(btnAddDetail, cc.xy(1, 1));

						//---- btnUpdateDetail ----
						btnUpdateDetail.setText("Actualizar");
						spButtonBar.add(btnUpdateDetail, cc.xy(3, 1));

						//---- btnDeleteDetail ----
						btnDeleteDetail.setText("Eliminar");
						spButtonBar.add(btnDeleteDetail, cc.xy(5, 1));

						//---- btnHit ----
						btnHit.setText("Aplicar cruce de documentos");
						spButtonBar.add(btnHit, cc.xywh(13, 1, 1, 1, CellConstraints.RIGHT, CellConstraints.DEFAULT));
					}
					jpDetail.add(spButtonBar, cc.xywh(3, 15, 13, 1));

					//======== spTblWalletDetail ========
					{

						//---- tblWalletDetail ----
						tblWalletDetail.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								" ", "Fecha", "Transacci\u00f3n", "Valor", "Saldo", "Diferido"
							}
						) {
							Class[] columnTypes = new Class[] {
								Boolean.class, Object.class, Object.class, Object.class, Object.class, Object.class
							};
							boolean[] columnEditable = new boolean[] {
								true, false, false, false, false, false
							};
							@Override
							public Class<?> getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							@Override
							public boolean isCellEditable(int rowIndex, int columnIndex) {
								return columnEditable[columnIndex];
							}
						});
						spTblWalletDetail.setViewportView(tblWalletDetail);
					}
					jpDetail.add(spTblWalletDetail, cc.xywh(3, 17, 13, 3));
				}
				jspDetail.setViewportView(jpDetail);
			}
			jtpWallet.addTab("Detalle", jspDetail);

		}
		add(jtpWallet, cc.xywh(1, 1, 1, 3));

		//---- bgWalletType ----
		ButtonGroup bgWalletType = new ButtonGroup();
		bgWalletType.add(rbCustomer);
		bgWalletType.add(rbProvider);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JideTabbedPane jtpWallet;
	private JScrollPane jspMaster;
	private JPanel jpMaster;
	private JFormattedTextField txtWalletId;
	private JFormattedTextField txtReferenceId;
	private JRadioButton rbCustomer;
	private JRadioButton rbProvider;
	private JLabel lblBusinessOperatorType;
	private JFormattedTextField txtCode;
	private JLabel lblEmissionDate;
	private JFormattedTextField txtEmissionDate;
	private JLabel lblOffice;
	private JFormattedTextField txtOffice;
	private JLabel lblCreationDate;
	private JFormattedTextField txtCreationDate;
	private JLabel lblDocumentType;
	private JComboBox cmbDocumentType;
	private JLabel lblLastUpdateDate;
	private JFormattedTextField txtLastUpdateDate;
	private JLabel lblCurrency;
	private JComboBox cmbCurrency;
	private JLabel lblAnulled;
	private JCheckBox cbAnulled;
	private JLabel lblComment;
	private JFormattedTextField txtComment;
	private JComponent gfsBusinessOperator;
	private JLabel lblLegalName;
	private JFormattedTextField txtLegalName;
	private JButton btnSearchBusinessOperator;
	private JButton btnAddBusinessOperator;
	private JLabel lblIdentificationType;
	private JFormattedTextField txtIdentification;
	private JLabel lblCityAddress;
	private JFormattedTextField txtCityAddress;
	private JComponent gfsTotalValues;
	private JLabel lblViewWalletAccountingEntry;
	private JLabel lblTotal;
	private JFormattedTextField txtTotal;
	private JLabel lblBalance;
	private JFormattedTextField txtBalance;
	private JScrollPane jspDetail;
	private JPanel jpDetail;
	private JFormattedTextField txtWalletDetailId;
	private JLabel lblDocument;
	private JComboBox cmbDocument;
	private JLabel lblDetailValue;
	private JFormattedTextField txtDetailValue;
	private JLabel lblDetailBalance;
	private JFormattedTextField txtDetailBalance;
	private JTabbedPane jtpTransactionDetail;
	private JPanel jpCheck;
	private JComponent gfsCheckData;
	private JComponent gfsBankDepositData;
	private JLabel lblCheckBank;
	private JComboBox cmbCheckBank;
	private JLabel lblDepositBank;
	private JComboBox cmbDepositBank;
	private JLabel lblCheckAccount;
	private JComboBox cmbCheckAccount;
	private JButton btnAddCheckAccount;
	private JLabel lblDepositAccount;
	private JComboBox cmbDepositAccount;
	private JButton btnAddDepositAccount;
	private JLabel lblCheckNumber;
	private JFormattedTextField txtCheckNumber;
	private JPanel jpRetention;
	private JComponent gfsRetentionData;
	private JLabel lblRetentionAuthorization;
	private JLabel lblRetentionNumber;
	private JFormattedTextField txtRetentionNumber;
	private JFormattedTextField txtRetentionAuthorization;
	private JLabel lblRetentionPercentage;
	private JComboBox cmbRetentionPercentage;
	private JPanel jpDebit;
	private JComponent gfsDebitData;
	private JLabel lblDebitBank;
	private JComboBox cmbDebitBank;
	private JLabel lblDebitAccount;
	private JComboBox cmbDebitAccount;
	private JButton btnAddDebitAccount;
	private JLabel lblDebitReference;
	private JTextField txtDebitReference;
	private JPanel jpTransfer;
	private JComponent gfsSourceAccountData;
	private JComponent gfsTargetAccountData;
	private JLabel lblSourceBank;
	private JComboBox cmbSourceBank;
	private JLabel lblTargetBank;
	private JComboBox cmbTargetBank;
	private JLabel lblSourceAccount;
	private JComboBox cmbSourceAccount;
	private JButton btnAddSourceAccount;
	private JLabel lblTargetAccount;
	private JComboBox cmbTargetAccount;
	private JButton btnAddTargetAccount;
	private JPanel jpCreditCard;
	private JComponent gfsCreditCardData;
	private JLabel lblCreditCardBank;
	private JComboBox cmbCreditCardBank;
	private JLabel lblCreditCard;
	private JComboBox cmbCreditCard;
	private JButton btnAddCreditCard;
	private JLabel lblVoucherReference;
	private JTextField txtVoucherReference;
	private JPanel jpElectronicPayment;
	private JLabel lblElectronicPaymentReference;
	private JComponent gfsElectronicPaymentData;
	private JTextField txtElectronicPaymentReference;
	private JLabel lblStatusMessage;
	private JPanel spButtonBar;
	private JButton btnAddDetail;
	private JButton btnUpdateDetail;
	private JButton btnDeleteDetail;
	private JButton btnHit;
	private JScrollPane spTblWalletDetail;
	private JTable tblWalletDetail;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public JideTabbedPane getJtpWallet() {
		return jtpWallet;
	}

	public void setJtpWallet(JideTabbedPane jtpWallet) {
		this.jtpWallet = jtpWallet;
	}

	public JScrollPane getJspMaster() {
		return jspMaster;
	}

	public void setJspMaster(JScrollPane jspMaster) {
		this.jspMaster = jspMaster;
	}

	public JPanel getJpMaster() {
		return jpMaster;
	}

	public void setJpMaster(JPanel jpMaster) {
		this.jpMaster = jpMaster;
	}

	public JRadioButton getRbCustomer() {
		return rbCustomer;
	}

	public void setRbCustomer(JRadioButton rbCustomer) {
		this.rbCustomer = rbCustomer;
	}

	public JRadioButton getRbProvider() {
		return rbProvider;
	}

	public void setRbProvider(JRadioButton rbProvider) {
		this.rbProvider = rbProvider;
	}

	public JFormattedTextField getTxtCode() {
		return txtCode;
	}

	public void setTxtCode(JFormattedTextField txtCode) {
		this.txtCode = txtCode;
	}

	public JLabel getLblBusinessOperatorType() {
		return lblBusinessOperatorType;
	}

	public void setLblBusinessOperatorType(JLabel lblBusinessOperatorType) {
		this.lblBusinessOperatorType = lblBusinessOperatorType;
	}

	public JLabel getLblEmissionDate() {
		return lblEmissionDate;
	}

	public void setLblEmissionDate(JLabel lblEmissionDate) {
		this.lblEmissionDate = lblEmissionDate;
	}

	public JFormattedTextField getTxtEmissionDate() {
		return txtEmissionDate;
	}

	public void setTxtEmissionDate(JFormattedTextField txtEmissionDate) {
		this.txtEmissionDate = txtEmissionDate;
	}

	public JLabel getLblOffice() {
		return lblOffice;
	}

	public void setLblOffice(JLabel lblOffice) {
		this.lblOffice = lblOffice;
	}

	public JFormattedTextField getTxtOffice() {
		return txtOffice;
	}

	public void setTxtOffice(JFormattedTextField txtOffice) {
		this.txtOffice = txtOffice;
	}

	public JLabel getLblCreationDate() {
		return lblCreationDate;
	}

	public void setLblCreationDate(JLabel lblCreationDate) {
		this.lblCreationDate = lblCreationDate;
	}

	public JFormattedTextField getTxtCreationDate() {
		return txtCreationDate;
	}

	public void setTxtCreationDate(JFormattedTextField txtCreationDate) {
		this.txtCreationDate = txtCreationDate;
	}

	public JLabel getLblDocumentType() {
		return lblDocumentType;
	}

	public void setLblDocumentType(JLabel lblDocumentType) {
		this.lblDocumentType = lblDocumentType;
	}

	public JComboBox getCmbDocumentType() {
		return cmbDocumentType;
	}

	public void setCmbDocumentType(JComboBox cmbDocumentType) {
		this.cmbDocumentType = cmbDocumentType;
	}

	public JLabel getLblLastUpdateDate() {
		return lblLastUpdateDate;
	}

	public void setLblLastUpdateDate(JLabel lblLastUpdateDate) {
		this.lblLastUpdateDate = lblLastUpdateDate;
	}

	public JFormattedTextField getTxtLastUpdateDate() {
		return txtLastUpdateDate;
	}

	public void setTxtLastUpdateDate(JFormattedTextField txtLastUpdateDate) {
		this.txtLastUpdateDate = txtLastUpdateDate;
	}

	public JLabel getLblCurrency() {
		return lblCurrency;
	}

	public void setLblCurrency(JLabel lblCurrency) {
		this.lblCurrency = lblCurrency;
	}

	public JComboBox getCmbCurrency() {
		return cmbCurrency;
	}

	public void setCmbCurrency(JComboBox cmbCurrency) {
		this.cmbCurrency = cmbCurrency;
	}

	public JLabel getLblAnulled() {
		return lblAnulled;
	}

	public void setLblAnulled(JLabel lblAnulled) {
		this.lblAnulled = lblAnulled;
	}

	public JCheckBox getCbAnulled() {
		return cbAnulled;
	}

	public void setCbAnulled(JCheckBox cbAnulled) {
		this.cbAnulled = cbAnulled;
	}

	public JLabel getLblComment() {
		return lblComment;
	}

	public void setLblComment(JLabel lblComment) {
		this.lblComment = lblComment;
	}

	public JFormattedTextField getTxtComment() {
		return txtComment;
	}

	public void setTxtComment(JFormattedTextField txtComment) {
		this.txtComment = txtComment;
	}

	public JLabel getLblLegalName() {
		return lblLegalName;
	}

	public void setLblLegalName(JLabel lblLegalName) {
		this.lblLegalName = lblLegalName;
	}

	public JFormattedTextField getTxtLegalName() {
		return txtLegalName;
	}

	public void setTxtLegalName(JFormattedTextField txtLegalName) {
		this.txtLegalName = txtLegalName;
	}

	public JButton getBtnSearchBusinessOperator() {
		return btnSearchBusinessOperator;
	}

	public void setBtnSearchBusinessOperator(JButton btnSearchBusinessOperator) {
		this.btnSearchBusinessOperator = btnSearchBusinessOperator;
	}

	public JLabel getLblIdentificationType() {
		return lblIdentificationType;
	}

	public void setLblIdentificationType(JLabel lblIdentificationType) {
		this.lblIdentificationType = lblIdentificationType;
	}

	public JFormattedTextField getTxtIdentification() {
		return txtIdentification;
	}

	public void setTxtIdentification(JFormattedTextField txtIdentification) {
		this.txtIdentification = txtIdentification;
	}

	public JLabel getLblCityAddress() {
		return lblCityAddress;
	}

	public void setLblCityAddress(JLabel lblCityAddress) {
		this.lblCityAddress = lblCityAddress;
	}

	public JFormattedTextField getTxtCityAddress() {
		return txtCityAddress;
	}

	public void setTxtCityAddress(JFormattedTextField txtCityAddress) {
		this.txtCityAddress = txtCityAddress;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}

	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	public JFormattedTextField getTxtTotal() {
		return txtTotal;
	}

	public void setTxtTotal(JFormattedTextField txtTotal) {
		this.txtTotal = txtTotal;
	}

	public JLabel getLblBalance() {
		return lblBalance;
	}

	public void setLblBalance(JLabel lblBalance) {
		this.lblBalance = lblBalance;
	}

	public JFormattedTextField getTxtBalance() {
		return txtBalance;
	}

	public void setTxtBalance(JFormattedTextField txtBalance) {
		this.txtBalance = txtBalance;
	}

	public JScrollPane getJspDetail() {
		return jspDetail;
	}

	public void setJspDetail(JScrollPane jspDetail) {
		this.jspDetail = jspDetail;
	}

	public JPanel getJpDetail() {
		return jpDetail;
	}

	public void setJpDetail(JPanel jpDetail) {
		this.jpDetail = jpDetail;
	}

	public JLabel getLblDocument() {
		return lblDocument;
	}

	public void setLblDocument(JLabel lblDocument) {
		this.lblDocument = lblDocument;
	}

	public JComboBox getCmbDocument() {
		return cmbDocument;
	}

	public void setCmbDocument(JComboBox cmbDocument) {
		this.cmbDocument = cmbDocument;
	}

	public JLabel getLblDetailValue() {
		return lblDetailValue;
	}

	public void setLblDetailValue(JLabel lblDetailValue) {
		this.lblDetailValue = lblDetailValue;
	}

	public JFormattedTextField getTxtDetailValue() {
		return txtDetailValue;
	}

	public void setTxtDetailValue(JFormattedTextField txtDetailValue) {
		this.txtDetailValue = txtDetailValue;
	}

	public JLabel getLblDetailBalance() {
		return lblDetailBalance;
	}

	public void setLblDetailBalance(JLabel lblDetailBalance) {
		this.lblDetailBalance = lblDetailBalance;
	}

	public JFormattedTextField getTxtDetailBalance() {
		return txtDetailBalance;
	}

	public void setTxtDetailBalance(JFormattedTextField txtDetailBalance) {
		this.txtDetailBalance = txtDetailBalance;
	}

	public JTabbedPane getJtpTransactionDetail() {
		return jtpTransactionDetail;
	}

	public void setJtpTransactionDetail(JTabbedPane jtpTransactionDetail) {
		this.jtpTransactionDetail = jtpTransactionDetail;
	}

	public JPanel getJpCheck() {
		return jpCheck;
	}

	public void setJpCheck(JPanel jpCheck) {
		this.jpCheck = jpCheck;
	}

	public JLabel getLblCheckBank() {
		return lblCheckBank;
	}

	public void setLblCheckBank(JLabel lblCheckBank) {
		this.lblCheckBank = lblCheckBank;
	}

	public JComboBox getCmbCheckBank() {
		return cmbCheckBank;
	}

	public void setCmbCheckBank(JComboBox cmbCheckBank) {
		this.cmbCheckBank = cmbCheckBank;
	}

	public JLabel getLblDepositBank() {
		return lblDepositBank;
	}

	public void setLblDepositBank(JLabel lblDepositBank) {
		this.lblDepositBank = lblDepositBank;
	}

	public JComboBox getCmbDepositBank() {
		return cmbDepositBank;
	}

	public void setCmbDepositBank(JComboBox cmbDepositBank) {
		this.cmbDepositBank = cmbDepositBank;
	}

	public JLabel getLblCheckAccount() {
		return lblCheckAccount;
	}

	public void setLblCheckAccount(JLabel lblCheckAccount) {
		this.lblCheckAccount = lblCheckAccount;
	}

	public JComboBox getCmbCheckAccount() {
		return cmbCheckAccount;
	}

	public void setCmbCheckAccount(JComboBox cmbCheckAccount) {
		this.cmbCheckAccount = cmbCheckAccount;
	}

	public JLabel getLblDepositAccount() {
		return lblDepositAccount;
	}

	public void setLblDepositAccount(JLabel lblDepositAccount) {
		this.lblDepositAccount = lblDepositAccount;
	}

	public JComboBox getCmbDepositAccount() {
		return cmbDepositAccount;
	}

	public void setCmbDepositAccount(JComboBox cmbDepositAccount) {
		this.cmbDepositAccount = cmbDepositAccount;
	}

	public JLabel getLblCheckNumber() {
		return lblCheckNumber;
	}

	public void setLblCheckNumber(JLabel lblCheckNumber) {
		this.lblCheckNumber = lblCheckNumber;
	}

	public JFormattedTextField getTxtCheckNumber() {
		return txtCheckNumber;
	}

	public void setTxtCheckNumber(JFormattedTextField txtCheckNumber) {
		this.txtCheckNumber = txtCheckNumber;
	}

	public JPanel getJpRetention() {
		return jpRetention;
	}

	public void setJpRetention(JPanel jpRetention) {
		this.jpRetention = jpRetention;
	}

	public JLabel getLblRetentionAuthorization() {
		return lblRetentionAuthorization;
	}

	public void setLblRetentionAuthorization(JLabel lblRetentionAuthorization) {
		this.lblRetentionAuthorization = lblRetentionAuthorization;
	}

	public JLabel getLblRetentionNumber() {
		return lblRetentionNumber;
	}

	public void setLblRetentionNumber(JLabel lblRetentionNumber) {
		this.lblRetentionNumber = lblRetentionNumber;
	}

	public JFormattedTextField getTxtRetentionNumber() {
		return txtRetentionNumber;
	}

	public void setTxtRetentionNumber(JFormattedTextField txtRetentionNumber) {
		this.txtRetentionNumber = txtRetentionNumber;
	}

	public JFormattedTextField getTxtRetentionAuthorization() {
		return txtRetentionAuthorization;
	}

	public void setTxtRetentionAuthorization(
			JFormattedTextField txtRetentionAuthorization) {
		this.txtRetentionAuthorization = txtRetentionAuthorization;
	}

	public JLabel getLblRetentionPercentage() {
		return lblRetentionPercentage;
	}

	public void setLblRetentionPercentage(JLabel lblRetentionPercentage) {
		this.lblRetentionPercentage = lblRetentionPercentage;
	}

	public JComboBox getCmbRetentionPercentage() {
		return cmbRetentionPercentage;
	}

	public void setCmbRetentionPercentage(JComboBox cmbRetentionPercentage) {
		this.cmbRetentionPercentage = cmbRetentionPercentage;
	}

	public JPanel getJpDebit() {
		return jpDebit;
	}

	public void setJpDebit(JPanel jpDebit) {
		this.jpDebit = jpDebit;
	}

	public JLabel getLblDebitBank() {
		return lblDebitBank;
	}

	public void setLblDebitBank(JLabel lblDebitBank) {
		this.lblDebitBank = lblDebitBank;
	}

	public JComboBox getCmbDebitBank() {
		return cmbDebitBank;
	}

	public void setCmbDebitBank(JComboBox cmbDebitBank) {
		this.cmbDebitBank = cmbDebitBank;
	}

	public JLabel getLblDebitAccount() {
		return lblDebitAccount;
	}

	public void setLblDebitAccount(JLabel lblDebitAccount) {
		this.lblDebitAccount = lblDebitAccount;
	}

	public JComboBox getCmbDebitAccount() {
		return cmbDebitAccount;
	}

	public void setCmbDebitAccount(JComboBox cmbDebitAccount) {
		this.cmbDebitAccount = cmbDebitAccount;
	}

	public JLabel getLblDebitReference() {
		return lblDebitReference;
	}

	public void setLblDebitReference(JLabel lblDebitReference) {
		this.lblDebitReference = lblDebitReference;
	}

	public JTextField getTxtDebitReference() {
		return txtDebitReference;
	}

	public void setTxtDebitReference(JTextField txtDebitReference) {
		this.txtDebitReference = txtDebitReference;
	}

	public JPanel getJpTransfer() {
		return jpTransfer;
	}

	public void setJpTransfer(JPanel jpTransfer) {
		this.jpTransfer = jpTransfer;
	}

	public JLabel getLblSourceBank() {
		return lblSourceBank;
	}

	public void setLblSourceBank(JLabel lblSourceBank) {
		this.lblSourceBank = lblSourceBank;
	}

	public JComboBox getCmbSourceBank() {
		return cmbSourceBank;
	}

	public void setCmbSourceBank(JComboBox cmbSourceBank) {
		this.cmbSourceBank = cmbSourceBank;
	}

	public JLabel getLblTargetBank() {
		return lblTargetBank;
	}

	public void setLblTargetBank(JLabel lblTargetBank) {
		this.lblTargetBank = lblTargetBank;
	}

	public JComboBox getCmbTargetBank() {
		return cmbTargetBank;
	}

	public void setCmbTargetBank(JComboBox cmbTargetBank) {
		this.cmbTargetBank = cmbTargetBank;
	}

	public JLabel getLblSourceAccount() {
		return lblSourceAccount;
	}

	public void setLblSourceAccount(JLabel lblSourceAccount) {
		this.lblSourceAccount = lblSourceAccount;
	}

	public JComboBox getCmbSourceAccount() {
		return cmbSourceAccount;
	}

	public void setCmbSourceAccount(JComboBox cmbSourceAccount) {
		this.cmbSourceAccount = cmbSourceAccount;
	}

	public JLabel getLblTargetAccount() {
		return lblTargetAccount;
	}

	public void setLblTargetAccount(JLabel lblTargetAccount) {
		this.lblTargetAccount = lblTargetAccount;
	}

	public JComboBox getCmbTargetAccount() {
		return cmbTargetAccount;
	}

	public void setCmbTargetAccount(JComboBox cmbTargetAccount) {
		this.cmbTargetAccount = cmbTargetAccount;
	}

	public JPanel getJpCreditCard() {
		return jpCreditCard;
	}

	public void setJpCreditCard(JPanel jpCreditCard) {
		this.jpCreditCard = jpCreditCard;
	}

	public JLabel getLblCreditCardBank() {
		return lblCreditCardBank;
	}

	public void setLblCreditCardBank(JLabel lblCreditCardBank) {
		this.lblCreditCardBank = lblCreditCardBank;
	}

	public JComboBox getCmbCreditCardBank() {
		return cmbCreditCardBank;
	}

	public void setCmbCreditCardBank(JComboBox cmbCreditCardBank) {
		this.cmbCreditCardBank = cmbCreditCardBank;
	}

	public JLabel getLblCreditCard() {
		return lblCreditCard;
	}

	public void setLblCreditCard(JLabel lblCreditCard) {
		this.lblCreditCard = lblCreditCard;
	}

	public JComboBox getCmbCreditCard() {
		return cmbCreditCard;
	}

	public void setCmbCreditCard(JComboBox cmbCreditCard) {
		this.cmbCreditCard = cmbCreditCard;
	}

	public JLabel getLblVoucherReference() {
		return lblVoucherReference;
	}

	public void setLblVoucherReference(JLabel lblVoucherReference) {
		this.lblVoucherReference = lblVoucherReference;
	}

	public JTextField getTxtVoucherReference() {
		return txtVoucherReference;
	}

	public void setTxtVoucherReference(JTextField txtVoucherReference) {
		this.txtVoucherReference = txtVoucherReference;
	}

	public JPanel getJpElectronicPayment() {
		return jpElectronicPayment;
	}

	public void setJpElectronicPayment(JPanel jpElectronicPayment) {
		this.jpElectronicPayment = jpElectronicPayment;
	}

	public JLabel getLblElectronicPaymentReference() {
		return lblElectronicPaymentReference;
	}

	public void setLblElectronicPaymentReference(
			JLabel lblElectronicPaymentReference) {
		this.lblElectronicPaymentReference = lblElectronicPaymentReference;
	}

	public JTextField getTxtElectronicPaymentReference() {
		return txtElectronicPaymentReference;
	}

	public void setTxtElectronicPaymentReference(
			JTextField txtElectronicPaymentReference) {
		this.txtElectronicPaymentReference = txtElectronicPaymentReference;
	}

	public JPanel getSpButtonBar() {
		return spButtonBar;
	}

	public void setSpButtonBar(JPanel spButtonBar) {
		this.spButtonBar = spButtonBar;
	}

	public JButton getBtnHit() {
		return btnHit;
	}

	public void setBtnHit(JButton btnHit) {
		this.btnHit = btnHit;
	}

	public JButton getBtnAddDetail() {
		return btnAddDetail;
	}

	public void setBtnAddDetail(JButton btnAddDetail) {
		this.btnAddDetail = btnAddDetail;
	}

	public JButton getBtnUpdateDetail() {
		return btnUpdateDetail;
	}

	public void setBtnUpdateDetail(JButton btnUpdateDetail) {
		this.btnUpdateDetail = btnUpdateDetail;
	}

	public JButton getBtnDeleteDetail() {
		return btnDeleteDetail;
	}

	public void setBtnDeleteDetail(JButton btnDeleteDetail) {
		this.btnDeleteDetail = btnDeleteDetail;
	}

	public JScrollPane getSpTblWalletDetail() {
		return spTblWalletDetail;
	}

	public void setSpTblWalletDetail(JScrollPane spTblWalletDetail) {
		this.spTblWalletDetail = spTblWalletDetail;
	}

	public JTable getTblWalletDetail() {
		return tblWalletDetail;
	}

	public void setTblWalletDetail(JTable tblWalletDetail) {
		this.tblWalletDetail = tblWalletDetail;
	}

	public JFormattedTextField getTxtWalletId() {
		return txtWalletId;
	}

	public void setTxtWalletId(JFormattedTextField txtWalletId) {
		this.txtWalletId = txtWalletId;
	}

	public JFormattedTextField getTxtWalletDetailId() {
		return txtWalletDetailId;
	}

	public void setTxtWalletDetailId(JFormattedTextField txtWalletDetailId) {
		this.txtWalletDetailId = txtWalletDetailId;
	}

	public JButton getBtnAddBusinessOperator() {
		return btnAddBusinessOperator;
	}

	public void setBtnAddBusinessOperator(JButton btnAddBusinessOperator) {
		this.btnAddBusinessOperator = btnAddBusinessOperator;
	}

	public JButton getBtnAddCheckAccount() {
		return btnAddCheckAccount;
	}

	public void setBtnAddCheckAccount(JButton btnAddCheckAccount) {
		this.btnAddCheckAccount = btnAddCheckAccount;
	}

	public JButton getBtnAddDepositAccount() {
		return btnAddDepositAccount;
	}

	public void setBtnAddDepositAccount(JButton btnAddDepositAccount) {
		this.btnAddDepositAccount = btnAddDepositAccount;
	}

	public JButton getBtnAddDebitAccount() {
		return btnAddDebitAccount;
	}

	public void setBtnAddDebitAccount(JButton btnAddDebitAccount) {
		this.btnAddDebitAccount = btnAddDebitAccount;
	}

	public JButton getBtnAddSourceAccount() {
		return btnAddSourceAccount;
	}

	public void setBtnAddSourceAccount(JButton btnAddSourceAccount) {
		this.btnAddSourceAccount = btnAddSourceAccount;
	}

	public JButton getBtnAddTargetAccount() {
		return btnAddTargetAccount;
	}

	public void setBtnAddTargetAccount(JButton btnAddTargetAccount) {
		this.btnAddTargetAccount = btnAddTargetAccount;
	}

	public JButton getBtnAddCreditCard() {
		return btnAddCreditCard;
	}

	public void setBtnAddCreditCard(JButton btnAddCreditCard) {
		this.btnAddCreditCard = btnAddCreditCard;
	}

	public JLabel getLblStatusMessage() {
		return lblStatusMessage;
	}

	public void setLblStatusMessage(JLabel lblStatusMessage) {
		this.lblStatusMessage = lblStatusMessage;
	}

	public JLabel getLblViewWalletAccountingEntry() {
		return lblViewWalletAccountingEntry;
	}

	public void setLblViewWalletAccountingEntry(JLabel lblViewWalletAccountingEntry) {
		this.lblViewWalletAccountingEntry = lblViewWalletAccountingEntry;
	}

	public JFormattedTextField getTxtReferenceId() {
		return txtReferenceId;
	}

	public void setTxtReferenceId(JFormattedTextField txtReferenceId) {
		this.txtReferenceId = txtReferenceId;
	}
}
