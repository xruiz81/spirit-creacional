package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface MarcaProductoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
