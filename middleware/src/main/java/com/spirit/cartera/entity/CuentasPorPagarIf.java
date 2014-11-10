package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CuentasPorPagarIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCarteraId();

   void setCarteraId(java.lang.Long carteraId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.sql.Timestamp getFechaEmision();

   void setFechaEmision(java.sql.Timestamp fechaEmision);

   java.lang.String getComentario();

   void setComentario(java.lang.String comentario);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getSaldo();

   void setSaldo(java.math.BigDecimal saldo);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);

   java.sql.Timestamp getFechaCompra();

   void setFechaCompra(java.sql.Timestamp fechaCompra);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.lang.String getRazonSocial();

   void setRazonSocial(java.lang.String razonSocial);

   java.lang.String getIdentificacion();

   void setIdentificacion(java.lang.String identificacion);

   java.lang.Long getProveedorOficinaId();

   void setProveedorOficinaId(java.lang.Long proveedorOficinaId);

   java.lang.Long getTipoProveedorId();

   void setTipoProveedorId(java.lang.Long tipoProveedorId);

   java.lang.String getCodigoTipoProveedor();

   void setCodigoTipoProveedor(java.lang.String codigoTipoProveedor);

   java.lang.String getTipoProveedor();

   void setTipoProveedor(java.lang.String tipoProveedor);


}
