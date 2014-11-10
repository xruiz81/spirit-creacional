package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OrdenAsociadaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);

   java.lang.String getTipoOrden();

   void setTipoOrden(java.lang.String tipoOrden);

   java.lang.Long getOrdenId();

   void setOrdenId(java.lang.Long ordenId);


}
