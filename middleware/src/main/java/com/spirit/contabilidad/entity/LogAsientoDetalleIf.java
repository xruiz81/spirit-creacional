package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface LogAsientoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.Long getLogAsientoId();

   void setLogAsientoId(java.lang.Long logAsientoId);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.String getGlosa();

   void setGlosa(java.lang.String glosa);

   java.lang.Long getCentrogastoId();

   void setCentrogastoId(java.lang.Long centrogastoId);

   java.lang.Long getEmpleadoId();

   void setEmpleadoId(java.lang.Long empleadoId);

   java.lang.Long getDepartamentoId();

   void setDepartamentoId(java.lang.Long departamentoId);

   java.lang.Long getLineaId();

   void setLineaId(java.lang.Long lineaId);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.math.BigDecimal getDebe();

   void setDebe(java.math.BigDecimal debe);

   java.math.BigDecimal getHaber();

   void setHaber(java.math.BigDecimal haber);

   java.lang.String getLog();

   void setLog(java.lang.String log);


}
