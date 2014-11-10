package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolPagoDetalleIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getRolpagoId();

   void setRolpagoId(java.lang.Long rolpagoId);

   java.lang.Long getContratoId();

   void setContratoId(java.lang.Long contratoId);

   java.lang.Long getRubroId();

   void setRubroId(java.lang.Long rubroId);

   java.lang.Long getRubroEventualId();

   void setRubroEventualId(java.lang.Long rubroEventualId);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.String getObservacion();

   void setObservacion(java.lang.String observacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.sql.Date getFechaPago();

   void setFechaPago(java.sql.Date fechaPago);

   java.lang.Long getTipoPagoId();

   void setTipoPagoId(java.lang.Long tipoPagoId);

   java.lang.Long getCuentaBancariaId();

   void setCuentaBancariaId(java.lang.Long cuentaBancariaId);

   java.lang.String getPreimpreso();

   void setPreimpreso(java.lang.String preimpreso);

   java.lang.Long getAsientoId();

   void setAsientoId(java.lang.Long asientoId);


}
