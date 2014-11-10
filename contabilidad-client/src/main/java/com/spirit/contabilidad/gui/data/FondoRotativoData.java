package com.spirit.contabilidad.gui.data;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;

public class FondoRotativoData {

	Long idCuentaBancaria = 0L;
	
	String nombreBanco = null;
	String codigoCuentaBancaria = null;
	
	Double saldoInicialBancario = 0D;
	Double saldoInicial = 0D;
	Double totalIngresos = 0D;
	Double totalEgresos = 0D;
	Double totalChequesEmitidos = 0D;
	
	Double totalFacturasCanceladas = 0D;
	Double totalFacturasPendientes = 0D;
	Double totalPresupuestosPendientes = 0D;
	Double totalPendientesPorCobrar = 0D;
	Double totalDebitosBancarios = 0D;
	
	JRDataSource ingresosDetalles = null;
	ArrayList<FondoRotativoIngresoData> ingresosConciliacionFondoRotativo = null;
	JRDataSource egresosDetalles = null;
	ArrayList<FondoRotativoEgresoData> egresosConciliacionFondoRotativo = null;
	JRDataSource chequesEmitidosDetalles = null;
	ArrayList<FondoRotativoEgresoData> chequesEmitidosConciliacionFondoRotativo = null;
	
	JRDataSource facturasCanceladasDetalles = null;
	ArrayList<FondoRotativoFacturaCanceladaData> facturasCanceladasConciliacionFondoRotativo = null;
	JRDataSource facturasPendientesDetalles = null;
	ArrayList<FondoRotativoFacturaPendienteData> facturasPendientesConciliacionFondoRotativo = null;
	JRDataSource presupuestosPendientesDetalles = null;
	ArrayList<FondoRotativoEgresoData> presupuestosPendientesConciliacionFondoRotativo = null;
	
	JRDataSource pendientesPorCobrarDetalles = null;
	ArrayList<FondoRotativoEgresoData> pendientesPorCobrarConciliacionFondoRotativo = null;
	JRDataSource debitosBancariosDetalles = null;
	ArrayList<FondoRotativoEgresoData> debitosBancariosConciliacionFondoRotativo = null;
	
	
	public FondoRotativoData() {
		
	}

	public Double getTotalDebitosBancarios() {
		return totalDebitosBancarios;
	}

	public void setTotalDebitosBancarios(Double totalDebitosBancarios) {
		this.totalDebitosBancarios = totalDebitosBancarios;
	}

	public JRDataSource getDebitosBancariosDetalles() {
		return debitosBancariosDetalles;
	}

	public void setDebitosBancariosDetalles(JRDataSource debitosBancariosDetalles) {
		this.debitosBancariosDetalles = debitosBancariosDetalles;
	}

	public ArrayList<FondoRotativoEgresoData> getDebitosBancariosConciliacionFondoRotativo() {
		return debitosBancariosConciliacionFondoRotativo;
	}

	public void setDebitosBancariosConciliacionFondoRotativo(
			ArrayList<FondoRotativoEgresoData> debitosBancariosConciliacionFondoRotativo) {
		this.debitosBancariosConciliacionFondoRotativo = debitosBancariosConciliacionFondoRotativo;
	}

	public ArrayList<FondoRotativoEgresoData> getPendientesPorCobrarConciliacionFondoRotativo() {
		return pendientesPorCobrarConciliacionFondoRotativo;
	}

	public void setPendientesPorCobrarConciliacionFondoRotativo(
			ArrayList<FondoRotativoEgresoData> pendientesPorCobrarConciliacionFondoRotativo) {
		this.pendientesPorCobrarConciliacionFondoRotativo = pendientesPorCobrarConciliacionFondoRotativo;
	}

	public Double getTotalPendientesPorCobrar() {
		return totalPendientesPorCobrar;
	}

	public void setTotalPendientesPorCobrar(Double totalPendientesPorCobrar) {
		this.totalPendientesPorCobrar = totalPendientesPorCobrar;
	}

	public JRDataSource getPendientesPorCobrarDetalles() {
		return pendientesPorCobrarDetalles;
	}

	public void setPendientesPorCobrarDetalles(
			JRDataSource pendientesPorCobrarDetalles) {
		this.pendientesPorCobrarDetalles = pendientesPorCobrarDetalles;
	}

	public Double getTotalPresupuestosPendientes() {
		return totalPresupuestosPendientes;
	}

	public void setTotalPresupuestosPendientes(Double totalPresupuestosPendientes) {
		this.totalPresupuestosPendientes = totalPresupuestosPendientes;
	}

	public JRDataSource getPresupuestosPendientesDetalles() {
		return presupuestosPendientesDetalles;
	}

