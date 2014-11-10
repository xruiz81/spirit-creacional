package com.spirit.util;

import java.sql.Date;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.spirit.server.QueryBuilder;

public class FindQuery {
	
	public static Query findQueryByDates(Map aMap,  String objectNameS, String cadenaQuery, String orden, EntityManager manager) {
		String queryString = "";
		String objectName = objectNameS; //e
				
		Date fechaIni = null;
		Date fechaInicio = null;
		Date fechaFin = null;
		Date fechaLimite = null;
		Date fechaMediaContrato = null;
		Date fechaMediaContratoMax = null;
		Date fechaInicioProvision = null;
		Date fechaFinProvision = null;
		Date fechaMedia = null;
		
		
		if ( aMap.get("fechaMedia") != null ){
			fechaMedia = (Date)aMap.get("fechaMedia");
			aMap.remove("fechaMedia");
		}
		
		if ( aMap.get("fechaInicioProvision") != null ){
			fechaInicioProvision = (Date)aMap.get("fechaInicioProvision");
			aMap.remove("fechaInicioProvision");
		}
		
		if ( aMap.get("fechaFinProvision") != null ){
			fechaFinProvision = (Date)aMap.get("fechaFinProvision");
			aMap.remove("fechaFinProvision");
		}
		
		if ( aMap.get("fechaMediaContrato") != null ){
			fechaMediaContrato = (Date)aMap.get("fechaMediaContrato");
			aMap.remove("fechaMediaContrato");
		}
		
		if ( aMap.get("fechaMediaContratoMax") != null ){
			fechaMediaContratoMax = (Date)aMap.get("fechaMediaContratoMax");
			aMap.remove("fechaMediaContratoMax");
		}
		
		if(aMap.get("fechaini") != null){
			fechaIni = (Date) aMap.get("fechaini");
			aMap.remove("fechaini");
		}
		if(aMap.get("fechaInicio") != null){
			fechaInicio = (Date) aMap.get("fechaInicio");
			aMap.remove("fechaInicio");
		}
		if(aMap.get("fechafin") != null){
			fechaFin = (Date) aMap.get("fechafin");
			aMap.remove("fechafin");
		}
		if(aMap.get("fechaFin") != null){
			fechaFin = (Date) aMap.get("fechaFin");
			aMap.remove("fechaFin");
		}
		if(aMap.get("fechalimite") != null){
			fechaLimite = (Date) aMap.get("fechalimite");
			aMap.remove("fechalimite");
		}
	   	
		String where = QueryBuilder.buildWhere(aMap, objectName);
		//INI_CAMBIO_20140620 MODIFICACION PARA GENERAR ROLES DE EMPLEADOS CON INICIO Y FIN DE CONTRATO EL MISMO MES Y ANIO
		//SE COMENTA PARA IMPLEMENTAR EL CAMBIO ARRIBA MENCIONADO 
		/*if ( fechaMediaContrato != null ){
			queryString = (cadenaQuery + where + 
				" and ("+objectName+".fechaInicio <= :fechaMediaContrato and "+objectName+".fechaFin >= :fechaMediaContrato )");
		}
		
		if ( fechaMediaContratoMax != null ){
			queryString = queryString.substring(0,queryString.length()-1);
			queryString += (" or "+objectName+".fechaInicio <= :fechaMediaContratoMax and "+objectName+".fechaFin >= :fechaMediaContratoMax) ");
		}*/
		
		//VALIDA INICIO DE CONTRATO DESPUES DEL DIA 1 y FIN DE CONTRATO ANTES DEL ULTIMO DIA DEL MISMO MES Y ANIO
		if (( fechaMediaContrato != null )&&( fechaMediaContratoMax != null )){
			queryString = (cadenaQuery + where + 
				" and ("+objectName+".fechaInicio <= :fechaMediaContratoMax and "+objectName+".fechaFin >= :fechaMediaContrato )");
		//FIN_CAMBIO_20140620
		} else if((fechaIni != null) && (fechaFin != null)){
			queryString = cadenaQuery
						+ where + " and e.fechaini >= :fechaIni and e.fechafin <= :fechaFin";
		} else if((fechaInicio != null) && (fechaFin != null)){
			queryString = cadenaQuery
						+ where + " and "+objectName+".fechaInicio >= :fechaInicio and "+objectName+".fechaFin <= :fechaFin";
		} else if((fechaIni != null) && (fechaFin == null)){
			queryString = cadenaQuery
						+ where + " and e.fechaini >= :fechaIni";
		} else if((fechaInicio != null) && (fechaFin == null)){
			queryString = cadenaQuery
						+ where + " and "+objectName+".fechaInicio >= :fechaInicio";
		} else if((fechaInicio == null) && (fechaFin != null)){
			queryString = cadenaQuery
						+ where + " and e.fechaFin <= :fechaFin";
		} else if(fechaLimite != null){
			queryString = cadenaQuery
						+ where + " and e.fechalimite <= :fechaLimite";
		} else if( fechaMedia != null ){
			queryString = cadenaQuery
			+ where +
				" ( :fechaMedia >= "+objectName+".fechaInicio and :fechaMedia <= "+objectName+".fechaFin  ) or " +
				" ( :fechaMedia >= "+objectName+".fechaInicio and "+objectName+".fechaFin is null  ) " ;	
		} else if(fechaInicioProvision != null || fechaFinProvision != null){
			queryString = cadenaQuery
			+ where +" and ( " +
				"not (c.fechaInicio > :fechaFinProvision or c.fechaFin < :fechaInicioProvision ) " +
				")"; 
			/*" ("+objectName+".fechaInicio >= :fechaInicioProvision and "+objectName+".fechaInicio <= :fechaFinProvision ) or "+
			" ("+objectName+".fechaFin >= :fechaInicioProvision and "+objectName+".fechaFin <= :fechaFinProvision ) or " +
			" ("+objectName+".fechaInicio <= :fechaInicioProvision and "+objectName+".fechaFin is null ) or "+
			" ("+objectName+".fechaInicio is null and "+objectName+".fechaFin >= :fechaFinProvision   ) " +
			" ) ";*/
		}
		else{
			queryString = cadenaQuery
						+ where;
		}
				
		if(orden!= null && !orden.equals("") && !orden.equals(" ")){
			queryString = queryString + " " + orden;
		}
		Query query = manager.createQuery(queryString);
		if(fechaIni != null){
			query.setParameter("fechaIni",fechaIni);
		}
		if(fechaInicio != null){
			query.setParameter("fechaInicio",fechaInicio);
		}
		if(fechaFin != null){
			query.setParameter("fechaFin",fechaFin);
		}
		if(fechaLimite != null){
			query.setParameter("fechaLimite",fechaLimite);
		}
		if ( fechaMediaContrato!=null ){
			query.setParameter("fechaMediaContrato",fechaMediaContrato);
		}
		if ( fechaMediaContratoMax!=null ){
			query.setParameter("fechaMediaContratoMax",fechaMediaContratoMax);
		} 
		if ( fechaMedia!=null ){
			query.setParameter("fechaMedia",fechaMedia);
		}
		if (fechaInicioProvision != null)
			query.setParameter("fechaInicioProvision",fechaInicioProvision);
		if (fechaFinProvision != null)
			query.setParameter("fechaFinProvision",fechaFinProvision);
		
		return query;
	}
}
