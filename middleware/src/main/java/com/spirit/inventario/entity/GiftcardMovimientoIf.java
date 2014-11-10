package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GiftcardMovimientoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getGiftcardId();

   void setGiftcardId(java.lang.Long giftcardId);

   java.math.BigDecimal getSaldoAnterior();

   void setSaldoAnterior(java.math.BigDecimal saldoAnterior);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.sql.Timestamp getFechaMovimiento();

   void setFechaMovimiento(java.sql.Timestamp fechaMovimiento);

   java.lang.Long getTransaccionId();

   void setTransaccionId(java.lang.Long transaccionId);

   java.lang.Long getTipoDocumentoId();

   void setTipoDocumentoId(java.lang.Long tipoDocumentoId);


}
