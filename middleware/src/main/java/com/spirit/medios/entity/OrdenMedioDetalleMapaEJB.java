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

@Table(name = "ORDEN_MEDIO_DETALLE_MAPA")
@Entity
public class OrdenMedioDetalleMapaEJB implements Serializable, OrdenMedioDetalleMapaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long ordenMedioDetalleId;
   private java.sql.Timestamp fecha;
   private java.lang.Integer frecuencia;

   public OrdenMedioDetalleMapaEJB() {
   }

   public OrdenMedioDetalleMapaEJB(com.spirit.medios.entity.OrdenMedioDetalleMapaIf value) {
      setId(value.getId());
      setOrdenMedioDetalleId(value.getOrdenMedioDetalleId());
      setFecha(value.getFecha());
      setFrecuencia(value.getFrecuencia());
   }

   public java.lang.Long create(com.spirit.medios.entity.OrdenMedioDetalleMapaIf value) {
      setId(value.getId());
      setOrdenMedioDetalleId(value.getOrdenMedioDetalleId());
      setFecha(value.getFecha());
      setFrecuencia(value.getFrecuencia());
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

   @Column(name = "ORDEN_MEDIO_DETALLE_ID")
   public java.lang.Long getOrdenMedioDetalleId() {
      return ordenMedioDetalleId;
   }

   public void setOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId) {
      this.ordenMedioDetalleId = ordenMedioDetalleId;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "FRECUENCIA")
   public java.lang.Integer getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.Integer frecuencia) {
      this.frecuencia = frecuencia;
   }
	public String toString() {
		return ToStringer.toString((OrdenMedioDetalleMapaIf)this);
	}
}
