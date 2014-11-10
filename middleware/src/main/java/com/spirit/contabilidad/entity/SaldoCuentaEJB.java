package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SALDO_CUENTA")
@Entity
public class SaldoCuentaEJB implements Serializable, SaldoCuentaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long cuentaId;
   private java.lang.Long periodoId;
   private java.lang.String anio;
   private java.lang.String mes;
   private java.math.BigDecimal valordebe;
   private java.math.BigDecimal valorhaber;
   private java.math.BigDecimal valor;

   public SaldoCuentaEJB() {
   }

   public SaldoCuentaEJB(com.spirit.contabilidad.entity.SaldoCuentaIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setPeriodoId(value.getPeriodoId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setValordebe(value.getValordebe());
      setValorhaber(value.getValorhaber());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.SaldoCuentaIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setPeriodoId(value.getPeriodoId());
      setAnio(value.getAnio());
      setMes(value.getMes());
      setValordebe(value.getValordebe());
      setValorhaber(value.getValorhaber());
      setValor(value.getValor());
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

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   @Column(name = "PERIODO_ID")
   public java.lang.Long getPeriodoId() {
      return periodoId;
   }

   public void setPeriodoId(java.lang.Long periodoId) {
      this.periodoId = periodoId;
   }

   @Column(name = "ANIO")
   public java.lang.String getAnio() {
      return anio;
   }

   public void setAnio(java.lang.String anio) {
      this.anio = anio;
   }

   @Column(name = "MES")
   public java.lang.String getMes() {
      return mes;
   }

   public void setMes(java.lang.String mes) {
      this.mes = mes;
   }

   @Column(name = "VALORDEBE")
   public java.math.BigDecimal getValordebe() {
      return valordebe;
   }

   public void setValordebe(java.math.BigDecimal valordebe) {
      this.valordebe = valordebe;
   }

   @Column(name = "VALORHABER")
   public java.math.BigDecimal getValorhaber() {
      return valorhaber;
   }

   public void setValorhaber(java.math.BigDecimal valorhaber) {
      this.valorhaber = valorhaber;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((SaldoCuentaIf)this);
	}
}
