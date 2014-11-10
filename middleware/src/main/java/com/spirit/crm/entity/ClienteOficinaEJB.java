package com.spirit.crm.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CLIENTE_OFICINA")
@Entity
public class ClienteOficinaEJB implements Serializable, ClienteOficinaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.Long clienteId;
   private java.lang.Long ciudadId;
   private java.lang.String direccion;
   private java.lang.String telefono;
   private java.lang.String fax;
   private java.lang.String email;
   private java.sql.Timestamp fechaCreacion;
   private java.math.BigDecimal montoCredito;
   private java.math.BigDecimal montoGarantia;
   private java.lang.String calificacion;
   private java.lang.String observacion;
   private java.lang.String estado;
   private java.lang.String descripcion;
   private java.lang.String codigoProveedorAuto;
   private java.lang.Long vendedorId;

   public ClienteOficinaEJB() {
   }

   public ClienteOficinaEJB(com.spirit.crm.entity.ClienteOficinaIf value) {
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

   public java.lang.Long create(com.spirit.crm.entity.ClienteOficinaIf value) {
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "CIUDAD_ID")
   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   @Column(name = "DIRECCION")
   public java.lang.String getDireccion() {
      return direccion;
   }

   public void setDireccion(java.lang.String direccion) {
      this.direccion = direccion;
   }

   @Column(name = "TELEFONO")
   public java.lang.String getTelefono() {
      return telefono;
   }

   public void setTelefono(java.lang.String telefono) {
      this.telefono = telefono;
   }

   @Column(name = "FAX")
   public java.lang.String getFax() {
      return fax;
   }

   public void setFax(java.lang.String fax) {
      this.fax = fax;
   }

   @Column(name = "EMAIL")
   public java.lang.String getEmail() {
      return email;
   }

   public void setEmail(java.lang.String email) {
      this.email = email;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "MONTO_CREDITO")
   public java.math.BigDecimal getMontoCredito() {
      return montoCredito;
   }

   public void setMontoCredito(java.math.BigDecimal montoCredito) {
      this.montoCredito = montoCredito;
   }

   @Column(name = "MONTO_GARANTIA")
   public java.math.BigDecimal getMontoGarantia() {
      return montoGarantia;
   }

   public void setMontoGarantia(java.math.BigDecimal montoGarantia) {
      this.montoGarantia = montoGarantia;
   }

   @Column(name = "CALIFICACION")
   public java.lang.String getCalificacion() {
      return calificacion;
   }

   public void setCalificacion(java.lang.String calificacion) {
      this.calificacion = calificacion;
   }

   @Column(name = "OBSERVACION")
   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "CODIGO_PROVEEDOR_AUTO")
   public java.lang.String getCodigoProveedorAuto() {
      return codigoProveedorAuto;
   }

   public void setCodigoProveedorAuto(java.lang.String codigoProveedorAuto) {
      this.codigoProveedorAuto = codigoProveedorAuto;
   }

   @Column(name = "VENDEDOR_ID")
   public java.lang.Long getVendedorId() {
      return vendedorId;
   }

   public void setVendedorId(java.lang.Long vendedorId) {
      this.vendedorId = vendedorId;
   }
	public String toString() {
		return ToStringer.toString((ClienteOficinaIf)this);
	}
}
