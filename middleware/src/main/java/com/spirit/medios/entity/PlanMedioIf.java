package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getConcepto();

   void setConcepto(java.lang.String concepto);

   java.lang.Long getOrdenTrabajoDetalleId();

   void setOrdenTrabajoDetalleId(java.lang.Long ordenTrabajoDetalleId);

   java.lang.Long getGrupoObjetivoId();

   void setGrupoObjetivoId(java.lang.Long grupoObjetivoId);

   java.lang.Long getTipoProveedorId();

   void setTipoProveedorId(java.lang.Long tipoProveedorId);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Integer getModificacion();

   void setModificacion(java.lang.Integer modificacion);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.sql.Timestamp getFechaInicio();

   void setFechaInicio(java.sql.Timestamp fechaInicio);

   java.sql.Timestamp getFechaFin();

   void setFechaFin(java.sql.Timestamp fechaFin);

   java.math.BigDecimal getValorSubtotal();

   void setValorSubtotal(java.math.BigDecimal valorSubtotal);

   java.math.BigDecimal getValorDescuento();

   void setValorDescuento(java.math.BigDecimal valorDescuento);

   java.lang.Long getPlanMedioOrigenId();

   void setPlanMedioOrigenId(java.lang.Long planMedioOrigenId);

   java.math.BigDecimal getCiudad1();

   void setCiudad1(java.math.BigDecimal ciudad1);

   java.math.BigDecimal getCiudad2();

   void setCiudad2(java.math.BigDecimal ciudad2);

   java.math.BigDecimal getCiudad3();

   void setCiudad3(java.math.BigDecimal ciudad3);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getCobertura();

   void setCobertura(java.lang.String cobertura);

   java.lang.String getConsideraciones();

   void setConsideraciones(java.lang.String consideraciones);

   java.math.BigDecimal getValorIva();

   void setValorIva(java.math.BigDecimal valorIva);

   java.lang.Long getUsuarioActualizacionId();

   void setUsuarioActualizacionId(java.lang.Long usuarioActualizacionId);

   java.sql.Timestamp getFechaActualizacion();

   void setFechaActualizacion(java.sql.Timestamp fechaActualizacion);

   java.math.BigDecimal getPorcentajeDescuento();

   void setPorcentajeDescuento(java.math.BigDecimal porcentajeDescuento);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.math.BigDecimal getValorDescuentoVenta();

   void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta);

   java.math.BigDecimal getValorComisionAgencia();

   void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia);

   java.lang.Long getPlanMedioHermanoId();

   void setPlanMedioHermanoId(java.lang.Long planMedioHermanoId);

   java.lang.String getOrdenMedioTipo();

   void setOrdenMedioTipo(java.lang.String ordenMedioTipo);

   java.lang.String getPlanMedioTipo();

   void setPlanMedioTipo(java.lang.String planMedioTipo);

   java.lang.String getAutorizacionSap();

   void setAutorizacionSap(java.lang.String autorizacionSap);

   java.sql.Timestamp getFechaAprobacion();

   void setFechaAprobacion(java.sql.Timestamp fechaAprobacion);

   java.lang.String getRevision();

   void setRevision(java.lang.String revision);

   java.lang.String getPrepago();

   void setPrepago(java.lang.String prepago);


}
