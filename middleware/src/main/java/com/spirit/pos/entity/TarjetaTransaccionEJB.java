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

@Table(name = "TARJETA_TRANSACCION")
@Entity
public class TarjetaTransaccionEJB implements Serializable, TarjetaTransaccionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoDocumentoId;
   private java.lang.Long documentoId;
   private java.lang.Long tarjetaId;
   private java.sql.Timestamp fecha;
   private java.lang.String referido;
   private java.lang.Long referidoPor;
   private java.lang.Long facturaId;
   private java.math.BigDecimal puntosGanados;
   private java.math.BigDecimal puntosUtilizados;

   public TarjetaTransaccionEJB() {
   }

   public TarjetaTransaccionEJB(com.spirit.pos.entity.TarjetaTransaccionIf value) {
      setId(value.getId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setDocumentoId(value.getDocumentoId());
      setTarjetaId(value.getTarjetaId());
      setFecha(value.getFecha());
      setReferido(value.getReferido());
      setReferidoPor(value.getReferidoPor());
      setFacturaId(value.getFacturaId());
      setPuntosGanados(value.getPuntosGanados());
      setPuntosUtilizados(value.getPuntosUtilizados());
   }

   public java.lang.Long create(com.spirit.pos.entity.TarjetaTransaccionIf value) {
      setId(value.getId());
      setTipoDocumentoId(value.getTipoDocumentoId());
      setDocumentoId(value.getDocumentoId());
      setTarjetaId(value.getTarjetaId());
      setFecha(value.getFecha());
      setReferido(value.getReferido());
      setReferidoPor(value.getReferidoPor());
      setFacturaId(value.getFacturaId());
      setPuntosGanados(value.getPuntosGanados());
      setPuntosUtilizados(value.getPuntosUtilizados());
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

   @Column(name = "TIPO_DOCUMENTO_ID")
   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   @Column(name = "DOCUMENTO_ID")
   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   @Column(name = "TARJETA_ID")
   public java.lang.Long getTarjetaId() {
      return tarjetaId;
   }

   public void setTarjetaId(java.lang.Long tarjetaId) {
      this.tarjetaId = tarjetaId;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "REFERIDO")
   public java.lang.String getReferido() {
      return referido;
   }

   public void setReferido(java.lang.String referido) {
      this.referido = referido;
   }

   @Column(name = "REFERIDO_POR")
   public java.lang.Long getReferidoPor() {
      return referidoPor;
   }

   public void setReferidoPor(java.lang.Long referidoPor) {
      this.referidoPor = referidoPor;
   }

   @Column(name = "FACTURA_ID")
   public java.lang.Long getFacturaId() {
      return facturaId;
   }

   public void setFacturaId(java.lang.Long facturaId) {
      this.facturaId = facturaId;
   }

   @Column(name = "PUNTOS_GANADOS")
   public java.math.BigDecimal getPuntosGanados() {
      return puntosGanados;
   }

   public void setPuntosGanados(java.math.BigDecimal puntosGanados) {
      this.puntosGanados = puntosGanados;
   }

   @Column(name = "PUNTOS_UTILIZADOS")
   public java.math.BigDecimal getPuntosUtilizados() {
      return puntosUtilizados;
   }

   public void setPuntosUtilizados(java.math.BigDecimal puntosUtilizados) {
      this.puntosUtilizados = puntosUtilizados;
   }
	public String toString() {
		return ToStringer.toString((TarjetaTransaccionIf)this);
	}
}
