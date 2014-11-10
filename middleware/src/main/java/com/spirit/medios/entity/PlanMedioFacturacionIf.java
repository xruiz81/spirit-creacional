package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioFacturacionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPlanMedioId();

   void setPlanMedioId(java.lang.Long planMedioId);

   java.lang.Long getComercialId();

   void setComercialId(java.lang.Long comercialId);

   java.sql.Timestamp getFechaInicio();

   void setFechaInicio(java.sql.Timestamp fechaInicio);

   java.sql.Timestamp getFechaFin();

   void setFechaFin(java.sql.Timestamp fechaFin);

   java.lang.Long getPedidoId();

   void setPedidoId(java.lang.Long pedidoId);

   java.lang.String getCanje();

   void setCanje(java.lang.String canje);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.math.BigDecimal getPorcentajeCanje();

   void setPorcentajeCanje(java.math.BigDecimal porcentajeCanje);

   java.lang.Long getOrdenMedioId();

   void setOrdenMedioId(java.lang.Long ordenMedioId);

   java.lang.Long getOrdenMedioDetalleId();

   void setOrdenMedioDetalleId(java.lang.Long ordenMedioDetalleId);

   java.lang.Long getOrdenMedioDetalleMapaId();

   void setOrdenMedioDetalleMapaId(java.lang.Long ordenMedioDetalleMapaId);

   java.lang.Long getProductoClienteId();

   void setProductoClienteId(java.lang.Long productoClienteId);

   java.lang.Long getCampanaProductoVersionId();

   void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId);


}
