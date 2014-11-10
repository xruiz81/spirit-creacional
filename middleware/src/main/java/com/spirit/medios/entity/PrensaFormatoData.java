package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class PrensaFormatoData implements PrensaFormatoIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String formato;

   public java.lang.String getFormato() {
      return formato;
   }

   public void setFormato(java.lang.String formato) {
      this.formato = formato;
   }

   private java.math.BigDecimal anchoColumnas;

   public java.math.BigDecimal getAnchoColumnas() {
      return anchoColumnas;
   }

   public void setAnchoColumnas(java.math.BigDecimal anchoColumnas) {
      this.anchoColumnas = anchoColumnas;
   }

   private java.math.BigDecimal altoModulos;

   public java.math.BigDecimal getAltoModulos() {
      return altoModulos;
   }

   public void setAltoModulos(java.math.BigDecimal altoModulos) {
      this.altoModulos = altoModulos;
   }

   private java.math.BigDecimal anchoCm;

   public java.math.BigDecimal getAnchoCm() {
      return anchoCm;
   }

   public void setAnchoCm(java.math.BigDecimal anchoCm) {
      this.anchoCm = anchoCm;
   }

   private java.math.BigDecimal altoCm;

   public java.math.BigDecimal getAltoCm() {
      return altoCm;
   }

   public void setAltoCm(java.math.BigDecimal altoCm) {
      this.altoCm = altoCm;
   }
   public PrensaFormatoData() {
   }

   public PrensaFormatoData(com.spirit.medios.entity.PrensaFormatoIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setFormato(value.getFormato());
      setAnchoColumnas(value.getAnchoColumnas());
      setAltoModulos(value.getAltoModulos());
      setAnchoCm(value.getAnchoCm());
      setAltoCm(value.getAltoCm());
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
		return ToStringer.toString((PrensaFormatoIf)this);
	}
}
