package com.spirit.sri.gui.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.spirit.client.Parametros;
import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.entity.TipoIdentificacionIf;
import com.spirit.general.entity.TipoParametroIf;
import com.spirit.general.session.ParametroEmpresaSessionService;
import com.spirit.general.session.TipoIdentificacionSessionService;
import com.spirit.general.session.TipoParametroSessionService;
import com.spirit.servicelocator.ServiceLocator;
import com.spirit.sri.entity.SriAirIf;
import com.spirit.sri.entity.SriIdentifTransaccionIf;
import com.spirit.sri.entity.SriIvaBienIf;
import com.spirit.sri.entity.SriIvaIf;
import com.spirit.sri.entity.SriIvaServicioIf;
import com.spirit.sri.entity.SriSustentoTributarioIf;
import com.spirit.sri.entity.SriTipoComprobanteIf;
import com.spirit.sri.entity.SriTipoTransaccionIf;
import com.spirit.sri.session.SriAirSessionService;
import com.spirit.sri.session.SriIdentifTransaccionSessionService;
import com.spirit.sri.session.SriIvaBienSessionService;
import com.spirit.sri.session.SriIvaServicioSessionService;
import com.spirit.sri.session.SriIvaSessionService;
import com.spirit.sri.session.SriSustentoTributarioSessionService;
import com.spirit.sri.session.SriTipoComprobanteSessionService;
import com.spirit.sri.session.SriTipoTransaccionSessionService;

public class IniciarMapas {
	
