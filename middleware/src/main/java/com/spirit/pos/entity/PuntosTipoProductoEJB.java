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

@Table(name = "PUNTOS_TIPO_PRODUCTO")
@Entity
public class PuntosTipoProductoEJB implements Serializable, PuntosTipoProductoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoProductoId;
   private java.lang.Long tarjetaTipoId;
   private java.math.BigDecimal puntosReferido;
   private java.math.BigDecimal puntosCompras;
   private java.math.BigDecimal porcentajeDineroReferido;
   private java.math.BigDecimal porcentajeDineroCompras;

   public PuntosTipoProductoEJB() {
   }

   public PuntosTipoProductoEJB(com.spirit.pos.entity.PuntosTipoProductoIf value) {
      setId(value.getId());
      setTipoProductoId(value.getTipoProductoId());
      setTarjetaTipoId(value.getTarjetaTipoId());
      setPuntosReferido(value.getPuntosReferido());
      setPuntosCompras(value.getPuntosCompras());
      setPorcentajeDineroReferido(value.getPorcentajeDineroReferido());
      setPorcentajeDineroCompras(value.getPorcentajeDineroCompras());
   }

   public java.lang.Long create(com.spirit.pos.entity.PuntosTipoProductoIf value) {
      setId(value.getId());
      setTipoProductoId(value.getTipoProductoId());
      setTarjetaTipoId(value.getTarjetaTipoId());
      setPuntosReferido(value.getPuntosReferido());
      setPuntosCompras(value.getPuntosCompras());
      setPorcentajeDineroReferido(value.getPorcentajeDineroReferido());
      setPorcentajeDineroCompras(value.getPorcentajeDineroCompras());
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

   @Column(name = "TIPO_PRODUCTO_ID")
   public java.lang.Long getTipoProductoId() {
      return tipoProductoId;
   }

   public void setTipoProductoId(java.lang.Long tipoProductoId) {
      this.tipoProductoId = tipoProductoId;
   }

   @Column(name = "TARJETA_TIPO_ID")
   public java.lang.Long getTarjetaTipoId() {
      return tarjetaTipoId;
   }

   public void setTarjetaTipoId(java.lang.Long tarjetaTipoId) {
      this.tarjetaTipoId = tarjetaTipoId;
   }

   @Column(name = "PUNTOS_REFERIDO")
   public java.math.BigDecimal getPuntosReferido() {
      return puntosReferido;
   }

   public void setPuntosReferido(java.math.BigDecimal puntosReferido) {
      this.puntosReferido = puntosReferido;
   }

   @Column(name = "PUNTOS_COMPRAS")
   public java.math.BigDecimal getPuntosCompras() {
      return puntosCompras;
   }

   public void setPuntosCompras(java.math.BigDecimal puntosCompras) {
      this.puntosCompras = puntosCompras;
   }

   @Column(name = "PORCENTAJE_DINERO_REFERIDO")
   public java.math.BigDecimal getPorcentajeDineroReferido() {
      return porcentajeDineroReferido;
   }

   public void setPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) {
      this.porcentajeDineroReferido = porcentajeDineroReferido;
   }

   @Column(name = "PORCENTAJE_DINERO_COMPRAS")
   public java.math.BigDecimal getPorcentajeDineroCompras() {
      return porcentajeDineroCompras;
   }

   public void setPorcentajeDineroCompras(java.math.BigDecimal porcentajeDineroCompras) {
      this.porcentajeDineroCompras = porcentajeDineroCompras;
   }
	public String toString() {
		return ToStringer.toString((PuntosTipoProductoIf)this);
	}
}
