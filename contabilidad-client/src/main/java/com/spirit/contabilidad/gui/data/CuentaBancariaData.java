package com.spirit.contabilidad.gui.data;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;

public class CuentaBancariaData {

	Long idCuentaBancaria = 0L;
	
	String nombreBanco = null;
	String codigoCuentaBancaria = null;
	
	Double saldoInicial = 0D;
	Double totalIngresos = 0D;
	Double totalEgresos = 0D;
	Double totalChequesAnulados = 0D;
	Double totalChequesEmitidos = 0D;
	Double totalNotasDebitos = 0D;
	
	JRDataSource ingresosDetalles = null;
	ArrayList<CuentaBancariaIngresoData> ingresosConciliacionBancaria = null;
	JRDataSource egresosDetalles = null;
	ArrayList<CuentaBancariaEgresoData> egresosConciliacionBancaria = null;
	JRDataSource chequesAnuladosDetalles = null;
	ArrayList<CuentaBancariaEgresoData> egresosAnuladosConciliacionBancaria = null;
	JRDataSource chequesEmitidosDetalles = null;
	ArrayList<CuentaBancariaIngresoData> chequesEmitidosConciliacionBancaria = null;
	JRDataSource notasDebitosDetalles = null;
	ArrayList<CuentaBancariaEgresoData> notasDebitoConciliacionBancaria = null;
	
	public CuentaBancariaData() {
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getCodigoCuentaBancaria() {
		return codigoCuentaBancaria;
	}

	public void setCodigoCuentaBancaria(String codigoCuentaBancaria) {
		this.codigoCuentaBancaria = codigoCuentaBancaria;
	}

	public JRDataSource getIngresosDetalles() {
		return ingresosDetalles;
	}

	public void setIngresosDetalles(JRDataSource ingresosDetalles) {
		this.ingresosDetalles = ingresosDetalles;
	}

	public JRDataSource getEgresosDetalles() {
		return egresosDetalles;
	}

	public void setEgresosDetalles(JRDataSource egresosDetalles) {
		this.egresosDetalles = egresosDetalles;
	}

	public Double getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(Double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public Double getTotalEgresos() {
		return totalEgresos;
	}

	public void setTotalEgresos(Double totalEgresos) {
		this.totalEgresos = totalEgresos;
	}

	public Double getTotalChequesAnulados() {
		return totalChequesAnulados;
	}

	public void setTotalChequesAnulados(Double totalChequesAnulados) {
		this.totalChequesAnulados = totalChequesAnulados;
	}

	public Double getTotalChequesEmitidos() {
		return totalChequesEmitidos;
	}

	public void setTotalChequesEmitidos(Double totalChequesEmitidos) {
		this.totalChequesEmitidos = totalChequesEmitidos;
	}

	public JRDataSource getChequesAnuladosDetalles() {
		return chequesAnuladosDetalles;
	}

	public void setChequesAnuladosDetalles(JRDataSource chequesAnuladosDetalles) {
		this.chequesAnuladosDetalles = chequesAnuladosDetalles;
	}

	public JRDataSource getChequesEmitidosDetalles() {
		return chequesEmitidosDetalles;
	}

	public void setChequesEmitidosDetalles(JRDataSource chequesEmitidosDetalles) {
		this.chequesEmitidosDetalles = chequesEmitidosDetalles;
	}

	public JRDataSource getNotasDebitosDetalles() {
		return notasDebitosDetalles;
	}

	public void setNotasDebitosDetalles(JRDataSource notasDebitosDetalles) {
		this.notasDebitosDetalles = notasDebitosDetalles;
	}

	public Long getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(Long idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public Double getTotalNotasDebitos() {
		return totalNotasDebitos;
	}

	public void setTotalNotasDebitos(Double totalNotasDebitos) {
		this.totalNotasDebitos = totalNotasDebitos;
	}

	public ArrayList<CuentaBancariaIngresoData> getIngresosConciliacionBancaria() {
		return ingresosConciliacionBancaria;
	}

	public void setIngresosConciliacionBancaria(
			ArrayList<CuentaBancariaIngresoData> ingresosConciliacionBancaria) {
		this.ingresosConciliacionBancaria = ingresosConciliacionBancaria;
	}

	public ArrayList<CuentaBancariaEgresoData> getEgresosConciliacionBancaria() {
		return egresosConciliacionBancaria;
	}

	public void setEgresosConciliacionBancaria(
			ArrayList<CuentaBancariaEgresoData> egresosConciliacionBancaria) {
		this.egresosConciliacionBancaria = egresosConciliacionBancaria;
	}

	public ArrayList<CuentaBancariaEgresoData> getEgresosAnuladosConciliacionBancaria() {
		return egresosAnuladosConciliacionBancaria;
	}

	public void setEgresosAnuladosConciliacionBancaria(
			ArrayList<CuentaBancariaEgresoData> egresosAnuladosConciliacionBancaria) {
		this.egresosAnuladosConciliacionBancaria = egresosAnuladosConciliacionBancaria;
	}

	public ArrayList<CuentaBancariaIngresoData> getChequesEmitidosConciliacionBancaria() {
		return chequesEmitidosConciliacionBancaria;
	}

	public void setChequesEmitidosConciliacionBancaria(
			ArrayList<CuentaBancariaIngresoData> chequesEmitidosConciliacionBancaria) {
		this.chequesEmitidosConciliacionBancaria = chequesEmitidosConciliacionBancaria;
	}

	public ArrayList<CuentaBancariaEgresoData> getNotasDebitoConciliacionBancaria() {
		return notasDebitoConciliacionBancaria;
	}

	public void setNotasDebitoConciliacionBancaria(
			ArrayList<CuentaBancariaEgresoData> notasDebitoConciliacionBancaria) {
		this.notasDebitoConciliacionBancaria = notasDebitoConciliacionBancaria;
	}
}
