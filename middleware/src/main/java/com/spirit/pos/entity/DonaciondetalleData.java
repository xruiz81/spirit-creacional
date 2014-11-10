package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class DonaciondetalleData implements DonaciondetalleIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.math.BigDecimal cant;

   public java.math.BigDecimal getCant() {
      return cant;
   }

   public void setCant(java.math.BigDecimal cant) {
      this.cant = cant;
   }

   private java.math.BigDecimal dev;

   public java.math.BigDecimal getDev() {
      return dev;
   }

   public void setDev(java.math.BigDecimal dev) {
      this.dev = dev;
   }

   private java.math.BigDecimal cantfinal;

   public java.math.BigDecimal getCantfinal() {
      return cantfinal;
   }

   public void setCantfinal(java.math.BigDecimal cantfinal) {
      this.cantfinal = cantfinal;
   }

   private java.math.BigDecimal valortipo;

   public java.math.BigDecimal getValortipo() {
      return valortipo;
   }

   public void setValortipo(java.math.BigDecimal valortipo) {
      this.valortipo = valortipo;
   }

   private java.math.BigDecimal total;

   public java.math.BigDecimal getTotal() {
      return total;
   }

   public void setTotal(java.math.BigDecimal total) {
      this.total = total;
   }

   private java.lang.String fundacion;

   public java.lang.String getFundacion() {
      return fundacion;
   }

   public void setFundacion(java.lang.String fundacion) {
      this.fundacion = fundacion;
   }

   private java.lang.Long fundacionid;

   public java.lang.Long getFundacionid() {
      return fundacionid;
   }

   public void setFundacionid(java.lang.Long fundacionid) {
      this.fundacionid = fundacionid;
   }

   private java.lang.Long modeloId;

   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   private java.lang.Long colorId;

   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   private java.lang.Long tipoproducto;

   public java.lang.Long getTipoproducto() {
      return tipoproducto;
   }

   public void setTipoproducto(java.lang.Long tipoproducto) {
      this.tipoproducto = tipoproducto;
   }

   private java.lang.Long tallaId;

   public java.lang.Long getTallaId() {
      return tallaId;
   }

   public void setTallaId(java.lang.Long tallaId) {
      this.tallaId = tallaId;
   }

   private java.lang.String nombrecliente;

   public java.lang.String getNombrecliente() {
      return nombrecliente;
   }

   public void setNombrecliente(java.lang.String nombrecliente) {
      this.nombrecliente = nombrecliente;
   }

   private java.lang.Long clienteoficinaid;

   public java.lang.Long getClienteoficinaid() {
      return clienteoficinaid;
   }

   public void setClienteoficinaid(java.lang.Long clienteoficinaid) {
      this.clienteoficinaid = clienteoficinaid;
   }

   private java.lang.String preimpreso;

   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   private java.lang.Long facturaaplId;

   public java.lang.Long getFacturaaplId() {
      return facturaaplId;
   }

   public void setFacturaaplId(java.lang.Long facturaaplId) {
      this.facturaaplId = facturaaplId;
   }

   private java.lang.Long fundaciondevolucionid;

   public java.lang.Long getFundaciondevolucionid() {
      return fundaciondevolucionid;
   }

   public void setFundaciondevolucionid(java.lang.Long fundaciondevolucionid) {
      this.fundaciondevolucionid = fundaciondevolucionid;
   }

   private java.lang.String nombrefundaciondevuelta;

   public java.lang.String getNombrefundaciondevuelta() {
      return nombrefundaciondevuelta;
   }

   public void setNombrefundaciondevuelta(java.lang.String nombrefundaciondevuelta) {
      this.nombrefundaciondevuelta = nombrefundaciondevuelta;
   }
   public DonaciondetalleData() {
   }

   public DonaciondetalleData(com.spirit.pos.entity.DonaciondetalleIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setFecha(value.getFecha());
      setCant(value.getCant());
      setDev(value.getDev());
      setCantfinal(value.getCantfinal());
      setValortipo(value.getValortipo());
      setTotal(value.getTotal());
      setFundacion(value.getFundacion());
      setFundacionid(value.getFundacionid());
      setModeloId(value.getModeloId());
      setColorId(value.getColorId());
      setTipoproducto(value.getTipoproducto());
      setTallaId(value.getTallaId());
      setNombrecliente(value.getNombrecliente());
      setClienteoficinaid(value.getClienteoficinaid());
      setPreimpreso(value.getPreimpreso());
      setFacturaaplId(value.getFacturaaplId());
      setFundaciondevolucionid(value.getFundaciondevolucionid());
      setNombrefundaciondevuelta(value.getNombrefundaciondevuelta());
   }



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((DonaciondetalleIf)this);
	}
}
