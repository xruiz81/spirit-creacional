package com.spirit.client;

public class Parametros {

	public static boolean jbpmPresente;
	public static boolean standAlone = true;
	public static final boolean ACTIVAR_REPLICACION = true;

	public static boolean isJbpmPresente() {
		return jbpmPresente;
	}

	public static void setJbpmPresente(boolean jbpm) {
		Parametros.jbpmPresente = jbpm;
	}

}
