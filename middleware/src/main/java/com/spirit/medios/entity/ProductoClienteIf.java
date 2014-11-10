package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ProductoClienteIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.Long getCreativoId();

   void setCreativoId(java.lang.Long creativoId);

   java.lang.Long getEjecutivoId();

   void setEjecutivoId(java.lang.Long ejecutivoId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getMarcaProductoId();

   void setMarcaProductoId(java.lang.Long marcaProductoId);

   java.lang.String getMarcaProductoNombre();

   void setMarcaProductoNombre(java.lang.String marcaProductoNombre);


}
