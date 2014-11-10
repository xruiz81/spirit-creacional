package com.spirit.nomina.session;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.spirit.contabilidad.entity.AsientoDetalleIf;
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.ChequeEmitidoIf;
import com.spirit.contabilidad.handler.ChequeDatos;
import com.spirit.contabilidad.session.AsientoDetalleSessionLocal;
import com.spirit.contabilidad.session.ChequeEmitidoSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.CuentaBancariaIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.OficinaIf;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoEmpleadoIf;
import com.spirit.general.entity.TipoPagoIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.EmpleadoSessionLocal;
import com.spirit.general.session.FileManagerSessionLocal;
import com.spirit.general.session.OficinaSessionLocal;
import com.spirit.general.session.ParametroEmpresaSessionLocal;
import com.spirit.general.session.TipoEmpleadoSessionLocal;
import com.spirit.general.session.TipoParametroSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.mailer.session.MailMessage;
import com.spirit.mailer.session.MailerException;
import com.spirit.mailer.session.MailerImpl;
import com.spirit.nomina.entity.ContratoGastoDeducibleIf;
import com.spirit.nomina.entity.ContratoIf;
import com.spirit.nomina.entity.ContratoRubroEJB;
import com.spirit.nomina.entity.ContratoRubroIf;
import com.spirit.nomina.entity.ContratoUtilidadIf;
import com.spirit.nomina.entity.ImpuestoRentaPersNatIf;
import com.spirit.nomina.entity.RolPagoData;
import com.spirit.nomina.entity.RolPagoDetalleData;
import com.spirit.nomina.entity.RolPagoDetalleEJB;
import com.spirit.nomina.entity.RolPagoDetalleIf;
import com.spirit.nomina.entity.RolPagoDetalleUtilidadData;
import com.spirit.nomina.entity.RolPagoDetalleUtilidadIf;
import com.spirit.nomina.entity.RolPagoEJB;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.RubroEventualIf;
import com.spirit.nomina.entity.RubroIf;
import com.spirit.nomina.entity.SalarioMinimoVitalIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.AsientoGenerado;
import com.spirit.nomina.handler.DatoAsientoMensual;
import com.spirit.nomina.handler.DatoAsientoPagoRol;
import com.spirit.nomina.handler.DatoAsientoRubroEventual;
import com.spirit.nomina.handler.DatoMulta;
import com.spirit.nomina.handler.DatoObservacion;
import com.spirit.nomina.handler.DatoRubroEventual;
import com.spirit.nomina.handler.EstadoRolPago;
import com.spirit.nomina.handler.EstadoRolPagoDetalle;
import com.spirit.nomina.handler.EstadoRubroEventual;
import com.spirit.nomina.handler.ModoOperacionRubro;
import com.spirit.nomina.handler.NominaParametros;
import com.spirit.nomina.handler.NominaUtilesLocal;
import com.spirit.nomina.handler.OperacionNomina;
import com.spirit.nomina.handler.RolPagoAsientoAutomaticoHandlerLocal;
import com.spirit.nomina.handler.TipoRol;
import com.spirit.nomina.handler.TipoRolFormaPago;
import com.spirit.nomina.handler.TipoRubro;
import com.spirit.nomina.handler.UtilesNomina;
import com.spirit.nomina.session.generated._RolPagoSession;
import com.spirit.rrhh.entity.EmpleadoFamiliaresIf;
import com.spirit.rrhh.entity.EmpleadoPersonalIf;
import com.spirit.rrhh.handler.ParentezcoEnum;
import com.spirit.rrhh.session.EmpleadoFamiliaresSessionLocal;
import com.spirit.rrhh.session.EmpleadoPersonalSessionLocal;
import com.spirit.server.LogService;
import com.spirit.server.Logger;
import com.spirit.server.QueryBuilder;
import com.spirit.util.FileInputStreamSerializable;

/**
 * The <code>RolPagoSession</code> session bean, which acts as a facade to the
 * underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.7 $, $Date: 2014/07/14 23:20:54 $
 * 
 */
@Stateless
public class RolPagoSessionEJB extends _RolPagoSession implements
		RolPagoSessionRemote {
	
	

	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@Resource
	private SessionContext ctx;

	@EJB private RolPagoDetalleSessionLocal rolPagoDetalleLocal;

	@EJB private TipoRolSessionLocal tipoRolLocal;

	@EJB private ContratoSessionLocal contratoLocal;

	@EJB private ContratoRubroSessionLocal contratoRubroLocal;

	@EJB private RubroSessionLocal rubroLocal;

	@EJB private RubroEventualSessionLocal rubroEventualLocal;

	@EJB private RolPagoAsientoAutomaticoHandlerLocal rolPagoAsientoAutomaticoHandlerLocal;

	@EJB private AsientoDetalleSessionLocal asientoDetalleLocal;

	@EJB private ChequeEmitidoSessionLocal chequeEmitidoLocal;

	@EJB private ContratoGastoDeducibleSessionLocal contratoGastoDeducibleLocal;

	@EJB private ImpuestoRentaPersNatSessionLocal impuestoRentaLocal;

	@EJB private SalarioMinimoVitalSessionLocal salarioMinimoVitalLocal;
	
	@EJB private ContratoUtilidadSessionLocal contratoUtilidadLocal; 

	@EJB private RolPagoDetalleUtilidadSessionLocal rolPagoDetalleUtilidadLocal;
	
	@EJB private EmpleadoSessionLocal empleadoLocal;
	
	@EJB private EmpleadoPersonalSessionLocal empleadoPersonalLocal;
	
	@EJB private EmpleadoFamiliaresSessionLocal empleadoFamiliarLocal;
	
	@EJB private TipoEmpleadoSessionLocal tipoEmpleadoLocal;
	
	@EJB private TipoContratoSessionLocal tipoContratoLocal;
	
	@EJB private UtilitariosSessionLocal utilitariosLocal;

	@EJB private NominaUtilesLocal nominaUtilesLocal;
	
	@EJB private ParametroEmpresaSessionLocal parametroEmpresaLocal;
	
	@EJB private TipoParametroSessionLocal tipoParametroLocal;
	
	@EJB private FileManagerSessionLocal fileManagerLocal;
	
	@EJB private OficinaSessionLocal oficinaLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService.getLogger(RolPagoSessionEJB.class);
	
	//INI_CAMBIO_20140618 Se inicializa variable a 30 dias para estadarizar fechas en rol de pago 
	private static final Integer diaFinMesRolPago=30;
	//FIN_CAMBIO_20140618

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 **************************************************************************/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Map<String,Object> procesarRolPago(RolPagoIf rolPago,
			TipoContratoIf tipoContratoIf, ContratoIf contratoIf, Long empresaId)
			throws GenericBusinessException {
		Map<String,Object> mapaResultado = new HashMap<String, Object>();
		try {
			boolean tieneDetalle = false;

			Calendar gcUltimoDiaMesRolPago = new GregorianCalendar();
			gcUltimoDiaMesRolPago.getTime();
			int mesRol = Integer.valueOf(rolPago.getMes()) - 1;
			//gcUltimoDiaMesRolPago.set(Calendar.MONTH, 1 );
			int anioRol = Integer.valueOf(rolPago.getAnio());
			//gcUltimoDiaMesRolPago.set(Calendar.YEAR, anioRol );
			gcUltimoDiaMesRolPago.set(anioRol, mesRol, 1);
			Integer mesRolPago = gcUltimoDiaMesRolPago.get(GregorianCalendar.MONTH);
			Integer anioRolPago = gcUltimoDiaMesRolPago.get(GregorianCalendar.YEAR);
			
			//INI_CAMBIO_20140618 Se comenta por estandarizacion de fin de mes a 30 dias en rol de pago 
			//Integer diaFinMesRolPago = gcUltimoDiaMesRolPago.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
			gcUltimoDiaMesRolPago.set(GregorianCalendar.DAY_OF_MONTH,diaFinMesRolPago);
			//FIN_CAMBIO_20140618 
			
			Date fechaRolPago = new Date(gcUltimoDiaMesRolPago.getTime().getTime());

			GregorianCalendar gcPrimerDiaMesRolPago = new GregorianCalendar();
			gcPrimerDiaMesRolPago.setTimeInMillis(gcUltimoDiaMesRolPago.getTimeInMillis());
			gcPrimerDiaMesRolPago.set(Calendar.DATE, 1);

			Date fechaPrimerDiaRolPago = new Date(gcPrimerDiaMesRolPago.getTimeInMillis());

			GregorianCalendar gce = new GregorianCalendar();
			gce.set(GregorianCalendar.MONTH,Integer.valueOf(rolPago.getMes()) - 1);
			gce.set(GregorianCalendar.YEAR, Integer.valueOf(rolPago.getAnio()));
			gce.set(GregorianCalendar.DAY_OF_MONTH, 1);
			//Date fechaEventual = new Date(gce.getTime().getTime());

			Collection<TipoRolIf> tiposRolPago = tipoRolLocal.getTipoRolList();
			
			TipoRolIf tipoRolIf = null;
			for (TipoRolIf tipoRolI : tiposRolPago) {
				if (tipoRolI.getId().longValue() == rolPago.getTiporolId().longValue()) {
					tipoRolIf = tipoRolI;
				}
			}
			if (tipoRolIf == null)
				throw new GenericBusinessException("No existe Tipo de Rol seleccionado !!");

			TipoRol tipoRol = null;
			TipoRolIf tipoRolQuincenal = null; // Se lo obtiene cuando sea tipo
												// de rol Mensual
			TipoRolIf tipoRolMensual = null; // Se lo obtiene cuando sea de
												// tipo Decimo
			RubroIf rubroQuincenal = null;
			RubroIf rubroDecimoTercero = null;
			RubroIf rubroDecimoCuarto = null;
			RubroIf rubroFondoReserva = null;
			RubroIf rubroVacaciones = null;

			RubroIf rubroAportePatronal = null;
			RubroIf rubroIece = null;
			RubroIf rubroSecap = null;
			
			RubroIf rubroUtilidades = null;

			tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
			if (tipoRol == null) {
				throw new GenericBusinessException("Manejo de Tipo de Rol no implementado !!");
			} else if (tipoRol == TipoRol.MENSUAL) {
				tipoRolQuincenal = buscarTipoRolIf(tiposRolPago, "QUINCENA");
				rubroQuincenal = nominaUtilesLocal.getRubroByTipoRol(TipoRol.QUINCENAL);
			} else if (tipoRol == TipoRol.DECIMO_TERCERO) {
				tipoRolMensual = buscarTipoRolIf(tiposRolPago, "MENSUAL");
				rubroDecimoTercero = nominaUtilesLocal.getRubroByTipoRol(tipoRol);
				if (rubroDecimoTercero == null)
					throw new GenericBusinessException("No existe Rubro con nombre DECIMO TERCERO");
			} else if (tipoRol == TipoRol.VACACIONES) {
				tipoRolMensual = buscarTipoRolIf(tiposRolPago, "MENSUAL");
				rubroVacaciones = nominaUtilesLocal.getRubroByTipoRol(tipoRol);
				if (rubroVacaciones == null)
					throw new GenericBusinessException("No existe Rubro con nombre VACACIONES");
			} else if (tipoRol == TipoRol.DECIMO_CUARTO) {
				tipoRolMensual = buscarTipoRolIf(tiposRolPago, "MENSUAL");
				rubroDecimoCuarto = nominaUtilesLocal.getRubroByTipoRol(tipoRol);
				if (rubroDecimoCuarto == null)
					throw new GenericBusinessException("No existe Rubro con nombre DECIMO CUARTO");
			} else if (tipoRol == TipoRol.FONDO_RESERVA) {
				tipoRolMensual = buscarTipoRolIf(tiposRolPago, "MENSUAL");
				rubroFondoReserva = nominaUtilesLocal.getRubroByTipoRol(tipoRol);
				if (rubroFondoReserva == null)
					throw new GenericBusinessException("No existe Rubro con nombre FONDO DE RESERVA");
			} else if (tipoRol == TipoRol.APORTE_PATRONAL) {
				tipoRolMensual = buscarTipoRolIf(tiposRolPago, "MENSUAL");
				rubroAportePatronal = nominaUtilesLocal.getRubroByTipoRol(tipoRol);
				rubroIece = nominaUtilesLocal.buscarRubroUnicoByNombreByTipoRubro("%IECE%",TipoRubro.PROVISION.getLetra());
				rubroSecap = nominaUtilesLocal.buscarRubroUnicoByNombreByTipoRubro("%SECAP%",TipoRubro.PROVISION.getLetra());

				if (rubroAportePatronal == null)
					throw new GenericBusinessException("No existe Rubro con nombre APORTE PATRONAL");
			} else if ( tipoRol == TipoRol.UTILIDADES ){
				rubroUtilidades = nominaUtilesLocal.buscarRubroUnicoByNombreByTipoRubro("%UTILIDAD%",TipoRubro.UTILIDAD.getLetra());
			}

			boolean registrarImpuestoRentaCero = false;
			// TIPOS DE PARAMETROS DE NOMINA
			RubroIf rubroAportePersonalIess = null;
			TipoParametroIf tp = utilitariosLocal.getTipoParametro(empresaId,NominaParametros.TIPO_PARAMETRO);
			if (tp != null) {

				// BUSQUEDA DE RUBRO PARA APORTE PERSONAL DEL EMPLEADO AL IESS
				Collection<ParametroEmpresaIf> pes = utilitariosLocal.getParametroEmpresa(
					empresaId, tp, false,NominaParametros.CODIGO_APORTE_IESS_PERSONAL);
				if (pes.size() > 0) {
					if (pes.size() > 1)
						throw new GenericBusinessException("Existe mas de un Parametro de Empresa con codigo "+
							NominaParametros.CODIGO_APORTE_IESS_PERSONAL+" para indicar codigo de Aporte Personal al IESS !!");

					ParametroEmpresaIf pe = pes.iterator().next();

					Collection<RubroIf> rubrosIessPersonal = rubroLocal.findRubroByCodigo(pe.getValor());
					if (rubrosIessPersonal.size() == 0)
						throw new GenericBusinessException("No existe Rubro con codigo "+pe.getValor()+ " indicativo de Aporte Personal IESS!!");
					rubroAportePersonalIess = rubrosIessPersonal.iterator().next();
				}

				// BUSQUEDA DE OPCION PARA REGISTRAR O NO EN DETALLE DE ROL EL
				// IMPUESTO A LA RENTA CON VALOR CERO.
				pes = utilitariosLocal.getParametroEmpresa(empresaId,tp,false,
						NominaParametros.REGISTRAR_IMPUESTO_RENTA_CON_VALOR_CERO);
				if (pes.size() == 1) {
					ParametroEmpresaIf pe = pes.iterator().next();
					registrarImpuestoRentaCero = 
						(pe.getValor() != null && pe.getValor().trim().substring(0, 1).equalsIgnoreCase("s")) ? true : false;
				}
				
			}

			Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();

			// CREAR CABECERA
			Date fechaHoy = utilitariosLocal.getServerDateSql();
			Map<String, Object> mapaRolPago = new HashMap<String, Object>();
			mapaRolPago.put("mes", rolPago.getMes());
			mapaRolPago.put("anio", rolPago.getAnio());
			mapaRolPago.put("tiporolId", rolPago.getTiporolId());
			mapaRolPago.put("tipocontratoId", rolPago.getTipocontratoId());
			
			//Map<Long, BigDecimal> mapSB = new HashMap<Long, BigDecimal>();
			//Map<Long, BigDecimal> mapOI = new HashMap<Long, BigDecimal>();
			
			Collection<RolPagoIf> rolesPago = findRolPagoByQuery(mapaRolPago);
			Set<Long> contratoIdGenerados = new HashSet<Long>();
			RolPagoIf rolPagoIf = null;
			if (rolesPago.size() == 0 && rolPago.getId() == null) {
				boolean usarFechaActualParaPagarRol = false;
				Map queryMap = new HashMap();
				queryMap.put("empresaId", tipoRolIf.getEmpresaId());
				queryMap.put("codigo", "UFAPPR");
				Iterator it = parametroEmpresaLocal.findParametroEmpresaByQuery(queryMap).iterator();
				if (it.hasNext()) {
					ParametroEmpresaIf parametroEmpresa = (ParametroEmpresaIf) it.next();
					usarFechaActualParaPagarRol = (parametroEmpresa.getValor().equals("S"))?true:false;
				}
				if (usarFechaActualParaPagarRol)
					rolPago.setFecha(fechaHoy);
				rolPago.setEstado("G");// GENERADO
				rolPago.setAsientoGenerado(AsientoGenerado.NO.getLetra());
				rolPagoIf = addRolPago(rolPago);
			} else {
				if (rolPago.getId() == null) {
					if (rolesPago.size() > 1)
						throw new GenericBusinessException(
								"Error, existe mas de un Rol de pago igual !!");
					rolPagoIf = rolesPago.iterator().next();
				} else {
					saveRolPago(rolPago);
					rolPagoIf = rolPago;
				}

				// busco los detalles que esten pagados, para coger el contratoId y no tomarlo
				// en cuenta ese contrato al momento de generar el rol
				Map<String, Object> mapaDetallePagados = new HashMap<String, Object>();
				mapaDetallePagados.put("rolpagoId", rolPagoIf.getId());
				if (contratoIf != null) {
					mapaDetallePagados.put("contratoId", contratoIf.getId());
				}
				mapaDetallePagados.put("rubroId", "not null");
				Collection<RolPagoDetalleIf> rolesDetallesAutorizadosPagados = rolPagoDetalleLocal
					.findRolPagoDetalleByQueryByEstados(mapaDetallePagados,
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
						EstadoRolPagoDetalle.PAGADO.getLetra());
				if (rolesDetallesAutorizadosPagados.size() > 0) {

					// throw new GenericBusinessException("No se puede generar
					// Rol Pago, ya ha sido autorizado o pagado " +
					// (contratoIf == null?" mínimo 1 contrato":("el contrato
					// con codigo "+contratoIf.getCodigo()) )+" !!");
					for (RolPagoDetalleIf rpdt : rolesDetallesAutorizadosPagados)
						contratoIdGenerados.add(rpdt.getContratoId());

				}

				// Se borra todos los detalles que estan en emitido, porque se lo va a volver a generar.
				// hay que volverlo a poner, porque cuando se usa null, al atributo se lo saco del mapa.
				Collection<Long> contratosIdParaEliminarUtilidades = new ArrayList<Long>(); 
				mapaDetallePagados.put("rubroId", "not null");
				Collection<RolPagoDetalleIf> rolesDetallesEmitidos = rolPagoDetalleLocal
					.findRolPagoDetalleByQueryByEstados(mapaDetallePagados,
						EstadoRolPagoDetalle.EMITIDO.getLetra(),
						EstadoRolPagoDetalle.PROVISIONADO.getLetra());
				
				for (RolPagoDetalleIf r : rolesDetallesEmitidos) {
					
					/*if(r.getRubroId() != null && r.getRubroId().compareTo(40L) == 0){
						mapSB.put(r.getContratoId(), r.getValor());
					}else if(r.getRubroId() != null && r.getRubroId().compareTo(600L) == 0){
						mapOI.put(r.getContratoId(), r.getValor());
					}*/
					
					rolPagoDetalleLocal.deleteRolPagoDetalle(r.getId());
					contratosIdParaEliminarUtilidades.add(r.getContratoId());
				}
				
				//Si es de utilidades, se elimina los registros en la tabla
				//RolPagoDetalleUtilidades
				Map<String,Object> mapaUtilidades = new HashMap<String, Object>();
				mapaUtilidades.put("rolpagoId", rolPagoIf.getId());
				for ( Long contratoId : contratosIdParaEliminarUtilidades ){
					mapaUtilidades.put("contratoId", contratoId);
					Collection<RolPagoDetalleUtilidadIf> rpdus = rolPagoDetalleUtilidadLocal
						.findRolPagoDetalleUtilidadByQuery(mapaUtilidades);
					for ( RolPagoDetalleUtilidadIf rpdu : rpdus ){
						rolPagoDetalleUtilidadLocal.deleteRolPagoDetalleUtilidad(rpdu.getId());
					}
				}

				// Elimino los Rubros Eventuales Emitidos y cambio el estado de Autorizado a Emitido en RubroEventual
				// Porque despues en el proceso de generar el rol Se lo vuelve e agreagar
				Map<String, Object> mdee = new HashMap<String, Object>();
				mdee.put("rolpagoId", rolPagoIf.getId());
				if (contratoIf != null)
					mdee.put("contratoId", contratoIf.getId());
				mdee.put("rubroEventualId", "not null");
				Collection<RolPagoDetalleIf> rolesDetallesEventualesEmitidos = rolPagoDetalleLocal
					.findRolPagoDetalleByQueryByEstados(mdee,EstadoRolPagoDetalle.EMITIDO.getLetra());
				for (RolPagoDetalleIf r : rolesDetallesEventualesEmitidos) {
					RubroEventualIf rubroEventual = rubroEventualLocal.getRubroEventual(r.getRubroEventualId());
					rubroEventual.setEstado(EstadoRubroEventual.EMITIDO.getLetra());
					rolPagoDetalleLocal.deleteRolPagoDetalle(r.getId());
				}
				
			}
			// rolPagoIf = addRolPago(rolPagoIf);

			Collection<ContratoIf> contratos = null;
			if (contratoIf == null) {
				//INI_CAMBIO_20140721 Se añade para considerar como fecha de ingreso el dia 31 y poder traer  
				//toda la coleccion de contratos bajo esa condicion
				Calendar calFechaRolPago31 = new GregorianCalendar();
				calFechaRolPago31.setTime(new java.util.Date(fechaRolPago.getTime()));
				calFechaRolPago31.add(Calendar.DAY_OF_YEAR, 1);
				Date fechaFechaRolPago31 = new Date(calFechaRolPago31.getTime().getTime());
							
				//Se comenta fechaRolPago debido a que la estandarizacion a 30 dias limita 
				//la carga de contratos y deja afuera los contratos con fecha inicio 31 si los hubiere
				contratos = contratoLocal.findContratoByFechaRolPagoByTipoContratoIdByEstado(
				fechaPrimerDiaRolPago,/*fechaRolPago*/fechaFechaRolPago31,tipoContratoIf.getId(), "A");
				//FIN_CAMBIO_20140721
			} else {
				contratos = contratoLocal.findContratoById(contratoIf.getId());
			}
			if (contratos.size() == 0)
				throw new GenericBusinessException("No existen contratos creados que inicien antes de "+ utilitariosLocal.getFechaUppercase(fechaHoy));

			Calendar calEnero2010 = new GregorianCalendar(2010,0,1); //1 de Enero de 2010
			Date fechaEnero2010 = new Date(calEnero2010.getTime().getTime()); 
			
			
			/*Map aMap = new HashMap();
			aMap.put("tiporolId", 41L);
			aMap.put("tipocontratoId", 1L);
			aMap.put("anio", "2013");
			aMap.put("mes", "02");
			RolPagoIf rolSep2013 = (RolPagoIf)findRolPagoByQuery(aMap).iterator().next();*/
			
			if (tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL) {
				// CREAR DETALLE
				Collection<RolPagoDetalleIf> rolPagoDetalleCollection = new ArrayList<RolPagoDetalleIf>();

				// Se carga los impuestos a la renta para empleados
				Map<String, Object> mapaImpuestos = new HashMap<String, Object>();
				Calendar calFechaInicioImpuesto = new GregorianCalendar();
				calFechaInicioImpuesto.setTimeInMillis(gcUltimoDiaMesRolPago.getTimeInMillis());
				mapaImpuestos.put("fechaInicio", new Date(calFechaInicioImpuesto.getTimeInMillis()));
				Collection<ImpuestoRentaPersNatIf> impuestosRenta = impuestoRentaLocal
						.findImpuestoRentaPersNatByQueryByFechaInicioByFechFin(mapaImpuestos);

				Calendar calEnero = new GregorianCalendar();
				calEnero.set(Calendar.MONTH, Calendar.JANUARY);
				calEnero.set(Calendar.YEAR, Integer.valueOf(rolPagoIf.getAnio()));

				Calendar calMesAnteriorAlActual = new GregorianCalendar();
				calMesAnteriorAlActual.setTime(gcUltimoDiaMesRolPago.getTime());
				//calMesAnteriorAlActual.set(Calendar.MONTH,calMesAnteriorAlActual.get(Calendar.MONTH) - 1);
				calMesAnteriorAlActual.add(Calendar.MONTH, -1);
				calMesAnteriorAlActual.set(Calendar.YEAR, Integer.valueOf(rolPagoIf.getAnio()));

				Collection<RolPagoIf> rolesPagoDesdeEnero = getRolesPagoByFechaInicioByFechaFin(
					rolPagoIf.getTipocontratoId(),rolPagoIf.getTiporolId(), calEnero,calMesAnteriorAlActual);

				String queryStringRolesPago = obtenerQueryRolesPagoAnterioresAlActual(rolesPagoDesdeEnero);

				// Se le resta 1 para considerar el mes actual
				int numeroMesesRestantes = 12 - (Integer.valueOf(rolPagoIf.getMes()) - 1);

				Map<String, RubroIf> mapaRubroByCodigo = new HashMap<String, RubroIf>();

				RubroIf rubroSMV = null;
				BigDecimal valorSMV = BigDecimal.ZERO;
				if (tipoRol == TipoRol.MENSUAL) {
					ParametroEmpresaIf peSMV = utilitariosLocal.getParametroEmpresa(
						empresaId,NominaParametros.TIPO_PARAMETRO,
						NominaParametros.CODIGO_SALARIO_MINIMO_VITAL,"para Salario Minimo Vital !!");
					Map<String, Object> mapa = new HashMap<String, Object>();
					mapa.put("codigo", peSMV.getValor());
					mapa.put("empresaId", empresaId);
					Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapa);
					if (rubros.size() > 1) {
						throw new GenericBusinessException("Existe mas de un rubro con código "+ NominaParametros.CODIGO_SALARIO_MINIMO_VITAL);
					}
					if (rubros.size() == 1) {
						rubroSMV = rubros.iterator().next();
						SalarioMinimoVitalIf smv = salarioMinimoVitalLocal.findSalarioMinimoVitalByFechaMedia(fechaRolPago);
						if (smv == null)
							throw new GenericBusinessException("No existe Valor de Salario Minimo Vital para la FECHA del Rol de Pago !!");
						valorSMV = smv.getValor();
					}
				}

				//Busqueda de tipos de roles a los que se les va a aumentar
				//fondo de reserva despues del año
				Collection<ParametroEmpresaIf> parametrosTiposRolesFondoReserva = utilitariosLocal
					.getParametroEmpresa(empresaId, tp, true, NominaParametros.BASE_TIPOS_CONTRATOS_PARA_AUMENTAR_FONDO_RESERVA);
				Set<Long> tiposContratosParaFondosReservasIds = new HashSet<Long>();
				for ( ParametroEmpresaIf peTrFr : parametrosTiposRolesFondoReserva ){
					Collection<TipoContratoIf> tcs = tipoContratoLocal.findTipoContratoByCodigo(peTrFr.getValor()); 
					for ( TipoContratoIf tc : tcs )
						tiposContratosParaFondosReservasIds.add(tc.getId());
				}
				
				
				RubroIf rubroFondoReservaPredeterminado = null;
				for (ContratoIf contratoTmp : contratos) {	
					if (contratoTmp != null)
						System.out.println("CONTRATO ID ----> " + contratoTmp.getId());
					if (contratoTmp.getId().compareTo(40L) == 0)
						System.out.println("PROBLEMA");

					if (contratos.size() == 1 && contratoIdGenerados.contains(contratoTmp.getId())) {
						throw new GenericBusinessException("Contrato para generar Rol ya está autorizado o pagado !!");
					}

					//Se generan solo los contratos que no tienen detalles ni autorizados ni pagados 
					if (!contratoIdGenerados.contains(contratoTmp.getId())) {
						
						// **** AGREGAR RUBRO FONDO DE RESERVA AL CONTRATO CUANDO
						// **** SE CUMPLE EL AÑO DESDE EL INICIO DEL CONTRATO
						Calendar calInicioContrato = new GregorianCalendar();
						calInicioContrato.setTime(new java.util.Date(contratoTmp.getFechaInicio().getTime()));
						calInicioContrato.add(Calendar.YEAR, 1);
						
						Date fechaInicioContratoDespuesDelAnio = new Date(calInicioContrato.getTime().getTime());
						
						{
							if ( tiposContratosParaFondosReservasIds.contains(contratoTmp.getTipocontratoId()) &&
								 fechaRolPago.compareTo(fechaInicioContratoDespuesDelAnio) >= 0 && 
								 !contieneRubroFondoReserva(mapaRubros, contratoTmp) ){
								
								if ( rubroFondoReservaPredeterminado == null ){
									ParametroEmpresaIf pe = utilitariosLocal.getParametroEmpresa(
											empresaId,NominaParametros.TIPO_PARAMETRO, 
											NominaParametros.CODIGO_FONDO_RESERVA_PREDETERMINADO, 
											"para agregar rubro Fondo de Reserva al contrato del empleado !!");
									String codigoFondoReservaPredertminado = pe.getValor();
									Collection<RubroIf> rubros = rubroLocal.findRubroByCodigo(codigoFondoReservaPredertminado);
									if ( rubros.size() == 0 ){
										throw new GenericBusinessException("No existe Rubro con codigo "+
												NominaParametros.CODIGO_FONDO_RESERVA_PREDETERMINADO);
									} else if ( rubros.size() > 1 ){
										throw new GenericBusinessException("Existe mas de un Rubro con codigo "+
												NominaParametros.CODIGO_FONDO_RESERVA_PREDETERMINADO);
									}
									rubroFondoReservaPredeterminado = rubros.iterator().next();
								}
								
								
								Map<String,Object> mapaFondoReservaNuevo = new HashMap<String, Object>();
								mapaFondoReservaNuevo.put("rubroId", rubroFondoReservaPredeterminado.getId());
								mapaFondoReservaNuevo.put("contratoId", contratoTmp.getId());

								Collection<ContratoRubroIf> crs = contratoRubroLocal.findContratoRubroByQuery(mapaFondoReservaNuevo);
								if ( crs.size() == 0 ){
									ContratoRubroIf crNuevo = new ContratoRubroEJB();
									crNuevo.setContratoId(contratoTmp.getId());
									crNuevo.setRubroId(rubroFondoReservaPredeterminado.getId());
									contratoRubroLocal.addContratoRubro(crNuevo);
								}

							}
						}
						// ************************************************************
						
						Map<String, Object> mapaBusquedaDetalle = new HashMap<String, Object>();
						mapaBusquedaDetalle.put("rolpagoId", rolPagoIf.getId());
						mapaBusquedaDetalle.put("contratoId", contratoTmp.getId());

						// POR CONTRATOID Y POR TIPO DE ROL. Si el tipo de rol es Quincenal, 
						// busca por frecencia Q, si es mensual busca por frecuencia M
						Collection<ContratoRubroIf> contratosRubro = contratoRubroLocal
							.findContratoRubroByContratoIdByFrecuenciaSinProvisiones(contratoTmp.getId(), tipoRolIf);
						// MAPA<CODIGO_RUBRO,VALOR_EN_CONTRATO_RUBRO> ,
						// VALOR_EN_CONTRATO_RUBRO, PUEDE SER: VALOR O CALCULADO
						Map<String, BigDecimal> codigoValorxRubro = contratoRubroLocal
							.findMapaContratoRubroByRubroResgistrado(contratoTmp.getId());
						codigoValorxRubro.put(NominaParametros.CODIGO_SALARIO_MINIMO_VITAL,valorSMV);
						
						//OCT relacion Sep
						/*if(!contratoTmp.getCodigo().endsWith("ALOPEZ") 
								&& !contratoTmp.getCodigo().endsWith("JNAVARRETE")
								&& !contratoTmp.getCodigo().endsWith("TMOLINA")
								&& !contratoTmp.getCodigo().endsWith("CSALTOS")){
							
						}*/
						
						//NOV relacion Dic
						/*if(!contratoTmp.getCodigo().endsWith("FFLORES") 
								&& !contratoTmp.getCodigo().endsWith("MLITARDO")
								&& !contratoTmp.getCodigo().endsWith("DLUZ")
								&& !contratoTmp.getCodigo().endsWith("MGKM")){
							
						}	*/			
						
						/*Map aMap2 = new HashMap();
						aMap2.put("rolpagoId", rolSep2013.getId());
						aMap2.put("contratoId", contratoTmp.getId());						
						Collection salarioAnterior = rolPagoDetalleLocal.findRolPagoDetalleByQuery(aMap2);
						Iterator salarioAnteriorIt = salarioAnterior.iterator();
						while(salarioAnteriorIt.hasNext()){
							RolPagoDetalleIf rolPagoDetalleIf = (RolPagoDetalleIf)salarioAnteriorIt.next();
							if(rolPagoDetalleIf.getRubroId() != null && rolPagoDetalleIf.getRubroId().compareTo(40L) == 0){
								codigoValorxRubro.put("SB", rolPagoDetalleIf.getValor());
							}else if(rolPagoDetalleIf.getRubroId() != null && rolPagoDetalleIf.getRubroId().compareTo(600L) == 0){
								codigoValorxRubro.put("OI", rolPagoDetalleIf.getValor());
							}
						}*/
						
						/*if(contratoTmp.getCodigo().equals("ADG")){
							System.out.println("aqui");
						}*/
						
						//noviembre 2013
						/*if(mapSB.get(contratoTmp.getId()) != null 
								&& !contratoTmp.getCodigo().equals("AAMM")
								&& !contratoTmp.getCodigo().equals("BCMC")
								&& !contratoTmp.getCodigo().equals("CNAR")
								&& !contratoTmp.getCodigo().equals("PORTIZ")
								&& !contratoTmp.getCodigo().equals("CSALTOS")
								&& !contratoTmp.getCodigo().equals("DLUZ")
								&& !contratoTmp.getCodigo().equals("GPES")){
							codigoValorxRubro.put("SB", mapSB.get(contratoTmp.getId()));
						}else if(contratoTmp.getCodigo().equals("CNAR")){
							codigoValorxRubro.put("SB", new BigDecimal(800.00));
						}else if(contratoTmp.getCodigo().equals("PORTIZ")){
							codigoValorxRubro.put("SB", new BigDecimal(1400.00));
						}else if(contratoTmp.getCodigo().equals("CSALTOS")){
							codigoValorxRubro.put("SB", new BigDecimal(600.00));
						}else if(contratoTmp.getCodigo().equals("BCMC")){
							codigoValorxRubro.put("SB", new BigDecimal(2500.00));
						}else if(contratoTmp.getCodigo().equals("DLUZ")){
							codigoValorxRubro.put("SB", new BigDecimal(600.00));
						}else if(contratoTmp.getCodigo().equals("GPES")){
							codigoValorxRubro.put("SB", new BigDecimal(500.00));
						}*/
						/*if(mapOI.get(contratoTmp.getId()) != null 
								&& !contratoTmp.getCodigo().equals("ISUAREZ")){
							codigoValorxRubro.put("OI", mapOI.get(contratoTmp.getId()));
						}else{
							codigoValorxRubro.put("OI", new BigDecimal(0));
						}*/
						
						/*if(mapSB.get(contratoTmp.getId()) != null ){
							codigoValorxRubro.put("SB", mapSB.get(contratoTmp.getId()));
						}*/
						
						/*if(mapOI.get(contratoTmp.getId()) != null){
							codigoValorxRubro.put("OI", mapOI.get(contratoTmp.getId()));
						}*/
						
						//otros ingresos, para no tener que añadirlo a cada contrato
						if(codigoValorxRubro.get("OI") == null)
							codigoValorxRubro.put("OI", new BigDecimal(0));
						
						Double totalIngresos = 0.0;

						Set<ContratoRubroIf> setContratoRubrosImpuestoRenta = new HashSet<ContratoRubroIf>();
						Set<ContratoRubroIf> setContratoRubrosQuincenales = new HashSet<ContratoRubroIf>();

						Map<String, Object> mapaInfoImpuestos = new HashMap<String, Object>();
						mapaInfoImpuestos.put("fechaPrimerDiaRolPago",fechaPrimerDiaRolPago);

						Collection<RubroEventualIf> rubrosEventuales = rubroEventualLocal
							.findRubroEventualesByTipoRolCobroIdByContratoByMesByAnioByEstado(rolPagoIf.getTiporolId(), 
								contratoTmp.getId(), rolPago.getMes(),anioRolPago.toString(),
								EstadoRubroEventual.EMITIDO.getLetra(),
								EstadoRubroEventual.AUTORIZADO.getLetra(),
								EstadoRubroEventual.PAGADO.getLetra());

						// Se suman los rubros eventuales que son comisiones al
						// total de ingreso
						for (RubroEventualIf re : rubrosEventuales) {
							RubroIf rubro = verificarRubro(mapaRubros, re.getRubroId());
							if (rubro.getTipoRubro().equals(TipoRubro.COMISION.getLetra())) {
								Double valor = utilitariosLocal.redondeoValor(re.getValor().doubleValue());
								totalIngresos += valor;
								codigoValorxRubro.put(rubro.getCodigo(),new BigDecimal(valor));
								codigoValorxRubro.put("TOTAL", new BigDecimal(totalIngresos));
							}
						}

						Date fechaInicioContrato = contratoTmp.getFechaInicio();
						Date fechaFinContrato = contratoTmp.getFechaFin();
												
						//formo fecha del rol solo con año, mes y dia para eliminar hora y poder comparar
						Date fechaInicioRolPago = new Date(fechaPrimerDiaRolPago.getYear(), fechaPrimerDiaRolPago.getMonth(), fechaPrimerDiaRolPago.getDate());
						Date fechaFinRolPago = new Date(fechaRolPago.getYear(), fechaRolPago.getMonth(), fechaRolPago.getDate());
												
						RubrosPorContrato: 
						for (ContratoRubroIf cr : contratosRubro) {
																				
							//tengo que encerar las horas para la comparacion							
							Date fechaInicioRubroContrato = null;
							Date fechaFinRubroContrato = null;
							if(cr.getFechaInicio() != null && cr.getFechaFin() != null){
								fechaInicioRubroContrato = new Date(cr.getFechaInicio().getYear(), cr.getFechaInicio().getMonth(), cr.getFechaInicio().getDate());
								fechaFinRubroContrato = new Date(cr.getFechaFin().getYear(), cr.getFechaFin().getMonth(), cr.getFechaFin().getDate());
							}
							
							//si el rubro del contrato no tiene fechas inicio y fin, o
							//si tiene fechas deben abarcar la fecha del rol
							if((cr.getFechaInicio() == null || cr.getFechaFin() == null) || 
									(cr.getFechaInicio() != null && cr.getFechaFin() != null 
									&& fechaInicioRubroContrato.compareTo(fechaInicioRolPago) <= 0 
									&& fechaFinRubroContrato.compareTo(fechaFinRolPago) >= 0)){
								
								RubroIf rubroIf = verificarRubro(mapaRubros, cr.getRubroId());
								if (rubroIf.getCodigo().startsWith(NominaParametros.BASE_CODIGO_IMPUESTO_RENTA_PARAMETRO_EMPRESA)) {
									setContratoRubrosImpuestoRenta.add(cr);
									continue RubrosPorContrato;
								}
								
								if ( fechaRolPago.compareTo(fechaEnero2010) >= 0 &&
									 rubroIf.getTipoRubro().equals(TipoRubro.PROVISION.getLetra()) ){
									continue RubrosPorContrato;
								}

								String observacion = "";
								String tipoRubro = rubroIf.getTipoRubro();
								if (TipoRubro.SUELDO.getLetra().equals(tipoRubro)|| // Se calcula el SUELDO desde el dia queingreso para Mes
									 TipoRubro.QUINCENA.getLetra().equals(tipoRubro)  || 
									 TipoRubro.BENEFICIO.getLetra().equals(tipoRubro) || 
									 TipoRubro.COMISION.getLetra().equals(tipoRubro)

								) {
									BigDecimal valor = calcularValor(cr,codigoValorxRubro, rubroIf, contratoTmp.getCodigo(), fechaFinRolPago);
									
									if ( TipoRubro.SUELDO.getLetra().equals(tipoRubro) || 
											 TipoRubro.QUINCENA.getLetra().equals(tipoRubro)  )
										valor = calcularValorDelMes(tipoRol, fechaRolPago, contratoTmp,
											utilitariosLocal.redondeoValor(valor).doubleValue(), null, null);
									
									observacion = getObservacion(fechaRolPago, contratoTmp,observacion,
											fechaInicioContrato,fechaFinContrato);
									
									/*if ( rubroIf.getModoOperacion().equals(ModoOperacionRubro.CALCULADO.getLetra()) ){
										BigDecimal valorMes = calcularValorDelMes(tipoRol, fechaRolPago, contratoTmp,
												utilitariosLocal.redondeoValor(valor).doubleValue(), null, null);
										if (valorMes.compareTo(valor) != 0) {
											valor = valorMes;
		
											observacion = getObservacion(fechaRolPago, contratoTmp,observacion,
												fechaInicioContrato,fechaFinContrato);
										}
									}*/
									// ***** CALCULO DEL FONDO DE RESERVA *****
									if ( rubroIf.getCodigo().startsWith(NominaParametros.BASE_CODIGO_FONDO_RESERVA_PARAMETRO_EMPRESA) ){
										
										valor = calcularValorFondoReserva(fechaRolPago, gce, contratoTmp, 
											calInicioContrato, fechaInicioContratoDespuesDelAnio, valor, tipoRol);
										//if ( esFechaContrato(fechaInicioContratoDespuesDelAnio, fechaRolPago) ){
										//	observacion = "AGREGADO AUTOMATICAMENTE, FECHA DE INICIO DEL " +
										//		"CONTRATO "+utilitariosLocal.getFechaUppercase(calInicioContrato.getTime());
										//}
										//observacion = getObservacion(fechaRolPago, contratoTmp, observacion, fechaInicioContrato, fechaFinContrato);
									}
									// ****************************************
									
									RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalle(
										rolPagoIf, contratoTmp, cr.getRubroId(), valor,observacion.trim());
									RolPagoDetalleIf rolPagoDetalleGuardado = rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
									rolPagoDetalleCollection.add(rolPagoDetalleGuardado);
									codigoValorxRubro.put(rubroIf.getCodigo(),valor);

									tieneDetalle = true;

									// if ( rubroIf.getTipoRubro().equals("S") ){
									if (TipoRubro.SUELDO.getLetra().equals(rubroIf.getTipoRubro())) {
										if (valor == null)
											throw new GenericBusinessException("Error general para cálculo de Ingreso Total !!");
										totalIngresos += utilitariosLocal.redondeoValor(valor.doubleValue());
										mapaInfoImpuestos.put("sueldo",utilitariosLocal.redondeoValor(valor.doubleValue()));
										codigoValorxRubro.put("TOTAL",new BigDecimal(totalIngresos));
										// } else if (
										// rubroIf.getTipoRubro().equals("B") ){
									} else if (TipoRubro.BENEFICIO.getLetra().equals(rubroIf.getTipoRubro()) || 
											   TipoRubro.COMISION.getLetra().equals(rubroIf.getTipoRubro())) {
										totalIngresos += utilitariosLocal.redondeoValor(rolPagoDetalleGuardado.getValor().doubleValue());
										codigoValorxRubro.put("TOTAL",new BigDecimal(totalIngresos));
									}
									
								} else {
									setContratoRubrosQuincenales.add(cr);
								}
							}
						}

						// Se analizan los rubros que no sean sueldos, quincenas ni beneficios
						for (ContratoRubroIf contratoRubro : setContratoRubrosQuincenales) {
							RubroIf rubroIf = verificarRubro(mapaRubros, contratoRubro.getRubroId());
							boolean existeRubroRolDetalle = existeRubroEnRolPagoDetalle(mapaBusquedaDetalle, rubroIf.getId());
							BigDecimal valor = null;
							
							if (!existeRubroRolDetalle) {
								
								//tengo que encerar las horas para la comparacion							
								Date fechaInicioRubroContrato = null;
								Date fechaFinRubroContrato = null;
								if(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null){
									fechaInicioRubroContrato = new Date(contratoRubro.getFechaInicio().getYear(), contratoRubro.getFechaInicio().getMonth(), contratoRubro.getFechaInicio().getDate());
									fechaFinRubroContrato = new Date(contratoRubro.getFechaFin().getYear(), contratoRubro.getFechaFin().getMonth(), contratoRubro.getFechaFin().getDate());
								}
								
								//si el rubro del contrato no tiene fechas inicio y fin, o
								//si tiene fechas deben abarcar la fecha del rol
								if((contratoRubro.getFechaInicio() == null || contratoRubro.getFechaFin() == null) || 
										(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null 
										&& fechaInicioRubroContrato.compareTo(fechaInicioRolPago) <= 0 
										&& fechaFinRubroContrato.compareTo(fechaFinRolPago) >= 0)){
									
									valor = calcularValor(contratoRubro,codigoValorxRubro, rubroIf, contratoTmp.getCodigo(), fechaFinRolPago);
									
									// RolPagoDetalleIf rolPagoDetalleIf =
									// registrarRolPagoDetalle(rolPagoIf,
									// contratoTmp,
									// contratoRubro,valor,observacion);
									
									RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalle(rolPagoIf, contratoTmp, contratoRubro.getRubroId(),valor, null);
									RolPagoDetalleIf rolPagoDetalleGuardado = rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
									rolPagoDetalleCollection.add(rolPagoDetalleGuardado);
									codigoValorxRubro.put(rubroIf.getCodigo(),valor);

									if (rubroAportePersonalIess != null && 
										rubroIf.getId().equals(rubroAportePersonalIess.getId())) {
										mapaInfoImpuestos.put("aportePersonalIess",	utilitariosLocal.redondeoValor(valor.doubleValue()));
									}

									tieneDetalle = true;
								}								
							}
						}

						// ************** CALCULO DE IMPUESTO A LA RENTA******************

						Double totalGastosDeducibles = CalcularTotalGastosDeducibles(rolPagoIf, contratoTmp);

						// Se analiza los descuentos que empiezan con codigo IR
						// que corresponde al Impuesto a la Renta
						for (ContratoRubroIf contratoRubro : setContratoRubrosImpuestoRenta) {
							
							//tengo que encerar las horas para la comparacion							
							Date fechaInicioRubroContrato = null;
							Date fechaFinRubroContrato = null;
							if(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null){
								fechaInicioRubroContrato = new Date(contratoRubro.getFechaInicio().getYear(), contratoRubro.getFechaInicio().getMonth(), contratoRubro.getFechaInicio().getDate());
								fechaFinRubroContrato = new Date(contratoRubro.getFechaFin().getYear(), contratoRubro.getFechaFin().getMonth(), contratoRubro.getFechaFin().getDate());
							}
							
							//si el rubro del contrato no tiene fechas inicio y fin, o
							//si tiene fechas deben abarcar la fecha del rol
							if((contratoRubro.getFechaInicio() == null || contratoRubro.getFechaFin() == null) || 
									(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null 
									&& fechaInicioRubroContrato.compareTo(fechaInicioRolPago) <= 0 
									&& fechaFinRubroContrato.compareTo(fechaFinRolPago) >= 0)){
								
								RubroIf rubroIf = verificarRubro(mapaRubros, contratoRubro.getRubroId());
								BigDecimal valor = null;
								valor = calcularValorImpuestoRenta(rolPagoIf,contratoTmp, contratoRubro,
									codigoValorxRubro, rubroIf, impuestosRenta,mapaInfoImpuestos, 
									queryStringRolesPago,numeroMesesRestantes, mapaRubroByCodigo,
									totalGastosDeducibles, mapaRubros);
								
								if ( valor.compareTo(BigDecimal.ZERO) > 0 || registrarImpuestoRentaCero) {
									RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalle(rolPagoIf, contratoTmp, contratoRubro.getRubroId(),valor, null);
									RolPagoDetalleIf rolPagoDetalleGuardado = rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
									rolPagoDetalleCollection.add(rolPagoDetalleGuardado);
									codigoValorxRubro.put(rubroIf.getCodigo(),valor);
									tieneDetalle = true;
								}
							}
						}						
						
						// RUBROS EVENTUALES POR CONTRATO
						// Se registran los mensuales y si hay algun quincenal del mismo rubro, tambien.
						// Los quincenales se registran para que sean tomados como descuentos.
						for (RubroEventualIf rubroEventual : rubrosEventuales) {
							
							Collection<RolPagoDetalleIf> roleDetalleExistente = rolPagoDetalleLocal
								.findRolPagoDetalleByRubroEventualId(rubroEventual.getId());
							if (roleDetalleExistente.size() == 0) {
								RolPagoDetalleIf rolPagoDetalleIf = registrarRubroEventual(
									rolPagoIf, contratoTmp.getId(),rubroEventual, rubroEventual.getValor(),
									EstadoRolPagoDetalle.EMITIDO.getLetra());
								RolPagoDetalleIf rolPagoDetalleGuardado = rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
								rolPagoDetalleCollection.add(rolPagoDetalleGuardado);
							} else {
								// talvez no se necesita, analizar
								RolPagoDetalleIf rolPagoDetalleIf = roleDetalleExistente.iterator().next();
								rolPagoDetalleCollection.add(rolPagoDetalleIf);
							}
						}

						Map<Long, RolPagoDetalleIf> mapaRubrosQuincenal = new HashMap<Long, RolPagoDetalleIf>();
						if (tipoRol == TipoRol.MENSUAL) {
							// SI ES MENSUAL, SE PONE LOS INGRESOS QUINCENALES
							// COMO EGRESOS, SIN NADA QUE LOS SEÑALE
							if (tipoRolQuincenal == null)
								throw new GenericBusinessException("No existe Tipo de Rol Quincenal para cálculo de Egresos Quincenales");
							registrarEgresosQuincenales(rolPagoIf,rubroQuincenal, tipoRolQuincenal,
								rolPagoDetalleCollection, contratoTmp,mapaRubros, mapaRubrosQuincenal);

							
							// SE REGISTRAN TODOS LOS PROVISIONADOS EN EL ROL MENSUAL PARA ANTES DE ENERO 2010
							if ( fechaRolPago.compareTo(fechaEnero2010) < 0  ){
								Map<String, Object> mapaContratoRubro = new HashMap<String, Object>();
								mapaContratoRubro.put("contratoId", contratoTmp.getId());

								Collection<ContratoRubroIf> contratoRubros = contratoRubroLocal
									.findContratoRubroByMapByTipoRubro(mapaContratoRubro,TipoRubro.PROVISION.getLetra());
								
								for (ContratoRubroIf cr : contratoRubros) {
									RubroIf rubroIf = verificarRubro(mapaRubros, cr.getRubroId());
									registrarProvisionales(rolPagoIf, fechaRolPago,contratoTmp, 
										totalIngresos, rubroIf,tipoRol, codigoValorxRubro, rubroSMV);
								}
							}
						}

						// talvez no se necesita, analizar
						for (Long rolDetalleQuincenalId : mapaRubrosQuincenal.keySet()) {
							RolPagoDetalleIf rolPagoDetalleIf = mapaRubrosQuincenal.get(rolDetalleQuincenalId);
							rolPagoDetalleCollection.add(rolPagoDetalleIf);
						}
					}
				}
			} else if (tipoRol == TipoRol.FONDO_RESERVA || tipoRol == TipoRol.APORTE_PATRONAL) {
				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("mes", rolPagoIf.getMes());
				mapa.put("anio", rolPagoIf.getAnio());
				mapa.put("tiporolId", tipoRolMensual.getId());
				mapa.put("tipocontratoId", rolPagoIf.getTipocontratoId());

				Collection<RolPagoIf> rolesMensuales = findRolPagoByQuery(mapa);
				if (rolesMensuales.size() == 0)
					throw new GenericBusinessException("Rol de Pago Mensual no ha sido generado !!!");
				RolPagoIf rolMensual = rolesMensuales.iterator().next();

				String observacionFecha = utilitariosLocal.getNombreMes(Integer.parseInt(rolPagoIf.getMes())) + "-" + rolPagoIf.getAnio();

				Set<RolPagoDetalleIf> detalleDetalles = new RolPagoDetalleSet();

				mapa = new HashMap<String, Object>();

				mapa.put("rolpagoId", rolMensual.getId());
				if (contratoIf != null)
					mapa.put("contratoId", contratoIf.getId());

				/*Calendar fecha = new GregorianCalendar();
				fecha.set(Calendar.MONTH,
						Integer.valueOf(rolPagoIf.getMes()) - 1);
				fecha.set(Calendar.YEAR, Integer.valueOf(rolPagoIf.getAnio()));

				Calendar fechaEnero2010 = new GregorianCalendar();
				fechaEnero2010.set(Calendar.MONTH, 0);
				fechaEnero2010.set(Calendar.YEAR, 2010);*/

				if (tipoRol == TipoRol.APORTE_PATRONAL) {

					// APORTE PATRONAL
					if (fechaRolPago.compareTo(fechaEnero2010) >= 0) {
						for (ContratoIf contratoTmp : contratos) {
							
							if(contratoTmp.getCodigo().equals("AVIL")){
								System.out.println("a");
							}
							
							
							Map<String, BigDecimal> codigoValorxRubro = new HashMap<String, BigDecimal>();
							Double totalIngresos = calcularValoresParaProvisionales(mapaRubros, mapa, contratoTmp,codigoValorxRubro, null);

							BigDecimal valorAportePatronal = calcularValorDelMes(TipoRol.TIPO_ROL_PROVISION, fechaRolPago,contratoTmp, totalIngresos,rubroAportePatronal, codigoValorxRubro);

							String observacion = rubroAportePatronal.getNombre()+ " DE " + observacionFecha;
							RolPagoDetalleIf rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
								rolPagoIf, contratoTmp.getId(),rubroAportePatronal.getId(),
								valorAportePatronal, null, observacion);
							rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
							tieneDetalle = true;

							// IECE
							if (rubroIece != null) {
								BigDecimal valorIece = calcularValorDelMes(
									TipoRol.TIPO_ROL_PROVISION,fechaRolPago, contratoTmp,
									totalIngresos, rubroIece,codigoValorxRubro);

								observacion = rubroIece.getNombre() + " DE " + observacionFecha;
								rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
									rolPagoIf, contratoTmp.getId(),rubroIece.getId(), valorIece, null,observacion);
								rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
								tieneDetalle = true;
							}

							// SECAP
							if (rubroSecap != null) {
								BigDecimal valorSecap = calcularValorDelMes(
									TipoRol.TIPO_ROL_PROVISION,fechaRolPago, contratoTmp,
									totalIngresos, rubroSecap,codigoValorxRubro);

								observacion = rubroSecap.getNombre() + " DE " + observacionFecha;
								rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
									rolPagoIf, contratoTmp.getId(),rubroSecap.getId(), valorSecap, null,observacion);
								rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
								tieneDetalle = true;
							}

						}
					} else {
						mapa.put("rubroEventualId", "null");
						mapa.put("estado", EstadoRolPagoDetalle.PROVISIONADO
								.getLetra());
						mapa.put("rubroId", rubroAportePatronal.getId());
						Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapa);
						for (RolPagoDetalleIf rpdi : detalles) {
							detalleDetalles.add(rpdi);
						}

						// IECE
						if (rubroIece != null) {
							mapa.put("rubroId", rubroIece.getId());
							detalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapa);
							for (RolPagoDetalleIf rpdi : detalles) {
								detalleDetalles.add(rpdi);
							}
						}

						// SECAP
						if (rubroSecap != null) {
							mapa.put("rubroId", rubroSecap.getId());
							detalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapa);
							for (RolPagoDetalleIf rpdi : detalles) {
								detalleDetalles.add(rpdi);
							}
						}
					}

				} else if (tipoRol == TipoRol.FONDO_RESERVA) {

					if (fechaRolPago.compareTo(fechaEnero2010) >= 0) {
						
						ProcesarRubroEnFondoReserva:
						for (ContratoIf contratoTmp : contratos) {

							Map<String,Object> mapaMensual = new HashMap<String, Object>();
							mapaMensual.put("rolpagoId", rolMensual.getId());
							mapaMensual.put("contratoId", contratoTmp.getId());
							Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaMensual);
							for ( RolPagoDetalleIf detalle : detalles ){
								if ( detalle.getRubroId() != null ){
									RubroIf rubro = verificarRubro(mapaRubros, detalle.getRubroId());
									//Si encuentra un rubro en el rol mensual que empieze con la base del codigo para 
									//fondo de reserva y el id no corresponde al rubro de fondo de reserva provisionado, 
									//es que ya esta el fondo de reserva de beneficio y no tiene que procesarlo.
									if ( rubro.getCodigo().startsWith(NominaParametros.BASE_CODIGO_FONDO_RESERVA_PARAMETRO_EMPRESA) &&
										 !rubro.getId().equals(rubroFondoReserva.getId())	){
										continue ProcesarRubroEnFondoReserva;
									}
								}
							}
							
							
							Map<String,Object> mapaCR = new HashMap<String, Object>();
							mapaCR.put("contratoId", contratoTmp.getId());
							mapaCR.put("rubroId", rubroFondoReserva.getId());
							Collection<ContratoRubroIf> rubros = contratoRubroLocal.findContratoRubroByQuery(mapaCR);
							if ( rubros.size() == 1 ) {
								Calendar calInicioContrato = new GregorianCalendar();
								calInicioContrato.setTime(new java.util.Date(contratoTmp.getFechaInicio().getTime()));
								calInicioContrato.add(Calendar.YEAR, 1);
								
								Date fechaInicioContratoDespuesDelAnio = new Date(calInicioContrato.getTime().getTime());
								
								//INI_CAMBIO_20140707 Se crean variables por escenario de que el fin de contrato sea en dia 31
								Calendar calFinContrato = new GregorianCalendar();
								calFinContrato.setTime(new java.util.Date(contratoTmp.getFechaFin().getTime()));
								Date fechaFinContrato30_31 = new Date(calFinContrato.getTime().getTime());
								
								//se resta un dia en caso de que el fin de contrato sea en dia 31 a fin de no 
								//pagar un dia de mas en fondos de reserva
								if(fechaFinContrato30_31.getDate()==31){
									fechaFinContrato30_31 = new Date(calFinContrato.getTime().getTime()-1);
								}
									
								java.sql.Date fechaDateFinContrato30_31 = new Date(fechaFinContrato30_31.getYear(),fechaFinContrato30_31.getMonth(),fechaFinContrato30_31.getDate());
								java.sql.Date fechaDateInicioContrato = new Date(fechaInicioContratoDespuesDelAnio.getYear(),fechaInicioContratoDespuesDelAnio.getMonth(),fechaInicioContratoDespuesDelAnio.getDate());
							
								if (fechaRolPago.compareTo(fechaInicioContratoDespuesDelAnio) >=0  
									//Se añade a fin de que no calcule valor por fondos de reserva si el contrato 
									//finaliza antes de	cumplir 1 año
									&& fechaDateFinContrato30_31.compareTo(fechaDateInicioContrato)>0){
																				
								//FIN_CAMBIO_20140707	

									Map<String, BigDecimal> codigoValorxRubro = new HashMap<String, BigDecimal>();
									Double totalIngresos = calcularValoresParaProvisionales(
										mapaRubros, mapa, contratoTmp,codigoValorxRubro, null);
										
									//Calcula el valor del fondo de reserva al salario basico sin contar con el calculo de dias
									BigDecimal valorFondoReserva = calcularValorDelMes(
									TipoRol.TIPO_ROL_PROVISION, fechaRolPago,contratoTmp, totalIngresos,
									rubroFondoReserva, codigoValorxRubro);
									
									//Valor final del Fondo de reserva con el calculo de dias respectivos
									valorFondoReserva = calcularValorFondoReserva(fechaRolPago, gce, contratoTmp,
										calInicioContrato,fechaInicioContratoDespuesDelAnio,valorFondoReserva, tipoRol);
									
									System.out.println("FONDOOOOOOOO: " + valorFondoReserva);
									
									String observacion = rubroFondoReserva.getNombre() + " DE " + observacionFecha;
									RolPagoDetalleIf rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
										rolPagoIf, contratoTmp.getId(),rubroFondoReserva.getId(),
										valorFondoReserva, null, observacion);
									rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
									tieneDetalle = true;
								}
							}
							tieneDetalle = true;
						}
					} else {
						mapa.put("rubroEventualId", "null");
						mapa.put("estado", EstadoRolPagoDetalle.PROVISIONADO.getLetra());
						mapa.put("rubroId", rubroFondoReserva.getId());
						Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapa);
						for (RolPagoDetalleIf rpdi : detalles) {
							detalleDetalles.add(rpdi);
						}

					}
				}
				
				if (fechaRolPago.compareTo(fechaEnero2010) < 0) {
					for (RolPagoDetalleIf detalle : detalleDetalles) {
						// detalle.setEstado(EstadoRolPagoDetalle.PAGADO.getLetra()
						// );
						RubroIf rubro = verificarRubro(mapaRubros, detalle.getRubroId());
						String observacion = rubro.getNombre() + " DE " + observacionFecha;
						RolPagoDetalleIf rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
							rolPagoIf, detalle.getContratoId(), detalle.getRubroId(), detalle.getValor(),null, observacion);
						rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
						tieneDetalle = true;
					}
				}

			} else if (tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO) {
								
				
				// Integer anioRolPago = gc.get(GregorianCalendar.YEAR);

				/*Calendar fecha = new GregorianCalendar();
				fecha.set(Calendar.MONTH,
						Integer.valueOf(rolPagoIf.getMes()) - 1);
				fecha.set(Calendar.YEAR, Integer.valueOf(rolPagoIf.getAnio()));

				Calendar fechaEnero2010 = new GregorianCalendar();
				fechaEnero2010.set(Calendar.MONTH, 0);
				fechaEnero2010.set(Calendar.YEAR, 2010);*/

				ParametroEmpresaIf peSMV = utilitariosLocal.getParametroEmpresa(empresaId,
					NominaParametros.TIPO_PARAMETRO,NominaParametros.CODIGO_SALARIO_MINIMO_VITAL,
					"para Salario Minimo Vital !!");
				String codigoSalarioMinimoVital = peSMV.getValor();
				Set<String> codigosRubrosEspeciales = new HashSet<String>();
				codigosRubrosEspeciales.add(peSMV.getValor());

				if (tipoRolMensual != null) {

					Map<String, Object> mapaRolPagoPorFecha = new HashMap<String, Object>();
					String mes = rolPagoIf.getMes();
					String nombreMes = utilitariosLocal.getNombreMes(Integer.parseInt(mes));
					mapaRolPagoPorFecha.put("mes", mes);
					String anio = rolPagoIf.getAnio();
					mapaRolPagoPorFecha.put("anio", anio);
					mapaRolPagoPorFecha.put("tiporolId", tipoRolMensual.getId());
					mapaRolPagoPorFecha.put("tipocontratoId", rolPagoIf.getTipocontratoId());

					Collection<RolPagoIf> roles = findRolPagoByQuery(mapaRolPagoPorFecha);
					if (roles.size() == 0)
						throw new GenericBusinessException(
							"No existe Rol Pago Mensual para fecha " + nombreMes);

					RolPagoIf rolMensual = roles.iterator().next();

					if (fechaRolPago.compareTo(fechaEnero2010) >= 0) {

						String observacionFecha = utilitariosLocal.getNombreMes(
							Integer.parseInt(rolPagoIf.getMes())) + "-" + rolPagoIf.getAnio();

						Map<String, Object> mapa = new HashMap<String, Object>();
						mapa.put("rolpagoId", rolMensual.getId());
						if (contratoIf != null)
							mapa.put("contratoId", contratoIf.getId());
						for (ContratoIf contratoTmp : contratos) {
							Map<String, BigDecimal> codigoValorxRubro = new HashMap<String, BigDecimal>();
							Double totalIngresos = calcularValoresParaProvisionales(mapaRubros, mapa, 
								contratoTmp,codigoValorxRubro, codigosRubrosEspeciales);
							codigoValorxRubro.put("TOTAL", new BigDecimal(totalIngresos));
											
							if(contratoTmp.getCodigo().equals("MLITARDO")){
								System.out.println("ver");
							}
							
							System.out.println("AQUIIIIIIIIIIIIIII: " + contratoTmp.getCodigo());
							
							Map<String,Object> mapaRubrosEspeciales =  new HashMap<String, Object>();
							for (String codigoRubroEspecial : codigosRubrosEspeciales){
								Collection<RubroIf> rubrosEspeciales = rubroLocal.findRubroByCodigo(codigoRubroEspecial);
								for ( RubroIf re : rubrosEspeciales ){
									mapaRubrosEspeciales.put("rubroId", re.getId());
									mapaRubrosEspeciales.put("contratoId", contratoTmp.getId());
									Collection<ContratoRubroIf> rubrosPorContrato = contratoRubroLocal.findContratoRubroByQuery(mapaRubrosEspeciales);
									for ( ContratoRubroIf cr : rubrosPorContrato ){
										RubroIf r = verificarRubro(mapaRubros, cr.getRubroId());
										if ( codigoSalarioMinimoVital.equals(r.getCodigo()) ){
											SalarioMinimoVitalIf smvIf = salarioMinimoVitalLocal.findSalarioMinimoVitalByFechaMedia(fechaRolPago);
											codigoValorxRubro.put(r.getCodigo(),smvIf.getValor());
										}
									}
								}
							}
							
							RubroIf rubroAplicado = tipoRol == TipoRol.DECIMO_TERCERO ? 
								rubroDecimoTercero : rubroDecimoCuarto;
							
							BigDecimal valorRubroAplicado = calcularValorDelMes(
									TipoRol.TIPO_ROL_PROVISION, fechaRolPago,
									contratoTmp, totalIngresos, rubroAplicado,
									codigoValorxRubro);
							
							
							//En R14 puede pasar que el empleado es nuevo e ingreso a mitad de mes, entonces se debe
							//calcular el proporcional del valor (se lo hara tomando meses de 30 dias cada uno)						
							if(tipoRol == TipoRol.DECIMO_CUARTO){
								Date fechaIngresoEmpleado = contratoTmp.getFechaInicio();
								int mesIngreso = fechaIngresoEmpleado.getMonth() + 1;
								int anioIngreso = fechaIngresoEmpleado.getYear() + 1900;
								int diaIngreso = fechaIngresoEmpleado.getDate();
								
								Date fechaSalidaEmpleado = contratoTmp.getFechaFin();
								int mesSalida = fechaSalidaEmpleado.getMonth()+1;
								int anioSalida = fechaSalidaEmpleado.getYear() + 1900;
								int diaSalida = fechaSalidaEmpleado.getDate();
								
								int diasTrabajadosMes=0;
								
								Integer diaFinMes = gcUltimoDiaMesRolPago.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
								
								//INI_CAMBIO_20140623 Se añaden validaciones para los distintos escenarios a presentarse en 
								//el calculo del decimo cuarto sueldo 
								if((anioIngreso == anioRol && mesIngreso == (mesRol+1) && diaIngreso > 1 && diaSalida == diaFinMes)
										||(anioIngreso == anioRol && mesIngreso == (mesRol+1) && diaIngreso > 1 && mesSalida > (mesRol+1))
										||(anioIngreso == anioRol && mesIngreso == (mesRol+1) && diaIngreso > 1 && anioSalida > anioRol)){ 
									double fraccionDia = valorRubroAplicado.doubleValue() / 30;
									//valida en caso de que se exista una fecha de ingreso en dia 31
									if(fechaIngresoEmpleado.getDate()==31)
										diasTrabajadosMes = 1;
									else	
										diasTrabajadosMes = 30 - diaIngreso + 1; //original
									valorRubroAplicado = BigDecimal.valueOf(fraccionDia * diasTrabajadosMes);
								}else if(anioIngreso == anioRol && mesIngreso == (mesRol+1) && mesSalida == (mesRol+1) 
										&& diaIngreso > 1 && diaSalida < diaFinMes && anioSalida == anioRol){
									double fraccionDia = valorRubroAplicado.doubleValue() / fechaRolPago.getDate();
									int diasTrabajadosI = 30 - diaIngreso;
									int diasTrabajadosF = 30 - diaSalida;
									diasTrabajadosMes = diasTrabajadosI-diasTrabajadosF+1;
									valorRubroAplicado = BigDecimal.valueOf(fraccionDia * diasTrabajadosMes);
								}else if(anioIngreso == anioRol && mesSalida == (mesRol+1)&& anioSalida == anioRol
										&& diaSalida < diaFinMes){
									valorRubroAplicado = calculoDecimoCuartoSMM(fechaRolPago, valorRubroAplicado,diaSalida);
								}else if(anioIngreso < anioRol && mesSalida == (mesRol+1)&& anioSalida == anioRol
										&& diaSalida < diaFinMes){
									valorRubroAplicado = calculoDecimoCuartoSMM(fechaRolPago, valorRubroAplicado,diaSalida);
								}
								//FIN_CAMBIO_20140623
							}														

							String observacion = rubroAplicado.getNombre() + " DE " + observacionFecha;
							RolPagoDetalleIf rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
								rolPagoIf, contratoTmp.getId(),rubroAplicado.getId(), valorRubroAplicado,
								null, observacion);
							rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
							tieneDetalle = true;

						}

					} else {

						// Se crea mapa para busqueda de detalles de decimos
						// creados
						Map<String, Object> mapaDetalle = new HashMap<String, Object>();
						if (tipoRol == TipoRol.DECIMO_TERCERO)
							mapaDetalle.put("rubroId", rubroDecimoTercero.getId());
						else if (tipoRol == TipoRol.DECIMO_CUARTO)
							mapaDetalle.put("rubroId", rubroDecimoCuarto.getId());

						Date fechaInicio = tipoRolIf.getFechaInicio();
						if (fechaInicio == null)
							throw new GenericBusinessException("Tipo de Rol \""
								+ tipoRolIf.getNombre() + "\" no tiene Fecha Inicio");

						Date fechaFin = tipoRolIf.getFechaFin();
						if (fechaFin == null)
							throw new GenericBusinessException("Tipo de Rol \""
								+ tipoRolIf.getNombre() + "\" no tiene Fecha Fin");

						for (RolPagoIf rol : roles) {
							// Se establece el Rol de pago Id en el mapa de
							// busqueda para el detalle
							mapaDetalle.put("rolpagoId", rol.getId());
							Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
									.findRolPagoDetalleByQuery(mapaDetalle);
							for (RolPagoDetalleIf detalle : detalles) {
								// detalle.setEstado(EstadoRolPagoDetalle.PAGADO.getLetra());
								String observacion = nombreMes + "-" + anio;
								RolPagoDetalleIf rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
									rolPagoIf, detalle.getContratoId(),detalle.getRubroId(), detalle.getValor(), 
									null, observacion);
								rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
								tieneDetalle = true;
							}
						}
					}
				} else
					throw new GenericBusinessException(
							"No existe tipo de Rol Mensual");
			} else if (tipoRol == TipoRol.VACACIONES) {
				
					//INI_CAMBIO_20140610 Estructura para el calculo del Rol correspondiente a Vacaciones 
					ParametroEmpresaIf peSMV = utilitariosLocal.getParametroEmpresa(empresaId,
					NominaParametros.TIPO_PARAMETRO,NominaParametros.CODIGO_SALARIO_MINIMO_VITAL,
					"para Salario Minimo Vital !!");
					String codigoSalarioMinimoVital = peSMV.getValor();
					Set<String> codigosRubrosEspeciales = new HashSet<String>();
					codigosRubrosEspeciales.add(peSMV.getValor());

					if (tipoRolMensual != null) {

						Map<String, Object> mapaRolPagoPorFecha = new HashMap<String, Object>();
						String mes = rolPagoIf.getMes();
						String nombreMes = utilitariosLocal.getNombreMes(Integer.parseInt(mes));
						mapaRolPagoPorFecha.put("mes", mes);
						String anio = rolPagoIf.getAnio();
						mapaRolPagoPorFecha.put("anio", anio);
						mapaRolPagoPorFecha.put("tiporolId", tipoRolMensual.getId());
						mapaRolPagoPorFecha.put("tipocontratoId", rolPagoIf.getTipocontratoId());

						Collection<RolPagoIf> roles = findRolPagoByQuery(mapaRolPagoPorFecha);
						if (roles.size() == 0)
							throw new GenericBusinessException(
								"No existe Rol Pago Mensual para fecha " + nombreMes);

						RolPagoIf rolMensual = roles.iterator().next();

						if (fechaRolPago.compareTo(fechaEnero2010) >= 0) {

							String observacionFecha = utilitariosLocal.getNombreMes(
								Integer.parseInt(rolPagoIf.getMes())) + "-" + rolPagoIf.getAnio();

							Map<String, Object> mapa = new HashMap<String, Object>();
							mapa.put("rolpagoId", rolMensual.getId());
							if (contratoIf != null)
								mapa.put("contratoId", contratoIf.getId());
							for (ContratoIf contratoTmp : contratos) {
								Map<String, BigDecimal> codigoValorxRubro = new HashMap<String, BigDecimal>();
								Double totalIngresos = calcularValoresParaProvisionales(mapaRubros, mapa, 
									contratoTmp,codigoValorxRubro, codigosRubrosEspeciales);
								codigoValorxRubro.put("TOTAL", new BigDecimal(totalIngresos));
												
								
								Map<String,Object> mapaRubrosEspeciales =  new HashMap<String, Object>();
								for (String codigoRubroEspecial : codigosRubrosEspeciales){
									Collection<RubroIf> rubrosEspeciales = rubroLocal.findRubroByCodigo(codigoRubroEspecial);
									for ( RubroIf re : rubrosEspeciales ){
										mapaRubrosEspeciales.put("rubroId", re.getId());
										mapaRubrosEspeciales.put("contratoId", contratoTmp.getId());
										Collection<ContratoRubroIf> rubrosPorContrato = contratoRubroLocal.findContratoRubroByQuery(mapaRubrosEspeciales);
										for ( ContratoRubroIf cr : rubrosPorContrato ){
											RubroIf r = verificarRubro(mapaRubros, cr.getRubroId());
											if ( codigoSalarioMinimoVital.equals(r.getCodigo()) ){
												SalarioMinimoVitalIf smvIf = salarioMinimoVitalLocal.findSalarioMinimoVitalByFechaMedia(fechaRolPago);
												codigoValorxRubro.put(r.getCodigo(),smvIf.getValor());
											}
										}
									}
								}
								
								RubroIf rubroAplicado = tipoRol == TipoRol.VACACIONES ? 
									rubroVacaciones : null;
								
								BigDecimal valorRubroAplicado = calcularValorDelMes(
										TipoRol.TIPO_ROL_PROVISION, fechaRolPago,
										contratoTmp, totalIngresos, rubroAplicado,
										codigoValorxRubro);
								
								String observacion = rubroAplicado.getNombre() + " DE " + observacionFecha;
								RolPagoDetalleIf rolDetalle = registrarRolPagoDetalleDecimosFondosAportesVacaciones(
									rolPagoIf, contratoTmp.getId(),rubroAplicado.getId(), valorRubroAplicado,
									null, observacion);
								rolPagoDetalleLocal.addRolPagoDetalle(rolDetalle);
								tieneDetalle = true;

							}
						} 
					} else
						throw new GenericBusinessException("No existe tipo de Rol Mensual");
					//FIN_CAMBIO_20140610
																
			} else if ( tipoRol == TipoRol.UTILIDADES ){
				
				
				ContratoUtilidadIf contratoUtilidadIf = 
					contratoUtilidadLocal.buscarContratoUtilidad(empresaId,fechaRolPago);
				mapaResultado.put("contratoUtilidadIf", contratoUtilidadIf);
				
				
				Integer anioUtilidad = contratoUtilidadLocal.obtenerAnioUtilidadParametroEmpresa(empresaId);
				Calendar calUtilidad = new GregorianCalendar();
				calUtilidad.setTime(new java.util.Date(fechaRolPago.getTime()));
				if ( anioUtilidad < 0 )
					calUtilidad.set(Calendar.YEAR, calUtilidad.get(Calendar.YEAR)-anioUtilidad );
				else 
					calUtilidad.set(Calendar.YEAR, anioUtilidad );
				
				Double utilidad = contratoUtilidadIf.getUtilidad().doubleValue();
				Double porcentajeContratos = contratoUtilidadIf.getPorcentajeContrato().doubleValue();
				Double valorPorcentajeContratos = utilidad * porcentajeContratos / 100;
				Double porcentajeCargas = contratoUtilidadIf.getPorcentajeCarga().doubleValue();
				Double valorPorcentajeCargas = utilidad * porcentajeCargas / 100;
				
				Date fechaInicioPeriodo = contratoUtilidadIf.getFechaInicio();
				Calendar calDiaInicioAnio = new GregorianCalendar();
				calDiaInicioAnio.setTime(new java.util.Date(fechaInicioPeriodo.getTime()));
				calDiaInicioAnio.set(Calendar.YEAR, calUtilidad.get(Calendar.YEAR));
				mapaResultado.put("fechaInicioPeriodo", utilitariosLocal.getFechaUppercase(calDiaInicioAnio.getTime()));
				
				Date fechaFinPeriodo = contratoUtilidadIf.getFechaFin();
				Calendar calDiaFinAnio = new GregorianCalendar();
				calDiaFinAnio.setTime(new java.util.Date(fechaFinPeriodo.getTime()));
				calDiaFinAnio.set(Calendar.YEAR, calUtilidad.get(Calendar.YEAR));
				mapaResultado.put("fechaFinPeriodo", utilitariosLocal.getFechaUppercase(calDiaFinAnio.getTime()));
				
				long diaEnMilisegundos = 1000 * 60 * 60 * 24;
				
				Calendar calHace18Anios = new GregorianCalendar();
				calHace18Anios.add(Calendar.YEAR, -18);
				calHace18Anios.set(Calendar.DAY_OF_MONTH, calHace18Anios.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date fechaHace18Anios = new Date(calHace18Anios.getTime().getTime());
				
				Long totalDiasTrabajados = 0L;
				Long totalDiasCargas= 0L;
				
				Map<Long,TipoEmpleadoIf> mapaTipoEmpleado =  new HashMap<Long, TipoEmpleadoIf>();
				Map<Long,ContratoIf> mapaContratos = new HashMap<Long, ContratoIf>();
				
				Map<Long,RolPagoDetalleUtilidadIf> mapaDetallaUtilidades = new HashMap<Long, RolPagoDetalleUtilidadIf>();
				Collection<DatoObservacion> errores = new ArrayList<DatoObservacion>();
				for (ContratoIf contratoTmp : contratos) {
					
					mapaContratos.put(contratoTmp.getId(), contratoTmp);
					
					RolPagoDetalleUtilidadIf cud = new RolPagoDetalleUtilidadData();
					cud.setContratoId(contratoTmp.getId());
					cud.setRolpagoId(rolPagoIf.getId());
					cud.setContratoUtilidadId(contratoUtilidadIf.getId());
					
					EmpleadoIf empleado = empleadoLocal.getEmpleado(contratoTmp.getEmpleadoId());
					
					if ( empleado.getTipoempleadoId() == null ){
						agregarErrores(errores, empleado,"Tipo de empleado no definido.");
						continue;
					}
					TipoEmpleadoIf te = verificarTipoEmpleado(mapaTipoEmpleado, empleado.getTipoempleadoId());
					cud.setCargo(te.getNombre());
					
					cud.setFechaIngreso(contratoTmp.getFechaInicio());
					
					//***GENERO***
					Collection<EmpleadoPersonalIf> datosPersonales = empleadoPersonalLocal.findEmpleadoPersonalByEmpleadoId(empleado.getId());
					if ( datosPersonales.size() == 0 ){
						//throw new GenericBusinessException("Datos personales del empleado no están ingresados !!");
						agregarErrores(errores, empleado,"Datos personales del empleado no están ingresados");
						continue;
					}
					else if ( datosPersonales.size() > 1 ){
						//throw new GenericBusinessException("Existe mas de un registro para Datos personales del empleado !!");
						agregarErrores(errores, empleado,"Existe mas de un registro para Datos personales del empleado");
						continue;
					}
					EmpleadoPersonalIf datoPersonal = datosPersonales.iterator().next();
					//info.setGenero(datoPersonal.getSexo().substring(0,1));
					cud.setGenero(datoPersonal.getSexo().substring(0,1));
					
					//***DIAS TRABAJADOS***
					int diasTrabajados = 365;
					Date fechaInicioContrato = contratoTmp.getFechaInicio();
					if ( fechaInicioContrato.compareTo(fechaInicioPeriodo) > 0 ){
						Long diferenciaDias = fechaFinPeriodo.getTime() - fechaInicioContrato.getTime();
						diferenciaDias = diferenciaDias / diaEnMilisegundos;
						diasTrabajados = diferenciaDias.intValue(); 
					}
					cud.setDiasLaborados(diasTrabajados);
					totalDiasTrabajados += diasTrabajados;
					
					//***CARGAS***
					int numeroCargas = 0;
					Collection<EmpleadoFamiliaresIf> familiares = empleadoFamiliarLocal.findEmpleadoFamiliaresByEmpleadoId(empleado.getId());
					for ( EmpleadoFamiliaresIf familiar : familiares ){
						String tipoFamiliar = familiar.getTipo();
						if ( ParentezcoEnum.HIJO.getLetra().equals(tipoFamiliar) ||
							 ParentezcoEnum.CONYUGUE.getLetra().equals(tipoFamiliar) ){
							Timestamp fechaNacimientoTs = familiar.getFechaNacimiento();
							Date fechaNacimiento = new Date(fechaNacimientoTs.getTime());
							
							//No se toma en cuenta hijos mayores de 18 anios
							if ( ParentezcoEnum.HIJO.getLetra().equals(tipoFamiliar) && 
								 fechaNacimiento.compareTo(fechaHace18Anios) < 0 )
								continue;
							
							numeroCargas++;
						}
					}
					cud.setNumeroCargas(numeroCargas);
					cud.setDiasCargas(diasTrabajados*numeroCargas);
					
					totalDiasCargas += cud.getDiasCargas();
					
					mapaDetallaUtilidades.put(contratoTmp.getId(), cud);
				}
				
				Double utilidadPorDiaContrato = valorPorcentajeContratos / totalDiasTrabajados;
				Double utilidadPorDiaCarga = valorPorcentajeCargas / totalDiasCargas;
				
				for ( Long contratoTmpId : mapaDetallaUtilidades.keySet() ){
					RolPagoDetalleUtilidadIf cud = mapaDetallaUtilidades.get(contratoTmpId);
					Double valorUtilidad = utilitariosLocal.redondeoValor(cud.getDiasLaborados()*utilidadPorDiaContrato);
					cud.setUtilidadContrato(new BigDecimal(valorUtilidad));
					Double valorUtilidadCarga = utilitariosLocal.redondeoValor(cud.getDiasCargas()*utilidadPorDiaCarga);
					cud.setUtilidadCargas(new BigDecimal(valorUtilidadCarga));
					
					ContratoIf contrato = mapaContratos.get(contratoTmpId);
					Double total = utilitariosLocal.redondeoValor(
						cud.getUtilidadContrato().doubleValue() + cud.getUtilidadCargas().doubleValue() );
					BigDecimal totalBd = new BigDecimal(total);
					cud.setTotal( totalBd );
					rolPagoDetalleUtilidadLocal.addRolPagoDetalleUtilidad(cud);
					
					RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalle(
						rolPagoIf, contrato, rubroUtilidades.getId(), totalBd, 
						"UTILIDAD DESDE "+utilitariosLocal.getFechaCortaUppercase(calDiaInicioAnio.getTime())+
						" HASTA "+utilitariosLocal.getFechaCortaUppercase(calDiaFinAnio.getTime()));
					rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
						
					tieneDetalle = true;
				}
				mapaResultado.put("observaciones", errores);
			} else
				throw new GenericBusinessException("Generación de Rol de Pago para "+ tipoRolIf.getNombre() + " no implementado");

			if (!tieneDetalle)
				throw new GenericBusinessException("No existe información del "+ tipoRolIf.getNombre()+ " para crear detalle de Rol de Pago");
		} catch (GenericBusinessException ex) {
			ctx.setRollbackOnly();
			ex.printStackTrace();
			throw new GenericBusinessException(ex.getMessage());
		} catch (Exception ex) {
			ctx.setRollbackOnly();
			ex.printStackTrace();
			throw new GenericBusinessException("Error general al guardar Rol de Pago.");
		}
		return mapaResultado;
	}

	private BigDecimal calculoDecimoCuartoSMM(Date fechaRolPago,
			BigDecimal valorRubroAplicado, int diaSalida) {
		double fraccionDia = valorRubroAplicado.doubleValue() / fechaRolPago.getDate();
		int diasTrabajadosMes = diaSalida;
		valorRubroAplicado = BigDecimal.valueOf(fraccionDia * diasTrabajadosMes);
		return valorRubroAplicado;
	}
	
	private String getObservacion(Date fechaRolPago, ContratoIf contratoTmp,
			String observacion, Date fechaInicioContrato, Date fechaFinContrato) {
		if ( esFechaContrato(fechaInicioContrato, fechaRolPago) )
			observacion = "CALCULADO DESDE EL "+ utilitariosLocal.getFechaUppercase(contratoTmp.getFechaInicio());

		// if ( rubroIf.getTipoRubro().equals("S") )
		if (esFechaContrato(fechaFinContrato,fechaRolPago))
			observacion += (" HASTA EL " + utilitariosLocal.getFechaUppercase(contratoTmp.getFechaFin()));
		return observacion;
	}

	private boolean contieneRubroFondoReserva(Map<Long, RubroIf> mapaRubros, 
			ContratoIf contratoTmp) throws GenericBusinessException {
		Map<String,Object> mapaBusqueda = new HashMap<String, Object>();
		mapaBusqueda.put("contratoId", contratoTmp.getId());
		Collection<ContratoRubroIf> contratosRubro = contratoRubroLocal
			.findContratoRubroByQuery(mapaBusqueda);
		for ( ContratoRubroIf cr : contratosRubro ){
			RubroIf rubro = verificarRubro(mapaRubros, cr.getRubroId());
			if ( rubro.getCodigo().startsWith(NominaParametros.BASE_CODIGO_FONDO_RESERVA_PARAMETRO_EMPRESA) ){
				return true;
			}
		}
		return false;
	}

	private void agregarErrores(Collection<DatoObservacion> errores,
		EmpleadoIf empleado,String descripcionError) {
		DatoObservacion error = null;
		String nombreEmpleado = empleado.getApellidos()+" "+empleado.getNombres();
		for ( DatoObservacion dao : errores ){
			if ( dao.getContenidoTitulo().equals(nombreEmpleado) )
				error = dao;
		}
		if ( error == null ){
			error = new DatoObservacion();
			error.setContenidoTitulo(nombreEmpleado);
			error.setDescripcionError(new ArrayList<String>());
			errores.add(error);
		}
		Collection<String> observaciones = error.getDescripcionError();
		observaciones.add(descripcionError);
	}

	private TipoEmpleadoIf verificarTipoEmpleado (Map<Long,TipoEmpleadoIf> mapaTipoEmpleado,Long tipoEmpleadoId) throws GenericBusinessException{
		TipoEmpleadoIf teIf = mapaTipoEmpleado.get(tipoEmpleadoId);
		if ( teIf == null ){
			teIf = tipoEmpleadoLocal.getTipoEmpleado(tipoEmpleadoId);
			mapaTipoEmpleado.put(teIf.getId(), teIf);
		}
		return teIf;
	}
	
	private BigDecimal calcularValorFondoReserva(Date fechaRolPago,
			GregorianCalendar gce, ContratoIf contratoTmp,
			Calendar calInicioContrato, Date fechaInicioContratoDespuesDelAnio,
			BigDecimal valorFondoReserva, TipoRol tipoRol) {
		
		/*Double valor = fechaRolPago.compareTo(fechaInicioContratoDespuesDelAnio) >= 0 ? valorFondoReserva.doubleValue() : 0D;
		
		if(contratoTmp.getCodigo().equals("MCABRERA")){
			valor = valorFondoReserva.doubleValue();
		}*/
		
		//AHORA REVISO SI EL EMPLEADO ANTES YA TRABAJO EN LA EMPRESA Y TIENE DIAS PREVIOS QUE SUMEN PARA VER SI TIENE O NO FONDO DE RESERVA
		java.util.Calendar fechaTemp = new GregorianCalendar(fechaRolPago.getYear()+1900, fechaRolPago.getMonth(), fechaRolPago.getDate());
		//si tiene dias previos sumo los dias a la fecha de rol de pago
		
		int diaInicioContrato=0;
		int diaFinMes=fechaRolPago.getDate();
		
		if(contratoTmp.getFondoReservaDiasPrevios() != null)
			fechaTemp.add(Calendar.DATE, contratoTmp.getFondoReservaDiasPrevios());
					
		//esta fecha se usara para ver si ya le toca recibir fondo de reserva, si no tiene dias previos sera igual a la fecha de rol de pago.
		java.sql.Date fechaFondoReserva = new java.sql.Date(fechaTemp.getTimeInMillis());
		
		Double valor = fechaFondoReserva.compareTo(fechaInicioContratoDespuesDelAnio) >= 0 ? valorFondoReserva.doubleValue() : 0D;
		
		//INI_CAMBIO_20140707 Se implementan validaciones para calculo del Rol por Fondos de Reserva 
		if ( esFechaContrato(fechaInicioContratoDespuesDelAnio, fechaRolPago) 
				&& contratoTmp.getFechaFin().compareTo(fechaInicioContratoDespuesDelAnio)>0){
			diaInicioContrato = calInicioContrato.get(Calendar.DAY_OF_MONTH);//-1; 
			if(diaInicioContrato == 31){
				valor = 0.0;
			//Esta validacion calcula el FR cuando el mismo se presenta en el Rol Mensual		
			}else if(contratoTmp.getFechaFin().getTime() < fechaRolPago.getTime() 
						&& tipoRol == TipoRol.MENSUAL){ 
						valor = valorFondoReserva.doubleValue();
						valor = valor * (contratoTmp.getFechaFin().getDate()) / diaFinMes; 
						valor = valor * (contratoTmp.getFechaFin().getDate() - diaInicioContrato) / diaFinMes;
			}else if(contratoTmp.getFechaFin().getTime() < fechaRolPago.getTime()){ 
						valor = valorFondoReserva.doubleValue();
						valor = valor * (contratoTmp.getFechaFin().getDate() - diaInicioContrato) / diaFinMes;
			}else{
				//Se comenta por estandarizacion a 30 dias para el calculo del Fondo de Reserva
				//int diaFinMes = gce.getActualMaximum(Calendar.DAY_OF_MONTH);
						valor = valorFondoReserva.doubleValue();
						valor = valor * (diaFinMes - diaInicioContrato) / diaFinMes;
			}
	
		}else if(esFechaContrato(fechaInicioContratoDespuesDelAnio, fechaRolPago)
					&& contratoTmp.getFechaFin().compareTo(fechaInicioContratoDespuesDelAnio)<=0){
					valor = 0.0;
		//Esta validacion calcula el FR cuando el mismo se presenta en el Rol Mensual			
		}else if(tipoRol == TipoRol.MENSUAL && esFechaContrato(contratoTmp.getFechaFin(),fechaRolPago) 
					&& contratoTmp.getFechaFin().getDate() < fechaRolPago.getDate()
					&& contratoTmp.getFechaFin().compareTo(fechaInicioContratoDespuesDelAnio)>0){
					valor = valorFondoReserva.doubleValue();
					valor = valor * (contratoTmp.getFechaFin().getDate()) / diaFinMes; 			
		}else if(esFechaContrato(contratoTmp.getFechaFin(),fechaRolPago) 
					&& contratoTmp.getFechaFin().getDate() < fechaRolPago.getDate()
					&& contratoTmp.getFechaFin().compareTo(fechaInicioContratoDespuesDelAnio)>0){
					valor = valorFondoReserva.doubleValue();
		}
		//FIN_CAMBIO_20140707
		
		valor = utilitariosLocal.redondeoValor(valor);
		valorFondoReserva = new BigDecimal(valor);
		return valorFondoReserva;
	}

	private Double CalcularTotalGastosDeducibles(RolPagoIf rolPagoIf,
			ContratoIf contratoTmp) throws GenericBusinessException {
		
		/*Map<String, Object> mapaCgd = new HashMap<String, Object>();
		mapaCgd.put("contratoId", contratoTmp.getId());
		mapaCgd.put("anio", rolPagoIf.getAnio());
		Collection<ContratoGastoDeducibleIf> gastosDeducibles = contratoGastoDeducibleLocal
				.findContratoGastoDeducibleByQuery(mapaCgd);*/
		
		int anio = Integer.parseInt( rolPagoIf.getAnio() );
		int mes = Integer.parseInt( rolPagoIf.getMes() )-1;
		Calendar calRol = new GregorianCalendar(anio,mes,1);
		calRol.set(Calendar.DAY_OF_MONTH, calRol.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		Collection<ContratoGastoDeducibleIf> gastosDeducibles = 
			contratoGastoDeducibleLocal.findContratoGastoDeducibleByFechas(
				contratoTmp.getId(), new Date(calRol.getTime().getTime()) );
		
		Double totalGastosDeducibles = 0.0;
		for (ContratoGastoDeducibleIf cgd : gastosDeducibles) {
			totalGastosDeducibles += cgd.getValor().doubleValue();
		}
		totalGastosDeducibles = utilitariosLocal
				.redondeoValor(totalGastosDeducibles);
		return totalGastosDeducibles;
	}

	private Double calcularValoresParaProvisionales(
			Map<Long, RubroIf> mapaRubros, Map<String, Object> mapa,
			ContratoIf contratoTmp, Map<String, BigDecimal> codigoValorxRubro,
			Set<String> codigoRubrosEspecialesSet)
			throws GenericBusinessException {
		Double totalIngresos = 0.0;
		// mapa.put("rubroEventualId", "null");
		mapa.put("contratoId", contratoTmp.getId());
		Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapa);
		for (RolPagoDetalleIf rpd : detalles) {

			RubroIf rubro;
			if (rpd.getRubroEventualId() != null) {
				RubroEventualIf re = rubroEventualLocal.getRubroEventual(rpd.getRubroEventualId());
				rubro = verificarRubro(mapaRubros, re.getRubroId());
			} else {
				rubro = verificarRubro(mapaRubros, rpd.getRubroId());
			}
			if (codigoRubrosEspecialesSet != null && codigoRubrosEspecialesSet.contains(rubro.getCodigo())) {
				codigoValorxRubro.put(rubro.getCodigo(), rpd.getValor());
			}
			
			/*if(!contratoTmp.getCodigo().equals("RYJA")){
				codigoValorxRubro.put("OI", new BigDecimal(0));
			}
			
			if(rubro.getCodigo().equals("OI") && !contratoTmp.getCodigo().equals("RYJA")){
				rpd.setValor(new BigDecimal(0));
			}*/
			
			if (!TipoRubro.PROVISION.getLetra().equals(rubro.getTipoRubro())) {
				codigoValorxRubro.put(rubro.getCodigo(), rpd.getValor());

				if (TipoRubro.SUELDO.getLetra().equals(rubro.getTipoRubro())) {
					totalIngresos += utilitariosLocal.redondeoValor(rpd.getValor()).doubleValue();

					// } else if ( rubroIf.getTipoRubro().equals("B") ){
				} else if (TipoRubro.BENEFICIO.getLetra().equals(rubro.getTipoRubro()) || TipoRubro.COMISION.getLetra().equals(rubro.getTipoRubro())) {
					totalIngresos += utilitariosLocal.redondeoValor(rpd.getValor()).doubleValue();
				}

			}
		}
		totalIngresos = utilitariosLocal.redondeoValor(totalIngresos);
		return totalIngresos;
	}

	private String obtenerQueryRolesPagoAnterioresAlActual(
			Collection<RolPagoIf> rolesPagoDesdeEnero) {
		String queryStringRolesPago = "";
		if (rolesPagoDesdeEnero.size() > 0) {
			queryStringRolesPago = " (";
			for (RolPagoIf rp : rolesPagoDesdeEnero) {
				queryStringRolesPago += (rp.getId() + ",");
			}
			queryStringRolesPago = queryStringRolesPago.substring(0,
					queryStringRolesPago.length() - 1);
			queryStringRolesPago += " )";
		}
		return queryStringRolesPago;
	}

	class RolPagoDetalleSet extends HashSet<RolPagoDetalleIf> implements
			Comparator<RolPagoDetalleIf>, Serializable {

		private static final long serialVersionUID = -7936541313269728647L;

		public int compare(RolPagoDetalleIf o1, RolPagoDetalleIf o2) {
			return o1.getId().compareTo(o2.getId());
		}

	}

	private RubroEventualIf buscarEnMapaEventualesQuincenalByRubro(
			Map<Long, RolPagoDetalleIf> mapaEventualesQuincenal, Long rubroId)
			throws GenericBusinessException {
		for (Iterator<Long> itMapa = mapaEventualesQuincenal.keySet()
				.iterator(); itMapa.hasNext();) {
			RolPagoDetalleIf rpd = mapaEventualesQuincenal.get(itMapa.next());
			RubroEventualIf re = rubroEventualLocal.getRubroEventual(rpd
					.getRubroEventualId());
			if (rubroId.equals(re.getRubroId())) {
				mapaEventualesQuincenal.remove(rpd.getId());
				return re;
			}
		}
		return null;
	}

	// DETERMINA SI UN RUBRO YA EXISTE EN EL DETALLE DEL ROL DE UN CONTRATO
	private boolean existeRubroEnRolPagoDetalle(
			Map<String, Object> mapaBusquedaDetalle, Long rubroId)
			throws GenericBusinessException {
		mapaBusquedaDetalle.put("rubroId", rubroId);
		Collection<RolPagoDetalleIf> detallesEncontrados = rolPagoDetalleLocal
				.findRolPagoDetalleByQueryByEstados(mapaBusquedaDetalle,
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
						EstadoRolPagoDetalle.PAGADO.getLetra());
		if (detallesEncontrados.size() == 0)
			return false;
		return true;
	}

	private TipoRolIf buscarTipoRolIf(Collection<TipoRolIf> tiposRolPago,
			String nombreTipoRol) {
		TipoRolIf tipoRol = null;
		for (TipoRolIf tipoRolI : tiposRolPago) {
			if (tipoRolI.getNombre().contains(nombreTipoRol)) {
				tipoRol = tipoRolI;
				break;
			}
		}
		return tipoRol;
	}

	private void registrarProvisionales(
			RolPagoIf rolPagoIf,
			Date fechaRolPago,// ,Map<Long, RubroIf> mapaRubros
			ContratoIf contratoIf, Double totalIngresos, RubroIf rubroIf,
			TipoRol tipoRol, Map<String, BigDecimal> codigoValorxRubro,
			RubroIf rubroSMV) throws GenericBusinessException, ParseException {

		BigDecimal valor = null;
		if (contratoIf.getCodigo().equals("KRKR"))
			System.out.println("HERE");
		if (rubroIf.getId().equals(rubroSMV.getId())) {
			valor = codigoValorxRubro
					.get(NominaParametros.CODIGO_SALARIO_MINIMO_VITAL);
		} else {
			valor = calcularValorDelMes(TipoRol.TIPO_ROL_PROVISION,
					fechaRolPago, contratoIf, totalIngresos, rubroIf,
					codigoValorxRubro);
		}
		RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalleProvisionado(
				rolPagoIf, contratoIf, rubroIf.getId(), valor, null);
		rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
	}

	private void registrarDecimosAportesFondos(
			RolPagoIf rolPagoIf,
			Date fechaRolPago// ,Map<Long, RubroIf> mapaRubros
			, ContratoIf contratoIf, Double totalIngresos, RubroIf rubroIf,
			TipoRol tipoRol, Map<String, BigDecimal> codigoValorxRubro)
			throws GenericBusinessException, ParseException {
		
		//formo fecha del rol solo con año, mes y dia para eliminar hora y poder comparar
		Date fechaInicioRolPago = new Date(fechaRolPago.getYear(), fechaRolPago.getMonth(), fechaRolPago.getDate());
		Date fechaFinRolPago = new Date(fechaRolPago.getYear(), fechaRolPago.getMonth(), fechaRolPago.getDate());
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("contratoId", contratoIf.getId());
		mapa.put("rubroId", rubroIf.getId());
		Collection<ContratoRubroIf> contratosRubros = contratoRubroLocal.findContratoRubroByQuery(mapa);
		
		for (ContratoRubroIf contratoRubro : contratosRubros) {
			
			//tengo que encerar las horas para la comparacion							
			Date fechaInicioRubroContrato = null;
			Date fechaFinRubroContrato = null;
			if(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null){
				fechaInicioRubroContrato = new Date(contratoRubro.getFechaInicio().getYear(), contratoRubro.getFechaInicio().getMonth(), contratoRubro.getFechaInicio().getDate());
				fechaFinRubroContrato = new Date(contratoRubro.getFechaFin().getYear(), contratoRubro.getFechaFin().getMonth(), contratoRubro.getFechaFin().getDate());
			}
			
			//si el rubro del contrato no tiene fechas inicio y fin, o
			//si tiene fechas deben abarcar la fecha del rol
			if((contratoRubro.getFechaInicio() == null || contratoRubro.getFechaFin() == null) || 
					(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null 
					&& fechaInicioRubroContrato.compareTo(fechaInicioRolPago) <= 0 
					&& fechaFinRubroContrato.compareTo(fechaFinRolPago) >= 0)){
				
				BigDecimal valor = calcularValorDelMes(tipoRol, fechaRolPago, contratoIf, totalIngresos, rubroIf, codigoValorxRubro);
				RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalleProvisionado(rolPagoIf, contratoIf, contratoRubro, valor, null);
				rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
			}		
		}
	}

	private void registrarVacaciones(RolPagoIf rolPagoIf, Date fechaRolPago,
			ContratoIf contratoIf, Double totalIngresos, RubroIf rubroIf,
			TipoRol tipoRol, Map<String, BigDecimal> codigoValorxRubro)
			throws GenericBusinessException, ParseException {
		
		//formo fecha del rol solo con año, mes y dia para eliminar hora y poder comparar
		Date fechaInicioRolPago = new Date(fechaRolPago.getYear(), fechaRolPago.getMonth(), fechaRolPago.getDate());
		Date fechaFinRolPago = new Date(fechaRolPago.getYear(), fechaRolPago.getMonth(), fechaRolPago.getDate());
				
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("contratoId", contratoIf.getId());
		mapa.put("rubroId", rubroIf.getId());
		Collection<ContratoRubroIf> contratosRubros = contratoRubroLocal.findContratoRubroByQuery(mapa);
		
		for (ContratoRubroIf contratoRubro : contratosRubros) {
			
			//tengo que encerar las horas para la comparacion							
			Date fechaInicioRubroContrato = null;
			Date fechaFinRubroContrato = null;
			if(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null){
				fechaInicioRubroContrato = new Date(contratoRubro.getFechaInicio().getYear(), contratoRubro.getFechaInicio().getMonth(), contratoRubro.getFechaInicio().getDate());
				fechaFinRubroContrato = new Date(contratoRubro.getFechaFin().getYear(), contratoRubro.getFechaFin().getMonth(), contratoRubro.getFechaFin().getDate());
			}
			
			//si el rubro del contrato no tiene fechas inicio y fin, o
			//si tiene fechas deben abarcar la fecha del rol
			if((contratoRubro.getFechaInicio() == null || contratoRubro.getFechaFin() == null) || 
					(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null 
					&& fechaInicioRubroContrato.compareTo(fechaInicioRolPago) <= 0 
					&& fechaFinRubroContrato.compareTo(fechaFinRolPago) >= 0)){
				
				BigDecimal valor = calcularValorDelMes(tipoRol, fechaRolPago, contratoIf, totalIngresos, rubroIf, codigoValorxRubro);
				RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalleProvisionado(rolPagoIf, contratoIf, contratoRubro, valor, null);
				rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
			}			
		}
	}

	private BigDecimal calcularValorDelMes(TipoRol tipoRol, Date fechaRolPago,
			ContratoIf contratoIf, Double totalIngresos, RubroIf rubroIf,
			Map<String, BigDecimal> codigoValorxRubro) throws ParseException,
			GenericBusinessException {
		
		if(codigoValorxRubro != null && codigoValorxRubro.get("OI") == null)
			codigoValorxRubro.put("OI", new BigDecimal(0));
		
		Double valor = 0.0;
		
		Date fechaInicioContrato = contratoIf.getFechaInicio();
		Calendar calInicioContrato = new GregorianCalendar();
		calInicioContrato.setTime(new java.util.Date(fechaInicioContrato.getTime()));
		
		Date fechaFinContrato = contratoIf.getFechaFin();
		Calendar calFinContrato = new GregorianCalendar();
		calFinContrato.setTime(new java.util.Date(fechaFinContrato.getTime()));
		System.out.println("----------- CONTRATO: " + contratoIf.getCodigo());
		
		if(contratoIf.getCodigo().equals("ISUAREZ")){
			System.out.println("A");
		}
		
		if (tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL) {
			if (esFechaContrato(fechaInicioContrato, fechaRolPago)
					|| esFechaContrato(fechaFinContrato, fechaRolPago)) {
				//int mesInicioContrato = fechaInicioContrato.getMonth();
				//int diaFinMesQuincena;
				//int diaFin = 0;
				int diaFinContrato = calFinContrato.get(Calendar.DAY_OF_MONTH);
				
				/*if (tipoRol == TipoRol.QUINCENAL) {
					diaFinMesQuincena = 15;
					java.sql.Date fechaQuincena = new java.sql.Date(fechaRolPago.getYear(), fechaRolPago.getMonth(), 15);
					if (fechaFinContrato.compareTo(fechaQuincena) >= 0) {
						if (fechaInicioContrato.getMonth() == fechaRolPago.getMonth() && fechaInicioContrato.getYear() == fechaRolPago.getYear()) {
							diaFin = diaFinMesQuincena - fechaInicioContrato.getDate();
						} else if (fechaInicioContrato.getMonth() < fechaRolPago.getMonth() && fechaInicioContrato.getYear() <= fechaRolPago.getYear()) {
							diaFin = diaFinMesQuincena;
						} else if (fechaInicioContrato.getMonth() > fechaRolPago.getMonth() && fechaInicioContrato.getYear() < fechaRolPago.getYear())
							diaFin = diaFinMesQuincena;
					}						
					//-- diaFin = diaFinContrato < diaFinMesQuincena ? diaFinContrato : diaFinMesQuincena;
				} else {
					diaFinMesQuincena = calInicioContrato.getActualMaximum(Calendar.DAY_OF_MONTH);
					diaFin = diaFinMesQuincena;
					// Si es que el mes y el anio del fin de contrato y el rol de pago coinciden, hay
					// que sacar el dia menor entre los dos para el calculo proporcional del valor.
					if (esFechaContrato(fechaFinContrato, fechaRolPago)) {
						diaFin = Math.min(diaFinMesQuincena, diaFinContrato);
					}
				}*/
				
				int diaInicioContrato = 0;
				int totalDiasLaborados = 0;
				int ultimoDiaMes = 0;
				
				//INI_CAMBIO_20140701 Se crean validaciones para calculo de roles mensual y quincenal debido
				//a estandarizacion de dias a 30
				if (tipoRol == TipoRol.QUINCENAL) {
					diaInicioContrato = calInicioContrato.get(Calendar.DAY_OF_MONTH) - 1; //Día de ingreso inclusive
					Calendar cal = Calendar.getInstance();
					cal.setTime(fechaRolPago);
					ultimoDiaMes = fechaRolPago.getDate();
					
					if (esFechaContrato(fechaInicioContrato, fechaRolPago)){
						//si la fecha de ingreso fuese posterior al dia 15 se mostrara como valor 0 en rol de quincena y mensual 
						if(fechaInicioContrato.getDate() > 15)
							totalDiasLaborados = 0;
						else if(fechaInicioContrato.getDate() > 1 && fechaFinContrato.getDate()<30 &&
								esFechaContrato(fechaFinContrato, fechaRolPago))
							totalDiasLaborados = fechaFinContrato.getDate()- diaInicioContrato;
						else if(fechaInicioContrato.getDate() > 1 )
							totalDiasLaborados = ultimoDiaMes-diaInicioContrato;
						else if(fechaInicioContrato.getDate() == 1 && 
								(fechaFinContrato.getDate()==30 || fechaFinContrato.getDate()==31))
							totalDiasLaborados = ultimoDiaMes;
						else if(fechaInicioContrato.getDate() == 1 && fechaFinContrato.getDate()<31
								&& esFechaContrato(fechaFinContrato, fechaRolPago))
							totalDiasLaborados = diaFinContrato;
						else if(fechaFinContrato.getTime() > fechaRolPago.getTime())
							totalDiasLaborados = ultimoDiaMes-diaInicioContrato;
										
					}else if (esFechaContrato(fechaFinContrato, fechaRolPago)){
						if(diaFinContrato == 30 || diaFinContrato==31)
							totalDiasLaborados = ultimoDiaMes;
						else if(diaFinContrato>=1) 
							totalDiasLaborados = diaFinContrato;
					}																
				}else if (tipoRol == TipoRol.MENSUAL){
					diaInicioContrato = calInicioContrato.get(Calendar.DAY_OF_MONTH) - 1; //Día de ingreso inclusive
					Calendar cal = Calendar.getInstance();
					cal.setTime(fechaRolPago);
					ultimoDiaMes = fechaRolPago.getDate();
					
					if (esFechaContrato(fechaInicioContrato, fechaRolPago) 
							&& esFechaContrato(fechaFinContrato, fechaRolPago)){
						if(fechaInicioContrato.getDate() > 1 && fechaFinContrato.getDate()<fechaRolPago.getDate())
							totalDiasLaborados = diaFinContrato - diaInicioContrato;	
						else if (fechaInicioContrato.getDate()==1 && fechaFinContrato.getDate()>=fechaRolPago.getDate())
							totalDiasLaborados=fechaRolPago.getDate();
						else if(fechaInicioContrato.getDate()==1)
							totalDiasLaborados = fechaFinContrato.getDate();
						else if(fechaFinContrato.getDate()>=fechaRolPago.getDate())
								if(fechaInicioContrato.getDate()==31 && fechaFinContrato.getDate()==31)
									totalDiasLaborados=1;
								else
									totalDiasLaborados = ultimoDiaMes - diaInicioContrato;
						
					}else if (esFechaContrato(fechaInicioContrato, fechaRolPago)){
							//valida en caso de que el inicio de un contrato se de el dia 31 
							if(fechaInicioContrato.getDate()==31)
								totalDiasLaborados = 1;
							else
								totalDiasLaborados = ultimoDiaMes - diaInicioContrato; //original
					}else if (esFechaContrato(fechaFinContrato, fechaRolPago)){
						if(fechaFinContrato.getDate()==31)
							totalDiasLaborados = fechaRolPago.getDate();
						else
							totalDiasLaborados = fechaFinContrato.getDate();
					}
				}
				valor = (totalIngresos/ultimoDiaMes) * totalDiasLaborados;
				//FIN_CAMBIO_20140701	
			} else {
				valor = totalIngresos;
			}
		} else if (tipoRol == TipoRol.DECIMO_TERCERO) {
			String politica = rubroIf.getPolitica();
			if (!politica.contains("TOTAL"))
				throw new GenericBusinessException("No se encontró la palabra TOTAL en la politica del Rubro Decimo Tercero");
			politica = politica.replaceAll("TOTAL", String.valueOf(totalIngresos));
			valor = utilitariosLocal.evaluadorExpresionJavaScript(politica).doubleValue();
		} else if (tipoRol == TipoRol.VACACIONES) {
			String politica = rubroIf.getPolitica();
			if (!politica.contains("TOTAL"))
				throw new GenericBusinessException("No se encontró la palabra TOTAL en la politica del Rubro Vacaciones");
			politica = politica.replaceAll("TOTAL", String.valueOf(totalIngresos));
			valor = utilitariosLocal.evaluadorExpresionJavaScript(politica).doubleValue();
		} else if (tipoRol == TipoRol.DECIMO_CUARTO
				|| tipoRol == TipoRol.APORTE_PATRONAL
				|| tipoRol == TipoRol.FONDO_RESERVA) {
			String politicaCambiada = cambiarPolitica(rubroIf,codigoValorxRubro);
			valor = utilitariosLocal.evaluadorExpresionJavaScript(politicaCambiada).doubleValue();
		} else if (tipoRol == TipoRol.TIPO_ROL_PROVISION) {
			// String politica = rubroIf.getPolitica();
			// if ( politica.contains("TOTAL") )
			// politica = politica.replaceAll( "TOTAL",
			// String.valueOf(totalIngresos) );
			codigoValorxRubro.put("TOTAL", new BigDecimal(totalIngresos));
			// if ( !rubroIf.getCodigo().equals("SMV") ){
			if (!rubroIf.getModoOperacion().equals(ModoOperacionRubro.REGISTRADO.getLetra())) {
				String politicaCambiada = cambiarPolitica(rubroIf,codigoValorxRubro);
				valor = utilitariosLocal.evaluadorExpresionJavaScript(politicaCambiada).doubleValue();
			}
		} else
			throw new GenericBusinessException("Cálculo de Valor para Tipo de Rol " + tipoRol + " no implementado");
		return utilitariosLocal.redondeoValor(new BigDecimal(valor));
	}

	private boolean esFechaContrato(Date fechaContrato,
			Date fechaRolPago) {
		
		Calendar calFechaContrato = new GregorianCalendar();
		calFechaContrato.setTime(new java.util.Date(fechaContrato.getTime()));
		
		Calendar calRolPago = new GregorianCalendar();
		calRolPago.setTime(new java.util.Date(fechaRolPago.getTime()));
		
		return (calRolPago.get(Calendar.MONTH) == calFechaContrato.get(Calendar.MONTH) && 
			calRolPago.get(Calendar.YEAR) == calFechaContrato.get(Calendar.YEAR));
			//&& calFechaContrato.get(Calendar.DATE) > calRolPago.get(Calendar.DATE)); //adicionado
	}
	
	private RubroIf verificarRubro(Map<Long, RubroIf> mapa, Long rubroId)
			throws GenericBusinessException {
		if (!mapa.containsKey(rubroId)) {
			RubroIf rubroIf = rubroLocal.getRubro(rubroId);
			mapa.put(rubroId, rubroIf);
			return rubroIf;
		} else
			return mapa.get(rubroId);
	}

	private RubroIf verificarRubro(Map<String, RubroIf> mapa, String codigo)
			throws GenericBusinessException {
		if (!mapa.containsKey(codigo)) {
			Collection<RubroIf> rubros = rubroLocal.findRubroByCodigo(codigo);
			if (rubros.size() == 0)
				throw new GenericBusinessException(
						"No existe Rubro con codigo " + codigo + " !!");
			RubroIf rubroIf = rubros.iterator().next();
			mapa.put(codigo, rubroIf);
			return rubroIf;
		} else
			return mapa.get(codigo);
	}

	/*
	 * private TipoRolIf verificarTipoRolIf(Map<Long,TipoRolIf> mapa,Long
	 * tipoRolId) throws GenericBusinessException{ if (
	 * !mapa.containsKey(tipoRolId) ){ TipoRolIf tr =
	 * tipoRolLocal.getTipoRol(tipoRolId); mapa.put(tipoRolId, tr); return tr; }
	 * else return mapa.get(tipoRolId); }
	 */

	private void registrarEgresosQuincenales(RolPagoIf rolPagoIf,
			RubroIf rubroQuincenal, TipoRolIf tipoRolQuincenal,
			Collection<RolPagoDetalleIf> rolPagoDetalleCollection,
			ContratoIf contratoIf,
			// Map<String, Object> mapaBusquedaDetalle) throws
			// GenericBusinessException {
			Map<Long, RubroIf> mapaRubros,
			Map<Long, RolPagoDetalleIf> mapaEventualesQuincena)
			throws GenericBusinessException {
		Map<String, Object> mapaRolPago = new HashMap<String, Object>();
		mapaRolPago.put("tiporolId", tipoRolQuincenal.getId());
		mapaRolPago.put("tipocontratoId", rolPagoIf.getTipocontratoId());
		mapaRolPago.put("mes", rolPagoIf.getMes());
		mapaRolPago.put("anio", rolPagoIf.getAnio());
		Collection<RolPagoIf> rolPagoQuincenales = findRolPagoByQuery(mapaRolPago);
		for (RolPagoIf rolPagoQuincenal : rolPagoQuincenales) {
			Map<String, Object> mapaRolPagoDetalle = new HashMap<String, Object>();
			mapaRolPagoDetalle.put("rubroId", " not null ");
			mapaRolPagoDetalle.put("rolpagoId", rolPagoQuincenal.getId());
			mapaRolPagoDetalle.put("contratoId", contratoIf.getId());
			// Se busca los detalles del rol que le corresponde a un contrato
			// determinado
			// para poder sacar cuanto se le dio en la quincena.
			
			/*if(contratoIf.getCodigo().equals("NLEON")){
				System.out.println("referencia");
			}*/
			
			Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
					.findRolPagoDetalleByQueryByEstados(mapaRolPagoDetalle,
							EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
							EstadoRolPagoDetalle.PAGADO.getLetra());
			double total = 0.0;
			for (RolPagoDetalleIf rpdq : detalles) {
				if (rpdq.getRubroId() != null) {
					RubroIf rubroIf = null;
					// if ( rpdq.getRubroId() != null ){
					rubroIf = verificarRubro(mapaRubros, rpdq.getRubroId());
					// }
					OperacionNomina on = UtilesNomina.getIngresoEgreso(
							tipoRolQuincenal, rubroIf);
					if (on == OperacionNomina.INGRESO)
						total += rpdq.getValor().doubleValue();
					else
						total -= rpdq.getValor().doubleValue();
				}
			}

			mapaRolPagoDetalle = new HashMap<String, Object>();
			mapaRolPagoDetalle.put("rubroEventualId", " not null ");
			mapaRolPagoDetalle.put("rolpagoId", rolPagoQuincenal.getId());
			mapaRolPagoDetalle.put("contratoId", contratoIf.getId());
			mapaRolPagoDetalle.put("tipoRolIdCobro", tipoRolQuincenal.getId());
			mapaRolPagoDetalle.put("mesCobro", rolPagoIf.getMes());
			mapaRolPagoDetalle.put("anioCobro", rolPagoIf.getAnio());
			mapaRolPagoDetalle.put("tipoContratoId", rolPagoIf
					.getTipocontratoId());
			// Se busca los detalles del rol que le corresponde a un contrato
			// determinado
			// para poder sacar cuanto se le dio en la quincena.
			detalles = rolPagoDetalleLocal
					.findRolPagoDetalleEventualesByMapByEstados(
							mapaRolPagoDetalle, EstadoRolPagoDetalle.EMITIDO
									.getLetra(),
							EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
							EstadoRolPagoDetalle.PAGADO.getLetra());
			for (RolPagoDetalleIf rpdq : detalles) {
				total -= rpdq.getValor().doubleValue();

				// RubroEventualIf re =
				// rubroEventualLocal.getRubroEventual(rpdq.getRubroEventualId());
				// RubroIf r = verificarRubro(mapaRubros, re.getRubroId());
				// if ( r == null )
				// throw new GenericBusinessException("Rubro Eventual no tiene
				// rubro registrado !!");
				// RolPagoDetalleIf rolPagoDetalleIf =
				// registrarRolPagoDetalleQuincenal(
				// rolPagoIf, contratoIf,r.getId(),rpdq.getValor());
				// RolPagoDetalleIf rolPagoDetalleGuardado =
				// rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalleIf);
				// rolPagoDetalleCollection.add(rolPagoDetalleGuardado);
				// mapaEventualesQuincena.put(re.getId(), re);
				mapaEventualesQuincena.put(rpdq.getId(), rpdq);
			}
			total = utilitariosLocal.redondeoValor(total);

			// AQUI EL VALOR DEL INGRESO QUINCENAL(INGRESOS - EGRESOS) VA COMO
			// EGRESO MENSUAL
			RolPagoDetalleIf rolPagoDetalleIf = registrarRolPagoDetalleQuincenal(
					rolPagoIf, contratoIf.getId(), rubroQuincenal.getId(),
					BigDecimal.valueOf(total));
			
			/*if(rolPagoDetalleIf.getValor().compareTo(new BigDecimal(0)) == -1){
				System.out.println("problema");
			}*/
			
			RolPagoDetalleIf rolPagoDetalleGuardado = rolPagoDetalleLocal
					.addRolPagoDetalle(rolPagoDetalleIf);
			rolPagoDetalleCollection.add(rolPagoDetalleGuardado);

		}
	}

	private RolPagoDetalleIf registrarRolPagoDetalleProvisionado(
			RolPagoIf rolPagoIf, ContratoIf contrato, Long rubroId,
			BigDecimal valor, String observacion)
			throws GenericBusinessException {

		RolPagoDetalleIf rolPagoDetalleIf = new RolPagoDetalleData();
		rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
		rolPagoDetalleIf.setContratoId(contrato.getId());
		rolPagoDetalleIf.setRubroId(rubroId);
		rolPagoDetalleIf.setValor(valor);
		rolPagoDetalleIf
				.setEstado(EstadoRolPagoDetalle.PROVISIONADO.getLetra());
		rolPagoDetalleIf.setObservacion(observacion);
		return rolPagoDetalleIf;
	}

	private RolPagoDetalleIf registrarRolPagoDetalleProvisionado(
			RolPagoIf rolPagoIf, ContratoIf contrato,
			ContratoRubroIf contratoRubro, BigDecimal valor, String observacion)
			throws GenericBusinessException {

		RolPagoDetalleIf rolPagoDetalleIf = new RolPagoDetalleData();
		rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
		rolPagoDetalleIf.setContratoId(contrato.getId());
		rolPagoDetalleIf.setRubroId(contratoRubro.getRubroId());
		rolPagoDetalleIf.setValor(valor);
		rolPagoDetalleIf
				.setEstado(EstadoRolPagoDetalle.PROVISIONADO.getLetra());
		rolPagoDetalleIf.setObservacion(observacion);
		return rolPagoDetalleIf;
	}

	private RolPagoDetalleIf registrarRolPagoDetalle(RolPagoIf rolPagoIf,
			ContratoIf contrato, Long rubroId,
			BigDecimal valor, String observacion)
			throws GenericBusinessException {

		RolPagoDetalleIf rolPagoDetalleIf = new RolPagoDetalleData();
		rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
		rolPagoDetalleIf.setContratoId(contrato.getId());
		rolPagoDetalleIf.setRubroId(rubroId);
		rolPagoDetalleIf.setValor(valor);
		rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.EMITIDO.getLetra());
		rolPagoDetalleIf.setObservacion(observacion);
		return rolPagoDetalleIf;
	}

	private RolPagoDetalleIf registrarRolPagoDetalleQuincenal(
			RolPagoIf rolPagoIf, Long contratoId, Long rubroId, BigDecimal valor)
			throws GenericBusinessException {
		RolPagoDetalleIf rolPagoDetalleIf = new RolPagoDetalleData();
		rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
		rolPagoDetalleIf.setContratoId(contratoId);
		rolPagoDetalleIf.setRubroId(rubroId);
		rolPagoDetalleIf.setValor(valor);
		rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.EMITIDO.getLetra());
		return rolPagoDetalleIf;
	}

	/*
	 * private RolPagoDetalleIf registrarRolPagoDetalleQuincenal(RolPagoIf
	 * rolPagoIf, ContratoIf contrato, RolPagoDetalleIf detalle) throws
	 * GenericBusinessException { RolPagoDetalleIf rolPagoDetalleIf = new
	 * RolPagoDetalleData(); rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
	 * rolPagoDetalleIf.setContratoId(contrato.getId());
	 * rolPagoDetalleIf.setRubroId(detalle.getRubroId());
	 * rolPagoDetalleIf.setValor(detalle.getValor());
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.EMITIDO));
	 * return rolPagoDetalleIf; }
	 */

	private RolPagoDetalleIf registrarRolPagoDetalleDecimosFondosAportesVacaciones(
			RolPagoIf rolPagoIf, Long contratoId, Long rubroId,
			BigDecimal valor, Date fechaPago, String observacion)
			throws GenericBusinessException {
		RolPagoDetalleIf rolPagoDetalleIf = new RolPagoDetalleData();
		rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
		rolPagoDetalleIf.setContratoId(contratoId);
		rolPagoDetalleIf.setRubroId(rubroId);
		rolPagoDetalleIf.setValor(valor);
		rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.EMITIDO.getLetra());
		rolPagoDetalleIf.setFechaPago(fechaPago);
		rolPagoDetalleIf.setObservacion(observacion);
		return rolPagoDetalleIf;
	}

	private BigDecimal calcularValor(ContratoRubroIf contratoRubro,
			Map<String, BigDecimal> codigoValorxRubro, RubroIf rubroIf,
			String codigoContrato, Date fechaRolPago) throws GenericBusinessException {
			
		BigDecimal valor = null;
		
		if (rubroIf.getModoOperacion().equals(ModoOperacionRubro.CALCULADO.getLetra())) {
			String politicaCambiada = cambiarPolitica(rubroIf, codigoValorxRubro);
			valor = utilitariosLocal.evaluadorExpresionJavaScript(politicaCambiada);
		} 
		else if (contratoRubro.getValor() != null){
			
			//tengo que encerar las horas para la comparacion							
			Date fechaInicioRubroContrato = null;
			Date fechaFinRubroContrato = null;
			if(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null){
				fechaInicioRubroContrato = new Date(contratoRubro.getFechaInicio().getYear(), contratoRubro.getFechaInicio().getMonth(), contratoRubro.getFechaInicio().getDate());
				fechaFinRubroContrato = new Date(contratoRubro.getFechaFin().getYear(), contratoRubro.getFechaFin().getMonth(), contratoRubro.getFechaFin().getDate());
			}
			
			//si el rubro del contrato no tiene fechas inicio y fin, o
			//si tiene fechas deben abarcar la fecha del rol
			if((contratoRubro.getFechaInicio() == null || contratoRubro.getFechaFin() == null) || 
					(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null 
					&& fechaInicioRubroContrato.compareTo(fechaRolPago) <= 0 
					&& fechaFinRubroContrato.compareTo(fechaRolPago) >= 0)){
				
				valor = utilitariosLocal.redondeoValor(contratoRubro.getValor());
				if(contratoRubro.getRubroId().compareTo(40L) == 0){
					valor = utilitariosLocal.redondeoValor(codigoValorxRubro.get("SB"));
				} else if(contratoRubro.getRubroId().compareTo(600L) == 0){
					valor = utilitariosLocal.redondeoValor(codigoValorxRubro.get("OI"));
				}
			}
		}
		else{
			valor = BigDecimal.ZERO;
		}
		
		return valor;
	}

	private BigDecimal calcularValorImpuestoRenta(RolPagoIf rolPagoIf,
			ContratoIf contratoIf, ContratoRubroIf contratoRubro,
			Map<String, BigDecimal> codigoValorxRubro, RubroIf rubroIf,
			Collection<ImpuestoRentaPersNatIf> impuestosRenta,
			Map<String, Object> mapaInfoImpuestoRenta, String queryRolesPago,
			int numeroMesesRestantes, Map<String, RubroIf> mapaRubroByCodigo,
			Double totalGastosDeducibles, Map<Long, RubroIf> mapaRubros)
			throws GenericBusinessException {
		
		BigDecimal valor = null;
		Date fechaPrimerDiaMesRolPago = (Date) mapaInfoImpuestoRenta.get("fechaPrimerDiaRolPago");
		Double aportePersonalIess = (Double) mapaInfoImpuestoRenta.get("aportePersonalIess");
		
		if (aportePersonalIess == null) {
			throw new GenericBusinessException(
				"No existe rubro de Aporte Personal al IESS para contrato con codigo "+ contratoIf.getCodigo() + "!!");
		}
		
		Double sueldo = (Double) mapaInfoImpuestoRenta.get("sueldo");
		
		if (sueldo == null) {
			throw new GenericBusinessException(
				"No existe rubro de sueldo para contrato con codigo "+ contratoIf.getCodigo() + "!!");
		}

		if ( rubroIf.getModoOperacion().equals(ModoOperacionRubro.CALCULADO.getLetra()) ) {

			Map<String, Double> mapaValores = calculoFinalBaseImponibleImpuestoRenta(
				rolPagoIf, contratoIf, rubroIf, queryRolesPago,
				numeroMesesRestantes, mapaRubroByCodigo,
				totalGastosDeducibles, mapaRubros, codigoValorxRubro);

			Double baseImponibleD = (Double) mapaValores.get("baseImponible");
			valor = new BigDecimal(baseImponibleD);

			ImpuestoRentaPersNatIf impuesto = buscarImpuestoRentaByValorByFecha(
				impuestosRenta, valor, fechaPrimerDiaMesRolPago);
			// Double BaseImponible = utilitariosLocal.redondeoValor( sueldo -
			// aportePersonalIess );
			if (impuesto == null)
				throw new GenericBusinessException(
					"No existe regla para calculo de Impuesto a la Renta !!" +
					"\n Valor: "+ utilitariosLocal.redondeoValor(valor)+
					", Fecha: "+ utilitariosLocal.getFechaUppercase(fechaPrimerDiaMesRolPago)+ ".");
			
			Double diferencia = valor.doubleValue() - impuesto.getFraccionBasica().doubleValue();
			diferencia = utilitariosLocal.redondeoValor(diferencia);

			Double porcentajeCalculado = diferencia* impuesto.getPorcentajeImpFraccionBasica().doubleValue()/ 100;
			porcentajeCalculado = utilitariosLocal.redondeoValor(porcentajeCalculado);

			Double total = porcentajeCalculado+ impuesto.getImpFraccionBasica().doubleValue();

			//Se suman todos los valores de impuesto a la renta de meses anteriores
			//para restarlos del total y dividirlo para los meses que faltan.
			BigDecimal totalRubroImpuestoRenta = null;
			if (queryRolesPago != null && !"".equals(queryRolesPago)){
				totalRubroImpuestoRenta = rubroLocal.getRubroTotalByRubroCodigo(
					rubroIf.getCodigo(),contratoIf.getId(), queryRolesPago);
			}
			totalRubroImpuestoRenta  = totalRubroImpuestoRenta == null ? BigDecimal.ZERO : totalRubroImpuestoRenta; 
			
			//total = total / numeroMesesRestantes; Error en calculo, es dividido para el total de meses, no de los meses restantes
			//total = total / 12;
			//Se resta los montos que ya se le resto de los meses anteriores.
			total = total - totalRubroImpuestoRenta.doubleValue();
			//Si mayor que cero, se lo divide para el numero de meses restantes.
			total = total >= 0 ? total / numeroMesesRestantes : 0D;

			total = utilitariosLocal.redondeoValor(total);

			valor = new BigDecimal(total);

		} else if (contratoRubro.getValor() != null){
			
			//tengo que encerar las horas para la comparacion							
			Date fechaInicioRubroContrato = null;
			Date fechaFinRubroContrato = null;
			if(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null){
				fechaInicioRubroContrato = new Date(contratoRubro.getFechaInicio().getYear(), contratoRubro.getFechaInicio().getMonth(), contratoRubro.getFechaInicio().getDate());
				fechaFinRubroContrato = new Date(contratoRubro.getFechaFin().getYear(), contratoRubro.getFechaFin().getMonth(), contratoRubro.getFechaFin().getDate());
			}
			
			Date fechaRolPago = new Date(rolPagoIf.getFecha().getYear(), rolPagoIf.getFecha().getMonth(), rolPagoIf.getFecha().getDate());
						
			//si el rubro del contrato no tiene fechas inicio y fin, o
			//si tiene fechas deben abarcar la fecha del rol
			if((contratoRubro.getFechaInicio() == null || contratoRubro.getFechaFin() == null) || 
					(contratoRubro.getFechaInicio() != null && contratoRubro.getFechaFin() != null 
					&& fechaInicioRubroContrato.compareTo(fechaRolPago) <= 0 
					&& fechaFinRubroContrato.compareTo(fechaRolPago) >= 0)){
				
				valor = utilitariosLocal.redondeoValor(contratoRubro.getValor());
			}
		}
		else{
			valor = BigDecimal.ZERO;
		}
		
		return valor;
	}

	private Map<String, Double> calculoFinalBaseImponibleImpuestoRenta(
			RolPagoIf rolPagoIf, ContratoIf contratoIf, RubroIf rubroIf,String queryRolesPago, 
			int numeroMesesRestantes,Map<String, RubroIf> mapaRubroByCodigo,Double totalGastosDeducibles, 
			Map<Long, RubroIf> mapaRubros,Map<String, BigDecimal> codigoValorxRubro)
			throws GenericBusinessException {
		
		Date fechaRolPago = new Date(rolPagoIf.getFecha().getYear(), rolPagoIf.getFecha().getMonth(), rolPagoIf.getFecha().getDate());
				
		Map<String, Double> mapaValores = calcularBaseImponibleImpuestoRenta(
				rolPagoIf.getId(), contratoIf, rubroIf.getPolitica(),
				queryRolesPago, numeroMesesRestantes, mapaRubroByCodigo,
				mapaRubros, codigoValorxRubro, fechaRolPago);
		Double baseImponibleD = mapaValores.get("baseImponible");

		// Se resta el total de todos los gastos deducibles por mes
		baseImponibleD -= totalGastosDeducibles;
		baseImponibleD = utilitariosLocal.redondeoValor(baseImponibleD);
		mapaValores.put("baseImponible", baseImponibleD);
		return mapaValores;
	}

	public Map<String, Double> calcularBaseImponibleImpuestoRenta(
			Long rolPagoActualId, ContratoIf contratoIf, String politicaRubro,
			String queryRolesPago, int numeroMesesRestantes,Map<String, RubroIf> mapaRubroByCodigo,
			Map<Long, RubroIf> mapaRubros,Map<String, BigDecimal> codigoValorxRubro, Date fechaRolPago)
			throws GenericBusinessException {
		
		Map<String, Double> mapaValores = new HashMap<String, Double>();
		ArrayList<String[]> codigosSumasRestas = separarPoliticaImpuestoRenta(politicaRubro);
		String[] codigosSumas = codigosSumasRestas.get(0);
		String[] codigosRestas = codigosSumasRestas.get(1);
		
		// ---------Sumas de Valores de Meses anteriores
		Double totalSumas = 0.0;
		Double totalRestas = 0.0;
		//Si es enero no es necesario calcular para los meses anteriores. 
		//Ademas si es enero hay un bug que me retorna todos los meses.
		if ( numeroMesesRestantes < 12 ){
			for (String codigo : codigosSumas) {
				BigDecimal totalRubro = null;
				if (queryRolesPago != null && !"".equals(queryRolesPago))
					totalRubro = rubroLocal.getRubroTotalByRubroCodigo(codigo,contratoIf.getId(), queryRolesPago);
				double totalD = utilitariosLocal.redondeoValor(totalRubro!= null ? totalRubro.doubleValue() : 0D );
				mapaValores.put(codigo, totalD);
				totalSumas += totalD;
			}
	
			for (String codigo : codigosRestas) {
				BigDecimal totalRubro = null;
				if (queryRolesPago != null && !"".equals(queryRolesPago))
					totalRubro = rubroLocal.getRubroTotalByRubroCodigo(codigo,contratoIf.getId(), queryRolesPago);
				double totalD = utilitariosLocal.redondeoValor(totalRubro != null ? totalRubro.doubleValue() : 0D );
				mapaValores.put(codigo, totalD);
				totalRestas += totalD;
			}
		}
		Double baseImponible = utilitariosLocal.redondeoValor(totalSumas - totalRestas);

		// ----------Valores Actuales
		Double totalSumasRolActual = 0.0;
		Map<String, Object> mapaRolActual = new HashMap<String, Object>();
		mapaRolActual.put("contratoId", contratoIf.getId());
		mapaRolActual.put("rolpagoId", rolPagoActualId);
		for (String codigo : codigosSumas) {
			RubroIf rubro = verificarRubro(mapaRubroByCodigo, codigo);
			mapaRolActual.put("rubroId", rubro.getId());

			Collection<RolPagoDetalleIf> rolesRubro = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaRolActual);
			for (RolPagoDetalleIf rpd : rolesRubro) {
				actualizarValoresSumasRestas(mapaValores, codigo, rpd.getValor().doubleValue());
				totalSumasRolActual += rpd.getValor().doubleValue();
			}
		}

		Double totalRestasRolActual = 0.0;
		for (String codigo : codigosRestas) {
			RubroIf rubro = verificarRubro(mapaRubroByCodigo, codigo);
			mapaRolActual.put("rubroId", rubro.getId());

			Collection<RolPagoDetalleIf> rolesRubro = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaRolActual);
			for (RolPagoDetalleIf rpd : rolesRubro) {
				actualizarValoresSumasRestas(mapaValores, codigo, rpd.getValor().doubleValue());
				totalRestasRolActual += rpd.getValor().doubleValue();
			}
		}

		Double baseImponibleActual = totalSumasRolActual - totalRestasRolActual;

		// ----------Valores proyectados para meses restantes
		Collection<ContratoRubroIf> rubrosDeContrato = contratoRubroLocal
				.findContratoRubroByContratoId(contratoIf.getId());
		Map<String, ContratoRubroIf> mapaContatoRubros = new HashMap<String, ContratoRubroIf>();
		for (ContratoRubroIf cr : rubrosDeContrato) {
			RubroIf rubro = verificarRubro(mapaRubros, cr.getRubroId());
			mapaContatoRubros.put(rubro.getCodigo(), cr);
		}
		//Reemplazo los valores registrados en el mapa por los valores de la tabla ContratoRubro.
		Map<String,BigDecimal> mapaExistentes = contratoRubroLocal.findMapaContratoRubroByRubroResgistrado(contratoIf.getId());
		
		//otros ingresos, para no tener que añadirlo a cada contrato
		if(codigoValorxRubro.get("OI") == null)
			codigoValorxRubro.put("OI", new BigDecimal(0));
		
		for ( String codigo : mapaExistentes.keySet() ){
			BigDecimal valorExistente = mapaExistentes.get(codigo); 
			codigoValorxRubro.put(codigo, valorExistente);
		}

		Double totalSumasRolMesesRestantes = 0.0;
		for (String codigo : codigosSumas) {
			ContratoRubroIf cr = mapaContatoRubros.get(codigo);
			RubroIf rubro = verificarRubro(mapaRubroByCodigo, codigo);
			BigDecimal valor = calcularValor(cr, codigoValorxRubro, rubro, contratoIf.getCodigo(), fechaRolPago);
			totalSumasRolMesesRestantes += valor.doubleValue();
			//actualizarValoresSumasRestas(mapaValores, rubro.getCodigo(), valor.doubleValue());
		}
		
		Double totalRestaRolMesesRestantes = 0.0;
		for (String codigo : codigosRestas) {
			ContratoRubroIf cr = mapaContatoRubros.get(codigo);
			RubroIf rubro = verificarRubro(mapaRubroByCodigo, codigo);
			BigDecimal valor = calcularValor(cr, codigoValorxRubro, rubro, contratoIf.getCodigo(), fechaRolPago);
			totalRestaRolMesesRestantes += valor.doubleValue();
			//actualizarValoresSumasRestas(mapaValores, rubro.getCodigo(), valor.doubleValue());
		}
		
		Double baseImponibleMesesRestantes = totalSumasRolMesesRestantes - totalRestaRolMesesRestantes;

		// Se multiplica por el numero de meses restantes para saber el valor
		// desde el rol pago siguiente al actual hasta el fin de anio, se le resta
		// uno, para no tomar en cuenta el mes actual.
		baseImponibleMesesRestantes = utilitariosLocal
				.redondeoValor(baseImponibleMesesRestantes * (numeroMesesRestantes-1) );

		BigDecimal valor = new BigDecimal(utilitariosLocal
				.redondeoValor(baseImponible + baseImponibleActual+baseImponibleMesesRestantes));
		mapaValores.put("baseImponible", valor.doubleValue());
		return mapaValores;
	}

	private void actualizarValoresSumasRestas(Map<String, Double> mapaValores,
			String codigoRubro, Double valor) {
		Double valorAntiguo = mapaValores.get(codigoRubro);
		if (valorAntiguo != null) {
			Double valorNuevo = valorAntiguo + valor;
			mapaValores.put(codigoRubro, utilitariosLocal
					.redondeoValor(valorNuevo));
		} else {
			mapaValores.put(codigoRubro, utilitariosLocal.redondeoValor(valor));
		}
	}

	private ArrayList<String[]> separarPoliticaImpuestoRenta(String politica)
			throws GenericBusinessException {
		// String politica = "+SB,CO;-AIES";
		politica = politica.trim();

		ArrayList<String[]> sumasRestas = new ArrayList<String[]>();

		if (politica == null || politica.length() == 0)
			throw new GenericBusinessException(
					"Impuesto a la Renta no tiene politica para el calculo establecida !!");

		int signoMas = politica.indexOf("+");
		int signoMenos = politica.indexOf("-");

		if (signoMenos < signoMas)
			throw new GenericBusinessException(
					"Signo + tiene que ir primero que signo - en politica de Impuesto a la Renta !!");

		int puntoComa = politica.indexOf(";");
		if (puntoComa < 1)
			throw new GenericBusinessException(
					"Caracter ; no encontrado para separacion de Sumas de Restas !!");

		String sumas = politica.substring(signoMas + 1, puntoComa);
		if (sumas.length() == 0)
			throw new GenericBusinessException(
					"Codigos para sumas no identificados !!");
		String[] codigosSumas = sumas.split(",");

		String restas = politica.substring(signoMenos + 1, politica.length());
		String[] codigosRestas = new String[] {};
		codigosRestas = restas.split(",");

		sumasRestas.add(codigosSumas);
		sumasRestas.add(codigosRestas);

		return sumasRestas;
	}

	private ImpuestoRentaPersNatIf buscarImpuestoRentaByValorByFecha(
			Collection<ImpuestoRentaPersNatIf> impuestos, BigDecimal valor,
			Date fecha) {

		/*if (valor.doubleValue() < 0D)
			valor = BigDecimal.ZERO;*/
		ImpuestoRentaPersNatIf impuesto = null;
		for (ImpuestoRentaPersNatIf ir : impuestos) {
			if ((valor.compareTo(ir.getFraccionBasica()) > 0 || valor.compareTo(ir.getFraccionBasica()) == 0)
					&& (valor.compareTo(ir.getExcesoHasta()) < 0 || valor.compareTo(ir.getExcesoHasta()) == 0)) {
				if (fecha.compareTo(ir.getFechaInicio()) > 0 || fecha.compareTo(ir.getFechaInicio()) == 0) {
					if (ir.getFechaFin() != null) {
						if (fecha.compareTo(ir.getFechaFin()) < 0 || fecha.compareTo(ir.getFechaFin()) == 0) {
							impuesto = ir;
							break;
						}
					} else {
						impuesto = ir;
						break;
					}
				}
			}
		}
		return impuesto;

	}

	private String cambiarPolitica(RubroIf rubroIf,
			Map<String, BigDecimal> codigoValorxRubro)
			throws GenericBusinessException {
		String politicaCambiada = rubroIf.getPolitica();
		boolean rubroEncontrado = false;
		for (String codigo : codigoValorxRubro.keySet()) {
			BigDecimal valorRubro = utilitariosLocal
					.redondeoValor(codigoValorxRubro.get(codigo));
			if (politicaCambiada.contains(codigo)) {
				// TODO: MEJORAR LA FORMA DE REEMPLAZO DE CODIGOS DE RUBROS EN
				// LA POLITICA
				politicaCambiada = politicaCambiada.replaceAll(codigo,
						valorRubro.toString());
				rubroEncontrado = true;
			}
		}
		if (rubroEncontrado) {
			return politicaCambiada;
		} else
			throw new GenericBusinessException(
					"No se encuentra código de rubro en la política "
							+ rubroIf.getPolitica() + " del Rubro "
							+ rubroIf.getNombre());
	}

	/*
	 * @TransactionAttribute(TransactionAttributeType.REQUIRED) public void
	 * cerrarRolPago(RolPagoIf rolPagoIf,Collection<Long>
	 * rolPagoDetalleIdCollection) throws GenericBusinessException { try{ if (
	 * rolPagoIf != null && rolPagoIf.getId() != null){ rolPagoIf =
	 * getRolPago(rolPagoIf.getId()); TipoRolIf tipoRolIf =
	 * tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId()); TipoRol tipoRol =
	 * utilesLocal.obtenerTipoRol(tipoRolIf); Map<Long,RubroIf> mapaRubros =
	 * new HashMap<Long, RubroIf>();
	 * 
	 * //SE PROCESAN LOS DETALLES DEL ROL DE PAGOS GregorianCalendar gc = new
	 * GregorianCalendar(); Date fechaHoy = new Date(gc.getTime().getTime());
	 * //double sumaValores = 0.0; double sumaDetalle = 0.0;
	 * 
	 * if ( rolPagoDetalleIdCollection == null ||
	 * rolPagoDetalleIdCollection.size() == 0 ) throw new
	 * GenericBusinessException("Rol de Pago vacío o no tiene detalles");
	 * OperacionNomina operacion = null; for ( Long rolPagoDetalleId :
	 * rolPagoDetalleIdCollection ){ RolPagoDetalleIf rolPagoDetalleIf =
	 * rolPagoDetalleLocal.getRolPagoDetalle(rolPagoDetalleId); //Si el rol de
	 * un empleado ha sido autorizado se lo toma en cuenta en el proceso. if (
	 * rolPagoDetalleIf.getEstado().equals(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO)) ){
	 * RubroIf rubroIf = verificarRubro(mapaRubros,
	 * rolPagoDetalleIf.getRubroId()); TipoRol tipoRolDetalle =
	 * utilesLocal.getTipoRolByRubro(rubroIf);
	 * 
	 * if ( tipoRol == TipoRol.MENSUAL ){
	 * 
	 * if ( tipoRolDetalle == TipoRol.QUINCENAL || tipoRolDetalle ==
	 * TipoRol.MENSUAL ){
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
	 * operacion = utilesLocal.getIngresoEgreso(tipoRolIf, rubroIf); if (
	 * operacion == OperacionNomina.INGRESO ) sumaDetalle +=
	 * rolPagoDetalleIf.getValor().doubleValue(); } else if ( tipoRolDetalle ==
	 * TipoRol.DECIMO_TERCERO || tipoRolDetalle == TipoRol.DECIMO_CUARTO ||
	 * tipoRolDetalle == TipoRol.APORTE_PATRONAL || tipoRolDetalle ==
	 * TipoRol.FONDO_RESERVA){
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.PROVISIONADO)); }
	 * //else // throw new GenericBusinessException("Tipo de Rol para rubro
	 * "+rubroIf.getNombre()+" no considerado para proceso");
	 *  } else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol ==
	 * TipoRol.DECIMO_CUARTO || tipoRol == TipoRol.APORTE_PATRONAL || tipoRol ==
	 * TipoRol.FONDO_RESERVA){
	 * 
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
	 * sumaDetalle += rolPagoDetalleIf.getValor().doubleValue(); } else throw
	 * new GenericBusinessException(tipoRolIf.getNombre()+" no soportado para
	 * cierre de detalle !!"); //sumaValores +=
	 * rolPagoDetalleIf.getValor().doubleValue() ;
	 * rolPagoDetalleIf.setFechaPago(fechaHoy); //System.out.println("-----
	 * Total de Rol de Pago para asiento: "+sumaValores); } } sumaDetalle =
	 * utilitariosLocal.redondeoValor(sumaDetalle);
	 * rolPagoIf.setEstado(EstadoRolPago.CERRADO.toString().substring(0,1));
	 * 
	 * AsientoIf asiento = null; if ( tipoRol == TipoRol.MENSUAL ) asiento =
	 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoMensual(rolPagoIf,
	 * EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
	 * else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol ==
	 * TipoRol.DECIMO_CUARTO || tipoRol == TipoRol.APORTE_PATRONAL || tipoRol ==
	 * TipoRol.FONDO_RESERVA ) asiento =
	 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoDecimosAportesFondos(rolPagoIf,
	 * tipoRolIf, tipoRol);
	 * 
	 * //VERIFICACION DE VALORES if ( asiento != null ){ double debe = 0.0;
	 * double haber = 0.0; Collection<AsientoDetalleIf> asientosDetalle =
	 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId()); for (
	 * Iterator itAsientos = asientosDetalle.iterator() ; itAsientos.hasNext() ; ){
	 * AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
	 * debe += asientoDetalle.getDebe().doubleValue(); haber +=
	 * asientoDetalle.getHaber().doubleValue(); } debe =
	 * utilitariosLocal.redondeoValor(debe); haber =
	 * utilitariosLocal.redondeoValor(haber); if ( debe != sumaDetalle ) throw
	 * new GenericBusinessException("Error al guardar "+tipoRolIf.getNombre()+",
	 * el valor del Debe y Haber no coinciden con el detalle del Rol"); else if (
	 * debe != haber ) throw new GenericBusinessException("Error al guardar
	 * "+tipoRolIf.getNombre()+", el valor del Debe y Haber no coinciden en el
	 * Asiento");
	 *  } else throw new GenericBusinessException("No se gener\u00f3 el asiento
	 * del "+tipoRolIf.getNombre());
	 *  } else throw new GenericBusinessException("Rol de Pago nulo"); } catch(
	 * GenericBusinessException e ){ ctx.setRollbackOnly(); e.printStackTrace();
	 * throw new GenericBusinessException(e.getMessage()); } catch(Exception e){
	 * ctx.setRollbackOnly(); e.printStackTrace(); throw new
	 * GenericBusinessException("Error general al cerrar el Rol de Pago"); } }
	 */

	/*
	 * @TransactionAttribute(TransactionAttributeType.REQUIRED) public void
	 * cerrarRolPago(RolPagoIf rolPagoIf) throws GenericBusinessException { try{
	 * if ( rolPagoIf != null && rolPagoIf.getId() != null){ rolPagoIf =
	 * getRolPago(rolPagoIf.getId()); TipoRolIf tipoRolIf =
	 * tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId()); TipoRol tipoRol =
	 * utilesLocal.obtenerTipoRol(tipoRolIf); Map<Long,RubroIf> mapaRubros =
	 * new HashMap<Long, RubroIf>();
	 * 
	 * //SE PROCESAN LOS DETALLES DEL ROL DE PAGOS GregorianCalendar gc = new
	 * GregorianCalendar(); Date fechaHoy = new Date(gc.getTime().getTime());
	 * //double sumaValores = 0.0; double sumaDetalle = 0.0; Collection<RolPagoDetalleIf>
	 * detalles = null; if ( tipoRol == TipoRol.QUINCENAL || tipoRol ==
	 * TipoRol.MENSUAL ){ Map<String, Object> mapa = new HashMap<String,
	 * Object>(); mapa.put("rolpagoId", rolPagoIf.getId()); detalles =
	 * rolPagoDetalleLocal.findRolPagoDetalleByQueryNormal(mapa); } else
	 * detalles =
	 * rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
	 * 
	 * if ( detalles == null || detalles.size() == 0 ) throw new
	 * GenericBusinessException("Rol de Pago vacío o no tiene detalles");
	 * OperacionNomina operacion = null; for ( RolPagoDetalleIf rolPagoDetalleIf :
	 * detalles ){ //Si el rol de un empleado ha sido autorizado se lo toma en
	 * cuenta en el proceso. if (
	 * rolPagoDetalleIf.getEstado().equals(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO)) ){
	 * RubroIf rubroIf = verificarRubro(mapaRubros,
	 * rolPagoDetalleIf.getRubroId()); TipoRol tipoRolDetalle =
	 * utilesLocal.getTipoRolByRubro(rubroIf);
	 * 
	 * if ( tipoRol == TipoRol.MENSUAL ){
	 * 
	 * if ( tipoRolDetalle == TipoRol.QUINCENAL || tipoRolDetalle ==
	 * TipoRol.MENSUAL ){
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.PAGADO));
	 * operacion = utilesLocal.getIngresoEgreso(tipoRolIf, rubroIf); if (
	 * operacion == OperacionNomina.INGRESO ) sumaDetalle +=
	 * rolPagoDetalleIf.getValor().doubleValue(); } else if ( tipoRolDetalle ==
	 * TipoRol.DECIMO_TERCERO || tipoRolDetalle == TipoRol.DECIMO_CUARTO ||
	 * tipoRolDetalle == TipoRol.APORTE_PATRONAL || tipoRolDetalle ==
	 * TipoRol.FONDO_RESERVA){
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.PROVISIONADO)); }
	 * //else // throw new GenericBusinessException("Tipo de Rol para rubro
	 * "+rubroIf.getNombre()+" no considerado para proceso");
	 *  } else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol ==
	 * TipoRol.DECIMO_CUARTO || tipoRol == TipoRol.APORTE_PATRONAL || tipoRol ==
	 * TipoRol.FONDO_RESERVA){
	 * 
	 * rolPagoDetalleIf.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.PAGADO));
	 * sumaDetalle += rolPagoDetalleIf.getValor().doubleValue(); } else throw
	 * new GenericBusinessException(tipoRolIf.getNombre()+" no soportado para
	 * cierre de detalle !!"); //sumaValores +=
	 * rolPagoDetalleIf.getValor().doubleValue() ;
	 * rolPagoDetalleIf.setFechaPago(fechaHoy); //System.out.println("-----
	 * Total de Rol de Pago para asiento: "+sumaValores); } } sumaDetalle =
	 * utilitariosLocal.redondeoValor(sumaDetalle);
	 * rolPagoIf.setEstado(EstadoRolPago.CERRADO.toString().substring(0,1));
	 * 
	 * AsientoIf asiento = null; if ( tipoRol == TipoRol.MENSUAL ) asiento =
	 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoMensual(rolPagoIf,
	 * EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
	 * else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol ==
	 * TipoRol.DECIMO_CUARTO || tipoRol == TipoRol.APORTE_PATRONAL || tipoRol ==
	 * TipoRol.FONDO_RESERVA ) asiento =
	 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoDecimosAportesFondos(rolPagoIf,
	 * tipoRolIf, tipoRol);
	 * 
	 * //VERIFICACION DE VALORES if ( asiento != null ){ double debe = 0.0;
	 * double haber = 0.0; Collection<AsientoDetalleIf> asientosDetalle =
	 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asiento.getId()); for (
	 * Iterator itAsientos = asientosDetalle.iterator() ; itAsientos.hasNext() ; ){
	 * AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
	 * debe += asientoDetalle.getDebe().doubleValue(); haber +=
	 * asientoDetalle.getHaber().doubleValue(); } debe =
	 * utilitariosLocal.redondeoValor(debe); haber =
	 * utilitariosLocal.redondeoValor(haber); if ( debe != sumaDetalle ) throw
	 * new GenericBusinessException("Error al guardar "+tipoRolIf.getNombre()+",
	 * el valor del Debe y Haber no coinciden con el detalle del Rol"); else if (
	 * debe != haber ) throw new GenericBusinessException("Error al guardar
	 * "+tipoRolIf.getNombre()+", el valor del Debe y Haber no coinciden en el
	 * Asiento");
	 *  } else throw new GenericBusinessException("No se gener\u00f3 el asiento
	 * del "+tipoRolIf.getNombre());
	 *  } else throw new GenericBusinessException("Rol de Pago nulo"); } catch(
	 * GenericBusinessException e ){ ctx.setRollbackOnly(); e.printStackTrace();
	 * throw new GenericBusinessException(e.getMessage()); } catch(Exception e){
	 * ctx.setRollbackOnly(); e.printStackTrace(); throw new
	 * GenericBusinessException("Error general al cerrar el Rol de Pago"); } }
	 */

	/*
	 * private String getLetraEstadoDetalle(EstadoRolPagoDetalle estado) throws
	 * GenericBusinessException{ if ( estado == EstadoRolPagoDetalle.EMITIDO )
	 * return estado.toString().substring(0,1); else if ( estado ==
	 * EstadoRolPagoDetalle.PROVISIONADO || estado ==
	 * EstadoRolPagoDetalle.PAGADO || estado == EstadoRolPagoDetalle.AUTORIZADO )
	 * return estado.toString().substring(1,2); throw new
	 * GenericBusinessException("Estado no considerado en la obtencion de
	 * Inicial"); }
	 */

	/*
	 * private TipoRol getTipoRolPago(TipoRolIf tipoRolIf){ if (
	 * tipoRolIf.getNombre().contains("QUINCENAL") ) return TipoRol.QUINCENAL;
	 * else if( tipoRolIf.getNombre().contains("MENSUAL") ) return
	 * TipoRol.MENSUAL; else if ( tipoRolIf.getNombre().contains("DECIMO") ){ if (
	 * tipoRolIf.getNombre().contains("TERCER") ) return TipoRol.DECIMO_TERCERO;
	 * else if ( tipoRolIf.getNombre().contains("CUARTO") ) return
	 * TipoRol.DECIMO_CUARTO; } return null; }
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findRolPagoByQueryByContratoIdByEmpleadoId(
			Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from RolPagoEJB " + objectName + " where "
				+ where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		return query.getResultList();

	}

	public Collection<RolPagoIf> getRolPagoListOrdenado(){
		ArrayList<RolPagoIf> rolesPago = new ArrayList<RolPagoIf>();
		String queryString = "select rp from RolPagoEJB rp order by rp.anio,rp.mes";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	
	/**
	 * *********************** RUBROS EVENTUALES **********************************************
	 */

	public void actualizarRubroEventuales(Long contratoId,
			ArrayList<RubroEventualIf> rubrosEventualesEmitidosColleccion,
			ArrayList<RubroEventualIf> rubrosEventualesEmitidosRemovidos,
			ArrayList<RubroEventualIf> rubrosEventualesAutorizadosColleccion,
			ArrayList<RubroEventualIf> rubrosEventualesPagadosColleccion)
			throws GenericBusinessException {
		try {
			DecimalFormat formatoDosEnteros = new DecimalFormat("00");
			ArrayList<RolPagoIf> rolesPago = new ArrayList<RolPagoIf>();
			for (RubroEventualIf rubroEventualEleminado : rubrosEventualesEmitidosRemovidos) {
				// Reviso en los roles detalles para ver si ya fue autorizado o
				// pagado, si fue asi,no se puede eliminar.
				Collection<RolPagoDetalleIf> rolesPagoEventuales = rolPagoDetalleLocal
						.findRolPagoDetalleByRubroEventualId(rubroEventualEleminado.getId());
				if (rolesPagoEventuales != null && rolesPagoEventuales.size() > 0) {
					for (RolPagoDetalleIf rpde : rolesPagoEventuales) {
						/*if (rpde.getEstado().equals(EstadoRolPagoDetalle.AUTORIZADO.getLetra())
							|| rpde.getEstado().equals(EstadoRolPagoDetalle.PAGADO.getLetra())) {*/
						EstadoRolPagoDetalle estadoRpde = EstadoRolPagoDetalle
							.getEstadoRolPagoDetalleByLetra(rpde.getEstado()); 
						if ( estadoRpde != EstadoRolPagoDetalle.EMITIDO ){
							throw new GenericBusinessException("Rubro Eventual con descripción: \n"
								+ rubroEventualEleminado.getObservacion()+ "\n ha sido Autorizado o Pagado !!");
						} else {
							rolPagoDetalleLocal.deleteRolPagoDetalle(rpde.getId());
						}
					}
				}
				if (!EstadoRubroEventual.EMITIDO.getLetra().equals(
						rubroEventualEleminado.getEstado()))
					throw new GenericBusinessException("El rubro eventual con descripcion: \n"
						+ rubroEventualEleminado.getObservacion()+ "\ntiene estado "+ EstadoRubroEventual
							.getRubroEventualByLetra(rubroEventualEleminado.getEstado())
									+ "\nno puede ser eliminado !!");
				rubroEventualLocal.deleteRubroEventual(rubroEventualEleminado.getId());
			}
			ContratoIf contratoIf = contratoLocal.getContrato(contratoId);
			if (contratoIf == null)
				throw new GenericBusinessException("Contrato no existe !!");
			for (RubroEventualIf modelRubroEventual : rubrosEventualesEmitidosColleccion) {
				modelRubroEventual.setContratoId(contratoId);
				RubroEventualIf rubroEventual = rubroEventualLocal
						.registrarRubroEventual(modelRubroEventual);
				
				//Solo se actualiza si el estado del rubro está en emitido 
				String estadoLetra = rubroEventual.getEstado();
				EstadoRubroEventual estado = EstadoRubroEventual.getRubroEventualByLetra(estadoLetra);
				if ( estado == EstadoRubroEventual.EMITIDO ){
					// OJO, VERIFICA SI EL ROL DE PAGO EXISTE, SI NO EXISTE, LO CREA
					verificarRolPago(rolesPago, contratoIf.getTipocontratoId(),
							rubroEventual, formatoDosEnteros);
					
					if (rubroEventual.getId() != null ){
						/*RubroEventualIf reTmp = rubroEventualLocal.getRubroEventual(rubroEventual.getId());
						String estadoLetra = reTmp.getEstado();
						EstadoRubroEventual estado = EstadoRubroEventual.getRubroEventualByLetra(estadoLetra);
						if ( estado == EstadoRubroEventual.EMITIDO )*/
							rubroEventualLocal.saveRubroEventual(rubroEventual);
					} else {
						rubroEventualLocal.addRubroEventual(rubroEventual);
					}
				}
			}

			for (RubroEventualIf modelRubroEventual : rubrosEventualesAutorizadosColleccion) {
				RubroEventualIf rubroEventual = rubroEventualLocal
						.registrarRubroEventual(modelRubroEventual);
				rubroEventualLocal.saveRubroEventual(rubroEventual);
			}

			for (RubroEventualIf modelRubroEventual : rubrosEventualesPagadosColleccion) {
				RubroEventualIf rubroEventual = rubroEventualLocal
						.registrarRubroEventual(modelRubroEventual);
				rubroEventualLocal.saveRubroEventual(rubroEventual);
			}

		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error general al guardar Rubros Eventuales !!");
		}
	}

	private RolPagoIf verificarRolPago(ArrayList<RolPagoIf> rolesPago,
			Long tipoContratoId, RubroEventualIf rubroEventual,
			DecimalFormat formatoDosEnteros) throws GenericBusinessException {
		// Si tiene fecha de pago siginifica que se emite cheque y tiene que
		// existir el rol de pago para ese mes y año
		if (rubroEventual.getFechaPago() != null) {
			Date fechaPago = rubroEventual.getFechaPago();
			String mes = formatoDosEnteros.format(fechaPago.getMonth() + 1);
			String anio = utilitariosLocal.getYearFromDate(fechaPago);
			for (RolPagoIf rolPagoIf : rolesPago) {
				if (mes.equals(rolPagoIf.getMes())
						&& anio.equals(rolPagoIf.getAnio())
						&& rubroEventual.getTipoRolIdPago().equals(
								rolPagoIf.getTiporolId())
						&& tipoContratoId.equals(rolPagoIf.getTipocontratoId())) {
					return rolPagoIf;
				}
			}
			Map<String, Object> mapaRolPago = new HashMap<String, Object>();
			mapaRolPago.put("mes", mes);
			mapaRolPago.put("anio", anio);
			mapaRolPago.put("tiporolId", rubroEventual.getTipoRolIdPago());
			mapaRolPago.put("tipocontratoId", tipoContratoId);
			Collection<RolPagoIf> rolesExistentes = findRolPagoByQuery(mapaRolPago);
			if (rolesExistentes.size() == 0) {
				RolPagoIf rolPagoIf = new RolPagoEJB();
				rolPagoIf.setTiporolId(rubroEventual.getTipoRolIdPago());
				rolPagoIf.setTipocontratoId(tipoContratoId);
				rolPagoIf.setFecha(new Date(new java.util.Date().getTime()));
				rolPagoIf.setMes(mes);
				rolPagoIf.setAnio(anio);
				rolPagoIf.setEstado(EstadoRolPago
						.getLetraEstadoRolPago(EstadoRolPago.GENERADO));
				RolPagoIf rp = addRolPago(rolPagoIf);
				return rp;
			} else if (rolesExistentes.size() == 1) {
				RolPagoIf rp = rolesExistentes.iterator().next();
				return rp;
			} else {
				throw new GenericBusinessException(
						"Existe mas de un rol de Pago con la misma Informacion !!! Llamar servicio Técnico !!");
			}
		}
		return null;
	}

	private RolPagoDetalleIf registrarRubroEventual(RolPagoIf rolPagoIf,
			Long contratoId, RubroEventualIf rubroEventual, BigDecimal total,
			String estado) throws GenericBusinessException {
		RolPagoDetalleIf rolPagoDetalleIf = new RolPagoDetalleData();
		rolPagoDetalleIf.setRolpagoId(rolPagoIf.getId());
		rolPagoDetalleIf.setContratoId(contratoId);
		rolPagoDetalleIf.setEstado(estado);
		rolPagoDetalleIf.setRubroEventualId(rubroEventual.getId());
		rolPagoDetalleIf.setValor(total);
		rolPagoDetalleIf.setObservacion(rubroEventual.getObservacion());
		return rolPagoDetalleIf;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarRolPago(RolPagoIf rolPagoIf)
			throws GenericBusinessException {

		try {
			// ELIMAR REGISTROS EXISTENTES
			Collection<RolPagoDetalleEJB> detalles = rolPagoDetalleLocal
					.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
			for (RolPagoDetalleEJB detalle : detalles) {
				rolPagoDetalleLocal.deleteRolPagoDetalle(detalle.getId());
			}
			deleteRolPago(rolPagoIf.getId());

		} catch (Exception ex) {
			ctx.setRollbackOnly();
			ex.printStackTrace();
			throw new GenericBusinessException(
					"Error al eliminar Rol de Pago !!");
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarRolPagoPorContrato(RolPagoIf rolPagoIf,
			ContratoIf contratoIf) throws GenericBusinessException {

		try {
			// ELIMAR REGISTROS EXISTENTES
			Map<String, Object> mapaEliminacion = new HashMap<String, Object>();
			mapaEliminacion.put("rolpagoId", rolPagoIf.getId());
			mapaEliminacion.put("contratoId", contratoIf.getId());
			mapaEliminacion.put("estado", EstadoRolPagoDetalle.EMITIDO
					.getLetra());
			Collection<RolPagoDetalleEJB> detalles = rolPagoDetalleLocal
					.findRolPagoDetalleByQuery(mapaEliminacion);
			for (RolPagoDetalleEJB detalle : detalles) {
				rolPagoDetalleLocal.deleteRolPagoDetalle(detalle.getId());
			}
			deleteRolPago(rolPagoIf.getId());

		} catch (Exception ex) {
			ctx.setRollbackOnly();
			ex.printStackTrace();
			throw new GenericBusinessException(
					"Error al eliminar Rol de Pago !!");
		}

	}

	public void eliminarRubroEventual(Long rubroEventualId)
			throws GenericBusinessException {

		RubroEventualIf rubroEventualIf = rubroEventualLocal
				.getRubroEventual(rubroEventualId);
		if (EstadoRubroEventual.EMITIDO.getLetra().equals(
				rubroEventualIf.getEstado())) {
			rubroEventualLocal.deleteRubroEventual(rubroEventualIf.getId());
			Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
					.findRolPagoDetalleByRubroEventualId(rubroEventualIf
							.getId());
			if (detalles.size() == 1) {
				RolPagoDetalleIf rpd = detalles.iterator().next();
				if (EstadoRolPagoDetalle.EMITIDO.getLetra().equals(
						rpd.getEstado())) {
					rolPagoDetalleLocal.deleteRolPagoDetalle(rpd.getId());
				} else {
					throw new GenericBusinessException(
							"No se puede eliminar Rubro Eventual, ya ha sido Autorizado o Pagado en el Rol de Pago !!");
				}

			} else if (detalles.size() > 1) {
				throw new GenericBusinessException(
						"Error en la eliminacion de Rubro Eventual, está suplicado en Rol Detalle !!");
			}
		} else {
			throw new GenericBusinessException(
					"No se puede eliminar Rubro Eventual, ya ha sido Autorizado o Pagado !!");
		}

	}

	/**
	 * ********************* RESULTADOS POR TIPO DE ROL *****************************
	 */

	public Collection<Map<String, Object>> crearColeccionContratos(
			Collection<Long> contratosId, RolPagoIf rolPagoIf, String estado,
			boolean devolverRolPagoDetalleId, boolean verValoresRolenR13Rv, boolean verValoresPagoR13Rv, 
			String decimoCuarto, boolean metodoLlamadoDesdeRolPagoModel)
			throws GenericBusinessException {

		TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
		TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
		if (tipoRol == TipoRol.MENSUAL)
			return crearColeccionContratosMensual(contratosId, rolPagoIf,
					tipoRolIf, tipoRol, estado, devolverRolPagoDetalleId);
		else if (tipoRol == TipoRol.QUINCENAL)
			return crearColeccionContratosQuincenal(contratosId, rolPagoIf,
					tipoRolIf, tipoRol, estado, devolverRolPagoDetalleId);
		else if (tipoRol == TipoRol.APORTE_PATRONAL
				|| tipoRol == TipoRol.FONDO_RESERVA)
			return crearColeccionAportePatronalFondoReserva(contratosId,
					rolPagoIf, tipoRol, devolverRolPagoDetalleId);
		else if (tipoRol == TipoRol.DECIMO_TERCERO
				|| tipoRol == TipoRol.DECIMO_CUARTO
				|| tipoRol == TipoRol.VACACIONES)
			return crearColeccionContratosDecimos(contratosId, rolPagoIf, estado,
					devolverRolPagoDetalleId, tipoRolIf, verValoresRolenR13Rv, verValoresPagoR13Rv, 
					decimoCuarto, metodoLlamadoDesdeRolPagoModel);
		else if ( tipoRol == TipoRol.UTILIDADES )
			return crearColeccionUtilidades(rolPagoIf, 
				tipoRol, devolverRolPagoDetalleId);
		return null;
	}
	
	private Collection<Map<String, Object>> crearColeccionUtilidades(
			RolPagoIf rolPagoIf, TipoRol tipoRol,
			boolean devolverRolPagoDetalleId) throws GenericBusinessException{
		Collection<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		Collection<RolPagoDetalleUtilidadIf> rpdus = 
			rolPagoDetalleUtilidadLocal.findRolPagoDetalleUtilidadByRolpagoId(rolPagoIf.getId());
		
		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		
		for ( RolPagoDetalleUtilidadIf rpdu : rpdus ){
			Map<String,Object> mapa =  new HashMap<String, Object>();
			String nombre = nominaUtilesLocal.verificarEmpleadoEnMapa(
				rpdu.getContratoId(),mapaContratoIdNombreEmpleado);
			mapa.put("nombreEmpleado", nombre);
			mapa.put("cargo", rpdu.getCargo());
			String fechaIngreso = rpdu.getFechaIngreso() != null ?
				utilitariosLocal.getFechaCortaUppercase(rpdu.getFechaIngreso()) : 
				null;
			mapa.put("fechaIngreso", fechaIngreso);
			String fechaSalida = rpdu.getFechaSalida() != null ?
				utilitariosLocal.getFechaCortaUppercase(rpdu.getFechaSalida()) : 
				null;
			mapa.put("fechaSalida",fechaSalida);
			mapa.put("genero", rpdu.getGenero());
			mapa.put("diasLaborados", rpdu.getDiasLaborados());
			mapa.put("utilidad", rpdu.getUtilidadContrato());
			mapa.put("numeroCargas", rpdu.getNumeroCargas());
			mapa.put("diasCargas", rpdu.getDiasCargas());
			mapa.put("utilidadCargas", rpdu.getUtilidadCargas());
			mapa.put("total", rpdu.getTotal());
			lista.add(mapa);
		}
		
		return lista;
	}

	private Collection<Map<String, Object>> crearColeccionAportePatronalFondoReserva(
			Collection<Long> contratosId, RolPagoIf rolPagoIf, TipoRol tipoRol,
			boolean devolverRolPagoDetalleId) throws GenericBusinessException {

		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();

		Collection<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> mapaConsulta = new HashMap<String, Object>();
		mapaConsulta.put("rolpagoId", rolPagoIf.getId());
		// Collection<RolPagoDetalleIf> rolPagoDetalles =
		// rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());

		Map<Long, Double> mapaTotales = new HashMap<Long, Double>();

		Collection<String> listaMesesAnio = null;
		Map<Long, RolPagoIf> mapaRolesPago = null;
		Map<String, Object> mapaFondoReserva = new HashMap<String, Object>();
		if (tipoRol == TipoRol.FONDO_RESERVA) {

			mapaFondoReserva.put("rolPago", rolPagoIf);

			Integer anioI = Integer.valueOf(rolPagoIf.getAnio());
			listaMesesAnio = getFechasDeAnioFondoReserva(anioI, null);
			mapaRolesPago = listaDeRolesPagoPorAnio(rolPagoIf, listaMesesAnio);

		}

		for (Long contratoId : contratosId) {

			Collection<RolPagoDetalleIf> detallesRolPago = null;
			if (devolverRolPagoDetalleId)
				detallesRolPago = new Vector<RolPagoDetalleIf>();

			mapaConsulta.put("contratoId", contratoId);
			Map<String, Object> mapa = new HashMap<String, Object>();

			if (tipoRol == TipoRol.FONDO_RESERVA) {
				for (String linea : listaMesesAnio) {
					Double cero = 0.00D;
					String mes = linea.split("-")[2];
					mapa.put(mes, cero);
				}
				mapaFondoReserva.put("contratoId", contratoId);
				getValoresTipoRolPorAnio(mapaFondoReserva, mapa, mapaTotales, rolPagoIf.getTiporolId(),
						mapaRolesPago, EstadoRolPagoDetalle.EMITIDO.getLetra(),
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
						EstadoRolPagoDetalle.PAGADO.getLetra());
			}

			if (mapa.get("nombreEmpleado") == null) {
				nominaUtilesLocal.verificarEmpleadoEnMapa(contratoId,
						mapaContratoIdNombreEmpleado);
				mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado
						.get(contratoId));
			}

			Double total = 0.0;
			Collection<RolPagoDetalleIf> rolPagoDetalles = rolPagoDetalleLocal
					.findRolPagoDetalleByQuery(mapaConsulta);
			for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
				if (devolverRolPagoDetalleId)
					detallesRolPago.add(rolPagoDetalle);

				// mapa.put("nombreEmpleado",
				// mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId())
				// );
				mapa.put("cuentaBancariaId", rolPagoDetalle
						.getCuentaBancariaId());
				if (tipoRol == TipoRol.APORTE_PATRONAL) {
					Double valor = utilitariosLocal
							.redondeoValor(rolPagoDetalle.getValor()
									.doubleValue());
					total += valor;
				}
			}
			if (devolverRolPagoDetalleId)
				mapa.put("detallesRolPago", detallesRolPago);

			if (tipoRol == TipoRol.APORTE_PATRONAL) {
				total = aumentarTotalPorEmpleado(mapaTotales, contratoId, total);
			} else {
				total = aumentarTotalPorEmpleado(mapaTotales, contratoId, 0.0);
			}

			mapa.put("contratoId", contratoId);
			mapa.put("total", utilitariosLocal.redondeoValor(total));
			lista.add(mapa);
		}

		Comparator<Map> comparadorMapaNombre = new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				String nombre1 = (String) o1.get("nombreEmpleado");
				String nombre2 = (String) o2.get("nombreEmpleado");
				return nombre1.compareTo(nombre2);
			}
		};

		Collections.sort((ArrayList) lista, comparadorMapaNombre);
		return lista;
	}

	private Collection<Map<String, Object>> crearColeccionContratosDecimos(
			Collection<Long> contratosId, RolPagoIf rolPagoIf,
			String estado, boolean devolverRolPagoDetalleId, TipoRolIf tipoRolIf, 
			boolean verValoresRolEnR13Rv, boolean verValoresPagoR13Rv, String decimoCuarto, 
			boolean metodoLlamadoDesdeRolPagoModel) 
			throws GenericBusinessException {
		
		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		Collection<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> mapaConsulta = new HashMap<String, Object>();
		mapaConsulta.put("rolpagoId", rolPagoIf.getId());

		ArrayList<String> listaMesesAnio = new ArrayList<String>();
		Map<Long, RolPagoIf> mapaRolesPago = new HashMap<Long, RolPagoIf>();
		
		//El tipo de rol de los Decimos tienen forma de pago: por fechas
		//El decimo tercero se calcula de diciembre de año X a noviembre de año Y (Y = X + 1)
		//El decimo cuarto se calcula de Marzo a Febrero (Costa) y de Septiembre a Agosto (Sierra)
		//Entonces la fecha Inicial de los Decimos va a estar en el año X y la fecha final en el año Y 
				
		//Para el Decimo Tercero
		int anioInicioR13 = 0;
		int mesInicioR13 = 0;
		int anioFinR13 = 0;
		int mesFinR13 = 0;
		java.sql.Date fechaInicioR13 = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		java.sql.Date fechaFinR13 = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		
		if(metodoLlamadoDesdeRolPagoModel && tipoRolIf.getCodigo().equals("R13")){
			Map<String ,Collection<String>> listaMesesAnios = new HashMap<String ,Collection<String>>();
			
			int anio = Integer.valueOf(rolPagoIf.getAnio());
			int mes = Integer.valueOf(rolPagoIf.getMes());			
			
			//segun el año y mes del rol veo en que periodo cae del Decimo Tercero
			//si el mes es Diciembre es el primer mes porque el Decimo Tercero va de Diciembre a Noviembre
			if(mes == 12){
				anioInicioR13 = anio;
				mesInicioR13 = mes - 1;
				anioFinR13 = anio + 1;
				mesFinR13 = 10;
			}else{
				anioInicioR13 = anio - 1;
				mesInicioR13 = 11;
				anioFinR13 = anio;
				mesFinR13 = 10;
			}
			
			listaMesesAnio = getFechasDeAnioReporteDecimos(anioInicioR13, mesInicioR13, anioFinR13, mesFinR13);
			
			fechaInicioR13 = new java.sql.Date(anioInicioR13-1900, mesInicioR13, 1);
			fechaFinR13 = new java.sql.Date(anioFinR13-1900, mesFinR13, 30);
			
		} else if(metodoLlamadoDesdeRolPagoModel && tipoRolIf.getCodigo().equals("R14")){
			Map<String ,Collection<String>> listaMesesAnios = new HashMap<String ,Collection<String>>();
			
			int anio = Integer.valueOf(rolPagoIf.getAnio());
			int mes = Integer.valueOf(rolPagoIf.getMes());			
			
			//segun el año y mes del rol veo en que periodo cae del Decimo Cuarto
			//Decimo Cuarto Costa: si el mes es Marzo es el primer mes porque va de Marzo a Febrero
			//Decimo Cuarto Sierra: si el mes es Septiembre es el primer mes porque va de Septiembre a Agosto
			
			if(decimoCuarto.equals("COSTA")){
				mesInicioR13 = 2; //2 es Marzo
				mesFinR13 = 1; //1 es Febrero
				
				//Marzo grabado en la tabla Rol Pago si es 3
				if(mes >= 3){
					anioInicioR13 = anio;
					anioFinR13 = anio + 1;
				}else{
					anioInicioR13 = anio - 1;
					anioFinR13 = anio;
				}
			}else if(decimoCuarto.equals("SIERRA")){
				mesInicioR13 = 8; //8 es Septiembre
				mesFinR13 = 7; //7 es Agosto
				
				//Septiembre grabado en la tabla Rol Pago si es 9
				if(mes >= 9){
					anioInicioR13 = anio;
					anioFinR13 = anio + 1;
				}else{
					anioInicioR13 = anio - 1;
					anioFinR13 = anio;
				}
			}else {
				mesInicioR13 = 0; //0 es Enero
				mesFinR13 = 11; //11 es Diciembre
				anioInicioR13 = anio;
				anioFinR13 = anio;
			}			
			
			listaMesesAnio = getFechasDeAnioReporteDecimos(anioInicioR13, mesInicioR13, anioFinR13, mesFinR13);
			
			fechaInicioR13 = new java.sql.Date(anioInicioR13-1900, mesInicioR13, 1);
			fechaFinR13 = new java.sql.Date(anioFinR13-1900, mesFinR13, 30);
			
		//INI_CAMBIO_20140611 Se crean las fechas en el año desde y hasta cuando se calcula el Rol por Vacaciones
		}else if(metodoLlamadoDesdeRolPagoModel && tipoRolIf.getCodigo().equals("RVA")){
			Map<String ,Collection<String>> listaMesesAnios = new HashMap<String ,Collection<String>>();
			
			int anio = Integer.valueOf(rolPagoIf.getAnio());
			int mes = Integer.valueOf(rolPagoIf.getMes());			
			
			//Vacaciones se contabilizan de Enero a Diciembre
			anioInicioR13 = anio;
			mesInicioR13 = 0;
			anioFinR13 = anio;
			mesFinR13 = 11;
				
			listaMesesAnio = getFechasDeAnioReporteDecimos(anioInicioR13, mesInicioR13, anioFinR13, mesFinR13);
			
			fechaInicioR13 = new java.sql.Date(anioInicioR13-1900, mesInicioR13, 1);
			fechaFinR13 = new java.sql.Date(anioFinR13-1900, mesFinR13, 30);
		//FIN_CAMBIO_20140611	
									
		}else{
			Integer anioI = Integer.valueOf(rolPagoIf.getAnio());
			
			listaMesesAnio = getFechasDeAnioReporteDecimos(anioI);		
		}	
		
		mapaRolesPago = listaDeRolesPagoPorAnio(rolPagoIf, listaMesesAnio);
		
		Map<Long,Long> contratosInactivos = new HashMap<Long,Long>();
		//si verValoresRolEnR13 es verdadero (true), reemplazo los RolPagoIf de decimo tercero 
		//por los de rol mensual porque necesito valores completos de Sueldo basico y Otros ingresos
		if(metodoLlamadoDesdeRolPagoModel && tipoRolIf.getCodigo().equals("R13") && verValoresRolEnR13Rv){
			Iterator mapaRolesPagoR13 = mapaRolesPago.keySet().iterator();
			while(mapaRolesPagoR13.hasNext()){
				Long rolR13id = (Long)mapaRolesPagoR13.next();
				RolPagoIf rolR13 = mapaRolesPago.get(rolR13id);
			
				Map<String, Object> mapaRolPago = new HashMap<String, Object>();
				mapaRolPago.put("tiporolId", 41L);
				mapaRolPago.put("tipocontratoId", rolR13.getTipocontratoId());
				mapaRolPago.put("mes", rolR13.getMes());
				mapaRolPago.put("anio", rolR13.getAnio());
				Collection<RolPagoIf> rolesPagoMensual = findRolPagoByQuery(mapaRolPago);
				if(rolesPagoMensual.size() > 0){
					mapaRolesPago.put(rolR13id, rolesPagoMensual.iterator().next());
				}
			}
		}
		
		//INI_CAMBIO_20140611 Se crea estructura para el calculo del Rol de Vacaciones
		if(metodoLlamadoDesdeRolPagoModel && tipoRolIf.getCodigo().equals("RVA") && verValoresRolEnR13Rv){
			Iterator mapaRolesPagoRV = mapaRolesPago.keySet().iterator();
			while(mapaRolesPagoRV.hasNext()){
				Long rolRVid = (Long)mapaRolesPagoRV.next();
				RolPagoIf rolRV = mapaRolesPago.get(rolRVid);
			
				Map<String, Object> mapaRolPago = new HashMap<String, Object>();
				mapaRolPago.put("tiporolId", 41L);
				mapaRolPago.put("tipocontratoId", rolRV.getTipocontratoId());
				mapaRolPago.put("mes", rolRV.getMes());
				mapaRolPago.put("anio", rolRV.getAnio());
				Collection<RolPagoIf> rolesPagoMensual = findRolPagoByQuery(mapaRolPago);
				if(rolesPagoMensual.size() > 0){
					mapaRolesPago.put(rolRVid, rolesPagoMensual.iterator().next());
				}
			}		
		}
		//FIN_CAMBIO_20140611
		
		Map<Long, Double> mapaTotales = new HashMap<Long, Double>();
		Map<String, Object> mapaInfoRolPorAnio = new HashMap<String, Object>();
		mapaInfoRolPorAnio.put("rolPago", rolPagoIf);

		//lleno los valores por cada contrato
		for (Long contratoId : contratosId) {
			
			//si el contrato no esta inactivo
			if(contratosInactivos.get(contratoId) == null){
				
				boolean contratoEnRol = true;
				//si se desea sacar el R14 se debe ver si es costa o sierra
				if(metodoLlamadoDesdeRolPagoModel && tipoRolIf.getCodigo().equals("R14")){
					ContratoIf contrato = contratoLocal.getContrato(contratoId);
					EmpleadoIf empleado = empleadoLocal.getEmpleado(contrato.getEmpleadoId());
					OficinaIf oficina = oficinaLocal.getOficina(empleado.getOficinaId());
					if((decimoCuarto.equals("COSTA") && oficina.getCodigo().equals("GYE")) || 
							(decimoCuarto.equals("QUITO") && oficina.getCodigo().equals("UIO"))){
						contratoEnRol = false;
					}
				}
				
				//si no es R14, o si es R14 y es de la oficina correcta según se haya elegido costa o sierra
				if(contratoEnRol){				
					
					Map<String, Object> mapa = new HashMap<String, Object>();
					
					//si se desea sacar el reporte de pago de la decimotercera remuneración o de vacaciones
					if(verValoresPagoR13Rv){
						ContratoIf contrato = contratoLocal.getContrato(contratoId);
						EmpleadoIf empleado = empleadoLocal.getEmpleado(contrato.getEmpleadoId());
											
						mapa.put("cedula", empleado.getIdentificacion());
						mapa.put("apellidos", empleado.getApellidos());
						mapa.put("nombres", empleado.getNombres());
						mapa.put("ocupacion", "");
						
						String genero = "";
						if(empleadoPersonalLocal.findEmpleadoPersonalByEmpleadoId(empleado.getId()).size() > 0){
							EmpleadoPersonalIf empleadoPersonal = (EmpleadoPersonalIf)empleadoPersonalLocal.findEmpleadoPersonalByEmpleadoId(empleado.getId()).iterator().next();
							genero = empleadoPersonal.getSexo();
						}					
						mapa.put("genero", genero);
						
						//cálculo de días trabajados en el periodo del decimo tercero				
						calculoDiasTrabajados(anioInicioR13, anioFinR13, fechaInicioR13, fechaFinR13, mapa, contrato);
										
						//calculo total ganado
						mapaInfoRolPorAnio.put("contratoId", contratoId);
						getValoresTipoRolPorAnio(mapaInfoRolPorAnio, mapa, mapaTotales, rolPagoIf.getTiporolId(),
								mapaRolesPago, EstadoRolPagoDetalle.EMITIDO.getLetra(),
								EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
								EstadoRolPagoDetalle.PAGADO.getLetra());
											
						mapa.put("totalGanado", mapaTotales.get(contratoId));
						
						mapa.put("retencion", 0D);
						
						mapa.put("valorDecimo", mapaTotales.get(contratoId) / 12D);
						
						//valor se añade para reporte de vacaciones
						mapa.put("valorVacaciones", mapaTotales.get(contratoId) / 24D);
						
						mapa.put("firma", "");
					}
					
					//caso contrario se saca los reportes normales
					else{
						Double totalMeses = 0.0;
						for (String linea : listaMesesAnio) {
							Double cero = 0.00D;
							String mes = linea.split("-")[2];
							mapa.put(mes, cero);
						}
						
						mapaInfoRolPorAnio.put("contratoId", contratoId);
						 
						if ( estado == null )
							getValoresTipoRolPorAnio(mapaInfoRolPorAnio, mapa, mapaTotales, rolPagoIf.getTiporolId(),
									mapaRolesPago, EstadoRolPagoDetalle.EMITIDO.getLetra(),
									EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
									EstadoRolPagoDetalle.PAGADO.getLetra());
						else 
							getValoresTipoRolPorAnio(mapaInfoRolPorAnio, mapa, mapaTotales, rolPagoIf.getTiporolId(),
									mapaRolesPago, estado);

						mapaConsulta.put("contratoId", contratoId);
						
						Collection<RolPagoDetalleIf> rolPagoDetalles = rolPagoDetalleLocal
								.findRolPagoDetalleByQuery(mapaConsulta);
						// encerarValoresMeses(mapa);

						if (mapa.get("nombreEmpleado") == null) {
							nominaUtilesLocal.verificarEmpleadoEnMapa(contratoId,
									mapaContratoIdNombreEmpleado);
							mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado
									.get(contratoId));
							mapa.put("contratoId", contratoId);
						}

						Collection<RolPagoDetalleIf> detallesRolPago = null;
						if (devolverRolPagoDetalleId)
							detallesRolPago = new Vector<RolPagoDetalleIf>();

						for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
							if (devolverRolPagoDetalleId)
								detallesRolPago.add(rolPagoDetalle);

							if (mapa.get("contratoId") == null)
								mapa.put("contratoId", rolPagoDetalle.getContratoId());
							mapa.put("cuentaBancariaId", rolPagoDetalle.getCuentaBancariaId());
						}
						if (devolverRolPagoDetalleId)
							mapa.put("detallesRolPago", detallesRolPago);

						totalMeses = aumentarTotalPorEmpleado(mapaTotales, contratoId, 0.0);

						mapa.put("total", utilitariosLocal.redondeoValor(totalMeses));
					}
					
					lista.add(mapa);
				}

			}		
		}
		
		Comparator<Map> comparadorMapaNombre = new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				
				String nombre1 = (String) o1.get("nombreEmpleado");
				String nombre2 = (String) o2.get("nombreEmpleado");
				
				if(o1.get("apellidos") != null && o1.get("nombres") != null
						&& o2.get("apellidos") != null && o2.get("nombres") != null){
					nombre1 = (String)o1.get("apellidos") + " " + (String)o1.get("nombres");
					nombre2 = (String)o2.get("apellidos") + " " + (String)o2.get("nombres");
				}else{
					nombre1 = (String) o1.get("nombreEmpleado");
					nombre2 = (String) o2.get("nombreEmpleado");
				}				
				
				if (nombre1 == null)
					System.out.println("");
				if (nombre2 == null)
					System.out.println("");
				return nombre1.compareTo(nombre2);
			}
		};
		Collections.sort((ArrayList) lista, comparadorMapaNombre);
		return lista;
	}

	private void calculoDiasTrabajados(int anioInicioR13, int anioFinR13,
			java.sql.Date fechaInicioR13, java.sql.Date fechaFinR13,
			Map<String, Object> mapa, ContratoIf contrato) {
		//busco fecha Inicio y Fin de Contrato
		java.sql.Date fechaInicioContrato = contrato.getFechaInicio();
		java.sql.Date fechaFinContrato = contrato.getFechaFin();
		
		//veo si el contrato cubre todo el periodo del R13 o es un contrato nuevo que solo cubre una parte
		int anioInicioContrato = Integer.valueOf(fechaInicioContrato.getYear()) + 1900;
		int mesInicioContrato = Integer.valueOf(fechaInicioContrato.getMonth()) + 1;
		int diaInicioContrato = Integer.valueOf(fechaInicioContrato.getDate());
		int anioFinContrato = Integer.valueOf(fechaFinContrato.getYear()) + 1900;
		int mesFinContrato = Integer.valueOf(fechaFinContrato.getMonth()) + 1;
		int diaFinContrato = Integer.valueOf(fechaFinContrato.getDate());
		
		//tomando en cuenta que no se repiten los meses, siempre va de dic a nov
		if(fechaInicioContrato.compareTo(fechaInicioR13) <= 0 &&  fechaFinContrato.compareTo(fechaFinR13) >= 0){
			mapa.put("diasTrabajados", "360");		
		}
		//comienza antes pero termina hasta el 29 de nov
		else if(fechaInicioContrato.compareTo(fechaInicioR13) <= 0){
			int diasTrabajados = 0;
			//si el contrato termino en dic
			if(anioFinContrato == anioInicioR13){
				if(diaFinContrato == 30 || diaFinContrato == 31){
					diasTrabajados = 30;
				}else{
					diasTrabajados = diaFinContrato;
				}
			}else if(anioFinContrato == anioFinR13){
				//por dic
				diasTrabajados = 30;
				
				//mes fin puede ir de 1 a 12
				diasTrabajados = diasTrabajados + (mesFinContrato * 30);
				
				//si el contrato no termina en febrero
				if(mesFinContrato != 2){
					if(diaFinContrato != 30 && diaFinContrato != 31){
						diasTrabajados = diasTrabajados - (30 - diaFinContrato);
					}
				}else{
					if(diaFinContrato != 28 && diaFinContrato != 29){
						diasTrabajados = diasTrabajados - (30 - diaFinContrato);
					}
				}							
			}
									
			mapa.put("diasTrabajados", String.valueOf(diasTrabajados));
		}
		//termina despues pero comienza minimo el 2 de dic
		else if(fechaFinContrato.compareTo(fechaFinR13) >= 0){
			int diasTrabajados = 0;
			
			//si el contrato inicia en dic, se suma uno porque se considera el dia que entro a trabajar
			if(anioInicioContrato == anioInicioR13){
				diasTrabajados = 30 - diaInicioContrato + 1;
				diasTrabajados = diasTrabajados + 330;
			}else{
				//dias no trabajados
				int diasNoTrabajados = mesInicioContrato * 30;
				
				if(diaInicioContrato == 1){
					diasNoTrabajados = diasNoTrabajados - 30;
				}else if(mesInicioContrato != 2 && (diaInicioContrato == 30 || diaInicioContrato == 31)){
					diasNoTrabajados = diasNoTrabajados - 1;
				}else if(mesInicioContrato != 2){
					//se suma uno por el dia de inicio de contrato que si se lo toma en cuenta
					diasNoTrabajados = diasNoTrabajados - (30 - diaInicioContrato + 1);
				}else if(mesInicioContrato == 2 && (diaInicioContrato == 28 || diaInicioContrato == 29)){
					diasNoTrabajados = diasNoTrabajados - 1;
				}else if(mesInicioContrato == 2 ){
					diasNoTrabajados = diasNoTrabajados - (28 - diaInicioContrato + 1);
				}
				
				diasTrabajados = 330 - diasNoTrabajados;
			}
			
			mapa.put("diasTrabajados", String.valueOf(diasTrabajados));
		}
		//el contrato inicia minimo el 2 de dic y termina maximo el 29 de nov
		else{
			int diasTrabajados = 0;
			
			//si el contrato inicia en dic
			if(anioInicioContrato == anioInicioR13 && anioFinContrato == anioInicioR13){
				diasTrabajados = diaFinContrato - diaInicioContrato + 1;
			}else if(anioInicioContrato == anioFinR13 && anioFinContrato == anioFinR13){
				diasTrabajados = (mesFinContrato - mesInicioContrato) * 30;
				
				//si no empezo desde el día 1, y le sumo 1 para incluir el día que empezo
				if(diaInicioContrato > 1){
					diasTrabajados = diasTrabajados - diaInicioContrato + 1;
				}
				
				//si no termino el 30 o 28 si es febrero
				if(mesFinContrato != 2){
					if(diaFinContrato == 30 || diaFinContrato == 31){
						diasTrabajados = diasTrabajados + 30;
					}else{
						diasTrabajados = diasTrabajados + diaFinContrato;
					}								
				}else if(mesFinContrato == 2){
					if(diaFinContrato == 28 || diaFinContrato == 29){
						diasTrabajados = diasTrabajados + 30;
					}else{
						diasTrabajados = diasTrabajados + diaFinContrato;
					}
				}		
			}else if(anioInicioContrato == anioInicioR13 && anioFinContrato == anioFinR13){
				//por dic
				diasTrabajados = 30 - diaInicioContrato + 1;
				
				//mes fin puede ir de 1 a 12
				diasTrabajados = diasTrabajados + (mesFinContrato * 30);
				
				//si el contrato no termina en febrero
				if(mesFinContrato != 2){
					if(diaFinContrato != 30 && diaFinContrato != 31){
						diasTrabajados = diasTrabajados - (30 - diaFinContrato);
					}
				}else{
					if(diaFinContrato != 28 && diaFinContrato != 29){
						diasTrabajados = diasTrabajados - (30 - diaFinContrato);
					}
				}	
			}
									
			mapa.put("diasTrabajados", String.valueOf(diasTrabajados));
		}
	}

	private Double aumentarTotalPorEmpleado(Map<Long, Double> mapaTotales,
			Long contratoId, Double valor) {

		Double total = mapaTotales.get(contratoId);
		if (total == null) {
			mapaTotales.put(contratoId, utilitariosLocal.redondeoValor(valor));
			total = valor;
		} else {
			total += valor;
			mapaTotales.put(contratoId, utilitariosLocal.redondeoValor(total));
		}

		return total;
	}

	private void encerarValoresMeses(Map mapa) {
		for (String nombreMes : utilitariosLocal.getMesesMayusculas()) {
			mapa.put(nombreMes, 0.0);
		}
	}

	public Double sumaImpuestoRentaRol(Collection<Long> contratosId,
			RolPagoIf rolPagoIf, TipoRolIf tipoRolIf, TipoRol tipoRol,
			String estado, boolean devolverRolPagoDetalleId)
			throws GenericBusinessException {

		Double impuestoRentaEmpleados = 0D;
		Double otrosIngresos = null;
		Double totalIngresos = null;
		Double otrosDescuentos = null;
		Double totalDescuentos = null;

		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		HashMap<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		HashMap<Long, String> mapaTipoRubros = new HashMap<Long, String>();
		Collection rolPagoContratoCollection = new ArrayList();

		for (Long contratoId : contratosId) {

			Collection<RolPagoDetalleIf> rolPagoDetalles = buscarRolPagoDetalle(
					rolPagoIf, contratoId, tipoRol, estado);
			ContabilizarRubrosNormales: for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
				Double valor = utilitariosLocal.redondeoValor(rolPagoDetalle
						.getValor().doubleValue());
				Long idRubro = rolPagoDetalle.getRubroId();
				// Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
				if (idRubro != null) {
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
							mapaRubros, mapaTipoRubros, idRubro);

					// No se toma en cuenta los normales
					if ("A".equals(rubroIf.getTipoRubro())
							|| "P".equals(rubroIf.getTipoRubro()))
						continue ContabilizarRubrosNormales;

					String nombre = rubroIf.getNombre();
					if (nombre.contains("IMPUESTO") && nombre.contains("RENTA"))
						impuestoRentaEmpleados = impuestoRentaEmpleados + valor;
				}
			}
		}

		return impuestoRentaEmpleados;
	}

	public Double sumaSueldos(Collection<Long> contratosId,
			RolPagoIf rolPagoIf, TipoRolIf tipoRolIf, TipoRol tipoRol,
			String estado, boolean devolverRolPagoDetalleId)
			throws GenericBusinessException {

		Double sueldos = 0D;
		Double otrosIngresos = null;
		Double totalIngresos = null;
		Double otrosDescuentos = null;
		Double totalDescuentos = null;

		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		HashMap<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		HashMap<Long, String> mapaTipoRubros = new HashMap<Long, String>();
		Collection rolPagoContratoCollection = new ArrayList();

		for (Long contratoId : contratosId) {

			Collection<RolPagoDetalleIf> rolPagoDetalles = buscarRolPagoDetalle(
					rolPagoIf, contratoId, tipoRol, estado);

		 
			ContabilizarRubrosNormales: for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
				Double valor = utilitariosLocal.redondeoValor(rolPagoDetalle
						.getValor().doubleValue());
				Long idRubro = rolPagoDetalle.getRubroId();
				// Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
				if (idRubro != null) {
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
							mapaRubros, mapaTipoRubros, idRubro);
 
					// No se toma en cuenta los normales
					if ("A".equals(rubroIf.getTipoRubro())
							|| "P".equals(rubroIf.getTipoRubro()))
						continue ContabilizarRubrosNormales;

					String nombre = rubroIf.getNombre();
					if (nombre.contains("SUELDO"))
						sueldos = sueldos + valor;
				}
			}
		}

		return sueldos;
	}

	private Collection<Map<String, Object>> crearColeccionContratosMensual(
			Collection<Long> contratosId, RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, TipoRol tipoRol, String estado,
			boolean devolverRolPagoDetalleId) throws GenericBusinessException {
		Double otrosIngresos = null;
		Double totalIngresos = null;
		Double otrosDescuentos = null;
		Double totalDescuentos = null;

		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		HashMap<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		HashMap<Long, String> mapaTipoRubros = new HashMap<Long, String>();
		Collection rolPagoContratoCollection = new ArrayList();

		for (Long contratoId : contratosId) {
			Collection<RolPagoDetalleIf> rolPagoDetalles = buscarRolPagoDetalle(
					rolPagoIf, contratoId, tipoRol, estado);
			Map<String, Object> mapa = new HashMap<String, Object>();

			otrosIngresos = 0.0;
			totalIngresos = 0.0;
			otrosDescuentos = 0.0;
			totalDescuentos = 0.0;
			mapa.put("otrosIngresos", otrosIngresos);
			mapa.put("totalIngresos", totalIngresos);
			mapa.put("otrosDescuentos", otrosDescuentos);
			mapa.put("totalDescuentos", totalDescuentos);
						
			Collection<RolPagoDetalleIf> detallesRolPago = null;
			if (devolverRolPagoDetalleId)
				detallesRolPago = new Vector<RolPagoDetalleIf>();

			inicializarValoresMapaMensual(mapa);

			if (mapa.get("nombreEmpleado") == null) {
				nominaUtilesLocal.verificarEmpleadoEnMapa(contratoId,mapaContratoIdNombreEmpleado);
				mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado.get(contratoId));
			}

			ContabilizarRubrosNormales: 
			for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {

				// utilesLocal.verificarEmpleadoEnMapa(rolPagoDetalle,mapaContratoIdNombreEmpleado);

				Double valor = utilitariosLocal.redondeoValor(rolPagoDetalle.getValor().doubleValue());
				// mapa.put("nombreEmpleado",
				// mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId()));
				mapa.put("cuentaBancariaId", rolPagoDetalle.getCuentaBancariaId());

				Long idRubro = rolPagoDetalle.getRubroId();
				// Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
				if (idRubro != null) {
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
							mapaRubros, mapaTipoRubros, idRubro);

					// No se toma en cuenta los anticipos o provisiones
					//if ("A".equals(rubroIf.getTipoRubro()) || "P".equals(rubroIf.getTipoRubro()))
					if (TipoRubro.ANTICIPO.getLetra().equals(rubroIf.getTipoRubro()) || 
						TipoRubro.PROVISION.getLetra().equals(rubroIf.getTipoRubro()))
						continue ContabilizarRubrosNormales;

					if (devolverRolPagoDetalleId)
						detallesRolPago.add(rolPagoDetalle);

					String nombre = rubroIf.getNombre();
					
					if ( TipoRubro.SUELDO.getLetra().equals(rubroIf.getTipoRubro()) ) {
						Double valorGuardado = mapa.get("iess") != null ? (Double) mapa.get("sueldo") : 0.0;
						valor = utilitariosLocal.redondeoValor(valor+ valorGuardado);
						mapa.put("sueldo", valor);
						totalIngresos += valor;
					} else if (TipoRubro.QUINCENA.getLetra().equals(rubroIf.getTipoRubro())) {
						Double valorGuardado = mapa.get("iess") != null ? (Double) mapa.get("anticipoQuincena") : 0.0;
						valor = utilitariosLocal.redondeoValor(valor+ valorGuardado);
						mapa.put("anticipoQuincena", valor);
						totalDescuentos += valor;
					} else if (nombre.contains("APORTE") && nombre.contains("IESS")) {
						Double valorGuardado = mapa.get("iess") != null ? (Double) mapa.get("iess") : 0.0;
						valor = utilitariosLocal.redondeoValor(valor+ valorGuardado);
						mapa.put("iess", valor);
						totalDescuentos += valor;
					} else if (nombre.contains("IMPUESTO") && nombre.contains("RENTA")) {
						mapa.put("impuestoRenta", valor);
						totalDescuentos += valor;
					} else if (nombre.contains("PRESTAMO") && nombre.contains("COMPA")) {
						mapa.put("prestamoCompania", valor);
						totalDescuentos += valor;
					} else if (nombre.contains("FONDO DE RESERVA (BENEFICIO)")) {
						mapa.put("fondoReservaBeneficio", valor);
						totalIngresos += valor;
					} else {
						calculoOtrosValores(valor, rubroIf, mapa, tipoRolIf);
					}
				} else {
					/*
					 * if ( tipoRol == TipoRol.MENSUAL ){ RubroEventualIf
					 * rubroEventualIf =
					 * rubroEventualLocal.getRubroEventual(rolPagoDetalle.getRubroEventualId());
					 * RubroIf rubroIf =
					 * utilesLocal.verificarRubrosEnMapa(mapaRubros,mapaTipoRubros,rubroEventualIf.getRubroId());
					 * calculoOtrosValores(valor, rubroIf,mapa,tipoRolIf);
					 * 
					 * if ( devolverRolPagoDetalleId )
					 * detallesRolPago.add(rolPagoDetalle); }
					 */
				}
			}

			// RUBROS EVENTUALES
			Map<String, Object> mapaEventual = new HashMap<String, Object>();
			// parte de rolDetalle
			// mapaEventual.put("rolpagoId", rolPagoIf.getId());
			mapaEventual.put("contratoId", contratoId);
			// parte de rubro Eventual
			mapaEventual.put("tipoRolIdCobro", rolPagoIf.getTiporolId());
			mapaEventual.put("mesCobro", rolPagoIf.getMes());
			mapaEventual.put("anioCobro", rolPagoIf.getAnio());
			mapaEventual.put("tipoContratoId", rolPagoIf.getTipocontratoId());
			// mapaEventual.put("estado",
			// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
			rolPagoDetalles = rolPagoDetalleLocal.findRolPagoDetalleEventualesByMapByEstados(
				mapaEventual,
				EstadoRolPagoDetalle.EMITIDO.getLetra(),
				EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
				EstadoRolPagoDetalle.PAGADO.getLetra());
			for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
				if (devolverRolPagoDetalleId)
					detallesRolPago.add(rolPagoDetalle);
				if (!mapa.containsKey("nombreEmpleado"))
					mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId()));
				Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
				Double valor = utilitariosLocal.redondeoValor(rolPagoDetalle.getValor().doubleValue());
				RubroEventualIf rubroEventualIf = rubroEventualLocal.getRubroEventual(idRubroEventual);
				RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
					mapaRubros, mapaTipoRubros, rubroEventualIf.getRubroId());
				calculoOtrosValores(valor, rubroIf, mapa, tipoRolIf);
			}

			// Si es el calculo mensual, voy a buscar los descuentos que se
			// hicieron en la quincena
			// para tomarlos en cuenta en el calculo final.
			if (tipoRol == TipoRol.MENSUAL) {

				Collection<TipoRolIf> tiposRolPago = tipoRolLocal.getTipoRolList();
				TipoRolIf tipoRolQuincenal = buscarTipoRolIf(tiposRolPago,"QUINCENA");

				/*
				 * Map<String, Object> mapaRolPago = new HashMap<String,
				 * Object>(); mapaRolPago.put("tiporolId",
				 * tipoRolQuincenal.getId()); mapaRolPago.put("tipocontratoId",
				 * rolPagoIf.getTipocontratoId()); mapaRolPago.put("mes",
				 * rolPagoIf.getMes()); mapaRolPago.put("anio",
				 * rolPagoIf.getAnio()); Collection<RolPagoIf>
				 * rolPagoQuincenales = findRolPagoByQuery(mapaRolPago);
				 * 
				 * RolPagoIf rolPagoQuincenal = null; for (RolPagoIf
				 * rolPagoQuincenalTmp : rolPagoQuincenales){ rolPagoQuincenal =
				 * rolPagoQuincenalTmp; }
				 * 
				 * if ( rolPagoQuincenal == null ){ throw new
				 * GenericBusinessException("No existe rol de Pago Quincenal
				 * generado !!"); }
				 */

				mapaEventual = new HashMap<String, Object>();
				// parte de rolDetalle
				// mapaEventual.put("rolpagoId", rolPagoQuincenal.getId());
				mapaEventual.put("contratoId", contratoId);
				mapaEventual.put("rubroEventualId", " not null ");
				// parte de rubro Eventual
				mapaEventual.put("tipoRolIdCobro", tipoRolQuincenal.getId());
				mapaEventual.put("mesCobro", rolPagoIf.getMes());
				mapaEventual.put("anioCobro", rolPagoIf.getAnio());
				mapaEventual.put("tipoContratoId", rolPagoIf.getTipocontratoId());
				// mapaEventual.put("estado",
				// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
				rolPagoDetalles = rolPagoDetalleLocal.findRolPagoDetalleEventualesByMapByEstados(
					mapaEventual, 
					EstadoRolPagoDetalle.EMITIDO.getLetra(),
					EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
					EstadoRolPagoDetalle.PAGADO.getLetra() );
				for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
					if (devolverRolPagoDetalleId)
						detallesRolPago.add(rolPagoDetalle);
					if (!mapa.containsKey("nombreEmpleado"))
						mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId()));
					Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
					Double valor = utilitariosLocal.redondeoValor(rolPagoDetalle.getValor().doubleValue());
					RubroEventualIf rubroEventualIf = rubroEventualLocal.getRubroEventual(idRubroEventual);
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
						mapaRubros, mapaTipoRubros, rubroEventualIf.getRubroId() );
					calculoOtrosValores(valor, rubroIf, mapa, tipoRolIf);
				}
			}

			if (devolverRolPagoDetalleId)
				mapa.put("detallesRolPago", detallesRolPago);

			mapa.put("contratoId", contratoId);

			otrosIngresos = (Double) mapa.get("otrosIngresos") + otrosIngresos;
			mapa.put("otrosIngresos", otrosIngresos);
			totalIngresos = (Double) mapa.get("totalIngresos") + totalIngresos;
			mapa.put("totalIngresos", totalIngresos);
			otrosDescuentos = (Double) mapa.get("otrosDescuentos") + otrosDescuentos;
			mapa.put("otrosDescuentos", otrosDescuentos);
			totalDescuentos = (Double) mapa.get("totalDescuentos") + totalDescuentos;
			mapa.put("totalDescuentos", totalDescuentos);
			Double total = utilitariosLocal.redondeoValor(totalIngresos - totalDescuentos);
			mapa.put("netoPagar", total);

			rolPagoContratoCollection.add(mapa);
		}

		Comparator<Map> comparadorMapaNombre = new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				String nombre1 = (String) o1.get("nombreEmpleado");
				String nombre2 = (String) o2.get("nombreEmpleado");
				return nombre1.compareTo(nombre2);
			}
		};

		mapaContratoIdNombreEmpleado = null;
		mapaRubros = null;
		mapaTipoRubros = null;
		Collections.sort((ArrayList) rolPagoContratoCollection,
				comparadorMapaNombre);
		return rolPagoContratoCollection;
	}

	private void inicializarValoresMapaMensual(Map<String, Object> mapa) {
		mapa.put("sueldo", new Double(0.0));
		mapa.put("otrosIngresos", new Double(0.0));
		mapa.put("totalIngresos", new Double(0.0));
		mapa.put("iess", new Double(0.0));
		mapa.put("impuestoRenta", new Double(0.0));
		mapa.put("prestamoCompania", new Double(0.0));
		mapa.put("anticipoQuincena", new Double(0.0));
		mapa.put("otrosDescuentos", new Double(0.0));
		mapa.put("totalDescuentos", new Double(0.0));
		mapa.put("netoPagar", new Double(0.0));
		mapa.put("fondoReservaBeneficio", new Double(0.0));
	}

	private void calculoOtrosValores(Double valor, RubroIf rubroIf,
			Map<String, Object> mapa, TipoRolIf tipoRolIf)
			throws GenericBusinessException {
		Double otrosIngresos = (Double) mapa.get("otrosIngresos");
		Double totalIngresos = (Double) mapa.get("totalIngresos");
		Double otrosDescuentos = (Double) mapa.get("otrosDescuentos");
		Double totalDescuentos = (Double) mapa.get("totalDescuentos");

		OperacionNomina ingresoEgreso = UtilesNomina.getIngresoEgreso(
				tipoRolIf, rubroIf);
		if (ingresoEgreso == OperacionNomina.INGRESO) {
			otrosIngresos += valor;
			totalIngresos += valor;
		} else {
			otrosDescuentos += valor;
			totalDescuentos += valor;
		}

		mapa.put("otrosIngresos", otrosIngresos);
		mapa.put("totalIngresos", totalIngresos);
		mapa.put("otrosDescuentos", otrosDescuentos);
		mapa.put("totalDescuentos", totalDescuentos);
	}

	private Collection<Map<String, Object>> crearColeccionContratosQuincenal(
			Collection<Long> contratosId, RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, TipoRol tipoRol, String estado,
			boolean devolverRolPagoDetalleId) throws GenericBusinessException {
		Double otrosIngresos = null;
		Double totalIngresos = null;
		Double otrosDescuentos = null;
		Double totalDescuentos = null;

		HashMap<Long, String> mapaContratoIdNombreEmpleado = new HashMap<Long, String>();
		HashMap<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		HashMap<Long, String> mapaTipoRubros = new HashMap<Long, String>();
		Collection rolPagoContratoCollection = new ArrayList();

		Map<String, Object> mapaSueldo = new HashMap<String, Object>();
		mapaSueldo.put("nombre", "SUELDO%BASICO%");
		Collection<RubroIf> rubros = rubroLocal.findRubroByQuery(mapaSueldo);
		if (rubros.size() == 0)
			throw new GenericBusinessException(
					"No existe Rubro con el nombre de Sueldo");
		if (rubros.size() > 1)
			throw new GenericBusinessException(
					"Existe mas de un Rubro con el nombre Sueldo Basico !!");
		RubroIf rubroSueldo = rubros.iterator().next();

		for (Long contratoId : contratosId) {
			if (contratoId == 40L)
				System.out.println("PROBLEMA");
			Collection<RolPagoDetalleIf> rolPagoDetalles = buscarRolPagoDetalle(
					rolPagoIf, contratoId, tipoRol, estado);
			if (rolPagoDetalles.size() > 0) {
				Map<String, Object> mapa = new HashMap<String, Object>();

				otrosIngresos = 0.0;
				totalIngresos = 0.0;
				otrosDescuentos = 0.0;
				totalDescuentos = 0.0;
				mapa.put("otrosIngresos", otrosIngresos);
				mapa.put("totalIngresos", totalIngresos);
				mapa.put("otrosDescuentos", otrosDescuentos);
				mapa.put("totalDescuentos", totalDescuentos);

				Collection<RolPagoDetalleIf> detallesRolPago = null;
				if (devolverRolPagoDetalleId)
					detallesRolPago = new Vector<RolPagoDetalleIf>();

				inicializarValoresMapaQuincenal(mapa);

				if (!mapa.containsKey("nombreEmpleado")) {
					String nombre = nominaUtilesLocal.verificarEmpleadoEnMapa(contratoId, mapaContratoIdNombreEmpleado);
					mapa.put("nombreEmpleado",nombre);
				}

				// RUBROS NORMALES
				ContabilizarRubrosNormales: for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {

					// utilesLocal.verificarEmpleadoEnMapa(rolPagoDetalle,mapaContratoIdNombreEmpleado);
					Double valor = utilitariosLocal
							.redondeoValor(rolPagoDetalle.getValor()
									.doubleValue());
					// if ( !mapa.containsKey("nombreEmpleado") )
					// mapa.put("nombreEmpleado",
					// mapaContratoIdNombreEmpleado.get(rolPagoDetalle.getContratoId()));

					mapa.put("cuentaBancariaId", rolPagoDetalle
							.getCuentaBancariaId());

					Long idRubro = rolPagoDetalle.getRubroId();
					// Long idRubroEventual =
					// rolPagoDetalle.getRubroEventualId();
					if (idRubro != null) {
						RubroIf rubroIf = nominaUtilesLocal
								.verificarRubrosEnMapa(mapaRubros,
										mapaTipoRubros, idRubro);

						// Di es de tipo Anticipo el rubro, no se lo toma en
						// cuenta.
						if ("A".equals(rubroIf.getTipoRubro()))
							continue ContabilizarRubrosNormales;

						if (devolverRolPagoDetalleId)
							detallesRolPago.add(rolPagoDetalle);

						String nombre = rubroIf.getNombre();
						if (nombre.contains("ANTICIPO")
								&& nombre.contains("QUINCENA")) {
							mapa.put("anticipoQuincena", valor);
							totalIngresos += valor;
						} else {
							calculoOtrosValores(valor, rubroIf, mapa, tipoRolIf);
						}
					} /*
						 * else { RubroEventualIf rubroEventualIf =
						 * rubroEventualLocal.getRubroEventual(idRubroEventual);
						 * RubroIf rubroIf =
						 * utilesLocal.verificarRubrosEnMapa(mapaRubros,mapaTipoRubros,rubroEventualIf.getRubroId());
						 * calculoOtrosValores(valor,rubroIf,mapa,tipoRolIf); }
						 */
				}

				// RUBROS EVENTUALES
				Map<String, Object> mapaEventual = new HashMap<String, Object>();
				// parte rol detalle
				mapaEventual.put("rolpagoId", rolPagoIf.getId());
				mapaEventual.put("contratoId", contratoId);
				// parte de rubro Eventual
				mapaEventual.put("tipoRolIdCobro", rolPagoIf.getTiporolId());
				mapaEventual.put("mesCobro", rolPagoIf.getMes());
				mapaEventual.put("anioCobro", rolPagoIf.getAnio());
				mapaEventual.put("tipoContratoId", rolPagoIf
						.getTipocontratoId());
				// mapaEventual.put("estado",
				// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
				rolPagoDetalles = rolPagoDetalleLocal
						.findRolPagoDetalleEventualesByMapByEstados(
								mapaEventual, EstadoRolPagoDetalle.EMITIDO
										.getLetra(),
								EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
								EstadoRolPagoDetalle.PAGADO.getLetra());
				// ,
				// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.EMITIDO)
				// ,
				// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO)
				// ,
				// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.PAGADO)
				// );
				for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
					if (devolverRolPagoDetalleId)
						detallesRolPago.add(rolPagoDetalle);
					if (!mapa.containsKey("nombreEmpleado"))
						mapa.put("nombreEmpleado", mapaContratoIdNombreEmpleado
								.get(rolPagoDetalle.getContratoId()));
					Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
					Double valor = utilitariosLocal
							.redondeoValor(rolPagoDetalle.getValor()
									.doubleValue());
					RubroEventualIf rubroEventualIf = rubroEventualLocal
							.getRubroEventual(idRubroEventual);
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
							mapaRubros, mapaTipoRubros, rubroEventualIf
									.getRubroId());
					calculoOtrosValores(valor, rubroIf, mapa, tipoRolIf);
				}

				if (devolverRolPagoDetalleId)
					mapa.put("detallesRolPago", detallesRolPago);

				Double valorSueldo = getValorSueldo(contratoId, rubroSueldo,
						mapaContratoIdNombreEmpleado);
				mapa.put("sueldo", valorSueldo);

				mapa.put("contratoId", contratoId);
				totalIngresos = (Double) mapa.get("totalIngresos")
						+ totalIngresos;
				mapa.put("totalIngresos", totalIngresos);
				totalDescuentos = (Double) mapa.get("totalDescuentos")
						+ totalDescuentos;
				mapa.put("totalDescuentos", totalDescuentos);
				otrosDescuentos = (Double) mapa.get("otrosDescuentos")
						+ otrosDescuentos;
				mapa.put("otrosDescuentos", otrosDescuentos);
				Double total = utilitariosLocal.redondeoValor(totalIngresos
						- otrosDescuentos);
				mapa.put("netoPagar", total);

				rolPagoContratoCollection.add(mapa);
			}
		}

		Comparator<Map> comparadorMapaNombre = new Comparator<Map>() {
			public int compare(Map o1, Map o2) {
				String nombre1 = (String) o1.get("nombreEmpleado");
				String nombre2 = (String) o2.get("nombreEmpleado");
				return nombre1.compareTo(nombre2);
			}
		};

		mapaContratoIdNombreEmpleado = null;
		mapaRubros = null;
		mapaTipoRubros = null;
		Collections.sort((ArrayList) rolPagoContratoCollection,
				comparadorMapaNombre);
		return rolPagoContratoCollection;
	}

	private void inicializarValoresMapaQuincenal(Map<String, Object> mapa) {
		mapa.put("sueldo", new Double(0.0));
		mapa.put("otrosIngresos", new Double(0.0));
		mapa.put("totalIngresos", new Double(0.0));
		mapa.put("anticipoQuincena", new Double(0.0));
		mapa.put("otrosDescuentos", new Double(0.0));
		mapa.put("totalDescuentos", new Double(0.0));
		mapa.put("netoPagar", new Double(0.0));
	}

	private Double getValorSueldo(Long contratoId, RubroIf rubroSueldo,
			HashMap<Long, String> mapaContratoIdNombreEmpleado)
			throws GenericBusinessException {
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("contratoId", contratoId);
		mapa.put("rubroId", rubroSueldo.getId());
		Collection<ContratoRubroIf> contratoRubros = contratoRubroLocal.findContratoRubroByQuery(mapa);
		
		if (contratoRubros.size() == 0) {
			String nombreEmpleado = mapaContratoIdNombreEmpleado.get(contratoId);
			throw new GenericBusinessException("Contrato de " + nombreEmpleado + " no tiene Rubro Sueldo");
		} 
		else if (contratoRubros.size() > 1) {
			String nombreEmpleado = mapaContratoIdNombreEmpleado.get(contratoId);
			throw new GenericBusinessException("Contrato de " + nombreEmpleado + " tiene mas de un Rubro de Sueldo Básico");
		}
		
		ContratoRubroIf contratoRubro = contratoRubros.iterator().next();
		Double valorSueldo = utilitariosLocal.redondeoValor(contratoRubro.getValor().doubleValue());
		return valorSueldo;
	}

	private Collection<RolPagoDetalleIf> buscarRolPagoDetalle(
			RolPagoIf rolPago, Long contratoId, TipoRol tipoRol, String estado)
			throws GenericBusinessException {
		// OJO
		// SI SE MODIFICA ESTE METODO, REVISAR EN
		// PAGO_ROL_DETALLE_SESSION_SERVICE EL METODO
		// findRolPagoDetalleContratoIdByRolpagoIdNormal
		Map<String, Object> mapaDetalle = new HashMap<String, Object>();
		mapaDetalle.put("rolpagoId", rolPago.getId());
		mapaDetalle.put("contratoId", contratoId);
		if (estado != null)
			mapaDetalle.put("estado", estado);
		Collection<RolPagoDetalleIf> rolPagoDetalleCollection = null;
		if (tipoRol == TipoRol.DECIMO_TERCERO
				|| tipoRol == TipoRol.DECIMO_CUARTO
				|| tipoRol == TipoRol.APORTE_PATRONAL
				|| tipoRol == TipoRol.FONDO_RESERVA) {
			rolPagoDetalleCollection = rolPagoDetalleLocal
					.findRolPagoDetalleByRolpagoId(rolPago.getId());
		} else if (tipoRol == TipoRol.QUINCENAL || tipoRol == TipoRol.MENSUAL)
			rolPagoDetalleCollection = rolPagoDetalleLocal
					.findRolPagoDetalleByQueryNormal(mapaDetalle);

		// if ( rolPagoDetalleCollection== null ||
		// rolPagoDetalleCollection.size() == 0 )
		// throw new GenericBusinessException("Rol de Pago no tiene detalle
		// !!");
		return rolPagoDetalleCollection;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void autorizarRolPago(Date fecha, RolPagoIf rolPagoIf,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList)
			throws GenericBusinessException {
		try {
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);

			Collection<DatoAsientoMensual> chequesCollection = new ArrayList<DatoAsientoMensual>();
			Collection<DatoAsientoMensual> nominaCollection = new ArrayList<DatoAsientoMensual>();

			for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {
				DatoAsientoMensual dato = new DatoAsientoMensual();
				TipoPagoIf tipoPago = (TipoPagoIf) mapa.get("tipoPagoIf");
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) mapa.get("cuentaBancariaIf");
				String descripcionAnticipo = (String) mapa.get("descripcionAnticipo");
				String numeroCheque = (String) mapa.get("numeroCheque");
				String nombreEmpleado = (String) mapa.get("nombreEmpleado");
				dato.setNombre(nombreEmpleado);
				Collection<RolPagoDetalleIf> detallesRolPago = 
					(Collection<RolPagoDetalleIf>) mapa.get("rolPagoDetalleCollection");
				dato.setRolPagoDetalleCollection(detallesRolPago);
				if (tipoPago.getNombre().contains("CHEQUE"))
					chequesCollection.add(dato);
				else
					nominaCollection.add(dato);

				for (RolPagoDetalleIf detalleTmp : detallesRolPago) {

					RolPagoDetalleIf detalle = rolPagoDetalleLocal.getRolPagoDetalle(detalleTmp.getId());
					if (detalle.getEstado().equals(EstadoRolPagoDetalle.EMITIDO.getLetra())) {

						if (detalle.getRubroEventualId() != null) {
							RubroEventualIf rubroEventualIf = rubroEventualLocal.getRubroEventual(detalle.getRubroEventualId());
							rubroEventualIf.setEstado(EstadoRubroEventual.AUTORIZADO_PARCIAL.getLetra());
							// Si es que en rubro_eventual NO tiene fecha y tipo
							// rol de pago significa que NO emite cheque
							// lo que significa que los datos usados para la
							// autorizacion seran puestos en el rol_pago_detalle
							// para estos eventuales.
							if (rubroEventualIf.getTipoRolIdPago() == null && rubroEventualIf.getFechaPago() == null) {
								detalle.setTipoPagoId(tipoPago.getId());
								detalle.setCuentaBancariaId(cuentaBancaria.getId());
								if (numeroCheque != null)
									detalle.setPreimpreso(numeroCheque);
							}
						} else {
							detalle.setTipoPagoId(tipoPago.getId());
							detalle.setCuentaBancariaId(cuentaBancaria.getId());
							if (numeroCheque != null)
								detalle.setPreimpreso(numeroCheque);
						}
						detalle.setEstado(EstadoRolPagoDetalle.AUTORIZADO.getLetra());
						detalle.setObservacion(descripcionAnticipo != null ? descripcionAnticipo : detalle.getObservacion());
						// sumaDetalle = sumarIngresos(tipoRolIf,
						// sumaDetalle,mapaRubro, detalle);
					}
				}
				// sumaDetalle = utilitariosLocal.redondeoValor(sumaDetalle);
			}

			if (tipoRol == TipoRol.QUINCENAL) {

			} else if (tipoRol == TipoRol.MENSUAL) {

				Collection<TipoRolIf> tiposRolPago = tipoRolLocal
						.getTipoRolList();
				TipoRolIf tipoRolQuincenal = null;
				RolPagoIf rolPagoQuincena = null;
				// Si el Tipo Rol es mensual, se busca el detalle del Rol
				// Quincenal
				// para que puedan hacerse los calculos de totales con estos,
				// puesto
				// que se descuentan para el pago del mes.
				if (tipoRol == TipoRol.MENSUAL) {
					tipoRolQuincenal = buscarTipoRolIf(tiposRolPago, "QUINCENA");
					Map<String, Object> mapaRolQuincena = new HashMap<String, Object>();
					mapaRolQuincena.put("tiporolId", tipoRolQuincenal.getId());
					mapaRolQuincena.put("tipocontratoId", rolPagoIf
							.getTipocontratoId());
					mapaRolQuincena.put("mes", rolPagoIf.getMes());
					mapaRolQuincena.put("anio", rolPagoIf.getAnio());
					Collection<RolPagoIf> rolesQuincena = findRolPagoByQuery(mapaRolQuincena);
					for (RolPagoIf rpq : rolesQuincena) {
						rolPagoQuincena = rpq;
					}
				}

				// GENERACION DEL ASIENTO MENSUAL
				/*
				 * Collection<AsientoIf> asientosGenerados =
				 * rolPagoAsientoAutomaticoHandlerLocal
				 * .generarAsientoAutomaticoAutorizacionMensual(fecha,rolPagoIf,
				 * chequesCollection , nominaCollection,rolPagoQuincena);
				 * 
				 * verificarSumaAsientosAutorizacionRolMensual(rolPagoIf,
				 * tipoRolIf,asientosGenerados , chequesCollection,
				 * nominaCollection,0D);
				 */

			} else if (tipoRol == TipoRol.APORTE_PATRONAL
					|| tipoRol == TipoRol.FONDO_RESERVA) {

				// RubroIf rubroFondoReserva =
				// utilesLocal.getRubroByTipoRol(TipoRol.FONDO_RESERVA);
				// RubroIf rubroAportePatronal =
				// utilesLocal.getRubroByTipoRol(TipoRol.APORTE_PATRONAL);

				/*
				 * double debe = 0D,haber = 0D; Map<String, Object>
				 * mapaRolDetalle = new HashMap<String, Object>();
				 * mapaRolDetalle.put("rolpagoId", rolPagoIf.getId());
				 * mapaRolDetalle.put("estado",
				 * EstadoRolPagoDetalle.AUTORIZADO.getLetra() ); if ( tipoRol ==
				 * TipoRol.APORTE_PATRONAL ){ //APORTE AL IESS PATRONAL
				 * //mapaRolDetalle.put("rubroId", rubroAportePatronal.getId());
				 * Collection<RolPagoDetalleIf> rolPagoDetalleAportePatronal =
				 * rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaRolDetalle);
				 * double totalAportes = 0D;
				 * 
				 * for ( RolPagoDetalleIf rpd : rolPagoDetalleAportePatronal ){
				 * totalAportes += rpd.getValor().doubleValue(); } totalAportes =
				 * utilitariosLocal.redondeoValor(totalAportes); AsientoIf
				 * asientoAportaPatronal =
				 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoProvisionAporteIessPatronal(fecha,rolPagoIf,
				 * rolPagoDetalleAportePatronal,tipoRolIf.getEmpresaId());
				 * Collection<AsientoDetalleIf> asientoDetalles =
				 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoAportaPatronal.getId());
				 * for ( AsientoDetalleIf asientoDetalleIf : asientoDetalles ){
				 * debe += asientoDetalleIf.getDebe().doubleValue(); haber +=
				 * asientoDetalleIf.getHaber().doubleValue(); } debe =
				 * utilitariosLocal.redondeoValor(debe); haber =
				 * utilitariosLocal.redondeoValor(haber); if ( debe!=haber )
				 * throw new GenericBusinessException("Valores de Debe y haber
				 * para Aporte Patronal no coinciden !!"); if (
				 * debe!=totalAportes ) throw new GenericBusinessException("El
				 * valor total no coincide con debe y haber de Aporte Patronal
				 * !!"); } else if ( tipoRol == TipoRol.FONDO_RESERVA ){ //FONDO
				 * DE RESERVA //mapaRolDetalle.put("rubroId",
				 * rubroFondoReserva.getId()); Collection<RolPagoDetalleIf>
				 * rolPagoDetalleFondoReserva =
				 * rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaRolDetalle);
				 * double totalFondo = 0D; for (RolPagoDetalleIf rpd :
				 * rolPagoDetalleFondoReserva ){ totalFondo +=
				 * rpd.getValor().doubleValue(); } totalFondo =
				 * utilitariosLocal.redondeoValor(totalFondo); AsientoIf
				 * asientoFondoReserva =
				 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoProvisionFondoReserva(fecha,rolPagoIf,
				 * rolPagoDetalleFondoReserva,tipoRolIf.getEmpresaId()); debe =
				 * 0D;haber = 0D; Collection<AsientoDetalleIf> asientoDetalles =
				 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoFondoReserva.getId());
				 * for ( AsientoDetalleIf asientoDetalleIf : asientoDetalles ){
				 * debe += asientoDetalleIf.getDebe().doubleValue(); haber +=
				 * asientoDetalleIf.getHaber().doubleValue(); } debe =
				 * utilitariosLocal.redondeoValor(debe); haber =
				 * utilitariosLocal.redondeoValor(haber); if ( debe!=haber )
				 * throw new GenericBusinessException("Valores de Debe y haber
				 * para Fondo de Reserva no coinciden !!"); if (
				 * debe!=totalFondo ) throw new GenericBusinessException("El
				 * valor total no coincide con debe y haber de Fondo de Reserva
				 * !!"); }
				 */
			} else if (tipoRol == TipoRol.DECIMO_TERCERO
					|| tipoRol == TipoRol.DECIMO_CUARTO) {

				/*
				double debe = 0D,haber = 0D; Map<String, Object>
				mapaRolDetalle = new HashMap<String, Object>();
				mapaRolDetalle.put("rolpagoId", rolPagoIf.getId());
				mapaRolDetalle.put("estado",EstadoRolPagoDetalle.AUTORIZADO.getLetra() );

				Collection<RolPagoDetalleIf> detalleDecimo =
					rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
				double totalDecimo = 0D; 
				for ( RolPagoDetalleIf rpd : detalleDecimo ){ 
					totalDecimo += rpd.getValor().doubleValue(); 
				}
				
				AsientoIf asientoFondoReserva = rolPagoAsientoAutomaticoHandlerLocal
					.generarAsientoAutomaticoProvisionDecimos(fecha,rolPagoIf,detalleDecimo,tipoRolIf, tipoRol); 
				debe = 0D;haber = 0D; Collection<AsientoDetalleIf>
				asientoDetalles = asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoFondoReserva.getId());
				for ( AsientoDetalleIf asientoDetalleIf : asientoDetalles ){
					debe += asientoDetalleIf.getDebe().doubleValue(); 
					haber += asientoDetalleIf.getHaber().doubleValue(); 
				}
				debe = utilitariosLocal.redondeoValor(debe); 
				haber = utilitariosLocal.redondeoValor(haber); 
				if ( debe!=haber )
					throw new GenericBusinessException("Valores de Debe y haber para Fondo de Reserva no coinciden !!"); 
				if ( debe!=totalDecimo ) 
					throw new GenericBusinessException("El valor total no coincide con debe y haber de Fondo de Reserva !!");
				*/

			} else if (tipoRol == TipoRol.VACACIONES) {
				/*
				 * double debe = 0D,haber = 0D; Map<String, Object>
				 * mapaRolDetalle = new HashMap<String, Object>();
				 * mapaRolDetalle.put("rolpagoId", rolPagoIf.getId());
				 * mapaRolDetalle.put("estado",
				 * EstadoRolPagoDetalle.AUTORIZADO.getLetra() ); Collection<RolPagoDetalleIf>
				 * rolPagoDetalleVacaciones =
				 * rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaRolDetalle);
				 * double totalAportes = 0D;
				 * 
				 * for ( RolPagoDetalleIf rpd : rolPagoDetalleVacaciones ){
				 * totalAportes += rpd.getValor().doubleValue(); } totalAportes =
				 * utilitariosLocal.redondeoValor(totalAportes); AsientoIf
				 * asientoVacaciones =
				 * rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoProvisionVacaciones(fecha,rolPagoIf,
				 * rolPagoDetalleVacaciones,tipoRolIf.getEmpresaId());
				 * Collection<AsientoDetalleIf> asientoDetalles =
				 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoVacaciones.getId());
				 * for ( AsientoDetalleIf asientoDetalleIf : asientoDetalles ){
				 * debe += asientoDetalleIf.getDebe().doubleValue(); haber +=
				 * asientoDetalleIf.getHaber().doubleValue(); } debe =
				 * utilitariosLocal.redondeoValor(debe); haber =
				 * utilitariosLocal.redondeoValor(haber); if ( debe!=haber )
				 * throw new GenericBusinessException("Valores de Debe y haber
				 * para Aporte Patronal no coinciden !!"); if (
				 * debe!=totalAportes ) throw new GenericBusinessException("El
				 * valor total no coincide con debe y haber de Aporte Patronal
				 * !!");
				 */
			} else
				throw new GenericBusinessException("Generacion de Asientos para autorizacion de "+
					tipoRol.toString() + " no implementada !!");

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al autorizar los pagos !!");
		}
	}

	public void generarAsientoRolPago(Date fecha, RolPagoIf rolPagoIf,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList)
			throws GenericBusinessException {
		try {
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);

			Collection<DatoAsientoMensual> chequesCollection = new ArrayList<DatoAsientoMensual>();
			Collection<DatoAsientoMensual> nominaCollection = new ArrayList<DatoAsientoMensual>();
			Collection<DatoMulta> multaCollection = new ArrayList<DatoMulta>();

			Set<Long> contratoSet = new HashSet<Long>();
			Double totalSeleccionado = 0.0;

			for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {
				DatoAsientoMensual dato = new DatoAsientoMensual();
				TipoPagoIf tipoPago = (TipoPagoIf) mapa.get("tipoPagoIf");
				String nombreEmpleado = (String) mapa.get("nombreEmpleado");
				Double total = (Double) mapa.get("total");
				totalSeleccionado += total;
				dato.setTotal(total);
				dato.setNombre(nombreEmpleado);
				Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa.get("rolPagoDetalleCollection");
				dato.setRolPagoDetalleCollection(detallesRolPago);
				if (tipoPago != null && tipoPago.getNombre().contains("CHEQUE"))
					chequesCollection.add(dato);
				else
					nominaCollection.add(dato);

				Long contratoId = (Long) mapa.get("contratoId");
				dato.setContratoId(contratoId);
				contratoSet.add(contratoId);

				DatoMulta datoMulta = new DatoMulta();
				datoMulta.setRolPagoDetalleCollection(detallesRolPago);
				datoMulta.setNombreEmpleado(nombreEmpleado);
				multaCollection.add(datoMulta);
			}

			totalSeleccionado = utilitariosLocal.redondeoValor(totalSeleccionado);

			if (tipoRol == TipoRol.QUINCENAL) {

			} else if (tipoRol == TipoRol.MENSUAL) {

				Collection<TipoRolIf> tiposRolPago = tipoRolLocal.getTipoRolList();
				TipoRolIf tipoRolQuincenal = null;
				RolPagoIf rolPagoQuincena = null;
				// Si el Tipo Rol es mensual, se busca el detalle del Rol Quincenal
				// para que puedan hacerse los calculos de totales con estos,puesto
				// que se descuentan para el pago del mes.
				if (tipoRol == TipoRol.MENSUAL) {
					tipoRolQuincenal = buscarTipoRolIf(tiposRolPago, "QUINCENA");
					Map<String, Object> mapaRolQuincena = new HashMap<String, Object>();
					mapaRolQuincena.put("tiporolId", tipoRolQuincenal.getId());
					mapaRolQuincena.put("tipocontratoId", rolPagoIf.getTipocontratoId());
					mapaRolQuincena.put("mes", rolPagoIf.getMes());
					mapaRolQuincena.put("anio", rolPagoIf.getAnio());
					Collection<RolPagoIf> rolesQuincena = findRolPagoByQuery(mapaRolQuincena);
					for (RolPagoIf rpq : rolesQuincena) {
						rolPagoQuincena = rpq;
					}
				}

				// GENERACION DEL ASIENTO MENSUAL
				Collection<AsientoIf> asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoAutorizacionMensual(fecha,rolPagoIf, 
						chequesCollection, nominaCollection,rolPagoQuincena);

				try {
					Collection<AsientoIf> asientoMultaGenerado = rolPagoAsientoAutomaticoHandlerLocal.generarAsientoAutomaticoMultas(fecha, rolPagoIf,multaCollection);
				} catch (Exception ex) {
					System.out.println("---Error en proceso de multas: "+ ex.getMessage());
				}

				verificarSumaAsientosAutorizacionRolMensual(rolPagoIf, tipoRolIf, asientosGenerados, 0D);

				establecerAsientoGenerado(rolPagoIf);
				
			} else if (tipoRol == TipoRol.APORTE_PATRONAL || tipoRol == TipoRol.FONDO_RESERVA) {
				double debe = 0D, haber = 0D;
				Map<String, Object> mapaRolDetalle = new HashMap<String, Object>();
				mapaRolDetalle.put("rolpagoId", rolPagoIf.getId());
				// mapaRolDetalle.put("estado",
				// EstadoRolPagoDetalle.PAGADO.getLetra() );
				if (tipoRol == TipoRol.APORTE_PATRONAL) {
					// APORTE AL IESS PATRONAL
					double totalAportes = 0D;
					Collection<RolPagoDetalleIf> rolPagoDetalleAportePatronalTodos = new ArrayList<RolPagoDetalleIf>();
					try {
						for (Long contratoId : contratoSet) {
							mapaRolDetalle.put("contratoId", contratoId);
							Collection<RolPagoDetalleIf> rolPagoDetalleAportePatronal = rolPagoDetalleLocal
									.findRolPagoDetalleByQuery(mapaRolDetalle);

							for (RolPagoDetalleIf rpd : rolPagoDetalleAportePatronal) {
								totalAportes += rpd.getValor().doubleValue();
								rolPagoDetalleAportePatronalTodos.add(rpd);
							}
							totalAportes = utilitariosLocal.redondeoValor(totalAportes);
						}

						AsientoIf asientoAportaPatronal = rolPagoAsientoAutomaticoHandlerLocal
							.generarAsientoProvisionVariacion1(fecha,rolPagoIf,
								rolPagoDetalleAportePatronalTodos,tipoRol.APORTE_PATRONAL);

					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("***ERROR EN GENERACION DE ASIENTO DE MULTA: "+ ex.getMessage());
					}

					try {

						Map<Long, RubroIf> mapaRubrosEspeciales = new HashMap<Long, RubroIf>();
						RolPagoIf rolMensual = obtenerRolPagoMensual(rolPagoIf,
								mapaRubrosEspeciales);
						Map<String, Object> mapa = new HashMap<String, Object>();
						mapa.put("rubroEventualId", "null");
						mapa.put("rolpagoId", rolMensual.getId());
						// mapa.put("estado",
						// EstadoRolPagoDetalle.PAGADO.getLetra());

						Set<RolPagoDetalleIf> detalleRubrosEspeciales = new RolPagoDetalleSet();
						for (Long contratoId : contratoSet) {
							for (Long rubroId : mapaRubrosEspeciales.keySet()) {
								RubroIf rubroMensual = mapaRubrosEspeciales.get(rubroId);
								mapa.put("rubroId", rubroMensual.getId());
								mapa.put("contratoId", contratoId);
								Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
									.findRolPagoDetalleByQuery(mapa);
								for (RolPagoDetalleIf rpd : detalles) {
									detalleRubrosEspeciales.add(rpd);
								}
							}
						}

						AsientoIf asientoAportaPatronalIessDescuentoAEmpleados = rolPagoAsientoAutomaticoHandlerLocal
							.generarAsientoProvisionVariacion2(fecha,rolPagoIf, detalleRubrosEspeciales,
										tipoRol.APORTE_PATRONAL);

					} catch (Exception ex) {
						System.out.println("***ERROR EN GENERACION DE ASIENTO DE DESCUENTOS DE IESS AL EMPLEADO: "+ ex.getMessage());
					}

					/*AsientoIf asientoAportaPatronal = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoProvisionAporteIessPatronal(fecha, rolPagoIf,
							rolPagoDetalleAportePatronalTodos,tipoRolIf.getEmpresaId());*/
					
					AsientoIf asientoAportaPatronal = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoProvisionAporteIessPatronalMensual(fecha, 
							rolPagoIf, nominaCollection, chequesCollection, tipoRolIf, tipoRol);

					Collection<AsientoDetalleIf> asientoDetalles = asientoDetalleLocal
						.findAsientoDetalleByAsientoId(asientoAportaPatronal.getId());

					for (AsientoDetalleIf asientoDetalleIf : asientoDetalles) {
						debe += asientoDetalleIf.getDebe().doubleValue();
						haber += asientoDetalleIf.getHaber().doubleValue();
					}
					debe = utilitariosLocal.redondeoValor(debe);
					haber = utilitariosLocal.redondeoValor(haber);
					if (debe != haber)
						throw new GenericBusinessException("Valores de Debe y haber para Aporte Patronal no coinciden !!");
					if (debe != totalAportes)
						throw new GenericBusinessException("El valor total no coincide con debe y haber de Aporte Patronal  !!");
					
					establecerAsientoGenerado(rolPagoIf);
					
				} else if (tipoRol == TipoRol.FONDO_RESERVA) {
					// FONDO DE RESERVA
					// mapaRolDetalle.put("rubroId", rubroFondoReserva.getId());
					Collection<RolPagoDetalleIf> rolPagoDetalleFondoReserva = rolPagoDetalleLocal
							.findRolPagoDetalleByQuery(mapaRolDetalle);
					double totalFondo = 0D;
					for (RolPagoDetalleIf rpd : rolPagoDetalleFondoReserva) {
						totalFondo += rpd.getValor().doubleValue();
					}
					totalFondo = utilitariosLocal.redondeoValor(totalFondo);

					try {
						AsientoIf asientoFondoReserva = rolPagoAsientoAutomaticoHandlerLocal
								.generarAsientoProvisionVariacion1(fecha,
										rolPagoIf, rolPagoDetalleFondoReserva,
										tipoRol.FONDO_RESERVA);
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("***ERROR: " + ex.getMessage());
					}

					/*AsientoIf asientoFondoReserva = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoProvisionFondoReserva(fecha, rolPagoIf,
							rolPagoDetalleFondoReserva, tipoRolIf.getEmpresaId());*/
					
					AsientoIf asientoFondoReserva = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoProvisionFondoReservaMensual(fecha, 
							rolPagoIf, nominaCollection, chequesCollection, 
							tipoRolIf, tipoRol);
					
					debe = 0D;
					haber = 0D;
					Collection<AsientoDetalleIf> asientoDetalles = asientoDetalleLocal
						.findAsientoDetalleByAsientoId(asientoFondoReserva.getId());
					for (AsientoDetalleIf asientoDetalleIf : asientoDetalles) {
						debe += asientoDetalleIf.getDebe().doubleValue();
						haber += asientoDetalleIf.getHaber().doubleValue();
					}
					debe = utilitariosLocal.redondeoValor(debe);
					haber = utilitariosLocal.redondeoValor(haber);
					if (debe != haber)
						throw new GenericBusinessException("Valores de Debe y haber para Fondo de Reserva no coinciden !!");
					if (debe != totalSeleccionado)
						throw new GenericBusinessException("El valor total no coincide con debe y haber de Fondo de Reserva  !!");
					
					establecerAsientoGenerado(rolPagoIf);
					
				}
			} else if (tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO) {

				double debe = 0D, haber = 0D;
				/*
				 * Map<String, Object> mapaRolDetalle = new HashMap<String,
				 * Object>(); mapaRolDetalle.put("rolpagoId",
				 * rolPagoIf.getId()); mapaRolDetalle.put("estado",
				 * EstadoRolPagoDetalle.PAGADO.getLetra() );
				 */

				/*
				 * Collection<RolPagoDetalleIf> detalleDecimo =
				 * rolPagoDetalleLocal.findRolPagoDetalleByRolpagoId(rolPagoIf.getId());
				 * double totalDecimo = 0D; for (RolPagoDetalleIf rpd :
				 * detalleDecimo ){ totalDecimo += rpd.getValor().doubleValue(); }
				 */
				// AsientoIf asientoFondoReserva =
				// rolPagoAsientoAutomaticoHandlerLocal
				// .generarAsientoAutomaticoProvisionDecimos(fecha,rolPagoIf,detalleDecimo,
				// tipoRolIf, tipoRol);
				AsientoIf asientoFondoReserva = rolPagoAsientoAutomaticoHandlerLocal
					.generarAsientoAutomaticoProvisionDecimosMensual(fecha,
						rolPagoIf, nominaCollection, chequesCollection,
						tipoRolIf, tipoRol);
				debe = 0D;
				haber = 0D;
				Collection<AsientoDetalleIf> asientoDetalles = asientoDetalleLocal
					.findAsientoDetalleByAsientoId(asientoFondoReserva.getId());
				for (AsientoDetalleIf asientoDetalleIf : asientoDetalles) {
					debe += asientoDetalleIf.getDebe().doubleValue();
					haber += asientoDetalleIf.getHaber().doubleValue();
				}
				debe = utilitariosLocal.redondeoValor(debe);
				haber = utilitariosLocal.redondeoValor(haber);
				if (debe != haber)
					throw new GenericBusinessException(
							"Valores de Debe y haber para "
									+ tipoRol.toString() + " no coinciden !!");
				if (debe != totalSeleccionado)
					throw new GenericBusinessException(
							"El valor total no coincide con debe y haber de "
									+ tipoRol.toString() + " !!");

				establecerAsientoGenerado(rolPagoIf);
				
			} else if (tipoRol == TipoRol.VACACIONES) {
				double debe = 0D, haber = 0D;
				Map<String, Object> mapaRolDetalle = new HashMap<String, Object>();
				mapaRolDetalle.put("rolpagoId", rolPagoIf.getId());
				mapaRolDetalle.put("estado", EstadoRolPagoDetalle.AUTORIZADO
						.getLetra());
				Collection<RolPagoDetalleIf> rolPagoDetalleVacaciones = rolPagoDetalleLocal
						.findRolPagoDetalleByQuery(mapaRolDetalle);
				double totalAportes = 0D;

				for (RolPagoDetalleIf rpd : rolPagoDetalleVacaciones) {
					totalAportes += rpd.getValor().doubleValue();
				}
				totalAportes = utilitariosLocal.redondeoValor(totalAportes);
				AsientoIf asientoVacaciones = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoProvisionVacaciones(fecha,
								rolPagoIf, rolPagoDetalleVacaciones, tipoRolIf
										.getEmpresaId());
				Collection<AsientoDetalleIf> asientoDetalles = asientoDetalleLocal
						.findAsientoDetalleByAsientoId(asientoVacaciones
								.getId());
				for (AsientoDetalleIf asientoDetalleIf : asientoDetalles) {
					debe += asientoDetalleIf.getDebe().doubleValue();
					haber += asientoDetalleIf.getHaber().doubleValue();
				}
				debe = utilitariosLocal.redondeoValor(debe);
				haber = utilitariosLocal.redondeoValor(haber);
				if (debe != haber)
					throw new GenericBusinessException(
							"Valores de Debe y haber para Aporte Patronal no coinciden !!");
				if (debe != totalAportes)
					throw new GenericBusinessException(
							"El valor total no coincide con debe y haber de Aporte Patronal  !!");

			} else
				throw new GenericBusinessException(
						"Generacion de Asientos para autorizacion de "
								+ tipoRol.toString() + " no implementada !!");
			/*
			 * else { throw new GenericBusinessException("Tipo de Rol
			 * "+tipoRol.toString()+"no implementado !!"); }
			 */

			establecerAsientoGenerado(rolPagoIf);
			
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al generar asiento de Rol de Pago !!");
		}
	}

	private void establecerAsientoGenerado(RolPagoIf rolPagoIf)
			throws GenericBusinessException {
		RolPagoIf rolPagoExistente = getRolPago(rolPagoIf.getId());
		rolPagoExistente.setAsientoGenerado(AsientoGenerado.SI.getLetra());
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void generarAsientoRubrosEventales(Date fechaPago,
			RolPagoIf rolPagoIf,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList)
			throws GenericBusinessException {
		try {

			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf
					.getTiporolId());
			Collection<DatoAsientoPagoRol> chequesCollection = new ArrayList<DatoAsientoPagoRol>();
			Collection<DatoAsientoPagoRol> nominaCollection = new ArrayList<DatoAsientoPagoRol>();
			double sumaDetalle = 0.0;

			Map<Long, ContratoIf> mapaContratos = new HashMap<Long, ContratoIf>();
			Map<Long, EmpleadoIf> mapaEmpleados = new HashMap<Long, EmpleadoIf>();

			for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {

				TipoPagoIf tipoPagoIf = (TipoPagoIf) mapa.get("tipoPagoIf");
				// Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");
				// String numeroCheque = (String) mapa.get("numeroCheque");
				// String nombreBeneficiario = (String)
				// mapa.get("beneficiario");
				Double total = (Double) mapa.get("total");
				String nombreRubro = (String) mapa.get("nombreRubro");

				DatoAsientoRubroEventual dato = new DatoAsientoRubroEventual();
				dato.setTotal(total);

				Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa
						.get("rolPagoDetalleCollection");

				dato.setDetalle(detallesRolPago);
				dato.setNombreRubro(nombreRubro);

				sumaDetalle += total;

				Collection<AsientoIf> asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoAutorizacionAnticipos(
								fechaPago, rolPagoIf, dato, mapaContratos,
								mapaEmpleados);

			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al autorizar los pagos !!");
		}
	}

	private void verificarSumaAsientosPagoRolMensual(RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, Collection<AsientoIf> asientosGenerados,
			Collection<DatoAsientoPagoRol> chequesCollection,
			Collection<DatoAsientoPagoRol> nominaCollection, double sumaDetalle)
			throws GenericBusinessException {

		double debe = 0.0;
		double haber = 0.0;
		for (AsientoIf asiento : asientosGenerados) {

			Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal
					.findAsientoDetalleByAsientoId(asiento.getId());
			for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos
					.hasNext();) {
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos
						.next();
				debe += asientoDetalle.getDebe().doubleValue();
				haber += asientoDetalle.getHaber().doubleValue();
			}
			debe = utilitariosLocal.redondeoValor(debe);
			haber = utilitariosLocal.redondeoValor(haber);
			if (debe != haber)
				throw new GenericBusinessException(
						"Error al guardar "
								+ tipoRolIf.getNombre()
								+ ", el valor del Debe y Haber no coinciden en el Asiento");
		}
		if (sumaDetalle > 0D && debe != sumaDetalle)
			throw new GenericBusinessException(
					"Error al guardar "
							+ tipoRolIf.getNombre()
							+ ", el valor del Debe y Haber no coinciden con el detalle del Rol");
	}

	private void verificarSumaAsientosAutorizacionRolMensual(
			RolPagoIf rolPagoIf, TipoRolIf tipoRolIf,
			Collection<AsientoIf> asientosGenerados, double sumaDetalle)
			throws GenericBusinessException {

		double debe = 0.0;
		double haber = 0.0;
		for (AsientoIf asiento : asientosGenerados) {
			debe = 0.0;
			haber = 0.0;
			Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal
					.findAsientoDetalleByAsientoId(asiento.getId());
			for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos.hasNext();) {
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos.next();
				debe += asientoDetalle.getDebe().doubleValue();
				haber += asientoDetalle.getHaber().doubleValue();
			}
			debe = utilitariosLocal.redondeoValor(debe);
			haber = utilitariosLocal.redondeoValor(haber);
			if (debe != haber)
				throw new GenericBusinessException("Error al guardar "+ tipoRolIf.getNombre()+
					", el valor del Debe y Haber no coinciden en el Asiento");
		}
		if (sumaDetalle > 0D && debe != sumaDetalle)
			throw new GenericBusinessException(
					"Error al guardar "
							+ tipoRolIf.getNombre()
							+ ", el valor del Debe y Haber no coinciden con el detalle del Rol");
	}

	private double inicializarSumaDetalleMensual(RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, double sumaDetalle,
			Map<Long, RubroIf> mapaRubro) throws GenericBusinessException {
		Map<String, Object> mapaDetalleMensual = new HashMap<String, Object>();
		mapaDetalleMensual.put("rolpagoId", rolPagoIf.getId());
		Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
				.findRolPagoDetalleByQueryByEstados(mapaDetalleMensual,
						EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
						EstadoRolPagoDetalle.PAGADO.getLetra());
		for (RolPagoDetalleIf rolPagoDetalleIf : detalles) {
			sumaDetalle = sumarIngresos(tipoRolIf, sumaDetalle, mapaRubro,
					rolPagoDetalleIf);
		}
		return sumaDetalle;
	}

	private double sumarIngresos(TipoRolIf tipoRolIf, double sumaDetalle,
			Map<Long, RubroIf> mapaRubro, RolPagoDetalleIf detalle)
			throws GenericBusinessException {
		RubroIf rubroIf = null;
		if (detalle.getRubroId() != null)
			rubroIf = verificarRubro(mapaRubro, detalle.getRubroId());
		else {
			RubroEventualIf rubroEventual = rubroEventualLocal
					.getRubroEventual(detalle.getRubroEventualId());
			rubroIf = verificarRubro(mapaRubro, rubroEventual.getRubroId());
		}
		OperacionNomina operacion = UtilesNomina.getIngresoEgreso(tipoRolIf,
				rubroIf);
		if (operacion == OperacionNomina.INGRESO)
			sumaDetalle += detalle.getValor().doubleValue();
		return sumaDetalle;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<AsientoIf> procesarPagoRol(Date fechaPago,
			RolPagoIf rolPagoIf,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList)
			throws GenericBusinessException {
		try {
			Collection<AsientoIf> asientosGenerados = null;
			Collection<AsientoIf> asientosGeneradosDeCheques = null;

			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf
					.getTiporolId());

			TipoRol tipoRol = TipoRol.obtenerTipoRol(tipoRolIf);
			if (tipoRol == null)
				throw new GenericBusinessException(
						"Manejo de Tipo de Rol no implementado !!");

			Collection<DatoAsientoPagoRol> chequesCollection = new ArrayList<DatoAsientoPagoRol>();
			Collection<DatoAsientoPagoRol> nominaCollection = new ArrayList<DatoAsientoPagoRol>();
			double sumaDetalle = 0.0;

			if (tipoRol == TipoRol.QUINCENAL) {
				Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();

				sumaDetalle = procesarPagoRolCalculo(rolPagoIf, tipoRol,
						fechaPago, rolPagoDetalleSeleccionadosList,
						chequesCollection, nominaCollection, null);

				asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
				asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoAutomaticoQuincenal(fechaPago,
								rolPagoIf, chequesCollection, nominaCollection,
								mapaChequesEmitidos, asientosGeneradosDeCheques);

				verificarSumaAsientosPagoRolQuincenal(rolPagoIf, tipoRolIf,
						asientosGenerados, chequesCollection, nominaCollection,
						sumaDetalle);

				for (String numeroCheque : mapaChequesEmitidos.keySet()) {
					ChequeDatos ChequeDatos = mapaChequesEmitidos.get(numeroCheque);
					ChequeEmitidoIf chequeEmitido = ChequeDatos.getChequeEmitido();
					if (chequeEmitido.getValor().doubleValue() > 0D)
						chequeEmitidoLocal.procesarChequeEmitido(ChequeDatos);
				}
			} else if (tipoRol == TipoRol.MENSUAL) {
				Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();

				sumaDetalle = procesarPagoRolCalculo(rolPagoIf, tipoRol,
						fechaPago, rolPagoDetalleSeleccionadosList,
						chequesCollection, nominaCollection, null);

				asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
				asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoPagoMensual(fechaPago, rolPagoIf,
								chequesCollection, nominaCollection,
								mapaChequesEmitidos, asientosGeneradosDeCheques);
				verificarSumaAsientosPagoRolMensual(rolPagoIf, tipoRolIf,
						asientosGenerados, chequesCollection, nominaCollection,
						sumaDetalle);

				for (String numeroCheque : mapaChequesEmitidos.keySet()) {
					ChequeDatos ChequeDatos = mapaChequesEmitidos.get(numeroCheque);
					ChequeEmitidoIf chequeEmitido = ChequeDatos.getChequeEmitido();
					if (chequeEmitido.getValor().doubleValue() > 0D)
						chequeEmitidoLocal.procesarChequeEmitido(ChequeDatos);
				}
			} else if (tipoRol == TipoRol.DECIMO_TERCERO
					|| tipoRol == TipoRol.DECIMO_CUARTO) {
				Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();

				sumaDetalle = procesarPagoRolCalculo(rolPagoIf, tipoRol,
						fechaPago, rolPagoDetalleSeleccionadosList,
						chequesCollection, nominaCollection, null);

				asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
				asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoPagoDecimos(fechaPago, rolPagoIf,
								chequesCollection, nominaCollection,
								mapaChequesEmitidos, asientosGeneradosDeCheques);
				verificarSumaAsientosPagoRolMensual(rolPagoIf, tipoRolIf,
						asientosGenerados, chequesCollection, nominaCollection,
						sumaDetalle);

				for (String numeroCheque : mapaChequesEmitidos.keySet()) {
					ChequeDatos ChequeDatos = mapaChequesEmitidos
							.get(numeroCheque);
					ChequeEmitidoIf chequeEmitido = ChequeDatos
							.getChequeEmitido();
					if (chequeEmitido.getValor().doubleValue() > 0D)
						chequeEmitidoLocal.procesarChequeEmitido(ChequeDatos);
				}
			} else if (tipoRol == TipoRol.FONDO_RESERVA
					|| tipoRol == TipoRol.APORTE_PATRONAL) {
				// throw new GenericBusinessException("Metodo no implementado");

				Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();

				Collection<DatoAsientoPagoRol> aportePatronalCollecction = new ArrayList<DatoAsientoPagoRol>();

				sumaDetalle = procesarPagoRolCalculo(rolPagoIf, tipoRol,
						fechaPago, rolPagoDetalleSeleccionadosList,
						chequesCollection, nominaCollection,
						aportePatronalCollecction);

				asientosGenerados = null;

				if (tipoRol == TipoRol.APORTE_PATRONAL) {
					asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
					try {
						asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
								.generarAsientoPagoAporteIessPatronalVariacion1(
										fechaPago, rolPagoIf,
										aportePatronalCollecction,
										mapaChequesEmitidos,
										asientosGeneradosDeCheques);
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("***ERROR: " + ex.getMessage());
					}

					asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
							.generarAsientoPagoAporteIessPatronal(fechaPago,
									rolPagoIf, chequesCollection,
									nominaCollection, mapaChequesEmitidos,
									asientosGeneradosDeCheques);

				} else if (tipoRol == TipoRol.FONDO_RESERVA) {
					asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
					asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
							.generarAsientoPagoFondoReserva(fechaPago,
									rolPagoIf, chequesCollection,
									nominaCollection, mapaChequesEmitidos,
									asientosGeneradosDeCheques);
				} else
					throw new GenericBusinessException(
							"Tipo de rol no considerado !!");

				verificarSumaAsientosPagoRolMensual(rolPagoIf, tipoRolIf,
						asientosGenerados, chequesCollection, nominaCollection,
						sumaDetalle);

				for (String numeroCheque : mapaChequesEmitidos.keySet()) {
					ChequeDatos ChequeDatos = mapaChequesEmitidos
							.get(numeroCheque);
					ChequeEmitidoIf chequeEmitido = ChequeDatos
							.getChequeEmitido();
					if (chequeEmitido.getValor().doubleValue() > 0D)
						chequeEmitidoLocal.procesarChequeEmitido(ChequeDatos);
				}
			} else if (tipoRol == TipoRol.VACACIONES) {
				Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();

				sumaDetalle = procesarPagoRolCalculo(rolPagoIf, tipoRol,
						fechaPago, rolPagoDetalleSeleccionadosList,
						chequesCollection, nominaCollection, null);

				asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
				asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
						.generarAsientoPagoVacaciones(fechaPago, rolPagoIf,
								chequesCollection, nominaCollection,
								mapaChequesEmitidos, asientosGeneradosDeCheques);
				verificarSumaAsientosPagoRolMensual(rolPagoIf, tipoRolIf,
						asientosGenerados, chequesCollection, nominaCollection,
						sumaDetalle);

				for (String numeroCheque : mapaChequesEmitidos.keySet()) {
					ChequeDatos ChequeDatos = mapaChequesEmitidos
							.get(numeroCheque);
					ChequeEmitidoIf chequeEmitido = ChequeDatos
							.getChequeEmitido();
					if (chequeEmitido.getValor().doubleValue() > 0D)
						chequeEmitidoLocal.procesarChequeEmitido(ChequeDatos);
				}
			} else
				throw new GenericBusinessException(
						"Tipo de Rol no considerado para Pago");

			return asientosGeneradosDeCheques;

		} catch (GenericBusinessException e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			ctx.setRollbackOnly();
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error general en la creación de Pago !!");
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<AsientoIf> procesarPagoAnticipoRol(Date fechaPago,
		RolPagoIf rolPagoIf,Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList)
		throws GenericBusinessException {
		try {
			TipoRolIf tipoRolIf = tipoRolLocal.getTipoRol(rolPagoIf.getTiporolId());
			Map<String, ChequeDatos> mapaChequesEmitidos = new HashMap<String, ChequeDatos>();
			Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
			Collection<DatoAsientoPagoRol> chequesCollection = new ArrayList<DatoAsientoPagoRol>();
			Collection<DatoAsientoPagoRol> nominaCollection = new ArrayList<DatoAsientoPagoRol>();
			double sumaDetalle = 0.0;

			sumaDetalle = procesarPagoAnticipoRolCalculo(fechaPago,
					rolPagoDetalleSeleccionadosList, chequesCollection,
					nominaCollection, mapaRubros);

			Collection<AsientoIf> asientosGeneradosDeCheques = new ArrayList<AsientoIf>();
			Collection<AsientoIf> asientosGenerados = rolPagoAsientoAutomaticoHandlerLocal
				.generarAsientoAutomaticoAnticipos(fechaPago, rolPagoIf,chequesCollection, nominaCollection,
					mapaChequesEmitidos, mapaRubros,asientosGeneradosDeCheques);

			verificarSumaAsientosPagoRolMensual(rolPagoIf, tipoRolIf,asientosGenerados, 
				chequesCollection, nominaCollection,sumaDetalle);

			for (String numeroCheque : mapaChequesEmitidos.keySet()) {
				ChequeDatos ChequeDatos = mapaChequesEmitidos.get(numeroCheque);
				ChequeEmitidoIf chequeEmitido = ChequeDatos.getChequeEmitido();
				if (chequeEmitido.getValor().doubleValue() > 0D)
					chequeEmitidoLocal.procesarChequeEmitido(ChequeDatos);
			}

			return asientosGeneradosDeCheques;

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al autorizar los pagos !!");
		}
	}

	private double procesarPagoAnticipoRolCalculo(Date fechaPago,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList,
			Collection<DatoAsientoPagoRol> chequesCollection,
			Collection<DatoAsientoPagoRol> nominaCollection,
			Map<Long, RubroIf> mapaRubros) throws GenericBusinessException {

		double sumaDetalle = 0.0;

		for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {

			TipoPagoIf tipoPagoIf = (TipoPagoIf) mapa.get("tipoPagoIf");
			Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");
			String numeroCheque = (String) mapa.get("numeroCheque");
			String nombreBeneficiario = (String) mapa.get("beneficiario");
			Double total = (Double) mapa.get("total");
			String nombreRubro = (String) mapa.get("nombreRubro");

			DatoAsientoPagoRol dato = new DatoAsientoPagoRol();
			dato.setTotal(total);
			dato.setNumeroCheque(numeroCheque);
			dato.setNombreRubro(nombreRubro);
			dato.setNombreEmpleado(nombreBeneficiario);
			// Long cuentaDeCuentaBancaria =
			// getCuentaDeCuentaBancaria(mapCuentaDeCuentaBancaria,
			// cuentaBancariaId);
			dato.setCuentaBancariaId(cuentaBancariaId);

			Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa
					.get("rolPagoDetalleCollection");

			for (RolPagoDetalleIf detalleTmp : detallesRolPago) {

				RolPagoDetalleIf detalle = rolPagoDetalleLocal
						.getRolPagoDetalle(detalleTmp.getId());
				boolean registrarDatosDeRubroEventual = false;
				if (detalle.getEstado().equals(
						EstadoRolPagoDetalle.AUTORIZADO.getLetra())) {

					if (detalle.getRubroEventualId() != null) {
						RubroEventualIf rubroEventualIf = rubroEventualLocal
								.getRubroEventual(detalle.getRubroEventualId());
						RubroIf rubroIf = verificarRubro(mapaRubros,
								rubroEventualIf.getRubroId());
						dato.setRubroIf(rubroIf);
						rubroEventualIf.setEstado(EstadoRubroEventual.PAGADO
								.getLetra());
						// Si es que en rubro_eventual no tiene fecha ni tipo
						// rol de pago significa que no emite cheque
						// y en el rol de pago detalle se puede poner los datos
						// que se ingresan ahi.
						if (rubroEventualIf.getTipoRolIdPago() == null
								&& rubroEventualIf.getFechaPago() == null) {
							// rubroEventualIf.setEstado(EstadoRubroEventual.getLetraEstadoRubroEventual(EstadoRubroEventual.PAGADO));
							detalle.setTipoPagoId(tipoPagoIf.getId());
							detalle.setCuentaBancariaId(cuentaBancariaId);
							if (numeroCheque != null
									&& registrarDatosDeRubroEventual)
								detalle.setPreimpreso(numeroCheque);
						}
					} else {
						RubroIf rubroIf = verificarRubro(mapaRubros, detalle
								.getRubroId());
						dato.setRubroIf(rubroIf);
						detalle.setTipoPagoId(tipoPagoIf.getId());
						detalle.setCuentaBancariaId(cuentaBancariaId);
						if (numeroCheque != null
								&& registrarDatosDeRubroEventual)
							detalle.setPreimpreso(numeroCheque);
					}
					
					//Setear el rolPagoDetalleId me va a servir para luego setear el asientoId
					//en la tabla rolPagoDetalle
					dato.setRolPagoDetalleId(detalle.getId());
					
					if (fechaPago != null && detalle.getFechaPago() == null)
						detalle.setFechaPago(fechaPago);
					detalle.setEstado(EstadoRolPagoDetalle.PAGADO.getLetra());
				}
			}
			dato.setTotal(total);
			if (numeroCheque != null && !"".equals(numeroCheque))
				dato.setNumeroCheque(numeroCheque);

			if (tipoPagoIf.getNombre().contains("CHEQUE")) {
				chequesCollection.add(dato);
			} else {
				nominaCollection.add(dato);
			}

			sumaDetalle += total;
		}
		return utilitariosLocal.redondeoValor(sumaDetalle);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void autorizarAnticipoRolPago(Date fechaPago, RolPagoIf rolPagoIf,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList)
			throws GenericBusinessException {
		try {
			DecimalFormat formatoDosEnteros = new DecimalFormat("00");
			ArrayList<RolPagoIf> rolesPagoCollection = new ArrayList<RolPagoIf>();
			for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {
				TipoPagoIf tipoPago = (TipoPagoIf) mapa.get("tipoPagoIf");
				CuentaBancariaIf cuentaBancaria = (CuentaBancariaIf) mapa.get("cuentaBancariaIf");
				String numeroCheque = (String) mapa.get("numeroCheque");
				// String nombreBeneficiario = (String)
				// mapa.get("nombreBeneficiario");
				Map<String, Object> mapaEventuales = new HashMap<String, Object>();
				mapaEventuales.put("rolpagoId", rolPagoIf.getId());

				ArrayList<RubroEventualIf> rubrosEventuales = (ArrayList<RubroEventualIf>) mapa
						.get("rubroEventualCollection");
				for (RubroEventualIf rubroEventual : rubrosEventuales) {
					RolPagoDetalleIf rolPagoDetalle = null;
					Collection<RolPagoDetalleIf> rubroEventualeExistente = rolPagoDetalleLocal
							.findRolPagoDetalleByRubroEventualId(rubroEventual.getId());
					if (rubroEventualeExistente.size() > 1) {
						throw new GenericBusinessException(
								"Error en la autorización del Pago, existen mas de un mismo Rubro Eventual registrado en el detalle del Rol !!");
					} else if (rubroEventualeExistente.size() == 1) {
						for (RolPagoDetalleIf re : rubroEventualeExistente) {
							rolPagoDetalle = re;
							rolPagoDetalle.setEstado(EstadoRolPagoDetalle.AUTORIZADO.getLetra());
						}
					} else if (rubroEventualeExistente.size() == 0) {
						// Lo que esta a continuacion se lo hace para que se ponga el rol de pago que le corresponde
						// al detalle que se va a registrar. Se busca el contrato para sacar el tipo de contrato
						ContratoIf contrato = contratoLocal.getContrato(rubroEventual.getContratoId());
						// Se usa el tipo de contrato para sacar el rol de pago junto con los otros parametros
						RolPagoIf rolPagoDeContrato = verificarRolPago(rolesPagoCollection, 
							contrato.getTipocontratoId(), rubroEventual,formatoDosEnteros);
						// rolPagoDetalle = registrarRubroEventual(rolPagoIf,
						// rubroEventual.getContratoId()
						rolPagoDetalle = registrarRubroEventual(rolPagoDeContrato, 
							rubroEventual.getContratoId(), rubroEventual,rubroEventual.getValor(),
							EstadoRolPagoDetalle.AUTORIZADO.getLetra());
					}
					rolPagoDetalle.setValor(rubroEventual.getValor());
					rolPagoDetalle.setTipoPagoId(tipoPago.getId());
					rolPagoDetalle.setCuentaBancariaId(cuentaBancaria.getId());
					if (numeroCheque != null)
						rolPagoDetalle.setPreimpreso(numeroCheque);

					if (rolPagoDetalle.getId() == null)
						rolPagoDetalleLocal.addRolPagoDetalle(rolPagoDetalle);
					else
						rolPagoDetalleLocal.saveRolPagoDetalle(rolPagoDetalle);

					// Se le cambia el estado al rubro eventual
					rubroEventual.setEstado(EstadoRubroEventual.AUTORIZADO
							.getLetra());
					rubroEventualLocal.saveRubroEventual(rubroEventual);
				}

				/*
				 * Collection<RolPagoDetalleIf> detallesRolPago =
				 * rolPagoDetalleLocal.findRolPagoDetalleEventualesByMapByEstados(mapaEventuales,rubroId,
				 * EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.EMITIDO) );
				 * 
				 * for ( RolPagoDetalleIf detalleTmp : detallesRolPago ){
				 * 
				 * RolPagoDetalleIf detalle =
				 * rolPagoDetalleLocal.getRolPagoDetalle(detalleTmp.getId()); if (
				 * detalle.getEstado().equals(
				 * EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.EMITIDO) ) ){
				 * 
				 * if ( detalle.getRubroEventualId() != null ){ RubroEventualIf
				 * rubroEventualIf =
				 * rubroEventualLocal.getRubroEventual(detalle.getRubroEventualId());
				 * rubroEventualIf.setEstado(EstadoRubroEventual.getLetraEstadoRubroEventual(EstadoRubroEventual.AUTORIZADO)); }
				 * 
				 * detalle.setTipoPagoId(tipoPago.getId());
				 * detalle.setCuentaBancariaId(cuentaBancaria.getId()); if (
				 * numeroCheque != null ) detalle.setPreimpreso(numeroCheque);
				 * detalle.setEstado(EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
				 * //detalle.setObservacion(descripcionAnticipo!=null?descripcionAnticipo:detalle.getObservacion());
				 * //sumaDetalle = sumarIngresos(tipoRolIf,
				 * sumaDetalle,mapaRubro, detalle); } }
				 */
			}

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.setRollbackOnly();
			throw new GenericBusinessException(
					"Error al autorizar los pagos !!");
		}
	}

	private RolPagoIf verificarRolPago(Set<RolPagoIf> setRolPago, String mes,
			String anio, Long tipoRolPagoId, Long tipoContratoId)
			throws GenericBusinessException {
		RolPagoIf rolPago = null;
		for (RolPagoIf rp : setRolPago) {
			if (rp.getMes().equals(mes) && rp.getAnio().equals(anio)
					&& rp.getTipocontratoId().equals(tipoContratoId)
					&& rp.getTiporolId().equals(tipoRolPagoId))
				return rp;
		}
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("mes", mes);
		mapa.put("anio", anio);
		mapa.put("tiporolId", tipoRolPagoId);
		mapa.put("tipocontratoId", tipoContratoId);
		Collection<RolPagoIf> roles = findRolPagoByQuery(mapa);
		if (roles.size() == 1)
			rolPago = roles.iterator().next();
		else if (roles.size() == 0)
			throw new GenericBusinessException(
					"No existe Rol de Pago con los pametros indicados para la creacion de Rubro Eventual !!");
		else if (roles.size() > 1)
			throw new GenericBusinessException(
					"Existe mas de un Rol de Pago con los pametros indicados para la creacion de Rubro Eventual !!");
		return rolPago;
	}

	private void verificarSumaAsientosPagoAnticipos(RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, Collection<AsientoIf> asientosGenerados,
			DatoAsientoPagoRol dato) throws GenericBusinessException {

		double debe = 0.0;
		double haber = 0.0;

		for (AsientoIf asiento : asientosGenerados) {

			Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal
					.findAsientoDetalleByAsientoId(asiento.getId());
			for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos
					.hasNext();) {
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos
						.next();
				debe += asientoDetalle.getDebe().doubleValue();
				haber += asientoDetalle.getHaber().doubleValue();
			}
			debe = utilitariosLocal.redondeoValor(debe);
			haber = utilitariosLocal.redondeoValor(haber);
			if (debe != haber)
				throw new GenericBusinessException(
						"Error al guardar "
								+ tipoRolIf.getNombre()
								+ ", el valor del Debe y Haber no coinciden en el Asiento");
		}
		if (debe != dato.getTotal())
			throw new GenericBusinessException(
					"Error al guardar "
							+ tipoRolIf.getNombre()
							+ ", el valor del Debe y Haber no coinciden con el detalle del Rol");
	}

	private void verificarSumaAsientosPagoRolQuincenal(RolPagoIf rolPagoIf,
			TipoRolIf tipoRolIf, Collection<AsientoIf> asientosGenerados,
			Collection<DatoAsientoPagoRol> chequesCollection,
			Collection<DatoAsientoPagoRol> nominaCollection, double sumaDetalle)
			throws GenericBusinessException {

		double debe = 0.0;
		double haber = 0.0;

		/*
		 * DocumentoIf documentoIf =
		 * documentoLocal.getDocumento(tipoRolIf.getDocumentoId());
		 * TipoDocumentoIf tipoDocumentoIf =
		 * tipoDocumentoLocal.getTipoDocumento(documentoIf.getTipodocumentoId());
		 * Map<String, Object> mapaAsientos = new HashMap<String, Object>();
		 * mapaAsientos.put("tipoDocumentoId",tipoDocumentoIf.getId());
		 * mapaAsientos.put("transaccionId",rolPagoIf.getId()); Collection<AsientoIf>
		 * asientosExistentes = asientoLocal.findAsientoByQuery(mapaAsientos);
		 * for ( AsientoIf asientoIf : asientosExistentes ){ Collection<AsientoDetalleIf>
		 * asientosDetalles =
		 * asientoDetalleLocal.findAsientoDetalleByAsientoId(asientoIf.getId());
		 * for ( AsientoDetalleIf asientoDetalleIf : asientosDetalles ){ debe +=
		 * asientoDetalleIf.getDebe().doubleValue(); haber +=
		 * asientoDetalleIf.getHaber().doubleValue(); } } debe =
		 * utilitariosLocal.redondeoValor(debe); haber =
		 * utilitariosLocal.redondeoValor(haber);
		 */

		for (AsientoIf asiento : asientosGenerados) {

			Collection<AsientoDetalleIf> asientosDetalle = asientoDetalleLocal
					.findAsientoDetalleByAsientoId(asiento.getId());
			for (Iterator itAsientos = asientosDetalle.iterator(); itAsientos
					.hasNext();) {
				AsientoDetalleIf asientoDetalle = (AsientoDetalleIf) itAsientos
						.next();
				debe += asientoDetalle.getDebe().doubleValue();
				haber += asientoDetalle.getHaber().doubleValue();
			}
			debe = utilitariosLocal.redondeoValor(debe);
			haber = utilitariosLocal.redondeoValor(haber);
			if (debe != haber)
				throw new GenericBusinessException(
						"Error al guardar "
								+ tipoRolIf.getNombre()
								+ ", el valor del Debe y Haber no coinciden en el Asiento");
		}
		// if ( debe != haber )
		// throw new GenericBusinessException("Error al guardar
		// "+tipoRolIf.getNombre()+", el valor del Debe y Haber no coinciden en
		// el Asiento");
		if (debe != sumaDetalle)
			throw new GenericBusinessException(
					"Error al guardar "
							+ tipoRolIf.getNombre()
							+ ", el valor del Debe y Haber no coinciden con el detalle del Rol");
	}

	private double procesarPagoRolCalculo(RolPagoIf rolPagoIf, TipoRol tipoRol,
			Date fechaPago,
			Collection<Map<String, Object>> rolPagoDetalleSeleccionadosList,
			Collection<DatoAsientoPagoRol> chequesCollection,
			Collection<DatoAsientoPagoRol> nominaCollection,
			Collection<DatoAsientoPagoRol> provisionCollection)
			throws GenericBusinessException {

		double sumaDetalle = 0.0;
		Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		for (Map<String, Object> mapa : rolPagoDetalleSeleccionadosList) {

			String numeroCheque = (String) mapa.get("numeroCheque");
			Double total = (Double) mapa.get("total");
			boolean contratoIdVerificado = false;
			DatoAsientoPagoRol dato = new DatoAsientoPagoRol();
			TipoPagoIf tipoPagoIf = (TipoPagoIf) mapa.get("tipoPagoIf");
			String nombre = null;
			if (mapa.get("beneficiario") != null)
				nombre = (String) mapa.get("beneficiario");
			else if ((String) mapa.get("nombre") != null)
				nombre = (String) mapa.get("nombre");
			else
				throw new GenericBusinessException("Nombre no establecido !!");
			dato.setNombreEmpleado(nombre);
			Long cuentaBancariaId = (Long) mapa.get("cuentaBancariaId");
			dato.setCuentaBancariaId(cuentaBancariaId);
			// Map<Long, DatoRubroEventual> mapaRubroEventuales = new
			// HashMap<Long, DatoRubroEventual>();

			Long contratoId = null;
			Collection<RolPagoDetalleIf> detallesRolPago = (Collection<RolPagoDetalleIf>) mapa
					.get("rolPagoDetalleCollection");
			for (RolPagoDetalleIf detalleTmp : detallesRolPago) {
				try {
					RolPagoDetalleIf detalle = rolPagoDetalleLocal
							.getRolPagoDetalle(detalleTmp.getId());
					if (detalle.getRubroId() != null) {
						// Se establecen datos en RolPagoDetalle
						if (fechaPago != null && detalle.getFechaPago() == null)
							detalle.setFechaPago(fechaPago);
						if (!contratoIdVerificado) {
							dato.setContratId(detalle.getContratoId());
							contratoId = detalle.getContratoId();
							contratoIdVerificado = true;
						}
						if (detalle.getEstado().equals(
								EstadoRolPagoDetalle.AUTORIZADO.getLetra())) {
							detalle.setEstado(EstadoRolPagoDetalle.PAGADO
									.getLetra());
						}
						/*if (fechaPago != null && detalle.getFechaPago() == null)
							detalle.setFechaPago(fechaPago);*/
						if (numeroCheque != null && !"".equals(numeroCheque)) {
							detalle.setPreimpreso(numeroCheque);
						}
					} else if (detalle.getRubroEventualId() != null
							&& detalle.getEstado().equals(
									EstadoRubroEventual.AUTORIZADO.getLetra())) {
						RubroEventualIf rubroEnventual = rubroEventualLocal
								.getRubroEventual(detalle.getRubroEventualId());
						// Si el rubroEventuale no tiene fecha de pago se lo
						// toma en cuenta
						// porque quiere decir que no se emitio cheque o no se
						// hizo debito con este.
						if (rubroEnventual.getFechaPago() == null) {
							// Se establecen datos en RolPagoDetalle para Rubros
							// Eventuales
							dato.setEventual(true);
							detalle.setEstado(EstadoRolPagoDetalle.PAGADO
									.getLetra());
							if (fechaPago != null
									&& detalle.getFechaPago() == null)
								detalle.setFechaPago(fechaPago);
							if (!contratoIdVerificado) {
								dato.setContratId(detalle.getContratoId());
								contratoIdVerificado = true;
							}
							/*if (fechaPago != null
									&& detalle.getFechaPago() == null)
								detalle.setFechaPago(fechaPago);*/
							if (numeroCheque != null
									&& !"".equals(numeroCheque)) {
								detalle.setPreimpreso(numeroCheque);
							}
							// Se establecen los datos que faltan en
							// RubroEventual.
							rubroEnventual.setFechaPago(fechaPago);
							rubroEnventual.setTipoRolIdPago(rubroEnventual
									.getTipoRolIdCobro());
							rubroEnventual.setEstado(EstadoRubroEventual.PAGADO
									.getLetra());

							// VERIFICAR SI EN RUBRO EVENTUAL TIENE EVENTO
							// CONTABLE , SI TIENE HAY QUE
							// SACARLO DE LA LISTA Y AGRUPARLO EN OTRA LISTA
							// PARA PODER PONERLO
							// CON LA PLANTILLA QUE LE CORRESPONDE
						}
					}
				} catch (GenericBusinessException e) {
					e.printStackTrace();
				}
			}
			// if ( mapaRubroEventuales.size() > 0 )
			// dato.setMapaRubroEventuales(mapaRubroEventuales);

			dato.setTotal(total);
			if (numeroCheque != null && !"".equals(numeroCheque))
				dato.setNumeroCheque(numeroCheque);

			if (tipoPagoIf.getNombre().contains("CHEQUE")) {
				chequesCollection.add(dato);
			} else {
				nominaCollection.add(dato);
			}

			sumaDetalle += total;

			//
			if (tipoRol == TipoRol.APORTE_PATRONAL
					&& provisionCollection != null) {

				Map<Long, RubroIf> mapaRubrosEspeciales = new HashMap<Long, RubroIf>();
				RolPagoIf rolMensual = obtenerRolPagoMensual(rolPagoIf,
						mapaRubrosEspeciales);
				mapa = new HashMap<String, Object>();
				mapa.put("rubroEventualId", "null");
				mapa.put("rolpagoId", rolMensual.getId());
				// mapa.put("estado", EstadoRolPagoDetalle.PAGADO.getLetra());

				for (Long rubroId : mapaRubrosEspeciales.keySet()) {

					RubroIf rubro = mapaRubrosEspeciales.get(rubroId);
					ingresarRubrosDeProvision(provisionCollection, mapa,
							nombre, cuentaBancariaId, contratoId, rubro);
				}

				// APORTE PERSONAL
				/*
				 * if ( rubroAportePersonal != null ){
				 * ingresarRubrosDeProvision(provisionCollection, mapa, nombre,
				 * cuentaBancariaId, contratoId, rubroAportePersonal); }
				 * 
				 * //QUIROGRAFARIO if ( rubroQuirografario != null ){
				 * ingresarRubrosDeProvision(provisionCollection, mapa, nombre,
				 * cuentaBancariaId, contratoId, rubroQuirografario); }
				 * 
				 * //HIPOTECARIO if ( rubroHipotecario != null ){
				 * ingresarRubrosDeProvision(provisionCollection, mapa, nombre,
				 * cuentaBancariaId, contratoId, rubroHipotecario); }
				 */

			}
		}
		return utilitariosLocal.redondeoValor(sumaDetalle);
	}

	private RolPagoIf obtenerRolPagoMensual(RolPagoIf rolPagoIf,
			Map<Long, RubroIf> mapaRubros) throws GenericBusinessException {
		Collection<TipoRolIf> tiposRolPago = tipoRolLocal.getTipoRolList();

		TipoRolIf tipoRolMensual = buscarTipoRolIf(tiposRolPago, "MENSUAL");
		Map<String, Object> mapaRolMensual = new HashMap<String, Object>();
		mapaRolMensual.put("mes", rolPagoIf.getMes());
		mapaRolMensual.put("anio", rolPagoIf.getAnio());
		mapaRolMensual.put("tiporolId", tipoRolMensual.getId());

		Collection<RolPagoIf> rolesMensuales = findRolPagoByQuery(mapaRolMensual);
		if (rolesMensuales.size() == 0)
			throw new GenericBusinessException(
					"Rol de Pago Mensual no ha sido generado !!!");
		RolPagoIf rolMensual = rolesMensuales.iterator().next();

		RubroIf rubroAportePersonal = nominaUtilesLocal
				.buscarRubroUnicoByNombreByTipoRubro("%APORTE%PERSONAL%",
						TipoRubro.DESCUENTO.getLetra());
		RubroIf rubroQuirografario = nominaUtilesLocal
				.buscarRubroUnicoByNombreByTipoRubro("%QUIROGRAFARIO%",
						TipoRubro.DESCUENTO.getLetra());
		RubroIf rubroHipotecario = nominaUtilesLocal
				.buscarRubroUnicoByNombreByTipoRubro("%HIPOTECARIO%",
						TipoRubro.DESCUENTO.getLetra());

		mapaRubros.put(rubroAportePersonal.getId(), rubroAportePersonal);
		mapaRubros.put(rubroQuirografario.getId(), rubroQuirografario);
		mapaRubros.put(rubroHipotecario.getId(), rubroHipotecario);

		return rolMensual;
	}

	private void ingresarRubrosDeProvision(
			Collection<DatoAsientoPagoRol> provisionCollection,
			Map<String, Object> mapa, String nombre, Long cuentaBancariaId,
			Long contratoId, RubroIf rubro) throws GenericBusinessException {
		mapa.put("rubroId", rubro.getId());
		Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
				.findRolPagoDetalleByQuery(mapa);
		for (RolPagoDetalleIf detalle : detalles) {
			DatoAsientoPagoRol dato = new DatoAsientoPagoRol();
			dato.setNombreEmpleado(nombre);
			dato.setCuentaBancariaId(cuentaBancariaId);
			dato.setContratId(contratoId);
			dato.setRubroIf(rubro);
			dato.setNombreRubro(rubro.getNombre());
			dato.setTotal(detalle.getValor().doubleValue());
			detalle.setEstado(EstadoRolPagoDetalle.PAGADO.getLetra());
			provisionCollection.add(dato);
		}
	}

	private void registrarRubroEventualEnMapa(RolPagoDetalleIf detalle,
			Double total, Map<Long, RubroIf> mapaRubros,
			Map<Long, DatoRubroEventual> mapaRubroEventuales)
			throws GenericBusinessException {
		RubroEventualIf rubroEventual = rubroEventualLocal
				.getRubroEventual(detalle.getRubroEventualId());
		RubroIf rubro = verificarRubro(mapaRubros, rubroEventual.getRubroId());

		if (!mapaRubroEventuales.containsKey(rubro.getId())) {
			DatoRubroEventual dre = new DatoRubroEventual();
			dre.setRubroEventualIf(rubroEventual);
			dre.setRubroIf(rubro);
			dre.setValor(total);
			mapaRubroEventuales.put(rubro.getId(), dre);
		} else {
			DatoRubroEventual dre = mapaRubroEventuales.get(rubro.getId());
			Double totalRubroEventual = dre.getValor();
			totalRubroEventual += total;
			dre.setValor(totalRubroEventual);
			mapaRubroEventuales.put(rubro.getId(), dre);
		}
	}

	/*
	 * private String crearQueryStringRolesId(Calendar calInicio, Calendar
	 * calFin, String queryString) throws GenericBusinessException { if (
	 * calInicio != null && calFin != null ){ Collection<String> listaMesesAnio =
	 * getFechasParaRolPagoByFechaInicioByFechaFin(calInicio,calFin); String
	 * queryRolesId = " and rp.id in ("; for (String linea : listaMesesAnio ){
	 * String mes = linea.split("-")[1]; String anio = linea.split("-")[3]; Map<String,
	 * Object> mapaRol = new HashMap<String, Object>(); mapaRol.put("mes",
	 * mes); mapaRol.put("anio", anio); Collection<RolPagoIf> roles =
	 * findRolPagoByQuery(mapaRol); for ( RolPagoIf rp : roles ){ queryRolesId +=
	 * (rp.getId()+","); } } queryRolesId =
	 * queryRolesId.substring(0,queryRolesId.length()-1); queryRolesId += " ) ";
	 * queryString += queryRolesId; } return queryString; }
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<RolPagoIf> getRolPagoList(String rolProvisionado,Long tipoContratoId,String estadoDetalle,
			Calendar calInicio, Calendar calFin)
			throws GenericBusinessException {
		try {
			String querySelectPeriodo = "select distinct rp ";
			String querySelectFechas = "select distinct rp.tiporolId,rp.tipocontratoId,rp.anio ";
			
			String queryString = "from TipoRolEJB tr,RolPagoEJB rp,RolPagoDetalleEJB rpd,RubroEJB r ";
			queryString += "where tr.id = rp.tiporolId and rp.id = rpd.rolpagoId and rpd.rubroId = r.id "
				+ " and rpd.rubroEventualId is null and rpd.estado = :estado "
				+ " and not r.tipoRubro = :tipoRubro " 
				+ " and tr.rubroProvisionado = :rolProvisionado "
				+ " and tr.formaPago = :formaPago ";

			if ( tipoContratoId != null )
				queryString += " and rp.tipocontratoId = :tipoContratoId ";
			
			
			Collection<RolPagoIf> rolesPago = getRolesPagoByFechaInicioByFechaFin(
					null, null, calInicio, calFin);
			queryString = generarStringRolesPago(rolesPago, queryString, "rp","id");

			//Roles con tipo de rol por periodo
			String queryOrderPeriodo = " order by rp.anio,rp.mes,rp.tiporolId";
			String queryPeriodo = querySelectPeriodo + queryString + queryOrderPeriodo;
			Query query = manager.createQuery(queryPeriodo);
			if ( tipoContratoId != null )
				query.setParameter("tipoContratoId", tipoContratoId);
			query.setParameter("rolProvisionado", rolProvisionado );
			query.setParameter("formaPago", TipoRolFormaPago.PERIODO.getLetra() );
			query.setParameter("estado", estadoDetalle);
			query.setParameter("tipoRubro", TipoRubro.ANTICIPO.getLetra());
			Collection<RolPagoIf> rolesPorPeriodo = query.getResultList(); 
			
			//Roles con tipo de rol por fechas
			String queryOrderFechas = " order by rp.anio";
			String queryFechas = querySelectFechas + queryString + queryOrderFechas;
			query = manager.createQuery(queryFechas);
			if ( tipoContratoId != null )
				query.setParameter("tipoContratoId", tipoContratoId);
			query.setParameter("rolProvisionado", rolProvisionado );
			query.setParameter("formaPago", TipoRolFormaPago.POR_FECHAS.getLetra() );
			query.setParameter("estado", estadoDetalle);
			query.setParameter("tipoRubro", TipoRubro.ANTICIPO.getLetra());
			
			Collection<Object[]> rolesPorFechas = query.getResultList();
			
			Collection<RolPagoIf> roles = new ArrayList<RolPagoIf>();
			for ( RolPagoIf rp : rolesPorPeriodo )
				roles.add(rp);
			
			for ( Object[] rp : rolesPorFechas ){
				RolPagoIf rolpago = new RolPagoData();
				rolpago.setTiporolId((Long)rp[0]);
				rolpago.setTipocontratoId((Long)rp[1]);
				rolpago.setAnio((String)rp[2]);
				roles.add(rolpago);
			}
			
			Collections.sort((List)roles, ordenarRolesPago);
			
			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al concultar Lista de Rol de Pago");
		}
	}

	Comparator<RolPagoIf> ordenarRolesPago = new Comparator<RolPagoIf>(){

		public int compare(RolPagoIf o1, RolPagoIf o2) {
			Integer mesAnio1 = Integer.valueOf(o1.getMes()!=null? o1.getMes() : "0" )+
				Integer.valueOf(o1.getAnio());
			Integer mesAnio2 = Integer.valueOf(o2.getMes()!=null? o2.getMes() : "0" )+
				Integer.valueOf(o2.getAnio());
			return mesAnio1.compareTo(mesAnio2);
		}
		
	};
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<RolPagoIf> getRolPagoListGeneracionAsientos(
			Collection<String> estadosDetalle, Collection<String> tiposRol,
			Calendar calInicio, Calendar calFin, String... estadosProvisionados)
			throws GenericBusinessException {
		try {

			String queryString = "select distinct rp ";
			queryString += "from RolPagoEJB rp,RolPagoDetalleEJB rpd,RubroEJB r,TipoRolEJB tr ";
			queryString += "where tr.id = rp.tiporolId and rp.id = rpd.rolpagoId and rpd.rubroId = r.id "
					+ " and rpd.rubroEventualId is null "
					// " and rpd.estado = :estado "+
					// " and not r.tipoRubro = 'A' ";
					+" and not r.tipoRubro = :tipoRubroAnticipo "
					+" and not r.tipoRubro = :tipoRubroProvision " 
					+" and rp.asientoGenerado = :asientoGenerado";

			// queryString = crearQueryStringRolesId(calInicio, calFin,
			// queryString);
			Collection<RolPagoIf> rolesPago = getRolesPagoByFechaInicioByFechaFin(
					null, null, calInicio, calFin);
			queryString = generarStringRolesPago(rolesPago, queryString, "rp","id");

			if (tiposRol.size() > 0) {
				queryString += " and (";
				for (String tr : tiposRol) {
					queryString += (" tr.nombre like '%" + tr + "%' or");
				}
				queryString = queryString
						.substring(0, queryString.length() - 2);
				queryString += " ) ";
			}

			if (estadosDetalle != null && estadosDetalle.size() > 0) {
				queryString += " and (";
				for (String e : estadosDetalle) {
					queryString += (" rpd.estado = '%" + e + "%' or");
				}
				queryString = queryString
						.substring(0, queryString.length() - 2);
				queryString += " ) ";
			}

			queryString += " order by rp.id";
			Query query = manager.createQuery(queryString);
			// query.setParameter("estado", estadoDetalle);
			query.setParameter("tipoRubroAnticipo", TipoRubro.ANTICIPO.getLetra());
			query.setParameter("tipoRubroProvision", TipoRubro.PROVISION.getLetra());
			query.setParameter("asientoGenerado", AsientoGenerado.NO.getLetra());
			Collection<RolPagoIf> roles = query.getResultList();

			queryString = "select distinct rp ";
			queryString += "from RolPagoEJB rp,RolPagoDetalleEJB rpd,RubroEJB r ";
			queryString += "where rp.id = rpd.rolpagoId "
					+ " and rpd.rubroId = r.id " 
					// " and rpd.rubroEventualId is null "+
					+" and r.tipoRubro = :tipoRubro " 
					+" and rp.asientoGenerado = :asientoGenerado ";

			// queryString = crearQueryStringRolesId(calInicio, calFin,
			// queryString);
			// rolesPago = getRolesPagoByFechaInicioByFechaFin(null, null,
			// calInicio, calFin);
			queryString = generarStringRolesPago(rolesPago, queryString, "rp","id");

			if (estadosProvisionados.length > 0) {
				queryString += " and ( ";

				for (String estadoP : estadosProvisionados) {
					queryString += " rpd.estado = '" + estadoP + "' or";
				}
				queryString = queryString
						.substring(0, queryString.length() - 2);
				queryString += " ) ";

				query = manager.createQuery(queryString);
				query.setParameter("tipoRubro", TipoRubro.PROVISION.getLetra());
				query.setParameter("asientoGenerado", AsientoGenerado.NO.getLetra());
				Collection<RolPagoIf> rolesProvisiones = query.getResultList();

				for (RolPagoIf rpp : rolesProvisiones) {
					agregarRolPagoEnLista(roles, rpp);
				}
			}
			return roles;

		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al concultar Lista de Rol de Pago");
		}
	}

	private void agregarRolPagoEnLista(Collection<RolPagoIf> lista,
			RolPagoIf rolPagoIf) {
		for (RolPagoIf rp : lista) {
			if (rolPagoIf.getId().equals(rp.getId()))
				return;
		}
		lista.add(rolPagoIf);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<RolPagoIf> getRolPagoEventualList(String estadoDetalle)
			throws GenericBusinessException {
		try {
			String queryString = "select distinct rp ";
			queryString += "from RolPagoEJB rp,RolPagoDetalleEJB rpd ";
			queryString += "where rp.id = rpd.rolpagoId and rpd.rubroEventualId is not null and rpd.estado = :estado ";
			queryString += " order by rp.id";
			Query query = manager.createQuery(queryString);
			query.setParameter("estado", estadoDetalle);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al concultar Lista de Rol de Pago");
		}
	}

	/*@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Collection<RolPagoIf> getRolPagoAnticiposList(String estadoDetalle)
			throws GenericBusinessException {
		try {
			String queryString = "select distinct rp ";
			queryString += "from RolPagoEJB rp,RolPagoDetalleEJB rpd ";
			queryString += "where rp.id = rpd.rolpagoId "
					+ " and rpd.rubroEventualId is not null and rpd.estado = :estado ";
			queryString += " order by rp.id";
			Query query = manager.createQuery(queryString);
			query.setParameter("estado", estadoDetalle);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al concultar Lista de Rol de Pago");
		}
	}*/

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int getRolPagoListSize(Map aMap) {
		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "select count(e) from RolPagoEJB " + objectName
				+ " where " + where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		List countQueryResult = query.getResultList();
		Long countResult = (Long) countQueryResult.get(0);
		log.debug("The list size is: " + countResult.intValue());
		return countResult.intValue();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection findRolPagoByQuery(int startIndex,
			int endIndex, Map aMap) {
		if ((endIndex - startIndex) < 0) {
			return new ArrayList();
		}

		String objectName = "e";
		String where = QueryBuilder.buildWhere(aMap, objectName);
		String queryString = "from RolPagoEJB " + objectName + " where "
				+ where;
		Query query = manager.createQuery(queryString);

		Set keys = aMap.keySet();
		Iterator it = keys.iterator();

		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);
			query.setParameter(propertyKey, property);

		}

		query.setFirstResult(startIndex);
		query.setMaxResults(endIndex - startIndex);
		return query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection<RolPagoIf> findRolPagoByQuery(
			Map<String, Object> aMap, Long contratoId, String estadoDetalle)
			throws GenericBusinessException {
		try {
			String objectName = "e";
			String where = QueryBuilder.buildWhere(aMap, objectName);
			String queryString = "select distinct e from RolPagoEJB "
					+ objectName + ",RolPagoDetalleEJB rpd where ";
			queryString += (" e.id=rpd.rolpagoId and " + where);
			if (estadoDetalle != null)
				queryString += " and rpd.estado = :estadoDetalle";
			if (contratoId != null)
				queryString += " and rpd.contratoId = :contratoId";
			Query query = manager.createQuery(queryString);

			Set keys = aMap.keySet();
			Iterator it = keys.iterator();

			while (it.hasNext()) {
				String propertyKey = (String) it.next();
				Object property = aMap.get(propertyKey);
				query.setParameter(propertyKey, property);

			}
			if (estadoDetalle != null)
				query.setParameter("estadoDetalle", estadoDetalle);
			if (contratoId != null)
				query.setParameter("contratoId", contratoId);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException(
					"Error al consultar Rol de Pagos");
		}
	}

	/**
	 * ********************** CONSULTA PARA REPORTE POR CONTRATO
	 * *************************************
	 */

	public Collection<Object> consultarReportePorContrato(Long contratoId,
			Date fechaInicio, Date fechaFin) throws GenericBusinessException {

		if (fechaInicio == null)
			throw new GenericBusinessException(
					"Fecha inicio debe ser establecida !!");
		if (fechaInicio == null)
			throw new GenericBusinessException(
					"Fecha fin debe ser establecida !!");

		if (fechaInicio.compareTo(fechaFin) > 0)
			throw new GenericBusinessException(
					"Fecha Inicio debe ser anterior que Fecha Fin !!");

		Calendar calInicio = new GregorianCalendar();
		calInicio.setTimeInMillis(fechaInicio.getTime());
		calInicio.set(Calendar.DAY_OF_MONTH, 1);

		Calendar calFin = new GregorianCalendar();
		calFin.setTimeInMillis(fechaFin.getTime());
		calFin.set(Calendar.DAY_OF_MONTH, 1);

		ContratoIf contratoIf = contratoLocal.getContrato(contratoId);

		Map<Long, RubroIf> mapaRubros = new HashMap<Long, RubroIf>();
		Map<Long, String> mapaTipoRubros = new HashMap<Long, String>();

		Collection<Object> resultado = new ArrayList<Object>();

		while (calInicio.compareTo(calFin) <= 0) {
			int mes = calInicio.get(Calendar.MONTH);
			int anio = calInicio.get(Calendar.YEAR);
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("mes", mes);
			mapa.put("anio", anio);
			mapa.put("tipocontratoId", contratoIf.getTipocontratoId());

			Collection<RolPagoIf> rolesPago = findRolPagoByQuery(mapa);
			for (RolPagoIf rolPagoIf : rolesPago) {

				Map<String, Object> mapaDetalle = new HashMap<String, Object>();
				mapaDetalle.put("rolpagoId", rolPagoIf.getId());
				mapaDetalle.put("contratoId", contratoId);

				Collection<RolPagoDetalleIf> detalles = rolPagoDetalleLocal
						.findRolPagoDetalleByQueryNormal(mapaDetalle);
				for (RolPagoDetalleIf rpd : detalles) {
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
							mapaRubros, mapaTipoRubros, rpd.getRubroId());
					BigDecimal valor = rpd.getValor();
					Collection registro = new ArrayList();
					registro.add(anio);
					registro.add(mes);
					registro.add(rubroIf.getNombre());
					registro.add(valor);
					resultado.add(registro);
				}

				// RUBROS EVENTUALES
				Map<String, Object> mapaEventual = new HashMap<String, Object>();
				// parte de rolDetalle
				// mapaEventual.put("rolpagoId", rolPagoIf.getId());
				mapaEventual.put("contratoId", contratoId);
				// parte de rubro Eventual
				mapaEventual.put("tipoRolIdCobro", rolPagoIf.getTiporolId());
				mapaEventual.put("mesCobro", rolPagoIf.getMes());
				mapaEventual.put("anioCobro", rolPagoIf.getAnio());
				mapaEventual.put("tipoContratoId", rolPagoIf
						.getTipocontratoId());
				// mapaEventual.put("estado",
				// EstadoRolPagoDetalle.getLetraEstadoRolPagoDetalle(EstadoRolPagoDetalle.AUTORIZADO));
				Collection<RolPagoDetalleIf> rolPagoDetalles = rolPagoDetalleLocal
						.findRolPagoDetalleEventualesByMapByEstados(
								mapaEventual, EstadoRolPagoDetalle.EMITIDO
										.getLetra(),
								EstadoRolPagoDetalle.AUTORIZADO.getLetra(),
								EstadoRolPagoDetalle.PAGADO.getLetra());
				for (RolPagoDetalleIf rolPagoDetalle : rolPagoDetalles) {
					Long idRubroEventual = rolPagoDetalle.getRubroEventualId();
					BigDecimal valor = rolPagoDetalle.getValor();
					RubroEventualIf rubroEventualIf = rubroEventualLocal
							.getRubroEventual(idRubroEventual);
					RubroIf rubroIf = nominaUtilesLocal.verificarRubrosEnMapa(
							mapaRubros, mapaTipoRubros, rubroEventualIf
									.getRubroId());

					Collection registro = new ArrayList();
					registro.add(anio);
					registro.add(mes);
					registro.add(rubroIf.getNombre());
					registro.add(valor);
					resultado.add(registro);
				}

			}

			// Si es Diciembre subo el año
			if (calInicio.get(Calendar.MONTH) == 11) {
				int anioCalInicio = calInicio.get(Calendar.YEAR);
				calInicio.set(Calendar.YEAR, anioCalInicio + 1);
			}

			calInicio.roll(Calendar.MONTH, 1);

			/*
			 * int mesInicio = calInicio.get(Calendar.MONTH); int anioInicio =
			 * calInicio.get(Calendar.YEAR);
			 * 
			 * int mesFin = calFin.get(Calendar.MONTH); int anioFin =
			 * calFin.get(Calendar.YEAR);
			 * 
			 * if ( anioInicio == anioFin ){ if ( mesInicio == mesFin ){
			 *  } else {
			 *  } } else {
			 *  }
			 */

		}

		return resultado;

	}

	public void getValoresTipoRolPorAnio(Map<String, Object> mapa,
			Map<String, Object> mapaResultado, Map<Long, Double> mapaTotales, Long tipoRolId,
			Map<Long, RolPagoIf> mapaRolesPago, String... estados)
			throws GenericBusinessException {

		Long contratoId = (Long) mapa.get("contratoId");

		for (Long rolPagoId : mapaRolesPago.keySet()) {
			RolPagoIf rolPago = mapaRolesPago.get(rolPagoId);
			
			String queryString = "select e from RolPagoDetalleEJB e "
					+ " where e.rolpagoId = :rolpagoId and e.contratoId = :contratoId";

			if (estados != null && estados.length > 0) {
				queryString += " and (";
				for (String estado : estados) {
					queryString += (" e.estado = '" + estado + "' or");
				}
				queryString = queryString.substring(0, queryString.length() - 2);
				queryString += " )";
			}

			Query query = manager.createQuery(queryString);
			query.setParameter("rolpagoId", rolPago.getId());
			query.setParameter("contratoId", contratoId);
			// query.setParameter("rubroId", rubroId);
			Collection<RolPagoDetalleIf> detalleFondo = query.getResultList();
			Double total = 0.0;
			
			//reviso el tipo de rol
			TipoRolIf tipoRol = tipoRolLocal.getTipoRol(tipoRolId);
			
			for (RolPagoDetalleIf rpd : detalleFondo) {
				
				//cambio_ini_20140612 RVA
				if((tipoRol.getCodigo().equals("R13") || tipoRol.getCodigo().equals("RVA")) && rpd.getRubroId() != null){
					RubroIf rubroDetalle = rubroLocal.getRubro(rpd.getRubroId());
					if(rubroDetalle.getCodigo().equals("SB") 
							|| rubroDetalle.getCodigo().equals("OI") 
							|| rubroDetalle.getCodigo().equals("DTER")
							|| rubroDetalle.getCodigo().equals("VACA")){
						total += rpd.getValor().doubleValue();
					}
					
				}else if(!tipoRol.getCodigo().equals("R13") && 
						!tipoRol.getCodigo().equals("RVA")){
					total += rpd.getValor().doubleValue();
				}
				//cambio_fin_20140612 RVA
			}
			
			total = utilitariosLocal.redondeoValor(total);
			aumentarTotalPorEmpleado(mapaTotales, contratoId, total);
			mapaResultado.put(utilitariosLocal.getNombreMes(rolPago.getMes()), utilitariosLocal.redondeoValor(total));
		}
	}

	private Map<Long, RolPagoIf> listaDeRolesPagoPorAnio(RolPagoIf rolPagoIf, Collection<String> listaMesesAnio) {
		Map<Long, RolPagoIf> listaRolesPago = new LinkedHashMap<Long, RolPagoIf>();
		for (String linea : listaMesesAnio) {
			Map<String, Object> mapaRolPago = new HashMap<String, Object>();
			mapaRolPago.put("tiporolId", rolPagoIf.getTiporolId());
			mapaRolPago.put("tipocontratoId", rolPagoIf.getTipocontratoId());
			String mes = linea.split("-")[1];
			mapaRolPago.put("mes", mes);
			String anioTmp = linea.split("-")[3];
			mapaRolPago.put("anio", anioTmp);
			Collection<RolPagoIf> rolesPago = findRolPagoByQuery(mapaRolPago);
			for (RolPagoIf rolPago : rolesPago) {
				listaRolesPago.put(rolPago.getId(), rolPago);
			}
		}
		return listaRolesPago;
	}
	
	/*private Map<Long, RolPagoIf> listaDeRolesPagoPorAnio(RolPagoIf rolPagoIf,
			Map<String ,Collection<String>> listaMesesAnios) {
		
		Map<Long, RolPagoIf> listaRolesPago = new LinkedHashMap<Long, RolPagoIf>();
		
		Iterator listaMesesAnioIt = listaMesesAnios.keySet().iterator();
		while (listaMesesAnioIt.hasNext()) {
			String anio = (String)listaMesesAnioIt.next();
			Collection<String> listaMesesAnio = listaMesesAnios.get(anio);
			for(String linea : listaMesesAnio){
				Map<String, Object> mapaRolPago = new HashMap<String, Object>();
				mapaRolPago.put("tiporolId", rolPagoIf.getTiporolId());
				mapaRolPago.put("tipocontratoId", rolPagoIf.getTipocontratoId());
				String mes = linea.split("-")[1];
				mapaRolPago.put("mes", mes);
				String anioTmp = linea.split("-")[3];
				mapaRolPago.put("anio", anio);
				Collection<RolPagoIf> rolesPago = findRolPagoByQuery(mapaRolPago);
				for (RolPagoIf rolPago : rolesPago) {
					listaRolesPago.put(rolPago.getId(), rolPago);
				}
			}		
		}
		return listaRolesPago;
	}*/

	private ArrayList<String> getFechasDeAnioReporteDecimos(Integer anio) throws GenericBusinessException {
		ArrayList<String> lista = new ArrayList<String>();
		String mesEntero_Mes_Anio = "";
		NumberFormat dosDigitos = new DecimalFormat("00");
		for (int i = 1; i <= 12; i++) {
			String mes = utilitariosLocal.getNombreMes(i);
			mesEntero_Mes_Anio = (i - 1) + "-" + dosDigitos.format(i) + "-"
			+ mes + "-" + anio;
			lista.add(mesEntero_Mes_Anio);
		}
		return lista;
	}
	
	private ArrayList<String> getFechasDeAnioReporteDecimos(Integer anioInicio, Integer mesInicio, 
			Integer anioFin, Integer mesFin) throws GenericBusinessException {
		ArrayList<String> lista = new ArrayList<String>();
		String mesEntero_Mes_Anio = "";
		NumberFormat dosDigitos = new DecimalFormat("00");
		
		for (int i = mesInicio; i < 12; i++) {
			String mes = utilitariosLocal.getNombreMes(i+1);
			mesEntero_Mes_Anio = i + "-" + dosDigitos.format(i+1) + "-"	+ mes + "-" + anioInicio;
			lista.add(mesEntero_Mes_Anio);
		}

		for (int p = 0; p <= mesFin; p++) {
			String mes = utilitariosLocal.getNombreMes(p+1);
			mesEntero_Mes_Anio = p + "-" + dosDigitos.format(p+1) + "-"	+ mes + "-" + anioFin;
			lista.add(mesEntero_Mes_Anio);
		}		
		
		return lista;
	}
	
	private Collection<String> getFechasDeAnioFondoReserva(Integer anioInicio,
			Integer anioFinal) throws GenericBusinessException {
		// String anioS = String.valueOf(anio);
		ArrayList<String> lista = new ArrayList<String>();
		if (anioFinal == null)
			anioFinal = anioInicio;
		for (int anio = anioInicio; anio <= anioFinal; anio++) {
			String mesEntero_Mes_Anio = "";
			//INI_CAMBIO_20140704 Se modifica para que el reporte de fondo de reserva se muestre 
			//en el año de Enero a Diciembre
			String mes = utilitariosLocal.getNombreMes(1);  //se remplaza 12 por 1
			NumberFormat dosDigitos = new DecimalFormat("00");
			mesEntero_Mes_Anio = 0 + "-" + dosDigitos.format(01) + "-" + mes //se reemplaza 11 - 12 por 0 - 01
					+ "-" + (anio);		// se quita el anio-1
			lista.add(mesEntero_Mes_Anio);
			//for (int i = 1; i < 12; i++) { 
			  for (int i = 2; i <= 12; i++) { //se remmplaza para que los meses lleguen hasta diciembre
				mes = utilitariosLocal.getNombreMes(i);
				mesEntero_Mes_Anio = (i - 1) + "-" + dosDigitos.format(i) + "-"
						+ mes + "-" + anio;
				lista.add(mesEntero_Mes_Anio);
			}
		}
			//FIN_CAMBIO_20140704
		return lista;
	}

	private Collection<String> getFechasParaRolPagoByFechaInicioByFechaFin(
			Calendar fechaInicio, Calendar fechaFin)
			throws GenericBusinessException {
		// String anioS = String.valueOf(anio);
		if (fechaInicio == null)
			throw new GenericBusinessException(
					"Fecha Inicio no establecida para consulta de Roles de Pago !!");
		if (fechaFin == null)
			throw new GenericBusinessException(
					"Fecha Fin no establecida para consulta de Roles de Pago !!");

		ArrayList<String> lista = new ArrayList<String>();
		NumberFormat dosDigitos = new DecimalFormat("00");

		int anioInicio = fechaInicio.get(Calendar.YEAR);
		int mesInicio = fechaInicio.get(Calendar.MONTH) + 1;
		int anioFinal = fechaFin.get(Calendar.YEAR);
		int mesFin = fechaFin.get(Calendar.MONTH) + 1;
		for (int anio = anioInicio; anio <= anioFinal; anio++) {
			int mesFinInterno = anioFinal > anio ? 12 : mesFin;
			for (int i = mesInicio; i <= mesFinInterno; i++) {
				String mes = utilitariosLocal.getNombreMes(i);
				String mesEntero_Mes_Anio = (i - 1) + "-"
						+ dosDigitos.format(i) + "-" + mes + "-" + anio;
				lista.add(mesEntero_Mes_Anio);
			}
			mesInicio = 1;
		}
		return lista;
	}

	/**
	 * ********************** CONSULTA PARA RUBROS
	 * *************************************
	 */

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public java.util.Collection<Object> consultarDetallePorRubro(
			RubroIf rubroIf, Date fechaInicio, Date fechaFin,
			Long tipoContratoId, Long tipoRolId, Long contratoId,
			String estadoDetalle, Boolean modoEstrictoConsulta,
			Map<Long, RubroIf> mapaRubros, Long empresaId)
			throws GenericBusinessException {
		try {
			Collection<Object> resultado = new ArrayList<Object>();
			String nombreRubro = rubroIf.getNombre();

			Map<Long, RolPagoIf> mapaRolesPago = null;

			Calendar calInicio = new GregorianCalendar();
			calInicio.setTime(fechaInicio);
			Integer anioInicio = calInicio.get(Calendar.YEAR);

			Calendar calFin = new GregorianCalendar();
			if (fechaFin != null) {
				calFin.setTime(fechaFin);
			}

			if (calInicio.compareTo(calFin) > 0)
				throw new GenericBusinessException(
						"Fecha Inicio tiene que ser menor que Fecha Fin y que la Fecha Actual !!");

			Collection<RolPagoIf> rolesPago = getRolesPagoByFechaInicioByFechaFin(
					tipoContratoId, tipoRolId, calInicio, calFin);

			if (rolesPago.size() == 0)
				throw new GenericBusinessException(
						"No existe Rol de Pago para ese rango de fecha !! ");

			boolean esFondoReserva = nombreRubro.toUpperCase()
					.contains("FONDO")
					&& nombreRubro.toUpperCase().contains("RESERVA") ? true
					: false;
			boolean esImpuestoRenta = nombreRubro.toUpperCase().contains(
					"IMPUESTO")
					&& nombreRubro.toUpperCase().contains("RENTA") ? true
					: false;

			String queryString = "select distinct rp.anio,rp.mes,e.nombres,e.apellidos,rpd.valor,r.tipoRubro,rpd.estado,c.fechaInicio ";

			if (esImpuestoRenta
					&& !TipoRubro.EVENTUAL.getLetra().equals(
							rubroIf.getTipoRubro()))
				queryString = "select distinct rp.anio,rp.mes,e.nombres,e.apellidos,c.fechaInicio,rpd.valor,rpd.estado,rp,c,r ";
			// queryString = "select distinct
			// rp.anio,rp.mes,e.nombres,e.apellidos,c.fechaInicio,rpd.valor,rpd.estado,rp,c.id,r
			// ";

			if (!TipoRubro.EVENTUAL.getLetra().equals(rubroIf.getTipoRubro())) {
				queryString += " from RolPagoEJB rp,RolPagoDetalleEJB rpd, RubroEJB r,ContratoEJB c,EmpleadoEJB e "
						+ " where rp.id = rpd.rolpagoId and rpd.rubroId = r.id and rpd.contratoId = c.id "
						+ " and c.empleadoId = e.id ";
			} else {
				queryString += " from RolPagoEJB rp,RolPagoDetalleEJB rpd,RubroEventualEJB re, RubroEJB r,ContratoEJB c,EmpleadoEJB e "
						+ " where rp.id = rpd.rolpagoId and rpd.rubroEventualId = re.id "
						+ " and re.rubroId = r.id and rpd.contratoId = c.id "
						+ " and c.empleadoId = e.id ";
			}

			boolean insertarRubroId = false;

			if (modoEstrictoConsulta) {
				queryString += " and r.id = :rubroId";
				insertarRubroId = true;
			} else {
				if (esFondoReserva) {
					queryString += " and r.nombre like 'FONDO%RESERVA%' ";
				} else if (esImpuestoRenta) {
					queryString += " and r.nombre like 'IMPUESTO%RENTA%' ";
				} else {
					queryString += " and r.id = :rubroId";
					insertarRubroId = true;
				}
			}

			queryString = generarStringRolesPago(rolesPago, queryString, "rp",
					"id");

			if (estadoDetalle != null)
				queryString += " and rpd.estado = :estadoDetalle ";
			if (contratoId != null)
				queryString += " and rpd.contratoId = :contratoId ";

			queryString += " order by rp.anio,rp.mes,e.apellidos,e.nombres ";

			Query query = manager.createQuery(queryString);

			// query.setParameter("rubroId", rubroIf.getId());
			if (estadoDetalle != null)
				query.setParameter("estadoDetalle", estadoDetalle);
			if (contratoId != null)
				query.setParameter("contratoId", contratoId);
			if (insertarRubroId)
				query.setParameter("rubroId", rubroIf.getId());

			resultado = query.getResultList();

			// Si es impuesto a la renta como rubro normal
			if (esImpuestoRenta && !TipoRubro.EVENTUAL.getLetra().equals(rubroIf.getTipoRubro())) {

				TipoParametroIf tp = utilitariosLocal.getTipoParametro(empresaId, NominaParametros.TIPO_PARAMETRO);
				if (tp == null)
					throw new GenericBusinessException(
							"No existe Tipo de Parametro de Nomina, necesrio para calculo de Valores !!");

				// BUSQUEDA DE RUBRO PARA SUELDO BASICO DEL EMPLEADO
				Collection<ParametroEmpresaIf> pes = utilitariosLocal
					.getParametroEmpresa(empresaId,tp,false,NominaParametros.PARAMETRO_EMPRESA_BASE_CODIGO_SUELDO_BASICO);
				if (pes.size() == 0)
					throw new GenericBusinessException("No existe Parametro de Empresa que con codigo "+
						NominaParametros.PARAMETRO_EMPRESA_BASE_CODIGO_SUELDO_BASICO+
						"\nindique la Base del codigo para los sueldos !!");
				ParametroEmpresaIf pe = pes.iterator().next();

				Map<String, Object> mapaSB = new HashMap<String, Object>();
				mapaSB.put("codigo", pe.getValor() + "%");
				Collection<RubroIf> rubrosSueldos = rubroLocal.findRubroByQuery(mapaSB);

				// BUSQUEDA DE RUBRO PARA APORTE PERSONAL DEL EMPLEADO AL IESS
				pes = utilitariosLocal.getParametroEmpresa(empresaId, tp,false, NominaParametros.CODIGO_APORTE_IESS_PERSONAL);
				if (pes.size() == 0)
					throw new GenericBusinessException(
							"No existe Parametro de Empresa que con codigo "
									+ NominaParametros.CODIGO_APORTE_IESS_PERSONAL
									+ "\nindique el Aporte al IESS Personal !!");
				pe = pes.iterator().next();

				if (pe.getValor() == null || "".equals(pe.getValor()))
					throw new GenericBusinessException("Codigo de Aporte Personal al IESS no esta establecido !!");
				Collection<RubroIf> rubrosIess = rubroLocal.findRubroByCodigo(pe.getValor());
				if (rubrosIess.size() == 0)
					throw new GenericBusinessException(
							"No existe rubro con codigo " + pe.getValor()
									+ " para Aporte Personal al IESS !!");
				else if (rubrosIess.size() > 1)
					throw new GenericBusinessException("Existe mas de un rubro con codigo "+
						pe.getValor()+ " para Aporte Personal al IESS !!");

				RubroIf rubroIess = rubrosIess.iterator().next();

				Calendar calEnero = new GregorianCalendar();
				calEnero.set(Calendar.MONTH, Calendar.JANUARY);

				GregorianCalendar gcPrimerDiaMesRolPago = new GregorianCalendar();

				Calendar calMesAnteriorAlActual = new GregorianCalendar();
				calMesAnteriorAlActual.set(Calendar.MONTH,
						calMesAnteriorAlActual.get(Calendar.MONTH) - 1);

				ArrayList<Object> resultadoImpuestoRenta = new ArrayList<Object>();
				Map<String, RubroIf> mapaRubroByCodigo = new HashMap<String, RubroIf>();
				Map<String, BigDecimal> codigoValorxRubro = contratoRubroLocal
						.findMapaContratoRubroByRubroResgistrado(contratoId);
				
				//otros ingresos, para no tener que añadirlo a cada contrato
				if(codigoValorxRubro.get("OI") == null)
					codigoValorxRubro.put("OI", new BigDecimal(0));
				
				for (Object o : resultado) {
					Object[] filaAnterior = (Object[]) o;
					BigDecimal valorDetalle = (BigDecimal) filaAnterior[5];
					RolPagoIf rolPagoActualIf = (RolPagoIf) filaAnterior[7];
					// Long contratoTmpId = (Long)filaAnterior[8];
					ContratoIf contratoTmp = (ContratoIf) filaAnterior[8];
					RubroIf rubroTmpIf = (RubroIf) filaAnterior[9];

					calEnero.set(Calendar.YEAR, Integer.valueOf(rolPagoActualIf
							.getAnio()));

					calMesAnteriorAlActual.set(Calendar.YEAR, Integer
							.valueOf(rolPagoActualIf.getAnio()));
					calMesAnteriorAlActual.set(Calendar.MONTH, Integer
							.valueOf(rolPagoActualIf.getMes()) - 2);
					calMesAnteriorAlActual.set(Calendar.DATE,
							calMesAnteriorAlActual
									.getActualMaximum(Calendar.DATE));

					gcPrimerDiaMesRolPago.set(Calendar.MONTH, Integer
							.valueOf(rolPagoActualIf.getMes()) - 1);
					gcPrimerDiaMesRolPago.set(Calendar.DATE, 1);
					Date fechaPrimerDiaRolPago = new Date(gcPrimerDiaMesRolPago
							.getTimeInMillis());

					GregorianCalendar gcUltimoDiaMesRolPago = new GregorianCalendar();
					gcUltimoDiaMesRolPago.set(GregorianCalendar.MONTH, Integer
							.valueOf(rolPagoActualIf.getMes()) - 1);
					gcUltimoDiaMesRolPago.set(GregorianCalendar.YEAR, Integer
							.valueOf(rolPagoActualIf.getAnio()));
					Integer mesRolPago = gcUltimoDiaMesRolPago
							.get(GregorianCalendar.MONTH);
					Integer anioRolPago = gcUltimoDiaMesRolPago
							.get(GregorianCalendar.YEAR);
					Integer diaFinMesRolPago = gcUltimoDiaMesRolPago
							.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
					gcUltimoDiaMesRolPago.set(GregorianCalendar.DAY_OF_MONTH,
							diaFinMesRolPago);

					Collection<RolPagoIf> rolesPagoDesdeEnero = getRolesPagoByFechaInicioByFechaFin(
							rolPagoActualIf.getTipocontratoId(),
							rolPagoActualIf.getTiporolId(), calEnero,
							calMesAnteriorAlActual);
					String queryStringRoles = obtenerQueryRolesPagoAnterioresAlActual(rolesPagoDesdeEnero);

					// Se le resta 1 para considerar el mes actual
					int numeroMesesRestantes = 12 - (Integer
							.valueOf(rolPagoActualIf.getMes()) - 1);

					/*
					 * Map<String,Double> mapaValores =
					 * calcularBaseImponibleImpuestoRenta(
					 * rolPagoActualIf.getId(), contratoTmpId,
					 * rubroTmpIf.getPolitica(), queryStringRoles,
					 * numeroMesesRestantes, mapaRubroByCodigo);
					 */

					Double totalGastos = CalcularTotalGastosDeducibles(
							rolPagoActualIf, contratoTmp);
					totalGastos = utilitariosLocal
							.redondeoValor(totalGastos / 12);
					BigDecimal totalGastosBD = new BigDecimal(totalGastos);

					Map<String, Double> mapaValores = calculoFinalBaseImponibleImpuestoRenta(
							rolPagoActualIf, contratoTmp, rubroIf,
							queryStringRoles, numeroMesesRestantes,
							mapaRubroByCodigo, totalGastos, mapaRubros,
							codigoValorxRubro);

					Double baseImponibleD = mapaValores.get("baseImponible");
					BigDecimal baseImponible = new BigDecimal(baseImponibleD);

					Double valorSueldosBasico = 0.0;
					BigDecimal valorSueldosBasicoBD = BigDecimal.ZERO;
					for (RubroIf rsb : rubrosSueldos) {
						if (mapaValores.containsKey(rsb.getCodigo())) {
							valorSueldosBasico += mapaValores.get(
									rsb.getCodigo()).doubleValue();
						}
					}
					valorSueldosBasico = utilitariosLocal
							.redondeoValor(valorSueldosBasico);
					valorSueldosBasicoBD = new BigDecimal(valorSueldosBasico);

					Double valorAportesPersonalIess = 0.0;
					BigDecimal valorAportesPersonalIessBD = BigDecimal.ZERO;
					if (mapaValores.containsKey(rubroIess.getCodigo())) {
						valorAportesPersonalIess = mapaValores.get(
								rubroIess.getCodigo()).doubleValue();
					}
					valorAportesPersonalIess = utilitariosLocal
							.redondeoValor(valorAportesPersonalIess);
					valorAportesPersonalIessBD = new BigDecimal(
							valorAportesPersonalIess);

					// Se carga los impuestos a la renta para empleados
					Map<String, Object> mapaImpuestos = new HashMap<String, Object>();
					Calendar calFechaInicioImpuesto = new GregorianCalendar();
					calFechaInicioImpuesto
							.setTimeInMillis(gcUltimoDiaMesRolPago
									.getTimeInMillis());
					mapaImpuestos.put("fechaInicio", new Date(
							calFechaInicioImpuesto.getTimeInMillis()));
					Collection<ImpuestoRentaPersNatIf> impuestosRenta = impuestoRentaLocal
							.findImpuestoRentaPersNatByQueryByFechaInicioByFechFin(mapaImpuestos);
					ImpuestoRentaPersNatIf impuesto = buscarImpuestoRentaByValorByFecha(
							impuestosRenta, baseImponible,
							fechaPrimerDiaRolPago);

					Object[] filaNueva = new Object[12];
					filaNueva[0] = filaAnterior[0]; // anio
					filaNueva[1] = filaAnterior[1]; // mes
					filaNueva[2] = filaAnterior[2]; // nombres
					filaNueva[3] = filaAnterior[3]; // apellidos
					filaNueva[4] = filaAnterior[4]; // fechaInicio
					filaNueva[5] = valorSueldosBasicoBD; // Sueldo Basico
					filaNueva[6] = valorAportesPersonalIessBD; // Aporte
																// PersonalIess
					filaNueva[7] = totalGastosBD; // Gastos Deducibles
					filaNueva[8] = baseImponible; // Baseimponible
					filaNueva[9] = impuesto.getImpFraccionBasica(); // Imp.
																	// Fraccion
																	// Basica
					filaNueva[10] = impuesto.getPorcentajeImpFraccionBasica(); // Porcentaje
					filaNueva[11] = valorDetalle; // valor detalle

					resultadoImpuestoRenta.add(filaNueva);
				}

				return resultadoImpuestoRenta;
			}

			return resultado;

		} catch (GenericBusinessException e) {
			e.printStackTrace();
			throw new GenericBusinessException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericBusinessException("Error al consultar Rubros");
		}
	}

	private String generarStringRolesPago(Collection<RolPagoIf> rolesPago,
			String queryString, String tabla, String campo) {
		if (rolesPago == null || rolesPago.size() == 0)
			return queryString;
		queryString += " and (";
		for (RolPagoIf rp : rolesPago) {
			queryString += " " + tabla + "." + campo + " = " + rp.getId()
					+ " or";
		}
		queryString = queryString.substring(0, queryString.length() - 2);
		queryString += " )";
		return queryString;
	}

	public Collection<RolPagoIf> getRolesPagoByFechaInicioByFechaFin(
			Long tipoContratoId, Long tipoRolId, Calendar calInicio,
			Calendar calFin) throws GenericBusinessException {
		if (calInicio == null && calFin == null)
			return null;

		Collection<String> listaMesesAnio = getFechasParaRolPagoByFechaInicioByFechaFin(calInicio, calFin);
		Collection<RolPagoIf> rolesPago = new ArrayList<RolPagoIf>();
		for (String linea : listaMesesAnio) {
			String mes = linea.split("-")[1];
			String anio = linea.split("-")[3];
			Map<String, Object> mapaRol = new HashMap<String, Object>();
			if (tipoRolId != null)
				mapaRol.put("tiporolId", tipoRolId);
			if (tipoContratoId != null)
				mapaRol.put("tipocontratoId", tipoContratoId);
			mapaRol.put("mes", mes);
			mapaRol.put("anio", anio);
			Collection<RolPagoIf> roles = findRolPagoByQuery(mapaRol);
			if (tipoRolId != null && tipoContratoId != null) {
				if (roles.size() == 1) {
					RolPagoIf rp = roles.iterator().next();
					rolesPago.add(rp);
				}
			} else {
				for (RolPagoIf rp : roles) {
					rolesPago.add(rp);
				}
			}
		}
		return rolesPago;
	}

	public Collection findBeneficiosByRolPagoByContratoByEmpresaId(ContratoIf contratoIf,Boolean conFechaDeTipoRol,Long empresaId)
			throws GenericBusinessException {

		Collection<Object[]> resultado = new ArrayList<Object[]>();

		Collection<TipoRolIf> tipoRolBeneficios = new ArrayList<TipoRolIf>();

		TipoParametroIf tp = utilitariosLocal.getTipoParametro(empresaId,NominaParametros.TIPO_PARAMETRO);
		if (tp != null) {
			Collection<ParametroEmpresaIf> pes = utilitariosLocal.getParametroEmpresa(
				empresaId,tp,true,NominaParametros.BASE_CODIGO_TIPO_ROL_BENEFICIOS_SOCIALES_PARA_REPORTE);
			if (pes.size() > 0) {
				if (pes.size() == 0)
					throw new GenericBusinessException("No existe Parametros de Empresa cuyo codigo empiexen con codigo "+ NominaParametros.BASE_CODIGO_TIPO_ROL_BENEFICIOS_SOCIALES_PARA_REPORTE);
				for (ParametroEmpresaIf pe : pes) {
					Collection<TipoRolIf> tiposRubros = tipoRolLocal.findTipoRolByCodigo(pe.getValor());
					if (tiposRubros.size() == 1) {
						tipoRolBeneficios.add(tiposRubros.iterator().next());
					}
				}
			}
		} else {
			throw new GenericBusinessException("No exite Tipo de Parametro: "+NominaParametros.TIPO_PARAMETRO + "");
		}

		Collection<Object[]> resultadoModificado = new ArrayList<Object[]>();

		for (TipoRolIf tipoRolIf : tipoRolBeneficios) {

			if (tipoRolIf.getFechaInicio() == null)
				throw new GenericBusinessException(tipoRolIf.getNombre()+ " no tiene establecida la Fecha Inicio !!");

			if (tipoRolIf.getFechaFin() == null)
				throw new GenericBusinessException(tipoRolIf.getNombre()+ " no tiene establecida la Fecha Fin !!");

			Calendar calActual = new GregorianCalendar();
			int anioPresente = calActual.get(Calendar.YEAR);
			
			Date fechaInicio = tipoRolIf.getFechaInicio();
			Calendar calInicio = new GregorianCalendar();
			calInicio.setTime(fechaInicio);
			
			Date fechaFin = tipoRolIf.getFechaFin();
			Calendar calFin = new GregorianCalendar();
			calFin.setTime(fechaFin);

			if (!conFechaDeTipoRol){
				calInicio.set(Calendar.YEAR, anioPresente);
				calFin.set(Calendar.YEAR, anioPresente);
			}
			
			int mesInicio = calInicio.get(Calendar.MONTH);
			int anioInicio = calInicio.get(Calendar.YEAR);
			int mesFin = calFin.get(Calendar.MONTH);
			int anioFin = calFin.get(Calendar.YEAR);
			if ( mesFin < mesInicio && anioInicio == anioFin ){
				calInicio.set(Calendar.YEAR,anioInicio-1);
			}

			Map<String, Object> mapa = new HashMap<String, Object>();

			String queryString = " select rpd.contratoId,sum(rpd.valor) from RolPagoDetalleEJB rpd "
				+ " where rpd.contratoId = :contratoId ";

			Collection<RolPagoIf> rolesPago = getRolesPagoByFechaInicioByFechaFin(
				contratoIf.getTipocontratoId(), tipoRolIf.getId(),calInicio, calFin);
			if (rolesPago.size() > 0) {
				queryString = generarStringRolesPago(rolesPago, queryString,"rpd", "rolpagoId");
				queryString += " group by rpd.contratoId ";

				Query query = manager.createQuery(queryString);
				query.setParameter("contratoId", contratoIf.getId());

				resultado = query.getResultList();

				for (Object[] fila : resultado) {
					Object[] filaNueva = new Object[5];
					filaNueva[0] = tipoRolIf.getNombre();
					filaNueva[1] = utilitariosLocal.getFechaMesAnioUppercase(fechaInicio);
					filaNueva[2] = utilitariosLocal.getFechaMesAnioUppercase(fechaFin);
					filaNueva[3] = "";
					BigDecimal total = (BigDecimal) fila[1];
					filaNueva[4] = utilitariosLocal.redondeoValor(total.doubleValue());
					resultadoModificado.add(filaNueva);
				}

				Map<String, Object> mapaDetalle = new HashMap<String, Object>();
				mapaDetalle.put("contratoId", contratoIf.getId());

				for (RolPagoIf rolPagoDetalle : rolesPago) {
					mapaDetalle.put("rolpagoId", rolPagoDetalle.getId());
					Collection<RolPagoDetalleIf> detalle = rolPagoDetalleLocal.findRolPagoDetalleByQuery(mapaDetalle);
					for (RolPagoDetalleIf rpd : detalle) {
						Object[] filaDetalle = new Object[5];
						filaDetalle[0] = "";
						String mes = utilitariosLocal.getNombreMes(rolPagoDetalle.getMes());
						filaDetalle[1] = mes;
						filaDetalle[2] = rolPagoDetalle.getAnio();
						filaDetalle[3] = EstadoRolPagoDetalle.getEstadoRolPagoDetalleByLetra(rpd.getEstado()).toString();
						BigDecimal total = rpd.getValor();
						filaDetalle[4] = utilitariosLocal.redondeoValor(total.doubleValue());
						resultadoModificado.add(filaDetalle);
					}
				}
			}

		}

		return resultadoModificado;
	}
	
	public Collection getYearList() throws GenericBusinessException {
		//SELECT DISTINCT ANIO FROM ROL_PAGO ORDER BY ANIO DESC
		String queryString = "select distinct rp.anio from RolPagoEJB rp order by rp.anio desc";
		Query query = manager.createQuery(queryString);
		return query.getResultList();
	}
	
	public void enviarEmail(Long idEmpresa, EmpleadoIf empleado, String mes, String anio, 
			String absolutePath, FileInputStreamSerializable attachment) throws GenericBusinessException {
		
		try {
			Session mailSession = createMailSession(idEmpresa);
			MailMessage mailMessage = new MailMessage();
			mailMessage.setFrom((String) mailSession.getProperty("mail.smtp.user"));
			//mailMessage.setFrom("tecnico@creacional.com");
			mailMessage.setSubject("ROL DE PAGO - " + mes + " " + anio);
			mailMessage.setBody("Estimado(a) " + empleado.getNombres() + " " + empleado.getApellidos() + "\n\n" + "Adjunto en formato PDF encontrará su rol de pago correspondiente a " + mes + " " + anio);
			mailMessage.setTo(empleado.getEmailOficina());
			//File attachment = new File(absolutePath+"/"+empleado.getCodigo()+".pdf");
			//attachment.createNewFile();
			
			absolutePath = absolutePath + "tmp/";
			
			FileInputStreamSerializable archivo = (FileInputStreamSerializable)attachment;
			try{
				if(archivo != null)
					fileManagerLocal.guardarArchivo(archivo, absolutePath, archivo.getNombreArchivo());
			
			} catch(Exception exa){
				exa.printStackTrace();
				System.out.println("No se guardo archivo con nombre: " + archivo.getNombreArchivo());
			}
			
			File adjunto = new File(absolutePath+"/"+empleado.getCodigo()+".pdf");
			adjunto.createNewFile();
						
			mailMessage.addAttachments(adjunto);
			
			MailerImpl mi = new MailerImpl();
			mi.setMailSession(mailSession);
			mi.sendMailMessage(mailMessage);
			
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
		} catch (NoSuchProviderException ex1) {
			ex1.printStackTrace();
		} catch (MessagingException ex2) {
			ex2.printStackTrace();
		} catch (MailerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Session createMailSession(Long idEmpresa) throws GenericBusinessException {
		Session mailSession = null;
		Map parameterMap = new HashMap();
		parameterMap.put("codigo", "SMTP");
		parameterMap.put("empresaId", idEmpresa);
		Iterator<TipoParametroIf> parameterTypeIt = tipoParametroLocal.findTipoParametroByQuery(parameterMap).iterator();
		if (parameterTypeIt.hasNext()) {
			TipoParametroIf parameterType = parameterTypeIt.next();
			Iterator<ParametroEmpresaIf> smtpParametersIt = parametroEmpresaLocal.findParametroEmpresaByTipoparametroId(parameterType.getId()).iterator();
			Map<String, Object> smtpParametersMap = new HashMap<String, Object>();
			while (smtpParametersIt.hasNext()) {
				ParametroEmpresaIf parameter = smtpParametersIt.next();
				if (!parameter.getCodigo().equals("PDF_TMP_DIR"))
					smtpParametersMap.put(parameter.getCodigo(), parameter.getValor());
			}
			//EmpleadoIf empleado = SessionServiceLocator.getEmpleadoSessionService().getEmpleado(((UsuarioIf) Parametros.getUsuarioIf()).getEmpleadoId());
			//smtpParametersMap.put("SMTP_AUTH_USER", empleado.getEmailOficina());
			
			mailSession = MailerImpl.createMailSession(smtpParametersMap);
		}
		return mailSession;
	}
	
	/*public String enviarRolViaEmail(RolPagoIf rolPagoIf, Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo, 
			TipoContratoIf tipoContratoIf, EmpresaIf empresa, OficinaIf oficina, CiudadIf ciudad, 
			String usuario, String mes, TipoRolIf tipoRolIf, String rutaCarpetaReportes, 
			Map<Long, EmpleadoIf> empleadosMap, String anio, 
			Vector<ContratoIf> contratosSeleccionados) throws GenericBusinessException {
		
		String mensaje = "No ha sido posible el envio de correo.";
		
		Session mailSession = null;
		try {
			Map queryMap = new HashMap();
			queryMap.put("empresaId", empresa.getId());
			queryMap.put("codigo", "PDF_TMP_DIR");
			Iterator<ParametroEmpresaIf> it = parametroEmpresaLocal.findParametroEmpresaByQuery(queryMap).iterator();
			if (it.hasNext()) {
				String directorioTemporalPDF = it.next().getValor();
				mailSession = createMailSession(empresa.getId());
				if ( rolPagoIf != null) {			
					if ( rolPagoDetalleMapaTodo==null || rolPagoDetalleMapaTodo.size() == 0){
						mensaje = "Rol de pago no tiene detalle.";
						return mensaje;
					}
					String fileName = "jaspers/nomina/RPRolPagoContratoPorPagina.jasper";
					HashMap parametrosMap = new HashMap();
					parametrosMap.put("codigoReporte", "NOMI.T.REM");
					parametrosMap.put("empresa", empresa.getNombre());
					parametrosMap.put("ruc", empresa.getRuc());
					parametrosMap.put("urlLogoEmpresa", empresa.getLogo());
					parametrosMap.put("ciudad", ciudad.getNombre());
					parametrosMap.put("usuario", usuario);
					parametrosMap.put("mes", mes);
					parametrosMap.put("anio", rolPagoIf.getAnio());
					if ( tipoContratoIf.getNombre().contains("PROFESIONAL") ){
						parametrosMap.put("tituloRol","L I Q U I D A C I Ó N");
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre().replace("ROL", "").replace("DE", ""));
					}
					else if ( tipoContratoIf.getNombre().contains("DEPENDENCIA") ){
						parametrosMap.put("tituloRol","R O L   D E   P A G O   P O R   C O N T R A T O");
						parametrosMap.put("nombreTipoRol", tipoRolIf.getNombre());
					}

					if (rutaCarpetaReportes != null && !"".equals(rutaCarpetaReportes) ){
						parametrosMap.put("pathSubreportEgresos", rutaCarpetaReportes+"/"+"jaspers/nomina/RPRolPagoContratoDetalleEgresos.jasper");
						parametrosMap.put("pathSubreportIngresos", rutaCarpetaReportes+"/"+"jaspers/nomina/RPRolPagoContratoDetalleIngresos.jasper");
					}				
					else 
						throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa !!");

					
					for(ContratoIf contrato : contratosSeleccionados){
						EmpleadoIf empleado = empleadosMap.get(contrato.getEmpleadoId());						
						Collection contratoCollection = transformarContratoCollection(contrato, rolPagoDetalleMapaTodo);
						JRDataSource dataSourceDetail = new JRBeanCollectionDataSource(contratoCollection);
						File directorio = new File(directorioTemporalPDF + mes + "-" + anio);
						directorio.mkdir();
						processReportToPDF(fileName, parametrosMap, dataSourceDetail, directorio.getAbsolutePath()+"/"+empleado.getCodigo()+".pdf", rutaCarpetaReportes);
						contratoCollection = null;
						if (empleado.getEmailOficina() != null && !empleado.getEmailOficina().equals("")) {
							MailMessage mailMessage = new MailMessage();
							mailMessage.setFrom((String) mailSession.getProperty("mail.smtp.user"));
							//mailMessage.setFrom("tecnico@creacional.com");
							mailMessage.setSubject("ROL DE PAGO - " + mes + " " + anio);
							mailMessage.setBody("Estimado(a) " + empleado.getNombres() + " " + empleado.getApellidos() + "\n\n" + "Adjunto en formato PDF encontrará su rol de pago correspondiente a " + mes + " " + anio);
							mailMessage.setTo(empleado.getEmailOficina());
							File attachment = new File(directorio.getAbsolutePath()+"/"+empleado.getCodigo()+".pdf");
							attachment.createNewFile();
							mailMessage.addAttachments(attachment);
							MailerImpl mi = new MailerImpl();
							mi.setMailSession(mailSession);
							mi.sendMailMessage(mailMessage);
						} else {
							mensaje = "No se ha podido enviar email a: " + empleado.getNombres() + " " + empleado.getApellidos() + "";
						}
					}				
					
					mensaje = "Envío realizado exitosamente";
				}
			} else {
				mensaje = "No se ha definido directorio temporal para archivos PDF";
			}
		} catch ( GenericBusinessException e ) {
			e.printStackTrace();
		} catch (NoSuchProviderException ex) {
			ex.printStackTrace();
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch( Exception e ) {
			e.printStackTrace();
		}	
		
		return mensaje;
	}*/
	
	/*public static void processReportToPDF(String fileName, HashMap<String, Object> parametrosMap, 
			JRDataSource dataSource, String file, String rutaCarpetaReportes) throws GenericBusinessException {
		JasperPrint jasperPrint = null;
		//JRRtfExporter jrRtfExporter = new JRRtfExporter();
		try {
			if (rutaCarpetaReportes != null && !"".equals(rutaCarpetaReportes) )
				jasperPrint = JasperFillManager.fillReport(
						ClassLoader.getSystemResourceAsStream(rutaCarpetaReportes + "/" + fileName),
						parametrosMap,
						dataSource);
			else
				throw new GenericBusinessException("La ruta del directorio de Reportes no está establecida en Parametros de Empresa.");
				
			JasperExportManager.exportReportToPdfFile(jasperPrint, file);
		} catch (JRException e) {
			throw new GenericBusinessException("Se ha producido un error al generar el reporte.");
		}
	}*/
	
	/*private Collection transformarContratoCollection(ContratoIf contrato, Map<Long,RolPagoContratoDatos> rolPagoDetalleMapaTodo) {
		Collection<RolPagoContratoDatos> coleccion = new ArrayList<RolPagoContratoDatos>();
		if (rolPagoDetalleMapaTodo!=null){
			RolPagoContratoDatos datoContrato = rolPagoDetalleMapaTodo.get(contrato.getId());
			if (datoContrato != null) {
				Collection<RubroContratoDatos> ingresos = datoContrato.getIngresosRubro();
				JRBeanCollectionDataSource ingresosBeans = new JRBeanCollectionDataSource(ingresos);
				datoContrato.setIngresos(ingresosBeans);
				ingresos = null;
				Collection<RubroContratoDatos> egresos = datoContrato.getEgresosRubro();
				JRBeanCollectionDataSource egresosBeans = new JRBeanCollectionDataSource(egresos);
				datoContrato.setEgresos(egresosBeans);
				egresos = null;
				coleccion.add(datoContrato);
				Collections.sort((ArrayList<RolPagoContratoDatos>)coleccion, comparadorNombre);
			}
		}
		return coleccion;
	}
	
	Comparator<RolPagoContratoDatos> comparadorNombre = new Comparator<RolPagoContratoDatos>(){
		public int compare(RolPagoContratoDatos o1, RolPagoContratoDatos o2) {
			return o1.getNombreEmpleado().compareTo(o2.getNombreEmpleado());
		}
	};*/
}
