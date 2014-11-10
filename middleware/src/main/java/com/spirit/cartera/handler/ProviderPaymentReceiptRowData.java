package com.spirit.cartera.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.client.SpiritConstants;

public class ProviderPaymentReceiptRowData implements Serializable {
	private static final long serialVersionUID = 2201428749115764242L;
	private String enterprise;
	private String identifier;
	private String documentType;
	private String code;
	private String accountingEntryNumber;
	private String businessOperator;
	private BigDecimal total;
	private BigDecimal balance;
	private String amountInWords;
	private String city;
	private String date;
	private String currency;
	private Integer sequentialNumber;
	private Long walletId;
	private String receiptDetail;
	private String transactionDetail;
	private BigDecimal detailValue;
	private BigDecimal appliedValue;
		
	public ProviderPaymentReceiptRowData() {
		this.setEnterprise(SpiritConstants.getEmptyCharacter());
		this.setIdentifier(SpiritConstants.getEmptyCharacter());
		this.setDocumentType(SpiritConstants.getEmptyCharacter());
		this.setCode(SpiritConstants.getEmptyCharacter());
		this.setAccountingEntryNumber(SpiritConstants.getEmptyCharacter());
		this.setBusinessOperator(SpiritConstants.getEmptyCharacter());
		this.setTotal(BigDecimal.ZERO);
		this.setBalance(BigDecimal.ZERO);
		this.setAmountInWords(SpiritConstants.getEmptyCharacter());
		this.setCity(SpiritConstants.getEmptyCharacter());
		this.setDate(SpiritConstants.getEmptyCharacter());
		this.setCurrency(SpiritConstants.getEmptyCharacter());
		this.setSequentialNumber(Integer.valueOf(SpiritConstants.getZeroValue().intValue()));
		this.setWalletId(Long.valueOf(SpiritConstants.getZeroValue().longValue()));
		this.setReceiptDetail(SpiritConstants.getEmptyCharacter());
		this.setTransactionDetail(SpiritConstants.getEmptyCharacter());
		this.setDetailValue(SpiritConstants.getZeroValue());
		this.setAppliedValue(SpiritConstants.getZeroValue());
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccountingEntryNumber() {
		return accountingEntryNumber;
	}

	public void setAccountingEntryNumber(String accountingEntryNumber) {
		this.accountingEntryNumber = accountingEntryNumber;
	}

	public String getBusinessOperator() {
		return businessOperator;
	}

	public void setBusinessOperator(String businessOperator) {
		this.businessOperator = businessOperator;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAmountInWords() {
		return amountInWords;
	}

	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getSequentialNumber() {
		return sequentialNumber;
	}

	public void setSequentialNumber(int sequentialNumber) {
		this.sequentialNumber = new Integer(sequentialNumber);
	}
	
	public Long getWalletId() {
		return walletId;
	}
	
	public void setWalletId(long walletId) {
		this.walletId = new Long(walletId);
	}

	public String getReceiptDetail() {
		return receiptDetail;
	}

	public void setReceiptDetail(String receiptDetail) {
		this.receiptDetail = receiptDetail;
	}

	public String getTransactionDetail() {
		return transactionDetail;
	}

	public void setTransactionDetail(String transactionDetail) {
		this.transactionDetail = transactionDetail;
	}

	public BigDecimal getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(BigDecimal detailValue) {
		this.detailValue = detailValue;
	}

	public BigDecimal getAppliedValue() {
		return appliedValue;
	}

	public void setAppliedValue(BigDecimal appliedValue) {
		this.appliedValue = appliedValue;
	}
}