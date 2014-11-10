package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EmpresaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getLogo();

   void setLogo(java.lang.String logo);

   java.lang.String getRuc();

   void setRuc(java.lang.String ruc);

   java.lang.String getWeb();

   void setWeb(java.lang.String web);

   java.lang.String getEmailContador();

   void setEmailContador(java.lang.String emailContador);

   java.lang.Long getTipoIdRepresentante();

   void setTipoIdRepresentante(java.lang.Long tipoIdRepresentante);

   java.lang.String getNumeroIdentificacion();

   void setNumeroIdentificacion(java.lang.String numeroIdentificacion);

   java.lang.String getRucContador();

   void setRucContador(java.lang.String rucContador);


}
