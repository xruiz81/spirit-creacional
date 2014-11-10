package com.spirit.bpm.process.elemets.state;

public enum EnumTareaState implements StateIf {

	SUSPENDIDA("/images/bpm/pausa16.png"), 
	TERMINADA("/images/bpm/terminada16.png"), 
	EJECUCION("/images/bpm/ejecucion16.png"), 
	CANCELADA("/images/bpm/cancelada16.png"), 
	ABORTADA("/images/bpm/cancelada16.png"), 
	PENDIENTE("/images/bpm/pendiente16.png"), 
	DESCONOCIDO;

	private static final long serialVersionUID = 1L;
	private String icono;
	private String descripcion;

	private EnumTareaState() {
	}

	private EnumTareaState(String icono) {
		this.icono = icono;
	}

	private EnumTareaState(String icono, String descripcion) {
		this.icono = icono;
		this.descripcion = descripcion;
	}

	public static EnumTareaState getState(String s) {
		String estado = s.trim();
		if (estado.equalsIgnoreCase("SUSPENDED")) {
			return SUSPENDIDA;
		} else if (estado.equalsIgnoreCase("FINISHED")) {
			return TERMINADA;
		} else if (estado.equalsIgnoreCase("EXECUTING")) {
			return EJECUCION;
		} else if (estado.equalsIgnoreCase("CANCELLED")) {
			return CANCELADA;
		} else if (estado.equalsIgnoreCase("ABORTED")) {
			return ABORTADA;
		} else if (estado.equalsIgnoreCase("READY")) {
			return PENDIENTE;
		} else {
			System.out.println("DESCONOCIDO!!!! " + estado);
			return DESCONOCIDO;
		}
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String getIcono() {
		return icono;
	}
}
