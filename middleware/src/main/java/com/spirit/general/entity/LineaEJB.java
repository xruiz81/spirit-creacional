package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "LINEA")
@Entity
public class LineaEJB implements Serializable, LineaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.Integer nivel;
   private java.lang.String aceptaitem;
   private java.lang.Long lineaId;

   public LineaEJB() {
   }

   public LineaEJB(com.spirit.general.entity.LineaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setNivel(value.getNivel());
      setAceptaitem(value.getAceptaitem());
      setLineaId(value.getLineaId());
   }

   public java.lang.Long create(com.spirit.general.entity.LineaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setNivel(value.getNivel());
      setAceptaitem(value.getAceptaitem());
      setLineaId(value.getLineaId());
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "NIVEL")
   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   @Column(name = "ACEPTAITEM")
   public java.lang.String getAceptaitem() {
      return aceptaitem;
   }

   public void setAceptaitem(java.lang.String aceptaitem) {
      this.aceptaitem = aceptaitem;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }
	public String toString() {
		return ToStringer.toString((LineaIf)this);
	}
}
