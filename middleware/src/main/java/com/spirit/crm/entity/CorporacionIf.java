package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CorporacionIf extends SpiritIf{


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

   java.lang.String getRepresentante();

   void setRepresentante(java.lang.String representante);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
