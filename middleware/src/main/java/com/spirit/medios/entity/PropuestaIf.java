package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PropuestaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getOrdentrabajoId();

   void setOrdentrabajoId(java.lang.Long ordentrabajoId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
