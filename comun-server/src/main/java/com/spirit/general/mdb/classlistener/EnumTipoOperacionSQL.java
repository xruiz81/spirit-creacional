package com.spirit.general.mdb.classlistener;

public enum EnumTipoOperacionSQL {
	OP_INSERT("INSERT", "INTO", "VALUES"), OP_UPDATE("UPDATE", "SET", "WHERE"), OP_DELETE(
			"DELETE", "FROM", "WHERE");
	private String cadenaOP1;
	private String cadenaOP2;
	private String cadenaOP3;

	EnumTipoOperacionSQL(String cadenaOP1, String cadenaOP2, String cadenaOP3) {
		this.cadenaOP1 = cadenaOP1;
		this.cadenaOP2 = cadenaOP2;
		this.cadenaOP3 = cadenaOP3;
	}

	public String getCadenaOP1() {
		return cadenaOP1;
	}

	public String getCadenaOP2() {
		return cadenaOP2;
	}

	public String getCadenaOP3() {
		return cadenaOP3;
	}

}
