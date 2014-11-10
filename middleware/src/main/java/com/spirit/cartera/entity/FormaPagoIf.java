package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface FormaPagoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Integer getDiasInicio();

   void setDiasInicio(java.lang.Integer diasInicio);

   java.lang.Integer getDiasFin();

   void setDiasFin(java.lang.Integer diasFin);

   java.math.BigDecimal getDescuentoVenta();

   void setDescuentoVenta(java.math.BigDecimal descuentoVenta);

   java.math.BigDecimal getDescuentoCartera();

   void setDescuentoCartera(java.math.BigDecimal descuentoCartera);

   java.math.BigDecimal getInteres();

   void setInteres(java.math.BigDecimal interes);


}
