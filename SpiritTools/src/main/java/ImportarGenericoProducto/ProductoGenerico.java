package ImportarGenericoProducto;

public class ProductoGenerico {

	 private java.lang.Long id;
	   private java.lang.Long genericoId;
	   private java.lang.Long presentacionId;
	   private java.lang.Long proveedorId;
	   private java.lang.String origenProducto;
	   private java.lang.String codigoBarras;
	   private java.math.BigDecimal costo;
	   private java.sql.Timestamp fechaCreacionprod;
	   private java.math.BigDecimal rebate;
	   private java.lang.String aceptapromocion;
	   private java.lang.String permiteventa;
	   private java.lang.String aceptadevolucion;
	   private java.lang.String cambioprecio;
	   private java.lang.String estadoprod;
	   private java.lang.String codigoprod;
	   private java.math.BigDecimal margenminimo;
	   private java.lang.String subproveedor;
	   private java.math.BigDecimal costoLifo;
	   private java.math.BigDecimal costoFifo;
	   private java.math.BigDecimal pesoBruto;
	   private java.math.BigDecimal pesoNeto;
	   private java.lang.Long colorId;
	   private java.lang.Long marcaId;
	   private java.lang.Long modeloId;
	   private java.lang.String generarCodigoBarras;
	   
	   
	   private java.lang.String proveedorIdentificacion;
	   
