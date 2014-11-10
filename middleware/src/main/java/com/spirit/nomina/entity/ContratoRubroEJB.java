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

@Table(name = "CONTRATO_RUBRO")
@Entity
public class ContratoRubroEJB implements Serializable, ContratoRubroIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Date fechaFin;
   private java.lang.String estado;
   private java.sql.Date fechaInicio;
   private java.math.BigDecimal valor;
   private java.lang.Long contratoId;
   private java.lang.Long rubroId;

   public ContratoRubroEJB() {
   }

   public ContratoRubroEJB(com.spirit.nomina.entity.ContratoRubroIf value) {
      setId(value.getId());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setFechaInicio(value.getFechaInicio());
      setValor(value.getValor());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
   }

   public java.lang.Long create(com.spirit.nomina.entity.ContratoRubroIf value) {
      setId(value.getId());
      setFechaFin(value.getFechaFin());
      setEstado(value.getEstado());
      setFechaInicio(value.getFechaInicio());
      setValor(value.getValor());
      setContratoId(value.getContratoId());
      setRubroId(value.getRubroId());
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

   @Column(name = "FECHA_INICIO")
   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "CONTRATO_ID")
   public java.lang.Long getContratoId() {
      return contratoId;
   }

   public void setContratoId(java.lang.Long contratoId) {
      this.contratoId = contratoId;
   }

   @Column(name = "RUBRO_ID")
   public java.lang.Long getRubroId() {
      return rubroId;
   }

   public void setRubroId(java.lang.Long rubroId) {
      this.rubroId = rubroId;
   }
	public String toString() {
		return ToStringer.toString((ContratoRubroIf)this);
	}
}
