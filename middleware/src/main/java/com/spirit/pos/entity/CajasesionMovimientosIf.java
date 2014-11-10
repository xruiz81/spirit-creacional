package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CajasesionMovimientosIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getCajasesionId();

   void setCajasesionId(java.lang.Long cajasesionId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.String getTipomovimiento();

   void setTipomovimiento(java.lang.String tipomovimiento);

   java.lang.Long getCuentaId();

   void setCuentaId(java.lang.Long cuentaId);

   java.lang.Long getCajadestinoId();

   void setCajadestinoId(java.lang.Long cajadestinoId);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.sql.Timestamp getFecha();

   void setFecha(java.sql.Timestamp fecha);

   java.lang.String getRevisado();

   void setRevisado(java.lang.String revisado);

   java.lang.String getNumDoc();

   void setNumDoc(java.lang.String numDoc);


}
