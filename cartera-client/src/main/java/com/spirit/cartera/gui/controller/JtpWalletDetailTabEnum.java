package com.spirit.cartera.gui.controller;

import com.spirit.exception.GenericBusinessException;

public enum JtpWalletDetailTabEnum {

	CASH, CHECK, DEBIT, CREDIT_CARD, ELECTRONIC_PAYMENT, TRANSFER, RETENTION;
	
	public static int getIndexTabByName(String name) throws GenericBusinessException {
		if (name.equals(CASH.getName()))
			return -1;
		if (name.equals(CHECK.getName()))
			return 0;
		if (name.equals(RETENTION.getName()))
			return 1;
		if (name.equals(DEBIT.getName()))
			return 2;
		if (name.equals(TRANSFER.getName()))
			return 3;
		if (name.equals(CREDIT_CARD.getName()))
			return 4;
		if (name.equals(ELECTRONIC_PAYMENT.getName()))
			return 5;
		return -1;
	}
	
	public String getName(){
		return this.toString();
	}
}
