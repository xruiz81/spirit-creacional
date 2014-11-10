package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ImpuestoRentaPersNatIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.math.BigDecimal getFraccionBasica();

   void setFraccionBasica(java.math.BigDecimal fraccionBasica);

   java.math.BigDecimal getExcesoHasta();

   void setExcesoHasta(java.math.BigDecimal excesoHasta);

   java.math.BigDecimal getImpFraccionBasica();

   void setImpFraccionBasica(java.math.BigDecimal impFraccionBasica);

   java.math.BigDecimal getPorcentajeImpFraccionBasica();

   void setPorcentajeImpFraccionBasica(java.math.BigDecimal porcentajeImpFraccionBasica);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);


}
