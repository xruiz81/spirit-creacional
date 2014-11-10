package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MovimientoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getBodegaId();

   void setBodegaId(java.lang.Long bodegaId);

   java.lang.Long getBodegarefId();

   void setBodegarefId(java.lang.Long bodegarefId);

   java.lang.Long getOrdencompraId();

   void setOrdencompraId(java.lang.Long ordencompraId);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.sql.Timestamp getFechaDocumento();

   void setFechaDocumento(java.sql.Timestamp fechaDocumento);

   java.math.BigDecimal getCosto();

   void setCosto(java.math.BigDecimal costo);

   java.math.BigDecimal getPrecio();

   void setPrecio(java.math.BigDecimal precio);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Long getUsuarioautId();

   void setUsuarioautId(java.lang.Long usuarioautId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getTipodocumentoOrigenId();

   void setTipodocumentoOrigenId(java.lang.Long tipodocumentoOrigenId);


}
