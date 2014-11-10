package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPlanMedioMesId();

   void setPlanMedioMesId(java.lang.Long planMedioMesId);

   java.lang.String getPrograma();

   void setPrograma(java.lang.String programa);

   java.lang.Long getGeneroProgramaId();

   void setGeneroProgramaId(java.lang.Long generoProgramaId);

   java.lang.Long getComercialId();

   void setComercialId(java.lang.Long comercialId);

   java.lang.Long getProveedorId();

   void setProveedorId(java.lang.Long proveedorId);

   java.lang.String getHoraInicio();

   void setHoraInicio(java.lang.String horaInicio);

   java.math.BigDecimal getRaiting1();

   void setRaiting1(java.math.BigDecimal raiting1);

   java.math.BigDecimal getRaiting2();

   void setRaiting2(java.math.BigDecimal raiting2);

   java.math.BigDecimal getRaitingPonderado();

   void setRaitingPonderado(java.math.BigDecimal raitingPonderado);

   java.math.BigDecimal getAudiencia();

   void setAudiencia(java.math.BigDecimal audiencia);

   java.math.BigDecimal getValorTarifa();

   void setValorTarifa(java.math.BigDecimal valorTarifa);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.lang.String getSeccion();

   void setSeccion(java.lang.String seccion);

   java.lang.String getUbicacion();

   void setUbicacion(java.lang.String ubicacion);

   java.lang.String getTamanio();

   void setTamanio(java.lang.String tamanio);

   java.lang.String getColor();

   void setColor(java.lang.String color);

   java.lang.Integer getTotalCunias();

   void setTotalCunias(java.lang.Integer totalCunias);

   java.lang.String getComercial();

   void setComercial(java.lang.String comercial);

   java.math.BigDecimal getValorDescuento();

   void setValorDescuento(java.math.BigDecimal valorDescuento);

   java.lang.Long getProductoProveedorId();

   void setProductoProveedorId(java.lang.Long productoProveedorId);

   java.math.BigDecimal getValorSubtotal();

   void setValorSubtotal(java.math.BigDecimal valorSubtotal);

   java.math.BigDecimal getPorcentajeDescuento();

   void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento);

   java.math.BigDecimal getValorIva();

   void setValorIva(java.math.BigDecimal valorIva);

   java.math.BigDecimal getValorDescuentoVenta();

   void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta);

   java.math.BigDecimal getValorComisionAgencia();

   void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia);

   java.math.BigDecimal getPorcentajeDescuentoVenta();

   void setPorcentajeDescuentoVenta(java.math.BigDecimal porcentajeDescuentoVenta);

   java.math.BigDecimal getPorcentajeComisionAgencia();

   void setPorcentajeComisionAgencia(java.math.BigDecimal porcentajeComisionAgencia);

   java.lang.Long getProductoClienteId();

   void setProductoClienteId(java.lang.Long productoClienteId);

   java.lang.String getPauta();

   void setPauta(java.lang.String pauta);

   java.lang.String getAuspicioDescripcion();

   void setAuspicioDescripcion(java.lang.String auspicioDescripcion);

   java.lang.Long getAuspicioPadre();

   void setAuspicioPadre(java.lang.Long auspicioPadre);

   java.lang.Long getCampanaProductoVersionId();

   void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId);

   java.math.BigDecimal getPorcentajeBonificacionCompra();

   void setPorcentajeBonificacionCompra(java.math.BigDecimal porcentajeBonificacionCompra);

   java.math.BigDecimal getPorcentajeBonificacionVenta();

   void setPorcentajeBonificacionVenta(java.math.BigDecimal porcentajeBonificacionVenta);

   java.lang.String getNegociacionComision();

   void setNegociacionComision(java.lang.String negociacionComision);

   java.lang.Integer getNumeroOrdenAgrupacion();

   void setNumeroOrdenAgrupacion(java.lang.Integer numeroOrdenAgrupacion);

   java.lang.String getVersion();

   void setVersion(java.lang.String version);


}
