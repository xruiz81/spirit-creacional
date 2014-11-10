package ImportarCartera;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class CarteraDetalleImportadaData {
	private java.lang.Long id;
	private java.lang.Long carteraId;
	private java.lang.Long documentoId;
	private java.lang.Integer secuencial;
	private java.sql.Date fechaCreacion;
	private java.sql.Date fechaCartera;
	private java.sql.Date fechaVencimiento;
	private java.sql.Date fechaUltimaActualizacion;
	private java.lang.Long lineaId;
	private java.lang.Long formaPagoId;
	private java.lang.Long cuentaBancariaId;
	private java.lang.String referencia;
	private java.lang.String preimpreso;
	private java.lang.Long depositoId;
	private java.math.BigDecimal valor;
	private java.math.BigDecimal saldo;
	private java.math.BigDecimal cotizacion;
	private java.lang.String cartera;
	private java.lang.String autorizacion;
	private java.lang.Long sriSustentoTributarioId;
	private java.lang.String diferido;
	private java.lang.String observacion;
	private java.lang.Long sriClienteRetencionId;
	private java.lang.Long chequeBancoId;
	private java.lang.Long chequeCuentaBancariaId;
	private java.lang.String chequeNumero;
	private java.lang.Long depositoBancoId;
	private java.lang.Long depositoCuentaBancariaId;
	private java.lang.String retencionNumero;
	private java.lang.String retencionAutorizacion;
	private java.math.BigDecimal valorRetencionRenta;
	private java.math.BigDecimal valorRetencionIva;
	private java.lang.Long debitoBancoId;
	private java.lang.Long debitoCuentaBancariaId;
	private java.lang.String debitoReferencia;
	private java.lang.Long transferenciaBancoOrigenId;
	private java.lang.Long transferenciaCuentaOrigenId;
	private java.lang.Long transferenciaBancoDestinoId;
	private java.lang.Long transferenciaCuentaDestinoId;
	private java.lang.Long tarjetaCreditoBancoId;
	private java.lang.Long tarjetaCreditoId;
	private java.lang.String voucherReference;
	private java.lang.String electronicPaymentReference;
	private java.lang.String factura;
	private java.lang.String notaventa;
	private java.lang.String clienteruc;
	private java.lang.String tipodoc;
	private java.lang.String empresa;
	private java.lang.String oficina;
	
	public CarteraDetalleImportadaData(){
		id = new Long(0);
		carteraId = new Long(0);
		documentoId = new Long(0);
		secuencial = 0;
		fechaCreacion = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		fechaCartera = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		fechaVencimiento = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		fechaUltimaActualizacion = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		lineaId = new Long(0);
		formaPagoId = new Long(0);
		cuentaBancariaId = new Long(0);
		referencia = "";
		preimpreso = "";
		depositoId = new Long(0);
		valor = new BigDecimal(0);
		saldo = new BigDecimal(0);
		cotizacion = new BigDecimal(0);
		cartera = "";
		autorizacion = "";
		sriSustentoTributarioId = new Long(0);
		diferido = "";
		observacion = "";
		sriClienteRetencionId = new Long(0);
		chequeBancoId = new Long(0);
		chequeCuentaBancariaId = new Long(0);
		chequeNumero = ""; 
		depositoBancoId = new Long(0);
		depositoCuentaBancariaId = new Long(0);
		retencionNumero = "";
		retencionAutorizacion = "";
		valorRetencionRenta = new BigDecimal(0);
		valorRetencionIva = new BigDecimal(0);
		debitoBancoId = new Long(0);
		debitoCuentaBancariaId = new Long(0);
		debitoReferencia = "";
		transferenciaBancoOrigenId = new Long(0);
		transferenciaCuentaOrigenId = new Long(0);
		transferenciaBancoDestinoId = new Long(0);
		transferenciaCuentaDestinoId = new Long(0);
		tarjetaCreditoBancoId = new Long(0);
		tarjetaCreditoId = new Long(0);
		voucherReference = "";
		electronicPaymentReference = "";
		factura = "";
		notaventa = "";
		clienteruc = "";
		tipodoc = "";
		empresa = "";
		oficina = "";
	}

	public java.lang.Long getId() {
		return id;
	}

	public void setId(java.lang.Long id) {
		this.id = id;
	}

	public java.lang.Long getCarteraId() {
		return carteraId;
	}

	public void setCarteraId(java.lang.Long carteraId) {
		this.carteraId = carteraId;
	}

	public java.lang.Long getDocumentoId() {
		return documentoId;
	}

	public void setDocumentoId(java.lang.Long documentoId) {
		this.documentoId = documentoId;
	}

	public java.lang.Integer getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(java.lang.Integer secuencial) {
		this.secuencial = secuencial;
	}

	public java.sql.Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(java.sql.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public java.sql.Date getFechaCartera() {
		return fechaCartera;
	}

	public void setFechaCartera(java.sql.Date fechaCartera) {
		this.fechaCartera = fechaCartera;
	}

	public java.sql.Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(java.sql.Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public java.sql.Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(java.sql.Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public java.lang.Long getLineaId() {
		return lineaId;
	}

	public void setLineaId(java.lang.Long lineaId) {
		this.lineaId = lineaId;
	}

	public java.lang.Long getFormaPagoId() {
		return formaPagoId;
	}

	public void setFormaPagoId(java.lang.Long formaPagoId) {
		this.formaPagoId = formaPagoId;
	}

	public java.lang.Long getCuentaBancariaId() {
		return cuentaBancariaId;
	}

	public void setCuentaBancariaId(java.lang.Long cuentaBancariaId) {
		this.cuentaBancariaId = cuentaBancariaId;
	}

	public java.lang.String getReferencia() {
		return referencia;
	}

	public void setReferencia(java.lang.String referencia) {
		this.referencia = referencia;
	}

	public java.lang.String getPreimpreso() {
		return preimpreso;
	}

	public void setPreimpreso(java.lang.String preimpreso) {
		this.preimpreso = preimpreso;
	}

	public java.lang.Long getDepositoId() {
		return depositoId;
	}

	public void setDepositoId(java.lang.Long depositoId) {
		this.depositoId = depositoId;
	}

	public java.math.BigDecimal getValor() {
		return valor;
	}

	public void setValor(java.math.BigDecimal valor) {
		this.valor = valor;
	}

	public java.math.BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(java.math.BigDecimal saldo) {
		this.saldo = saldo;
	}

	public java.math.BigDecimal getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(java.math.BigDecimal cotizacion) {
		this.cotizacion = cotizacion;
	}

	public java.lang.String getCartera() {
		return cartera;
	}

	public void setCartera(java.lang.String cartera) {
		this.cartera = cartera;
	}

	public java.lang.String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(java.lang.String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public java.lang.Long getSriSustentoTributarioId() {
		return sriSustentoTributarioId;
	}

	public void setSriSustentoTributarioId(java.lang.Long sriSustentoTributarioId) {
		this.sriSustentoTributarioId = sriSustentoTributarioId;
	}

	public java.lang.String getDiferido() {
		return diferido;
	}

	public void setDiferido(java.lang.String diferido) {
		this.diferido = diferido;
	}

	public java.lang.String getObservacion() {
		return observacion;
	}

	public void setObservacion(java.lang.String observacion) {
		this.observacion = observacion;
	}

	public java.lang.Long getSriClienteRetencionId() {
		return sriClienteRetencionId;
	}

	public void setSriClienteRetencionId(java.lang.Long sriClienteRetencionId) {
		this.sriClienteRetencionId = sriClienteRetencionId;
	}

	public java.lang.Long getChequeBancoId() {
		return chequeBancoId;
	}

	public void setChequeBancoId(java.lang.Long chequeBancoId) {
		this.chequeBancoId = chequeBancoId;
	}

	public java.lang.Long getChequeCuentaBancariaId() {
		return chequeCuentaBancariaId;
	}

	public void setChequeCuentaBancariaId(java.lang.Long chequeCuentaBancariaId) {
		this.chequeCuentaBancariaId = chequeCuentaBancariaId;
	}

	public java.lang.String getChequeNumero() {
		return chequeNumero;
	}

	public void setChequeNumero(java.lang.String chequeNumero) {
		this.chequeNumero = chequeNumero;
	}

	public java.lang.Long getDepositoBancoId() {
		return depositoBancoId;
	}

	public void setDepositoBancoId(java.lang.Long depositoBancoId) {
		this.depositoBancoId = depositoBancoId;
	}

	public java.lang.Long getDepositoCuentaBancariaId() {
		return depositoCuentaBancariaId;
	}

	public void setDepositoCuentaBancariaId(java.lang.Long depositoCuentaBancariaId) {
		this.depositoCuentaBancariaId = depositoCuentaBancariaId;
	}

	public java.lang.String getRetencionNumero() {
		return retencionNumero;
	}

	public void setRetencionNumero(java.lang.String retencionNumero) {
		this.retencionNumero = retencionNumero;
	}

	public java.lang.String getRetencionAutorizacion() {
		return retencionAutorizacion;
	}

	public void setRetencionAutorizacion(java.lang.String retencionAutorizacion) {
		this.retencionAutorizacion = retencionAutorizacion;
	}

	public java.math.BigDecimal getValorRetencionRenta() {
		return valorRetencionRenta;
	}

	public void setValorRetencionRenta(java.math.BigDecimal valorRetencionRenta) {
		this.valorRetencionRenta = valorRetencionRenta;
	}

	public java.math.BigDecimal getValorRetencionIva() {
		return valorRetencionIva;
	}

	public void setValorRetencionIva(java.math.BigDecimal valorRetencionIva) {
		this.valorRetencionIva = valorRetencionIva;
	}

	public java.lang.Long getDebitoBancoId() {
		return debitoBancoId;
	}

	public void setDebitoBancoId(java.lang.Long debitoBancoId) {
		this.debitoBancoId = debitoBancoId;
	}

	public java.lang.Long getDebitoCuentaBancariaId() {
		return debitoCuentaBancariaId;
	}

	public void setDebitoCuentaBancariaId(java.lang.Long debitoCuentaBancariaId) {
		this.debitoCuentaBancariaId = debitoCuentaBancariaId;
	}

	public java.lang.String getDebitoReferencia() {
		return debitoReferencia;
	}

	public void setDebitoReferencia(java.lang.String debitoReferencia) {
		this.debitoReferencia = debitoReferencia;
	}

	public java.lang.Long getTransferenciaBancoOrigenId() {
		return transferenciaBancoOrigenId;
	}

	public void setTransferenciaBancoOrigenId(
			java.lang.Long transferenciaBancoOrigenId) {
		this.transferenciaBancoOrigenId = transferenciaBancoOrigenId;
	}

	public java.lang.Long getTransferenciaCuentaOrigenId() {
		return transferenciaCuentaOrigenId;
	}

	public void setTransferenciaCuentaOrigenId(
			java.lang.Long transferenciaCuentaOrigenId) {
		this.transferenciaCuentaOrigenId = transferenciaCuentaOrigenId;
	}

	public java.lang.Long getTransferenciaBancoDestinoId() {
		return transferenciaBancoDestinoId;
	}

	public void setTransferenciaBancoDestinoId(
			java.lang.Long transferenciaBancoDestinoId) {
		this.transferenciaBancoDestinoId = transferenciaBancoDestinoId;
	}

	public java.lang.Long getTransferenciaCuentaDestinoId() {
		return transferenciaCuentaDestinoId;
	}

	public void setTransferenciaCuentaDestinoId(
			java.lang.Long transferenciaCuentaDestinoId) {
		this.transferenciaCuentaDestinoId = transferenciaCuentaDestinoId;
	}

	public java.lang.Long getTarjetaCreditoBancoId() {
		return tarjetaCreditoBancoId;
	}

	public void setTarjetaCreditoBancoId(java.lang.Long tarjetaCreditoBancoId) {
		this.tarjetaCreditoBancoId = tarjetaCreditoBancoId;
	}

	public java.lang.Long getTarjetaCreditoId() {
		return tarjetaCreditoId;
	}

	public void setTarjetaCreditoId(java.lang.Long tarjetaCreditoId) {
		this.tarjetaCreditoId = tarjetaCreditoId;
	}

	public java.lang.String getVoucherReference() {
		return voucherReference;
	}

	public void setVoucherReference(java.lang.String voucherReference) {
		this.voucherReference = voucherReference;
	}

	public java.lang.String getElectronicPaymentReference() {
		return electronicPaymentReference;
	}

	public void setElectronicPaymentReference(
			java.lang.String electronicPaymentReference) {
		this.electronicPaymentReference = electronicPaymentReference;
	}

	public java.lang.String getFactura() {
		return factura;
	}

	public void setFactura(java.lang.String factura) {
		this.factura = factura;
	}

	public java.lang.String getNotaventa() {
		return notaventa;
	}

	public void setNotaventa(java.lang.String notaventa) {
		this.notaventa = notaventa;
	}

	public java.lang.String getClienteruc() {
		return clienteruc;
	}

	public void setClienteruc(java.lang.String clienteruc) {
		this.clienteruc = clienteruc;
	}

	public java.lang.String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(java.lang.String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public java.lang.String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(java.lang.String empresa) {
		this.empresa = empresa;
	}

	public java.lang.String getOficina() {
		return oficina;
	}

	public void setOficina(java.lang.String oficina) {
		this.oficina = oficina;
	}
}
