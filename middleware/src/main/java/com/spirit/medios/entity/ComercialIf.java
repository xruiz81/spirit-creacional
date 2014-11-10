package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ComercialIf extends SpiritIf{


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

   java.lang.Long getCampanaId();

   void setCampanaId(java.lang.Long campanaId);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getDerechoprogramaId();

   void setDerechoprogramaId(java.lang.Long derechoprogramaId);

   java.lang.String getVersion();

   void setVersion(java.lang.String version);

   java.lang.String getDuracion();

   void setDuracion(java.lang.String duracion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getProductoClienteId();

   void setProductoClienteId(java.lang.Long productoClienteId);

   java.lang.Long getCampanaProductoVersionId();

   void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId);


}
