package com.spirit.bpm.process.elemets.state;

public enum EnumPrioridad implements StateIf {

	NORMAL("/images/bpm/p_normal16.png"), 
	ALTA("/images/bpm/p_alta16.png"), 
	URGENTE("/images/bpm/p_urgente16.png"), 
	NINGUNA(null,"NO APLICA"), 
	DESCONOCIDO;
	private static final long serialVersionUID = 1L;
	private String icono;
	private String descripcion;

	private EnumPrioridad(String icono, String descripcion) {
		this.icono = icono;
		this.descripcion = descripcion;
	}

	private EnumPrioridad() {

	}

	private EnumPrioridad(String icono) {
		this.icono = icono;
	}

	public static EnumPrioridad getPrioridad(EnumTareaState estadoTarea, int s) {
		String prioridad = Integer.toString(s);
		if (EnumTareaState.TERMINADA.equals(estadoTarea)
				|| EnumTareaState.CANCELADA.equals(estadoTarea)
				|| EnumTareaState.ABORTADA.equals(estadoTarea)) {
			return NINGUNA;
		}
		if (prioridad.equalsIgnoreCase("0")) {
			return NORMAL;
		} else if (prioridad.equalsIgnoreCase("1")) {
			return ALTA;
		} else if (prioridad.equalsIgnoreCase("2")) {
			return URGENTE;
		} else {
			System.out.println("PRIORIDAD DESCONOCIDA!!!! " + prioridad);
			return DESCONOCIDO;
		}
	}

	@Override
	public String getIcono() {
		return icono;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

}
