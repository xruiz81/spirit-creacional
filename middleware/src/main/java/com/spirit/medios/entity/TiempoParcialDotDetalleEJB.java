package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "TIEMPO_PARCIAL_DOT_DETALLE")
@Entity
public class TiempoParcialDotDetalleEJB implements Serializable, TiempoParcialDotDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long fecha;
   private java.lang.Long horaInicio;
   private java.lang.Long horaFin;
   private java.lang.Long tiempo;
   private java.lang.Long idTiempoParcialDot;

   public TiempoParcialDotDetalleEJB() {
   }

   public TiempoParcialDotDetalleEJB(com.spirit.medios.entity.TiempoParcialDotDetalleIf value) {
      setId(value.getId());
      setFecha(value.getFecha());
      setHoraInicio(value.getHoraInicio());
      setHoraFin(value.getHoraFin());
      setTiempo(value.getTiempo());
      setIdTiempoParcialDot(value.getIdTiempoParcialDot());
   }

   public java.lang.Long create(com.spirit.medios.entity.TiempoParcialDotDetalleIf value) {
      setId(value.getId());
      setFecha(value.getFecha());
      setHoraInicio(value.getHoraInicio());
      setHoraFin(value.getHoraFin());
      setTiempo(value.getTiempo());
      setIdTiempoParcialDot(value.getIdTiempoParcialDot());
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

   @Column(name = "FECHA")
   public java.lang.Long getFecha() {
      return fecha;
   }

   public void setFecha(java.lang.Long fecha) {
      this.fecha = fecha;
   }

   @Column(name = "HORA_INICIO")
   public java.lang.Long getHoraInicio() {
      return horaInicio;
   }

   public void setHoraInicio(java.lang.Long horaInicio) {
      this.horaInicio = horaInicio;
   }

   @Column(name = "HORA_FIN")
   public java.lang.Long getHoraFin() {
      return horaFin;
   }

   public void setHoraFin(java.lang.Long horaFin) {
      this.horaFin = horaFin;
   }

   @Column(name = "TIEMPO")
   public java.lang.Long getTiempo() {
      return tiempo;
   }

   public void setTiempo(java.lang.Long tiempo) {
      this.tiempo = tiempo;
   }

   @Column(name = "ID_TIEMPO_PARCIAL_DOT")
   public java.lang.Long getIdTiempoParcialDot() {
      return idTiempoParcialDot;
   }

   public void setIdTiempoParcialDot(java.lang.Long idTiempoParcialDot) {
      this.idTiempoParcialDot = idTiempoParcialDot;
   }
	public String toString() {
		return ToStringer.toString((TiempoParcialDotDetalleIf)this);
	}
}
