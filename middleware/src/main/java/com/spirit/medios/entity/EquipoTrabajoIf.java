package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EquipoTrabajoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getTipoordenId();

   void setTipoordenId(java.lang.Long tipoordenId);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.sql.Date getFechaini();

   void setFechaini(java.sql.Date fechaini);

   java.sql.Date getFechafin();

   void setFechafin(java.sql.Date fechafin);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
