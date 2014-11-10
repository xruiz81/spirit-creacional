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

@Table(name = "TECLAS_CONFIGURACION")
@Entity
public class TeclasConfiguracionEJB implements Serializable, TeclasConfiguracionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String teclasNombre;
   private java.lang.String descripcion;
   private java.lang.String codigo;
   private java.lang.String estado;
   private java.lang.String mascara;

   public TeclasConfiguracionEJB() {
   }

   public TeclasConfiguracionEJB(com.spirit.pos.entity.TeclasConfiguracionIf value) {
      setId(value.getId());
      setTeclasNombre(value.getTeclasNombre());
      setDescripcion(value.getDescripcion());
      setCodigo(value.getCodigo());
      setEstado(value.getEstado());
      setMascara(value.getMascara());
   }

   public java.lang.Long create(com.spirit.pos.entity.TeclasConfiguracionIf value) {
      setId(value.getId());
      setTeclasNombre(value.getTeclasNombre());
      setDescripcion(value.getDescripcion());
      setCodigo(value.getCodigo());
      setEstado(value.getEstado());
      setMascara(value.getMascara());
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

   @Column(name = "TECLAS_NOMBRE")
   public java.lang.String getTeclasNombre() {
      return teclasNombre;
   }

   public void setTeclasNombre(java.lang.String teclasNombre) {
      this.teclasNombre = teclasNombre;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "MASCARA")
   public java.lang.String getMascara() {
      return mascara;
   }

   public void setMascara(java.lang.String mascara) {
      this.mascara = mascara;
   }
	public String toString() {
		return ToStringer.toString((TeclasConfiguracionIf)this);
	}
}
