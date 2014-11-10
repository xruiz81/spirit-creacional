package com.spirit.rrhh.gui.reporteData;

import java.io.Serializable;

public class DatosAcademicosData implements Serializable  {
	private static final long serialVersionUID = 7644977459530184219L;

	private String nivel;
	private String anioAprobado;
	private String fecGraduado;
	private String titulo;
	private String institucion;
	 
	
	public DatosAcademicosData(){
		nivel="";
		anioAprobado="";
		fecGraduado="";
		titulo="";
		institucion="";
	}


	public String getNivel() {
		return nivel;
	}


	public void setNivel(String nivel) {
		this.nivel = nivel;
	}


	public String getAnioAprobado() {
		return anioAprobado;
	}


	public void setAnioAprobado(String anioAprobado) {
		this.anioAprobado = anioAprobado;
	}


	public String getFecGraduado() {
		return fecGraduado;
	}


	public void setFecGraduado(String fecGraduado) {
		this.fecGraduado = fecGraduado;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getInstitucion() {
		return institucion;
	}


	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	 
}
