package com.spirit.general.mdb.sqlbuilders;

import java.io.Serializable;
import java.util.HashMap;

import com.spirit.general.mdb.classlistener.BeanHelper;

public abstract class SQLBuilder implements SQLBuilderIf,Serializable {

	protected String tableName;
	protected HashMap<String, Object> mapa;

	protected String buffer = "";
	protected String cadenaParametros = "(";
	protected Object objetoEntity;

	public void print(String string) {
		System.out.println(string);
	}

	public SQLBuilder(String pTableName, HashMap<String, Object> pMapa) {
		tableName = pTableName;
		mapa = pMapa;
	}

	public SQLBuilder(String pTableName, Object objetoBL, String... listaCampos) {
		tableName = pTableName;
		objetoEntity=objetoBL;
		mapa = BeanHelper.getFields(objetoBL, listaCampos);
	}
	
	public SQLBuilder(String pTableName, Object objetoBL) {
		tableName = pTableName;
		objetoEntity=objetoBL;
	}

	public String buildParametro(Object objeto) {
		if (objeto == null) {
			return "null";
		} else if (objeto instanceof String) {
			return "'" + objeto + "'";
		} else {
			return objeto.toString();
		}
	}

	public void build() {
		buildOP();
		buildBody();
	}

	public String printSQL() {
		build();
		print(buffer + ";");
		return (buffer + ";");
	}

	public Object getObjetoEntity() {
		return objetoEntity;
	}

}
