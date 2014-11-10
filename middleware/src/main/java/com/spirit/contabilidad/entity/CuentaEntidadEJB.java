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

@Table(name = "CUENTA_ENTIDAD")
@Entity
public class CuentaEntidadEJB implements Serializable, CuentaEntidadIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long entidadId;
   private java.lang.String tipoEntidad;
   private java.lang.String nemonico;
   private java.lang.Long cuentaId;
   private java.lang.Long oficinaId;

   public CuentaEntidadEJB() {
   }

   public CuentaEntidadEJB(com.spirit.contabilidad.entity.CuentaEntidadIf value) {
      setId(value.getId());
      setEntidadId(value.getEntidadId());
      setTipoEntidad(value.getTipoEntidad());
      setNemonico(value.getNemonico());
      setCuentaId(value.getCuentaId());
      setOficinaId(value.getOficinaId());
   }

   public java.lang.Long create(com.spirit.contabilidad.entity.CuentaEntidadIf value) {
      setId(value.getId());
      setEntidadId(value.getEntidadId());
      setTipoEntidad(value.getTipoEntidad());
      setNemonico(value.getNemonico());
      setCuentaId(value.getCuentaId());
      setOficinaId(value.getOficinaId());
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

   @Column(name = "ENTIDAD_ID")
   public java.lang.Long getEntidadId() {
      return entidadId;
   }

   public void setEntidadId(java.lang.Long entidadId) {
      this.entidadId = entidadId;
   }

   @Column(name = "TIPO_ENTIDAD")
   public java.lang.String getTipoEntidad() {
      return tipoEntidad;
   }

   public void setTipoEntidad(java.lang.String tipoEntidad) {
      this.tipoEntidad = tipoEntidad;
   }

   @Column(name = "NEMONICO")
   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }

   @Column(name = "CUENTA_ID")
   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }
	public String toString() {
		return ToStringer.toString((CuentaEntidadIf)this);
	}
}
