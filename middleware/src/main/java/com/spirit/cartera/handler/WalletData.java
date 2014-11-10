package com.spirit.cartera.handler;

import java.io.Serializable;
import java.math.BigDecimal;

import com.spirit.client.SpiritConstants;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.MonedaIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;

public class WalletData implements Serializable {
	private static final long serialVersionUID = -2503449127263355395L;
	/* Fields */
	private Long walletId;
	private String walletType;
	private String walletCodeNumber;
	private java.util.Date emissionDate;
	private java.util.Date creationDate;
	private java.util.Date lastUpdateDate;
	private EmpresaIf enterprise;
	private OficinaIf office;
	private UsuarioIf user;
	private TipoDocumentoIf documentType;
	private Long referenceId;
	private MonedaIf currency;
	private ClienteIf businessOperator;
	private ClienteOficinaIf businessOperatorOffice;
	private String status;
	private String comment;
	private BigDecimal total;
	private BigDecimal balance;
	private String activateRetrocompatibility;
	
	public WalletData() {
		this.setWalletId(SpiritConstants.getNullValue());
		this.setWalletType(SpiritConstants.getEmptyCharacter());
		this.setWalletCodeNumber(SpiritConstants.getEmptyCharacter());
		this.setEmissionDate(SpiritConstants.getNullValue());
		this.setCreationDate(SpiritConstants.getNullValue());
		this.setLastUpdateDate(SpiritConstants.getNullValue());
		this.setEnterprise(SpiritConstants.getNullValue());
		this.setOffice(SpiritConstants.getNullValue());
		this.setUser(SpiritConstants.getNullValue());
		this.setDocumentType(SpiritConstants.getNullValue());
		this.setReferenceId(SpiritConstants.getNullValue());
		this.setCurrency(SpiritConstants.getNullValue());
		this.setBusinessOperator(SpiritConstants.getNullValue());
		this.setBusinessOperatorOffice(SpiritConstants.getNullValue());
		this.setStatus(SpiritConstants.getEmptyCharacter());
		this.setComment(SpiritConstants.getEmptyCharacter());
		this.setTotal(SpiritConstants.getZeroValue());
		this.setBalance(SpiritConstants.getZeroValue());
		this.setActivateRetrocompatibility(SpiritConstants.getEmptyCharacter());
	}

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Object walletId) {
		this.walletId = (walletId != null)?(Long) walletId:null;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public String getWalletCodeNumber() {
		return walletCodeNumber;
	}

	public void setWalletCodeNumber(String walletCodeNumber) {
		this.walletCodeNumber = walletCodeNumber;
	}

	public java.util.Date getEmissionDate() {
		return emissionDate;
	}

	public void setEmissionDate(Object emissionDate) {
		this.emissionDate = (emissionDate != null)?(java.util.Date) emissionDate:null;
	}

	public java.util.Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Object creationDate) {
		this.creationDate = (creationDate != null)?(java.util.Date) creationDate:null;
	}

	public java.util.Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Object lastUpdateDate) {
		this.lastUpdateDate = (lastUpdateDate != null)?(java.util.Date) lastUpdateDate:null;
	}
	
	public EmpresaIf getEnterprise() {
		return enterprise;
	}
	
	public void setEnterprise(Object enterprise) {
		this.enterprise = (enterprise != null)?(EmpresaIf) enterprise:null;
	}

	public OficinaIf getOffice() {
		return office;
	}

	public void setOffice(Object office) {
		this.office = (office != null)?(OficinaIf) office:null;
	}
	
	public UsuarioIf getUser() {
		return user;
	}
	
	public void setUser(Object user) {
		this.user = (user != null)?(UsuarioIf) user:null;
	}

	public TipoDocumentoIf getDocumentType() {
		return documentType;
	}
	
	public void setDocumentType(Object documentType) {
		this.documentType = (documentType != null)?(TipoDocumentoIf) documentType:null;
	}
	
	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Object referenceId) {
		this.referenceId = (referenceId != null)?(Long) referenceId:null;
	}

	public MonedaIf getCurrency() {
		return currency;
	}
	
	public void setCurrency(Object currency) {
		this.currency = (currency != null)?(MonedaIf) currency:null;
	}
	
	public ClienteIf getBusinessOperator() {
		return businessOperator;
	}
	
	public void setBusinessOperator(Object businessOperator) {
		this.businessOperator = (businessOperator != null)?(ClienteIf) businessOperator:null;
	}

	public ClienteOficinaIf getBusinessOperatorOffice() {
		return businessOperatorOffice;
	}

	public void setBusinessOperatorOffice(Object businessOperatorOffice) {
		this.businessOperatorOffice = (businessOperatorOffice != null)?(ClienteOficinaIf) businessOperatorOffice:null;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getActivateRetrocompatibility() {
		return activateRetrocompatibility;
	}

	public void setActivateRetrocompatibility(String activateRetrocompatibility) {
		this.activateRetrocompatibility = activateRetrocompatibility;
	}
}
