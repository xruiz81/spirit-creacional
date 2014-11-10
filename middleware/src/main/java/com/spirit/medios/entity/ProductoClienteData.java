package com.spirit.medios.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ProductoClienteData implements ProductoClienteIf, Serializable    {


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

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.Long creativoId;

   public java.lang.Long getCreativoId() {
      return creativoId;
   }

   public void setCreativoId(java.lang.Long creativoId) {
      this.creativoId = creativoId;
   }

   private java.lang.Long ejecutivoId;

   public java.lang.Long getEjecutivoId() {
      return ejecutivoId;
   }

   public void setEjecutivoId(java.lang.Long ejecutivoId) {
      this.ejecutivoId = ejecutivoId;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.Long marcaProductoId;

   public java.lang.Long getMarcaProductoId() {
      return marcaProductoId;
   }

   public void setMarcaProductoId(java.lang.Long marcaProductoId) {
      this.marcaProductoId = marcaProductoId;
   }

   private java.lang.String marcaProductoNombre;

   public java.lang.String getMarcaProductoNombre() {
      return marcaProductoNombre;
   }

   public void setMarcaProductoNombre(java.lang.String marcaProductoNombre) {
      this.marcaProductoNombre = marcaProductoNombre;
   }
   public ProductoClienteData() {
   }

   public ProductoClienteData(com.spirit.medios.entity.ProductoClienteIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setClienteId(value.getClienteId());
      setCreativoId(value.getCreativoId());
      setEjecutivoId(value.getEjecutivoId());
      setEstado(value.getEstado());
      setMarcaProductoId(value.getMarcaProductoId());
      setMarcaProductoNombre(value.getMarcaProductoNombre());
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
		return ToStringer.toString((ProductoClienteIf)this);
	}
}
