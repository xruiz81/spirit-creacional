package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ReunionProductoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getReunionId();

   void setReunionId(java.lang.Long reunionId);

   java.lang.Long getProductoClienteId();

   void setProductoClienteId(java.lang.Long productoClienteId);

   java.lang.String getProductoCliente();

   void setProductoCliente(java.lang.String productoCliente);


}
