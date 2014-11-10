package com.spirit.nomina.handler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spirit.contabilidad.entity.AsientoData;
import com.spirit.contabilidad.entity.AsientoDetalleData;
import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.ChequeEmitidoEJB;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.entity.CuentaEntidadIf;
import com.spirit.contabilidad.entity.CuentaIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.entity.PeriodoIf;
import com.spirit.contabilidad.entity.PlanCuentaIf;
import com.spirit.contabilidad.entity.PlantillaIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.contabilidad.handler.EstadoChequeEmitido;
import com.spirit.contabilidad.handler.OrigenCheque;
import com.spirit.contabilidad.handler.TipoEntidadEnum;
import com.spirit.contabilidad.session.AsientoSessionLocal;
import com.spirit.contabilidad.session.ChequeEmitidoSessionLocal;
import com.spirit.contabilidad.session.CuentaEntidadSessionLocal;
import com.spirit.contabilidad.session.CuentaSessionLocal;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.contabilidad.session.PeriodoSessionLocal;
import com.spirit.contabilidad.session.PlanCuentaSessionLocal;
import com.spirit.contabilidad.session.PlantillaSessionLocal;
import com.spirit.contabilidad.session.SubTipoAsientoSessionLocal;
import com.spirit.exception.CuentaNoImputableException;
import com.spirit.exception.GenericBusinessException;
import com.spirit.exception.SaldoCuentaNegativoException;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.DepartamentoIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.EmpresaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoDocumentoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.session.CuentaBancariaSessionLocal;
import com.spirit.general.session.DepartamentoSessionLocal;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.EmpresaSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.TipoDocumentoSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoDocumentoIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.session.ContratoSessionLocal;
import com.spirit.nomina.session.RolPagoDetalleSessionLocal;
import com.spirit.nomina.session.RolPagoDocumentoSessionLocal;
import com.spirit.nomina.session.RubroEventualSessionLocal;
import com.spirit.nomina.session.RubroSessionLocal;
import com.spirit.nomina.session.TipoContratoSessionLocal;
import com.spirit.nomina.session.TipoRolSessionLocal;

@Stateless
public class RolPagoAsientoAutomaticoHandler implements RolPagoAsientoAutomaticoHandlerLocal {

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB DocumentoSessionLocal documentoLocal;

	@EJB EventoContableSessionLocal eventoContableLocal;

	@EJB PlanCuentaSessionLocal planCuentaLocal;

	@EJB PeriodoSessionLocal periodoLocal;

	@EJB EmpresaSessionLocal empresaLocal;

	@EJB SubTipoAsientoSessionLocal subTipoAsientoLocal;

	@EJB TipoDocumentoSessionLocal tipoDocumentoLocal;

	@EJB PlantillaSessionLocal plantillaLocal;

	@EJB CuentaSessionLocal cuentaLocal;
	
	@EJB CuentaEntidadSessionLocal cuentaEntidadLocal;
	
	@EJB CuentaBancariaSessionLocal cuentaBancariaLocal;

	@EJB ContratoSessionLocal contratoLocal;

	@EJB EmpleadoSessionLocal empleadoLocal;

	@EJB DepartamentoSessionLocal departamentoLocal;
	
	@EJB RubroSessionLocal rubroLocal;
	
	@EJB RubroEventualSessionLocal rubroEventualLocal;
	
	@EJB AsientoSessionLocal asientoLocal;
	
	@EJB RolPagoDetalleSessionLocal rolPagoDetalleLocal;
	
	@EJB TipoRolSessionLocal tipoRolLocal;
	
	@EJB TipoContratoSessionLocal tipoContratoLocal;

	@EJB ChequeEmitidoSessionLocal chequeEmitidoLocal;
	
	@EJB RolPagoDocumentoSessionLocal rolPagoDocumentoLocal;
	
	@EJB UtilitariosSessionLocal utilitariosLocal;
	
	@EJB NominaUtilesLocal utilesLocal;
	
	@EJB ParametroEmpresaSessionLocal parametroEmpresaLocal;
	
	Map<Long, ContratoIf> mapaContrato = new HashMap<Long, ContratoIf>();
	Map<Long, EmpleadoIf> mapaEmpleado = new HashMap<Long, EmpleadoIf>();

	public Collection<AsientoIf> generarAsientoAutomaticoQuincenal(
			Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.QUINCENAL )
			throw new GenericBusinessException("Método solo para creacion de Asientos "+TipoRol.QUINCENAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		//TipoContratoIf tipoContratoIf = tipoContratoLocal.getTipoContrato(rolPagoIf.getTipocontratoId());
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		if ( documentos.size() == 1 ){
			
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoContratoIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TipoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 1L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());
			
			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();

			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			if (nominaCollection.size() > 0){
				String baseObservacion = "ANTICIPO ROL 1ERA QUINCENA, ";
				String observacion = baseObservacion+getMesAnio(rolPagoIf);
				String referencia = "PRIMERA QUINCENA";
				String glosa = "ANTICIPO A LA QUINCENA";
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta, periodo, oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
				Long cuentaBancariaId = null;
				//detalle del asiento
				for (  DatoAsientoPagoRol dato : nominaCollection ){
					PlantillaIf plantilla = getPlantillaByNombre(plantillas, "NOMINA"); 
						if ( plantilla == null )
							throw new GenericBusinessException("Plantilla que contenga NOMINA no encontrada");
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+" DE "+dato.getNombreEmpleado());
					total += dato.getTotal();
					cuentaBancariaId = dato.getCuentaBancariaId();
				}
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria,
						plantillas, total,cuentaBancariaId,asientoDetalleColeccion,referencia,glosa);
				
