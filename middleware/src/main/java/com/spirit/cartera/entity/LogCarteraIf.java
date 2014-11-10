package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogCarteraIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);

   java.lang.Long getClienteoficinaId();

   void setClienteoficinaId(java.lang.Long clienteoficinaId);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Long getVendedorId();

   void setVendedorId(java.lang.Long vendedorId);

   java.lang.Long getMonedaId();

   void setMonedaId(java.lang.Long monedaId);

   java.sql.Date getFechaEmision();

   void setFechaEmision(java.sql.Date fechaEmision);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getSaldo();

   void setSaldo(java.math.BigDecimal saldo);

   java.sql.Date getFechaUltimaActualizacion();

   void setFechaUltimaActualizacion(java.sql.Date fechaUltimaActualizacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getComentario();

   void setComentario(java.lang.String comentario);

   java.lang.String getAprobado();

   void setAprobado(java.lang.String aprobado);

   java.lang.String getLog();

   void setLog(java.lang.String log);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.sql.Date getFechaLog();

   void setFechaLog(java.sql.Date fechaLog);


}
