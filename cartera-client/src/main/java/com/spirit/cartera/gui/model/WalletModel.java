package com.spirit.cartera.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.entity.CarteraRelacionadaEJB;
import com.spirit.cartera.gui.controller.DeprecatedPaymentReceiptHandler;
import com.spirit.cartera.gui.controller.JtpWalletDetailTabEnum;
import com.spirit.cartera.gui.controller.WalletConstants;
import com.spirit.cartera.gui.controller.WalletErrorMessagesEnum;
import com.spirit.cartera.gui.controller.WalletKeyboardFocusManager;
import com.spirit.cartera.gui.controller.WalletValidationHandler;
import com.spirit.cartera.gui.criteria.CarteraCriteria;
import com.spirit.cartera.gui.panel.JDApplyWalletTransaction;
import com.spirit.cartera.gui.panel.JPWallet;
import com.spirit.cartera.handler.CrossingWalletDetailData;
import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.cartera.handler.WalletReceiptRowData;
import com.spirit.client.HotKeyComponent;
import com.spirit.client.MainFrameModel;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.client.model.SpiritMode;
import com.spirit.client.model.SpiritModel;
import com.spirit.client.model.SpiritResourceManager;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.gui.criteria.AsientoCriteria;
import com.spirit.contabilidad.gui.model.AsientoModel;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.crm.gui.criteria.ClienteOficinaCriteria;
import com.spirit.crm.gui.model.OperadoresNegocioModel;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.ModuloIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoClienteIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.gui.controller.JDPopupFinderModel;
import com.spirit.general.gui.controller.PanelHandler;
import com.spirit.general.gui.panel.JDCheque;
import com.spirit.pos.entity.TarjetaCreditoIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.util.ComboBoxComponent;
import com.spirit.util.DeepCopy;
import com.spirit.util.NumberTextField;
import com.spirit.util.TableCellRendererHorizontalCenterAlignment;
import com.spirit.util.TableCellRendererHorizontalRightAlignment;
import com.spirit.util.TextChecker;
import com.spirit.util.Utilitarios;

public class WalletModel extends JPWallet {
	private static final long serialVersionUID = -3479197604590317904L;
	/* Global entities */
	private WalletModel walletModel;
	private ClienteIf businessOperator;
	private ClienteOficinaIf businessOperatorOffice;
	private Vector<WalletDetailData> walletDetailDataVector;
	private Vector<WalletDetailData> deletedWalletDetailDataVector;
	private Vector<CrossingWalletDetailData> crossingWalletDetailsVector;
	private int maximumSequentialNumber;
	private int selectedWalletDetailIndex;
	private int selectedRowTblWalletDetail;
	private JDPopupFinderModel popupFinder;
	private int walletValidationPolicy;
	private boolean updatedOriginalReceipt;
	private boolean findAction;
	private boolean deleteAction;
	private boolean activatedRetrocompatibility;
	private boolean voidTransaction;

	public WalletModel() {
		//setModelLocale();
		initKeyListeners();
		showSaveMode();
		initListeners();
		WalletKeyboardFocusManager.initKeyboardFocusManager();
		new HotKeyComponent(this);
		setWalletModel(this);
	}
	
	/*private void localeProof() {
		Locale[] locales = NumberFormat.getAvailableLocales();
		 double myNumber = -1234.56;
		 NumberFormat form;
		 for (int j=0; j<4; ++j) {
		     System.out.println("FORMAT");
		     for (int i = 0; i < locales.length; ++i) {
		         if (locales[i].getCountry().length() == 0) {
		            continue; // Skip language-only locales
		         }
		         System.out.print(locales[i].getDisplayName());
		         switch (j) {
		         case 0:
		             form = NumberFormat.getInstance(locales[i]); break;
		         case 1:
		             form = NumberFormat.getIntegerInstance(locales[i]); break;
		         case 2:
		             form = NumberFormat.getCurrencyInstance(locales[i]); break;
		         default:
		             form = NumberFormat.getPercentInstance(locales[i]); break;
		         }
		         if (form instanceof DecimalFormat) {
		             System.out.print(": " + ((DecimalFormat) form).toPattern());
		         }
		         System.out.print(" -> " + form.format(myNumber));
		         try {
		             System.out.println(" -> " + form.parse(form.format(myNumber)));
		         } catch (ParseException e) {}
		     }
		 }
	}*/
	
	/*private void setModelLocale() {
		String[] defaultLocale = Parametros.getDefaultLocale().split(SpiritConstants.getSplitCharacter());
		Locale locale = Locale.getDefault();
		locale = new Locale(defaultLocale[0], defaultLocale[1]); 
		Locale.setDefault(locale);	
	}*/

