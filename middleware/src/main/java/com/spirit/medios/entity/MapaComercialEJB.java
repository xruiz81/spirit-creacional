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

@Table(name = "MAPA_COMERCIAL")
@Entity
public class MapaComercialEJB implements Serializable, MapaComercialIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long planMedioDetalleId;
   private java.sql.Date fecha;
   private java.lang.Integer frecuencia;

   public MapaComercialEJB() {
   }

   public MapaComercialEJB(com.spirit.medios.entity.MapaComercialIf value) {
      setId(value.getId());
      setPlanMedioDetalleId(value.getPlanMedioDetalleId());
      setFecha(value.getFecha());
      setFrecuencia(value.getFrecuencia());
   }

   public java.lang.Long create(com.spirit.medios.entity.MapaComercialIf value) {
      setId(value.getId());
      setPlanMedioDetalleId(value.getPlanMedioDetalleId());
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

   @Column(name = "PLAN_MEDIO_DETALLE_ID")
   public java.lang.Long getPlanMedioDetalleId() {
      return planMedioDetalleId;
   }

   public void setPlanMedioDetalleId(java.lang.Long planMedioDetalleId) {
      this.planMedioDetalleId = planMedioDetalleId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
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
		return ToStringer.toString((MapaComercialIf)this);
	}
}
