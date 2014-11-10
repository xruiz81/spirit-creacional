package com.spirit.nomina.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class RubroData implements RubroIf, Serializable    {


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

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String frecuencia;

   public java.lang.String getFrecuencia() {
      return frecuencia;
   }

   public void setFrecuencia(java.lang.String frecuencia) {
      this.frecuencia = frecuencia;
   }

   private java.lang.String tipoRubro;

   public java.lang.String getTipoRubro() {
      return tipoRubro;
   }

   public void setTipoRubro(java.lang.String tipoRubro) {
      this.tipoRubro = tipoRubro;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.Long tiporolId;

   public java.lang.Long getTiporolId() {
      return tiporolId;
   }

   public void setTiporolId(java.lang.Long tiporolId) {
      this.tiporolId = tiporolId;
   }

   private java.sql.Date fecha;

   public java.sql.Date getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Date fecha) {
      this.fecha = fecha;
   }

   private java.lang.String politica;

   public java.lang.String getPolitica() {
      return politica;
   }

   public void setPolitica(java.lang.String politica) {
      this.politica = politica;
   }

   private java.lang.String modoOperacion;

   public java.lang.String getModoOperacion() {
      return modoOperacion;
   }

   public void setModoOperacion(java.lang.String modoOperacion) {
      this.modoOperacion = modoOperacion;
   }

   private java.lang.String pagoIndividual;

   public java.lang.String getPagoIndividual() {
      return pagoIndividual;
   }

   public void setPagoIndividual(java.lang.String pagoIndividual) {
      this.pagoIndividual = pagoIndividual;
   }

   private java.lang.String nemonico;

   public java.lang.String getNemonico() {
      return nemonico;
   }

   public void setNemonico(java.lang.String nemonico) {
      this.nemonico = nemonico;
   }
   public RubroData() {
   }

   public RubroData(com.spirit.nomina.entity.RubroIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setEmpresaId(value.getEmpresaId());
      setFrecuencia(value.getFrecuencia());
      setTipoRubro(value.getTipoRubro());
      setNombre(value.getNombre());
      setTiporolId(value.getTiporolId());
      setFecha(value.getFecha());
      setPolitica(value.getPolitica());
      setModoOperacion(value.getModoOperacion());
      setPagoIndividual(value.getPagoIndividual());
      setNemonico(value.getNemonico());
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
		return ToStringer.toString((RubroIf)this);
	}
}
