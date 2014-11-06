package com.spirit.client;

import java.awt.Color;
import java.util.Map;

import javax.swing.JFrame;

import com.spirit.client.model.SpiritResourceManager;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.general.entity.MonedaIf;

public class Parametros {

	private static long idOficina;
	private static long idEmpresa;
	private static String nombreEmpresa;
	private static String usuario;
	private static String codMoneda;
	private static String nombreOficina;
	private static String logoEmpresa;
	private static final int numeroElementosPorPagina = 10;
	private static JFrame mainFrame;
	public static Map _SubMenusUsuarioByRoles;
	public static Map funcionesSubMenuByRoles;
	public static boolean jbpmPresente;
	public static boolean standAlone = true; 
	public static final boolean ACTIVAR_REPLICACION = false;
	public static Color findColor = new Color(135, 206, 250);
	public static Color saveUpdateColor = new Color(255, 255, 255);
	public static String urlCarpetaServidor = "";
	public static String rutaWindowsCarpetaServidor = "";
	public static String rutaCarpetaReportes = "";
	public static String rutaCarpetaSriDimm = "";
	public static double IVA = 0.0;
	public static double ICE = 0.0;
	public static double porcentajeComision = 0.0;	
	public static boolean vistaPreviaImpresion = true;
	private static Object empresa;
	private static Object oficina;
	private static Object ciudad;
	private static String defaultLocale;
	private static PlanCuentaIf planCuentaPredeterminado;
	private static MonedaIf monedaPredeterminada;
	private static String excluirProductoTipoGastosVarios;
	private static Integer maximaLongitudPreimpresoEstablecimiento;
	private static Integer maximaLongitudPreimpresoPuntoEmision;
	private static Integer maximaLongitudPreimpresoSecuencial;
	private static Integer maximaLongitudPreimpresoAutorizacion;
	private static String patronPreimpreso;
	private static Integer maxLongitudClienteDireccionNotaCredito;

	
	public static String VERSION = SpiritResourceManager
			.getPropertyFromFileResource("conf/version.properties", "version");

