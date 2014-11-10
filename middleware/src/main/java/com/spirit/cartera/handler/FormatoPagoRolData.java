package com.spirit.cartera.handler;

import java.io.Serializable;

public class FormatoPagoRolData implements Serializable {
	private static final long serialVersionUID = 6632016095670928759L;
	private String tipo;
	private String identificacion;
	private String nombre;
	private String detalle;
	private String tipoCuenta;
	private String codigo;
	private String codigoCuenta;
	private String numeroCuenta;
	private String valor;
		
	public FormatoPagoRolData() {
		tipo = "";
		identificacion = "";
		nombre = "";
		detalle = "";
		tipoCuenta = "";
		codigo = "";
		codigoCuenta = "";
		numeroCuenta = "";
		valor = "";
	}

	public String getCampo(int i){
		String campo = "";
		
		switch (i) {
		case 1:
			campo = getTipo();
			break;
		case 2:
			campo = getIdentificacion();
			break;
		case 3:
			campo = getNombre();
			break;
		case 4:
			campo = getDetalle();
			break;
		case 5:
			campo = getTipoCuenta();
			break;
		case 6:
			campo = getCodigo();
			break;
		case 7:
			campo = getCodigoCuenta();
			break;
		case 8:
			campo = getNumeroCuenta();
			break;
		case 9:
			campo = getValor();
			break;
		
		default:
			campo = "Indice Invalido";
			break;
		}		
		
		return campo;
	}
	
	public String getNombreAtributo(int i){
		String nombre = "";
		
		switch (i) {
		case 1:
			nombre = "TIPO";
			break;
		case 2:
			nombre = "CEDULA NUMERO";
			break;
		case 3:
			nombre = "NOMBRE";
			break;
		case 4:
			nombre = "DETALLE";
			break;
		case 5:
			nombre = "TIPO DE CTA.";
			break;
		case 6:
			nombre = "CODIGO";
			break;
		case 7:
			nombre = "CODIGO DE CTA.";
			break;
		case 8:
			nombre = "# DE CTA.";
			break;			
		case 9:
			nombre = "VALOR";
			break;
		
		default:
			nombre = "Indice Invalido";
			break;
		}		
		
		return nombre;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoCuenta() {
		return codigoCuenta;
	}

	public void setCodigoCuenta(String codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}