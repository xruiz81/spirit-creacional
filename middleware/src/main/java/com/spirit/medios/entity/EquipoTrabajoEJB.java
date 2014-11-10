package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "EQUIPO_TRABAJO")
@Entity
public class EquipoTrabajoEJB implements Serializable, EquipoTrabajoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long tipoordenId;
   private java.lang.Long empresaId;
   private java.sql.Date fechaini;
   private java.sql.Date fechafin;
   private java.lang.String estado;

   public EquipoTrabajoEJB() {
   }

   public EquipoTrabajoEJB(com.spirit.medios.entity.EquipoTrabajoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoordenId(value.getTipoordenId());
      setEmpresaId(value.getEmpresaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setEstado(value.getEstado());
   }

   public java.lang.Long create(com.spirit.medios.entity.EquipoTrabajoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setTipoordenId(value.getTipoordenId());
      setEmpresaId(value.getEmpresaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "TIPOORDEN_ID")
   public java.lang.Long getTipoordenId() {
      return tipoordenId;
   }

   public void setTipoordenId(java.lang.Long tipoordenId) {
      this.tipoordenId = tipoordenId;
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

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
	public String toString() {
		return ToStringer.toString((EquipoTrabajoIf)this);
	}
}
