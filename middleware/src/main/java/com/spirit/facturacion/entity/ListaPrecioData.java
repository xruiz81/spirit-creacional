package com.spirit.facturacion.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ListaPrecioData implements ListaPrecioIf, Serializable    {


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

   private java.lang.Long empresaId;

   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   private java.lang.String referenciaFisica;

   public java.lang.String getReferenciaFisica() {
      return referenciaFisica;
   }

   public void setReferenciaFisica(java.lang.String referenciaFisica) {
      this.referenciaFisica = referenciaFisica;
   }

   private java.sql.Date fechaCreacion;

   public java.sql.Date getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Date fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.sql.Date fechaInicio;

   public java.sql.Date getFechaInicio() {
      return fechaInicio;
   }

   public void setFechaInicio(java.sql.Date fechaInicio) {
      this.fechaInicio = fechaInicio;
   }

   private java.sql.Date fechaFinal;

   public java.sql.Date getFechaFinal() {
      return fechaFinal;
   }

   public void setFechaFinal(java.sql.Date fechaFinal) {
      this.fechaFinal = fechaFinal;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }
   public ListaPrecioData() {
   }

   public ListaPrecioData(com.spirit.facturacion.entity.ListaPrecioIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setReferenciaFisica(value.getReferenciaFisica());
      setFechaCreacion(value.getFechaCreacion());
      setFechaInicio(value.getFechaInicio());
      setFechaFinal(value.getFechaFinal());
      setEstado(value.getEstado());
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
		return ToStringer.toString((ListaPrecioIf)this);
	}
}
