package com.spirit.cartera.gui.controller;

import java.util.Iterator;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ModuloIf;
import com.spirit.inventario.gui.helper.SessionServiceLocator;

public class WalletConstants {
	/* Global wallet constants */
	private static final int MAX_LENGHT_CODE = 30;
	private static final int MAX_LENGHT_COMMENTS = 100;
	private static final int MAX_LENGHT_BUSINESS_OPERATOR = 160;
	private static final String CODE_WALLET_MODULE = "CART";
	private static final String NORMAL_STATUS = "NORMAL";
	private static final String NULLIFY_STATUS = "ANULADO";
	private static final String TRANSACTION_CASH = "CASH";
	private static final String TRANSACTION_CHECK = "CHECK";
	private static final String TRANSACTION_DEBIT = "DEBIT";
	private static final String TRANSACTION_CREDIT_CARD = "CREDIT_CARD";
	private static final String TRANSACTION_ELECTRONIC_PAYMENT = "ELECTRONIC_PAYMENT";
	private static final String TRANSACTION_TRANSFER = "TRANSFER";
	private static final String TRANSACTION_RETENTION = "RETENTION";
	private static final String CHECKING_ACCOUNT = "CORRIENTE";
	private static final String SAVINGS_ACCOUNT = "AHORROS";
	private static final int MASTER_TAB = 0;
	private static final int DETAIL_TAB = 1;
	private static final int TBL_WALLET_DETAIL_SELECTION_COLUMN_INDEX = 0;
	private static final int TBL_WALLET_DETAIL_DATE_COLUMN_INDEX = 1;
	private static final int TBL_WALLET_DETAIL_TRANSACTION_COLUMN_INDEX = 2;
	private static final int TBL_WALLET_DETAIL_VALUE_COLUMN_INDEX = 3;
	private static final int TBL_WALLET_DETAIL_BALANCE_COLUMN_INDEX = 4;
	private static final int TBL_WALLET_DETAIL_DEFERRED_COLUMN_INDEX = 5;
	private static final int TBL_PENDING_ACCOUNTS_SELECTION_COLUMN_INDEX = 0;
	private static final int JDIALOG_APPLY_WALLET_TRANSACTION_WIDTH = 725;
	private static final int JDIALOG_APPLY_WALLET_TRANSACTION_HEIGHT = 470;
	private static final int JDIALOG_PENDING_BALANCES_CONFIRMATION_WIDTH = 619;
	private static final int JDIALOG_PENDING_BALANCES_CONFIRMATION_HEIGHT = 206;
	private static final int JDIALOG_ADDING_ACCOUNT_BANK_WIDHT = 619;
	private static final int JDIALOG_ADDING_ACCOUNT_BANK_HEIGHT = 206; 
	
	public static int getMaxLenghtCode() {
		return MAX_LENGHT_CODE;
	}
	public static int getMaxLenghtComments() {
		return MAX_LENGHT_COMMENTS;
	}
	public static int getMaxLenghtBusinessOperator() {
		return MAX_LENGHT_BUSINESS_OPERATOR;
	}
	public static String getCodeWalletModule() {
		return CODE_WALLET_MODULE;
	}
	public static String getNormalStatus() {
		return NORMAL_STATUS;
	}
	public static String getNullifyStatus() {
		return NULLIFY_STATUS;
	}
	public static String getTransactionCash() {
		return TRANSACTION_CASH;
	}
	public static String getTransactionCheck() {
		return TRANSACTION_CHECK;
	}
	public static String getTransactionDebit() {
		return TRANSACTION_DEBIT;
	}
	public static String getTransactionCreditCard() {
		return TRANSACTION_CREDIT_CARD;
	}
	public static String getTransactionElectronicPayment() {
		return TRANSACTION_ELECTRONIC_PAYMENT;
	}
	public static String getTransactionTransfer() {
		return TRANSACTION_TRANSFER;
	}
	public static String getTransactionRetention() {
		return TRANSACTION_RETENTION;
	}
	public static String getCheckingAccount() {
		return CHECKING_ACCOUNT;
	}
	public static String getSavingsAccount() {
		return SAVINGS_ACCOUNT;
	}
	public static int getMasterTab() {
		return MASTER_TAB;
	}
	public static int getDetailTab() {
		return DETAIL_TAB;
	}
	public static int getTblWalletDetailSelectionColumnIndex() {
		return TBL_WALLET_DETAIL_SELECTION_COLUMN_INDEX;
	}

	public static int getTblWalletDetailTransactionColumnIndex() {
		return TBL_WALLET_DETAIL_TRANSACTION_COLUMN_INDEX;
	}

	public static int getTblWalletDetailDateColumnIndex() {
		return TBL_WALLET_DETAIL_DATE_COLUMN_INDEX;
	}

	public static int getTblWalletDetailValueColumnIndex() {
		return TBL_WALLET_DETAIL_VALUE_COLUMN_INDEX;
	}

	public static int getTblWalletDetailBalanceColumnIndex() {
		return TBL_WALLET_DETAIL_BALANCE_COLUMN_INDEX;
	}

	public static int getTblWalletDetailDeferredColumnIndex() {
		return TBL_WALLET_DETAIL_DEFERRED_COLUMN_INDEX;
	}
	public static int getTblPendingAccountsSelectionColumnIndex() {
		return TBL_PENDING_ACCOUNTS_SELECTION_COLUMN_INDEX;
	}
	public static int getJdialogApplyWalletTransactionWidth() {
		return JDIALOG_APPLY_WALLET_TRANSACTION_WIDTH;
	}
	public static int getJdialogApplyWalletTransactionHeight() {
		return JDIALOG_APPLY_WALLET_TRANSACTION_HEIGHT;
	}
	public static int getJdialogPendingBalancesConfirmationWidth() {
		return JDIALOG_PENDING_BALANCES_CONFIRMATION_WIDTH;
	}
	public static int getJdialogPendingBalancesConfirmationHeight() {
		return JDIALOG_PENDING_BALANCES_CONFIRMATION_HEIGHT;
	}
	public static int getJdialogAddingAccountBankWidht() {
		return JDIALOG_ADDING_ACCOUNT_BANK_WIDHT;
	}
	public static int getJdialogAddingAccountBankHeight() {
		return JDIALOG_ADDING_ACCOUNT_BANK_HEIGHT;
	}
	@SuppressWarnings("unchecked")
	public static ModuloIf getWalletModule() throws GenericBusinessException {
		ModuloIf module = null;
		Iterator<ModuloIf> it = SessionServiceLocator.getModuloSessionService().findModuloByCodigo(getCodeWalletModule()).iterator();
		if (it.hasNext())
			module = (ModuloIf) it.next();
		return module;
	}
}
