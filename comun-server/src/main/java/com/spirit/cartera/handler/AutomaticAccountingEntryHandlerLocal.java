package com.spirit.cartera.handler;

import java.util.List;
import java.util.Vector;

import javax.ejb.Local;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.PeriodoContableException;

@Local
public interface AutomaticAccountingEntryHandlerLocal {
	
	public AsientoIf generateAutomaticAccountingEntry(WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, boolean registerAutomaticAccountingEntry, boolean processAutomaticAccountingEntry, boolean update)  throws GenericBusinessException, PeriodoContableException;
	//public AsientoIf generateAutomaticAccountingEntry(WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, boolean processAutomaticAccountingEntry, boolean update)  throws GenericBusinessException, PeriodoContableException;
	public List<AsientoDetalleIf> getAccountingEntryDetailList();
	public void setAccountingEntryDetailList(List<AsientoDetalleIf> accountingEntryDetailList);
	
}
