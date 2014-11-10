package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TarjetaTransaccionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoDocumentoId();

   void setTipoDocumentoId(java.lang.Long tipoDocumentoId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getTarjetaId();

   void setTarjetaId(java.lang.Long tarjetaId);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.lang.String getReferido();

   void setReferido(java.lang.String referido);

   java.lang.Long getReferidoPor();

   void setReferidoPor(java.lang.Long referidoPor);

   java.lang.Long getFacturaId();

   void setFacturaId(java.lang.Long facturaId);

   java.math.BigDecimal getPuntosGanados();

   void setPuntosGanados(java.math.BigDecimal puntosGanados);

   java.math.BigDecimal getPuntosUtilizados();

   void setPuntosUtilizados(java.math.BigDecimal puntosUtilizados);


}
