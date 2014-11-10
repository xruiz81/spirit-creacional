package com.spirit.cartera.gui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.spirit.cartera.gui.model.WalletModel;
import com.spirit.cartera.handler.WalletData;
import com.spirit.cartera.handler.WalletDetailData;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.SpiritMode;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.DocumentoIf;



public class WalletValidationHandler {
	public static boolean validate(WalletData walletData, Vector<WalletDetailData> walletDetailDataVector, WalletModel walletModel) throws GenericBusinessException {
		if (Parametros.getPlanCuentaPredeterminado() == SpiritConstants.getNullValue()) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.DEFAULT_CHART_OF_ACCOUNTS_IS_NOT_DEFINED.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			walletModel.getTxtEmissionDate().requestFocusInWindow();
			return false;
		}
		if (walletData.getEmissionDate() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_EMISSION_DATE.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			walletModel.getTxtEmissionDate().requestFocusInWindow();
			return false;
		}
		if (walletData.getDocumentType() == null || walletData.getDocumentType().toString().equals(SpiritConstants.getPlaceholderCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DOCUMENT_TYPE.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			walletModel.getCmbDocumentType().requestFocusInWindow();
			return false;
		}
		if (walletData.getCurrency() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_CURRENCY.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			walletModel.getCmbCurrency().requestFocusInWindow();
			return false;
		}
		/* Unnecessary requirement
		if (walletData.getComment().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.ERR004.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			walletModel.getTxtComment().requestFocusInWindow();
			return false;
		}*/
		if (walletData.getBusinessOperatorOffice() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_BUSINESS_OPERATOR.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			walletModel.getBtnSearchBusinessOperator().requestFocusInWindow();
			return false;
		}
		if (walletData.getTotal().doubleValue() <= 0D) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.TOTAL_NOT_ZERO.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			return false;
		}
		if (walletDetailDataVector != null && !accountingEntryTemplateExistsForAll(walletDetailDataVector, walletModel)) {
			walletModel.switchJtpWallet(WalletConstants.getMasterTab());
			return false;
		}
		return true;
	}
	
	private static boolean accountingEntryTemplateExistsForAll(Vector<WalletDetailData> walletDetailDataVector, WalletModel walletModel) throws GenericBusinessException {
		String warningMessage = SpiritConstants.getEmptyCharacter();
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetail = walletDetailDataVector.get(i);
			DocumentoIf document = walletDetail.getDocument();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("documentoId", document.getId());
			queryMap.put("planCuentaId", Parametros.getPlanCuentaPredeterminado().getId());
			if (walletModel.getMode() == SpiritMode.SAVE_MODE)
				queryMap.put("validoAlGuardar", SpiritConstants.getOptionYes().substring(0,1));
			else if (walletModel.getMode() == SpiritMode.UPDATE_MODE)
				queryMap.put("validoAlActualizar", SpiritConstants.getOptionYes().substring(0,1));
			Iterator<EventoContableIf> accountingEventIterator = SessionServiceLocator.getEventoContableSessionService().findEventoContableByQuery(queryMap).iterator();
			if (accountingEventIterator.hasNext()) {
				EventoContableIf accountingEvent = accountingEventIterator.next();
				List<PlantillaIf> templatesList = (ArrayList<PlantillaIf>) SessionServiceLocator.getPlantillaSessionService().findPlantillaByEventocontableId(accountingEvent.getId());
				if (templatesList.size() <= 0)
					warningMessage += document.getNombre() + "\n";
			}
		}
		
		if (!warningMessage.equals(SpiritConstants.getEmptyCharacter())) {
			warningMessage = "No se han definido adecuadamente eventos o plantillas contables para:\n\n" + warningMessage + "\n";
			Object[] options = {SpiritConstants.getOptionYes(), SpiritConstants.getOptionNo()}; 
    		int option = JOptionPane.showOptionDialog(walletModel, warningMessage + "Si continúa deberá contabilizar los movimientos especificados de forma manual.\n¿Desea continuar?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, SpiritConstants.getOptionNo());
    		if (option == JOptionPane.NO_OPTION)
    			return false;
		}
		
		return true;
	}
	
	public static boolean validate(String transactionName, WalletDetailData walletDetailData, Vector<WalletDetailData> walletDetailDataVector, WalletModel walletModel, boolean loadingData) throws GenericBusinessException {
		if (validateGeneralWalletDetailFields(walletDetailData, walletModel, loadingData)) {
			if (!walletModel.isActivatedRetrocompatibility()) {
				if (transactionName.equals(JtpWalletDetailTabEnum.CASH.getName()))
					return validateTransactionCash(walletDetailData, walletModel);
				if (transactionName.equals(JtpWalletDetailTabEnum.CHECK.getName()))
					return validateTransactionCheck(walletDetailData, walletDetailDataVector, walletModel);
				if (transactionName.equals(JtpWalletDetailTabEnum.RETENTION.getName()))
					return validateTransactionRetention(walletDetailData, walletModel);
				if (transactionName.equals(JtpWalletDetailTabEnum.DEBIT.getName()))
					return validateTransactionDebit(walletDetailData, walletModel);
				if (transactionName.equals(JtpWalletDetailTabEnum.TRANSFER.getName()))
					return validateTransactionTransfer(walletDetailData, walletModel);
				if (transactionName.equals(JtpWalletDetailTabEnum.CREDIT_CARD.getName()))
					return validateTransactionCreditCard(walletDetailData, walletModel);
				if (transactionName.equals(JtpWalletDetailTabEnum.ELECTRONIC_PAYMENT.getName()))
					return validateTransactionElectronicPayment(walletDetailData, walletModel);
			}
		} else
			return false;
		return true;
	}
	
	private static boolean validateGeneralWalletDetailFields(WalletDetailData walletDetailData, WalletModel walletModel, boolean loadingData) throws GenericBusinessException {
		if ((walletDetailData.getDocument() == null || walletDetailData.getDocument().toString().equals(SpiritConstants.getPlaceholderCharacter())) && !loadingData) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DOCUMENT.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbDocument().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getValue().doubleValue() <= 0D) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.VALUE_NOT_ZERO.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtDetailValue().requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static boolean validateTransactionCash(WalletDetailData walletDetailData, WalletModel walletModel) throws GenericBusinessException {
		// TODO:
		return true;
	}
	
	private static boolean validateTransactionCheck(WalletDetailData walletDetailData, Vector<WalletDetailData> walletDetailDataVector, WalletModel walletModel) throws GenericBusinessException {
		if (walletDetailData.getCheckBank() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_CHECK_BANK.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbCheckBank().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getCheckAccount() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_CHECK_ACCOUNT.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbCheckAccount().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getCheckNumber().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_CHECK_NUMBER.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtCheckNumber().requestFocusInWindow();
			return false;
		}
		for (int i=0; i<walletDetailDataVector.size(); i++) {
			WalletDetailData walletDetailDataFromVector = walletDetailDataVector.get(i);
			if (walletDetailDataFromVector.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				if (isCanceledCheck(walletDetailData)) {
					SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.CANCELED_CHECK.getMessage()), SpiritAlert.WARNING);
					walletModel.switchJtpWallet(WalletConstants.getDetailTab());
					walletModel.getTxtCheckNumber().requestFocusInWindow();
					return false;
				}
				if (isIssuedCheck(walletDetailData) && walletDetailData.getCheckAccount().getId().compareTo(walletDetailDataFromVector.getCheckAccount().getId()) == 0 && walletDetailData.getCheckNumber().equals(walletDetailDataFromVector.getCheckNumber()) && walletDetailData.getSequentialNumber() != walletDetailDataFromVector.getSequentialNumber()) {
					SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.CHECK_NUMBER_DUPLICATED.getMessage()), SpiritAlert.WARNING);
					walletModel.switchJtpWallet(WalletConstants.getDetailTab());
					walletModel.getTxtCheckNumber().requestFocusInWindow();
					return false;
				}
			}
		}
		if (walletModel.getRbCustomer().isSelected() && walletDetailData.getDepositBank() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DEPOSIT_BANK.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbDepositBank().requestFocusInWindow();
			return false;
		}
		if (walletModel.getRbCustomer().isSelected() && walletDetailData.getDepositAccount() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DEPOSIT_ACCOUNT.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbDepositAccount().requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private static boolean isIssuedCheck(WalletDetailData walletDetailData) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("cuentaBancariaId", walletDetailData.getCheckAccount().getId());
		queryMap.put("numero", walletDetailData.getCheckNumber());
		Iterator<ChequeEmitidoIf> issuedCheckIterator;
		try {
			issuedCheckIterator = SessionServiceLocator.getChequeEmitidoSessionService().findChequeEmitidoByQuery(queryMap).iterator();
			if (issuedCheckIterator.hasNext()) {
				return true;
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al verificar datos del cheque # " + walletDetailData.getCheckNumber(), SpiritAlert.ERROR);
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isCanceledCheck(WalletDetailData walletDetailData) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if (walletDetailData.getPreviousCheckAccount() != SpiritConstants.getNullValue() && walletDetailData.getPreviousCheckNumber() != SpiritConstants.getNullValue()) {
			queryMap.put("cuentaBancariaId", walletDetailData.getPreviousCheckAccount().getId());
			queryMap.put("numero", walletDetailData.getPreviousCheckNumber());
			queryMap.put("estado", SpiritConstants.getCanceledCheck().substring(0,1));
			Iterator<ChequeEmitidoIf> canceledCheckIterator;
			try {
				canceledCheckIterator = SessionServiceLocator.getChequeEmitidoSessionService().findChequeEmitidoByQuery(queryMap).iterator();
				if (canceledCheckIterator.hasNext())
					return true;
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("Se ha producido un error al verificar datos del cheque # " + walletDetailData.getCheckNumber(), SpiritAlert.ERROR);
			}
		}
		return false;
	}
	
	private static boolean validateTransactionDebit(WalletDetailData walletDetailData, WalletModel walletModel) throws GenericBusinessException {
		if (walletDetailData.getDebitBank() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DEBIT_BANK.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbDebitBank().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getDebitAccount() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DEBIT_ACCOUNT.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbDebitAccount().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getDebitReference().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_DEBIT_REFERENCE.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtDebitReference().requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static boolean validateTransactionTransfer(WalletDetailData walletDetailData, WalletModel walletModel) throws GenericBusinessException {
		/*if (walletDetailData.getSourceBank() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_SOURCE_BANK.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbSourceBank().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getSourceAccount() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_SOURCE_ACCOUNT.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbSourceAccount().requestFocusInWindow();
			return false;
		}*/
		if (walletDetailData.getTargetBank() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_TARGET_BANK.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbTargetBank().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getTargetAccount() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_TARGET_ACCOUNT.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbTargetAccount().requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static boolean validateTransactionCreditCard(WalletDetailData walletDetailData, WalletModel walletModel) throws GenericBusinessException {
		if (walletDetailData.getCreditCardBank() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_CREDIT_CARD_BANK.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbCreditCardBank().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getCreditCard() == null) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_CREDIT_CARD.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getCmbCreditCard().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getVoucherReference().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_VOUCHER_REFERENCE.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtVoucherReference().requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static boolean validateTransactionElectronicPayment(WalletDetailData walletDetailData, WalletModel walletModel) throws GenericBusinessException {
		if (walletDetailData.getElectronicPaymentReference().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_ELECTRONIC_PAYMENT_REFERENCE.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtElectronicPaymentReference().requestFocusInWindow();
			return false;
		}
		return true;
	}
	
	private static boolean validateTransactionRetention(WalletDetailData walletDetailData, WalletModel walletModel) throws GenericBusinessException {
		if (walletDetailData.getRetentionNumber().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_RETENTION_NUMBER.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtRetentionNumber().requestFocusInWindow();
			return false;
		}
		if (walletDetailData.getRetentionAuthorization().equals(SpiritConstants.getEmptyCharacter())) {
			SpiritAlert.createAlert(WalletErrorMessagesEnum.getMessageByErrorCode(WalletErrorMessagesEnum.SET_RETENTION_AUTHORIZATION.getMessage()), SpiritAlert.WARNING);
			walletModel.switchJtpWallet(WalletConstants.getDetailTab());
			walletModel.getTxtRetentionAuthorization().requestFocusInWindow();
			return false;
		}
		return true;
	}
}
