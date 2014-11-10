package com.spirit.facturacion.gui.reporteData;

import java.io.Serializable;

public class LibroVentasNotasCreditoData implements Serializable {
	private static final long serialVersionUID = 6632016095670928759L;
	private String clienteOficina;
	private String presupuesto;
	private String preimpresoFactura;
	private String notaCredito;
	private String fechaFactura;
	private String productoCliente;
	private String tipoMedio;
	
	private String inversion;
	
	private String subtotalFactura;
	private String ivaFactura;
	private String totalFactura;
	private String sap;
	
	private String fechaPagoCliente;
	private String numeroIngreso;
	private String valorIngreso;
	
	private String medioOficina;
	private String ordenCompra;
	private String preimpresoFacturaMedio;
	private String fechaFacturaMedio;
	private String subtotalFacturaMedio;
	
	private String ivaFacturaMedio;
	
	private String totalFacturaMedio;
	
	private String retencionIvaMedio;
	private String retencionRentaMedio;
	
	private String valorPagoMedio;
	private String fechaPagoMedio;
	private String formaPago;
	private String numeroEgreso;
	private String numeroCheque;
	private String notaCreditoProveedor;
	private String valorNotaCreditoProveedor;
	private String valorAplicadoFacturaMedio;
	
	
	public LibroVentasNotasCreditoData() {
		clienteOficina = "";
		presupuesto = "";
		preimpresoFactura = "";
		notaCredito = "";
		fechaFactura = "";
		productoCliente = "";		
		tipoMedio = "";
		
		inversion = "";
		
		subtotalFactura = "";
		ivaFactura = "";
		totalFactura = "";
		sap = "";
		
		fechaPagoCliente = "";
		numeroIngreso = "";
		valorIngreso = "";
		
		medioOficina = "";		
		ordenCompra = "";
		preimpresoFacturaMedio = "";
		fechaFacturaMedio = "";
		subtotalFacturaMedio = "";
		
		ivaFacturaMedio = "";
		
		totalFacturaMedio = "";
		
		retencionIvaMedio = "";
		retencionRentaMedio = "";
		
		valorPagoMedio = "";		
		fechaPagoMedio = "";
		formaPago = "";
		numeroEgreso = "";
		numeroCheque = "";
		notaCreditoProveedor = "";
		valorNotaCreditoProveedor = "";		
		valorAplicadoFacturaMedio = "";		
	}
	
	public String getCampo(int i){
		String campo = "";
		
		switch (i) {
		case 1:
			campo = getClienteOficina();
			break;
		case 2:
			campo = getPresupuesto();
			break;
		case 3:
			campo = getPreimpresoFactura();
			break;
		case 4:
			campo = getNotaCredito();
			break;
		case 5:
			campo = getFechaFactura();
			break;
		case 6:
			campo = getProductoCliente();
			break;
		case 7:
			campo = getTipoMedio();
			break;
		case 8:
			campo = getInversion();
			break;
		case 9:
			campo = getSubtotalFactura();
			break;
		case 10:
			campo = getIvaFactura();
			break;
		case 11:
			campo = getTotalFactura();
			break;
		case 12:
			campo = getSap();
			break;
		case 13:
			campo = getFechaPagoCliente();
			break;
		case 14:
			campo = getNumeroIngreso();
			break;
		case 15:
			campo = getValorIngreso();
			break;
		case 16:
			campo = getMedioOficina();
			break;
		case 17:
			campo = getOrdenCompra();
			break;
		case 18:
			campo = getPreimpresoFacturaMedio();
			break;
		case 19:
			campo = getFechaFacturaMedio();
			break;
		case 20:
			campo = getSubtotalFacturaMedio();
			break;
		case 21:
			campo = getIvaFacturaMedio();
			break;
		case 22:
			campo = getTotalFacturaMedio();
			break;
		case 23:
			campo = getRetencionRentaMedio();
			break;
		case 24:
			campo = getRetencionIvaMedio();
			break;
		case 25:
			campo = getValorPagoMedio();
			break;
		case 26:
			campo = getFechaPagoMedio();
			break;
		case 27:
			campo = getFormaPago();
			break;
		case 28:
			campo = getNumeroEgreso();
			break;
		case 29:
			campo = getNumeroCheque();
			break;
		case 30:
			campo = getNotaCreditoProveedor();
			break;
		case 31:
			campo = getValorNotaCreditoProveedor();
			break;
		case 32:
			campo = getValorAplicadoFacturaMedio();
			break;		
		
		default:
			campo = "Indice Invalido";
			break;
		}		
		
		return campo;
	}
	
