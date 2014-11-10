package com.spirit.sri.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriIdentifTransaccionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getIdTipoTransaccion();

   void setIdTipoTransaccion(java.lang.Long idTipoTransaccion);

   java.lang.Long getIdTipoIdentificacion();

   void setIdTipoIdentificacion(java.lang.Long idTipoIdentificacion);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);


}
