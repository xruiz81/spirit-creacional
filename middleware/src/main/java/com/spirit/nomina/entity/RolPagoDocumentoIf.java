package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolPagoDocumentoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipoRolId();

   void setTipoRolId(java.lang.Long tipoRolId);

   java.lang.Long getTipoContratoId();

   void setTipoContratoId(java.lang.Long tipoContratoId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.sql.Date getFechaCreacion();

   void setFechaCreacion(java.sql.Date fechaCreacion);

   java.lang.Long getCreacionUsuarioId();

   void setCreacionUsuarioId(java.lang.Long creacionUsuarioId);

   java.sql.Date getFechaActualizacion();

   void setFechaActualizacion(java.sql.Date fechaActualizacion);

   java.lang.Long getActualizacionUsuarioId();

   void setActualizacionUsuarioId(java.lang.Long actualizacionUsuarioId);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);


}
