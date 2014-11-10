package com.spirit.cartera.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.client.SpiritConstants;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;

public class PendingAccountData implements Serializable {
	private static final long serialVersionUID = 3011652467077258931L;
	/* Fields */
	CarteraIf pendingAccountWallet;
	CarteraDetalleIf pendingAccountWalletDetail;
	ClienteIf businessOperator;
	TipoDocumentoIf documentType;
	DocumentoIf document;
	BigDecimal approvedPayment;
	
	public PendingAccountData() {
		this.setPendingAccountWallet(SpiritConstants.getNullValue());
		this.setPendingAccountWalletDetail(SpiritConstants.getNullValue());
		this.setBusinessOperator(SpiritConstants.getNullValue());
		this.setDocumentType(SpiritConstants.getNullValue());
		this.setDocument(SpiritConstants.getNullValue());
		this.setApprovedPayment(SpiritConstants.getZeroValue());
	}

	public CarteraIf getPendingAccountWallet() {
		return pendingAccountWallet;
	}

	public void setPendingAccountWallet(Object pendingAccountWallet) {
		this.pendingAccountWallet = (pendingAccountWallet!= null)?(CarteraIf) pendingAccountWallet:null;
	}

	public CarteraDetalleIf getPendingAccountWalletDetail() {
		return pendingAccountWalletDetail;
	}

	public void setPendingAccountWalletDetail(Object pendingAccountWalletDetail) {
		this.pendingAccountWalletDetail = (pendingAccountWalletDetail!=null)?(CarteraDetalleIf) pendingAccountWalletDetail:null;
	}

	public ClienteIf getBusinessOperator() {
		return businessOperator;
	}

	public void setBusinessOperator(Object businessOperator) {
		this.businessOperator = (businessOperator!=null)?(ClienteIf) businessOperator:null;
	}

	public TipoDocumentoIf getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Object documentType) {
		this.documentType = (documentType!=null)?(TipoDocumentoIf) documentType:null;
	}

	public DocumentoIf getDocument() {
		return document;
	}

	public void setDocument(Object document) {
		this.document = (document!=null)?(DocumentoIf) document:null;
	}

	public BigDecimal getApprovedPayment() {
		return approvedPayment;
	}

	public void setApprovedPayment(BigDecimal approvedPayment) {
		this.approvedPayment = approvedPayment;
	}
}
