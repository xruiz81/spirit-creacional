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

@Table(name = "SRI_TIPO_COMPROBANTE")
@Entity
public class SriTipoComprobanteEJB implements Serializable, SriTipoComprobanteIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String nombre;
   private java.lang.String codigo;
   private java.lang.Long anulacionTipoComprobanteId;

   public SriTipoComprobanteEJB() {
   }

   public SriTipoComprobanteEJB(com.spirit.sri.entity.SriTipoComprobanteIf value) {
      setId(value.getId());
      setNombre(value.getNombre());
      setCodigo(value.getCodigo());
      setAnulacionTipoComprobanteId(value.getAnulacionTipoComprobanteId());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriTipoComprobanteIf value) {
      setId(value.getId());
      setNombre(value.getNombre());
      setCodigo(value.getCodigo());
      setAnulacionTipoComprobanteId(value.getAnulacionTipoComprobanteId());
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "ANULACION_TIPO_COMPROBANTE_ID")
   public java.lang.Long getAnulacionTipoComprobanteId() {
      return anulacionTipoComprobanteId;
   }

   public void setAnulacionTipoComprobanteId(java.lang.Long anulacionTipoComprobanteId) {
      this.anulacionTipoComprobanteId = anulacionTipoComprobanteId;
   }
	public String toString() {
		return ToStringer.toString((SriTipoComprobanteIf)this);
	}
}