	public String getNombreAtributo(int i){
		String nombre = "";
		
		switch (i) {
		case 1:
			nombre = "Cliente";
			break;
		case 2:
			nombre = "Presupuesto";
			break;
		case 3:
			nombre = "Factura Agencia";
			break;
		case 4:
			nombre = "Nota de Crédito";
			break;
		case 5:
			nombre = "Fecha de Factura";
			break;
		case 6:
			nombre = "Producto";
			break;
		case 7:
			nombre = "Tipo de Medio";
			break;
		case 8:
			nombre = "Inversión";
			break;			
		case 9:
			nombre = "Valor Factura sin IVA";
			break;
		case 10:
			nombre = "IVA";
			break;
		case 11:
			nombre = "Total Factura";
			break;
		case 12:
			nombre = "SAP";
			break;
		case 13:
			nombre = "Fecha de Pago del Cliente";
			break;
		case 14:
			nombre = "Número del Ingreso";
			break;
		case 15:
			nombre = "Valor del Ingreso";
			break;
		case 16:
			nombre = "Medio";
			break;
		case 17:
			nombre = "Orden de Compra";
			break;
		case 18:
			nombre = "Factura del Medio";
			break;
		case 19:
			nombre = "Fecha Factura del Medio";
			break;
		case 20:
			nombre = "Subtotal Factura Medio";
			break;
		case 21:
			nombre = "IVA Factura Medio";
			break;
		case 22:
			nombre = "Total Factura Medio";
			break;
		case 23:
			nombre = "Retención Renta";
			break;
		case 24:
			nombre = "Retención IVA";
			break;
		case 25:
			nombre = "Valor Pagado al Medio";
			break;
		case 26:
			nombre = "Fecha de Pago al Medio";
			break;
		case 27:
			nombre = "Forma de Pago";
			break;
		case 28:
			nombre = "Número del Egreso";
			break;
		case 29:
			nombre = "Número del Cheque";
			break;
		case 30:
			nombre = "Nota de Crédito Proveedor";
			break;
		case 31:
			nombre = "Valor Nota de Crédito Proveedor";
			break;
		case 32:
			nombre = "Valor Aplicado a la Factura del Medio";
			break;	
		
		default:
			nombre = "Indice Invalido";
			break;
		}		
		
		return nombre;
	}
		
	public String getRetencionIvaMedio() {
		return retencionIvaMedio;
	}

	public void setRetencionIvaMedio(String retencionIvaMedio) {
		this.retencionIvaMedio = retencionIvaMedio;
	}

	public String getRetencionRentaMedio() {
		return retencionRentaMedio;
	}

	public void setRetencionRentaMedio(String retencionRentaMedio) {
		this.retencionRentaMedio = retencionRentaMedio;
	}

	public String getIvaFacturaMedio() {
		return ivaFacturaMedio;
	}

	public void setIvaFacturaMedio(String ivaFacturaMedio) {
		this.ivaFacturaMedio = ivaFacturaMedio;
	}

	public String getInversion() {
		return inversion;
	}

	public void setInversion(String inversion) {
		this.inversion = inversion;
	}

	public String getClienteOficina() {
		return clienteOficina;
	}

	public void setClienteOficina(String clienteOficina) {
		this.clienteOficina = clienteOficina;
	}

	public String getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getPreimpresoFactura() {
		return preimpresoFactura;
	}

	public void setPreimpresoFactura(String preimpresoFactura) {
		this.preimpresoFactura = preimpresoFactura;
	}

	public String getNotaCredito() {
		return notaCredito;
	}

