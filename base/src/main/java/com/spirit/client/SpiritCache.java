package com.spirit.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lmunoz
 * 
 * Implementa un cache de catalogos.
 */
public class SpiritCache {

	private static Map cache = new HashMap();

	/**
	 * Setea una lista en el cache dado un nombre
	 * 
	 * @param name
	 *            el nombre
	 * @param aList
	 *            unaLista
	 */

	public static void setObject(String name, List list) {
		cache.put(name, list);
	}

	public static List findObject(String name) {
		return ((List) cache.get("name"));
	}

	public static void removeObject(String name) {
		cache.remove(name);
	}
}
