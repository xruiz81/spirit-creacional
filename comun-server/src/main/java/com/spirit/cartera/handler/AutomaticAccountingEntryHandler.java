package com.spirit.cartera.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

import com.spirit.client.SpiritConstants;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.session.CompraSessionLocal;
import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.entity.SubtipoAsientoIf;
import com.spirit.contabilidad.handler.TipoEntidadEnum;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.CuentaEntidadSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.contabilidad.session.PeriodoSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.contabilidad.session.SubTipoAsientoSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.PeriodoContableException;
import com.spirit.facturacion.entity.FacturaIf;
import com.spirit.facturacion.entity.PedidoIf;
import com.spirit.facturacion.session.FacturaSessionLocal;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.medios.entity.PlanMedioIf;
import com.spirit.medios.entity.PresupuestoIf;
import com.spirit.medios.entity.ProductoClienteIf;
import com.spirit.medios.session.PlanMedioSessionLocal;
import com.spirit.medios.session.PresupuestoSessionLocal;
import com.spirit.sri.entity.SriClienteRetencionIf;
import com.spirit.sri.session.SriClienteRetencionSessionLocal;

@Stateless
public class AutomaticAccountingEntryHandler implements AutomaticAccountingEntryHandlerLocal {
	@PersistenceContext(unitName = "spirit")
	@EJB private AsientoSessionLocal accountingEntrySessionLocal;
	@EJB private SubTipoAsientoSessionLocal accountingEntrySubtypeSessionLocal;
	@EJB private PeriodoSessionLocal accountingPeriodSessionLocal;
	@EJB private EventoContableSessionLocal accountingEventSessionLocal;
	@EJB private PlantillaSessionLocal templateSessionLocal;
	@EJB private FacturaSessionLocal customerInvoiceSessionLocal;
	@EJB private PresupuestoSessionLocal bugdetSessionLocal;
	@EJB private PlanMedioSessionLocal mediaPlanSessionLocal;
	@EJB private CompraSessionLocal providerInvoiceSessionLocal;
	@EJB private PlanCuentaSessionLocal chartOfAccountsSessionLocal;
	@EJB private CuentaSessionLocal accountSessionLocal;
	@EJB private CuentaEntidadSessionLocal accountByEntitySessionLocal;
	@EJB private TipoDocumentoSessionLocal documentTypeSessionLocal;
	@EJB private UtilitariosSessionLocal utilitiesSessionLocal;
	@EJB private SriClienteRetencionSessionLocal sriCustomerRetentionSessionLocal;
	private static List<AsientoDetalleIf> accountingEntryDetailList;
	
	@SuppressWarnings("unchecked")
	public AsientoIf generateAutomaticAccountingEntry(WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, boolean registerAutomaticAccountingEntry, boolean processAutomaticAccountingEntry, boolean update) throws GenericBusinessException, PeriodoContableException {
	//public AsientoIf generateAutomaticAccountingEntry(WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, boolean processAutomaticAccountingEntry, boolean update) throws GenericBusinessException, PeriodoContableException {
		AsientoIf accountingEntry = null;
		DocumentoIf document = walletDetailData.getDocument();

		if (document != null) {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("documentoId", document.getId());
			if (!update)
				queryMap.put("validoAlGuardar", SpiritConstants.getOptionYes().substring(0,1));
			else
				queryMap.put("validoAlActualizar", SpiritConstants.getOptionYes().substring(0,1));
			Iterator<EventoContableIf> accountingEventIterator = accountingEventSessionLocal.findEventoContableByQuery(queryMap).iterator();
			if (accountingEventIterator.hasNext()) {
				EventoContableIf accountingEvent = accountingEventIterator.next();
				accountingEntry = processAccountingEvent(accountingEvent, walletData, walletDetailData, crossingWalletDetailDataVector, registerAutomaticAccountingEntry, processAutomaticAccountingEntry, update);
				//accountingEntry = processAccountingEvent(accountingEvent, walletData, walletDetailData, crossingWalletDetailDataVector, processAutomaticAccountingEntry, update);
			}
		}
		return accountingEntry;
	}
	
