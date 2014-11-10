package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PuntoImpresionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getTipodocumentoId();

   void setTipodocumentoId(java.lang.Long tipodocumentoId);

   java.lang.Long getCajaId();

   void setCajaId(java.lang.Long cajaId);

   java.lang.String getSerie();

   void setSerie(java.lang.String serie);

   java.lang.String getImpresora();

   void setImpresora(java.lang.String impresora);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);


}
