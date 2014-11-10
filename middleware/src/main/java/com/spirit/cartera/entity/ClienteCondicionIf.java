package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteCondicionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.Long getSubtipoordenId();

   void setSubtipoordenId(java.lang.Long subtipoordenId);

   java.lang.Long getFormapagoId();

   void setFormapagoId(java.lang.Long formapagoId);

   java.lang.String getObservaciones();

   void setObservaciones(java.lang.String observaciones);

   java.sql.Date getFechaini();

   void setFechaini(java.sql.Date fechaini);

   java.sql.Date getFechafin();

   void setFechafin(java.sql.Date fechafin);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);


}