				procesarAsiento(asientosRetornar,null, asiento,asientoDetalleColeccion, null);
			}
			
			Date fechaCheque = obtenerFechaDeCheque(fechaPago, tipoRol,empresaId);
			
			for (  DatoAsientoPagoRol dato : chequesCollection ){
				
				//Se registra el Asiento para cheques
				String observacion = dato.getNombreEmpleado();
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
				
				asientoDetalleColeccion = null;
				asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
				PlantillaIf plantilla = getPlantillaByNombre(plantillas, "NOMINA"); 
					if ( plantilla == null )
						throw new GenericBusinessException("Plantilla que contenga NOMINA no encontrada");
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
				
				//Se crea el detalle de nomina
				String glosa = "ANTICIPO DE ROL 1ERA QUINCENA "+dato.getNombreEmpleado();
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal()
					, cuenta, plantilla,dato.getNombreEmpleado(),glosa);
				
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria,
						plantillas, dato.getTotal(),dato.getCuentaBancariaId()
						,asientoDetalleColeccion,dato.getNumeroCheque(),glosa);
				
				registrarChequeEmitido(fechaCheque,
					mapaChequesEmitidos,rolPagoIf.getId(), dato.getNumeroCheque(), dato.getCuentaBancariaId()
					,glosa, utilitariosLocal.redondeoValor( new BigDecimal(dato.getTotal()) ), dato.getNombreEmpleado());
				
				procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, null);
			}

			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol y Tipo de Contrato no tiene Documento asociado, sirve para la creación de Asientos");
	}

	private boolean usarFechaActual(TipoRolIf tipoRolIf) throws GenericBusinessException {
		boolean usarFechaActualParaPagarRol = false;
		Map queryMap = new HashMap();
		queryMap.put("empresaId", tipoRolIf.getEmpresaId());
		queryMap.put("codigo", "UFAPPR");
		Iterator it = parametroEmpresaLocal.findParametroEmpresaByQuery(queryMap).iterator();
		if (it.hasNext()) {
			ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) it.next();
			usarFechaActualParaPagarRol = (parametroEmpresa.getValor().equals("S"))?true:false;
		}
		
		return usarFechaActualParaPagarRol;
	}
	
	public Collection<AsientoIf> generarAsientoAutomaticoAutorizacionMensual(Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoAsientoMensual> chequesCollection, Collection<DatoAsientoMensual> nominaCollection,RolPagoIf rolPagoQuincena)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.MENSUAL )
			throw new GenericBusinessException("Método solo para creación de Asientos "+TipoRol.MENSUAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//AsientoIf asientoRetornar = null;
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 1L );
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			//ESTA BASE ES IMPORTANTE POR AHORA PORQUE PERMITE DISTINGUIR LA CABECERA DE ASIENTO DE NOMINA PARA EL ROL
			//Y ASI PODER AGREGAR A ESTE MAS DETALLES EN EL FUTURO, SI ES QUE SE QUIERE USAR EL MISMO ASIENTO.
			
			String baseObservacion = "CANCELACION ROL 2DA QUINCENA, "; 
			 
			String observacion = baseObservacion+getMesAnio(rolPagoIf);
			Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existen Plantillas Contables para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			TipoRolIf tipoRolQuincenal = null;
			//PARA NOMINA
			AsientoIf asiento = null;

			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			Map<String,Double> mapaTotales = new HashMap<String, Double>();
			
			if ( nominaCollection.size() > 0 ) {
				asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
	
				tipoRolQuincenal = buscarTipoRolByName("%QUINCENAL%",tipoDocumento.getEmpresaId());
				
				for (DatoAsientoMensual dato : nominaCollection){
	
					mapaTotales.put("totalIngresos", 0.0);
					mapaTotales.put("totalEgresos", 0.0);
					ArrayList<RolPagoDetalleIf> listaEventuales = new ArrayList<RolPagoDetalleIf>();
					ArrayList<RolPagoDetalleIf> listaEventualesQuincenales = new ArrayList<RolPagoDetalleIf>();
					Long contratoId = null;
					
					Collection<RolPagoDetalleIf> rolPagoDetalles = dato.getRolPagoDetalleCollection();
					
					//Crear asientos para rubros que no son eventuales
					contratoId = crearAsientoDetalleRubroNoEventuales(
						rolPagoIf, tipoRolIf, mapaRubros, plantillas,
						mapaContratoDepartamento, mapaTotales,
						asientoDetalleColeccion, dato, listaEventuales,
						contratoId, rolPagoDetalles);
					
					Map<Long, Double> mapaTotalesRubroEventuales = new HashMap<Long, Double>();
					//Agrupo los valores de los rubros eventuales 
					//for ( RolPagoDetalleIf rpd : listaEventuales ){
					Iterator<RolPagoDetalleIf> itRpd = listaEventuales.iterator();
					while ( itRpd.hasNext() ){
						RolPagoDetalleIf rpd = itRpd.next();
						RubroIf r = verificarRubro(mapaRubros, rpd);
						Double total = mapaTotalesRubroEventuales.get(r.getId());
						if ( total != null ){
							total += utilitariosLocal.redondeoValor(rpd.getValor().doubleValue());
							total = utilitariosLocal.redondeoValor(total);
							mapaTotalesRubroEventuales.put(r.getId(), total);
							itRpd.remove();
						} else {
							mapaTotalesRubroEventuales.put(r.getId(), rpd.getValor().doubleValue());
						}
					}
					
					//Crear asientos para rubros eventuales 
					crearAsientoDetalleRubroEventual(rolPagoIf,tipoRolQuincenal,
						rolPagoQuincena, tipoRolIf, mapaRubros, plantillas,
						mapaContratoDepartamento, mapaTotales,
						asientoDetalleColeccion, dato, listaEventuales,
						listaEventualesQuincenales,mapaTotalesRubroEventuales);
					
					//Ingreso del asiento de Nomina
					creacionAsientoNomina(rolPagoIf, plantillas, mapaTotales,asientoDetalleColeccion,"","CANCELACION ROL "+dato.getNombre());
				}
				//asientoRetornar = buscarAsientoCreado(asientoRetornar, asiento,asientoDetalleColeccion);
				//asientosRetornar.add(asientoRetornar);
				
				//procesarAsiento(asientosRetornar, asiento,asientoDetalleColeccion);
			}
			
			//PARA CHEQUES
			if ( chequesCollection.size() > 0 ){
				
				if (asiento == null)
					asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
				
				for ( DatoAsientoMensual dato : chequesCollection ){
					
					//observacion = "CANCELACION ROL 2DA QUINCENA " +dato.getNombre()+", "+getMesAnio(rolPagoIf);;
					//AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null);
					
					//List<AsientoDetalleIf>asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
					
					if ( tipoRolQuincenal == null )
						tipoRolQuincenal = buscarTipoRolByName("%QUINCENAL%",tipoDocumento.getEmpresaId());
					
					//mapaTotales = new HashMap<String, Double>();
					mapaTotales.put("totalIngresos", 0.0);
					mapaTotales.put("totalEgresos", 0.0);
					ArrayList<RolPagoDetalleIf> listaEventuales = new ArrayList<RolPagoDetalleIf>();
					ArrayList<RolPagoDetalleIf> listaEventualesQuincenales = new ArrayList<RolPagoDetalleIf>();
					Long contratoId = null;
					
					Collection<RolPagoDetalleIf> rolPagoDetalles = dato.getRolPagoDetalleCollection();
					
					//Crear asientos para rubros que no son eventuales
					contratoId = crearAsientoDetalleRubroNoEventuales(
							rolPagoIf, tipoRolIf, mapaRubros, plantillas,
							mapaContratoDepartamento, mapaTotales,
							asientoDetalleColeccion, dato, listaEventuales,
							contratoId, rolPagoDetalles);
					
					Map<Long, Double> mapaTotalesRubroEventuales = new HashMap<Long, Double>();
					//Agrupo los valores de los rubros eventuales 
					//for ( RolPagoDetalleIf rpd : rolPagoDetalles ){
					Iterator<RolPagoDetalleIf> itRubroEventual = listaEventuales.iterator(); 
					while( itRubroEventual.hasNext() ){
						RolPagoDetalleIf rpd = itRubroEventual.next();
						RubroIf r = verificarRubro(mapaRubros, rpd);
						Double total = mapaTotalesRubroEventuales.get(r.getId());
						if ( total != null ){
							total += utilitariosLocal.redondeoValor(rpd.getValor().doubleValue());
							total = utilitariosLocal.redondeoValor(total);
							mapaTotalesRubroEventuales.put(r.getId(), total);
							itRubroEventual.remove();
						} else {
							mapaTotalesRubroEventuales.put(r.getId(), rpd.getValor().doubleValue());
						}
					}
					
					//Crear asientos para rubros eventuales que esten
					//como descuento en el mes
					crearAsientoDetalleRubroEventual(rolPagoIf,tipoRolQuincenal,
							rolPagoQuincena, tipoRolIf, mapaRubros, plantillas,
							mapaContratoDepartamento, mapaTotales,
							asientoDetalleColeccion, dato, listaEventuales,
							listaEventualesQuincenales,mapaTotalesRubroEventuales);
					
				
					//Ingreso del asiento de Nomina
					creacionAsientoNomina(rolPagoIf, plantillas, mapaTotales,asientoDetalleColeccion,"","CANCELACION ROL "+dato.getNombre());
					
					//procesarAsiento(asientosRetornar, asiento,asientoDetalleColeccion);
				}
			}

			procesarAsiento(asientosRetornar,null, asiento,asientoDetalleColeccion, null);
			
			//Agrupacion de detalles
			//asientoDetalleColeccion = agruparDetalles(asientoDetalleColeccion);
			//Se genera asiento
			
			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	public Collection<AsientoIf> generarAsientoAutomaticoMultas(Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoMulta> listaMultas)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.MENSUAL )
			throw new GenericBusinessException("Método solo para creación de Asientos "+TipoRol.MENSUAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//AsientoIf asientoRetornar = null;
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 4L );
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			//ESTA BASE ES IMPORTANTE POR AHORA PORQUE PERMITE DISTINGUIR LA CABECERA DE ASIENTO DE NOMINA PARA EL ROL
			//Y ASI PODER AGREGAR A ESTE MAS DETALLES EN EL FUTURO, SI ES QUE SE QUIERE USAR EL MISMO ASIENTO.
			
			String baseObservacion = "MULTAS DE ROL DE PAGO "; 
			 
			String observacion = baseObservacion+getMesAnio(rolPagoIf);
			Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existen Plantillas Contables para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			
			AsientoIf asiento = null;

			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			Map<RubroIf,Double> mapaTotales = new HashMap<RubroIf, Double>();
			
			Double total = 0.00;
			if ( listaMultas.size() > 0 ) {
				asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
				for ( DatoMulta dato : listaMultas )
				{
					Collection<RolPagoDetalleIf> rolPagoDetalles = dato.getRolPagoDetalleCollection();
					mapaTotales = new HashMap<RubroIf, Double>();
					for ( RolPagoDetalleIf rpd : rolPagoDetalles ){
						if ( rpd.getRubroEventualId() != null ){
							RubroIf r = verificarRubro(mapaRubros, rpd);
							if ( r.getCodigo().startsWith("MLT") ){
								Double totalTmp = mapaTotales.get(r.getCodigo());
								if ( totalTmp == null ){
									totalTmp = rpd.getValor().doubleValue();
								} else{
									totalTmp += rpd.getValor().doubleValue();
								}
								mapaTotales.put(r, totalTmp);
							}
						}
					}
					
					//Crear asientos para rubros eventuales 
					for ( RubroIf rubro : mapaTotales.keySet() ){
						Double totalMulta = utilitariosLocal.redondeoValor(mapaTotales.get(rubro));
						creacionAsientoMulta(rolPagoIf, plantillas, asientoDetalleColeccion,
								"",dato,rubro,rubro.getNombre()+" DE "+dato.getNombreEmpleado(),
								totalMulta,rubro.getNemonico() );
						total += totalMulta;
					}
					
				}
				
				creacionAsientoMulta(rolPagoIf, plantillas, asientoDetalleColeccion,
						"",null,null,"TOTAL MULTAS", 
						total,"PUENTE","MULTA");
				
			}
			if ( total > 0 )
				procesarAsiento(asientosRetornar,null, asiento,asientoDetalleColeccion, null);
			
			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	private void creacionAsientoMulta(RolPagoIf rolPagoIf,
			Collection<PlantillaIf> plantillas,
			List<AsientoDetalleIf> asientoDetalleColeccion
			,String referencia, DatoMulta dato,RubroIf rubroIf,String preGlosa,
			Double totalMulta,String... nemonicoRubro)
			throws GenericBusinessException {
		//String nemonicoRubro = rubro.getNemonico();
		PlantillaIf plantilla = getPlantillaByNombre(plantillas,
				(nemonicoRubro!= null && nemonicoRubro.length == 1) ? nemonicoRubro[0] : "MULTA");
		CuentaIf cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
		String glosa = plantilla.getGlosa();
		if ( dato != null ){
			glosa = (glosa != null && !glosa.equals("") )?
				glosa+" DE "+dato.getNombreEmpleado() : preGlosa ;
		} else
			glosa = (glosa != null && !glosa.equals("") )? glosa : preGlosa ;
		creacionAsientoDetalles(
				rolPagoIf, asientoDetalleColeccion,totalMulta, cuenta, plantilla,referencia,glosa);
	}
	
	
	private  TipoRolIf buscarTipoRolByName(String nombreTipoRol,Long empresaId) throws GenericBusinessException{
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("nombre", nombreTipoRol);
		mapa.put("empresaId", empresaId);
		Collection<TipoRolIf> tipos = tipoRolLocal.findTipoRolByQuery(mapa);
		if ( tipos.size() == 0 ){
			throw new GenericBusinessException("No existe Tipo de Rol con nombre que contenga "+nombreTipoRol+" !!");
		} else if ( tipos.size() == 1 ){
			return tipos.iterator().next();
		}  else {
			throw new GenericBusinessException("No existe Tipo de Rol con nombre que contenga "+nombreTipoRol+" !!");
		} 
	}

	private Long crearAsientoDetalleRubroNoEventuales(RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, Map<Long, RubroIf> mapaRubros,
			Collection<PlantillaIf> plantillas,
			Map<Long, DepartamentoIf> mapaContratoDepartamento,
			Map<String, Double> mapaTotales,
			List<AsientoDetalleIf> asientoDetalleColeccion,
			DatoAsientoMensual dato,
			ArrayList<RolPagoDetalleIf> listaEventuales, Long contratoId,
			Collection<RolPagoDetalleIf> rolPagoDetalles)
			throws GenericBusinessException {
		procesar_no_eventuales:
		for ( RolPagoDetalleIf rolPagoDetalleIf : rolPagoDetalles ){
			contratoId = rolPagoDetalleIf.getContratoId();
			RubroIf rubroIf = verificarRubro(mapaRubros, rolPagoDetalleIf);
			Double valorExtra = 0.0;
			
			//Saco los rubros eventuales, se los coloca en una lista y no se los toma en cuenta.
			if ( rolPagoDetalleIf.getRubroEventualId() != null ){
				listaEventuales.add(rolPagoDetalleIf);
				continue procesar_no_eventuales;
			}
			
			//SUMA LOS INGRESOS Y EGRESOS POR CONTRATO, PARA DESPUES SACAR EL VALOR DEL ASIENTO DE NOMINA
			sumarIngresosEgresos(tipoRolIf, mapaTotales,rolPagoDetalleIf, rubroIf,valorExtra,null);

			PlantillaIf plantilla = getPlantillaByRubro(rubroIf, plantillas); 
			if ( plantilla == null )
				throw new GenericBusinessException("Plantilla no encontrada para Rubro "+rubroIf.getNombre());
			StringBuilder sb = new StringBuilder("");
			CuentaIf cuenta = buscarCuenta(rolPagoDetalleIf.getContratoId(), plantilla, mapaContratoDepartamento,sb);
			creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,rolPagoDetalleIf.getValor().doubleValue()
					, cuenta, plantilla,"",rubroIf.getNombre()+" "+dato.getNombre());
		}
		return contratoId;
	}

	private void crearAsientoDetalleRubroEventual(RolPagoIf rolPagoIf,
			TipoRolIf tipoRolQuincenal,
			RolPagoIf rolPagoQuincena, TipoRolIf tipoRolIf,
			Map<Long, RubroIf> mapaRubros, Collection<PlantillaIf> plantillas,
			Map<Long, DepartamentoIf> mapaContratoDepartamento,
			Map<String, Double> mapaTotales,
			List<AsientoDetalleIf> asientoDetalleColeccion,
			DatoAsientoMensual dato, ArrayList<RolPagoDetalleIf> listaEventuales,
			ArrayList<RolPagoDetalleIf> listaEventualesQuincenales,
			Map<Long, Double> mapaTotalesRubroEventuales )
			throws GenericBusinessException {
		for ( RolPagoDetalleIf rpd : listaEventuales ){
			RubroEventualIf re = rubroEventualLocal.getRubroEventual(rpd.getRubroEventualId());
			RubroIf rubroIf = verificarRubroEnMapa(mapaRubros, re.getRubroId());
			
			/*Double valorExtra = 0.0;
			Map<String, Object> mapaEventualQuincena = new HashMap<String, Object>();
			mapaEventualQuincena.put("rolpagoId", rolPagoQuincena.getId() );
			mapaEventualQuincena.put("contratoId", rpd.getContratoId() ); 
			mapaEventualQuincena.put("tipoRolIdCobro", tipoRolQuincenal.getId() );
			mapaEventualQuincena.put("mesCobro", rolPagoIf.getMes() );
			mapaEventualQuincena.put("anioCobro", rolPagoIf.getAnio() );
			Collection<RolPagoDetalleIf> rolesPagoQuincena = rolPagoDetalleLocal
				.findRolPagoDetalleEventualesByMapByEstados(mapaEventualQuincena
						, EstadoRubroEventual.getLetraEstadoRubroEventual(EstadoRubroEventual.AUTORIZADO)
						, EstadoRubroEventual.getLetraEstadoRubroEventual(EstadoRubroEventual.PAGADO) );
			for ( RolPagoDetalleIf rpdq : rolesPagoQuincena ){
				//registro los rubros eventuales quincenales que son considerados
				//para el asiento, y sirve para que no vuelvan a ser usados.
				listaEventualesQuincenales.add(rpdq);
				valorExtra += rpdq.getValor().doubleValue();
			}	*/

			//SUMA LOS INGRESOS Y EGRESOS POR CONTRATO, PARA DESPUES SACAR EL VALOR DEL ASIENTO DE NOMINA
			Double total = mapaTotalesRubroEventuales.get(rubroIf.getId());
			sumarIngresosEgresos(tipoRolIf, mapaTotales,rpd, rubroIf,0D,total);

			PlantillaIf plantilla = null;
			//Si el rubro eventual no tiene fecha de pago ni tipo de pago es que no es cheque
			//entonces hay que usar nemonico "MATERIALES_MAS"
			if ( re.getFechaPago() == null && re.getTipoRolIdPago() == null && 
				 ( rubroIf.getNemonico()==null || rubroIf.getNemonico().equals("") ) )
				plantilla = getPlantillaByNombre(plantillas,"MATERIAL","MAS");
			//si no usa empleado
			else 
				plantilla = getPlantillaByRubro(rubroIf, plantillas);
			
			if ( plantilla == null )
				throw new GenericBusinessException("Plantilla no encontrada para Rubro "+rubroIf.getNombre());
			StringBuilder sb = new StringBuilder("");
			CuentaIf cuenta = buscarCuenta(rpd.getContratoId(), plantilla, mapaContratoDepartamento,sb);
			//creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,rpd.getValor().doubleValue()
			//	, cuenta, plantilla,"",rubroIf.getNombre()+" "+dato.getNombre());
			creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,total
				, cuenta, plantilla,"",rubroIf.getNombre()+" "+dato.getNombre());
		}
	}
	
	/*
	private void crearAsientoDetalleRubroEventualQuincenal(RolPagoIf rolPagoIf,
			RolPagoIf rolPagoQuincena, TipoRolIf tipoRolIf,
			Map<Long, RubroIf> mapaRubros, Collection<PlantillaIf> plantillas,
			Map<Long, DepartamentoIf> mapaContratoDepartamento,
			Map<String, Double> mapaTotales,
			List<AsientoDetalleIf> asientoDetalleColeccion,
			DatoAsientoMensual dato,
			ArrayList<RolPagoDetalleIf> listaEventuales, Long contratoId,
			ArrayList<RolPagoDetalleIf> listaEventualesQuincenales)
			throws GenericBusinessException {
		Map<String, Object> mapaEventualQuincena = new HashMap<String, Object>();
		mapaEventualQuincena.put("rolpagoId", rolPagoQuincena.getId());
		mapaEventualQuincena.put("contratoId", contratoId);
		mapaEventualQuincena.put("tipoRolIdCobro", tipoRolIf.getId());
		mapaEventualQuincena.put("mesCobro", rolPagoQuincena.getMes());
		mapaEventualQuincena.put("anioCobro", rolPagoQuincena.getAnio());
		mapaEventualQuincena.put("tipoContratoId", rolPagoQuincena.getTipocontratoId() );
		Collection<RolPagoDetalleIf> rolesPagoQuincena = rolPagoDetalleLocal
		.findRolPagoDetalleEventualesByMapByEstados(mapaEventualQuincena, 
				EstadoRubroEventual.AUTORIZADO.getLetra(),
				EstadoRubroEventual.PAGADO.getLetra() );
		for ( RolPagoDetalleIf rpdq : rolesPagoQuincena ){
			if ( !contieneRolPagoDetalle(listaEventuales, rpdq) && 
				 !contieneRolPagoDetalle(listaEventualesQuincenales,rpdq) ){
				RubroIf rubroIf = verificarRubro(mapaRubros, rpdq);
				sumarIngresosEgresos(tipoRolIf, mapaTotales,rpdq, rubroIf,0D,null);
				
				PlantillaIf plantilla = getPlantillaByRubro(rubroIf, plantillas); 
				if ( plantilla == null )
					throw new GenericBusinessException("Plantilla no encontrada para Rubro "+rubroIf.getNombre());
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(rpdq.getContratoId(), plantilla, mapaContratoDepartamento,sb);
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,rpdq.getValor().doubleValue()
						, cuenta, plantilla,"",rubroIf.getNombre()+" "+dato.getNombre());
				
			}
		}
	}
	
	private boolean contieneRolPagoDetalle( ArrayList<RolPagoDetalleIf> lista , RolPagoDetalleIf rpd ){
		for ( RolPagoDetalleIf rpdt : lista ){
			if ( rpdt.getId().equals(rpd.getId()) ){
				return true;
			}
		}
		return false;
	}
	*/
	
	public Collection<AsientoIf> generarAsientoAutomaticoAnticipos(
			Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoAsientoPagoRol> chequesCollection, 
			Collection<DatoAsientoPagoRol> nominaCollection,Map<String, ChequeDatos> mapaChequesEmitidos,
			Map<Long, RubroIf> mapaRubros,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( !(tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL) )
			throw new GenericBusinessException("Método solo para creación de Asientos de Anticipos  "+TipoRol.QUINCENAL+" y "+TipoRol.MENSUAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 0L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();

			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();
			Long cuentaBancariaId = null;
			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			if ( nominaCollection.size() > 0 ){
				String baseObservacion = "CANCELACIÓN ";
				String observacion = "CANCELACIÓN ";
				String glosa = "CANCELACIÓN ";
				Double total = 0.0;
				//detalle del asiento
				for ( DatoAsientoPagoRol dato : nominaCollection  ){
					baseObservacion = dato.getNombreRubro()+", ";
					//Long rolPagoDetalleId = dato.getRolPagoDetalleId();
					
					RubroIf rubroIf = null;
					String nemonico = null;
					if ( dato.getRubroIf() != null ){
						rubroIf = mapaRubros.get(dato.getRubroIf().getId());
						nemonico = rubroIf.getNemonico();
					}
					
					PlantillaIf plantilla = null;
					if ( rubroIf != null && nemonico != null && !nemonico.trim().equals("") )
						plantilla = getPlantillaByNombre(plantillas, nemonico.trim() );
					else 
						plantilla = getPlantillaByNombre(plantillas, "EMPLEADO");
					
					cuentaBancariaId = dato.getCuentaBancariaId();
					dato.getNombreEmpleado();
					observacion += tipoRol.toString();
					glosa += dato.getNombreRubro();
					
					if ( plantilla == null ){
						if ( rubroIf != null && nemonico != null && !nemonico.trim().equals("") )
							throw new GenericBusinessException("Plantilla que contenga "+rubroIf.getNemonico()+" no encontrada");
						throw new GenericBusinessException("Plantilla que contenga EMPLEADO no encontrada");
					}
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
		
					//Se crea el detalle de empleado
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
							, cuenta, plantilla,"",glosa);
					total += dato.getTotal();
				}
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta
						, periodo, oficinaId,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
			
				//Se crea el detalle del banco
				if (cuentaBancariaId == null)
					throw new GenericBusinessException("Cuenta Bancaria no establecida !!");
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, plantillas
						, total,cuentaBancariaId,asientoDetalleColeccion,"",glosa);

				procesarAsiento(asientosRetornar,null, asiento,asientoDetalleColeccion, null);
				
			}
				
			if ( chequesCollection.size() > 0 ){
				String baseObservacion = "CANCELACIÓN ";
				String observacion = "CANCELACIÓN ";
				String glosa = "CANCELACIÓN ";
				//detalle del asiento
				for ( DatoAsientoPagoRol dato : chequesCollection  ){
					asientoDetalleColeccion = null;
					asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
					baseObservacion = dato.getNombreRubro()+", ";
					String  numeroCheque = dato.getNumeroCheque() != null ? dato.getNumeroCheque() : ""; 
					String beneficiario = dato.getNombreEmpleado();
					Long rolPagoDetalleId = dato.getRolPagoDetalleId();
					
					RubroIf rubroIf = null;
					String nemonico = null;
					if ( dato.getRubroIf() != null ){
						rubroIf = mapaRubros.get(dato.getRubroIf().getId());
						nemonico = rubroIf.getNemonico();
					}
					
					PlantillaIf plantilla = null;
					if ( rubroIf != null && nemonico != null && !nemonico.trim().equals("") )
						plantilla = getPlantillaByNombre(plantillas, nemonico.trim() );
					else 
						plantilla = getPlantillaByNombre(plantillas, "EMPLEADO");
					
					observacion += tipoRol.toString();
					glosa += dato.getNombreRubro();
					
					if ( plantilla == null ){
						if ( rubroIf != null && nemonico != null && !nemonico.trim().equals("") )
							throw new GenericBusinessException("Plantilla que contenga "+rubroIf.getNemonico()+" no encontrada");
						throw new GenericBusinessException("Plantilla que contenga EMPLEADO no encontrada");
					}
					
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
		
					//Se crea el detalle de empleado
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
							, cuenta, plantilla,beneficiario,glosa);
					
					AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta
							, periodo, oficinaId,tipoDocumento,dato.getNombreEmpleado(),true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
					//Se crea el detalle del banco
					if (dato.getCuentaBancariaId() == null)
						throw new GenericBusinessException("Cuenta Bancaria no establecida !!");
					creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, plantillas
							, dato.getTotal(),dato.getCuentaBancariaId(),asientoDetalleColeccion,numeroCheque,glosa);

					procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, rolPagoDetalleId);
					
					if ( numeroCheque != null )
						registrarChequeEmitido(fechaPago,mapaChequesEmitidos,rolPagoIf.getId(),numeroCheque
							, dato.getCuentaBancariaId(),glosa, utilitariosLocal.redondeoValor( BigDecimal.valueOf(dato.getTotal()) ), beneficiario);

				}
			}
			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	public Collection<AsientoIf> generarAsientoAutomaticoAutorizacionAnticipos(
			Date fechaPago,RolPagoIf rolPagoIf,DatoAsientoRubroEventual datoAsientoRubroEventual,
			Map<Long,ContratoIf> mapaContratos,Map<Long,EmpleadoIf> mapaEmpleados )
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( !(tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL) )
			throw new GenericBusinessException("Método solo para creación de Asientos de Anticipos  "+TipoRol.QUINCENAL+" y "+TipoRol.MENSUAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 3L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();

			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();
			//Long cuentaBancariaId = null;
			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			String nombreRubro = datoAsientoRubroEventual.getNombreRubro();
			Double total = datoAsientoRubroEventual.getTotal();
			Collection<RolPagoDetalleIf> detalles = datoAsientoRubroEventual.getDetalle();
			
			String observacion = "CANCELACIÓN "+tipoRol.toString()+", "+nombreRubro;
			
			for ( RolPagoDetalleIf detalle : detalles){
				
				String glosa = "CANCELACIÓN DE ";
				
				ContratoIf contratoIf = verificarContrato(mapaContratos,detalle.getContratoId());
				EmpleadoIf empleadoIf = verificarEmpleado(mapaEmpleados, contratoIf.getEmpleadoId());
				
				
				PlantillaIf plantilla = getPlantillaByNombre(plantillas, "EMPLEADO");
				glosa += (empleadoIf.getNombres()+" "+empleadoIf.getApellidos());
				
				if ( plantilla == null )
					throw new GenericBusinessException("Plantilla que contenga EMPLEADO no encontrada");
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(contratoIf.getId(), plantilla, mapaContratoDepartamento,sb);
	
				//Se crea el detalle de empleado
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,detalle.getValor().doubleValue()
						, cuenta, plantilla,"",glosa);
				
			}
			creacionAsientoDetallePuente(rolPagoIf,mapaContratoDepartamento,
					plantillas, total,asientoDetalleColeccion,
					"","CANCELACIÓN DE "+nombreRubro);

			Calendar calAsiento = new GregorianCalendar();
			calAsiento.set(Calendar.MONTH,Integer.valueOf(rolPagoIf.getMes())-1);
			calAsiento.set(Calendar.YEAR,Integer.valueOf(rolPagoIf.getAnio()) );
			calAsiento.set(Calendar.DAY_OF_MONTH,calAsiento.getActualMaximum(Calendar.DAY_OF_MONTH) );
			Date fechaAsiento = new Date(calAsiento.getTimeInMillis());
			
			//AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta
			AsientoIf asiento = registrarAsiento(fechaAsiento,rolPagoIf, empresaId, planCuenta
					, periodo, oficinaId,tipoDocumento,observacion,true,"", usarFechaActualParaPagarRol, eventoContable);
		
			procesarAsiento(asientosRetornar,null, asiento,asientoDetalleColeccion, null);
			
			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	private ContratoIf verificarContrato(Map<Long,ContratoIf> mapaContrato,Long contratoId) throws GenericBusinessException{
		
		ContratoIf contratoIf = mapaContrato.get(contratoId);
		if ( contratoIf == null ){
			contratoIf = contratoLocal.getContrato(contratoId);
			mapaContrato.put(contratoIf.getId(),contratoIf);
		}
		return contratoIf;
	}

	private EmpleadoIf verificarEmpleado(Map<Long,EmpleadoIf> mapaEmpleado,Long empleadoId) throws GenericBusinessException{
		
		EmpleadoIf empleadoIf = mapaEmpleado.get(empleadoId);
		if ( empleadoIf == null ){
			empleadoIf = empleadoLocal.getEmpleado(empleadoId);
			mapaEmpleado.put(empleadoIf.getId(),empleadoIf);
		}
		return empleadoIf;
	}
	
	private void procesarAsiento(Collection<AsientoIf> asientosRetornar,Collection<AsientoIf> asientosGeneradosDeCheques,
			AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleColeccion, Long rolPagoDetalleId)
			throws GenericBusinessException {
		String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
		Collection asientosRetornarTmp = asientoLocal.findAsientoByNumero(numeroAsiento);
		if ( asientosRetornarTmp!=null && asientosRetornarTmp.iterator().hasNext() ){
			AsientoIf asientoCreado = (AsientoIf)asientosRetornarTmp.iterator().next();
			asientosRetornar.add( asientoCreado );
			
			//Guardo el id del asiento en la tabla rol pago detalle para tener la relacion entre ellos.
			if(rolPagoDetalleId != null){
				RolPagoDetalleIf rolPagoDetalle = rolPagoDetalleLocal.getRolPagoDetalle(rolPagoDetalleId);
				rolPagoDetalle.setAsientoId(asientoCreado.getPrimaryKey());
				manager.merge(rolPagoDetalle);
			}
			
			if ( asientosGeneradosDeCheques != null )
				asientosGeneradosDeCheques.add(asientoCreado);
		}
	}

	public Collection<AsientoIf> generarAsientoPagoMensual(
			Date fechaPago,RolPagoIf rolPagoIf,Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.MENSUAL )
			throw new GenericBusinessException("Método solo para creación de Asientos "+TipoRol.MENSUAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 2L );
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			
			 
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			//Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			
			Map<Long, Long> mapaCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			//PARA NOMINA
			if ( nominaCollection.size() > 0 ){
				//ESTA BASE ES IMPORTANTE POR AHORA PORQUE PERMITE DISTINGUIR LA CABECERA DE ASIENTO DE NOMINA PARA EL ROL
				//Y ASI PODER AGREGAR A ESTE MAS DETALLES EN EL FUTURO 
				//SI SE USA LA MISMA CABECERA.
				String baseObservacion = "CANCELACION ROL 2DA QUINCENA, "; 
				String observacion = baseObservacion+getMesAnio(rolPagoIf);
				String referencia = "ROL 2DA QUINCENA";
				String glosa = "CANCELACION ROL 2DA QUINCENA ";
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
	
				List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
				
				Long cuantaBancariaId = null;
				for (  DatoAsientoPagoRol dato : nominaCollection ){
					PlantillaIf plantilla = getPlantillaByNombre(plantillas, "NOMINA"); 
						if ( plantilla == null )
							throw new GenericBusinessException("Plantilla que contenga NOMINA no encontrada");
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+" DE "+dato.getNombreEmpleado());
					total += dato.getTotal();
					cuantaBancariaId = dato.getCuentaBancariaId();
				}
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapaCuentaDeCuentaBancaria,
						plantillas, total,cuantaBancariaId,asientoDetalleColeccion,referencia,glosa);
				
				procesarAsiento(asientosRetornar,null, asiento,
						asientoDetalleColeccion, null);
			}
			
			Date fechaCheque = obtenerFechaDeCheque(fechaPago, tipoRol,empresaId);
			
			//PARA CHEQUES
			if ( chequesCollection.size() > 0 ){
				for (  DatoAsientoPagoRol dato : chequesCollection ){
					
					//Se registra el Asiento para cheques
					String observacion = dato.getNombreEmpleado();
					AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
					
					List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
					PlantillaIf plantilla = getPlantillaByNombre(plantillas, "NOMINA"); 
						if ( plantilla == null )
							throw new GenericBusinessException("Plantilla que contenga NOMINA no encontrada");
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					String glosa = "CANCELACION ROL 2DA QUINCENA "+dato.getNombreEmpleado();
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal()
						, cuenta, plantilla,dato.getNombreEmpleado(),glosa);
					
					//Se crea el detalle de banco
					creacionAsientoDetalleBanco(rolPagoIf,mapaCuentaDeCuentaBancaria,
							plantillas, dato.getTotal(),dato.getCuentaBancariaId(),
							asientoDetalleColeccion,dato.getNumeroCheque(),glosa);
					
					registrarChequeEmitido(fechaCheque,
						mapaChequesEmitidos,rolPagoIf.getId(), dato.getNumeroCheque(), dato.getCuentaBancariaId()
						,glosa, utilitariosLocal.redondeoValor(new BigDecimal(dato.getTotal())), dato.getNombreEmpleado());
					
					procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, null);
				}
			}
			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}

	private Date obtenerFechaDeCheque(Date fechaPago, TipoRol tipoRol,
			Long empresaId) throws GenericBusinessException {
		Date fechaCheque = fechaPago;
		ParametroEmpresaIf pe = utilitariosLocal.getParametroEmpresa(empresaId, NominaParametros.TIPO_PARAMETRO, 
				NominaParametros.USAR_FECHA_DIA_FINAL_ROL_PARA_CHEQUES, "");
		String valorPe = pe.getValor();
		if ( valorPe!= null && !valorPe.trim().equals("") ){
			if ( "S".equals(valorPe.trim().toUpperCase()) ){
				Calendar cal = new GregorianCalendar();
				cal.setTime(new java.util.Date(fechaPago.getTime()));
				if ( tipoRol == TipoRol.QUINCENAL )
					cal.set(Calendar.DAY_OF_MONTH, 15);
				else if ( tipoRol == TipoRol.MENSUAL )
					cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				fechaCheque = new Date(cal.getTime().getTime());
			}
		}
		return fechaCheque;
	}
	
	private void registrarChequeEmitido(Date fechaPago,Map<String, ChequeDatos> mapaChequesEmitidos,Long rolPagoId,
			String numeroCheque,Long cuentaBancariaId,String detalle,BigDecimal valorCheque,String beneficiario) throws GenericBusinessException {
		
		ChequeDatos chequeDatos = null;
		if ( !mapaChequesEmitidos.containsKey( numeroCheque ) ){
			
			ChequeEmitidoIf chequeEmitidoIf = new ChequeEmitidoEJB();
			chequeEmitidoIf.setFechaEmision(fechaPago);
			chequeEmitidoIf.setCuentaBancariaId(cuentaBancariaId);
			chequeEmitidoIf.setNumero(numeroCheque);
			chequeEmitidoIf.setDetalle(detalle);
			chequeEmitidoIf.setValor(utilitariosLocal.redondeoValor(valorCheque));
			chequeEmitidoIf.setEstado(chequeEmitidoLocal.getLetraEstadoChequeEmitido(EstadoChequeEmitido.EMITIDO));
			//chequeEmitidoIf.setTipoDocumentoId(asientoIf.getTipoDocumentoId());
			//chequeEmitidoIf.setTransaccionId(asientoIf.getPrimaryKey());
			//chequeEmitidoIf.setOrigen(OrigenCheque.getLetraOrigenCheque(OrigenCheque.NOMINA));
			chequeEmitidoIf.setOrigen(OrigenCheque.NOMINA.getLetra());
			chequeEmitidoIf.setBeneficiario(beneficiario);
			
			chequeDatos =  new ChequeDatos();
			chequeDatos.setChequeEmitido(chequeEmitidoIf);
			//chequeDatos.setOrigen(OrigenCheque.getLetraOrigenCheque(OrigenCheque.NOMINA));
			chequeDatos.setOrigen(OrigenCheque.NOMINA.getLetra());
			chequeDatos.getTransaccionesIds().add(rolPagoId);
			
			mapaChequesEmitidos.put(numeroCheque, chequeDatos);
			
		} else {
			chequeDatos = mapaChequesEmitidos.get(numeroCheque);
			ChequeEmitidoIf chequeEmitidoIf = chequeDatos.getChequeEmitido();
			BigDecimal valor =  chequeEmitidoIf.getValor();
			valor = valor.add(valorCheque);
			chequeEmitidoIf.setValor( utilitariosLocal.redondeoValor(valor) );
			chequeDatos.setChequeEmitido(chequeEmitidoIf);
			chequeDatos.getTransaccionesIds().add(rolPagoId);
			mapaChequesEmitidos.put(numeroCheque, chequeDatos);
		}
	}
	
	private AsientoIf buscarAsientoCreado(AsientoIf asientoRetornar, AsientoIf asiento, List<AsientoDetalleIf> asientoDetalleColeccion, UsuarioIf usuario)
			throws GenericBusinessException {
		if ( asiento.getId() == null ){
			String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
			Collection asientosTmp = asientoLocal.findAsientoByNumero(numeroAsiento);
			if ( asientosTmp!=null && asientosTmp.iterator().hasNext() ){
				asientoRetornar = (AsientoIf)asientosTmp.iterator().next();
			}
		}
		else {
			try {
				asientoRetornar = (AsientoIf) asientoLocal.actualizarAsiento(asiento, asientoDetalleColeccion, null, null, null, false, false, usuario, new HashMap(), new HashMap(), new HashMap(), new HashMap(), false).get("ASIENTO");
			} catch (SaldoCuentaNegativoException e) {
				e.printStackTrace();
				throw new GenericBusinessException(e.getMessage());
			} catch (CuentaNoImputableException ex) {
				ex.printStackTrace();
				throw new GenericBusinessException(ex.getMessage());
			}
		
		}
		return asientoRetornar;
	}
	
	/*public AsientoIf generarAsientoAutomaticoProvisionAporteIessPatronal(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,Long empresaId)
	throws GenericBusinessException{
		
		return generarAsientoProvision(fechaPago,rolPagoIf, rolPagoDetalleCollection, TipoRol.APORTE_PATRONAL);
	}*/
	
	/*public AsientoIf generarAsientoAutomaticoProvisionFondoReserva(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,Long empresaId)
	throws GenericBusinessException{
		
		return generarAsientoProvision(fechaPago,rolPagoIf, rolPagoDetalleCollection, TipoRol.FONDO_RESERVA);
		
	}*/
	
	public AsientoIf generarAsientoAutomaticoProvisionVacaciones(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,Long empresaId)
	throws GenericBusinessException{
		
		return generarAsientoProvision(fechaPago,rolPagoIf, rolPagoDetalleCollection, TipoRol.VACACIONES);
		
	}
	
	public AsientoIf generarAsientoAutomaticoProvisionAporteIessPatronalMensual(Date fechaPago,RolPagoIf rolPagoIf, 
			Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
			TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException{
		
		return generarAsientoProvisionMensual(fechaPago,rolPagoIf,nominaCollection,
				chequesCollection,tipoRol);
	}
	
	public AsientoIf generarAsientoAutomaticoProvisionFondoReservaMensual(Date fechaPago,RolPagoIf rolPagoIf, 
			Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
			TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException{
		
		return generarAsientoProvisionMensual(fechaPago,rolPagoIf,nominaCollection,
				chequesCollection,tipoRol);
	}
	
	public AsientoIf generarAsientoAutomaticoProvisionDecimosMensual(Date fechaPago,RolPagoIf rolPagoIf, 
			Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
			TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException{
		
		return generarAsientoProvisionMensual(fechaPago,rolPagoIf,nominaCollection,
				chequesCollection,tipoRol);
	}
	
	/*public AsientoIf generarAsientoAutomaticoProvisionDecimos(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRolIf tipoRolIf, TipoRol tipoRol)
	throws GenericBusinessException{
		
		return generarAsientoProvision(fechaPago,rolPagoIf, rolPagoDetalleCollection, tipoRol);
		
	}*/
	
	private AsientoIf generarAsientoProvisionMensual(Date fechaPago,RolPagoIf rolPagoIf, 
			Collection<DatoAsientoMensual> nominaCollection,Collection<DatoAsientoMensual> chequesCollection,
			TipoRol tipoRol) throws GenericBusinessException{
		
		AsientoIf asientoRetornar = null;
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 1L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			String baseObservacion = "PROVISION "; 
			String observacion = baseObservacion+tipoRolIf.getNombre()+", "+getMesAnio(rolPagoIf);
			AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);

			//Plantillas Contable
			//Map<Long, Collection<PlantillaIf>> mapaPlantillas = new HashMap<Long, Collection<PlantillaIf>>();
			//Collection<PlantillaIf> plantillas = verificarPlantilla(mapaPlantillas, eventoContable.getId(), planCuenta.getId());
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());
			
			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			RubroIf rubro = utilesLocal.getRubroByTipoRol(tipoRol);
			PlantillaIf plantilla = getPlantillaByRubro(rubro, plantillas);
			
			//Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
			Double totalProvision = 0.0;
			
			//for ( RolPagoDetalleIf rolPagoDetalleIf : rolPagoDetalleCollection  ){
			for ( DatoAsientoMensual dato : nominaCollection  ){
			
				StringBuilder sb = new StringBuilder("");
				//CuentaIf cuenta = buscarCuenta(rolPagoDetalleIf.getContratoId(), plantilla, mapaContratoDepartamento, sb);
				sb.append(dato.getNombre()+", "+tipoRolIf.getNombre()+", ");
				CuentaIf cuenta = buscarCuenta(dato.getContratoId(), plantilla, mapaContratoDepartamento, null);
				//creacionAsientoDetalles(
				//		rolPagoIf, asientoDetalleColeccion,rolPagoDetalleIf.getValor().doubleValue(), cuenta, plantilla,"",sb.toString());
				creacionAsientoDetalles(
						rolPagoIf, asientoDetalleColeccion,dato.getTotal(), cuenta, plantilla,"",sb.toString());
				//totalProvision += rolPagoDetalleIf.getValor().doubleValue();
				totalProvision += dato.getTotal();
				
			}
			
			for ( DatoAsientoMensual dato : chequesCollection  ){
				
				StringBuilder sb = new StringBuilder("");
				//CuentaIf cuenta = buscarCuenta(rolPagoDetalleIf.getContratoId(), plantilla, mapaContratoDepartamento, sb);
				sb.append(dato.getNombre()+", "+tipoRolIf.getNombre()+", ");
				CuentaIf cuenta = buscarCuenta(dato.getContratoId(), plantilla, mapaContratoDepartamento, sb);
				//creacionAsientoDetalles(
				//		rolPagoIf, asientoDetalleColeccion,rolPagoDetalleIf.getValor().doubleValue(), cuenta, plantilla,"",sb.toString());
				creacionAsientoDetalles(
						rolPagoIf, asientoDetalleColeccion,dato.getTotal(), cuenta, plantilla,"",sb.toString());
				//totalProvision += rolPagoDetalleIf.getValor().doubleValue();
				totalProvision += dato.getTotal();
				
			}
			
			totalProvision = utilitariosLocal.redondeoValor(totalProvision);
			creacionAsientoProvision(rolPagoIf, asientoDetalleColeccion, tipoRol, plantillas, totalProvision);
				
			//Agrupacion de detalles
			//asientoDetalleColeccion = agruparDetalles(asientoDetalleColeccion);
			//Se genera asiento
			String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
			//Busco el asiento generado para retornarlo
			Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
			if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
				asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
			}
			return asientoRetornar;
		}else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
		
	}
	
	private AsientoIf generarAsientoProvision(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRol tipoRol) throws GenericBusinessException{
	
		AsientoIf asientoRetornar = null;
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 1L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			String baseObservacion = "PROVISION "; 
			String observacion = baseObservacion+tipoRolIf.getNombre()+", "+getMesAnio(rolPagoIf);
			AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);

			//Plantillas Contable
			//Map<Long, Collection<PlantillaIf>> mapaPlantillas = new HashMap<Long, Collection<PlantillaIf>>();
			//Collection<PlantillaIf> plantillas = verificarPlantilla(mapaPlantillas, eventoContable.getId(), planCuenta.getId());
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());
			
			//RubroIf rubro = utilesLocal.getRubroByTipoRol(tipoRol);
			
			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//PlantillaIf plantilla = getPlantillaByRubro(rubro, plantillas);
			
			Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			//Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
			Double totalProvision = 0.0;
			for ( RolPagoDetalleIf rolPagoDetalleIf : rolPagoDetalleCollection  ){
				StringBuilder sb = new StringBuilder("");
				RubroIf rubro = verificarRubro(mapaRubros, rolPagoDetalleIf);
				PlantillaIf plantilla = getPlantillaByRubro(rubro, plantillas);
				CuentaIf cuenta = buscarCuenta(rolPagoDetalleIf.getContratoId(), plantilla, mapaContratoDepartamento, sb);
				creacionAsientoDetalles(
						rolPagoIf, asientoDetalleColeccion,rolPagoDetalleIf.getValor().doubleValue(), cuenta, plantilla,"",sb.toString());
				totalProvision += rolPagoDetalleIf.getValor().doubleValue();
				
				//CAMBIO EL ESTADO DEL DETALLE A AUTORIZADO
				//rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.AUTORIZADO.getLetra());
			}
			totalProvision = utilitariosLocal.redondeoValor(totalProvision);
			creacionAsientoProvision(rolPagoIf, asientoDetalleColeccion, tipoRol, plantillas, totalProvision);
				
			//Agrupacion de detalles
			//asientoDetalleColeccion = agruparDetalles(asientoDetalleColeccion);
			//Se genera asiento
			String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
			//Busco el asiento generado para retornarlo
			Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
			if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
				asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
			}
			return asientoRetornar;
		}else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
		
	}
	
	public AsientoIf generarAsientoProvisionVariacion1(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRol tipoRol) throws GenericBusinessException{
		
		AsientoIf asientoRetornar = null;
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 3L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			String baseObservacion = "PROVISION "; 
			String observacion = baseObservacion+tipoRolIf.getNombre()+", "+getMesAnio(rolPagoIf);
			AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);

			//Plantillas Contable
			//Map<Long, Collection<PlantillaIf>> mapaPlantillas = new HashMap<Long, Collection<PlantillaIf>>();
			//Collection<PlantillaIf> plantillas = verificarPlantilla(mapaPlantillas, eventoContable.getId(), planCuenta.getId());
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());
			
			//RubroIf rubro = utilesLocal.getRubroByTipoRol(tipoRol);
			
			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//PlantillaIf plantilla = getPlantillaByRubro(rubro, plantillas);
			
			Map<Long,RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			//Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
			Double totalProvision = 0.0;
			for ( RolPagoDetalleIf rolPagoDetalleIf : rolPagoDetalleCollection  ){
				StringBuilder sb = new StringBuilder("");
				RubroIf rubro = verificarRubro(mapaRubros, rolPagoDetalleIf);
				PlantillaIf plantilla = getPlantillaByRubro(rubro, plantillas);
				CuentaIf cuenta = buscarCuenta(rolPagoDetalleIf.getContratoId(), plantilla, mapaContratoDepartamento, sb);
				creacionAsientoDetalles(
						rolPagoIf, asientoDetalleColeccion,rolPagoDetalleIf.getValor().doubleValue(), cuenta, plantilla,"",sb.toString());
				totalProvision += rolPagoDetalleIf.getValor().doubleValue();
				
				creacionAsientoProvision(rolPagoIf, asientoDetalleColeccion, tipoRol, plantillas, rolPagoDetalleIf.getValor().doubleValue() );
				
				//CAMBIO EL ESTADO DEL DETALLE A AUTORIZADO
				//rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.AUTORIZADO.getLetra());
			}
			totalProvision = utilitariosLocal.redondeoValor(totalProvision);
				
			//Agrupacion de detalles
			//asientoDetalleColeccion = agruparDetalles(asientoDetalleColeccion);
			//Se genera asiento
			String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
			//Busco el asiento generado para retornarlo
			Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
			if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
				asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
			}
			return asientoRetornar;
		}else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
		
	}
	
	public AsientoIf generarAsientoProvisionVariacion2(Date fechaPago,RolPagoIf rolPagoIf, Collection<RolPagoDetalleIf> rolPagoDetalleCollection,TipoRol tipoRol) throws GenericBusinessException{
		
		AsientoIf asientoRetornar = null;
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 5L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());

			//Se registra el Asiento
			Long oficinaId = eventoContable.getOficinaId();
			String baseObservacion = "ASIENTO RUBROS DEL IESS DE DESCUENTOS AL EMPLEADO "; 
			String observacion = baseObservacion+", "+getMesAnio(rolPagoIf);
			AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);

			//Plantillas Contable
			//Map<Long, Collection<PlantillaIf>> mapaPlantillas = new HashMap<Long, Collection<PlantillaIf>>();
			//Collection<PlantillaIf> plantillas = verificarPlantilla(mapaPlantillas, eventoContable.getId(), planCuenta.getId());
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());
						
			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			//Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
			Double totalProvision = 0.0;
			for ( RolPagoDetalleIf rolPagoDetalleIf : rolPagoDetalleCollection  ){
				StringBuilder sb = new StringBuilder("");
				RubroIf rubro = verificarRubro(mapaRubros, rolPagoDetalleIf); 
				PlantillaIf plantilla = getPlantillaByRubro(rubro, plantillas);
				CuentaIf cuenta = buscarCuenta(rolPagoDetalleIf.getContratoId(), plantilla, mapaContratoDepartamento, sb);
				creacionAsientoDetalles(
						rolPagoIf, asientoDetalleColeccion,rolPagoDetalleIf.getValor().doubleValue(), cuenta, plantilla,"",sb.toString());
				totalProvision += rolPagoDetalleIf.getValor().doubleValue();
				
				//CAMBIO EL ESTADO DEL DETALLE A AUTORIZADO
				//rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.AUTORIZADO.getLetra());
			}
			totalProvision = utilitariosLocal.redondeoValor(totalProvision);
			creacionAsientoIess(
					rolPagoIf, asientoDetalleColeccion, tipoRol, plantillas, totalProvision );
			
			//Agrupacion de detalles
			//asientoDetalleColeccion = agruparDetalles(asientoDetalleColeccion);
			//Se genera asiento
			String numeroAsiento = asientoLocal.procesarAsiento(asiento, asientoDetalleColeccion, true);
			//Busco el asiento generado para retornarlo
			Collection asientosRetornar = asientoLocal.findAsientoByNumero(numeroAsiento);
			if ( asientosRetornar!=null && asientosRetornar.iterator().hasNext() ){
				asientoRetornar = (AsientoIf)asientosRetornar.iterator().next();
			}
			return asientoRetornar;
		}else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
		
	}
	
	public Collection<AsientoIf> generarAsientoPagoDecimos(
			Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( !(tipoRol == TipoRol.DECIMO_CUARTO || tipoRol == TipoRol.DECIMO_TERCERO) )
			throw new GenericBusinessException("Método solo para creacion de Asientos "+TipoRol.QUINCENAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 2L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());
			
			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();

			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			String nemonico = "PROV_"+tipoRol.name();
			
			if (nominaCollection.size() > 0){
				String mesAnioString = getMesAnio(rolPagoIf);
				String baseObservacion = "PAGO "+tipoRol.toString()+", "+mesAnioString;
				String observacion = baseObservacion;
				String referencia = tipoRol.name();
				String glosa = "CANCELACIÓN DE ";
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta, periodo, oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
				//detalle del asiento
				
				Long cuentaBancariaId = null;

				for (  DatoAsientoPagoRol dato : nominaCollection ){
					PlantillaIf plantilla = getPlantillaByNombre(plantillas, nemonico); 
						if ( plantilla == null )
							throw new GenericBusinessException("Plantilla que contenga "+nemonico+" no encontrada");
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+dato.getNombreEmpleado()+mesAnioString);
					total += dato.getTotal();
					cuentaBancariaId = dato.getCuentaBancariaId();
				}
				if ( cuentaBancariaId == null )
					throw new GenericBusinessException("Cuenta Bancaria no establecida !!");
				
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, total,cuentaBancariaId,asientoDetalleColeccion,referencia,glosa);
				
				procesarAsiento(asientosRetornar,null, asiento,
						asientoDetalleColeccion, null);
			}
			
			
			for (  DatoAsientoPagoRol dato : chequesCollection ){
				
				//Se registra el Asiento para cheques
				String observacion = dato.getNombreEmpleado();
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
				
				asientoDetalleColeccion = null;
				asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
				
				PlantillaIf plantilla = getPlantillaByNombre(plantillas, nemonico); 
					if ( plantilla == null )
						throw new GenericBusinessException("Plantilla que contenga "+nemonico+" no encontrada");
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
				
				//Se crea el detalle de nomina
				String glosa = "CANCELACIÓN "+tipoRol.toString()+" "+dato.getNombreEmpleado();
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal()
					, cuenta, plantilla,dato.getNombreEmpleado(),glosa);
				
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, dato.getTotal(),dato.getCuentaBancariaId(),asientoDetalleColeccion,dato.getNumeroCheque(),glosa);
				
				
				registrarChequeEmitido(fechaPago,
					mapaChequesEmitidos,rolPagoIf.getId(), dato.getNumeroCheque(), dato.getCuentaBancariaId()
					,glosa, utilitariosLocal.redondeoValor( new BigDecimal(dato.getTotal()) ), dato.getNombreEmpleado());
				
				procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, null);
			}

			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	public Collection<AsientoIf> generarAsientoPagoAporteIessPatronal(
			Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.APORTE_PATRONAL )
			throw new GenericBusinessException("Método solo para creacion de Asientos "+TipoRol.APORTE_PATRONAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 2L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());
			
			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();

			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			if (nominaCollection.size() > 0){
				String baseObservacion = "PAGO "+tipoRol.toString()+", ";
				String observacion = baseObservacion+getMesAnio(rolPagoIf);
				String referencia = tipoRol.name();
				String glosa = "CANCELACIÓN DE ";
				String mesAnioString = getMesAnio(rolPagoIf);
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta, periodo, oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
				//detalle del asiento
				
				Long cuentaBancariaId = null;

				PlantillaIf plantilla = getPlantillaByNombre(plantillas,"PROV","APORTE","PATRONAL"); 
				if ( plantilla == null )
					throw new GenericBusinessException("Plantilla que contenga "+tipoRol.toString()+" no encontrada");
				
				for (  DatoAsientoPagoRol dato : nominaCollection ){
					
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+dato.getNombreEmpleado());
					total += dato.getTotal();
					cuentaBancariaId = dato.getCuentaBancariaId();
				}
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, total,cuentaBancariaId,asientoDetalleColeccion,referencia,glosa);
				
				procesarAsiento(asientosRetornar,null, asiento,
						asientoDetalleColeccion, null);
			}
			
			
			for (  DatoAsientoPagoRol dato : chequesCollection ){
				
				//Se registra el Asiento para cheques
				String observacion = dato.getNombreEmpleado();
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
				
				asientoDetalleColeccion = null;
				asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
				
				PlantillaIf plantilla = getPlantillaByNombre(plantillas,"PROV","APORTE","PATRONAL"); 
					if ( plantilla == null )
						throw new GenericBusinessException("Plantilla que contenga "+tipoRol.toString()+" no encontrada");
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
				
				//Se crea el detalle de nomina
				String glosa = "CANCELACIÓN "+tipoRol.toString()+" "+dato.getNombreEmpleado();
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal()
					, cuenta, plantilla,dato.getNombreEmpleado(),glosa);
				
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, dato.getTotal(),dato.getCuentaBancariaId(),asientoDetalleColeccion,dato.getNumeroCheque(),glosa);
				
				
				registrarChequeEmitido(fechaPago,
					mapaChequesEmitidos,rolPagoIf.getId(), dato.getNumeroCheque(), dato.getCuentaBancariaId()
					,glosa, utilitariosLocal.redondeoValor( new BigDecimal(dato.getTotal()) ), dato.getNombreEmpleado());
				
				procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, null);
			}

			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	public Collection<AsientoIf> generarAsientoPagoAporteIessPatronalVariacion1(
			Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> aportePatronalCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.APORTE_PATRONAL )
			throw new GenericBusinessException("Método solo para creacion de Asientos "+TipoRol.APORTE_PATRONAL);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 6L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());
			
			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();

			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			if (aportePatronalCollection.size() > 0){
				String baseObservacion = "PAGO "+tipoRol.toString()+" (DESCUENTOS A EMPLEADOS), ";
				String observacion = baseObservacion+getMesAnio(rolPagoIf);
				String referencia = tipoRol.name();
				String glosa = "CANCELACIÓN DE ";
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta, periodo, oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
				//detalle del asiento
				
				Long cuentaBancariaId = null;

				PlantillaIf plantilla = getPlantillaByNombre(plantillas,"TOTAL","IESS"); 
				if ( plantilla == null )
					throw new GenericBusinessException("Plantilla que contenga "+tipoRol.toString()+" no encontrada");
				
				for (  DatoAsientoPagoRol dato : aportePatronalCollection ){
					
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+dato.getNombreEmpleado());
					total += dato.getTotal();
					cuentaBancariaId = dato.getCuentaBancariaId();
				}
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, total,cuentaBancariaId,asientoDetalleColeccion,referencia,
						glosa+tipoRol.toString()+" ");
				
				procesarAsiento(asientosRetornar,null, asiento,
						asientoDetalleColeccion, null);
			}
			
			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	public Collection<AsientoIf> generarAsientoPagoFondoReserva(
			Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.FONDO_RESERVA )
			throw new GenericBusinessException("Método solo para creacion de Asientos "+TipoRol.FONDO_RESERVA);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 2L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());
			
			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();

			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			if (nominaCollection.size() > 0){
				String mesAnioString = getMesAnio(rolPagoIf);
				String baseObservacion = "PAGO "+tipoRol.toString()+", "+mesAnioString;
				String observacion = baseObservacion;
				String referencia = tipoRol.name();
				String glosa = "CANCELACIÓN DE ";
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta, periodo, oficinaId
						,tipoDocumento,observacion,true,baseObservacion, usarFechaActualParaPagarRol, eventoContable);
				
				//detalle del asiento
				
				Long cuentaBancariaId = null;
				
				PlantillaIf plantilla = getPlantillaByNombre(plantillas,"PROV","FONDO","RESERVA"); 
				if ( plantilla == null )
					throw new GenericBusinessException("Plantilla que contenga "+tipoRol.toString()+" no encontrada");
			
				for (  DatoAsientoPagoRol dato : nominaCollection ){
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+dato.getNombreEmpleado()+mesAnioString);
					total += dato.getTotal();
					cuentaBancariaId = dato.getCuentaBancariaId();
				}
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, total,cuentaBancariaId,asientoDetalleColeccion,referencia,glosa);
				
				procesarAsiento(asientosRetornar,null, asiento,
						asientoDetalleColeccion, null);
			}
			
			
			for (  DatoAsientoPagoRol dato : chequesCollection ){
				
				//Se registra el Asiento para cheques
				String observacion = dato.getNombreEmpleado();
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
				
				asientoDetalleColeccion = null;
				asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
				
				PlantillaIf plantilla = getPlantillaByNombre(plantillas,"PROV","FONDO","RESERVA"); 
					if ( plantilla == null )
						throw new GenericBusinessException("Plantilla que contenga "+tipoRol.toString()+" no encontrada");
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
				
				//Se crea el detalle de nomina
				String glosa = "CANCELACIÓN "+tipoRol.toString()+" "+dato.getNombreEmpleado();
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal()
					, cuenta, plantilla,dato.getNombreEmpleado(),glosa);
				
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, dato.getTotal(),dato.getCuentaBancariaId(),asientoDetalleColeccion,dato.getNumeroCheque(),glosa);
				
				
				registrarChequeEmitido(fechaPago,
					mapaChequesEmitidos,rolPagoIf.getId(), dato.getNumeroCheque(), dato.getCuentaBancariaId()
					,glosa, utilitariosLocal.redondeoValor( new BigDecimal(dato.getTotal()) ), dato.getNombreEmpleado());
				
				procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, null);
			}

			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	public Collection<AsientoIf> generarAsientoPagoVacaciones(
			Date fechaPago,RolPagoIf rolPagoIf, Collection<DatoAsientoPagoRol> chequesCollection, Collection<DatoAsientoPagoRol> nominaCollection,
			Map<String, ChequeDatos> mapaChequesEmitidos,Collection<AsientoIf> asientosGeneradosDeCheques)
	throws GenericBusinessException {
		
		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());

		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if ( tipoRol == null )
			throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
		
		if ( tipoRol != TipoRol.VACACIONES )
			throw new GenericBusinessException("Método solo para creacion de Asientos "+TipoRol.VACACIONES);
		
		boolean usarFechaActualParaPagarRol = usarFechaActual(tipoRolIf);
		
		Map<String,Object> mapaDocumentos =  new HashMap<String,Object>();
		mapaDocumentos.put("tipoContratoId",rolPagoIf.getTipocontratoId());
		mapaDocumentos.put("tipoRolId",rolPagoIf.getTiporolId());
		Collection<RolPagoDocumentoIf> documentos = rolPagoDocumentoLocal.findRolPagoDocumentoByQuery(mapaDocumentos);
		
		//if ( tipoRolIf.getDocumentoId() != null ){
		if ( documentos.size() == 1 ){
			Collection<AsientoIf> asientosRetornar = new ArrayList<AsientoIf>();
			Long empresaId = tipoRolIf.getEmpresaId();
			//Documento
			//DocumentoIf documento = (DocumentoIf) documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
			RolPagoDocumentoIf rpDoc = documentos.iterator().next();
			DocumentoIf documento = documentoLocal.getDocumento(rpDoc.getDocumentoId());
			
			//TIpoDocumento
			TipoDocumentoIf tipoDocumento = tipoDocumentoLocal.getTipoDocumento(documento.getTipoDocumentoId());

			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("etapa", 2L);
			//EventoContable
			EventoContableIf eventoContable = buscarEventoContable(empresaId,documento, mapa);
			if ( eventoContable == null )
				throw new GenericBusinessException("No existe Evento Contable para Documento "+documento.getNombre());

			//Plan de Cuenta
			PlanCuentaIf planCuenta = buscarPlanCuenta(empresaId, mapa,eventoContable);

			//Periodo
			PeriodoIf periodo = buscarPeriodo(empresaId, (usarFechaActualParaPagarRol)?fechaPago:rolPagoIf.getFecha());
			
			//Plantillas Contable
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContable.getId(), planCuenta.getId());
			if ( plantillas.size() == 0 )
				throw new GenericBusinessException("No existe Plantilla Contable para Evento Contable "+eventoContable.getNombre());

			Map<Long,DepartamentoIf> mapaContratoDepartamento = new HashMap<Long, DepartamentoIf>();
			List<AsientoDetalleIf> asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
			
			//Se registra el Asiento para nomina
			Long oficinaId = eventoContable.getOficinaId();

			Map<Long, Long> mapCuentaDeCuentaBancaria = new HashMap<Long, Long>();
			
			if (nominaCollection.size() > 0){
				String baseObservacion = "PAGO "+tipoRol.toString()+", ";
				String observacion = baseObservacion;
				String referencia = tipoRol.name();
				String glosa = "CANCELACIÓN DE ";
				String mesAnioString = getMesAnio(rolPagoIf);
				Double total = 0.0;
				
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf, empresaId, planCuenta, periodo, oficinaId
						,tipoDocumento,observacion,true,baseObservacion,usarFechaActualParaPagarRol, eventoContable);
				
				//detalle del asiento
				
				Long cuentaBancariaId = null;

				for (  DatoAsientoPagoRol dato : nominaCollection ){
					PlantillaIf plantilla = getPlantillaByNombre(plantillas, "VACACIONES"); 
						if ( plantilla == null )
							throw new GenericBusinessException("Plantilla que contenga VACACIONES no encontrada");
					StringBuilder sb = new StringBuilder("");
					CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
					
					//Se crea el detalle de nomina
					creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal().doubleValue()
						, cuenta, plantilla,referencia,glosa+dato.getNombreEmpleado()+mesAnioString);
					total += dato.getTotal();
					cuentaBancariaId = dato.getCuentaBancariaId();
				}
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, total,cuentaBancariaId,asientoDetalleColeccion,referencia,glosa);
				
				procesarAsiento(asientosRetornar,null, asiento,
						asientoDetalleColeccion, null);
			}
			
			
			for (  DatoAsientoPagoRol dato : chequesCollection ){
				
				//Se registra el Asiento para cheques
				String observacion = dato.getNombreEmpleado();
				AsientoIf asiento = registrarAsiento(fechaPago,rolPagoIf,empresaId,planCuenta,periodo,oficinaId,tipoDocumento,observacion,false,null, usarFechaActualParaPagarRol, eventoContable);
				
				asientoDetalleColeccion = null;
				asientoDetalleColeccion = new ArrayList<AsientoDetalleIf>();
				
				PlantillaIf plantilla = getPlantillaByNombre(plantillas, "VACACIONES"); 
					if ( plantilla == null )
						throw new GenericBusinessException("Plantilla que contenga VACACIONES no encontrada");
				StringBuilder sb = new StringBuilder("");
				CuentaIf cuenta = buscarCuenta(dato.getContratId(), plantilla, mapaContratoDepartamento,sb);
				
				//Se crea el detalle de nomina
				String glosa = "CANCELACIÓN "+tipoRol.toString()+" "+dato.getNombreEmpleado();
				creacionAsientoDetalles(rolPagoIf, asientoDetalleColeccion,dato.getTotal()
					, cuenta, plantilla,dato.getNombreEmpleado(),glosa);
				
				//Se crea el detalle de banco
				creacionAsientoDetalleBanco(rolPagoIf,mapCuentaDeCuentaBancaria, 
						plantillas, dato.getTotal(),dato.getCuentaBancariaId(),asientoDetalleColeccion,dato.getNumeroCheque(),glosa);
				
				
				registrarChequeEmitido(fechaPago,
					mapaChequesEmitidos,rolPagoIf.getId(), dato.getNumeroCheque(), dato.getCuentaBancariaId()
					,glosa, utilitariosLocal.redondeoValor( new BigDecimal(dato.getTotal()) ), dato.getNombreEmpleado());
				
				procesarAsiento(asientosRetornar,asientosGeneradosDeCheques, asiento,asientoDetalleColeccion, null);
			}

			return asientosRetornar;

		} else
			throw new GenericBusinessException("El Tipo de Rol no tiene Documento asociado, sirve para la creación de Asientos");
	}
	
	
	
	//------------------------------------------------------------------------------------------------
	
	private void creacionAsientoIess(RolPagoIf rolPagoIf,List<AsientoDetalleIf> asientoDetalleColeccion,TipoRol tipoRol, Collection<PlantillaIf> plantillas,Double totalProvision) throws GenericBusinessException{
		PlantillaIf plantillaProvision = getPlantillaByNombre(plantillas, "TOTAL","IESS" );
		if ( plantillaProvision == null )
			throw new GenericBusinessException("Plantilla de "+tipoRol+" no encontrada, verificar nombre !!");
		CuentaIf cuenta = cuentaLocal.getCuenta(plantillaProvision.getCuentaId());
		creacionAsientoDetalles(
				rolPagoIf, asientoDetalleColeccion,totalProvision, cuenta, plantillaProvision,"",cuenta.getNombre());
	}
	
	private void creacionAsientoProvision(RolPagoIf rolPagoIf,List<AsientoDetalleIf> asientoDetalleColeccion,TipoRol tipoRol, Collection<PlantillaIf> plantillas,Double totalProvision) throws GenericBusinessException{
		PlantillaIf plantillaProvision = getPlantillaProvision(tipoRol, plantillas);
		if ( plantillaProvision == null )
			throw new GenericBusinessException("Plantilla para provisión de "+tipoRol+" no encontrada, verificar nombre !!");
		CuentaIf cuenta = cuentaLocal.getCuenta(plantillaProvision.getCuentaId());
		creacionAsientoDetalles(
				rolPagoIf, asientoDetalleColeccion,totalProvision, cuenta, plantillaProvision,"",cuenta.getNombre());
	}
	
	private PlantillaIf getPlantillaProvision(TipoRol tipoRol, Collection<PlantillaIf> plantillas) throws GenericBusinessException{
		PlantillaIf plantillaIf = null;
		if ( tipoRol == TipoRol.DECIMO_TERCERO ){
			plantillaIf = getPlantillaByNombre(plantillas,false, "PROV","DECIMO","TERCERO");
		}else if ( tipoRol == TipoRol.DECIMO_CUARTO ){
			plantillaIf = getPlantillaByNombre(plantillas,false, "PROV","DECIMO","CUARTO");
		}else if ( tipoRol == TipoRol.APORTE_PATRONAL ){
			plantillaIf = getPlantillaByNombre(plantillas,false, "PROV","APORTE","PATRONAL");
		}else if ( tipoRol == TipoRol.FONDO_RESERVA ){
			plantillaIf = getPlantillaByNombre(plantillas,false, "PROV","FONDO","RESERVA");
		}else if ( tipoRol == TipoRol.VACACIONES ){
			plantillaIf = getPlantillaByNombre(plantillas,false, "PROV","VACACIONES");
		}else
			throw new GenericBusinessException("Búsqueda de plantilla para "+tipoRol+" no implementado !!");
		
		if ( plantillaIf == null ){
			if ( tipoRol == TipoRol.DECIMO_TERCERO ){
				plantillaIf = getPlantillaByNombre(plantillas, "TOTAL","DECIMO","TERCERO");
			}else if ( tipoRol == TipoRol.DECIMO_CUARTO ){
				plantillaIf = getPlantillaByNombre(plantillas, "TOTAL","DECIMO","CUARTO");
			}else if ( tipoRol == TipoRol.APORTE_PATRONAL ){
				plantillaIf = getPlantillaByNombre(plantillas, "TOTAL","APORTE","PATRONAL");
			}else if ( tipoRol == TipoRol.FONDO_RESERVA ){
				plantillaIf = getPlantillaByNombre(plantillas, "TOTAL","FONDO","RESERVA");
			}else if ( tipoRol == TipoRol.VACACIONES ){
				plantillaIf = getPlantillaByNombre(plantillas, "TOTAL","VACACIONES");
			}
		}
		
		return plantillaIf;
	}
	
	private CuentaIf buscarCuenta(Long contratoId,PlantillaIf plantilla,Map<Long,DepartamentoIf> mapaContratoDepartamento,StringBuilder sb) throws GenericBusinessException{
		CuentaIf cuentaTmp = cuentaLocal.getCuenta(plantilla.getCuentaId());
		
		if ( "N".equals(cuentaTmp.getImputable()) ){
			DepartamentoIf departamento = verificarDepartamentoContrato(mapaContratoDepartamento, contratoId);
			
			if ( sb != null )
				sb.append("DEPARTAMENTO "+departamento.getNombre());
			
			Map<String,Object> mapa = new HashMap<String, Object>();			
			//mapa.put("tipoEntidad", "A");	//OJO CON ESTE CAMPO, SI SE CAMBIA EL TIPO DE ENTIDAD PARA DEPARTAMENTO EN PANEL CUENTA ENTIDAD
			mapa.put("tipoEntidad", TipoEntidadEnum.DEPARTAMENTO.getLetra() );	
			mapa.put("entidadId", departamento.getId());
			mapa.put("nemonico", plantilla.getNemonico());
			
			ContratoIf contrato = verificarContrato(mapaContrato, contratoId);
			EmpleadoIf empleado = verificarEmpleado(mapaEmpleado, contrato.getEmpleadoId());
			mapa.put("oficinaId", empleado.getOficinaId());
			
			Collection<CuentaEntidadIf> cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapa);
			
			//SI NO HAY RESULTADOS CON LA OFICINA ELEGIDA SE REMUEVE LA OFICINA PARA VOLVER A BUSCAR
			if ( cuentasEntidades.size() == 0 ) {
				mapa.remove("oficinaId");
				cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapa);
			}
			
			if ( cuentasEntidades.size() == 0 ) {
				mapa.put("tipoEntidad", TipoEntidadEnum.EMPLEADO.getLetra());
				mapa.put("entidadId", empleado.getId());
				cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapa);
				
				if (cuentasEntidades.size() == 0) {
					throw new GenericBusinessException("No existe Cuenta para "+plantilla.getNemonico()+" relacionada con Departamento "+departamento.getNombre()+"\n"+
					"No existe Cuenta para "+plantilla.getNemonico()+" relacionada con Empleado "+empleado.getNombres()+" "+empleado.getApellidos());
				} else if ( cuentasEntidades.size() > 1 )
					throw new GenericBusinessException("Existe mas de una Cuenta para "+plantilla.getNemonico()+" con Empleado "+empleado.getNombres()+" "+empleado.getApellidos());
			
			} else if ( cuentasEntidades.size() > 1 )
				throw new GenericBusinessException("Existe mas de una Cuenta para "+plantilla.getNemonico()+" con Departamento "+departamento.getNombre());
			
			CuentaEntidadIf cuentaEntidad = cuentasEntidades.iterator().next();
			Long cuentaId = cuentaEntidad.getCuentaId();
			
			return  cuentaLocal.getCuenta(cuentaId);
		
		} else{
			return  cuentaLocal.getCuenta(plantilla.getCuentaId());
		}
	}

	private PeriodoIf buscarPeriodo(Long empresaId, java.sql.Date fechaPago)
			throws GenericBusinessException {
		//Map<String, Object> mapa = new HashMap<String, Object>();
		//mapa.put("empresaId", empresaId);
		//mapa.put("status", "A");	
		//Collection<PeriodoIf> periodos = periodoLocal.findPeriodoByQuery(mapa);
		/*int mes = Integer.valueOf(fechaPago.getMonth())-1;
		int anio = Integer.valueOf(fechaPago.getYear() + 1900);
		Calendar calendar = new GregorianCalendar(anio,mes,1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));*/
		//Date fechaAsiento = new Date(calendar.getTime().getTime());
		Date fechaAsiento = fechaPago;
		Collection<PeriodoIf> periodos = periodoLocal.findPeriodoForAsientoAutomatico(empresaId, fechaAsiento);
		if ( periodos.size() == 0 )
			throw new GenericBusinessException("No existe período contable activo para asiento con fecha " + utilitariosLocal.getFechaCortaUppercase(utilitariosLocal.fromSqlDateToUtilDate(fechaAsiento)));
		PeriodoIf periodo = periodos.iterator().next();
		return periodo;
	}

	private PlanCuentaIf buscarPlanCuenta(Long empresaId,
			Map<String, Object> mapa, EventoContableIf eventoContable)
			throws GenericBusinessException {
		mapa.clear();
		mapa.put("empresaId", empresaId);
		mapa.put("id", eventoContable.getPlanCuentaId());
		Collection<PlanCuentaIf> planes = planCuentaLocal.findPlanCuentaByQuery(mapa);
		if ( planes.size() == 0 )
			throw new GenericBusinessException("No existe Plan de Cuenta para Evento Contable "+eventoContable.getNombre());
		PlanCuentaIf planCuenta = planes.iterator().next();
		if ( !"A".equals(planCuenta.getEstado()) )
			throw new GenericBusinessException("Plan de Cuenta no esta Activo");
		return planCuenta;
	}

	private EventoContableIf buscarEventoContable(Long empresaId,
			DocumentoIf documento, Map<String, Object> mapa)
			throws GenericBusinessException {
		mapa.put("empresaId", empresaId);
		mapa.put("documentoId", documento.getId());
		Collection<EventoContableIf> eventos = eventoContableLocal.findEventoContableByQuery(mapa);
		if ( eventos.size() == 0 )
			throw new GenericBusinessException("No existe Evento Contable para documento "+documento.getNombre());
		else if ( eventos.size() > 1 )
			throw new GenericBusinessException("Existe mas de un Evento Contable para documento "+documento.getNombre());
		EventoContableIf eventoContable = eventos.iterator().next();
		return eventoContable;
	}
	
	private void creacionAsientoDetalleBanco(RolPagoIf rolPagoIf,
			Map<Long, Long> mapCuentaDeCuentaBancaria,
			Collection<PlantillaIf> plantillas,
			Double total,Long cuentaBancariaId,
			List<AsientoDetalleIf> asientoDetalleColeccion,String referencia, String glosa)
			throws GenericBusinessException {
		PlantillaIf plantilla = getPlantillaByNombre(plantillas,"BANCO");
		Long cuentaId = getCuentaDeCuentaBancaria(mapCuentaDeCuentaBancaria, cuentaBancariaId);
		CuentaIf cuenta = cuentaLocal.getCuenta(cuentaId);
		creacionAsientoDetalles(
				rolPagoIf, asientoDetalleColeccion,total, cuenta, plantilla,referencia,glosa);
	}
	
	private void creacionAsientoDetallePuente(RolPagoIf rolPagoIf,
			Map<Long,DepartamentoIf> mapaContratoDepartamento,
			Collection<PlantillaIf> plantillas,
			Double total,
			List<AsientoDetalleIf> asientoDetalleColeccion,String referencia, String glosa)
			throws GenericBusinessException {
		PlantillaIf plantilla = getPlantillaByNombre(plantillas,"PUENTE");
		//CuentaIf cuenta = cuentaLocal.getCuenta(cuentaId);
		CuentaIf cuenta = buscarCuenta(null,plantilla,mapaContratoDepartamento,null);
		creacionAsientoDetalles(
				rolPagoIf, asientoDetalleColeccion,total, cuenta, plantilla,referencia,glosa);
	}
	
	private Long getCuentaDeCuentaBancaria(Map<Long, Long> mapCuentaDeCuentaBancaria,Long cuentaBancariaId) throws GenericBusinessException{
		Long cuentaId = mapCuentaDeCuentaBancaria.get(cuentaBancariaId);
		if ( cuentaId==null ){
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("entidadId", cuentaBancariaId);
			mapa.put("nemonico", "BANCO");
			mapa.put("tipoEntidad", "B");
			Collection<CuentaEntidadIf> cuentasEntidades = cuentaEntidadLocal.findCuentaEntidadByQuery(mapa);
			if ( cuentasEntidades.size()==0 ){
				CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal.getCuentaBancaria(cuentaBancariaId);
				throw new GenericBusinessException("No existe Cuenta relacionada con cuenta Bancaria "+cuentaBancaria.getNumeroCheque()+" !!");
			} else if (cuentasEntidades.size()>1){
				CuentaBancariaIf cuentaBancaria = cuentaBancariaLocal.getCuentaBancaria(cuentaBancariaId);
				throw new GenericBusinessException("Cuenta Bancaria "+cuentaBancaria.getNumeroCheque()+" tiene mas de una cuenta Relacionada !!");
			}
			CuentaEntidadIf ce = cuentasEntidades.iterator().next();
			cuentaId = ce.getCuentaId();
		}
		return cuentaId;
	}
	
	private void creacionAsientoNomina(RolPagoIf rolPagoIf,
			Collection<PlantillaIf> plantillas,
			Map<String, Double> mapaTotales,
			List<AsientoDetalleIf> asientoDetalleColeccion
			,String referencia, String glosa)
			throws GenericBusinessException {
		Double totalIngresos = mapaTotales.get("totalIngresos");
		Double totalEgresos = mapaTotales.get("totalEgresos");
		Double totalNomina = totalIngresos-totalEgresos;
		PlantillaIf plantilla = getPlantillaByNombre(plantillas,"NOMINA");
		CuentaIf cuenta = cuentaLocal.getCuenta(plantilla.getCuentaId());
		creacionAsientoDetalles(
				rolPagoIf, asientoDetalleColeccion,totalNomina, cuenta, plantilla,referencia,glosa);
	}

	private void sumarIngresosEgresos(TipoRolIf tipoRolIf,
			Map<String, Double> mapaTotales, RolPagoDetalleIf rolPagoDetalleIf,
			RubroIf rubroIf,Double valorExtra,Double total) throws GenericBusinessException {
		
		OperacionNomina operacion = UtilesNomina.getIngresoEgreso(tipoRolIf, rubroIf);
		
		if ( operacion == OperacionNomina.INGRESO ){
			Double valor = mapaTotales.get("totalIngresos");
			if ( total == null )
				valor += utilitariosLocal.redondeoValor( (rolPagoDetalleIf.getValor().doubleValue() + valorExtra) );
			else 
				valor += utilitariosLocal.redondeoValor( total ); 
			valor = utilitariosLocal.redondeoValor( valor );
			mapaTotales.put("totalIngresos",valor);
		} else {
			Double valor = mapaTotales.get("totalEgresos");
			if ( total == null )
				valor += utilitariosLocal.redondeoValor( (rolPagoDetalleIf.getValor().doubleValue() + valorExtra) );
			else 
				valor += utilitariosLocal.redondeoValor( total );
			valor = utilitariosLocal.redondeoValor( valor );
			mapaTotales.put("totalEgresos",valor);
		}
			
	}
	
	private AsientoDetalleIf creacionAsientoDetalles(RolPagoIf rolPagoIf,
			List<AsientoDetalleIf> asientoDetalleColeccion,
			double valorDetalle, CuentaIf cuenta,
			PlantillaIf plantilla,String referencia,String glosa) throws GenericBusinessException {
		if (cuenta != null) {
			AsientoDetalleIf asientoDetalle = new AsientoDetalleData();
			asientoDetalle.setCuentaId(cuenta.getId());
			String mesAnio = getMesAnio(rolPagoIf);
			asientoDetalle.setReferencia(referencia);
			asientoDetalle.setGlosa(glosa+" "+mesAnio);
			//asientoDetalle.setCentrogastoId();
			//asientoDetalle.setEmpleadoId(empleado.getId());
			//asientoDetalle.setDepartamentoId(empleado.getDepartamentoId());
			//asientoDetalle.setLineaId();
			//asientoDetalle.setClienteId(cliente.getId());
			
			double valor = utilitariosLocal.redondeoValor(valorDetalle);
			if (plantilla.getDebehaber().equals("D")) {
				asientoDetalle.setDebe(BigDecimal.valueOf(valor));
				asientoDetalle.setHaber(BigDecimal.ZERO);
			} else if (plantilla.getDebehaber().equals("H")) {
				asientoDetalle.setHaber(BigDecimal.valueOf(valor));
				asientoDetalle.setDebe(BigDecimal.ZERO);
			}
			//if (valor > 0.00){
				asientoDetalleColeccion.add(asientoDetalle);
				return asientoDetalle;
			//}
			//return null;
		} else 
			throw new GenericBusinessException("No se encontró Cuenta para plantilla con nemónico "+plantilla.getNemonico());
	}
		
	private Collection<RolPagoDetalleIf> buscarRolPagoDetalle(RolPagoIf rolPago,Long contratoId,TipoRol tipoRol,RubroIf rubroDecimoTercero)
	throws GenericBusinessException {
		Map<String, Object> mapaDetalle = new HashMap<String, Object>();
		mapaDetalle.put("rolpagoId", rolPago.getId());
		mapaDetalle.put("contratoId", contratoId);
		Collection<RolPagoDetalleIf> rolPagoDetalleCollection = null;
		if ( tipoRol == TipoRol.DECIMO_TERCERO && rubroDecimoTercero != null){
			mapaDetalle.put("rubroId", rubroDecimoTercero.getId());
			rolPagoDetalleCollection = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaDetalle);
		}else if ( tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL )
			rolPagoDetalleCollection = rolPagoDetalleLocal.findRolPagoDetalleByQueryNormal(mapaDetalle);

		if ( rolPagoDetalleCollection== null || rolPagoDetalleCollection.size() == 0 )
			throw new GenericBusinessException("Rol de Pago no tiene detalle !!");
		return rolPagoDetalleCollection;
	}
	
	private RubroIf verificarRubro(Map<Long,RubroIf> mapa,RolPagoDetalleIf rolPagoDetalleIf) throws GenericBusinessException{
		Long rubroId = rolPagoDetalleIf.getRubroId();
		if ( rubroId == null ){
			RubroEventualIf rubroEventual = rubroEventualLocal.getRubroEventual(rolPagoDetalleIf.getRubroEventualId());
			rubroId = rubroEventual.getRubroId();
		}
		return verificarRubroEnMapa(mapa, rubroId);
	}

	private RubroIf verificarRubroEnMapa(Map<Long, RubroIf> mapa, Long rubroId)
			throws GenericBusinessException {
		if ( !mapa.containsKey(rubroId) ){
			RubroIf rubroIf = rubroLocal.getRubro(rubroId);
			mapa.put(rubroId, rubroIf);
			return rubroIf;
		} else
			return mapa.get(rubroId);
	}
		
	private PlantillaIf getPlantillaByRubro(RubroIf rubro,Collection<PlantillaIf> plantillas) throws GenericBusinessException{
		String nombre = rubro.getNombre().trim();
		String tipoRubro = rubro.getTipoRubro();
		String nemonico = rubro.getNemonico();
		
		if ( nemonico!=null && !"".equals(nemonico.trim()) ){
			return getPlantillaByNemonico(plantillas,rubro);
		}
		
		//ESTA CONDICION ES PORQUE PUEDE SER RUBRO EVENTUAL, QUE IGUAL ES UN DESCUENTO Y SE VA A LA CUENTA EMPLEADO
		if ( TipoRubro.EVENTUAL.getLetra().equals(tipoRubro) ){	
			/*if ( nemonico!=null && !"".equals(nemonico.trim()) ){
				return getPlantillaByNombre(plantillas,nemonico);
			}*/
			return getPlantillaByNombre(plantillas,"DESCUENTO");
		}
		
		//ESTA CONDICION ES PORQUE PUEDE SER RUBRO COMISION
		if ( TipoRubro.COMISION.getLetra().equals(tipoRubro) ){	
			/*if ( nemonico!=null && !"".equals(nemonico.trim()) ){
				return getPlantillaByNombre(plantillas,nemonico);
			}*/
			return getPlantillaByNombre(plantillas,"COMISION");
		}
		
		if ( "M".equals(rubro.getFrecuencia()) ){ //MENSUAL
			if ( nemonico!=null && !"".equals(nemonico.trim()) )
				return getPlantillaByNemonico(plantillas, rubro);
			
			//if ( "S".equals(tipoRubro) )
			if ( TipoRubro.SUELDO.getLetra().equals(tipoRubro) ) //SUELDOS
				return getPlantillaByNombre(plantillas,"SUELDO");
			/*else if ( TipoRubro.OTROS_INGRESOS.getLetra().equals(tipoRubro) ){
				return getPlantillaByNombre(plantillas,"SUELDO");
			}*/
			//este if debe estar antes del if ( TipoRubro.BENEFICIO.getLetra().equals(tipoRubro) )
			//porque sino entraría en el if equivocado.
			else if ( rubro.getCodigo().equals("OI") ){
				return getPlantillaByNombre(plantillas,"SUELDO");
			}
			else if ( TipoRubro.BENEFICIO.getLetra().equals(tipoRubro) ){
				return getPlantillaByNombre(plantillas,"BONO");
			//} else if ( "D".equals(tipoRubro) || "A".equals(tipoRubro) ){
			} else if ( TipoRubro.DESCUENTO.getLetra().equals(tipoRubro) || 
					    TipoRubro.ANTICIPO.getLetra().equals(tipoRubro) ){ //DECUENTOS O ANTICIPOS
				if ( nombre.contains("APORTE") && nombre.contains("IESS") && nombre.contains("PERSONAL") )
					return getPlantillaByNombre(plantillas,new String[]{"APORTE","IESS","PERSONAL"}); 
				else if ( nombre.contains("IMP") && nombre.contains("RENTA") )
					return getPlantillaByNombre(plantillas,new String[]{"IMP","RENTA","EMPLEADO"});
				else 
					return getPlantillaByNombre(plantillas,"DESCUENTO");
			//} else if ( "P".equals(tipoRubro) ){
			} else if ( TipoRubro.PROVISION.getLetra().equals(tipoRubro) ){ //PROVISION
				if ( nombre.contains("DECIMO") && nombre.contains("TERCERO")){
					return getPlantillaByNombre(plantillas,"DECIMO","TERCERO");
				} else if (nombre.contains("DECIMO") && nombre.contains("CUARTO")){
					return getPlantillaByNombre(plantillas,"DECIMO","CUARTO");
				} else if (nombre.contains("FONDO") && nombre.contains("RESERVA")){
					return getPlantillaByNombre(plantillas,"FONDO","RESERVA");
				} else if (nombre.contains("APORTE") && nombre.contains("PATRONAL")){
					return getPlantillaByNombre(plantillas,"APORTE","PATRONAL");
				} else if ( nombre.contains("VACACIONES") ){
					return getPlantillaByNombre(plantillas,"VACACIONES");
				} else 
					throw new GenericBusinessException("Rubro "+nombre+" de Provision no considerado para creación de Asiento");
			}
		} else if ( "Q".equals(rubro.getFrecuencia()) ){ //QUINCENAL
			return getPlantillaByNombre(plantillas,"DESCUENTO");
		}
		throw new GenericBusinessException("Solo se verifica Rubros Mensuales y Quincenales y el Rubro "+nombre+" no lo es !!");
	}
	
	private PlantillaIf getPlantillaByNombre(Collection<PlantillaIf> plantillas,String... nombre) throws GenericBusinessException{
		for ( PlantillaIf plantilla: plantillas ){
			String nemonico = plantilla.getNemonico();
			if (nombre.length==1 && nemonico.contains(nombre[0].toUpperCase()) )
				return plantilla;
			else if( nombre.length==2 && nemonico.contains(nombre[0]) && nemonico.contains(nombre[1].toUpperCase()) )
				return plantilla;
			else if( nombre.length==3 && nemonico.contains(nombre[0]) 
					&& nemonico.contains(nombre[1].toUpperCase()) && nemonico.contains(nombre[2].toUpperCase()))
				return plantilla;
		}
		StringBuilder sb =  new StringBuilder();
		for ( String n: nombre )
			sb.append(n+",");
		sb.replace(sb.length()-1, sb.length(), "");
		throw new GenericBusinessException("No se encontró nemónico con palabra(s) "+sb.toString());
	}
	
	private PlantillaIf getPlantillaByNombre(Collection<PlantillaIf> plantillas,boolean mostrarError,String... nombre) throws GenericBusinessException{
		if ( mostrarError )
			return getPlantillaByNombre(plantillas, nombre);
		else {
			try{
				return getPlantillaByNombre(plantillas, nombre);
			} catch(Exception e){
				return null;
			}
		}
	}

	private PlantillaIf getPlantillaByNemonico(Collection<PlantillaIf> plantillas,RubroIf rubroIf) throws GenericBusinessException{
		String nemonicoRubro = rubroIf.getNemonico();
		for ( PlantillaIf plantilla: plantillas ){
			String nemonicoPlantilla = plantilla.getNemonico();
			if ( nemonicoRubro.equals(nemonicoPlantilla) ){
				return plantilla;
			}
		}
		throw new GenericBusinessException("Nemónico en Rubro "+rubroIf.getNombre()+" no corresponde a ningún Rubro en las plantillas !!");
	}
	
	private DepartamentoIf verificarDepartamentoContrato(Map<Long,DepartamentoIf> mapaContratoDepartamento,Long contratoId) throws GenericBusinessException{
		if ( !mapaContratoDepartamento.containsKey(contratoId) ){
			ContratoIf contrato = contratoLocal.getContrato(contratoId);
			EmpleadoIf empleado = empleadoLocal.getEmpleado(contrato.getEmpleadoId());
			DepartamentoIf departamento = departamentoLocal.getDepartamento(empleado.getDepartamentoId());
			if ( departamento == null )
				throw new GenericBusinessException("Empleado \""+empleado.getNombres()+" "+empleado.getApellidos()+"\", no tiene departamento asignado !!");
			mapaContratoDepartamento.put(contratoId, departamento);
			return departamento;
		} else {
			return mapaContratoDepartamento.get(contratoId);
		}
	}

	private Collection<PlantillaIf> verificarPlantilla(Map<Long, Collection<PlantillaIf>> mapa,Long eventoContableId,Long planCuentaId ) throws GenericBusinessException{
		if ( !mapa.containsKey(eventoContableId) ){
			Collection<PlantillaIf> plantillas = plantillaLocal.findPlantillaByEventoContableIdAndPlanCuentaId(eventoContableId, planCuentaId);
			mapa.put(eventoContableId, plantillas);
			return plantillas;
		} else {
			return mapa.get(eventoContableId);
		}
	}

	private AsientoIf registrarAsiento(Date fechaPago,RolPagoIf rolPago,Long empresaId,PlanCuentaIf planCuenta
			,PeriodoIf periodo,Long oficinaId,TipoDocumentoIf tipoDocumento,String observacion
			,boolean usarAsientoExistente,String baseObservacion, boolean usarFechaActual, EventoContableIf eventoContable) throws GenericBusinessException {
		try {			
			AsientoIf data = new AsientoData();
			String strEstado = "P";	
			Date fechaAsiento = usarFechaActual? fechaPago : rolPago.getFecha();
			String unNumeroAsiento = getNumeroAsiento(fechaAsiento,empresaId,planCuenta);
			data.setEmpresaId(empresaId);
			data.setNumero(unNumeroAsiento);
			data.setStatus(strEstado);
			data.setPeriodoId(periodo.getId());
			data.setPlancuentaId(planCuenta.getId());
			data.setObservacion( observacion);
			data.setOficinaId(oficinaId);
			data.setFecha(fechaAsiento);
			data.setTipoDocumentoId(tipoDocumento.getId());
			data.setTransaccionId(rolPago.getId());
			data.setEfectivo("S");
			data.setSubtipoasientoId(eventoContable.getSubtipoAsientoId());
			return data;
		
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error en el registro del asiento");
		}
	}

	private String getMesAnio(RolPagoIf rolPago ) throws GenericBusinessException{
		return  utilitariosLocal.getNombreMes(rolPago.getMes())+"/"+rolPago.getAnio();
	}

	private String getNumeroAsiento(Date fechaAsiento,Long empresaId,PlanCuentaIf planCuenta) throws GenericBusinessException {
		// "CREA-PC01-20070912-1012-013"
		// CODIGO_EMPRESA - CODIGO_PLAN_CUENTA - CODIGO_PERIODO - MES_AÑO - NUMERO_ASIENTO
		try {
			EmpresaIf empresa = empresaLocal.getEmpresa(empresaId);
			//PeriodoIf periodo = (PeriodoIf) getCmbPeriodo().getSelectedItem();
			String mesAsiento = utilitariosLocal.getMonthFromDate(fechaAsiento);
			String anioAsiento = utilitariosLocal.getYearFromDate(fechaAsiento);
			String codigo = empresa.getCodigo() + "-";
			codigo += planCuenta.getCodigo() + "-";
			codigo += mesAsiento + "-";
			codigo += anioAsiento + "-";
			return codigo;
		} catch (GenericBusinessException e1) {
			e1.printStackTrace();
			throw new GenericBusinessException("Error al generar N\u00famero de Asiento");
		}
	}

	/*private boolean validateAsientoAutomatico(Long empresaId) throws GenericBusinessException {
		try {
			Map filtroMap = new HashMap();
			filtroMap.put("empresaId", empresaId);
			filtroMap.put("estado", "A");
			Iterator planCuentaIterator = planCuentaLocal.findPlanCuentaByQuery(filtroMap).iterator();

			if (planCuentaIterator.hasNext())
				planCuenta = (PlanCuentaIf) planCuentaIterator.next();
			else {
				//return false;
				throw new GenericBusinessException("No existe un plan de cuentas activo.\nSe registr\u00f3 la compra pero no se gener\u00f3 el asiento contable.");
			}

			filtroMap.clear();
			filtroMap.put("empresaId", empresaId);
			filtroMap.put("status", "A");	
			Iterator periodoIterator = periodoLocal.findPeriodoByQuery(filtroMap).iterator();

			if (periodoIterator.hasNext())
				periodo = (PeriodoIf) periodoIterator.next();
			else {
				//return false;
				throw new GenericBusinessException("No existe un período activo.\nSe registr\u00f3 la compra pero no se gener\u00f3 el asiento contable.");
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException("Se ha producido un error al validar el Asiento");
		}

		return true;
	}*/
	
}