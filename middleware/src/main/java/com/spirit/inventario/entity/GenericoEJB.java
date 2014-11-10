package com.spirit.inventario.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "GENERICO")
@Entity
public class GenericoEJB implements Serializable, GenericoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
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
   private java.lang.String cobraIvaCliente;

   public GenericoEJB() {
   }

   public GenericoEJB(com.spirit.inventario.entity.GenericoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setAbreviado(value.getAbreviado());
      setNombreGenerico(value.getNombreGenerico());
      setCambioDescripcion(value.getCambioDescripcion());
      setEmpresaId(value.getEmpresaId());
      setTipoproductoId(value.getTipoproductoId());
      setMedidaId(value.getMedidaId());
      setPartidaArancelaria(value.getPartidaArancelaria());
      setFechaCreacion(value.getFechaCreacion());
      setReferencia(value.getReferencia());
      setUsaLote(value.getUsaLote());
      setLineaId(value.getLineaId());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setServicio(value.getServicio());
      setFamiliaGenericoId(value.getFamiliaGenericoId());
      setSerie(value.getSerie());
      setAfectastock(value.getAfectastock());
      setEstado(value.getEstado());
      setCobraIva(value.getCobraIva());
      setCobraIce(value.getCobraIce());
      setMediosProduccion(value.getMediosProduccion());
      setLlevaInventario(value.getLlevaInventario());
      setAceptaDescuento(value.getAceptaDescuento());
      setCobraIvaCliente(value.getCobraIvaCliente());
   }

   public java.lang.Long create(com.spirit.inventario.entity.GenericoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setAbreviado(value.getAbreviado());
      setNombreGenerico(value.getNombreGenerico());
      setCambioDescripcion(value.getCambioDescripcion());
      setEmpresaId(value.getEmpresaId());
      setTipoproductoId(value.getTipoproductoId());
      setMedidaId(value.getMedidaId());
      setPartidaArancelaria(value.getPartidaArancelaria());
      setFechaCreacion(value.getFechaCreacion());
      setReferencia(value.getReferencia());
      setUsaLote(value.getUsaLote());
      setLineaId(value.getLineaId());
      setIva(value.getIva());
      setIce(value.getIce());
      setOtroImpuesto(value.getOtroImpuesto());
      setServicio(value.getServicio());
      setFamiliaGenericoId(value.getFamiliaGenericoId());
      setSerie(value.getSerie());
      setAfectastock(value.getAfectastock());
      setEstado(value.getEstado());
      setCobraIva(value.getCobraIva());
      setCobraIce(value.getCobraIce());
      setMediosProduccion(value.getMediosProduccion());
      setLlevaInventario(value.getLlevaInventario());
      setAceptaDescuento(value.getAceptaDescuento());
      setCobraIvaCliente(value.getCobraIvaCliente());
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

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "ABREVIADO")
   public java.lang.String getAbreviado() {
      return abreviado;
   }

   public void setAbreviado(java.lang.String abreviado) {
      this.abreviado = abreviado;
   }

   @Column(name = "NOMBRE_GENERICO")
   public java.lang.String getNombreGenerico() {
      return nombreGenerico;
   }

   public void setNombreGenerico(java.lang.String nombreGenerico) {
      this.nombreGenerico = nombreGenerico;
   }

   @Column(name = "CAMBIO_DESCRIPCION")
   public java.lang.String getCambioDescripcion() {
      return cambioDescripcion;
   }

   public void setCambioDescripcion(java.lang.String cambioDescripcion) {
      this.cambioDescripcion = cambioDescripcion;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "TIPOPRODUCTO_ID")
   public java.lang.Long getTipoproductoId() {
      return tipoproductoId;
   }

   public void setTipoproductoId(java.lang.Long tipoproductoId) {
      this.tipoproductoId = tipoproductoId;
   }

   @Column(name = "MEDIDA_ID")
   public java.lang.Long getMedidaId() {
      return medidaId;
   }

   public void setMedidaId(java.lang.Long medidaId) {
      this.medidaId = medidaId;
   }

   @Column(name = "PARTIDA_ARANCELARIA")
   public java.lang.String getPartidaArancelaria() {
      return partidaArancelaria;
   }

   public void setPartidaArancelaria(java.lang.String partidaArancelaria) {
      this.partidaArancelaria = partidaArancelaria;
   }

   @Column(name = "FECHA_CREACION")
   public java.sql.Timestamp getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(java.sql.Timestamp fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   @Column(name = "REFERENCIA")
   public java.lang.String getReferencia() {
      return referencia;
   }

   public void setReferencia(java.lang.String referencia) {
      this.referencia = referencia;
   }

   @Column(name = "USA_LOTE")
   public java.lang.String getUsaLote() {
      return usaLote;
   }

   public void setUsaLote(java.lang.String usaLote) {
      this.usaLote = usaLote;
   }

   @Column(name = "LINEA_ID")
   public java.lang.Long getLineaId() {
      return lineaId;
   }

   public void setLineaId(java.lang.Long lineaId) {
      this.lineaId = lineaId;
   }

   @Column(name = "IVA")
   public java.math.BigDecimal getIva() {
      return iva;
   }

   public void setIva(java.math.BigDecimal iva) {
      this.iva = iva;
   }

   @Column(name = "ICE")
   public java.math.BigDecimal getIce() {
      return ice;
   }

   public void setIce(java.math.BigDecimal ice) {
      this.ice = ice;
   }

   @Column(name = "OTRO_IMPUESTO")
   public java.math.BigDecimal getOtroImpuesto() {
      return otroImpuesto;
   }

   public void setOtroImpuesto(java.math.BigDecimal otroImpuesto) {
      this.otroImpuesto = otroImpuesto;
   }

   @Column(name = "SERVICIO")
   public java.lang.String getServicio() {
      return servicio;
   }

   public void setServicio(java.lang.String servicio) {
      this.servicio = servicio;
   }

   @Column(name = "FAMILIA_GENERICO_ID")
   public java.lang.Long getFamiliaGenericoId() {
      return familiaGenericoId;
   }

   public void setFamiliaGenericoId(java.lang.Long familiaGenericoId) {
      this.familiaGenericoId = familiaGenericoId;
   }

   @Column(name = "SERIE")
   public java.lang.String getSerie() {
      return serie;
   }

   public void setSerie(java.lang.String serie) {
      this.serie = serie;
   }

   @Column(name = "AFECTASTOCK")
   public java.lang.String getAfectastock() {
      return afectastock;
   }

   public void setAfectastock(java.lang.String afectastock) {
      this.afectastock = afectastock;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "COBRA_IVA")
   public java.lang.String getCobraIva() {
      return cobraIva;
   }

   public void setCobraIva(java.lang.String cobraIva) {
      this.cobraIva = cobraIva;
   }

   @Column(name = "COBRA_ICE")
   public java.lang.String getCobraIce() {
      return cobraIce;
   }

   public void setCobraIce(java.lang.String cobraIce) {
      this.cobraIce = cobraIce;
   }

   @Column(name = "MEDIOS_PRODUCCION")
   public java.lang.String getMediosProduccion() {
      return mediosProduccion;
   }

   public void setMediosProduccion(java.lang.String mediosProduccion) {
      this.mediosProduccion = mediosProduccion;
   }

   @Column(name = "LLEVA_INVENTARIO")
   public java.lang.String getLlevaInventario() {
      return llevaInventario;
   }

   public void setLlevaInventario(java.lang.String llevaInventario) {
      this.llevaInventario = llevaInventario;
   }

   @Column(name = "ACEPTA_DESCUENTO")
   public java.lang.String getAceptaDescuento() {
      return aceptaDescuento;
   }

   public void setAceptaDescuento(java.lang.String aceptaDescuento) {
      this.aceptaDescuento = aceptaDescuento;
   }

   @Column(name = "COBRA_IVA_CLIENTE")
   public java.lang.String getCobraIvaCliente() {
      return cobraIvaCliente;
   }

   public void setCobraIvaCliente(java.lang.String cobraIvaCliente) {
      this.cobraIvaCliente = cobraIvaCliente;
   }
	public String toString() {
		return ToStringer.toString((GenericoIf)this);
	}
}
