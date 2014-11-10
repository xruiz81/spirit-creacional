package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaInsertosIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.math.BigDecimal getMaxPaginas();

   void setMaxPaginas(java.math.BigDecimal maxPaginas);

   java.math.BigDecimal getTarifa();

   void setTarifa(java.math.BigDecimal tarifa);


}
