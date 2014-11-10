package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface OficinaIf extends SpiritIf{


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

   java.lang.Long getCiudadId();

   void setCiudadId(java.lang.Long ciudadId);

   java.lang.Long getAdministradorId();

   void setAdministradorId(java.lang.Long administradorId);

   java.lang.String getDireccion();

   void setDireccion(java.lang.String direccion);

   java.lang.String getTelefono();

   void setTelefono(java.lang.String telefono);

   java.lang.String getFax();

   void setFax(java.lang.String fax);

   java.lang.String getPreimpresoSerie();

   void setPreimpresoSerie(java.lang.String preimpresoSerie);

   java.lang.Long getServidorId();

   void setServidorId(java.lang.Long servidorId);


}
