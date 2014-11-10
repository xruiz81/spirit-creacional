package com.spirit.server;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class QueryBuilder {

	//Convierte el Mapa a string(estructura query) del string objeto que se envia
	public static String buildWhere(Map aMap, String objectName) {

		Set keys = aMap.keySet();

		String query = " ";

		Iterator it = keys.iterator();
		int propertyNumber = keys.size();
		int i = 1;
		while (it.hasNext()) {
			String propertyKey = (String) it.next();
			Object property = aMap.get(propertyKey);

			if (property instanceof String) {

				String propertyString = (String) property;

				if ( propertyString.contains("null") ){
					query = query + objectName + "." + propertyKey + " is "
					+ propertyString;
					it.remove();
				}else if (propertyString.contains("%")) {

					query = query + objectName + "." + propertyKey + " like :"
							+ propertyKey;
				} else {
					query = query + objectName + "." + propertyKey + " = :"
							+ propertyKey;
				}

			} else {

				if ( property == null ){
					query = query + objectName + "." + propertyKey + " is null ";
					it.remove();
				}else
					query = query + objectName + "." + propertyKey + " = :"
						+ propertyKey;
			}

			if (i == propertyNumber) {
				query = query + " ";
			} else {
				query = query + " and ";
			}

			i++;
		}
		//System.out.println("Parte del Where del Query: " + query);
		return query;
	}
	
	public static String buildWhere(String nombreParametro,String valorParametro, String objectName) {

		if ( valorParametro == null || objectName == null
				|| "".equals(valorParametro) || "".equals(objectName) )
			return "";

		String query = " ";

		int i = 1;
		if (valorParametro instanceof String) {

				if (valorParametro.contains("%")) {

					query = query + objectName + "." + nombreParametro + " like :"
							+ nombreParametro;
				} else {
					query = query + objectName + "." + nombreParametro + " = :"
							+ nombreParametro;
				}

			} else {

				query = query + objectName + "." + nombreParametro + " = :"
						+ nombreParametro;
			}		
		return query;
	}
	
}
