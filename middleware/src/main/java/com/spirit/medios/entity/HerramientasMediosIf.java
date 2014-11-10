package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface HerramientasMediosIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getClienteOficinaId();

   void setClienteOficinaId(java.lang.Long clienteOficinaId);

   java.lang.Long getProveedorOficinaId();

   void setProveedorOficinaId(java.lang.Long proveedorOficinaId);

   java.lang.String getFrecuencia();

   void setFrecuencia(java.lang.String frecuencia);


}
