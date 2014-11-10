package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NumeradoresIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.math.BigDecimal getUltimoValor();

   void setUltimoValor(java.math.BigDecimal ultimoValor);

   java.lang.String getNombreTabla();

   void setNombreTabla(java.lang.String nombreTabla);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
