package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface AsociacionTransaccionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.sql.Timestamp getFechaEmision();

   void setFechaEmision(java.sql.Timestamp fechaEmision);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.String getTablaOrigen();

   void setTablaOrigen(java.lang.String tablaOrigen);

   java.lang.Long getTipoDocumentoOrigenId();

   void setTipoDocumentoOrigenId(java.lang.Long tipoDocumentoOrigenId);

   java.lang.Long getTransaccionOrigenId();

   void setTransaccionOrigenId(java.lang.Long transaccionOrigenId);

   java.lang.String getTablaDestino();

   void setTablaDestino(java.lang.String tablaDestino);

   java.lang.Long getTipoDocumentoDestinoId();

   void setTipoDocumentoDestinoId(java.lang.Long tipoDocumentoDestinoId);

   java.lang.Long getTransaccionDestinoId();

   void setTransaccionDestinoId(java.lang.Long transaccionDestinoId);


}
