package com.spirit.log.entity;

import java.io.Serializable;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogSistemaIf extends Serializable{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getTipoMensaje();

   void setTipoMensaje(java.lang.String tipoMensaje);

   java.lang.String getTipoRegistro();

   void setTipoRegistro(java.lang.String tipoRegistro);

   java.sql.Timestamp getFechaTransaccion();

   void setFechaTransaccion(java.sql.Timestamp fechaTransaccion);

   java.lang.String getError();

   void setError(java.lang.String error);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.sql.Blob getObject();

   void setObject(java.sql.Blob object);


}
