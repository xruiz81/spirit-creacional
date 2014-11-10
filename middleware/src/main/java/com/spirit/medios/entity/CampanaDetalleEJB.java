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

@Table(name = "CAMPANA_DETALLE")
@Entity
public class CampanaDetalleEJB implements Serializable, CampanaDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteZonaId;
   private java.lang.Long campanaId;
   private java.math.BigDecimal participacion;
   private java.lang.String descripcion;

   public CampanaDetalleEJB() {
   }

   public CampanaDetalleEJB(com.spirit.medios.entity.CampanaDetalleIf value) {
      setId(value.getId());
      setClienteZonaId(value.getClienteZonaId());
      setCampanaId(value.getCampanaId());
      setParticipacion(value.getParticipacion());
      setDescripcion(value.getDescripcion());
   }

   public java.lang.Long create(com.spirit.medios.entity.CampanaDetalleIf value) {
      setId(value.getId());
      setClienteZonaId(value.getClienteZonaId());
      setCampanaId(value.getCampanaId());
      setParticipacion(value.getParticipacion());
      setDescripcion(value.getDescripcion());
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

   @Column(name = "CLIENTE_ZONA_ID")
   public java.lang.Long getClienteZonaId() {
      return clienteZonaId;
   }

   public void setClienteZonaId(java.lang.Long clienteZonaId) {
      this.clienteZonaId = clienteZonaId;
   }

   @Column(name = "CAMPANA_ID")
   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   @Column(name = "PARTICIPACION")
   public java.math.BigDecimal getParticipacion() {
      return participacion;
   }

   public void setParticipacion(java.math.BigDecimal participacion) {
      this.participacion = participacion;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }
	public String toString() {
		return ToStringer.toString((CampanaDetalleIf)this);
	}
}
