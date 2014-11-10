package com.spirit.pos.session;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 
import com.spirit.contabilidad.entity.AsientoIf;
import com.spirit.contabilidad.entity.EventoContableIf;
import com.spirit.contabilidad.session.EventoContableSessionLocal;
import com.spirit.exception.GenericBusinessException;
import com.spirit.facturacion.handler.ComprobanteIngresoPosAsientoAutomaticoHandlerLocal;
import com.spirit.general.entity.CajaIf;
import com.spirit.general.entity.DocumentoIf;
import com.spirit.general.entity.EmpleadoIf;
import com.spirit.general.entity.UsuarioIf;
import com.spirit.general.mdb.messages.bo.AsientoPosMessageLocal;
import com.spirit.general.mdb.messages.bo.VentasConsolidadasMessageLocal;
import com.spirit.general.session.CajaSessionLocal;
import com.spirit.general.session.DocumentoSessionLocal;
import com.spirit.general.session.EmpleadoSessionLocal;

import com.spirit.general.session.UsuarioSessionLocal;
import com.spirit.general.session.UtilitariosSessionLocal;

import com.spirit.pos.entity.CajaSesionData;
import com.spirit.pos.entity.CajaSesionIf;
import com.spirit.pos.entity.CajasesionMovimientosIf;
import com.spirit.pos.entity.VentasConsolidadoData;
import com.spirit.pos.entity.VentasConsolidadoIf;
import com.spirit.pos.session.generated._CajaSesionSession;

import com.spirit.server.LogService;
import com.spirit.server.Logger;


/**
 * The <code>CajaSesionSession</code> session bean, which acts as a facade to
 * the underlying entity beans.
 * 
 * @author lmunoz@versality.biz
 * @version $Revision: 1.1 $, $Date: 2014/03/28 18:00:19 $
 * 
 */
