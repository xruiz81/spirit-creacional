package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioFormaPagoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPedidoId();

   void setPedidoId(java.lang.Long pedidoId);

   java.lang.Long getPlanMedioId();

   void setPlanMedioId(java.lang.Long planMedioId);

   java.sql.Timestamp getFechaInicio();

   void setFechaInicio(java.sql.Timestamp fechaInicio);

   java.sql.Timestamp getFechaFin();

   void setFechaFin(java.sql.Timestamp fechaFin);

   java.lang.String getTipoFormaPago();

   void setTipoFormaPago(java.lang.String tipoFormaPago);

   java.lang.Long getFormaPagoId();

   void setFormaPagoId(java.lang.Long formaPagoId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getFormaPagoCampanaProductoVersionId();

   void setFormaPagoCampanaProductoVersionId(java.lang.Long formaPagoCampanaProductoVersionId);


}
