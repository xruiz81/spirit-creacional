package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ListaPrecioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getReferenciaFisica();

   void setReferenciaFisica(java.lang.String referenciaFisica);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.sql.Date getFechaInicio();

   void setFechaInicio(java.sql.Date fechaInicio);

   java.sql.Date getFechaFinal();

   void setFechaFinal(java.sql.Date fechaFinal);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
