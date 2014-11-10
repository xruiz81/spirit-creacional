package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface NoticiasIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.sql.Date getFechaIni();

   void setFechaIni(java.sql.Date fechaIni);

   java.sql.Date getFechaFin();

   void setFechaFin(java.sql.Date fechaFin);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.lang.String getStatus();

   void setStatus(java.lang.String status);

   java.lang.String getNoticia();

   void setNoticia(java.lang.String noticia);

   java.lang.String getArchivo();

   void setArchivo(java.lang.String archivo);

   java.lang.String getAsunto();

   void setAsunto(java.lang.String asunto);


}
