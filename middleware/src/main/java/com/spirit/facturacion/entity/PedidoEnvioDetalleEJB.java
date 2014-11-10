package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PEDIDO_ENVIO_DETALLE")
@Entity
public class PedidoEnvioDetalleEJB implements Serializable, PedidoEnvioDetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long pedidoEnvioId;
   private java.lang.String codigoBarras;
   private java.math.BigDecimal cantidad;
   private java.lang.String incluyeIva;
   private java.math.BigDecimal valor;

   public PedidoEnvioDetalleEJB() {
   }

   public PedidoEnvioDetalleEJB(com.spirit.facturacion.entity.PedidoEnvioDetalleIf value) {
      setId(value.getId());
      setPedidoEnvioId(value.getPedidoEnvioId());
      setCodigoBarras(value.getCodigoBarras());
      setCantidad(value.getCantidad());
      setIncluyeIva(value.getIncluyeIva());
      setValor(value.getValor());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PedidoEnvioDetalleIf value) {
      setId(value.getId());
      setPedidoEnvioId(value.getPedidoEnvioId());
      setCodigoBarras(value.getCodigoBarras());
      setCantidad(value.getCantidad());
      setIncluyeIva(value.getIncluyeIva());
      setValor(value.getValor());
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

   @Column(name = "PEDIDO_ENVIO_ID")
   public java.lang.Long getPedidoEnvioId() {
      return pedidoEnvioId;
   }

   public void setPedidoEnvioId(java.lang.Long pedidoEnvioId) {
      this.pedidoEnvioId = pedidoEnvioId;
   }

   @Column(name = "CODIGO_BARRAS")
   public java.lang.String getCodigoBarras() {
      return codigoBarras;
   }

   public void setCodigoBarras(java.lang.String codigoBarras) {
      this.codigoBarras = codigoBarras;
   }

   @Column(name = "CANTIDAD")
   public java.math.BigDecimal getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.math.BigDecimal cantidad) {
      this.cantidad = cantidad;
   }

   @Column(name = "INCLUYE_IVA")
   public java.lang.String getIncluyeIva() {
      return incluyeIva;
   }

   public void setIncluyeIva(java.lang.String incluyeIva) {
      this.incluyeIva = incluyeIva;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }
	public String toString() {
		return ToStringer.toString((PedidoEnvioDetalleIf)this);
	}
}
