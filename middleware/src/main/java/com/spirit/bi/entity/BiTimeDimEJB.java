package com.spirit.bi.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "BI_TIME_DIM")
@Entity
public class BiTimeDimEJB implements Serializable{
private static final long serialVersionUID = 1L;

   public static final String ID = "id";
   public static final String ORIGENID = "origenId";
   public static final String FECHA = "fecha";
   public static final String ANIOID = "anioId";
   public static final String ANIO = "anio";
   public static final String SEMESTREID = "semestreId";
   public static final String SEMESTRE = "semestre";
   public static final String QUARTERID = "quarterId";
   public static final String QUARTER = "quarter";
   public static final String TRIMESTREID = "trimestreId";
   public static final String TRIMESTRE = "trimestre";
   public static final String MESID = "mesId";
   public static final String MES = "mes";
   public static final String QUINCENAID = "quincenaId";
   public static final String QUINCENA = "quincena";
   public static final String SEMANAID = "semanaId";
   public static final String SEMANA = "semana";
   public static final String DIAID = "diaId";
   public static final String DIA = "dia";

public static final String[] ALL_FIELDS=new String[]{
ID,ORIGENID,FECHA,ANIOID,ANIO,SEMESTREID,SEMESTRE,QUARTERID,QUARTER,TRIMESTREID,TRIMESTRE,MESID,MES,QUINCENAID,QUINCENA,SEMANAID,SEMANA,DIAID,DIA
};

public static final String[] ALL_FIELDS_NO_ID=new String[]{
ORIGENID,FECHA,ANIOID,ANIO,SEMESTREID,SEMESTRE,QUARTERID,QUARTER,TRIMESTREID,TRIMESTRE,MESID,MES,QUINCENAID,QUINCENA,SEMANAID,SEMANA,DIAID,DIA
};



   private java.lang.Long id;
   private java.lang.Long origenId;
   private java.sql.Date fecha;
   private java.lang.Integer anioId;
   private java.lang.String anio;
   private java.lang.Integer semestreId;
   private java.lang.String semestre;
   private java.lang.Integer quarterId;
   private java.lang.String quarter;
   private java.lang.Integer trimestreId;
   private java.lang.String trimestre;
   private java.lang.Integer mesId;
   private java.lang.String mes;
   private java.lang.Integer quincenaId;
   private java.lang.String quincena;
   private java.lang.Integer semanaId;
   private java.lang.String semana;
   private java.lang.Integer diaId;
   private java.lang.String dia;

   public BiTimeDimEJB() {
   }


   @Column(name = "ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "ORIGEN_ID")
   public java.lang.Long getOrigenId() {
      return origenId;
   }

   public void setOrigenId(java.lang.Long origenId) {
      this.origenId = origenId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "ANIO_ID")
   public java.lang.Integer getAnioId() {
      return anioId;
   }

   public void setAnioId(java.lang.Integer anioId) {
      this.anioId = anioId;
   }

   @Column(name = "ANIO")
   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   @Column(name = "SEMESTRE_ID")
   public java.lang.Integer getSemestreId() {
      return semestreId;
   }

   public void setSemestreId(java.lang.Integer semestreId) {
      this.semestreId = semestreId;
   }

   @Column(name = "SEMESTRE")
   public java.lang.String getSemestre() {
      return semestre;
   }

   public void setSemestre(java.lang.String semestre) {
      this.semestre = semestre;
   }

   @Column(name = "QUARTER_ID")
   public java.lang.Integer getQuarterId() {
      return quarterId;
   }

   public void setQuarterId(java.lang.Integer quarterId) {
      this.quarterId = quarterId;
   }

   @Column(name = "QUARTER")
   public java.lang.String getQuarter() {
      return quarter;
   }

   public void setQuarter(java.lang.String quarter) {
      this.quarter = quarter;
   }

   @Column(name = "TRIMESTRE_ID")
   public java.lang.Integer getTrimestreId() {
      return trimestreId;
   }

   public void setTrimestreId(java.lang.Integer trimestreId) {
      this.trimestreId = trimestreId;
   }

   @Column(name = "TRIMESTRE")
   public java.lang.String getTrimestre() {
      return trimestre;
   }

   public void setTrimestre(java.lang.String trimestre) {
      this.trimestre = trimestre;
   }

   @Column(name = "MES_ID")
   public java.lang.Integer getMesId() {
      return mesId;
   }

   public void setMesId(java.lang.Integer mesId) {
      this.mesId = mesId;
   }

   @Column(name = "MES")
   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   @Column(name = "QUINCENA_ID")
   public java.lang.Integer getQuincenaId() {
      return quincenaId;
   }

   public void setQuincenaId(java.lang.Integer quincenaId) {
      this.quincenaId = quincenaId;
   }

   @Column(name = "QUINCENA")
   public java.lang.String getQuincena() {
      return quincena;
   }

   public void setQuincena(java.lang.String quincena) {
      this.quincena = quincena;
   }

   @Column(name = "SEMANA_ID")
   public java.lang.Integer getSemanaId() {
      return semanaId;
   }

   public void setSemanaId(java.lang.Integer semanaId) {
      this.semanaId = semanaId;
   }

   @Column(name = "SEMANA")
   public java.lang.String getSemana() {
      return semana;
   }

   public void setSemana(java.lang.String semana) {
      this.semana = semana;
   }

   @Column(name = "DIA_ID")
   public java.lang.Integer getDiaId() {
      return diaId;
   }

   public void setDiaId(java.lang.Integer diaId) {
      this.diaId = diaId;
   }

   @Column(name = "DIA")
   public java.lang.String getDia() {
      return dia;
   }

   public void setDia(java.lang.String dia) {
      this.dia = dia;
   }
	public String toString() {
		return super.toString();
	}
}
