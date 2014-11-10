package com.spirit.seguridad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "MENU")
@Entity
public class MenuEJB implements Serializable, MenuIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long padreId;
   private java.lang.Integer nivel;
   private java.lang.Integer favorito;
   private java.lang.String panel;

   public MenuEJB() {
   }

   public MenuEJB(com.spirit.seguridad.entity.MenuIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setPadreId(value.getPadreId());
      setNivel(value.getNivel());
      setFavorito(value.getFavorito());
      setPanel(value.getPanel());
   }

   public java.lang.Long create(com.spirit.seguridad.entity.MenuIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setPadreId(value.getPadreId());
      setNivel(value.getNivel());
      setFavorito(value.getFavorito());
      setPanel(value.getPanel());
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

   @Column(name = "PADRE_ID")
   public java.lang.Long getPadreId() {
      return padreId;
   }

   public void setPadreId(java.lang.Long padreId) {
      this.padreId = padreId;
   }

   @Column(name = "NIVEL")
   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   @Column(name = "FAVORITO")
   public java.lang.Integer getFavorito() {
      return favorito;
   }

   public void setFavorito(java.lang.Integer favorito) {
      this.favorito = favorito;
   }

   @Column(name = "PANEL")
   public java.lang.String getPanel() {
      return panel;
   }

   public void setPanel(java.lang.String panel) {
      this.panel = panel;
   }
	public String toString() {
		return ToStringer.toString((MenuIf)this);
	}
}
