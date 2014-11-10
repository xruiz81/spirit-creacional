package com.spirit.bpm.process.elemets.state;

public enum EnumNivelVencido implements StateIf {
	NO_APLICA, A_TIEMPO("/images/bpm/f_esperada_ok16.png"), PARA_HOY(
			"/images/bpm/f_esperada_hoy16.png"), VENCIDO(
			"/images/bpm/f_esperada_no16.png");
	private String descripcion;
	private String icono;

	private EnumNivelVencido() {
	}

	private EnumNivelVencido(String icono) {
		this.icono = icono;
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
