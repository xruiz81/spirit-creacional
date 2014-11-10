package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CajaSesionData implements CajaSesionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long usuarioId;

   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   private java.lang.Long cajaId;

   public java.lang.Long getCajaId() {
      return cajaId;
   }

   public void setCajaId(java.lang.Long cajaId) {
      this.cajaId = cajaId;
   }

   private java.sql.Timestamp fechaini;

   public java.sql.Timestamp getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Timestamp fechaini) {
      this.fechaini = fechaini;
   }

   private java.sql.Timestamp fechafin;

   public java.sql.Timestamp getFechafin() {
      return fechafin;
   }

   public void setFechafin(java.sql.Timestamp fechafin) {
      this.fechafin = fechafin;
   }

   private java.math.BigDecimal valorInicial;

   public java.math.BigDecimal getValorInicial() {
      return valorInicial;
   }

   public void setValorInicial(java.math.BigDecimal valorInicial) {
      this.valorInicial = valorInicial;
   }

   private java.math.BigDecimal valorFinal;

   public java.math.BigDecimal getValorFinal() {
      return valorFinal;
   }

   public void setValorFinal(java.math.BigDecimal valorFinal) {
      this.valorFinal = valorFinal;
   }

   private java.math.BigDecimal descuadre;

   public java.math.BigDecimal getDescuadre() {
      return descuadre;
   }

   public void setDescuadre(java.math.BigDecimal descuadre) {
      this.descuadre = descuadre;
   }

   private java.math.BigDecimal valorMultas;

   public java.math.BigDecimal getValorMultas() {
      return valorMultas;
   }

   public void setValorMultas(java.math.BigDecimal valorMultas) {
      this.valorMultas = valorMultas;
   }

   private java.math.BigDecimal valorDocumentos;

   public java.math.BigDecimal getValorDocumentos() {
      return valorDocumentos;
   }

   public void setValorDocumentos(java.math.BigDecimal valorDocumentos) {
      this.valorDocumentos = valorDocumentos;
   }

   private java.lang.String turno;

   public java.lang.String getTurno() {
      return turno;
   }

   public void setTurno(java.lang.String turno) {
      this.turno = turno;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String revisado;

   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }
   public CajaSesionData() {
   }

   public CajaSesionData(com.spirit.pos.entity.CajaSesionIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setCajaId(value.getCajaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setValorInicial(value.getValorInicial());
      setValorFinal(value.getValorFinal());
      setDescuadre(value.getDescuadre());
      setValorMultas(value.getValorMultas());
      setValorDocumentos(value.getValorDocumentos());
      setTurno(value.getTurno());
      setEstado(value.getEstado());
      setRevisado(value.getRevisado());
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
		return ToStringer.toString((CajaSesionIf)this);
	}
}
