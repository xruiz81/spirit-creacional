package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PautaGenericoClienteIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.Long getTipoProductoId();

   void setTipoProductoId(java.lang.Long tipoProductoId);

   java.lang.Long getGenericoId();

   void setGenericoId(java.lang.Long genericoId);


}
