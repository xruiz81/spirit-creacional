package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class VentasPagosData implements VentasPagosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.Long tipo;

   public java.lang.Long getTipo() {
      return tipo;
   }

   public void setTipo(java.lang.Long tipo) {
      this.tipo = tipo;
   }

   private java.lang.String referencia;

   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   private java.lang.Long referenciaId;

   public java.lang.Long getReferenciaId() {
      return referenciaId;
   }

   public void setReferenciaId(java.lang.Long referenciaId) {
      this.referenciaId = referenciaId;
   }

   private java.lang.Long ventastrackId;

   public java.lang.Long getVentastrackId() {
      return ventastrackId;
   }

   public void setVentastrackId(java.lang.Long ventastrackId) {
      this.ventastrackId = ventastrackId;
   }

   private java.lang.String revisado;

   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }

   private java.lang.String numDoc;

   public java.lang.String getNumDoc() {
      return numDoc;
   }

   public void setNumDoc(java.lang.String numDoc) {
      this.numDoc = numDoc;
   }
   public VentasPagosData() {
   }

   public VentasPagosData(com.spirit.pos.entity.VentasPagosIf value) {
      setId(value.getId());
      setValor(value.getValor());
      setTipo(value.getTipo());
      setReferencia(value.getReferencia());
      setReferenciaId(value.getReferenciaId());
      setVentastrackId(value.getVentastrackId());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
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
		return ToStringer.toString((VentasPagosIf)this);
	}
}
