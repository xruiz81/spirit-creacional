package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PrecioHistoricoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPrecioId();

   void setPrecioId(java.lang.Long precioId);

   java.sql.Date getFechaini();

   void setFechaini(java.sql.Date fechaini);

   java.sql.Date getFechafin();

   void setFechafin(java.sql.Date fechafin);

   java.math.BigDecimal getPrecioHis();

   void setPrecioHis(java.math.BigDecimal precioHis);

   java.math.BigDecimal getPrecio();

   void setPrecio(java.math.BigDecimal precio);


}
