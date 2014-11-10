package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteWebIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getIdExterno();

   void setIdExterno(java.lang.String idExterno);

   java.lang.String getNombres();

   void setNombres(java.lang.String nombres);

   java.lang.String getApellidos();

   void setApellidos(java.lang.String apellidos);

   java.lang.String getEmail();

   void setEmail(java.lang.String email);

   java.lang.String getPais();

   void setPais(java.lang.String pais);

   java.lang.String getCiudad();

   void setCiudad(java.lang.String ciudad);

   java.lang.String getDireccion();

   void setDireccion(java.lang.String direccion);

   java.lang.String getTelefono();

   void setTelefono(java.lang.String telefono);

   java.lang.String getCelular();

   void setCelular(java.lang.String celular);


}
