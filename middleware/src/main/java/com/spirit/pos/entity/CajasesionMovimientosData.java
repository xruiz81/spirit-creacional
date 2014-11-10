package com.spirit.pos.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CajasesionMovimientosData implements CajasesionMovimientosIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long cajasesionId;

   public java.lang.Long getCajasesionId() {
      return cajasesionId;
   }

   public void setCajasesionId(java.lang.Long cajasesionId) {
      this.cajasesionId = cajasesionId;
   }

   private java.math.BigDecimal valor;

   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   private java.lang.String tipomovimiento;

   public java.lang.String getTipomovimiento() {
      return tipomovimiento;
   }

   public void setTipomovimiento(java.lang.String tipomovimiento) {
      this.tipomovimiento = tipomovimiento;
   }

   private java.lang.Long cuentaId;

   public java.lang.Long getCuentaId() {
      return cuentaId;
   }

   public void setCuentaId(java.lang.Long cuentaId) {
      this.cuentaId = cuentaId;
   }

   private java.lang.Long cajadestinoId;

   public java.lang.Long getCajadestinoId() {
      return cajadestinoId;
   }

   public void setCajadestinoId(java.lang.Long cajadestinoId) {
      this.cajadestinoId = cajadestinoId;
   }

   private java.lang.String observacion;

   public java.lang.String getObservacion() {
      return observacion;
   }

   public void setObservacion(java.lang.String observacion) {
      this.observacion = observacion;
   }

   private java.sql.Timestamp fecha;

   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   private java.lang.String revisado;

   public java.lang.String getRevisado() {
      return revisado;
   }

   public void setRevisado(java.lang.String revisado) {
      this.revisado = revisado;
   }

   private java.lang.String numDoc;

   public java.lang.String getNumDoc() {
      return numDoc;
   }

   public void setNumDoc(java.lang.String numDoc) {
      this.numDoc = numDoc;
   }
   public CajasesionMovimientosData() {
   }

   public CajasesionMovimientosData(com.spirit.pos.entity.CajasesionMovimientosIf value) {
      setId(value.getId());
      setCajasesionId(value.getCajasesionId());
      setValor(value.getValor());
      setTipomovimiento(value.getTipomovimiento());
      setCuentaId(value.getCuentaId());
      setCajadestinoId(value.getCajadestinoId());
      setObservacion(value.getObservacion());
      setFecha(value.getFecha());
      setRevisado(value.getRevisado());
      setNumDoc(value.getNumDoc());
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
		return ToStringer.toString((CajasesionMovimientosIf)this);
	}
}
