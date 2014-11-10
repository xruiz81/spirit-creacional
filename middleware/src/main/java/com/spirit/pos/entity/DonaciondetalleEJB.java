package com.spirit.pos.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "DONACIONDETALLE")
@Entity
public class DonaciondetalleEJB implements Serializable, DonaciondetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String descripcion;
   private java.sql.Timestamp fecha;
   private java.math.BigDecimal cant;
   private java.math.BigDecimal dev;
   private java.math.BigDecimal cantfinal;
   private java.math.BigDecimal valortipo;
   private java.math.BigDecimal total;
   private java.lang.String fundacion;
   private java.lang.Long fundacionid;
   private java.lang.Long modeloId;
   private java.lang.Long colorId;
   private java.lang.Long tipoproducto;
   private java.lang.Long tallaId;
   private java.lang.String nombrecliente;
   private java.lang.Long clienteoficinaid;
   private java.lang.String preimpreso;
   private java.lang.Long facturaaplId;
   private java.lang.Long fundaciondevolucionid;
   private java.lang.String nombrefundaciondevuelta;

   public DonaciondetalleEJB() {
   }

   public DonaciondetalleEJB(com.spirit.pos.entity.DonaciondetalleIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setFecha(value.getFecha());
      setCant(value.getCant());
      setDev(value.getDev());
      setCantfinal(value.getCantfinal());
      setValortipo(value.getValortipo());
      setTotal(value.getTotal());
      setFundacion(value.getFundacion());
      setFundacionid(value.getFundacionid());
      setModeloId(value.getModeloId());
      setColorId(value.getColorId());
      setTipoproducto(value.getTipoproducto());
      setTallaId(value.getTallaId());
      setNombrecliente(value.getNombrecliente());
      setClienteoficinaid(value.getClienteoficinaid());
      setPreimpreso(value.getPreimpreso());
      setFacturaaplId(value.getFacturaaplId());
      setFundaciondevolucionid(value.getFundaciondevolucionid());
      setNombrefundaciondevuelta(value.getNombrefundaciondevuelta());
   }

   public java.lang.Long create(com.spirit.pos.entity.DonaciondetalleIf value) {
      setId(value.getId());
      setDescripcion(value.getDescripcion());
      setFecha(value.getFecha());
      setCant(value.getCant());
      setDev(value.getDev());
      setCantfinal(value.getCantfinal());
      setValortipo(value.getValortipo());
      setTotal(value.getTotal());
      setFundacion(value.getFundacion());
      setFundacionid(value.getFundacionid());
      setModeloId(value.getModeloId());
      setColorId(value.getColorId());
      setTipoproducto(value.getTipoproducto());
      setTallaId(value.getTallaId());
      setNombrecliente(value.getNombrecliente());
      setClienteoficinaid(value.getClienteoficinaid());
      setPreimpreso(value.getPreimpreso());
      setFacturaaplId(value.getFacturaaplId());
      setFundaciondevolucionid(value.getFundaciondevolucionid());
      setNombrefundaciondevuelta(value.getNombrefundaciondevuelta());
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "id")
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

   @Column(name = "descripcion")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }

   @Column(name = "fecha")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "cant")
   public java.math.BigDecimal getCant() {
      return cant;
   }

   public void setCant(java.math.BigDecimal cant) {
      this.cant = cant;
   }

   @Column(name = "dev")
   public java.math.BigDecimal getDev() {
      return dev;
   }

   public void setDev(java.math.BigDecimal dev) {
      this.dev = dev;
   }

   @Column(name = "cantFinal")
   public java.math.BigDecimal getCantfinal() {
      return cantfinal;
   }

   public void setCantfinal(java.math.BigDecimal cantfinal) {
      this.cantfinal = cantfinal;
   }

   @Column(name = "valortipo")
   public java.math.BigDecimal getValortipo() {
      return valortipo;
   }

   public void setValortipo(java.math.BigDecimal valortipo) {
      this.valortipo = valortipo;
   }

   @Column(name = "total")
   public java.math.BigDecimal getTotal() {
      return total;
   }

   public void setTotal(java.math.BigDecimal total) {
      this.total = total;
   }

   @Column(name = "fundacion")
   public java.lang.String getFundacion() {
      return fundacion;
   }

   public void setFundacion(java.lang.String fundacion) {
      this.fundacion = fundacion;
   }

   @Column(name = "fundacionId")
   public java.lang.Long getFundacionid() {
      return fundacionid;
   }

   public void setFundacionid(java.lang.Long fundacionid) {
      this.fundacionid = fundacionid;
   }

   @Column(name = "modelo_id")
   public java.lang.Long getModeloId() {
      return modeloId;
   }

   public void setModeloId(java.lang.Long modeloId) {
      this.modeloId = modeloId;
   }

   @Column(name = "color_id")
   public java.lang.Long getColorId() {
      return colorId;
   }

   public void setColorId(java.lang.Long colorId) {
      this.colorId = colorId;
   }

   @Column(name = "tipoProducto")
   public java.lang.Long getTipoproducto() {
      return tipoproducto;
   }

   public void setTipoproducto(java.lang.Long tipoproducto) {
      this.tipoproducto = tipoproducto;
   }

   @Column(name = "talla_id")
   public java.lang.Long getTallaId() {
      return tallaId;
   }

   public void setTallaId(java.lang.Long tallaId) {
      this.tallaId = tallaId;
   }

   @Column(name = "nombrecliente")
   public java.lang.String getNombrecliente() {
      return nombrecliente;
   }

   public void setNombrecliente(java.lang.String nombrecliente) {
      this.nombrecliente = nombrecliente;
   }

   @Column(name = "clienteoficinaId")
   public java.lang.Long getClienteoficinaid() {
      return clienteoficinaid;
   }

   public void setClienteoficinaid(java.lang.Long clienteoficinaid) {
      this.clienteoficinaid = clienteoficinaid;
   }

   @Column(name = "preimpreso")
   public java.lang.String getPreimpreso() {
      return preimpreso;
   }

   public void setPreimpreso(java.lang.String preimpreso) {
      this.preimpreso = preimpreso;
   }

   @Column(name = "FACTURAAPL_ID")
   public java.lang.Long getFacturaaplId() {
      return facturaaplId;
   }

   public void setFacturaaplId(java.lang.Long facturaaplId) {
      this.facturaaplId = facturaaplId;
   }

   @Column(name = "fundacionDevolucionId")
   public java.lang.Long getFundaciondevolucionid() {
      return fundaciondevolucionid;
   }

   public void setFundaciondevolucionid(java.lang.Long fundaciondevolucionid) {
      this.fundaciondevolucionid = fundaciondevolucionid;
   }

   @Column(name = "nombreFundacionDevuelta")
   public java.lang.String getNombrefundaciondevuelta() {
      return nombrefundaciondevuelta;
   }

   public void setNombrefundaciondevuelta(java.lang.String nombrefundaciondevuelta) {
      this.nombrefundaciondevuelta = nombrefundaciondevuelta;
   }
	public String toString() {
		return ToStringer.toString((DonaciondetalleIf)this);
	}
}
