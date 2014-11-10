package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface DonaciondetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.math.BigDecimal getCant();

   void setCant(java.math.BigDecimal cant);

   java.math.BigDecimal getDev();

   void setDev(java.math.BigDecimal dev);

   java.math.BigDecimal getCantfinal();

   void setCantfinal(java.math.BigDecimal cantfinal);

   java.math.BigDecimal getValortipo();

   void setValortipo(java.math.BigDecimal valortipo);

   java.math.BigDecimal getTotal();

   void setTotal(java.math.BigDecimal total);

   java.lang.String getFundacion();

   void setFundacion(java.lang.String fundacion);

   java.lang.Long getFundacionid();

   void setFundacionid(java.lang.Long fundacionid);

   java.lang.Long getModeloId();

   void setModeloId(java.lang.Long modeloId);

   java.lang.Long getColorId();

   void setColorId(java.lang.Long colorId);

   java.lang.Long getTipoproducto();

   void setTipoproducto(java.lang.Long tipoproducto);

   java.lang.Long getTallaId();

   void setTallaId(java.lang.Long tallaId);

   java.lang.String getNombrecliente();

   void setNombrecliente(java.lang.String nombrecliente);

   java.lang.Long getClienteoficinaid();

   void setClienteoficinaid(java.lang.Long clienteoficinaid);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.Long getFacturaaplId();

   void setFacturaaplId(java.lang.Long facturaaplId);

   java.lang.Long getFundaciondevolucionid();

   void setFundaciondevolucionid(java.lang.Long fundaciondevolucionid);

   java.lang.String getNombrefundaciondevuelta();

   void setNombrefundaciondevuelta(java.lang.String nombrefundaciondevuelta);


}