	@SuppressWarnings("unchecked")
	private AsientoIf processAccountingEvent(EventoContableIf accountingEvent, WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, boolean registerAutomaticAccountingEntry, boolean processAutomaticAccountingEntry, boolean update) throws GenericBusinessException, PeriodoContableException {
	//private AsientoIf processAccountingEvent(EventoContableIf accountingEvent, WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, boolean processAutomaticAccountingEntry, boolean update) throws GenericBusinessException, PeriodoContableException {
		AsientoIf accountingEntry = null;
		try {
			TipoDocumentoIf documentType = walletData.getDocumentType();
			DocumentoIf document = walletDetailData.getDocument();
			CuentaIf account = null;
			List<PlantillaIf> templateList = (ArrayList<PlantillaIf>) templateSessionLocal.findPlantillaByEventocontableId(accountingEvent.getId());
			Iterator<PlantillaIf> templateIterator = templateList.iterator();
			while (templateIterator.hasNext()) {
				PlantillaIf template = (PlantillaIf) templateIterator.next();
				account = accountSessionLocal.getCuenta(template.getCuentaId());
				if (account.getImputable().equals(SpiritConstants.getOptionNo().substring(0,1))) {
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("tipoEntidad", template.getTipoEntidad());
					queryMap.put("nemonico", template.getNemonico());
					Long entityId = null;
					if (template.getTipoEntidad() != SpiritConstants.getNullValue() && template.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.CUENTA_BANCARIA))) {
						if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0,1))) {
							if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1)))
								entityId = walletDetailData.getDepositAccount().getId();
							else
								entityId = walletDetailData.getCheckAccount().getId();
						} else if (document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1))) {
							if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1)))
								entityId = walletDetailData.getDepositAccount().getId();
							else
								entityId = walletDetailData.getDebitAccount().getId();
						} else if (document.getTransferenciaBancaria().equals(SpiritConstants.getOptionYes().substring(0,1))) {
							if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1)))
								entityId = walletDetailData.getTargetAccount().getId();
							else
								entityId = walletDetailData.getSourceAccount().getId();
						}
					} else if (template.getTipoEntidad() != SpiritConstants.getNullValue() && template.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.CLIENTE))) {
						entityId = walletData.getBusinessOperator().getId();
					} else if (template.getTipoEntidad() != SpiritConstants.getNullValue() && template.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.PROVEEDOR))) {
						entityId = walletData.getBusinessOperator().getId();
					} else if (template.getTipoEntidad() != SpiritConstants.getNullValue() && template.getTipoEntidad().equals(TipoEntidadEnum.getLetraTipoEntidad(TipoEntidadEnum.OFICINA))) {
						entityId = walletData.getOffice().getId();
					}
					
					queryMap.put("entidadId", entityId);
					Iterator<CuentaEntidadIf> it = accountByEntitySessionLocal.findCuentaEntidadByQuery(queryMap).iterator();
					if (it.hasNext()) {
						CuentaEntidadIf accountByEntity = it.next();
						account = accountSessionLocal.getCuenta(accountByEntity.getCuentaId());
					} else {
						boolean accountDefined = false;
						if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1))) {
							queryMap = new HashMap<String, Object>();
							if (template.getNemonico().equals(SpiritConstants.getRetentionIncomeMnemonic()) || template.getNemonico().equals(SpiritConstants.getRetentionIvaMnemonic())) {
								if (template.getNemonico().equals(SpiritConstants.getRetentionIncomeMnemonic()))
									queryMap.put("tipoRetencion", SpiritConstants.getRetentionIncomeType());
								if (template.getNemonico().equals(SpiritConstants.getRetentionIvaMnemonic()))
									queryMap.put("tipoRetencion", SpiritConstants.getRetentionIvaType());
								queryMap.put("porcentajeRetencion", walletDetailData.getRetentionPercentage());
								queryMap.put("estado", SpiritConstants.getActiveStatus().substring(0,1));
								Iterator<SriClienteRetencionIf> scrIt = sriCustomerRetentionSessionLocal.findSriClienteRetencionByQueryAndFecha((Map) queryMap, utilitiesSessionLocal.fromUtilDateToSqlDate(walletData.getEmissionDate())).iterator();
								if (scrIt.hasNext()) {
									SriClienteRetencionIf scr = scrIt.next();
									account = accountSessionLocal.getCuenta(scr.getCuentaId());
									accountDefined = true;
								}
							}
						} 
						if (!accountDefined) {
							if (template.getCuentaPredeterminadaId() != null)
								account = accountSessionLocal.getCuenta(template.getCuentaPredeterminadaId());
							else
								throw new GenericBusinessException("Se ha producido un error al procesar la transacción\nVerificar asociación de cuenta contable para nemónico " + template.getNemonico());
						}
					}
				}
				AsientoDetalleIf accountingEntryDetail = new AsientoDetalleData(); 
				accountingEntryDetail.setCuentaId(account.getId());
				String accountingEntryComment = (template.getGlosa() != null)?template.getGlosa() + SpiritConstants.getBlankSpaceCharacter():SpiritConstants.getEmptyCharacter();
				String invoiceAndWalletComment = getInvoiceAndWalletComment(walletData, walletDetailData);
				accountingEntryDetail.setGlosa(accountingEntryComment + SpiritConstants.getBlankSpaceCharacter() + walletData.getBusinessOperator().getRazonSocial() + SpiritConstants.getBlankSpaceCharacter() + invoiceAndWalletComment);
				if (walletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)))
					accountingEntryDetail.setReferencia(walletDetailData.getCheckNumber());
				double value = calculateEntryValue(walletData, walletDetailData, crossingWalletDetailDataVector, template.getFormula());
				if (template.getDebehaber().equals("D")) {
					accountingEntryDetail.setDebe(BigDecimal.valueOf(value));
					accountingEntryDetail.setHaber(BigDecimal.ZERO);
				} else if (template.getDebehaber().equals("H")) {
					accountingEntryDetail.setHaber(BigDecimal.valueOf(value));
					accountingEntryDetail.setDebe(BigDecimal.ZERO);
				}
				if (value > 0D)
					accountingEntryDetailList.add(accountingEntryDetail);
			}
			
			if (registerAutomaticAccountingEntry)
				accountingEntry = registerAccountingEntry(walletData, accountingEvent);
			if (processAutomaticAccountingEntry && (accountingEntryDetailList.size() > 0)) {
				if (accountingEntry != null) {
					String accountingEntryNumber = accountingEntrySessionLocal.procesarAsiento(accountingEntry, accountingEntryDetailList, true);
					Iterator<AsientoIf> it = accountingEntrySessionLocal.findAsientoByNumero(accountingEntryNumber).iterator();
					if (it.hasNext())
						accountingEntry = it.next();
					else
						throw new GenericBusinessException("Se ha producido un error al procesar la transacción\nNo se ha podido generar asiento contable");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof PeriodoContableException)
				throw new PeriodoContableException(e.getMessage());
			else 
				throw new GenericBusinessException(e.getMessage());
		}
		
		return accountingEntry;
	}
	
	private double calculateEntryValue(WalletData walletData, WalletDetailData walletDetailData, Vector<CrossingWalletDetailData> crossingWalletDetailDataVector, String formula) throws GenericBusinessException {
		double entryValue = 0D;
		if (formula != SpiritConstants.getNullValue() && formula.contains(SpiritConstants.getCalculatedCrossingValueInAccountingEntryTemplate())) {
			for (int i=0; i<crossingWalletDetailDataVector.size(); i++) {
				CrossingWalletDetailData crossingWalletDetailData = crossingWalletDetailDataVector.get(i);
				if (crossingWalletDetailData.getWalletDetailData().getSequentialNumber() == walletDetailData.getSequentialNumber()) {
					entryValue += crossingWalletDetailData.getValueToApply().doubleValue();
					walletData.setEmissionDate(crossingWalletDetailData.getApplyingDate());
				}
			}
			formula = formula.replaceAll(SpiritConstants.getCalculatedCrossingValueInAccountingEntryTemplate(), String.valueOf(entryValue));
		}
		if (formula != SpiritConstants.getNullValue() && formula.contains(SpiritConstants.getFixedValueInAccountingEntryTemplate()))
			formula = formula.replaceAll(SpiritConstants.getFixedValueInAccountingEntryTemplate(), String.valueOf(walletDetailData.getValue().doubleValue()));
		return utilitiesSessionLocal.evaluadorExpresionJavaScript(formula).doubleValue();
	}
	
	private AsientoIf registerAccountingEntry(WalletData walletData, EventoContableIf accountingEvent) throws GenericBusinessException, PeriodoContableException {
		AsientoData accountingEntry = new AsientoData();
		try {		
			java.sql.Date accountingEntryDate = utilitiesSessionLocal.fromUtilDateToSqlDate(walletData.getEmissionDate());
			PeriodoIf accountingPeriod = getAccountingPeriod(walletData.getEnterprise().getId(), accountingEntryDate);
			PlanCuentaIf chartOfAccounts = chartOfAccountsSessionLocal.getPlanCuenta(accountingEvent.getPlanCuentaId());
			String accountingEntryNumber = accountingEntrySessionLocal.getNumeroAsiento(accountingEntryDate, walletData.getEnterprise().getId(), chartOfAccounts);
			accountingEntry.setNumero(accountingEntryNumber);
			accountingEntry.setEmpresaId(walletData.getEnterprise().getId());
			accountingEntry.setPeriodoId(accountingPeriod.getId());
			accountingEntry.setPlancuentaId(accountingEvent.getPlanCuentaId());
			accountingEntry.setFecha(accountingEntryDate);
			accountingEntry.setStatus((accountingEvent.getAutorizacionRequerida().equals(SpiritConstants.getOptionYes().substring(0,1)))?SpiritConstants.getUnauthorizedAccountingEntry().substring(0,1):SpiritConstants.getAuthorizedAccountingEntry().substring(0,1));
			SubtipoAsientoIf accountingEntrySubtype = (accountingEvent.getSubtipoAsientoId() != null)?accountingEntrySubtypeSessionLocal.getSubtipoAsiento(accountingEvent.getSubtipoAsientoId()):(SubtipoAsientoIf) SpiritConstants.getNullValue();
			accountingEntry.setEfectivo((accountingEntrySubtype != (SubtipoAsientoIf) SpiritConstants.getNullValue())?SpiritConstants.getOptionYes().substring(0,1):SpiritConstants.getOptionNo().substring(0,1));
			accountingEntry.setSubtipoasientoId((accountingEntrySubtype != (SubtipoAsientoIf) SpiritConstants.getNullValue())?accountingEntrySubtype.getId():(Long) SpiritConstants.getNullValue());
			accountingEntry.setObservacion(getWalletComment(walletData) + SpiritConstants.getBlankSpaceCharacter() + walletData.getBusinessOperator().getRazonSocial());
			accountingEntry.setOficinaId(walletData.getOffice().getId());
			accountingEntry.setTipoDocumentoId(walletData.getDocumentType().getId());
			accountingEntry.setTransaccionId(walletData.getWalletId());
			accountingEntry.setElaboradoPorId(walletData.getUser().getId());
			accountingEntry.setAutorizadoPorId((accountingEvent.getAutorizacionRequerida().equals(SpiritConstants.getOptionYes().substring(0,1)))?walletData.getUser().getId():(Long) SpiritConstants.getNullValue());
			accountingEntry.setEventoContableId(accountingEvent.getId());
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof PeriodoContableException)
				throw new PeriodoContableException(e.getMessage());
			else 
				throw new GenericBusinessException(e.getMessage());
		}
		return accountingEntry;
	}
	
	@SuppressWarnings("unchecked")
	private PeriodoIf getAccountingPeriod(Long enterpriseId, java.sql.Date accountingEntryDate) throws GenericBusinessException, PeriodoContableException {
		Iterator<PeriodoIf> it = (Iterator<PeriodoIf>) SpiritConstants.getNullValue();
		try {
			try {
				it = accountingPeriodSessionLocal.findPeriodoForAsientoAutomatico(enterpriseId, accountingEntryDate).iterator();
			} catch (GenericBusinessException gbe) {
				throw new GenericBusinessException("Se ha producido un error general al procesar la transacción");
			}
			if (it.hasNext()) {
				return it.next();
			} else {
				throw new PeriodoContableException("Se ha producido un error al procesar la transacción\nVerificar que el periodo contable correspondiente se encuentre en estado activo o parcial");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof PeriodoContableException)
				throw new PeriodoContableException(e.getMessage());
			else 
				throw new GenericBusinessException(e.getMessage());
		}
	}
	
	public String getInvoiceAndWalletComment(WalletData walletData, WalletDetailData walletDetailData) throws GenericBusinessException {
		String invoiceComment = SpiritConstants.getEmptyCharacter();
		String walletComment = SpiritConstants.getEmptyCharacter();
		if (walletDetailData.getDocument().getRetencionIva().equals(SpiritConstants.getOptionYes().substring(0,1)) || walletDetailData.getDocument().getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1)) 
				|| walletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)) || walletDetailData.getDocument().getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1))
				|| walletDetailData.getDocument().getTransferenciaBancaria().equals(SpiritConstants.getOptionYes().substring(0,1)) || walletDetailData.getDocument().getTarjetaCredito().equals(SpiritConstants.getOptionYes().substring(0,1))
				|| walletDetailData.getDocument().getTransaccionElectronica().equals(SpiritConstants.getOptionYes().substring(0,1))) {
			//walletComment = (walletData.getDocumentType().getAbreviatura() != null)?walletData.getDocumentType().getAbreviatura():walletData.getDocumentType().getCodigo();
			//walletComment +=  SpiritConstants.getColonCharacter() + SpiritConstants.getBlankSpaceCharacter();
			if (walletDetailData.getDocument().getRetencionIva().equals(SpiritConstants.getOptionYes().substring(0,1)) || walletDetailData.getDocument().getRetencionRenta().equals(SpiritConstants.getOptionYes().substring(0,1)))
				 walletComment += "R: " + walletDetailData.getRetentionNumber();
			//else if (walletDetailData.getDocument().getCheque().equals(SpiritConstants.getOptionYes().substring(0,1)))
				//walletComment += "CH.# " + walletDetailData.getCheckNumber();
			else if (walletDetailData.getDocument().getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1)))
				walletComment += "D/B: " + walletDetailData.getDebitReference();
			else if (walletDetailData.getDocument().getTarjetaCredito().equals(SpiritConstants.getOptionYes().substring(0,1)))
				walletComment += "REF: " + walletDetailData.getVoucherReference();
			else if (walletDetailData.getDocument().getTransaccionElectronica().equals(SpiritConstants.getOptionYes().substring(0,1)))
				walletComment += "REF: " + walletDetailData.getElectronicPaymentReference();
			if (walletData.getWalletType().equals(SpiritConstants.getCustomerWalletType().substring(0,1))) {
				Iterator<Object[]> invoiceIterator = customerInvoiceSessionLocal.findFacturaByCarteraDetalleComprobante(walletDetailData.getWalletDetailId()).iterator();
				int n = 0;
				while (invoiceIterator.hasNext()) {
					Object[] o = invoiceIterator.next();
					FacturaIf invoice = (FacturaIf) o[0];
					PedidoIf order = (PedidoIf) o[1];
					TipoDocumentoIf invoiceDocumentType = documentTypeSessionLocal.getTipoDocumento(invoice.getTipodocumentoId());
					if (n > 0)
						invoiceComment += "\n";
					invoiceComment += invoiceDocumentType.getAbreviatura() + SpiritConstants.getColonCharacter() + SpiritConstants.getBlankSpaceCharacter();
					invoiceComment += (invoice.getPreimpresoNumero() != SpiritConstants.getNullValue())?invoice.getPreimpresoNumero().split(SpiritConstants.getPlaceholderCharacter())[SpiritConstants.getSerialNumberSplitPosition()]:SpiritConstants.getWithoutSerialNumber();
					Map<String, Object> queryMap = new HashMap<String, Object>();
					queryMap.put("enterpriseId", walletData.getEnterprise().getId());
					queryMap.put("orderId", order.getId());
					if (order.getTiporeferencia().equals(SpiritConstants.getBudgetReferenceType())) {
						Iterator<Object[]> budgetIterator = bugdetSessionLocal.findPresupuestoByPedido(queryMap).iterator();
						while (budgetIterator.hasNext()) {
							Object[] budgetData = (Object[]) budgetIterator.next();
							PresupuestoIf budget = (PresupuestoIf) budgetData[0];
							ProductoClienteIf customerProduct = (ProductoClienteIf) budgetData[1];
							invoiceComment += SpiritConstants.getSemicolonCharacter() + SpiritConstants.getBlankSpaceCharacter() + "P: " + budget.getCodigo() + SpiritConstants.getSemicolonCharacter() + SpiritConstants.getBlankSpaceCharacter() + customerProduct.getNombre();
						}
					} else if (order.getTiporeferencia().equals(SpiritConstants.getMediaPlanReferencieType())) {
						Iterator<Object[]> mediaPlanIterator = mediaPlanSessionLocal.findPlanMedioByPedido(queryMap).iterator();
						while (mediaPlanIterator.hasNext()) {
							Object[] mediaPlanData = (Object[]) mediaPlanIterator.next();
							PlanMedioIf mediaPlan = (PlanMedioIf) mediaPlanData[0];
							ProductoClienteIf customerProduct = (ProductoClienteIf) mediaPlanData[1];
							invoiceComment += SpiritConstants.getSemicolonCharacter() + SpiritConstants.getBlankSpaceCharacter() + "PM: " + mediaPlan.getCodigo() + SpiritConstants.getSemicolonCharacter() + SpiritConstants.getBlankSpaceCharacter() + customerProduct.getNombre();
						}
					} else if (order.getTiporeferencia().equals(SpiritConstants.getNoneReferenceType())) {
						invoiceComment += SpiritConstants.getSemicolonCharacter() + SpiritConstants.getBlankSpaceCharacter() + "S/R";
					}
					n++;
				}
			} else {
				Iterator<CompraIf> invoiceIterator = providerInvoiceSessionLocal.findCompraByCarteraDetalleComprobante(walletDetailData.getWalletDetailId()).iterator();
				int n = 0;
				while (invoiceIterator.hasNext()) {
					CompraIf invoice = (CompraIf) invoiceIterator.next();
					TipoDocumentoIf invoiceDocumentType = documentTypeSessionLocal.getTipoDocumento(invoice.getTipodocumentoId());
					if (n > 0)
						invoiceComment += "\n" + SpiritConstants.getSemicolonCharacter();
					invoiceComment += invoiceDocumentType.getAbreviatura() + SpiritConstants.getColonCharacter() + SpiritConstants.getBlankSpaceCharacter();
					invoiceComment += (invoice.getPreimpreso() != SpiritConstants.getNullValue())?invoice.getPreimpreso().split(SpiritConstants.getPlaceholderCharacter())[SpiritConstants.getSerialNumberSplitPosition()]:SpiritConstants.getWithoutSerialNumber();
					n++;
				}
			}
			if (!invoiceComment.equals(SpiritConstants.getEmptyCharacter()))
				invoiceComment +=  SpiritConstants.getBlankSpaceCharacter(); 
		} else {
			walletComment = getWalletComment(walletData);
		}
		if (!walletComment.equals(SpiritConstants.getEmptyCharacter()))
			walletComment +=  SpiritConstants.getBlankSpaceCharacter();
		String invoiceAndWalletComment = walletComment + invoiceComment;
		return invoiceAndWalletComment;
	}
	
	public String getWalletComment(WalletData walletData) {
		String walletComment = (walletData.getDocumentType().getAbreviatura() != SpiritConstants.getNullValue())?walletData.getDocumentType().getAbreviatura():walletData.getDocumentType().getCodigo();
		walletComment += SpiritConstants.getColonCharacter() + SpiritConstants.getBlankSpaceCharacter();
		//int splitLength = walletData.getWalletCodeNumber().split(SpiritConstants.getPlaceholderCharacter()).length;
		//walletComment += walletData.getWalletCodeNumber().split(SpiritConstants.getPlaceholderCharacter())[splitLength - 1];
		int firstOccurrence = walletData.getWalletCodeNumber().indexOf(SpiritConstants.getPlaceholderCharacter());
		walletComment += walletData.getWalletCodeNumber().substring(walletData.getWalletCodeNumber().indexOf(SpiritConstants.getPlaceholderCharacter(), firstOccurrence + 1) + 1, walletData.getWalletCodeNumber().length());
		//walletComment += walletData.getWalletCodeNumber();
		return walletComment;
	}
	
	public List<AsientoDetalleIf> getAccountingEntryDetailList() {
		return accountingEntryDetailList;
	}

	public void setAccountingEntryDetailList(List<AsientoDetalleIf> accountingEntryDetailList) {
		AutomaticAccountingEntryHandler.accountingEntryDetailList = accountingEntryDetailList;
	}	
}
