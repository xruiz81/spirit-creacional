package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoRolIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getNemonico();

   void setNemonico(java.lang.String nemonico);

   java.lang.String getRubroEventual();

   void setRubroEventual(java.lang.String rubroEventual);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.lang.String getRubroProvisionado();

   void setRubroProvisionado(java.lang.String rubroProvisionado);

   java.lang.String getFormaPago();

   void setFormaPago(java.lang.String formaPago);


}
