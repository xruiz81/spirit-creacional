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

@Table(name = "PERIODO")
@Entity
public class PeriodoEJB implements Serializable, PeriodoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long empresaId;
   private java.sql.Date fechaini;
   private java.sql.Date fechafin;
   private java.lang.String status;
   private java.lang.Long orden;

   public PeriodoEJB() {
   }

   public PeriodoEJB(com.spirit.contabilidad.entity.PeriodoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setStatus(value.getStatus());
      setOrden(value.getOrden());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.PeriodoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setStatus(value.getStatus());
      setOrden(value.getOrden());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
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

   @Column(name = "STATUS")
   public java.lang.String getStatus() {
      return status;
   }

   public void setStatus(java.lang.String status) {
      this.status = status;
   }

   @Column(name = "ORDEN")
   public java.lang.Long getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Long orden) {
      this.orden = orden;
   }
	public String toString() {
		return ToStringer.toString((PeriodoIf)this);
	}
}
