package com.spirit.facturacion.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "PEDIDO_ENVIO")
@Entity
public class PedidoEnvioEJB implements Serializable, PedidoEnvioIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long pedidoId;
   private java.lang.String metodoEnvio;
   private java.math.BigDecimal valorEnvio;
   private java.lang.String nombresFacturacion;
   private java.lang.String apellidosFacturacion;
   private java.lang.String direccionFacturacion;
   private java.lang.String ciudadFacturacion;
   private java.lang.String regionFacturacion;
   private java.lang.String zipFacturacion;
   private java.lang.String codigoPaisFacturacion;
   private java.lang.String telefonoFacturacion;
   private java.lang.String celularFacturacion;
   private java.lang.String nombresEnvio;
   private java.lang.String apellidosEnvio;
   private java.lang.String direccionEnvio;
   private java.lang.String ciudadEnvio;
   private java.lang.String regionEnvio;
   private java.lang.String zipEnvio;
   private java.lang.String codigoPaisEnvio;
   private java.lang.String telefonoEnvio;
   private java.lang.String celularEnvio;
   private java.lang.String correoCliente;
   private java.lang.String nombresCliente;
   private java.lang.String apellidosCliente;
   private java.lang.String nombreEmpresaFacturacion;
   private java.lang.String nombreEmpresaEnvio;
   private java.lang.String ordenExternaId;
   private java.lang.String fechaActualizacion;
   private java.lang.String identificacionFundacion;
   private java.math.BigDecimal valorDescuento;
   private java.math.BigDecimal valorSubtotal;
   private java.math.BigDecimal valorTotal;
   private java.math.BigDecimal valorImpuesto;

   public PedidoEnvioEJB() {
   }

   public PedidoEnvioEJB(com.spirit.facturacion.entity.PedidoEnvioIf value) {
      setId(value.getId());
      setPedidoId(value.getPedidoId());
      setMetodoEnvio(value.getMetodoEnvio());
      setValorEnvio(value.getValorEnvio());
      setNombresFacturacion(value.getNombresFacturacion());
      setApellidosFacturacion(value.getApellidosFacturacion());
      setDireccionFacturacion(value.getDireccionFacturacion());
      setCiudadFacturacion(value.getCiudadFacturacion());
      setRegionFacturacion(value.getRegionFacturacion());
      setZipFacturacion(value.getZipFacturacion());
      setCodigoPaisFacturacion(value.getCodigoPaisFacturacion());
      setTelefonoFacturacion(value.getTelefonoFacturacion());
      setCelularFacturacion(value.getCelularFacturacion());
      setNombresEnvio(value.getNombresEnvio());
      setApellidosEnvio(value.getApellidosEnvio());
      setDireccionEnvio(value.getDireccionEnvio());
      setCiudadEnvio(value.getCiudadEnvio());
      setRegionEnvio(value.getRegionEnvio());
      setZipEnvio(value.getZipEnvio());
      setCodigoPaisEnvio(value.getCodigoPaisEnvio());
      setTelefonoEnvio(value.getTelefonoEnvio());
      setCelularEnvio(value.getCelularEnvio());
      setCorreoCliente(value.getCorreoCliente());
      setNombresCliente(value.getNombresCliente());
      setApellidosCliente(value.getApellidosCliente());
      setNombreEmpresaFacturacion(value.getNombreEmpresaFacturacion());
      setNombreEmpresaEnvio(value.getNombreEmpresaEnvio());
      setOrdenExternaId(value.getOrdenExternaId());
      setFechaActualizacion(value.getFechaActualizacion());
      setIdentificacionFundacion(value.getIdentificacionFundacion());
      setValorDescuento(value.getValorDescuento());
      setValorSubtotal(value.getValorSubtotal());
      setValorTotal(value.getValorTotal());
      setValorImpuesto(value.getValorImpuesto());
   }

   public java.lang.Long create(com.spirit.facturacion.entity.PedidoEnvioIf value) {
      setId(value.getId());
      setPedidoId(value.getPedidoId());
      setMetodoEnvio(value.getMetodoEnvio());
      setValorEnvio(value.getValorEnvio());
      setNombresFacturacion(value.getNombresFacturacion());
      setApellidosFacturacion(value.getApellidosFacturacion());
      setDireccionFacturacion(value.getDireccionFacturacion());
      setCiudadFacturacion(value.getCiudadFacturacion());
      setRegionFacturacion(value.getRegionFacturacion());
      setZipFacturacion(value.getZipFacturacion());
      setCodigoPaisFacturacion(value.getCodigoPaisFacturacion());
      setTelefonoFacturacion(value.getTelefonoFacturacion());
      setCelularFacturacion(value.getCelularFacturacion());
      setNombresEnvio(value.getNombresEnvio());
      setApellidosEnvio(value.getApellidosEnvio());
      setDireccionEnvio(value.getDireccionEnvio());
      setCiudadEnvio(value.getCiudadEnvio());
      setRegionEnvio(value.getRegionEnvio());
      setZipEnvio(value.getZipEnvio());
      setCodigoPaisEnvio(value.getCodigoPaisEnvio());
      setTelefonoEnvio(value.getTelefonoEnvio());
      setCelularEnvio(value.getCelularEnvio());
      setCorreoCliente(value.getCorreoCliente());
      setNombresCliente(value.getNombresCliente());
      setApellidosCliente(value.getApellidosCliente());
      setNombreEmpresaFacturacion(value.getNombreEmpresaFacturacion());
      setNombreEmpresaEnvio(value.getNombreEmpresaEnvio());
      setOrdenExternaId(value.getOrdenExternaId());
      setFechaActualizacion(value.getFechaActualizacion());
      setIdentificacionFundacion(value.getIdentificacionFundacion());
      setValorDescuento(value.getValorDescuento());
      setValorSubtotal(value.getValorSubtotal());
      setValorTotal(value.getValorTotal());
      setValorImpuesto(value.getValorImpuesto());
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

   @Column(name = "PEDIDO_ID")
   public java.lang.Long getPedidoId() {
      return pedidoId;
   }

   public void setPedidoId(java.lang.Long pedidoId) {
      this.pedidoId = pedidoId;
   }

   @Column(name = "METODO_ENVIO")
   public java.lang.String getMetodoEnvio() {
      return metodoEnvio;
   }

   public void setMetodoEnvio(java.lang.String metodoEnvio) {
      this.metodoEnvio = metodoEnvio;
   }

   @Column(name = "VALOR_ENVIO")
   public java.math.BigDecimal getValorEnvio() {
      return valorEnvio;
   }

   public void setValorEnvio(java.math.BigDecimal valorEnvio) {
      this.valorEnvio = valorEnvio;
   }

   @Column(name = "NOMBRES_FACTURACION")
   public java.lang.String getNombresFacturacion() {
      return nombresFacturacion;
   }

   public void setNombresFacturacion(java.lang.String nombresFacturacion) {
      this.nombresFacturacion = nombresFacturacion;
   }

   @Column(name = "APELLIDOS_FACTURACION")
   public java.lang.String getApellidosFacturacion() {
      return apellidosFacturacion;
   }

   public void setApellidosFacturacion(java.lang.String apellidosFacturacion) {
      this.apellidosFacturacion = apellidosFacturacion;
   }

   @Column(name = "DIRECCION_FACTURACION")
   public java.lang.String getDireccionFacturacion() {
      return direccionFacturacion;
   }

   public void setDireccionFacturacion(java.lang.String direccionFacturacion) {
      this.direccionFacturacion = direccionFacturacion;
   }

   @Column(name = "CIUDAD_FACTURACION")
   public java.lang.String getCiudadFacturacion() {
      return ciudadFacturacion;
   }

   public void setCiudadFacturacion(java.lang.String ciudadFacturacion) {
      this.ciudadFacturacion = ciudadFacturacion;
   }

   @Column(name = "REGION_FACTURACION")
   public java.lang.String getRegionFacturacion() {
      return regionFacturacion;
   }

   public void setRegionFacturacion(java.lang.String regionFacturacion) {
      this.regionFacturacion = regionFacturacion;
   }

   @Column(name = "ZIP_FACTURACION")
   public java.lang.String getZipFacturacion() {
      return zipFacturacion;
   }

   public void setZipFacturacion(java.lang.String zipFacturacion) {
      this.zipFacturacion = zipFacturacion;
   }

   @Column(name = "CODIGO_PAIS_FACTURACION")
   public java.lang.String getCodigoPaisFacturacion() {
      return codigoPaisFacturacion;
   }

   public void setCodigoPaisFacturacion(java.lang.String codigoPaisFacturacion) {
      this.codigoPaisFacturacion = codigoPaisFacturacion;
   }

   @Column(name = "TELEFONO_FACTURACION")
   public java.lang.String getTelefonoFacturacion() {
      return telefonoFacturacion;
   }

   public void setTelefonoFacturacion(java.lang.String telefonoFacturacion) {
      this.telefonoFacturacion = telefonoFacturacion;
   }

   @Column(name = "CELULAR_FACTURACION")
   public java.lang.String getCelularFacturacion() {
      return celularFacturacion;
   }

   public void setCelularFacturacion(java.lang.String celularFacturacion) {
      this.celularFacturacion = celularFacturacion;
   }

   @Column(name = "NOMBRES_ENVIO")
   public java.lang.String getNombresEnvio() {
      return nombresEnvio;
   }

   public void setNombresEnvio(java.lang.String nombresEnvio) {
      this.nombresEnvio = nombresEnvio;
   }

   @Column(name = "APELLIDOS_ENVIO")
   public java.lang.String getApellidosEnvio() {
      return apellidosEnvio;
   }

   public void setApellidosEnvio(java.lang.String apellidosEnvio) {
      this.apellidosEnvio = apellidosEnvio;
   }

   @Column(name = "DIRECCION_ENVIO")
   public java.lang.String getDireccionEnvio() {
      return direccionEnvio;
   }

   public void setDireccionEnvio(java.lang.String direccionEnvio) {
      this.direccionEnvio = direccionEnvio;
   }

   @Column(name = "CIUDAD_ENVIO")
   public java.lang.String getCiudadEnvio() {
      return ciudadEnvio;
   }

   public void setCiudadEnvio(java.lang.String ciudadEnvio) {
      this.ciudadEnvio = ciudadEnvio;
   }

   @Column(name = "REGION_ENVIO")
   public java.lang.String getRegionEnvio() {
      return regionEnvio;
   }

   public void setRegionEnvio(java.lang.String regionEnvio) {
      this.regionEnvio = regionEnvio;
   }

   @Column(name = "ZIP_ENVIO")
   public java.lang.String getZipEnvio() {
      return zipEnvio;
   }

   public void setZipEnvio(java.lang.String zipEnvio) {
      this.zipEnvio = zipEnvio;
   }

   @Column(name = "CODIGO_PAIS_ENVIO")
   public java.lang.String getCodigoPaisEnvio() {
      return codigoPaisEnvio;
   }

   public void setCodigoPaisEnvio(java.lang.String codigoPaisEnvio) {
      this.codigoPaisEnvio = codigoPaisEnvio;
   }

   @Column(name = "TELEFONO_ENVIO")
   public java.lang.String getTelefonoEnvio() {
      return telefonoEnvio;
   }

   public void setTelefonoEnvio(java.lang.String telefonoEnvio) {
      this.telefonoEnvio = telefonoEnvio;
   }

   @Column(name = "CELULAR_ENVIO")
   public java.lang.String getCelularEnvio() {
      return celularEnvio;
   }

   public void setCelularEnvio(java.lang.String celularEnvio) {
      this.celularEnvio = celularEnvio;
   }

   @Column(name = "CORREO_CLIENTE")
   public java.lang.String getCorreoCliente() {
      return correoCliente;
   }

   public void setCorreoCliente(java.lang.String correoCliente) {
      this.correoCliente = correoCliente;
   }

   @Column(name = "NOMBRES_CLIENTE")
   public java.lang.String getNombresCliente() {
      return nombresCliente;
   }

   public void setNombresCliente(java.lang.String nombresCliente) {
      this.nombresCliente = nombresCliente;
   }

   @Column(name = "APELLIDOS_CLIENTE")
   public java.lang.String getApellidosCliente() {
      return apellidosCliente;
   }

   public void setApellidosCliente(java.lang.String apellidosCliente) {
      this.apellidosCliente = apellidosCliente;
   }

   @Column(name = "NOMBRE_EMPRESA_FACTURACION")
   public java.lang.String getNombreEmpresaFacturacion() {
      return nombreEmpresaFacturacion;
   }

   public void setNombreEmpresaFacturacion(java.lang.String nombreEmpresaFacturacion) {
      this.nombreEmpresaFacturacion = nombreEmpresaFacturacion;
   }

   @Column(name = "NOMBRE_EMPRESA_ENVIO")
   public java.lang.String getNombreEmpresaEnvio() {
      return nombreEmpresaEnvio;
   }

   public void setNombreEmpresaEnvio(java.lang.String nombreEmpresaEnvio) {
      this.nombreEmpresaEnvio = nombreEmpresaEnvio;
   }

   @Column(name = "ORDEN_EXTERNA_ID")
   public java.lang.String getOrdenExternaId() {
      return ordenExternaId;
   }

   public void setOrdenExternaId(java.lang.String ordenExternaId) {
      this.ordenExternaId = ordenExternaId;
   }

   @Column(name = "FECHA_ACTUALIZACION")
   public java.lang.String getFechaActualizacion() {
      return fechaActualizacion;
   }

   public void setFechaActualizacion(java.lang.String fechaActualizacion) {
      this.fechaActualizacion = fechaActualizacion;
   }

   @Column(name = "IDENTIFICACION_FUNDACION")
   public java.lang.String getIdentificacionFundacion() {
      return identificacionFundacion;
   }

   public void setIdentificacionFundacion(java.lang.String identificacionFundacion) {
      this.identificacionFundacion = identificacionFundacion;
   }

   @Column(name = "VALOR_DESCUENTO")
   public java.math.BigDecimal getValorDescuento() {
      return valorDescuento;
   }

   public void setValorDescuento(java.math.BigDecimal valorDescuento) {
      this.valorDescuento = valorDescuento;
   }

   @Column(name = "VALOR_SUBTOTAL")
   public java.math.BigDecimal getValorSubtotal() {
      return valorSubtotal;
   }

   public void setValorSubtotal(java.math.BigDecimal valorSubtotal) {
      this.valorSubtotal = valorSubtotal;
   }

   @Column(name = "VALOR_TOTAL")
   public java.math.BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(java.math.BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   @Column(name = "VALOR_IMPUESTO")
   public java.math.BigDecimal getValorImpuesto() {
      return valorImpuesto;
   }

   public void setValorImpuesto(java.math.BigDecimal valorImpuesto) {
      this.valorImpuesto = valorImpuesto;
   }
	public String toString() {
		return ToStringer.toString((PedidoEnvioIf)this);
	}
}