@Stateless
public class CajaSesionSessionEJB  extends _CajaSesionSession implements CajaSesionSessionRemote,
		CajaSesionSessionLocal {
	
	@PersistenceContext(unitName = "spirit")
	private EntityManager manager;

	@EJB
	private CajaSessionLocal cajaSessionLocal;

	@EJB
	private UsuarioSessionLocal usuarioSessionLocal;

	@EJB
	private EmpleadoSessionLocal empleadoSessionLocal;

	@EJB
	private VentasConsolidadasMessageLocal ventasConsolidadasMessageLocal;
	
	@EJB
	private AsientoPosMessageLocal asientoPosMessageLocal;

	@EJB
	private VentasConsolidadoSessionLocal ventasConsolidadoSessionLocal;

	@EJB
	private CajasesionMovimientosSessionLocal cajasesionMovimientosSessionLocal;

	@EJB
	private DocumentoSessionLocal documentoLocal;

	@EJB
	private EventoContableSessionLocal eventoContableLocal;

	@EJB
	private ComprobanteIngresoPosAsientoAutomaticoHandlerLocal comprobanteIngresoPosAsientoAutomaticoHandlerLocal;

	@EJB
	private UtilitariosSessionLocal utilitariosSessionLocal;

	/**
	 * The logger object.
	 */
	private static Logger log = LogService
			.getLogger(CajaSesionSessionEJB.class);

	/***************************************************************************
	 * B U S I N E S S M E T H O D S
	 * 
	 * @throws GenericBusinessException
	 **************************************************************************/


	private List getResumenDevolucionCaja(Long idCaja) {
		String queryString = "SELECT ventasDocumentos.tablaNombre,sum(factura.valor+factura.descuento+factura.iva) "+
			"from VentasDocumentosEJB ventasDocumentos,FacturaEJB factura,VentasTrackEJB ventasTrack "+
			"where ventasDocumentos.tablaId=factura.id and ventasDocumentos.ventastrackId = ventasTrack.id "+
			"and ventasTrack.cajasesionId = :idCaja "+" and ventasDocumentos.tablaNombre='DEVOLUCION' "+
			"group by ventasDocumentos.tablaNombre";		
		Query query = manager.createQuery(queryString);
		query.setParameter("idCaja", idCaja);
		return query.getResultList();

	}

	
	
	
	private List getResumenCierreCaja(Long idCaja) {
		String queryString = "SELECT " + "tipoPago.codigo, "
				+ "sum(ventasPagos.valor) " + "from "
				+ "VentasTrackEJB ventasTrack, "
				+ "VentasPagosEJB ventasPagos, " + "TipoPagoEJB tipoPago "
				+ "where " + "ventasPagos.ventastrackId = ventasTrack.id and "
				+ "ventasPagos.tipo=tipoPago.id and "
				+
				// PARAMETROS
				"ventasTrack.cajasesionId = :idCaja " + "group by "
				+ "tipoPago.nombre";

		Query query = manager.createQuery(queryString);
		query.setParameter("idCaja", idCaja);
		return query.getResultList();

	}

	public void generarAsientos(HashMap<String, BigDecimal> mapaAsientos,
			Long idEmpresa, Long idOficina,String cajero) {
		for (String key : mapaAsientos.keySet()) {
			BigDecimal valor = mapaAsientos.get(key);
			if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
				generarAsientosFaltantesMultas(key, valor.toString(),
						idEmpresa, idOficina,cajero);
			}
		}		
		try {
			asientoPosMessageLocal.setData(mapaAsientos, idEmpresa, idOficina, cajero);
			asientoPosMessageLocal.sendToPrincipalIfPos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void activarCaja(CajaSesionIf cajaSesionif)
			throws GenericBusinessException {
		
		saveCajaSesion(cajaSesionif);
		
		CajaIf cajaIf = cajaSessionLocal.getCaja(cajaSesionif.getCajaId());
		UsuarioIf usuarioIf = usuarioSessionLocal.getUsuario(cajaSesionif.getUsuarioId());
		EmpleadoIf empleadoIf = empleadoSessionLocal.getEmpleado(usuarioIf.getEmpleadoId());
		
		VentasConsolidadoData ventasConsolidadoData = new VentasConsolidadoData();		
		ventasConsolidadoData.setFechaApertura(utilitariosSessionLocal.getServerDateTimeStamp());
		ventasConsolidadoData.setCajeroCedula(empleadoIf.getIdentificacion());
		ventasConsolidadoData.setCajeroNombre(empleadoIf.getNombres() + " "	+ empleadoIf.getApellidos());
		ventasConsolidadoData.setCajaCodigo(cajaIf.getCodigo());
		ventasConsolidadoData.setCajaNombre(cajaIf.getNombre());
		ventasConsolidadoData.setValorCajaInicial(cajaSesionif.getValorInicial());
		ventasConsolidadoData.setValorDocumentos(cajaSesionif.getValorDocumentos());
		ventasConsolidadoData.setValorDescuadre(cajaSesionif.getDescuadre());
		ventasConsolidadoData.setValorMultas(cajaSesionif.getValorMultas());
		ventasConsolidadoSessionLocal.addVentasConsolidado(ventasConsolidadoData);

		try {

			ventasConsolidadasMessageLocal.setVentaConsolidada(ventasConsolidadoData);
			ventasConsolidadasMessageLocal.sendToPrincipalIfPos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearCaja(CajaSesionIf cajasesionIf)
			throws GenericBusinessException {

		saveCajaSesion(cajasesionIf);
	}

	public void cerrarCaja(String cajero,CajaSesionIf cajaSesionIf,
			HashMap<String, BigDecimal> mapaAsientos, Long idEmpresa,
			Long idOficina) throws GenericBusinessException {
		// TODO: ALGO DE LOGICA DE CIERRE
		saveCajaSesion(cajaSesionIf);
		generarAsientos(mapaAsientos, idEmpresa, idOficina,cajero);
		// ENVIAR RESUMENES A PRINCIPAL

		CajaIf cajaIf = cajaSessionLocal.getCaja(cajaSesionIf.getCajaId());
		UsuarioIf usuarioIf = usuarioSessionLocal.getUsuario(cajaSesionIf.getUsuarioId());
		EmpleadoIf empleadoIf = empleadoSessionLocal.getEmpleado(usuarioIf.getEmpleadoId());

		List<CajasesionMovimientosIf> listaCajasesionMovimientosIf = (List) cajasesionMovimientosSessionLocal.findCajasesionMovimientosByCajasesionId(cajaSesionIf.getId());

		boolean isNuevo = false;
		VentasConsolidadoIf ventasConsolidadoData = new VentasConsolidadoData();

		try {
			ventasConsolidadoData = ventasConsolidadoSessionLocal.getVentasConsolidado(cajaIf.getCodigo(), empleadoIf.getIdentificacion());
			if (ventasConsolidadoData == null) {
				isNuevo = true;
				ventasConsolidadoData = new VentasConsolidadoData();
			}
		} catch (Exception e1) {
			isNuevo = true;
			ventasConsolidadoData = new VentasConsolidadoData();
			e1.printStackTrace();
		}
		BigDecimal ingresos = BigDecimal.ZERO;
		BigDecimal egresos = BigDecimal.ZERO;

		for (CajasesionMovimientosIf cajasesionMovimientosIf : listaCajasesionMovimientosIf) {
			if (cajasesionMovimientosIf.getTipomovimiento().equalsIgnoreCase(
					"I"))
				ingresos = ingresos.add(cajasesionMovimientosIf.getValor());
			else if (cajasesionMovimientosIf.getTipomovimiento()
					.equalsIgnoreCase("E"))
				egresos = egresos.add(cajasesionMovimientosIf.getValor());
		}

		ventasConsolidadoData.setValorCajaEgreso(egresos);
		ventasConsolidadoData.setValorCajaIngreso(ingresos);
		ventasConsolidadoData.setCajaCodigo(cajaIf.getCodigo());
		ventasConsolidadoData.setCajaNombre(cajaIf.getNombre());
		ventasConsolidadoData.setCajeroCedula(empleadoIf.getIdentificacion());
		ventasConsolidadoData.setCajeroNombre(empleadoIf.getNombres() + " "
				+ empleadoIf.getApellidos());

		ventasConsolidadoData.setValorCajaInicial(cajaSesionIf
				.getValorInicial());
		ventasConsolidadoData.setValorDocumentos(cajaSesionIf
				.getValorDocumentos());
		ventasConsolidadoData.setValorDescuadre(cajaSesionIf.getDescuadre());
		ventasConsolidadoData.setValorMultas(cajaSesionIf.getValorMultas());

		ventasConsolidadoData.setFechaCierre(utilitariosSessionLocal
				.getServerDateTimeStamp());

		List lista = getResumenCierreCaja(cajaSesionIf.getId());
		Object[] temp = null;
		for (int i = 0; i < lista.size(); i++) {
			temp = (Object[]) lista.get(i);
			String tipo = (String) temp[0];
			BigDecimal valor = (BigDecimal) temp[1];

			if (tipo.equalsIgnoreCase("EF")) {
				ventasConsolidadoData.setValorEfectivo(valor);
			} else if (tipo.equalsIgnoreCase("CR")) {
				ventasConsolidadoData.setValorCredito(valor);
			} else if (tipo.equalsIgnoreCase("TA")) {
				ventasConsolidadoData.setValorTarjeta(valor);
			} else if (tipo.equalsIgnoreCase("CH")) {
				ventasConsolidadoData.setValorCheque(valor);
			} else if (tipo.equalsIgnoreCase("DB")) {
				// ventasConsolidadoData.setValorDe(valor);
			} else if (tipo.equalsIgnoreCase("GC")) {
				ventasConsolidadoData.setValorGiftcards(valor);
			} else if (tipo.equalsIgnoreCase("DO")) {
				ventasConsolidadoData.setValorDonacion(valor);
			}
			else {
				System.out.println("TIPO DESCONOCIDO: " + tipo);
			}
		}
		
		
		
		/////aqui añadir esto para el valor de las devoluciones
		 
		List lista2 = getResumenDevolucionCaja(cajaSesionIf.getId());
		Object[] temp2 = null;
		System.out.println("AAA-<"+lista2.size());
		for (int i2 = 0; i2 < lista2.size(); i2++) {
			temp2 = (Object[]) lista2.get(i2);
			String tipo2 = (String) temp2[0];
			BigDecimal valor2 = (BigDecimal) temp2[1];
			if (tipo2.equalsIgnoreCase("DEVOLUCION")) {
				ventasConsolidadoData.setValorDevoluciones(valor2);
			} 
			else {
				System.out.println("nO HAY devoluciones");
			}
		}
		 
		/////////////////////////////////////
		
		/////////////////

		if (isNuevo) {
			ventasConsolidadoSessionLocal
					.addVentasConsolidado(ventasConsolidadoData);
		} else {
			ventasConsolidadoSessionLocal
					.saveVentasConsolidado(ventasConsolidadoData);
		}
		ventasConsolidadasMessageLocal.setVentaConsolidada(ventasConsolidadoData);
		try {
			ventasConsolidadasMessageLocal.sendToPrincipalIfPos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public AsientoIf generarAsientosFaltantesMultas(String tipo, String valor,
			Long idEmpresa, Long idOficina,String cajero) {
		AsientoIf asientoRetornar = null;
		DocumentoIf documentoIf = null;
		Map<String, Object> parameterMap = new HashMap<String, Object>();

		Map<String, Object> parametrosEmpresa = new HashMap<String, Object>();
		parametrosEmpresa.put("EMPRESA_ID", idEmpresa);
		parametrosEmpresa.put("OFICINA_ID", idOficina);

		parameterMap.clear();
		
		
		System.out.println("AJERO!!!!"+cajero);
		
		
		Iterator iterDocumento;
		try {
			iterDocumento = documentoLocal.findDocumentoByCodigo(tipo)
					.iterator();
			if (iterDocumento.hasNext()) {
				documentoIf = (DocumentoIf) iterDocumento.next();
			}

			if (documentoIf != null) {
				Map aMap = new HashMap();
				aMap.put("documentoId", documentoIf.getId());
				Long etapa = 1L;
				aMap.put("etapa", etapa);
				Iterator eventoContableIterator = eventoContableLocal
						.findEventoContableByQuery(aMap).iterator();

				if (eventoContableIterator.hasNext()) {
					EventoContableIf eventoContable = (EventoContableIf) eventoContableIterator
							.next();
					if (eventoContable != null) {
						parameterMap.clear();

						if (documentoIf.getCodigo().equals("FCA")) {
							parameterMap.put("CTAXCOB", new Double(valor).doubleValue());
							parameterMap.put("CAJA", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION", "FCA: FALTANTES DE CAJA "+cajero);
						}
						if (documentoIf.getCodigo().equals("MPP")) {
							parameterMap.put("CTAXCOB", new Double(valor).doubleValue());
							parameterMap.put("MULTA", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION","MPP: MULTAS POR PERDIDA DOC "+cajero);
						}
						if (documentoIf.getCodigo().equals("SDC")) {
							parameterMap.put("CTAXCOB", new Double(valor).doubleValue());
							parameterMap.put("CAJA", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION", "SDC: SOBRANTES DE CAJA ");
						}
						//////////////////////////////////////////////////////////////////////
						if (documentoIf.getCodigo().equals("ITM")) {
							parameterMap.put("CAJAING", new Double(valor).doubleValue());
							parameterMap.put("CAJAEGR", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION", "INGRESO DE MATRIZ AL MALL");
						}
						if (documentoIf.getCodigo().equals("IBM")) {
							parameterMap.put("CAJAING", new Double(valor).doubleValue());
							parameterMap.put("BCOEGR", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION", "IBM: INGRESOS DEL BANCO AL MALL");
						}
						if (documentoIf.getCodigo().equals("EMT")) {
							parameterMap.put("CAJAING", new Double(valor).doubleValue());
							parameterMap.put("CAJAING", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION", "EMT: EGRESOS MALL A MATRIZ");
						}
						if (documentoIf.getCodigo().equals("EMB")) {
							parameterMap.put("BCOING", new Double(valor).doubleValue());
							parameterMap.put("CAJAEGR", new Double(valor).doubleValue());
							parameterMap.put("OBSERVACION", "EMB: EGRESO MALL AL BANCO");
						}
						//////////////////////////////////////////////////////
						
						asientoRetornar = comprobanteIngresoPosAsientoAutomaticoHandlerLocal
								.generarFaltantesMultasSobrantesCajaAsientoAutomaticoHandler(
										eventoContable, parameterMap, true,
										parametrosEmpresa, etapa);

					}

				}
			}
		} catch (GenericBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return asientoRetornar;

	}
 
	 


	// ///////////////
}
