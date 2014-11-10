package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TipoDocumentoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getModuloId();

   void setModuloId(java.lang.Long moduloId);

   java.lang.String getMascara();

   void setMascara(java.lang.String mascara);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Integer getNumerolineas();

   void setNumerolineas(java.lang.Integer numerolineas);

   java.lang.String getAfectacartera();

   void setAfectacartera(java.lang.String afectacartera);

   java.lang.String getAfectastock();

   void setAfectastock(java.lang.String afectastock);

   java.lang.String getAfectaventa();

   void setAfectaventa(java.lang.String afectaventa);

   java.lang.String getExigemotivo();

   void setExigemotivo(java.lang.String exigemotivo);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getFormapago();

   void setFormapago(java.lang.String formapago);

   java.lang.String getCliente();

   void setCliente(java.lang.String cliente);

   java.lang.String getCaja();

   void setCaja(java.lang.String caja);

   java.lang.String getPermiteeliminacion();

   void setPermiteeliminacion(java.lang.String permiteeliminacion);

   java.lang.String getReembolso();

   void setReembolso(java.lang.String reembolso);

   java.lang.String getSignocartera();

   void setSignocartera(java.lang.String signocartera);

   java.lang.String getSignostock();

   void setSignostock(java.lang.String signostock);

   java.lang.String getSignoventa();

   void setSignoventa(java.lang.String signoventa);

   java.lang.String getDescuentoespecial();

   void setDescuentoespecial(java.lang.String descuentoespecial);

   java.lang.String getTipocartera();

   void setTipocartera(java.lang.String tipocartera);

   java.lang.Long getIdSriTipoComprobante();

   void setIdSriTipoComprobante(java.lang.Long idSriTipoComprobante);

   java.lang.Long getTipoTroqueladoId();

   void setTipoTroqueladoId(java.lang.Long tipoTroqueladoId);

   java.lang.String getTransferible();

   void setTransferible(java.lang.String transferible);

   java.lang.String getFactura();

   void setFactura(java.lang.String factura);

   java.lang.String getNotaVenta();

   void setNotaVenta(java.lang.String notaVenta);

   java.lang.String getNotaCredito();

   void setNotaCredito(java.lang.String notaCredito);

   java.lang.String getNotaDebito();

   void setNotaDebito(java.lang.String notaDebito);

   java.lang.String getLiquidacionCompras();

   void setLiquidacionCompras(java.lang.String liquidacionCompras);

   java.lang.String getAbreviatura();

   void setAbreviatura(java.lang.String abreviatura);

   java.lang.String getEgreso();

   void setEgreso(java.lang.String egreso);

   java.lang.String getAnticipo();

   void setAnticipo(java.lang.String anticipo);


}
