package com.spirit.cartera.gui.controller;

import com.spirit.exception.GenericBusinessException;

public enum WalletErrorMessagesEnum {

	SET_EMISSION_DATE, SET_DOCUMENT_TYPE, SET_CURRENCY, SET_COMMENT, SET_BUSINESS_OPERATOR, TOTAL_NOT_ZERO, SET_DOCUMENT, VALUE_NOT_ZERO, SET_CHECK_BANK, SET_CHECK_ACCOUNT, 
	SET_CHECK_NUMBER, SET_DEPOSIT_BANK, SET_DEPOSIT_ACCOUNT, SET_DEBIT_BANK, SET_DEBIT_ACCOUNT, SET_DEBIT_REFERENCE, SET_SOURCE_BANK, SET_SOURCE_ACCOUNT, SET_TARGET_BANK, SET_TARGET_ACCOUNT,
	SET_CREDIT_CARD_BANK, SET_CREDIT_CARD, SET_VOUCHER_REFERENCE, SET_ELECTRONIC_PAYMENT_REFERENCE, SET_RETENTION_NUMBER, SET_RETENTION_AUTHORIZATION, DEFAULT_CHART_OF_ACCOUNTS_IS_NOT_DEFINED, NOT_VALID_ACTION_FOR_NULLIFIED_TRANSACTIONS, CHECK_NUMBER_DUPLICATED, CANCELED_CHECK;
	
	public static String getMessageByErrorCode(String errorCode) throws GenericBusinessException {
		String message = "";
		if (errorCode.equals(SET_EMISSION_DATE.getMessage()))
			message = "Debe ingresar la fecha de emisión";
		if (errorCode.equals(SET_DOCUMENT_TYPE.getMessage()))
			message = "Debe seleccionar tipo de documento";
		if (errorCode.equals(SET_CURRENCY.getMessage()))
			message = "Debe seleccionar moneda";
		if (errorCode.equals(SET_COMMENT.getMessage()))
			message = "Debe ingresar un comentario";
		if (errorCode.equals(SET_BUSINESS_OPERATOR.getMessage()))
			message = "Debe seleccionar operador de negocio";
		if (errorCode.equals(TOTAL_NOT_ZERO.getMessage()))
			message = "Total debe ser mayor a cero";
		if (errorCode.equals(SET_DOCUMENT.getMessage()))
			message = "Debe seleccionar documento";
		if (errorCode.equals(VALUE_NOT_ZERO.getMessage()))
			message = "Valor debe ser mayor a cero";
		if (errorCode.equals(SET_CHECK_BANK.getMessage()))
			message = "Debe seleccionar banco del cheque";
		if (errorCode.equals(SET_CHECK_ACCOUNT.getMessage()))
			message = "Debe seleccionar cuenta bancaria del cheque";
		if (errorCode.equals(SET_CHECK_NUMBER.getMessage()))
			message = "Debe ingresar número del cheque";
		if (errorCode.equals(SET_DEPOSIT_BANK.getMessage()))
			message = "Debe seleccionar banco para depósito";
		if (errorCode.equals(SET_DEPOSIT_ACCOUNT.getMessage()))
			message = "Debe seleccionar cuenta bancaria para depósito";
		if (errorCode.equals(SET_DEBIT_BANK.getMessage()))
			message = "Debe seleccionar banco";
		if (errorCode.equals(SET_DEBIT_ACCOUNT.getMessage()))
			message = "Debe seleccionar cuenta bancaria";
		if (errorCode.equals(SET_DEBIT_REFERENCE.getMessage()))
			message = "Debe ingresar referencia del débito";
		if (errorCode.equals(SET_SOURCE_BANK.getMessage()))
			message = "Debe seleccionar banco origen";
		if (errorCode.equals(SET_SOURCE_ACCOUNT.getMessage()))
			message = "Debe seleccionar cuenta bancaria origen";
		if (errorCode.equals(SET_TARGET_BANK.getMessage()))
			message = "Debe seleccionar banco destino";
		if (errorCode.equals(SET_TARGET_ACCOUNT.getMessage()))
			message = "Debe seleccionar cuenta bancaria destino";
		if (errorCode.equals(SET_CREDIT_CARD_BANK.getMessage()))
			message = "Debe seleccionar banco";
		if (errorCode.equals(SET_CREDIT_CARD.getMessage()))
			message = "Debe seleccionar tarjeta de crédito";
		if (errorCode.equals(SET_VOUCHER_REFERENCE.getMessage()))
			message = "Debe ingresar referencia del voucher";
		if (errorCode.equals(SET_ELECTRONIC_PAYMENT_REFERENCE.getMessage()))
			message = "Debe ingresar referencia del pago";
		if (errorCode.equals(SET_RETENTION_NUMBER.getMessage()))
			message = "Debe ingresar preimpreso de retención";
		if (errorCode.equals(SET_RETENTION_AUTHORIZATION.getMessage()))
			message = "Debe ingresar autorización de retención";
		if (errorCode.equals(DEFAULT_CHART_OF_ACCOUNTS_IS_NOT_DEFINED.getMessage()))
			message = "Plan de cuentas predeterminado no ha sido especificado";
		if (errorCode.equals(NOT_VALID_ACTION_FOR_NULLIFIED_TRANSACTIONS.getMessage()))
			message = "Acción no permitida para comprobantes anulados";
		if (errorCode.equals(CHECK_NUMBER_DUPLICATED.getMessage()))
			message = "Número de cheque duplicado";
		if (errorCode.equals(CANCELED_CHECK.getMessage()))
			message = "Cheque ha sido cobrado. No se puede modificar ni eliminar";
		return message;
	}
	
	public String getMessage() {
		return this.toString();
	}
}