	public void setNotaCredito(String notaCredito) {
		this.notaCredito = notaCredito;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getProductoCliente() {
		return productoCliente;
	}

	public void setProductoCliente(String productoCliente) {
		this.productoCliente = productoCliente;
	}

	public String getTipoMedio() {
		return tipoMedio;
	}

	public void setTipoMedio(String tipoMedio) {
		this.tipoMedio = tipoMedio;
	}

	public String getSubtotalFactura() {
		return subtotalFactura;
	}

	public void setSubtotalFactura(String subtotalFactura) {
		this.subtotalFactura = subtotalFactura;
	}

	public String getIvaFactura() {
		return ivaFactura;
	}

	public void setIvaFactura(String ivaFactura) {
		this.ivaFactura = ivaFactura;
	}

	public String getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(String totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getSap() {
		return sap;
	}

	public void setSap(String sap) {
		this.sap = sap;
	}

	public String getMedioOficina() {
		return medioOficina;
	}

	public void setMedioOficina(String medioOficina) {
		this.medioOficina = medioOficina;
	}

	public String getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(String ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public String getPreimpresoFacturaMedio() {
		return preimpresoFacturaMedio;
	}

	public void setPreimpresoFacturaMedio(String preimpresoFacturaMedio) {
		this.preimpresoFacturaMedio = preimpresoFacturaMedio;
	}

	public String getFechaFacturaMedio() {
		return fechaFacturaMedio;
	}

	public void setFechaFacturaMedio(String fechaFacturaMedio) {
		this.fechaFacturaMedio = fechaFacturaMedio;
	}

	public String getSubtotalFacturaMedio() {
		return subtotalFacturaMedio;
	}

	public void setSubtotalFacturaMedio(String subtotalFacturaMedio) {
		this.subtotalFacturaMedio = subtotalFacturaMedio;
	}

	public String getTotalFacturaMedio() {
		return totalFacturaMedio;
	}

	public void setTotalFacturaMedio(String totalFacturaMedio) {
		this.totalFacturaMedio = totalFacturaMedio;
	}

	public String getValorPagoMedio() {
		return valorPagoMedio;
	}

	public void setValorPagoMedio(String valorPagoMedio) {
		this.valorPagoMedio = valorPagoMedio;
	}

	public String getFechaPagoMedio() {
		return fechaPagoMedio;
	}

	public void setFechaPagoMedio(String fechaPagoMedio) {
		this.fechaPagoMedio = fechaPagoMedio;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getNumeroEgreso() {
		return numeroEgreso;
	}

	public void setNumeroEgreso(String numeroEgreso) {
		this.numeroEgreso = numeroEgreso;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getNotaCreditoProveedor() {
		return notaCreditoProveedor;
	}

	public void setNotaCreditoProveedor(String notaCreditoProveedor) {
		this.notaCreditoProveedor = notaCreditoProveedor;
	}

	public String getValorNotaCreditoProveedor() {
		return valorNotaCreditoProveedor;
	}

	public void setValorNotaCreditoProveedor(String valorNotaCreditoProveedor) {
		this.valorNotaCreditoProveedor = valorNotaCreditoProveedor;
	}

	public String getValorAplicadoFacturaMedio() {
		return valorAplicadoFacturaMedio;
	}

	public void setValorAplicadoFacturaMedio(String valorAplicadoFacturaMedio) {
		this.valorAplicadoFacturaMedio = valorAplicadoFacturaMedio;
	}

	public String getFechaPagoCliente() {
		return fechaPagoCliente;
	}

	public void setFechaPagoCliente(String fechaPagoCliente) {
		this.fechaPagoCliente = fechaPagoCliente;
	}

	public String getNumeroIngreso() {
		return numeroIngreso;
	}

	public void setNumeroIngreso(String numeroIngreso) {
		this.numeroIngreso = numeroIngreso;
	}

	public String getValorIngreso() {
		return valorIngreso;
	}

	public void setValorIngreso(String valorIngreso) {
		this.valorIngreso = valorIngreso;
	}	
}