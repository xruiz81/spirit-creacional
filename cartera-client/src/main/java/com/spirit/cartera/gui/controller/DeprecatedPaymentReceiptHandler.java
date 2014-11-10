package com.spirit.cartera.gui.controller;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.spirit.cartera.entity.CarteraAfectaIf;
import com.spirit.cartera.entity.CarteraDetalleIf;
import com.spirit.cartera.entity.CarteraIf;
import com.spirit.cartera.handler.ComprobanteEgresoData;
import com.spirit.cartera.handler.WalletData;
import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.client.SpiritConstants;
import com.spirit.client.model.ReportModelImpl;
import com.spirit.compras.entity.CompraIf;
import com.spirit.compras.entity.OrdenAsociadaIf;
import com.spirit.compras.entity.OrdenCompraIf;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.crm.entity.ClienteIf;
import com.spirit.crm.entity.ClienteOficinaIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.BancoIf;
import com.spirit.general.entity.CiudadIf;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.medios.entity.OrdenMedioIf;
import com.spirit.seguridad.entity.MenuIf;
import com.spirit.util.Utilitarios;

public class DeprecatedPaymentReceiptHandler {
	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
	private Map<Long,TipoDocumentoIf> documentTypeMap = new HashMap<Long,TipoDocumentoIf>();
	
