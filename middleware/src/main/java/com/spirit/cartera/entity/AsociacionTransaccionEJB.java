package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "ASOCIACION_TRANSACCION")
@Entity
public class AsociacionTransaccionEJB implements Serializable, AsociacionTransaccionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.sql.Timestamp fechaEmision;
   private java.sql.Timestamp fechaCreacion;
   private java.lang.String tablaOrigen;
   private java.lang.Long tipoDocumentoOrigenId;
   private java.lang.Long transaccionOrigenId;
   private java.lang.String tablaDestino;
   private java.lang.Long tipoDocumentoDestinoId;
   private java.lang.Long transaccionDestinoId;

   public AsociacionTransaccionEJB() {
   }

   public AsociacionTransaccionEJB(com.spirit.cartera.entity.AsociacionTransaccionIf value) {
      setId(value.getId());
      setFechaEmision(value.getFechaEmision());
      setFechaCreacion(value.getFechaCreacion());
      setTablaOrigen(value.getTablaOrigen());
      setTipoDocumentoOrigenId(value.getTipoDocumentoOrigenId());
      setTransaccionOrigenId(value.getTransaccionOrigenId());
      setTablaDestino(value.getTablaDestino());
      setTipoDocumentoDestinoId(value.getTipoDocumentoDestinoId());
      setTransaccionDestinoId(value.getTransaccionDestinoId());
   }

   public java.lang.Long create(com.spirit.cartera.entity.AsociacionTransaccionIf value) {
      setId(value.getId());
      setFechaEmision(value.getFechaEmision());
      setFechaCreacion(value.getFechaCreacion());
      setTablaOrigen(value.getTablaOrigen());
      setTipoDocumentoOrigenId(value.getTipoDocumentoOrigenId());
      setTransaccionOrigenId(value.getTransaccionOrigenId());
      setTablaDestino(value.getTablaDestino());
      setTipoDocumentoDestinoId(value.getTipoDocumentoDestinoId());
      setTransaccionDestinoId(value.getTransaccionDestinoId());
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

   @Column(name = "FECHA_EMISION")
   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "TABLA_ORIGEN")
   public java.lang.String getTablaOrigen() {
      return tablaOrigen;
   }

   public void setTablaOrigen(java.lang.String tablaOrigen) {
      this.tablaOrigen = tablaOrigen;
   }

   @Column(name = "TIPO_DOCUMENTO_ORIGEN_ID")
   public java.lang.Long getTipoDocumentoOrigenId() {
      return tipoDocumentoOrigenId;
   }

   public void setTipoDocumentoOrigenId(java.lang.Long tipoDocumentoOrigenId) {
      this.tipoDocumentoOrigenId = tipoDocumentoOrigenId;
   }

   @Column(name = "TRANSACCION_ORIGEN_ID")
   public java.lang.Long getTransaccionOrigenId() {
      return transaccionOrigenId;
   }

   public void setTransaccionOrigenId(java.lang.Long transaccionOrigenId) {
      this.transaccionOrigenId = transaccionOrigenId;
   }

   @Column(name = "TABLA_DESTINO")
   public java.lang.String getTablaDestino() {
      return tablaDestino;
   }

   public void setTablaDestino(java.lang.String tablaDestino) {
      this.tablaDestino = tablaDestino;
   }

   @Column(name = "TIPO_DOCUMENTO_DESTINO_ID")
   public java.lang.Long getTipoDocumentoDestinoId() {
      return tipoDocumentoDestinoId;
   }

   public void setTipoDocumentoDestinoId(java.lang.Long tipoDocumentoDestinoId) {
      this.tipoDocumentoDestinoId = tipoDocumentoDestinoId;
   }

   @Column(name = "TRANSACCION_DESTINO_ID")
   public java.lang.Long getTransaccionDestinoId() {
      return transaccionDestinoId;
   }

   public void setTransaccionDestinoId(java.lang.Long transaccionDestinoId) {
      this.transaccionDestinoId = transaccionDestinoId;
   }
	public String toString() {
		return ToStringer.toString((AsociacionTransaccionIf)this);
	}
}
