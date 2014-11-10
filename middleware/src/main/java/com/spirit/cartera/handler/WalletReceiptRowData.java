package com.spirit.cartera.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.client.SpiritConstants;

public class WalletReceiptRowData implements Serializable {
	private static final long serialVersionUID = 8165384909820257520L;
	private Integer sequentialNumber;
	private String receiptDetail;
	private String transactionDetail;
	private BigDecimal detailValue;
	private BigDecimal appliedValue;
	
	public WalletReceiptRowData() {
		this.setSequentialNumber(Integer.valueOf(SpiritConstants.getZeroValue().intValue()));
		this.setReceiptDetail(SpiritConstants.getEmptyCharacter());
		this.setTransactionDetail(SpiritConstants.getEmptyCharacter());
		this.setDetailValue(SpiritConstants.getZeroValue());
		this.setAppliedValue(SpiritConstants.getZeroValue());
	}

	public Integer getSequentialNumber() {
		return sequentialNumber;
	}

	public void setSequentialNumber(int sequentialNumber) {
		this.sequentialNumber = new Integer(sequentialNumber);
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