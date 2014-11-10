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

@Table(name = "CAJASESION_MOVIMIENTOS")
@Entity
public class CajasesionMovimientosEJB implements Serializable, CajasesionMovimientosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long cajasesionId;
   private java.math.BigDecimal valor;
   private java.lang.String tipomovimiento;
   private java.lang.Long cuentaId;
   private java.lang.Long cajadestinoId;
   private java.lang.String observacion;
   private java.sql.Timestamp fecha;
   private java.lang.String revisado;
   private java.lang.String numDoc;

   public CajasesionMovimientosEJB() {
   }

   public CajasesionMovimientosEJB(com.spirit.pos.entity.CajasesionMovimientosIf value) {
      setId(value.getId());
      setCajasesionId(value.getCajasesionId());
      setValor(value.getValor());
      setTipomovimiento(value.getTipomovimiento());
      setCuentaId(value.getCuentaId());
      setCajadestinoId(value.getCajadestinoId());
      setObservacion(value.getObservacion());
      setFecha(value.getFecha());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
   }

   public java.lang.Long create(com.spirit.pos.entity.CajasesionMovimientosIf value) {
      setId(value.getId());
      setCajasesionId(value.getCajasesionId());
      setValor(value.getValor());
      setTipomovimiento(value.getTipomovimiento());
      setCuentaId(value.getCuentaId());
      setCajadestinoId(value.getCajadestinoId());
      setObservacion(value.getObservacion());
      setFecha(value.getFecha());
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

   @Column(name = "CAJASESION_ID")
   public java.lang.Long getCajasesionId() {
      return cajasesionId;
   }

   public void setCajasesionId(java.lang.Long cajasesionId) {
      this.cajasesionId = cajasesionId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "TIPOMOVIMIENTO")
   public java.lang.String getTipomovimiento() {
      return tipomovimiento;
   }

   public void setTipomovimiento(java.lang.String tipomovimiento) {
      this.tipomovimiento = tipomovimiento;
   }

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   @Column(name = "CAJADESTINO_ID")
   public java.lang.Long getCajadestinoId() {
      return cajadestinoId;
   }

   public void setCajadestinoId(java.lang.Long cajadestinoId) {
      this.cajadestinoId = cajadestinoId;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
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
		return ToStringer.toString((CajasesionMovimientosIf)this);
	}
}
