package com.spirit.sri.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface SriProveedorRetencionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getTipoPersona();

   void setTipoPersona(java.lang.String tipoPersona);

   java.lang.String getLlevaContabilidad();

   void setLlevaContabilidad(java.lang.String llevaContabilidad);

   java.lang.String getBienServicio();

   void setBienServicio(java.lang.String bienServicio);

   java.math.BigDecimal getRetefuente();

   void setRetefuente(java.math.BigDecimal retefuente);

   java.math.BigDecimal getReteiva();

   void setReteiva(java.math.BigDecimal reteiva);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getIdCuentaRetefuente();

   void setIdCuentaRetefuente(java.lang.Long idCuentaRetefuente);

   java.lang.Long getIdCuentaReteiva();

   void setIdCuentaReteiva(java.lang.Long idCuentaReteiva);

   java.lang.String getProfesional();

   void setProfesional(java.lang.String profesional);


}
