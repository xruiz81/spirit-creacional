package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class TarjetaTransaccionData implements TarjetaTransaccionIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long tipoDocumentoId;

   public java.lang.Long getTipoDocumentoId() {
      return tipoDocumentoId;
   }

   public void setTipoDocumentoId(java.lang.Long tipoDocumentoId) {
      this.tipoDocumentoId = tipoDocumentoId;
   }

   private java.lang.Long documentoId;

   public java.lang.Long getDocumentoId() {
      return documentoId;
   }

   public void setDocumentoId(java.lang.Long documentoId) {
      this.documentoId = documentoId;
   }

   private java.lang.Long tarjetaId;

   public java.lang.Long getTarjetaId() {
      return tarjetaId;
   }

   public void setTarjetaId(java.lang.Long tarjetaId) {
      this.tarjetaId = tarjetaId;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.lang.String referido;

   public java.lang.String getReferido() {
      return referido;
   }

   public void setReferido(java.lang.String referido) {
      this.referido = referido;
   }

   private java.lang.Long referidoPor;

   public java.lang.Long getReferidoPor() {
      return referidoPor;
   }

   public void setReferidoPor(java.lang.Long referidoPor) {
      this.referidoPor = referidoPor;
   }

   private java.lang.Long facturaId;

   public java.lang.Long getFacturaId() {
      return facturaId;
   }

   public void setFacturaId(java.lang.Long facturaId) {
      this.facturaId = facturaId;
   }

   private java.math.BigDecimal puntosGanados;

   public java.math.BigDecimal getPuntosGanados() {
      return puntosGanados;
   }

   public void setPuntosGanados(java.math.BigDecimal puntosGanados) {
      this.puntosGanados = puntosGanados;
   }

   private java.math.BigDecimal puntosUtilizados;

   public java.math.BigDecimal getPuntosUtilizados() {
      return puntosUtilizados;
   }

   public void setPuntosUtilizados(java.math.BigDecimal puntosUtilizados) {
      this.puntosUtilizados = puntosUtilizados;
   }
   public TarjetaTransaccionData() {
   }

   public TarjetaTransaccionData(com.spirit.pos.entity.TarjetaTransaccionIf value) {
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



	public String toString() {
		return ToStringer.toString((TarjetaTransaccionIf)this);
	}
}
