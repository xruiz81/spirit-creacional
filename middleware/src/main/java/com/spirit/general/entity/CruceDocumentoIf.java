package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CruceDocumentoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getDocumentoaplId();

   void setDocumentoaplId(java.lang.Long documentoaplId);

   java.lang.String getValidoAlGuardar();

   void setValidoAlGuardar(java.lang.String validoAlGuardar);

   java.lang.String getValidoAlActualizar();

   void setValidoAlActualizar(java.lang.String validoAlActualizar);


}
