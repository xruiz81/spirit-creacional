package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraAfectaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCarteradetalleId();

   void setCarteradetalleId(java.lang.Long carteradetalleId);

   java.lang.Long getCarteraafectaId();

   void setCarteraafectaId(java.lang.Long carteraafectaId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.sql.Date getFechaAplicacion();

   void setFechaAplicacion(java.sql.Date fechaAplicacion);

   java.lang.String getCartera();

   void setCartera(java.lang.String cartera);


}
