package com.spirit.cartera.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.client.SpiritConstants;

public class CrossingWalletDetailData implements Serializable {
	private static final long serialVersionUID = -3373721172966582171L;
	private Long crossingWalletDetailId;
	private WalletDetailData walletDetailData;
	private PendingAccountData pendingAccountDetailData;
	private BigDecimal valueToApply;
	private java.util.Date applyingDate;
	private boolean fresh;
	
	public CrossingWalletDetailData() {
		this.setCrossingWalletDetailId(SpiritConstants.getNullValue());
		this.setWalletDetailData(SpiritConstants.getNullValue());
		this.setPendingAccountDetailData(SpiritConstants.getNullValue());
		this.setValueToApply(SpiritConstants.getZeroValue());
		this.setApplyingDate(SpiritConstants.getNullValue());
		this.setFresh(true);
	}

	public Long getCrossingWalletDetailId() {
		return crossingWalletDetailId;
	}

	public void setCrossingWalletDetailId(Object crossingWalletDetailId) {
		this.crossingWalletDetailId = (crossingWalletDetailId != null)?(Long) crossingWalletDetailId:null;
	}

	public WalletDetailData getWalletDetailData() {
		return walletDetailData;
	}

	public void setWalletDetailData(Object walletDetailData) {
		this.walletDetailData = (walletDetailData!=null)?(WalletDetailData) walletDetailData:null;
	}

	public PendingAccountData getPendingAccountDetailData() {
		return pendingAccountDetailData;
	}

	public void setPendingAccountDetailData(Object pendingAccountDetailData) {
		this.pendingAccountDetailData = (pendingAccountDetailData!=null)?(PendingAccountData) pendingAccountDetailData:null;
	}

	public BigDecimal getValueToApply() {
		return valueToApply;
	}

	public void setValueToApply(BigDecimal valueToApply) {
		this.valueToApply = valueToApply;
	}

	public java.util.Date getApplyingDate() {
		return applyingDate;
	}

	public void setApplyingDate(Object applyingDate) {
		this.applyingDate = (applyingDate!=null)?(java.util.Date) applyingDate:null;
	}

	public boolean isFresh() {
		return fresh;
	}

	public void setFresh(boolean fresh) {
		this.fresh = fresh;
	}
}
