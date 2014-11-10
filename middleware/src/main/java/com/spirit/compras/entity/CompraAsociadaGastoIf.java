package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CompraAsociadaGastoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCompraGastoId();

   void setCompraGastoId(java.lang.Long compraGastoId);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);


}
