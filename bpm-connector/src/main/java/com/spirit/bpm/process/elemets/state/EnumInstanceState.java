package com.spirit.bpm.process.elemets.state;

public enum EnumInstanceState implements StateIf {

	ALL, CANCELADA("/images/bpm/cancelada16.png"), 
	INICIADA("/images/bpm/ejecucion16.png"), 
	TERMINADA("/images/bpm/terminada16.png"), DESCONOCIDO;

	private static final long serialVersionUID = 1L;
	private String icono;
	private String descripcion;

	private EnumInstanceState() {
	}

	private EnumInstanceState(String icono) {
		this.icono = icono;
	}

	public static EnumInstanceState getState(String s) {
		String estado = s.trim();
		if (estado.equalsIgnoreCase("STARTED"))
			return INICIADA;
		else if (estado.equalsIgnoreCase("FINISHED"))
			return TERMINADA;
		else if (estado.equalsIgnoreCase("CANCELLED"))
			return CANCELADA;
		else {
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
