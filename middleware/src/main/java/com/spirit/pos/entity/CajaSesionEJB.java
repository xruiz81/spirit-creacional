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

@Table(name = "CAJA_SESION")
@Entity
public class CajaSesionEJB implements Serializable, CajaSesionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long usuarioId;
   private java.lang.Long cajaId;
   private java.sql.Timestamp fechaini;
   private java.sql.Timestamp fechafin;
   private java.math.BigDecimal valorInicial;
   private java.math.BigDecimal valorFinal;
   private java.math.BigDecimal descuadre;
   private java.math.BigDecimal valorMultas;
   private java.math.BigDecimal valorDocumentos;
   private java.lang.String turno;
   private java.lang.String estado;
   private java.lang.String revisado;

   public CajaSesionEJB() {
   }

   public CajaSesionEJB(com.spirit.pos.entity.CajaSesionIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setCajaId(value.getCajaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setValorInicial(value.getValorInicial());
      setValorFinal(value.getValorFinal());
      setDescuadre(value.getDescuadre());
      setValorMultas(value.getValorMultas());
      setValorDocumentos(value.getValorDocumentos());
      setTurno(value.getTurno());
      setEstado(value.getEstado());
      setRevisado(value.getRevisado());
   }

   public java.lang.Long create(com.spirit.pos.entity.CajaSesionIf value) {
      setId(value.getId());
      setUsuarioId(value.getUsuarioId());
      setCajaId(value.getCajaId());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setValorInicial(value.getValorInicial());
      setValorFinal(value.getValorFinal());
      setDescuadre(value.getDescuadre());
      setValorMultas(value.getValorMultas());
      setValorDocumentos(value.getValorDocumentos());
      setTurno(value.getTurno());
      setEstado(value.getEstado());
      setRevisado(value.getRevisado());
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

   @Column(name = "USUARIO_ID")
   public java.lang.Long getUsuarioId() {
      return usuarioId;
   }

   public void setUsuarioId(java.lang.Long usuarioId) {
      this.usuarioId = usuarioId;
   }

   @Column(name = "CAJA_ID")
   public java.lang.Long getCajaId() {
      return cajaId;
   }

   public void setCajaId(java.lang.Long cajaId) {
      this.cajaId = cajaId;
   }

   @Column(name = "FECHAINI")
   public java.sql.Timestamp getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Timestamp fechaini) {
      this.fechaini = fechaini;
   }

   @Column(name = "FECHAFIN")
   public java.sql.Timestamp getFechafin() {
      return fechafin;
   }

   public void setFechafin(java.sql.Timestamp fechafin) {
      this.fechafin = fechafin;
   }

   @Column(name = "VALOR_INICIAL")
   public java.math.BigDecimal getValorInicial() {
      return valorInicial;
   }

   public void setValorInicial(java.math.BigDecimal valorInicial) {
      this.valorInicial = valorInicial;
   }

   @Column(name = "VALOR_FINAL")
   public java.math.BigDecimal getValorFinal() {
      return valorFinal;
   }

   public void setValorFinal(java.math.BigDecimal valorFinal) {
      this.valorFinal = valorFinal;
   }

   @Column(name = "DESCUADRE")
   public java.math.BigDecimal getDescuadre() {
      return descuadre;
   }

   public void setDescuadre(java.math.BigDecimal descuadre) {
      this.descuadre = descuadre;
   }

   @Column(name = "VALOR_MULTAS")
   public java.math.BigDecimal getValorMultas() {
      return valorMultas;
   }

   public void setValorMultas(java.math.BigDecimal valorMultas) {
      this.valorMultas = valorMultas;
   }

   @Column(name = "VALOR_DOCUMENTOS")
   public java.math.BigDecimal getValorDocumentos() {
      return valorDocumentos;
   }

   public void setValorDocumentos(java.math.BigDecimal valorDocumentos) {
      this.valorDocumentos = valorDocumentos;
   }

   @Column(name = "TURNO")
   public java.lang.String getTurno() {
      return turno;
   }

   public void setTurno(java.lang.String turno) {
      this.turno = turno;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "REVISADO")
   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }
	public String toString() {
		return ToStringer.toString((CajaSesionIf)this);
	}
}