	   private java.lang.Long idgen;
	   private java.lang.String codigogen;
	   private java.lang.String nombre;
	   private java.lang.String abreviado;
	   private java.lang.String nombreGenerico;
	   private java.lang.String cambioDescripcion;
	   private java.lang.Long empresaId;
	   private java.lang.Long tipoproductoId;
	   private java.lang.Long medidaId;
	   private java.lang.String partidaArancelaria;
	   private java.sql.Timestamp fechaCreacion;
	   private java.lang.String referencia;
	   private java.lang.String usaLote;
	   private java.lang.Long lineaId;
	   private java.math.BigDecimal iva;
	   private java.math.BigDecimal ice;
	   private java.math.BigDecimal otroImpuesto;
	   private java.lang.String servicio;
	   private java.lang.Long familiaGenericoId;
	   private java.lang.String serie;
	   private java.lang.String afectastock;
	   private java.lang.String estado;
	   private java.lang.String cobraIva;
	   private java.lang.String cobraIce;
	   private java.lang.String mediosProduccion;
	   private java.lang.String llevaInventario;
	   private java.lang.String aceptaDescuento;
	   
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.Long getGenericoId() {
		return genericoId;
	}
	public void setGenericoId(java.lang.Long genericoId) {
		this.genericoId = genericoId;
	}
	public java.lang.Long getPresentacionId() {
		return presentacionId;
	}
	public void setPresentacionId(java.lang.Long presentacionId) {
		this.presentacionId = presentacionId;
	}
	public java.lang.Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(java.lang.Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	public java.lang.String getOrigenProducto() {
		return origenProducto;
	}
	public void setOrigenProducto(java.lang.String origenProducto) {
		this.origenProducto = origenProducto;
	}
	public java.lang.String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(java.lang.String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public java.math.BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(java.math.BigDecimal costo) {
		this.costo = costo;
	}
	public java.sql.Timestamp getFechaCreacionprod() {
		return fechaCreacionprod;
	}
	public void setFechaCreacionprod(java.sql.Timestamp fechaCreacionprod) {
		this.fechaCreacionprod = fechaCreacionprod;
	}
	public java.math.BigDecimal getRebate() {
		return rebate;
	}
	public void setRebate(java.math.BigDecimal rebate) {
		this.rebate = rebate;
	}
	public java.lang.String getAceptapromocion() {
		return aceptapromocion;
	}
	public void setAceptapromocion(java.lang.String aceptapromocion) {
		this.aceptapromocion = aceptapromocion;
	}
	public java.lang.String getPermiteventa() {
		return permiteventa;
	}
	public void setPermiteventa(java.lang.String permiteventa) {
		this.permiteventa = permiteventa;
	}
	public java.lang.String getAceptadevolucion() {
		return aceptadevolucion;
	}
	public void setAceptadevolucion(java.lang.String aceptadevolucion) {
		this.aceptadevolucion = aceptadevolucion;
	}
	public java.lang.String getCambioprecio() {
		return cambioprecio;
	}
	public void setCambioprecio(java.lang.String cambioprecio) {
		this.cambioprecio = cambioprecio;
	}
	public java.lang.String getEstadoprod() {
		return estadoprod;
	}
	public void setEstadoprod(java.lang.String estadoprod) {
		this.estadoprod = estadoprod;
	}
	public java.lang.String getCodigoprod() {
		return codigoprod;
	}
	public void setCodigoprod(java.lang.String codigoprod) {
		this.codigoprod = codigoprod;
	}
	public java.math.BigDecimal getMargenminimo() {
		return margenminimo;
	}
	public void setMargenminimo(java.math.BigDecimal margenminimo) {
		this.margenminimo = margenminimo;
	}
	public java.lang.String getSubproveedor() {
		return subproveedor;
	}
	public void setSubproveedor(java.lang.String subproveedor) {
		this.subproveedor = subproveedor;
	}
	public java.math.BigDecimal getCostoLifo() {
		return costoLifo;
	}
	public void setCostoLifo(java.math.BigDecimal costoLifo) {
		this.costoLifo = costoLifo;
	}
	public java.math.BigDecimal getCostoFifo() {
		return costoFifo;
	}
	public void setCostoFifo(java.math.BigDecimal costoFifo) {
		this.costoFifo = costoFifo;
	}
	public java.math.BigDecimal getPesoBruto() {
		return pesoBruto;
	}
	public void setPesoBruto(java.math.BigDecimal pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	public java.math.BigDecimal getPesoNeto() {
		return pesoNeto;
	}
	public void setPesoNeto(java.math.BigDecimal pesoNeto) {
		this.pesoNeto = pesoNeto;
	}
	public java.lang.Long getColorId() {
		return colorId;
	}
	public void setColorId(java.lang.Long colorId) {
		this.colorId = colorId;
	}
	public java.lang.Long getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(java.lang.Long marcaId) {
		this.marcaId = marcaId;
	}
	public java.lang.Long getModeloId() {
		return modeloId;
	}
	public void setModeloId(java.lang.Long modeloId) {
		this.modeloId = modeloId;
	}
	public java.lang.String getGenerarCodigoBarras() {
		return generarCodigoBarras;
	}
	public void setGenerarCodigoBarras(java.lang.String generarCodigoBarras) {
		this.generarCodigoBarras = generarCodigoBarras;
	}
	public java.lang.Long getIdgen() {
		return idgen;
	}
	public void setIdgen(java.lang.Long idgen) {
		this.idgen = idgen;
	}
	public java.lang.String getCodigogen() {
		return codigogen;
	}
	public void setCodigogen(java.lang.String codigogen) {
		this.codigogen = codigogen;
	}
	public java.lang.String getNombre() {
		return nombre;
	}
	public void setNombregen(java.lang.String nombre) {
		this.nombre = nombre;
	}
	public java.lang.String getAbreviado() {
		return abreviado;
	}
	public void setAbreviado(java.lang.String abreviado) {
		this.abreviado = abreviado;
	}
	public java.lang.String getNombreGenerico() {
		return nombreGenerico;
	}
	public void setNombreGenerico(java.lang.String nombreGenerico) {
		this.nombreGenerico = nombreGenerico;
	}
	public java.lang.String getCambioDescripcion() {
		return cambioDescripcion;
	}
	public void setCambioDescripcion(java.lang.String cambioDescripcion) {
		this.cambioDescripcion = cambioDescripcion;
	}
	public java.lang.Long getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(java.lang.Long empresaId) {
		this.empresaId = empresaId;
	}
	public java.lang.Long getTipoproductoId() {
		return tipoproductoId;
	}
	public void setTipoproductoId(java.lang.Long tipoproductoId) {
		this.tipoproductoId = tipoproductoId;
	}
	public java.lang.Long getMedidaId() {
		return medidaId;
	}
	public void setMedidaId(java.lang.Long medidaId) {
		this.medidaId = medidaId;
	}
	public java.lang.String getPartidaArancelaria() {
		return partidaArancelaria;
	}
	public void setPartidaArancelaria(java.lang.String partidaArancelaria) {
		this.partidaArancelaria = partidaArancelaria;
	}
	public java.sql.Timestamp getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public java.lang.String getReferencia() {
		return referencia;
	}
	public void setReferencia(java.lang.String referencia) {
		this.referencia = referencia;
	}
	public java.lang.String getUsaLote() {
		return usaLote;
	}
	public void setUsaLote(java.lang.String usaLote) {
		this.usaLote = usaLote;
	}
	public java.lang.Long getLineaId() {
		return lineaId;
	}
	public void setLineaId(java.lang.Long lineaId) {
		this.lineaId = lineaId;
	}
	public java.math.BigDecimal getIva() {
		return iva;
	}
	public void setIva(java.math.BigDecimal iva) {
		this.iva = iva;
	}
	public java.math.BigDecimal getIce() {
		return ice;
	}
	public void setIce(java.math.BigDecimal ice) {
		this.ice = ice;
	}
	public java.math.BigDecimal getOtroImpuesto() {
		return otroImpuesto;
	}
	public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
		this.otroImpuesto = otroImpuesto;
	}
	public java.lang.String getServicio() {
		return servicio;
	}
	public void setServicio(java.lang.String servicio) {
		this.servicio = servicio;
	}
	public java.lang.Long getFamiliaGenericoId() {
		return familiaGenericoId;
	}
	public void setFamiliaGenericoId(java.lang.Long familiaGenericoId) {
		this.familiaGenericoId = familiaGenericoId;
	}
	public java.lang.String getSerie() {
		return serie;
	}
	public void setSerie(java.lang.String serie) {
		this.serie = serie;
	}
	public java.lang.String getAfectastock() {
		return afectastock;
	}
	public void setAfectastock(java.lang.String afectastock) {
		this.afectastock = afectastock;
	}
	public java.lang.String getEstado() {
		return estado;
	}
	public void setEstado(java.lang.String estado) {
		this.estado = estado;
	}
	public java.lang.String getCobraIva() {
		return cobraIva;
	}
	public void setCobraIva(java.lang.String cobraIva) {
		this.cobraIva = cobraIva;
	}
	public java.lang.String getCobraIce() {
		return cobraIce;
	}
	public void setCobraIce(java.lang.String cobraIce) {
		this.cobraIce = cobraIce;
	}
	public java.lang.String getMediosProduccion() {
		return mediosProduccion;
	}
	public void setMediosProduccion(java.lang.String mediosProduccion) {
		this.mediosProduccion = mediosProduccion;
	}
	public java.lang.String getLlevaInventario() {
		return llevaInventario;
	}
	public void setLlevaInventario(java.lang.String llevaInventario) {
		this.llevaInventario = llevaInventario;
	}
	public java.lang.String getAceptaDescuento() {
		return aceptaDescuento;
	}
	public void setAceptaDescuento(java.lang.String aceptaDescuento) {
		this.aceptaDescuento = aceptaDescuento;
	}
	public java.lang.String getProveedorIdentificacion() {
		return proveedorIdentificacion;
	}
	public void setProveedorIdentificacion(java.lang.String proveedorIdentificacion) {
		this.proveedorIdentificacion = proveedorIdentificacion;
	} 

	
}
