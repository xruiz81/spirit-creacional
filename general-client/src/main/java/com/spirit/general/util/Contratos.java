package com.spirit.general.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.spirit.client.SpiritAlert;
import com.spirit.comun.util.SessionServiceLocator;
import com.spirit.exception.GenericBusinessException;
import com.spirit.nomina.entity.ContratoUtilidadIf;
import com.spirit.nomina.entity.RolPagoIf;
import com.spirit.nomina.entity.TipoContratoIf;
import com.spirit.nomina.entity.TipoRolIf;
import com.spirit.nomina.handler.TipoRol;

public class Contratos {

	public static Collection<Long> obtenerContratosId(TipoRol tipoRol,TipoContratoIf tipoContratoIf,RolPagoIf rolPagoIf,String estado)
	throws GenericBusinessException {
		Collection<Long> contratosId = null;
		
		Map<String, Object> mapaRolPagoDetalle =  new HashMap<String, Object>();
		mapaRolPagoDetalle.put("rolpagoId", rolPagoIf.getId());
		
		Map<String, Object> mapaContrato =  new HashMap<String, Object>();
		TipoRolIf tipoRolIf = SessionServiceLocator.getTipoRolSessionService().getTipoRol(rolPagoIf.getTiporolId());
		
		if (tipoRol == TipoRol.MENSUAL || tipoRol == TipoRol.QUINCENAL){
			//contratosId = getRolPagoDetalleSessionService().findRolPagoDetalleContratoIdByRolpagoIdNormal(rolPagoIf.getId(),estado);
			int mes = Integer.parseInt(rolPagoIf.getMes())-1;
			int anio = Integer.valueOf(rolPagoIf.getAnio());
			Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
			Date fechaMedia = new Date(calFechaMedia.getTime().getTime());
			int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
			calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
			Date fechaMediaMax = new Date(calFechaMedia.getTime().getTime());
			
			mapaContrato.put("fechaMediaContrato", fechaMedia);
			mapaContrato.put("fechaMediaContratoMax", fechaMediaMax);
			mapaContrato.put("tipocontratoId", tipoContratoIf.getId());
			contratosId = SessionServiceLocator.getRolPagoDetalleSessionService().findRolPagoDetalleContratoIdByMapByEstadosNormal(
					mapaRolPagoDetalle,mapaContrato,false,tipoRolIf,estado);
			
		} else if ( tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO
				|| tipoRol == TipoRol.APORTE_PATRONAL || tipoRol == TipoRol.FONDO_RESERVA
				|| tipoRol == TipoRol.VACACIONES  || tipoRol == TipoRol.UTILIDADES ){
			/*Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("rolpagoId", rolPagoIf.getId());
			contratosId = SessionServiceLocator.getRolPagoDetalleSessionService().findRolPagoDetalleContratoIdByQuery(mapa);*/
			
			
			Date fechaInicio = null;
			Date fechaFin = null;
			if ( tipoRol == TipoRol.UTILIDADES ){
				Long empresaId = tipoContratoIf.getEmpresaId();
				Calendar calRolPago = new GregorianCalendar();
				calRolPago.set(Calendar.MONTH, Integer.valueOf(rolPagoIf.getMes())-1 );
				calRolPago.set(Calendar.YEAR, Integer.valueOf(rolPagoIf.getAnio()) );
				calRolPago.set(Calendar.DAY_OF_MONTH, calRolPago.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date fechaRolPago = new Date(calRolPago.getTime().getTime());
				ContratoUtilidadIf cu = SessionServiceLocator.getContratoUtilidadSessionService()
					.buscarContratoUtilidad(empresaId, fechaRolPago);
				fechaInicio = cu.getFechaInicio();
				fechaFin = cu.getFechaFin();
			} else if(tipoRol == TipoRol.DECIMO_TERCERO || tipoRol == TipoRol.DECIMO_CUARTO) {				
				fechaInicio = tipoRolIf.getFechaInicio();
				fechaFin = tipoRolIf.getFechaFin();
				
				if ( fechaInicio == null ){
					throw new GenericBusinessException("Fecha de Inicio para Tipo de Rol no definido !!");
				}
				if ( fechaFin == null ){
					throw new GenericBusinessException("Fecha de Inicio para Tipo de Rol no definido !!");
				}
			} else {
				int mes = Integer.parseInt(rolPagoIf.getMes())-1;
				int anio = Integer.valueOf(rolPagoIf.getAnio());
				Calendar calFechaMedia = new GregorianCalendar(anio,mes,1);
				fechaInicio = new Date(calFechaMedia.getTime().getTime());
				int diaFinal = calFechaMedia.getActualMaximum(Calendar.DATE);
				calFechaMedia = new GregorianCalendar(anio,mes,diaFinal);
				fechaFin = new Date(calFechaMedia.getTime().getTime());
			}
			
			Calendar calFechaInicio = new GregorianCalendar();
			calFechaInicio.setTime(fechaInicio);
			System.out.println(calFechaInicio.get(Calendar.MONTH)+"-"+calFechaInicio.get(Calendar.YEAR));
			Calendar calFechaFin = new GregorianCalendar();
			calFechaFin.setTime(fechaFin);
			System.out.println(calFechaFin.get(Calendar.MONTH)+"-"+calFechaFin.get(Calendar.YEAR));
			
			//int diferenciaAnios = calFechaFin.get(Calendar.YEAR)-calFechaInicio.get(Calendar.YEAR);
			//calFechaInicio.set(Calendar.YEAR, anio-diferenciaAnios);
			//calFechaFin.set(Calendar.YEAR, anio);
			
			fechaInicio = new Date(calFechaInicio.getTime().getTime());
			fechaFin = new Date(calFechaFin.getTime().getTime());
			
			mapaContrato.put("fechaInicioProvision", fechaInicio);
			mapaContrato.put("fechaFinProvision", fechaFin);
			mapaContrato.put("tipocontratoId", tipoContratoIf.getId());
			//mapaContrato.put("estado", EstadoContrato.ACTIVO.getLetra());
			
			//System.out.println(fechaInicio);
			//System.out.println(fechaFin);
			contratosId = SessionServiceLocator.getRolPagoDetalleSessionService().findRolPagoDetalleContratoIdByMapByEstadosNormal(
					mapaRolPagoDetalle,mapaContrato,true,tipoRolIf,estado);
			
		} else{
			SpiritAlert.createAlert("No esta implementada búsqueda para el tipo de rol seleccionado", SpiritAlert.WARNING);
		}
		return contratosId;
	}

}
