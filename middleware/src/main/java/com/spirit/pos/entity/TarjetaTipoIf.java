package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TarjetaTipoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getPadreId();

   void setPadreId(java.lang.Long padreId);

   java.lang.String getPuntosDinero();

   void setPuntosDinero(java.lang.String puntosDinero);

   java.math.BigDecimal getDsctoReferido();

   void setDsctoReferido(java.math.BigDecimal dsctoReferido);

   java.math.BigDecimal getDsctoPropietario();

   void setDsctoPropietario(java.math.BigDecimal dsctoPropietario);

   java.math.BigDecimal getPorcentajeDineroReferido();

   void setPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido);

   java.math.BigDecimal getPorcentajeDineroPropietario();

   void setPorcentajeDineroPropietario(java.math.BigDecimal porcentajeDineroPropietario);

   java.lang.Long getStatusSiguiente();

   void setStatusSiguiente(java.lang.Long statusSiguiente);

   java.lang.Long getStatusAnterior();

   void setStatusAnterior(java.lang.Long statusAnterior);

   java.math.BigDecimal getPuntosSubirStatus();

   void setPuntosSubirStatus(java.math.BigDecimal puntosSubirStatus);

   java.math.BigDecimal getDineroSubirStatus();

   void setDineroSubirStatus(java.math.BigDecimal dineroSubirStatus);

   java.math.BigDecimal getPuntosMantenerStatus();

   void setPuntosMantenerStatus(java.math.BigDecimal puntosMantenerStatus);

   java.math.BigDecimal getDineroMantenerStatus();

   void setDineroMantenerStatus(java.math.BigDecimal dineroMantenerStatus);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
