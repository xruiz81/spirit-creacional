package com.spirit.general.mdb.sqlbuilders;

import java.util.HashMap;
import java.util.Set;

import com.spirit.general.mdb.classlistener.EnumTipoOperacionSQL;
import com.spirit.general.mdb.classlistener.StringUtils;

public class InsertSQLBuilder extends SQLBuilder {

	public InsertSQLBuilder(String tableName, Object objetoBL,
			String... listaCampos) {
		super(tableName, objetoBL, listaCampos);
	}

	public InsertSQLBuilder(String tableName, HashMap<String, Object> mapa) {
		super(tableName, mapa);
	}
	
	public InsertSQLBuilder(String pTableName, Object objetoBL) {
		super(pTableName, objetoBL);
	}

	EnumTipoOperacionSQL operacion=EnumTipoOperacionSQL.OP_INSERT;
	
	public void buildOP() {
		buffer = operacion.getCadenaOP1() + " " + operacion.getCadenaOP2()
				+ " " + tableName + "(";
	}

	public void buildBody() {
		Set<String> campos = mapa.keySet();
		for (String campo : campos) {
			buffer += StringUtils.revertCamelCase(campo) + ",";
			cadenaParametros += buildParametro(mapa.get(campo)) + ",";
		}
		cadenaParametros = cadenaParametros.substring(0, cadenaParametros
				.length() - 1)
				+ ")";
		buffer = buffer.substring(0, buffer.length() - 1) + ") "
				+ operacion.getCadenaOP3() + " " + cadenaParametros;
	}

	public static void main(String[] args) {
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("USUARIO", "RICARDO");
		mapa.put("CLAVE", "12345678");

		InsertSQLBuilder insert=new InsertSQLBuilder("USUARIO", mapa);
		insert.printSQL();
	}
}
