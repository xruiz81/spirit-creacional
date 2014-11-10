package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PagoTarjetaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getTipoTarjeta();

   void setTipoTarjeta(java.lang.String tipoTarjeta);

   java.lang.String getNombreCliente();

   void setNombreCliente(java.lang.String nombreCliente);

   java.lang.String getIdentificacion();

   void setIdentificacion(java.lang.String identificacion);

   java.lang.String getTelefono();

   void setTelefono(java.lang.String telefono);

   java.lang.String getNoReferencia();

   void setNoReferencia(java.lang.String noReferencia);

   java.lang.String getNoVoucher();

   void setNoVoucher(java.lang.String noVoucher);

   java.lang.String getNoAutorizacion();

   void setNoAutorizacion(java.lang.String noAutorizacion);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);


}
