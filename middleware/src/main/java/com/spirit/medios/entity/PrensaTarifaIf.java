package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrensaTarifaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.Long getPrensaSeccionId();

   void setPrensaSeccionId(java.lang.Long prensaSeccionId);

   java.lang.Long getPrensaUbicacionId();

   void setPrensaUbicacionId(java.lang.Long prensaUbicacionId);

   java.lang.Long getPrensaFormatoId();

   void setPrensaFormatoId(java.lang.Long prensaFormatoId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getColor();

   void setColor(java.lang.String color);

   java.lang.String getDia();

   void setDia(java.lang.String dia);

   java.lang.String getTarifaCalculada();

   void setTarifaCalculada(java.lang.String tarifaCalculada);

   java.math.BigDecimal getTarifa();

   void setTarifa(java.math.BigDecimal tarifa);

   java.math.BigDecimal getRecargo();

   void setRecargo(java.math.BigDecimal recargo);

   java.lang.String getOperacion();

   void setOperacion(java.lang.String operacion);


}