	public DeprecatedPaymentReceiptHandler() {
		documentTypeMap = mappingDocumentTypes();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void printReport(WalletData wallet, boolean advancePayment, boolean annulled) {
		try {
			AsientoIf accountingEntry = null;
			Map<String, Long> accountingEntryMap = new HashMap<String, Long>();
			accountingEntryMap.put("tipoDocumentoId", wallet.getDocumentType().getId());
			accountingEntryMap.put("transaccionId", wallet.getWalletId());
			if(!SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(accountingEntryMap).isEmpty())
				accountingEntry = (AsientoIf) SessionServiceLocator.getAsientoSessionService().findAsientoByQuery(accountingEntryMap).iterator().next();	

			Vector<ComprobanteEgresoData> paymentReceiptVector = new Vector<ComprobanteEgresoData>();
			Iterator<CarteraDetalleIf> walletDetailIterator = SessionServiceLocator.getCarteraDetalleSessionService().findCarteraDetalleByCarteraId(wallet.getWalletId()).iterator();
			ClienteOficinaIf providerOffice = SessionServiceLocator.getClienteOficinaSessionService().getClienteOficina(wallet.getBusinessOperatorOffice().getId());
			ClienteIf provider = SessionServiceLocator.getClienteSessionService().getCliente(providerOffice.getClienteId());
			String receiptValue = (!wallet.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)))?decimalFormat.format(Utilitarios.redondeoValor(wallet.getTotal().doubleValue())):decimalFormat.format(0D);
			String receiptAppliedTotal = (!wallet.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)))?decimalFormat.format(Utilitarios.redondeoValor(wallet.getTotal().subtract(wallet.getBalance()).doubleValue())):SpiritConstants.getPlaceholderCharacter();
			/*String receiptValue = "";
			if (advancePayment && annulled) {
				receiptValue = decimalFormat.format(Double.valueOf(wallet.getBalance().subtract(wallet.getTotal()).abs().toString()));
			} else if (advancePayment) {
				receiptValue = decimalFormat.format(Double.valueOf(wallet.getTotal().abs().toString()));
			} else {
				receiptValue = decimalFormat.format(Double.valueOf(wallet.getBalance().subtract(wallet.getTotal()).abs().toString()));
			}*/
			String decimalPart = receiptValue.substring(receiptValue.indexOf(SpiritConstants.getPeriodCharacter()), receiptValue.length());
			Double dDecimalPart = 0.0;
			if (!decimalPart.isEmpty())
				dDecimalPart = Double.valueOf(decimalPart);
			String[] amountInLetters = Utilitarios.obtenerCantidadEnLetras(receiptValue, dDecimalPart, new int[] { 90 }, false, wallet.getCurrency());
			String amountInLettersFirstLine = amountInLetters[0].replaceAll(SpiritConstants.getBlankSpaceCharacter() + SpiritConstants.getBlankSpaceCharacter(), SpiritConstants.getEmptyCharacter());
			String date = Utilitarios.getStringDateFromDate(wallet.getEmissionDate());
			String day = date.substring(0,2);
			String month = date.substring(3,5);
			String year = date.substring(6,10);
			String emissionDate = Utilitarios.getNombreMes(Integer.parseInt(month)) + " " + day + " DEL " + year;
			while (walletDetailIterator.hasNext()) {
				CarteraDetalleIf walletDetail = (CarteraDetalleIf) walletDetailIterator.next();
				if (advancePayment) {
					Iterator<CarteraAfectaIf> crossingWalletIterator = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(walletDetail.getId()).iterator();
					if (crossingWalletIterator.hasNext()) {
						while (crossingWalletIterator.hasNext()) {
							CarteraAfectaIf crossingWallet = (CarteraAfectaIf) crossingWalletIterator.next();
							ComprobanteEgresoData paymentReceiptData = addAdvancePaymentReceiptDetail(walletDetail, crossingWallet, annulled);
							paymentReceiptData.setFechaEmision(emissionDate);
							paymentReceiptData.setProveedor(provider.getRazonSocial());
							if (annulled) {
								paymentReceiptData.setValorTotal(decimalFormat.format(0D));
								paymentReceiptData.setCantidad("CERO 00/100 DÓLARES");
							} else {
								paymentReceiptData.setValorTotal(receiptValue);
								paymentReceiptData.setCantidad(amountInLettersFirstLine);
							}
							paymentReceiptData.setConcepto(wallet.getComment());
							paymentReceiptData.setCodigoAsiento(accountingEntry != null ? accountingEntry.getNumero() : "N/A");
							paymentReceiptData.setCodigo(wallet.getWalletCodeNumber());
							paymentReceiptData.setAnulado(wallet.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)));
							paymentReceiptData.setTotalAplicado(receiptAppliedTotal);
							paymentReceiptData.setSaldoTotal(decimalFormat.format(Utilitarios.redondeoValor(wallet.getBalance())));
							paymentReceiptVector.add(paymentReceiptData);
						}
					} else {
						ComprobanteEgresoData paymentReceiptData = addAdvancePaymentReceiptDetail(walletDetail, (CarteraAfectaIf) SpiritConstants.getNullValue(), annulled);
						paymentReceiptData.setFechaEmision(emissionDate);
						paymentReceiptData.setProveedor(provider.getRazonSocial());
						if (annulled) {
							paymentReceiptData.setValorTotal(decimalFormat.format(0D));
							paymentReceiptData.setCantidad("CERO 00/100 DÓLARES");
						} else {
							paymentReceiptData.setValorTotal(receiptValue);
							paymentReceiptData.setCantidad(amountInLettersFirstLine);
						}
						paymentReceiptData.setConcepto(wallet.getComment());
						paymentReceiptData.setCodigoAsiento(accountingEntry != null ? accountingEntry.getNumero() : "N/A");
						paymentReceiptData.setCodigo(wallet.getWalletCodeNumber());
						paymentReceiptData.setAnulado(wallet.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)));
						paymentReceiptData.setTotalAplicado(receiptAppliedTotal);
						paymentReceiptData.setSaldoTotal(decimalFormat.format(Utilitarios.redondeoValor(wallet.getBalance())));
						paymentReceiptVector.add(paymentReceiptData);
					}
				} else if (annulled) {
					ComprobanteEgresoData paymentReceiptData = addPaymentReceiptDetail(walletDetail, (CarteraAfectaIf) SpiritConstants.getNullValue(), annulled);
					paymentReceiptData.setFechaEmision(emissionDate);
					paymentReceiptData.setProveedor(provider.getRazonSocial());
					paymentReceiptData.setValorTotal(decimalFormat.format(0D));
					paymentReceiptData.setCantidad("CERO 00/100 DÓLARES");
					paymentReceiptData.setConcepto(wallet.getComment());
					paymentReceiptData.setCodigoAsiento(accountingEntry != null ? accountingEntry.getNumero() : "N/A");
					paymentReceiptData.setCodigo(wallet.getWalletCodeNumber());
					paymentReceiptData.setAnulado(wallet.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)));
					paymentReceiptData.setTotalAplicado(receiptAppliedTotal);
					paymentReceiptData.setSaldoTotal(decimalFormat.format(Utilitarios.redondeoValor(wallet.getBalance())));
					paymentReceiptVector.add(paymentReceiptData);
				} else {
					Iterator<CarteraAfectaIf> crossingWalletIterator = SessionServiceLocator.getCarteraAfectaSessionService().findCarteraAfectaByCarteradetalleId(walletDetail.getId()).iterator();
					while (crossingWalletIterator.hasNext()) {
						CarteraAfectaIf crossingWallet = (CarteraAfectaIf) crossingWalletIterator.next();
						ComprobanteEgresoData paymentReceiptData = addPaymentReceiptDetail(walletDetail, crossingWallet, annulled);
						paymentReceiptData.setFechaEmision(emissionDate);
						paymentReceiptData.setProveedor(provider.getRazonSocial());
						paymentReceiptData.setConcepto(wallet.getComment());
						paymentReceiptData.setCodigoAsiento(accountingEntry != null ? accountingEntry.getNumero() : "N/A");
						paymentReceiptData.setValorTotal(receiptValue);
						paymentReceiptData.setCantidad(amountInLettersFirstLine);
						paymentReceiptData.setCodigo(wallet.getWalletCodeNumber());
						paymentReceiptData.setAnulado(wallet.getStatus().equals(WalletConstants.getNullifyStatus().substring(0,1)));
						paymentReceiptData.setTotalAplicado(receiptAppliedTotal);
						paymentReceiptData.setSaldoTotal(decimalFormat.format(Utilitarios.redondeoValor(wallet.getBalance())));
						paymentReceiptVector.add(paymentReceiptData);
					}
				}				
			}

			if (paymentReceiptVector.size() > 0) {
				int opcion = JOptionPane.showOptionDialog(null, "¿Desea generar el reporte para imprimir?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, SpiritConstants.getOptions(), SpiritConstants.getOptionNo());
				if (opcion == JOptionPane.YES_OPTION) {
					String fileName = "jaspers/cartera/RPComprobanteEgreso.jasper";
					HashMap parameterMap = new HashMap();
					MenuIf menu = (MenuIf) SessionServiceLocator.getMenuSessionService().findMenuByNombre("CARTERA").iterator().next();
					parameterMap.put("codigoReporte", menu.getCodigo());
					EmpresaIf enterprise = (EmpresaIf) Parametros.getEmpresa();
					parameterMap.put("empresa", enterprise.getNombre());
					parameterMap.put("ruc", enterprise.getRuc());
					parameterMap.put("codigo", wallet.getWalletCodeNumber());
					CiudadIf city = (CiudadIf) Parametros.getCiudad();
					parameterMap.put("ciudad", city.getNombre());
					parameterMap.put("urlLogoEmpresa", enterprise.getLogo());
					parameterMap.put("usuario", Parametros.getUsuario().toLowerCase());
					ReportModelImpl.processReport(fileName, parameterMap, paymentReceiptVector, true);
				}
			} else
				SpiritAlert.createAlert("No existen datos para imprimir", SpiritAlert.WARNING);
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}	
	}
	
	private ComprobanteEgresoData addPaymentReceiptDetail(CarteraDetalleIf walletDetail, CarteraAfectaIf crossingWallet, boolean annulled) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		try {
			DocumentoIf document = SessionServiceLocator.getDocumentoSessionService().getDocumento(walletDetail.getDocumentoId());
			CarteraIf crossedWallet = null;
			CompraIf invoice = null;
			/*if (annulled) {
			if (!document.getCodigo().equals("CFP")) {
				Long invoiceWalletId = Long.valueOf(walletDetail.getReferencia());
				crossedWallet = SessionServiceLocator.getCarteraSessionService().getCartera(invoiceWalletId);
				TipoDocumentoIf documentType = documentTypeMap.get(crossedWallet.getTipodocumentoId());
				if (documentType.getCodigo().equals("COM") || documentType.getCodigo().equals("COR") || documentType.getCodigo().equals("LIC") || documentType.getCodigo().equals("COI") || documentType.getCodigo().equals("CNV") || documentType.getCodigo().equals("SAE"))
					invoice = SessionServiceLocator.getCompraSessionService().getCompra(crossedWallet.getReferenciaId());
			}
			} else if (crossingWallet != (CarteraAfectaIf) SpiritConstants.getNullValue()) {*/
			if (!annulled && crossingWallet != (CarteraAfectaIf) SpiritConstants.getNullValue()) {
				CarteraDetalleIf crossedInvoiceWalletDetail = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(crossingWallet.getCarteraafectaId());
				crossedWallet = SessionServiceLocator.getCarteraSessionService().getCartera(crossedInvoiceWalletDetail.getCarteraId());
				TipoDocumentoIf documentType = documentTypeMap.get(crossedWallet.getTipodocumentoId());
				if (documentType.getCodigo().equals("COM") || documentType.getCodigo().equals("COR") || documentType.getCodigo().equals("LIC") || documentType.getCodigo().equals("COI") || documentType.getCodigo().equals("CNV") || documentType.getCodigo().equals("SAE"))					
					invoice = SessionServiceLocator.getCompraSessionService().getCompra(crossedWallet.getReferenciaId());
			}			
			
			if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0, 1)) || document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0, 1))) {
					CuentaBancariaIf bankAccount = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(walletDetail.getChequeCuentaBancariaId());
					BancoIf bank = SessionServiceLocator.getBancoSessionService().getBanco(bankAccount.getBancoId());
					data.setBanco(bank.getNombre());
					data.setNumeroCuenta(bankAccount.getCuenta());
					data.setNumeroCheque(walletDetail.getChequeNumero());
				} else {
					CuentaBancariaIf bankAccount = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(walletDetail.getDebitoCuentaBancariaId());
					BancoIf bank = SessionServiceLocator.getBancoSessionService().getBanco(bankAccount.getBancoId());
					data.setBanco(bank.getNombre());
					data.setNumeroCuenta(bankAccount.getCuenta());
					data.setNumeroCheque("D/B");
				}
			} else if (document.getEfectivo().equals(SpiritConstants.getOptionYes().substring(0, 1))) {
				data.setBanco("PAGO EN EFECTIVO");
				data.setNumeroCuenta("N/A");
				data.setNumeroCheque("N/A");
			}
			
			if (!document.getCodigo().equals("CFP") && invoice != null && crossedWallet != null) {
				data.setFechaCompra(Utilitarios.getFechaCortaUppercase((invoice!=null)?invoice.getFecha():crossedWallet.getFechaEmision()));
				data.setCodigoCompra((invoice!=null)?invoice.getCodigo():crossedWallet.getCodigo());
			} else {
				data.setFechaCompra("N/A");
				data.setCodigoCompra("N/A");
			}
						
			if (annulled) {
				data.setFechaCompra(SpiritConstants.getEmptyCharacter());
				data.setPreimpresoFactura(SpiritConstants.getEmptyCharacter());
				data.setDetalle(SpiritConstants.getEmptyCharacter());
				data.setValor(SpiritConstants.getEmptyCharacter());
				data.setSaldo(SpiritConstants.getEmptyCharacter());
			} else {
				data.setPreimpresoFactura((invoice!=null)?" F# " + invoice.getPreimpreso():"N/A");
				if (invoice != null) {
					//data.setDetalle(invoice.getObservacion().length()>52?invoice.getObservacion().substring(0,52):invoice.getObservacion());
					
					Collection ordenesAsociadas = SessionServiceLocator.getOrdenAsociadaSessionService().findOrdenAsociadaByCompraId(invoice.getId());
					if(ordenesAsociadas.size() > 0){
						String ordenCodigo = "";
						String clienteNombreLegal = "";
						OrdenAsociadaIf ordenAsociada = (OrdenAsociadaIf)ordenesAsociadas.iterator().next();
						if(ordenAsociada.getTipoOrden().equals("OC")){
							Collection ordenesCompraPresupuestosClientesClientesOficina = SessionServiceLocator.getCompraSessionService().findOrdenCompraPresupuestoClienteOficinaClienteByCompraId(invoice.getId());
							Iterator ordenesCompraPresupuestosClientesClientesOficinaIt = ordenesCompraPresupuestosClientesClientesOficina.iterator();
							while(ordenesCompraPresupuestosClientesClientesOficinaIt.hasNext()){
								Object[] ordenCompraPresupuestoClienteClienteOficina = (Object[])ordenesCompraPresupuestosClientesClientesOficinaIt.next();
								OrdenCompraIf ordenCompra = (OrdenCompraIf)ordenCompraPresupuestoClienteClienteOficina[0];
								ClienteIf cliente = (ClienteIf)ordenCompraPresupuestoClienteClienteOficina[3];
								ordenCodigo = ordenCompra.getCodigo().split("-")[1];
								clienteNombreLegal = cliente.getNombreLegal();
							}
								
						}else if(ordenAsociada.getTipoOrden().equals("OM")){
							Collection ordenesMedioClientesClientesOficina = SessionServiceLocator.getCompraSessionService().findOrdenMedioClienteOficinaClienteByCompraId(invoice.getId());
							Iterator ordenesMedioClientesClientesOficinaIt = ordenesMedioClientesClientesOficina.iterator();
							while(ordenesMedioClientesClientesOficinaIt.hasNext()){
								Object[] ordenMedioClienteClienteOficina = (Object[])ordenesMedioClientesClientesOficinaIt.next();
								OrdenMedioIf ordenMedio = (OrdenMedioIf)ordenMedioClienteClienteOficina[0];
								ClienteIf cliente = (ClienteIf)ordenMedioClienteClienteOficina[2];
								ordenCodigo = ordenMedio.getCodigo().split("-")[1];
								clienteNombreLegal = cliente.getNombreLegal();
							}
						}
						data.setDetalle("Orden: " + ordenCodigo + ", Cliente: " + clienteNombreLegal);
						
					}else{
						data.setDetalle(invoice.getObservacion().length()>52?invoice.getObservacion().substring(0,52):invoice.getObservacion());
					}
					
				} else if (crossedWallet != null) {
					data.setDetalle(crossedWallet.getComentario().length()>52?crossedWallet.getComentario().substring(0,52):crossedWallet.getComentario());
				} else {
					data.setDetalle("N/A");
				}
				if (crossingWallet != null)
					data.setValor(decimalFormat.format(crossingWallet.getValor().doubleValue()));
				else
					data.setValor("N/A");
				if (crossedWallet != null)
					data.setSaldo(decimalFormat.format(crossedWallet.getSaldo().doubleValue()));
				else
					data.setSaldo("N/A");
			}			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	private ComprobanteEgresoData addAdvancePaymentReceiptDetail(CarteraDetalleIf walletDetail, CarteraAfectaIf crossingWallet, boolean annulled) {
		ComprobanteEgresoData data = new ComprobanteEgresoData();
		
		try {
			DocumentoIf document = SessionServiceLocator.getDocumentoSessionService().getDocumento(walletDetail.getDocumentoId());
			CarteraIf crossedWallet = null;
			CompraIf invoice = null;
			/*if (annulled) {
				if (!document.getCodigo().equals("CFP")) {
					Long invoiceWalletId = Long.valueOf(walletDetail.getReferencia());
					crossedWallet = SessionServiceLocator.getCarteraSessionService().getCartera(invoiceWalletId);
					TipoDocumentoIf documentType = documentTypeMap.get(crossedWallet.getTipodocumentoId());
					if (documentType.getCodigo().equals("COM") || documentType.getCodigo().equals("COR") || documentType.getCodigo().equals("LIC") || documentType.getCodigo().equals("COI") || documentType.getCodigo().equals("CNV") || documentType.getCodigo().equals("SAE"))
						invoice = SessionServiceLocator.getCompraSessionService().getCompra(crossedWallet.getReferenciaId());
				}
			} else if (crossingWallet != (CarteraAfectaIf) SpiritConstants.getNullValue()) {*/
			if (!annulled && crossingWallet != (CarteraAfectaIf) SpiritConstants.getNullValue()) {
				CarteraDetalleIf crossedInvoiceWalletDetail = SessionServiceLocator.getCarteraDetalleSessionService().getCarteraDetalle(crossingWallet.getCarteraafectaId());
				crossedWallet = SessionServiceLocator.getCarteraSessionService().getCartera(crossedInvoiceWalletDetail.getCarteraId());
				TipoDocumentoIf documentType = documentTypeMap.get(crossedWallet.getTipodocumentoId());
				if (documentType.getCodigo().equals("COM") || documentType.getCodigo().equals("COR") || documentType.getCodigo().equals("LIC") || documentType.getCodigo().equals("COI") || documentType.getCodigo().equals("CNV") || documentType.getCodigo().equals("SAE"))					
					invoice = SessionServiceLocator.getCompraSessionService().getCompra(crossedWallet.getReferenciaId());
			}
			if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0, 1)) || document.getDebitoBancario().equals(SpiritConstants.getOptionYes().substring(0,1))) {
				if (document.getCheque().equals(SpiritConstants.getOptionYes().substring(0, 1))) {
					CuentaBancariaIf bankAccount = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(walletDetail.getChequeCuentaBancariaId());
					BancoIf bank = SessionServiceLocator.getBancoSessionService().getBanco(bankAccount.getBancoId());
					data.setBanco(bank.getNombre());
					data.setNumeroCuenta(bankAccount.getCuenta());
					data.setNumeroCheque(walletDetail.getChequeNumero());
				} else {
					CuentaBancariaIf bankAccount = SessionServiceLocator.getCuentaBancariaSessionService().getCuentaBancaria(walletDetail.getDebitoCuentaBancariaId());
					BancoIf bank = SessionServiceLocator.getBancoSessionService().getBanco(bankAccount.getBancoId());
					data.setBanco(bank.getNombre());
					data.setNumeroCuenta(bankAccount.getCuenta());
					data.setNumeroCheque("D/B");
				}			
			} else if (document.getEfectivo().equals(SpiritConstants.getOptionYes().substring(0, 1))) {
				data.setBanco("PAGO EN EFECTIVO");
				data.setNumeroCuenta("N/A");
				data.setNumeroCheque("N/A");
			}
			
			if (!document.getCodigo().equals("CFP") && invoice != null && crossedWallet != null) {
				data.setFechaCompra(Utilitarios.getFechaCortaUppercase((invoice!=null)?invoice.getFecha():crossedWallet.getFechaEmision()));
				data.setCodigoCompra((invoice!=null)?invoice.getCodigo():crossedWallet.getCodigo());
			} else {
				data.setFechaCompra("N/A");
				data.setCodigoCompra("N/A");
			}
			
			if (annulled) {
				data.setPreimpresoFactura("N/A");
				data.setDetalle(SpiritConstants.getEmptyCharacter());
				data.setValor(decimalFormat.format(0D));
				data.setSaldo(decimalFormat.format(0D));
			} else {
				data.setPreimpresoFactura((invoice!=null)?" F# " + invoice.getPreimpreso():"N/A");
				if (invoice != null) {
					data.setDetalle(invoice.getObservacion().length()>52?invoice.getObservacion().substring(0,52):invoice.getObservacion());
				} else if (crossedWallet != null) {
					data.setDetalle(crossedWallet.getComentario().length()>52?crossedWallet.getComentario().substring(0,52):crossedWallet.getComentario());
				} else {
					data.setDetalle("N/A");
				}
				if (crossingWallet != null)
					data.setValor(decimalFormat.format(crossingWallet.getValor().doubleValue()));
				else
					data.setValor("N/A");
				if (crossedWallet != null)
					data.setSaldo(decimalFormat.format(crossedWallet.getSaldo().doubleValue()));
				else
					data.setSaldo("N/A");
			}			
			
		} catch(GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error", SpiritAlert.ERROR);
		}
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long, TipoDocumentoIf> mappingDocumentTypes() {
		Map<Long, TipoDocumentoIf> documentTypeMap = new HashMap<Long, TipoDocumentoIf>();
		
		try {
			Iterator<TipoDocumentoIf> documentTypeIterator = SessionServiceLocator.getTipoDocumentoSessionService().findTipoDocumentoByEmpresaId(Parametros.getIdEmpresa()).iterator();
			while (documentTypeIterator.hasNext()) {
				TipoDocumentoIf documentType = documentTypeIterator.next();
				documentTypeMap.put(documentType.getId(), documentType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			SpiritAlert.createAlert("Se ha producido un error al generar el reporte", SpiritAlert.ERROR);
		}
		
		return documentTypeMap;
	}
}