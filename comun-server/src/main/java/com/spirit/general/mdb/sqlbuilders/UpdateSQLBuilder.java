package com.spirit.general.mdb.sqlbuilders;

import java.util.HashMap;
import java.util.Set;

import com.spirit.general.mdb.classlistener.EnumTipoOperacionSQL;

public class UpdateSQLBuilder extends SQLBuilder {

	private EnumTipoOperacionSQL operacion = EnumTipoOperacionSQL.OP_UPDATE;
	private HashMap<String, Object> mapaWhere;

	public UpdateSQLBuilder(String tableName, Object objetoBL,
			String... listaCampos) {
		super(tableName, objetoBL, listaCampos);
	}

	public UpdateSQLBuilder(String tableName, HashMap<String, Object> mapa) {
		super(tableName, mapa);
	}

	public void buildOP() {
		buffer = operacion.getCadenaOP1() + " " + tableName + " "
				+ operacion.getCadenaOP2() + " ";
	}

	public void buildBody() {
		Set<String> campos = mapa.keySet();
		for (String campo : campos) {
			buffer += campo + "=" + buildParametro(mapa.get(campo)) + ",";
		}
		buffer = buffer.substring(0, buffer.length() - 1);

	}

	@Override
	public void build() {
		super.build();
		buildWhere();
	}

	private void buildWhere() {
		if (mapaWhere == null)
			return;
		buffer += " " + operacion.getCadenaOP3() + " ";
		Set<String> campos = mapaWhere.keySet();
		for (String campo : campos) {
			buffer += campo + "=" + buildParametro(mapaWhere.get(campo))
					+ " AND ";
		}
		buffer = buffer.substring(0, buffer.length() - 4);
	}

	public void setWhere(HashMap<String, Object> mapaWhere) {
		this.mapaWhere = mapaWhere;
	}

	public static void main(String[] args) {
		HashMap<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("USUARIO", "RICARDO");
		mapa.put("CLAVE", "12345678");

		UpdateSQLBuilder update = new UpdateSQLBuilder("USUARIO", mapa);
		// update.setWhere(mapa);
		update.printSQL();
	}
}
