package com.spirit.compras.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogCompraRetencionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getEstablecimiento();

   void setEstablecimiento(java.lang.String establecimiento);

   java.lang.String getPuntoEmision();

   void setPuntoEmision(java.lang.String puntoEmision);

   java.lang.String getSecuencial();

   void setSecuencial(java.lang.String secuencial);

   java.lang.String getAutorizacion();

   void setAutorizacion(java.lang.String autorizacion);

   java.sql.Date getFechaEmision();

   void setFechaEmision(java.sql.Date fechaEmision);

   java.lang.Long getCompraId();

   void setCompraId(java.lang.Long compraId);

   java.lang.String getEjercicioFiscal();

   void setEjercicioFiscal(java.lang.String ejercicioFiscal);

   java.math.BigDecimal getBaseImponible();

   void setBaseImponible(java.math.BigDecimal baseImponible);

   java.lang.String getImpuesto();

   void setImpuesto(java.lang.String impuesto);

   java.math.BigDecimal getPorcentajeRetencion();

   void setPorcentajeRetencion(java.math.BigDecimal porcentajeRetencion);

   java.math.BigDecimal getValorRetenido();

   void setValorRetenido(java.math.BigDecimal valorRetenido);

   java.lang.String getCodigoImpuesto();

   void setCodigoImpuesto(java.lang.String codigoImpuesto);

   java.lang.String getLog();

   void setLog(java.lang.String log);


}
