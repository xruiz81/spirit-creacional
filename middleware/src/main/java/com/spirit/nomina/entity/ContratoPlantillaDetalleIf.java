package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ContratoPlantillaDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getContratoPlantillaId();

   void setContratoPlantillaId(java.lang.Long contratoPlantillaId);

   java.lang.Long getRubroId();

   void setRubroId(java.lang.Long rubroId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);


}
