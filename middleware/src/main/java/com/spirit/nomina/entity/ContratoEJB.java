package com.spirit.nomina.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CONTRATO")
@Entity
public class ContratoEJB implements Serializable, ContratoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.sql.Date fechaElaboracion;
   private java.lang.Long tipocontratoId;
   private java.lang.Long empleadoId;
   private java.sql.Date fechaInicio;
   private java.sql.Date fechaFin;
   private java.lang.String estado;
   private java.lang.String observacion;
   private java.lang.String url;
   private java.lang.Integer fondoReservaDiasPrevios;

   public ContratoEJB() {
   }

   public ContratoEJB(com.spirit.nomina.entity.ContratoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setFechaElaboracion(value.getFechaElaboracion());
      setTipocontratoId(value.getTipocontratoId());
      setEmpleadoId(value.getEmpleadoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setUrl(value.getUrl());
      setFondoReservaDiasPrevios(value.getFondoReservaDiasPrevios());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ContratoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setFechaElaboracion(value.getFechaElaboracion());
      setTipocontratoId(value.getTipocontratoId());
      setEmpleadoId(value.getEmpleadoId());
      setFechaInicio(value.getFechaInicio());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setObservacion(value.getObservacion());
      setUrl(value.getUrl());
      setFondoReservaDiasPrevios(value.getFondoReservaDiasPrevios());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "FECHA_ELABORACION")
   public java.sql.Date getFechaElaboracion() {
      return fechaElaboracion;
   }

   public void setFechaElaboracion(java.sql.Date fechaElaboracion) {
      this.fechaElaboracion = fechaElaboracion;
   }

   @Column(name = "TIPOCONTRATO_ID")
   public java.lang.Long getTipocontratoId() {
      return tipocontratoId;
   }

   public void setTipocontratoId(java.lang.Long tipocontratoId) {
      this.tipocontratoId = tipocontratoId;
   }

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "FECHA_INICIO")
   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "FECHA_FIN")
   public java.sql.Date getFechaFin() {
      return fechaFin;
   }

   public void setFechaFin(java.sql.Date fechaFin) {
      this.fechaFin = fechaFin;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "URL")
   public java.lang.String getUrl() {
      return url;
   }

   public void setUrl(java.lang.String url) {
      this.url = url;
   }

   @Column(name = "FONDO_RESERVA_DIAS_PREVIOS")
   public java.lang.Integer getFondoReservaDiasPrevios() {
      return fondoReservaDiasPrevios;
   }

   public void setFondoReservaDiasPrevios(java.lang.Integer fondoReservaDiasPrevios) {
      this.fondoReservaDiasPrevios = fondoReservaDiasPrevios;
   }
	public String toString() {
		return ToStringer.toString((ContratoIf)this);
	}
}
