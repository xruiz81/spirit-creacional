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

@Table(name = "CUENTAS_POR_PAGAR")
@Entity
public class CuentasPorPagarEJB implements Serializable, CuentasPorPagarIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long carteraId;
   private java.lang.String codigo;
   private java.lang.String preimpreso;
   private java.sql.Timestamp fechaEmision;
   private java.lang.String comentario;
   private java.lang.Long tipodocumentoId;
   private java.lang.Long referenciaId;
   private java.math.BigDecimal valor;
   private java.math.BigDecimal saldo;
   private java.lang.Long compraId;
   private java.sql.Timestamp fechaCompra;
   private java.lang.Long oficinaId;
   private java.lang.String observacion;
   private java.lang.Long proveedorId;
   private java.lang.String razonSocial;
   private java.lang.String identificacion;
   private java.lang.Long proveedorOficinaId;
   private java.lang.Long tipoProveedorId;
   private java.lang.String codigoTipoProveedor;
   private java.lang.String tipoProveedor;

   public CuentasPorPagarEJB() {
   }

   public CuentasPorPagarEJB(com.spirit.cartera.entity.CuentasPorPagarIf value) {
      setId(value.getId());
      setCarteraId(value.getCarteraId());
      setCodigo(value.getCodigo());
      setPreimpreso(value.getPreimpreso());
      setFechaEmision(value.getFechaEmision());
      setComentario(value.getComentario());
      setTipodocumentoId(value.getTipodocumentoId());
      setReferenciaId(value.getReferenciaId());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setCompraId(value.getCompraId());
      setFechaCompra(value.getFechaCompra());
      setOficinaId(value.getOficinaId());
      setObservacion(value.getObservacion());
      setProveedorId(value.getProveedorId());
      setRazonSocial(value.getRazonSocial());
      setIdentificacion(value.getIdentificacion());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setTipoProveedorId(value.getTipoProveedorId());
      setCodigoTipoProveedor(value.getCodigoTipoProveedor());
      setTipoProveedor(value.getTipoProveedor());
   }

   public java.lang.Long create(com.spirit.cartera.entity.CuentasPorPagarIf value) {
      setId(value.getId());
      setCarteraId(value.getCarteraId());
      setCodigo(value.getCodigo());
      setPreimpreso(value.getPreimpreso());
      setFechaEmision(value.getFechaEmision());
      setComentario(value.getComentario());
      setTipodocumentoId(value.getTipodocumentoId());
      setReferenciaId(value.getReferenciaId());
      setValor(value.getValor());
      setSaldo(value.getSaldo());
      setCompraId(value.getCompraId());
      setFechaCompra(value.getFechaCompra());
      setOficinaId(value.getOficinaId());
      setObservacion(value.getObservacion());
      setProveedorId(value.getProveedorId());
      setRazonSocial(value.getRazonSocial());
      setIdentificacion(value.getIdentificacion());
      setProveedorOficinaId(value.getProveedorOficinaId());
      setTipoProveedorId(value.getTipoProveedorId());
      setCodigoTipoProveedor(value.getCodigoTipoProveedor());
      setTipoProveedor(value.getTipoProveedor());
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

   @Column(name = "CARTERA_ID")
   public java.lang.Long getCarteraId() {
      return carteraId;
   }

   public void setCarteraId(java.lang.Long carteraId) {
      this.carteraId = carteraId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "PREIMPRESO")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "FECHA_EMISION")
   public java.sql.Timestamp getFechaEmision() {
      return fechaEmision;
   }

   public void setFechaEmision(java.sql.Timestamp fechaEmision) {
      this.fechaEmision = fechaEmision;
   }

   @Column(name = "COMENTARIO")
   public java.lang.String getComentario() {
      return comentario;
   }

   public void setComentario(java.lang.String comentario) {
      this.comentario = comentario;
   }

   @Column(name = "TIPODOCUMENTO_ID")
   public java.lang.Long getTipodocumentoId() {
      return tipodocumentoId;
   }

   public void setTipodocumentoId(java.lang.Long tipodocumentoId) {
      this.tipodocumentoId = tipodocumentoId;
   }

   @Column(name = "REFERENCIA_ID")
   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "SALDO")
   public java.math.BigDecimal getSaldo() {
      return saldo;
   }

   public void setSaldo(java.math.BigDecimal saldo) {
      this.saldo = saldo;
   }

   @Column(name = "COMPRA_ID")
   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   @Column(name = "FECHA_COMPRA")
   public java.sql.Timestamp getFechaCompra() {
      return fechaCompra;
   }

   public void setFechaCompra(java.sql.Timestamp fechaCompra) {
      this.fechaCompra = fechaCompra;
   }

   @Column(name = "OFICINA_ID")
   public java.lang.Long getOficinaId() {
      return oficinaId;
   }

   public void setOficinaId(java.lang.Long oficinaId) {
      this.oficinaId = oficinaId;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "PROVEEDOR_ID")
   public java.lang.Long getProveedorId() {
      return proveedorId;
   }

   public void setProveedorId(java.lang.Long proveedorId) {
      this.proveedorId = proveedorId;
   }

   @Column(name = "RAZON_SOCIAL")
   public java.lang.String getRazonSocial() {
      return razonSocial;
   }

   public void setRazonSocial(java.lang.String razonSocial) {
      this.razonSocial = razonSocial;
   }

   @Column(name = "IDENTIFICACION")
   public java.lang.String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(java.lang.String identificacion) {
      this.identificacion = identificacion;
   }

   @Column(name = "PROVEEDOR_OFICINA_ID")
   public java.lang.Long getProveedorOficinaId() {
      return proveedorOficinaId;
   }

   public void setProveedorOficinaId(java.lang.Long proveedorOficinaId) {
      this.proveedorOficinaId = proveedorOficinaId;
   }

   @Column(name = "TIPO_PROVEEDOR_ID")
   public java.lang.Long getTipoProveedorId() {
      return tipoProveedorId;
   }

   public void setTipoProveedorId(java.lang.Long tipoProveedorId) {
      this.tipoProveedorId = tipoProveedorId;
   }

   @Column(name = "CODIGO_TIPO_PROVEEDOR")
   public java.lang.String getCodigoTipoProveedor() {
      return codigoTipoProveedor;
   }

   public void setCodigoTipoProveedor(java.lang.String codigoTipoProveedor) {
      this.codigoTipoProveedor = codigoTipoProveedor;
   }

   @Column(name = "TIPO_PROVEEDOR")
   public java.lang.String getTipoProveedor() {
      return tipoProveedor;
   }

   public void setTipoProveedor(java.lang.String tipoProveedor) {
      this.tipoProveedor = tipoProveedor;
   }
	public String toString() {
		return ToStringer.toString((CuentasPorPagarIf)this);
	}
}