	public void showSaveMode() {
		setSaveMode();
		initMode();
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash()));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar", SpiritAlert.ERROR);
		}
	}

	public void showUpdateMode() {
		setUpdateMode();
		initMode();
		getLblViewWalletAccountingEntry().setVisible(true);
	}

	public void showFindMode() {
		setFindMode();
		initMode();
		try {
			manageJtpTransactionDetail();
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el modo de búsqueda", SpiritAlert.ERROR);
		}
	}
	
	public boolean usingDeleteHandler() {
		return false;
	}
	
	private void initMode() {
		initModelComponents();
		setBackgroundColorComponents();
		switchJtpWallet(WalletConstants.getMasterTab());
		this.validate();
		this.refresh();
	}
	
	private void setBackgroundColorComponents() {
		/* By default, backgroundColor value will be saveUpdateColor. This parameter only changes its value on FIND_MODE. */
		Color backgroundColor = Parametros.saveUpdateColor;
		if (this.getMode() == SpiritMode.FIND_MODE)
			backgroundColor = Parametros.findColor;
		getTxtCode().setBackground(backgroundColor);
		getTxtEmissionDate().setBackground(backgroundColor);
		getCmbDocumentType().setBackground(backgroundColor);
		getTxtComment().setBackground(backgroundColor);
		getTxtLegalName().setBackground(backgroundColor);
		getCmbDocument().setBackground(backgroundColor);
	}

	public void clean() {
		cleanMasterTab();
		cleanDetailTab(true, true);
	}
	
	public void cleanMasterTab() {
		cleanTextFields(WalletConstants.getMasterTab());
		cleanComboBoxes(WalletConstants.getMasterTab());
		getCbAnulled().setSelected(false);
	}
	
	public void cleanDetailTab(boolean cleanComboBoxes, boolean cleanDetailTable) {
		cleanTextFields(WalletConstants.getDetailTab());
		if (cleanComboBoxes)
			cleanComboBoxes(WalletConstants.getDetailTab());
		if (cleanDetailTable)
			cleanTables();
	}

	public void delete() {
		if (this.getMode() == SpiritMode.UPDATE_MODE) {
			int option = JOptionPane.NO_OPTION;
			if (!isVoidTransaction())
				option = JOptionPane.showOptionDialog(null, "¿Desea anular la transacción?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
			else
				option = JOptionPane.YES_OPTION;
			if (option == JOptionPane.YES_OPTION) {
				setDeleteAction(true);
				WalletData walletData = setWalletData();
				if (!walletData.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1))) {
					Vector<WalletDetailData> walletDetailDataVector = getWalletDetailDataVector();
					if (validateRetrocompatibleFields(walletDetailDataVector)) {
						getCbAnulled().setSelected(true);
						walletData.setStatus(getCbAnulled().isSelected()?WalletConstants.getNullifyStatus().substring(0,1):WalletConstants.getNormalStatus().substring(0,1));
						try {
							SessionServiceLocator.getCarteraSessionService().nullifyWallet(walletData, walletDetailDataVector);
							SpiritAlert.createAlert("Anulación realizada con éxito", SpiritAlert.INFORMATION);
							if (!walletData.getDocumentType().getEgreso().equals(SpiritConstants.getOptionYes().substring(0,1)))
								printReport(walletData, walletDetailDataVector, true);
							else {
								if (walletDetailDataVector.size() > 1) {
									option = JOptionPane.showOptionDialog(null, "Está intentando imprimir un comprobante multitransacción en un formato obsoleto.\nSe recomienda imprimir la versión actual de este comprobante.\n¿Desea imprimir la versión actual del comprobante?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionYes());
									if (option == JOptionPane.YES_OPTION) {
										printReport(walletData, walletDetailDataVector, true);
									} else {
										SpiritAlert.createAlert("Se generará una versión obsoleta del comprobante, el resultado es impredecible.", SpiritAlert.WARNING);
										DeprecatedPaymentReceiptHandler dph = new DeprecatedPaymentReceiptHandler();
										dph.printReport(walletData, walletData.getDocumentType().getAnticipo().equals(SpiritConstants.getOptionYes().substring(0,1)), true);
									}
								} else {
									DeprecatedPaymentReceiptHandler dph = new DeprecatedPaymentReceiptHandler();
									dph.printReport(walletData, walletData.getDocumentType().getAnticipo().equals(SpiritConstants.getOptionYes().substring(0,1)), true);
								}
							}
							duplicate();
						} catch (GenericBusinessException e) {
							e.printStackTrace();
							SpiritAlert.createAlert("Se ha producido un error al anular la transacción", SpiritAlert.ERROR);	
						} catch (Exception e) {
							e.printStackTrace();
							SpiritAlert.createAlert("Se ha producido un error al anular la transacción", SpiritAlert.ERROR);
						}
					}
				} else {
					try {
						SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.NOT_VALID_ACTION_FOR_NULLIFIED_TRANSACTIONS.getMessage()), SpiritAlert.WARNING);
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error al anular la transacción", SpiritAlert.ERROR);
					}
				}
				setDeleteAction(false);
			}
		} else {
			SpiritAlert.createAlert("Comprobante aún no emitido. Los comprobantes no emitidos no requieren anulación.", SpiritAlert.INFORMATION);
		}
	}

	@SuppressWarnings("unchecked")
	public void duplicate() {
		if (this.getMode() == SpiritMode.UPDATE_MODE) {
			int option = JOptionPane.showOptionDialog(null, "¿Desea utilizar los datos de este comprobante para emitir uno nuevo?\n", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
			if (option == JOptionPane.YES_OPTION) {
				try {
					WalletData duplicatedWalletData = (WalletData) DeepCopy.copy(setWalletData());
					Vector<WalletDetailData> duplicatedWalletDetailDataVector = (Vector<WalletDetailData>) DeepCopy.copy(getWalletDetailDataVector());
					showSaveMode();
					loadDuplicatedWalletData(duplicatedWalletData);
					loadDuplicatedWalletDetailDataVector(duplicatedWalletDetailDataVector);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al duplicar el comprobante", SpiritAlert.ERROR);
				}
			} else if (isDeleteAction())
				showSaveMode();
		} else {
			SpiritAlert.createAlert("Comprobante aún no emitido. Los comprobantes no emitidos no se pueden duplicar.", SpiritAlert.INFORMATION);
		}
	}

	public void find() {
		setCursorEspera();
		Map<String, Object> queryMap = buildQueryMap();
		try {
			ModuloIf walletModule = WalletConstants.getWalletModule();
			Long businessOperatorId = (getBusinessOperatorOffice() != null)?getBusinessOperatorOffice().getClienteId():0L;
			int sizeList = SessionServiceLocator.getCarteraSessionService().getCarteraListSize(queryMap, businessOperatorId, walletModule.getId(), Parametros.getIdEmpresa());
			if (sizeList > 0) {
				CarteraCriteria walletCriteria = new CarteraCriteria(businessOperatorId, walletModule.getId(), Parametros.getIdEmpresa());
				walletCriteria.setResultListSize(sizeList);
				walletCriteria.setQueryBuilded(queryMap);
				setPopupFinder(new JDPopupFinderModel(Parametros.getMainFrame(), walletCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, walletCriteria.getAnchoColumnasVector(), walletCriteria.getAlineacionColumnasMap()));
				if (getPopupFinder().getElementoSeleccionado() != null) {
					showUpdateMode();
					setFindAction(true);
					loadData();
					setFindAction(false);
					getCmbDocument().setSelectedIndex(0);
				}
			} else {
				SpiritAlert.createAlert("No se encontraron registros", SpiritAlert.INFORMATION);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al consultar los datos", SpiritAlert.ERROR);
		}
		setCursorNormal();
	}
	
	private void loadData() {
		try {
			CarteraIf wallet = loadWallet();
			loadWalletDetailList(wallet);
			setUpdatedOriginalReceipt(false);
			if (getCbAnulled().isSelected()) {
				getBtnAddDetail().setEnabled(false);
				getBtnUpdateDetail().setEnabled(false);
				getBtnDeleteDetail().setEnabled(false);
				getBtnHit().setEnabled(false);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar los datos", SpiritAlert.ERROR);
		}
	}
	
	private CarteraIf loadWallet() throws GenericBusinessException {
		CarteraIf wallet = (CarteraIf) getPopupFinder().getElementoSeleccionado();
		getTxtWalletId().setValue(wallet.getId());
		if (wallet.getReferenciaId() != SpiritConstants.getNullValue())
			getTxtReferenceId().setValue(wallet.getReferenciaId());
		if (wallet.getTipo().equals(SpiritConstants.getCustomerWalletType().substring(0,1)))
			getRbCustomer().setSelected(true);
		else
			getRbProvider().setSelected(true);
		getTxtCode().setText(wallet.getCodigo());
		OficinaIf office = SessionServiceLocator.getOficinaSessionService().getOficina(wallet.getOficinaId());
		getTxtOffice().setText(office.getNombre());
		getTxtEmissionDate().setValue(Utilitarios.fromTimestampToUtilDate(wallet.getFechaEmision()));
		if (wallet.getFechaCreacion() != null)
			getTxtCreationDate().setValue(Utilitarios.fromTimestampToUtilDate(wallet.getFechaCreacion()));
		if (wallet.getFechaUltimaActualizacion() != null)
			getTxtLastUpdateDate().setValue(Utilitarios.fromTimestampToUtilDate(wallet.getFechaUltimaActualizacion()));
		getCmbDocumentType().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumentType(), wallet.getTipodocumentoId()));
		getCmbCurrency().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCurrency(), wallet.getMonedaId()));
		if (wallet.getEstado().equals(WalletConstants.getNullifyStatus().substring(0, 1)))
			getCbAnulled().setSelected(true);
		getTxtComment().setValue(wallet.getComentario());
		ClienteOficinaIf businessOperatorOffice = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(wallet.getClienteoficinaId());
		setBusinessOperatorOffice(businessOperatorOffice);
		setBusinessOperatorData();
		getTxtTotal().setValue(SpiritConstants.getZeroValue());
		getTxtBalance().setValue(SpiritConstants.getZeroValue());
		if (wallet.getActivarRetrocompatibilidad() == SpiritConstants.getNullValue() || wallet.getActivarRetrocompatibilidad().equals(SpiritConstants.getOptionYes().substring(0,1)))
			setActivatedRetrocompatibility(true);
		else
			setActivatedRetrocompatibility(false);
		return wallet;
	}
	
	private void loadDuplicatedWalletData(WalletData duplicatedWalletData) throws GenericBusinessException {
		getTxtWalletId().setValue(SpiritConstants.getNullValue());
		if (duplicatedWalletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1)))
			getRbCustomer().setSelected(true);
		else
			getRbProvider().setSelected(true);
		getTxtCode().setText(SpiritConstants.getEmptyCharacter());
		getTxtEmissionDate().setValue(duplicatedWalletData.getEmissionDate());
		getCmbDocumentType().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocumentType(), duplicatedWalletData.getDocumentType().getId()));
		getCmbCurrency().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCurrency(), duplicatedWalletData.getCurrency().getId()));
		getCbAnulled().setSelected(false);
		getTxtComment().setValue(duplicatedWalletData.getComment());
		setBusinessOperatorOffice(duplicatedWalletData.getBusinessOperatorOffice());
		setBusinessOperatorData();
		getTxtTotal().setValue(SpiritConstants.getZeroValue());
		getTxtBalance().setValue(SpiritConstants.getZeroValue());
	}
	
	private void loadDuplicatedWalletDetailDataVector(Vector<WalletDetailData> duplicatedWalletDetailDataVector) {
		for (int i=0; i<duplicatedWalletDetailDataVector.size(); i++) {
			WalletDetailData duplicatedWalletDetail = duplicatedWalletDetailDataVector.get(i);
			duplicatedWalletDetail.setWalletDetailId(SpiritConstants.getNullValue());
			duplicatedWalletDetail.setBalance(duplicatedWalletDetail.getValue());
			getWalletDetailDataVector().add(duplicatedWalletDetail);
			addRowToTblWalletDetail(duplicatedWalletDetail);
			calculateTotal();
			calculateBalance();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadWalletDetailList(CarteraIf wallet) throws GenericBusinessException {
		if (wallet.getActivarRetrocompatibilidad() == SpiritConstants.getNullValue() || wallet.getActivarRetrocompatibilidad().equals(SpiritConstants.getOptionYes().substring(0,1)))
			setActivatedRetrocompatibility(true);
		else
			setActivatedRetrocompatibility(false);
		List<CarteraDetalleIf> walletDetailList = (ArrayList<CarteraDetalleIf>) SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(wallet.getId());
		Collections.sort(walletDetailList, getWalletDetailComparator());
		Iterator<CarteraDetalleIf> it = walletDetailList.iterator();
		while (it.hasNext()) {
			CarteraDetalleIf walletDetail = it.next();
			getTxtWalletDetailId().setValue(walletDetail.getId());
			getCmbDocument().setSelectedIndex(walletDetail.getDocumentoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDocument(), walletDetail.getDocumentoId()):BigDecimal.ONE.negate().intValue());
			getTxtDetailValue().setValue(walletDetail.getValor());
			getTxtDetailBalance().setValue(walletDetail.getSaldo());
			int walletValidationPolicy = getWalletValidationPolicy();
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash()))
				loadTransactionCash(walletDetail);
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck()))
				loadTransactionCheck(walletDetail);
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention()))
				loadTransactionRetention(walletDetail);
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit()))
				loadTransactionDebit(walletDetail);
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer()))
				loadTransactionTransfer(walletDetail);
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard()))
				loadTransactionCreditCard(walletDetail);
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment()))
				loadTransactionElectronicPayment(walletDetail);
			if (walletDetail.getSecuencial() != SpiritConstants.getNullValue())
				setMaximumSequentialNumber(walletDetail.getSecuencial());
			else  {
				setMaximumSequentialNumber(getMaximumSequentialNumber());
			}
			addDetail();
			int nextSequentialNumber = getMaximumSequentialNumber() + 1;
			setMaximumSequentialNumber(nextSequentialNumber);
		}
	}
	
	private Comparator<CarteraDetalleIf> walletDetailComparator = new Comparator<CarteraDetalleIf>() {
		public int compare(CarteraDetalleIf walletDetail1, CarteraDetalleIf walletDetail2) {
			if (walletDetail1.getSecuencial() != null && walletDetail2.getSecuencial() != null)
				return walletDetail1.getSecuencial().compareTo(walletDetail2.getSecuencial());
			else
				return SpiritConstants.getZeroValue().intValue();
		}
	};
	
	private Comparator<WalletReceiptRowData> walletReceiptRowDataComparator = new Comparator<WalletReceiptRowData>() {
		public int compare(WalletReceiptRowData walletReceiptRowData1, WalletReceiptRowData walletReceiptRowData2) {
			return walletReceiptRowData1.getSequentialNumber().compareTo(walletReceiptRowData2.getSequentialNumber());
		}
	};
	
	private void loadTransactionCash(CarteraDetalleIf walletDetail) {
		// TODO:
	}
	
	private void loadTransactionCheck(CarteraDetalleIf walletDetail) {
		getCmbCheckBank().setSelectedIndex(walletDetail.getChequeBancoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCheckBank(), walletDetail.getChequeBancoId()):BigDecimal.ONE.negate().intValue());
		getCmbCheckAccount().setSelectedIndex(walletDetail.getChequeCuentaBancariaId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCheckAccount(), walletDetail.getChequeCuentaBancariaId()):BigDecimal.ONE.negate().intValue());
		getTxtCheckNumber().setValue(walletDetail.getChequeNumero() != null?walletDetail.getChequeNumero():SpiritConstants.getEmptyCharacter());
		getCmbDepositBank().setSelectedIndex(walletDetail.getDepositoBancoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDepositBank(), walletDetail.getDepositoBancoId()):BigDecimal.ONE.negate().intValue());
		getCmbDepositAccount().setSelectedIndex(walletDetail.getDepositoCuentaBancariaId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDepositAccount(), walletDetail.getDepositoCuentaBancariaId()):BigDecimal.ONE.negate().intValue());
	}
	
	private void loadTransactionRetention(CarteraDetalleIf walletDetail) {
		getTxtRetentionNumber().setValue(walletDetail.getRetencionNumero() != null?walletDetail.getRetencionNumero():SpiritConstants.getEmptyCharacter());
		getTxtRetentionAuthorization().setValue(walletDetail.getRetencionAutorizacion() != null?walletDetail.getRetencionAutorizacion():SpiritConstants.getEmptyCharacter());
		DocumentoIf document = (DocumentoIf) getCmbDocument().getSelectedItem();
		if (getRbCustomer().isSelected()) {
			getCmbRetentionPercentage().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbRetentionPercentage(), walletDetail.getSriClienteRetencionId() != SpiritConstants.getNullValue()?walletDetail.getSriClienteRetencionId():BigDecimal.ONE.negate().intValue()));
		} else if (getRbProvider().isSelected()) {
			if (document.getRetencionRenta() != null && document.getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1)))
				getCmbRetentionPercentage().setSelectedItem(walletDetail.getValorRetencionRenta() != null?walletDetail.getValorRetencionRenta():BigDecimal.ZERO);
			else if (document.getRetencionIva() != null && document.getRetencionIva().equals(SpiritConstants.getOptionYes().substring(0,1)))
				getCmbRetentionPercentage().setSelectedItem(walletDetail.getValorRetencionRenta() != null?walletDetail.getValorRetencionIva():BigDecimal.ZERO);
		}
	}
	
	private void loadTransactionDebit(CarteraDetalleIf walletDetail) {
		getCmbDebitBank().setSelectedIndex(walletDetail.getDebitoBancoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDebitBank(), walletDetail.getDebitoBancoId()):BigDecimal.ONE.negate().intValue());
		getCmbDebitAccount().setSelectedIndex(walletDetail.getDebitoCuentaBancariaId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDebitAccount(), walletDetail.getDebitoCuentaBancariaId()):BigDecimal.ONE.negate().intValue());
		getTxtDebitReference().setText(walletDetail.getDebitoReferencia() != null?walletDetail.getDebitoReferencia():SpiritConstants.getEmptyCharacter());
	}
	
	private void loadTransactionTransfer(CarteraDetalleIf walletDetail) {
		getCmbSourceBank().setSelectedIndex(walletDetail.getTransferenciaBancoOrigenId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbSourceBank(), walletDetail.getTransferenciaBancoOrigenId()):BigDecimal.ONE.negate().intValue());
		getCmbSourceAccount().setSelectedIndex(walletDetail.getTransferenciaCuentaOrigenId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbSourceAccount(), walletDetail.getTransferenciaCuentaOrigenId()):BigDecimal.ONE.negate().intValue());
		getCmbTargetBank().setSelectedIndex(walletDetail.getTransferenciaBancoDestinoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbTargetBank(), walletDetail.getTransferenciaBancoDestinoId()):BigDecimal.ONE.negate().intValue());
		getCmbTargetAccount().setSelectedIndex(walletDetail.getTransferenciaCuentaDestinoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbTargetAccount(), walletDetail.getTransferenciaCuentaDestinoId()):BigDecimal.ONE.negate().intValue());
	}
	
	private void loadTransactionCreditCard(CarteraDetalleIf walletDetail) {
		getCmbCreditCardBank().setSelectedIndex(walletDetail.getTarjetaCreditoBancoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCreditCardBank(), walletDetail.getTarjetaCreditoBancoId()):BigDecimal.ONE.negate().intValue());
		getCmbCreditCard().setSelectedIndex(walletDetail.getTarjetaCreditoId() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCreditCard(), walletDetail.getTarjetaCreditoId()):BigDecimal.ONE.negate().intValue());
		getTxtVoucherReference().setText(walletDetail.getVoucherReferencia() != null?walletDetail.getVoucherReferencia():SpiritConstants.getEmptyCharacter());
	}
	
	private void loadTransactionElectronicPayment(CarteraDetalleIf walletDetail) {
		getTxtElectronicPaymentReference().setText(walletDetail.getPagoElectronicoReferencia() != null?walletDetail.getPagoElectronicoReferencia():SpiritConstants.getEmptyCharacter());
	}
	
	private Map<String, Object> buildQueryMap() {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("codigo", SpiritConstants.getWildcardCharacter() + getTxtCode().getText().trim() + SpiritConstants.getWildcardCharacter());
		if (getRbCustomer().isSelected() || getRbProvider().isSelected()) {
			String walletType = (getRbCustomer().isSelected())?SpiritConstants.getCustomerWalletType().substring(0,1):SpiritConstants.getProviderWalletType().substring(0,1);
			queryMap.put("tipo", walletType);
		}
		if (getCmbDocumentType().getSelectedItem() != SpiritConstants.getNullValue() && !getCmbDocumentType().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))
			queryMap.put("tipodocumentoId", ((TipoDocumentoIf) getCmbDocumentType().getSelectedItem()).getId());
		if (getCmbDocument().getSelectedItem() != SpiritConstants.getNullValue() && !getCmbDocument().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))
			queryMap.put("documentoId",((DocumentoIf) getCmbDocument().getSelectedItem()).getId());				
		if (getBusinessOperatorOffice() != SpiritConstants.getNullValue())
			queryMap.put("clienteoficinaId", getBusinessOperatorOffice().getId());
		if (getTxtEmissionDate().getValue() != null)
			queryMap.put("fechaEmision", Utilitarios.fromUtilDateToSqlDate((java.util.Date) getTxtEmissionDate().getValue()));
		if (getCbAnulled().isSelected())
			queryMap.put("estado", WalletConstants.getNullifyStatus().substring(0,1));
		//queryMap.put("oficinaId", Parametros.getIdOficina());
		return queryMap;
	}

	public void report() {
		if (this.getMode() == SpiritMode.UPDATE_MODE)
			try {
				WalletData walletData = setWalletData();
				boolean anulledReceipt = (walletData.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)))?true:false;
				if (!walletData.getDocumentType().getEgreso().equals(SpiritConstants.getOptionYes().substring(0,1)))
					printReport(walletData, getWalletDetailDataVector(), anulledReceipt);
				else {
					if (walletDetailDataVector.size() > 1) {
						int option = JOptionPane.showOptionDialog(null, "Está intentando imprimir un comprobante multitransacción en un formato obsoleto.\nSe recomienda imprimir la versión actual de este comprobante.\n¿Desea imprimir la versión actual del comprobante?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionYes());
						if (option == JOptionPane.YES_OPTION) {
							printReport(walletData, getWalletDetailDataVector(), anulledReceipt);
						} else {
							SpiritAlert.createAlert("Se generará una versión obsoleta del comprobante, el resultado es impredecible.", SpiritAlert.WARNING);
							DeprecatedPaymentReceiptHandler dph = new DeprecatedPaymentReceiptHandler();
							dph.printReport(walletData, walletData.getDocumentType().getAnticipo().equals(SpiritConstants.getOptionYes().substring(0,1)), anulledReceipt);
						}
					} else {
						DeprecatedPaymentReceiptHandler dph = new DeprecatedPaymentReceiptHandler();
						dph.printReport(walletData, walletData.getDocumentType().getAnticipo().equals(SpiritConstants.getOptionYes().substring(0,1)), anulledReceipt);
					}
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
			}
	}

	public void save() {
		WalletData walletData = setWalletData();
		Vector<WalletDetailData> walletDetailDataVector = getWalletDetailDataVector();
		Vector<CrossingWalletDetailData> crossingWalletDetailsVector = getCrossingWalletDetailsVector();
		// On save mode, deletedWalletDetailDataVector will be empty
		Vector<WalletDetailData> deletedWalletDetailDataVector = new Vector<WalletDetailData>();
		try {
			if (WalletValidationHandler.validate(walletData, walletDetailDataVector, this)) {
				if (validateRetrocompatibleFields(walletDetailDataVector)) {
					boolean update = false;
					processWallet(walletData, walletDetailDataVector, crossingWalletDetailsVector, deletedWalletDetailDataVector, update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
		}
	}
	
	public void update() {
		WalletData walletData = setWalletData();
		if (!walletData.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1))) {
			Vector<WalletDetailData> walletDetailDataVector = getWalletDetailDataVector();
			Vector<CrossingWalletDetailData> crossingWalletDetailsVector = getCrossingWalletDetailsVector();
			Vector<WalletDetailData> deletedWalletDetailDataVector = getDeletedWalletDetailDataVector();
			try {
				if (WalletValidationHandler.validate(walletData, walletDetailDataVector, this)) {
					if (validateRetrocompatibleFields(walletDetailDataVector)) {
						boolean update = true;
						processWallet(walletData, walletDetailDataVector, crossingWalletDetailsVector, deletedWalletDetailDataVector, update);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				SpiritAlert.createAlert(e.getMessage(), SpiritAlert.ERROR);
			}
		} else {
			try {
				SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.NOT_VALID_ACTION_FOR_NULLIFIED_TRANSACTIONS.getMessage()), SpiritAlert.WARNING);
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al actualizar la transacción", SpiritAlert.ERROR);
			}
		}
	}

	private void processWallet(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector, Vector<CrossingWalletDetailData> crossingWalletDetailsVector, Vector<WalletDetailData> deletedWalletDetailDataVector, boolean update) throws Exception, GenericBusinessException {
		boolean isCancelAction = false;
		LevelingConfirmationModel jdPendingBalancesConfirmation = (LevelingConfirmationModel) SpiritConstants.getNullValue();
		if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1)) && havePendingBalance(walletData)) {
			jdPendingBalancesConfirmation = new LevelingConfirmationModel(Parametros.getMainFrame(), walletData);
			isCancelAction = jdPendingBalancesConfirmation.isCancelAction();
		}
		if (!isCancelAction) {
			DocumentoIf levelingDocument = (DocumentoIf) SpiritConstants.getNullValue();
			DocumentoIf advancePaymentDocument = (DocumentoIf) SpiritConstants.getNullValue();
			if (jdPendingBalancesConfirmation != SpiritConstants.getNullValue()) {
				if (jdPendingBalancesConfirmation.getLevelingDocument() != SpiritConstants.getNullValue())
					levelingDocument = jdPendingBalancesConfirmation.getLevelingDocument();
				if (jdPendingBalancesConfirmation.getAdvancePaymentDocument() != SpiritConstants.getNullValue())
					advancePaymentDocument = jdPendingBalancesConfirmation.getAdvancePaymentDocument();
			}
			walletData = SessionServiceLocator.getCarteraSessionService().processWallet(walletData, walletDetailDataVector, crossingWalletDetailsVector, deletedWalletDetailDataVector, update, wasUpdatedOriginalReceipt(), levelingDocument, advancePaymentDocument, (CarteraRelacionadaEJB) SpiritConstants.getNullValue());
			if (!update)
				SpiritAlert.createAlert("Transacción procesada con éxito", SpiritAlert.INFORMATION);
			else
				SpiritAlert.createAlert("Transacción actualizada con éxito", SpiritAlert.INFORMATION);
			printChecks(walletData, walletDetailDataVector);
			boolean anulledReceipt = (walletData.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)))?true:false;
			if (!walletData.getDocumentType().getEgreso().equals(SpiritConstants.getOptionYes().substring(0,1)))
				printReport(walletData, walletDetailDataVector, anulledReceipt);
			else {
				if (walletDetailDataVector.size() > 1) {
					int option = JOptionPane.showOptionDialog(null, "Está intentando imprimir un comprobante multitransacción en un formato obsoleto.\nSe recomienda imprimir la versión actual de este comprobante.\n¿Desea imprimir la versión actual del comprobante?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionYes());
					if (option == JOptionPane.YES_OPTION) {
						printReport(walletData, walletDetailDataVector, anulledReceipt);
					} else {
						SpiritAlert.createAlert("Se generará una versión obsoleta del comprobante, el resultado es impredecible.", SpiritAlert.WARNING);
						DeprecatedPaymentReceiptHandler dph = new DeprecatedPaymentReceiptHandler();
						dph.printReport(walletData, walletData.getDocumentType().getAnticipo().equals(SpiritConstants.getOptionYes().substring(0,1)), anulledReceipt);
					}
				} else {
					DeprecatedPaymentReceiptHandler dph = new DeprecatedPaymentReceiptHandler();
					dph.printReport(walletData, walletData.getDocumentType().getAnticipo().equals(SpiritConstants.getOptionYes().substring(0,1)), anulledReceipt);
				}
			}
			showSaveMode();
		}
	}

	private void printChecks(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector) {
		ArrayList<String[]> checks = getCheckList(walletData, walletDetailDataVector);
		if (checks.size() > 0)
			new JDCheque(Parametros.getMainFrame(), checks);
	}
	
	private ArrayList<String[]> getCheckList(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector) {
		ArrayList<String[]> checks = new ArrayList<String[]>();
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailData = walletDetailDataVector.get(i);
			if (walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1)) && walletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				checks.add(generateCheckData(walletData, walletDetailData));
			}
		}
		return checks;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private void printReport(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector, boolean anulledReceipt) throws GenericBusinessException {
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1)))
			parameterMap.put("isCustomerWalletType", true);
		else if (walletData.getWalletType().equals(SpiritConstants.getProviderWalletType().substring(0,1)))
			parameterMap.put("isProviderWalletType", true);
		parameterMap.put("enterprise", walletData.getEnterprise().getNombre());
		parameterMap.put("user", walletData.getUser().getUsuario());
		parameterMap.put("identifier", walletData.getEnterprise().getRuc());
		parameterMap.put("documentType", walletData.getDocumentType().getNombre());
		parameterMap.put("code", walletData.getWalletCodeNumber());
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("tipoDocumentoId", walletData.getDocumentType().getId());
		if (walletData.getDocumentType().getNotaCredito().equals("S")) {
			if (walletData.getReferenceId() != SpiritConstants.getNullValue())
				queryMap.put("transaccionId", walletData.getReferenceId());
			else
				// ONLY IN SPECIAL CASE OF INTERNAL CREDIT MEMO
				queryMap.put("transaccionId", walletData.getWalletId());
		} else
			queryMap.put("transaccionId", walletData.getWalletId()); 
		Iterator<AsientoIf> it = SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(queryMap).iterator();
		String accountingEntryNumber = (it.hasNext())?((AsientoIf) it.next()).getNumero():SpiritConstants.getEmptyCharacter();
		parameterMap.put("accountingEntryNumber", accountingEntryNumber);
		parameterMap.put("businessOperator", walletData.getBusinessOperator().getRazonSocial());
		parameterMap.put("total", walletData.getTotal());
		parameterMap.put("balance", walletData.getBalance());
		parameterMap.put("amountInWords", Utilitarios.amountToWords(walletData.getTotal().doubleValue(), false, walletData.getCurrency())[0]);
		parameterMap.put("city", SessionServiceLocator.getCiudadSessionService().getCiudad(walletData.getOffice().getCiudadId()).getNombre());
		parameterMap.put("date", Utilitarios.getEmissionDateUppercase(walletData.getEmissionDate()));
		parameterMap.put("currency", walletData.getCurrency().getSimbolo());
		parameterMap.put("anulledReceipt", new Boolean(anulledReceipt));
		Vector<WalletReceiptRowData> walletReceiptRowDataVector = new Vector<WalletReceiptRowData>();
		if (!anulledReceipt) {
			Map<Integer, WalletDetailData> walletDetailDataMap = mappingWalletDetailDataFromVector(walletDetailDataVector);
			ArrayList walletReceiptRowDataList = (ArrayList) SessionServiceLocator.getCarteraDetalleSessionService().findWalletReceiptRowDataByWalletId(walletData.getWalletId());
			Iterator<Object[]> walletReceiptRowDataIterator = walletReceiptRowDataList.iterator();
			if (walletReceiptRowDataIterator.hasNext()) {
				while (walletReceiptRowDataIterator.hasNext()) {
					Object[] walletReceiptData = walletReceiptRowDataIterator.next();
					CarteraDetalleIf walletDetail = (CarteraDetalleIf) walletReceiptData[0];
					CarteraAfectaIf crossingWalletDetail = (CarteraAfectaIf) walletReceiptData[1];
					CarteraDetalleIf transactionWalletDetail = (CarteraDetalleIf) walletReceiptData[2];
					BigDecimal appliedValue = (BigDecimal) walletReceiptData[3];
					WalletDetailData walletDetailData = walletDetailDataMap.get(walletDetail.getSecuencial());
					WalletReceiptRowData walletReceiptRowData = new WalletReceiptRowData();
					walletReceiptRowData.setSequentialNumber(walletDetailData.getSequentialNumber());
					walletReceiptRowData.setReceiptDetail(walletDetailData.getComment());
					String transactionDetail = SpiritConstants.getEmptyCharacter();
					transactionDetail = "A: " + transactionDetail + Utilitarios.getFechaCortaUppercase(crossingWalletDetail.getFechaAplicacion()) + ", ";
					CarteraIf transactionWallet = SessionServiceLocator.getCarteraSessionService().getCartera(transactionWalletDetail.getCarteraId());
					TipoDocumentoIf transactionDocumentType = SessionServiceLocator.getTipoDocumentoSessionService().getTipoDocumento(transactionWallet.getTipodocumentoId());
					transactionDetail += transactionDocumentType.getAbreviatura() + SpiritConstants.getColonCharacter() + SpiritConstants.getBlankSpaceCharacter();
					if (transactionDocumentType.getFactura().equals(SpiritConstants.getOptionYes().substring(0,1))
							|| transactionDocumentType.getNotaVenta().equals(SpiritConstants.getOptionYes().substring(0,1))
							|| transactionDocumentType.getNotaCredito().equals(SpiritConstants.getOptionYes().substring(0,1)))
						transactionDetail += transactionWallet.getPreimpreso();
					else
						transactionDetail += transactionWallet.getCodigo();
					transactionDetail +=  SpiritConstants.getBlankSpaceCharacter() + transactionWallet.getComentario();
					walletReceiptRowData.setTransactionDetail(transactionDetail);
					walletReceiptRowData.setDetailValue(walletDetailData.getValue());
					walletReceiptRowData.setAppliedValue(appliedValue.negate());
					walletReceiptRowDataVector.add(walletReceiptRowData);
				}
			}
			for (int i=0; i<walletDetailDataVector.size(); i++) {
				WalletDetailData walletDetailData = walletDetailDataVector.get(i);
				if (walletDetailData.getValue().compareTo(walletDetailData.getBalance()) == 0) {
					WalletReceiptRowData walletReceiptRowData = new WalletReceiptRowData();
					walletReceiptRowData.setSequentialNumber(walletDetailData.getSequentialNumber());
					walletReceiptRowData.setReceiptDetail(walletDetailData.getComment());
					walletReceiptRowData.setTransactionDetail(SpiritConstants.getEmptyCharacter());
					walletReceiptRowData.setDetailValue(walletDetailData.getValue());
					walletReceiptRowData.setAppliedValue(SpiritConstants.getZeroValue());
					walletReceiptRowDataVector.add(walletReceiptRowData);
				}
			}
		} else {
			for (int i=0; i<walletDetailDataVector.size(); i++) {
				WalletDetailData walletDetailData = walletDetailDataVector.get(i);
				WalletReceiptRowData walletReceiptRowData = new WalletReceiptRowData();
				walletReceiptRowData.setSequentialNumber(walletDetailData.getSequentialNumber());
				walletReceiptRowData.setReceiptDetail(walletDetailData.getComment());
				walletReceiptRowData.setTransactionDetail(WalletConstants.getNullifyStatus());
				walletReceiptRowDataVector.add(walletReceiptRowData);
				parameterMap.put("total", SpiritConstants.getZeroValue());
				parameterMap.put("balance", SpiritConstants.getZeroValue());
			}
		}
		Collections.sort((List) walletReceiptRowDataVector, getWalletReceiptRowDataComparator());
		String fileName = "jaspers/cartera/RPWalletReceipt.jasper";
		ReportModelImpl.processReport(fileName, parameterMap, walletReceiptRowDataVector, true);
	}
	
	private Map<Integer, WalletDetailData> mappingWalletDetailDataFromVector(Vector<WalletDetailData> walletDetailDataVector) {
		Map<Integer, WalletDetailData> walletDetailDataMap = new HashMap<Integer, WalletDetailData>();
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailData = walletDetailDataVector.get(i);
			System.out.println("walletDetailData sequential number >> " + walletDetailData.getSequentialNumber());
			walletDetailDataMap.put(walletDetailData.getSequentialNumber(), walletDetailData);
		}
		return walletDetailDataMap;
	}
	
	public String[] generateCheckData(WalletData walletData, WalletDetailData walletDetailData) {
		String[] check = new String[5];
		double amount = walletDetailData.getValue().doubleValue();
		if (amount > 0D) {
			ClienteIf businessOperator = walletData.getBusinessOperator();
			String beneficiary = businessOperator.getRazonSocial();
			CiudadIf city = (CiudadIf) Parametros.getCiudad();
			String[] amountInWords = Utilitarios.amountToWords(amount, true, walletData.getCurrency());
			String amountInWordsFirstLine = amountInWords[0].replaceAll(SpiritConstants.getBlankSpaceCharacter() + SpiritConstants.getBlankSpaceCharacter(), SpiritConstants.getEmptyCharacter());
			String amountInWordsSecondLine = amountInWords[1].replaceAll(SpiritConstants.getBlankSpaceCharacter() + SpiritConstants.getBlankSpaceCharacter(), SpiritConstants.getEmptyCharacter());
			//String placeAndDate = city.getNombre() + SpiritConstants.getCommaCharacter() + SpiritConstants.getBlankSpaceCharacter() + Utilitarios.getFechaUppercase(walletData.getEmissionDate());
			//String firstReplacementPlaceAndDate = placeAndDate.replaceFirst(SpiritConstants.getPlaceholderCharacter(), "DE");
			//placeAndDate = firstReplacementPlaceAndDate.replaceAll(SpiritConstants.getPlaceholderCharacter(), "DEL");
			
			//String lugarFecha = ciudad.getNombre() + ", " + Utilitarios.getFechaUppercase(carteraDetalle.getFechaCartera());
			//String lugarFechaPrimerReemplazo = lugarFecha.replaceFirst("-","DE");
			//lugarFecha = lugarFechaPrimerReemplazo.replaceAll("-","DEL");
			
			String placeAndDate = city.getNombre() + SpiritConstants.getCommaCharacter() + SpiritConstants.getBlankSpaceCharacter() + Utilitarios.getFechaNuevosCheques(walletData.getEmissionDate()); 
			
			check[0] = SpiritConstants.getDecimalFormatPattern().format(Double.valueOf(amount));
			check[1] = beneficiary;
			check[2] = amountInWordsFirstLine;
			check[3] = amountInWordsSecondLine;
			check[4] = placeAndDate;
		}
		return check;
	}

	private boolean havePendingBalance(WalletData walletData) {
		return walletData.getBalance().doubleValue() > 0D && walletData.getBalance().doubleValue() <= walletData.getTotal().doubleValue();
	}
	
	public boolean validateFields() {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean validateDetailFields(WalletDetailData walletDetailData) {
		boolean loadingData = isFindAction();
		try {
			if (!isFindAction() && !isDeleteAction()) {
				int walletValidationPolicy = getWalletValidationPolicy();
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash()))
					return WalletValidationHandler.validate(WalletConstants.getTransactionCash(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck())) {
					return WalletValidationHandler.validate(WalletConstants.getTransactionCheck(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
				} if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit()))
					return WalletValidationHandler.validate(WalletConstants.getTransactionDebit(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer()))
					return WalletValidationHandler.validate(WalletConstants.getTransactionTransfer(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard()))
					return WalletValidationHandler.validate(WalletConstants.getTransactionCreditCard(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment()))
					return WalletValidationHandler.validate(WalletConstants.getTransactionElectronicPayment(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention()))
					return WalletValidationHandler.validate(WalletConstants.getTransactionRetention(), walletDetailData, getWalletDetailDataVector(), this, loadingData);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al validar los datos", SpiritAlert.ERROR);
		}	
		return true;
	}
	
	@SuppressWarnings("unused")
	private boolean validateRetrocompatibleFields(Vector<WalletDetailData> walletDetailDataVector)  {
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailData = walletDetailDataVector.get(i);
			showWalletDetailData(walletDetailData);
			return validateDetailFields(walletDetailData);
		}
		return true;
	}

	public void addDetail() {
		WalletDetailData walletDetailData = setWalletDetailData(getWalletValidationPolicy(), false);
		if (validateDetailFields(walletDetailData)) {
			walletDetailData.setComment(buildComment(walletDetailData, isActivatedRetrocompatibility()));
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				setUpdatedOriginalReceipt(true);
			getWalletDetailDataVector().add(walletDetailData);
			addRowToTblWalletDetail(walletDetailData);
			calculateTotal();
			calculateBalance();
			cleanDetailTab(false, false);
			getCmbDocument().requestFocusInWindow();
		}
	}
		
	private void calculateTotal() {
		BigDecimal total = SpiritConstants.getZeroValue();
		for (int i=0; i<getWalletDetailDataVector().size(); i++) {
			WalletDetailData walletDetailData = getWalletDetailDataVector().get(i);
			total = total.add(walletDetailData.getValue());
		}
		getTxtTotal().setValue(total);
	}
	
	private void calculateBalance() {
		BigDecimal balance = SpiritConstants.getZeroValue();
		for (int i=0; i<getWalletDetailDataVector().size(); i++) {
			WalletDetailData walletDetailData = getWalletDetailDataVector().get(i);
			balance = balance.add(walletDetailData.getBalance());
		}
		getTxtBalance().setValue(balance);
	}

	private WalletData setWalletData() {
		WalletData walletData = new WalletData();
		if (this.getMode() == SpiritMode.UPDATE_MODE) {
			walletData.setWalletCodeNumber(getTxtCode().getText());
			walletData.setWalletId(getTxtWalletId().getValue());
			if (getTxtReferenceId().getValue() != SpiritConstants.getNullValue())
				walletData.setReferenceId(getTxtReferenceId().getValue());
		}
		walletData.setWalletType(getRbCustomer().isSelected()?SpiritConstants.getCustomerWalletType().substring(0,1):SpiritConstants.getProviderWalletType().substring(0,1));
		walletData.setEmissionDate(getTxtEmissionDate().getValue());
		walletData.setCreationDate(getTxtCreationDate().getValue());
		walletData.setLastUpdateDate(getTxtLastUpdateDate().getValue());
		if (this.getMode() == SpiritMode.UPDATE_MODE) {
			Map queryMap = new HashMap();
			queryMap.put("empresaId", Parametros.getIdEmpresa());
			queryMap.put("nombre", getTxtOffice().getText());
			try {
				Iterator<OficinaIf> it = SessionServiceLocator.getOficinaSessionService().findOficinaByQuery(queryMap).iterator();
				if (it.hasNext()) {
					OficinaIf office = it.next();
					walletData.setOffice(office);
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al cargar datos de la oficina", SpiritAlert.ERROR);
			}			
		} else {
			walletData.setOffice(Parametros.getOficina());
		}
		walletData.setEnterprise(Parametros.getEmpresa());
		walletData.setUser(Parametros.getUsuarioIf());
		walletData.setDocumentType(getCmbDocumentType().getSelectedItem());
		walletData.setCurrency(getCmbCurrency().getSelectedItem());
		walletData.setBusinessOperator(getBusinessOperator());
		walletData.setBusinessOperatorOffice(getBusinessOperatorOffice());
		walletData.setStatus(getCbAnulled().isSelected()?WalletConstants.getNullifyStatus().substring(0,1):WalletConstants.getNormalStatus().substring(0,1));
		walletData.setComment((getTxtComment().getText()!=null)?getTxtComment().getText():SpiritConstants.getEmptyCharacter());
		walletData.setTotal(Utilitarios.moneyFormatterToBigDecimal(getTxtTotal()));
		walletData.setBalance(Utilitarios.moneyFormatterToBigDecimal(getTxtBalance()));
		if (isActivatedRetrocompatibility())
			walletData.setActivateRetrocompatibility(SpiritConstants.getOptionYes().substring(0,1));
		else
			walletData.setActivateRetrocompatibility(SpiritConstants.getOptionNo().substring(0,1));
		return walletData;
	}
	
	private WalletDetailData setWalletDetailData(int walletValidationPolicy, boolean update) {
		WalletDetailData walletDetailData = (!update)?new WalletDetailData():getWalletDetailDataVector().get(getSelectedWalletDetailIndex());
		try {
			if (this.getMode() == SpiritMode.UPDATE_MODE && isFindAction())
				walletDetailData.setWalletDetailId(getTxtWalletDetailId().getValue());
			if (!update || (this.getMode() == SpiritMode.UPDATE_MODE && isFindAction()))
				walletDetailData.setSequentialNumber(getMaximumSequentialNumber());
			if (!getCmbDocument().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))
				walletDetailData.setDocument((DocumentoIf) getCmbDocument().getSelectedItem());
			walletDetailData.setValue(Utilitarios.moneyFormatterToBigDecimal(getTxtDetailValue()));
			walletDetailData.setBalance(Utilitarios.moneyFormatterToBigDecimal(getTxtDetailBalance()));
			if (!isActivatedRetrocompatibility()) {
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash())) {
					// TODO
				}
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck())) {
					if (update) {
						walletDetailData.setPreviousCheckAccount(walletDetailData.getCheckAccount());
						walletDetailData.setPreviousCheckBank(walletDetailData.getCheckBank());
						walletDetailData.setPreviousCheckNumber(walletDetailData.getCheckNumber());
					}
					walletDetailData.setCheckBank((BancoIf) getCmbCheckBank().getSelectedItem());
					walletDetailData.setCheckAccount((CuentaBancariaIf) getCmbCheckAccount().getSelectedItem());
					walletDetailData.setCheckNumber((getTxtCheckNumber().getValue() != null)?getTxtCheckNumber().getValue().toString():SpiritConstants.getEmptyCharacter());
					walletDetailData.setDepositBank((BancoIf) getCmbDepositBank().getSelectedItem());
					walletDetailData.setDepositAccount((CuentaBancariaIf) getCmbDepositAccount().getSelectedItem());
				}
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention())) {
					walletDetailData.setRetentionNumber((getTxtRetentionNumber().getValue() != null)?getTxtRetentionNumber().getValue().toString():SpiritConstants.getEmptyCharacter());
					walletDetailData.setRetentionAuthorization((getTxtRetentionAuthorization().getValue() != null)?getTxtRetentionAuthorization().getValue().toString():SpiritConstants.getEmptyCharacter());
					if (getRbCustomer().isSelected()) {
						SriClienteRetencionIf retentionPercentage = (SriClienteRetencionIf) getCmbRetentionPercentage().getSelectedItem();
						walletDetailData.setRetentionPercentage(retentionPercentage.getPorcentajeRetencion());
						walletDetailData.setSriRetentionPercentageDefinition(retentionPercentage);
					} else if (getRbProvider().isSelected())
						walletDetailData.setRetentionPercentage(BigDecimal.valueOf(Double.valueOf(getCmbRetentionPercentage().getSelectedItem().toString())));
				}
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit())) {
					walletDetailData.setDebitBank((BancoIf) getCmbDebitBank().getSelectedItem());
					walletDetailData.setDebitAccount((CuentaBancariaIf) getCmbDebitAccount().getSelectedItem());
					walletDetailData.setDebitReference(getTxtDebitReference().getText());
				}
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer())) {
					walletDetailData.setSourceBank((BancoIf) getCmbSourceBank().getSelectedItem());
					walletDetailData.setSourceAccount((CuentaBancariaIf) getCmbSourceAccount().getSelectedItem());
					walletDetailData.setTargetBank((BancoIf) getCmbTargetBank().getSelectedItem());
					walletDetailData.setTargetAccount((CuentaBancariaIf) getCmbTargetAccount().getSelectedItem());
				}
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard())) {
					walletDetailData.setCreditCardBank((BancoIf) getCmbCreditCardBank().getSelectedItem());
					walletDetailData.setCreditCard((TarjetaCreditoIf) getCmbCreditCard().getSelectedItem());
					walletDetailData.setVoucherReference(getTxtVoucherReference().getText());
				}
				if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment())) {
					walletDetailData.setElectronicPaymentReference(getTxtElectronicPaymentReference().getText());
				}
			}
			//walletDetailData.setComment(buildComment(walletDetailData, isActivatedRetrocompatibility()));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al procesar los datos", SpiritAlert.ERROR);	
		}
		return walletDetailData;
	}

	private String buildComment(WalletDetailData walletDetailData, boolean isActivatedRetrocompatibility) {
		String comment = SpiritConstants.getEmptyCharacter();
		DocumentoIf document = walletDetailData.getDocument();
		comment += document.getAbreviado() + SpiritConstants.getBlankSpaceCharacter();
		if (!isActivatedRetrocompatibility) {
			if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += "No. " + walletDetailData.getCheckNumber() + "; CTA. # " + walletDetailData.getCheckAccount().getCuenta() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getCheckBank().getNombre();
			else if (document.getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += String.valueOf(walletDetailData.getRetentionPercentage().intValue()) + "% " + walletDetailData.getRetentionNumber();
			else if (document.getRetencionIva().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += String.valueOf(walletDetailData.getRetentionPercentage().intValue()) + "% " + walletDetailData.getRetentionNumber();
			else if (document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += "CTA. # " + walletDetailData.getDebitAccount().getCuenta() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getDebitBank().getNombre();
			else if (document.getTarjetaCredito().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += walletDetailData.getCreditCard().getNombre() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getCreditCardBank().getNombre();
			else if (document.getTransaccionElectronica().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += walletDetailData.getElectronicPaymentReference();
			else if (document.getTransferenciaBancaria().equals(SpiritConstants.getOptionYes().substring(0,1)))
				comment += "DE CTA. # " + (walletDetailData.getSourceAccount() != (CuentaBancariaIf) SpiritConstants.getNullValue()?walletDetailData.getSourceAccount().getCuenta() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getSourceBank().getNombre():"SIN ESPECIFICAR") + " A CTA. # " + walletDetailData.getTargetAccount().getCuenta() + SpiritConstants.getBlankSpaceCharacter() + walletDetailData.getTargetBank().getNombre();
		}
		return comment;
	}
	
	private void addRowToTblWalletDetail(WalletDetailData walletDetailData) {
		DefaultTableModel dtm = (DefaultTableModel) getTblWalletDetail().getModel();
		Vector<Object> dataRow = new Vector<Object>();
		dataRow.add(new Boolean(false));
		dataRow.add(Utilitarios.getFechaUppercase((java.util.Date) getTxtEmissionDate().getValue()));
		dataRow.add(walletDetailData.getComment());
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getValue()));
		dataRow.add(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getBalance()));
		dtm.addRow(dataRow);
	}
	
	private void updateRowTblWalletDetail(WalletDetailData walletDetailData) {
		DefaultTableModel dtm = (DefaultTableModel) getTblWalletDetail().getModel();
		dtm.setValueAt(new Boolean(false), getSelectedRowTblWalletDetail(), WalletConstants.getTblWalletDetailSelectionColumnIndex());
		dtm.setValueAt(Utilitarios.getFechaUppercase((java.util.Date) getTxtEmissionDate().getValue()), getSelectedRowTblWalletDetail(), WalletConstants.getTblWalletDetailDateColumnIndex());
		dtm.setValueAt(walletDetailData.getComment(), getSelectedRowTblWalletDetail(), WalletConstants.getTblWalletDetailTransactionColumnIndex());
		dtm.setValueAt(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getValue()), getSelectedRowTblWalletDetail(), WalletConstants.getTblWalletDetailValueColumnIndex());
		dtm.setValueAt(SpiritConstants.getDecimalFormatPattern().format(walletDetailData.getBalance()), getSelectedRowTblWalletDetail(), WalletConstants.getTblWalletDetailBalanceColumnIndex());
	}

	private void deleteRowFromTblWalletDetail(int row) {
		DefaultTableModel jTableModel = (DefaultTableModel) getTblWalletDetail().getModel();
		jTableModel.removeRow(row);
	}

	public void updateDetail() {
		if (getTblWalletDetail().getSelectedRow() != -1) {
			WalletDetailData previousWalletDetailData = (WalletDetailData) DeepCopy.copy(getWalletDetailDataVector().get(getSelectedWalletDetailIndex()));
			WalletDetailData walletDetailData = setWalletDetailData(getWalletValidationPolicy(), true);
			if (validateDetailFields(walletDetailData)) {
				walletDetailData.setComment(buildComment(walletDetailData, isActivatedRetrocompatibility()));
				if (this.getMode() == SpiritMode.UPDATE_MODE)
					setUpdatedOriginalReceipt(true);
				getWalletDetailDataVector().set(getSelectedWalletDetailIndex(), walletDetailData);
				updateRowTblWalletDetail(walletDetailData);
				calculateTotal();
				calculateBalance();
				cleanDetailTab(false, false);
				getCmbDocument().requestFocusInWindow();
			} else {
				getWalletDetailDataVector().set(getSelectedWalletDetailIndex(), previousWalletDetailData);
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el registro que desea actualizar", SpiritAlert.WARNING);
		}
	}

	public void deleteDetail() {
		if (getTblWalletDetail().getSelectedRow() != -1) {
			WalletDetailData walletDetailData = getWalletDetailDataVector().get(getSelectedWalletDetailIndex());
			walletDetailData.setPreviousCheckBank(walletDetailData.getCheckBank());
			walletDetailData.setPreviousCheckAccount(walletDetailData.getCheckAccount());
			walletDetailData.setPreviousCheckNumber(walletDetailData.getCheckNumber());
			if (!WalletValidationHandler.isCanceledCheck(walletDetailData)) {
				if (walletDetailData.getWalletDetailId() != null) {
					if (this.getMode() == SpiritMode.UPDATE_MODE)
						setUpdatedOriginalReceipt(true);
					getDeletedWalletDetailDataVector().add(getWalletDetailDataVector().get(getSelectedWalletDetailIndex()));
				}
				Iterator<CrossingWalletDetailData> crossingWalletDetailDataIterator = getCrossingWalletDetailsVector().iterator();
				while (crossingWalletDetailDataIterator.hasNext()) {
					CrossingWalletDetailData crossingWalletDetailData = crossingWalletDetailDataIterator.next();
					if (crossingWalletDetailData.getWalletDetailData().getSequentialNumber() == walletDetailData.getSequentialNumber())
						crossingWalletDetailDataIterator.remove();
				}
				getWalletDetailDataVector().remove(getSelectedWalletDetailIndex());
				calculateTotal();
				calculateBalance();
				cleanDetailTab(false, false);
				deleteRowFromTblWalletDetail(getSelectedRowTblWalletDetail());
				getCmbDocument().requestFocusInWindow();
			} else {
				try {
					SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.CANCELED_CHECK.getMessage()), SpiritAlert.WARNING);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al validar datos", SpiritAlert.ERROR);
				}
				switchJtpWallet(WalletConstants.getDetailTab());
				getTxtCheckNumber().requestFocusInWindow();
			}
		} else {
			SpiritAlert.createAlert("Debe seleccionar el registro que desea eliminar", SpiritAlert.WARNING);
		}
	}
	
	WindowListener wl = new WindowAdapter() {
		@SuppressWarnings("unused")
		public void windowClosed(WindowEvent e) {
			JDApplyWalletTransaction jd = (JDApplyWalletTransaction) e.getComponent();
			jd = null;
			System.gc();
		}
	};
	
	public boolean deleteDetailGroup() {
		boolean notDeletedDetails = false;
		int notDeleted = 0;
		for (int i=0; i<getTblWalletDetail().getRowCount(); i++) {
			if ((Boolean) getTblWalletDetail().getValueAt(i,WalletConstants.getTblWalletDetailSelectionColumnIndex())) {
				getTblWalletDetail().setRowSelectionInterval(i, i);
				setSelectedWalletDetailIndex(getTblWalletDetail().convertRowIndexToModel(i));
				setSelectedRowTblWalletDetail(i);
				WalletDetailData walletDetailData = getWalletDetailDataVector().get(getSelectedWalletDetailIndex());
				if (walletDetailData.getValue().compareTo(walletDetailData.getBalance()) == 0) {
					deleteDetail();
					i--;
				} else {
					notDeleted++;
					notDeletedDetails = true;
				}
			}
		}
		
		if (notDeleted > 0) {
			SpiritAlert.createAlert("Uno o más registros seleccionados no fueron eliminados debido a que están cruzados.\nPara eliminar estos registros debe primeramente reversar los cruces realizados.", SpiritAlert.WARNING);
		}
		
		return notDeletedDetails;
	}
	
	private int getSelectedRowsCount() {
		int selectedRowsCount = 0;
		for (int i=0; i<getTblWalletDetail().getRowCount(); i++) {
			if ((Boolean) getTblWalletDetail().getValueAt(i,0))
				selectedRowsCount++;
		}
		return selectedRowsCount;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void refresh() {
		// TODO Auto-generated method stub

	}

	private void initModelComponents() {
		if (this.getMode() != SpiritMode.UPDATE_MODE) {
			clean();
			initObjects();
			initJtpWalletDetail();
			initTblWalletDetail();
			loadComboBoxes();
		}
		initTextFields();
		initButtons();
		setSelectedPropertyComponents();
		setEnabledPropertyComponents();
		setFocusablePropertyComponents();
	}

	private void initObjects() {
		setWalletDetailDataVector(new Vector<WalletDetailData>());
		setDeletedWalletDetailDataVector(new Vector<WalletDetailData>());
		setCrossingWalletDetailsVector(new Vector<CrossingWalletDetailData>());
		setSelectedWalletDetailIndex(-1);
		setBusinessOperatorOffice((ClienteOficinaIf) SpiritConstants.getNullValue());
		setBusinessOperator((ClienteIf) SpiritConstants.getNullValue());
		setMaximumSequentialNumber(0);
		if (this.getMode() == SpiritMode.SAVE_MODE)
			setUpdatedOriginalReceipt(false);
		setDeleteAction(false);
		setFindAction(false);
		setActivatedRetrocompatibility(false);
	}

	private void cleanTextFields(int tab) {
		Map<String, String> classMap = new HashMap<String, String>();
		classMap.put(SpiritConstants.getJtextfieldClassName(), (new JTextField()).getClass().getName());
		classMap.put(SpiritConstants.getJformattedtextfieldClassName(), (new JFormattedTextField()).getClass().getName());
		cleanComponentsByClassMap(classMap, tab);
	}

	private void cleanComboBoxes(int tab) {
		Map<String, String> classMap = new HashMap<String, String>();
		classMap.put(SpiritConstants.getJcomboboxClassName(), (new JComboBox()).getClass().getName());
		cleanComponentsByClassMap(classMap, tab);
	}

	@SuppressWarnings("unchecked")
	private void refreshCmbDocument() throws GenericBusinessException {
		List<Object> documents = new ArrayList<Object>();
		if (getCmbDocumentType().getSelectedItem() != null && !getCmbDocumentType().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter())) {
			TipoDocumentoIf documentType = (TipoDocumentoIf) getCmbDocumentType().getSelectedItem();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("tipoDocumentoId", documentType.getId());
			documents = (ArrayList<Object>) SessionServiceLocator.getDocumentoSessionService().findDocumentoByQuery(queryMap);
		}
		//documents.add(0, (DocumentoIf) SpiritConstants.getNullValue());
		documents.add(0, SpiritConstants.getPlaceholderCharacter());
		refreshCombo(getCmbDocument(), documents);
	}

	private void cleanTables() {
		DefaultTableModel jTableModel = (DefaultTableModel) getTblWalletDetail().getModel();
		int rows = getTblWalletDetail().getRowCount();
		for(int j=rows;j>0;--j)
			jTableModel.removeRow(j-1);
	}

	private void cleanComponentsByClassMap(Map<String, String> classMap, int tab) {
		Iterator<String> it = classMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String className = (String) classMap.get(key);
			if (tab == WalletConstants.getMasterTab()) {  
				cleanComponentsContainerByClassName(getJpMaster(), className);
			}
			if (tab == WalletConstants.getDetailTab()) {
				cleanComponentsContainerByClassName(getJpDetail(), className);
				cleanComponentsContainerByClassName(getJpCheck(), className);
				cleanComponentsContainerByClassName(getJpDebit(), className);
				cleanComponentsContainerByClassName(getJpCreditCard(), className);
				cleanComponentsContainerByClassName(getJpElectronicPayment(), className);
				cleanComponentsContainerByClassName(getJpRetention(), className);
				cleanComponentsContainerByClassName(getJpTransfer(), className);
			}
		}
	}

	private void cleanComponentsContainerByClassName(JComponent jPanel, String className) {
		Component[] components = jPanel.getComponents();
		for (int i=0; i<components.length; i++) {
			Component component = components[i];
			if (component.getClass().getName().equals(className)) {
				if (className.equals(SpiritConstants.getJtextfieldClassName()))
					((JTextField) component).setText(SpiritConstants.getEmptyCharacter());
				if (className.equals(SpiritConstants.getJformattedtextfieldClassName())) {
					((JFormattedTextField) component).setText(SpiritConstants.getEmptyCharacter());
					((JFormattedTextField) component).setValue(null);
				} if (className.equals(SpiritConstants.getJcomboboxClassName()))
					((JComboBox) component).setSelectedItem(null);
			}
		}
	}
	
	private void initJtpWalletDetail() {
		try {
			int index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck());
			getJtpTransactionDetail().setIconAt(index, SpiritResourceManager.getImageIcon("images/icons/funcion/transactionCheck.png"));
			index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention());
			getJtpTransactionDetail().setIconAt(index, SpiritResourceManager.getImageIcon("images/icons/funcion/transactionRetention.png"));
			index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit());
			getJtpTransactionDetail().setIconAt(index, SpiritResourceManager.getImageIcon("images/icons/funcion/transactionDebit.png"));
			index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer());
			getJtpTransactionDetail().setIconAt(index, SpiritResourceManager.getImageIcon("images/icons/funcion/transactionTransfer.png"));
			index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard());
			getJtpTransactionDetail().setIconAt(index, SpiritResourceManager.getImageIcon("images/icons/funcion/transactionCreditCard.png"));
			index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment());
			getJtpTransactionDetail().setIconAt(index, SpiritResourceManager.getImageIcon("images/icons/funcion/transactionElectronicPayment.png"));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar panel de detalles", SpiritAlert.ERROR);
		}		
	}

	private void initTblWalletDetail() {
		getTblWalletDetail().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSelectedRowTblWalletDetail(-1);
		setTblWalletDetailWidthColumns();
		setTblWalletDetailAlignment();
		getTblWalletDetail().getTableHeader().setReorderingAllowed(false);
	}
	
	private void setTblWalletDetailWidthColumns() {
		TableColumn columnWidth = getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailSelectionColumnIndex());
		columnWidth.setPreferredWidth(20);
		columnWidth = getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailDateColumnIndex());
		columnWidth.setPreferredWidth(50);
		columnWidth = getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailTransactionColumnIndex());
		columnWidth.setPreferredWidth(150);
		columnWidth = getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailValueColumnIndex());
		columnWidth.setPreferredWidth(30);
		columnWidth = getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailBalanceColumnIndex());
		columnWidth.setPreferredWidth(30);
		columnWidth = getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailDeferredColumnIndex());
		columnWidth.setPreferredWidth(30);
	}

	private void setTblWalletDetailAlignment() {
		TableCellRendererHorizontalCenterAlignment horizontalCenterAlignment = new TableCellRendererHorizontalCenterAlignment();
		getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailDateColumnIndex()).setCellRenderer(horizontalCenterAlignment);
		TableCellRendererHorizontalRightAlignment horizontalRightAlignment = new TableCellRendererHorizontalRightAlignment();
		getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailValueColumnIndex()).setCellRenderer(horizontalRightAlignment);
		getTblWalletDetail().getColumnModel().getColumn(WalletConstants.getTblWalletDetailBalanceColumnIndex()).setCellRenderer(horizontalRightAlignment);
	}

	private void initButtons() {
		boolean enabled = true;
		if (this.getMode() == SpiritMode.FIND_MODE)
			enabled = false;
		getBtnSearchBusinessOperator().setText(SpiritConstants.getEmptyCharacter());
		getBtnSearchBusinessOperator().setToolTipText("Buscar operador de negocio");
		getBtnSearchBusinessOperator().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/findElement.png"));
		getBtnAddDetail().setToolTipText("Agregar documento");
		getBtnAddDetail().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/addElement.png"));
		getBtnAddDetail().setEnabled(enabled);
		getBtnUpdateDetail().setToolTipText("Actualizar documento");
		getBtnUpdateDetail().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/updateElement.png"));
		getBtnUpdateDetail().setEnabled(enabled);
		getBtnDeleteDetail().setToolTipText("Eliminar documento(s)");
		getBtnDeleteDetail().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/deleteElement.png"));
		getBtnDeleteDetail().setEnabled(enabled);
		getBtnHit().setToolTipText("Aplicar cruce de documento(s)");
		getBtnHit().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/applyDocument.png"));
		getBtnHit().setEnabled(enabled);
		getBtnAddBusinessOperator().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddBusinessOperator().setToolTipText("Agregar nuevo operador de negocio");
		getBtnAddBusinessOperator().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
		getBtnAddCheckAccount().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddCheckAccount().setToolTipText("Agregar nueva cuenta bancaria");
		getBtnAddCheckAccount().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
		getBtnAddDepositAccount().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddDepositAccount().setToolTipText("Agregar nueva cuenta bancaria");
		getBtnAddDepositAccount().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
		getBtnAddDebitAccount().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddDebitAccount().setToolTipText("Agregar nueva cuenta bancaria");
		getBtnAddDebitAccount().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
		getBtnAddSourceAccount().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddSourceAccount().setToolTipText("Agregar nueva cuenta bancaria");
		getBtnAddSourceAccount().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
		getBtnAddTargetAccount().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddTargetAccount().setToolTipText("Agregar nueva cuenta bancaria");
		getBtnAddTargetAccount().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
		getBtnAddCreditCard().setText(SpiritConstants.getEmptyCharacter());
		getBtnAddCreditCard().setToolTipText("Agregar nueva tarjeta de crédito");
		getBtnAddCreditCard().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/add.png"));
	}

	private void initTextFields() {
		boolean editable = true;
		getTxtCode().setEditable(editable);
		if (this.getMode() == SpiritMode.FIND_MODE)
			editable = false;
		if (this.getMode() == SpiritMode.SAVE_MODE)
			getTxtCode().setVisible(false);
		else {
			getTxtCode().setVisible(true);
			if (this.getMode() == SpiritMode.UPDATE_MODE)
				getTxtCode().setEditable(false);
		}
		getTxtWalletId().setVisible(false);
		getTxtReferenceId().setVisible(false);
		java.util.Date now = new java.util.Date();
		if (this.getMode() != SpiritMode.FIND_MODE) {
			OficinaIf office = (OficinaIf) Parametros.getOficina();
			getTxtOffice().setText(office.getNombre());
			Utilitarios.setDateFormatter(getTxtEmissionDate(), SpiritConstants.getDateFormatPattern());
			getTxtEmissionDate().setValue(now);
			Utilitarios.setDateFormatter(getTxtCreationDate(), SpiritConstants.getDateFormatPattern());
			getTxtCreationDate().setValue(now);
		} else {
			getTxtOffice().setText(SpiritConstants.getEmptyCharacter());
		}
		if (this.getMode() == SpiritMode.UPDATE_MODE) {
			getTxtEmissionDate().setEditable(false);
			Utilitarios.setDateFormatter(getTxtLastUpdateDate(), SpiritConstants.getDateFormatPattern());
			getTxtLastUpdateDate().setValue(now);
		} else {
			getTxtEmissionDate().setEditable(true);
			getTxtLastUpdateDate().setValue(null);
		}
		getTxtEmissionDate().setName("txtEmissionDate");
		getTxtLegalName().setName("txtLegalName");
		Utilitarios.setMoneyFormatter(getTxtTotal());
		getTxtTotal().setValue(SpiritConstants.getZeroValue());
		Utilitarios.setMoneyFormatter(getTxtBalance());
		getTxtBalance().setValue(SpiritConstants.getZeroValue());
		Utilitarios.setMoneyFormatter(getTxtDetailValue());
		getTxtWalletDetailId().setVisible(false);
		getTxtDetailValue().setValue(SpiritConstants.getZeroValue());
		getTxtDetailValue().setEditable(editable);
		getTxtDetailValue().setName("txtDetailValue");
		Utilitarios.setMoneyFormatter(getTxtDetailBalance());
		getTxtDetailBalance().setValue(SpiritConstants.getZeroValue());
		getTxtCheckNumber().setValue(SpiritConstants.getEmptyCharacter());
		getTxtRetentionNumber().setValue(SpiritConstants.getEmptyCharacter());
		getTxtRetentionAuthorization().setValue(SpiritConstants.getEmptyCharacter());
		getLblStatusMessage().setVisible(false);
		getLblStatusMessage().setText(SpiritConstants.getEmptyCharacter());
		getLblViewWalletAccountingEntry().setVisible(false);
		//Cursor lblViewWalletAccountingEntryCursor = getLblViewWalletAccountingEntry().getCursor();
		getLblViewWalletAccountingEntry().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getLblViewWalletAccountingEntry().setIcon(SpiritResourceManager.getImageIcon("images/icons/funcion/aceptar.png"));
	}

	private void initKeyListeners() {
		getTxtCode().addKeyListener(new TextChecker(WalletConstants.getMaxLenghtCode()));
		getTxtComment().addKeyListener(new TextChecker(WalletConstants.getMaxLenghtComments()));
		getTxtLegalName().addKeyListener(new TextChecker(WalletConstants.getMaxLenghtBusinessOperator()));
		getTxtVoucherReference().addKeyListener(new TextChecker(SpiritConstants.getMaxLenghtIntegerValues()));
		getTxtVoucherReference().addKeyListener(new NumberTextField());
		getTxtElectronicPaymentReference().addKeyListener(new TextChecker(SpiritConstants.getMaxLenghtIntegerValues()));
		getTxtElectronicPaymentReference().addKeyListener(new NumberTextField());
		initFocusComponentsOrderKeyListener();
		getCmbDocument().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtDetailValue().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbCheckBank().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbCheckAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddBusinessOperator().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtCheckNumber().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbDepositBank().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbDepositAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddDepositAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtRetentionNumber().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtRetentionAuthorization().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbRetentionPercentage().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbDebitBank().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbDebitAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddDebitAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtDebitReference().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbSourceBank().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbSourceAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddSourceAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbTargetBank().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbTargetAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddTargetAccount().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbCreditCardBank().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getCmbCreditCard().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddCreditCard().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtVoucherReference().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getTxtElectronicPaymentReference().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnAddDetail().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnUpdateDetail().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnDeleteDetail().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
		getBtnHit().addKeyListener(initSetFocusOnTblWalletDetailKeyListener());
	}
	
	private KeyListener initSetFocusOnTblWalletDetailKeyListener()  {
		return(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
					if (getTblWalletDetail().getRowCount() > 0) {
						getTblWalletDetail().grabFocus();
						getTblWalletDetail().setRowSelectionInterval(0, 0);
					}
				}
			}
		});
	}
	
	private void initFocusComponentsOrderKeyListener() {
		/* Master Tab */
		getJtpWallet().addKeyListener(focusComponentOrderKeyAdapter(getJtpWallet(), getRbCustomer(), false, false));
		getRbCustomer().addKeyListener(focusComponentOrderKeyAdapter(getRbCustomer(), getRbProvider(), false, false));
		getRbProvider().addKeyListener(focusComponentOrderKeyAdapter(getRbProvider(), getTxtEmissionDate(), false, false));
		getTxtEmissionDate().addKeyListener(focusComponentOrderKeyAdapter(getTxtEmissionDate(), getCmbDocumentType(), false, false));
		getCmbDocumentType().addKeyListener(focusComponentOrderKeyAdapter(getCmbDocumentType(), getCmbCurrency(), false, false));
		getCmbCurrency().addKeyListener(focusComponentOrderKeyAdapter(getCmbCurrency(), getTxtComment(), false, false));
		getTxtComment().addKeyListener(focusComponentOrderKeyAdapter(getTxtComment(), getTxtLegalName(), false, false));
		getTxtLegalName().addKeyListener(focusComponentOrderKeyAdapter(getTxtLegalName(), getBtnSearchBusinessOperator(), false, false));
		getBtnSearchBusinessOperator().addKeyListener(focusComponentOrderKeyAdapter(getBtnSearchBusinessOperator(), getBtnAddBusinessOperator(), false, false));
		getBtnAddBusinessOperator().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddBusinessOperator(), getCmbDocument(), true, false));
		/* Detail Tab */
		getCmbDocument().addKeyListener(focusComponentOrderKeyAdapter(getCmbDocument(), getTxtDetailValue(), false, false));
		getTxtDetailValue().addKeyListener(focusComponentOrderKeyAdapter(getTxtDetailValue(), getBtnAddDetail(), false, false));
		/* Check Transaction */
		getCmbCheckBank().addKeyListener(focusComponentOrderKeyAdapter(getCmbCheckBank(), getCmbCheckAccount(), false, false));
		getCmbCheckAccount().addKeyListener(focusComponentOrderKeyAdapter(getCmbCheckAccount(), getBtnAddCheckAccount(), false, false));
		getBtnAddCheckAccount().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddCheckAccount(), getTxtCheckNumber(), false, false));
		getTxtCheckNumber().addKeyListener(focusComponentOrderKeyAdapter(getTxtCheckNumber(), getCmbDepositBank(), false, false));
		getCmbDepositBank().addKeyListener(focusComponentOrderKeyAdapter(getCmbDepositBank(), getCmbDepositAccount(), false, false));
		getCmbDepositAccount().addKeyListener(focusComponentOrderKeyAdapter(getCmbDepositAccount(), getBtnAddDepositAccount(), false, false));
		getBtnAddDepositAccount().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddDepositAccount(), getBtnAddDetail(), false, false));
		/* Retention Transaction */
		getTxtRetentionNumber().addKeyListener(focusComponentOrderKeyAdapter(getTxtRetentionNumber(), getTxtRetentionAuthorization(), false, false));
		getTxtRetentionAuthorization().addKeyListener(focusComponentOrderKeyAdapter(getTxtRetentionAuthorization(), getCmbRetentionPercentage(), false, false));
		getCmbRetentionPercentage().addKeyListener(focusComponentOrderKeyAdapter(getCmbRetentionPercentage(), getBtnAddDetail(), false, false));
		/* Debit Transaction */
		getCmbDebitBank().addKeyListener(focusComponentOrderKeyAdapter(getCmbDebitBank(), getCmbDebitAccount(), false, false));
		getCmbDebitAccount().addKeyListener(focusComponentOrderKeyAdapter(getCmbDebitAccount(), getBtnAddDebitAccount(), false, false));
		getBtnAddDebitAccount().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddDebitAccount(), getTxtDebitReference(), false, false));
		getTxtDebitReference().addKeyListener(focusComponentOrderKeyAdapter(getTxtDebitReference(), getBtnAddDetail(), false, false));
		/* Transfer Transaction */
		getCmbSourceBank().addKeyListener(focusComponentOrderKeyAdapter(getCmbSourceBank(), getCmbSourceAccount(), false, false));
		getCmbSourceAccount().addKeyListener(focusComponentOrderKeyAdapter(getCmbSourceAccount(), getBtnAddSourceAccount(), false, false));
		getBtnAddSourceAccount().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddSourceAccount(), getCmbTargetBank(), false, false));
		getCmbTargetBank().addKeyListener(focusComponentOrderKeyAdapter(getCmbTargetBank(), getCmbTargetAccount(), false, false));
		getCmbTargetAccount().addKeyListener(focusComponentOrderKeyAdapter(getCmbTargetAccount(), getBtnAddTargetAccount(), false, false));
		getBtnAddTargetAccount().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddTargetAccount(), getBtnAddDetail(), false, false));
		/* Credit Card Transaction */
		getCmbCreditCardBank().addKeyListener(focusComponentOrderKeyAdapter(getCmbCreditCardBank(), getCmbCreditCard(), false, false));
		getCmbCreditCard().addKeyListener(focusComponentOrderKeyAdapter(getCmbCreditCard(), getBtnAddCreditCard(), false, false));
		getBtnAddCreditCard().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddCreditCard(), getTxtVoucherReference(), false, false));
		getTxtVoucherReference().addKeyListener(focusComponentOrderKeyAdapter(getTxtVoucherReference(), getBtnAddDetail(), false, false));
		/* Electronic Payment Transaction */
		getTxtElectronicPaymentReference().addKeyListener(focusComponentOrderKeyAdapter(getTxtElectronicPaymentReference(), getBtnAddDetail(), false, false));
		/* Tool Tab */
		getBtnAddDetail().addKeyListener(focusComponentOrderKeyAdapter(getBtnAddDetail(), getBtnUpdateDetail(), false, false));
		getBtnUpdateDetail().addKeyListener(focusComponentOrderKeyAdapter(getBtnUpdateDetail(), getBtnDeleteDetail(), false, false));
		getBtnDeleteDetail().addKeyListener(focusComponentOrderKeyAdapter(getBtnDeleteDetail(), getTblWalletDetail(), false, true));
		getTblWalletDetail().addKeyListener(focusComponentOrderKeyAdapter(getTblWalletDetail(), getBtnHit(), false, false));
		setTblWalletDetailVkEnterCustomBehavior();
		setTblWalletDetailVkSpaceCustomBehavior();
		getBtnHit().addKeyListener(focusComponentOrderKeyAdapter(getBtnHit(), getRbCustomer(), true, false));
	}
	
	private KeyListener focusComponentOrderKeyAdapter(final JComponent jOldComponent, final JComponent jNextComponent, final boolean changeJtpWalletTab, final boolean nextFocusOnTblWalletDetail) {
		return(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					int walletValidationPolicy = getWalletValidationPolicy();
					try {
						if ((jOldComponent.getName() == SpiritConstants.getNullValue()) || jOldComponent.getName().equals("txtEmissionDate") || jOldComponent.getName().equals("txtLegalName") || ((jOldComponent.getName().equals("txtDetailValue") && walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash())))) {
							if (!nextFocusOnTblWalletDetail) {
								jNextComponent.grabFocus();
								if (changeJtpWalletTab) {
									if (getJtpWallet().getSelectedIndex() == 0)
										getJtpWallet().setSelectedIndex(1);
									else
										getJtpWallet().setSelectedIndex(0);
								}
							} else if (getTblWalletDetail().getRowCount() > 0) {
								getTblWalletDetail().grabFocus();
								getTblWalletDetail().setRowSelectionInterval(0, 0);
							} else
								getBtnHit().grabFocus();
						} else if (jOldComponent.getName().equals("txtDetailValue")) {
							if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck()))
								getCmbCheckBank().grabFocus();
							if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit()))
								getCmbDebitBank().grabFocus();
							if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer()))
								getCmbSourceBank().grabFocus();
							if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard()))
								getCmbCreditCardBank().grabFocus();
							if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment()))
								getTxtElectronicPaymentReference().grabFocus();
							if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention()))
								getTxtRetentionNumber().grabFocus();
						}
					} catch (GenericBusinessException ex) {
						ex.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error al actualizar estado de la pantalla", SpiritAlert.ERROR);
					}
				}
			}
		});
	}

	private void loadComboBoxes() {
		loadComboBoxDocumentType();
		loadComboBoxCurrency();
		if (this.getMode() == SpiritMode.FIND_MODE) {
			getCmbDocumentType().setSelectedIndex(-1);
			getCmbDocument().setSelectedIndex(-1);
			getCmbCurrency().setSelectedIndex(-1);
		}
	}
	
	private void setSelectedPropertyComponents() {
		if (this.getMode() == SpiritMode.FIND_MODE) {
			getRbCustomer().setSelected(false);
			getRbProvider().setSelected(false);
		} else if (this.getMode() == SpiritMode.SAVE_MODE) {
			getRbCustomer().setSelected(true);
		}
	}

	private void setEnabledPropertyComponents() {
		if (this.getMode() != SpiritMode.FIND_MODE)
			getCbAnulled().setEnabled(false);
		else
			getCbAnulled().setEnabled(true);
		if (this.getMode() == SpiritMode.FIND_MODE)
			getCmbCurrency().setEnabled(false);
		else
			getCmbCurrency().setEnabled(true);
	}

	private void setFocusablePropertyComponents() {
		getJtpTransactionDetail().setFocusable(false);
		getTxtOffice().setFocusable(false);
		getTxtCreationDate().setFocusable(false);
		getTxtLastUpdateDate().setFocusable(false);
		if (this.getMode() == SpiritMode.SAVE_MODE)
			getCbAnulled().setFocusable(false);
		else
			getCbAnulled().setFocusable(true);
		getTxtIdentification().setFocusable(false);
		getTxtCityAddress().setFocusable(false);
		getTxtTotal().setFocusable(false);
		getTxtBalance().setFocusable(false);
		getTxtDetailBalance().setFocusable(false);
	}

	@SuppressWarnings("unchecked")
	private void loadComboBoxDocumentType() {
		Long moduleId;
		try {
			moduleId = ((ModuloIf) SessionServiceLocator.getModuloSessionService().findModuloByCodigo(WalletConstants.getCodeWalletModule()).iterator().next()).getId();
			boolean isCustomerWalletType = (getRbCustomer().isSelected())?true:false;
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("empresaId", Parametros.getIdEmpresa());
			queryMap.put("moduloId", moduleId);
			queryMap.put("tipocartera", (isCustomerWalletType)?SpiritConstants.getCustomerWalletType().substring(0,1):SpiritConstants.getProviderWalletType().substring(0,1));
			List<Object> documentTypes = (ArrayList<Object>) SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByQuery(queryMap);
			//documentTypes.add(0, (TipoDocumentoIf) SpiritConstants.getNullValue());
			documentTypes.add(0, SpiritConstants.getPlaceholderCharacter());
			refreshCombo(getCmbDocumentType(), documentTypes);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}			
	}

	@SuppressWarnings("unchecked")
	private void loadComboBoxCurrency() {
		try {
			List<MonedaIf> currencies = (ArrayList<MonedaIf>) SessionServiceLocator.getMonedaSessionService().getMonedaList();
			refreshCombo(getCmbCurrency(), currencies);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar monedas", SpiritAlert.ERROR);
		}
	}
	
	private void cleanBusinessOperatorData() {
		getTxtLegalName().setText(SpiritConstants.getEmptyCharacter());
		getTxtIdentification().setText(SpiritConstants.getEmptyCharacter());
		getTxtCityAddress().setText(SpiritConstants.getEmptyCharacter());
		setBusinessOperator((ClienteIf) SpiritConstants.getNullValue());
		setBusinessOperatorOffice((ClienteOficinaIf) SpiritConstants.getNullValue());
	}
	
	private void changeWalletType(JRadioButton rbWalletType, String walletType) {
		getRbCustomer().removeActionListener(getRbCustomerWalletTypeActionListener());
		getRbProvider().removeActionListener(getRbProviderWalletTypeActionListener());
		if (rbWalletType.isSelected()) {
			if (getBusinessOperator() != SpiritConstants.getNullValue()) {
				try {
					TipoClienteIf businessOperatorType = SessionServiceLocator.getTipoClienteSessionService().getTipoCliente(getBusinessOperator().getTipoclienteId());
					if (!businessOperatorType.getCodigo().equals(walletType.substring(0,2))) {
						int option = JOptionPane.NO_OPTION;
						setVoidTransaction(false);
						if (this.getMode() == SpiritMode.SAVE_MODE)
							option = JOptionPane.showOptionDialog(null, "Esta acción removerá los siguientes datos del presente comprobante:\n\n-> Información de operador de negocios\n-> Detalles del comprobante\n\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
						else if (this.getMode() == SpiritMode.UPDATE_MODE) {
							//option = JOptionPane.showOptionDialog(null, "La acción que desea realizar requiere la anulación del presente comprobante\n\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
							//setVoidTransaction(true);
							showSaveMode();
						}
						if (option == JOptionPane.YES_OPTION && !isVoidTransaction()) {
							cleanBusinessOperatorData();
							for (int i=0; i<getTblWalletDetail().getRowCount(); i++)
								getTblWalletDetail().setValueAt(new Boolean(true), i, WalletConstants.getTblWalletDetailSelectionColumnIndex());
							deleteDetailGroup();
						} /*else if (option == JOptionPane.YES_OPTION && isVoidTransaction()) {
							delete();
							setVoidTransaction(false);
						}*/ else {
							if (walletType.equals(SpiritConstants.getCustomerWalletType())) {
								getRbProvider().setSelected(true);
								getRbCustomer().setSelected(false);
							} else {
								getRbCustomer().setSelected(true);
								getRbProvider().setSelected(false);
							}
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al actualizar datos en pantalla", SpiritAlert.ERROR);
				}
			}
		}
		getRbProvider().validate();
		getRbProvider().repaint();
		getRbCustomer().validate();
		getRbCustomer().repaint();
		loadComboBoxDocumentType();
		getRbCustomer().addActionListener(getRbCustomerWalletTypeActionListener());
		getRbProvider().addActionListener(getRbProviderWalletTypeActionListener());
	}

	private void initListeners() {
		getRbCustomer().addActionListener(getRbCustomerWalletTypeActionListener());
		
		getRbProvider().addActionListener(getRbProviderWalletTypeActionListener());
		
		getCmbDocumentType().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				try {
					refreshCmbDocument();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al cargar tipos de documentos", SpiritAlert.ERROR);
				}
			}
		});
		getCmbDocument().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					manageJtpTransactionDetail();
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al cargar documentos", SpiritAlert.ERROR);
				}
			}			
		});
		getTxtLegalName().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ev) {
				if (ev.getKeyChar() == KeyEvent.VK_ENTER)
					try {
						if (getMode() == SpiritMode.UPDATE_MODE) {
							/*int option = JOptionPane.showOptionDialog(null, "La acción que desea realizar requiere la anulación del presente comprobante\n\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
							if (option == JOptionPane.YES_OPTION) {
								setVoidTransaction(true);
								delete();
								setVoidTransaction(false);
							} else {*/
								setBusinessOperatorData();
								getBtnSearchBusinessOperator().grabFocus();
							//}
						} else {
							String message = receiptHasBusinessOperatorAccountBankRelated();
							if (getMode() == SpiritMode.SAVE_MODE && !message.equals(SpiritConstants.getEmptyCharacter())) {
								int option = JOptionPane.showOptionDialog(null, "La acción que desea realizar removerá los siguientes detalles del presente comprobante:\n\n" + message + "\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
								if (option == JOptionPane.YES_OPTION) {
									boolean notDeletedDetails = deleteDetailGroup();
									if (!notDeletedDetails)
										searchBusinessOperator();
									else {
										setBusinessOperatorData();
										getBtnSearchBusinessOperator().grabFocus();
									}
								} else {
									setBusinessOperatorData();
									getBtnSearchBusinessOperator().grabFocus();
								}
							} else {
								searchBusinessOperator();
							}							
							for (int i=0; i<getWalletDetailDataVector().size(); i++)
								getTblWalletDetail().setValueAt(new Boolean(false), getTblWalletDetail().convertRowIndexToView(i), WalletConstants.getTblWalletDetailSelectionColumnIndex());
						}
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error al buscar operador de negocio", SpiritAlert.ERROR);
					}
			}
		});
		getBtnSearchBusinessOperator().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					if (getMode() == SpiritMode.UPDATE_MODE) {
						/*int option = JOptionPane.showOptionDialog(null, "La acción que desea realizar requiere la anulación del presente comprobante\n\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
						if (option == JOptionPane.YES_OPTION) {
							setVoidTransaction(true);
							delete();
							setVoidTransaction(false);
						} else*/
							setBusinessOperatorData();
					} else {
						String message = receiptHasBusinessOperatorAccountBankRelated();
						if (getMode() == SpiritMode.SAVE_MODE && !message.equals(SpiritConstants.getEmptyCharacter())) {
							int option = JOptionPane.showOptionDialog(null, "La acción que desea realizar removerá los siguientes detalles del presente comprobante:\n\n" + message + "\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
							if (option == JOptionPane.YES_OPTION) {
								boolean notDeletedDetails = deleteDetailGroup();
								if (!notDeletedDetails) {
									showBusinessOperatorFinder(-1, new HashMap<String, Object>(), JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, false);
								} else
									setBusinessOperatorData();
							} else
								setBusinessOperatorData();
						} else {
							showBusinessOperatorFinder(-1, new HashMap<String, Object>(), JDPopupFinderModel.BUSQUEDA_POR_PARAMETROS, false);
						}
						for (int i=0; i<getWalletDetailDataVector().size(); i++)
							getTblWalletDetail().setValueAt(new Boolean(false), getTblWalletDetail().convertRowIndexToView(i), WalletConstants.getTblWalletDetailSelectionColumnIndex());
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al buscar operador de negocio", SpiritAlert.ERROR);
				}
			}
		});
		getBtnAddBusinessOperator().addActionListener(new ActionListener()  {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
				SpiritModel businessOperatorModel = (SpiritModel) new OperadoresNegocioModel();
				Map panels = MainFrameModel.get_panels();
				if (panels.size()>0 && panels.get("Operadores de Negocio") != null) {
					int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana de operadores de negocio?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
					if (opcion == JOptionPane.YES_OPTION) {
						MainFrameModel.get_dockingManager().removeFrame("Operadores de Negocio");
					}
				}
				PanelHandler.showPanelModel(businessOperatorModel);
			}
		});
		getLblViewWalletAccountingEntry().addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			public void mouseReleased(MouseEvent ev) {
				Vector<Integer> widthColumns = new Vector<Integer>();
				widthColumns.addElement(100);
				widthColumns.addElement(60);
				widthColumns.addElement(220);
				Map<String, Object> queryMap = new HashMap<String, Object>();
				TipoDocumentoIf documentType = ((TipoDocumentoIf) getCmbDocumentType().getSelectedItem());
				queryMap.put("tipoDocumentoId", documentType.getId());
				if (documentType.getFactura().equals(SpiritConstants.getOptionYes().substring(0,1)) || documentType.getNotaVenta().equals(SpiritConstants.getOptionYes().substring(0,1)) || documentType.getNotaCredito().equals(SpiritConstants.getOptionYes().substring(0,1)) || documentType.getLiquidacionCompras().equals(SpiritConstants.getOptionYes().substring(0,1)))
					queryMap.put("transaccionId", (Long) getTxtReferenceId().getValue());
				else
					queryMap.put("transaccionId", (Long) getTxtWalletId().getValue());
				try {
					int listSize = SessionServiceLocator.getAsientoSessionService().getAsientoListSize(queryMap);
					if (listSize > 0) {
						AsientoCriteria accountingEntryCriteria = new AsientoCriteria();
						accountingEntryCriteria.setResultListSize(listSize);
						accountingEntryCriteria.setQueryBuilded(queryMap);
						setPopupFinder(new JDPopupFinderModel(Parametros.getMainFrame(), accountingEntryCriteria, JDPopupFinderModel.BUSQUEDA_TODOS, widthColumns, (Map) SpiritConstants.getNullValue()));
						if (getPopupFinder().getElementoSeleccionado() != SpiritConstants.getNullValue()) {
							AsientoIf accountingEntry = (AsientoIf) getPopupFinder().getElementoSeleccionado();
							//--
							SpiritModel accountingEntryPanel = (SpiritModel) new AsientoModel(accountingEntry);
							Map panels = MainFrameModel.get_panels();
							if (panels.size()>0 && panels.get("Asiento") != SpiritConstants.getNullValue()) {
								int option = JOptionPane.showOptionDialog(null, "¿Desea cerrar la ventana Asiento?, se perderá la información que no haya sido guardada", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
								if (option == JOptionPane.YES_OPTION) {
									MainFrameModel.get_dockingManager().removeFrame("Asiento");
								}
							}
							PanelHandler.showPanelModel(accountingEntryPanel);
						}
					} else
						SpiritAlert.createAlert("No se encontraron registros",SpiritAlert.INFORMATION);
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error al consultar los datos", SpiritAlert.ERROR);
				}
			}
			public void mouseEntered(MouseEvent ev) {
				Color foreground = new Color(255,0,255);
				getLblViewWalletAccountingEntry().setForeground(foreground);
			}
			public void mouseExited(MouseEvent ev) {
				Color foreground = new Color(0,0,255);
				getLblViewWalletAccountingEntry().setForeground(foreground);
			}
		});
		getTxtDetailValue().addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				getTxtDetailBalance().setValue(getTxtDetailValue().getValue());
			}
		});
		getBtnAddDetail().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nextSequentialNumber = getMaximumSequentialNumber() + 1;
				setMaximumSequentialNumber(nextSequentialNumber);
				addDetail();
			}			
		});
		getBtnUpdateDetail().addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if (getTblWalletDetail().getSelectedRow() != -1) {
					WalletDetailData walletDetailData = getWalletDetailDataVector().get(getSelectedWalletDetailIndex());
					if (walletDetailData.getValue().compareTo(walletDetailData.getBalance()) == 0)
						updateDetail();
					else
						SpiritAlert.createAlert("Registro seleccionado no puede ser actualizado debido a que está cruzado.", SpiritAlert.WARNING);
				}
			}
		});
		getBtnDeleteDetail().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getSelectedRowsCount() == 0) {
					if (getTblWalletDetail().getSelectedRow() != -1) {
						WalletDetailData walletDetailData = getWalletDetailDataVector().get(getSelectedWalletDetailIndex());
						if (walletDetailData.getValue().compareTo(walletDetailData.getBalance()) == 0)
							deleteDetail();
						else
							SpiritAlert.createAlert("Registro seleccionado no puede ser eliminado debido a que está cruzado.", SpiritAlert.WARNING);
					}
				} else
					deleteDetailGroup();
			}
		});
		getBtnHit().addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent evt) {
				WalletData walletData = setWalletData();
				Vector<WalletDetailData> selectedWalletDetailDataVector = getSelectedWalletDetailDataVector();
				Vector<CrossingWalletDetailData> crossingWalletDetailsVector = getCrossingWalletDetailsVector();
				try {
					if (WalletValidationHandler.validate(walletData, (Vector<WalletDetailData>) SpiritConstants.getNullValue(), getWalletModel())) {
						if (getSelectedRowsCount() > 0 || getTblWalletDetail().getSelectedRow() != -1) {
							if (validateRetrocompatibleFields(selectedWalletDetailDataVector)) {
								showJDApplyWalletTransaction(walletData, selectedWalletDetailDataVector, crossingWalletDetailsVector);
								reloadTblWalletDetail();
							}
						} else
							SpiritAlert.createAlert("Debe seleccionar documento(s) a cruzar", SpiritAlert.WARNING);
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
					SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
				}
			}
		});
		getCmbCheckBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getCmbCheckBank().getSelectedItem() != null) {
					try {
						BancoIf bank = (BancoIf) getCmbCheckBank().getSelectedItem();
						if ((getRbCustomer().isSelected() && getBusinessOperatorOffice() != null) || getRbProvider().isSelected()) {
							getLblStatusMessage().setVisible(false);
							getLblStatusMessage().setText(SpiritConstants.getEmptyCharacter());
							loadComboBoxAccountByBank(getCmbCheckAccount(), bank, getRbCustomer().isSelected());
						} else {
							getLblStatusMessage().setVisible(true);
							getLblStatusMessage().setText("Para cargar listado de cuentas bancarias debe seleccionar operador de negocio");
							getCmbCheckBank().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbCheckBank(), bank.getId()));
							getCmbCheckBank().validate();
							getCmbCheckBank().repaint();
						}
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}
		});
		getBtnAddCheckAccount().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ((getRbCustomer().isSelected() && getBusinessOperatorOffice() != null) || getRbProvider().isSelected()) {
					addAccountBank(getCmbCheckBank(), getCmbCheckAccount(), getRbCustomer().isSelected()?getBusinessOperator():(ClienteIf) SpiritConstants.getNullValue(), getRbCustomer().isSelected());
				} else {
					SpiritAlert.createAlert("Para agregar nueva cuenta bancaria debe seleccionar operador de negocio", SpiritAlert.WARNING);
					getJtpWallet().setSelectedIndex(WalletConstants.getMasterTab());
					getTxtLegalName().requestFocusInWindow();
				}
			}
		});
		getCmbDepositBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getCmbDepositBank().getSelectedItem() != null) {
					try {
						loadComboBoxAccountByBank(getCmbDepositAccount(), (BancoIf) getCmbDepositBank().getSelectedItem(), false);
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}
		});
		getBtnAddDepositAccount().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ((getRbProvider().isSelected() && getBusinessOperatorOffice() != null) || getRbCustomer().isSelected()) {
					addAccountBank(getCmbDepositBank(), getCmbDepositAccount(), getRbProvider().isSelected()?getBusinessOperator():(ClienteIf) SpiritConstants.getNullValue(), getRbProvider().isSelected());
				} else {
					SpiritAlert.createAlert("Para agregar nueva cuenta bancaria debe seleccionar operador de negocio", SpiritAlert.WARNING);
					getJtpWallet().setSelectedIndex(WalletConstants.getMasterTab());
					getTxtLegalName().requestFocusInWindow();
				}
			}
		});
		getCmbDebitBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getCmbDebitBank().getSelectedItem() != null) {
					try {
						loadComboBoxAccountByBank(getCmbDebitAccount(), (BancoIf) getCmbDebitBank().getSelectedItem(), false);
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}			
		});
		getCmbSourceBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*if (getCmbSourceBank().getSelectedItem() != null) {
					try {
						loadComboBoxAccountByBank(getCmbSourceAccount(), (BancoIf) getCmbSourceBank().getSelectedItem(), true);
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}*/
				if (getCmbSourceBank().getSelectedItem() != null) {
					try {
						BancoIf bank = (BancoIf) getCmbSourceBank().getSelectedItem();
						if ((getRbCustomer().isSelected() && getBusinessOperatorOffice() != null) || getRbProvider().isSelected()) {
							getLblStatusMessage().setVisible(false);
							getLblStatusMessage().setText(SpiritConstants.getEmptyCharacter());
							loadComboBoxAccountByBank(getCmbSourceAccount(), bank, getRbCustomer().isSelected());
						} else {
							getLblStatusMessage().setVisible(true);
							getLblStatusMessage().setText("Para cargar listado de cuentas bancarias debe seleccionar operador de negocio");
							getCmbSourceBank().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbSourceBank(), bank.getId()));
							getCmbSourceBank().validate();
							getCmbSourceBank().repaint();
						}
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}
		});
		getBtnAddSourceAccount().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ((getRbCustomer().isSelected() && getBusinessOperatorOffice() != null) || getRbProvider().isSelected()) {
					addAccountBank(getCmbSourceBank(), getCmbSourceAccount(), getRbCustomer().isSelected()?getBusinessOperator():(ClienteIf) SpiritConstants.getNullValue(), getRbCustomer().isSelected());
				} else {
					SpiritAlert.createAlert("Para agregar nueva cuenta bancaria debe seleccionar operador de negocio", SpiritAlert.WARNING);
					getJtpWallet().setSelectedIndex(WalletConstants.getMasterTab());
					getTxtLegalName().requestFocusInWindow();
				}
			}
		});
		getCmbTargetBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/*if (getCmbTargetBank().getSelectedItem() != null) {
					try {
						loadComboBoxAccountByBank(getCmbTargetAccount(), (BancoIf) getCmbTargetBank().getSelectedItem(), false);
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}*/
				
				if (getCmbTargetBank().getSelectedItem() != null) {
					try {
						BancoIf bank = (BancoIf) getCmbTargetBank().getSelectedItem();
						if ((getRbProvider().isSelected() && getBusinessOperatorOffice() != null) || getRbCustomer().isSelected()) {
							getLblStatusMessage().setVisible(false);
							getLblStatusMessage().setText(SpiritConstants.getEmptyCharacter());
							loadComboBoxAccountByBank(getCmbTargetAccount(), bank, getRbProvider().isSelected());
						} else {
							getLblStatusMessage().setVisible(true);
							getLblStatusMessage().setText("Para cargar listado de cuentas bancarias debe seleccionar operador de negocio");
							getCmbTargetBank().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbTargetBank(), bank.getId()));
							getCmbTargetBank().validate();
							getCmbTargetBank().repaint();
						}
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}
		});
		getBtnAddTargetAccount().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if ((getRbProvider().isSelected() && getBusinessOperatorOffice() != null) || getRbCustomer().isSelected()) {
					addAccountBank(getCmbTargetBank(), getCmbTargetAccount(), getRbProvider().isSelected()?getBusinessOperator():(ClienteIf) SpiritConstants.getNullValue(), getRbProvider().isSelected());
				} else {
					SpiritAlert.createAlert("Para agregar nueva cuenta bancaria debe seleccionar operador de negocio", SpiritAlert.WARNING);
					getJtpWallet().setSelectedIndex(WalletConstants.getMasterTab());
					getTxtLegalName().requestFocusInWindow();
				}
			}
		});
		getCmbCreditCardBank().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (getCmbCreditCardBank().getSelectedItem() != null) {
					try {
						loadComboBoxCreditCardByBank(getCmbCreditCard(), (BancoIf) getCmbCreditCardBank().getSelectedItem(), false);
					} catch (GenericBusinessException e) {
						e.printStackTrace();
						SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
					}
				}
			}
		});
		getTblWalletDetail().addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent evt) {
				selectDetail(evt);
			}
		});
		getTblWalletDetail().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				selectDetail(evt);
			}
		});
		getTxtEmissionDate().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				//TODO: 
			}
		});
		getTxtEmissionDate().addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				//TODO: 
			}			
		});
		getTblWalletDetail().addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint(); 
				int row = getTblWalletDetail().rowAtPoint(p);
				int column = getTblWalletDetail().columnAtPoint(p);
				if (column == WalletConstants.getTblWalletDetailTransactionColumnIndex()) {
					String toolTipText = getTblWalletDetail().getValueAt(row,column) != null?String.valueOf(getTblWalletDetail().getValueAt(row,column)):SpiritConstants.getEmptyCharacter();
					getTblWalletDetail().setToolTipText(toolTipText);
				}
			}
		});
		/*getTxtDetailValue().addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				 SwingUtilities.invokeLater( new Runnable() {
                     public void run() {
                    	 getTxtDetailValue().selectAll();              
                     }
				 });
            }
		});*/
	}
	
	private void setTblWalletDetailVkEnterCustomBehavior() {
		getTblWalletDetail().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItem");
    	getTblWalletDetail().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "selectItem");
    	getTblWalletDetail().getActionMap().put("selectItem", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			// Precaución: ¡No borrar! 
    			// Esta sección se deja en blanco para evitar el comportamiento default de la tecla ENTER dentro de la tabla de detalles.
    			getTblWalletDetail().clearSelection();
    		}
    	});
	}
	
	private void setTblWalletDetailVkSpaceCustomBehavior() {
		getTblWalletDetail().getInputMap(JInternalFrame.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "hitItem");
    	getTblWalletDetail().getInputMap(JInternalFrame.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "hitItem");
    	getTblWalletDetail().getActionMap().put("hitItem", new AbstractAction() {
    		public void actionPerformed(ActionEvent ae) {
    			int selectedRow = getTblWalletDetail().getSelectedRow();
    			if (selectedRow > -1) {
    				boolean selected = ((Boolean) getTblWalletDetail().getValueAt(selectedRow, WalletConstants.getTblWalletDetailSelectionColumnIndex())).booleanValue();
    				getTblWalletDetail().setValueAt(new Boolean(!selected), selectedRow, WalletConstants.getTblWalletDetailSelectionColumnIndex());
    			}
    		}
    	});
	}
		
	ActionListener rbCustomerWalletTypeActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			changeWalletType(getRbCustomer(), SpiritConstants.getCustomerWalletType());
		}
	};
	
	ActionListener rbProviderWalletTypeActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			changeWalletType(getRbProvider(), SpiritConstants.getProviderWalletType());
		}
	};

	private String receiptHasBusinessOperatorAccountBankRelated() {
		String message = SpiritConstants.getEmptyCharacter();
		for (int i=0; i<getWalletDetailDataVector().size(); i++) {
			WalletDetailData walletDetailData = getWalletDetailDataVector().get(i);
			DocumentoIf document = walletDetailData.getDocument();
			if ((getRbCustomer().isSelected() && document.getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) || document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1)) || document.getTransferenciaBancaria().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				message += walletDetailData.getComment() + "\n";
				getTblWalletDetail().setValueAt(new Boolean(true), getTblWalletDetail().convertRowIndexToView(i), WalletConstants.getTblWalletDetailSelectionColumnIndex());
			}
		}
		return message;
	}	
	
	private void addAccountBank(JComboBox cmbBank, JComboBox cmbAccountBank, ClienteIf businessOperator, boolean relatedBusinessOperator) {
		BancoIf bank = (BancoIf) cmbBank.getSelectedItem();
		AddingAccountBankModel addingAccountBankModel = new AddingAccountBankModel(Parametros.getMainFrame(), bank, businessOperator, relatedBusinessOperator);
		try {
			if (!addingAccountBankModel.isCancelAction()) {
				loadComboBoxBank(cmbBank, relatedBusinessOperator);
				cmbBank.setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(cmbBank, addingAccountBankModel.getAddedAccountBank().getBancoId()));
				cmbBank.validate();
				cmbBank.repaint();
				bank = (BancoIf) cmbBank.getSelectedItem();
				loadComboBoxAccountByBank(cmbAccountBank, bank, relatedBusinessOperator);
				cmbAccountBank.setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(cmbAccountBank, addingAccountBankModel.getAddedAccountBank().getId()));
				cmbAccountBank.validate();
				cmbAccountBank.repaint();
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar listado de cuentas bancarias", SpiritAlert.ERROR);
		}
	}
	
	private void reloadTblWalletDetail() {
		cleanTables();
		for (int i=0; i<getWalletDetailDataVector().size(); i++) {
			WalletDetailData  walletDetailData = getWalletDetailDataVector().get(i);
			addRowToTblWalletDetail(walletDetailData);
		}
		calculateBalance();
	}
	
	private Vector<WalletDetailData> getSelectedWalletDetailDataVector() {
		Vector<WalletDetailData> selectedWalletDetailDataVector = new Vector<WalletDetailData>();
		if (getSelectedRowsCount() == 0) {
			if (getTblWalletDetail().getSelectedRow() != -1)
				selectedWalletDetailDataVector.add(getWalletDetailDataVector().get(getSelectedWalletDetailIndex()));
		} else {
			for (int i=0; i<getTblWalletDetail().getRowCount(); i++)
				if ((Boolean) getTblWalletDetail().getValueAt(i, 0)) {
					selectedWalletDetailDataVector.add(getWalletDetailDataVector().get(getTblWalletDetail().convertRowIndexToModel(i)));
				}
		}
		return selectedWalletDetailDataVector;
	}
	
	private void showJDApplyWalletTransaction(WalletData walletData, Vector<WalletDetailData> selectedWalletDetailDataVector, Vector<CrossingWalletDetailData> crossingWalletDetailsVector) {
		new ApplyWalletTransactionModel(Parametros.getMainFrame(), walletData, selectedWalletDetailDataVector, crossingWalletDetailsVector, this.getMode());
	}
	
	private void selectDetail(ComponentEvent evt) {
		if (getTblWalletDetail().getSelectedRow() != -1) {
			setSelectedWalletDetailIndex(((JTable) evt.getSource()).convertRowIndexToModel(getTblWalletDetail().getSelectedRow()));
			setSelectedRowTblWalletDetail(getTblWalletDetail().getSelectedRow());
			WalletDetailData walletDetailData = getWalletDetailDataVector().get(getSelectedWalletDetailIndex());
			showWalletDetailData(walletDetailData);
		}
	}
	
	private void showWalletDetailData(WalletDetailData walletDetailData) {
		getCmbDocument().setSelectedIndex(ComboBoxComponent.getIndexToSelectItem(getCmbDocument(), walletDetailData.getDocument().getId()));
		getCmbDocument().validate();
		getCmbDocument().repaint();
		if (walletDetailData.getWalletDetailId() != null)
			getTxtWalletDetailId().setValue(walletDetailData.getWalletDetailId());
		walletDetailData.setDocument((DocumentoIf) getCmbDocument().getSelectedItem());
		getTxtDetailValue().setValue(walletDetailData.getValue());
		getTxtDetailBalance().setValue(walletDetailData.getBalance());
		try {
			if (!isActivatedRetrocompatibility()) {
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash())) {
					// TODO
				}
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck())) {
					getCmbCheckBank().setSelectedIndex(walletDetailData.getCheckBank() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCheckBank(), walletDetailData.getCheckBank().getId()):BigDecimal.ONE.negate().intValue());
					getCmbCheckAccount().setSelectedIndex(walletDetailData.getCheckAccount() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCheckAccount(), walletDetailData.getCheckAccount().getId()):BigDecimal.ONE.negate().intValue());
					getTxtCheckNumber().setValue(walletDetailData.getCheckNumber() != null?walletDetailData.getCheckNumber():SpiritConstants.getEmptyCharacter());
					getCmbDepositBank().setSelectedIndex(walletDetailData.getDepositBank() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDepositBank(), walletDetailData.getDepositBank().getId()):BigDecimal.ONE.negate().intValue());
					getCmbDepositAccount().setSelectedIndex(walletDetailData.getDepositAccount() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDepositAccount(), walletDetailData.getDepositAccount().getId()):BigDecimal.ONE.negate().intValue());
				}
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention())) {
					getTxtRetentionNumber().setValue(walletDetailData.getRetentionNumber() != null?walletDetailData.getRetentionNumber():SpiritConstants.getEmptyCharacter());
					getTxtRetentionAuthorization().setValue(walletDetailData.getRetentionAuthorization() != null?walletDetailData.getRetentionAuthorization():SpiritConstants.getEmptyCharacter());
					if (getRbCustomer().isSelected())
						getCmbRetentionPercentage().setSelectedIndex(walletDetailData.getSriRetentionPercentageDefinition() != null?ComboBoxComponent.getIndexToSelectItem(getCmbRetentionPercentage(), walletDetailData.getSriRetentionPercentageDefinition().getId()):BigDecimal.ONE.negate().intValue());
					else if (getRbProvider().isSelected())
						getCmbRetentionPercentage().setSelectedIndex(walletDetailData.getRetentionPercentage() != null?ComboBoxComponent.getIndexToStringSelectItem(getCmbRetentionPercentage(), walletDetailData.getRetentionPercentage()):BigDecimal.ONE.negate().intValue());
				}
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit())) {
					getCmbDebitBank().setSelectedIndex(walletDetailData.getDebitBank() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDebitBank(), walletDetailData.getDebitBank().getId()):BigDecimal.ONE.negate().intValue());
					getCmbDebitAccount().setSelectedIndex(walletDetailData.getDebitAccount() != null?ComboBoxComponent.getIndexToSelectItem(getCmbDebitAccount(), walletDetailData.getDebitAccount().getId()):BigDecimal.ONE.negate().intValue());
					getTxtDebitReference().setText(walletDetailData.getDebitReference() != null?walletDetailData.getDebitReference():SpiritConstants.getEmptyCharacter());
				}
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer())) {
					getCmbSourceBank().setSelectedIndex(walletDetailData.getSourceBank() != null?ComboBoxComponent.getIndexToSelectItem(getCmbSourceBank(), walletDetailData.getSourceBank().getId()):BigDecimal.ONE.negate().intValue());
					getCmbSourceAccount().setSelectedIndex(walletDetailData.getSourceAccount() != null?ComboBoxComponent.getIndexToSelectItem(getCmbSourceAccount(), walletDetailData.getSourceAccount().getId()):BigDecimal.ONE.negate().intValue());
					getCmbTargetBank().setSelectedIndex(walletDetailData.getTargetBank() != null?ComboBoxComponent.getIndexToSelectItem(getCmbTargetBank(), walletDetailData.getTargetBank().getId()):BigDecimal.ONE.negate().intValue());
					getCmbTargetAccount().setSelectedIndex(walletDetailData.getTargetAccount() != null?ComboBoxComponent.getIndexToSelectItem(getCmbTargetAccount(), walletDetailData.getTargetAccount().getId()):BigDecimal.ONE.negate().intValue());
				}
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard())) {
					getCmbCreditCardBank().setSelectedIndex(walletDetailData.getCreditCardBank() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCreditCardBank(), walletDetailData.getCreditCardBank().getId()):BigDecimal.ONE.negate().intValue());
					getCmbCreditCard().setSelectedIndex(walletDetailData.getCreditCard() != null?ComboBoxComponent.getIndexToSelectItem(getCmbCreditCard(), walletDetailData.getCreditCard().getId()):BigDecimal.ONE.negate().intValue());
					getTxtVoucherReference().setText(walletDetailData.getVoucherReference() != null?walletDetailData.getVoucherReference():SpiritConstants.getEmptyCharacter());
				}
				if (getWalletValidationPolicy() == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment())) {
					getTxtElectronicPaymentReference().setText(walletDetailData.getElectronicPaymentReference() != null?walletDetailData.getElectronicPaymentReference():SpiritConstants.getEmptyCharacter());
				}
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al cargar los datos", SpiritAlert.ERROR);
		}

	}

	private void manageJtpTransactionDetail() throws GenericBusinessException {
		DocumentoIf document = (getCmbDocument().getSelectedItem() != SpiritConstants.getNullValue() && !getCmbDocument().getSelectedItem().toString().equals(SpiritConstants.getPlaceholderCharacter()))?(DocumentoIf) getCmbDocument().getSelectedItem():(DocumentoIf) SpiritConstants.getNullValue();
		int index = -1;
		if (document != SpiritConstants.getNullValue()) {
			if (document.getEfectivo() != SpiritConstants.getNullValue() && document.getEfectivo().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionCashPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash());
			} else if (document.getCheque() != SpiritConstants.getNullValue() && document.getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionCheckPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck());
			} else if (document.getDebitoBancario() != SpiritConstants.getNullValue() && document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionDebitPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit());
			} else if (document.getTarjetaCredito() != SpiritConstants.getNullValue() && document.getTarjetaCredito().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionCreditCardPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard());
			} else if (document.getTransaccionElectronica() != SpiritConstants.getNullValue() && document.getTransaccionElectronica().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionElectronicPaymentPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment());
			} else if (document.getTransferenciaBancaria() != SpiritConstants.getNullValue() && document.getTransferenciaBancaria().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionTransferPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer());
			} else if (document.getRetencionIva() != SpiritConstants.getNullValue() && document.getRetencionIva().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionRetentionPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention());
			} else if (document.getRetencionRenta() != SpiritConstants.getNullValue() && document.getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				initTransactionRetentionPanel();
				index = JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention());
			} else {
				// For all other cases
				initTransactionCashPanel();
			}
		}
		
		enableJtpTransactionDetailTab(index);
	}

	private void initTransactionCashPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCash()));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	private void initTransactionCheckPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck()));
			loadComboBoxBank(getCmbCheckBank(), true);
			loadComboBoxBank(getCmbDepositBank(), false);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	private void initTransactionDebitPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionDebit()));
			loadComboBoxBank(getCmbDebitBank(), false);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	private void initTransactionCreditCardPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCreditCard()));
			loadComboBoxBank(getCmbCreditCardBank(), true);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	private void initTransactionElectronicPaymentPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionElectronicPayment()));
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	private void initTransactionTransferPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionTransfer()));
			if (getRbCustomer().isSelected()) {
				loadComboBoxBank(getCmbSourceBank(), true);
				loadComboBoxBank(getCmbTargetBank(), false);
			} else if (getRbProvider().isSelected()) {
				loadComboBoxBank(getCmbSourceBank(), false);
				loadComboBoxBank(getCmbTargetBank(), true);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	private void initTransactionRetentionPanel() {
		try {
			setWalletValidationPolicy(JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionRetention()));
			loadComboBoxRetentionPercentage(getCmbRetentionPercentage());
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al inicializar el panel", SpiritAlert.ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadComboBoxBank(JComboBox jCmbBank, boolean relatedBusinessOperator) throws GenericBusinessException {
		List<BancoIf> banks = new ArrayList<BancoIf>();
		if (relatedBusinessOperator) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("estado", SpiritConstants.getActiveStatus().substring(0,1));
			banks = (List<BancoIf>) SessionServiceLocator.getBancoSessionService().findBancoByQuery(queryMap);
		} else
			banks = (List<BancoIf>) SessionServiceLocator.getBancoSessionService().findBancosDeCuentaBancariasPorEmpresa(Parametros.getIdEmpresa()); 
		refreshCombo(jCmbBank, banks);
	}
	
	@SuppressWarnings("unchecked")
	private void loadComboBoxAccountByBank(JComboBox jCmbAccount, BancoIf bank, boolean relatedBusinessOperator) throws GenericBusinessException {
		if (bank != null) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("empresaId", Parametros.getIdEmpresa());
			queryMap.put("bancoId", bank.getId());
			int walletValidationPolicy = getWalletValidationPolicy();
			if (walletValidationPolicy == JtpWalletDetailTabEnum.getIndexTabByName(WalletConstants.getTransactionCheck())) {
				if ((getRbCustomer().isSelected() && relatedBusinessOperator) || (getRbProvider().isSelected() && !relatedBusinessOperator))
					queryMap.put("tipocuenta", WalletConstants.getCheckingAccount().substring(0,1));
			} if (relatedBusinessOperator && getBusinessOperatorOffice() != null) {
				queryMap.put("cuentaCliente", SpiritConstants.getOptionYes().substring(0,1));
				queryMap.put("clienteId", getBusinessOperatorOffice().getClienteId());
			} else {
				queryMap.put("cuentaCliente", SpiritConstants.getOptionNo().substring(0,1));
			}
			List<CuentaBancariaIf> accounts = (List<CuentaBancariaIf>) SessionServiceLocator.getCuentaBancariaSessionService().findCuentaBancariaByQuery(queryMap);
			refreshCombo(jCmbAccount, accounts);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadComboBoxCreditCardByBank(JComboBox jCmbCreditCard, BancoIf bank, boolean relatedBusinessOperator) throws GenericBusinessException {
		if (bank != null) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("bancoId", bank.getId());
			List<TarjetaCreditoIf> creditCards = (List<TarjetaCreditoIf>) SessionServiceLocator.getTarjetaCreditoSessionService().findTarjetaCreditoByQuery(queryMap);
			refreshCombo(jCmbCreditCard, creditCards);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadComboBoxRetentionPercentage(JComboBox jCmbRetentionPercentage) throws GenericBusinessException {
		List retentions = new ArrayList();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (this.getMode() == SpiritMode.SAVE_MODE)
			queryMap.put("estado", SpiritConstants.getActiveStatus().substring(0,1));
		if (getRbCustomer().isSelected()) {
			DocumentoIf document = (DocumentoIf) getCmbDocument().getSelectedItem();
			queryMap.put("tipoRetencion", document.getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1))?SpiritConstants.getRetentionIncomeType():SpiritConstants.getRetentionIvaType());
			retentions = (List) SessionServiceLocator.getSriClienteRetencionSessionService().findPorcentajesRetencionByQuery(queryMap);
		} else
			//TODO: Este query hay que arreglarlo pues está devolviendo porcentajes de retención de clientes
			retentions = (List) SessionServiceLocator.getSriProveedorRetencionSessionService().findPorcentajesRetencionByQuery(queryMap);
		if (retentions.size() > 0)
			refreshCombo(jCmbRetentionPercentage, retentions);
	}

	private void enableJtpTransactionDetailTab(int index) {
		getJtpTransactionDetail().setSelectedIndex(index);
		for (int i=0; i<getJtpTransactionDetail().getTabCount(); i++)
			getJtpTransactionDetail().setEnabledAt(i, false);
		if (index >= 0)
			getJtpTransactionDetail().setEnabledAt(index, true);
	}
	
	private void searchBusinessOperator() throws GenericBusinessException {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		String businessOperator = getTxtLegalName().getText().trim();
		String businessOperatorIdentifierOrName = SpiritConstants.getEmptyCharacter();

		if (businessOperator.contains(SpiritConstants.getBlankSpaceCharacter() + SpiritConstants.getPlaceholderCharacter() + SpiritConstants.getBlankSpaceCharacter()))
			businessOperatorIdentifierOrName = businessOperator.split(" - ")[0] + SpiritConstants.getWildcardCharacter();
		else 
			businessOperatorIdentifierOrName = businessOperator + SpiritConstants.getWildcardCharacter();

		queryMap.put("identifierOrName", businessOperatorIdentifierOrName);
		queryMap.put("enterpriseId", Parametros.getIdEmpresa());
		queryMap.put("walletType", getRbCustomer().isSelected()?SpiritConstants.getCustomerWalletType().substring(0, 2):SpiritConstants.getProviderWalletType().substring(0,2));
		int listSize = SessionServiceLocator.getClienteOficinaSessionService().findBusinessOperatorOfficeByQueryListSize(queryMap);
		if (listSize == 1) {
			Iterator<ClienteOficinaIf> it = SessionServiceLocator.getClienteOficinaSessionService().findBusinessOperatorOfficeByQuery(queryMap).iterator();
			if (it.hasNext()) {
				setBusinessOperatorOffice((ClienteOficinaIf) it.next());
				setBusinessOperatorData();
			}
		} else if (listSize > 1) {
			showBusinessOperatorFinder(listSize, queryMap, JDPopupFinderModel.BUSQUEDA_TODOS, true);
		} else if (listSize <= 0) {
			setBusinessOperatorOffice((ClienteOficinaIf) SpiritConstants.getNullValue());
			setBusinessOperator((ClienteIf) SpiritConstants.getNullValue());
		}

		if (getBusinessOperatorOffice() == null) {
			if (listSize <= 0)
				SpiritAlert.createAlert("No se hallaron coincidencias", SpiritAlert.WARNING);
		} else {
			getLblStatusMessage().setText(SpiritConstants.getEmptyCharacter());
			getLblStatusMessage().setVisible(false);
		}
	}

	private void showBusinessOperatorFinder(int listSize, Map<String, Object> queryMap, int searchType, boolean textFieldSearch) throws GenericBusinessException {
		String searchWindowTitle = SpiritConstants.getEmptyCharacter();
		String businessOperatorType = SpiritConstants.getEmptyCharacter();
		if (getRbCustomer().isSelected()) {
			businessOperatorType = SpiritConstants.getCustomerWalletType().substring(0,2).toUpperCase();
			searchWindowTitle = SpiritConstants.getCustomerWalletType();
		} else {
			businessOperatorType = SpiritConstants.getProviderWalletType().substring(0,2).toUpperCase();
			searchWindowTitle = SpiritConstants.getProviderWalletType();
		}
		ClienteOficinaCriteria businessOperatorOfficeCriteria = new ClienteOficinaCriteria(searchWindowTitle, 0L, 0L, businessOperatorType, SpiritConstants.getEmptyCharacter(), textFieldSearch);
		Vector<Integer> columnsWidth = new Vector<Integer>();
		columnsWidth.addElement(70);
		columnsWidth.addElement(200);
		columnsWidth.addElement(80);
		columnsWidth.addElement(230);
		businessOperatorOfficeCriteria.setQueryBuilded(queryMap);
		if (listSize > -1)
			businessOperatorOfficeCriteria.setResultListSize(listSize);
		setPopupFinder(new JDPopupFinderModel(Parametros.getMainFrame(), businessOperatorOfficeCriteria, searchType, columnsWidth, null));
		if (getPopupFinder().getElementoSeleccionado() != null) {
			setBusinessOperatorOffice((ClienteOficinaIf) getPopupFinder().getElementoSeleccionado());
			setBusinessOperatorData();
		}
	}

	private void setBusinessOperatorData() throws GenericBusinessException {
		ClienteIf businessOperator = SessionServiceLocator.getClienteSessionService().getCliente(getBusinessOperatorOffice().getClienteId());
		setBusinessOperator(businessOperator);
		getTxtLegalName().setText(businessOperator.getNombreLegal());
		getTxtIdentification().setText(businessOperator.getIdentificacion());
		String city = SessionServiceLocator.getCiudadSessionService().getCiudad(getBusinessOperatorOffice().getCiudadId()).getNombre() + " / ";
		String address = getBusinessOperatorOffice().getDireccion().toUpperCase();
		getTxtCityAddress().setText(city + address);
		//getBtnSearchBusinessOperator().grabFocus();
	}

	public void switchJtpWallet(int indexTab) {
		getJtpWallet().setSelectedIndex(indexTab);
		if (indexTab == WalletConstants.getDetailTab())
			getCmbDocument().requestFocusInWindow();
		else if (this.getMode() != SpiritMode.FIND_MODE)
			getRbCustomer().requestFocusInWindow();
		else
			getTxtCode().requestFocusInWindow();
	}
	
	public ClienteIf getBusinessOperator() {
		return businessOperator;
	}
	
	public void setBusinessOperator(ClienteIf businessOperator) {
		this.businessOperator = businessOperator;
	}

	public ClienteOficinaIf getBusinessOperatorOffice() {
		return businessOperatorOffice;
	}

	public void setBusinessOperatorOffice(ClienteOficinaIf businessOperatorOffice) {
		this.businessOperatorOffice = businessOperatorOffice;
	}

	public Vector<WalletDetailData> getWalletDetailDataVector() {
		return walletDetailDataVector;
	}

	public void setWalletDetailDataVector(Vector<WalletDetailData> walletDetailDataVector) {
		this.walletDetailDataVector = walletDetailDataVector;
	}

	public Vector<WalletDetailData> getDeletedWalletDetailDataVector() {
		return deletedWalletDetailDataVector;
	}

	public void setDeletedWalletDetailDataVector(
			Vector<WalletDetailData> deletedWalletDetailDataVector) {
		this.deletedWalletDetailDataVector = deletedWalletDetailDataVector;
	}

	public Vector<CrossingWalletDetailData> getCrossingWalletDetailsVector() {
		return crossingWalletDetailsVector;
	}

	public void setCrossingWalletDetailsVector(
			Vector<CrossingWalletDetailData> crossingWalletDetailsVector) {
		this.crossingWalletDetailsVector = crossingWalletDetailsVector;
	}

	public JDPopupFinderModel getPopupFinder() {
		return popupFinder;
	}

	public void setPopupFinder(JDPopupFinderModel popupFinder) {
		this.popupFinder = popupFinder;
	}

	public int getWalletValidationPolicy() {
		return walletValidationPolicy;
	}

	public void setWalletValidationPolicy(int walletValidationPolicy) {
		this.walletValidationPolicy = walletValidationPolicy;
	}

	public boolean wasUpdatedOriginalReceipt() {
		return updatedOriginalReceipt;
	}

	public void setUpdatedOriginalReceipt(boolean updatedOriginalReceipt) {
		this.updatedOriginalReceipt = updatedOriginalReceipt;
	}

	public boolean isFindAction() {
		return findAction;
	}

	public void setFindAction(boolean findAction) {
		this.findAction = findAction;
	}

	public boolean isDeleteAction() {
		return deleteAction;
	}

	public void setDeleteAction(boolean deleteAction) {
		this.deleteAction = deleteAction;
	}

	public boolean isActivatedRetrocompatibility() {
		return activatedRetrocompatibility;
	}

	public void setActivatedRetrocompatibility(boolean activatedRetrocompatibility) {
		this.activatedRetrocompatibility = activatedRetrocompatibility;
	}

	public Comparator<CarteraDetalleIf> getWalletDetailComparator() {
		return walletDetailComparator;
	}

	public void setWalletDetailComparator(Comparator<CarteraDetalleIf> walletDetailComparator) {
		this.walletDetailComparator = walletDetailComparator;
	}

	public Comparator<WalletReceiptRowData> getWalletReceiptRowDataComparator() {
		return walletReceiptRowDataComparator;
	}

	public void setWalletReceiptRowDataComparator(
			Comparator<WalletReceiptRowData> walletReceiptRowDataComparator) {
		this.walletReceiptRowDataComparator = walletReceiptRowDataComparator;
	}

	public int getMaximumSequentialNumber() {
		return maximumSequentialNumber;
	}

	public void setMaximumSequentialNumber(int maximumSequentialNumber) {
		this.maximumSequentialNumber = maximumSequentialNumber;
	}

	public int getSelectedWalletDetailIndex() {
		return selectedWalletDetailIndex;
	}

	public void setSelectedWalletDetailIndex(int selectedWalletDetailIndex) {
		this.selectedWalletDetailIndex = selectedWalletDetailIndex;
	}

	public int getSelectedRowTblWalletDetail() {
		return selectedRowTblWalletDetail;
	}

	public void setSelectedRowTblWalletDetail(int selectedRowTblWalletDetail) {
		this.selectedRowTblWalletDetail = selectedRowTblWalletDetail;
	}

	public WalletModel getWalletModel() {
		return walletModel;
	}

	public void setWalletModel(WalletModel walletModel) {
		this.walletModel = walletModel;
	}

	public boolean isVoidTransaction() {
		return voidTransaction;
	}

	public void setVoidTransaction(boolean voidTransaction) {
		this.voidTransaction = voidTransaction;
	}

	public ActionListener getRbCustomerWalletTypeActionListener() {
		return rbCustomerWalletTypeActionListener;
	}

	public void setRbCustomerWalletTypeActionListener(ActionListener rbCustomerWalletTypeActionListener) {
		this.rbCustomerWalletTypeActionListener = rbCustomerWalletTypeActionListener;
	}

	public ActionListener getRbProviderWalletTypeActionListener() {
		return rbProviderWalletTypeActionListener;
	}

	public void setRbProviderWalletTypeActionListener(ActionListener rbProviderWalletTypeActionListener) {
		this.rbProviderWalletTypeActionListener = rbProviderWalletTypeActionListener;
	}
}