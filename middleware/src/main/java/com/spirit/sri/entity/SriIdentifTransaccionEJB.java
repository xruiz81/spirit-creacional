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

@Table(name = "SRI_IDENTIF_TRANSACCION")
@Entity
public class SriIdentifTransaccionEJB implements Serializable, SriIdentifTransaccionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long idTipoTransaccion;
   private java.lang.Long idTipoIdentificacion;
   private java.lang.String codigo;

   public SriIdentifTransaccionEJB() {
   }

   public SriIdentifTransaccionEJB(com.spirit.sri.entity.SriIdentifTransaccionIf value) {
      setId(value.getId());
      setIdTipoTransaccion(value.getIdTipoTransaccion());
      setIdTipoIdentificacion(value.getIdTipoIdentificacion());
      setCodigo(value.getCodigo());
   }

   public java.lang.Long create(com.spirit.sri.entity.SriIdentifTransaccionIf value) {
      setId(value.getId());
      setIdTipoTransaccion(value.getIdTipoTransaccion());
      setIdTipoIdentificacion(value.getIdTipoIdentificacion());
      setCodigo(value.getCodigo());
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

   @Column(name = "ID_TIPO_TRANSACCION")
   public java.lang.Long getIdTipoTransaccion() {
      return idTipoTransaccion;
   }

   public void setIdTipoTransaccion(java.lang.Long idTipoTransaccion) {
      this.idTipoTransaccion = idTipoTransaccion;
   }

   @Column(name = "ID_TIPO_IDENTIFICACION")
   public java.lang.Long getIdTipoIdentificacion() {
      return idTipoIdentificacion;
   }

   public void setIdTipoIdentificacion(java.lang.Long idTipoIdentificacion) {
      this.idTipoIdentificacion = idTipoIdentificacion;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
	public String toString() {
		return ToStringer.toString((SriIdentifTransaccionIf)this);
	}
}
