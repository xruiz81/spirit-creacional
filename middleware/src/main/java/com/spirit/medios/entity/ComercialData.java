package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ComercialData implements ComercialIf, Serializable    {


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

   private java.lang.Long campanaId;

   public java.lang.Long getCampanaId() {
      return campanaId;
   }

   public void setCampanaId(java.lang.Long campanaId) {
      this.campanaId = campanaId;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Long derechoprogramaId;

   public java.lang.Long getDerechoprogramaId() {
      return derechoprogramaId;
   }

   public void setDerechoprogramaId(java.lang.Long derechoprogramaId) {
      this.derechoprogramaId = derechoprogramaId;
   }

   private java.lang.String version;

   public java.lang.String getVersion() {
      return version;
   }

   public void setVersion(java.lang.String version) {
      this.version = version;
   }

   private java.lang.String duracion;

   public java.lang.String getDuracion() {
      return duracion;
   }

   public void setDuracion(java.lang.String duracion) {
      this.duracion = duracion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long productoClienteId;

   public java.lang.Long getProductoClienteId() {
      return productoClienteId;
   }

   public void setProductoClienteId(java.lang.Long productoClienteId) {
      this.productoClienteId = productoClienteId;
   }

   private java.lang.Long campanaProductoVersionId;

   public java.lang.Long getCampanaProductoVersionId() {
      return campanaProductoVersionId;
   }

   public void setCampanaProductoVersionId(java.lang.Long campanaProductoVersionId) {
      this.campanaProductoVersionId = campanaProductoVersionId;
   }
   public ComercialData() {
   }

   public ComercialData(com.spirit.medios.entity.ComercialIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setCampanaId(value.getCampanaId());
      setDescripcion(value.getDescripcion());
      setDerechoprogramaId(value.getDerechoprogramaId());
      setVersion(value.getVersion());
      setDuracion(value.getDuracion());
      setEstado(value.getEstado());
      setProductoClienteId(value.getProductoClienteId());
      setCampanaProductoVersionId(value.getCampanaProductoVersionId());
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
		return ToStringer.toString((ComercialIf)this);
	}
}
