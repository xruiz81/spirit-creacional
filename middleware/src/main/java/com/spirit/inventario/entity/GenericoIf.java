package com.spirit.inventario.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GenericoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getAbreviado();

   void setAbreviado(java.lang.String abreviado);

   java.lang.String getNombreGenerico();

   void setNombreGenerico(java.lang.String nombreGenerico);

   java.lang.String getCambioDescripcion();

   void setCambioDescripcion(java.lang.String cambioDescripcion);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Long getTipoproductoId();

   void setTipoproductoId(java.lang.Long tipoproductoId);

   java.lang.Long getMedidaId();

   void setMedidaId(java.lang.Long medidaId);

   java.lang.String getPartidaArancelaria();

   void setPartidaArancelaria(java.lang.String partidaArancelaria);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.lang.String getReferencia();

   void setReferencia(java.lang.String referencia);

   java.lang.String getUsaLote();

   void setUsaLote(java.lang.String usaLote);

   java.lang.Long getLineaId();

   void setLineaId(java.lang.Long lineaId);

   java.math.BigDecimal getIva();

   void setIva(java.math.BigDecimal iva);

   java.math.BigDecimal getIce();

   void setIce(java.math.BigDecimal ice);

   java.math.BigDecimal getOtroImpuesto();

   void setOtroImpuesto(java.math.BigDecimal otroImpuesto);

   java.lang.String getServicio();

   void setServicio(java.lang.String servicio);

   java.lang.Long getFamiliaGenericoId();

   void setFamiliaGenericoId(java.lang.Long familiaGenericoId);

   java.lang.String getSerie();

   void setSerie(java.lang.String serie);

   java.lang.String getAfectastock();

   void setAfectastock(java.lang.String afectastock);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getCobraIva();

   void setCobraIva(java.lang.String cobraIva);

   java.lang.String getCobraIce();

   void setCobraIce(java.lang.String cobraIce);

   java.lang.String getMediosProduccion();

   void setMediosProduccion(java.lang.String mediosProduccion);

   java.lang.String getLlevaInventario();

   void setLlevaInventario(java.lang.String llevaInventario);

   java.lang.String getAceptaDescuento();

   void setAceptaDescuento(java.lang.String aceptaDescuento);

   java.lang.String getCobraIvaCliente();

   void setCobraIvaCliente(java.lang.String cobraIvaCliente);


}
