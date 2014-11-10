package com.spirit.compras.reportesData;

public class TransaccionesSRIData {
	
	
	
	/*fecha de emision, tipo de documento, no de comprobante, no de autorizacion, subtotal, base imponible12%,base imponible0%,otros impuestos, iva(usd), valor total,
	%ret. renta, valor retenido, no. comp. de retencion,
	tipo doc(nc/nd), fecha doc,no de nc/nd,valor sin iva, valor de n/c con iva
*/
	
	private static final long serialVersionUID = 4503965903716429869L;
	private String clienteId;
	private String cliente;
	///////////////////////////
	private String fechaEmision;
	private String tipoDocumentoId;
	private String tipoDocumentoNombre;
	private String numeroFactura;
	private String numeroAutorizacion;
	private String subtotalFactura;
	private String baseIva12;
	private String baseIva0;
	private String impuesto;
	private String iva;
	private String total;
	/////////////////////////////////////
	private String codigo;
	private String porcentajeRenta;
	private String valorRetenido;
	private String numeroRetencion;
	////////////////////////////
	
	private String tipoDocNcNd;
	private String fechaDocNcNd;
	private String numeroNcNd;
	private String valorSinIvaNcNd;
	private String valorIvaNcNd;
	
	
	
	private String anulada;
	private String exterior;
	private String reembolso;
	private String reposicion;
	private String normal;
	private String ruc;
	
	public TransaccionesSRIData(){
		clienteId = "";
		cliente = "";
		/////////////
		fechaEmision= "";
		tipoDocumentoId= "";
		tipoDocumentoNombre= "";
		numeroFactura= "";
		numeroAutorizacion= "";
		subtotalFactura= "";
		baseIva12= "";
		baseIva0= "";
		impuesto= "";
		iva= "";
		total= "";
		/////////////
		codigo= "";
		porcentajeRenta= "";
		valorRetenido= "";
		numeroRetencion= "";
		//////////		
		tipoDocNcNd= "";
		fechaDocNcNd= "";
		numeroNcNd= "";
		valorSinIvaNcNd= "";
		valorIvaNcNd= "";		
		/////////////
		anulada = "";
		exterior = "";
		reembolso = "";
		reposicion = "";
		normal = "";
		ruc = "";
		/////////////
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAnulada() {
		return anulada;
	}

	public void setAnulada(String anulada) {
		this.anulada = anulada;
	}

	public String getBaseIva0() {
		return baseIva0;
	}

	public void setBaseIva0(String baseIva0) {
		this.baseIva0 = baseIva0;
	}

	public String getBaseIva12() {
		return baseIva12;
	}

	public void setBaseIva12(String baseIva12) {
		this.baseIva12 = baseIva12;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public String getFechaDocNcNd() {
		return fechaDocNcNd;
	}

	public void setFechaDocNcNd(String fechaDocNcNd) {
		this.fechaDocNcNd = fechaDocNcNd;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public String getNumeroAutorizacion() {
		return numeroAutorizacion;
	}

	public void setNumeroAutorizacion(String numeroAutorizacion) {
		this.numeroAutorizacion = numeroAutorizacion;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getNumeroNcNd() {
		return numeroNcNd;
	}

	public void setNumeroNcNd(String numeroNcNd) {
		this.numeroNcNd = numeroNcNd;
	}

	public String getNumeroRetencion() {
		return numeroRetencion;
	}

	public void setNumeroRetencion(String numeroRetencion) {
		this.numeroRetencion = numeroRetencion;
	}

	public String getPorcentajeRenta() {
		return porcentajeRenta;
	}

	public void setPorcentajeRenta(String porcentajeRenta) {
		this.porcentajeRenta = porcentajeRenta;
	}

	public String getReembolso() {
		return reembolso;
	}

	public void setReembolso(String reembolso) {
		this.reembolso = reembolso;
	}

	public String getReposicion() {
		return reposicion;
	}

	public void setReposicion(String reposicion) {
		this.reposicion = reposicion;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getSubtotalFactura() {
		return subtotalFactura;
	}

	public void setSubtotalFactura(String subtotalFactura) {
		this.subtotalFactura = subtotalFactura;
	}

	public String getTipoDocNcNd() {
		return tipoDocNcNd;
	}

	public void setTipoDocNcNd(String tipoDocNcNd) {
		this.tipoDocNcNd = tipoDocNcNd;
	}

	public String getTipoDocumentoId() {
		return tipoDocumentoId;
	}

	public void setTipoDocumentoId(String tipoDocumentoId) {
		this.tipoDocumentoId = tipoDocumentoId;
	}

	public String getTipoDocumentoNombre() {
		return tipoDocumentoNombre;
	}

	public void setTipoDocumentoNombre(String tipoDocumentoNombre) {
		this.tipoDocumentoNombre = tipoDocumentoNombre;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getValorIvaNcNd() {
		return valorIvaNcNd;
	}

	public void setValorIvaNcNd(String valorIvaNcNd) {
		this.valorIvaNcNd = valorIvaNcNd;
	}

	public String getValorRetenido() {
		return valorRetenido;
	}

	public void setValorRetenido(String valorRetenido) {
		this.valorRetenido = valorRetenido;
	}

	public String getValorSinIvaNcNd() {
		return valorSinIvaNcNd;
	}

	public void setValorSinIvaNcNd(String valorSinIvaNcNd) {
		this.valorSinIvaNcNd = valorSinIvaNcNd;
	}
	
	
	
 
}
