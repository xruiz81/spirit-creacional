package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface VentasDocumentosIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getVentastrackId();

   void setVentastrackId(java.lang.Long ventastrackId);

   java.lang.String getTablaNombre();

   void setTablaNombre(java.lang.String tablaNombre);

   java.lang.Long getTablaId();

   void setTablaId(java.lang.Long tablaId);

   java.lang.String getRevisado();

   void setRevisado(java.lang.String revisado);

   java.lang.String getNumDoc();

   void setNumDoc(java.lang.String numDoc);


}
