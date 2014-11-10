package com.spirit.comun.util;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CompraDetalleLiquidacionData implements Serializable    {

	private String fechaEmision;
	
	
	
	
   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long compraId;

   public java.lang.Long getCompraId() {
      return compraId;
   }

   public void setCompraId(java.lang.Long compraId) {
      this.compraId = compraId;
   }

   private java.lang.Long productoId;

   public java.lang.Long getProductoId() {
      return productoId;
   }

   public void setProductoId(java.lang.Long productoId) {
      this.productoId = productoId;
   }

   private java.lang.String cantidad;

   public java.lang.String getCantidad() {
      return cantidad;
   }

   public void setCantidad(java.lang.String cantidad) {
      this.cantidad = cantidad;
   }

   private String valor;

   public String getValor() {
      return valor;
   }

   public void setValor(String valor) {
      this.valor = valor;
   }

   private String descuento;

   public String getDescuento() {
      return descuento;
   }

   public void setDescuento(String descuento) {
      this.descuento = descuento;
   }

   private java.math.BigDecimal iva;

   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   private java.math.BigDecimal ice;

   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   private java.math.BigDecimal otroImpuesto;

   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   private java.lang.Long idSriAir;

   public java.lang.Long getIdSriAir() {
      return idSriAir;
   }

   public void setIdSriAir(java.lang.Long idSriAir) {
      this.idSriAir = idSriAir;
   }
   
   
   private java.lang.String proveedor;
   private java.lang.String ruc;
   private java.lang.String direccion;
   private java.lang.String direccionEmpresa;
   
   
   
   
   

   private java.lang.String descripcion;

   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   private java.lang.Long sriIvaRetencionId;

   public java.lang.Long getSriIvaRetencionId() {
      return sriIvaRetencionId;
   }

   public void setSriIvaRetencionId(java.lang.Long sriIvaRetencionId) {
      this.sriIvaRetencionId = sriIvaRetencionId;
   }
   public CompraDetalleLiquidacionData() {
	   
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

public java.lang.String getProveedor() {
	return proveedor;
}

public void setProveedor(java.lang.String proveedor) {
	this.proveedor = proveedor;
}

public java.lang.String getRuc() {
	return ruc;
}

public void setRuc(java.lang.String ruc) {
	this.ruc = ruc;
}

public java.lang.String getDireccion() {
	return direccion;
}

public void setDireccion(java.lang.String direccion) {
	this.direccion = direccion;
}

public java.lang.String getDireccionEmpresa() {
	return direccionEmpresa;
}

public void setDireccionEmpresa(java.lang.String direccionEmpresa) {
	this.direccionEmpresa = direccionEmpresa;
}

public String getFechaEmision() {
	return fechaEmision;
}

public void setFechaEmision(String fechaEmision) {
	this.fechaEmision = fechaEmision;
}


}
