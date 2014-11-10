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

@Table(name = "TARJETA_TIPO")
@Entity
public class TarjetaTipoEJB implements Serializable, TarjetaTipoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long padreId;
   private java.lang.String puntosDinero;
   private java.math.BigDecimal dsctoReferido;
   private java.math.BigDecimal dsctoPropietario;
   private java.math.BigDecimal porcentajeDineroReferido;
   private java.math.BigDecimal porcentajeDineroPropietario;
   private java.lang.Long statusSiguiente;
   private java.lang.Long statusAnterior;
   private java.math.BigDecimal puntosSubirStatus;
   private java.math.BigDecimal dineroSubirStatus;
   private java.math.BigDecimal puntosMantenerStatus;
   private java.math.BigDecimal dineroMantenerStatus;
   private java.lang.Long empresaId;

   public TarjetaTipoEJB() {
   }

   public TarjetaTipoEJB(com.spirit.pos.entity.TarjetaTipoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setPadreId(value.getPadreId());
      setPuntosDinero(value.getPuntosDinero());
      setDsctoReferido(value.getDsctoReferido());
      setDsctoPropietario(value.getDsctoPropietario());
      setPorcentajeDineroReferido(value.getPorcentajeDineroReferido());
      setPorcentajeDineroPropietario(value.getPorcentajeDineroPropietario());
      setStatusSiguiente(value.getStatusSiguiente());
      setStatusAnterior(value.getStatusAnterior());
      setPuntosSubirStatus(value.getPuntosSubirStatus());
      setDineroSubirStatus(value.getDineroSubirStatus());
      setPuntosMantenerStatus(value.getPuntosMantenerStatus());
      setDineroMantenerStatus(value.getDineroMantenerStatus());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.pos.entity.TarjetaTipoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setPadreId(value.getPadreId());
      setPuntosDinero(value.getPuntosDinero());
      setDsctoReferido(value.getDsctoReferido());
      setDsctoPropietario(value.getDsctoPropietario());
      setPorcentajeDineroReferido(value.getPorcentajeDineroReferido());
      setPorcentajeDineroPropietario(value.getPorcentajeDineroPropietario());
      setStatusSiguiente(value.getStatusSiguiente());
      setStatusAnterior(value.getStatusAnterior());
      setPuntosSubirStatus(value.getPuntosSubirStatus());
      setDineroSubirStatus(value.getDineroSubirStatus());
      setPuntosMantenerStatus(value.getPuntosMantenerStatus());
      setDineroMantenerStatus(value.getDineroMantenerStatus());
      setEmpresaId(value.getEmpresaId());
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

   @Column(name = "PADRE_ID")
   public java.lang.Long getPadreId() {
      return padreId;
   }

   public void setPadreId(java.lang.Long padreId) {
      this.padreId = padreId;
   }

   @Column(name = "PUNTOS_DINERO")
   public java.lang.String getPuntosDinero() {
      return puntosDinero;
   }

   public void setPuntosDinero(java.lang.String puntosDinero) {
      this.puntosDinero = puntosDinero;
   }

   @Column(name = "DSCTO_REFERIDO")
   public java.math.BigDecimal getDsctoReferido() {
      return dsctoReferido;
   }

   public void setDsctoReferido(java.math.BigDecimal dsctoReferido) {
      this.dsctoReferido = dsctoReferido;
   }

   @Column(name = "DSCTO_PROPIETARIO")
   public java.math.BigDecimal getDsctoPropietario() {
      return dsctoPropietario;
   }

   public void setDsctoPropietario(java.math.BigDecimal dsctoPropietario) {
      this.dsctoPropietario = dsctoPropietario;
   }

   @Column(name = "PORCENTAJE_DINERO_REFERIDO")
   public java.math.BigDecimal getPorcentajeDineroReferido() {
      return porcentajeDineroReferido;
   }

   public void setPorcentajeDineroReferido(java.math.BigDecimal porcentajeDineroReferido) {
      this.porcentajeDineroReferido = porcentajeDineroReferido;
   }

   @Column(name = "PORCENTAJE_DINERO_PROPIETARIO")
   public java.math.BigDecimal getPorcentajeDineroPropietario() {
      return porcentajeDineroPropietario;
   }

   public void setPorcentajeDineroPropietario(java.math.BigDecimal porcentajeDineroPropietario) {
      this.porcentajeDineroPropietario = porcentajeDineroPropietario;
   }

   @Column(name = "STATUS_SIGUIENTE")
   public java.lang.Long getStatusSiguiente() {
      return statusSiguiente;
   }

   public void setStatusSiguiente(java.lang.Long statusSiguiente) {
      this.statusSiguiente = statusSiguiente;
   }

   @Column(name = "STATUS_ANTERIOR")
   public java.lang.Long getStatusAnterior() {
      return statusAnterior;
   }

   public void setStatusAnterior(java.lang.Long statusAnterior) {
      this.statusAnterior = statusAnterior;
   }

   @Column(name = "PUNTOS_SUBIR_STATUS")
   public java.math.BigDecimal getPuntosSubirStatus() {
      return puntosSubirStatus;
   }

   public void setPuntosSubirStatus(java.math.BigDecimal puntosSubirStatus) {
      this.puntosSubirStatus = puntosSubirStatus;
   }

   @Column(name = "DINERO_SUBIR_STATUS")
   public java.math.BigDecimal getDineroSubirStatus() {
      return dineroSubirStatus;
   }

   public void setDineroSubirStatus(java.math.BigDecimal dineroSubirStatus) {
      this.dineroSubirStatus = dineroSubirStatus;
   }

   @Column(name = "PUNTOS_MANTENER_STATUS")
   public java.math.BigDecimal getPuntosMantenerStatus() {
      return puntosMantenerStatus;
   }

   public void setPuntosMantenerStatus(java.math.BigDecimal puntosMantenerStatus) {
      this.puntosMantenerStatus = puntosMantenerStatus;
   }

   @Column(name = "DINERO_MANTENER_STATUS")
   public java.math.BigDecimal getDineroMantenerStatus() {
      return dineroMantenerStatus;
   }

   public void setDineroMantenerStatus(java.math.BigDecimal dineroMantenerStatus) {
      this.dineroMantenerStatus = dineroMantenerStatus;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((TarjetaTipoIf)this);
	}
}
