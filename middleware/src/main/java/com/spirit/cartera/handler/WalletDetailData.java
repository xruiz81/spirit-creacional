package com.spirit.cartera.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.client.SpiritConstants;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.pos.entity.TarjetaCreditoIf;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.util.Utilitarios;

public class WalletDetailData implements Serializable {
	private static final long serialVersionUID = 3321847309961151542L;
	/* General fields */
	private Long walletDetailId;
	private int sequentialNumber;
	private DocumentoIf document;
	private java.util.Date dateToApply;
	private BigDecimal value;
	private BigDecimal balance;
	private String comment;
	/* Check fields */
	private BancoIf checkBank;
	private CuentaBancariaIf checkAccount;
	private String checkNumber;
	private BancoIf previousCheckBank;
	private CuentaBancariaIf previousCheckAccount;
	private String previousCheckNumber;
	private BancoIf depositBank;
	private CuentaBancariaIf depositAccount;
	/* Retention fields */
	private String retentionNumber;
	private String retentionAuthorization;
	private BigDecimal retentionPercentage;
	private SriClienteRetencionIf sriRetentionPercentageDefinition;
	/* Debit fields */
	private BancoIf debitBank;
	private CuentaBancariaIf debitAccount;
	private String debitReference;
	/* Transfer fields */
	private BancoIf sourceBank;
	private CuentaBancariaIf sourceAccount;
	private BancoIf targetBank;
	private CuentaBancariaIf targetAccount;
	/* Credit card fields */
	private BancoIf creditCardBank;
	private TarjetaCreditoIf creditCard;
	private String voucherReference;
	/* Electronic payment fields */
	private String electronicPaymentReference;
	
	public WalletDetailData() {
		this.setWalletDetailId(SpiritConstants.getNullValue());
		this.setSequentialNumber(0);
		this.setDocument(SpiritConstants.getNullValue());
		this.setDateToApply(new java.util.Date(Utilitarios.calendarHoy().getTimeInMillis()));
		this.setValue(SpiritConstants.getZeroValue());
		this.setBalance(SpiritConstants.getZeroValue());
		this.setComment(SpiritConstants.getEmptyCharacter());
		this.setCheckBank(SpiritConstants.getNullValue());
		this.setCheckAccount(SpiritConstants.getNullValue());
		this.setCheckNumber(SpiritConstants.getEmptyCharacter());
		this.setPreviousCheckBank(SpiritConstants.getNullValue());
		this.setPreviousCheckAccount(SpiritConstants.getNullValue());
		this.setPreviousCheckNumber(SpiritConstants.getEmptyCharacter());
		this.setDepositBank(SpiritConstants.getNullValue());
		this.setDepositAccount(SpiritConstants.getNullValue());
		this.setRetentionNumber(SpiritConstants.getEmptyCharacter());
		this.setRetentionAuthorization(SpiritConstants.getEmptyCharacter());
		this.setRetentionPercentage(SpiritConstants.getZeroValue());
		this.setSriRetentionPercentageDefinition(SpiritConstants.getNullValue());
		this.setDebitBank(SpiritConstants.getNullValue());
		this.setDebitAccount(SpiritConstants.getNullValue());
		this.setDebitReference(SpiritConstants.getEmptyCharacter());
		this.setSourceBank(SpiritConstants.getNullValue());
		this.setSourceAccount(SpiritConstants.getNullValue());
		this.setTargetBank(SpiritConstants.getNullValue());
		this.setTargetAccount(SpiritConstants.getNullValue());
		this.setCreditCardBank(SpiritConstants.getNullValue());
		this.setCreditCard(SpiritConstants.getNullValue());
		this.setVoucherReference(SpiritConstants.getEmptyCharacter());
		this.setElectronicPaymentReference(SpiritConstants.getEmptyCharacter());
	}

	public Long getWalletDetailId() {
		return walletDetailId;
	}

	public void setWalletDetailId(Object walletDetailId) {
		this.walletDetailId = (walletDetailId != null)?(Long) walletDetailId:null;
	}

	public int getSequentialNumber() {
		return sequentialNumber;
	}

	public void setSequentialNumber(int sequentialNumber) {
		this.sequentialNumber = sequentialNumber;
	}

	public DocumentoIf getDocument() {
		return document;
	}

	public void setDocument(Object document) {
		this.document = (document != null)?(DocumentoIf) document:null;
	}
	
	public java.util.Date getDateToApply() {
		return dateToApply;
	}
	
