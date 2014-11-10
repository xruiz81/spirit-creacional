package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OverComisionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getProveedorOficinaId();

   void setProveedorOficinaId(java.lang.Long proveedorOficinaId);

   java.sql.Timestamp getAnio();

   void setAnio(java.sql.Timestamp anio);

   java.math.BigDecimal getInversionDe();

   void setInversionDe(java.math.BigDecimal inversionDe);

   java.math.BigDecimal getInversionA();

   void setInversionA(java.math.BigDecimal inversionA);

   java.math.BigDecimal getPorcentajeOver();

   void setPorcentajeOver(java.math.BigDecimal porcentajeOver);

   java.lang.String getObjetivo();

   void setObjetivo(java.lang.String objetivo);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);


}
