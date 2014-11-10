package com.spirit.nomina.session;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.spirit.exception.GenericBusinessException;
import com.spirit.general.entity.ParametroEmpresaIf;
import com.spirit.general.session.UtilitariosSessionLocal;
import com.spirit.nomina.entity.ContratoUtilidadIf;
import com.spirit.nomina.handler.NominaParametros;
import com.spirit.nomina.session.generated._ContratoUtilidadSession;

/**
 *
 * @author  www.versality.com.ec
 *
 */
@Stateless
public class ContratoUtilidadSessionEJB extends _ContratoUtilidadSession implements ContratoUtilidadSessionRemote,ContratoUtilidadSessionLocal  {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/
	
	@EJB private UtilitariosSessionLocal utilitariosLocal;
	
	public Map<String,Object> getInformacionUtilidades(Long empresaId,Date fechaUltimoDiaMesRolPago) throws GenericBusinessException{
		Map<String,Object> mapa = new HashMap<String, Object>();
		ContratoUtilidadIf cu = buscarContratoUtilidad(empresaId, fechaUltimoDiaMesRolPago);
		mapa.put("contratoUtilidadIf", cu);
		
		Integer anioUtilidad = obtenerAnioUtilidadParametroEmpresa(empresaId);
		
		Calendar calUtilidad = new GregorianCalendar();
		calUtilidad.setTime(new java.util.Date(fechaUltimoDiaMesRolPago.getTime()));
		if ( anioUtilidad < 0 )
			anioUtilidad = calUtilidad.get(Calendar.YEAR)+anioUtilidad;
		
		Calendar calInicio = new GregorianCalendar();
		calInicio.setTime(new java.util.Date(cu.getFechaInicio().getTime()));
		calInicio.set(Calendar.YEAR, anioUtilidad);
		
		Calendar calFin = new GregorianCalendar();
		calFin.setTime(new java.util.Date(cu.getFechaFin().getTime()));
		calFin.set(Calendar.YEAR, anioUtilidad);
		
		mapa.put("fechaInicioPeriodo", utilitariosLocal.getFechaUppercase(calInicio.getTime()) );
		mapa.put("fechaFinPeriodo", utilitariosLocal.getFechaUppercase(calFin.getTime()) );
		
		return mapa;
	}
	
	
	public ContratoUtilidadIf buscarContratoUtilidad(Long empresaId,Date fechaUltimoDiaMesRolPago)
	throws GenericBusinessException {
		ContratoUtilidadIf contratoUtilidadIf = null;
		
		Integer anioUtilidad = obtenerAnioUtilidadParametroEmpresa(empresaId);
		Calendar calUtilidad = new GregorianCalendar();
		//calUtilidad.setTime(gcUltimoDiaMesRolPago.getTime());
		calUtilidad.setTime(new java.util.Date(fechaUltimoDiaMesRolPago.getTime()));
		if ( anioUtilidad < 0 )
			calUtilidad.set(Calendar.YEAR, calUtilidad.get(Calendar.YEAR)+anioUtilidad );
		else 
			calUtilidad.set(Calendar.YEAR, anioUtilidad );
		Date fechaUtilidad = new Date(calUtilidad.getTime().getTime());
		
		//Date fechaRolSql = new Date(fechaRolPago.getTime());
		Collection<ContratoUtilidadIf> utilidades = findContratoUtilidadByEmpresaId(empresaId);
		for ( ContratoUtilidadIf utilidad : utilidades ){
			if ( fechaUtilidad.compareTo(utilidad.getFechaInicio()) >= 0 &&
					fechaUtilidad.compareTo(utilidad.getFechaFin()) <= 0	){
				contratoUtilidadIf = utilidad;
			}
		}
		if ( contratoUtilidadIf == null )
			throw new GenericBusinessException("No existe Utilidad con rango de Fechas que contenga el Mes y  el Año seleccionados !!");
		return contratoUtilidadIf;
	}

	public Integer obtenerAnioUtilidadParametroEmpresa(Long empresaId)
	throws GenericBusinessException {
		ParametroEmpresaIf pe = utilitariosLocal.getParametroEmpresa(
			empresaId,NominaParametros.TIPO_PARAMETRO, NominaParametros.ANIO_UTILIDADES,
		"que indica el año para el calculo de utilidades !!");

		String anioS = pe.getValor();
		if ( anioS == null || "".equals(anioS)  )
			throw new GenericBusinessException("Anio no establecido !!");
		Integer anioUtilidad = null;
		try {
			anioUtilidad = Integer.parseInt(anioS);
		} catch( Exception e ){
			throw new GenericBusinessException("Año de Utilidad tiene que ser numérico !!");
		}
		if ( anioUtilidad >= 0 && anioUtilidad <= 2008 ){
			throw new GenericBusinessException("Año de Utilidad tiene que ser mayor que 2008 !!");
		}
		return anioUtilidad;
	}
	
}
