package com.spirit.cartera.handler;

import java.io.Serializable;

public class FormatoPagoMulticashData implements Serializable {
	private static final long serialVersionUID = 6632016095670928759L;
	private String codigoOrientacion;
	private String cuentaEmpresa;
	private String secuencialPago;
	private String comprobantePago;
	private String codigo;
	private String moneda;
	private String valor;
	private String formaPago;
	private String codigoInstitucionFinanciera;
	private String tipoCuenta;
	private String numeroCuenta;
	private String tipoIdClienteBeneficiario;
	private String numeroIdClienteBeneficiario;
	private String nombreClienteBeneficiario;
	private String direccionBeneficiario;
	private String ciudadBeneficiario;
	private String telefonoBeneficiario;
	private String localidadPago;
	private String referencia;
	private String referenciaAdicional;
	
	public FormatoPagoMulticashData() {
		codigoOrientacion = "1";
		cuentaEmpresa = "2";
		secuencialPago = "3";
		comprobantePago = "4";
		codigo = "5";
		moneda = "6";
		valor = "7";
		formaPago = "8";
		codigoInstitucionFinanciera = "9";
		tipoCuenta = "10";
		numeroCuenta = "11";
		tipoIdClienteBeneficiario = "12";
		numeroIdClienteBeneficiario = "13";
		nombreClienteBeneficiario = "14";
		direccionBeneficiario = "15";
		ciudadBeneficiario = "16";
		telefonoBeneficiario = "17";
		localidadPago = "18";
		referencia = "19";
		referenciaAdicional = "20";
	}

	public String getCampo(int i){
		String campo = "";
		
		switch (i) {
		case 1:
			campo = getCodigoOrientacion();
			break;
		case 2:
			campo = getCuentaEmpresa();
			break;
		case 3:
			campo = getSecuencialPago();
			break;
		case 4:
			campo = getComprobantePago();
			break;
		case 5:
			campo = getCodigo();
			break;
		case 6:
			campo = getMoneda();
			break;
		case 7:
			campo = getValor();
			break;
		case 8:
			campo = getFormaPago();
			break;
		case 9:
			campo = getCodigoInstitucionFinanciera();
			break;
		case 10:
			campo = getTipoCuenta();
			break;
		case 11:
			campo = getNumeroCuenta();
			break;
		case 12:
			campo = getTipoIdClienteBeneficiario();
			break;
		case 13:
			campo = getNumeroIdClienteBeneficiario();
			break;
		case 14:
			campo = getNombreClienteBeneficiario();
			break;
		case 15:
			campo = getDireccionBeneficiario();
			break;
		case 16:
			campo = getCiudadBeneficiario();
			break;
		case 17:
			campo = getTelefonoBeneficiario();
			break;
		case 18:
			campo = getLocalidadPago();
			break;
		case 19:
			campo = getReferencia();
			break;
		case 20:
			campo = getReferenciaAdicional();
			break;
		default:
			campo = "Indice Invalido";
			break;
		}		
		
		return campo;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCodigoOrientacion() {
		return codigoOrientacion;
	}

	public String getCuentaEmpresa() {
		return cuentaEmpresa;
	}

	public String getSecuencialPago() {
		return secuencialPago;
	}

	public String getComprobantePago() {
		return comprobantePago;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getMoneda() {
		return moneda;
	}

	public String getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public String getCodigoInstitucionFinanciera() {
		return codigoInstitucionFinanciera;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getTipoIdClienteBeneficiario() {
		return tipoIdClienteBeneficiario;
	}

	public String getNumeroIdClienteBeneficiario() {
		return numeroIdClienteBeneficiario;
	}

	public String getNombreClienteBeneficiario() {
		return nombreClienteBeneficiario;
	}

	public String getDireccionBeneficiario() {
		return direccionBeneficiario;
	}

	public String getCiudadBeneficiario() {
		return ciudadBeneficiario;
	}

	public String getTelefonoBeneficiario() {
		return telefonoBeneficiario;
	}

	public String getLocalidadPago() {
		return localidadPago;
	}

	public String getReferencia() {
		return referencia;
	}

	public String getReferenciaAdicional() {
		return referenciaAdicional;
	}

	public void setCodigoOrientacion(String codigoOrientacion) {
		this.codigoOrientacion = codigoOrientacion;
	}

	public void setCuentaEmpresa(String cuentaEmpresa) {
		this.cuentaEmpresa = cuentaEmpresa;
	}

	public void setSecuencialPago(String secuencialPago) {
		this.secuencialPago = secuencialPago;
	}

	public void setComprobantePago(String comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public void setCodigoInstitucionFinanciera(String codigoInstitucionFinanciera) {
		this.codigoInstitucionFinanciera = codigoInstitucionFinanciera;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public void setTipoIdClienteBeneficiario(String tipoIdClienteBeneficiario) {
		this.tipoIdClienteBeneficiario = tipoIdClienteBeneficiario;
	}

	public void setNumeroIdClienteBeneficiario(String numeroIdClienteBeneficiario) {
		this.numeroIdClienteBeneficiario = numeroIdClienteBeneficiario;
	}

	public void setNombreClienteBeneficiario(String nombreClienteBeneficiario) {
		this.nombreClienteBeneficiario = nombreClienteBeneficiario;
	}

	public void setDireccionBeneficiario(String direccionBeneficiario) {
		this.direccionBeneficiario = direccionBeneficiario;
	}

	public void setCiudadBeneficiario(String ciudadBeneficiario) {
		this.ciudadBeneficiario = ciudadBeneficiario;
	}

	public void setTelefonoBeneficiario(String telefonoBeneficiario) {
		this.telefonoBeneficiario = telefonoBeneficiario;
	}

	public void setLocalidadPago(String localidadPago) {
		this.localidadPago = localidadPago;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public void setReferenciaAdicional(String referenciaAdicional) {
		this.referenciaAdicional = referenciaAdicional;
	}
	
}