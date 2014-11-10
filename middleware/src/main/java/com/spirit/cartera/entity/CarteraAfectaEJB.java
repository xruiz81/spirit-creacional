package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CARTERA_AFECTA")
@Entity
public class CarteraAfectaEJB implements Serializable, CarteraAfectaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long carteradetalleId;
   private java.lang.Long carteraafectaId;
   private java.lang.Long usuarioId;
   private java.math.BigDecimal valor;
   private java.sql.Date fechaCreacion;
   private java.sql.Date fechaAplicacion;
   private java.lang.String cartera;

   public CarteraAfectaEJB() {
   }

   public CarteraAfectaEJB(com.spirit.cartera.entity.CarteraAfectaIf value) {
      setId(value.getId());
      setCarteradetalleId(value.getCarteradetalleId());
      setCarteraafectaId(value.getCarteraafectaId());
      setUsuarioId(value.getUsuarioId());
      setValor(value.getValor());
      setFechaCreacion(value.getFechaCreacion());
      setFechaAplicacion(value.getFechaAplicacion());
      setCartera(value.getCartera());
   }

   public java.lang.Long create(com.spirit.cartera.entity.CarteraAfectaIf value) {
      setId(value.getId());
      setCarteradetalleId(value.getCarteradetalleId());
      setCarteraafectaId(value.getCarteraafectaId());
      setUsuarioId(value.getUsuarioId());
      setValor(value.getValor());
      setFechaCreacion(value.getFechaCreacion());
      setFechaAplicacion(value.getFechaAplicacion());
      setCartera(value.getCartera());
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

   @Column(name = "CARTERADETALLE_ID")
   public java.lang.Long getCarteradetalleId() {
      return carteradetalleId;
   }

   public void setCarteradetalleId(java.lang.Long carteradetalleId) {
      this.carteradetalleId = carteradetalleId;
   }

   @Column(name = "CARTERAAFECTA_ID")
   public java.lang.Long getCarteraafectaId() {
      return carteraafectaId;
   }

   public void setCarteraafectaId(java.lang.Long carteraafectaId) {
      this.carteraafectaId = carteraafectaId;
   }

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_APLICACION")
   public java.sql.Date getFechaAplicacion() {
      return fechaAplicacion;
   }

   public void setFechaAplicacion(java.sql.Date fechaAplicacion) {
      this.fechaAplicacion = fechaAplicacion;
   }

   @Column(name = "CARTERA")
   public java.lang.String getCartera() {
      return cartera;
   }

   public void setCartera(java.lang.String cartera) {
      this.cartera = cartera;
   }
	public String toString() {
		return ToStringer.toString((CarteraAfectaIf)this);
	}
}
