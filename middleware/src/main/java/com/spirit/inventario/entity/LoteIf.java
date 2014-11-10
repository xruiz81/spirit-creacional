package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LoteIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.sql.Timestamp getFechaElaboracion();

   void setFechaElaboracion(java.sql.Timestamp fechaElaboracion);

   java.sql.Timestamp getFechaVencimiento();

   void setFechaVencimiento(java.sql.Timestamp fechaVencimiento);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
