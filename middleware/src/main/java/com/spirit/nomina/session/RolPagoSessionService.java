package com.spirit.nomina.session;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.session.generated._RolPagoSessionService;
import com.spirit.util.FileInputStreamSerializable;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolPagoSessionService extends _RolPagoSessionService{
	public Map<String,Object> procesarRolPago(RolPagoIf rolPago,TipoContratoIf tipoContratoIf,ContratoIf contratoIf,Long empresaId) throws GenericBusinessException;
	public void eliminarRolPago(RolPagoIf rolPagoIf) throws GenericBusinessException;
	public void eliminarRolPagoPorContrato(RolPagoIf rolPagoIf,ContratoIf contratoIf) throws GenericBusinessException;
	//public void cerrarRolPago(RolPagoIf rolPagoIf) throws GenericBusinessException;
	//public Collection<Map<String, Object>> crearColeccionContratos(Collection<Long> contratosId,RolPagoIf rolPagoIf,String estado, boolean devolverRolPagoDetalleId,EtapaRolPago etapaRolPago) throws GenericBusinessException;
	public Collection<Map<String, Object>> crearColeccionContratos(Collection<Long> contratosId,RolPagoIf rolPagoIf,String estado, boolean devolverRolPagoDetalleId, boolean verValoresRolenR13, boolean verValoresPagoR13, String decimoCuarto, boolean metodoLlamadoDesdeRolPagoModel) throws GenericBusinessException;
	public void autorizarRolPago(Date fecha,RolPagoIf rolPagoIf,Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) throws GenericBusinessException;
	
	public Collection<AsientoIf> procesarPagoRol(Date fechaPago,RolPagoIf rolPagoIf,Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) throws GenericBusinessException;
	
	public void generarAsientoRolPago(Date fecha,RolPagoIf rolPagoIf,Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) throws GenericBusinessException;
	public void generarAsientoRubrosEventales(Date fechaPago,RolPagoIf rolPagoIf,Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) throws GenericBusinessException;
	
	public void autorizarAnticipoRolPago(Date fechaPago,RolPagoIf rolPagoIf,Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) throws GenericBusinessException;
	public Collection<AsientoIf> procesarPagoAnticipoRol(Date fechaPago,RolPagoIf rolPagoIf,Collection<Map<String,Object>> rolPagoDetalleSeleccionadosList) throws GenericBusinessException;
	
	public Collection<RolPagoIf> getRolPagoList(String rolProvisionado,Long tipoContratoId,String estadoDetalle,Calendar calInicio, Calendar calFin) throws GenericBusinessException;
	public Collection<RolPagoIf> getRolPagoListGeneracionAsientos(Collection<String> estadosDetalle,Collection<String> tiposRol,Calendar calInicio,Calendar calFin,String... estadosProvisionados) throws GenericBusinessException;
	public Collection<RolPagoIf> getRolPagoEventualList(String estadoDetalle) throws GenericBusinessException;
	//public Collection<RolPagoIf> getRolPagoAnticiposList(String estadoDetalle) throws GenericBusinessException;
	
	
	public Double sumaSueldos(Collection<Long> contratosId
			,RolPagoIf rolPagoIf,TipoRolIf tipoRolIf,TipoRol tipoRol,String estado, boolean devolverRolPagoDetalleId)
	throws GenericBusinessException ;
	
	public Double sumaImpuestoRentaRol(Collection<Long> contratosId
			,RolPagoIf rolPagoIf,TipoRolIf tipoRolIf,TipoRol tipoRol,String estado, boolean devolverRolPagoDetalleId)
	throws GenericBusinessException ;
	
	public Collection<RolPagoIf> getRolPagoListOrdenado();
	
	public Collection getYearList() throws GenericBusinessException;
	
	/************************* RUBROS EVENTUALES ***********************************************/

	public void actualizarRubroEventuales(
		Long contratoId, ArrayList<RubroEventualIf> rubrosEventualesEmitidosColleccion,
		ArrayList<RubroEventualIf> rubrosEventualesEmitidosRemovidos,
		ArrayList<RubroEventualIf> rubrosEventualesAutorizadosColleccion,
		ArrayList<RubroEventualIf> rubrosEventualesPagadosColleccion) throws GenericBusinessException;
	public void eliminarRubroEventual(Long rubroEventualId) throws GenericBusinessException;;
	
	/*******************************************************************************************/
	
	public java.util.Collection findRolPagoByQuery(int startIndex, int endIndex, Map aMap) throws GenericBusinessException;
	public java.util.Collection<RolPagoIf> findRolPagoByQuery(Map<String,Object> aMap,Long contratoId,String estadoDetalle) throws GenericBusinessException;
	public int getRolPagoListSize(Map aMap) throws GenericBusinessException;

	/************************ CONSULTA PARA REPORTE POR CONTRATO **************************************/
	
	public Collection<Object> consultarReportePorContrato(Long contratoId,Date fechaInicio,Date fechaFin) throws GenericBusinessException;


	/************************ CONSULTA PARA RUBROS **************************************/
	
	public java.util.Collection<Object> consultarDetallePorRubro(
			RubroIf rubroIf,Date fechaInicio,Date fechaFin,Long tipoContratoId,
			Long tipoRolId,Long contratoId,String estadoDetalle, 
			Boolean modoEstrictoConsulta,Map<Long,RubroIf> mapaRubros,Long empresaId) throws GenericBusinessException;

	public Collection<RolPagoIf> getRolesPagoByFechaInicioByFechaFin(
			Long tipoContratoId, Long tipoRolId, Calendar calInicio,
			Calendar calFin) throws GenericBusinessException;
	
	public Map<String,Double> calcularBaseImponibleImpuestoRenta(Long rolPagoActualId,
			ContratoIf contratoIf, String politicaRubro, String queryRolesPago,
			int numeroMesesRestantes, Map<String, RubroIf> mapaRubroByCodigo,
			Map<Long,RubroIf> mapaRubros,Map<String, BigDecimal> codigoValorxRubro, Date fechaRolPago)
			throws GenericBusinessException;
	
	
	/******************* CONSULTA PARA DEUDAS POR EMPLEADO (HISTORIAL) ******************/
	
	public Collection findBeneficiosByRolPagoByContratoByEmpresaId(ContratoIf contratoIf,Boolean conFechaDeTipoRol,Long empresaId) throws GenericBusinessException;
	
	//correo
	public void enviarEmail(Long idEmpresa, EmpleadoIf empleado, String mes, String anio, 
			String absolutePath, FileInputStreamSerializable attachment) throws GenericBusinessException;
	
}
