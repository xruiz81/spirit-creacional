package com.spirit.general.mdb.sqlbuilders;

import java.util.HashMap;
import java.util.Set;

import com.spirit.general.mdb.classlistener.EnumTipoOperacionSQL;

public class DeleteSQLBuilder extends SQLBuilder {

	public DeleteSQLBuilder(String tableName, Object objetoBL,
			String... listaCampos) {
		super(tableName, objetoBL, listaCampos);
	}

	public DeleteSQLBuilder(String tableName, HashMap<String, Object> mapa) {
		super(tableName, mapa);
	}

	EnumTipoOperacionSQL operacion = EnumTipoOperacionSQL.OP_DELETE;

	public void buildOP() {
		buffer = operacion.getCadenaOP1() + " " + operacion.getCadenaOP2()
				+ " " + tableName;
	}

	public void buildBody() {
		if (mapa == null)
			return;
		buffer += " " + operacion.getCadenaOP3() + " ";
		Set<String> campos = mapa.keySet();
		for (String campo : campos) {
			buffer += campo + "=" + buildParametro(mapa.get(campo)) + " AND ";
		}
		buffer = buffer.substring(0, buffer.length() - 5);
	}

	public static void main(String[] args) {
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("USUARIO", "RICARDO");
		mapa.put("CLAVE", "12345678");

		DeleteSQLBuilder update = new DeleteSQLBuilder("USUARIO", mapa);
		update.printSQL();
	}
}
