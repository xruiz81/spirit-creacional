package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "VENTAS_PAGOS")
@Entity
public class VentasPagosEJB implements Serializable, VentasPagosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.math.BigDecimal valor;
   private java.lang.Long tipo;
   private java.lang.String referencia;
   private java.lang.Long referenciaId;
   private java.lang.Long ventastrackId;
   private java.lang.String revisado;
   private java.lang.String numDoc;

   public VentasPagosEJB() {
   }

   public VentasPagosEJB(com.spirit.pos.entity.VentasPagosIf value) {
      setId(value.getId());
      setValor(value.getValor());
      setTipo(value.getTipo());
      setReferencia(value.getReferencia());
      setReferenciaId(value.getReferenciaId());
      setVentastrackId(value.getVentastrackId());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
   }

   public java.lang.Long create(com.spirit.pos.entity.VentasPagosIf value) {
      setId(value.getId());
      setValor(value.getValor());
      setTipo(value.getTipo());
      setReferencia(value.getReferencia());
      setReferenciaId(value.getReferenciaId());
      setVentastrackId(value.getVentastrackId());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
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

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "TIPO")
   public java.lang.Long getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.Long tipo) {
      this.tipo = tipo;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   @Column(name = "VENTASTRACK_ID")
   public java.lang.Long getVentastrackId() {
      return ventastrackId;
   }

   public void setVentastrackId(java.lang.Long ventastrackId) {
      this.ventastrackId = ventastrackId;
   }

   @Column(name = "REVISADO")
   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }

   @Column(name = "NUM_DOC")
   public java.lang.String getNumDoc() {
      return numDoc;
   }

   public void setNumDoc(java.lang.String numDoc) {
      this.numDoc = numDoc;
   }
	public String toString() {
		return ToStringer.toString((VentasPagosIf)this);
	}
}
