package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteIndicadorIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.Long getTipoindicadorId();

   void setTipoindicadorId(java.lang.Long tipoindicadorId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);


}
