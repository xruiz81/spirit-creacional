package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface VentasPagosIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.Long getTipo();

   void setTipo(java.lang.Long tipo);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.Long getReferenciaId();

   void setReferenciaId(java.lang.Long referenciaId);

   java.lang.Long getVentastrackId();

   void setVentastrackId(java.lang.Long ventastrackId);

   java.lang.String getRevisado();

   void setRevisado(java.lang.String revisado);

   java.lang.String getNumDoc();

   void setNumDoc(java.lang.String numDoc);


}
