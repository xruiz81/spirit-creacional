package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoParametroIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.lang.String getMascara();

   void setMascara(java.lang.String mascara);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
