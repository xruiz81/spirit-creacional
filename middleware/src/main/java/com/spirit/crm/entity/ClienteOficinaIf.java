package com.spirit.crm.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ClienteOficinaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getClienteId();

   void setClienteId(java.lang.Long clienteId);

   java.lang.Long getCiudadId();

   void setCiudadId(java.lang.Long ciudadId);

   java.lang.String getDireccion();

   void setDireccion(java.lang.String direccion);

   java.lang.String getTelefono();

   void setTelefono(java.lang.String telefono);

   java.lang.String getFax();

   void setFax(java.lang.String fax);

   java.lang.String getEmail();

   void setEmail(java.lang.String email);

   java.sql.Timestamp getFechaCreacion();

   void setFechaCreacion(java.sql.Timestamp fechaCreacion);

   java.math.BigDecimal getMontoCredito();

   void setMontoCredito(java.math.BigDecimal montoCredito);

   java.math.BigDecimal getMontoGarantia();

   void setMontoGarantia(java.math.BigDecimal montoGarantia);

   java.lang.String getCalificacion();

   void setCalificacion(java.lang.String calificacion);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getDescripcion();

   void setDescripcion(java.lang.String descripcion);

   java.lang.String getCodigoProveedorAuto();

   void setCodigoProveedorAuto(java.lang.String codigoProveedorAuto);

   java.lang.Long getVendedorId();

   void setVendedorId(java.lang.Long vendedorId);


}