	public static HashMap<String, String> cargarSustentoTributario(){
		HashMap<String, String> mapaSustentoTributario = new HashMap<String, String>();
		try {
			Collection sustentos = SessionServiceLocator.getSustentoTributarioSessionService().getSriSustentoTributarioList();
			for(Iterator itSustentos = sustentos.iterator();itSustentos.hasNext();){
				SriSustentoTributarioIf sustento = (SriSustentoTributarioIf)itSustentos.next();
				mapaSustentoTributario.put(sustento.getCodigo(), sustento.getDescripcion());
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("No se puede cargar los Sustentos Tributarios", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return mapaSustentoTributario;
	}

	public static HashMap<Integer, Integer> cargarCodigosIva(){
		HashMap<Integer, Integer> mapaCodigoIva = new HashMap<Integer, Integer>();
		Calendar calendario = new GregorianCalendar();
		java.util.Date fechaU = calendario.getTime();
		Date fecha = new Date(fechaU.getTime());
		try {
			Collection<SriIvaIf> ivas = SessionServiceLocator.getSriIvaSessionService().getIvaByFecha(fecha);
			for (Iterator itIvas = ivas.iterator();itIvas.hasNext();){
				SriIvaIf iva = (SriIvaIf) itIvas.next(); 
				mapaCodigoIva.put(iva.getPorcentaje(), iva.getCodigo());
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede cargar los c\u00f3digos de Iva", SpiritAlert.ERROR);
		}
		/*mapaCodigoIva.put(0, 0);
		mapaCodigoIva.put(10, 1);
		mapaCodigoIva.put(12, 2);
		mapaCodigoIva.put(14, 3);*/
		return mapaCodigoIva;
	}

	public static HashMap<String, SriIvaBienIf> cargarCodigosIvaBien(){
		HashMap<String, SriIvaBienIf> mapaCodigoIvaBien = new HashMap<String, SriIvaBienIf>();
		try {
			Collection ivasBien = SessionServiceLocator.getSriIvaBienSessionService().getSriIvaBienList();
			for(Iterator itIvaBien=ivasBien.iterator();itIvaBien.hasNext();){
				SriIvaBienIf ivaBien = (SriIvaBienIf) itIvaBien.next();
				mapaCodigoIvaBien.put(ivaBien.getDescripcionPorcentaje(), ivaBien);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede cargar los c\u00f3digos de Iva Bien", SpiritAlert.ERROR);
		}
		return mapaCodigoIvaBien;
	}

	public static HashMap<String, SriIvaServicioIf> cargarCodigosIvaServicio(){
		HashMap<String, SriIvaServicioIf> mapaCodigoIvaServicio = new HashMap<String, SriIvaServicioIf>();
		try {
			Collection ivasServicio = SessionServiceLocator.getSriIvaServicioSessionService().getSriIvaServicioList();
			for(Iterator itIvaServicio=ivasServicio.iterator();itIvaServicio.hasNext();){
				SriIvaServicioIf ivaBien = (SriIvaServicioIf) itIvaServicio.next();
				mapaCodigoIvaServicio.put(ivaBien.getDescripcionPorcentaje(), ivaBien);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede cargar los c\u00f3digos de Iva Bien", SpiritAlert.ERROR);
		}
		return mapaCodigoIvaServicio;
	}

	public static HashMap<Double, Integer> cargarCodigosIce(){
		HashMap<Double, Integer> mapaCodigoIce = new HashMap<Double, Integer>();
		mapaCodigoIce.put(0.0, 0);
		mapaCodigoIce.put(77.25, 1);
		mapaCodigoIce.put(18.54, 2);
		mapaCodigoIce.put(30.9, 3);
		mapaCodigoIce.put(10.3, 4);
		mapaCodigoIce.put(26.78, 5);
		mapaCodigoIce.put(5.15, 6);
		mapaCodigoIce.put(10.3, 7);
		mapaCodigoIce.put(15.0, 8);
		return mapaCodigoIce;
	}

	public static Object[] cargarCodigoTipoComprobantes(){
		Object[] objetos = new Object[2];
		HashMap<String, String> mapaTipoComprobantes = new HashMap<String, String>();
		HashMap<Long, SriTipoComprobanteIf> mapaCodigoTipoComprobantes = new HashMap<Long, SriTipoComprobanteIf>();
		try {
			Collection<SriTipoComprobanteIf> tipos = SessionServiceLocator.getSriTipoComprobanteSessionService().getSriTipoComprobanteList();
			for (Iterator itTipos = tipos.iterator();itTipos.hasNext();){
				SriTipoComprobanteIf tipo = (SriTipoComprobanteIf) itTipos.next();
				mapaTipoComprobantes.put(tipo.getCodigo(), tipo.getNombre());
				mapaCodigoTipoComprobantes.put(tipo.getId(), tipo);
			}
			objetos[0]= mapaTipoComprobantes;
			objetos[1]= mapaCodigoTipoComprobantes;
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede cargar los c\u00f3digos de Tipos de Comprobantes", SpiritAlert.ERROR);
		}
		return objetos;
	}

	public static HashMap<Long, TipoIdentificacionIf> cargarCodigoIdentificacion(){
		HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion = new HashMap<Long, TipoIdentificacionIf>();
		try {
			Collection<TipoIdentificacionIf> tipos = SessionServiceLocator.getTipoIdentificacionSessionService().getTipoIdentificacionList();
			for (Iterator itTipos = tipos.iterator();itTipos.hasNext();){
				TipoIdentificacionIf tipo = (TipoIdentificacionIf)itTipos.next();
				mapaCodigoIdentificacion.put(tipo.getId(), tipo);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede cargar los c\u00f3digos de los Tipos de Identificaci\u00f3n", SpiritAlert.ERROR);
		}
		return mapaCodigoIdentificacion;
	}

	public static HashMap<String, SriTipoTransaccionIf> cargarTipoTransacciones(){
		HashMap<String, SriTipoTransaccionIf> mapaCodigoTransaccion = new HashMap<String, SriTipoTransaccionIf>();
		try {
			Collection<SriTipoTransaccionIf> transacciones = SessionServiceLocator.getSriTipoTransaccionSessionService().getSriTipoTransaccionList();
			for (Iterator itTransacciones = transacciones.iterator();itTransacciones.hasNext();){
				SriTipoTransaccionIf transaccion = (SriTipoTransaccionIf) itTransacciones.next();
				mapaCodigoTransaccion.put(transaccion.getNombre(), transaccion);
			}
		} catch (GenericBusinessException e) {
			e.printStackTrace();
			SpiritAlert.createAlert("No se puede cargar los Tipos de Transacciones", SpiritAlert.ERROR);
		}
		return mapaCodigoTransaccion;
	}

	public static Object[] cargarCodigosTipoIdentificacion(String nombreTransaccion,
			HashMap<String, SriTipoTransaccionIf> mapaCodigoTransaccion,HashMap<Long, TipoIdentificacionIf> mapaCodigoIdentificacion){

		SriTipoTransaccionIf transaccion = mapaCodigoTransaccion.get(nombreTransaccion);
		if(transaccion!=null){
			HashMap<String, String> mapaCodigoTipoIdentificacionXCodigoSpirit = new HashMap<String, String>();
			HashMap<String, String> mapaCodigoTipoIdentificacionXCodigoSri = new HashMap<String, String>();
			try {
				Collection<SriIdentifTransaccionIf> tipos = SessionServiceLocator.getSriIdentifTransaccionSessionService().findSriIdentifTransaccionByIdTipoTransaccion(transaccion.getId());
				for (Iterator itTipos = tipos.iterator();itTipos.hasNext();){
					SriIdentifTransaccionIf identificacion = (SriIdentifTransaccionIf)itTipos.next();
					TipoIdentificacionIf tipoIdentificacion = mapaCodigoIdentificacion.get(identificacion.getIdTipoIdentificacion());
					mapaCodigoTipoIdentificacionXCodigoSpirit.put(tipoIdentificacion.getCodigo(), identificacion.getCodigo());
					mapaCodigoTipoIdentificacionXCodigoSri.put(identificacion.getCodigo(), tipoIdentificacion.getNombre());
				}
				Object[] mapas = new Object[]{mapaCodigoTipoIdentificacionXCodigoSpirit,mapaCodigoTipoIdentificacionXCodigoSri};
				return mapas;

			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("No se puede cargar los codigos de Identificaciones para "+nombreTransaccion, SpiritAlert.ERROR);
			}

		} else
			SpiritAlert.createAlert("No existe Tipo de Transacci\u00f3n \""+nombreTransaccion+"\"", SpiritAlert.ERROR);
		return null;
	}
	/*private void cargarCodigosTipoIdentificacion(HashMap<String, SriTipoTransaccionIf> mapaCodigoTransaccion,String nombreTransaccion, HashMap<String,String> mapa){
		SriTipoTransaccionIf transaccion = mapaCodigoTransaccion.get(nombreTransaccion);
		if(transaccion!=null){
			try {
				Collection<SriIdentifTransaccionIf> tipos = getIdentificacionTransaccionSessionService().findSriIdentifTransaccionByIdTipoTransaccion(transaccion.getId());
				for (Iterator itTipos = tipos.iterator();itTipos.hasNext();){
					SriIdentifTransaccionIf identificacion = (SriIdentifTransaccionIf)itTipos.next();
					TipoIdentificacionIf tipoIdentificacion = mapaCodigoIdentificacion.get(identificacion.getIdTipoIdentificacion());
					mapa.put(tipoIdentificacion.getCodigo(), identificacion.getCodigo());
				}
			} catch (GenericBusinessException e) {
				e.printStackTrace();
				SpiritAlert.createAlert("No se puede cargar los codigos de Identificaciones para "+nombreTransaccion, SpiritAlert.ERROR);
			}
	
		} else
			SpiritAlert.createAlert("No existe Tipo de Transacci\u00f3n \""+nombreTransaccion+"\"", SpiritAlert.ERROR);
	}*/

	public static HashMap<String, Double> cargarImpuestos(){
		HashMap<String, Double> mapaImpuestos = new HashMap<String, Double>();
		try {
			Map mapa = new HashMap();
			mapa.put("empresaId", Parametros.getIdEmpresa());
			mapa.put("codigo", "IMPUESTOS");
			Collection tiposParametros = SessionServiceLocator.getTipoParametroSessionService().findTipoParametroByQuery(mapa);

			if ( tiposParametros.iterator().hasNext() ){
				TipoParametroIf tipoParametro = (TipoParametroIf)tiposParametros.iterator().next();

				Collection impuestosEmpresa = SessionServiceLocator.getParametroEmpresaSessionService().findParametroEmpresaByTipoparametroId(tipoParametro.getId());
				if (impuestosEmpresa.iterator().hasNext())
					for (Iterator itImpuestos = impuestosEmpresa.iterator();itImpuestos.hasNext();){
						ParametroEmpresaIf parametro = (ParametroEmpresaIf) itImpuestos.next();
						mapaImpuestos.put(parametro.getCodigo(), Double.valueOf(parametro.getValor()));
					}
				else
					SpiritAlert.createAlert("No existen Parametros de Impuestos", SpiritAlert.WARNING);
			} else{
				SpiritAlert.createAlert("No existe Tipo de Parametro con c\u00f3digo: \"IMP\"", SpiritAlert.WARNING);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("No se puede cargar los valores de los impuestos", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return mapaImpuestos;
	}
	
	public static HashMap<Long, SriAirIf> cargarAir(){
		HashMap<Long, SriAirIf> mapaAir = new HashMap<Long, SriAirIf>();
		try {
			Collection<SriAirIf> airs = SessionServiceLocator.getAirSessionService().getSriAirList();
			for (SriAirIf air : airs){
				mapaAir.put(air.getId(), air);
			}
		} catch (GenericBusinessException e) {
			SpiritAlert.createAlert("No se puede cargar los valores de los impuestos", SpiritAlert.ERROR);
			e.printStackTrace();
		}
		return mapaAir;
	}
/*
	//SESSIONS SERVICES
	private static SriIvaSessionService getIvaSessionService() {
		try {
			return (SriIvaSessionService) ServiceLocator.getService(ServiceLocator.SRIIVASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private static SriIvaBienSessionService getIvaBienSessionService() {
		try {
			return (SriIvaBienSessionService) ServiceLocator.getService(ServiceLocator.SRIIVABIENSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private static SriIvaServicioSessionService getIvaServicioSessionService() {
		try {
			return (SriIvaServicioSessionService) ServiceLocator.getService(ServiceLocator.SRIIVASERVICIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private static SriTipoComprobanteSessionService getTipoComprobanteSessionService() {
		try {
			return (SriTipoComprobanteSessionService) ServiceLocator.getService(ServiceLocator.SRITIPOCOMPROBANTESESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private static TipoIdentificacionSessionService getTipoIdentificacionSessionService() {
		try {
			return (TipoIdentificacionSessionService) ServiceLocator.getService(ServiceLocator.TIPOIDENTIFICACIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private static SriTipoTransaccionSessionService getTipoTransaccionSessionService() {
		try {
			return (SriTipoTransaccionSessionService) ServiceLocator.getService(ServiceLocator.SRITIPOTRANSACCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}

	private static SriIdentifTransaccionSessionService getIdentificacionTransaccionSessionService() {
		try {
			return (SriIdentifTransaccionSessionService) ServiceLocator.getService(ServiceLocator.SRIIDENTIFTRANSACCIONSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	private static TipoParametroSessionService getTipoParametroSessionService() {
		try {
			return (TipoParametroSessionService) ServiceLocator.getService(ServiceLocator.TIPOPARAMETROSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	private static ParametroEmpresaSessionService getParametroEmpresaSessionService() {
		try {
			return (ParametroEmpresaSessionService) ServiceLocator.getService(ServiceLocator.PARAMETROEMPRESASESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicaci\u00f3n con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	private static SriSustentoTributarioSessionService getSustentoTributarioSessionService() {
		try {
			return (SriSustentoTributarioSessionService) ServiceLocator.getService(ServiceLocator.SRISUSTENTOTRIBUTARIOSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	private static SriAirSessionService getAirSessionService() {
		try {
			return (SriAirSessionService) ServiceLocator.getService(ServiceLocator.SRIAIRSESSION_SERVICE);
		} catch (Exception e) {
			SpiritAlert.createAlert("Se ha producido un error de comunicación con el servidor", SpiritAlert.ERROR);
			e.printStackTrace();
			return null;
		}
	}*/
}
