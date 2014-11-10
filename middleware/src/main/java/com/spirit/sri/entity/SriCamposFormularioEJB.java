package com.spirit.sri.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "SRI_CAMPOS_FORMULARIO")
@Entity
public class SriCamposFormularioEJB implements Serializable, SriCamposFormularioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String impuesto;
   private java.lang.String codigo;
   private java.lang.String concepto;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.String valor;

   public SriCamposFormularioEJB() {
   }

   public SriCamposFormularioEJB(com.spirit.sri.entity.SriCamposFormularioIf value) {
      setId(value.getId());
      setImpuesto(value.getImpuesto());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriCamposFormularioIf value) {
      setId(value.getId());
      setImpuesto(value.getImpuesto());
      setCodigo(value.getCodigo());
      setConcepto(value.getConcepto());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
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

   @Column(name = "IMPUESTO")
   public java.lang.String getImpuesto() {
      return impuesto;
   }

   public void setImpuesto(java.lang.String impuesto) {
      this.impuesto = impuesto;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "CONCEPTO")
   public java.lang.String getConcepto() {
      return concepto;
   }

   public void setConcepto(java.lang.String concepto) {
      this.concepto = concepto;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "VALOR")
   public java.lang.String getValor() {
      return valor;
   }

   public void setValor(java.lang.String valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((SriCamposFormularioIf)this);
	}
}
