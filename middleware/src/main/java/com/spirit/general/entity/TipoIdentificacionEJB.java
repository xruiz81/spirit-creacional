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

@Table(name = "TIPO_IDENTIFICACION")
@Entity
public class TipoIdentificacionEJB implements Serializable, TipoIdentificacionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String codigoSri;

   public TipoIdentificacionEJB() {
   }

   public TipoIdentificacionEJB(com.spirit.general.entity.TipoIdentificacionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setCodigoSri(value.getCodigoSri());
   }

   public java.lang.Long create(com.spirit.general.entity.TipoIdentificacionIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setCodigoSri(value.getCodigoSri());
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

   @Column(name = "CODIGO_SRI")
   public java.lang.String getCodigoSri() {
      return codigoSri;
   }

   public void setCodigoSri(java.lang.String codigoSri) {
      this.codigoSri = codigoSri;
   }
	public String toString() {
		return ToStringer.toString((TipoIdentificacionIf)this);
	}
}
