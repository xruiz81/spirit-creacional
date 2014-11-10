package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PRECIO_HISTORICO")
@Entity
public class PrecioHistoricoEJB implements Serializable, PrecioHistoricoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long precioId;
   private java.sql.Date fechaini;
   private java.sql.Date fechafin;
   private java.math.BigDecimal precioHis;
   private java.math.BigDecimal precio;

   public PrecioHistoricoEJB() {
   }

   public PrecioHistoricoEJB(com.spirit.facturacion.entity.PrecioHistoricoIf value) {
      setId(value.getId());
      setPrecioId(value.getPrecioId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setPrecioHis(value.getPrecioHis());
      setPrecio(value.getPrecio());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PrecioHistoricoIf value) {
      setId(value.getId());
      setPrecioId(value.getPrecioId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setPrecioHis(value.getPrecioHis());
      setPrecio(value.getPrecio());
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

   @Column(name = "PRECIO_ID")
   public java.lang.Long getPrecioId() {
      return precioId;
   }

   public void setPrecioId(java.lang.Long precioId) {
      this.precioId = precioId;
   }

   @Column(name = "FECHAINI")
   public java.sql.Date getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Date fechaini) {
      this.fechaini = fechaini;
   }

   @Column(name = "FECHAFIN")
   public java.sql.Date getFechafin() {
      return fechafin;
   }

   public void setFechafin(java.sql.Date fechafin) {
      this.fechafin = fechafin;
   }

   @Column(name = "PRECIO_HIS")
   public java.math.BigDecimal getPrecioHis() {
      return precioHis;
   }

   public void setPrecioHis(java.math.BigDecimal precioHis) {
      this.precioHis = precioHis;
   }

   @Column(name = "PRECIO")
   public java.math.BigDecimal getPrecio() {
      return precio;
   }

   public void setPrecio(java.math.BigDecimal precio) {
      this.precio = precio;
   }
	public String toString() {
		return ToStringer.toString((PrecioHistoricoIf)this);
	}
}
