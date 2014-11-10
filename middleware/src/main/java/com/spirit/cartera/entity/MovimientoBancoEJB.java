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

@Table(name = "MOVIMIENTO_BANCO")
@Entity
public class MovimientoBancoEJB implements Serializable, MovimientoBancoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long cuentaId;
   private java.lang.Long documentoId;
   private java.lang.String referencia;
   private java.sql.Timestamp fecha;
   private java.math.BigDecimal valor;

   public MovimientoBancoEJB() {
   }

   public MovimientoBancoEJB(com.spirit.cartera.entity.MovimientoBancoIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setDocumentoId(value.getDocumentoId());
      setReferencia(value.getReferencia());
      setFecha(value.getFecha());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.cartera.entity.MovimientoBancoIf value) {
      setId(value.getId());
      setCuentaId(value.getCuentaId());
      setDocumentoId(value.getDocumentoId());
      setReferencia(value.getReferencia());
      setFecha(value.getFecha());
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

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((MovimientoBancoIf)this);
	}
}
