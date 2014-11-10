package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaFormatoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getFormato();

   void setFormato(java.lang.String formato);

   java.math.BigDecimal getAnchoColumnas();

   void setAnchoColumnas(java.math.BigDecimal anchoColumnas);

   java.math.BigDecimal getAltoModulos();

   void setAltoModulos(java.math.BigDecimal altoModulos);

   java.math.BigDecimal getAnchoCm();

   void setAnchoCm(java.math.BigDecimal anchoCm);

   java.math.BigDecimal getAltoCm();

   void setAltoCm(java.math.BigDecimal altoCm);


}
