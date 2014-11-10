package com.spirit.crm.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class ClienteOficinaData implements ClienteOficinaIf, Serializable    {


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

   private java.lang.Long clienteId;

   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   private java.lang.Long ciudadId;

   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   private java.lang.String direccion;

   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   private java.lang.String telefono;

   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   private java.lang.String fax;

   public java.lang.String getFax() {
      return fax;
   }

   public void setFax(java.lang.String fax) {
      this.fax = fax;
   }

   private java.lang.String email;

   public java.lang.String getEmail() {
      return email;
   }

   public void setEmail(java.lang.String email) {
      this.email = email;
   }

   private java.sql.Timestamp fechaCreacion;

   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private java.math.BigDecimal montoCredito;

   public java.math.BigDecimal getMontoCredito() {
      return montoCredito;
   }

   public void setMontoCredito(java.math.BigDecimal montoCredito) {
      this.montoCredito = montoCredito;
   }

   private java.math.BigDecimal montoGarantia;

   public java.math.BigDecimal getMontoGarantia() {
      return montoGarantia;
   }

   public void setMontoGarantia(java.math.BigDecimal montoGarantia) {
      this.montoGarantia = montoGarantia;
   }

   private java.lang.String calificacion;

   public java.lang.String getCalificacion() {
      return calificacion;
   }

   public void setCalificacion(java.lang.String calificacion) {
      this.calificacion = calificacion;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.String codigoProveedorAuto;

   public java.lang.String getCodigoProveedorAuto() {
      return codigoProveedorAuto;
   }

   public void setCodigoProveedorAuto(java.lang.String codigoProveedorAuto) {
      this.codigoProveedorAuto = codigoProveedorAuto;
   }

   private java.lang.Long vendedorId;

   public java.lang.Long getVendedorId() {
      return vendedorId;
   }

   public void setVendedorId(java.lang.Long vendedorId) {
      this.vendedorId = vendedorId;
   }
   public ClienteOficinaData() {
   }

   public ClienteOficinaData(com.spirit.crm.entity.ClienteOficinaIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setClienteId(value.getClienteId());
      setCiudadId(value.getCiudadId());
      setDireccion(value.getDireccion());
      setTelefono(value.getTelefono());
      setFax(value.getFax());
      setEmail(value.getEmail());
      setFechaCreacion(value.getFechaCreacion());
      setMontoCredito(value.getMontoCredito());
      setMontoGarantia(value.getMontoGarantia());
      setCalificacion(value.getCalificacion());
      setObservacion(value.getObservacion());
      setEstado(value.getEstado());
      setDescripcion(value.getDescripcion());
      setCodigoProveedorAuto(value.getCodigoProveedorAuto());
      setVendedorId(value.getVendedorId());
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
		return ToStringer.toString((ClienteOficinaIf)this);
	}
}
