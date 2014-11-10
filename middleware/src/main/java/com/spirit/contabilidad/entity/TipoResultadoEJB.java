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

@Table(name = "TIPO_RESULTADO")
@Entity
public class TipoResultadoEJB implements Serializable, TipoResultadoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long orden;
   private java.lang.String utilidad;
   private java.lang.String leyendaResultado;

   public TipoResultadoEJB() {
   }

   public TipoResultadoEJB(com.spirit.contabilidad.entity.TipoResultadoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setOrden(value.getOrden());
      setUtilidad(value.getUtilidad());
      setLeyendaResultado(value.getLeyendaResultado());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.TipoResultadoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setOrden(value.getOrden());
      setUtilidad(value.getUtilidad());
      setLeyendaResultado(value.getLeyendaResultado());
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

   @Column(name = "ORDEN")
   public java.lang.Long getOrden() {
      return orden;
   }

   public void setOrden(java.lang.Long orden) {
      this.orden = orden;
   }

   @Column(name = "UTILIDAD")
   public java.lang.String getUtilidad() {
      return utilidad;
   }

   public void setUtilidad(java.lang.String utilidad) {
      this.utilidad = utilidad;
   }

   @Column(name = "LEYENDA_RESULTADO")
   public java.lang.String getLeyendaResultado() {
      return leyendaResultado;
   }

   public void setLeyendaResultado(java.lang.String leyendaResultado) {
      this.leyendaResultado = leyendaResultado;
   }
	public String toString() {
		return ToStringer.toString((TipoResultadoIf)this);
	}
}
