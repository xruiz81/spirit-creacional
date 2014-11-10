package com.spirit.cartera.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CarteraRelacionadaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCarteraOrigenId();

   void setCarteraOrigenId(java.lang.Long carteraOrigenId);

   java.lang.Long getCarteraRelacionadaId();

   void setCarteraRelacionadaId(java.lang.Long carteraRelacionadaId);


}
