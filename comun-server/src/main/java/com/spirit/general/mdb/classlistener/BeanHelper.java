package com.spirit.general.mdb.classlistener;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanHelper {

	private static ArrayList<String> listaCamposExcluir = new ArrayList<String>();

	static {
		listaCamposExcluir.add("primaryKeyParameters");
		listaCamposExcluir.add("class");
		listaCamposExcluir.add("id");
		listaCamposExcluir.add("primaryKey");
	}

	public static HashMap<String, Object> getFields(Object objetoBL,
			String... listaCampos) {
		HashMap<String, Object> mapaParametros = new HashMap<String, Object>();
		if (listaCampos.length == 1 && listaCampos[0].equalsIgnoreCase("ALL")) {
			return getAll(objetoBL);
		}
		for (String campo : listaCampos) {
			mapaParametros.put(campo, getObject(campo, objetoBL));
		}
		return mapaParametros;
	}

	private static HashMap<String, Object> getAll(Object bean) {
		try {
			HashMap<String, Object> mapa = (HashMap<String, Object>) PropertyUtils
					.describe(bean);
			for (String s : listaCamposExcluir) {
				mapa.remove(s);
			}
			return mapa;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Object getObject(String propertyName, Object bean) {
		try {
			return PropertyUtils.getProperty(bean, propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {

	}
}