	public void setDateToApply(Object dateToApply) {
		this.dateToApply = (dateToApply != null)?(java.util.Date) dateToApply:null;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BancoIf getCheckBank() {
		return checkBank;
	}

	public void setCheckBank(Object checkBank) {
		this.checkBank = (checkBank != null)?(BancoIf) checkBank:null;
	}

	public CuentaBancariaIf getCheckAccount() {
		return checkAccount;
	}

	public void setCheckAccount(Object checkAccount) {
		this.checkAccount = (checkAccount != null)?(CuentaBancariaIf) checkAccount:null;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public BancoIf getPreviousCheckBank() {
		return previousCheckBank;
	}

	public void setPreviousCheckBank(Object previousCheckBank) {
		this.previousCheckBank = (previousCheckBank != null)?(BancoIf) previousCheckBank:null;
	}

	public CuentaBancariaIf getPreviousCheckAccount() {
		return previousCheckAccount;
	}

	public void setPreviousCheckAccount(Object previousCheckAccount) {
		this.previousCheckAccount = (previousCheckAccount != null)?(CuentaBancariaIf) previousCheckAccount:null;
	}

	public String getPreviousCheckNumber() {
		return previousCheckNumber;
	}

	public void setPreviousCheckNumber(String previousCheckNumber) {
		this.previousCheckNumber = previousCheckNumber;
	}

	public BancoIf getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(Object depositBank) {
		this.depositBank = (depositBank != null)?(BancoIf) depositBank:null;
	}

	public CuentaBancariaIf getDepositAccount() {
		return depositAccount;
	}

	public void setDepositAccount(Object depositAccount) {
		this.depositAccount = (depositAccount != null)?(CuentaBancariaIf) depositAccount:null;
	}

	public String getRetentionNumber() {
		return retentionNumber;
	}

	public void setRetentionNumber(String retentionNumber) {
		this.retentionNumber = retentionNumber;
	}

	public String getRetentionAuthorization() {
		return retentionAuthorization;
	}

	public void setRetentionAuthorization(String retentionAuthorization) {
		this.retentionAuthorization = retentionAuthorization;
	}

	public BigDecimal getRetentionPercentage() {
		return retentionPercentage;
	}

	public void setRetentionPercentage(BigDecimal retentionPercentage) {
		this.retentionPercentage = retentionPercentage;
	}

	public SriClienteRetencionIf getSriRetentionPercentageDefinition() {
		return sriRetentionPercentageDefinition;
	}

	public void setSriRetentionPercentageDefinition(Object sriRetentionPercentageDefinition) {
		this.sriRetentionPercentageDefinition = (sriRetentionPercentageDefinition != null)?(SriClienteRetencionIf) sriRetentionPercentageDefinition:null;
	}

	public BancoIf getDebitBank() {
		return debitBank;
	}

	public void setDebitBank(Object debitBank) {
		this.debitBank = (debitBank != null)?(BancoIf) debitBank:null;
	}

	public CuentaBancariaIf getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(Object debitAccount) {
		this.debitAccount = (debitAccount != null)?(CuentaBancariaIf) debitAccount:null;
	}

	public String getDebitReference() {
		return debitReference;
	}

	public void setDebitReference(String debitReference) {
		this.debitReference = debitReference;
	}

	public BancoIf getSourceBank() {
		return sourceBank;
	}

	public void setSourceBank(Object sourceBank) {
		this.sourceBank = (sourceBank != null)?(BancoIf) sourceBank:null;
	}

	public CuentaBancariaIf getSourceAccount() {
		return sourceAccount;
	}

	public void setSourceAccount(Object sourceAccount) {
		this.sourceAccount = (sourceAccount != null)?(CuentaBancariaIf) sourceAccount:null;
	}

	public BancoIf getTargetBank() {
		return targetBank;
	}

	public void setTargetBank(Object targetBank) {
		this.targetBank = (targetBank != null)?(BancoIf) targetBank:null;
	}

	public CuentaBancariaIf getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(Object targetAccount) {
		this.targetAccount = (targetAccount != null)?(CuentaBancariaIf) targetAccount:null;
	}

	public BancoIf getCreditCardBank() {
		return creditCardBank;
	}

	public void setCreditCardBank(Object creditCardBank) {
		this.creditCardBank = (creditCardBank != null)?(BancoIf) creditCardBank:null;
	}

	public TarjetaCreditoIf getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Object creditCard) {
		this.creditCard = (creditCard != null)?(TarjetaCreditoIf) creditCard:null;
	}

	public String getVoucherReference() {
		return voucherReference;
	}

	public void setVoucherReference(String voucherReference) {
		this.voucherReference = voucherReference;
	}

	public String getElectronicPaymentReference() {
		return electronicPaymentReference;
	}

	public void setElectronicPaymentReference(String electronicPaymentReference) {
		this.electronicPaymentReference = electronicPaymentReference;
	}
}