	private static Object usuarioIf;

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		Parametros.usuario = usuario;
	}

	public static long getIdEmpresa() {
		return idEmpresa;
	}

	public static void setIdEmpresa(long idEmpresa) {
		Parametros.idEmpresa = idEmpresa;
	}

	public static Object getUsuarioIf() {
		return usuarioIf;
	}

	public static void setUsuarioIf(Object usuarioIf) {
		Parametros.usuarioIf = usuarioIf;
	}

	public static long getIdOficina() {
		return idOficina;
	}

	public static void setIdOficina(long idOficina) {
		Parametros.idOficina = idOficina;
	}

	public static String getNombreOficina() {
		return nombreOficina;
	}

	public static void setNombreOficina(String nombreOficina) {
		Parametros.nombreOficina = nombreOficina;
	}

	public static String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public static void setNombreEmpresa(String nombreEmpresa) {
		Parametros.nombreEmpresa = nombreEmpresa;
	}

	public static String getCodMoneda() {
		return codMoneda;
	}

	public static void setCodMoneda(String codMoneda) {
		Parametros.codMoneda = codMoneda;
	}

	public static int getNumeroElementosPorPagina() {
		return numeroElementosPorPagina;
	}

	public static JFrame getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(JFrame mainFrame) {
		Parametros.mainFrame = mainFrame;
	}

	public static String getLogoEmpresa() {
		return logoEmpresa;
	}

	public static void setLogoEmpresa(String logoEmpresa) {
		Parametros.logoEmpresa = logoEmpresa;
	}

	public static Map get_SubMenusUsuarioByRoles() {
		return _SubMenusUsuarioByRoles;
	}

	public static void set_SubMenusUsuarioByRoles(Map subMenusUsuarioByRoles) {
		_SubMenusUsuarioByRoles = subMenusUsuarioByRoles;
	}

	public static Map getFuncionesSubMenuByRoles() {
		return funcionesSubMenuByRoles;
	}

	public static void setFuncionesSubMenuByRoles(Map funcionesSubMenuByRoles) {
		Parametros.funcionesSubMenuByRoles = funcionesSubMenuByRoles;
	}

	public static boolean isJbpmPresente() {
		return jbpmPresente;
	}

	public static void setJbpmPresente(boolean jbpm) {
		Parametros.jbpmPresente = jbpm;
	}

	public static String getUrlCarpetaServidor() {
		return urlCarpetaServidor;
	}

	public static void setUrlCarpetaServidor(String urlCarpetaServidor) {
		Parametros.urlCarpetaServidor = urlCarpetaServidor;
	}

	public static double getICE() {
		return ICE;
	}

	public static void setICE(double ice) {
		ICE = ice;
	}

	public static double getIVA() {
		return IVA;
	}

	public static void setIVA(double iva) {
		IVA = iva;
	}

	public static String getRutaWindowsCarpetaServidor() {
		return rutaWindowsCarpetaServidor;
	}

	public static void setRutaWindowsCarpetaServidor(String rutaCarpetaServidor) {
		Parametros.rutaWindowsCarpetaServidor = rutaCarpetaServidor;
	}

	public static String getRutaCarpetaReportes() {
		return rutaCarpetaReportes;
	}

	public static void setRutaCarpetaReportes(String rutaCarpetaReportes) {
		Parametros.rutaCarpetaReportes = rutaCarpetaReportes;
	}

	public static String getRutaCarpetaSriDimm() {
		return rutaCarpetaSriDimm;
	}

	public static void setRutaCarpetaSriDimm(String rutaCarpetaSriDimm) {
		Parametros.rutaCarpetaSriDimm = rutaCarpetaSriDimm;
	}

	public static double getPorcentajeComision() {
		return porcentajeComision;
	}

	public static void setPorcentajeComision(double porcentajeComision) {
		Parametros.porcentajeComision = porcentajeComision;
	}
	
	public static boolean isVistaPreviaImpresion() {
		return vistaPreviaImpresion;
	}

	public static void setVistaPreviaImpresion(boolean vistaPreviaImpresion) {
		Parametros.vistaPreviaImpresion = vistaPreviaImpresion;
	}

	public static Object getEmpresa() {
		return empresa;
	}

	public static void setEmpresa(Object empresa) {
		Parametros.empresa = empresa;
	}

	public static Object getOficina() {
		return oficina;
	}

	public static void setOficina(Object oficina) {
		Parametros.oficina = oficina;
	}

	public static Object getCiudad() {
		return ciudad;
	}

	public static void setCiudad(Object ciudad) {
		Parametros.ciudad = ciudad;
	}
	
	public static String getDefaultLocale() {
		return defaultLocale;
	}
	
	public static void setDefaultLocale(String defaultLocale) {
		Parametros.defaultLocale = defaultLocale;
	}

	public static PlanCuentaIf getPlanCuentaPredeterminado() {
		return planCuentaPredeterminado;
	}

	public static void setPlanCuentaPredeterminado(
			PlanCuentaIf planCuentaPredeterminado) {
		Parametros.planCuentaPredeterminado = planCuentaPredeterminado;
	}

	public static MonedaIf getMonedaPredeterminada() {
		return monedaPredeterminada;
	}

	public static void setMonedaPredeterminada(MonedaIf monedaPredeterminada) {
		Parametros.monedaPredeterminada = monedaPredeterminada;
	}

	public static String getExcluirProductoTipoGastosVarios() {
		return excluirProductoTipoGastosVarios;
	}

	public static void setExcluirProductoTipoGastosVarios(
			String excluirProductoTipoGastosVarios) {
		Parametros.excluirProductoTipoGastosVarios = excluirProductoTipoGastosVarios;
	}

	public static Integer getMaximaLongitudPreimpresoEstablecimiento() {
		return maximaLongitudPreimpresoEstablecimiento;
	}

	public static void setMaximaLongitudPreimpresoEstablecimiento(
			Integer maximaLongitudPreimpresoEstablecimiento) {
		Parametros.maximaLongitudPreimpresoEstablecimiento = maximaLongitudPreimpresoEstablecimiento;
	}

	public static Integer getMaximaLongitudPreimpresoPuntoEmision() {
		return maximaLongitudPreimpresoPuntoEmision;
	}

	public static void setMaximaLongitudPreimpresoPuntoEmision(
			Integer maximaLongitudPreimpresoPuntoEmision) {
		Parametros.maximaLongitudPreimpresoPuntoEmision = maximaLongitudPreimpresoPuntoEmision;
	}

	public static Integer getMaximaLongitudPreimpresoSecuencial() {
		return maximaLongitudPreimpresoSecuencial;
	}

	public static void setMaximaLongitudPreimpresoSecuencial(
			Integer maximaLongitudPreimpresoSecuencial) {
		Parametros.maximaLongitudPreimpresoSecuencial = maximaLongitudPreimpresoSecuencial;
	}

	public static Integer getMaximaLongitudPreimpresoAutorizacion() {
		return maximaLongitudPreimpresoAutorizacion;
	}

	public static void setMaximaLongitudPreimpresoAutorizacion(
			Integer maximaLongitudPreimpresoAutorizacion) {
		Parametros.maximaLongitudPreimpresoAutorizacion = maximaLongitudPreimpresoAutorizacion;
	}

	public static String getPatronPreimpreso() {
		return patronPreimpreso;
	}

	public static void setPatronPreimpreso(String patronPreimpreso) {
		Parametros.patronPreimpreso = patronPreimpreso;
	}

	public static Integer getMaxLongitudClienteDireccionNotaCredito() {
		return maxLongitudClienteDireccionNotaCredito;
	}

	public static void setMaxLongitudClienteDireccionNotaCredito(
			Integer maxLongitudClienteDireccionNotaCredito) {
		Parametros.maxLongitudClienteDireccionNotaCredito = maxLongitudClienteDireccionNotaCredito;
	}

	public static boolean isActivarReplicacion() {
		return ACTIVAR_REPLICACION;
	}
}
