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

@Table(name = "ROL_PAGO")
@Entity
public class RolPagoEJB implements Serializable, RolPagoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tiporolId;
   private java.lang.String estado;
   private java.lang.String mes;
   private java.lang.String anio;
   private java.sql.Date fecha;
   private java.lang.Long tipocontratoId;
   private java.lang.String asientoGenerado;

   public RolPagoEJB() {
   }

   public RolPagoEJB(com.spirit.nomina.entity.RolPagoIf value) {
      setId(value.getId());
      setTiporolId(value.getTiporolId());
      setEstado(value.getEstado());
      setMes(value.getMes());
      setAnio(value.getAnio());
      setFecha(value.getFecha());
      setTipocontratoId(value.getTipocontratoId());
      setAsientoGenerado(value.getAsientoGenerado());
   }

   public java.lang.Long create(com.spirit.nomina.entity.RolPagoIf value) {
      setId(value.getId());
      setTiporolId(value.getTiporolId());
      setEstado(value.getEstado());
      setMes(value.getMes());
      setAnio(value.getAnio());
      setFecha(value.getFecha());
      setTipocontratoId(value.getTipocontratoId());
      setAsientoGenerado(value.getAsientoGenerado());
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

   @Column(name = "TIPOROL_ID")
   public java.lang.Long getTiporolId() {
      return tiporolId;
   }

   public void setTiporolId(java.lang.Long tiporolId) {
      this.tiporolId = tiporolId;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "MES")
   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   @Column(name = "ANIO")
   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "TIPOCONTRATO_ID")
   public java.lang.Long getTipocontratoId() {
      return tipocontratoId;
   }

   public void setTipocontratoId(java.lang.Long tipocontratoId) {
      this.tipocontratoId = tipocontratoId;
   }

   @Column(name = "ASIENTO_GENERADO")
   public java.lang.String getAsientoGenerado() {
      return asientoGenerado;
   }

   public void setAsientoGenerado(java.lang.String asientoGenerado) {
      this.asientoGenerado = asientoGenerado;
   }
	public String toString() {
		return ToStringer.toString((RolPagoIf)this);
	}
}
