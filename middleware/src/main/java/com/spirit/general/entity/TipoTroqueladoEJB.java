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

@Table(name = "TIPO_TROQUELADO")
@Entity
public class TipoTroqueladoEJB implements Serializable, TipoTroqueladoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String descripcion;
   private java.lang.Integer numeroSeccionesHoja;

   public TipoTroqueladoEJB() {
   }

   public TipoTroqueladoEJB(com.spirit.general.entity.TipoTroqueladoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
      setNumeroSeccionesHoja(value.getNumeroSeccionesHoja());
   }

   public java.lang.Long create(com.spirit.general.entity.TipoTroqueladoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
      setNumeroSeccionesHoja(value.getNumeroSeccionesHoja());
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

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "NUMERO_SECCIONES_HOJA")
   public java.lang.Integer getNumeroSeccionesHoja() {
      return numeroSeccionesHoja;
   }

   public void setNumeroSeccionesHoja(java.lang.Integer numeroSeccionesHoja) {
      this.numeroSeccionesHoja = numeroSeccionesHoja;
   }
	public String toString() {
		return ToStringer.toString((TipoTroqueladoIf)this);
	}
}
