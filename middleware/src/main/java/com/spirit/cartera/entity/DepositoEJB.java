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

@Table(name = "DEPOSITO")
@Entity
public class DepositoEJB implements Serializable, DepositoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long oficinaId;
   private java.lang.Long cuentabancariaId;
   private java.sql.Date fechaCreacion;
   private java.sql.Date fechaDeposito;
   private java.math.BigDecimal valor;
   private java.lang.String estado;

   public DepositoEJB() {
   }

   public DepositoEJB(com.spirit.cartera.entity.DepositoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setCuentabancariaId(value.getCuentabancariaId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaDeposito(value.getFechaDeposito());
      setValor(value.getValor());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.cartera.entity.DepositoIf value) {
      setId(value.getId());
      setOficinaId(value.getOficinaId());
      setCuentabancariaId(value.getCuentabancariaId());
      setFechaCreacion(value.getFechaCreacion());
      setFechaDeposito(value.getFechaDeposito());
      setValor(value.getValor());
      setEstado(value.getEstado());
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

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "CUENTABANCARIA_ID")
   public java.lang.Long getCuentabancariaId() {
      return cuentabancariaId;
   }

   public void setCuentabancariaId(java.lang.Long cuentabancariaId) {
      this.cuentabancariaId = cuentabancariaId;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "FECHA_DEPOSITO")
   public java.sql.Date getFechaDeposito() {
      return fechaDeposito;
   }

   public void setFechaDeposito(java.sql.Date fechaDeposito) {
      this.fechaDeposito = fechaDeposito;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((DepositoIf)this);
	}
}
