package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PeriodoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.sql.Date getFechaini();

   void setFechaini(java.sql.Date fechaini);

   java.sql.Date getFechafin();

   void setFechafin(java.sql.Date fechafin);

   java.lang.String getStatus();

   void setStatus(java.lang.String status);

   java.lang.Long getOrden();

   void setOrden(java.lang.Long orden);


}
