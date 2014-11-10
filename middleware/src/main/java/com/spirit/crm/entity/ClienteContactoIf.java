package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteContactoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipocontactoId();

   void setTipocontactoId(java.lang.Long tipocontactoId);

   java.lang.Long getClienteoficinaId();

   void setClienteoficinaId(java.lang.Long clienteoficinaId);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getDireccion();

   void setDireccion(java.lang.String direccion);

   java.lang.String getTelefonoOfic();

   void setTelefonoOfic(java.lang.String telefonoOfic);

   java.lang.String getTelefonoCasa();

   void setTelefonoCasa(java.lang.String telefonoCasa);

   java.lang.String getCelular();

   void setCelular(java.lang.String celular);

   java.lang.String getMail();

   void setMail(java.lang.String mail);

   java.sql.Timestamp getCumpleanos();

   void setCumpleanos(java.sql.Timestamp cumpleanos);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);


}
