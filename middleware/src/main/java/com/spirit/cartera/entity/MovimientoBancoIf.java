package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MovimientoBancoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
