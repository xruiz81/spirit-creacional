package com.spirit.general.mdb.sqlbuilders;


public interface SQLBuilderIf {
	void print(String string);
	void buildOP();
	void buildBody();
	String buildParametro(Object objeto);
	String printSQL() ;
}