	public void setPresupuestosPendientesDetalles(
			JRDataSource presupuestosPendientesDetalles) {
		this.presupuestosPendientesDetalles = presupuestosPendientesDetalles;
	}

	public ArrayList<FondoRotativoEgresoData> getPresupuestosPendientesConciliacionFondoRotativo() {
		return presupuestosPendientesConciliacionFondoRotativo;
	}

	public void setPresupuestosPendientesConciliacionFondoRotativo(
			ArrayList<FondoRotativoEgresoData> presupuestosPendientesConciliacionFondoRotativo) {
		this.presupuestosPendientesConciliacionFondoRotativo = presupuestosPendientesConciliacionFondoRotativo;
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

	public Double getTotalChequesEmitidos() {
		return totalChequesEmitidos;
	}

	public void setTotalChequesEmitidos(Double totalChequesEmitidos) {
		this.totalChequesEmitidos = totalChequesEmitidos;
	}

	public JRDataSource getChequesEmitidosDetalles() {
		return chequesEmitidosDetalles;
	}

	public void setChequesEmitidosDetalles(JRDataSource chequesEmitidosDetalles) {
		this.chequesEmitidosDetalles = chequesEmitidosDetalles;
	}

	public Long getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	public void setIdCuentaBancaria(Long idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	public ArrayList<FondoRotativoIngresoData> getIngresosConciliacionFondoRotativo() {
		return ingresosConciliacionFondoRotativo;
	}

	public void setIngresosConciliacionFondoRotativo(ArrayList<FondoRotativoIngresoData> ingresosConciliacionFondoRotativo) {
		this.ingresosConciliacionFondoRotativo = ingresosConciliacionFondoRotativo;
	}

	public ArrayList<FondoRotativoEgresoData> getEgresosConciliacionFondoRotativo() {
		return egresosConciliacionFondoRotativo;
	}

	public void setEgresosConciliacionFondoRotativo(ArrayList<FondoRotativoEgresoData> egresosConciliacionFondoRotativo) {
		this.egresosConciliacionFondoRotativo = egresosConciliacionFondoRotativo;
	}

	public ArrayList<FondoRotativoEgresoData> getChequesEmitidosConciliacionFondoRotativo() {
		return chequesEmitidosConciliacionFondoRotativo;
	}

	public void setChequesEmitidosConciliacionFondoRotativo(ArrayList<FondoRotativoEgresoData> chequesEmitidosConciliacionFondoRotativo) {
		this.chequesEmitidosConciliacionFondoRotativo = chequesEmitidosConciliacionFondoRotativo;
	}

	public Double getTotalFacturasCanceladas() {
		return totalFacturasCanceladas;
	}

	public void setTotalFacturasCanceladas(Double totalFacturasCanceladas) {
		this.totalFacturasCanceladas = totalFacturasCanceladas;
	}

	public Double getTotalFacturasPendientes() {
		return totalFacturasPendientes;
	}

	public void setTotalFacturasPendientes(Double totalFacturasPendientes) {
		this.totalFacturasPendientes = totalFacturasPendientes;
	}

	public JRDataSource getFacturasCanceladasDetalles() {
		return facturasCanceladasDetalles;
	}

	public void setFacturasCanceladasDetalles(
			JRDataSource facturasCanceladasDetalles) {
		this.facturasCanceladasDetalles = facturasCanceladasDetalles;
	}

	public ArrayList<FondoRotativoFacturaCanceladaData> getFacturasCanceladasConciliacionFondoRotativo() {
		return facturasCanceladasConciliacionFondoRotativo;
	}

	public void setFacturasCanceladasConciliacionFondoRotativo(
			ArrayList<FondoRotativoFacturaCanceladaData> facturasCanceladasConciliacionFondoRotativo) {
		this.facturasCanceladasConciliacionFondoRotativo = facturasCanceladasConciliacionFondoRotativo;
	}

	public JRDataSource getFacturasPendientesDetalles() {
		return facturasPendientesDetalles;
	}

	public void setFacturasPendientesDetalles(
			JRDataSource facturasPendientesDetalles) {
		this.facturasPendientesDetalles = facturasPendientesDetalles;
	}

	public ArrayList<FondoRotativoFacturaPendienteData> getFacturasPendientesConciliacionFondoRotativo() {
		return facturasPendientesConciliacionFondoRotativo;
	}

	public void setFacturasPendientesConciliacionFondoRotativo(
			ArrayList<FondoRotativoFacturaPendienteData> facturasPendientesConciliacionFondoRotativo) {
		this.facturasPendientesConciliacionFondoRotativo = facturasPendientesConciliacionFondoRotativo;
	}

	public Double getSaldoInicialBancario() {
		return saldoInicialBancario;
	}

	public void setSaldoInicialBancario(Double saldoInicialBancario) {
		this.saldoInicialBancario = saldoInicialBancario;
	}
}
