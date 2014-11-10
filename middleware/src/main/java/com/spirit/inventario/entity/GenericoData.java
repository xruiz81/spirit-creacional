package com.spirit.inventario.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class GenericoData implements GenericoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.String abreviado;

   public java.lang.String getAbreviado() {
      return abreviado;
   }

   public void setAbreviado(java.lang.String abreviado) {
      this.abreviado = abreviado;
   }

   private java.lang.String nombreGenerico;

   public java.lang.String getNombreGenerico() {
      return nombreGenerico;
   }

   public void setNombreGenerico(java.lang.String nombreGenerico) {
      this.nombreGenerico = nombreGenerico;
   }

   private java.lang.String cambioDescripcion;

   public java.lang.String getCambioDescripcion() {
      return cambioDescripcion;
   }

   public void setCambioDescripcion(java.lang.String cambioDescripcion) {
      this.cambioDescripcion = cambioDescripcion;
   }

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.Long tipoproductoId;

   public java.lang.Long getTipoproductoId() {
      return tipoproductoId;
   }

   public void setTipoproductoId(java.lang.Long tipoproductoId) {
      this.tipoproductoId = tipoproductoId;
   }

   private java.lang.Long medidaId;

   public java.lang.Long getMedidaId() {
      return medidaId;
   }

   public void setMedidaId(java.lang.Long medidaId) {
      this.medidaId = medidaId;
   }

   private java.lang.String partidaArancelaria;

   public java.lang.String getPartidaArancelaria() {
      return partidaArancelaria;
   }

   public void setPartidaArancelaria(java.lang.String partidaArancelaria) {
      this.partidaArancelaria = partidaArancelaria;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.String usaLote;

   public java.lang.String getUsaLote() {
      return usaLote;
   }

   public void setUsaLote(java.lang.String usaLote) {
      this.usaLote = usaLote;
   }

   private java.lang.Long lineaId;

   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.math.BigDecimal ice;

   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   private java.math.BigDecimal otroImpuesto;

   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   private java.lang.String servicio;

   public java.lang.String getServicio() {
      return servicio;
   }

   public void setServicio(java.lang.String servicio) {
      this.servicio = servicio;
   }

   private java.lang.Long familiaGenericoId;

   public java.lang.Long getFamiliaGenericoId() {
      return familiaGenericoId;
   }

   public void setFamiliaGenericoId(java.lang.Long familiaGenericoId) {
      this.familiaGenericoId = familiaGenericoId;
   }

   private java.lang.String serie;

   public java.lang.String getSerie() {
      return serie;
   }

   public void setSerie(java.lang.String serie) {
      this.serie = serie;
   }

   private java.lang.String afectastock;

   public java.lang.String getAfectastock() {
      return afectastock;
   }

   public void setAfectastock(java.lang.String afectastock) {
      this.afectastock = afectastock;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String cobraIva;

   public java.lang.String getCobraIva() {
      return cobraIva;
   }

   public void setCobraIva(java.lang.String cobraIva) {
      this.cobraIva = cobraIva;
   }

   private java.lang.String cobraIce;

   public java.lang.String getCobraIce() {
      return cobraIce;
   }

   public void setCobraIce(java.lang.String cobraIce) {
      this.cobraIce = cobraIce;
   }

   private java.lang.String mediosProduccion;

   public java.lang.String getMediosProduccion() {
      return mediosProduccion;
   }

   public void setMediosProduccion(java.lang.String mediosProduccion) {
      this.mediosProduccion = mediosProduccion;
   }

   private java.lang.String llevaInventario;

   public java.lang.String getLlevaInventario() {
      return llevaInventario;
   }

   public void setLlevaInventario(java.lang.String llevaInventario) {
      this.llevaInventario = llevaInventario;
   }

   private java.lang.String aceptaDescuento;

   public java.lang.String getAceptaDescuento() {
      return aceptaDescuento;
   }

   public void setAceptaDescuento(java.lang.String aceptaDescuento) {
      this.aceptaDescuento = aceptaDescuento;
   }

   private java.lang.String cobraIvaCliente;

   public java.lang.String getCobraIvaCliente() {
      return cobraIvaCliente;
   }

   public void setCobraIvaCliente(java.lang.String cobraIvaCliente) {
      this.cobraIvaCliente = cobraIvaCliente;
   }
   public GenericoData() {
   }

   public GenericoData(com.spirit.inventario.entity.GenericoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setAbreviado(value.getAbreviado());
      setNombreGenerico(value.getNombreGenerico());
      setCambioDescripcion(value.getCambioDescripcion());
      setEmpresaId(value.getEmpresaId());
      setTipoproductoId(value.getTipoproductoId());
      setMedidaId(value.getMedidaId());
      setPartidaArancelaria(value.getPartidaArancelaria());
      setFechaCreacion(value.getFechaCreacion());
      setReferencia(value.getReferencia());
      setUsaLote(value.getUsaLote());
      setLineaId(value.getLineaId());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setServicio(value.getServicio());
      setFamiliaGenericoId(value.getFamiliaGenericoId());
      setSerie(value.getSerie());
      setAfectastock(value.getAfectastock());
      setEstado(value.getEstado());
      setCobraIva(value.getCobraIva());
      setCobraIce(value.getCobraIce());
      setMediosProduccion(value.getMediosProduccion());
      setLlevaInventario(value.getLlevaInventario());
      setAceptaDescuento(value.getAceptaDescuento());
      setCobraIvaCliente(value.getCobraIvaCliente());
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
		return ToStringer.toString((GenericoIf)this);
	}
}
