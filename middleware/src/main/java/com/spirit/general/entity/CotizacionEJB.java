package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "COTIZACION")
@Entity
public class CotizacionEJB implements Serializable, CotizacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long monedaId;
   private java.lang.Long monedaequivId;
   private java.sql.Date fecha;
   private java.math.BigDecimal cotizacion;

   public CotizacionEJB() {
   }

   public CotizacionEJB(com.spirit.general.entity.CotizacionIf value) {
      setId(value.getId());
      setMonedaId(value.getMonedaId());
      setMonedaequivId(value.getMonedaequivId());
      setFecha(value.getFecha());
      setCotizacion(value.getCotizacion());
   }

   public java.lang.Long create(com.spirit.general.entity.CotizacionIf value) {
      setId(value.getId());
      setMonedaId(value.getMonedaId());
      setMonedaequivId(value.getMonedaequivId());
      setFecha(value.getFecha());
      setCotizacion(value.getCotizacion());
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

   @Column(name = "MONEDA_ID")
   public java.lang.Long getMonedaId() {
      return monedaId;
   }

   public void setMonedaId(java.lang.Long monedaId) {
      this.monedaId = monedaId;
   }

   @Column(name = "MONEDAEQUIV_ID")
   public java.lang.Long getMonedaequivId() {
      return monedaequivId;
   }

   public void setMonedaequivId(java.lang.Long monedaequivId) {
      this.monedaequivId = monedaequivId;
   }

   @Column(name = "FECHA")
   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   @Column(name = "COTIZACION")
   public java.math.BigDecimal getCotizacion() {
      return cotizacion;
   }

   public void setCotizacion(java.math.BigDecimal cotizacion) {
      this.cotizacion = cotizacion;
   }
	public String toString() {
		return ToStringer.toString((CotizacionIf)this);
	}
}
