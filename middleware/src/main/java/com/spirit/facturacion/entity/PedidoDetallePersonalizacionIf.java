package com.spirit.facturacion.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PedidoDetallePersonalizacionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPedidoDetalleId();

   void setPedidoDetalleId(java.lang.Long pedidoDetalleId);

   java.lang.Long getTipoPersonalizacionId();

   void setTipoPersonalizacionId(java.lang.Long tipoPersonalizacionId);

   java.lang.Long getImpresionPersonalizacionId();

   void setImpresionPersonalizacionId(java.lang.Long impresionPersonalizacionId);

   java.lang.Long getTamanioPersonalizacionId();

   void setTamanioPersonalizacionId(java.lang.Long tamanioPersonalizacionId);

   java.lang.Long getColorPersonalizacionId();

   void setColorPersonalizacionId(java.lang.Long colorPersonalizacionId);

   java.lang.Long getUbicacionPersonalizacionId();

   void setUbicacionPersonalizacionId(java.lang.Long ubicacionPersonalizacionId);

   java.lang.Long getTipoLetraPersonalizacionId();

   void setTipoLetraPersonalizacionId(java.lang.Long tipoLetraPersonalizacionId);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.Long getLogoDisenioPersonalizacionId();

   void setLogoDisenioPersonalizacionId(java.lang.Long logoDisenioPersonalizacionId);

   java.lang.String getMensaje();

   void setMensaje(java.lang.String mensaje);

   java.lang.Long getDisenioPersonalizacionId();

   void setDisenioPersonalizacionId(java.lang.Long disenioPersonalizacionId);


}
