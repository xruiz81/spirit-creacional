package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ParametroEmpresaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoparametroId();

   void setTipoparametroId(java.lang.Long tipoparametroId);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getValor();

   void setValor(java.lang.String valor);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);


}
